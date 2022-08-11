package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dao.ReferenceDBDao;
import model.entity.Reference;
import utils.StageManager;

import java.net.URL;
import java.util.ResourceBundle;

public class PrevController implements Initializable {
    public TableColumn clPrew;
    public TableColumn clPcontent;
    public TableView tvPrew;
    @FXML
    private MainController mainController;
    Reference creference = new Reference();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainController = (MainController) StageManager.CONTROLLER.get("MainController");
        creference = mainController.shareref;
        creference.setFrom(null);
        ReferenceDBDao referenceDBDao = new ReferenceDBDao();
        ObservableList<Reference> list =  referenceDBDao.findMultip(creference.getAuthor(),creference.getYear(),creference.getTitle());
        clPrew.setCellValueFactory(new PropertyValueFactory("id"));//映射
        clPcontent.setCellValueFactory(new PropertyValueFactory("title"));

        tvPrew.getItems().add(creference);
//        tvPrew.setItems(list); //tableview添加list
    }
}
