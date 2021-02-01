package fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import network.ClientThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ViewerMenuController {

    @FXML
    private TableView<Car> viewerMenuTable;

    @FXML
    private TableColumn<Car, String> viewerMenuTableRegNumber;

    @FXML
    private TableColumn<Car, Integer> viewerMenuTableYearMade;

    @FXML
    private TableColumn<Car, String> viewerMenuTableColor1;

    @FXML
    private TableColumn<Car, String> viewerMenuTableColor2;

    @FXML
    private TableColumn<Car, String> viewerMenuTableColor3;

    @FXML
    private TableColumn<Car, String> viewerMenuTableCarMake;

    @FXML
    private TableColumn<Car, String> viewerMenuTableCarModel;

    @FXML
    private TableColumn<Car, Integer> viewerMenuTablePrice;

    @FXML
    private TableColumn<Car, Integer> viewerMenuTableQuantity;

    @FXML
    private Label viewerMenuTitle;

    @FXML
    private TextField regNumberField;

    @FXML
    private TextField carMakeField;

    @FXML
    private TextField carModelField;

    @FXML
    private Button searchWithRegButton;

    @FXML
    private Button searchWithMakeModelButton1;

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    private static ObservableList<Car> data;

    private void loadData(String dataString) {
        String [] inputs = dataString.split(",");
        List<Car> carList = new ArrayList<>();
        for (int i=0; i<inputs.length/9; i++) {
            Car temp = new Car(inputs[i*9+0], Integer.parseInt(inputs[i*9+1]), inputs[i*9+2],
                    inputs[i*9+3], inputs[i*9+4], inputs[i*9+5], inputs[i*9+6], Integer.parseInt(inputs[i*9+7]),
                    Integer.parseInt(inputs[i*9+8]));
            carList.add(temp);
        }
        data = FXCollections.observableArrayList(carList);
        System.out.println(data);
    }

    public void viewAllCars() {

        String toBeWritten = ClientThread.getClientThread().getUserName() + "," + "viewerMenuInput" + "," + 1;
        System.out.println(toBeWritten);
        String response = ClientThread.getClientThread().query(toBeWritten);
        System.out.println(response.length());

//        data = FXCollections.observableArrayList(
//                new Car("abc123", 2010, "Red", "Black", "Yellow", "Toyota", "Corolla", 10000, 3),
//                new Car("efg456", 2010, "Red", "Black", "Yellow", "Toyota", "Corolla", 10000, 3),
//                new Car("hij789", 2010, "Red", "Black", "Yellow", "Toyota", "Corolla", 10000, 3)
//        );

        loadData(response);

        viewerMenuTable.setEditable(true);
        viewerMenuTable.setItems(data);
    }

    public void searchPressed(ActionEvent event) {
        try {
            main.showSearch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buyThisCar(ActionEvent actionEvent) {
        Car selectedCar = viewerMenuTable.getSelectionModel().getSelectedItem();

        if (selectedCar.getQuantity() <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Not enough quantity");
            alert.showAndWait();
            return;
        }

        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Buy This Car ?");
        alert.setHeaderText("Buy Car: " + selectedCar);
        alert.setContentText("Are you sure? Press Ok to confirm, Cancel to Back Out.");
        Optional<ButtonType> result= alert.showAndWait();
        if(result.isPresent() && result.get()==ButtonType.OK){
            String toBeWritten = ClientThread.getClientThread().getUserName() + "," + "viewerMenuInput" + "," + 4 +
                    "," + selectedCar.getRegNumber();
            String response = ClientThread.getClientThread().query(toBeWritten);
            if (response.equals("Car bought!")) {
                viewAllCars();
                System.out.println("Car bought!");
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("No such car found");
                System.out.println("No such car found");
                a.showAndWait();
                return;
            }
        }
    }

    @FXML
    void searchWithRegPressed(ActionEvent event) {
        if (regNumberField.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter a registration number");
            alert.showAndWait();
            return;
        }

        String toBeWritten = ClientThread.getClientThread().getUserName() + "," + "viewerMenuInput" + "," + 2;
        toBeWritten = toBeWritten + "," + regNumberField.getText();
        System.out.println(toBeWritten);
        String response = ClientThread.getClientThread().query(toBeWritten);
        System.out.println(response.length());

        if (response.equals("No such car with this Registration Number")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No such car with this Registration Number");
            alert.showAndWait();
            return;
        }

        loadData(response);

        viewerMenuTable.setEditable(true);
        viewerMenuTable.setItems(data);

    }

    @FXML
    void searchWithMakeModelPressed(ActionEvent event) {
         if (carMakeField.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter a car make");
            alert.showAndWait();
            return;
         }

        String toBeWritten = ClientThread.getClientThread().getUserName() + "," + "viewerMenuInput" + "," + 3;
        toBeWritten = toBeWritten + "," + carMakeField.getText();
        if (carModelField.getText().equalsIgnoreCase("any") || carModelField.getText().isBlank()) {
            toBeWritten = toBeWritten + "," + "any";
        } else {
            toBeWritten = toBeWritten + "," + carModelField.getText();
        }
        System.out.println(toBeWritten);
        String response = ClientThread.getClientThread().query(toBeWritten);
        System.out.println(response.length());

        if (response.equals("No such car with this Car Make and Car Model")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No such car with this Car Make and Car Model");
            alert.showAndWait();
            return;
        }

        loadData(response);

        viewerMenuTable.setEditable(true);
        viewerMenuTable.setItems(data);

    }

    public void backPressed(ActionEvent actionEvent) {
        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

        viewerMenuTableRegNumber.setCellValueFactory(new PropertyValueFactory<>("regNumber"));
        viewerMenuTableYearMade.setCellValueFactory(new PropertyValueFactory<>("yearMade"));
        viewerMenuTableColor1.setCellValueFactory(new PropertyValueFactory<>("color1"));
        viewerMenuTableColor2.setCellValueFactory(new PropertyValueFactory<>("color2"));
        viewerMenuTableColor3.setCellValueFactory(new PropertyValueFactory<>("color3"));
        viewerMenuTableCarMake.setCellValueFactory(new PropertyValueFactory<>("carMake"));
        viewerMenuTableCarModel.setCellValueFactory(new PropertyValueFactory<>("carModel"));
        viewerMenuTablePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        viewerMenuTableQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        viewAllCars();
    }
}
