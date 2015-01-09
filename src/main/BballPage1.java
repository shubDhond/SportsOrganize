package main;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BballPage1 {
	//creates the UI components for this page and references FXML for their placement and attributes
	@FXML private TextField bballTeams;
	@FXML private TextField bballCourts;
	@FXML private TextField bballMatchTime;
	@FXML private CheckBox playoffsBtn;
	//variables to store the info from the text fields and check box
	private boolean playoffs;
	private int teams;
	private int fieldsOfPlay;
	private int time;
	private String x;
	
	//the event listener of the next button on this page
	public void createTournament(ActionEvent e) throws IOException{
		Node node=(Node) e.getSource();
		//gets and sets info after checking for errors such as null pointer exceptions or limits of the info
		if(bballTeams.getText()!=null&&!bballTeams.getText().isEmpty()){
			  teams=Integer.parseInt(bballTeams.getText());
			  try{
				  Integer.parseInt(bballTeams.getText());
			  }catch(NumberFormatException e2){
				 x =JOptionPane.showInputDialog(null, "Error Please enter a number for teams:","ERROR",JOptionPane.ERROR_MESSAGE);
				 teams=Integer.parseInt(x); 
			  }
			  if(teams<2){
				  x =JOptionPane.showInputDialog(null, "Error At least two teams are needed:","ERROR",JOptionPane.ERROR_MESSAGE);
				  teams=Integer.parseInt(x);
			  }
			}
			else{
				 x =JOptionPane.showInputDialog(null, "Error Please enter a number for teams:","ERROR",JOptionPane.ERROR_MESSAGE);
				 teams=Integer.parseInt(x); 
			}
			if(bballCourts.getText()!=null&&!bballCourts.getText().isEmpty()){	
			  fieldsOfPlay=Integer.parseInt(bballCourts.getText());
			  try{
				  Integer.parseInt(bballCourts.getText());
			  }catch(NumberFormatException e2){
				 x =JOptionPane.showInputDialog(null, "Error Please enter a number for courts:","ERROR",JOptionPane.ERROR_MESSAGE);
				 fieldsOfPlay=Integer.parseInt(x); 
			  }
			  if(fieldsOfPlay<1){
				  x =JOptionPane.showInputDialog(null, "Error At least one court is needed:","ERROR",JOptionPane.ERROR_MESSAGE);
				  fieldsOfPlay=Integer.parseInt(x);
			  }
			}
			else{
				x =JOptionPane.showInputDialog(null, "Error Please enter a number for courts:","ERROR",JOptionPane.ERROR_MESSAGE);
				 fieldsOfPlay=Integer.parseInt(x); 
			}
			if(bballMatchTime.getText()!=null&&!bballMatchTime.getText().isEmpty()){
			  time=Integer.parseInt(bballMatchTime.getText());
			  try{
				  time=Integer.parseInt(bballMatchTime.getText());
			  }catch(NumberFormatException e2){
				  x =JOptionPane.showInputDialog(null, "Error Please enter a number for time per match:","ERROR",JOptionPane.ERROR_MESSAGE);
				  time=Integer.parseInt(x);
			  }
			  if(time<=0){
				  x =JOptionPane.showInputDialog(null, "Error Time per match must be greater than zero:","ERROR",JOptionPane.ERROR_MESSAGE);
				  time=Integer.parseInt(x);
			  }
			}
			else{
				x =JOptionPane.showInputDialog(null, "Error Please enter a number for time per match:","ERROR",JOptionPane.ERROR_MESSAGE);
				 time=Integer.parseInt(x); 
			}
			//sets the playoffs boolean which checks whether there are playoffs
			playoffs=playoffsBtn.isSelected();
			//if playoffs are on less than four teams are entered there is an error
			if(playoffs&&teams<4){
				x =JOptionPane.showInputDialog(null, "Error At least four teams are needed for playoffs:","ERROR",JOptionPane.ERROR_MESSAGE);
				teams=Integer.parseInt(x);
			}
			System.out.println(teams+"\n"+fieldsOfPlay+"\n"+time+"\n"+playoffs);
			//loads the next page
			Stage stage=(Stage) node.getScene().getWindow();
			 Parent root = FXMLLoader.load(getClass().getResource("/fxml/BballPage2.fxml"));/* Exception */
			  Scene scene = new Scene(root);
			  stage.setScene(scene);
			  stage.show();
			  BballPage2.createPage(playoffs, teams, fieldsOfPlay, time);
	}
	//action listener for the home button which loads the home page
	public void Home(ActionEvent homeBtnClick) throws IOException{
		  Node node=(Node) homeBtnClick.getSource();
		  Stage stage=(Stage) node.getScene().getWindow();
		  Parent root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));/* Exception */
		  Scene scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		  
	}

}
