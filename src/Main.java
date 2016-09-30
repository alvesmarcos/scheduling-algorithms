// Created By: Marcos alves
// Created Date: Sept. 10th, 2016	  
// Last Modified: Sept. 28th, 2016	      

import java.util.*;

public class Main {
	public static void main(String args[]){
		// Object reader
		Scanner reader = new Scanner(System.in);
		// buffers
		String line = null;
		String[] parts = null;
		Queue<Job> queue = new LinkedList<Job>();
		int index = 0;
	
		while(reader.hasNextLine()){
			line = reader.nextLine();

			if(line.contains(" ")){
				parts = line.split(" ");
				queue.add(new Job(index++, Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
			} else
				throw new IllegalArgumentException("File doens't contains SPACE!");
		}	

		AlgorithmScheduler.SJF(queue);
	}
}