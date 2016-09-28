// Created By: Marcos alves
// Created Date: Sept. 28th, 2016	  
// Last Modified: Sept. 28th, 2016	    

public class Time {
	//enums
	public enum Math { ADD, SUB, MUL, DIV, SET };
	//attributes
	private float arrival, duration;
	private float response, waiting, turnaround;
	private float clock, offset, in, out;

	//constructor
	public Time(float arrival, Float duration){
		this.arrival = arrival;
		this.duration = duration;
	}
	//constructor
	public Time(float response, float waiting, float turnaround){
		this.response = response;
		this.waiting = waiting;
		this.turnaround = turnaround;
	}
	//constructor
	public Time(float clock, float offset, float in, float out){
		this.clock = clock;
		this.offset = offset;
		this.in = in;
		this.out = out;
	}
	//constructor default
	public Time(){
		arrival = duration = 0.0f;
		response = waiting = turnaround = 0.0f;
		clock = offset = in = out = 0.0f;
	}

	//methods
	//get arrival
	public float getArrival(){
		return arrival;
	}
	//get clock
	public float getClock(){
		return clock;
	}
	//get duration
	public float getDuration(){
		return duration;
	}
	//get out
	public float getIn(){
		return in;
	}
	//get out
	public float getOut(){
		return out;
	}
	//get offset
	public float getOffset(){
		return offset;
	}
	//get response
	public float getResponse(){
		return response;
	}
	//get turnaround
	public float getTurnaround(){
		return turnaround;
	}
	//get waiting
	public float getWaiting(){
		return waiting;
	}
	//set arrival
	public void setArrival(float a, Math operation){
		this.arrival = operation(operation, arrival, a);
	}
	//set clock
	public void setClock(float c, Math operation){
		this.clock = operation(operation, clock, c);
	}
	//set duration
	public void setDuration(float d, Math operation){
		this.duration = operation(operation, duration, d);
	}
	//set in
	public void setIn(float in){
		this.in = in;
	}
	//set out
	public void setOut(float out){
		this.out = out;
	}
	//set offset
	public void setOffset(float offset){
		this.offset = offset;
	}
	//set response
	public void setResponse(float r, Math operation){
		this.turnaround = operation(operation, response, r);
	}	
	//set turnaround
	public void setTurnaround(float t, Math operation){
		this.turnaround = operation(operation, turnaround, t);
	}
	//set waiting
	public void setWaiting(float w, Math operation){
		this.waiting = operation(operation, waiting, w);
	}
	//choice operation with time
	private float operation(Math choice, float attrClass, float value){
		switch(choice){
			case ADD: attrClass += value; break;
			case SUB: attrClass -= value; break;
			case MUL: attrClass *= value; break;
			case DIV: attrClass /= value; break;	
			case SET: attrClass = value; break;
			default: throw new IllegalArgumentException("Undefined Math operation!");
		}
		return attrClass;
	}
}