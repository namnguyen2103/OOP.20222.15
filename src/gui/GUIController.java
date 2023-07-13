package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class GUIController {

    @FXML
    private MenuItem closeButton;

    @FXML
    void handleCloseButtonAction(ActionEvent event) {
    	Stage stage = (Stage) closeButton.getParentPopup().getOwnerWindow();
        stage.close();
    }

}
