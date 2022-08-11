package controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.dao.UserDBDao;
import model.entity.User;
import utils.StageManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public TextField txtUsername;

    public PasswordField txtPassword;

    public Button btnLogin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StageManager.CONTROLLER.put("Login",this);
    }

    public void loginButtonClicked() throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        UserDBDao userDBDao = new UserDBDao();
        User trueuser = userDBDao.getUser(username,password);
        System.out.println(trueuser.toString());

        if(trueuser.getUsername().equals(username)&&trueuser.getUserpwd().equals(password)){
            openIndex();
        }else
            alertLoginFail();

    }

    private void alertLoginFail() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("用户名或者密码错误");
        alert.showAndWait();
    }

    private void openIndex() throws IOException {
        Stage mainstage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/main.fxml"));
        Parent loader = fxmlLoader.load();
        mainstage.setScene(new Scene(loader));
        mainstage.show();

        StageManager.STAGE.get("Login").close();//标记是Login舞台,其实起始是main的primarystage
        StageManager.STAGE.remove("Login");
    }

}
