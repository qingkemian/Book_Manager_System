package main.controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import main.MainApp;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController  implements Initializable {

    private MainApp application;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setApp(MainApp application){
        this.application = application;
    }

}
