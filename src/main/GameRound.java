package main;

import java.util.ArrayList;

public class GameRound{

   private ArrayList<Game> round;
   private String time;

   public GameRound(ArrayList<Game> round,String time){
      this.round = round;
      this.time = time;
   }
   
   public ArrayList<Game> getGames(){
      return round;
   }

   public String getTime(){
      return time;
    }
}