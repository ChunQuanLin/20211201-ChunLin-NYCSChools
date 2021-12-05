package com.example.a20211201_chunlin_nycschools.model;

import static com.example.a20211201_chunlin_nycschools.StringConstants.notApplicable;

import com.google.gson.annotations.SerializedName;

/*
* This is mainly for saving the sat scores from the api call.
* */
public class NYCSchoolSATScoreModel {
    private String dbn = "";
    private String school_name = String.format("School name: %s", notApplicable);
    @SerializedName("sat_critical_reading_avg_score")
    private String readingAverageScore = notApplicable;
    @SerializedName("sat_math_avg_score")
    private String mathAverageScore = notApplicable;
    @SerializedName("sat_writing_avg_score")
    private String writingAverageScore = notApplicable;

    public String getDbn() {
        return dbn;
    }

    public void setDbn(String dbn) {
        if(dbn != null) this.dbn = dbn;
    }

    public String getWritingAverageScore() {
        return writingAverageScore;
    }

    public void setWritingAverageScore(String writingAverageScore) {
        if(writingAverageScore != null)
        this.writingAverageScore = writingAverageScore;
    }

    public String getMathAverageScore() {
        return mathAverageScore;
    }

    public void setMathAverageScore(String mathAverageScore) {
        if(mathAverageScore != null)
        this.mathAverageScore = mathAverageScore;
    }

    public String getReadingAverageScore() {
        return readingAverageScore;
    }

    public void setReadingAverageScore(String readingAverageScore) {
        if(readingAverageScore != null)
        this.readingAverageScore = readingAverageScore;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        if(school_name != null)
        this.school_name = school_name;
    }
}
