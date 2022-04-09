import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ElevatorSystemTest {
    @Test
    void shouldCreate4Elevators(){
        //given
        ElevatorSystem elevatorSystem = new ElevatorSystem(4);
        int expectedAmount = 4;

        //when
        int actualAmount = elevatorSystem.getElevators().size();

        //then
        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    void shouldPickupFirstIfAllSameDistance(){
        //given
        ElevatorSystem elevatorSystem = new ElevatorSystem(4);
        List<Elevator> elevators = elevatorSystem.getElevators();

        int firstExpectedTarget = 3;
        int othersExpectedTarget = 0;

        //when
        elevatorSystem.pickup(firstExpectedTarget);

        //then
        assertEquals(firstExpectedTarget, elevators.get(0).getTargetFloor());
        assertEquals(othersExpectedTarget, elevators.get(1).getTargetFloor());
        assertEquals(othersExpectedTarget, elevators.get(2).getTargetFloor());
        assertEquals(othersExpectedTarget, elevators.get(3).getTargetFloor());
    }

    @Test
    void shouldPickupFirstNonBusyIfAllSameDistance(){
        //given
        ElevatorSystem elevatorSystem = new ElevatorSystem(4);
        List<Elevator> elevators = elevatorSystem.getElevators();

        int expectedTarget = 3;
        int othersExpectedTarget = 0;
        int busyExpectedTarget = 5;

        //when
        elevators.get(0).chooseTargetFloor(busyExpectedTarget);
        elevatorSystem.pickup(expectedTarget);

        //then
        assertEquals(busyExpectedTarget, elevators.get(0).getTargetFloor());
        assertEquals(expectedTarget, elevators.get(1).getTargetFloor());
        assertEquals(othersExpectedTarget, elevators.get(2).getTargetFloor());
        assertEquals(othersExpectedTarget, elevators.get(3).getTargetFloor());
    }

    @Test
    void shouldPickupClosestNonBusy(){
        //given
        ElevatorSystem elevatorSystem = new ElevatorSystem(4);
        List<Elevator> elevators = elevatorSystem.getElevators();

        int pickupFloor = 1;
        int busyExpectedTarget = 5;


        //when
        elevators.get(0).chooseTargetFloor(busyExpectedTarget);
        elevators.get(3).chooseTargetFloor(busyExpectedTarget);

        elevators.get(1).update(elevators.get(1).getId(), 4, 4);
        elevators.get(2).update(elevators.get(2).getId(), 3, 3);

        elevatorSystem.pickup(pickupFloor);

        //then
        assertEquals(busyExpectedTarget, elevators.get(0).getTargetFloor());
        assertEquals(4, elevators.get(1).getTargetFloor());
        assertEquals(pickupFloor, elevators.get(2).getTargetFloor());
        assertEquals(busyExpectedTarget, elevators.get(3).getTargetFloor());
    }
}
