package fx;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class Car {
    private String regNumber;
    private int yearMade;
    private String color1;
    private String color2;
    private String color3;
    private String carMake;
    private String carModel;
    private int price;
    private int quantity;

//    private final Button button;


    public Car(String regNumber, int yearMade, String color1, String color2, String color3, String carMake, String carModel, int price, int quantity) {
        this.regNumber = regNumber;
        this.yearMade = yearMade;
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.carMake = carMake;
        this.carModel = carModel;
        this.price = price;
        this.quantity = quantity;

//        this.button = new Button("Click Me");
//        button.setOnAction( e -> {
//                    System.out.println(getRegNumber() + "," + getCarMake() + "," + getCarModel());
//                    Alert a = new Alert(Alert.AlertType.INFORMATION);
//                    a.setContentText(getRegNumber() + "," + getCarMake() + "," + getCarModel());
//                    a.showAndWait();
//                }
//        );
    }

    @Override
    public String toString() {
        return regNumber + ',' +
                yearMade + ',' +
                color1 + ',' +
                color2 + ',' +
                color3 + ',' +
                carMake + ',' +
                carModel + ',' +
                price + ',' +
                quantity;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public int getYearMade() {
        return yearMade;
    }

    public void setYearMade(int yearMade) {
        this.yearMade = yearMade;
    }

    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public String getColor3() {
        return color3;
    }

    public void setColor3(String color3) {
        this.color3 = color3;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
