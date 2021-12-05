package com.example.a20211201_chunlin_nycschools.model;

import static com.example.a20211201_chunlin_nycschools.StringConstants.notApplicable;

import com.google.gson.annotations.SerializedName;

/*
* The main pojo that we'll be using.
* */
public class NYCSchoolModel {
    @SerializedName("school_name")
    private String schoolName = String.format("School name: %s", notApplicable);
    @SerializedName("overview_paragraph")
    private String overviewParagraph = String.format("School overview: %s", notApplicable);
    @SerializedName("phone_number")
    private String phoneNumber = String.format("School phone number: %s", notApplicable);
    @SerializedName("school_email")
    private String schoolEmail = String.format("School email: %s", notApplicable);
    private String website = String.format("School website: %s", notApplicable);
    private String dbn = "";
    private String satAvgReadScore = notApplicable;
    private String satAvgMathScore = notApplicable;
    private String satAvgWriteScore = notApplicable;
    @SerializedName("primary_address_line_1")
    private String address = "";
    private String city = "";
    @SerializedName("state_code")
    private String state = "";
    private String zip = "";
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        if(schoolName != null) this.schoolName = schoolName;
    }

    public String getOverviewParagraph() {
        return overviewParagraph;
    }

    public void setOverviewParagraph(String overviewParagraph) {
        if(overviewParagraph != null)
        this.overviewParagraph = overviewParagraph;
    }

    public String getFormattedLocation() {
        return String.format("%s, %s %s, %s", address, city, state, zip);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber != null)
        this.phoneNumber = phoneNumber;
    }

    public String getSchoolEmail() {
        return schoolEmail;
    }

    public void setSchoolEmail(String schoolEmail) {
        if(schoolEmail != null) this.schoolEmail = schoolEmail;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        if(website != null) this.website = website;
    }

    public String getDbn() {
        return dbn;
    }

    public void setDbn(String dbn) {
        if(dbn != null) this.dbn = dbn;
    }

    public String getSatAvgReadScore() {
        return satAvgReadScore;
    }

    public void setSatAvgReadScore(String satAvgReadScore) {
        if(satAvgReadScore != null)
        this.satAvgReadScore = satAvgReadScore;
    }

    public String getSatAvgMathScore() {
        return satAvgMathScore;
    }

    public void setSatAvgMathScore(String satAvgMathScore) {
        if(satAvgMathScore != null)
        this.satAvgMathScore = satAvgMathScore;
    }

    public String getSatAvgWriteScore() {
        return satAvgWriteScore;
    }

    public void setSatAvgWriteScore(String satAvgWriteScore) {
        if(satAvgWriteScore != null)
        this.satAvgWriteScore = satAvgWriteScore;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if(address != null)
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if(city != null)
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if(state != null)
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        if(zip != null)
        this.zip = zip;
    }
}
