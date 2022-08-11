package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import model.dao.ReferenceDBDao;
import model.dao.UserDBDao;
import model.entity.Reference;
import model.entity.User;
import utils.StageManager;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

public class EditRefController implements Initializable {
    public Tab refTab;
    public Tab ataTab;
    public Button btnNewRef;

    @FXML
    private ChildEditRefController refContentController;
    @FXML
    private ChildEditAtaController ataContentController;
    @FXML
    private LoginController loginController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        refContentController.injectController(this);
//        ataContentController.injectController(this);
        refContentController = (ChildEditRefController) StageManager.CONTROLLER.get("refEditController");
        ataContentController = (ChildEditAtaController) StageManager.CONTROLLER.get("ataEditController");
    }

    public void onNewRefClickAction(MouseEvent mouseEvent) throws SQLException, ParseException {
        ReferenceDBDao referenceDBDao = new ReferenceDBDao();
        String author = refContentController.txtAuthor.getText();
        Integer year = Integer.valueOf(refContentController.txtYear.getText());
        String title = refContentController.txtTitle.getText();
        String rating = refContentController.comboRating.getSelectionModel().getSelectedItem().toString();
        String journal = refContentController.txtJournal.getText();
        String lastupdate = refContentController.txtLastUpdate.getValue().toString();
        String type = refContentController.txtType.getText();
        Integer categoryid = Integer.valueOf(refContentController.txtCategoryid.getText());
        String group = refContentController.txtGroup.getText();
        String url = ataContentController.txtareaAta.getText();
        Reference reference = new Reference(author,year,title,rating,journal,lastupdate,type,categoryid,0,group,url);
        reference.setCitation("");
        reference.setSource("");
        reference.setFrom("");
        reference.setOperator("");
        reference.setLocalsource("");
        referenceDBDao.updateSelect(reference);
        //categoryid,group要和operator关联起来
        if (categoryid!=null||!categoryid.equals("")||group!=null||!group.equals("")){
            loginController = (LoginController) StageManager.CONTROLLER.get("Login");
            UserDBDao userDBDao = new UserDBDao();
            User user = userDBDao.getUser(loginController.txtUsername.getText(),loginController.txtPassword.getText());
            reference.setOperator(user.getUsername());
        }else
            reference.setOperator(null);

        alertInputFail("修改成功");
//        System.out.println(author+year+title+rating+journal+lastupdate.toString()+type+categoryid+group+url);
        StageManager.STAGE.get("NewRef").close();
        StageManager.STAGE.remove("NewRef");
    }

    private void alertInputFail(String string) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().lookupButton(ButtonType.CLOSE);
        alert.setContentText(string);
        alert.showAndWait();
    }
}
