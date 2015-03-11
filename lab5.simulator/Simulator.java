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
	 * It will also add the observer view as an observer to the observable state.
	 * 
	 * @param s SimState The simstate is a state which till be manipulated by the events.
	 * @param sv SimView SimView is an observer which will watch the observable state.
	 * @param eStart Event The start event that should be added with a priority 0.0.
	 * @param eStop Event The stop event that should set state variable stop to true and 
	 * be added with a priority more than 0.0. 
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
	 * @param e Event The event to be added into the queue.
	 * @return void
	 * */
	public void addEvent(Event e) {
		queue.addEvent(e);
	}
	
	/** 
	 * The main loop will execute an event, if there are any, and stop if there aren't or if the state has its
	 * stop boolean set to true.
	 * @return void
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
