package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUIMain extends Application {
	
	@Override
	public void start (Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/GUI.fxml"));
		Scene scene = new Scene(root, 750, 500);
		stage.setTitle("Project 1");

	    stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}