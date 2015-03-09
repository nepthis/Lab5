package lab5.carwash;

import lab5.simulator.SimState;
import lab5.simulator.Simulator;

public class CarWashEventStart extends CarWashEvent {
	
	public CarWashEventStart(double p) {
		super(p, -1);
	}
	
	/**
	 * I CarWashEventStarts execute metod generar det första arrive eventet
	 * samt printa ut de ursprungliga värdena
	 */
	public void execute(Simulator sim, SimState ss) {
		CarWashState ss2 = (CarWashState) ss;

		ss2.setStateChange(this);
		sim.addEvent(new CarWashEventArrive(ss2.getNextArrival(), ss2.getNextCarId()));
		
	}
	
	public String toString() {
		return "Start";
	}
}

