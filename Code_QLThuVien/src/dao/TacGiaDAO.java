/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import pojo.DocGia;
import pojo.TacGia;

/**
 *
 * @author ADMIN
 */
public class TacGiaDAO {

    KetNoiSQL kn = new KetNoiSQL();

    public ArrayList<TacGia> listTacGia() {
        ArrayList<TacGia> lstTG = new ArrayList<>();
        try {
            String sql = "select* from tacGia ";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                TacGia tg = new TacGia();
                tg.setMaTG(rs.getString("maTacGia"));
                tg.setTenTG(rs.getString("tenTacGia"));
                if (rs.getString("website") != null) {
                    tg.setWebsite(rs.getString("website"));
                }
                if (rs.getString("ghiChu") != null) {
                    tg.setGhiChu(rs.getString("ghiChu"));
                }

                lstTG.add(tg);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstTG;
    }

    public String layMaTacGiaCuoiCung() {
        ResultSet rs = null;
        String kq = "";
        try {
            String sql = "select MAX(right(maDocGia,7)) as maDocGia from docGia";
            kn.open();
            rs = kn.executeQuery(sql);
            while (rs.next()) {
                kq = rs.getString("maDocGia").trim();
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public boolean themTacGia(TacGia tg) {
        boolean kq = false;
        try {
            String sql = String.format("insert into tacGia values(N'%s','%s',N'%s')", tg.getTenTG(), tg.getWebsite(), tg.getGhiChu());
            kn.open();
            int n = kn.excuteUpdate(sql);
            if (n >= 1) {
                kq = true;
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public boolean updateTacGia(TacGia tg) {
        boolean kq = false;
        try {
            String sql = String.format("Update tacGia set tenTacGia=N'%s',website='%s',ghiChu=N'%s' "
                    + "where maTacGia='%s'"
                    + "", tg.getTenTG(), tg.getWebsite(), tg.getGhiChu(), tg.getMaTG());
            kn.open();
            int n = kn.excuteUpdate(sql);
            if (n >= 1) {
                kq = true;
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public boolean deleteTacGia(String maTG) {
        boolean kq = false;
        try {
            String sql = String.format("delete from tacGia where maTacGia = '%s' ", maTG);
            kn.open();
            int n = kn.excuteUpdate(sql);
            if (n >= 1) {
                kq = true;
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public String layMaTGCuoiCung() {
        ResultSet rs = null;
        String kq = "";
        try {
            String sql = "select maTacGia from tacGia";
            kn.open();
            rs = kn.executeQuery(sql);
            while (rs.next()) {
                kq = rs.getString("maTacGia").trim();
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
}
