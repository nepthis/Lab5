package lab5;

public class CarWashEventArrive extends Event {
	private int id;
	public CarWashEventArrive(double p, int id) {
		id = this.id;
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
		ss.time = ss.time + prio;
		ss.idleTime += (ss.fastWashMax - ss.fastWash.size()) * prio + (ss.slowWashMax - ss.slowWash.size()) * prio;
		
		if(ss.fastWash.size() <= ss.fastWashMax){
			ss.fastWash.add(id);
			CarWashEventLeave leave = new CarWashEventLeave(ss.fastWashRand.next());
			setChanged();
			notifyObservers;
		}
		else if(ss.slowWash.size() <= ss.slowWashMax){
			ss.slowWash.add(id);
			CarWashEventLeave leave = new CarWashEventLeave(ss.slowWashRand.next());
			setChanged();
			notifyObservers;
		}
		else if(ss.carQueue.size() <= ss.carQueueMax){
			ss.carQueue.add(id);
			setChanged();
			notifyObservers;
		}
		else(){
			rejectedCars++;
			setChanged();
			notifyObservers;
		}
		CarWashArrive(ss.arrivalRand());

	}
	
	public String toString() {
		return "";
	}
}
