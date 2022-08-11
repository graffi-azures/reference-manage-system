package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.entity.Reference;
import utils.StageManager;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class ChildEditAtaController implements Initializable {
    public TextArea txtareaAta;
    public Button btnSelecFile;
    public VBox ataContent;

    @FXML
    private MainController mainController;

    Reference editref = new Reference();
//    public void injectController(NewRefController newRefController){
//        this.newRefController = newRefController;
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StageManager.CONTROLLER.put("ataEditController",this);
        mainController = (MainController) StageManager.CONTROLLER.get("MainController");
        editref = mainController.shareref;
        editref.setFrom(null);
        txtareaAta.setText(editref.getUrl());
    }

    public void onSelecFileAction(MouseEvent mouseEvent) {
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        fc.setTitle("选择文献附件");
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
                txtareaAta.appendText(file.getAbsolutePath());
            }
        });

    }
}

