package com.roller.doc.api.request;

import lombok.Data;

@Data
public class HospitalFilterReq {
    private double e;
    private double w;
    private double s;
    private double n;
    private int p1;
    private int p2;
    private int p3;
    private int p4;
    private int p5;
    private int sat;
    private int sun;
    private int holiday;
    private int night;
}
