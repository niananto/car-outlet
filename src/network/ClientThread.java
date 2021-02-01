package network;

public class ClientThread extends Thread {
//public class ClientThread {

//    private Thread thr;
//    private NetworkUtil networkUtil;
    private static String userName = "ananto";
//    String response;

    private static ClientThread clientThread;
    private static NetworkUtil networkUtil;

//    private ClientThread(NetworkUtil networkUtil) {
//        this.networkUtil = networkUtil;
//        this.thr = new Thread(this);
//        thr.start();
//    }

    private ClientThread() {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        networkUtil = new NetworkUtil(serverAddress, serverPort);
        start();
    }

    public static ClientThread getClientThread() {
        if (clientThread == null) {
            clientThread = new ClientThread();
        }
        return clientThread;
    }

    public static String getUserName() {
        return userName;
    }

    public String query (String queryString) {
        // Write to the server
        networkUtil.write(queryString);

        // Read from the server
        try {
            while (true) {
                String s = (String) networkUtil.read();
                if (s != null) {
                    return s;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void run() {

//        try {
//            while (true) {
//                System.out.println("loooooooooooop");
//                String s = (String) networkUtil.read();
//                System.out.println("ajsd;fjadfs");
//                if (s != null) {
//                    System.out.println(s);
//                    break;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        try {
//            Scanner input = new Scanner(System.in);
//            while (true) {
//
//                int modeInput = -999;
//                while (true) {
//                    System.out.println("Are you a" +
//                            "\n(1) Manufacturer" +
//                            "\n(2) Viewer");
//
//                    String modeString = input.nextLine();
//                    try {
//                        modeInput = Integer.parseInt(modeString);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    } finally {
//                        if (modeInput != 1 && modeInput != 2) {
//                            System.out.println("Your input should be between 1 and 2");
//                            continue;
//                        } else {
//                            break;
//                        }
//                    }
//                }
//
//
//                if (modeInput == 1) {
//
//                    HashMap<String, String>  credentials = new HashMap<>();
//                    credentials.put("admin", "1234");
//                    while (true) {
//                        System.out.println("Enter username : ");
//                        String usernameInput = input.nextLine();
//                        System.out.println("Enter password : ");
//                        String passwordInput = input.nextLine();
//
//                        if (passwordInput.equals(credentials.get(usernameInput))) {
//                            this.userName = usernameInput;
//                            break;
//                        } else {
//                            System.out.println("Wrong username or password ! Try again");
//                        }
//                    }
//
//                    while (true) {
//
//                        int manufacturerMenuInput = -999;
//                        while (true) {
//                            System.out.println("Manufacturer Menu" +
//                                    "\n(1) View all cars" +
//                                    "\n(2) Add a car" +
//                                    "\n(3) Edit a car" +
//                                    "\n(4) Delete a car" +
//                                    "\n(5) Exit system");
//
//                            String manufacturerMenuString = input.nextLine();
//                            try {
//                                manufacturerMenuInput = Integer.parseInt(manufacturerMenuString);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            } finally {
//                                if (manufacturerMenuInput < 1 || manufacturerMenuInput > 5) {
//                                    System.out.println("Your input should be between 1 and 5");
//                                    continue;
//                                } else {
//                                    break;
//                                }
//                            }
//                        }
//
//                        String toBeWritten = userName + "," + "manufacturerMenuInput" + "," + manufacturerMenuInput;
//                        if (manufacturerMenuInput == 1) {
//                            // View all cars
//                            networkUtil.write(toBeWritten);
//
//                        } else if (manufacturerMenuInput == 2) {
//                            // Add a car
//                            System.out.println("Enter the Registration Number, Making Year, Color 1, Color 2, " +
//                                    "Color 3, Car Make, Car Model, Price & Quantity");
//                            for (int i=0; i<9; i++) {
//                                String t = input.nextLine();
//                                toBeWritten = toBeWritten + "," + t;
//                            }
//                            networkUtil.write(toBeWritten);
//
//                        } else if (manufacturerMenuInput == 3) {
//                            // Edit a car
//                            System.out.println("Enter the Registration Number of the car you want to edit");
//                            String t = input.nextLine();
//                            toBeWritten = toBeWritten + "," + t;
//                            System.out.println("Enter the Making Year, Color 1, Color 2, " +
//                                    "Color 3, Car Make, Car Model, Price & Quantity. Leave blank if you want to keep" +
//                                    "that field unchanged");
//                            for (int i=1; i<9; i++) {
//                                t = input.nextLine();
//                                if (t.isBlank()) {
//                                    toBeWritten = toBeWritten + "," + "UNCHANGED";
//                                    continue;
//                                }
//                                toBeWritten = toBeWritten + "," + t;
//                            }
//                            networkUtil.write(toBeWritten);
//
//                        } else if (manufacturerMenuInput == 4) {
//                            // Delete a car
//                            System.out.print("Enter a registration number to delete : ");
//                            String t = input.nextLine();
//                            toBeWritten = toBeWritten + "," + t;
//                            networkUtil.write(toBeWritten);
//
//                        } else { // manufacturerMenuInput == 5
//                            System.out.println("System terminating ...");
//                            return;
//                        }
//
//                        // Read from the server
//                        try {
//                            while (true) {
//                                String s = (String) networkUtil.read();
//                                if (s != null) {
//                                    response = s;
//                                    System.out.println(s);
//                                    break;
//                                }
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//
//
//                } else { // modeInput == 2
//
//                    String username = "viewer";
//                    String password = "";
//                    while (true) {
//                        System.out.println("Enter username : ");
//                        String usernameInput = input.nextLine();
//                        System.out.println("Enter password : ");
//                        String passwordInput = input.nextLine();
//
//                        if (usernameInput.equals(username) && passwordInput.equals(password)) {
//                            this.userName = usernameInput;
//                            break;
//                        } else {
//                            System.out.println("Wrong username or password ! Try again");
//                        }
//                    }
//
//                    while (true) {
//
//                        int viewerMenuInput = -999;
//                        while (true) {
//                            System.out.println("Viewer Menu" +
//                                    "\n(1) View all cars" +
//                                    "\n(2) Search car by registration number" +
//                                    "\n(3) Search car by make and model" +
//                                    "\n(4) Buy a car" +
//                                    "\n(5) Exit system");
//
//                            String viewerMenuString = input.nextLine();
//                            try {
//                                viewerMenuInput = Integer.parseInt(viewerMenuString);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            } finally {
//                                if (viewerMenuInput < 1 || viewerMenuInput > 5) {
//                                    System.out.println("Your input should be between 1 and 5");
//                                    continue;
//                                } else {
//                                    break;
//                                }
//                            }
//                        }
//
//                        String toBeWritten = userName + "," + "viewerMenuInput" + "," + viewerMenuInput;
//                        if (viewerMenuInput == 1) {
//                            // View all cars
//                            networkUtil.write(toBeWritten);
//
//                        } else if (viewerMenuInput == 2) {
//                            // Search car by registration number
//                            System.out.println("Enter a registration number : ");
//                            String t = input.nextLine();
//                            toBeWritten = toBeWritten + "," + t;
//                            networkUtil.write(toBeWritten);
//
//                        } else if (viewerMenuInput == 3) {
//                            // Search by car make and model
//                            System.out.println("Enter a car make :");
//                            String carMake = input.nextLine();
//                            System.out.println("Enter a car model :");
//                            String carModel = input.nextLine();
//                            toBeWritten = toBeWritten + "," + carMake + "," + carModel;
//                            networkUtil.write(toBeWritten);
//
//                        } else if (viewerMenuInput == 4) {
//                            // Buy a car
//                            System.out.println("Enter the registration number of the car you want to buy");
//                            String t = input.nextLine();
//                            toBeWritten = toBeWritten + "," + t;
//                            networkUtil.write(toBeWritten);
//
//                        } else { // viewerMenuInput == 5
//                            System.out.println("System terminating ...");
//                            networkUtil.closeConnection();
//                            return;
//                        }
//
//                        // Read from the server
//                        try {
//                            while (true) {
//                                String s = (String) networkUtil.read();
//                                if (s != null) {
//                                    response = s;
//                                    System.out.println(s);
//                                    break;
//                                }
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            networkUtil.closeConnection();
//        }
    }
}



