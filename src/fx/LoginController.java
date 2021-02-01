package fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import network.ClientThread;

import java.util.HashMap;


public class LoginController {

    @FXML
    private TextField usernameText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button resetButton;

    @FXML
    private Button loginButton;

    private Main main;

//    private ClientThread clientThread;

//    public void setClientThread(ClientThread clientThread) {
//        this.clientThread = clientThread;
//    }

    @FXML
    void loginAction(ActionEvent event) {

        String usernameInput = usernameText.getText();
        String passwordInput = passwordText.getText();
        if (usernameInput.equals("viewer") && passwordInput.equals("")) {
            // successful login
            // viewer
            try {
                main.showViewerMenu();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            String toBeWritten = ClientThread.getClientThread().getUserName() + "," + "manufacturerMenuInput" + "," + 0;
            toBeWritten = toBeWritten + "," + usernameInput;
            toBeWritten = toBeWritten + "," + passwordInput;
            System.out.println(toBeWritten);
            String response = ClientThread.getClientThread().query(toBeWritten);
            System.out.println(response.length());

            if (response.equals("LOGIN APPROVED")) {
                // successful manufacturer
                try {
                    main.showManufacturerMenu();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // failed login
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Incorrect Credentials");
                alert.setHeaderText("Incorrect Credentials");
                alert.setContentText("The username and password you provided is not correct. " +
                        "If you are a viewer, then put 'viewer' as username with no password");
                alert.showAndWait();
            }
        }

    }

    @FXML
    void resetAction(ActionEvent event) {
        usernameText.setText(null);
        passwordText.setText(null);
    }

    void setMain(Main main) {
        this.main = main;
    }

}
