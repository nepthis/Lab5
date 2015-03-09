package lab5.simulator;

import java.util.Observable;

public class SimState extends Observable {
	protected boolean stop;
	
	public SimState() {
		stop = false;
	}
	
	public boolean hasStopped() {
		return stop;
	}
	
	public void setStopped() {
		stop = true;
	}
}
