package lab5;

public class CarWashEventArrive extends Event {
	public CarWashEventArrive(double p) {
		super(p);
		
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
	public void execute(Simulator sim, SimState ss) {
		CarWashState cws = (CarWashState) ss;
		int id = cws.cFactory.nextId();
		cws.counter++;
		int deltaTime = this.priority-cws.time;
		cws.time += deltaTime;
		cws.idleTime += ((cws.fastWashMax - cws.fastWash.size()) + (cws.slowWashMax - cws.slowWash.size())) * deltaTime;
		cws.queueTime += (cws.carQueue.size()) * deltaTime;
		ss.setChanged();
		ss.notifyObservers();
		if(cws.fastWash.size() < cws.fastWashMax){
			cws.fastWash.add(id);
			sim.addEvent(new CarWashEventLeave(cws.fastWashRand.next() + cws.time, id);
		}
		else if(cws.slowWash.size() < cws.slowWashMax){
			cws.slowWash.add(id);
			sim.addEvent(new CarWashEventLeave(cws.slowWashRand.next() + cws.time, id);
		}
		else if(cws.carQueue.size() < cws.carQueueMax){
			cws.carQueue.add(id);
		}
		else(){
			cws.rejectedCars++;
		}

	}
	
	public String toString() {
		return "Arrive";
	}
}
