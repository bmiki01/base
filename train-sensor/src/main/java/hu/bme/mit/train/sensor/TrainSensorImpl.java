package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import com.google.common.collect.Table;
import com.google.common.collect.HashBasedTable;
import java.time.LocalDateTime;

public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private TrainUser user;
	private int speedLimit = 5;

	Table<LocalDateTime, Integer, Integer> tachoGraph = HashBasedTable.create();

	public TrainSensorImpl(TrainController controller, TrainUser user) {
		this.controller = controller;
		this.user = user;
	}

	@Override
	public boolean checkAbsoluteLimit(int speedlim){
		return speedlim<0 || speedlim > 500;
	}

	@Override
	public boolean checkRelativeLimit(int speedlim){
		return speedlim < controller.getReferenceSpeed()*0.5;
	}


	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {
		if(checkAbsoluteLimit(speedLimit)||checkRelativeLimit(speedLimit)){
			this.user.setAlarmState(true);
		}
		this.speedLimit = speedLimit;
		controller.setSpeedLimit(speedLimit);
	}

	@Override
	public void append(LocalDateTime time, int joystickPosition, int referenceSpeed) {
		tachoGraph.put(time, joystickPosition, referenceSpeed);
	}

	public Table<LocalDateTime, Integer, Integer> getTachoGraph() {
		return tachoGraph;
	}

}
