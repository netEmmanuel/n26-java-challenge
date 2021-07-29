package com.n26.Model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.n26.Constant.AppConstant;

/**
 * Created by oyelakin on 4/29/2021.
 * Stats Model
 */
public class Statistics {


    private BigDecimal sum;
    private BigDecimal avg;
    private BigDecimal max;
    private BigDecimal min;
    private long count;

    /**
     *
     * @param sum
     * @param avg
     * @param max
     * @param min
     * @param count
     * model constructor
     */
    public Statistics(BigDecimal sum, BigDecimal avg, BigDecimal max, BigDecimal min, long count) {
        super();
        this.sum = sum;
        this.avg = avg;
        this.max = max;
        this.min = min;
        this.count = count;
    }

    /**
     * Statistics method
     */
    public Statistics() {
        sum = BigDecimal.valueOf(0.00).setScale(AppConstant.SCALE, RoundingMode.HALF_UP);
        avg = BigDecimal.valueOf(0.00).setScale(AppConstant.SCALE, RoundingMode.HALF_UP);
        max = BigDecimal.valueOf(0.00).setScale(AppConstant.SCALE, RoundingMode.HALF_UP);
        min = BigDecimal.valueOf(0.00).setScale(AppConstant.SCALE, RoundingMode.HALF_UP);
        count = 0L;
    }

    /**
     *
     * @return the sum
     */
    public BigDecimal getSum() {
        return sum;
    }

    /**
     *
     * @param sum is the sum to set
     */
    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    /**
     *
     * @return the average
     */
    public BigDecimal getAvg() {
        return avg;
    }

    /**
     *
     * @param avg is the average set
     */
    public void setAvg(BigDecimal avg) {
        this.avg = avg;
    }

    /**
     *
     * @return the maximum
     */
    public BigDecimal getMax() {
        return max;
    }

    /**
     *
     * @param max the maximum value to set
     */
    public void setMax(BigDecimal max) {
        this.max = max;
    }

    /**
     *
     * @return the minimum value
     */
    public BigDecimal getMin() {
        return min;
    }

    /**
     *
     * @param min is the mimimum value to set
     */
    public void setMin(BigDecimal min) {
        this.min = min;
    }

    /**
     *
     * @return the count value
     */
    public long getCount() {
        return count;
    }

    /**
     *
     * @param count is the count to set
     */
    public void setCount(long count) {
        this.count = count;
    }


    /**
     *
     * @return returns the string representation of the object
     */
    @Override
    public String toString() {
        return "Statistics{" +
                "sum=" + sum +
                ", avg=" + avg +
                ", max=" + max +
                ", min=" + min +
                ", count=" + count +
                '}';
    }
}
