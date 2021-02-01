package network;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
//    public HashMap<String, NetworkUtil> clientMap; // HashMap of client's userName and socket information

    Server() {
//        clientMap = new HashMap<>();
        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
//        String clientName = (String) networkUtil.read();
//        clientMap.put(clientName, networkUtil);
        ServerThread serverThread = new ServerThread(networkUtil);
    }

    public static void main(String args[]) {
        Server server = new Server();
    }
}
