package lab5.carwash;

import java.util.ArrayList;

import lab5.simulator.SimState;
import lab5.simulator.Simulator;

public class CarWashEventArrive extends CarWashEvent {
	
	public CarWashEventArrive(double p, int id) {
		super(p, id);
		
	}
	
	/**
	 * Increases time- and idleTime variable. Searches for car id in the fast wash, then the slow one. If the car id 
	 * is a wash, it removes it and then it add a new car id from the car queue (the first one in queue) to a wash.
	 * Fast washes are prioritized over slow washes. After that a new leave event for that car id is generated with the next priority.
	 * 
	 *  @param sim Simulator shell.
	 *  @param ss CarWashState
	 *  @return void
	 */
	 
	 //FEL KOD HÄRRRRR
	public void execute(Simulator sim, SimState ss) {
		CarWashState cws = (CarWashState) ss;
		cws.increaseTotalCars();
		double deltaTime = this.priority-cws.getTime();
		/*cws.time += deltaTime;
		cws.idleTime += ((cws.fastWashMax - cws.fastWash.size()) + (cws.slowWashMax - cws.slowWash.size())) * deltaTime;
		cws.queueTime += (cws.carQueue.size()) * deltaTime;*/
		cws.updateTime(deltaTime);
		
		cws.setStateChange(this);
		
		ArrayList<Integer> fastWash = cws.getFastWashQueue();
		ArrayList<Integer> slowWash = cws.getSlowWashQueue();
		ArrayList<Integer> carQueue = cws.getCarQueue();
		
		if(fastWash.size() < cws.getFastWashMax()){
			fastWash.add(id);
			sim.addEvent(new CarWashEventLeave(cws.getFastWashTime() + cws.getTime(), id));
		}
		else if(slowWash.size() < cws.getSlowWashMax()){
			slowWash.add(id);
			sim.addEvent(new CarWashEventLeave(cws.getSlowWashTime() + cws.getTime(), id));
		}
		else if(carQueue.size() < cws.getCarQueueMax()){
			carQueue.add(id);
		}
		else {
			cws.increaseRejectedCars();
		}
		
		sim.addEvent(new CarWashEventArrive(cws.getNextArrival() + cws.getTime(), cws.getNextCarId()));


	}
	/**
	* @return returns the name of the event as a string
	*/
	public String toString() {
		return "Arrive";
	}
}
