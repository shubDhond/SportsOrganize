package main;

import java.util.ArrayList;
import java.util.Random;

public class Tournament{

   private ArrayList<Team> teams = new ArrayList<Team>();
   private int numNets;
   private double gameLength;
   private String startTime;
   private ArrayList<ArrayList<Game>> schedule = new ArrayList<ArrayList<Game>>();

   public Tournament(){
   }
   //creates a new tournament

   public void addTeam(String name){
      Team team = new Team(name);
      teams.add(team);
   }
   //adds teams to the tournament

   public ArrayList<Team> getTeams(){
      return teams;
   }

   public double getNumTeams(){
      return teams.size();
   }

   public void setNumNets(int numNets){
      this.numNets = numNets;
   }
   //takes in how many baskets are available

   public int getNumNets(){
      return numNets;
   }

   public double getNumGameRounds(){
	   int gR = 0;
	   double tG = (Math.pow(this.getNumTeams(),2.0)-getNumTeams())*0.5;
	   int totalGames = (int)tG;
	   if(totalGames%getNumNets()==0){
	      gR = totalGames/getNumNets();
	   }
	   else{
	      gR = totalGames/getNumNets()+1;
	   }
	   return gR;
	}
	//returns how many rounds of games will need to be played
   

   public double getNumGamePerTeam(){
      return this.getNumTeams()-1;
   }
   //returns how many games each team plays

   public void setStartTime(String startTime){
      this.startTime = startTime;
   }
   //takes in a starting time

   public String getStartTime(){
      return startTime;
   }

   public double getNumTeamsPlaying(){
      return 2*this.getNumNets();
   }
   //needed for making the schedule

   public void setGameLength(double minutes){
      this.gameLength = gameLength;
   }

  public void generateSchedule(){
   Random generator = new Random();
   ArrayList<Game> allGames = new ArrayList<Game>();
   for(int i=0;i<getNumTeams()-1;i++){
      for(int j=i+1;j<getNumTeams();j++){
         allGames.add(new Game(teams.get(i),teams.get(j)));
      }
   }
 
   
   //creates an ArrayList with all the possible games in it
   for(int i=0;i<getNumGameRounds();i++){
      schedule.add(new ArrayList<Game>());
   }

   //this fills a the schedule with game rounds that initially contain all games
   for(int i=0;i<getNumGameRounds();i++){
      for(int j=0;j<getNumNets();j++){
         int a = generator.nextInt(allGames.size());
         if(contains(schedule.get(i),allGames.get(a).getTeamOne())||contains(schedule.get(i),allGames.get(a).getTeamTwo())){
            j--;
            //a team can not play in more than one game in the same game round, j-- so the game round still fills with
            //the correct number of games if a team gets generated to play more than once
         }
         else{
            schedule.get(i).add(allGames.get(a));
            allGames.remove(allGames.indexOf(allGames.get(a)));
            }
            //this removes any game from future game rounds once it appears in a game round, that's why
            //the first for loop only goes up until the second last ArrayList in gameRounds, the last round
            //will be already created after the procedure is run on the second last game round
            //once a game is picked it is put in the schedule ArrayList
         }
      }
   }
   //the schedule ArrayList is now full of game rounds that are not overlapping
   
   
   public ArrayList<ArrayList<Game>> getSchedule(){
      generateSchedule();
      return schedule;
   }
   //creates and returns the tournament schedule ArrayList
  
   public ArrayList<ArrayList<Game>> retSchedule(){
	   return schedule;
   }
   
   public ArrayList<Team> displayStandings(){
    ArrayList<Team> standings = new ArrayList<Team>();
    ArrayList<Team> teams1 = teams;
    while(teams.size()!=1){
       Team top = teams1.get(0);
       int address = 0;
       for(int i=1;i<teams1.size();i++){
          if(top.getWins()<teams1.get(i).getWins()||(top.getWins()==teams1.get(i).getWins()&&top.getPointDifference()<teams1.get(i).getPointDifference())){
             top = teams1.get(i);
             address = i;
          }
       }
       teams1.remove(address);
       standings.add(top);
       if(teams1.size()==1){
          standings.add(teams1.get(0));
       }
     }
    for(int i=0;i<standings.size();i++){
    	standings.get(i).setRank(i+1);
    }
     return standings;
   }

   
   public void playoffs(ArrayList<Team> a){
      Game semi1 = new Game(a.get(0), a.get(3));
      Game semi2 = new Game(a.get(1), a.get(2));
      //first place team plays fourth place team, second place team plays third place team
      Game finals = new Game(semi1.getWinner(), semi2.getWinner());
   }
   
   public boolean contains(ArrayList<Game> a,Team t){
   boolean contains = false;
   for(int i=0;i<a.size();i++){
      if(a.get(i).getTeamOne().getName().equals(t.getName())||a.get(i).getTeamTwo().getName().equals(t.getName())){
         contains = true;
      }
   }
   return contains;
}
}
         
      