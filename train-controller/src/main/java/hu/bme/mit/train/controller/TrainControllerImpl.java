package hu.bme.mit.train.controller;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	private int emergencyBrake = -500;
	

	public TrainControllerImpl() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            followSpeed();
        }, 0, 1, TimeUnit.SECONDS);
    }

	@Override
	public void followSpeed() {
		if (step == emergencyBrake){
			referenceSpeed = 0;
		} else {
			if (referenceSpeed < 0) {
				referenceSpeed = 0;
			} else {
				if(referenceSpeed+step > 0) {
					referenceSpeed += step;
				} else {
					referenceSpeed = 0;
				}
			}
		}
		enforceSpeedLimit();
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
		//Ha a joystick position megeggyezik a "maximális értékkel - új állapot -500 értékkel, akkor 0-ra állítom"
		if (step == emergencyBrake) {
			referenceSpeed = 0;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}

}
