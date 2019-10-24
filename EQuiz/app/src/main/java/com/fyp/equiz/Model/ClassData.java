package com.fyp.equiz.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ClassData implements Parcelable{

    private String className,classID, enrollmentKey;

    protected ClassData(Parcel in) {
        className = in.readString();
        classID = in.readString();
        enrollmentKey = in.readString();
    }

    public static final Creator<ClassData> CREATOR = new Creator<ClassData>() {
        @Override
        public ClassData createFromParcel(Parcel in) {
            return new ClassData(in);
        }

        @Override
        public ClassData[] newArray(int size) {
            return new ClassData[size];
        }
    };

    public String getClassName() {
        return className;
    }

    public String getClassID() {
        return classID;
    }

    public String getEnrollmentKey() {
        return enrollmentKey;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public void setEnrollmentKey(String enrollmentKey) {
        this.enrollmentKey = enrollmentKey;
    }

    public ClassData(){

    }

    public ClassData(String className, String classID, String enrollmentKey) {
        this.className = className;
        this.classID = classID;
        this.enrollmentKey = enrollmentKey;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(className);
        dest.writeString(classID);
        dest.writeString(enrollmentKey);
    }
}


