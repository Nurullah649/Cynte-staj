package com.example.example_sdk;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Camera;


public class ProjectInformationSDK implements Calculator{
    private static final String SDK_VERSION = "1.0";
    private static final String CREATOR_NAME = "Nurullah";
    public String getSdkVersion() {
        return SDK_VERSION;
    }

    public String getCreatorName() {
        return CREATOR_NAME;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b != 0.0) {
            return a / b;
        } else {
            throw new IllegalArgumentException("Division by zero");
        }
    }
    public double calculatePercentage(double part, double total) {
        if (total == 0) {
            throw new IllegalArgumentException("Total cannot be zero.");
        }
        return (part / total) * 100;
    }
    public static boolean getCameraStatement(){
        IntentFilter filter=new IntentFilter(Intent.ACTION_CAMERA_BUTTON);

        return false;
    }

}
