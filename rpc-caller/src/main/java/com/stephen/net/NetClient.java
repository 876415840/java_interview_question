package com.stephen.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author stephen
 * @date 2020/11/25 3:25 下午
 */
public class NetClient {

    public static Object callRemoteService(String host, int port, Object reqObject) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;

        Object respObject = null;

        try {
            Socket socket = new Socket(host, port);
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(reqObject);
            oos.flush();

            ois = new ObjectInputStream(socket.getInputStream());
            respObject = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return respObject;
    }
}
