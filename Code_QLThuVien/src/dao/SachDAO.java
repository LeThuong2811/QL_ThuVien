/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import pojo.DocGia;
import pojo.Sach;

/**
 *
 * @author ADMIN
 */
public class SachDAO {

    KetNoiSQL kn = new KetNoiSQL();

    public ArrayList<Sach> listSach() {
        ArrayList<Sach> lstSach = new ArrayList<>();
        try {
            String sql = "select* from Sach,theLoai,tacGia,nhaXuatBan "
                    + "where Sach.maTheLoai=theLoai.maTheLoai and Sach.maNXB=nhaXuatBan.maNXB and Sach.maTacGia=tacGia.maTacGia";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                Sach s = new Sach();
                if (rs.getString("maSach") != null) {
                    s.setMaS(rs.getString("maSach"));
                }
                if (rs.getString("tenSach") != null) {
                    s.setTenS(rs.getString("tenSach"));
                }
                if (rs.getString("tenTacGia") != null) {
                    s.setTacGia(rs.getString("tenTacGia"));
                }
                if (rs.getString("tenNXB") != null) {
                    s.setNhaXB(rs.getString("tenNXB"));
                }
                if (rs.getString("tenTheLoai") != null) {
                    s.setTheLoai(rs.getString("tenTheLoai"));
                }
                if (rs.getString("namXuatBan") != null) {
                    s.setNamXB(rs.getInt("namXuatBan"));
                }
                if (rs.getString("soLuong") != null) {
                    s.setSoLuong(rs.getInt("soLuong"));
                }
                if (rs.getString("tenNXB") != null) {
                    s.setNhaXB(rs.getString("tenNXB"));
                }
                if (rs.getString("hinh") != null) {
                    s.setHinh(rs.getString("hinh"));
                }
                lstSach.add(s);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstSach;
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

    public boolean themSach(Sach s) {
        boolean kq = false;
        try {
            String sql = String.format("insert into sach values('%s',N'%s',%d,%d,%d,%d,%d,'%s')"
                    + " ", s.getMaS(), s.getTenS(), Integer.parseInt(s.getTacGia()), Integer.parseInt(s.getTheLoai()), Integer.parseInt(s.getNhaXB()), s.getNamXB(), s.getSoLuong(), s.getHinh());
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

    public boolean capNhatSach(Sach s) {
        boolean kq = false;
        try {
            String sql = String.format("update sach set tenSach=N'%s',maTacGia=%d,maTheLoai=%d,maNXB=%d,namXuatBan=%d,soLuong=%d where maSach='%s'", s.getTenS(), Integer.parseInt(s.getTacGia()), Integer.parseInt(s.getTheLoai()), Integer.parseInt(s.getNhaXB()), s.getNamXB(), s.getSoLuong(), s.getMaS());
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

    public boolean xoaSach(String ma) {
        boolean kq = false;
        try {
            String sql = String.format("delete sach where maSach = '%s'", ma);
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

    public ArrayList<DocGia> layDanhSachMuon(String maS) {
        ResultSet rs = null;
        ArrayList<DocGia> lstDG = new ArrayList<>();
        try {
            String sql = String.format("select docGia.* "
                    + "from muonTra,ctMuonTra,theThuVien,docGia "
                    + "where muonTra.maMuonTra=ctMuonTra.maMuonTra and muonTra.soThe=theThuVien.soThe and theThuVien.soThe = docGia.soThe and maSach='%s'", maS);
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

    public boolean kTraSachDaHet(String maSach) {
        ArrayList<Sach> lstSach = listSach();
        for (Sach s : lstSach) {
            if (s.getMaS().equals(maSach) && s.getSoLuong() == 0) {//đã hết
                return true;
            }
        }
        return false;
    }

    //ko đủ trả về số lượng sách
    public int kTraSachDaHetTheoSoLuongMua(String maSach, int slSachMuon) {
        ArrayList<Sach> lstSach = listSach();
        int a = -1;
        for (Sach s : lstSach) {
            a = s.getSoLuong() - slSachMuon;
            if (s.getMaS().equals(maSach) && a < 0) {//đã hết
                return s.getSoLuong();//trả về số lượng tồn
            }
        }
        return -1;
    }

}
