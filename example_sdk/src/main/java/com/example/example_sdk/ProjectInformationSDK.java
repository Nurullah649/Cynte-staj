package com.example.example_sdk;

public class ProjectInformationSDK {
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
    public static double calculatePercentage(double part, double total) {
        if (total == 0) {
            throw new IllegalArgumentException("Total cannot be zero.");
        }
        return (part / total) * 100;
    }
}
