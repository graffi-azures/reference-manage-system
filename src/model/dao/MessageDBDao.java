package model.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.entity.Message;
import model.entity.Reference;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDBDao {
    public boolean insertMsg(Message message) {
        if (!findContent(message.getContent())){
            return false;
        }
        String sql = "insert into message values (?,?,?,?)";
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement psmt = connection.prepareStatement(sql);

            psmt.setString(1,null);

            psmt.setString(2, message.getContent());

            psmt.setString(3, message.getUsername() );

            psmt.setString(4, message.getTime());

            psmt.execute();
            psmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean findContent(String content) {
        Connection connection = DBConnection.getConnection();
        List<String> strs = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from message where content='"+content+"'");
            while (resultSet.next()){
                String txtcontent = resultSet.getString(1);
                System.out.println(txtcontent);
                strs.add(txtcontent);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (strs.size()!=0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("已经有同样内容的信息存在了");
            alert.showAndWait();
            return false;
        }else
            return true;
    }

    public ObservableList<Message> findAllMsgList(){
        String sql = "select * from message order by id asc";
        Connection connection = DBConnection.getConnection();
        ObservableList<Message> messages = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Message message = new Message();
                message.setId(resultSet.getString(1));
                message.setContent(resultSet.getString(2));
                message.setUsername(resultSet.getString(3));
                message.setTime(resultSet.getString(4));

                messages.add(message);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public boolean deletebyselect(String username,String content){
        String sql="delete from message where 1=1 and username=? and content =?";
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement psmt=connection.prepareStatement(sql);
            psmt.setString(1,username);
            psmt.setString(2,content);
            psmt.execute();
            psmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatebyselect(Message message){
        if (!findContent(message.getContent())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("已存在相同内容的信息");
            alert.showAndWait();
        }
        String sql="update message set content=?,username=?,time=? where id=?";
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement psmt=connection.prepareStatement(sql);

            psmt.setString(1, message.getContent());
            psmt.setString(2, message.getUsername());
            psmt.setString(3, message.getTime());
            psmt.setString(4, message.getId());
            psmt.execute();
            psmt.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
