package com.bigdata.utils.rpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.Server;

import java.io.IOException;

public class RpcServer {
    public RpcServer() {
    }

    public static void main(String[] args) throws IOException {
        Server server = (new RPC.Builder(new Configuration())).setBindAddress(args[0]).setPort(Integer.valueOf(args[1])).setInstance(new GetIPServiceImpl()).setProtocol(GetIPServiceInterface.class).build();
        server.start();
    }
}
