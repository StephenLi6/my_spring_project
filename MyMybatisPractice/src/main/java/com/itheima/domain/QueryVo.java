package com.itheima.domain;

import java.util.ArrayList;
import java.util.List;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/1510:04
 * com.itheima.domainMyDailyProject
 */
public class QueryVo {
    private Integer min;
    private Integer max;
    List<Integer> mang ;

    public List<Integer> getMang() {
        return mang;
    }

    public void setMang(List<Integer> mang) {
        this.mang = mang;
    }

    public QueryVo(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}
