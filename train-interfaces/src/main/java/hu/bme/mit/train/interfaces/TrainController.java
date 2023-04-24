package hu.bme.mit.train.interfaces;

public interface TrainController {

	void followSpeed();

	void incrementSpeed();

	int getReferenceSpeed();

	void setSpeedLimit(int speedLimit);

	void setJoystickPosition(int joystickPosition);

}
