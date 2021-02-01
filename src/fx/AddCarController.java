package fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import network.ClientThread;

public class AddCarController {

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

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
    public Button backButton;

    @FXML
    private Button confirmButton;

    @FXML
    void initialize() {

    }

    public void confirmPressed(ActionEvent actionEvent) {
        try {
            Integer.parseInt(yearMadeField.getText());
            Integer.parseInt(priceField.getText());
            Integer.parseInt(quantityField.getText());
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Year Made, Price and Quantity has to be numeric");
            alert.showAndWait();
            return;
        }

        String toBeWritten = ClientThread.getClientThread().getUserName() + "," + "manufacturerMenuInput" + "," + 2;
        toBeWritten = toBeWritten + "," + regNumberField.getText();
        toBeWritten = toBeWritten + "," + yearMadeField.getText();
        toBeWritten = toBeWritten + "," + color1Field.getText();
        toBeWritten = toBeWritten + "," + color2Field.getText();
        toBeWritten = toBeWritten + "," + color3Field.getText();
        toBeWritten = toBeWritten + "," + carMakeField.getText();
        toBeWritten = toBeWritten + "," + carModelField.getText();
        toBeWritten = toBeWritten + "," + priceField.getText();
        toBeWritten = toBeWritten + "," + quantityField.getText();
        System.out.println(toBeWritten);
        String response = ClientThread.getClientThread().query(toBeWritten);
        System.out.println(response.length());

        try {
            main.showManufacturerMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backPressed(ActionEvent actionEvent) {
        try {
            main.showManufacturerMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
