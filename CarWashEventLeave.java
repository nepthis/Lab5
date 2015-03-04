package lab5;

public class CarWashEventLeave extends Event {
	
	private int id;
	private double prio;
	
	public CarWashEventLeave(double p, int id) {
		prio=p;
		this.id = id;
		super(p);
		
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
	public void execute(Simulator sim, CarWashState ss) {
		ss.time= prio- ss.time;
		ss.idleTime= prio= ss.idleTime; //förstod inte riktigt formulering om vad som skulle göras här
		
		
		if (ss.fastWash.contains(id)){
			ss.fastWash.remove(id);
			if (!ss.carQueue.isEmpty()){
				if (ss.fastWash.size()<ss.fastWashMax){
					ss.fastWash.add(ss.carQueue.get(0));
					ss.carQueue.remove(0);
				}else if(ss.slowWash.size()<ss.slowWashMax){
					ss.slowWash.add(ss.carQueue.get(0));
					ss.carQueue.remove(0);
				}
				CarWashEventLeave(ss.carQueue.get(0)); //eller nåt
			}
			
		}
		else if (ss.slowWash.contains(id)){
			ss.slowWash.remove(id);
			if (!ss.carQueue.isEmpty()){
				if (ss.fastWash.size()<ss.fastWashMax){
					ss.fastWash.add(ss.carQueue.get(0));
					ss.carQueue.remove(0);
				}else if(ss.slowWash.size()<ss.slowWashMax){
					ss.slowWash.add(ss.carQueue.get(0));
					ss.carQueue.remove(0);
				}
				CarWashEventLeave(ss.carQueue.get(0));
			}
		}
				
	}		
		

	public String toString() {
		return "";
	}
}
