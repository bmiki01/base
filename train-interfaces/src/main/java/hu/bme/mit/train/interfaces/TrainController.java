package hu.bme.mit.train.interfaces;

public interface TrainController {

	void followSpeed();

	void incrementing() throws InterruptedException;

	void startIncrementing();

	void stopIncrementing();

	int getReferenceSpeed();

	void setSpeedLimit(int speedLimit);

	void setJoystickPosition(int joystickPosition);

}
