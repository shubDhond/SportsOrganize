package main;

public class Game
{
   private Team TeamOne, TeamTwo, Winner;
   private int pointsForTeamOne, pointsForTeamTwo, court;
   
   //no args constructor
   public Game (){   
   }
   
   //constructor
   public Game(Team t1, Team t2){
      TeamOne = t1;
      TeamTwo = t2;
   }
  
  //setters
   public void setPointsForTeamOne(int pt1){
      pointsForTeamOne = pt1;
      TeamOne.setPointsFor(pt1);
      TeamTwo.setPointsAgainst(pt1);
   }
   
   public void setPointsForTeamTwo(int pt2){
      pointsForTeamTwo = pt2;
      TeamOne.setPointsAgainst(pt2);
      TeamTwo.setPointsFor(pt2);
   }
   
   public void setTeamOne(Team t1){
      TeamOne = t1;
   }
   
   public void setTeamTwo(Team t2){
      TeamTwo = t2;
   }
   
   public void setWinner(Team w){
      Winner = w;
   }
   
   public void setCourt(int c){
      court = c;
   }
   
   //getters
   public int getPointsTeamOne(){
      return pointsForTeamOne;
   }
   
   public int getPointsTeamTwo(){
      return pointsForTeamTwo;
   }
   
   public Team getTeamOne(){
      return TeamOne;
   }
   
   public Team getTeamTwo(){
      return TeamTwo;
   }
   
   public Team getWinner(){
	   Team winner = TeamOne;
      if(TeamOne.getPointsFor()<TeamTwo.getPointsFor())
    	  winner = TeamTwo;
      winner.setWins();
    return winner;
   }
   
   public int getCourt(){
      return court;
   }
   
   
   
}

