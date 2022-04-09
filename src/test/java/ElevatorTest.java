import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ElevatorTest {
    @Test
    void shouldCreateElevator(){
        //given
        int expectedId = 1;
        int expectedCurrentFloor = 0;
        int expectedTargetFloor = 0;

        //when
        Elevator elevator = new Elevator(1);
        List<Integer> elevatorInfo = elevator.status();
        int actualId = elevatorInfo.get(0);
        int actualCurrentFloor = elevatorInfo.get(1);
        int actualTargetFloor = elevatorInfo.get(2);

        //then
        assertEquals(expectedId, actualId);
        assertEquals(expectedCurrentFloor, actualCurrentFloor);
        assertEquals(expectedTargetFloor, actualTargetFloor);
    }

    @Test
    void shouldUpdateElevator(){
        //given
        int expectedId = 3;
        int expectedCurrentFloor = 2;
        int expectedTargetFloor = 5;
        Elevator elevator = new Elevator(1);

        //when
        elevator.update(3, 2, 5);
        List<Integer> elevatorInfo = elevator.status();
        int actualId = elevatorInfo.get(0);
        int actualCurrentFloor = elevatorInfo.get(1);
        int actualTargetFloor = elevatorInfo.get(2);


        //then
        assertEquals(expectedId, actualId);
        assertEquals(expectedCurrentFloor, actualCurrentFloor);
        assertEquals(expectedTargetFloor, actualTargetFloor);
    }

    @Test
    void shouldBeBusy(){
        //given
        Elevator elevator = new Elevator(1);
        elevator.chooseTargetFloor(4);

        //when
        boolean actualValue = elevator.isBusy();

        //then
        assertTrue(actualValue);
    }

    @Test
    void shouldNotBeBusy(){
        //given
        Elevator elevator = new Elevator(1);

        //when
        boolean actualValue = elevator.isBusy();

        //then
        assertFalse(actualValue);
    }

    @Test
    void shouldChooseTargetFloorIfNotBusy(){
        //given
        Elevator elevator = new Elevator(1);
        int expectedFloor = 4;

        //when
        boolean isBusyBefore = elevator.isBusy();
        elevator.chooseTargetFloor(expectedFloor);
        boolean isBusyAfter = elevator.isBusy();

        //then
        assertFalse(isBusyBefore);
        assertEquals(expectedFloor, elevator.getTargetFloor());
        assertTrue(isBusyAfter);
    }

    @Test
    void shouldNotChooseTargetFloorIfBusy(){
        //given
        Elevator elevator = new Elevator(1);
        int expectedFloor = 3;
        elevator.update(elevator.getId(), elevator.getCurrentFloor(), expectedFloor);


        //when
        boolean isBusyBefore = elevator.isBusy();
        elevator.chooseTargetFloor(4);
        boolean isBusyAfter = elevator.isBusy();

        //then
        assertTrue(isBusyBefore);
        assertEquals(expectedFloor, elevator.getTargetFloor());
        assertTrue(isBusyAfter);
    }

    @Test
    void shouldNotMove(){
        //given
        Elevator elevator = new Elevator(1);
        int expectedCurrentFloor = 0;


        //when
        elevator.move();
        int actualCurrentFloor = elevator.getCurrentFloor();

        //then
        assertEquals(expectedCurrentFloor, actualCurrentFloor);
    }

    @Test
    void shouldMoveUp(){
        //given
        Elevator elevator = new Elevator(1);
        elevator.chooseTargetFloor(3);
        int expectedCurrentFloor = 1;


        //when
        elevator.move();
        int actualCurrentFloor = elevator.getCurrentFloor();

        //then
        assertEquals(expectedCurrentFloor, actualCurrentFloor);
    }

    @Test
    void shouldMoveDown(){
        //given
        Elevator elevator = new Elevator(1);
        elevator.update(elevator.getId(), 5, 2);
        int expectedCurrentFloor = 4;


        //when
        elevator.move();
        int actualCurrentFloor = elevator.getCurrentFloor();

        //then
        assertEquals(expectedCurrentFloor, actualCurrentFloor);
    }
}
