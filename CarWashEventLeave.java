package lab5;

public class CarWashEventLeave extends Event {
	
	private int id;
	
	public CarWashEventLeave(double p, int id) {
		super(p);
		this.id = id;
		
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
		ss2.time+= getPriority()- ss2.time;
		ss2.idleTime= (ss2.fastWashMax-ss2.fastWash.size())+(ss2.slowWashMax-ss2.slowWash.size())*(getPriority()-ss2.time); 
		
		
		if (ss2.fastWash.contains(id)){
			ss2.fastWash.remove(id);
			if (!ss2.carQueue.isEmpty()){
				if (ss2.fastWash.size()<ss2.fastWashMax){
					ss2.fastWash.add(ss2.carQueue.get(0));
					ss2.carQueue.remove(0);
				}else if(ss2.slowWash.size()<ss2.slowWashMax){
					ss2.slowWash.add(ss2.carQueue.get(0));
					ss2.carQueue.remove(0);
				}
				sim.addEvent(new CarWashEventLeave(ss2.fastWashRand.next(), id));
			}
			
		}
		else if (ss2.slowWash.contains(id)){
			ss2.slowWash.remove(id);
			if (!ss2.carQueue.isEmpty()){
				if (ss2.fastWash.size()<ss2.fastWashMax){
					ss2.fastWash.add(ss2.carQueue.get(0));
					ss2.carQueue.remove(0);
				}else if(ss2.slowWash.size()<ss2.slowWashMax){
					ss2.slowWash.add(ss2.carQueue.get(0));
					ss2.carQueue.remove(0);
				}
				sim.addEvent(new CarWashEventLeave(ss2.fastWashRand.next(), id));
			}
		}
		
				
	}		
		

	public String toString() {
		return "";
	}


}
