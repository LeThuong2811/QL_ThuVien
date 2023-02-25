
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pojo.NhanVien;

/**
 *
 * @author ngoth
 */
public class NhanVienDAO {

    KetNoiSQL kn = new KetNoiSQL();

    public ArrayList<NhanVien> listSach() {
        ArrayList<NhanVien> lstNhanVien = new ArrayList<>();
        try {
            String sql = "select* from nhanVien";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                NhanVien s = new NhanVien();
                if (rs.getString("maNhanVien") != null) {
                    s.setMaNhanVien(rs.getString("maNhanVien"));
                }
                if (rs.getString("hoTen") != null) {
                    s.setHoTen(rs.getString("hoTen"));
                }
                if (rs.getString("ngaySinh") != null) {
                    s.setNgaySinh(rs.getString("ngaySinh"));
                }
                if (rs.getString("soDienThoai") != null) {
                    s.setSoDienThoai(rs.getString("soDienThoai"));
                }
                lstNhanVien.add(s);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstNhanVien;
    }

    public static ArrayList<NhanVien> SelectSQL(String ten) {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "select * from nhanVien where hoTen like N'%" + ten + "%' or maNhanVien like N'%" + ten + "%' "
                + "or soDienThoai like N'%" + ten + "%' or gioiTinh like N'%" + ten + "%'";
        KetNoiSQL cnDB = new KetNoiSQL();
        cnDB.open();
        try {
            PreparedStatement ps = cnDB.con.prepareStatement(sql);
            ResultSet rs = cnDB.executeQuery(sql);
            while (rs.next()) {
                NhanVien a = new NhanVien(rs.getString("maNhanVien").trim(), rs.getString("hoTen").trim(), rs.getString("ngaySinh").trim(), rs.getString("soDienThoai").trim());
                list.add(a);
            }
            System.out.println("Done");
        } catch (SQLException e) {
            System.out.println("Error");
        }
        cnDB.close();
        return list;
    }

    public static ArrayList<NhanVien> GetDSNV() {
        ArrayList<NhanVien> list = new ArrayList<>();
        try {
            String sql = "select * from NHANVIEN ";
            KetNoiSQL cn = new KetNoiSQL();
            cn.open();
            ResultSet rs = cn.executeQuery(sql);

            while (rs.next()) {
                NhanVien a = new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTen"), rs.getString("ngaySinh"), rs.getString("soDienThoai"));
                list.add(a);
            }
            cn.close();
        } catch (SQLException e) {
        }
        return list;
    }

    public static void InsertSQL(NhanVien a) {
        String sql = "insert into nhanVien values(?,?,?,?)";
        KetNoiSQL cnDB = new KetNoiSQL();
        cnDB.open();
        try {
            PreparedStatement ps = cnDB.con.prepareStatement(sql);
            ps.setString(1, a.getMaNhanVien());
            ps.setString(2, a.getHoTen());
            ps.setString(3, a.getNgaySinh());
            ps.setString(4, a.getSoDienThoai());
            ps.executeUpdate();
            System.out.println("Them thanh cong");
        } catch (SQLException e) {
            System.out.println("Them khong thanh cong");
        }
        cnDB.close();
    }

    public static void DeleteSQL(String Ma) {
        String sql = "delete from nhanVien where maNhanVien = '" + Ma + "'";
        KetNoiSQL cnDB = new KetNoiSQL();
        cnDB.open();
        try {
            PreparedStatement ps = cnDB.con.prepareStatement(sql);
            ps.executeUpdate();
            System.out.println("Xoa thanh cong");
        } catch (SQLException e) {
            System.out.println("Xoa khong thanh cong");
        }
        cnDB.close();
    }

    public boolean UpdateNV(String ma, String ten, String birthDay, String phone, String gender, String img, String email, String cmnd) {
        boolean kq = false;
        try {
            String sql = String.format("update nhanVien set hoTen=N'%s',ngaySinh ='%s',soDienThoai='%s', "
                    + "gioiTinh=N'%s', hinh ='%s',email='%s', cmnd='%s' "
                    + "where maNhanVien='%s' ", ten, birthDay, phone, gender, img, email, cmnd, ma);
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

    public static void UpdateTenNVSQL(String ma, String ten) {
        String sql = String.format("update nhanVien set hoTen=N'%s' where maNhanVien='%s'", ma, ten);
        KetNoiSQL cnDB = new KetNoiSQL();
        cnDB.open();
        try {
            PreparedStatement ps = cnDB.con.prepareStatement(sql);
            ps.executeUpdate();
            System.out.println("Sua thanh cong");
        } catch (SQLException e) {
            System.out.println("Sua khong thanh cong");
        }
        cnDB.close();
    }

    public static void UpdateNgaySinhNVSQL(String ma, String ten) {
        String sql = "update nhanVien set ngaySinh=N'" + ten + "' where maNhanVien='" + ma + "'";
        KetNoiSQL cnDB = new KetNoiSQL();
        cnDB.open();
        try {
            PreparedStatement ps = cnDB.con.prepareStatement(sql);
            ps.executeUpdate();
            System.out.println("Sua thanh cong");
        } catch (SQLException e) {
            System.out.println("Sua khong thanh cong");
        }
        cnDB.close();
    }

    public static void UpdateSDTNVSQL(String ma, String ten) {
        String sql = "update nhanVien set soDienThoai=N'" + ten + "' where maNhanVien='" + ma + "'";
        KetNoiSQL cnDB = new KetNoiSQL();
        cnDB.open();
        try {
            PreparedStatement ps = cnDB.con.prepareStatement(sql);
            ps.executeUpdate();
            System.out.println("Sua thanh cong");
        } catch (SQLException e) {
            System.out.println("Sua khong thanh cong");
        }
        cnDB.close();
    }

}
