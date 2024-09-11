package network;

import java.io.Serializable;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Connection implements Serializable {
    private final int PORT = 9777;

    public DatagramSocket connecting() throws SocketException {
        return new DatagramSocket(PORT);
    }
}
