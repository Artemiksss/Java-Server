package network.request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class CommandProcessing implements Serializable {
    public Request processing(byte[] receivedBytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(receivedBytes);
        ObjectInputStream in = new ObjectInputStream(byteInputStream);
        return (Request) in.readObject();
    }
}
