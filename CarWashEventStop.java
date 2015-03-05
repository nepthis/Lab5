package lab5;

public class CarWashEventStop extends Event {
	public CarWashEventStop(double p) {
		super(p);
	}
	
	/**
	 * Stop-eventet sÃƒÂ¤tter statets stopvariabel till sann sÃƒÂ¥ exekveringen av
	 * simuleringen slutar vid nÃƒÂ¤sta koll efter event.
	 * Sedan printar den ut slut info (se lab 5 PDF:en).
	 */
	public void execute(Simulator sim, SimState ss) {
		ss.stop = true;
		
		CarWashState ss2 = (CarWashState) ss;
		
		System.out.println("-----------------------------------------");
		System.out.println("Total idle machine time: " + ss2.idleTime);
		System.out.println("Total queueing time: " + ss2.time);
		System.out.println("Mean queueing time: " + ((någon total kötid)/(antalet bilar)));
		System.out.println("Rejected cars: " + ss2.rejectedCars);
		
	}
	
	public String toString() {
		return "";
	}
}

