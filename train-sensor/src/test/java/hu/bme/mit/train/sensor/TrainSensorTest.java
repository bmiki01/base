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
    //private TrainSensor sensor;
    TrainSensorImpl trainSensorImpl;
    LocalDateTime firstTimestamp;
    LocalDateTime secondTimestamp;

    @Before
    public void before() {
        // TODO Add initializations
        controller = mock(TrainController.class);
        user = mock(TrainUser.class);
       // sensor = new TrainSensorImpl(controller,user);

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
    
    
    @Test
    public void absoluteLimit_AlarmOn(){
        when(controller.getReferenceSpeed()).thenReturn(100);
        trainSensorImpl.overrideSpeedLimit(501);
        verify(user,times(1)).setAlarmState(true);
    }

    @Test
    public void absoluteLimit_AlarmOn2(){
        when(controller.getReferenceSpeed()).thenReturn(100);
        trainSensorImpl.overrideSpeedLimit(-1);
        verify(user,times(1)).setAlarmState(true);
    }

    @Test
    public void relativeLimit_AlarmOn(){
        when(controller.getReferenceSpeed()).thenReturn(500);
        trainSensorImpl.overrideSpeedLimit(249);
        verify(user,times(1)).setAlarmState(true);
    }

    @Test
    public void relativeLimit_AlarmOff(){
        when(controller.getReferenceSpeed()).thenReturn(500);
        trainSensorImpl.overrideSpeedLimit(255);
        verify(user,times(0)).setAlarmState(true);
    }

    @Test
    public void testDefaultSpeedLimitValue(){
        Assert.assertEquals(5, trainSensorImpl.getSpeedLimit());
    }

}
