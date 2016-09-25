// Created By: Marcos alves
// Created Date: Sept. 10th, 2016	  
// Last Modified: Sept. 24th, 2016	      

import java.util.*;
import java.io.*;

public class Scheduler {
	//Algorithms scheduling
	public static final int FCFS = 0x1; // First Come, First Served
	public static final int RR = 0x2;	// Round-Robin
    public static final int SJF = 0x3;  //Shortest Job First
	
	//attributes
	private float avgResponse, avgWaiting, avgTurnaround; //AVERAGE
	private int routine;
	private QueueJob queue;
	//print about Running jobs | true - print
	protected static final boolean DEBUG = true;

	//constructor
	public Scheduler(int r){
		avgResponse = avgWaiting = avgTurnaround = 0.0f;
		routine = r;
		queue = new QueueJob();
		
		//expect path ../jobs.txt
		loaderJobs();
	}
	
	//methods
	//routine = FCFS | 
	private void firstComeFirstServed(){
		int accTime = 0, count = 0;
		//get first element
		Job first = queue.peek();
		int offset = first.getArrival();

		while(!queue.isEmpty()){
			//return and remove first element
			first = queue.poll(); 

			if(DEBUG)
				System.out.println("Running job (" + first.getID() + ") " + accTime + "-" + 
							  	  (accTime+first.getTime()));
			//increment queue
			count++;
			avgWaiting += accTime + offset - first.getArrival();
			accTime += first.getTime(); 
			avgTurnaround += accTime + offset - first.getArrival();
		}
		avgTurnaround/=count;
		avgWaiting/=count;
		avgResponse=avgWaiting;
	}

	//print info about algorithm
	public void info(){
		String nameAlgorithm;
		//what algorithm ?
		switch(routine){
			case FCFS: nameAlgorithm = "FCFS"; break;
			case SJF:  nameAlgorithm = "SJF"; break;
			case RR:   nameAlgorithm = "RR"; break;
			default: throw new IllegalArgumentException("Undefined reference routine!");
		}
		//print
		System.out.printf("%s %.1f %.1f %.1f\n", nameAlgorithm, avgTurnaround, avgResponse, avgWaiting);
	}

	//loaderJobs expected command "java Main < jobs.txt" 
	private void loaderJobs(){
		//getting keyboard input 
		Scanner reader = new Scanner(System.in);
		//buffers
		String line = null;
		String[] parts = null;
	
		while(reader.hasNextLine()){
			line = reader.nextLine();

			if(line.contains(" ")){
				parts = line.split(" ");
				queue.push(new Job(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
			} else
				throw new IllegalArgumentException("File doens't contains SPACE!");
		}	
	}

	//print specific element (IF EXIST)
	public void peekJob(int index){
		int count = 1;
		if(index <= queue.size()){
			for(Iterator j = queue.iterator(); j.hasNext();){
				if(count == index)
					System.out.println(j.next().toString()); break;
			}
		} else
			throw new IndexOutOfBoundsException("Outside range");
	}

	//print all elements of queue 
	public void printQueue(){
		queue.forEach();
	}

	//routine = RR | 
	private void roundRobin(){
		int accTime = 0, count = 0;
		final int quantum = 2; //IMPORTANT
		Job first = queue.peek();
		int offset = first.getArrival();

		while(!queue.isEmpty()){
			//return and remove first element
			first = queue.poll(); 

			if(DEBUG)
				System.out.println("Running job (" + first.getID() + ") " + accTime + "-" + 
							  	  (accTime+quantum));

			//add quantum
			accTime+=quantum;			
			//sub quantum of the job
			first.setTime(quantum, Job.SUB);
			//check if finished
			if(first.getTime() > 0)
				queue.setClockAndAdd(first, quantum);
			else
				;//TODO
		}	
		System.out.println(avgWaiting/4);
	}

	//run scheduling-cpu
	public void run(){
		//test how routine enum
		switch(routine){
			case FCFS:
				firstComeFirstServed();
				break;
			case SJF:
				shortestJobFirst();
				break;
			case RR:
				roundRobin();
				break;
			default: 
				throw new IllegalArgumentException("Undefined reference routine!");
		}
	}

	//routine = SJF | 
	private void shortestJobFirst(){
		//TODO
	}
}