package lab5;

public class CarWashEventStop extends Event {
	public CarWashEventStop(double p) {
		super(p);
	}
	
	/**
	 * Stop-eventet sÃ¤tter statets stopvariabel till sann sÃ¥ exekveringen av
	 * simuleringen slutar vid nÃ¤sta koll efter event.
	 * Sedan printar den ut slut info (se lab 5 PDF:en).
	 */
	public void execute(Simulator sim, SimState ss) {
		ss.stop = true;
		
		System.out.println("-----------------------------------------");
		System.out.println("Total idle machine time: " + ss.idleTime);
		System.out.println("Total queueing time: " + ss.time);
		System.out.println("Mean queueing time: " + ((ss.time)/(ss.counter)));
		System.out.println("Rejected cars: " + ss.rejectedCars);
		
	}
	
	public String toString() {
		return "";
	}
}
