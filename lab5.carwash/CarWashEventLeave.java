package lab5.carwash;

import java.util.ArrayList;

import lab5.simulator.SimState;
import lab5.simulator.Simulator;

public class CarWashEventArrive extends CarWashEvent {
	
	public CarWashEventArrive(double p, int id) {
		super(p, id);
		
	}
	
	/**
	 * Increases time- and idleTime variable. Searches for car id in the fast wash, then the slow one. If the car id 
	 * is a wash, it removes it and then it add a new car id from the car queue (the first one in queue) to a wash.
	 * Fast washes are prioritized over slow washes. After that a new leave event for that car id is generated with the next priority.
	 * 
	 *  @param sim Simulator shell.
	 *  @param ss CarWashState
	 *  @return void
	 */
	public void execute(Simulator sim, SimState ss) {
		CarWashState ss2 = (CarWashState) ss;
		double deltaTime = this.priority - ss2.getTime();
		/*ss2.time+= deltaTime;
		ss2.idleTime+= ((ss2.fastWashMax-ss2.fastWash.size())+(ss2.slowWashMax-ss2.slowWash.size()))*(deltaTime); 
		//ss2.queueTime += (ss2.carQueue.size()) * (this.priority - ss2.time);
		ss2.queueTime += (ss2.carQueue.size()* deltaTime);*/
		ss2.updateTime(deltaTime);
		
		ss2.setStateChange(this);
		
		ArrayList<Integer> fastWash = ss2.getFastWashQueue();
		ArrayList<Integer> slowWash = ss2.getSlowWashQueue();
		ArrayList<Integer> carQueue = ss2.getCarQueue();
		
		if (fastWash.contains(id)){
			fastWash.remove((Integer)id);
			if (!carQueue.isEmpty()){
				if (fastWash.size()<ss2.getFastWashMax()){
					int bilId=carQueue.get(0);
					fastWash.add(bilId);
					carQueue.remove(0);
					sim.addEvent(new CarWashEventLeave(ss2.getTime()+ss2.getFastWashTime(),bilId ));
				}else if(slowWash.size()<ss2.getSlowWashMax()){
					int bilId=carQueue.get(0);
					slowWash.add(bilId);
					carQueue.remove(0);
					sim.addEvent(new CarWashEventLeave(ss2.getTime()+ss2.getSlowWashTime(),bilId ));
				}
			}
			
		}
		else if (slowWash.contains(id)){
			slowWash.remove((Integer)id);
			if (!carQueue.isEmpty()){
				if (fastWash.size()<ss2.getFastWashMax()){
					int bilId = carQueue.get(0);
					fastWash.add(bilId);
					carQueue.remove(0);
					sim.addEvent(new CarWashEventLeave(ss2.getTime() +ss2.getFastWashTime(), bilId));
				}else if(slowWash.size()<ss2.getSlowWashMax()){
					int bilId = carQueue.get(0);
					slowWash.add(bilId);
					carQueue.remove(0);
					sim.addEvent(new CarWashEventLeave(ss2.getTime() +ss2.getSlowWashTime(), bilId));
				}
			}
		}
		
				
	}		
		

	public String toString() {
		return "Leave";
	}
}
