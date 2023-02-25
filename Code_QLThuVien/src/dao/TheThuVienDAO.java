/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import pojo.TheThuVien;

/**
 *
 * @author ADMIN
 */
public class TheThuVienDAO {

    KetNoiSQL kn = new KetNoiSQL();
    public static String dateNow = LocalDate.now().toString();
    public ArrayList<TheThuVien> listTheThuVien() {
        ArrayList<TheThuVien> lstTTV = new ArrayList<>();
        try {
            String sql = "select* from theThuVien";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                TheThuVien t = new TheThuVien();
                t.setSoThe(rs.getString("soThe").trim());
                t.setNgayBatDau(rs.getString("ngayBatDau"));
                t.setNgayKetThuc(rs.getString("ngayHetHan"));
                if (rs.getString("ghiChu") == null) {
                    t.setGhiChu("");
                } else {
                    t.setGhiChu(rs.getString("ghiChu"));
                }
                lstTTV.add(t);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstTTV;
    }

    public String layMaTheCuoiCung() {
        ResultSet rs = null;
        String kq = "";
        try {
            String sql = "select MAX(right(soThe,7)) as soThe from theThuVien";
            kn.open();
            rs = kn.executeQuery(sql);
            while (rs.next()) {
                kq = rs.getString("soThe").trim();
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public boolean themTheThuVien(TheThuVien th) {
        boolean kq = false;
        try {
            String sql = String.format("insert into theThuVien values('%s','%s','%s',N'%s')", th.getSoThe(), th.getNgayBatDau(), th.getNgayKetThuc(), th.getGhiChu());
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

    public long soNgayConLai(String dateBD, String dateKT) {
        DateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Date currentDate = new Date();
        Date date1 = null;
        Date date2 = null;
        long getDaysDiff = 0;

        try {
            String startDate = dateBD;
            String endDate = dateKT;

            date1 = simpleDateFormat.parse(startDate);
            date2 = simpleDateFormat.parse(endDate);

            long getDiff = date2.getTime() - date1.getTime();

            getDaysDiff = getDiff / (24 * 60 * 60 * 1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getDaysDiff;
    }

    public String soNgayGiaHan(int ngay, int thang, int nam, int soNgayGiaHan) {
        String date = "";
        // Định dạng thời gian
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c2 = Calendar.getInstance();

        //Gán mốc thời gian 
        c2.set(nam, thang - 1, ngay);
        System.out.println("Ngày ban đầu : " + dateFormat.format(c2.getTime()));
        // Tăng số ngày thêm 
        //-- Sử dụng phương thức add()
        c2.add(Calendar.DATE, soNgayGiaHan);
        date = dateFormat.format(c2.getTime());

        return date;
    }

    public boolean capNhatTheThuVien(TheThuVien ttv) {
        boolean kq = false;
        try {
            String sql = String.format("update theThuVien set ngayBatDau='%s',ngayHetHan='%s', "
                    + "ghiChu=N'%s' where soThe='%s'", ttv.getNgayBatDau(), ttv.getNgayKetThuc(), ttv.getGhiChu(), ttv.getSoThe());
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

    public boolean giaHanTheThuVien(String maThe, int ngay) {
        boolean kq = false;
        try {
            String sql = String.format("update theThuVien set ngayHetHan= DATEADD(month,%d,ngayHetHan) "
                    + "where soThe='%s' ", ngay, maThe);
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
    public boolean kTraHanThe(String soThe)
    {
        ArrayList<TheThuVien> lstTTV = listTheThuVien();
        for (TheThuVien ttv: lstTTV) {
            if(ttv.getSoThe().equals(soThe) && ttv.getNgayKetThuc().compareTo(dateNow)<0)
                return true;
        }
        return false;
    }
}
