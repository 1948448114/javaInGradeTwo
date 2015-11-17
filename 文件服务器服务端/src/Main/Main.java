package Main;

import Message.UserMessage;

import Serverthread.UDPserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2014/12/18.
 */
public class Main {
    public static UserMessage userMessage=new UserMessage();

    public  static void main(String[] args) {
        final String address = "127.0.0.1";
        final int port = 8888;
        new UDPserver(address, port);
    }

}
