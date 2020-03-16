package model;

public class Minfo {

    private int vno;
    private int mno;
    private String mname;
    private double mprice;
    private int mquantity;
    private double mtotalprice;

    public Minfo(int vno, int mno, String mname, double mprice, int mquantity, double mtotalprice) {
        this.vno = vno;
        this.mno = mno;
        this.mname = mname;
        this.mprice = mprice;
        this.mquantity = mquantity;
        this.mtotalprice = mtotalprice;
    }

    public int getVno() {
        return vno;
    }

    public void setVno(int vno) {
        this.vno = vno;
    }

    public int getMno() {
        return mno;
    }

    public void setMno(int mno) {
        this.mno = mno;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public double getMprice() {
        return mprice;
    }

    public void setMprice(double mprice) {
        this.mprice = mprice;
    }

    public int getMquantity() {
        return mquantity;
    }

    public void setMquantity(int mquantity) {
        this.mquantity = mquantity;
    }

    public double getMtotalprice() {
        return mtotalprice;
    }

    public void setMtotalprice(double mtotalprice) {
        this.mtotalprice = mtotalprice;
    }
}
