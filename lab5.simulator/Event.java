package lab5.simulator;

/**
 * This Event class' purpose is to change a state. It contains a priority (seen as time in certain
 * circumstances) that will be decide in which order it will be executed compared to other event objects.
 * */
public abstract class Event {

	protected double priority;
	
	/**
	 * If no priority of the type double is given, this constructer will initialize the priority as 0.
	 * */
	public Event() {
		priority = 0d;
	}
	
	/**
	 * Initializes the priority to be equal to the double given as an argument.
	 * @param p double The priority to be used.
	 * */
	public Event(double p) {
		priority = p;
	}
	
	/**
	 * Returns the priority of the object.
	 * @return double
	 * */
	public double getPriority() {
		return priority;
	}
	
	/**
	 * The method is executed in the simulators main loop and its purpose is to change or manipulate the
	 * SimState object given as an argument.
	 * @param sim Simulator The current simulator object this event object is a "part" of. If this object wants
	 * to add a new event, it has to go through the Simulator.
	 * @param ss SimState The state to be changed by the event.
	 * @return void
	 * */
	public abstract void execute(Simulator sim, SimState ss);
}
