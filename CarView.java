package lab5;

import java.util.Observable;
import lab5.CarWashState;
public class CarView extends lab5.SimView {

	/** 
	 * 
	 */

    private void printFinalSettings(CarWashState state){
        write("---------------------------");
        write("Total idle matchine time: %.2f", );
        write("Total queueing time: %.2f", );
        write("Mean queueing time: %.2f", );
        write("Rejected cars: %d", );
    }

    private void printSettings(CarWashState state) {
        write("Fast machines: %d", state.fastWashMax);
        write("Slow machines: %d", state.slowWashMax);

        write("Fast distribution: (%.1f, %.1f)", );
        write("Slow distribution: (%.1f, %.1f)", );
        write("Exponential distribution with lambda = %.1f", );

        write("Seed = %d", );
        write("Max Queue size: %d", );

        write("-----------------------------------");
        write("%-8s%-6s%-6s%-5s%-11s%-10s%-11s%-11s%-10s", "Time", "Fast", "Slow", "Id", "Event", "IdleTime", "QueueTime", "QueueSize", "Rejected");



    public void update(Observable o, Object e) {

        if (e instanceof lab5.CarWashEventStart) {

            printSettings((CarWashState) o);
        }

        if (e instanceof lab5.CarWashEventArrive){


        }

        if (e instanceof lab5.CarWashEventLeave){


        }

        if (e instanceof lab5.CarWashEventStop){
            printFinalSettings((CarWashState) o);
        }
    }
}
}
