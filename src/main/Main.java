package main;
	
import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class Main extends Application {
	
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root= FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
		Scene scene=new Scene(root, 520, 387);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Sports Organizer");
		primaryStage.show();
	}
	
	public void bballInfoPage(ActionEvent e) throws IOException {
		Node node=(Node) e.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		 Parent root = FXMLLoader.load(getClass().getResource("/fxml/BBallPage1.fxml"));/* Exception */
		  Scene scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	     
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
