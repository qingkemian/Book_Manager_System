package main.controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import main.MainApp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.MenuItem;

import javafx.event.ActionEvent;
import main.utils.SimpleTools;

public class MainController  implements Initializable {

    @FXML
    private MenuItem menuReader;

    @FXML
    private MenuItem menuBook;

    @FXML
    private MenuItem menuBr;

    @FXML
    private MenuItem exit;

    @FXML
    private AnchorPane mainFrameAnchorPane;

    private MainApp application;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setApp(MainApp application){
        this.application = application;
    }

    public void to_reader(ActionEvent event) {
        AnchorPane pane = new MainApp().initReader();
        mainFrameAnchorPane.getChildren().clear();
        mainFrameAnchorPane.getChildren().add(pane);
    }

    public void to_book(ActionEvent event) {
        AnchorPane pane = new MainApp().initBook();
        mainFrameAnchorPane.getChildren().clear();
        mainFrameAnchorPane.getChildren().add(pane);
    }

    public void to_br(ActionEvent event) {
        AnchorPane pane = new MainApp().initBR();
        mainFrameAnchorPane.getChildren().clear();
        mainFrameAnchorPane.getChildren().add(pane);
    }

    @FXML
    void outAction(ActionEvent event) {
        SimpleTools simpleTools = new SimpleTools();

        try{
            application.adminOut();
        } catch (Exception e) {
            simpleTools.informationDialog(Alert.AlertType.WARNING,"Remind", "warning", "Error");
        }
    }
}
