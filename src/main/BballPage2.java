package main;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BballPage2 {
	/* This variables declared below include the UIcomponents that are being displayed on to the 
	 * frame and the placement and other attributes are retrieved from the BballPage2 FXML using the 
	 * @FXML declaration. The FXML also sets the event listeners for the buttons.*/
	@FXML private static VBox tableSpace;
	@FXML private static Label Lbl;
	@FXML private static Button Next;
	@FXML private static Button Home;
	/* The statements below declare all the variables and lists that are used to hold information 
	 * of a particular tournament */
	//boolean to check if this tournament will have playoffs
	private static boolean play=false;
	//index allowing to display the correct pages
	private static int index=0;
	//creates a tournament
	private static Tournament t=new Tournament();
	//schedule of games rounds and games
	private static ArrayList<ArrayList<Game>> schedule1;
	//TableView to show schedules
	private static TableView table=new TableView();
	//a schedule list making it easier to display in the table
	private static ArrayList<Game> scheduleList=new ArrayList<Game>();
	//ArrayLists to hold the scores of the TeamOnes and TeamTwos
	private static  TableView playoffs=new TableView();
	private static TableView playoffs2=new TableView();
	private static ArrayList<Integer> scoresT1=new ArrayList<Integer>();
	private static ArrayList<Integer> scoresT2=new ArrayList<Integer>();
	private static Game semiFinalGame1=new Game();
	private static Game semiFinalGame2=new Game();
	private static Game finals=new Game();
	private static int net;
	
	public static void createPage(boolean playoffs, int teams, int nets, int time){
		//sets the info for the tournament
		t.setGameLength(time);
		net=nets;
		t.setNumNets(nets);
		//adds teams to the tournament
		for(int i=1;i<=teams;i++)
			t.addTeam("team "+i);
		
		schedule1=t.getSchedule();
		//sets playoff depending on whether the checkbox was checked
		play=playoffs;
		
		
		for(int i=0;i<schedule1.size();i++){
			for(int j=0;j<nets;j++)
				scheduleList.add(schedule1.get(i).get(j));
		}
		
		//creates an array list of rows to display in the table
		int courtNum=0;
		ArrayList<Row> rows=new ArrayList<Row>();
		for(int i=0;i<scheduleList.size();i++){
			courtNum=(courtNum%nets)+1;
			rows.add(new Row(""+courtNum,scheduleList.get(i).getTeamOne().getName(),scheduleList.get(i).getTeamTwo().getName()));
		}
		
		//sets the text of the label to show which page we are on
		  Lbl.setText("Round Robbin");
		  //creates table columns to hold the row information
		  //column 1 will show the court a game is to be played on
		  TableColumn firstCol=new TableColumn("Court");
		  //column 2 will show the team one of a game
		  TableColumn secondCol=new TableColumn("Team");
		  //column 3 will show the team two of a game
		  TableColumn thirdCol=new TableColumn("Team");
		  table.setEditable(true);
		  firstCol.setMinWidth(407/3);
		  secondCol.setMinWidth(407/3);
		  thirdCol.setMinWidth(407/3);

		  //creates an observable list to show in the bale using the ArrayList of rows
		  ObservableList<Row> data=FXCollections.observableArrayList(rows);
		  
		  //sets which variable of row will fill each column with
		  firstCol.setCellValueFactory(new PropertyValueFactory<Row, String>("netNum"));
		  secondCol.setCellValueFactory(new PropertyValueFactory<Row,String>("TeamOne"));
		  thirdCol.setCellValueFactory(new PropertyValueFactory<Row,String>("TeamTwo"));
		  
		  //adds the columns to the table
		  table.getColumns().addAll(firstCol, secondCol, thirdCol);
		  
		  //sets the data to be used in the table and adds the table to the VBox
		  table.setItems(data);
		  tableSpace.getChildren().addAll(table);
		  
		  JOptionPane.showMessageDialog(null, "Double Click Cells To Enter Scores. If the scores of a game are not entered they will be assumed to be 0 for each team.");
		  System.out.println(index);
		  
		  //sets the scores for each team to 0 by default to avoid null pointer
		  for(int i=0;i<scheduleList.size();i++){
			  scoresT1.add(0);
			  scoresT2.add(0);
		  }
		
		  //event handler for double clicking cells to add scores for a game
		  table.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>(){

			@Override
			public void handle(MouseEvent e) {
				if(e.getClickCount()>1){
					if(table.getSelectionModel().getSelectedIndex()>=0){
						int i=table.getSelectionModel().getSelectedIndex();
						//gets and sets the score for team one of a game once errors are checked
						String x=JOptionPane.showInputDialog("Please enter the score for Team one:");
						if(x!=null){
							try{
								scoresT1.set(i, Integer.parseInt(x));
							}	catch(NumberFormatException e2){
								x =JOptionPane.showInputDialog(null, "Error Please enter a number for score:","ERROR",JOptionPane.ERROR_MESSAGE);
								scoresT1.set(i,Integer.parseInt(x));
							}
							if(Integer.parseInt(x)<0){
								x =JOptionPane.showInputDialog(null, "Error Please enter a positive number for score:","ERROR",JOptionPane.ERROR_MESSAGE);
								scoresT1.set(i,Integer.parseInt(x));
							}
						}else{
							x =JOptionPane.showInputDialog(null, "Error Please enter a number for score:","ERROR",JOptionPane.ERROR_MESSAGE);
							scoresT1.set(i,Integer.parseInt(x));
						}
						//gets and sets the scores for team two of a game once errors are checked
						x=JOptionPane.showInputDialog("Please enter the score for Team two:");
						if(x!=null){
							try{
								scoresT2.set(i,Integer.parseInt(x));
							}catch(NumberFormatException e2){
								x =JOptionPane.showInputDialog(null, "Error Please enter a number for score:","ERROR",JOptionPane.ERROR_MESSAGE);
								scoresT2.set(i,Integer.parseInt(x));
							}
							if(Integer.parseInt(x)<0){
								x =JOptionPane.showInputDialog(null, "Error Please enter a positive number for score:","ERROR",JOptionPane.ERROR_MESSAGE);
								scoresT2.set(i, Integer.parseInt(x));
							}
						}else{
							x =JOptionPane.showInputDialog(null, "Error Please enter a number for score:","ERROR",JOptionPane.ERROR_MESSAGE);
							scoresT2.set(i,Integer.parseInt(x));
						}
					}
				}
			}
			  	
		  });
		  
		  
		  
	}
	
	//event listener for the next button click
	public void Next(ActionEvent e) throws IOException{
		index++;
		//if its page one
		if(index==1){
			int is=0;
			for(int i=0;i<t.retSchedule().size();i++){
				for(int j=0;j<t.retSchedule().get(i).size();j++){
					t.retSchedule().get(i).get(j).setPointsForTeamOne(scoresT1.get(is));
					t.retSchedule().get(i).get(j).setPointsForTeamTwo(scoresT2.get(is));
					is++;
				}
			}
			//load the page
			 //if there are no playoffs get rid of next button
			  if(play==false){
				  Next.setDisable(true);
				  Next.setVisible(false);
			  }
			Node node=(Node) e.getSource();
			 Stage stage=(Stage) node.getScene().getWindow();
			  Parent root = FXMLLoader.load(getClass().getResource("/fxml/BballPage2.fxml"));/* Exception */
			  Scene scene = new Scene(root);
			  stage.setScene(scene);
			  stage.show();
			  //set label to standings
			  Lbl.setText("Standings");
			 
			  ArrayList<Team> standing;
			  standing=t.displayStandings();
			  System.out.println(standing.get(0).getPointsFor()+"\t"+standing.get(1).getPointsFor());
			  
			  if(standing.size()>=4){
				  semiFinalGame1.setTeamOne(standing.get(0));
				  semiFinalGame1.setTeamTwo(standing.get(3));
				  semiFinalGame2.setTeamOne(standing.get(1));
				  semiFinalGame2.setTeamTwo(standing.get(2));
			  }
			  
			  scheduleList.clear();
			  scoresT1.clear();
			  scoresT2.clear();
			  scheduleList.add(semiFinalGame1);
			  scheduleList.add(semiFinalGame2);
			  
			  //creates and adds a standings table to the VBox
			  TableColumn firstCol=new TableColumn("Rank");
			  firstCol.setMinWidth(407/2);
			  TableColumn secondCol=new TableColumn("Team");
			  secondCol.setMinWidth(407/2);
			  
			  ObservableList<Team> data=FXCollections.observableArrayList(standing);
			  firstCol.setCellValueFactory(new PropertyValueFactory<Team,String>("rank"));
			  secondCol.setCellValueFactory(new PropertyValueFactory<Team,String>("name"));
			  
			  TableView standings=new TableView();
			  standings.getColumns().addAll(firstCol,secondCol);
			  standings.setItems(data);
			  tableSpace.getChildren().addAll(standings); 
			  
			  if(play==false){
				  Next.setDisable(true);
				  Next.setVisible(false);
			  }
			  
		}
		//else if its page two(can only be displayed if there are playoffs )
		else if(index==2){
			
			//load the page
			Node node=(Node) e.getSource();
			 Stage stage=(Stage) node.getScene().getWindow();
			  Parent root = FXMLLoader.load(getClass().getResource("/fxml/BballPage2.fxml"));/* Exception */
			  Scene scene = new Scene(root);
			  stage.setScene(scene);
			  stage.show();
			  Lbl.setText("Playoffs");
			  //creates table columns to hold the row information
			  //column 1 will show the court a game is to be played on
			  TableColumn firstCol=new TableColumn("Court");
			  //column 2 will show the team one of a game
			  TableColumn secondCol=new TableColumn("Team");
			  //column 3 will show the team two of a game
			  TableColumn thirdCol=new TableColumn("Team");
			  table.setEditable(true);
			  firstCol.setMinWidth(407/3);
			  secondCol.setMinWidth(407/3);
			  thirdCol.setMinWidth(407/3);
			  
			
			  
			  int courtNum=0;
				ArrayList<Row> rows=new ArrayList<Row>();
				for(int i=0;i<scheduleList.size();i++){
					courtNum=(courtNum%net)+1;
					rows.add(new Row(""+courtNum,scheduleList.get(i).getTeamOne().getName(),scheduleList.get(i).getTeamTwo().getName()));
				}
			  
				  //creates an observable list to show in the bale using the ArrayList of rows
				  ObservableList<Row> data=FXCollections.observableArrayList(rows);
				  
				  //sets which variable of row will fill each column with
				  firstCol.setCellValueFactory(new PropertyValueFactory<Row, String>("netNum"));
				  secondCol.setCellValueFactory(new PropertyValueFactory<Row,String>("TeamOne"));
				  thirdCol.setCellValueFactory(new PropertyValueFactory<Row,String>("TeamTwo"));
				  
				  playoffs.getColumns().addAll(firstCol,secondCol,thirdCol);
				  playoffs.setItems(data);
				  tableSpace.getChildren().addAll(playoffs);
				  
				  for(int i=0;i<2;i++){
					  scoresT1.add(0);
					  scoresT2.add(0);
				  }
				  
				  playoffs.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>(){

						@Override
						public void handle(MouseEvent e) {
							if(e.getClickCount()>1){
								if(playoffs.getSelectionModel().getSelectedIndex()>=0){
									int i=playoffs.getSelectionModel().getSelectedIndex();
									//gets and sets the score for team one of a game once errors are checked
									String x=JOptionPane.showInputDialog("Please enter the score for Team one:");
									if(x!=null){
										try{
											scoresT1.set(i, Integer.parseInt(x));
										}	catch(NumberFormatException e2){
											x =JOptionPane.showInputDialog(null, "Error Please enter a number for score:","ERROR",JOptionPane.ERROR_MESSAGE);
											scoresT1.set(i,Integer.parseInt(x));
										}
										if(Integer.parseInt(x)<0){
											x =JOptionPane.showInputDialog(null, "Error Please enter a positive number for score:","ERROR",JOptionPane.ERROR_MESSAGE);
											scoresT1.set(i,Integer.parseInt(x));
										}
									}else{
										x =JOptionPane.showInputDialog(null, "Error Please enter a number for score:","ERROR",JOptionPane.ERROR_MESSAGE);
										scoresT1.set(i,Integer.parseInt(x));
									}
									//gets and sets the scores for team two of a game once errors are checked
									x=JOptionPane.showInputDialog("Please enter the score for Team two:");
									if(x!=null){
										try{
											scoresT2.set(i,Integer.parseInt(x));
										}catch(NumberFormatException e2){
											x =JOptionPane.showInputDialog(null, "Error Please enter a number for score:","ERROR",JOptionPane.ERROR_MESSAGE);
											scoresT2.set(i,Integer.parseInt(x));
										}
										if(Integer.parseInt(x)<0){
											x =JOptionPane.showInputDialog(null, "Error Please enter a positive number for score:","ERROR",JOptionPane.ERROR_MESSAGE);
											scoresT2.set(i, Integer.parseInt(x));
										}
									}else{
										x =JOptionPane.showInputDialog(null, "Error Please enter a number for score:","ERROR",JOptionPane.ERROR_MESSAGE);
										scoresT2.set(i,Integer.parseInt(x));
									}
								}
							}
						}
						  	
					  });
		}
		//else if its page 3
		else if(index==3){
			semiFinalGame1.setPointsForTeamOne(scoresT1.get(0));
			semiFinalGame1.setPointsForTeamTwo(scoresT2.get(0));
			semiFinalGame2.setPointsForTeamOne(scoresT1.get(1));
			semiFinalGame2.setPointsForTeamTwo(scoresT2.get(1));
			finals.setTeamOne(semiFinalGame1.getWinner());
			finals.setTeamTwo(semiFinalGame2.getWinner());
			
			 scoresT1.clear();
			 scoresT2.clear();
			scheduleList.clear();
			scheduleList.add(finals);
			
			//load the page
			Node node=(Node) e.getSource();
			 Stage stage=(Stage) node.getScene().getWindow();
			  Parent root = FXMLLoader.load(getClass().getResource("/fxml/BballPage2.fxml"));/* Exception */
			  Scene scene = new Scene(root);
			  stage.setScene(scene);
			  stage.show();
			  Lbl.setText("Playoffs");
			  
			  TableColumn firstCol=new TableColumn("Court");
			  //column 2 will show the team one of a game
			  TableColumn secondCol=new TableColumn("Team");
			  //column 3 will show the team two of a game
			  TableColumn thirdCol=new TableColumn("Team");
			  table.setEditable(true);
			  firstCol.setMinWidth(407/3);
			  secondCol.setMinWidth(407/3);
			  thirdCol.setMinWidth(407/3);
			  
			
			  
			  int courtNum=0;
				ArrayList<Row> rows=new ArrayList<Row>();
				for(int i=0;i<scheduleList.size();i++){
					courtNum=(courtNum%net)+1;
					rows.add(new Row(""+courtNum,scheduleList.get(i).getTeamOne().getName(),scheduleList.get(i).getTeamTwo().getName()));
				}
			  
				  //creates an observable list to show in the bale using the ArrayList of rows
				  ObservableList<Row> data=FXCollections.observableArrayList(rows);
				  
				  //sets which variable of row will fill each column with
				  firstCol.setCellValueFactory(new PropertyValueFactory<Row, String>("netNum"));
				  secondCol.setCellValueFactory(new PropertyValueFactory<Row,String>("TeamOne"));
				  thirdCol.setCellValueFactory(new PropertyValueFactory<Row,String>("TeamTwo"));
				  
				  playoffs2.getColumns().addAll(firstCol,secondCol,thirdCol);
				  playoffs2.setItems(data);
				  tableSpace.getChildren().addAll(playoffs2);
				  
				  for(int i=0;i<2;i++){
					  scoresT1.add(0);
					  scoresT2.add(0);
				  }
				  
				  playoffs2.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>(){

						@Override
						public void handle(MouseEvent e) {
							if(e.getClickCount()>1){
								if(playoffs2.getSelectionModel().getSelectedIndex()>=0){
									int i=playoffs2.getSelectionModel().getSelectedIndex();
									//gets and sets the score for team one of a game once errors are checked
									String x=JOptionPane.showInputDialog("Please enter the score for Team one:");
									if(x!=null){
										try{
											scoresT1.set(i, Integer.parseInt(x));
										}	catch(NumberFormatException e2){
											x =JOptionPane.showInputDialog(null, "Error Please enter a number for score:","ERROR",JOptionPane.ERROR_MESSAGE);
											scoresT1.set(i,Integer.parseInt(x));
										}
										if(Integer.parseInt(x)<0){
											x =JOptionPane.showInputDialog(null, "Error Please enter a positive number for score:","ERROR",JOptionPane.ERROR_MESSAGE);
											scoresT1.set(i,Integer.parseInt(x));
										}
									}else{
										x =JOptionPane.showInputDialog(null, "Error Please enter a number for score:","ERROR",JOptionPane.ERROR_MESSAGE);
										scoresT1.set(i,Integer.parseInt(x));
									}
									//gets and sets the scores for team two of a game once errors are checked
									x=JOptionPane.showInputDialog("Please enter the score for Team two:");
									if(x!=null){
										try{
											scoresT2.set(i,Integer.parseInt(x));
										}catch(NumberFormatException e2){
											x =JOptionPane.showInputDialog(null, "Error Please enter a number for score:","ERROR",JOptionPane.ERROR_MESSAGE);
											scoresT2.set(i,Integer.parseInt(x));
										}
										if(Integer.parseInt(x)<0){
											x =JOptionPane.showInputDialog(null, "Error Please enter a positive number for score:","ERROR",JOptionPane.ERROR_MESSAGE);
											scoresT2.set(i, Integer.parseInt(x));
										}
									}else{
										x =JOptionPane.showInputDialog(null, "Error Please enter a number for score:","ERROR",JOptionPane.ERROR_MESSAGE);
										scoresT2.set(i,Integer.parseInt(x));
									}
								}
							}
						}
						  	
					  });
			  
		}
		//if its the last page
		else{
			finals.setPointsForTeamOne(scoresT1.get(0));
			finals.setPointsForTeamTwo(scoresT2.get(0));
			
			Team Winner =finals.getWinner();
			
			//load the page
			Node node=(Node) e.getSource();
			 Stage stage=(Stage) node.getScene().getWindow();
			  Parent root = FXMLLoader.load(getClass().getResource("/fxml/BballPage2.fxml"));/* Exception */
			  Scene scene = new Scene(root);
			  stage.setScene(scene);
			  stage.show();
			  Lbl.setText("Winner");
			  //get Rid of next button and reset index
			  
			  Label win=new Label(Winner.getName());
			  win.setMaxWidth(100);
			  win.setMinHeight(100);
			  win.setTextFill(Color.web("FFFFFF"));
			  tableSpace.getChildren().addAll(win);
			  index=0;
			  Next.setDisable(true);
			  Next.setVisible(false);
		}
	}
	
	//event listener for home button
	public void Home(ActionEvent e) throws IOException{
		//loads the home page
		Node node=(Node) e.getSource();
		  Stage stage=(Stage) node.getScene().getWindow();
		  Parent root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));/* Exception */
		  Scene scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		  index=0;
		  t.getTeams().clear();
		  t.retSchedule().clear();
		  scheduleList.clear();
		  scoresT1.clear();
		  scoresT2.clear();
		  table.getColumns().clear();
		  table.setItems(null);
	}
	

}
