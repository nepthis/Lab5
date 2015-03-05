package lab5;

import java.util.ArrayList;
import java.util.Random;


public class CarWashState extends SimState {
	
	ArrayList<Integer> fastWash = new ArrayList<Integer>();
	int fastWashMax;
	ArrayList<Integer> slowWash = new ArrayList<Integer>();
	int slowWashMax;
	ArrayList<Integer> carQueue = new ArrayList<Integer>();
	int carQueueMax;
	
	int rejectedCars = 0;
	int counter = 0; // total antal bilar som anlänt (inkluderar rejectedCars) 
	double time = 0d;
	double idleTime = 0d;
	
	CarFactory cFactory = new CarFactory();
	
	ExponentialRandomStream arrivalRand;
	UniformRandomStream fastWashRand;
	UniformRandomStream slowWashRand;
	
	/**
	 * Klassen initialiserar storleken på den snabba och långsamma biltvätten
	 * samt storleken på kön.
	 * Den skapar också 3 randomiserare.
	 */
	public CarWashState(int fastMax, int slowMax, int queueMax) {
		super();
		fastWashMax = fastMax;
		slowWashMax = slowMax;
		carQueueMax = queueMax;
		
		arrivalRand = new ExponentialRandomStream(2, 1234);
		fastWashRand = new UniformRandomStream(2.8, 4.6, 1234);
		slowWashRand = new UniformRandomStream(3.5, 6.7, 1234);
	}

	
	public void stop() {
		this.stop = true;
	}
	
}

class ExponentialRandomStream {
	
	private Random rand;
	private double lambda;
	  
	public ExponentialRandomStream(double lambda, long seed) {
	  	rand = new Random(seed);
	  	this.lambda = lambda;
	}
	  
	public ExponentialRandomStream(double lambda) {
		rand = new Random();
	    this.lambda = lambda;
	}
	  
	public double next() {
	  	return -Math.log(rand.nextDouble())/lambda;
	}
}

class UniformRandomStream {

	private Random rand;
	private double lower, width;
	
	public UniformRandomStream(double lower, double upper, long seed) {
		rand = new Random(seed);
		this.lower = lower;
		this.width = upper-lower;
	}
	
	public UniformRandomStream(double lower, double upper) {
		rand = new Random();
	    this.lower = lower;
	    this.width = upper-lower;
	}
	
	public double next() {
	    return lower+rand.nextDouble()*width;
	}
}


