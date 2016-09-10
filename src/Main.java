// Created By: Marcos alves
// Created Date: Sept. 10th, 2016	  
// Last Modified: Sept. 10th, 2016	      

import java.util.*;
import java.io.*;


public class Main {
	public static void main(String args[]){
		String path = null;
		List<Job> jobs = new ArrayList<Job>();
		Scanner read = new Scanner(System.in);
			
		if(args.length == 0){
			System.out.print("[path] ~> ");	
			path = read.next();
		}
		else
			path = args[0];

		try {
			// optimize if/when necessary
			String line = null;
			String[] parts = null;
			BufferedReader br = new BufferedReader(new FileReader(path)); 

			while((line = br.readLine()) != null){
				if(line.contains(" ")){
					parts = line.split(" ");
					jobs.add(new Job(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
				} else
					throw new IllegalArgumentException("File "+path+" doens't contain [space]");
			}	
			
		} catch(IOException e){
			e.printStackTrace();
		} 

		for(Job e: jobs)
			System.out.println(e.toString());
	}
}