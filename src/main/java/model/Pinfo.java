package model;

public class Pinfo {

    private int vno;
    private int pno;
    private String pname;
    private double pprice;
    private int pquantity;
    private double ptotalprice;

    public Pinfo(int vno, int pno, String pname, double pprice, int pquantity, double ptotalprice) {
        this.vno = vno;
        this.pno = pno;
        this.pname = pname;
        this.pprice = pprice;
        this.pquantity = pquantity;
        this.ptotalprice = ptotalprice;
    }

    public int getVno() {
        return vno;
    }

    public void setVno(int vno) {
        this.vno = vno;
    }

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public double getPprice() {
        return pprice;
    }

    public void setPprice(double pprice) {
        this.pprice = pprice;
    }

    public int getPquantity() {
        return pquantity;
    }

    public void setPquantity(int pquantity) {
        this.pquantity = pquantity;
    }

    public double getPtotalprice() {
        return ptotalprice;
    }

    public void setPtotalprice(double ptotalprice) {
        this.ptotalprice = ptotalprice;
    }
}
