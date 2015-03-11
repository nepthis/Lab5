package lab5.carwash;

import lab5.simulator.Event;

abstract public class CarWashEvent extends Event {
	int id;
	
	public CarWashEvent(double p, int id) {
		super(p);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	/** 
	 * The toString method is supposed to return the name of the event for
	 * the view to use.
	 * @return String
	 * */
	public abstract String toString();
}
