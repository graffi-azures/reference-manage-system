package model.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.entity.Help;
import model.entity.Reference;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelpDBDao {
    public ObservableList<Help> findlikeContent(String content) {
        String sql = "select * from help where helpcontent like '%" + content + "%' order by id asc";
        Connection connection = DBConnection.getConnection();
        ObservableList<Help> helps = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Help help = new Help();
                help.setId(resultSet.getString(1));
                help.setHelpcontent(resultSet.getString(2));

                helps.add(help);

            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return helps;
    }
}
