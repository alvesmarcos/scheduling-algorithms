// Created By: Marcos alves
// Created Date: Sept. 10th, 2016	  
// Last Modified: Sept. 13th, 2016	      

import java.util.*;

public class Main {
	public static void main(String args[]){
		Scheduler shr = new Scheduler(Routine.FCFS);

		shr.loaderJobs();
		shr.printQueue();
	}
}