package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class QfxParse {
    private static final Logger logger = LoggerFactory.getLogger(QfxParse.class);

    public static void main(String[] args) {
        SpringApplication.run(QfxParse.class, args);
        HashMap<String, Security> securityMap = new HashMap();
        ArrayList<BuyMf> buyMfList = new ArrayList<>();
        ArrayList<MfInfo> mfInfoList = new ArrayList<>();
        HashMap<String, InvPos> invPosMap = new HashMap<>();
        securityMap.put("RNWA", new Security( "RNWA","RNWGX", "American Funds New World Fund R6"));
        securityMap.put("BPPA", new Security("BPPA", "PFORX", "PIMCO International Bond Fund USD Hedged"));
        securityMap.put("MCGA", new Security("MCGA", "MPEGX", "Morgan Stanley Institutional Fund Trust Class I"));
        securityMap.put("CSYA", new Security("CSYA", "VBTLX", "Vanguard Total Bond Market Index Fund Admiral Shares"));
        securityMap.put("BFSA", new Security("BFSA", "BUFSX", "Buffalo Small Cap Fund"));
        securityMap.put("CUQA", new Security("CUQA", "VTSAX", "Vanguard Total Stock Market Index Fund"));

        ArrayList<Income>incomeList = new ArrayList<>();

        File file = new File("/Users/don/workspace/qfx/943-8030820200229.XML");

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        // Read QFX
        List<String> lines = new ArrayList<>();
        boolean done = false;
        while (!done) {
            try {
                String line = br.readLine();
                if (line != null) {
                    lines.add(line);
                } else {
                    done = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Set header
        List<String> header = lines.subList(0,10);
        lines = lines.subList(10, lines.size());
        String dtEnd = "^<DTEND>[0-9]{8,8}$";
        Pattern dtEndPattern = Pattern.compile(dtEnd);
        int dtEndNdx = 0;
        int ndx = 0;
        for (String line : lines) {
            Matcher dtEndMatcher = dtEndPattern.matcher(line);
            if(dtEndMatcher.matches()) {
                dtEndNdx = ndx;
            }
            ++ndx;
        }

        // Set preface
        List<String> preface = lines.subList(0,++dtEndNdx);
        lines = lines.subList(dtEndNdx, lines.size());

        // Set BUYMF
        List<String> lines1 = new ArrayList<>();
        String buymf = "^<BUYMF><INVBUY><INVTRAN><FITID>([A-Z-]*)([0-9]+)([A-Z]+)([0-9.]+)EE<DTTRADE>([0-9]+)<MEMO>([a-zA-Z]+)"
                + "</INVTRAN><SECID><UNIQUEID>([A-Z]+)<UNIQUEIDTYPE>([A-Z]+)</SECID><UNITS>([0-9.]+)<UNITPRICE>([0-9.]+)"
                + "<COMMISSION>([0-9.]+)<TOTAL>([0-9.]+)<SUBACCTSEC>([A-Z]+)<SUBACCTFUND>([A-Z]+)</INVBUY><BUYTYPE>"
                + "([A-Z]+)</BUYMF>$";
        Pattern buymfPattern = Pattern.compile(buymf);
        for (String line : lines) {
            Matcher m = buymfPattern.matcher(line);
            if(m.matches()) {
                for(int i = 0; i<=15; ++i) {
                    try {
                        String group = m.group(i);
                        logger.debug(group);
                    } catch (IllegalStateException e) {
                        logger.error(e.getMessage());
                    }
                }
                BuyMf buyMf = new BuyMf(m.group(1),Integer.valueOf(m.group(2)),m.group(3),Float.parseFloat(m.group(4)),
                    Integer.valueOf(m.group(5)),m.group(6),m.group(7),m.group(8),
                    Float.parseFloat(m.group(9)), Float.parseFloat(m.group(10)),Float.parseFloat(m.group(11)),Float.parseFloat(m.group(12)),
                    m.group(13),m.group(14),m.group(15));
                Security security = securityMap.get(buyMf.getUniqueId());
                security.setHistoricalCost(security.getHistoricalCost() + buyMf.getTotal());
                buyMfList.add(buyMf);
            } else {
                lines1.add(line);
            }
        }
        lines = new ArrayList<>(lines1);
        lines1.clear();

        // Set INCOME
        String income = "^<INCOME><INVTRAN><FITID>([A-Z-]*)([0-9]+)([A-Z]+)([0-9.]+)EE<DTTRADE>([0-9]+)<MEMO>([a-zA-Z]+)"
                + "</INVTRAN><SECID><UNIQUEID>([A-Z]+)<UNIQUEIDTYPE>([A-Z]+)</SECID><INCOMETYPE>([A-Z]+)<TOTAL>([0-9.]+)"
                + "<SUBACCTSEC>([A-Z]+)<SUBACCTFUND>([A-Z]+)</INCOME>$";
        Pattern incomePattern = Pattern.compile(income);
        for (String line : lines) {
            Matcher m = incomePattern.matcher(line);
            if(m.matches()) {
                Income inc = new Income(m.group(1),Integer.valueOf(m.group(2)),m.group(3),Float.parseFloat(m.group(4)),
                        Integer.valueOf(m.group(5)),m.group(6),m.group(7),m.group(8),
                        m.group(9), Float.parseFloat(m.group(10)), m.group(11),m.group(12));
                incomeList.add(inc);
            } else {
                lines1.add(line);
            }
        }
        lines = new ArrayList<>(lines1);
        lines1.clear();

        // Set MfInfo
        String mfinfo = "^<MFINFO><SECINFO><SECID><UNIQUEID>([A-Z]+)<UNIQUEIDTYPE>([A-Z]+)</SECID><TICKER>([A-Z.]+)"
                + "<SECNAME>([A-Za-z0-9 ]+)</SECINFO><MFTYPE>([A-Z]+)</MFINFO>$";
        Pattern mfinfoPattern = Pattern.compile(mfinfo);
        List<String> mfinfos = new ArrayList<>();
        for (String line : lines) {
            Matcher m = mfinfoPattern.matcher(line);
            if(m.matches()) {
                MfInfo mfInfo = new  MfInfo(m.group(1), m.group(2), m.group(3), m.group(4), m.group(5));
                mfInfoList.add(mfInfo);
            } else {
                lines1.add(line);
            }
        }
        lines = new ArrayList<>(lines1);
        lines1.clear();

        // Set InvPos
        String invposlist = "^<INVPOSLIST>(.+)</INVPOSLIST>.*$";
        Pattern invposlistPattern = Pattern.compile(invposlist);
        String posMf = "<INVPOS><SECID><UNIQUEID>([A-Z]+)<UNIQUEIDTYPE>([A-Z]+)</SECID>"
                + "<HELDINACCT>([A-Z]+)<POSTYPE>([A-Z]+)<UNITS>([0-9.]+)<UNITPRICE>([0-9.]+)"
                + "<MKTVAL>([0-9.]+)<DTPRICEASOF>([0-9]+)</INVPOS></POSMF>.*";
        Pattern posMfPattern = Pattern.compile(posMf);
        for (String line : lines) {
            Matcher m = invposlistPattern.matcher(line);
            if(m.matches()) {
                String[] posMfArray = line.split("<POSMF>");
                for(String line1 : posMfArray) {
                    Matcher m2 = posMfPattern.matcher(line1);
                    if(m2.matches()) {
                        String uniqueId = m2.group(1);
                        InvPos invPos = new InvPos(uniqueId, m2.group(2), m2.group(3), m2.group(4),
                            Float.parseFloat(m2.group(5)), Float.parseFloat(m2.group(6)),
                            Float.parseFloat(m2.group(7)), Integer.valueOf(m2.group(8)));
                        invPosMap.put(invPos.getUniqueId(),invPos);
                    }
                }
            } else {
                lines1.add(line);
            }
        }
        List<String> remainder = new ArrayList<>(lines1);

        // Write report of returns
        Set<Map.Entry<String, Security>> entrySet = securityMap.entrySet();
        for(Map.Entry<String, Security> entry: entrySet) {
            Security security = entry.getValue();
            String uniqueId = entry.getKey();
            InvPos invPos = invPosMap.get(uniqueId);
            Float delta = invPos.getMktVal() - security.getHistoricalCost();
            Float percent = delta/security.getHistoricalCost() * 100.0F;
            String line = String.format("%s Cost: %.2f Units: %.2f Market Value: %.2f Gain/Loss: %.2f%%",
                    security.getSecurity(), security.getHistoricalCost(), invPos.getUnits(), invPos.getMktVal(),
                    percent);
            logger.info(line);
        }

        file = new File("/Users/don/workspace/qfx/import.csv");
        try {
            PrintWriter pw = new PrintWriter(file);
            pw.println("Symbol,Current Price,Date,Time,Change,Open,High,Low,Volume,Trade Date,Purchase Price,Quantity,Commission,High Limit,Low Limit,Comment");
            // VTTVX,19.14,2020/03/06,20:00 EST,-0.16,,,,,20200228,17.4931129477,1083.0,,,,
            // EMB,115.3,2020/03/06,16:00 EST,-0.47999573,115.08,115.375,114.34,7200531,20190222,108.36,19.0,,,,Amy IRA;
            for (Map.Entry<String, Security> entry : entrySet) {
                Security security = entry.getValue();
                String uniqueId = entry.getKey();
                InvPos invPos = invPosMap.get(uniqueId);
                pw.printf("%s,,,,,,,,,,,%f,,,,John Galt 401K\n", security.getSymbol(), invPos.getUnits());
            }
            pw.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }
}
