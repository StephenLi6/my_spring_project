package com.itheima.domain;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/1621:08
 * com.itheima.domainMyDailyProject
 */
public class Sc {
    private String cno;
    private String score;
    private String sno;

    @Override
    public String toString() {
        return "Sc{" +
                "cno='" + cno + '\'' +
                ", score='" + score + '\'' +
                ", sno='" + sno + '\'' +
                '}';
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }
}
