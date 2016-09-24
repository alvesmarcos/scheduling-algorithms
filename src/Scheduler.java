// Created By: Marcos alves
// Created Date: Sept. 10th, 2016	  
// Last Modified: Sept. 15th, 2016	      

import java.util.*;
import java.io.*;

public class Scheduler {
	//Algorithms scheduling
	public static final int FCFS = 0x1; // First Come, First Served
	public static final int RR = 0x2;	// Round-Robin
    public static final int SJF = 0x3;  //Shortest Job First
	
	//attributes
	private float response, waiting, turnaround; //AVERAGE
	private int routine;
	private Queue<Job> queue;
	//print about Running jobs | true - print
	protected static final boolean DEBUG = false;

	//constructor
	public Scheduler(int r){
		response = waiting = turnaround = 0.0f;
		routine = r;
		queue = new LinkedList<Job>();
		
		//expect path ../jobs.txt
		loaderJobs();
	}
	
	//methods
	//routine = Routine.FCFS | 
	private void firstComeFirstServed(){
		int accTime = 0, count = 0;
		//get first element
		Job first = queue.peek();
		int offset = first.getArrival();

		while(!queue.isEmpty()){
			//return and remove first element
			first = queue.poll(); 

			if(DEBUG)
				System.out.println("Running job (" + count + ") " + accTime + "-" + 
							  	  (accTime+first.getTime()));
			//increment queue
			count++;
			waiting+=accTime + offset - first.getArrival();
			accTime+=first.getTime(); 
			turnaround+=accTime + offset - first.getArrival();
		}
		turnaround/=count;
		waiting/=count;
		response=waiting;
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
		System.out.printf("%s %.1f %.1f %.1f\n", nameAlgorithm, turnaround, response, waiting);
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
				queue.add(new Job(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
			} else
				throw new IllegalArgumentException("File doens't contains SPACE!");
		}	
	}

	//print specific element (IF EXIST)
	public void peekJob(int index){
		int count = 1;

		if(index > queue.size()){
			for(Job j: queue){
				if(count == index)
					System.out.println(j.toString()); break;
			}
		} else
			throw new IndexOutOfBoundsException("Outside range");
	}

	//print all elements of queue 
	public void printQueue(){
		queue.forEach(j->System.out.println(j.toString()));
	}

	//routine = Routine.RR | 
	private void roundRobin(){
		int sum = 0, count = 0;
		final int quantum = 2; //IMPORTANT
		Job first = null;

		while(!queue.isEmpty()){
			if(DEBUG)
				System.out.println("Running job (" + (count++) + ") " + sum + "-" + 
							  	  (sum+quantum));
			sum += quantum;
			//sub quantum of the job
			queue.peek().subTime(quantum);
			//return always first element 
			first = queue.peek();
			if(first.getTime() > 0)
				queue.add(first);
			//pop anyway
			queue.poll();
		}
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

	//routine = Routine.SJF | 
	private void shortestJobFirst(){
		int sum = 0, count = 0;
		//copy queue -> priorityQueue
		PriorityQueue<Job> queueCopy = new PriorityQueue<Job>(queue);
		queue.clear();
		
		while(!queueCopy.isEmpty()){
			if(DEBUG)
				System.out.println("Running job (" + (count++) + ") " + sum + "-" + 
							  	  (sum+queueCopy.peek().getTime()));
			sum += queueCopy.peek().getTime();
			queueCopy.poll();
		}	
	}
}