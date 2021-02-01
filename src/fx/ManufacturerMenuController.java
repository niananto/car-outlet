package fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import network.ClientThread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManufacturerMenuController {
    @FXML
    private AnchorPane backButton;

    @FXML
    private TextField regNumberField;

    @FXML
    private TextField yearMadeField;

    @FXML
    private TextField color1Field;

    @FXML
    private TextField color2Field;

    @FXML
    private TextField color3Field;

    @FXML
    private TextField carMakeField;

    @FXML
    private TextField carModelField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField quantityField;

    @FXML
    private TableView<Car> manufacturerMenuTable;

    @FXML
    private TableColumn<Car, String> manufacturerMenuTableRegNumber;

    @FXML
    private TableColumn<Car, Integer> manufacturerMenuTableYearMade;

    @FXML
    private TableColumn<Car, String> manufacturerMenuTableColor1;

    @FXML
    private TableColumn<Car, String> manufacturerMenuTableColor2;

    @FXML
    private TableColumn<Car, String> manufacturerMenuTableColor3;

    @FXML
    private TableColumn<Car, String> manufacturerMenuTableCarMake;

    @FXML
    private TableColumn<Car, String> manufacturerMenuTableCarModel;

    @FXML
    private TableColumn<Car, Integer> manufacturerMenuTablePrice;

    @FXML
    private TableColumn<Car, Integer> manufacturerMenuTableQuantity;

    @FXML
    private Label viewerMenuTitle;

    @FXML
    private Button manufacturerMenuViewAllCarsButton;

    @FXML
    private Button manufacturerMenuAddCarButton;

    @FXML
    private Button manufacturerMenuEditCarButton;

    @FXML
    private Button viewerMenuBackButton;

    @FXML
    private Button manufacturerMenuDeleteButton;

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

        String toBeWritten = ClientThread.getClientThread().getUserName() + "," + "manufacturerMenuInput" + "," + 1;
        System.out.println(toBeWritten);
        String response = ClientThread.getClientThread().query(toBeWritten);
        System.out.println(response.length());

        loadData(response);

        manufacturerMenuTable.setEditable(true);
        manufacturerMenuTable.setItems(data);
    }


    public void addCar(ActionEvent actionEvent) {
        try {
            main.showAddCar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editThisCar(ActionEvent actionEvent) throws IOException {
        Car selectedCar = manufacturerMenuTable.getSelectionModel().getSelectedItem();
        try {
            main.showEditCar(selectedCar);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deletePressed(ActionEvent actionEvent) {
        Car selectedCar = manufacturerMenuTable.getSelectionModel().getSelectedItem();
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete This Car ?");
        alert.setHeaderText("Delete Car: " + selectedCar);
        alert.setContentText("Are you sure? Press Ok to confirm, Cancel to Back Out.");
        Optional<ButtonType> result= alert.showAndWait();
        if(result.isPresent() && result.get()==ButtonType.OK){
            String toBeWritten = ClientThread.getClientThread().getUserName() + "," + "manufacturerMenuInput" + "," + 4 +
                    "," + selectedCar.getRegNumber();
            String response = ClientThread.getClientThread().query(toBeWritten);
            if (response.equals("Removed car!")) {
                viewAllCars();
                System.out.println("Removed car!");
            } else {
                System.out.println("No such car found");
            }
        }
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

        manufacturerMenuTableRegNumber.setCellValueFactory(new PropertyValueFactory<>("regNumber"));
        manufacturerMenuTableYearMade.setCellValueFactory(new PropertyValueFactory<>("yearMade"));
        manufacturerMenuTableColor1.setCellValueFactory(new PropertyValueFactory<>("color1"));
        manufacturerMenuTableColor2.setCellValueFactory(new PropertyValueFactory<>("color2"));
        manufacturerMenuTableColor3.setCellValueFactory(new PropertyValueFactory<>("color3"));
        manufacturerMenuTableCarMake.setCellValueFactory(new PropertyValueFactory<>("carMake"));
        manufacturerMenuTableCarModel.setCellValueFactory(new PropertyValueFactory<>("carModel"));
        manufacturerMenuTablePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        manufacturerMenuTableQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        viewAllCars();
    }

}
