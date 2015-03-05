package lab5;

public abstract class Event {

	protected double priority;
	
	public Event() {
		priority = 0d;
	}
	
	public Event(double p) {
		priority = p;
	}
	
	public double getPriority() {
		return priority;
	}
	
	public abstract void execute(Simulator sim, SimState ss);
	
	public abstract String toString();
}
