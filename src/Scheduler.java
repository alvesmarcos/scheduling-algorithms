// Created By: Marcos alves
// Created Date: Sept. 10th, 2016	  
// Last Modified: Sept. 28th, 2016	      

import java.util.*;
import java.io.*;

public class Scheduler {
	//enum algorithms scheduling
	public enum Algorithm { FCFS, RR, SJF };
	//print about Running jobs | true - print
	public static final boolean DEBUG = false;

	//attributes
	private float meanResponse, meanWaiting, meanTurnaround;
	private Algorithm algorithm;
	private QueueJob queue;

	//constructor
	public Scheduler(Algorithm alg){
		meanResponse=meanWaiting=meanTurnaround=0.0f;
		algorithm = alg;
		queue = new QueueJob();
		//expect path ../jobs.txt
		loaderJobs();
	}
	
	//methods
	//algorithm = FCFS | 
	private void firstComeFirstServed(){
		int length = queue.size(), viewClock = 0;
		Job tmp = null;

		while(!queue.isEmpty()){
			//get first element
			tmp = queue.peek(); 
			//return clock of queue
			viewClock = queue.getClock();

			if(DEBUG)
				System.out.println("Running job (" + tmp.getID() + ") " + (viewClock - tmp.getArrival()) + 
								   "-" + (viewClock + tmp.getTime()));

			tmp.execute(tmp.getTime(), viewClock);
			tmp = queue.poll();	
			meanWaiting += tmp.getWaiting();
			meanTurnaround += tmp.getTurnaround();
			meanResponse += tmp.getResponse();
		}
		meanTurnaround/=length;
		meanResponse/=length;
		meanWaiting/=length;
	}

	//print info about algorithm
	public void info(){
		//print
		System.out.printf("%s %.1f %.1f %.1f\n", algorithm.name(), meanTurnaround, meanResponse, meanWaiting);
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
		int length = queue.size(), viewClock = 0;
		final int quantum = 2; //IMPORTANT
		Job tmp = null;

		while(!queue.isEmpty()){
			//return and remove first element
			tmp = queue.peek(); 
			//return clock of queue
			viewClock = queue.getClock();

			if(DEBUG)
				System.out.println("Running job (" + tmp.getID() + ") " + (viewClock) + 
								   "-" + (viewClock + quantum));

			
			tmp.execute(quantum, viewClock);
			tmp = queue.poll();	
			
			if(tmp.getTime() > 0)
				queue.add(tmp);
			else {
				meanWaiting += tmp.getWaiting();
				meanTurnaround += viewClock;
			}
		}	

		meanTurnaround/=length;
		meanResponse/=length;
		meanWaiting/=length;
	}

	//run scheduling-cpu
	public void run(){
		//test how routine enum
		switch(algorithm){
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
				throw new IllegalArgumentException("Undefined reference algorithm!");
		}
	}

	//routine = SJF | 
	private void shortestJobFirst(){
		//TODO
	}
}