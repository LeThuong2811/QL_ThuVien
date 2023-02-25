/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import pojo.NhaXuatBan;

/**
 *
 * @author ADMIN
 */
public class NhaXuatBanDAO {

    KetNoiSQL kn = new KetNoiSQL();

    public ArrayList<NhaXuatBan> listNXB() {
        ArrayList<NhaXuatBan> lstNXB = new ArrayList<>();
        try {
            String sql = "select* from nhaXuatBan";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                NhaXuatBan nxb = new NhaXuatBan();
                if (rs.getString("maNXB") != null) {
                    nxb.setMaNXB(rs.getString("maNXB"));
                }
                if (rs.getString("tenNXB") != null) {
                    nxb.setTenNXB(rs.getString("tenNXB"));
                }
                if (rs.getString("diaChi") != null) {
                    nxb.setDiaChi(rs.getString("diaChi"));
                }
                if (rs.getString("email") != null) {
                    nxb.setEmail(rs.getString("email"));
                }
                if (rs.getString("thongTInNguoiDaiDien") != null) {
                    nxb.setThongTinNguoiDaiDien(rs.getString("thongTInNguoiDaiDien"));
                }
                lstNXB.add(nxb);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstNXB;
    }

    public boolean themNXB(NhaXuatBan nxb) {
        boolean kq = false;
        try {
            String sql = String.format("insert into nhaXuatBan values(N'%s',N'%s','%s',N'%s')", nxb.getTenNXB(), nxb.getDiaChi(), nxb.getEmail(), nxb.getThongTinNguoiDaiDien());
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

    public boolean updateNXB(NhaXuatBan nxb) {
        boolean kq = false;
        try {
            String sql = String.format("update nhaXuatBan set tenNXB = N'%s',diaChi = N'%s',email = '%s',thongTinNguoiDaiDien=N'%s'"
                    + "where maNXB = '%s' "
                    + "", nxb.getTenNXB(), nxb.getDiaChi(), nxb.getEmail(), nxb.getThongTinNguoiDaiDien(), nxb.getMaNXB());
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

    public boolean deleteNXB(String maNXB) {
        boolean kq = false;
        try {
            String sql = String.format("delete from nhaXuatBan where maNXB = '%s' ", maNXB);
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

    public String layMaNXBCuoiCung() {
        ResultSet rs = null;
        String kq = "";
        try {
            String sql = "select maNXB from nhaXuatBan";
            kn.open();
            rs = kn.executeQuery(sql);
            while (rs.next()) {
                kq = rs.getString("maNXB").trim();
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
}
