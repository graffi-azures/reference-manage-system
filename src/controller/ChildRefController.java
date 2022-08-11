package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import utils.StageManager;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class ChildRefController implements Initializable {
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


//    private MainController mainController;

//    public void injectController(MainController mainController){
//        this.mainController = mainController;
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StageManager.CONTROLLER.put("refContentController",this);
        if (txtAuthor.getText()==""){
            txtAuthor.setText(null);
        }
        if (txtYear.getText()==""){
            txtYear.setText(null);
        }
        if (txtTitle.getText()==""){
            alertInputFail("title can't be null,please reinput！");
        }
        comboRating.getItems().addAll("weak","middle","high");
        if (comboRating.getSelectionModel().getSelectedItem()==""){
            comboRating.setSelectionModel(null);
        }
        if (txtJournal.getText()==""){
            txtJournal.setText(null);
        }
//        if (txtLastUpdate.getValue().toString()==""){
//            txtLastUpdate.setValue();
//        }
        if (txtType.getText()==""){
            txtType.setText(null);
        }
        if (txtCategoryid.getText()==""){
            txtCategoryid.setText(null);
        }
        if (txtGroup.getText()==""){
            txtGroup.setText(null);
        }
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
