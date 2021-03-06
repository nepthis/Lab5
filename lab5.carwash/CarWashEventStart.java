package lab5.carwash;

import lab5.simulator.SimState;
import lab5.simulator.Simulator;

public class CarWashEventStart extends CarWashEvent {
	/**
	 * The class CarWashEventStart starts the carwash by creating the first arrive event 
	 * 
	 */
	
	
	public CarWashEventStart(double p) {
		super(p, -1);
	}
	
	/**
	 * 
	 * The execute method is generating the first arrive event
	 *@param sim Simulator shell.
	 *@param ss CarWashState.
	 * @return void
	 */
	public void execute(Simulator sim, SimState ss) {
		CarWashState ss2 = (CarWashState) ss;

		ss2.setStateChange(this);
		sim.addEvent(new CarWashEventArrive(ss2.getNextArrival(), ss2.getNextCarId()));
		
	}


	/**
	 * @return returns the name of the event as a string
	 * 
	 */
	public String toString() {

		return "Start";
	}
}

