package com.example.demo;

public class Security {
    String uniqueId;
    String symbol;
    String security;
    Integer earliestDateOfTrade = Integer.MAX_VALUE;
    Float historicalCost = 0.0F;

    public Security(String uniqueId, String symbol, String security) {
        this.uniqueId = uniqueId;
        this.symbol = symbol;
        this.security = security;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getSecurity() {
        return security;
    }

    public Float getHistoricalCost() {
        return historicalCost;
    }

    public void setHistoricalCost(Float historicalCost) {
        this.historicalCost = historicalCost;
    }

    public Integer getEarliestDateOfTrade() {
        return earliestDateOfTrade;
    }

    public void setEarliestDateOfTrade(Integer earliestDateOfTrade) {
        this.earliestDateOfTrade = earliestDateOfTrade;
    }
}
