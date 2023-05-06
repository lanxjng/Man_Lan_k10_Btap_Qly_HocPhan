package com.example.qly_hocphan;

import java.io.Serializable;

public class HocPhan implements Serializable {
    private String MaHp, TenHp, SoTc, HpTq, HpSs, KhoaQly;

    public String getMaHp() {
        return MaHp;
    }

    public void setMaHp(String maHp) {
        MaHp = maHp;
    }

    public String getTenHp() {
        return TenHp;
    }

    public void setTenHp(String tenHp) {
        TenHp = tenHp;
    }

    public String getSoTc() {
        return SoTc;
    }

    public void setSoTc(String soTc) {
        SoTc = soTc;
    }

    public String getHpTq() {
        return HpTq;
    }

    public void setHpTq(String hpTq) {
        HpTq = hpTq;
    }

    public String getHpSs() {
        return HpSs;
    }

    public void setHpSs(String hpSs) {
        HpSs = hpSs;
    }

    public String getKhoaQly() {
        return KhoaQly;
    }

    public void setKhoaQly(String khoaQly) {
        KhoaQly = khoaQly;
    }

    public HocPhan() {
    }

    public HocPhan(String maHp, String tenHp, String soTc, String hpTq, String hpSs, String khoaQly) {
        MaHp = maHp;
        TenHp = tenHp;
        SoTc = soTc;
        HpTq = hpTq;
        HpSs = hpSs;
        KhoaQly = khoaQly;
    }

    public HocPhan(String tenHp, String soTc, String hpTq, String hpSs, String khoaQly) {
        TenHp = tenHp;
        SoTc = soTc;
        HpTq = hpTq;
        HpSs = hpSs;
        KhoaQly = khoaQly;
    }
}
