package lab5.simulator;

	/**
	 * Simulator can be considered the "main class" in the simulator pacakge.
	 * It keeps track of an event queue, a state and a view. When started, the main loop
	 * will execute events until the state either has its stop boolean set to true, or 
	 * there are no more events to execute.
	 * */
public class Simulator {
	private EventQueue queue;
	private SimState state;
	private SimView view;
	
	/**
	 * This will initialize the object with a view and a state and add a start event and a stop event.
	 * */
	public Simulator(SimState s, SimView sv, Event eStart, Event eStop) {
		state = s;
		view = sv;
		
		queue = new EventQueue();
		queue.addEvent(eStart);
		queue.addEvent(eStop);
		
		state.addObserver(view);
	}
	
	/**
	 * Adds an event to the event queue
	 * */
	public void addEvent(Event e) {
		queue.addEvent(e);
	}
	
	/** 
	 * The main loop will execute an event, if there are any, and stop if there aren't or if the state has its
	 * stop boolean set to true.
	 * */
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
