package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainController controller;
	TrainUser user;
	int speedLimit = 5;

    @Before
    public void before() {
        // TODO Add initializations
        TrainSystem system = new TrainSystem();
        controller = system.getController();
        user = system.getUser();
        sensor.overrideSpeedLimit(100);
    }

    @Test
    public void testOverrideSpeedLimit() {
        // TODO Delete this and add test cases based on the issues
        Assert.assertEquals(100, sensor.getSpeedLimit());
        sensor.overrideSpeedLimit(140);
        Assert.assertEquals(140, sensor.getSpeedLimit());
    }
}
