package org.example.flowlimit.demo.flowLimitAlgorithm;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 漏桶算法
 */
@Data
public class LeakybucketDemo {

    /**
     * @param capacity : 桶容量
     */
    public LeakybucketDemo(long capacity) {
        this.capacity = capacity;
        this.duration = 1000;
        // 根据Qps算通过的效率
        BigDecimal divide = new BigDecimal(capacity).divide(new BigDecimal(duration));
        this.velocity = divide.doubleValue();
    }
    /**
     * The water that is in the bucket.
     */
    private long left;
    /**
     * The timestamp of the last successful water injection.
     */
    private long lastInjectTime = System.currentTimeMillis();
    /**
     * The bucket capacity.
     */
    private long capacity;
    /**
     * The time required for the bucket to be drained.
     */
    private long duration;
    /**
     * The water leakage rate of the bucket, which is equal to capacity/duration.
     */
    private double velocity;

    public boolean tryAcquire() {
        long now = System.currentTimeMillis();
        // Water in the bucket = Previously left water – Water leaked during the past period of time.
        // Water leaked during the last period of time = (Current time – Last water injection time) × Water leakage rate
        // If the current time is too far from the last water injection time (no water has been injected for a long time), the water left in the bucket is 0 (the bucket is drained).
        left = Math.max(0, left - (long)((now - lastInjectTime) * velocity));
        // If no water overflows after one unit volume of water is injected, access is allowed.
        if (left + 1 <= capacity) {
            lastInjectTime = now;
            left++;
            return true;
        } else {
            return false;
        }
    }
}
