package lab5.simulator;

/**
 * The event queue contains a sorted sequence in which the events are stored.
 * You are able to add event, get its length, and pull the next event in form of
 * an event object.
 * This class is rather redundant at this point.
 * */
public class EventQueue {
	
	private SortedSequence ss;
	
	/**
	 * Initializes the sorted sequence to be used.
	 * */
	public EventQueue() {
		ss = new SortedSequence();
	}
	
	/**
	 * Adds an event to the sorted sequence by calling the appropriate function.
	 * @param e Event The event to be added.
	 * @return void
	 * */
	public void addEvent(Event e) {
		ss.addEvent(e);
	}
	
	/** 
	 * Pulls the next event from the queue.
	 * @return Event
	 * */
	public Event getNext() {
		return ss.getNext();
	}
	
	/** 
	 * Returns the current length of the queue.
	 * return int
	 * */
	public int length() {
		return ss.length();
	}
}
