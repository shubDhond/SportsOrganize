package main;

import javafx.beans.property.SimpleStringProperty;


public class Row {
	private String netNum;
	private String TeamOne;
	private String TeamTwo;
	
	public Row(String n, String t1, String t2){
		netNum=n;
		TeamOne=t1;;
		TeamTwo=t2;
	}

	public String getNetNum() {
		return netNum;
	}

	public void setNetNum(String netNum) {
		this.netNum = netNum;
	}

	public String getTeamOne() {
		return TeamOne;
	}

	public void setTeamOne(String teamOne) {
		TeamOne = teamOne;
	}

	public String getTeamTwo() {
		return TeamTwo;
	}

	public void setTeamTwo(String teamTwo) {
		TeamTwo = teamTwo;
	}

	
	
}
