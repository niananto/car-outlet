package extras;

import network.ClientThread;
import network.NetworkUtil;

public class ReadThreadClient implements Runnable {
    private Thread thr;
    private NetworkUtil networkUtil;

    ClientThread clientThread;

    public ReadThreadClient(NetworkUtil networkUtil, ClientThread clientThread) {
        this.networkUtil = networkUtil;
        this.clientThread = clientThread;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                String s = (String) networkUtil.read();
                if (s != null) {
                    System.out.println(s);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            networkUtil.closeConnection();
        }
    }
}



