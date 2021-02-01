package network;

import fx.Car;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class ServerThread implements Runnable {
    private Thread thr;
    private NetworkUtil networkUtil;

    private static final String FILE_NAME = "cars.txt";

    public ServerThread(NetworkUtil networkUtil) {
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {

            // FX

            while (true) {

                String s = (String) networkUtil.read();
                System.out.println("Server read : " + s);

                if (s != null) {

                    String response = "";

                    System.out.println(s);
                    StringTokenizer st = new StringTokenizer(s,",");
                    String clientName = st.nextToken();
                    String indicator = st.nextToken();

                    List<Car> carList = new ArrayList();

                    // Initializing file reader
                    BufferedReader br = null;
                    try {
                        String line;
                        int i = 0;
                        br = new BufferedReader(new FileReader(FILE_NAME));
                        while (true) {
                            line = br.readLine();
                            if (line == null) break;
                            String [] inputs = line.split(",");
                            Car temp = new Car(inputs[0], Integer.parseInt(inputs[1]), inputs[2],
                                    inputs[3], inputs[4], inputs[5], inputs[6], Integer.parseInt(inputs[7]), Integer.parseInt(inputs[8]));
                            carList.add(temp);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        assert br != null;
                        br.close();
                    }

                    if (indicator.equalsIgnoreCase("manufacturerMenuInput")) {
                        int manufacturerMenuInput = Integer.parseInt(st.nextToken());

                        if (manufacturerMenuInput == 0) {
                            // login validate
                            HashMap<String, String> credentials = new HashMap<>();
                            credentials.put("admin", "1234");
                            credentials.put("ananto", "pass");
                            credentials.put("boss", "done");
                            credentials.put("biplob", "biplob");

                            String usernameInput = st.nextToken();
                            String passwordInput = st.nextToken();

                            if (passwordInput.equals(credentials.get(usernameInput))) {
                                response = "LOGIN APPROVED";
                                System.out.println("LOGIN APPROVED");
                            } else {
                                response = "LOGIN FAILED";
                                System.out.println("LOGIN FAILED");
                            }
                        } else if (manufacturerMenuInput == 1) {
                            // View all cars
                            for (Car car : carList) {
                                response = response + car + ",";
                                System.out.println(car);
                            }

                        } else if (manufacturerMenuInput == 2) {
                            // Add a car
                            String [] inputs = new String[9];
                            for (int i=0; i<9; i++) {
                                try {
                                    inputs[i] = st.nextToken();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    if (i==1 || i==7 || i==8) {
                                        inputs[i] = "0";
                                    } else {
                                        inputs[i] = "";
                                    }
                                }
                            }

                            // Search for cars with the same reg number
                            boolean found = false;
                            for (Car car : carList) {
                                if (car.getRegNumber().equalsIgnoreCase(inputs[0])) {
                                    found = true;
                                    break;
                                }
                            }

                            if(found) {
                                response = "There is already a car with this Registration Number";
                                System.out.println("There is already a car with this Registration Number");
                            } else {
                                Car temp = new Car(inputs[0], Integer.parseInt(inputs[1]), inputs[2],
                                        inputs[3], inputs[4], inputs[5], inputs[6], Integer.parseInt(inputs[7]), Integer.parseInt(inputs[8]));
                                carList.add(temp);
                                response = "Car added!";
                                System.out.println("Car added!");
                            }

                        } else if (manufacturerMenuInput == 3) {
                            // Edit a car
                            String [] inputs = new String[9];
                            inputs[0] = st.nextToken();

                            boolean found = false;
                            for (Car car : carList) {
                                if (car.getRegNumber().equalsIgnoreCase(inputs[0])) {

                                    inputs[1] = String.valueOf(car.getYearMade());
                                    inputs[2] = car.getColor1();
                                    inputs[3] = car.getColor2();
                                    inputs[4] = car.getColor3();
                                    inputs[5] = car.getCarMake();
                                    inputs[6] = car.getCarModel();
                                    inputs[7] = String.valueOf(car.getPrice());
                                    inputs[8] = String.valueOf(car.getQuantity());

                                    for (int i=1; i<9; i++) {
                                        String t = st.nextToken();
                                        if (!t.equals("UNCHANGED")) {
                                            inputs[i] = t;
                                        }
                                    }

                                    Car temp = new Car(inputs[0], Integer.parseInt(inputs[1]), inputs[2],
                                            inputs[3], inputs[4], inputs[5], inputs[6], Integer.parseInt(inputs[7]), Integer.parseInt(inputs[8]));
                                    carList.remove(car);
                                    carList.add(temp);

                                    response = "Car edited!";
                                    System.out.println("Car edited!");
                                    found = true;
                                    break;
                                }
                            }

                            if (!found) {
                                response = "Car not found with Registration Number" + inputs[0];
                            }


                        } else if (manufacturerMenuInput == 4) {
                            // Delete a car
                            String input = st.nextToken();
                            boolean found = false;
                            for (int i=0; i< carList.size(); i++) {
                                if (carList.get(i).getRegNumber().equalsIgnoreCase(input)) {
                                    carList.remove(carList.get(i));
                                    response = "Removed car!";
                                    System.out.println("Removed car!");
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                response = "No such car found";
                                System.out.println("No such car found");
                            }
                        }

                    } else if (indicator.equalsIgnoreCase("viewerMenuInput")) {
                        int viewerMenuInput = Integer.parseInt(st.nextToken());

                        int serial = 1;

                        if (viewerMenuInput == 1) {
                            // View all cars
                            for (Car car : carList) {
                                response = response + car + ",";
                                System.out.println(car);
                            }

                        } else if (viewerMenuInput == 2) {
                            // Search by registration number
                            String searchByRegNumberInput = st.nextToken();
                            boolean found = false;
                            for (Car car : carList) {
                                if (car.getRegNumber().equalsIgnoreCase(searchByRegNumberInput)) {
                                    response = response + car + ",";
                                    System.out.println(car);
                                    found = true;
                                }
                            }
                            if(!found) {
                                response = "No such car with this Registration Number";
                                System.out.println("No such car with this Registration Number");
                            }

                        } else if (viewerMenuInput == 3) {
                            // Search car by make and model
                            String carMakeInput = st.nextToken();
                            String carModelInput = st.nextToken();
                            boolean found = false;
                            for (Car car: carList) {
                                if(car.getCarMake().equalsIgnoreCase(carMakeInput)) {
                                    if(carModelInput.equalsIgnoreCase("any")) {
                                        response = response + car + ",";
                                        System.out.println(car);
                                        found = true;
                                    } else if(car.getCarModel().equalsIgnoreCase(carModelInput)) {
                                        response = response + car + ",";
                                        System.out.println(car);
                                        found = true;
                                    }
                                }
                            }
                            if(!found) {
                                response = "No such car with this Car Make and Car Model";
                                System.out.println("No such car with this Car Make and Car Model");
                            }

                        } else if (viewerMenuInput == 4) {
                            // Buy a car
                            String input = st.nextToken();
                            boolean found = false;
                            for (Car car : carList) {
                                if (car.getRegNumber().equalsIgnoreCase(input)) {
                                    if (car.getQuantity() <= 0) {
                                        response = "No such car found";
                                        System.out.println("No such car found");
                                        break;
                                    }
                                    Car temp = new Car(car.getRegNumber(), car.getYearMade(), car.getColor1(), car.getColor2(), car.getColor3(),
                                            car.getCarMake(), car.getCarModel(), car.getPrice(), car.getQuantity() - 1);
                                    carList.remove(car);
                                    carList.add(temp);
                                    response = "Car bought!";
                                    System.out.println("Car bought!");
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                response = "No such car found";
                                System.out.println("No such car found");
                            }
                        }
                    }

                    // Initializing file writer
                    BufferedWriter bw = null;
                    try {
                        bw = new BufferedWriter(new FileWriter(FILE_NAME, false));
                        bw.flush();
                        for (Car car : carList) {
                            bw.write(String.valueOf(car));
                            bw.write("\n");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        assert bw != null;
                        bw.close();
                    }

                    networkUtil.write(response);
                    System.out.println("Server write : " + response);
                }
            }

            // CONSOLE

//            while (true) {
//
//                String s = (String) networkUtil.read();
//
//                if (s != null) {
//
//                    System.out.println(s);
//                    StringTokenizer st = new StringTokenizer(s,",");
//                    String clientName = st.nextToken();
//                    String indicator = st.nextToken();
//
//                    List<Car> carList = new ArrayList();
//
//                    // Initializing file reader
//                    BufferedReader br = null;
//                    try {
//                        String line;
//                        int i = 0;
//                        br = new BufferedReader(new FileReader(FILE_NAME));
//                        while (true) {
//                            line = br.readLine();
//                            if (line == null) break;
//                            String [] inputs = line.split(",");
//                            Car temp = new Car(inputs[0], Integer.parseInt(inputs[1]), inputs[2],
//                                    inputs[3], inputs[4], inputs[5], inputs[6], Integer.parseInt(inputs[7]), Integer.parseInt(inputs[8]));
//                            carList.add(temp);
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    } finally {
//                        assert br != null;
//                        br.close();
//                    }
//
//                    if (indicator.equalsIgnoreCase("manufacturerMenuInput")) {
//                        int manufacturerMenuInput = Integer.parseInt(st.nextToken());
//
//                        if (manufacturerMenuInput == 1) {
//                            // View all cars
//                            for (Car car : carList) {
//                                response = response + "(" + (serial++) + ") " + car + "\n";
//                                System.out.println(car);
//                            }
//
//                        } else if (manufacturerMenuInput == 2) {
//                            // Add a car
//                            String [] inputs = new String[9];
//                            for (int i=0; i<9; i++) {
//                                inputs[i] = st.nextToken();
//                            }
//
//                            // Search for cars with the same reg number
//                            boolean found = false;
//                            for (Car car : carList) {
//                                if (car.getRegNumber().equalsIgnoreCase(inputs[0])) {
//                                    found = true;
//                                    break;
//                                }
//                            }
//
//                            if(found) {
//                                response = response + "There is already a car with this Registration Number";
//                                System.out.println("There is already a car with this Registration Number");
//                            } else {
//                                Car temp = new Car(inputs[0], Integer.parseInt(inputs[1]), inputs[2],
//                                        inputs[3], inputs[4], inputs[5], inputs[6], Integer.parseInt(inputs[7]), Integer.parseInt(inputs[8]));
//                                carList.add(temp);
//                                response = "Car added!";
//                                System.out.println("Car added!");
//                            }
//
//                        } else if (manufacturerMenuInput == 3) {
//                            // Edit a car
//                            String [] inputs = new String[9];
//                            inputs[0] = st.nextToken();
//
//                            boolean found = false;
//                            for (Car car : carList) {
//                                if (car.getRegNumber().equalsIgnoreCase(inputs[0])) {
//
//                                    inputs[1] = String.valueOf(car.getYearMade());
//                                    inputs[2] = car.getColor1();
//                                    inputs[3] = car.getColor2();
//                                    inputs[4] = car.getColor3();
//                                    inputs[5] = car.getCarMake();
//                                    inputs[6] = car.getCarModel();
//                                    inputs[7] = String.valueOf(car.getPrice());
//                                    inputs[8] = String.valueOf(car.getQuantity());
//
//                                    for (int i=1; i<9; i++) {
//                                        String t = st.nextToken();
//                                        if (!t.equals("UNCHANGED")) {
//                                            inputs[i] = t;
//                                        }
//                                    }
//
//                                    Car temp = new Car(inputs[0], Integer.parseInt(inputs[1]), inputs[2],
//                                            inputs[3], inputs[4], inputs[5], inputs[6], Integer.parseInt(inputs[7]), Integer.parseInt(inputs[8]));
//                                    carList.remove(car);
//                                    carList.add(temp);
//
//                                    response = "Car edited!";
//                                    System.out.println("Car edited!");
//                                    found = true;
//                                    break;
//                                }
//                            }
//
//                            if (!found) {
//                                response = "Car not found with Registration Number" + inputs[0];
//                            }
//
//
//                        } else if (manufacturerMenuInput == 4) {
//                            // Delete a car
//                            String input = st.nextToken();
//                            boolean found = false;
//                            for (int i=0; i< carList.size(); i++) {
//                                if (carList.get(i).getRegNumber().equalsIgnoreCase(input)) {
//                                    carList.remove(carList.get(i));
//                                    response = "Removed car!";
//                                    System.out.println("Removed car!");
//                                    found = true;
//                                    break;
//                                }
//                            }
//                            if (!found) {
//                                response = "No such car found";
//                                System.out.println("No such car found");
//                            }
//                        }
//
//                    } else if (indicator.equalsIgnoreCase("viewerMenuInput")) {
//                        int viewerMenuInput = Integer.parseInt(st.nextToken());
//
//                        response = "Here is your result :\n";
//                        int serial = 1;
//
//                        if (viewerMenuInput == 1) {
//                            // View all cars
//                            for (Car car : carList) {
//                                response = response + "(" + (serial++) + ") " + car + "\n";
//                                System.out.println(car);
//                            }
//
//                        } else if (viewerMenuInput == 2) {
//                            // Search by registration number
//                            String searchByRegNumberInput = st.nextToken();
//                            boolean found = false;
//                            for (Car car : carList) {
//                                if (car.getRegNumber().equalsIgnoreCase(searchByRegNumberInput)) {
//                                    response = response + "(" + (serial++) + ") " + car + "\n";
//                                    System.out.println(car);
//                                    found = true;
//                                }
//                            }
//                            if(!found) {
//                                response = response + "No such car with this Registration Number";
//                                System.out.println("No such car with this Registration Number");
//                            }
//
//                        } else if (viewerMenuInput == 3) {
//                            // Search car by make and model
//                            String carMakeInput = st.nextToken();
//                            String carModelInput = st.nextToken();
//                            boolean found = false;
//                            for (Car car: carList) {
//                                if(car.getCarMake().equalsIgnoreCase(carMakeInput)) {
//                                    if(carModelInput.equalsIgnoreCase("any")) {
//                                        response = response + "(" + (serial++) + ") " + car + "\n";
//                                        System.out.println(car);
//                                        found = true;
//                                    } else if(car.getCarModel().equalsIgnoreCase(carModelInput)) {
//                                        response = response + "(" + (serial++) + ") " + car + "\n";
//                                        System.out.println(car);
//                                        found = true;
//                                    }
//                                }
//                            }
//                            if(!found) {
//                                response = response + "No such car with this Car Make and Car Model";
//                                System.out.println("No such car with this Car Make and Car Model");
//                            }
//
//                        } else if (viewerMenuInput == 4) {
//                            // Buy a car
//                            String input = st.nextToken();
//                            boolean found = false;
//                            for (Car car : carList) {
//                                if (car.getRegNumber().equalsIgnoreCase(input)) {
//                                    Car temp = new Car(car.getRegNumber(), car.getYearMade(), car.getColor1(), car.getColor2(), car.getColor3(),
//                                            car.getCarMake(), car.getCarModel(), car.getPrice(), car.getQuantity() - 1);
//                                    carList.remove(car);
//                                    carList.add(temp);
//                                    response = "Car bought!";
//                                    System.out.println("Car bought!");
//                                    found = true;
//                                    break;
//                                }
//                            }
//                            if (!found) {
//                                response = "No such car found";
//                                System.out.println("No such car found");
//                            }
//                        }
//                    }
//
//                    // Initializing file writer
//                    BufferedWriter bw = null;
//                    try {
//                        bw = new BufferedWriter(new FileWriter(FILE_NAME, false));
//                        bw.flush();
//                        for (Car car : carList) {
//                            bw.write(String.valueOf(car));
//                            bw.write("\n");
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    } finally {
//                        assert bw != null;
//                        bw.close();
//                    }
//
//                    networkUtil.write(response);
//
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            networkUtil.closeConnection();
        }
    }
}
