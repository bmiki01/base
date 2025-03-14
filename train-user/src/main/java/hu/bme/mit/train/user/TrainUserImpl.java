package hu.bme.mit.train.user;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainUserImpl implements TrainUser {

	private TrainController controller;
	private int joystickPosition;
	private boolean isAlerted = false;

	public TrainUserImpl(TrainController controller) {
		this.controller = controller;
	}

	@Override
	public boolean getAlarmFlag() {
		boolean igaz = false;
		return false;
	}

	@Override
	public boolean getAlarmState(){
		return this.isAlerted;
	}

	@Override
	public void setAlarmState(boolean state){
		this.isAlerted = state;
	}

	@Override
	public int getJoystickPosition() {
		return joystickPosition;
	}

	@Override
	public void overrideJoystickPosition(int joystickPosition) {
		this.joystickPosition = joystickPosition;
		controller.setJoystickPosition(joystickPosition);
	}

}
