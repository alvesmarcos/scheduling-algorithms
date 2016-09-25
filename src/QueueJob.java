// Created By: Marcos alves
// Created Date: Sept. 24th, 2016	  
// Last Modified: Sept. 24th, 2016	     

import java.util.*;

public class QueueJob {
	//attributes
	int clock, len;
	private List<Job> listJob;

	//constructor
	public QueueJob(){
		clock=len=0;
		listJob = new ArrayList<Job>();	
	}

	//methods
	//Inserts the specified element at the specified position in this list
	public void add(int index, Job j){
		len++;
		j.setID(len);
		listJob.add(index, j);
	}
	//clear listJob
	public void clear(){
		listJob.clear();
	}
	//return first element
	public Job element(){
		return listJob.get(0);
	}
	//print all elements
	public void forEach(){
		listJob.forEach(j->System.out.println(j));
	}
	//check if is empty
	public boolean isEmpty(){
		return listJob.isEmpty();
	}
	//return an iterator of listJob
	public Iterator<Job> iterator(){
		return listJob.iterator();
	}
	//add in listJob 
	public boolean offer(Job j){
		return listJob.add(j);
	}
	//return frist element or null
	public Job peek(){
		if(listJob.isEmpty())
			return null;
		else
			return element();
	}
	//retrieves and removes the head of this queue, or returns null if this queue is empty
	public Job poll(){
		if(listJob.isEmpty())
			return null;
		else 
			return remove();
	}
	//add in listJob
	public boolean push(Job j){
		len++;
		j.setID(len);
		return listJob.add(j);
	}
	//retrieves and removes the head of this queue
	public Job remove(){
		len--;
		Job tmp = listJob.get(0);
		listJob.remove(0);
		return tmp;
	}
	//add in adjust clock
	public void setClockAndAdd(Job j, int time){
		clock+=time;
		int index=-1;
		for(int i = 0 ; i < len; i++){
			if(listJob.get(i).getArrival() > clock){
				index=i; 
				break;
			}
		}
		if(index==-1)
			push(j);
		else
			add(index, j);
		
	}
	//return size listJob
	public int size(){
		return len;
	}
}