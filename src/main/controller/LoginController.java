package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import main.services.AdminServer;
import main.utils.SimpleTools;

public class LoginController {
    @FXML
    private Hyperlink login;

    @FXML
    private PasswordField adminPw;

    @FXML
    private TextField adminID;

    @FXML
    void loginAction(ActionEvent event) {
        String adminIDText = adminID.getText();
        String adminPwText = adminPw.getText();

        SimpleTools simpleTools = new SimpleTools();
        AdminServer adminServer = new AdminServer();

        boolean flag;
        flag = adminServer.adminLogin(adminIDText,adminPwText);

        if (flag) {
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "Remind", "Info", "登录成功");
        } else {
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "Remind", "Info", "登录失败");
        }
    }
}
