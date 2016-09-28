// Created By: Marcos alves
// Created Date: Sept. 10th, 2016	  
// Last Modified: Sept. 28th, 2016	      

import java.util.*;

public class Main {
	public static void main(String args[]){
		Scheduler sched = new Scheduler(Scheduler.Algorithm.RR);

		sched.printQueue();
		sched.run();
		sched.info();
	}
}