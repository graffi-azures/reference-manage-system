package controller;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.dao.HelpDBDao;
import model.dao.ReferenceDBDao;
import model.entity.Help;
import model.entity.Reference;

public class HelpController {
    public TextField txtSrcBox;
    public Button btnSrch;
    public TableView tvHelp;
    public TableColumn clId;
    public TableColumn clContent;

    public void onSearchAction(MouseEvent mouseEvent) {
        HelpDBDao helpDBDao = new HelpDBDao();
        ObservableList<Help> list = helpDBDao.findlikeContent(txtSrcBox.getText());

        clId.setCellValueFactory(new PropertyValueFactory("id"));//映射
        clContent.setCellValueFactory(new PropertyValueFactory("helpcontent"));

        tvHelp.setItems(list); //tableview添加list
    }
}
