package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.dao.HelpDBDao;
import model.dao.ReferenceDBDao;
import model.dao.UserDBDao;
import model.entity.Help;
import model.entity.Reference;
import model.entity.User;
import utils.StageManager;

import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

public class ShareController implements Initializable {
    public TextField txtUsername;
    public Button btnSearch;
    public TableView tvUser;
    public TableColumn clId;
    public TableColumn clUser;
    public Button btnShare;

    @FXML
    private MainController mainController;

    Reference acceptor = new Reference();

    public void onSearchAction(MouseEvent mouseEvent) {
        UserDBDao userDBDao = new UserDBDao();
        ObservableList<User> list = userDBDao.findlikeName(txtUsername.getText());

        clId.setCellValueFactory(new PropertyValueFactory("id"));//映射
        clUser.setCellValueFactory(new PropertyValueFactory("username"));

        tvUser.setItems(list); //tableview添加list
    }

    public void onShareAction(MouseEvent mouseEvent) throws SQLException {
        ReferenceDBDao referenceDBDao = new ReferenceDBDao();
        referenceDBDao.insertRef(acceptor);
        alertInputFail("分享成功");
//        System.out.println(author+year+title+rating+journal+lastupdate.toString()+type+categoryid+group+url);
        StageManager.STAGE.get("Share").close();
        StageManager.STAGE.remove("Share");
    }

    private void alertInputFail(String string) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().lookupButton(ButtonType.CLOSE);
        alert.setContentText(string);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StageManager.CONTROLLER.put("Share",this);
        tvUser.getSelectionModel().selectedItemProperty().addListener(
                ((observable,oldValue, newValue) -> getUserDetails((User) newValue)));
    }

    private void getUserDetails(User newValue) {
        mainController = (MainController) StageManager.CONTROLLER.get("MainController");
        acceptor = mainController.shareref;
        acceptor.setOperator(newValue.getUsername());
        acceptor.setCategoryid(0);
        acceptor.setIsdelete(0);
        acceptor.setGroup(null);
        acceptor.setUrl(null);
        acceptor.setCitation(null);
        acceptor.setSource("Internet");
        acceptor.setLocalsource(null);
    }
}
