package com.example.administrator.seek;

import android.app.Activity;

/**
 * Created by Administrator on 2019/1/3.
 */

public class data {
    private int id;
    private String school;
    private String place;
    private String type;
    private String major;
    private String year;
    private String max;
    private String average;
    private String min;
    private String kind;

    public data() {
        super();
        school = "";
        place = "";
        type="";
        major="";
        max="";
        average="";
        min="";
        kind="";
    }
    public data(String school, String place,String type,String major,String year,String max,String average,String min,String kind){
        super();
        this.school = school;
        this.place=place;
        this.type=type;
        this.major=major;
        this.year=year;
        this.max=max;
        this.average=average;
        this.min=min;
        this.kind=kind;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school=school;
    }

    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place=place;
    }

    public String getType(){ return type; }
    public void setType(String type){ this.type=type;}

    public String getMajor(){ return major;}
    public void setMajor(String major){this.major=major;}

    public String getYear(){return year;}
    public void setYear(String year){this.year=year;}

    public String getMax(){return max;}
    public void setMax(String max){ this.max=max;}

    public String getAverage(){return average;}
    public void setAverage(String average){this.average=average;}

    public String getMin(){return average;}
    public void setMin(String min){this.min=min;}

    public String getKind(){return kind;}
    public void setKind(String kind){this.kind=kind;}
}
