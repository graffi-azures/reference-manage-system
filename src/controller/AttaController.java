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

public class AttaController implements Initializable {
    public TableView tvAtta;
    public TableColumn clAtta;
    public TableColumn clAcontent;
    @FXML
    private MainController mainController;
    Reference areference = new Reference();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainController = (MainController) StageManager.CONTROLLER.get("MainController");
        areference = mainController.shareref;
        areference.setFrom(null);
        ReferenceDBDao referenceDBDao = new ReferenceDBDao();
        ObservableList<Reference> list =  referenceDBDao.findMultip(areference.getAuthor(),areference.getYear(),areference.getTitle());
        clAtta.setCellValueFactory(new PropertyValueFactory("id"));//映射
        clAcontent.setCellValueFactory(new PropertyValueFactory("localsource"));

        tvAtta.getItems().add(areference);
//        tvAtta.setItems(list); //tableview添加list
    }
}
