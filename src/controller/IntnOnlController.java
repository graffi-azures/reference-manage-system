package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class IntnOnlController implements Initializable {
    public VBox iomode;
    public Label labTitle;
    public ToggleButton btnAlRe;
    public ToggleButton btnRcAd;
    public ToggleButton btnUnfi;
    public ToggleButton btnTrsh;
    public StackPane leftIO;
    public TitledPane selectgroup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectgroup.setExpanded(true);
        selectgroup.setAnimated(false);

        final ContextMenu cm = new ContextMenu();
        MenuItem creatitem = new MenuItem("create group");
        MenuItem deleteitem = new MenuItem("delete group");
        creatitem.setOnAction((ActionEvent e) -> {
            VBox gitems = new VBox();
            gitems.setPrefHeight(400);
            List<Button> glist = new ArrayList<>();
            Button btncg = new Button("New Group");
            glist.add(btncg);
            for (Button btn : glist) {
                gitems.getChildren().addAll(btn);
            }
            selectgroup.setContent(gitems);
        });
        cm.getItems().add(creatitem);
        cm.getItems().add(deleteitem);
        selectgroup.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getButton() == MouseButton.SECONDARY && e.getClickCount() == 1) {
                cm.show(selectgroup, e.getScreenX(), e.getScreenY());
            }
        });
    }
}
