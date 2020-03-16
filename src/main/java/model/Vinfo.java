package model;

public class Vinfo {

    private int vno;
    private int time;
    private String hospital;
    private String dname;
    private double total;

    public Vinfo(int vno, int time, String hospital, String dname, double total) {
        this.vno = vno;
        this.time = time;
        this.hospital = hospital;
        this.dname = dname;
        this.total = total;
    }

    public int getVno() {
        return vno;
    }

    public void setVno(int vno) {
        this.vno = vno;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
