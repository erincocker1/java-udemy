package org.example.section6;

public class BucketsOfPaint {
    public static int getBucketCount(double width, double height, double areaPerBucket, int extraBuckets) {
        if (width <= 0 || height <= 0 || areaPerBucket <= 0 || extraBuckets < 0) {
            return -1;
        }

        double remainingArea = width*height - areaPerBucket*extraBuckets;
        return (int) Math.ceil(remainingArea/areaPerBucket);
    }

    public static int getBucketCount(double width, double height, double areaPerBucket) {
        return getBucketCount(width, height, areaPerBucket, 0);
    }
}
