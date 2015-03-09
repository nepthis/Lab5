package lab5.carwash;

import java.util.ArrayList;

import lab5.simulator.SimState;
import lab5.simulator.Simulator;

public class CarWashEventArrive extends CarWashEvent {
	
	public CarWashEventArrive(double p, int id) {
		super(p, id);
		
	}
	
	/**
	 * Timevariabeln i statevariabeln ökas med detta eventets tid minus timevariabeln.
	 * 
	 * Finns det några lediga biltvättar måste även tidigare nämnd deltatid gångras med antal lediga tvättar,
	 * och plussas på i idleTime i statevariabeln.
	 * 
	 * Sedan läggs en bil till i den snabba eller långsamma biltvätten.
	 * Den snabba prioriteras över den långsamma. Finns ingen ledig plats läggs den till i kön.
	 * Lades en bil till i en tvätt, måste ett leave-event genereras med prioriteten som fås av
	 * ss.slowWashRand.next() om den ligger i en långsam biltvätt eller ss.fastWashRand.next() om
	 * den ligger i en snabb biltvätt.
	 * Är kön i sin tur full ska rejectedCars i statevariabeln ökas.
	 * 
	 * Sist skall ett nytt arrive genereras med prioriteten som fås av ss.arrivalRand() och funktionen avslutas.
	 */
	 /**
	  * 
	  * Ökar både tidsvariabeln och idletidsvariabeln. Kollar om det finns någon tvätt ledig, prioriterar snabba
	  * tvätten över den långsamma. Finns det ledig tvätt tvättas bilen, annars läggs den i kön. Är kön full
	  * blir bilen rejected och rejectedCarsvariabeln ökas. 
	  * Till slut generaras ett nytt Arrive-event med nästa prioritet.
	  * 
	  * 
	  * @param Simulator sim Simulator shell.
	  * @Simstate CarWashState.
	  * @return void
	  */
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
	 * 
	 * @return Returns the name of the event as a string. 
	 */
	public String toString() {
		return "Arrive";
	}
}
