package com.example.demo;

public class Income {
    /*
         "^<INCOME><INVTRAN><FITID>([A-Z-]*)([0-9]+)([A-Z]+)([0-9.]+)EE<DTTRADE>([0-9]+)<MEMO>([a-zA-Z]+)"
         + "</INVTRAN><SECID><UNIQUEID>([A-Z]+)<UNIQUEIDTYPE>([A-Z]+)</SECID><INCOMETYPE>([A-Z]+)<TOTAL>([0-9.]+)"
         + "<SUBACCTSEC>([A-Z]+)<SUBACCTFUND>([A-Z]+)</INCOME>$";
         <INCOME><INVTRAN><FITID>MISC-20200224RNWA58.56870000EE<DTTRADE>20190729<MEMO>Contribution</INVTRAN><SECID>
         <UNIQUEID>RNWA<UNIQUEIDTYPE>CUSIP</SECID><INCOMETYPE>MISC<TOTAL>3107.17<SUBACCTSEC>CASH<SUBACCTFUND>CASH</INCOME>
     */
    String misc;
    Integer date;
    String uniqueId;
    Float price;
    Integer date2;
    String memo;
    String uniqueId2;
    String uniqueIdType;
    String incomeType;
    Float total;
    String subAcctSec;
    String subAcctFund;

    Income(String misc, Integer date, String uniqueId, Float price,
           Integer date2, String memo, String uniqueId2, String uniqueIdType,
           String incomeType, Float total, String subAcctSec, String subAcctFund) {
        this.misc = misc;
        this.date = date;
        this.uniqueId = uniqueId;
        this.price = price;
        this.date2 = date2;
        this.memo = memo;
        this.uniqueId2 = uniqueId2;
        this.uniqueIdType = uniqueIdType;
        this.incomeType = incomeType;
        this.total = total;
        this.subAcctSec = subAcctSec;
        this.subAcctFund = subAcctFund;
    }
}
