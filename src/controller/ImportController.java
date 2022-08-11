package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.dao.ReferenceDBDao;
import model.entity.Reference;
import utils.StageManager;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class ImportController implements Initializable {
    public TextField txtFiled;
    public Button btnchoose;
    public ComboBox cbxIop;
    public ChoiceBox cbxDup;
    public ChoiceBox cbxTsl;
    public Button btnCel;
    public Button btnImp;

    public String path;
    @FXML
    private MainController mainController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StageManager.CONTROLLER.put("Import",this);
        mainController = (MainController) StageManager.CONTROLLER.get("MainController");
        cbxIop.getItems().addAll("PDF","Text","Word");
        cbxIop.getSelectionModel().select(0);
        cbxDup.getItems().addAll("Import all");
        cbxDup.getSelectionModel().select(0);
        cbxTsl.getItems().addAll("No translation");
        cbxTsl.getSelectionModel().select(0);
    }

    public void onChooseClickAction(MouseEvent mouseEvent) {
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        fc.setTitle("选择导入文件");
        fc.setInitialDirectory(new File("D:\\Program Files\\JetBrains\\IntelliJ IDEA 2019.3.1\\IdeaProjects\\rmcs\\src\\resources"));
        List<File> fileList = fc.showOpenMultipleDialog(stage);
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Word","*.docx","*.doc"),
                new FileChooser.ExtensionFilter("PDF","*.pdf"),
                new FileChooser.ExtensionFilter("Text","*.txt"),
                new FileChooser.ExtensionFilter("All Filetype","*.*"));
        if(fileList == null){
            return;
        }
        fileList.forEach(new Consumer<File>() {
            @Override
            public void accept(File file) {
                System.out.println(file.getAbsolutePath());
                txtFiled.setText(file.getName().substring(0,file.getName().lastIndexOf(".")));
                path = file.getAbsolutePath();
            }
        });
    }

    public void onCancleAction(MouseEvent mouseEvent) {
        StageManager.STAGE.get("ImportFile").close();
        StageManager.STAGE.remove("ImportFile");
    }

    public void onImportClickAction(MouseEvent mouseEvent) throws SQLException {
        ReferenceDBDao referenceDBDao = new ReferenceDBDao();
        String title = txtFiled.getText();
        String localsource = path;
        Reference reference = new Reference("",2000,title,"","","","",0,0,"","");
        reference.setCitation("");
        reference.setSource("local");
        reference.setFrom("");
        reference.setOperator("");
        reference.setLocalsource(localsource);
        if (referenceDBDao.findTitle(reference.getTitle())){
            referenceDBDao.insertRef(reference);
            mainController.showAllRef();
            alertInputFail("导入成功");
        }else
            alertInputFail("非法输入或该文献已存在");
    }
    private void alertInputFail(String string) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().lookupButton(ButtonType.CLOSE);
        alert.setContentText(string);
        alert.showAndWait();
    }
}
