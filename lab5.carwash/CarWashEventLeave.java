package lab5.carwash;

import java.util.ArrayList;

import lab5.simulator.SimState;
import lab5.simulator.Simulator;

public class CarWashEventArrive extends CarWashEvent {
	
	public CarWashEventArrive(double p, int id) {
		super(p, id);
		
	}
	
	/**
	 * Ökar tids- och idletidvariabeln. Söker igenom den snabba och sedan långsamma tvätten om id:t för bilen finns.
	 * Om den gör det, ta bort den och lägga till första id:t från bilkön(om det finns) i en en tvätt, med högre priorietet
	 * på snabb tvätt. Sedan genereras ett nytt leave event för det id:t
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
	
	public String toString() {
		return "Arrive";
	}
}
