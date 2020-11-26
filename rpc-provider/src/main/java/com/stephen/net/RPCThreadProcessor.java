package com.stephen.net;

import com.stephen.dispatch.ServiceDispatch;
import lombok.val;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author stephen
 * @date 2020/11/25 2:29 下午
 */
public class RPCThreadProcessor implements Runnable {

    private Socket socket;

    public RPCThreadProcessor(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            // 从网络中获取的请求对象
            Object reqObj = ois.readObject();

            Object respObj = ServiceDispatch.dispatch(reqObj);

            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(respObj);
            oos.flush();
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
    }
}
