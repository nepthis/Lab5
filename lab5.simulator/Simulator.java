package lab5.simulator;


public class Simulator {
	private EventQueue queue;
	private SimState state;
	private SimView view;
	
	public Simulator(SimState s, SimView sv, Event eStart, Event eStop) {
		state = s;
		view = sv;
		
		queue = new EventQueue();
		queue.addEvent(eStart);
		queue.addEvent(eStop);
		
		state.addObserver(view);
	}
	
	public void addEvent(Event e) {
		queue.addEvent(e);
	}
	
	public void mainLoop() {

		Event e;
		while(!state.hasStopped()) {
			if(!(queue.length() == 0)) {
				e = queue.getNext();
			} else {
				return;
			}
			
			e.execute(this, state);
		}
	}
}
