/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.DocGiaDAO;
import dao.HamXuLyChung;
import dao.MuonTraDAO;
import dao.SachDAO;
import dao.TaiKhoanDAO;
import dao.TheLoaiDAO;
import dao.TheThuVienDAO;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import javax.print.DocFlavor;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pojo.DocGia;
import pojo.ImageThongBao;
import pojo.MuonTra;
import pojo.Sach;
import pojo.SoNguyen;
import pojo.TaiKhoan;
import pojo.TheThuVien;
import sun.swing.table.DefaultTableCellHeaderRenderer;

/**
 *
 * @author ADMIN
 */
public class frm_MuonTra extends javax.swing.JInternalFrame {

    /**
     * Creates new form frm_MuonTra
     */
    //Bảng đọc giả
    Vector vctHeadDG = new Vector();
    Vector vctDataDG = new Vector();
    //Bảng Sách
    Vector vctHeadS = new Vector();
    Vector vctDataS = new Vector();
    //Bảng Mượn trả
    Vector vctHeadMT = new Vector();
    Vector vctDataMT = new Vector();
    //Bảng danh sách Đăng ký mượn trả
    Vector vctHeadDSMT = new Vector();
    Vector vctDataDSMT = new Vector();
    //Panel Thêm phiếu mượn
    Vector vctHeadPhieuMuon = new Vector();//Phiếu mượn
    Vector vctDataPhieuMuon = new Vector();
    Vector vctHeadCTPhieuMuon = new Vector();//Chi tiết Phiếu mượn
    Vector vctDataCTPhieuMuon = new Vector();

    //vector cho combobox
    Vector vctCBBMaNV = new Vector();
    Vector vctCBBNgayMuon = new Vector();
    Vector vctCBBThangMuon = new Vector();
    Vector vctCBBNamMuon = new Vector();
    Vector vctCBBNgayTra = new Vector();
    Vector vctCBBThangTra = new Vector();
    Vector vctCBBNamTra = new Vector();
    Vector vctCBBSoThe = new Vector();
    Vector vctCBBMaSach = new Vector();

    //
    Vector vctHeadTTV = new Vector();
    Vector vctDataTTV = new Vector();
    Vector vctCBBNgayDK = new Vector();
    Vector vctCBBThangDK = new Vector();
    Vector vctCBBNamDK = new Vector();

    Vector vctCBBNgayHH = new Vector();
    Vector vctCBBThangHH = new Vector();
    Vector vctCBBNamHH = new Vector();
    DocGiaDAO dgDAO = new DocGiaDAO();
    SachDAO sDAO = new SachDAO();
    MuonTraDAO mtDAO = new MuonTraDAO();
    TaiKhoanDAO tkDAO = new TaiKhoanDAO();
    HamXuLyChung hxl = new HamXuLyChung();
    TheThuVienDAO ttvDAO = new TheThuVienDAO();
    DefaultTableModel dfm = null;
    DefaultTableCellHeaderRenderer center = new DefaultTableCellHeaderRenderer();
    ImageThongBao imgTB = new ImageThongBao();

    public static String dateNow = LocalDate.now().toString();
    public static String maNhanVien;

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    void chonMaNVHienHanh(String maNV) {
        int vtMaNV = hxl.timViTriVctcbb(vctCBBMaNV, maNV);
        if (vtMaNV != -1) {
            cbbMaNV.setSelectedIndex(vtMaNV);
        }

    }

    public frm_MuonTra() {
        initComponents();
        this.setSize(1400, 750);
        this.setTitle("Quản lý mượn trả");
        ButtonGroup btngr1 = new ButtonGroup();
//        btngr1.add(rbtnTuNgay);
//        btngr1.add(rbtnTheoThang);
        showDocGia();
//        tbDSMuon.setModel(new DefaultTableModel(vctDataDG, vctHeadDG));
        showSach();
        showMuonTra();
        //--------Panel Them phieu muon-----------
        hideChiTietPhieuMuon();
        //Phiếu mượn
        showPhieuMuon(vctHeadPhieuMuon, vctDataPhieuMuon);
        tbPhieuMuon.setModel(new DefaultTableModel(vctDataPhieuMuon, vctHeadPhieuMuon));
        //Chi tiết phiếu mượn
        showCTPhieuMuon();

        txtMaPhieuMuon.setEnabled(false);
        cbbNgayMuon.setEnabled(false);
        cbbThangMuon.setEnabled(false);
        cbbNamMuon.setEnabled(false);
        cbbMaNV.setEnabled(false);

        themCBBPhieuMuon();

        ///Hiển thị thẻ thư viện
        hienThiTheThuVien();
        txtMaThe.setEnabled(false);
        layCBBDMY();
        hideCBB();
        hideGiaHan();

    }

    void themCBBPhieuMuon() {
        vctCBBMaNV.clear();
        vctCBBNgayMuon.clear();
        vctCBBThangMuon.clear();
        vctCBBNamMuon.clear();
        vctCBBNgayTra.clear();
        vctCBBThangTra.clear();
        vctCBBNamTra.clear();
        vctCBBSoThe.clear();
        vctCBBMaSach.clear();

        //Lấy combobox mã nhân viên
        ArrayList<TaiKhoan> lstTK = tkDAO.listTaiKhoan();
        for (TaiKhoan taiKhoan : lstTK) {
            vctCBBMaNV.add(taiKhoan.getMaNV());
        }
        cbbMaNV.setModel(new DefaultComboBoxModel(vctCBBMaNV));
        //Lấy combobox ngày mượn
        hxl.layNgayThangNam(vctCBBNgayMuon, vctCBBThangMuon, vctCBBNamMuon);
        cbbNgayMuon.setModel(new DefaultComboBoxModel(vctCBBNgayMuon));
        cbbThangMuon.setModel(new DefaultComboBoxModel(vctCBBThangMuon));
        cbbNamMuon.setModel(new DefaultComboBoxModel(vctCBBNamMuon));
        //Lấy combobox ngày trả
        hxl.layNgayThangNam(vctCBBNgayTra, vctCBBThangTra, vctCBBNamTra);
        cbbNgayTra.setModel(new DefaultComboBoxModel(vctCBBNgayTra));
        cbbThangTra.setModel(new DefaultComboBoxModel(vctCBBThangTra));
        cbbNamTra.setModel(new DefaultComboBoxModel(vctCBBNamTra));

        //Lấy combobox số thẻ
        ArrayList<TheThuVien> lstTTV = ttvDAO.listTheThuVien();
        for (TheThuVien theThuVien : lstTTV) {
            vctCBBSoThe.add(theThuVien.getSoThe());
        }
        cbbSoThe.setModel(new DefaultComboBoxModel(vctCBBSoThe));
        //Lấy combobox mã sách
        ArrayList<Sach> lstS = sDAO.listSach();
        for (Sach sach : lstS) {
            vctCBBMaSach.add(sach.getMaS());
        }
        cbbMaSach.setModel(new DefaultComboBoxModel(vctCBBMaSach));

    }

    void showSach() {
        dfm = (DefaultTableModel) tbSach.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHeadS.clear();
        vctHeadS.add("Mã sách");
        vctHeadS.add("Tên sách");
        vctHeadS.add("Thể loại");
        vctHeadS.add("Số lượng");

        //add từng dòng của bảng
        ArrayList<Sach> lstSach = sDAO.listSach();
        for (Sach s : lstSach) {
            Vector vctRow = new Vector();
            vctRow.add(s.getMaS());//Cột 1
            vctRow.add(s.getTenS());//Cột 2
            vctRow.add(s.getTheLoai());//Cột 3
            vctRow.add(s.getSoLuong());//Cột 4
            vctDataS.add(vctRow);//dòng 1 
        }
        tbSach.setModel(new DefaultTableModel(vctDataS, vctHeadS));
        setSizeTableSach();
    }

    void setSizeTableSach() {
        int[] lstSize = {110, 330, 150, 70};
        tbSach.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < 4; i++) {
            tbSach.getColumnModel().getColumn(i).setPreferredWidth(lstSize[i]);
        }
    }

    void showMuonTra() {

        dfm = (DefaultTableModel) tbMuonTra.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHeadMT.clear();
        vctDataMT.clear();
        vctHeadMT.add("Mã #");
        vctHeadMT.add("Mã thẻ");
        vctHeadMT.add("Nhân viên");
        vctHeadMT.add("Ngày mượn");
        vctHeadMT.add("Ngày trả");
        vctHeadMT.add("Mã sách");
        vctHeadMT.add("Tên sách");
        vctHeadMT.add("Số lượng");
        vctHeadMT.add("Đã trả");
        vctHeadMT.add("Ghi chú");
        //add từng dòng của bảng
        ArrayList<MuonTra> lstMT = mtDAO.listMuonTra();
        ArrayList<Sach> lstS = sDAO.listSach();
        for (MuonTra mt : lstMT) {

            Vector vctRow = new Vector();//Chứa dữ liệu cột
            vctRow.add(mt.getMaMuonTra().trim());//Cột 1
            vctRow.add(mt.getMaThe().trim());//Cột 2
            vctRow.add(mt.getTenNV().trim());//Cột 3
            vctRow.add(mt.getNgayMuon().trim());//Cột 4
            vctRow.add(mt.getNgayTra().trim());//Cột 5
            vctRow.add(mt.getMaSach().trim());//Cột 6
            //lấy tên sách thông qua listSach
            for (Sach sach : lstS) {
                if (sach.getMaS().equals(mt.getMaSach())) {
                    vctRow.add(sach.getTenS());//Cột 7
                    break;
                }
            }
            vctRow.add(mt.getSoLuong());//Cột 8
            //nếu ngày trả chưa có thì ghi chú là chưa trả
            vctRow.add(mt.getDaTra());//Cột 9

            //Add cột ghi chú; quá Hạn hay ko
            if (mt.getDaTra().equals("No") && hxl.ktraNgay(dateNow, mt.getNgayTra()) == false) {
                vctRow.add("Quá hạn");//Cột 9
            } else if (mt.getDaTra().equals("No") && hxl.ktraNgay(dateNow, mt.getNgayTra()) == true) {
                vctRow.add("Đang mượn...");
            } else {
                vctRow.add("Đã trả.");//Cot 10
            }

            vctDataMT.add(vctRow);//thêm Dòng vctRow gồm 4 cột
        }
        tbMuonTra.setModel(new DefaultTableModel(vctDataMT, vctHeadMT));
        setSizeTableMT();
    }

    void setSizeTableMT() {
//        tbMuonTra.setFont(new Font("Times New Roman", Font.PLAIN, 20));//Set font size cho chu
        int[] lstSize = {110, 110, 200, 120, 120, 110, 310, 70, 60, 130};
        tbMuonTra.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < 10; i++) {
            tbMuonTra.getColumnModel().getColumn(i).setPreferredWidth(lstSize[i]);
        }
    }

    void showPhieuMuon(Vector head, Vector data) {

        dfm = (DefaultTableModel) tbPhieuMuon.getModel();
        dfm.setRowCount(0);
        //Tên cột
        head.clear();
        head.add("Mã#");
        head.add("Mã thẻ");
        head.add("Mã nhân viên");
        head.add("Ngày mượn");
        //add từng dòng của bảng
        ArrayList<MuonTra> lstMT = mtDAO.listPhieuMuon();
        for (MuonTra mt : lstMT) {
            Vector vctRow = new Vector();//Chứa dữ liệu cột
            vctRow.add(mt.getMaMuonTra().trim());//Cột 1
            vctRow.add(mt.getMaThe().trim());//Cột 2
            vctRow.add(mt.getMaNV().trim());//Cột 3
            vctRow.add(mt.getNgayMuon().trim());//Cột 4
            data.add(vctRow);//thêm Dòng vctRow gồm 4 cột
        }
        hideBtnThemPhieu();
    }

    void showCTPhieuMuon() {

        dfm = (DefaultTableModel) tbCTPhieuMuon.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHeadCTPhieuMuon.clear();
        vctHeadCTPhieuMuon.add("Mã#");
        vctHeadCTPhieuMuon.add("Mã sách");
        vctHeadCTPhieuMuon.add("Ngày trả");
        vctHeadCTPhieuMuon.add("Số lượng");
        vctHeadCTPhieuMuon.add("Đã trả");
        vctHeadCTPhieuMuon.add("Ghi chú");
        //add từng dòng của bảng
        ArrayList<MuonTra> lstMT = mtDAO.listMuonTra();
        for (MuonTra mt : lstMT) {
            Vector vctRow = new Vector();//Chứa dữ liệu cột
            vctRow.add(mt.getMaMuonTra().trim());//Cột 1
            vctRow.add(mt.getMaSach().trim());//Cột 2
            vctRow.add(mt.getNgayTra().trim());//Cột 3
            vctRow.add(mt.getSoLuong());//Cột 4
            vctRow.add(mt.getDaTra());//Cột 5
            //Add cột ghi chú; quá Hạn hay ko
            if (mt.getDaTra().equals("No") && hxl.ktraNgay(dateNow, mt.getNgayTra()) == false) {
                vctRow.add("Quá hạn");//Cột 6
            } else if (mt.getDaTra().equals("No") && hxl.ktraNgay(dateNow, mt.getNgayTra()) == true) {
                vctRow.add("Đang mượn...");
            } else {
                vctRow.add("Đã trả.");
            }
            vctDataCTPhieuMuon.add(vctRow);//thêm Dòng vctRow gồm 6 cột
        }
        tbCTPhieuMuon.setModel(new DefaultTableModel(vctDataCTPhieuMuon, vctHeadCTPhieuMuon));
        setSizeTableCTMT();
    }

    void setSizeTableCTMT() {
        int[] lstSize = {110, 100, 110, 70, 50, 140};
        tbCTPhieuMuon.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < 6; i++) {
            tbCTPhieuMuon.getColumnModel().getColumn(i).setPreferredWidth(lstSize[i]);
        }
    }

    void setSizeTableCTPM() {
//        tbMuonTra.setFont(new Font("Times New Roman", Font.PLAIN, 20));//Set font size cho chu
        int[] lstSize = {110, 110, 200, 120, 120, 110, 280, 70, 60, 130};
        tbMuonTra.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < 10; i++) {
            tbMuonTra.getColumnModel().getColumn(i).setPreferredWidth(lstSize[i]);
        }
    }

    void showDocGia() {
        dfm = (DefaultTableModel) tbDocGia.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHeadDG.clear();
        vctDataDG.clear();
        vctHeadDG.add("Mã đọc giả");
        vctHeadDG.add("Tên đọc giả");
        vctHeadDG.add("Giới tính");
        vctHeadDG.add("Số điện thoại");

        //add từng dòng của bảng
        ArrayList<DocGia> lstDG = dgDAO.listDocGia();
        for (DocGia dg : lstDG) {
            Vector vctRow = new Vector();//Chứa dữ liệu cột
            vctRow.add(dg.getMaDG());//Cột 1
            vctRow.add(dg.getTenDG());//Cột 2
            vctRow.add(dg.getGioiTinh());//Cột 3
            vctRow.add(dg.getSdt());//Cột 4
            vctDataDG.add(vctRow);//thêm Dòng vctRow gồm 4 cột
        }
        tbDocGia.setModel(new DefaultTableModel(vctDataDG, vctHeadDG));
        setSizeTableDG();

    }

    void setSizeTableDG() {
        int[] lstSize = {120, 250, 70, 120};
        tbDocGia.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 4; i++) {
            tbDocGia.getColumnModel().getColumn(i).setPreferredWidth(lstSize[i]);
        }
    }

    void hidePhieuMuon() {
        cbbNgayMuon.setEnabled(false);
        cbbThangMuon.setEnabled(false);
        cbbNamMuon.setEnabled(false);
        cbbSoThe.setEnabled(false);

        btnTaoMa.setEnabled(false);
        btnNgayHienTai.setEnabled(false);
        btnFindSoThe.setEnabled(false);
    }

    void showPhieuMuon() {
        btnTaoMa.setEnabled(true);
        cbbNgayMuon.setEnabled(true);
        cbbThangMuon.setEnabled(true);
        cbbNamMuon.setEnabled(true);
        cbbSoThe.setEnabled(true);
        btnNgayHienTai.setEnabled(true);
        btnFindSoThe.setEnabled(true);
    }

    void hideChiTietPhieuMuon() {
        txtGhiChu.setEnabled(false);
        cbbNgayTra.setEnabled(false);
        cbbThangTra.setEnabled(false);
        cbbNamTra.setEnabled(false);
        cbbMaSach.setEnabled(false);
        btnComplete.setEnabled(false);
        btnLuuPhieu.setEnabled(false);
        btnFindMaSach.setEnabled(false);
        txtFindMaSach.setEnabled(false);
        txtSoLuongInsert.setEnabled(false);

    }

    void showChiTietPhieuMuon() {
        txtGhiChu.setEnabled(true);
        cbbNgayTra.setEnabled(true);
        cbbThangTra.setEnabled(true);
        cbbNamTra.setEnabled(true);
        cbbMaSach.setEnabled(true);
        btnComplete.setEnabled(true);
        btnLuuPhieu.setEnabled(true);
        btnFindMaSach.setEnabled(true);
        txtFindMaSach.setEnabled(true);
        txtSoLuongInsert.setEnabled(true);
    }

    void emptyTXTThe() {
        txtMaThe.setText("");
        txtGhiChuTTV.setText("");
        txtSoThangGiaHan.setText("");
        txtHanThe.setText("");
    }

    void hideBtnThemPhieu() {
        btnXoaCTP.setEnabled(false);
        btnCapNhatCTPhieu.setEnabled(false);
        btnDaTra.setEnabled(false);
//        btnXoaPhieuMuon.setEnabled(false);
    }

    void showBtnThemPhieu() {
        btnXoaCTP.setEnabled(true);
        btnCapNhatCTPhieu.setEnabled(true);
        btnDaTra.setEnabled(true);
//        btnXoaPhieuMuon.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        txtSearchDocGia = new javax.swing.JTextField();
        btnSearchDG = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbDocGia = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        txtDangMuon = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btnXoaSearchDG = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        txtSearchSach = new javax.swing.JTextField();
        btnSearchSach = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbSach = new javax.swing.JTable();
        btnXoaSearchS = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbMuonTra = new javax.swing.JTable();
        txtSearchPhieuMuon = new javax.swing.JTextField();
        btnSearchMT = new javax.swing.JButton();
        btnXoaSearchMT = new javax.swing.JButton();
        btnViPham = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbCTPhieuMuon = new javax.swing.JTable();
        txtSearchPM = new javax.swing.JTextField();
        txtFindPhieuMuon = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbPhieuMuon = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        btnResetCTP = new javax.swing.JButton();
        btnXoaSearch = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        cbbMaNV = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cbbNgayMuon = new javax.swing.JComboBox<>();
        cbbThangMuon = new javax.swing.JComboBox<>();
        cbbNamMuon = new javax.swing.JComboBox<>();
        txtMaPhieuMuon = new javax.swing.JTextField();
        pn_ctPhieuMuon = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        cbbMaSach = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        cbbNgayTra = new javax.swing.JComboBox<>();
        cbbThangTra = new javax.swing.JComboBox<>();
        cbbNamTra = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        txtGhiChu = new javax.swing.JTextField();
        btnLuuPhieu = new javax.swing.JButton();
        btnComplete = new javax.swing.JButton();
        lbTBThemCT = new javax.swing.JLabel();
        txtFindMaSach = new javax.swing.JTextField();
        btnFindMaSach = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        txtSoLuongInsert = new javax.swing.JTextField();
        lbTBSLInsert = new javax.swing.JLabel();
        btnThemPhieu = new javax.swing.JButton();
        lb_tb = new javax.swing.JLabel();
        btnTaoMa = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        cbbSoThe = new javax.swing.JComboBox<>();
        btnNgayHienTai = new javax.swing.JButton();
        txtFindSoThe = new javax.swing.JTextField();
        btnFindSoThe = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        btnDaTra = new javax.swing.JButton();
        btnXoaCTP = new javax.swing.JButton();
        btnCapNhatCTPhieu = new javax.swing.JButton();
        txtMaSachUpd = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtSoLuongUpd = new javax.swing.JTextField();
        lbTBSLUP = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        cbbNgayDK = new javax.swing.JComboBox<>();
        cbbThangDK = new javax.swing.JComboBox<>();
        cbbNamDK = new javax.swing.JComboBox<>();
        txtMaThe = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        cbbNgayHH = new javax.swing.JComboBox<>();
        cbbThangHH = new javax.swing.JComboBox<>();
        cbbNamHH = new javax.swing.JComboBox<>();
        txtGhiChuTTV = new javax.swing.JTextField();
        btnLayMaSachTuDong = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbTheThuVien = new javax.swing.JTable();
        jPanel27 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnGiaHan = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtSoThangGiaHan = new javax.swing.JTextField();
        txtHanThe = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        lbTBFiaHan = new javax.swing.JLabel();
        txtSearchTTV = new javax.swing.JTextField();
        btnXoaSearchTTV = new javax.swing.JButton();
        btnSearchTTV = new javax.swing.JButton();
        btnTheHetHan = new javax.swing.JButton();

        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.SE_RESIZE_CURSOR));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách đăng ký mượn/trả:", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        txtSearchDocGia.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        btnSearchDG.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnSearchDG.setText("Tìm kiếm");
        btnSearchDG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchDGMouseClicked(evt);
            }
        });
        btnSearchDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchDGActionPerformed(evt);
            }
        });

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setToolTipText("");
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tbDocGia.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tbDocGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbDocGia.setRowHeight(30);
        tbDocGia.setRowMargin(7);
        tbDocGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDocGiaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbDocGia);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel15.setText("Đang mượn:");

        txtDangMuon.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel16.setText("quyển");

        btnXoaSearchDG.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnXoaSearchDG.setText("X");
        btnXoaSearchDG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaSearchDGMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDangMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(txtSearchDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaSearchDG, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(btnSearchDG)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchDG)
                    .addComponent(btnXoaSearchDG))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtDangMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(38, 38, 38))
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sách", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        txtSearchSach.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        btnSearchSach.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnSearchSach.setText("Tìm kiếm");
        btnSearchSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchSachMouseClicked(evt);
            }
        });

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tbSach.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tbSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbSach.setRowHeight(30);
        tbSach.setRowMargin(7);
        tbSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSachMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbSach);

        btnXoaSearchS.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnXoaSearchS.setText("X");
        btnXoaSearchS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaSearchSMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(0, 68, Short.MAX_VALUE)
                        .addComponent(txtSearchSach, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaSearchS)
                        .addGap(1, 1, 1)
                        .addComponent(btnSearchSach))
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchSach)
                    .addComponent(btnXoaSearchS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mượn trả", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        tbMuonTra.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tbMuonTra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbMuonTra.setRowHeight(30);
        tbMuonTra.setRowMargin(7);
        tbMuonTra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMuonTraMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbMuonTra);

        txtSearchPhieuMuon.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        btnSearchMT.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnSearchMT.setText("Tìm kiếm");
        btnSearchMT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMTMouseClicked(evt);
            }
        });

        btnXoaSearchMT.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnXoaSearchMT.setText("X");
        btnXoaSearchMT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaSearchMTMouseClicked(evt);
            }
        });

        btnViPham.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnViPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconWarning.png"))); // NOI18N
        btnViPham.setText("Vi phạm");
        btnViPham.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnViPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnViPhamMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSearchPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaSearchMT)
                        .addGap(2, 2, 2)
                        .addComponent(btnSearchMT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnViPham)))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchMT)
                    .addComponent(btnXoaSearchMT)
                    .addComponent(btnViPham, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tra cứu", jPanel16);

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mượn trả", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        tbCTPhieuMuon.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tbCTPhieuMuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbCTPhieuMuon.setRowHeight(30);
        tbCTPhieuMuon.setRowMargin(7);
        tbCTPhieuMuon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCTPhieuMuonMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tbCTPhieuMuon);

        txtSearchPM.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        txtFindPhieuMuon.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtFindPhieuMuon.setText("Tìm kiếm");
        txtFindPhieuMuon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFindPhieuMuonMouseClicked(evt);
            }
        });

        tbPhieuMuon.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tbPhieuMuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbPhieuMuon.setRowHeight(30);
        tbPhieuMuon.setRowMargin(7);
        tbPhieuMuon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPhieuMuonMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tbPhieuMuon);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel17.setText("Chi tiết phiếu mượn:");

        btnResetCTP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconReset3.png"))); // NOI18N
        btnResetCTP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnResetCTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetCTPActionPerformed(evt);
            }
        });

        btnXoaSearch.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnXoaSearch.setText("X");
        btnXoaSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaSearchMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnResetCTP, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel17)
                        .addGroup(jPanel21Layout.createSequentialGroup()
                            .addComponent(txtSearchPM, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnXoaSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(txtFindPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                        .addComponent(jScrollPane8)))
                .addGap(27, 27, 27))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFindPhieuMuon)
                    .addComponent(btnXoaSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnResetCTP))
        );

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        cbbMaNV.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cbbMaNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel19.setText("Mã nhân viên:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel20.setText("Ngày mượn:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel18.setText("Mã phiếu:");

        cbbNgayMuon.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cbbNgayMuon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbThangMuon.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cbbThangMuon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbNamMuon.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cbbNamMuon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtMaPhieuMuon.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        pn_ctPhieuMuon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thêm chi tiết phiếu mượn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Mã sách");

        cbbMaSach.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cbbMaSach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Ngày trả");

        cbbNgayTra.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cbbNgayTra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbThangTra.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cbbThangTra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbNamTra.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cbbNamTra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Ghi chú:");

        txtGhiChu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        btnLuuPhieu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLuuPhieu.setText("Lưu");
        btnLuuPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuPhieuActionPerformed(evt);
            }
        });

        btnComplete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnComplete.setText("Xong");
        btnComplete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompleteActionPerformed(evt);
            }
        });

        lbTBThemCT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbTBThemCT.setForeground(new java.awt.Color(51, 204, 0));

        txtFindMaSach.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        btnFindMaSach.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnFindMaSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Zoom-icon.png"))); // NOI18N
        btnFindMaSach.setText("Find");
        btnFindMaSach.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnFindMaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindMaSachActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel27.setText("Số lượng");

        txtSoLuongInsert.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtSoLuongInsert.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoLuongInsertKeyReleased(evt);
            }
        });

        lbTBSLInsert.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbTBSLInsert.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout pn_ctPhieuMuonLayout = new javax.swing.GroupLayout(pn_ctPhieuMuon);
        pn_ctPhieuMuon.setLayout(pn_ctPhieuMuonLayout);
        pn_ctPhieuMuonLayout.setHorizontalGroup(
            pn_ctPhieuMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_ctPhieuMuonLayout.createSequentialGroup()
                .addGroup(pn_ctPhieuMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pn_ctPhieuMuonLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(pn_ctPhieuMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_ctPhieuMuonLayout.createSequentialGroup()
                                .addComponent(btnFindMaSach)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pn_ctPhieuMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbbMaSach, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(pn_ctPhieuMuonLayout.createSequentialGroup()
                                        .addComponent(cbbNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbbThangTra, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbbNamTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtFindMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_ctPhieuMuonLayout.createSequentialGroup()
                                .addGroup(pn_ctPhieuMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_ctPhieuMuonLayout.createSequentialGroup()
                                        .addGroup(pn_ctPhieuMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(1, 1, 1))
                                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(24, 24, 24)
                                .addGroup(pn_ctPhieuMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pn_ctPhieuMuonLayout.createSequentialGroup()
                                        .addComponent(txtSoLuongInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbTBSLInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(pn_ctPhieuMuonLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel27)
                        .addGap(101, 101, 101)
                        .addComponent(btnLuuPhieu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnComplete)))
                .addGap(0, 20, Short.MAX_VALUE))
            .addGroup(pn_ctPhieuMuonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTBThemCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pn_ctPhieuMuonLayout.setVerticalGroup(
            pn_ctPhieuMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_ctPhieuMuonLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pn_ctPhieuMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFindMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFindMaSach))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_ctPhieuMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_ctPhieuMuonLayout.createSequentialGroup()
                        .addComponent(cbbMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pn_ctPhieuMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbNamTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbThangTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pn_ctPhieuMuonLayout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pn_ctPhieuMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pn_ctPhieuMuonLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(pn_ctPhieuMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pn_ctPhieuMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(txtSoLuongInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbTBSLInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pn_ctPhieuMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuuPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnComplete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbTBThemCT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnThemPhieu.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnThemPhieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconPlus.png"))); // NOI18N
        btnThemPhieu.setText("Thêm Phiếu");
        btnThemPhieu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnThemPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemPhieuActionPerformed(evt);
            }
        });

        lb_tb.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lb_tb.setForeground(new java.awt.Color(51, 204, 0));

        btnTaoMa.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        btnTaoMa.setText("Tạo mã");
        btnTaoMa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTaoMaMouseClicked(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel23.setText("Số thẻ:");

        cbbSoThe.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cbbSoThe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnNgayHienTai.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        btnNgayHienTai.setText("Now");
        btnNgayHienTai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNgayHienTaiMouseClicked(evt);
            }
        });

        txtFindSoThe.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        btnFindSoThe.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnFindSoThe.setText("Find");
        btnFindSoThe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindSoTheActionPerformed(evt);
            }
        });

        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thao tác trên chi tiết phiếu", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        btnDaTra.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        btnDaTra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/complete.png"))); // NOI18N
        btnDaTra.setText("Đã trả");
        btnDaTra.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDaTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDaTraActionPerformed(evt);
            }
        });

        btnXoaCTP.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        btnXoaCTP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/delete.png"))); // NOI18N
        btnXoaCTP.setText("Delete");
        btnXoaCTP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnXoaCTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCTPActionPerformed(evt);
            }
        });

        btnCapNhatCTPhieu.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        btnCapNhatCTPhieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/update.png"))); // NOI18N
        btnCapNhatCTPhieu.setText("Update");
        btnCapNhatCTPhieu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCapNhatCTPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatCTPhieuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btnCapNhatCTPhieu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoaCTP)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDaTra)
                .addGap(82, 82, 82))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaCTP, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhatCTPhieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDaTra, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        txtMaSachUpd.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Tahoma", 3, 15)); // NOI18N
        jLabel25.setText("Mã sách");

        jLabel26.setFont(new java.awt.Font("Tahoma", 3, 15)); // NOI18N
        jLabel26.setText("Số lượng");

        txtSoLuongUpd.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtSoLuongUpd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoLuongUpdKeyReleased(evt);
            }
        });

        lbTBSLUP.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbTBSLUP.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_tb, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel23)
                                    .addComponent(btnFindSoThe, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFindSoThe)
                                    .addComponent(txtMaPhieuMuon)
                                    .addComponent(cbbSoThe, 0, 159, Short.MAX_VALUE))
                                .addGap(16, 16, 16)
                                .addComponent(btnTaoMa))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGap(280, 280, 280)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnThemPhieu)
                                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel22Layout.createSequentialGroup()
                                            .addComponent(jLabel20)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbbNgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbbThangMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbbNamMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnNgayHienTai))
                                        .addGroup(jPanel22Layout.createSequentialGroup()
                                            .addGap(103, 103, 103)
                                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(cbbMaNV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                        .addGap(0, 15, Short.MAX_VALUE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel22Layout.createSequentialGroup()
                                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel22Layout.createSequentialGroup()
                                        .addComponent(txtMaSachUpd, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30))
                                    .addGroup(jPanel22Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(txtSoLuongUpd, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbTBSLUP, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12))))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(pn_ctPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(btnTaoMa)
                    .addComponent(cbbMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFindSoThe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFindSoThe))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbSoThe, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbNgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbNamMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbThangMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNgayHienTai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThemPhieu)
                .addGap(12, 12, 12)
                .addComponent(lb_tb, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pn_ctPhieuMuon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(txtMaSachUpd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel26)
                                .addComponent(txtSoLuongUpd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbTBSLUP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Thêm phiếu mượn trả", jPanel20);

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin thẻ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 13))); // NOI18N

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel28.setText("Mã thẻ:");

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel29.setText("Ngày đăng ký:");

        cbbNgayDK.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cbbNgayDK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbThangDK.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cbbThangDK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbNamDK.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cbbNamDK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtMaThe.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel30.setText("Ngày hết hạn:");

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel31.setText("Ghi chú");

        cbbNgayHH.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cbbNgayHH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbThangHH.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cbbThangHH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbNamHH.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cbbNamHH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtGhiChuTTV.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        btnLayMaSachTuDong.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnLayMaSachTuDong.setText("Lấy mã");
        btnLayMaSachTuDong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLayMaSachTuDongMouseClicked(evt);
            }
        });

        jPanel2.setLayout(new java.awt.GridLayout(1, 3, 50, 0));

        btnNew.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconPlus.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jPanel2.add(btnNew);

        btnSave.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconSave.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel2.add(btnSave);

        btnUpdate.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/update.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel2.add(btnUpdate);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel28)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaThe, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLayMaSachTuDong))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(18, 18, 18)
                        .addComponent(cbbNgayDK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(cbbThangDK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbNamDK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel31)
                        .addGap(18, 18, 18)
                        .addComponent(txtGhiChuTTV, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbNgayHH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(cbbThangHH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbNamHH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaThe, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLayMaSachTuDong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGhiChuTTV, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbNgayHH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbThangHH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbNamHH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbNgayDK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbThangDK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbNamDK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tbTheThuVien.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tbTheThuVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbTheThuVien.setRowHeight(25);
        tbTheThuVien.setRowMargin(5);
        tbTheThuVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTheThuVienMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbTheThuVien);

        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gia hạn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 13))); // NOI18N

        jPanel3.setLayout(new java.awt.GridLayout(2, 1, 0, 20));

        btnGiaHan.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        btnGiaHan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconPlus2.png"))); // NOI18N
        btnGiaHan.setText("Gia Hạn:");
        btnGiaHan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnGiaHan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiaHanActionPerformed(evt);
            }
        });
        jPanel3.add(btnGiaHan);

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconHan.png"))); // NOI18N
        jLabel32.setText("Còn lại:");
        jPanel3.add(jLabel32);

        jPanel4.setLayout(new java.awt.GridLayout(2, 1, 0, 35));

        txtSoThangGiaHan.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txtSoThangGiaHan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoThangGiaHanKeyReleased(evt);
            }
        });
        jPanel4.add(txtSoThangGiaHan);

        txtHanThe.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txtHanThe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHanTheKeyReleased(evt);
            }
        });
        jPanel4.add(txtHanThe);

        jPanel5.setLayout(new java.awt.GridLayout(2, 1, 0, 20));

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel34.setText("tháng");
        jPanel5.add(jLabel34);

        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel33.setText("ngày");
        jPanel5.add(jLabel33);

        lbTBFiaHan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbTBFiaHan.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbTBFiaHan, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTBFiaHan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtSearchTTV.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        btnXoaSearchTTV.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnXoaSearchTTV.setText("X");
        btnXoaSearchTTV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSearchTTVActionPerformed(evt);
            }
        });

        btnSearchTTV.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnSearchTTV.setText("Tìm kiếm");
        btnSearchTTV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchTTVMouseClicked(evt);
            }
        });

        btnTheHetHan.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnTheHetHan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icon-warnning.png"))); // NOI18N
        btnTheHetHan.setText("Thẻ hết hạn");
        btnTheHetHan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTheHetHanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTheHetHan)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(54, 54, 54))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1358, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtSearchTTV, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaSearchTTV)
                        .addGap(2, 2, 2)
                        .addComponent(btnSearchTTV)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTheHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearchTTV, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearchTTV, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnXoaSearchTTV, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lý thẻ thư viện", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1384, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 718, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCapNhatCTPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatCTPhieuActionPerformed
        int vt = tbCTPhieuMuon.getSelectedRow();

        if (vt == -1) {//kiểm tra nếu chưa chọn dòng trên bảng
            JOptionPane.showMessageDialog(rootPane, "Chưa có dòng cần xử lý.", "Thông báo", 1, imgTB.getImgWarning());
        } else {
            Vector vctRow = (Vector) vctDataCTPhieuMuon.get(vt);
            String maSach1 = vctRow.get(1).toString();
            String maPhieu = vctRow.get(0).toString();
            String maSach2 = txtMaSachUpd.getText().trim();
            int kt = 0;
            ArrayList<Sach> lstS = sDAO.listSach();
            for (Sach s : lstS) {//kiểm tra mã sách có tồn tại
                if (s.getMaS().equals(maSach2)) {
                    kt = 1;
                    break;
                }
            }

            if (kt == 0) {
                JOptionPane.showMessageDialog(rootPane, "Mã sách không tồn tại", "Thông báo", 1, imgTB.getImgWarning());
            } else {
                ArrayList<MuonTra> lstMT = mtDAO.listMuonTra();
                int kt2 = 1;
                for (MuonTra mt : lstMT) {//kiểm tra mã sách trùng
                    if (mt.getMaMuonTra().equals(maPhieu) && mt.getMaSach().equals(maSach2)) {
                        kt = 0;
                    }
                }
                String strSL = txtSoLuongUpd.getText().trim();
                if (strSL.length() == 0 || hxl.kTraChuoiIsNumber(strSL) == false || strSL.charAt(0) == '0') {
                    JOptionPane.showMessageDialog(rootPane, "Số lượng sách không hợp lệ", "Thông báo", 0, imgTB.getImgWarning());
                    txtSoLuongUpd.requestFocus();
                } else {
                    int soLuong = Integer.parseInt(txtSoLuongUpd.getText());
                    int slTon = sDAO.kTraSachDaHetTheoSoLuongMua(maSach2, soLuong);
                    if (slTon >= 0)//Đã hết
                    {
                        JOptionPane.showMessageDialog(rootPane, "Số lượng sách không đủ.\nChỉ còn " + slTon + " quyển.", "Thất bại", 0, imgTB.getImgWarning());
                        txtSoLuongUpd.requestFocus();
                    } else {
                        if (mtDAO.capNhatCTPhieuMuon2(maPhieu, maSach1, maSach2, soLuong) == false) {
                            JOptionPane.showMessageDialog(rootPane, "Cập nhật không thành công", "Thông báo", 0, imgTB.getImgError());
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Đã cập nhật mã sách '" + maSach1 + "' thành '" + maSach2 + "'\n Với số sách mượn là " + soLuong, "Thông báo", 1, imgTB.getImgCompleteGreen());
                            showCTPhieuMuon();
                            showSach();
                            hideBtnThemPhieu();
                        }
                    }
                }
//                }

            }
        }
    }//GEN-LAST:event_btnCapNhatCTPhieuActionPerformed

    private void btnXoaCTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCTPActionPerformed
        int vt = tbCTPhieuMuon.getSelectedRow();//chọn 1 dòng
        int[] vt2 = tbCTPhieuMuon.getSelectedRows();//Lấy list vị trí khi Chọn nhiều dòng
        int demSoDongXoa = vt2.length;

        if (vt == -1) {//Nếu chưa chọn đòng
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn chi tiết phiếu cần xóa.", "Thông báo", 1, imgTB.getImgWarning());
        } else {
            int sl = vt2.length;
            int n = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa " + demSoDongXoa + " dòng ?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, imgTB.getImgWarning());
            if (n == JOptionPane.YES_OPTION) {
                for (int i = vt2.length - 1; i >= 0; i--) {//Xóa lần lượt theo các dòng đã chọn
                    Vector vctRow = (Vector) vctDataCTPhieuMuon.get(vt2[i]);
                    String maPhieu = vctRow.get(0).toString();
                    String maSach = vctRow.get(1).toString();
                    String daTra = vctRow.get(4).toString();
                    if (daTra.equals("Yes")) {
                        JOptionPane.showMessageDialog(rootPane, "Không thể xóa phiếu: " + maPhieu, "Thông báo", 1, imgTB.getImgWarning());
                        sl -= 1;
                    } else {
                        mtDAO.xoaCTPhieuMuon(maPhieu, maSach);
                    }
                }
                JOptionPane.showMessageDialog(rootPane, "Đã xóa: " + sl + " dòng.", "Thông báo", 1, imgTB.getImgCompleteGreen());
                showCTPhieuMuon();//Cập nhật lại table
                showSach();
                hideBtnThemPhieu();
            }
        }
    }//GEN-LAST:event_btnXoaCTPActionPerformed

    private void btnDaTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDaTraActionPerformed
        int vt = tbCTPhieuMuon.getSelectedRow();
        int[] vt2 = tbCTPhieuMuon.getSelectedRows();

        if (vt == -1) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có dòng cần xử lý.", "Thông báo", 1, imgTB.getImgWarning());
        } else {

            int n = JOptionPane.showConfirmDialog(this, "Đã trả sách ?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, imgTB.getImgQuestion());
            if (n == JOptionPane.YES_OPTION) {
                for (int i = vt2.length - 1; i >= 0; i--) {//Xóa nhiều dòng
                    Vector vctRow = (Vector) vctDataCTPhieuMuon.get(vt2[i]);
                    String daTra = vctRow.get(4).toString();
                    String maPhieu = vctRow.get(0).toString();
                    String maSach = vctRow.get(1).toString();
                    int sl = Integer.parseInt(vctRow.get(3).toString());
                    if (daTra.equals("Yes")) {
                        JOptionPane.showMessageDialog(rootPane, "Sách '" + maSach + "' đã trả rồi", "Thất bại", 1, imgTB.getImgWarning());
                        continue;
                    } else {
                        mtDAO.capNhatCTPhieuMuon(maPhieu, maSach, sl);
                        mtDAO.capNhatSLTon(maSach, sl);
                        JOptionPane.showMessageDialog(rootPane, "Ấn Ok để tiếp tục.", "Thành công", 1, imgTB.getImgCompleteGreen());
                        showCTPhieuMuon();
                        showSach();
                        hideBtnThemPhieu();
                    }
                }

            }
        }
    }//GEN-LAST:event_btnDaTraActionPerformed

    private void btnFindSoTheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindSoTheActionPerformed
        String maThe = txtFindSoThe.getText().trim();
        //Tìm vị trí trong vector chứa mã thẻ
        int vTri = hxl.timViTriVctcbb(vctCBBSoThe, maThe);
        if (vTri == -1) {
            JOptionPane.showMessageDialog(rootPane, "Không tìm thấy mã thẻ", "Thông báo", 0, imgTB.getImgWarning());
        } else {
            int n = JOptionPane.showConfirmDialog(this, "Bạn muốn chọn mã thẻ này", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, imgTB.getImgQuestion());
            if (n == JOptionPane.YES_OPTION) {
                cbbSoThe.setSelectedIndex(vTri);
            }
        }
    }//GEN-LAST:event_btnFindSoTheActionPerformed

    private void btnNgayHienTaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNgayHienTaiMouseClicked
        String now[] = dateNow.split("-");//Căt chuỗi ngày tháng năm hiện tại
        String dayNow = now[2];//ngày
        String monthNow = now[1];//tháng
        String yearNow = now[0];//Năm
        int vtNgay = hxl.timViTriVctcbb(vctCBBNgayMuon, dayNow);//Tìm vị trí ngày trong combobox ngày
        int vtThang = hxl.timViTriVctcbb(vctCBBThangMuon, monthNow);//Tìm vị trí ngày trong combobox tháng
        int vtNam = hxl.timViTriVctcbb(vctCBBNamMuon, yearNow);//Tìm vị trí ngày trong combobox năm
        //Set cho combobox
        cbbNgayMuon.setSelectedIndex(vtNgay);
        cbbThangMuon.setSelectedIndex(vtThang);
        cbbNamMuon.setSelectedIndex(vtNam);
    }//GEN-LAST:event_btnNgayHienTaiMouseClicked

    private void btnTaoMaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaoMaMouseClicked
        String maPhieuMuon = taoMaMuonTraTuDong();
        txtMaPhieuMuon.setText(maPhieuMuon);
    }//GEN-LAST:event_btnTaoMaMouseClicked

    private void btnThemPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemPhieuActionPerformed
        hideBtnThemPhieu();
        String maPhieu = txtMaPhieuMuon.getText().trim();
        String soThe = cbbSoThe.getSelectedItem().toString().trim();
        String maNV = cbbMaNV.getSelectedItem().toString().trim();
        String ngayMuon = cbbNamMuon.getSelectedItem().toString() + "-" + cbbThangMuon.getSelectedItem().toString() + "-" + cbbNgayMuon.getSelectedItem().toString();

        if (maPhieu.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng tạo mã phiếu mượn", "Thông báo", 1, imgTB.getImgWarning());
        } else if (hxl.ktraNgay(dateNow, ngayMuon) == false) {//kiểm tra ngày mượn phải lớn hơn ngày hiện tại
            JOptionPane.showMessageDialog(rootPane, "Ngày mượn không hợp lệ\nVui lòng bấm vào nút ' Now ' để lấy ngày", "Thông báo", 0, imgTB.getImgWarning());
        } else if (ttvDAO.kTraHanThe(soThe)) {//kiểm tra thẻ thư viện đã hết hạn => True: đã hết hạn
            JOptionPane.showMessageDialog(rootPane, "Số thẻ '" + soThe + " đã hết hạn", "Thông báo", 0, imgTB.getImgWarning());
        } else {
            //kiểm tra mã phiếu đã tồn tại chưa
            boolean ktra = true;
            ArrayList<MuonTra> lstMT = mtDAO.listPhieuMuon();
            for (MuonTra mt : lstMT) {
                if (mt.getMaMuonTra().equals(maPhieu)) {
                    ktra = false;
                    break;
                }
            }
            if (ktra == false) {
                JOptionPane.showMessageDialog(rootPane, "Mã phiếu mươn '" + maPhieu + "' đã tồn tại\nVui lòng tạo mã mới", "Thông báo", 0, imgTB.getImgWarning());
            } else {
                MuonTra mt = new MuonTra(maPhieu, soThe, maNV, ngayMuon);
                if (mtDAO.themPhieuMuon(mt) == true) {
                    lb_tb.setText("Thêm thành công phiếu '" + maPhieu + "'. Vui lòng nhập chi tiết bên dưới.");
                    hidePhieuMuon();
                    showChiTietPhieuMuon();
                    btnThemPhieu.setEnabled(false);
                    showPhieuMuon(vctHeadPhieuMuon, vctDataPhieuMuon);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Vui lòng kiểm tra lại các thông tin", "Thất bại", 1, imgTB.getImgError());
                }
            }
        }
    }//GEN-LAST:event_btnThemPhieuActionPerformed

    private void btnFindMaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindMaSachActionPerformed
        String maSach = txtFindMaSach.getText();
        //Tìm vị trí trong vector chứa mã sách
        int vTri = hxl.timViTriVctcbb(vctCBBMaSach, maSach);
        if (vTri == -1) {
            JOptionPane.showMessageDialog(rootPane, "Không tìm thấy mã sách", "Thông báo", 0, imgTB.getImgWarning());
        } else {
            int n = JOptionPane.showConfirmDialog(this, "Bạn muốn chọn mã sách này", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, imgTB.getImgQuestion());
            if (n == JOptionPane.YES_OPTION) {
                cbbMaSach.setSelectedIndex(vTri);
            }
        }
    }//GEN-LAST:event_btnFindMaSachActionPerformed

    private void btnCompleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompleteActionPerformed
        showPhieuMuon();
        hideChiTietPhieuMuon();
        btnThemPhieu.setEnabled(true);
        lb_tb.setText("");
        lbTBThemCT.setText("");
        txtFindSoThe.setText("");
        lbTBSLInsert.setText("");
    }//GEN-LAST:event_btnCompleteActionPerformed

    private void btnLuuPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuPhieuActionPerformed
        String maPhieu = txtMaPhieuMuon.getText();
        String maSach = cbbMaSach.getSelectedItem().toString();
        int ngay = Integer.parseInt(cbbNgayTra.getSelectedItem().toString());
        int thang = Integer.parseInt(cbbThangTra.getSelectedItem().toString());
        int nam = Integer.parseInt(cbbNamTra.getSelectedItem().toString());
        String ngayTra = nam + "-" + thang + "-" + ngay;
        String ghiChu = txtGhiChu.getText();
        String tachNgayMuon[] = dateNow.split("-");
        String ngayMuon = tachNgayMuon[2] + "-" + tachNgayMuon[1] + "-" + tachNgayMuon[0];
        //kiểm tra mã phiếu và mã sách tồn tại hay chưa
        int kt = 1;
        ArrayList<MuonTra> lstMT = mtDAO.listMuonTra();
        for (MuonTra mt : lstMT) {
            if (mt.getMaMuonTra().equals(maPhieu) && mt.getMaSach().equals(maSach)) {
                kt = 0;
                break;
            }
        }
        if (kt == 0)//nếu đã tồn tại => vi phạm ko đc thêm
        {
            JOptionPane.showMessageDialog(rootPane, "Đã tồn tại mã sách '" + maSach + "' của phiếu '" + maPhieu + "'", "Thất bại", 1, imgTB.getImgWarning());
        } else//Được phép
        {
            if (hxl.kTraNgayHopLe(ngay, thang, nam) == false) {
                JOptionPane.showMessageDialog(rootPane, "Ngày '" + ngay + "-" + thang + "-" + nam + " không hợp lệ, vui lòng kiểm tra lại.", "Thất bại", 0, imgTB.getImgWarning());
            } else {
                //Kiểm tra ngày trả phải lớn hơn ngày mượn
                if (hxl.ktraNgay(dateNow, ngayTra) == false) {
                    JOptionPane.showMessageDialog(rootPane, "Ngày trả phải lớn hơn ngày '" + ngayMuon + "'.", "Thất bại", 0, imgTB.getImgWarning());
                } else {
                    String strSL = txtSoLuongInsert.getText().trim();
                    if (strSL.length() == 0 || hxl.kTraChuoiIsNumber(strSL) == false || strSL.charAt(0) == '0') {
                        JOptionPane.showMessageDialog(rootPane, "Số lượng không hợp lệ.", "Thất bại", 0, imgTB.getImgWarning());
                        txtSoLuongInsert.requestFocus();
                    } else {
                        int sl = Integer.parseInt(txtSoLuongInsert.getText());
                        //Kiểm tra số lượng sách có đủ hay không
                        int slTon = sDAO.kTraSachDaHetTheoSoLuongMua(maSach, sl);
                        if (slTon >= 0)//Đã hết
                        {
                            JOptionPane.showMessageDialog(rootPane, "Số lượng sách không đủ.\nChỉ còn " + slTon + " quyển.", "Thất bại", 0, imgTB.getImgWarning());
                            txtSoLuongInsert.requestFocus();
                        } else {
                            MuonTra mt = new MuonTra();
                            mt.setMaMuonTra(maPhieu);
                            mt.setMaSach(maSach);
                            mt.setNgayTra(ngayTra);
                            mt.setGhiChu(ghiChu);
                            mt.setSoLuong(sl);
                            if (mtDAO.themCTPhieuMuon(mt) == true) {
                                JOptionPane.showMessageDialog(rootPane, "Đã thêm mã sách '" + maSach + "' của phiếu '" + maPhieu + "'.\n Mượn với số lượng " + sl, "Thành công", 1, imgTB.getImgCompleteGreen());
                                lbTBThemCT.setText("Đã thêm mã sách '" + maSach + "' của phiếu '" + maPhieu + "'");
                                showCTPhieuMuon();
                                showSach();
                                lbTBSLInsert.setText("");
                                hideBtnThemPhieu();
                            } else {
                                JOptionPane.showMessageDialog(rootPane, "Đã có lỗi xảy ra trong quá trình thêm.", "Thất bại", 0, imgTB.getImgWarning());
                                lbTBThemCT.setText("Lỗi.......");
                            }
                        }
                    }
                }
            }

        }

    }//GEN-LAST:event_btnLuuPhieuActionPerformed

    private void btnResetCTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetCTPActionPerformed
        showCTPhieuMuon();
        hideBtnThemPhieu();
    }//GEN-LAST:event_btnResetCTPActionPerformed

    private void tbPhieuMuonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPhieuMuonMouseClicked
        showCTPhieuMuon();
        hideBtnThemPhieu();
//        btnXoaPhieuMuon.setEnabled(true);
        int vt = tbPhieuMuon.getSelectedRow();//Lấy vị trí chọn trên bảng
        Vector vctRowSelect = (Vector) vctDataPhieuMuon.get(vt);//Lấy dòng trong vector chứa dữ liệu
        String maPM = vctRowSelect.get(0).toString();//Lấy cột 0 <=> mã Phiếu mượn
        dfm = (DefaultTableModel) tbCTPhieuMuon.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHeadCTPhieuMuon.clear();
        vctHeadCTPhieuMuon.add("Mã#");
        vctHeadCTPhieuMuon.add("Mã sách");
        vctHeadCTPhieuMuon.add("Số lượng");
        vctHeadCTPhieuMuon.add("Ngày trả");
        vctHeadCTPhieuMuon.add("Đã trả");
        vctHeadCTPhieuMuon.add("Ghi chú");
        //add từng dòng của bảng
        ArrayList<MuonTra> lstMT = mtDAO.listCTPhieuMuon(maPM);
        for (MuonTra mt : lstMT) {
            Vector vctRow = new Vector();//Chứa dữ liệu cột
            vctRow.add(mt.getMaMuonTra().trim());//Cột 1
            vctRow.add(mt.getMaSach().trim());//Cột 2
            vctRow.add(mt.getNgayTra().trim());//Cột 3
            vctRow.add(mt.getSoLuong());//Cột 4
            vctRow.add(mt.getDaTra());//Cột 5
            //Add cột ghi chú; quá Hạn hay ko
            if (mt.getDaTra().equals("No") && hxl.ktraNgay(dateNow, mt.getNgayTra()) == false) {
                vctRow.add("Quá hạn");//Cột 6
            } else if (mt.getDaTra().equals("No") && hxl.ktraNgay(dateNow, mt.getNgayTra()) == true) {
                vctRow.add("Đang mượn...");
            } else {
                vctRow.add("Đã trả.");
            }
            vctDataCTPhieuMuon.add(vctRow);//thêm Dòng vctRow gồm 4 cột
        }
    }//GEN-LAST:event_tbPhieuMuonMouseClicked

    private void tbCTPhieuMuonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCTPhieuMuonMouseClicked
        showBtnThemPhieu();
//        btnXoaPhieuMuon.setEnabled(false);
        int vt = tbCTPhieuMuon.getSelectedRow();
        Vector vctRow = (Vector) vctDataCTPhieuMuon.get(vt);
        String maSach = vctRow.get(1).toString();

        txtMaSachUpd.setText(maSach);
        txtSoLuongUpd.setText(vctRow.get(3).toString());
    }//GEN-LAST:event_tbCTPhieuMuonMouseClicked

    private void btnViPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViPhamMouseClicked
        dfm = (DefaultTableModel) tbMuonTra.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHeadMT.clear();
        vctHeadMT.add("Mã #");
        vctHeadMT.add("Mã thẻ");
        vctHeadMT.add("Nhân viên");
        vctHeadMT.add("Ngày mượn");
        vctHeadMT.add("Ngày trả");
        vctHeadMT.add("Mã sách");
        vctHeadMT.add("Tên sách");
        vctHeadMT.add("Số lượng");
        vctHeadMT.add("Đã trả");
        vctHeadMT.add("Ghi chú");

        //add từng dòng của bảng
        ArrayList<MuonTra> lstMT = mtDAO.listMuonTraViPham();
        ArrayList<Sach> lstS = sDAO.listSach();
        for (MuonTra mt : lstMT) {

            Vector vctRow = new Vector();//Chứa dữ liệu cột
            vctRow.add(mt.getMaMuonTra().trim());//Cột 1
            vctRow.add(mt.getMaThe().trim());//Cột 2
            vctRow.add(mt.getTenNV().trim());//Cột 3
            vctRow.add(mt.getNgayMuon().trim());//Cột 4
            vctRow.add(mt.getNgayTra().trim());//Cột 5
            vctRow.add(mt.getMaSach().trim());//Cột 6
            //lấy tên sách thông qua listSach
            for (Sach sach : lstS) {
                if (sach.getMaS().equals(mt.getMaSach())) {
                    vctRow.add(sach.getTenS());//Cột 7
                    break;
                }
            }
            //nếu ngày trả chưa có thì ghi chú là chưa trả
            vctRow.add(mt.getSoLuong());//Cột 8
            vctRow.add(mt.getDaTra());//Cột 9

            //Add cột ghi chú; quá Hạn hay ko
            if (mt.getDaTra().equals("No") && hxl.ktraNgay(dateNow, mt.getNgayTra()) == false) {
                vctRow.add("Quá hạn");//Cột 10
            } else if (mt.getDaTra().equals("No") && hxl.ktraNgay(dateNow, mt.getNgayTra()) == true) {
                vctRow.add("Đang mượn...");
            } else {
                vctRow.add("Đã trả.");
            }
            vctDataMT.add(vctRow);//thêm Dòng vctRow gồm 10 cột   
        }
        tbMuonTra.setModel(new DefaultTableModel(vctDataMT, vctHeadMT));
        setSizeTableMT();
    }//GEN-LAST:event_btnViPhamMouseClicked

    private void btnXoaSearchMTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaSearchMTMouseClicked
        showMuonTra();
        txtSearchPhieuMuon.setText("");
        setSizeTableMT();
    }//GEN-LAST:event_btnXoaSearchMTMouseClicked

    private void btnSearchMTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMTMouseClicked
        dfm = (DefaultTableModel) tbMuonTra.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHeadMT.clear();
        vctDataMT.clear();
        vctHeadMT.add("Mã #");
        vctHeadMT.add("Mã thẻ");
        vctHeadMT.add("Nhân viên");
        vctHeadMT.add("Ngày mượn");
        vctHeadMT.add("Ngày trả");
        vctHeadMT.add("Mã sách");
        vctHeadMT.add("Tên sách");
        vctHeadMT.add("Số lượng");
        vctHeadMT.add("Đã trả");
        vctHeadMT.add("Ghi chú");
        //add từng dòng của bảng
        ArrayList<MuonTra> lstMT = mtDAO.listMuonTra();
        String search = txtSearchPhieuMuon.getText().trim();
        ArrayList<Sach> lstS = sDAO.listSach();
        for (MuonTra mt : lstMT) {
            for (Sach sach : lstS) {
                if (sach.getMaS().equals(mt.getMaSach())) {
                    if (mt.getMaMuonTra().contains(search) || mt.getMaThe().contains(search) || mt.getTenNV().contains(search) || mt.getMaNV().contains(search) || mt.getMaSach().contains(search) || mt.getGhiChu().contains(search)) {
                        Vector vctRow = new Vector();//Chứa dữ liệu cột
                        vctRow.add(mt.getMaMuonTra().trim());//Cột 1
                        vctRow.add(mt.getMaThe().trim());//Cột 2
                        vctRow.add(mt.getTenNV().trim());//Cột 3
                        vctRow.add(mt.getNgayMuon().trim());//Cột 4
                        vctRow.add(mt.getNgayTra());//Cột 5
                        vctRow.add(mt.getMaSach());//Cột 6
                        vctRow.add(sach.getTenS());//Cột 7
                        vctRow.add(mt.getSoLuong());//Cột 8
                        //nếu ngày trả chưa có thì ghi chú là chưa trả
                        vctRow.add(mt.getDaTra());//Cột 9

                        //Add cột ghi chú; quá Hạn hay ko
                        if (mt.getDaTra().equals("No") && hxl.ktraNgay(dateNow, mt.getNgayTra()) == false) {
                            vctRow.add("Quá hạn");//Cột 10
                        } else if (mt.getDaTra().equals("No") && hxl.ktraNgay(dateNow, mt.getNgayTra()) == true) {
                            vctRow.add("Đang mượn...");
                        } else {
                            vctRow.add("Đã trả.");
                        }

                        vctDataMT.add(vctRow);//thêm Dòng vctRow gồm 4 cột

                    }
                    break;
                }
            }

        }
        tbMuonTra.setModel(new DefaultTableModel(vctDataMT, vctHeadMT));
        setSizeTableMT();
    }//GEN-LAST:event_btnSearchMTMouseClicked

    private void tbMuonTraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMuonTraMouseClicked
        txtDangMuon.setText("");
        int vt = tbMuonTra.getSelectedRow();//Lấy vị trí chọn trên bảng
        Vector vctRowSelect = (Vector) vctDataMT.get(vt);//Lấy dòng trong vector chứa dữ liệu
        String maMT = vctRowSelect.get(0).toString();//Lấy cột 0 <=> mã đọc giả
        String maSach = vctRowSelect.get(5).toString();//Lấy cột 5 <=> mã sách

        //Hien thi sách----------------------------------
        dfm = (DefaultTableModel) tbSach.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHeadS.clear();
        vctHeadS.add("Mã sách");
        vctHeadS.add("Tên sách");
        vctHeadS.add("Thể loại");
        vctHeadS.add("Số lượng");

        //add từng dòng của bảng
        ArrayList<Sach> lstSach = mtDAO.listSach(maMT, maSach);
        for (Sach s : lstSach) {
            Vector vctRow = new Vector();
            vctRow.add(s.getMaS());//Cột 1
            vctRow.add(s.getTenS());//Cột 2
            vctRow.add(s.getTheLoai());//Cột 3
            vctRow.add(s.getSoLuong());//Cột 4
            vctDataS.add(vctRow);//dòng 1
        }
        //Hien thi Đọc giả----------------------------------
        dfm = (DefaultTableModel) tbDocGia.getModel();//
        dfm.setRowCount(0);//reset dữ liệu dòng thành 0
        //Tên cột
        vctHeadDG.clear();
        vctHeadDG.add("Mã đọc giả");
        vctHeadDG.add("Tên đọc giả");
        vctHeadDG.add("Giới tính");
        vctHeadDG.add("Số điện thoại");

        //add từng dòng của bảng
        ArrayList<DocGia> lstDG = mtDAO.listDG(maMT, maSach);
        for (DocGia dg : lstDG) {
            Vector vctRow = new Vector();//Chứa dữ liệu cột
            vctRow.add(dg.getMaDG());//Cột 1
            vctRow.add(dg.getTenDG());//Cột 2
            vctRow.add(dg.getGioiTinh());//Cột 3
            vctRow.add(dg.getSdt());//Cột 4
            vctDataDG.add(vctRow);//thêm Dòng vctRow gồm 4 cột
        }
    }//GEN-LAST:event_tbMuonTraMouseClicked

    private void btnXoaSearchSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaSearchSMouseClicked
        showSach();
        txtSearchSach.setText("");
    }//GEN-LAST:event_btnXoaSearchSMouseClicked

    private void tbSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSachMouseClicked
        int vt = tbSach.getSelectedRow();
        Vector vctRowSelect = (Vector) vctDataS.get(vt);
        String maSach = vctRowSelect.get(0).toString();
        dfm = (DefaultTableModel) tbDocGia.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHeadDG.clear();
        vctHeadDG.add("Mã đọc giả");
        vctHeadDG.add("Tên đọc giả");
        vctHeadDG.add("Giới tính");
        vctHeadDG.add("Số điện thoại");

        //add từng dòng của bảng
        ArrayList<DocGia> lstDG = sDAO.layDanhSachMuon(maSach);
        for (DocGia dg : lstDG) {
            Vector vctRow = new Vector();//Chứa dữ liệu cột
            vctRow.add(dg.getMaDG());//Cột 1
            vctRow.add(dg.getTenDG());//Cột 2
            vctRow.add(dg.getGioiTinh());//Cột 3
            vctRow.add(dg.getSdt());//Cột 4
            vctDataDG.add(vctRow);//thêm Dòng vctRow gồm 4 cột
        }
        //        txtSearchSach.setText(vctRow.get(2).toString());
    }//GEN-LAST:event_tbSachMouseClicked

    private void btnSearchSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchSachMouseClicked
        dfm = (DefaultTableModel) tbSach.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHeadS.clear();
        vctHeadS.add("Mã sách");
        vctHeadS.add("Tên sách");
        vctHeadS.add("Thể loại");
        vctHeadS.add("Số lượng");
        String search = txtSearchSach.getText().trim();
        //add từng dòng của bảng
        ArrayList<Sach> lstSach = sDAO.listSach();
        for (Sach s : lstSach) {
            if (s.getMaS().contains(search) || s.getTenS().contains(search) || s.getTheLoai().contains(search) || String.valueOf(s.getSoLuong()).equals(search)) {
                Vector vctRow = new Vector();
                vctRow.add(s.getMaS());//Cột 1
                vctRow.add(s.getTenS());//Cột 2
                vctRow.add(s.getTheLoai());//Cột 3
                vctRow.add(s.getSoLuong());//Cột 4
                vctDataS.add(vctRow);//dòng 1
            }
        }
        tbSach.setModel(new DefaultTableModel(vctDataS, vctHeadS));
        setSizeTableSach();
    }//GEN-LAST:event_btnSearchSachMouseClicked

    private void btnXoaSearchDGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaSearchDGMouseClicked
        showDocGia();
        txtSearchDocGia.setText("");
        setSizeTableDG();
    }//GEN-LAST:event_btnXoaSearchDGMouseClicked

    private void tbDocGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDocGiaMouseClicked
        int vt = tbDocGia.getSelectedRow();//Lấy vị trí chọn trên bảng
        Vector vctRowSelect = (Vector) vctDataDG.get(vt);//Lấy dòng trong vector chứa dữ liệu
        String maDG = vctRowSelect.get(0).toString();//Lấy cột 0 <=> mã đọc giả
        int kq = dgDAO.laySoLuongSachMuon(maDG);
        txtDangMuon.setText(String.valueOf(kq));
        dfm = (DefaultTableModel) tbSach.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHeadS.clear();
        vctHeadS.add("Mã sách");
        vctHeadS.add("Tên sách");
        vctHeadS.add("Thể loại");
        vctHeadS.add("Số lượng");

        //add từng dòng của bảng
        ArrayList<Sach> lstSach = dgDAO.laySachDaMuon(maDG);
        for (Sach s : lstSach) {
            Vector vctRow = new Vector();
            vctRow.add(s.getMaS());//Cột 1
            vctRow.add(s.getTenS());//Cột 2
            vctRow.add(s.getTheLoai());//Cột 3
            vctRow.add(s.getSoLuong());//Cột 4
            vctDataS.add(vctRow);//dòng 1
        }

    }//GEN-LAST:event_tbDocGiaMouseClicked

    private void btnSearchDGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchDGMouseClicked
        String search = txtSearchDocGia.getText().trim();
        dfm = (DefaultTableModel) tbDocGia.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHeadDG.clear();
        vctHeadDG.add("Mã đọc giả");
        vctHeadDG.add("Tên đọc giả");
        vctHeadDG.add("Giới tính");
        vctHeadDG.add("Số điện thoại");
        ArrayList<DocGia> lstDG = dgDAO.listDocGia();
        for (DocGia dg : lstDG) {
            if (dg.getMaDG().contains(search) || dg.getTenDG().contains(search) || dg.getGioiTinh().contains(search) || dg.getSdt().contains(search)) {
                Vector vctRow = new Vector();//Chứa dữ liệu cột
                vctRow.add(dg.getMaDG());//Cột 1
                vctRow.add(dg.getTenDG());//Cột 2
                vctRow.add(dg.getGioiTinh());//Cột 3
                vctRow.add(dg.getSdt());//Cột 4
                vctDataDG.add(vctRow);//thêm Dòng vctRow gồm 4 cột
            }
        }
        tbDocGia.setModel(new DefaultTableModel(vctDataDG, vctHeadDG));
        setSizeTableDG();
    }//GEN-LAST:event_btnSearchDGMouseClicked

    private void txtSoLuongUpdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongUpdKeyReleased

        String sl = txtSoLuongUpd.getText();
        char c = evt.getKeyChar();
        int phim = evt.getKeyCode();
        if (sl.length() > 0 && phim != 8 & !(c == KeyEvent.VK_ENTER) && !(c >= '0' && c <= '9') && phim != 37 && phim != 39 && phim != 38 && phim != 40) {
            StringBuilder sb = new StringBuilder();
            String s = txtSoLuongUpd.getText();
            sb.append(s);
            sb.deleteCharAt(s.length() - 1);
            s = sb.toString();
            txtSoLuongUpd.setText(s);

        }
        if (hxl.kTraChuoiIsNumber(sl) == false) {
            lbTBSLUP.setText("Không hợp lệ");
        } else {
            lbTBSLUP.setText("");
        }

    }//GEN-LAST:event_txtSoLuongUpdKeyReleased

    private void txtSoLuongInsertKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongInsertKeyReleased
        String sl = txtSoLuongInsert.getText();
        char c = evt.getKeyChar();
        int phim = evt.getKeyCode();
        if (sl.length() > 0 && phim != 8 & !(c == KeyEvent.VK_ENTER) && !(c >= '0' && c <= '9') && phim != 37 && phim != 39 && phim != 38 && phim != 40) {
            StringBuilder sb = new StringBuilder();
            String s = txtSoLuongInsert.getText();
            sb.append(s);
            sb.deleteCharAt(s.length() - 1);
            s = sb.toString();
            txtSoLuongInsert.setText(s);

        }
        if (hxl.kTraChuoiIsNumber(sl) == false) {
            lbTBSLInsert.setText("Không hợp lệ");
        } else {
            lbTBSLInsert.setText("");
        }
    }//GEN-LAST:event_txtSoLuongInsertKeyReleased

    private void txtFindPhieuMuonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFindPhieuMuonMouseClicked
        String search = txtSearchPM.getText();
        dfm = (DefaultTableModel) tbPhieuMuon.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHeadPhieuMuon.clear();
        vctDataPhieuMuon.clear();
        vctHeadPhieuMuon.add("Mã#");
        vctHeadPhieuMuon.add("Mã thẻ");
        vctHeadPhieuMuon.add("Mã nhân viên");
        vctHeadPhieuMuon.add("Ngày mượn");
        //add từng dòng của bảng
        ArrayList<MuonTra> lstMT = mtDAO.listPhieuMuon();
        for (MuonTra mt : lstMT) {
            if (mt.getMaMuonTra().contains(search) || mt.getMaThe().contains(search) || mt.getMaNV().contains(search)) {
                Vector vctRow = new Vector();//Chứa dữ liệu cột
                vctRow.add(mt.getMaMuonTra().trim());//Cột 1
                vctRow.add(mt.getMaThe().trim());//Cột 2
                vctRow.add(mt.getMaNV().trim());//Cột 3
                vctRow.add(mt.getNgayMuon().trim());//Cột 4
                vctDataPhieuMuon.add(vctRow);//thêm Dòng vctRow gồm 4 cột
            }
        }
        tbPhieuMuon.setModel(new DefaultTableModel(vctDataPhieuMuon, vctHeadPhieuMuon));
        
    }//GEN-LAST:event_txtFindPhieuMuonMouseClicked

    private void btnXoaSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaSearchMouseClicked
        showPhieuMuon(vctHeadPhieuMuon, vctDataPhieuMuon);
        hideBtnThemPhieu();
        tbPhieuMuon.setModel(new DefaultTableModel(vctDataPhieuMuon, vctHeadPhieuMuon));
        txtSearchPM.setText("");
    }//GEN-LAST:event_btnXoaSearchMouseClicked

    private void btnLayMaSachTuDongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLayMaSachTuDongMouseClicked
        // TODO add your handling code here:
        String ma = taoMaTheThuVienTuDong();
        txtMaThe.setText(ma);
    }//GEN-LAST:event_btnLayMaSachTuDongMouseClicked

    private void tbTheThuVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTheThuVienMouseClicked
        btnUpdate.setEnabled(true);
        btnSave.setEnabled(false);
        showGiaHan();
        int vt = tbTheThuVien.getSelectedRow();
        Vector vctRow = (Vector) vctDataTTV.get(vt);
        txtMaThe.setText(vctRow.get(0).toString());

        //tách Ngày dạng yyyy-MM-dd thành yyyy,MM,dd để dưa vào combobox Ngày đăng ký
        String ngayDangKy[] = vctRow.get(1).toString().split("-");
        int vtriNamDK = hxl.timViTriVctcbb(vctCBBNamDK, ngayDangKy[0]);
        cbbNamDK.setSelectedIndex(vtriNamDK);
        int vtriThangDK = hxl.timViTriVctcbb(vctCBBThangDK, ngayDangKy[1]);
        cbbThangDK.setSelectedIndex(vtriThangDK);
        int vtriNgayDK = hxl.timViTriVctcbb(vctCBBNgayDK, ngayDangKy[2]);
        cbbNgayDK.setSelectedIndex(vtriNgayDK);

        //tách Ngày dạng yyyy-MM-dd thành yyyy,MM,dd để dưa vào combobox Ngày hết hạn
        String ngayHetHan[] = vctRow.get(2).toString().split("-");
        int vtriNamHH = hxl.timViTriVctcbb(vctCBBNamHH, ngayHetHan[0]);
        cbbNamHH.setSelectedIndex(vtriNamHH);
        int vtriThangHH = hxl.timViTriVctcbb(vctCBBThangHH, ngayHetHan[1]);
        cbbThangHH.setSelectedIndex(vtriThangHH);
        int vtriNgayHH = hxl.timViTriVctcbb(vctCBBNgayHH, ngayHetHan[2]);
        cbbNgayHH.setSelectedIndex(vtriNgayHH);

        txtGhiChuTTV.setText(vctRow.get(3).toString());
        layNgayConLai();
    }//GEN-LAST:event_tbTheThuVienMouseClicked

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String maThe = txtMaThe.getText().trim();
        String ghiChu = txtGhiChuTTV.getText();

        //Ngày đăng ký
        String ngayDK = cbbNgayDK.getSelectedItem().toString();
        String thangDK = cbbThangDK.getSelectedItem().toString();
        String namDK = cbbNamDK.getSelectedItem().toString();
        String dateDK = namDK + "-" + thangDK + "-" + ngayDK;
        String dateDK1 = ngayDK + "-" + thangDK + "-" + namDK;

        //Ngày hết hạn
        String ngayHH = cbbNgayHH.getSelectedItem().toString();
        String thangHH = cbbThangHH.getSelectedItem().toString();
        String namHH = cbbNamHH.getSelectedItem().toString();
        String dateHH = namHH + "-" + thangHH + "-" + ngayHH;
        String dateHH1 = ngayHH + "-" + thangHH + "-" + namHH;

        //Kiểm tra input
        if (maThe.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chưa lấy mã!");
            txtMaThe.requestFocus();
        } else if (hxl.kTraNgayHopLe(Integer.parseInt(ngayHH), Integer.parseInt(thangHH), Integer.parseInt(namHH)) == false) {
            JOptionPane.showMessageDialog(rootPane, "Ngày kết thúc: '" + dateHH1 + "' không hợp lệ", "Thất bại", 0, imgTB.getImgWarning());
        } else if (hxl.ktraNgay(dateNow, dateHH) == false) {
            JOptionPane.showMessageDialog(rootPane, "Ngày kết thúc phải lớn hơn ngày bắt đầu", "Thất bại", 0, imgTB.getImgWarning());
        } else//input hợp lệ
        {
            boolean ktMaThe = true;
            //kiểm tra mã thẻ đã có trong database chưa
            ArrayList<TheThuVien> lstTTV = ttvDAO.listTheThuVien();

            for (TheThuVien ttv : lstTTV) {
                if (maThe.equals(ttv.getSoThe())) {
                    ktMaThe = false;//mã thẻ đã tồn tại
                    break;
                }
            }

            if (ktMaThe == false) {
                //Kiểm tra mã thẻ
                int n = JOptionPane.showConfirmDialog(this, "Mã thẻ thư viện đã tồn tại (Hãy bấm nút lấy mã)", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, imgTB.getImgQuestion());
                if (n == JOptionPane.YES_OPTION) {
                    String maTTVAuTo = taoMaTheThuVienTuDong();
                    txtMaThe.setText(maTTVAuTo);
                    JOptionPane.showMessageDialog(rootPane, "Đã tạo mã " + maTTVAuTo);
                }
            } else {

                TheThuVien ttv = new TheThuVien(maThe, this.dateNow, dateHH, ghiChu);
                boolean kt = ttvDAO.themTheThuVien(ttv);
                if (kt == true) {
                    JOptionPane.showMessageDialog(rootPane, "Thêm thành công\nMã: " + maThe + "\nNgày đăng ký: " + dateDK1 + "\nNgày hết hạn: " + dateHH1, "Thông báo", 1, imgTB.getImgCompleteGreen());
                    hienThiTheThuVien();
                    xoaTrangTheThuVien();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Thêm thất bại", "Thông báo", 0, imgTB.getImgError());
                }

            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnSearchTTVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchTTVMouseClicked
        showFindThe();
        hideGiaHan();
        emptyTXTThe();
    }//GEN-LAST:event_btnSearchTTVMouseClicked

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        btnSave.setEnabled(true);
        btnUpdate.setEnabled(false);
        hideGiaHan();
        String maThe = taoMaTheThuVienTuDong();
        txtMaThe.setText(maThe);

        String date[] = this.dateNow.split("-");
        String day = date[2];
        String month = date[1];
        String year = date[0];
        String date2 = day + "-" + month + "-" + year;
        //set select cho cbb ngày đăng ký
        int indexD = hxl.timViTriVctcbb(vctCBBNgayDK, day);
        int indexMM = hxl.timViTriVctcbb(vctCBBThangDK, month);
        int indexYYYY = hxl.timViTriVctcbb(vctCBBNamDK, year);
        cbbNgayDK.setSelectedIndex(indexD);
        cbbThangDK.setSelectedIndex(indexMM);
        cbbNamDK.setSelectedIndex(indexYYYY);
        JOptionPane.showMessageDialog(btnComplete, "Tự động tạo mã: " + maThe + "\nNgày đăng ký: " + date2 + "\nTiếp tục nhập các thông tin còn lại sau đó SAVE.","Thông báo",1,imgTB.getImgCompleteYellow());

    }//GEN-LAST:event_btnNewActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String maTV = txtMaThe.getText().trim();
        //Kiểm tra khi mã thẻ ko tồn tại
        boolean ktNaThe = false;//không tồn tại
        ArrayList<TheThuVien> lstTTV = ttvDAO.listTheThuVien();
        for (TheThuVien ttv : lstTTV) {
            if (ttv.getSoThe().equals(maTV)) {
                ktNaThe = true;//Tồn tại có thể cập nhật
                break;
            }
        }
        if (ktNaThe == false) {
            JOptionPane.showMessageDialog(rootPane, "Mã thẻ không tồn tại.", "Thông báo", 0, imgTB.getImgWarning());
        } else {
            String ghiChu = txtGhiChuTTV.getText().trim();
            /////ham kiem tra ngay
            //Ngày đăng ký
            String ngayDK = cbbNgayDK.getSelectedItem().toString();
            String thangDK = cbbThangDK.getSelectedItem().toString();
            String namDK = cbbNamDK.getSelectedItem().toString();
            String dateDK = namDK + "-" + thangDK + "-" + ngayDK;
            String dateDK1 = ngayDK + "-" + thangDK + "-" + namDK;

            //Ngày hết hạn
            String ngayHH = cbbNgayHH.getSelectedItem().toString();
            String thangHH = cbbThangHH.getSelectedItem().toString();
            String namHH = cbbNamHH.getSelectedItem().toString();
            String dateHH = namHH + "-" + thangHH + "-" + ngayHH;
            String dateHH1 = ngayHH + "-" + thangHH + "-" + namHH;

            //Kiểm tra ngày hợp lệ
//            if (hxl.kTraNgayHopLe(Integer.parseInt(ngayDK), Integer.parseInt(thangDK), Integer.parseInt(namDK)) == false) {
//                JOptionPane.showMessageDialog(rootPane, "Ngày bắt đầu: '" + dateDK1 + "' không hợp lệ", "Thất bại", 0);
//            } else 
            if (hxl.kTraNgayHopLe(Integer.parseInt(ngayHH), Integer.parseInt(thangHH), Integer.parseInt(namHH)) == false) {
                JOptionPane.showMessageDialog(rootPane, "Ngày kết thúc: '" + dateHH1 + "' không hợp lệ", "Thất bại", 0, imgTB.getImgWarning());
            } else {
                // kiểm tra ngày kết thúc phải lớn hơn ngày bắt đầu
                if (hxl.ktraNgay(dateDK, dateHH) == false) {
                    JOptionPane.showMessageDialog(rootPane, "Ngày hết hạn không được nhỏ hơn ngày đăng ký '" + dateDK1 + "'.", "Thất bại", 0, imgTB.getImgWarning());
                } else {
                    //hợp lệ
                    TheThuVien t = new TheThuVien(maTV, dateDK, dateHH, ghiChu);
                    if (ttvDAO.capNhatTheThuVien(t) == false) {
                        JOptionPane.showMessageDialog(rootPane, "Chưa được cập nhật\nVui lòng kiểm tra lại thông tin", "Thất bại", 0, imgTB.getImgWarning());

                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công", "Thông báo", 1, imgTB.getImgCompleteGreen());
                        hienThiTheThuVien();
                        xoaTrangTheThuVien();
                        txtMaThe.requestFocus();
                    }

                }
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtSoThangGiaHanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoThangGiaHanKeyReleased
        char c = evt.getKeyChar();
        int phim = evt.getKeyCode();
        String s = txtSoThangGiaHan.getText();
        if (s.length() > 0 && phim != 8 & !(c == KeyEvent.VK_ENTER) && !(c >= '0' && c <= '9') && phim != 37 && phim != 39 && phim != 38 && phim != 40) {
            StringBuilder sb = new StringBuilder();
            sb.append(s);
            sb.deleteCharAt(s.length() - 1);
            s = sb.toString();
            txtSoThangGiaHan.setText(s);
        }
        if (hxl.kTraChuoiIsNumber(txtSoThangGiaHan.getText()) == false) {
            lbTBFiaHan.setText("Gia hạn không hợp lệ");
        } else {
            lbTBFiaHan.setText("");
        }
    }//GEN-LAST:event_txtSoThangGiaHanKeyReleased

    private void txtHanTheKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHanTheKeyReleased
        char c = evt.getKeyChar();
        int phim = evt.getKeyCode();
        String s = txtHanThe.getText();
        if (s.length() > 0 && phim != 8 & !(c == KeyEvent.VK_ENTER) && !(c >= '0' && c <= '9') && phim != 37 && phim != 39 && phim != 38 && phim != 40) {
            StringBuilder sb = new StringBuilder();
            sb.append(s);
            sb.deleteCharAt(s.length() - 1);
            s = sb.toString();
            txtHanThe.setText(s);
        }

    }//GEN-LAST:event_txtHanTheKeyReleased

    private void btnTheHetHanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTheHetHanActionPerformed
        showTheHetHan();
        hideGiaHan();
    }//GEN-LAST:event_btnTheHetHanActionPerformed

    private void btnXoaSearchTTVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSearchTTVActionPerformed
        hienThiTheThuVien();
        txtSearchTTV.setText("");
        hideGiaHan();
        emptyTXTThe();
    }//GEN-LAST:event_btnXoaSearchTTVActionPerformed

    private void btnGiaHanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiaHanActionPerformed
        int soThangGiaHan = 0;
        String maThe = txtMaThe.getText();
        String gh = txtSoThangGiaHan.getText().trim();
        if (gh.length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Không được gia hạn trống.", "Thông báo", 0, imgTB.getImgWarning());
            txtSoThangGiaHan.requestFocus();
        } else if (hxl.kTraChuoiIsNumber(gh) == false) {
            JOptionPane.showMessageDialog(rootPane, "Số tháng gia hạn không hợp lệ.", "Thông báo", 0, imgTB.getImgWarning());
            txtSoThangGiaHan.requestFocus();
        } else {
            soThangGiaHan = Integer.parseInt(gh);
            ttvDAO.giaHanTheThuVien(maThe, soThangGiaHan);
            JOptionPane.showMessageDialog(rootPane, "Đã gia hạn thêm: " + soThangGiaHan + " tháng cho thẻ:" + maThe, "Thông báo", 1, imgTB.getImgCompleteGreen());
            hienThiTheThuVien();
            txtSoThangGiaHan.setText("");
            hideGiaHan();
        }
    }//GEN-LAST:event_btnGiaHanActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
//        showSach(vctHeadS, vctDataS);
//        showDocGia(vctHeadDG, vctDataDG);
//        showPhieuMuon(vctHeadMT, vctDataDSMT);
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void btnSearchDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchDGActionPerformed

    }//GEN-LAST:event_btnSearchDGActionPerformed

    public String taoMaMuonTraTuDong()//Tạo mã hóa dơn tự động từ 'HD000000' -> 'HD999999'
    {
        String maMT = "";
        maMT = mtDAO.layMaMuonTraCuoiCung();
        ArrayList<MuonTra> lstMT = mtDAO.listMuonTra();
        if (lstMT.size() <= 0) {
            maMT = "MT0000000";
        } else {
            int ma = Integer.parseInt(maMT);
            int ma2 = ma + 1;
            if (ma >= 0 && ma < 9) {
                maMT = "MT000000" + String.valueOf(ma2);
            } else if (ma >= 9 && ma < 99) {
                maMT = "MT00000" + String.valueOf(ma2);
            } else if (ma >= 99 && ma < 999) {
                maMT = "MT0000" + String.valueOf(ma2);
            } else if (ma >= 999 && ma < 9999) {
                maMT = "MT000" + String.valueOf(ma2);
            } else if (ma >= 9999 && ma < 99999) {
                maMT = "MT00" + String.valueOf(ma2);
            } else if (ma >= 99999 && ma < 999999) {
                maMT = "MT0" + String.valueOf(ma2);
            }
        }
        return maMT;
    }

    public String taoMaTheThuVienTuDong()//Sạo mã thẻ tự động từ 'T0000000' -> 'HD9999999'
    {
        String maThe = "";
        maThe = ttvDAO.layMaTheCuoiCung();
        ArrayList<TheThuVien> lstTTV = ttvDAO.listTheThuVien();
        if (lstTTV.size() <= 0) {
            maThe = "T0000000";
        } else {
            int ma = Integer.parseInt(maThe);
            int ma2 = ma + 1;
            if (ma >= 0 && ma < 9) {
                maThe = "T000000" + String.valueOf(ma2);
            } else if (ma >= 9 && ma < 99) {
                maThe = "T00000" + String.valueOf(ma2);
            } else if (ma >= 99 && ma < 999) {
                maThe = "T0000" + String.valueOf(ma2);
            } else if (ma >= 999 && ma < 9999) {
                maThe = "T000" + String.valueOf(ma2);
            } else if (ma >= 9999 && ma < 99999) {
                maThe = "T00" + String.valueOf(ma2);
            } else if (ma >= 99999 && ma < 999999) {
                maThe = "T0" + String.valueOf(ma2);
            }
        }
        return maThe;
    }

    void hienThiTheThuVien() {
        dfm = (DefaultTableModel) tbTheThuVien.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHeadTTV.clear();
        vctHeadTTV.add("Mã thẻ");
        vctHeadTTV.add("Ngày bắt đầu");
        vctHeadTTV.add("Ngày kết thúc");
        vctHeadTTV.add("Ghi chú");

        //add từng dòng của bảng
        ArrayList<TheThuVien> lstTTV = ttvDAO.listTheThuVien();
        for (TheThuVien ttv : lstTTV) {
            Vector vctRow = new Vector();
            vctRow.add(ttv.getSoThe());//Cột 1
            vctRow.add(ttv.getNgayBatDau());//Cột 2
            vctRow.add(ttv.getNgayKetThuc());//Cột 3
            vctRow.add(ttv.getGhiChu());//Cột 4

            vctDataTTV.add(vctRow);
        }
        tbTheThuVien.setModel(new DefaultTableModel(vctDataTTV, vctHeadTTV));
    }

    void layCBBDMY() {
        //Lấy combobox ngày đăng ký
        hxl.layNgayThangNam(vctCBBNgayDK, vctCBBThangDK, vctCBBNamDK);
        cbbNgayDK.setModel(new DefaultComboBoxModel(vctCBBNgayDK));
        cbbThangDK.setModel(new DefaultComboBoxModel(vctCBBThangDK));
        cbbNamDK.setModel(new DefaultComboBoxModel(vctCBBNamDK));

        //Lấy combobox ngày hết hạn
        hxl.layNgayThangNam(vctCBBNgayHH, vctCBBThangHH, vctCBBNamHH);
        cbbNgayHH.setModel(new DefaultComboBoxModel(vctCBBNgayHH));
        cbbThangHH.setModel(new DefaultComboBoxModel(vctCBBThangHH));
        cbbNamHH.setModel(new DefaultComboBoxModel(vctCBBNamHH));

    }

    void layNgayConLai() {
        cbbNgayDK.getSelectedItem();
        cbbThangDK.getSelectedItem();
        cbbNamDK.getSelectedItem();
        String NgayDK = cbbNgayDK.getSelectedItem() + "-" + cbbThangDK.getSelectedItem() + "-" + cbbNamDK.getSelectedItem();
        String NgayHH = cbbNgayHH.getSelectedItem() + "-" + cbbThangHH.getSelectedItem() + "-" + cbbNamHH.getSelectedItem();
        txtHanThe.setText(ttvDAO.soNgayConLai(NgayDK, NgayHH) + "");
    }

    public void xoaTrangTheThuVien() {
        txtMaThe.setText("");
        txtGhiChu.setText("");
        txtHanThe.setText("");
        txtSoThangGiaHan.setText("");

        txtMaThe.requestFocus();
    }

    public void hideCBB() {
        cbbNamDK.setEnabled(false);
        cbbNgayDK.setEnabled(false);
        cbbThangDK.setEnabled(false);
        btnUpdate.setEnabled(false);
        btnSave.setEnabled(false);
        txtHanThe.setEnabled(false);
    }

    void showTheHetHan() {
        dfm = (DefaultTableModel) tbTheThuVien.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHeadTTV.clear();
        vctDataTTV.clear();
        vctHeadTTV.add("Mã thẻ");
        vctHeadTTV.add("Ngày bắt đầu");
        vctHeadTTV.add("Ngày kết thúc");
        vctHeadTTV.add("Ghi chú");

        //add từng dòng của bảng
        ArrayList<TheThuVien> lstTTV = ttvDAO.listTheThuVien();

        for (TheThuVien ttv : lstTTV) {//add đọc giả
            if (ttv.getNgayKetThuc().compareTo(dateNow) < 0) {
                Vector vctRow = new Vector();
                vctRow.add(ttv.getSoThe());//Cột 1
                vctRow.add(ttv.getNgayBatDau());//Cột 2
                vctRow.add(ttv.getNgayKetThuc());//Cột 3
                vctRow.add(ttv.getGhiChu());//Cột 4
                vctDataTTV.add(vctRow);
            }
        }
        tbTheThuVien.setModel(new DefaultTableModel(vctDataTTV, vctHeadTTV));
    }

    void showFindThe() {
        dfm = (DefaultTableModel) tbTheThuVien.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHeadTTV.clear();
        vctDataTTV.clear();
        vctHeadTTV.add("Mã thẻ");
        vctHeadTTV.add("Ngày bắt đầu");
        vctHeadTTV.add("Ngày kết thúc");
        vctHeadTTV.add("Ghi chú");

        //add từng dòng của bảng
        ArrayList<TheThuVien> lstTTV = ttvDAO.listTheThuVien();
        String find = txtSearchTTV.getText();
        for (TheThuVien ttv : lstTTV) {//add đọc giả
            if (ttv.getSoThe().contains(find) || ttv.getNgayBatDau().contains(find) || ttv.getNgayKetThuc().contains(find) || ttv.getGhiChu().contains(find)) {
                Vector vctRow = new Vector();
                vctRow.add(ttv.getSoThe());//Cột 1
                vctRow.add(ttv.getNgayBatDau());//Cột 2
                vctRow.add(ttv.getNgayKetThuc());//Cột 3
                vctRow.add(ttv.getGhiChu());//Cột 4
                vctDataTTV.add(vctRow);
            }
        }
        tbTheThuVien.setModel(new DefaultTableModel(vctDataTTV, vctHeadTTV));
    }

    void hideGiaHan() {
        btnGiaHan.setEnabled(false);
        txtSoThangGiaHan.setEnabled(false);
    }

    void showGiaHan() {
        btnGiaHan.setEnabled(true);
        txtSoThangGiaHan.setEnabled(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatCTPhieu;
    private javax.swing.JButton btnComplete;
    private javax.swing.JButton btnDaTra;
    private javax.swing.JButton btnFindMaSach;
    private javax.swing.JButton btnFindSoThe;
    private javax.swing.JButton btnGiaHan;
    private javax.swing.JButton btnLayMaSachTuDong;
    private javax.swing.JButton btnLuuPhieu;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNgayHienTai;
    private javax.swing.JButton btnResetCTP;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearchDG;
    private javax.swing.JButton btnSearchMT;
    private javax.swing.JButton btnSearchSach;
    private javax.swing.JButton btnSearchTTV;
    private javax.swing.JButton btnTaoMa;
    private javax.swing.JButton btnTheHetHan;
    private javax.swing.JButton btnThemPhieu;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnViPham;
    private javax.swing.JButton btnXoaCTP;
    private javax.swing.JButton btnXoaSearch;
    private javax.swing.JButton btnXoaSearchDG;
    private javax.swing.JButton btnXoaSearchMT;
    private javax.swing.JButton btnXoaSearchS;
    private javax.swing.JButton btnXoaSearchTTV;
    private javax.swing.JComboBox<String> cbbMaNV;
    private javax.swing.JComboBox<String> cbbMaSach;
    private javax.swing.JComboBox<String> cbbNamDK;
    private javax.swing.JComboBox<String> cbbNamHH;
    private javax.swing.JComboBox<String> cbbNamMuon;
    private javax.swing.JComboBox<String> cbbNamTra;
    private javax.swing.JComboBox<String> cbbNgayDK;
    private javax.swing.JComboBox<String> cbbNgayHH;
    private javax.swing.JComboBox<String> cbbNgayMuon;
    private javax.swing.JComboBox<String> cbbNgayTra;
    private javax.swing.JComboBox<String> cbbSoThe;
    private javax.swing.JComboBox<String> cbbThangDK;
    private javax.swing.JComboBox<String> cbbThangHH;
    private javax.swing.JComboBox<String> cbbThangMuon;
    private javax.swing.JComboBox<String> cbbThangTra;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbTBFiaHan;
    private javax.swing.JLabel lbTBSLInsert;
    private javax.swing.JLabel lbTBSLUP;
    private javax.swing.JLabel lbTBThemCT;
    private javax.swing.JLabel lb_tb;
    private javax.swing.JPanel pn_ctPhieuMuon;
    private javax.swing.JTable tbCTPhieuMuon;
    private javax.swing.JTable tbDocGia;
    private javax.swing.JTable tbMuonTra;
    private javax.swing.JTable tbPhieuMuon;
    private javax.swing.JTable tbSach;
    private javax.swing.JTable tbTheThuVien;
    private javax.swing.JTextField txtDangMuon;
    private javax.swing.JTextField txtFindMaSach;
    private javax.swing.JButton txtFindPhieuMuon;
    private javax.swing.JTextField txtFindSoThe;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtGhiChuTTV;
    private javax.swing.JTextField txtHanThe;
    private javax.swing.JTextField txtMaPhieuMuon;
    private javax.swing.JTextField txtMaSachUpd;
    private javax.swing.JTextField txtMaThe;
    private javax.swing.JTextField txtSearchDocGia;
    private javax.swing.JTextField txtSearchPM;
    private javax.swing.JTextField txtSearchPhieuMuon;
    private javax.swing.JTextField txtSearchSach;
    private javax.swing.JTextField txtSearchTTV;
    private javax.swing.JTextField txtSoLuongInsert;
    private javax.swing.JTextField txtSoLuongUpd;
    private javax.swing.JTextField txtSoThangGiaHan;
    // End of variables declaration//GEN-END:variables
}
