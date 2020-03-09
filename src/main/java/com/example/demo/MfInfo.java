package com.example.demo;

public class MfInfo {
    /*
       "^<MFINFO><SECINFO><SECID><UNIQUEID>([A-Z]+)<UNIQUEIDTYPE>([A-Z]+)</SECID><TICKER>([A-Z.]+)"
       + "<SECNAME>([A-Za-z0-9 ]+)</SECINFO><MFTYPE>([A-Z]+)</MFINFO>$";
       <MFINFO><SECINFO><SECID><UNIQUEID>MCGA<UNIQUEIDTYPE>CUSIP</SECID><TICKER>NW.MCGA
       <SECNAME>MSIF Disc IMSIF Disc I</SECINFO><MFTYPE>OPENEND</MFINFO>
     */
    String uniqueId;
    String uniqueIdType;
    String ticker;
    String secName;
    String mfType;

    MfInfo(String uniqueId, String uniqueIdType, String ticker,
           String secName, String mfType) {
        this.uniqueId = uniqueId;
        this.uniqueIdType = uniqueIdType;
        this.ticker = ticker;
        this.secName = secName;
        this.mfType = mfType;
    }
}
