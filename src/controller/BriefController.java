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

public class BriefController implements Initializable {
    public TableView tvBrif;
    public TableColumn clBrief;
    public TableColumn clBcontent;
    @FXML
    private MainController mainController;

    Reference breference = new Reference();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mainController = (MainController) StageManager.CONTROLLER.get("MainController");
        breference = mainController.shareref;
        breference.setFrom(null);
        ReferenceDBDao referenceDBDao = new ReferenceDBDao();
        ObservableList<Reference> list =  referenceDBDao.findMultip(breference.getAuthor(),breference.getYear(),breference.getTitle());
        clBrief.setCellValueFactory(new PropertyValueFactory("id"));//映射
        clBcontent.setCellValueFactory(new PropertyValueFactory("author"));

        tvBrif.getItems().add(breference);
//        tvBrif.setItems(list); //tableview添加list
    }
}