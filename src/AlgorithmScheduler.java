// v. Java SE 8
// --
// Author: Marcos Alves
// ++

import java.util.Queue;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
// IMPORT: Your class Job need implements interface Comparable

public abstract class AlgorithmScheduler {
	// Variable control printf
	private static boolean DEBUG = true;
	private static boolean INFO = true;

	// FCFS - Fist Come, First Served
	public static void FCFS(Queue<Job> queue) {
		// average values
		float waiting = 0.0f, turnaround = 0.0f;
		// aux
		int length = queue.size();
		int duration = 0, clock = 0, sum = 0;
		Job tmp = queue.peek();
		int offset = tmp.getArrival(); 
		// != empty
		while(!queue.isEmpty()){
			// get first element
			tmp = queue.poll();
			duration = tmp.getDuration();
			if(DEBUG)
				System.out.printf("Running job (%d) $ %d %d\n", tmp.getID(), clock, clock+duration);
			// set values
			sum = (clock + offset) - tmp.getArrival();
			waiting += sum;
			turnaround += sum + duration;
			clock += duration;
		}
		waiting/=length;
		turnaround/=length;
		// needless to calculate the response time since waiting = response (ALWAYS)
		if(INFO)
			System.out.printf("FCFS %.1f %.1f %.1f\n", turnaround, waiting, waiting);
	}

	// RR - Round Robin
	public static void RR(Queue<Job> queue){ 
		// List
		List<Job> list = new ArrayList<Job>(queue);
		// IMPORT
		final int quantum = 2;
		// average values
		float response = 0.0f, waiting = 0.0f, turnaround = 0.0f;
		// aux
		int length = queue.size();
		int duration = 0, clock = 0, sub = 0, index = 0;
		Job tmp = null;

		while(!list.isEmpty()){
			// get first element
			tmp = list.get(0);
			duration = tmp.getDuration();
			sub = quantum > duration ? duration: quantum;

			if(DEBUG)
				System.out.printf("Running job (%d) $ %d %d\n", tmp.getID(), clock, clock + sub);
			// set values
			tmp.subDuration(sub);
			clock += duration;
			if(tmp.getDuration() > 0){
				for(int i = 0 ; i < list.size() ; ++i){
					if(list.get(i).getArrival() <= clock)
						index++;
				}
				list.add(index, tmp);
			} else
				;
			list.remove(0);
		}	
	}
	
	// SJF - Shortest Job First
	public static void SJF(Queue<Job> queue) {
		// Need PriorityQueue
		Queue<Job> priorityQueue = new PriorityQueue<Job>();
		// average values
		float waiting = 0.0f, turnaround = 0.0f;
		// aux
		int length = queue.size();
		int duration = 0, clock = 0, sum = 0;
		Job tmp = queue.peek();
		int offset = tmp.getArrival();
		// != empty
		do {
			while(!queue.isEmpty() && queue.peek().getArrival() <= clock + offset)
				priorityQueue.add(queue.poll());

			// get first element
			tmp = priorityQueue.peek();
			duration = tmp.getDuration();

			if(DEBUG)
				System.out.printf("Running job (%d) $ %d %d\n", tmp.getID(), clock, clock+duration);
			// set values
			sum = (clock + offset) - tmp.getArrival();
			waiting += sum;
			turnaround += sum + duration;
			clock += duration;
			priorityQueue.poll();
		
		} while(!priorityQueue.isEmpty() || !queue.isEmpty());
		
		waiting/=length;
		turnaround/=length;
		// needless to calculate the response time since waiting = response (ALWAYS)
		if(INFO)
			System.out.printf("SJF %.1f %.1f %.1f\n", turnaround, waiting, waiting);

	} 
} 