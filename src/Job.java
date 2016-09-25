// Created By: Marcos alves
// Created Date: Sept. 10th, 2016	  
// Last Modified: Sept. 15th, 2016	      

public class Job {
	//consts
	public static final int ADD = 0x1;
	public static final int SUB = 0x2;
	//attributes
	private int arrival, identity, time;
	private int waiting, turnaround, response;

	//constructor
	public Job(int arrival, int time){
		this.arrival = arrival;
		this.time = time;
		identity = 0;
	} 

	//methods
	//get arrival
	public int getArrival(){
		return arrival;
	}
	//get identity
	public int getID(){
		return identity;
	}
	//get response
	public int getResponse(){
		return response;
	}
	//get time
	public int getTime(){
		return time;
	}
	//get turnaround
	public int getTurnaround(){
		return turnaround;
	}
	//get waiting
	public int getWaiting(){
		return waiting;
	}
	//set id
	public void setID(int index){
		identity = identity==0 ? index : identity;
	}
	//set response
	public void setResponse(int value, int apply){
		response += apply==ADD ? value : -value;
	}
	//set time
	public void setTime(int value, int apply){
		this.time += apply==ADD ? value : -value;
	}
	//set turnaround
	public void setTurnaround(int value, int apply){
		turnaround += apply==ADD ? value : -value;
	}
	//set waiting
	public void setWaiting(int value, int apply){
		waiting += apply==ADD ? value : -value;
	}
	//return string formatted
	public String toString(){
		return "Arrival: "+arrival+"\t Time: "+time;
	}
}