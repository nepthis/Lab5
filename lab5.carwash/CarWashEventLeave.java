package lab5.carwash;

import lab5.simulator.SimState;
import lab5.simulator.Simulator;

public class CarWashEventLeave extends CarWashEvent {

	
	public CarWashEventLeave(double p, int id) {
		super(p, id);
		
	}
	
	/**
	 * I leave-eventets executemetod skall först öka på timevariabeln i statet
	 * med delta-tiden (detta events prioritet minus statets tid) och sedan idleTime 
	 * i statet om det finns några lediga biltvättar
	 * och sedan idleTime i statet om några lediga tvättar finns.
	 * 
	 * Efter det söker man igenom långsamma och snabba biltvättar efter id:t som skall tas bort
	 * och gör det om id:t finns.
	 * 
	 * Om ett id togs bort skall det första id:t i statets kö (om det finns bilar i kön) läggas till
	 * i en biltvätt, med högre prioritet på en snabb tvätt. Sedan genereras ett till leave-event för det id:t
	 * 
	 * Sedan avslutas funktionen.
	 */
	public void execute(Simulator sim, SimState ss) {
		CarWashState ss2 = (CarWashState) ss;
		double deltaTime = this.priority - ss2.time;
		/*ss2.time+= deltaTime;
		ss2.idleTime+= ((ss2.fastWashMax-ss2.fastWash.size())+(ss2.slowWashMax-ss2.slowWash.size()))*(deltaTime); 
		//ss2.queueTime += (ss2.carQueue.size()) * (this.priority - ss2.time);
		ss2.queueTime += (ss2.carQueue.size()* deltaTime);*/
		ss2.updateTime(deltaTime);
		
		ss2.setStateChange();
		ss2.notifyObservers(this);
		
		if (ss2.fastWash.contains(id)){
			ss2.fastWash.remove((Integer)id);
			if (!ss2.carQueue.isEmpty()){
				if (ss2.fastWash.size()<ss2.fastWashMax){
					int bilId=ss2.carQueue.get(0);
					ss2.fastWash.add(bilId);
					ss2.carQueue.remove(0);
					sim.addEvent(new CarWashEventLeave(ss2.time+ss2.fastWashRand.next(),bilId ));
				}else if(ss2.slowWash.size()<ss2.slowWashMax){
					int bilId=ss2.carQueue.get(0);
					ss2.slowWash.add(bilId);
					ss2.carQueue.remove(0);
					sim.addEvent(new CarWashEventLeave(ss2.time+ss2.slowWashRand.next(),bilId ));
				}
			}
			
		}
		else if (ss2.slowWash.contains(id)){
			ss2.slowWash.remove((Integer)id);
			if (!ss2.carQueue.isEmpty()){
				if (ss2.fastWash.size()<ss2.fastWashMax){
					int bilId = ss2.carQueue.get(0);
					ss2.fastWash.add(bilId);
					ss2.carQueue.remove(0);
					sim.addEvent(new CarWashEventLeave(ss2.time +ss2.fastWashRand.next(), bilId));
				}else if(ss2.slowWash.size()<ss2.slowWashMax){
					int bilId = ss2.carQueue.get(0);
					ss2.slowWash.add(bilId);
					ss2.carQueue.remove(0);
					sim.addEvent(new CarWashEventLeave(ss2.time +ss2.slowWashRand.next(), bilId));
				}
			}
		}
		
				
	}		
		

	public String toString() {
		return "Leave";
	}


}
