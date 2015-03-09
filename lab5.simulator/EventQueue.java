package lab5.simulator;


public class EventQueue {
	
	private SortedSequence ss;
	
	public EventQueue() {
		ss = new SortedSequence();
	}
	
	public void addEvent(Event e) {
		ss.addEvent(e);
	}
	
	public Event getNext() {
		return ss.getNext();
	}
	
	public int length() {
		return ss.length();
	}
}
