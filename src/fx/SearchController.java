package fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SearchController {
    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private TextField regNumberField;

    @FXML
    private TextField carMakeField;

    @FXML
    private TextField carModelField;

    @FXML
    private Button backButton;

    @FXML
    private Button searchWithRegButton;

    @FXML
    private Button searchWithMakeModelButton1;

    @FXML
    void backPressed(ActionEvent event) {
        try {
            main.showViewerMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void searchWithRegPressed(ActionEvent event) {

    }

    @FXML
    void searchWithMakeModelPressed(ActionEvent event) {

    }

}
