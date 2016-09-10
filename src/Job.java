// Created By: Marcos alves
// Created Date: Sept. 10th, 2016	  
// Last Modified: Sept. 10th, 2016	      

public class Job {
	//attributes
	private int arrival, time;

	//constructor
	public Job(int arrival, int time){
		this.arrival = arrival;
		this.time = time;
	} 

	//getters
	public int getArrival(){
		return arrival;
	}

	public int getTime(){
		return time;
	}

	//toString
	public String toString(){
		return "Arrival: "+arrival+"\t Time: "+time;
	}
}