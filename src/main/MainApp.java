package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.controller.LoginController;
import main.controller.MainController;
import main.services.AdminServer;
import main.utils.SimpleTools;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainApp extends Application {
    private Stage stage;
    private final double MINIMUM_WINDOW_WIDTH = 600;
    private final double MINIMUM_WINDOW_HEIGHT = 500;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("Admin");
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        goLogin();
        stage.show();
    }

    public void goLogin(){
        try {
            LoginController login = (LoginController) replaceSceneContent("../resources/Login.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void goMain(){
        try {
            MainController main = (MainController) replaceSceneContent("../resources/Main.fxml");
            main.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void adminLogin(String id,String password){
        SimpleTools simpleTools = new SimpleTools();
        AdminServer adminServer = new AdminServer();

        boolean flag;
        flag = adminServer.adminLogin(id,password);

        if (flag) {
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "Remind", "Info", "登录成功");
            goMain();
        } else {
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "Remind", "Info", "登录失败");
        }
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = MainApp.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(MainApp.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page, 800, 732);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
