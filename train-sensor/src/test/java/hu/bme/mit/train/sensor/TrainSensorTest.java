package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;
import java.time.LocalDateTime;

public class TrainSensorTest {
    private TrainController controller;
    private TrainUser user;
    TrainSensorImpl trainSensorImpl;
    LocalDateTime firstTimestamp;
    LocalDateTime secondTimestamp;

    @Before
    public void before() {
        // TODO Add initializations
        controller = mock(TrainController.class);
        user = mock(TrainUser.class);

        trainSensorImpl = new TrainSensorImpl(controller, user);

        firstTimestamp = LocalDateTime.now();
        trainSensorImpl.append(firstTimestamp, 0,10);

        secondTimestamp = LocalDateTime.now();
        trainSensorImpl.append(secondTimestamp, 1,20);
    }

    @Test
    public void ThisIsAnExampleTestStub() {
        Assert.assertEquals(Integer.valueOf(10), trainSensorImpl.getTachoGraph().get(firstTimestamp, 0));
    }

    @Test
    public void tachographTableLengthTest() {
        Assert.assertEquals(2, trainSensorImpl.getTachoGraph().size());
    }   
}
