package lab5.simulator;

import java.util.Observable;

/**
 * The skeleton state class. It contains the boolean variable that decides it the
 * simulation should stop, and two functions that checks the variable or sets it to true.
 * */
public class SimState extends Observable {
	protected boolean stop;
	
	/**
	 * Initializes the stop boolean to false.
	 * */
	public SimState() {
		stop = false;
	}
	
	/**
	 * Returns the value of the stop variable.
	 * @return boolean
	 * */
	public boolean hasStopped() {
		return stop;
	}
	
	/**
	 * Sets the boolean stop to true, effectively ending the simulation.
	 * @return void
	 * /*
	public void setStopped() {
		stop = true;
	}
}
