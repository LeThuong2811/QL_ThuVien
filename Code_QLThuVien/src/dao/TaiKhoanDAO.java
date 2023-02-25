/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import pojo.NhanVien;
import pojo.TaiKhoan;

/**
 *
 * @author ADMIN
 */
public class TaiKhoanDAO {

    KetNoiSQL kn = new KetNoiSQL();

    public ArrayList<TaiKhoan> listTaiKhoan() {
        ArrayList<TaiKhoan> lstTK = new ArrayList<>();
        try {
            String sql = "select* from LoaiTaiKhoan,TaiKhoan,nhanVien "
                    + " where TaiKhoan.maLoaiTK = LoaiTaiKhoan.maLoaiTK and nhanVien.maNhanVien = TaiKhoan.maNhanVien ";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setUsername(rs.getString("tenTaiKhoan").trim());
                tk.setPassword(rs.getString("matKhau").trim());
                tk.setLoaiTK(rs.getString("maLoaiTK").trim());
                tk.setTenLoaiTK(rs.getString("tenLoaiTK").trim());
                tk.setMaNV(rs.getString("maNhanVien").trim());
                tk.setTenNV(rs.getString("hoTen").trim());
                if (rs.getString("ngaySinh") == null) {
                    tk.setNgaySinh("1975-01-01");
                } else {
                    tk.setNgaySinh(rs.getString("ngaySinh"));
                }
                if (rs.getString("soDienThoai") == null) {
                    tk.setSdt("");
                } else {
                    tk.setSdt(rs.getString("soDienThoai").trim());
                }
                if (rs.getString("gioiTinh") == null) {
                    tk.setGioiTinh("Nam");
                } else {
                    tk.setGioiTinh(rs.getString("gioiTinh").trim());
                }
                if (rs.getString("hinh") == null) {
                    tk.setHinh("");
                } else {
                    tk.setHinh(rs.getString("hinh").trim());
                }
                tk.setLuong(rs.getInt("luong"));
                if (rs.getString("cmnd") == null) {
                    tk.setCmnd("");
                } else {
                    tk.setCmnd(rs.getString("cmnd").trim());
                }
                if (rs.getString("email") == null) {
                    tk.setEmail("");
                } else {
                    tk.setEmail(rs.getString("email").trim());
                }
                if (rs.getString("tinhTrang") == null) {
                    tk.setTinhTrang("");
                } else {
                    tk.setTinhTrang(rs.getString("tinhTrang").trim());
                }
                lstTK.add(tk);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstTK;
    }

    public ArrayList<TaiKhoan> listNhanVien() {
        ArrayList<TaiKhoan> lstTK = new ArrayList<>();
        try {
            String sql = "select* from nhanVien ";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setMaNV(rs.getString("maNhanVien").trim());
                tk.setTenNV(rs.getString("hoTen").trim());
                if (rs.getString("ngaySinh") == null) {
                    tk.setNgaySinh("1975-01-01");
                } else {
                    tk.setNgaySinh(rs.getString("ngaySinh"));
                }
                if (rs.getString("soDienThoai") == null) {
                    tk.setSdt("");
                } else {
                    tk.setSdt(rs.getString("soDienThoai").trim());
                }
                if (rs.getString("gioiTinh") == null) {
                    tk.setGioiTinh("Nam");
                } else {
                    tk.setGioiTinh(rs.getString("gioiTinh").trim());
                }
                if (rs.getString("hinh") == null) {
                    tk.setHinh("");
                } else {
                    tk.setHinh(rs.getString("hinh").trim());
                }
                tk.setLuong(rs.getInt("luong"));
                if (rs.getString("cmnd") == null) {
                    tk.setCmnd("");
                } else {
                    tk.setCmnd(rs.getString("cmnd").trim());
                }
                if (rs.getString("email") == null) {
                    tk.setEmail("");
                } else {
                    tk.setEmail(rs.getString("email").trim());
                }
                if (rs.getString("tinhTrang") == null) {
                    tk.setTinhTrang("");
                } else {
                    tk.setTinhTrang(rs.getString("tinhTrang").trim());
                }
                lstTK.add(tk);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstTK;
    }

    public ArrayList<TaiKhoan> listLoaiTK() {
        ArrayList<TaiKhoan> lstTK = new ArrayList<>();
        try {
            String sql = "select* from LoaiTaiKhoan ";
            kn.open();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setLoaiTK(rs.getString("maLoaiTK").trim());
                tk.setTenLoaiTK(rs.getString("tenLoaiTK").trim());
                lstTK.add(tk);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstTK;
    }

    public boolean doiMatKhauNhanVien(String user, String pass) {
        boolean kq = false;
        String sql = String.format("Update TaiKhoan set matKhau='%s'  where tenTaiKhoan='%s'", pass, user);
        kn.open();
        int n = kn.excuteUpdate(sql);
        if (n >= 1) {
            kq = true;
        }
        kn.close();

        return kq;
    }

    public String layMaNVCuoiCung() {
        ResultSet rs = null;
        String kq = "";
        try {
            String sql = "select MAX(right(maNhanVien,7)) as maNV from nhanVien";
            kn.open();
            rs = kn.executeQuery(sql);
            while (rs.next()) {
                kq = rs.getString("maNV").trim();
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public boolean themNhanVien(TaiKhoan tk) {
        boolean kq = false;
        try {
            String sql = String.format("insert into nhanVien values('%s',N'%s','%s','%s',N'%s',%d,'%s','%s','%s',N'%s')"
                    + "", tk.getMaNV(), tk.getTenNV(), tk.getNgaySinh(), tk.getSdt(), tk.getGioiTinh(), tk.getLuong(), tk.getHinh(), tk.getCmnd(), tk.getEmail(), tk.getTinhTrang());
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

    public boolean capNhatNhanVien(TaiKhoan tk) {
        boolean kq = false;
        try {
            String sql = String.format("update nhanVien set hoTen=N'%s',ngaySinh='%s', "
                    + "soDienThoai='%s',gioiTinh=N'%s',luong=%d,hinh='%s',cmnd='%s',email='%s',tinhTrang=N'%s' "
                    + "where maNhanVien='%s' "
                    + "", tk.getTenNV(), tk.getNgaySinh(), tk.getSdt(), tk.getGioiTinh(), tk.getLuong(), tk.getHinh(), tk.getCmnd(), tk.getEmail(), tk.getTinhTrang(), tk.getMaNV());
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

    public boolean xoaNhanVien(String maNV) {
        boolean kq = false;
        try {
            String sql = String.format("update nhanVien set tinhTrang = N'Đã nghỉ việc' where maNhanVien='%s'", maNV);
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

    //thêm Account pass Default: 123
    public boolean themTaiKhoan(TaiKhoan tk) {
        boolean kq = false;
        try {
            String loaiTK = "nv";
            String sql = String.format("insert into TaiKhoan(tenTaiKhoan,maLoaiTK,maNhanVien) values('%s','%s','%s')"
                    + "", tk.getSdt(), loaiTK, tk.getMaNV());
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

    //thêm Account pass được chỉ định
    public boolean themTaiKhoanPass(TaiKhoan tk) {
        boolean kq = false;
        try {
            String sql = String.format("insert into TaiKhoan values('%s','%s','%s','%s')"
                    + "", tk.getUsername(), tk.getPassword(), tk.getLoaiTK(), tk.getMaNV());
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

    public boolean khoaTaiKhoan(String username) {
        boolean kq = false;
        try {
            String sql = String.format("update TaiKhoan set maLoaiTK= 'lock' "
                    + "where tenTaiKhoan = '%s'", username);
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
    public boolean resetPassword(String username) {
        boolean kq = false;
        try {
            String sql = String.format("update TaiKhoan set matKhau= '202cb962ac59075b964b07152d234b70' "
                    + "where tenTaiKhoan = '%s'", username);
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
    public boolean phanQuyen(String username,String maLoai) {
        boolean kq = false;
        try {
            String sql = String.format("update TaiKhoan set maLoaiTK= '%s' "
                    + "where tenTaiKhoan = '%s'",maLoai, username);
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

}
