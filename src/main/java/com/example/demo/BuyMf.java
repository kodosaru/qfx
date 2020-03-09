package com.example.demo;

public class BuyMf {
    /*^<BUYMF><INVBUY><INVTRAN><FITID>([A-Z-]*)([0-9]+)([A-Z]+)([0-9.]+)EE<DTTRADE>([0-9]+)<MEMO>([a-zA-Z]+)"
            + "</INVTRAN><SECID><UNIQUEID>([A-Z]+)<UNIQUEIDTYPE>([A-Z]+)</SECID><UNITS>([0-9.]+)<UNITPRICE>([0-9.]+)"
            + "<COMMISSION>([0-9.]+)<TOTAL>([0-9.]+)<SUBACCTSEC>([A-Z]+)<SUBACCTFUND>([A-Z]+)</INVBUY><BUYTYPE>"
            + "([A-Z]+)</BUYMF>$";
        <BUYMF><INVBUY><INVTRAN><FITID>20190729CSYA46.43720000EE<DTTRADE>20190729<MEMO>DepositAllocationChange</INVTRAN>
        <SECID><UNIQUEID>CSYA<UNIQUEIDTYPE>CUSIP</SECID><UNITS>46.43720000<UNITPRICE>1.09825743<COMMISSION>0.00
        <TOTAL>51.0<SUBACCTSEC>CASH<SUBACCTFUND>CASH</INVBUY><BUYTYPE>BUY</BUYMF>
     */
    String unused;
    Integer date;
    String uniqueId2;
    Float price;
    Integer date2;
    String memo;
    String uniqueId;
    String uniqueIdType;
    Float units;
    Float unitPrice;
    Float commision;
    Float total;
    String subAcctSec;
    String subAcctFund;
    String buyType;

    BuyMf( String unused, Integer date, String uniqueId2, Float price,
           Integer date2, String memo, String uniqueId, String uniqueIdType,
           Float units, Float unitPrice, Float commision, Float total,
           String subAcctSec, String subAcctFund, String buyType) {
        this.unused = unused;
        this.date = date;
        this.uniqueId2 = uniqueId2;
        this.price = price;
        this.date2 = date2;
        this.memo = memo;
        this.uniqueId = uniqueId;
        this.uniqueIdType = uniqueIdType;
        this.units = units;
        this.unitPrice = unitPrice;
        this.commision = commision;
        this.total = total;
        this.subAcctSec = subAcctSec;
        this.subAcctFund = subAcctFund;
        this.buyType = buyType;
    }

    public Float getTotal() {
        return total;
    }

    public String getUniqueId() {
        return uniqueId;
    }
}
