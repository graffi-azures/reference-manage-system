package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.entity.Reference;
import utils.StageManager;

import java.net.URL;
import java.util.ResourceBundle;

public class ChildEditRefController implements Initializable {
    public TextField txtAuthor;
    public TextField txtYear;
    public TextField txtTitle;
    //    public TextField txtRating;
    public ComboBox comboRating;
    public TextField txtJournal;
    public DatePicker txtLastUpdate;
    public TextField txtType;
    public TextField txtCategoryid;
    public VBox refContent;
    public TextField txtGroup;


    @FXML
    private MainController mainController;

    Reference editref = new Reference();
//    public void injectController(MainController mainController){
//        this.mainController = mainController;
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StageManager.CONTROLLER.put("refEditController",this);
        mainController = (MainController) StageManager.CONTROLLER.get("MainController");
        editref = mainController.shareref;
        editref.setFrom(null);
        txtAuthor.setText(editref.getAuthor());
        txtYear.setText(editref.getYear().toString());
        txtTitle.setText(editref.getTitle());
        txtJournal.setText(editref.getJournal());
        txtType.setText(editref.getType());
//        txtCategoryid.setText(editref.getCategoryid().toString());
        txtGroup.setText(editref.getGroup());
        txtYear.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("[0-9]*")||newValue.length()>4){
                    try {
                        txtYear.setText(oldValue);
                    }catch (Exception e){
                        txtYear.setText("");
                        alertInputFail("只能输入4位非零开头的正整数");
                    }
                }
            }
        });
//        txtLastUpdate.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                if (!newValue.matches("[0-9]*")||newValue.length()>8){
//                    try {
//                        txtLastUpdate.setText(oldValue);
//                    }catch (Exception e){
//                        txtLastUpdate.clear();
//                        alertInputFail("只能输入8位非零开头的正整数");
//                    }
//                }
//            }
//        });
        txtCategoryid.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("[0-9]{0,2}")||newValue.length()>2){
                    try {
                        txtCategoryid.setText(oldValue);
                    }catch (Exception e){
                        txtCategoryid.clear();
                        alertInputFail("只能输入最多2位正整数");
                    }
                }
            }
        });

    }
    private void alertInputFail(String string) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.getDialogPane().lookupButton(ButtonType.CLOSE);
        alert.setContentText(string);
        alert.showAndWait();
    }
}
