package model;

public class Msinfo {

    private int msno;
    private String msname;
    private String dosage;
    private int msquantity;
    private String shelflife;
    private int exp;

    public Msinfo(int msno, String msname, String dosage, int msquantity, String shelflife, int exp) {
        this.msno = msno;
        this.msname = msname;
        this.dosage = dosage;
        this.msquantity = msquantity;
        this.shelflife = shelflife;
        this.exp = exp;
    }

    public int getMsno() {
        return msno;
    }

    public void setMsno(int msno) {
        this.msno = msno;
    }

    public String getMsname() {
        return msname;
    }

    public void setMsname(String msname) {
        this.msname = msname;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public int getMsquantity() {
        return msquantity;
    }

    public void setMsquantity(int msquantity) {
        this.msquantity = msquantity;
    }

    public String getShelflife() {
        return shelflife;
    }

    public void setShelflife(String shelflife) {
        this.shelflife = shelflife;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
