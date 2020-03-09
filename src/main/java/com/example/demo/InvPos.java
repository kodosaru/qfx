package com.example.demo;

public class InvPos {
    /*
            "<INVPOS><SECID><UNIQUEID>([A-Z]+)<UNIQUEIDTYPE>([A-Z]+)</SECID>"
                + "<HELDINACCT>([A-Z]+)<POSTYPE>([A-Z]+)<UNITS>([0-9.]+)<UNITPRICE>([0-9.]+)"
                + "<MKTVAL>([0-9.]+)<DTPRICEASOF>([0-9]+)</INVPOS></POSMF>.*";
     */
    private String uniqueId;
    private String uniqueIdType;
    private String heldInAcct;
    private String posType;
    private Float units;
    private Float unitPrice;
    private Float mktVal;
    private Integer date;

    public InvPos(String uniqueId, String uniqueIdType, String heldInAcct, String posType, Float units, Float unitPrice, Float mktVal, Integer date) {
        this.uniqueId = uniqueId;
        this.uniqueIdType = uniqueIdType;
        this.heldInAcct = heldInAcct;
        this.posType = posType;
        this.units = units;
        this.unitPrice = unitPrice;
        this.mktVal = mktVal;
        this.date = date;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public String getUniqueIdType() {
        return uniqueIdType;
    }

    public String getHeldInAcct() {
        return heldInAcct;
    }

    public String getPosType() {
        return posType;
    }

    public Float getUnits() {
        return units;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public Float getMktVal() {
        return mktVal;
    }

    public Integer getDate() {
        return date;
    }
}
