package lab5.carwash;

import lab5.simulator.SimState;
import lab5.simulator.Simulator;

public class CarWashEventStop extends CarWashEvent {
	public CarWashEventStop(double p) {
		super(p, -1);
	}
	
	/**
	 * Stop-eventet sätter statets stopvariabel till sann så exekveringen av
	 * simuleringen slutar vid nästaa koll efter event.
	 * Sedan printar den ut slut info (se lab 5 PDF:en).
	 */
	public void execute(Simulator sim, SimState ss) {
		CarWashState ss2 = (CarWashState) ss;
		
		double deltaTime = this.priority - ss2.time;
		ss2.updateTime(deltaTime);
		
		ss2.setStateChange();
		ss2.notifyObservers(this);
		
		ss2.setStopped();
	}
	
	public String toString() {
		return "Stop";
	}
}

