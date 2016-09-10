// Created By: Marcos alves
// Created Date: Sept. 10th, 2016	  
// Last Modified: Sept. 10th, 2016	      

public abstract class Scheduler {
	//attributes
	protected float response, waiting, turnaround;

	//methods abstracts
	public abstract void addJob(Job j);
	public abstract void run();
	public abstract void info();

	//methods
	public float responseTime(){
		return response;
	}

	public float turnaroundTime(){
		return turnaround;
	}

	public float waitingTime(){
		return waiting;
	}
}