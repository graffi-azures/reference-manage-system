package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dao.MessageDBDao;
import model.entity.Message;
import model.entity.Reference;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MsgController implements Initializable {
    public DatePicker datePickerTextField;
    public TextField messageNameField;
    public TextField messageContentField;
    public Button addButton;
    public Button updateButton;
    public Button deleteButton;
    public TableView MessageTypeManageView;
    public TableColumn messageIdColumn;
    public TableColumn messageNameColumn;
    public TableColumn messageContentColumn;
    public TableColumn messageTimeColumn;

//    用一个String来存id
    String selectid = new String();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MessageDBDao messageDBDao = new MessageDBDao();
        ObservableList<Message> list = messageDBDao.findAllMsgList();

        messageIdColumn.setCellValueFactory(new PropertyValueFactory("id"));//映射
        messageNameColumn.setCellValueFactory(new PropertyValueFactory("username"));
        messageContentColumn.setCellValueFactory(new PropertyValueFactory("content"));
        messageTimeColumn.setCellValueFactory(new PropertyValueFactory("time"));

        MessageTypeManageView.setItems(list);

        MessageTypeManageView.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showMessageDetails((Message) newValue)));

    }

    public void showMessageDetails(Message newValue) {
        //是否选中
        if(newValue==null){
            return;
        } else {
            //如果表格行被选中，把数据显示在输入框中
            selectid = newValue.getId();
            messageNameField.setText(newValue.getUsername());
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            datePickerTextField.setValue(LocalDate.parse(newValue.getTime(),fmt));
            messageContentField.setText(newValue.getContent());
        }
    }

    public void updateButton_event(ActionEvent actionEvent) {
        MessageDBDao messageDBDao = new MessageDBDao();
        String username = messageNameField.getText();
        String content = messageContentField.getText();
        String time = datePickerTextField.getValue().toString();
        Message message = new Message(selectid,content,username,time);
        messageDBDao.updatebyselect(message);
        showAllMsg();
        alertInputFail("修改成功");
    }

    public void deleteButton_event(ActionEvent actionEvent) {
        MessageDBDao messageDBDao = new MessageDBDao();
        String name = messageNameField.getText();
        String content = messageContentField.getText();
        messageDBDao.deletebyselect(name,content);
        showAllMsg();
        alertInputFail("删除成功");
    }


    public void addButton_event(ActionEvent actionEvent) {
        MessageDBDao messageDBDao = new MessageDBDao();
        String name = messageNameField.getText();
        String date = datePickerTextField.getValue().toString();
        String content = messageContentField.getText();
        if(name==null||name.equals("")||content==null||content.equals("")||date==null||date.equals("")){
            alertInputFail("输入信息不能为空");
        }
        Message message = new Message();
        message.setUsername(name);
        message.setContent(content);
        message.setTime(date);
        messageDBDao.insertMsg(message);
        showAllMsg();
        alertInputFail("发布成功");
    }

    private void showAllMsg() {
        MessageDBDao messageDBDao = new MessageDBDao();
        ObservableList<Message> list = messageDBDao.findAllMsgList();
        messageIdColumn.setCellValueFactory(new PropertyValueFactory("id"));//映射
        messageNameColumn.setCellValueFactory(new PropertyValueFactory("username"));
        messageContentColumn.setCellValueFactory(new PropertyValueFactory("content"));
        messageTimeColumn.setCellValueFactory(new PropertyValueFactory("time"));
        MessageTypeManageView.setItems(list);
    }

    private void alertInputFail(String string) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().lookupButton(ButtonType.CLOSE);
        alert.setContentText(string);
        alert.showAndWait();
    }
}
