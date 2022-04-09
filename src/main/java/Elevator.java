import java.util.ArrayList;
import java.util.List;

public class Elevator {
    private int id;
    private int currentFloor;
    private int targetFloor;

    public Elevator(int id) {
        this.id = id;
        currentFloor = 0;
        targetFloor = 0;
    }

    public List<Integer> status(){
        List<Integer> elevatorInfo = new ArrayList<>();
        elevatorInfo.add(id);
        elevatorInfo.add(currentFloor);
        elevatorInfo.add(targetFloor);
        return elevatorInfo;
    }

    public void update(int id, int currentFloor, int targetFloor){
        this.id = id;
        this.currentFloor = currentFloor;
        this.targetFloor = targetFloor;
    }

    public void chooseTargetFloor(int targetFloor){
        if (!isBusy()){
            update(getId(), getCurrentFloor(), targetFloor);
        }
    }

    public void move(){
        if (targetFloor < currentFloor){
            currentFloor--;
        }else if (targetFloor > currentFloor){
            currentFloor++;
        }
    }

    public boolean isBusy(){
        return currentFloor != targetFloor;
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getTargetFloor() {
        return targetFloor;
    }
}
