// Created By: Marcos alves
// Created Date: Sept. 24th, 2016	  
// Last Modified: Sept. 28th, 2016	     

import java.util.*;

public class QueueJob {
	//attributes
	private int clock, len;
	private List<Job> listJob;

	//constructor
	public QueueJob(){
		clock=len=0;
		listJob = new ArrayList<Job>();	
	}

	//methods
	//Inserts the specified element at the specified position in this list
	public void add(Job j){
		int index = 0;
		if(j.getArrival() <= clock){
			for(int i = 0 ; i < len ; i++){
				if(listJob.get(i).getState() == Job.State.CREATED)
					break;
				index++;
			}
			j.setState(Job.State.READY);
			listJob.add(index, j);
		} else
			listJob.add(j);
		len++;
	}
	//clear listJob
	public void clear(){
		listJob.clear();
	}
	//print all elements
	public void forEach(){
		listJob.forEach(j->System.out.println(j));
	}
	//get clock
	public int getClock(){
		return clock;
	}
	//check if is empty
	public boolean isEmpty(){
		return listJob.isEmpty();
	}
	//return an iterator of listJob
	public Iterator<Job> iterator(){
		return listJob.iterator();
	}
	//return frist element or null
	public Job peek(){
		if(listJob.isEmpty())
			return null;
		else
			return listJob.get(0);
	}
	//retrieves and removes the head of this queue, or returns null if this queue is empty
	public Job poll(){
		if(listJob.isEmpty())
			return null;
		len--;
		Job tmp = listJob.get(0);
		
		if(tmp.getState() == Job.State.RUNNING){
			//refresh queue | clock
			tmp.setState(Job.State.TERMINATED);
			clock += tmp.getRunning();

			listJob.remove(0);
		} else
			throw new RuntimeException("State is not RUNNING");

		refresh();
		return tmp;	
	}
	//add in listJob
	public boolean push(Job j){
		len++;
		j.setID(len);
		//sync clock with first job
		if(len==1)
			clock = j.getArrival();

		if(j.getArrival() <= clock)
			j.setState(Job.State.READY);
		else
			j.setState(Job.State.CREATED);
		
		return listJob.add(j);
	}
	//refresh queue | clock/response/turnround/waiting
	private void refresh(){
		for(int i = 0 ; i < len ; i++){
			if(listJob.get(i).getArrival() <= clock)
				listJob.get(i).setState(Job.State.READY);
			else
				break;
		}
	}

	//return size listJob
	public int size(){
		return len;
	}
}