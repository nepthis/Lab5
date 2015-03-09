package lab5.carwash;

public class CarFactory {
	private int counter;
	
	public CarFactory() {
		counter = 0;
	}
	
	public int nextId () {
		return counter++;
	}
}
