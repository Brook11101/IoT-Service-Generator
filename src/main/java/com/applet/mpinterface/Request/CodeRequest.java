package com.applet.mpinterface.Request;

import lombok.Data;

@Data
public class CodeRequest {
    private String javaCode;
    private String appletName;
    private String genAddress;
    private Integer portNum;
}
