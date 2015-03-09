package lab5.carwash;

import java.util.ArrayList;
import java.util.Random;

import lab5.simulator.Event;
import lab5.simulator.SimState;


public class CarWashState extends SimState {
	
	private ArrayList<Integer> fastWash = new ArrayList<Integer>();
	private int fastWashMax;
	private ArrayList<Integer> slowWash = new ArrayList<Integer>();
	private int slowWashMax;
	private ArrayList<Integer> carQueue = new ArrayList<Integer>();
	private int carQueueMax;
	
	public ArrayList<Integer> getFastWashQueue() {
		return fastWash;
	}
	
	public int getFastWashMax() {
		return fastWashMax;
	}
	
	public ArrayList<Integer> getSlowWashQueue() {
		return slowWash;
	}
	
	public int getSlowWashMax() {
		return slowWashMax;
	}
	
	public ArrayList<Integer> getCarQueue() {
		return carQueue;
	}
	
	public int getCarQueueMax() {
		return carQueueMax;
	}
	
	private int rejectedCars = 0;
	private int counter = 0; // total antal bilar som anlänt (inkluderar rejectedCars) 
	private double time = 0d;
	private double idleTime = 0d;
	private double queueTime = 0d;
	
	public int getRejectedCars() {
		return rejectedCars;
	}
	
	public void increaseRejectedCars() {
		rejectedCars++;
	}
	
	public int getTotalCars() {
		return counter;
	}
	
	public void increaseTotalCars() {
		counter++;
	}
	
	public double getTime() {
		return time;
	}
	
	public double getIdleTime() {
		return idleTime;
	}
	
	public double getQueueTime() {
		return queueTime;
	}
	
	private CarFactory cFactory = new CarFactory();
	
	public int getNextCarId() {
		return cFactory.nextId();
	}
	
	private ExponentialRandomStream arrivalRand;
	private UniformRandomStream fastWashRand;
	private UniformRandomStream slowWashRand;
	
	public double getNextArrival() {
		return arrivalRand.next();
	}
	
	public double getFastWashTime() {
		return fastWashRand.next();
	}
	
	public double getSlowWashTime() {
		return slowWashRand.next();
	}
	
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
	
	public void setStateChange(Event e) {
		this.setChanged();
		this.notifyObservers(e);
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
