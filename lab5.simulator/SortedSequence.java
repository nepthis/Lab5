package lab5.simulator;

import java.util.ArrayList;

/**
 * This class takes care of the list of events, sorts it when a new event is added
 * and pulls the next event in the queue when the apprioriate function is called.
 * */
public class SortedSequence {
	private ArrayList<Event> ss;
	
	/**
	 * Initializes the ArrayList to be used to store events.
	 * */
	public SortedSequence() {
		ss = new ArrayList<Event>();
	}
	
	/**
	 * Adds an event to the event queue. Before doing so it iterates over the events in the list
	 * comparing their priority, should the priority of the event in the list be higher than the priority of the
	 * event to be added, it will simply push all the events slightly up and add the event there.
	 * Otherwise it will appended at the end.
	 * 
	 * @param e Event The event to be added.
	 * @return void
	 * */
	public void addEvent(Event e) {
		//System.out.println("Adding event " + e.toString() + " with priority "+ e.getPriority());
		for(int i = 0; i < ss.size(); i++) {
			if(ss.get(i).getPriority() > e.getPriority()) {
				ss.add(i, e);
				return; 
			}
		}
		
		ss.add(e);
	}
	
	/** 
	 * Gets the next event and removes it from the queue, effectively pulling it.
	 * @return Event
	 * */
	public Event getNext() {
		if(ss.size() == 0) {
			return null;
		}
		
		Event e = ss.get(0);
		ss.remove(0);
		return e;
	}
	
	/** 
	 * Returns the length of the queue.
	 * return int
	 * */
	public int length() {
		return ss.size();
	}
}
