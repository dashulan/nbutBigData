package com.bigdata.utils.rpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class RPCClient {

    static InetSocketAddress inetSocketAddress;

    public RPCClient() {
    }

    public static void main(String[] args) throws IOException {
        inetSocketAddress = new InetSocketAddress(args[0], Integer.valueOf(args[1]));
        GetIPServiceInterface proxy =  RPC.getProxy(GetIPServiceInterface.class, 1L, inetSocketAddress, new Configuration());
        Scanner in = new Scanner(System.in);
        System.out.println("start ip locate server now!");

        while(true) {
            String ip = in.nextLine();
            String result = proxy.getIP(ip);
            System.out.println(result);
            System.out.println("---------------------");
        }
    }
}
