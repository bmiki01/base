package hu.bme.mit.train.interfaces;
import java.time.LocalDateTime;
import com.google.common.collect.Table;
import com.google.common.collect.HashBasedTable;

public interface TrainSensor {

	int getSpeedLimit();

	public void append(LocalDateTime time, int joystickPosition, int referenceSpeed);

	public Table<LocalDateTime, Integer, Integer> getTachoGraph();

	void overrideSpeedLimit(int speedLimit);

}
