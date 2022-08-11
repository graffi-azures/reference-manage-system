package model.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.entity.Reference;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReferenceDBDao {
    public ObservableList<Reference> findAll(){
        String sql = "select * from reference where isdelete = 0 order by id asc";
        Connection connection = DBConnection.getConnection();
        ObservableList<Reference> references = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Reference reference = new Reference();
                reference.setAuthor(resultSet.getString(2));
                reference.setYear(resultSet.getInt(3));
                reference.setTitle(resultSet.getString(4));
                reference.setRating(resultSet.getString(5));
                reference.setJournal(resultSet.getString(6));
                reference.setLastupdate(resultSet.getString(7));
                reference.setType(resultSet.getString(8));
                reference.setLocalsource(resultSet.getString(17));
                reference.setId(resultSet.getString(1));

                references.add(reference);

            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return references;
    }

    public ObservableList<Reference> findLastone(){
        String sql = "select * from reference order by id desc limit 3";
        Connection connection = DBConnection.getConnection();
        ObservableList<Reference> references = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Reference reference = new Reference();
                reference.setAuthor(resultSet.getString(2));
                reference.setYear(resultSet.getInt(3));
                reference.setTitle(resultSet.getString(4));
                reference.setRating(resultSet.getString(5));
                reference.setJournal(resultSet.getString(6));
                reference.setLastupdate(resultSet.getString(7));
                reference.setType(resultSet.getString(8));
                reference.setLocalsource(resultSet.getString(17));
                reference.setId(resultSet.getString(1));

                references.add(reference);

            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return references;
    }

    public ObservableList<Reference> findUnfiled(){
        String sql = "select * from reference where categoryid is null order by id asc";
        Connection connection = DBConnection.getConnection();
        ObservableList<Reference> references = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Reference reference = new Reference();
                reference.setAuthor(resultSet.getString(2));
                reference.setYear(resultSet.getInt(3));
                reference.setTitle(resultSet.getString(4));
                reference.setRating(resultSet.getString(5));
                reference.setJournal(resultSet.getString(6));
                reference.setLastupdate(resultSet.getString(7));
                reference.setType(resultSet.getString(8));
                reference.setLocalsource(resultSet.getString(17));
                reference.setId(resultSet.getString(1));

                references.add(reference);

            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return references;
    }

    public ObservableList<Reference> findTrash(){
        String sql = "select * from reference where isdelete = 1 order by id asc";
        Connection connection = DBConnection.getConnection();
        ObservableList<Reference> references = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Reference reference = new Reference();
                reference.setAuthor(resultSet.getString(2));
                reference.setYear(resultSet.getInt(3));
                reference.setTitle(resultSet.getString(4));
                reference.setRating(resultSet.getString(5));
                reference.setJournal(resultSet.getString(6));
                reference.setLastupdate(resultSet.getString(7));
                reference.setType(resultSet.getString(8));
                reference.setLocalsource(resultSet.getString(17));
                reference.setId(resultSet.getString(1));

                references.add(reference);

            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return references;
    }

    public boolean insertRef(Reference reference) throws SQLException {
        if (!findTitle(reference.getTitle())){
            return false;
        }
        String sql = "insert into reference values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement psmt = connection.prepareStatement(sql);

            psmt.setString(1,null);

            psmt.setString(2, reference.getAuthor());

            psmt.setInt(3, reference.getYear());

            psmt.setString(4, reference.getTitle());

            psmt.setString(5, reference.getRating());

            psmt.setString(6, reference.getJournal());

            psmt.setString(7, reference.getLastupdate());

            psmt.setString(8, reference.getType());

            psmt.setInt(9, reference.getCategoryid());

            psmt.setInt(10, reference.getIsdelete());

            psmt.setString(11, reference.getGroup());

            psmt.setString(12, reference.getUrl());

            psmt.setString(13,reference.getCitation());

            psmt.setString(14,reference.getSource());

            psmt.setString(15,reference.getFrom());

            psmt.setString(16,reference.getOperator());

            psmt.setString(17,reference.getLocalsource());

            psmt.execute();
            psmt.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean findTitle(String title) {
        Connection connection = DBConnection.getConnection();
        List<String> strs = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from reference where title='"+title+"'");
            while (resultSet.next()){
                String titlename = resultSet.getString(4);
                System.out.println(titlename);
                strs.add(titlename);
            }
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (strs.size()!=0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("非法输入或该文献已存在");
            alert.showAndWait();
            return false;
        }else
            return true;
    }

    public ObservableList<Reference> findJournal(String journal){
        String sql = "select * from reference where journal ='"+journal+"' order by id asc";
        Connection connection = DBConnection.getConnection();
        ObservableList<Reference> references = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Reference reference = new Reference();
                reference.setAuthor(resultSet.getString(2));
                reference.setYear(resultSet.getInt(3));
                reference.setTitle(resultSet.getString(4));
                reference.setRating(resultSet.getString(5));
                reference.setJournal(resultSet.getString(6));
                reference.setLastupdate(resultSet.getString(7));
                reference.setType(resultSet.getString(8));
                reference.setLocalsource(resultSet.getString(17));
                reference.setId(resultSet.getString(1));

                references.add(reference);

            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return references;
    }

    public void deleteSelect(String id){
        String sql="delete from reference where id=?";
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement psmt=connection.prepareStatement(sql);
            psmt.setString(1,id);
            psmt.execute();
            psmt.close();
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSelect(Reference reference){
        String sql="update reference set author=?,year=?,title=?,rating=?,journal=?,lastupdate=?,type=?,localsource=? where id=?";
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement psmt=connection.prepareStatement(sql);

            psmt.setString(1, reference.getAuthor());
            psmt.setInt(2, reference.getYear());
            psmt.setString(3, reference.getTitle());
            psmt.setString(4, reference.getRating());
            psmt.setString(5,reference.getJournal());
            psmt.setString(6,reference.getLastupdate());
            psmt.setString(7,reference.getType());
            psmt.setString(8,reference.getLocalsource());
            psmt.setString(9,reference.getId());
            psmt.execute();
            psmt.close();
            return;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //标题模糊查询
    public ObservableList<Reference> findlikeTitle(String title) {
        String sql = "select * from reference where title like '%" + title + "%' order by id asc";
        Connection connection = DBConnection.getConnection();
        ObservableList<Reference> references = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Reference reference = new Reference();
                reference.setAuthor(resultSet.getString(2));
                reference.setYear(resultSet.getInt(3));
                reference.setTitle(resultSet.getString(4));
                reference.setRating(resultSet.getString(5));
                reference.setJournal(resultSet.getString(6));
                reference.setLastupdate(resultSet.getString(7));
                reference.setType(resultSet.getString(8));
                reference.setLocalsource(resultSet.getString(17));
                reference.setId(resultSet.getString(1));

                references.add(reference);

            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return references;
    }
    //多条件精确搜索
    public ObservableList<Reference> findMultip(String author,Integer year,String title) {
        String sql = "select * from reference where author ='" + author + "'and year = "+year+" and title ='"+title+"' order by id asc";
        Connection connection = DBConnection.getConnection();
        ObservableList<Reference> references = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Reference reference = new Reference();
                reference.setAuthor(resultSet.getString(2));
                reference.setYear(resultSet.getInt(3));
                reference.setTitle(resultSet.getString(4));
                reference.setRating(resultSet.getString(5));
                reference.setJournal(resultSet.getString(6));
                reference.setLastupdate(resultSet.getString(7));
                reference.setType(resultSet.getString(8));
                reference.setLocalsource(resultSet.getString(17));
                reference.setId(resultSet.getString(1));

                references.add(reference);

            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return references;
    }
}
