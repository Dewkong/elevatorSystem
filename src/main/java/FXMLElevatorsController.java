import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLElevatorsController implements Initializable {
    @FXML
    TextField floorField;

    @FXML
    TextField generateField;

    @FXML
    Button generateButton;

    @FXML
    Button pickupButton;

    @FXML
    Button stepButton;

    @FXML
    Button statusButton;

    @FXML
    GridPane mainGrid;

    ElevatorSystem elevatorSystem;
    GridPane gridElevators;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusButton.setDisable(true);
        stepButton.setDisable(true);
        floorField.setDisable(true);
        pickupButton.setDisable(true);

        gridElevators = new GridPane();
        gridElevators.setAlignment(Pos.TOP_CENTER);
        gridElevators.setStyle("-fx-border-insets: 5px");
        gridElevators.setStyle("-fx-padding: 5px");
        gridElevators.setStyle("-fx-background-insets: 5px");
        gridElevators.setVgap(10);
        gridElevators.setHgap(10);

        ScrollPane scrollElevators = new ScrollPane();
        scrollElevators.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollElevators.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollElevators.setVmax(440);
        scrollElevators.setMinWidth(630);
        scrollElevators.setMaxWidth(630);
        scrollElevators.setStyle("-fx-padding: 0px");
        scrollElevators.setContent(gridElevators);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(5, 5, 5, 5));
        vbox.getChildren().add(scrollElevators);
        mainGrid.add(vbox, 0, 2);
    }

    private Label generateLabel(String labelText){
        Label label = new Label(labelText);
        label.setAlignment(Pos.CENTER);
        label.setMinWidth(125);
        label.setMaxWidth(125);
        label.setMinHeight(40);
        return label;
    }

    public void generateElevators(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Please check your elevator input");
        alert.setContentText("Amount of elevators has to be an integer in range 1-16");
        try{
            int elevatorAmount = Integer.parseInt(generateField.getText());
            if (elevatorAmount < 17 && elevatorAmount > 0){
                elevatorSystem = new ElevatorSystem(elevatorAmount);

                statusButton.setDisable(false);
                stepButton.setDisable(false);
                floorField.setDisable(false);
                pickupButton.setDisable(false);

                generateField.setVisible(false);
                generateButton.setVisible(false);

                loadElevatorsUI();
            }else{
                alert.showAndWait();
            }
        }catch (NumberFormatException e){

            alert.showAndWait();
        }
    }

    public void loadElevatorsUI(){
        int i = 0;
        for (Elevator elevator : elevatorSystem.getElevators()){
            VBox boxElevator = new VBox();
            boxElevator.setAlignment(Pos.CENTER);
            boxElevator.setStyle("-fx-background-color: #44DD99");
            boxElevator.setPadding(new Insets(10, 10, 10, 10));

            List<Integer> elevatorStatus = elevator.status();

            Label idLabel = generateLabel("Elevator " + elevatorStatus.get(0));
            Label currentLabel = generateLabel("Current floor: " + elevatorStatus.get(1));
            Label targetLabel = generateLabel("Target floor: " + elevatorStatus.get(2));
            HBox boxFloor = new HBox();
            boxFloor.setAlignment(Pos.CENTER);
            TextField targetField = new TextField();
            targetField.setPrefHeight(26.0);
            targetField.setPrefWidth(36.0);
            targetField.setDisable(elevator.isBusy());
            Button targetButton = new Button("Choose");
            targetButton.setDisable(elevator.isBusy());
            targetButton.setOnAction(e -> {
                try{
                    int targetFloor = Integer.parseInt(targetField.getText());
                    elevator.chooseTargetFloor(targetFloor);
                    loadElevatorsUI();
                }catch (NumberFormatException numberFormatException){
                    floorAlert();
                }
            });
            boxFloor.getChildren().add(targetButton);
            boxFloor.getChildren().add(targetField);

            boxElevator.getChildren().add(idLabel);
            boxElevator.getChildren().add(currentLabel);
            boxElevator.getChildren().add(targetLabel);
            boxElevator.getChildren().add(boxFloor);

            gridElevators.add(boxElevator, i % 4, i / 4);
            i++;
        }
    }

    public void showStatus(){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Elevator Status");
        alert.setHeaderText(null);

        List<List<Integer>> status = elevatorSystem.status();
        ScrollPane scrollPane = new ScrollPane();
        VBox vbox = new VBox();
        for (List<Integer> stat : status){
            vbox.getChildren().add(new Text("Elevator " + stat.get(0)
                    + " | Current floor: " + stat.get(1)
                    + " | Target floor : " + stat.get(2)
            ));
        }
        scrollPane.setContent(vbox);
        scrollPane.setPrefHeight(125);
        scrollPane.setPrefWidth(280);
        alert.getDialogPane().setContent(scrollPane);

        alert.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        alert.showAndWait();
    }

    public void makeStep(){
        elevatorSystem.step();
        loadElevatorsUI();
    }

    public void pickup(){

        try{
            int targetFloor = Integer.parseInt(floorField.getText());
            elevatorSystem.pickup(targetFloor);
            loadElevatorsUI();
        }catch (NumberFormatException e){
            floorAlert();
        }
    }

    private void floorAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Please check your floor input");
        alert.setContentText("Floor has to be an integer");
        alert.showAndWait();
    }
}
