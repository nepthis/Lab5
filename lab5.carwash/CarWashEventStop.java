package lab5.carwash;

import lab5.simulator.SimState;
import lab5.simulator.Simulator;

	/**
	 *The class CarWashEventStop makes the simulation stop at the next loop 
	 * 
	 */
public class CarWashEventStop extends CarWashEvent {
	public CarWashEventStop(double p) {
		super(p, -1);
	}

	 
	 /**
	 * The method execute calculate the delta time and update the time
	 * 
	 */
	public void execute(Simulator sim, SimState ss) {
		CarWashState ss2 = (CarWashState) ss;
		
		double deltaTime = this.priority - ss2.getTime();
		ss2.updateTime(deltaTime);
		
		ss2.setStateChange(this);
		
		ss2.setStopped();
	}
	/**
	 *
	 * 
	 */
	
	public String toString() {
		return "Stop";
	}
}

