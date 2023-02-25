/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class KetNoiSQL {

    public Connection con;
    private ResultSet rs = null;
    private PreparedStatement preparedStatement = null;

    public void open() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KetNoiSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        String connectionURL = "jdbc:sqlserver://LETRONGTHUONG\\SQLEXPRESS:1433;databaseName=ql_ThuVien;user=sa;password=123";
        try {
            con = DriverManager.getConnection(connectionURL);
            if (con != null) {
                System.out.println("Connected Successfully");
            } else {
                System.out.println("Connect failed.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(KetNoiSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close() {
        try {
            this.con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String sql) {
        try {
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public int excuteUpdate(String sql) {
        int n = -1;
        try {
            preparedStatement = con.prepareStatement(sql);
            n = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    public void layCBBTheLoaiSach(Vector vctCBBTheLoai) {
        try {
            String sql = "Select * from theLoai";
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String tenTL = rs.getString("tenTheLoai");
                vctCBBTheLoai.add(tenTL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Select_SearchSach(Vector vctData, String timKiem) {
        try {
            String sql = "select* from Sach,theLoai,tacGia,nhaXuatBan "
                    + "where Sach.maTheLoai=theLoai.maTheLoai and Sach.maNXB=nhaXuatBan.maNXB and Sach.maTacGia=tacGia.maTacGia "
                    + "and ( Sach.maSach like '%'+?+'%' "
                    + "or Sach.tenSach like '%'+?+'%' "
                    + "or CAST(namXuatBan as nvarchar(5)) like '%'+?+'%' "
                    + "or tenTacGia like '%'+?+'%' "
                    + "or tenNXB like '%'+?+'%' "
                    + "or tenTheLoai like '%'+?+'%' ) ";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, timKiem);
            preparedStatement.setString(2, timKiem);
            preparedStatement.setString(3, timKiem);
            preparedStatement.setString(4, timKiem);
            preparedStatement.setString(5, timKiem);
            preparedStatement.setString(6, timKiem);
            rs = preparedStatement.executeQuery();
//            resultset = statement.executeQuery(sql);
            while (rs.next()) {
                String ma = rs.getString("maSach");
                String ten = rs.getString("tenSach");
                String loai = rs.getString("tenTheLoai");
                int namXB = rs.getInt("namXuatBan");
                String tacGia = rs.getString("tenTacGia");
                int soLuong = rs.getInt("soLuong");
                String nhaXB = rs.getString("tenNXB");
                String URL = rs.getString("hinh");
                Vector vctRow = new Vector();
                vctRow.add(ma);
                vctRow.add(ten);
                vctRow.add(loai);
                vctRow.add(String.valueOf(namXB));
                vctRow.add(tacGia);
                vctRow.add(soLuong);
                vctRow.add(nhaXB);
                vctRow.add(URL);
                vctData.add(vctRow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Select_SearchDocGia(Vector vctData, String timKiem) {
        try {
            String sql = "select* from Sach,theLoai,tacGia,nhaXu "
                    + "where Sach.maTheLoai=theLoai.maTheLoai and Sach.maNXB=nhaXuatBan.maNXB and Sach.maTacGia=tacGia.maTacGia "
                    + "and ( Sach.maSach like '%'+?+'%' "
                    + "or Sach.tenSach like '%'+?+'%' "
                    + "or CAST(namXuatBan as nvarchar(5)) like '%'+?+'%' "
                    + "or tenTacGia like '%'+?+'%' "
                    + "or tenNXB like '%'+?+'%' "
                    + "or tenTheLoai like '%'+?+'%' ) ";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, timKiem);
            preparedStatement.setString(2, timKiem);
            preparedStatement.setString(3, timKiem);
            preparedStatement.setString(4, timKiem);
            preparedStatement.setString(5, timKiem);
            preparedStatement.setString(6, timKiem);
            rs = preparedStatement.executeQuery();
//            resultset = statement.executeQuery(sql);
            while (rs.next()) {
                String ma = rs.getString("maSach");
                String ten = rs.getString("tenSach");
                String loai = rs.getString("tenTheLoai");
                int namXB = rs.getInt("namXuatBan");
                String tacGia = rs.getString("tenTacGia");
                int soLuong = rs.getInt("soLuong");
                String nhaXB = rs.getString("tenNXB");
                Vector vctRow = new Vector();
                vctRow.add(ma);
                vctRow.add(ten);
                vctRow.add(loai);
                vctRow.add(String.valueOf(namXB));
                vctRow.add(tacGia);
                vctRow.add(soLuong);
                vctRow.add(nhaXB);
                vctData.add(vctRow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
