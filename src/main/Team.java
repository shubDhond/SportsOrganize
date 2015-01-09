package main;

public class Team {
   
   //instance variables
   private String name;
   private int rank;
   private int pointsFor;
   private int pointsAgainst;
   private int wins;
   
   //constructor
   
   public Team(String name) {
      this.name = name;
   }
   
   //setters
   public void setName(String n) {
      name = n;
   }
   
   public void setRank(int r) {
      rank = r;
   }
   
   public void setPointsFor(int f) {
      pointsFor += f;
   }
   
   public void setPointsAgainst(int a) {
      pointsAgainst += a;
   }
   
   public void setWins() {
      wins++;
   }

   
   //getters
   public String getName() {
      return name;
   }
   
   public int getRank() {
      return rank;
   }
   
   public int getPointDifference() {
      return pointsFor - pointsAgainst; 
   }
   
   public int getPointsFor(){
	   return pointsFor;
   }
   
   public int getWins() {
      return wins;
   }
   
   public String toString(){
	   return name;
   }
   

}