package lab5;

public class CarWashEventStart extends Event {
	
	public CarWashEventStart(double p) {
		super(p);
	}
	
	/**
	 * I CarWashEventStarts execute metod generar det fÃ¶rsta arrive eventet
	 * samt printa ut de ursprungliga vÃ¤rdena
	 */
	public void execute(Simulator sim, SimState ss) {
		CarWashArrive(ss.arrivalRand());
//		CarWashState ss3 = new CarWashState();
//		int FastMax = ss3.fastWashMax;
//		int SlowMax = ss3.slowWashMax;
//		int QueueMax = ss3.carQueueMax;
//		
//		int FastMax = ss.fastWashMax;
//		int SlowMax = ss.slowWashMax;
//		int QueueMax = ss.carQueueMax;
		
		System.out.println("Fast machines: " + FastMax);
		System.out.println("Slow machines: " + SlowMax);
		
		System.out.println("Fast distribution: " + "(2,8, 4,6)");
		System.out.println("Slow distribution: " + "(3,5, 6,7)");
		System.out.println("Exponential distribution with lambda = 2,0");
		System.out.println("Seed = 1234");
		System.out.println("--------------------------------------------");
		
		
		System.out.println("Fast machines: " + QueueMax);
		
		
	}
	
	public String toString() {
		return "";
	}
}
