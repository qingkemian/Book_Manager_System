package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import main.MainApp;
import main.services.AdminServer;
import main.utils.SimpleTools;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Hyperlink login;

    @FXML
    private PasswordField adminPw;

    @FXML
    private TextField adminID;

    private MainApp application;

    public void setApp(MainApp application){
        this.application = application;
    }

    @FXML
    void loginAction(ActionEvent event) {
        SimpleTools simpleTools = new SimpleTools();

        try{
            application.adminLogin(adminID.getText(),adminPw.getText());
        }catch (Exception e)
        {
            simpleTools.informationDialog(Alert.AlertType.WARNING,"Remind", "warning", "Error");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
