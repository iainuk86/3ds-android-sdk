package com.iainuk.mysdk;

public class ChallengeParameters {
    private String acsRefNumber;
    private String acsTransactionID;
    private String acsSignedContent;
    private String tdsRequestorAppUrl;
    private String tdsServerTransactionID;

    public ChallengeParameters(String acsRefNumber, String acsTransactionID,
                               String acsSignedContent, String tdsRequestorAppUrl,
                               String tdsServerTransactionID) {
        this.acsRefNumber = acsRefNumber;
        this.acsTransactionID = acsTransactionID;
        this.acsSignedContent = acsSignedContent;
        this.tdsRequestorAppUrl = tdsRequestorAppUrl;
        this.tdsServerTransactionID = tdsServerTransactionID;
    }

    public String getAcsRefNumber() {
        return acsRefNumber;
    }

    public void setAcsRefNumber(String acsRefNumber) {
        this.acsRefNumber = acsRefNumber;
    }

    public String getAcsTransactionID() {
        return acsTransactionID;
    }

    public void setAcsTransactionID(String acsTransactionID) {
        this.acsTransactionID = acsTransactionID;
    }

    public String getAcsSignedContent() {
        return acsSignedContent;
    }

    public void setAcsSignedContent(String acsSignedContent) {
        this.acsSignedContent = acsSignedContent;
    }

    public String getTdsRequestorAppUrl() {
        return tdsRequestorAppUrl;
    }

    public void setTdsRequestorAppUrl(String tdsRequestorAppUrl) {
        this.tdsRequestorAppUrl = tdsRequestorAppUrl;
    }

    public String getTdsServerTransactionID() {
        return tdsServerTransactionID;
    }

    public void setTdsServerTransactionID(String tdsServerTransactionID) {
        this.tdsServerTransactionID = tdsServerTransactionID;
    }
}
