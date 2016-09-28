// Created By: Marcos alves
// Created Date: Sept. 10th, 2016	  
// Last Modified: Sept. 28th, 2016	      

public class Job {
	//enum
	public enum State { CREATED, READY, RUNNING, BLOCKED, TERMINATED };
	//attributes
	private int arrival, identity, time;
	private int response, waiting, turnaround, running, out;
	private State state;

	//constructor
	public Job(int arr, int tim){
		arrival = arr;
		time = tim;
		out=response=waiting=identity=running=turnaround=0;
	} 

	//methods
	//execute
	public void execute(int t, int sysc){
		if(state == State.READY){
			state = State.RUNNING;
			waiting += sysc - arrival - turnaround;
			turnaround += waiting + t;
			//last time
			out = sysc + t; 
			arrival = 0;
			time -= t;
			running = t;
		} else
			throw new IllegalArgumentException("State is not READY");
	}
	//get arrival
	public int getArrival(){
		return arrival;
	}
	//get identity
	public int getID(){
		return identity;
	}
	//get out
	public int getOut(){
		return out;
	}
	//get response
	public int getResponse(){
		return response;
	}
	//get run
	public int getRunning(){
		return running;
	}
	//get state
	public State getState(){
		return state;
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
	public void setResponse(int value){
		response += value;
	}
	//set state
	public void setState(State s){
		state = s;
	}
	//set time
	public void setTime(int value){
		time += value;
	}
	//set turnaround
	public void setTurnaround(int value){
		turnaround += value;
	}
	//set waiting
	public void setWaiting(int value){
		waiting += value;
	}
	//return string formatted
	public String toString(){
		return "Arrival: "+arrival+" | Time: "+time;
	}
}