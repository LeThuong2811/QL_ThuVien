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
import pojo.NhaXuatBan;
import pojo.Sach;
import pojo.TacGia;
import pojo.TheLoai;

/**
 *
 * @author 84396
 */
public class ThongKeDAO {

    KetNoiSQL kn = new KetNoiSQL();
    ///-----------------------------Sách----------------------------
    public ArrayList<Sach> listSach() {
        ArrayList<Sach> lstSach = new ArrayList<>();
        try {
            String sql = "select ctMuonTra.maSach,tenSach,COUNT(*) as soLuotMuon "
                    + " from ctMuonTra, Sach "
                    + " where ctMuonTra.maSach=Sach.maSach "
                    + " group by ctMuonTra.maSach,tenSach";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                Sach s = new Sach();
                s.setMaS(rs.getString("maSach"));
                s.setTenS(rs.getString("tenSach"));
                s.setSoLuotMuon(rs.getInt("soLuotMuon"));
                lstSach.add(s);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstSach;
    }

    public ArrayList<Sach> listSachThang() {
        ArrayList<Sach> lstSach = new ArrayList<>();
        try {
            String sql = "select ctMuonTra.maSach,tenSach,COUNT(*) as soLuotMuon "
                    + " from ctMuonTra, Sach,muonTra "
                    + " where ctMuonTra.maSach=Sach.maSach and muonTra.maMuonTra=ctMuonTra.maMuonTra "
                    + "and DATEDIFF(MONTH,ngayMuon,ngayTra) = 1 "
                    + " group by ctMuonTra.maSach,tenSach";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                Sach s = new Sach();
                s.setMaS(rs.getString("maSach"));
                s.setTenS(rs.getString("tenSach"));
                s.setSoLuotMuon(rs.getInt("soLuotMuon"));
                lstSach.add(s);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstSach;
    }
    public ArrayList<Sach> listSachTuan() {
        ArrayList<Sach> lstSach = new ArrayList<>();
        try {
            String sql = "select ctMuonTra.maSach,tenSach,COUNT(*) as soLuotMuon "
                    + " from ctMuonTra, Sach,muonTra "
                    + " where ctMuonTra.maSach=Sach.maSach and muonTra.maMuonTra=ctMuonTra.maMuonTra "
                    + "and DATEDIFF(DAY,ngayMuon,ngayTra) <= 7 "
                    + " group by ctMuonTra.maSach,tenSach";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                Sach s = new Sach();
                s.setMaS(rs.getString("maSach"));
                s.setTenS(rs.getString("tenSach"));
                s.setSoLuotMuon(rs.getInt("soLuotMuon"));
                lstSach.add(s);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstSach;
    }
    public ArrayList<Sach> listSachStartEnd(String start ,String end) {
        ArrayList<Sach> lstSach = new ArrayList<>();
        try {
            String sql = String.format("select ctMuonTra.maSach,tenSach,COUNT(*) as soLuotMuon "
                    + " from ctMuonTra, Sach,muonTra "
                    + " where ctMuonTra.maSach=Sach.maSach and muonTra.maMuonTra=ctMuonTra.maMuonTra "
                    + "and CONVERT(nvarchar(30),ngayMuon) >= '%s' and CONVERT(nvarchar(30),ngayTra) <= '%s' "
                    + " group by ctMuonTra.maSach,tenSach", start,end);
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                Sach s = new Sach();
                s.setMaS(rs.getString("maSach"));
                s.setTenS(rs.getString("tenSach"));
                s.setSoLuotMuon(rs.getInt("soLuotMuon"));
                lstSach.add(s);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstSach;
    }
    ///-----------------------------Đọc giả----------------------------
    public ArrayList<DocGia> listDocGia() {
        ArrayList<DocGia> lstDG = new ArrayList<>();
        try {
            String sql = "select docGia.maDocGia,tenDocGia,COUNT(*) as soLuotMuon "
                    + "from ctMuonTra,muonTra,Sach,theThuVien,docGia "
                    + "where ctMuonTra.maSach=Sach.maSach and muonTra.maMuonTra=ctMuonTra.maMuonTra "
                    + "and muonTra.soThe=theThuVien.soThe and docGia.soThe=theThuVien.soThe "
                    + " group by docGia.maDocGia,tenDocGia";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                DocGia dg = new DocGia();
                dg.setMaDG(rs.getString("maDocGia").trim());
                dg.setTenDG(rs.getString("tenDocGia").trim());
                dg.setSoLuotMuon(rs.getInt("soLuotMuon"));
                lstDG.add(dg);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstDG;
    }

    public ArrayList<DocGia> listDocGiaThang() {
        ArrayList<DocGia> lstDG = new ArrayList<>();
        try {
            String sql = "select docGia.maDocGia,tenDocGia,COUNT(*) as soLuotMuon "
                    + "from ctMuonTra,muonTra,Sach,theThuVien,docGia "
                    + "where ctMuonTra.maSach=Sach.maSach and muonTra.maMuonTra=ctMuonTra.maMuonTra "
                    + "and muonTra.soThe=theThuVien.soThe and docGia.soThe=theThuVien.soThe "
                    + "and DATEDIFF(MONTH,ngayMuon,ngayTra) = 1 "
                    + "group by docGia.maDocGia,tenDocGia ";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                DocGia dg = new DocGia();
                dg.setMaDG(rs.getString("maDocGia").trim());
                dg.setTenDG(rs.getString("tenDocGia").trim());
                dg.setSoLuotMuon(rs.getInt("soLuotMuon"));
                lstDG.add(dg);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstDG;
    }
    public ArrayList<DocGia> listDocGiaTuan() {
        ArrayList<DocGia> lstDG = new ArrayList<>();
        try {
            String sql = "select docGia.maDocGia,tenDocGia,COUNT(*) as soLuotMuon "
                    + "from ctMuonTra,muonTra,Sach,theThuVien,docGia "
                    + "where ctMuonTra.maSach=Sach.maSach and muonTra.maMuonTra=ctMuonTra.maMuonTra "
                    + "and muonTra.soThe=theThuVien.soThe and docGia.soThe=theThuVien.soThe "
                    + "and DATEDIFF(DAY,ngayMuon,ngayTra) <= 7 "
                    + " group by docGia.maDocGia,tenDocGia";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                DocGia dg = new DocGia();
                dg.setMaDG(rs.getString("maDocGia").trim());
                dg.setTenDG(rs.getString("tenDocGia").trim());
                dg.setSoLuotMuon(rs.getInt("soLuotMuon"));
                lstDG.add(dg);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstDG;
    }
    public ArrayList<DocGia> listDocStartEnd(String start, String end) {
        ArrayList<DocGia> lstDG = new ArrayList<>();
        try {
            String sql =String.format("select docGia.maDocGia,tenDocGia,COUNT(*) as soLuotMuon "
                    + "from ctMuonTra,muonTra,Sach,theThuVien,docGia "
                    + "where ctMuonTra.maSach=Sach.maSach and muonTra.maMuonTra=ctMuonTra.maMuonTra "
                    + "and muonTra.soThe=theThuVien.soThe and docGia.soThe=theThuVien.soThe "
                    + "and CONVERT(nvarchar(30),ngayMuon) >= '%s' and CONVERT(nvarchar(30),ngayTra) <= '%s' "
                    + " group by docGia.maDocGia,tenDocGia",start,end);
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                DocGia dg = new DocGia();
                dg.setMaDG(rs.getString("maDocGia").trim());
                dg.setTenDG(rs.getString("tenDocGia").trim());
                dg.setSoLuotMuon(rs.getInt("soLuotMuon"));
                lstDG.add(dg);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstDG;
    }
    ///----------------------------NXB----------------------------
    public ArrayList<NhaXuatBan> listNXB() {
        ArrayList<NhaXuatBan> lstNXB = new ArrayList<>();
        try {
            String sql = "select nhaXuatBan.maNXB,tenNXB,COUNT(*) as soLuotMuon "
                    + "from ctMuonTra,Sach,nhaXuatBan "
                    + "where ctMuonTra.maSach=Sach.maSach and Sach.maNXB=nhaXuatBan.maNXB "
                    + "group by nhaXuatBan.maNXB,tenNXB";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                NhaXuatBan nxb = new NhaXuatBan();
                nxb.setMaNXB(rs.getString("maNXB"));
                nxb.setTenNXB(rs.getString("tenNXB"));
                nxb.setSoLuotMuon(rs.getInt("soLuotMuon"));
                lstNXB.add(nxb);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstNXB;
    }

    public ArrayList<NhaXuatBan> listNXBThang() {
        ArrayList<NhaXuatBan> lstNXB = new ArrayList<>();
        try {
            String sql = "select nhaXuatBan.maNXB,tenNXB,COUNT(*) as soLuotMuon "
                    + "from ctMuonTra,Sach,nhaXuatBan,muonTra "
                    + "where ctMuonTra.maSach=Sach.maSach and muonTra.maMuonTra=ctMuonTra.maMuonTra "
                    + "and Sach.maNXB=nhaXuatBan.maNXB and DATEDIFF(MONTH,ngayMuon,ngayTra) = 1 "
                    + "group by nhaXuatBan.maNXB,tenNXB";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                NhaXuatBan nxb = new NhaXuatBan();
                nxb.setMaNXB(rs.getString("maNXB"));
                nxb.setTenNXB(rs.getString("tenNXB"));
                nxb.setSoLuotMuon(rs.getInt("soLuotMuon"));
                lstNXB.add(nxb);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstNXB;
    }

    public ArrayList<NhaXuatBan> listNXBTuan() {
        ArrayList<NhaXuatBan> lstNXB = new ArrayList<>();
        try {
            String sql = "select nhaXuatBan.maNXB,tenNXB,COUNT(*) as soLuotMuon "
                    + "from ctMuonTra,Sach,nhaXuatBan,muonTra "
                    + "where ctMuonTra.maSach=Sach.maSach and muonTra.maMuonTra=ctMuonTra.maMuonTra "
                    + "and Sach.maNXB=nhaXuatBan.maNXB and DATEDIFF(DAY,ngayMuon,ngayTra) <= 7 "
                    + "group by nhaXuatBan.maNXB,tenNXB";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                NhaXuatBan nxb = new NhaXuatBan();
                nxb.setMaNXB(rs.getString("maNXB"));
                nxb.setTenNXB(rs.getString("tenNXB"));
                nxb.setSoLuotMuon(rs.getInt("soLuotMuon"));
                lstNXB.add(nxb);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstNXB;
    }
    public ArrayList<NhaXuatBan> listNXBStartEnd(String start,String end) {
        ArrayList<NhaXuatBan> lstNXB = new ArrayList<>();
        try {
            String sql = String.format("select nhaXuatBan.maNXB,tenNXB,COUNT(*) as soLuotMuon "
                    + "from ctMuonTra,Sach,nhaXuatBan,muonTra "
                    + "where ctMuonTra.maSach=Sach.maSach and muonTra.maMuonTra=ctMuonTra.maMuonTra "
                    + "and Sach.maNXB=nhaXuatBan.maNXB "
                    + "and CONVERT(nvarchar(30),ngayMuon) >= '%s' and CONVERT(nvarchar(30),ngayTra) <= '%s' "
                    + "group by nhaXuatBan.maNXB,tenNXB",start,end);
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                NhaXuatBan nxb = new NhaXuatBan();
                nxb.setMaNXB(rs.getString("maNXB"));
                nxb.setTenNXB(rs.getString("tenNXB"));
                nxb.setSoLuotMuon(rs.getInt("soLuotMuon"));
                lstNXB.add(nxb);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstNXB;
    }
    ///-----------------------------Tác giả----------------------------
    public ArrayList<TacGia> listTacGia() {
        ArrayList<TacGia> lstTG = new ArrayList<>();
        try {
            String sql =  "select tacGia.maTacGia,tenTacGia,COUNT(*) as soLuotMuon "
                    + "from ctMuonTra,Sach,tacGia "
                    + "where ctMuonTra.maSach=Sach.maSach and Sach.maTacGia=tacGia.maTacGia "
                    + "group by tacGia.maTacGia,tenTacGia";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                TacGia tg = new TacGia();
                tg.setMaTG(rs.getString("maTacGia"));
                tg.setTenTG(rs.getString("tenTacGia"));
                tg.setSoLuotMuon(rs.getInt("soLuotMuon"));
                lstTG.add(tg);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstTG;
    }
    public ArrayList<TacGia> listTacGiaThang() {
        ArrayList<TacGia> lstTG = new ArrayList<>();
        try {
            String sql =  "select tacGia.maTacGia,tenTacGia,COUNT(*) as soLuotMuon "
                    + "from ctMuonTra,Sach,tacGia,muonTra "
                    + "where ctMuonTra.maSach=Sach.maSach and muonTra.maMuonTra=ctMuonTra.maMuonTra " 
                    + "and Sach.maTacGia=tacGia.maTacGia and DATEDIFF(MONTH,ngayMuon,ngayTra) = 1 "
                    + "group by tacGia.maTacGia,tenTacGia";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                TacGia tg = new TacGia();
                tg.setMaTG(rs.getString("maTacGia"));
                tg.setTenTG(rs.getString("tenTacGia"));
                tg.setSoLuotMuon(rs.getInt("soLuotMuon"));
                lstTG.add(tg);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstTG;
    }
    public ArrayList<TacGia> listTacGiaTuan() {
        ArrayList<TacGia> lstTG = new ArrayList<>();
        try {
            String sql =  "select tacGia.maTacGia,tenTacGia,COUNT(*) as soLuotMuon "
                    + "from ctMuonTra,Sach,tacGia,muonTra "
                    + "where ctMuonTra.maSach=Sach.maSach and muonTra.maMuonTra=ctMuonTra.maMuonTra " 
                    + "and Sach.maTacGia=tacGia.maTacGia and DATEDIFF(DAY,ngayMuon,ngayTra) <= 7 "
                    + "group by tacGia.maTacGia,tenTacGia";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                TacGia tg = new TacGia();
                tg.setMaTG(rs.getString("maTacGia"));
                tg.setTenTG(rs.getString("tenTacGia"));
                tg.setSoLuotMuon(rs.getInt("soLuotMuon"));
                lstTG.add(tg);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstTG;
    }
    public ArrayList<TacGia> listTacGiaStartEnd(String start, String end) {
        ArrayList<TacGia> lstTG = new ArrayList<>();
        try {
            String sql = String.format("select tacGia.maTacGia,tenTacGia,COUNT(*) as soLuotMuon "
                    + "from ctMuonTra,Sach,tacGia,muonTra "
                    + "where ctMuonTra.maSach=Sach.maSach and muonTra.maMuonTra=ctMuonTra.maMuonTra " 
                    + "and Sach.maTacGia=tacGia.maTacGia "
                    + "and CONVERT(nvarchar(30),ngayMuon) >= '%s' and CONVERT(nvarchar(30),ngayTra) <= '%s' "
                    + "group by tacGia.maTacGia,tenTacGia",start,end);
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                TacGia tg = new TacGia();
                tg.setMaTG(rs.getString("maTacGia"));
                tg.setTenTG(rs.getString("tenTacGia"));
                tg.setSoLuotMuon(rs.getInt("soLuotMuon"));
                lstTG.add(tg);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstTG;
    }
    ///-----------------------------Thể loại----------------------------
    public ArrayList<TheLoai> listTheLoai() {
        ArrayList<TheLoai> lstTL = new ArrayList<>();
        try {
            String sql = "select theLoai.maTheLoai,tenTheLoai,COUNT(*) as soLuotMuon "
                    + "from ctMuonTra,Sach,theLoai "
                    + "where ctMuonTra.maSach=Sach.maSach and Sach.maTheLoai=theLoai.maTheLoai " 
                    + "group by theLoai.maTheLoai,tenTheLoai ";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                TheLoai tl = new TheLoai();
                tl.setMaTL(rs.getString("maTheLoai"));
                tl.setTenTL(rs.getString("tenTheLoai"));
                tl.setSoLuotMuon(rs.getInt("soLuotMuon"));
                lstTL.add(tl);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstTL;
    }
    public ArrayList<TheLoai> listTheLoaiThang() {
        ArrayList<TheLoai> lstTL = new ArrayList<>();
        try {
            String sql = "select theLoai.maTheLoai,tenTheLoai,COUNT(*) as soLuotMuon "
                    + "from ctMuonTra,Sach,theLoai,muonTra "
                    + "where ctMuonTra.maSach=Sach.maSach and Sach.maTheLoai=theLoai.maTheLoai "
                    + "and muonTra.maMuonTra=ctMuonTra.maMuonTra and DATEDIFF(MONTH,ngayMuon,ngayTra) = 1 " 
                    + "group by theLoai.maTheLoai,tenTheLoai ";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                TheLoai tl = new TheLoai();
                tl.setMaTL(rs.getString("maTheLoai"));
                tl.setTenTL(rs.getString("tenTheLoai"));
                tl.setSoLuotMuon(rs.getInt("soLuotMuon"));
                lstTL.add(tl);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstTL;
    }
    public ArrayList<TheLoai> listTheLoaiTuan() {
        ArrayList<TheLoai> lstTL = new ArrayList<>();
        try {
            String sql = "select theLoai.maTheLoai,tenTheLoai,COUNT(*) as soLuotMuon "
                    + "from ctMuonTra,Sach,theLoai,muonTra "
                    + "where ctMuonTra.maSach=Sach.maSach and Sach.maTheLoai=theLoai.maTheLoai "
                    + "and muonTra.maMuonTra=ctMuonTra.maMuonTra and DATEDIFF(DAY,ngayMuon,ngayTra) <= 7 " 
                    + "group by theLoai.maTheLoai,tenTheLoai ";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                TheLoai tl = new TheLoai();
                tl.setMaTL(rs.getString("maTheLoai"));
                tl.setTenTL(rs.getString("tenTheLoai"));
                tl.setSoLuotMuon(rs.getInt("soLuotMuon"));
                lstTL.add(tl);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstTL;
    }
    public ArrayList<TheLoai> listTheLoaiStartEnd(String start,String end) {
        ArrayList<TheLoai> lstTL = new ArrayList<>();
        try {   
            String sql = String.format("select theLoai.maTheLoai,tenTheLoai,COUNT(*) as soLuotMuon "
                    + "from ctMuonTra,Sach,theLoai,muonTra "
                    + "where ctMuonTra.maSach=Sach.maSach and Sach.maTheLoai=theLoai.maTheLoai "
                    + "and muonTra.maMuonTra=ctMuonTra.maMuonTra "
                    + "and CONVERT(nvarchar(30),ngayMuon) >= '%s' and CONVERT(nvarchar(30),ngayTra) <= '%s' " 
                    + "group by theLoai.maTheLoai,tenTheLoai ",start, end);
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                TheLoai tl = new TheLoai();
                tl.setMaTL(rs.getString("maTheLoai"));
                tl.setTenTL(rs.getString("tenTheLoai"));
                tl.setSoLuotMuon(rs.getInt("soLuotMuon"));
                lstTL.add(tl);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstTL;
    }
}
