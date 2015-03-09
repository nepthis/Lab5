package lab5.carwash;

import java.util.Observable;

import lab5.simulator.SimView;
public class CarView extends SimView {

	/** 
	 * 
	 */

    public void update(Observable o, Object e) {
    	CarWashState cws = (CarWashState) o;
    	CarWashEvent cwe = (CarWashEvent) e;
    	
    	if(cwe.toString() == "Start") {
    		System.out.println("Fast machines: " + cws.fastWashMax);
    		System.out.println("Slow machines: " + cws.slowWashMax);
    		System.out.println("Fast distribution: " + "(" + cws.fastDist1+ ", "+ cws.fastDist2+")");
    		System.out.println("Slow distribution: " + "(" + cws.slowDist1+ ", "+ cws.slowDist2+")");
    		System.out.println("Exponential distribution with lambda = "+ cws.lambda);
    		System.out.println("Seed = "+cws.seed);
    		System.out.println("Max Queue size: " + cws.carQueueMax);
    		System.out.println("--------------------------------------");
    		System.out.format("%-8s%-8s%-8s%-8s%-10s%-14s%-14s%-14s%-10s\n", "Time", "Fast", "Slow", "Id", "Event", "IdleTime", "QueueTime", "QueueSize", "Rejected");
    	}


    	System.out.format("%-8.2f %-7d %-7d%-8s%-10s  %-12.2f  %-12.2f   %-11d   %-7d\n",
    			cws.time, cws.fastWashMax-cws.fastWash.size(), cws.slowWashMax-cws.slowWash.size(),
    			(cwe.getId() == -1 ? "-" : Integer.toString(cwe.getId())),
    			cwe, cws.idleTime, cws.queueTime, cws.carQueue.size(), cws.rejectedCars);
    	
        
    	if(cwe.toString() == "Stop") {
    		System.out.println("-----------------------------------");
    		System.out.format("Total idle machine time: %.2f\n" +
    				"Total queueing time: %.2f\n" +
    				"Mean queueing time: %.2f\n" +
    				"Rejected cars: %d", 
    				cws.idleTime, cws.queueTime, cws.queueTime/(cws.counter-cws.rejectedCars), cws.rejectedCars);
    	}	
    }
}
