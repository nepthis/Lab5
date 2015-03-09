package lab5.simulator;

import java.util.ArrayList;


public class SortedSequence {
	private ArrayList<Event> ss;
	
	public SortedSequence() {
		ss = new ArrayList<Event>();
	}
	
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
	
	public Event getNext() {
		if(ss.size() == 0) {
			return null;
		}
		
		Event e = ss.get(0);
		ss.remove(0);
		return e;
	}
	
	public int length() {
		return ss.size();
	}
}
