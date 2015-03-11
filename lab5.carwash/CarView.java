package lab5.carwash;

import java.util.ArrayList;
import java.util.Observable;

import lab5.simulator.SimView;
public class CarView extends SimView {

	/** 
	 * Updates the CarWashState and prints out different information about the carwash (every time it updates) depending on the event.
	 * @param o The observer that observers whenever something happens in the carwash.
	 * @param e An object which in this case is the event.
	 */

    public void update(Observable o, Object e) {
    	CarWashState cws = (CarWashState) o;
    	CarWashEvent cwe = (CarWashEvent) e;
    	
    	if(cwe.toString() == "Start") {
    		System.out.println("Fast machines: " + cws.getFastWashMax());
    		System.out.println("Slow machines: " + cws.getSlowWashMax());
    		System.out.println("Fast distribution: " + "(" + cws.fastDist1+ ", "+ cws.fastDist2+")");
    		System.out.println("Slow distribution: " + "(" + cws.slowDist1+ ", "+ cws.slowDist2+")");
    		System.out.println("Exponential distribution with lambda = "+ cws.lambda);
    		System.out.println("Seed = "+cws.seed);
    		System.out.println("Max Queue size: " + cws.getCarQueueMax());
    		System.out.println("--------------------------------------");
    		System.out.format("%-8s%-8s%-8s%-8s%-10s%-14s%-14s%-14s%-10s\n", "Time", "Fast", "Slow", "Id", "Event", "IdleTime", "QueueTime", "QueueSize", "Rejected");
    	}
    	ArrayList<Integer> fastWash = cws.getFastWashQueue();
    	ArrayList<Integer> slowWash = cws.getSlowWashQueue();
    	ArrayList<Integer> carQueue = cws.getCarQueue();

    	System.out.format("%-8.2f %-7d %-7d%-8s%-10s  %-12.2f  %-12.2f   %-11d   %-7d\n",
    			cws.getTime(), cws.getFastWashMax()-fastWash.size(), cws.getSlowWashMax() - slowWash.size(),
    			(cwe.getId() == -1 ? "-" : Integer.toString(cwe.getId())),
    			cwe, cws.getIdleTime(), cws.getQueueTime(), carQueue.size(), cws.getRejectedCars());
    	
        
    	if(cwe.toString() == "Stop") {
    		System.out.println("-----------------------------------");
    		System.out.format("Total idle machine time: %.2f\n" +
    				"Total queueing time: %.2f\n" +
    				"Mean queueing time: %.2f\n" +
    				"Rejected cars: %d", 
    				cws.getIdleTime(), cws.getQueueTime(), cws.getQueueTime()/(cws.getTotalCars()-cws.getRejectedCars()), cws.getRejectedCars());
    	}	
    }
}
