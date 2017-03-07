package com.example.android.focusednews;

/**
 * Created by Jason Jia on 3/5/2017.
 */

public class NewsDataBase {
    private String mCountryName;
    private String mSectionName;
    private String mNewsTitle;
    private String mDateTime;
    private String mURL;

    public NewsDataBase(String CountryName, String SectionName, String NewsTitle, String DateTime, String URL) {
        mCountryName = CountryName;
        mSectionName = SectionName;
        mNewsTitle = NewsTitle;
        mDateTime = DateTime;
        mURL = URL;
    }

    public String getCountryName() {
        return mCountryName;
    }

    public String getSectionName() {
        return mSectionName;
    }

    public String getNewsTitle() {
        return mNewsTitle;
    }

    public String getDateTime() {
        return mDateTime;
    }

    public String getURL() {
        return mURL;
    }
}
