package lab5;

import lab5.carwash.*;
import lab5.simulator.Simulator;

public class MainSim {
	public static void main(String[] args) {
		Simulator sim = new Simulator(new CarWashState(2,2,5, 2.8,4.6, 3.5, 6.7, 1234l, 2d), new CarView(), new CarWashEventStart(0d), new CarWashEventStop(15d));
		sim.mainLoop();
	}
}
