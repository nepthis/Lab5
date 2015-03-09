package lab5.carwash;

import java.util.ArrayList;
import java.util.Random;

import lab5.simulator.SimState;


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
	double queueTime = 0d;
	
	CarFactory cFactory = new CarFactory();
	
	ExponentialRandomStream arrivalRand;
	UniformRandomStream fastWashRand;
	UniformRandomStream slowWashRand;
	
	double fastDist1, fastDist2, slowDist1, slowDist2, lambda;
	long seed;
	
	/**
	 * Klassen initialiserar storleken på den snabba och långsamma biltvätten
	 * samt storleken på kön.
	 * Den skapar också 3 randomiserare.
	 */
	public CarWashState(int fastMax, int slowMax, int queueMax, double fastDist1, double fastDist2, double slowDist1, double slowDist2, long seed, double lambda) {
		super();
		fastWashMax = fastMax;
		slowWashMax = slowMax;
		carQueueMax = queueMax;
		
		this.fastDist1 = fastDist1;
		this.fastDist2 = fastDist2;
		this.slowDist1 = slowDist1;
		this.slowDist2 = slowDist2;
		this.seed = seed;
		this.lambda = lambda;
		
		arrivalRand = new ExponentialRandomStream(lambda, seed);
		fastWashRand = new UniformRandomStream(fastDist1,fastDist2, seed);
		slowWashRand = new UniformRandomStream(slowDist1, slowDist2, seed);
	}
	
	public void setStateChange() {
		this.setChanged();
	}
	
	public void updateTime(double deltaTime) {
		time += deltaTime;
		idleTime += ((fastWashMax - fastWash.size()) + (slowWashMax - slowWash.size())) * deltaTime;
		queueTime += (carQueue.size()) * deltaTime;
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


