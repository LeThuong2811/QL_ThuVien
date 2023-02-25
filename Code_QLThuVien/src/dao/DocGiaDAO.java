/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import pojo.DocGia;
import pojo.Sach;

/**
 *
 * @author ADMIN
 */
public class DocGiaDAO {

    KetNoiSQL kn = new KetNoiSQL();
    public static String dateNow = LocalDate.now().toString();

    public ArrayList<DocGia> listDocGia() {
        ArrayList<DocGia> lstDG = new ArrayList<>();
        try {
            String sql = "select* from docGia,theThuVien where docGia.soThe=theThuVien.soThe";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                DocGia dg = new DocGia();
                dg.setMaDG(rs.getString("maDocGia").trim());
                if (rs.getString("tenDocGia") == null) {
                    dg.setTenDG("");
                } else {
                    dg.setTenDG(rs.getString("tenDocGia").trim());
                }
                if (rs.getString("diaChi") == null) {
                    dg.setDiaChi("");
                } else {
                    dg.setDiaChi(rs.getString("diaChi").trim());
                }
                if (rs.getString("ngaySinh") == null) {
                    dg.setNgaySinh("");
                } else {
                    dg.setNgaySinh(rs.getString("ngaySinh"));
                }
                if (rs.getString("gioiTInh") == null) {
                    dg.setGioiTinh("");
                } else {
                    dg.setGioiTinh(rs.getString("gioiTInh").trim());
                }
                if (rs.getString("Email") == null) {
                    dg.setEmail("");
                } else {
                    dg.setEmail(rs.getString("Email").trim());
                }
                if (rs.getString("soDienThoai") == null) {
                    dg.setSdt("");
                } else {
                    dg.setSdt(rs.getString("soDienThoai").trim());
                }
                if (rs.getString("ngayBatDau") == null) {
                    dg.setNgayBatDau("");
                } else {
                    dg.setNgayBatDau(rs.getString("ngayBatDau").trim());
                }
                if (rs.getString("ngayHetHan") == null) {
                    dg.setNgayHetHan("");
                } else {
                    dg.setNgayHetHan(rs.getString("ngayHetHan").trim());
                }
                if (rs.getString("soThe") == null) {
                    dg.setSoThe("");
                } else {
                    dg.setSoThe(rs.getString("soThe").trim());
                }
                lstDG.add(dg);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstDG;
    }

    public String layMaDocGiaCuoiCung() {
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

    public boolean themDocGia(DocGia dg) {
        boolean kq = false;
        try {
            String sql = String.format("insert into docGia values('%s',N'%s',N'%s','%s',N'%s','%s','%s','%s')", dg.getMaDG(), dg.getTenDG(), dg.getDiaChi(), dg.getNgaySinh(), dg.getGioiTinh(), dg.getEmail(), dg.getSdt(), dg.getSoThe());
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

    public boolean capNhatDocGia(DocGia dg) {
        boolean kq = false;
        try {
            String sql = String.format("update docGia set tenDocGia=N'%s',diaChi=N'%s',ngaySinh='%s',gioiTinh=N'%s',Email='%s',soDienThoai='%s',soThe='%s' where maDocGia='%s'", dg.getTenDG(), dg.getDiaChi(), dg.getNgaySinh(), dg.getGioiTinh(), dg.getEmail(), dg.getSdt(), dg.getSoThe(), dg.getMaDG());
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

    public boolean xoaDocGia(String ma) {
        boolean kq = false;
        try {
            String sql = String.format("delete docGia where maDocGia = '%s'", ma);
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

    public int laySoLuongSachMuon(String maDG) {
        ResultSet rs = null;
        int kq = 0;
        try {
            String sql = String.format("select COUNT(*) as sl "
                    + " from muonTra,ctMuonTra,theThuVien,docGia "
                    + "where muonTra.maMuonTra=ctMuonTra.maMuonTra and muonTra.soThe=theThuVien.soThe and theThuVien.soThe = docGia.soThe and maDocGia = '%s'", maDG);
            kn.open();
            rs = kn.executeQuery(sql);
            while (rs.next()) {
                kq = rs.getInt("sl");
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public ArrayList<Sach> laySachDaMuon(String maDG) {
        ResultSet rs = null;
        ArrayList<Sach> lstS = new ArrayList<>();
        try {
            String sql = String.format("select sach.*,tenTheLoai "
                    + " from muonTra,ctMuonTra,theThuVien,docGia,Sach,theLoai "
                    + " where muonTra.maMuonTra=ctMuonTra.maMuonTra and muonTra.soThe=theThuVien.soThe "
                    + " and theThuVien.soThe = docGia.soThe and Sach.maSach = ctMuonTra.maSach"
                    + " and sach.maTheLoai= theLoai.maTheLoai "
                    + " and maDocGia = '%s'", maDG);
            kn.open();
            rs = kn.executeQuery(sql);
            while (rs.next()) {
                Sach s = new Sach();
                s.setMaS(rs.getString("maSach"));
                s.setTenS(rs.getString("tenSach"));
                s.setTheLoai(rs.getString("tenTheLoai"));
                s.setSoLuong(rs.getInt("soLuong"));
                lstS.add(s);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstS;
    }

    public ArrayList<DocGia> listDGViPham() {
        ResultSet rs = null;
        ArrayList<DocGia> lstDG = new ArrayList<>();
        try {
            String sql = "select docGia.maDocGia,tenDocGia,soDienThoai,tenSach,ngayTra "
                    + "from docGia,theThuVien,ctMuonTra,muonTra,Sach "
                    + "where docGia.soThe=theThuVien.soThe and ctMuonTra.maMuonTra=muonTra.maMuonTra "
                    + "and muonTra.soThe=theThuVien.soThe and ctMuonTra.maSach = Sach.maSach "
                    + "and ngayTra <GETDATE() and daTra = 0";
            kn.open();
            rs = kn.executeQuery(sql);
            while (rs.next()) {
                DocGia dg = new DocGia();
                dg.setMaDG(rs.getString("maDocGia"));
                dg.setTenDG(rs.getString("tenDocGia"));
                dg.setSdt(rs.getString("soDienThoai"));
                dg.setMaSach(rs.getString("tenSach"));
                dg.setNgayHetHan(rs.getString("ngayTra"));
                lstDG.add(dg);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstDG;
    }

    public boolean kTraDGDaHetHanThe(String soThe) {
        ArrayList<DocGia> lstDG = listDocGia();
        for (DocGia dg : lstDG) {
            if (dg.getSoThe().equals(soThe) && dg.getNgayHetHan().compareTo(dateNow) < 0) {
                return true;
            }
        }
        return false;
    }
}
