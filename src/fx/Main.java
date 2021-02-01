package fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import network.Client;
import network.ClientThread;
import network.NetworkUtil;

import java.io.IOException;

public class Main extends Application {

    Stage stage;
    Car car;

//    public void setClientThread(ClientThread clientThread) {
//        this.clientThread = clientThread;
//    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;

        showLoginPage();
    }

//    public void showLoginPage(ClientThread clientThread) throws Exception {
    public void showLoginPage() throws Exception {

    // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LoginController controller = loader.getController();
        controller.setMain(this);
//        controller.setClientThread(clientThread);

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 400, 250));
        stage.show();
    }

//    public void showViewerMenu(ClientThread clientThread) throws Exception {
    public void showViewerMenu() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("viewermenu.fxml"));
        Parent root = loader.load();

        // Loading the controller
        ViewerMenuController controller = loader.getController();
        controller.setMain(this);
//        controller.setClientThread(clientThread);

        // Set the primary stage
        stage.setTitle("Viewer Menu");
        stage.setScene(new Scene(root, 917, 623));
        stage.show();
    }

//    public void showManufacturerMenu(ClientThread clientThread) throws Exception {
    public void showManufacturerMenu() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("manufacturermenu.fxml"));
        Parent root = loader.load();

        // Loading the controller
        ManufacturerMenuController controller = loader.getController();
        controller.setMain(this);
//        controller.setClientThread(clientThread);

        // Set the primary stage
        stage.setTitle("Manufacturer Menu");
        stage.setScene(new Scene(root, 917, 623));
        stage.show();
    }

    public void showEditCar(Car car) throws IOException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("editcar.fxml"));
        Parent root = loader.load();

        // Loading the controller
        EditCarController controller = loader.getController();
        controller.setMain(this);
        controller.load(car);

        // Set the primary stage
        stage.setTitle("Edit Car");
        stage.setScene(new Scene(root, 455, 505));
        stage.show();
    }

    public void showAddCar () throws IOException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addcar.fxml"));
        Parent root = loader.load();

        // Loading the controller
        AddCarController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Add Car");
        stage.setScene(new Scene(root, 455, 505));
        stage.show();
    }

    public void showSearch () throws IOException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("search.fxml"));
        Parent root = loader.load();

        // Loading the controller
        SearchController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Search Car");
        stage.setScene(new Scene(root, 455, 505));
        stage.show();
    }

    public static void main(String[] args) {
        // This will launch the JavaFX application
        launch(args);
    }
}
