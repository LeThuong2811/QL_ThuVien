/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import pojo.DocGia;
import pojo.MuonTra;
import pojo.Sach;

/**
 *
 * @author ADMIN
 */
public class MuonTraDAO {

    KetNoiSQL kn = new KetNoiSQL();

    public ArrayList<MuonTra> listMuonTra() {
        ArrayList<MuonTra> lstMT = new ArrayList<>();
        try {
            String sql = "select* from muonTra,ctMuonTra,nhanVien "
                    + "where muonTra.maMuonTra=ctMuonTra.maMuonTra and muonTra.maNhanVien=nhanVien.maNhanVien";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                MuonTra mt = new MuonTra();
                mt.setMaMuonTra(rs.getString("maMuonTra"));
                mt.setMaThe(rs.getString("soThe"));
                if (rs.getString("maNhanVien") != null)//Nhân viên
                {
                    mt.setMaNV(rs.getString("maNhanVien"));
                }
                if (rs.getString("hoTen") != null)//Nhân viên
                {
                    mt.setTenNV(rs.getString("hoTen"));
                }
                if (rs.getString("ngayMuon") != null) {
                    mt.setNgayMuon(rs.getString("ngayMuon"));
                }
                if (rs.getString("ngayTra") != null)//Ngay tra
                {
                    mt.setNgayTra(rs.getString("ngayTra"));
                }
                if (rs.getString("maSach") != null)//Ngay tra
                {
                    mt.setMaSach(rs.getString("maSach"));
                }
                if (rs.getString("ghiChu") != null)//ghi chu
                {
                    mt.setGhiChu(rs.getString("ghiChu"));
                }
                if (rs.getString("daTra") != null && rs.getInt("daTra") == 0) {
                    mt.setDaTra("No");
                } else {
                    mt.setDaTra("Yes");
                }
                mt.setSoLuong(rs.getInt("soLuong"));
                lstMT.add(mt);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstMT;
    }

    public ArrayList<MuonTra> listPhieuMuon() {
        ArrayList<MuonTra> lstMT = new ArrayList<>();
        try {
            String sql = "select* from muonTra";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                MuonTra mt = new MuonTra();
                mt.setMaMuonTra(rs.getString("maMuonTra"));
                mt.setMaThe(rs.getString("soThe"));
                if (rs.getString("maNhanVien") == null)//Nhân viên
                {
                    mt.setMaNV("");
                } else {
                    mt.setMaNV(rs.getString("maNhanVien"));
                }

//                mt.setTenNV(rs.getString("hoTen"));
                mt.setNgayMuon(rs.getString("ngayMuon"));
                lstMT.add(mt);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstMT;
    }

    public ArrayList<MuonTra> listCTPhieuMuon(String maPM) {
        ArrayList<MuonTra> lstMT = new ArrayList<>();
        try {
            String sql = String.format("select* from ctMuonTra where maMuonTra='%s'", maPM);
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                MuonTra mt = new MuonTra();
                mt.setMaMuonTra(rs.getString("maMuonTra"));
                mt.setMaSach(rs.getString("maSach"));
                mt.setMaSach(rs.getString("maSach"));
                //Ngay tra
                if (rs.getString("ngayTra") == null) {
                    mt.setNgayTra("");
                } else {
                    mt.setNgayTra(rs.getString("ngayTra"));
                }
                //ghi chu
                if (rs.getString("ghiChu") == null) {
                    mt.setGhiChu("");
                } else {
                    mt.setGhiChu(rs.getString("ghiChu"));
                }
                //Da tra
                if (rs.getInt("daTra") == 0) {
                    mt.setDaTra("No");
                } else {
                    mt.setDaTra("Yes");
                }
                if (rs.getString("soLuong") != null) {
                    mt.setSoLuong(rs.getInt("soLuong"));
                }
                lstMT.add(mt);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstMT;
    }

    public ArrayList<MuonTra> listMuonTraViPham() {
        ArrayList<MuonTra> lstMT = new ArrayList<>();
        try {
            String sql = "select* from muonTra,ctMuonTra,nhanVien "
                    + "where muonTra.maMuonTra=ctMuonTra.maMuonTra and muonTra.maNhanVien=nhanVien.maNhanVien "
                    + " and daTra = '0' and ngayTra < getdate()";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                MuonTra mt = new MuonTra();
                mt.setMaMuonTra(rs.getString("maMuonTra"));
                mt.setMaThe(rs.getString("soThe"));
                if (rs.getString("maNhanVien") != null)//Nhân viên
                {
                    mt.setMaNV(rs.getString("maNhanVien"));
                }
                if (rs.getString("hoTen") != null) {
                    mt.setTenNV(rs.getString("hoTen"));
                }
                if (rs.getString("ngayMuon") != null) {
                    mt.setNgayMuon(rs.getString("ngayMuon"));
                }
                if (rs.getString("ngayTra") != null)//Ngay tra
                {
                    mt.setNgayTra(rs.getString("ngayTra"));
                }
                if (rs.getString("maSach") != null) {
                    mt.setMaSach(rs.getString("maSach"));
                }
                if (rs.getString("ghiChu") != null)//ghi chu
                {
                    mt.setGhiChu(rs.getString("ghiChu"));
                }
                if (rs.getString("daTra") != null && rs.getInt("daTra") != 0) {
                    mt.setDaTra("No");
                } else {
                    mt.setDaTra("Yes");
                }
                mt.setSoLuong(rs.getInt("soLuong"));
                lstMT.add(mt);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstMT;
    }

    public String layMaSachCuoiCung() {
        ResultSet rs = null;
        String kq = "";
        try {
            String sql = "select MAX(right(maSach,7)) as maSach from sach";
            kn.open();
            rs = kn.executeQuery(sql);
            while (rs.next()) {
                kq = rs.getString("maSach").trim();
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public boolean themPhieuMuon(MuonTra mt) {
        boolean kq = false;
        try {
            String sql = String.format("insert into muonTra "
                    + " values('%s','%s','%s','%s')", mt.getMaMuonTra(), mt.getMaThe(), mt.getMaNV(), mt.getNgayMuon());
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

    public boolean themCTPhieuMuon(MuonTra mt) {
        boolean kq = false;
        try {
            String sql = String.format("insert into ctMuonTra(maMuonTra,maSach,ngayTra,ghiChu,soLuong) "
                    + " values('%s','%s','%s','%s',%d)", mt.getMaMuonTra(), mt.getMaSach(), mt.getNgayTra(), mt.getGhiChu(), mt.getSoLuong());
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

    public boolean xoaCTPhieuMuon(String maPhieu, String maSach) {
        boolean kq = false;
        try {
            String sql = String.format("delete from ctMuonTra where maMuonTra='%s' and maSach='%s'", maPhieu, maSach);
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

    public boolean xoaPhieuMuon(String maPhieu) {
        boolean kq = false;
        try {
            //Xóa ct phiếu mượn trước
            String sql = String.format("delete from ctMuonTra where maMuonTra='%s'", maPhieu);
            //Xóa ct phiếu mượn trước
            String sql2 = String.format("delete from muonTra where maMuonTra='%s'", maPhieu);
            kn.open();
            kn.excuteUpdate(sql);
            kn.excuteUpdate(sql2);
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public boolean capNhatCTPhieuMuon2(String maPhieu, String maSach1, String maSach2, int soLuong) {//mã sách 1 thành mã 2
        boolean kq = false;
        try {
            String sql = String.format("Update ctMuonTra set maSach='%s', soLuong=%d "
                    + " where maMuonTra='%s' and maSach='%s'", maSach2, soLuong, maPhieu, maSach1);
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

    public boolean capNhatCTPhieuMuon(String maPhieu, String maSach, int soLuong) {
        boolean kq = false;
        try {
            String sql = String.format("Update ctMuonTra set daTra=1,ngayTra=getdate(),soLuong=%d "
                    + "where maMuonTra='%s' and maSach='%s'", soLuong, maPhieu, maSach);
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

    public boolean capNhatSLTon(String maSach, int soLuong) {
        boolean kq = false;
        try {
            String sql = String.format("Update Sach set soLuong = soLuong + %d "
                    + "where maSach='%s'", soLuong, maSach);
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

    public boolean capNhatSLSachCTPhieuMuon(String maPhieu, String maSach, int sl) {
        boolean kq = false;
        try {
            String sql = String.format("Update ctMuonTra set soLuong=%d "
                    + "where maMuonTra='%s' and maSach='%s'", sl, maPhieu, maSach);
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

    public ArrayList<Sach> listSach(String maMT, String maSach) {
        ResultSet rs = null;
        ArrayList<Sach> lstS = new ArrayList<>();
        try {
            String sql = String.format("select sach.*,tenTheLoai "
                    + " from muonTra,ctMuonTra,theThuVien,docGia,Sach,theLoai "
                    + " where muonTra.maMuonTra=ctMuonTra.maMuonTra and muonTra.soThe=theThuVien.soThe "
                    + " and theThuVien.soThe = docGia.soThe and Sach.maSach = ctMuonTra.maSach"
                    + " and sach.maTheLoai= theLoai.maTheLoai "
                    + " and muonTra.maMuonTra='%s'  and Sach.maSach='%s'", maMT, maSach);
            kn.open();
            rs = kn.executeQuery(sql);
            while (rs.next()) {
                Sach s = new Sach();
                if (rs.getString("maSach") != null) {
                    s.setMaS(rs.getString("maSach"));
                }
                if (rs.getString("tenSach") != null) {
                    s.setTenS(rs.getString("tenSach"));
                }
                if (rs.getString("tenTheLoai") != null) {
                    s.setTheLoai(rs.getString("tenTheLoai"));
                }
                if (rs.getString("soLuong") != null) {
                    s.setSoLuong(rs.getInt("soLuong"));
                }
                lstS.add(s);
            }
            kn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstS;
    }

    public ArrayList<DocGia> listDG(String maMT, String maSach) {
        ResultSet rs = null;
        ArrayList<DocGia> lstDG = new ArrayList<>();
        try {
            String sql = String.format("select docGia.* "
                    + " from muonTra,ctMuonTra,theThuVien,docGia,Sach,theLoai "
                    + " where muonTra.maMuonTra=ctMuonTra.maMuonTra and muonTra.soThe=theThuVien.soThe "
                    + " and theThuVien.soThe = docGia.soThe and Sach.maSach = ctMuonTra.maSach"
                    + " and sach.maTheLoai= theLoai.maTheLoai "
                    + " and muonTra.maMuonTra='%s'  and Sach.maSach='%s'", maMT, maSach);
            kn.open();
            rs = kn.executeQuery(sql);
            while (rs.next()) {
                DocGia dg = new DocGia();
                if (rs.getString("maDocGia") != null) {
                    dg.setMaDG(rs.getString("maDocGia").trim());
                }
                if (rs.getString("tenDocGia") != null) {
                    dg.setTenDG(rs.getString("tenDocGia").trim());
                }
                if (rs.getString("diaChi") != null) {
                    dg.setDiaChi(rs.getString("diaChi").trim());
                }
                if (rs.getString("ngaySinh") != null) {
                    dg.setNgaySinh(rs.getString("ngaySinh"));
                }
                if (rs.getString("gioiTInh") != null) {
                    dg.setGioiTinh(rs.getString("gioiTInh").trim());
                }
                if (rs.getString("Email") != null) {
                    dg.setEmail(rs.getString("Email").trim());
                }
                if (rs.getString("soDienThoai") != null) {
                    dg.setSdt(rs.getString("soDienThoai").trim());
                }
                if (rs.getString("soThe") != null) {
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

    public String layMaMuonTraCuoiCung() {
        ResultSet rs = null;
        String kq = "";
        try {
            String sql = "select MAX(right(maMuonTra,7)) as ma from muonTra";
            kn.open();
            rs = kn.executeQuery(sql);
            while (rs.next()) {
                kq = rs.getString("ma").trim();
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

}
