/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import pojo.TacGia;
import pojo.TheLoai;

/**
 *
 * @author ADMIN
 */
public class TheLoaiDAO {
    
    KetNoiSQL kn = new KetNoiSQL();
    public ArrayList<TheLoai> listTheLoai() {
        ArrayList<TheLoai> lstTL = new ArrayList<>();
        try {
            String sql = "select* from theLoai ";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                TheLoai tl = new TheLoai();
                tl.setMaTL(rs.getString("maTheLoai"));
                tl.setTenTL(rs.getString("tenTheLoai"));
                lstTL.add(tl);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstTL;
    }
    
    public boolean themTheLoai(TheLoai tl) {
        boolean kq = false;
        try {
            String sql = String.format("insert into theLoai values(N'%s')",tl.getTenTL());
            kn.open();
            int n = kn.excuteUpdate(sql);
            if(n>=1)
                kq = true;
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    public boolean updateTheLoai(TheLoai tl) {
        boolean kq = false;
        try {
            String sql = String.format("update theLoai set tenTheLoai=N'%s' where maTheLoai ='%s'",tl.getTenTL(),tl.getMaTL());
            kn.open();
            int n = kn.excuteUpdate(sql);
            if(n>=1)
                kq = true;
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    public boolean deleteTheLoai(String maTL) {
        boolean kq = false;
        try {
            String sql = String.format("delete from theLoai where maTheLoai = '%s'",maTL);
            kn.open();
            int n = kn.excuteUpdate(sql);
            if(n>=1)
                kq = true;
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    public String layMaTLCuoiCung() {
        ResultSet rs = null;
        String kq = "";
        try {
            String sql = "select maTheLoai from theLoai";
            kn.open();
            rs = kn.executeQuery(sql);
            while (rs.next()) {
                kq = rs.getString("maTheLoai").trim();
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
}
