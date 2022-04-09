import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {
    private final ArrayList<Elevator> elevators = new ArrayList<>();

    public ElevatorSystem(int elevatorCount){
        for (int i = 0; i < elevatorCount; i++){
            elevators.add(new Elevator(i+1));
        }
    }

    public void pickup(int floor){
        Elevator closestElevator = new Elevator(0);
        int minDistance = Integer.MAX_VALUE;
        for (Elevator elevator : elevators){
            if (!elevator.isBusy()){
                int distance = Math.abs(floor - elevator.getCurrentFloor());
                if (distance < minDistance){
                    minDistance = distance;
                    closestElevator = elevator;
                }
            }
        }
        closestElevator.chooseTargetFloor(floor);
    }


    public void step(){
        for (Elevator elevator : elevators){
            elevator.move();
        }
    }

    public List<List<Integer>> status(){
        List<List<Integer>> systemStatus = new ArrayList<>();
        for (Elevator elevator : elevators){
            systemStatus.add(elevator.status());
        }
        return systemStatus;
    }

    public List<Elevator> getElevators(){
        return elevators;
    }

}
