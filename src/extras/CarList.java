package extras;

import fx.Car;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CarList {
    private static final String INPUT_FILE_NAME = "cars.txt";
    private static final String OUTPUT_FILE_NAME = "cars.txt";

    private static void printMainMenu() {
        System.out.println("Main Menu\n" +
                "(1) Search Cars\n" +
                "(2) Add Car\n" +
                "(3) Delete Car\n" +
                "(4) Exit System");
    }

    private static void printSearchingOptions() {
        System.out.println("Car Searching Options:\n" +
                "(1) By Registration Number\n" +
                "(2) By Car Make and Car Model\n" +
                "(3) Back to Main Menu");
    }

    public static void main(String[] args) {
        List<Car> carList = new ArrayList();

        try {
            String line;
            int i = 0;
            BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
            while (true) {
                line = br.readLine();
                if (line == null) break;
//                    System.out.println(line);
                String [] inputs = line.split(",");
                Car temp = new Car(inputs[0], Integer.parseInt(inputs[1]), inputs[2],
                        inputs[3], inputs[4], inputs[5], inputs[6], Integer.parseInt(inputs[7]), Integer.parseInt(inputs[8]));
                carList.add(temp);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);

        while(true) {
            printMainMenu();
            try {
                int mainMenuInput = sc.nextInt();
                if (mainMenuInput == 1) {
                    // Search Cars

                    while (true) {
                        printSearchingOptions();
                        try {
                            int searchingMenuInput = sc.nextInt();
                            if (searchingMenuInput == 1) {
                                // By Registration Number
                                System.out.print("Enter a registration number to search : ");
                                sc.nextLine();
                                String searchByRegNumberInput = sc.nextLine();
                                boolean found = false;
                                for (Car car : carList) {
                                    if (car.getRegNumber().equalsIgnoreCase(searchByRegNumberInput)) {
                                        System.out.println(car);
                                        found = true;
                                    }
                                }
                                if(found == false) {
                                    System.out.println("No such car with this Registration Number");
                                }
                            } else if (searchingMenuInput == 2) {
                                // By Car Make and Car Model
                                System.out.print("Enter a car make : ");
                                sc.nextLine();
                                String carMakeInput = sc.nextLine();
                                System.out.print("Enter a car model : ");
                                String carModelInput = sc.nextLine();
                                boolean found = false;
                                for (Car car: carList) {
                                    if(car.getCarMake().equalsIgnoreCase(carMakeInput)) {
                                        if(carModelInput.equalsIgnoreCase("any")) {
                                            System.out.println(car);
                                            found = true;
                                        } else if(car.getCarModel().equalsIgnoreCase(carModelInput)) {
                                            System.out.println(car);
                                            found = true;
                                        }
                                    }
                                }
                                if(!found) {
                                    System.out.println("No such car with this Car Make and Car Model");
                                }
                            } else if (searchingMenuInput == 3) {
                                // Back to Main Menu
                                break;
                            } else {
                                System.out.println("Enter a valid input within 1 to 3");
                            }
                        } catch (InputMismatchException e) {
                            e.printStackTrace();
                        }
                    }

                } else if (mainMenuInput == 2) {
                    // Add Car
                    String [] inputs = new String[9];
                    System.out.println("Enter the Registration Number, Making Year, Color 1, Color 2, Color 3, Car Make, Car Model, Price & Quantity");
                    sc.nextLine();
                    for (int i=0; i<9; i++) {
                        inputs[i] = sc.nextLine();
                    }
                    // Search for cars with the same reg number
                    boolean found = false;
                    for (Car car : carList) {
                        if (car.getRegNumber().equalsIgnoreCase(inputs[0])) {
                            found = true;
                        }
                    }
                    if(found) {
                        System.out.println("There is already a car with this Registration Number");
                    } else {
                        Car temp = new Car(inputs[0], Integer.parseInt(inputs[1]), inputs[2],
                                inputs[3], inputs[4], inputs[5], inputs[6], Integer.parseInt(inputs[7]), Integer.parseInt(inputs[8]));
                        carList.add(temp);
                        System.out.println("Car added!");
                    }
                } else if (mainMenuInput == 3) {
                    // Delete Car
                    System.out.print("Enter a registration number to delete : ");
                    sc.nextLine();
                    String deleteByRegNumberInput = sc.nextLine();
                    for (int i=0; i< carList.size(); i++) {
                        if (carList.get(i).getRegNumber().equalsIgnoreCase(deleteByRegNumberInput)) {
                            carList.remove(carList.get(i));
                            System.out.println("Removed car!");
                        }
                    }
                } else if (mainMenuInput == 4) {
                    // Exit System
                    break;
                } else {
                    System.out.println("Enter a valid input within 1 to 4");
                }
            } catch (InputMismatchException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Cars till now");
        for(Car car : carList) {
            System.out.println(car);
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
            for (Car car : carList) {
                bw.write(String.valueOf(car));
                bw.write("\n");
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}