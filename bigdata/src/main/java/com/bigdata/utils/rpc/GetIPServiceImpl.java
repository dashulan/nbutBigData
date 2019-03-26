package com.bigdata.utils.rpc;

import com.bigdata.utils.FieldPattern;
import com.bigdata.utils.IPLocate;
import org.apache.hadoop.ipc.ProtocolSignature;

import java.io.IOException;

public class GetIPServiceImpl {
    private final String fileName = "F:\\ip_locate_data\\IP_trial_2019M03_single_WGS84.dat";
    private IPLocate ipLocate = IPLocate.loadDat("F:\\ip_locate_data\\IP_trial_2019M03_single_WGS84.dat");
    private FieldPattern pattern = new FieldPattern();

    public GetIPServiceImpl() {
    }

    public String getIP(String ip) {
        String result = this.ipLocate.locate_ip(ip);
        return result;
    }

    public long getProtocolVersion(String s, long l) throws IOException {
        return 1L;
    }

    public ProtocolSignature getProtocolSignature(String s, long l, int i) throws IOException {
        return new ProtocolSignature(1L, (int[])null);
    }
}
