// Created By: Marcos alves
// Created Date: Sept. 10th, 2016	  
// Last Modified: Sept. 15th, 2016	      

public class Job {
	//attributes
	private int arrival, time;

	//constructor
	public Job(int arrival, int time){
		this.arrival = arrival;
		this.time = time;
	} 

	//methods
	//get arrival
	public int getArrival(){
		return arrival;
	}
	//get time
	public int getTime(){
		return time;
	}
	//subtration time
	public void subTime(int time){
		this.time -= time;
	}
	//add time
	public void addTime(int time){
		this.time += time;
	}
	//return string formatted
	public String toString(){
		return "Arrival: "+arrival+"\t Time: "+time;
	}
}