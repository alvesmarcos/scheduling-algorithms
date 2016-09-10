// Created By: Marcos alves
// Created Date: Sept. 10th, 2016	  
// Last Modified: Sept. 10th, 2016	      

import java.util.*;

// [FCFS] -> First-Come, First Served
public class FirstCome extends Scheduler {
	//attributes
	private Deque<Job> jobs;

	//constructor
	public FirstCome(){
		response = waiting = turnaround = 0;
		jobs = new ArrayDeque<Job>();
	}

	//methods
	public void addJob(Job j){
		jobs.add(j);
	}

	public void run(){
		int count = 0;

		while(!jobs.isEmpty()){
			System.out.println("Running job ("+count+") "+(int) waiting+"-"+
				              (int)(waiting+jobs.getFirst().getTime()));

			waiting += jobs.getFirst().getTime(); 
			jobs.pollFirst();
			count++;
		}
	}

	public void info(){
		System.out.printf("FCFS %.1f %.1f %.1f", turnaround, response, waiting);
	}
}