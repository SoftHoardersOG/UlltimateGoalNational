package com.example.trajectoryvisualizer;

class Math {
    static double toRadians(double angle) {
        angle += 180;
        return java.lang.Math.toRadians(angle);
    }
}
