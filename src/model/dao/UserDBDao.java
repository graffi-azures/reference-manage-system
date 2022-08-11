package model.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.entity.Help;
import model.entity.User;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDBDao {
    public User getUser(String username,String userpwd){
        String sql = "select * from user where 1=1 and username ="+"'"+username+"'"+"and userpwd ="+"'"+userpwd+"'";
        Connection connection = DBConnection.getConnection();
        User user = new User();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setUserpwd(resultSet.getString("userpwd"));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;

    }
    public ObservableList<User> findlikeName(String name) {
        String sql = "select * from user where username like '%" + name + "%' order by id asc";
        Connection connection = DBConnection.getConnection();
        ObservableList<User> users = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));

                users.add(user);

            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean addUser(User user){
        return false;
    }

    public String findUser(String username){
        return null;
    }
}
