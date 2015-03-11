package lab5.carwash;

import java.util.ArrayList;
import java.util.Random;

import lab5.simulator.Event;
import lab5.simulator.SimState;

/**
 * This is the state to be changed by the CarWashEvent's extenders. It contains a variety 
 * of state variables that keep track of certain things. The point of the class is to keep track
 * of two car washers (in the form of ArrayLists) and a car queue (also an ArrayList).
 * It supplies functions to change add, remove and the like that are to be used by the events.
 * */
public class CarWashState extends SimState {
	
	private ArrayList<Integer> fastWash = new ArrayList<Integer>();
	private int fastWashMax;
	private ArrayList<Integer> slowWash = new ArrayList<Integer>();
	private int slowWashMax;
	private ArrayList<Integer> carQueue = new ArrayList<Integer>();
	private int carQueueMax;
	
	/**
	 * Returns the fast carwasher in the form of an ArrayList.
	 * @return ArrayList<Integer>
	 * */
	public ArrayList<Integer> getFastWashQueue() {
		return fastWash;
	}
	/**
	 * Returns the max size that the fast carwasher should be.
	 * return int
	 * */
	public int getFastWashMax() {
		return fastWashMax;
	}
	
	/**
	 * Returns the slow car washer in the form of an ArrayList
	 * @return ArrayList<Integer>
	 * */
	public ArrayList<Integer> getSlowWashQueue() {
		return slowWash;
	}
	
	/** 
	 * Returns the max size the slow carwasher should be.
	 * @return int
	 * */
	public int getSlowWashMax() {
		return slowWashMax;
	}
	
	/**
	 * Returns the car queue in the form of an ArrayList
	 * @return ArrayList<Integer>
	 * */
	public ArrayList<Integer> getCarQueue() {
		return carQueue;
	}
	
	/**
	 * Returns the max size the car queue should be
	 * @return int
	 * */
	public int getCarQueueMax() {
		return carQueueMax;
	}
	
	private int rejectedCars = 0;
	private int counter = 0; // total antal bilar som anlänt (inkluderar rejectedCars) 
	private double time = 0d;
	private double idleTime = 0d;
	private double queueTime = 0d;
	
	/**
	 * Gets the amount of rejected cars.
	 * @return int
	 * */
	public int getRejectedCars() {
		return rejectedCars;
	}
	
	/**
	 * Increases the amount of rejected cars by one
	 * @return void
	 * */
	public void increaseRejectedCars() {
		rejectedCars++;
	}
	
	/**
	 * Gets the total amount of cars that has been processed
	 * @return int
	 * */
	public int getTotalCars() {
		return counter;
	}
	
	/**
	 * Increases the total amount of cars processed by one
	 * @return void
	 * */
	public void increaseTotalCars() {
		counter++;
	}
	
	/**
	 * Gets the amount "time" that has gone through the state
	 * @return double
	 * */
	public double getTime() {
		return time;
	}
	
	/**
	 * Returns the amount of time the carwashers have been idle
	 * @return double
	 * */
	public double getIdleTime() {
		return idleTime;
	}
	
	/**
	 * Returns the total amount of time cars have been queueing
	 * @return double
	 * */
	public double getQueueTime() {
		return queueTime;
	}
	
	private CarFactory cFactory = new CarFactory();
	
	/**
	 * Returns the next car ID to be used, provided by the CarFactory.
	 * @return int
	 * */
	public int getNextCarId() {
		return cFactory.nextId();
	}
	
	private ExponentialRandomStream arrivalRand;
	private UniformRandomStream fastWashRand;
	private UniformRandomStream slowWashRand;
	
	/**
	 * Returns the next random delta time to be used to calculate when the next car
	 * arrival should be.
	 * @return double
	 * */
	public double getNextArrival() {
		return arrivalRand.next();
	}
	
	/**
	 * Returns the next random delta time to be used to calculate when a car
	 * in the fast carwasher should leave.
	 * @return double
	 * */
	public double getFastWashTime() {
		return fastWashRand.next();
	}
	
	/**
	 * Returns the next random delta time to be used to calculate when a car
	 * in the slow carwasher should leave.
	 * @return double
	 * */
	public double getSlowWashTime() {
		return slowWashRand.next();
	}
	
	double fastDist1, fastDist2, slowDist1, slowDist2, lambda;
	long seed;
	
	/**
	 * The class intializes the max sizes of the carwashes and the queue and also seeds and numbers used
	 * for the randomisers.
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
	
	/**
	 * Sets the state to changed and calls on the observers update method with the Event that called
	 * the function as an argument.
	 * @param e Event This should be the event the called this function.
	 * @return void
	 * */
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
