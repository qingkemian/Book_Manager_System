package main.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class SimpleTools {
    public boolean informationDialog(Alert.AlertType alterType, String title, String header, String message) {

        Alert alert = new Alert(alterType, message, new ButtonType("取消", ButtonBar.ButtonData.CANCEL_CLOSE), new ButtonType("确定", ButtonBar.ButtonData.YES));

        alert.setTitle(title);
        alert.setHeaderText(header);

        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
            return true;
        } else {
            return false;
        }
    }
}
