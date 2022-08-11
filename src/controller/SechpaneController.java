package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.dao.ReferenceDBDao;
import model.entity.Reference;
import utils.StageManager;

import java.net.URL;
import java.util.ResourceBundle;

public class SechpaneController implements Initializable {
    public Button btnSearch;
    public TextField txtAuthor;
    public TextField txtYear;
    public TextField txtTitle;

    @FXML
    private MainController mainController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onMulSearchAction(MouseEvent mouseEvent) {
        ReferenceDBDao referenceDBDao = new ReferenceDBDao();
        ObservableList<Reference> list = referenceDBDao.findMultip(txtAuthor.getText(),Integer.parseInt(txtYear.getText()),txtTitle.getText());
        mainController = (MainController) StageManager.CONTROLLER.get("MainController");
        mainController.clAuthor.setCellValueFactory(new PropertyValueFactory("author"));//映射
        mainController.clYear.setCellValueFactory(new PropertyValueFactory("year"));
        mainController.clTitle.setCellValueFactory(new PropertyValueFactory("title"));
        mainController.clRating.setCellValueFactory(new PropertyValueFactory("rating"));
        mainController.clJournal.setCellValueFactory(new PropertyValueFactory("journal"));
        mainController.clUpdated.setCellValueFactory(new PropertyValueFactory("lastupdate"));
        mainController.clType.setCellValueFactory(new PropertyValueFactory("type"));

        mainController.tvCenter.setItems(list); //tableview添加list
    }
}
