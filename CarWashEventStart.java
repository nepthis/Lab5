package lab5;

public class CarWashEventStart extends Event {
	
	public CarWashEventStart(double p) {
		super(p);
	}
	
	/**
	 * I CarWashEventStarts execute metod generar det första arrive eventet
	 * samt printa ut de ursprungliga värdena
	 */
	public void execute(Simulator sim, SimState ss) {
		CarWashState ss2 = (CarWashState) ss;
		sim.addEvent(new CarWashArrive(ss.arrivalRand.next(), ss.cFactory.nextId()));
		
		System.out.println("Fast machines: " + ss2.fastWashMax);
		System.out.println("Slow machines: " + ss2.slowWashMax);
		
		System.out.println("Fast distribution: " + "(2,8, 4,6)");
		System.out.println("Slow distribution: " + "(3,5, 6,7)");
		System.out.println("Exponential distribution with lambda = 2,0");
		System.out.println("Seed = 1234");
		System.out.println("--------------------------------------------");
		
		System.out.println("Fast machines: " + ss2.carQueueMax);
		
		ss2.setChanged();
		ss2.notifyObservers();
		
		
	}
	
	public String toString() {
		return "Start";
	}
}

