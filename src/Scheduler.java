// Created By: Marcos alves
// Created Date: Sept. 10th, 2016	  
// Last Modified: Sept. 13th, 2016	      

import java.util.*;
import java.io.*;

public class Scheduler {
	//attributes
	private float response, waiting, turnaround; //AVERAGE
	private Routine r;
	private List<Job> queue;

	//constructor
	public Scheduler(Routine r){
		response = waiting = turnaround = 0.0f;
		this.r = r;
		queue = new ArrayList<Job>();
	}
	
	//methods
	//loaderJobs expected command "java Main < jobs.txt" 
	public void loaderJobs(){
		//getting keyboard input 
		Scanner reader = new Scanner(System.in);
		//buffers
		String line = null;
		String[] parts = null;
	
		while(reader.hasNextLine()){
			line = reader.nextLine();

			if(line.contains(" ")){
				parts = line.split(" ");
				queue.add(new Job(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
			} else
				throw new IllegalArgumentException("File doens't contains [space]!");
		}	
	}

	//print specific element by index (IF EXIST) 
	public void peekJob(int index){
		try {
			System.out.println(queue.get(index).toString());
		} catch (IndexOutOfBoundsException e){
			e.printStackTrace();
		}
	}

	//print all elements of queue 
	public void printQueue(){
		for(Job j: queue)
			System.out.println(j.toString());
	}
}