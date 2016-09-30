// v. Java SE 8
// --
// Author: Marcos Alves
// ++     

public class Job implements Comparable<Job> {
	//attributes
	private int arrival, duration, id;

	//constructor
	public Job(int id ,int arrival, int duration){
		this.arrival = arrival;
		this.duration = duration;
		this.id = id;
	} 

	//methods
	public int compareTo(Job j){
		if(duration > j.duration)
			return 1;
		else if (duration < j.duration)
			return -1;
		else 
			return 0;
	}
	//get arrival
	public int getArrival(){
		return arrival;
	}
	//get duration
	public int getDuration(){
		return duration;
	}
	//get identity
	public int getID(){
		return id;
	}
	//set id
	public void setID(int index){
		id = id==0 ? index : id;
	}
	//return string formatted
	public String toString(){
		return "Arrival: "+ arrival +" | Time: "+ duration;
	}
}