/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.DocGiaDAO;
import dao.HamXuLyChung;
import dao.KetNoiSQL;
import dao.NhaXuatBanDAO;
import dao.SachDAO;
import dao.TacGiaDAO;
import dao.TheLoaiDAO;
import dao.TheThuVienDAO;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import pojo.DocGia;
import pojo.ImageThongBao;
import pojo.NhaXuatBan;
import pojo.Sach;
import pojo.TacGia;
import pojo.TheLoai;
import pojo.TheThuVien;
import sun.security.x509.AttributeNameEnumeration;
import sun.swing.table.DefaultTableCellHeaderRenderer;

/**
 *
 * @author ADMIN
 */
public class frm_Sach extends javax.swing.JInternalFrame {

    /**
     * Creates new form frm_Sach
     */
    KetNoiSQL conSQL = new KetNoiSQL();
    HamXuLyChung hxl = new HamXuLyChung();
    SachDAO sDAO = new SachDAO();
    TacGiaDAO tgDAO = new TacGiaDAO();
    NhaXuatBanDAO nxbDAO = new NhaXuatBanDAO();
    DocGiaDAO dgDAO = new DocGiaDAO();
    TheThuVienDAO tttDAO = new TheThuVienDAO();
    TheLoaiDAO tlDAO = new TheLoaiDAO();
    ImageThongBao imgTB = new ImageThongBao();
    DefaultTableModel dfm = null;
    DefaultTableCellHeaderRenderer center = new DefaultTableCellHeaderRenderer();
    //Cho panel sach
    Vector vctHead = new Vector();
    Vector vctData = new Vector();
    Vector vctComBoBoxTheLoai = new Vector();
    Vector vctComBoBoxNamXB = new Vector();
    Vector vctComBoBoxTinhTrang = new Vector();
    Vector vctComBoBoxNXB = new Vector();
    Vector vctComBoBoxTacGia = new Vector();
    //Cho panel doc gia
    Vector vctHeadDG = new Vector();
    Vector vctDataDG = new Vector();
    Vector vctCbbGioiTinh = new Vector();
    Vector vctCbbMaThe = new Vector();
    //lay ngay
    Vector vctCbbNgay = new Vector();
    Vector vctCbbThang = new Vector();
    Vector vctCbbNam = new Vector();
    //Cho bảng tác giả
    Vector vctHeadTG = new Vector();
    Vector vctDataTG = new Vector();
    //Cho bảng NXB
    Vector vctHeadNXB = new Vector();
    Vector vctDataNXB = new Vector();
    //Cho bảng Thể loại
    Vector vctHeadTL = new Vector();
    Vector vctDataTL = new Vector();

    frm_ThemNhaXuatBan addNXB = new frm_ThemNhaXuatBan();
    frm_ThemTacGia frmTG = new frm_ThemTacGia();

    public frm_Sach() {
        initComponents();
        this.setTitle("Quản lý sách");
        this.setSize(1400, 800);
        this.resizable = false;
        this.pack();
        conSQL.open();
        txtMaSach.setEnabled(false);
        hienThiSach();
        layCBBFrmSach();

        layGioiTinh(vctCbbGioiTinh);
        cbbGioiTinh.setModel(new DefaultComboBoxModel(vctCbbGioiTinh));

        hxl.layNgayThangNam(vctCbbNgay, vctCbbThang, vctCbbNam);
        //add vào combobox ngày tháng năm sinh 
        cbbNgaySinh.setModel(new DefaultComboBoxModel(vctCbbNgay));
        cbbThangSinh.setModel(new DefaultComboBoxModel(vctCbbThang));
        cbbNamSinh.setModel(new DefaultComboBoxModel(vctCbbNam));
        //add vào combobox ngày tháng năm mở thẻ
        cbbNgayMoThe.setModel(new DefaultComboBoxModel(vctCbbNgay));
        cbbThangMoThe.setModel(new DefaultComboBoxModel(vctCbbThang));
        cbbNamMoThe.setModel(new DefaultComboBoxModel(vctCbbNam));
        //Lấy combobox thẻ thư viện
        layTheThuVien();

        cbbNgayMoThe.setEnabled(false);
        cbbThangMoThe.setEnabled(false);
        cbbNamMoThe.setEnabled(false);
        hienThiDocGia();

        showTacGia();//show tác giả
        showNXB();//show NXB
        showTheLoai();//show Thể loại
        hideMa();
    }

    void hienThiSach() {
        dfm = (DefaultTableModel) tbSach.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHead.clear();
        vctHead.add("Mã sách");
        vctHead.add("Tên sách");
        vctHead.add("Thể loại");
        vctHead.add("Năm xuất bản");
        vctHead.add("Tác giả");
        vctHead.add("Số lượng");
        vctHead.add("Nhà xuất bản");
        vctHead.add("URL");
        //add từng dòng của bảng
        ArrayList<Sach> lstSach = sDAO.listSach();
        for (Sach s : lstSach) {
            Vector vctRow = new Vector();
            vctRow.add(s.getMaS());//Cột 1
            vctRow.add(s.getTenS());//Cột 2
            vctRow.add(s.getTheLoai());//Cột 3
            vctRow.add(s.getNamXB());//Cột 4
            vctRow.add(s.getTacGia());//Cột 5
            vctRow.add(s.getSoLuong());//Cột 6
            vctRow.add(s.getNhaXB());//Cột 7
            vctRow.add(s.getHinh());//Cột 8
            vctData.add(vctRow);//dòng 1 
        }
        tbSach.setModel(new DefaultTableModel(vctData, vctHead));
        hideBtnSach();
        setSizeTableSach();
    }

    void setSizeTableSach() {
        int[] lstSize = {100, 400, 150, 100, 180, 80, 230, 100};
        tbSach.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 8; i++) {
            tbSach.getColumnModel().getColumn(i).setPreferredWidth(lstSize[i]);
        }
    }

    void showIconForButtonDG() {
        ImageIcon imgNew = new ImageIcon("src/Icon/iconPlus.png");
        btnThemDG.setIcon(imgNew);
        ImageIcon imgSave = new ImageIcon("src/Icon/iconSave.png");
        btnThemDG.setIcon(imgSave);
        ImageIcon imgUpd = new ImageIcon("src/Icon/update.png");
        btnThemDG.setIcon(imgUpd);
        ImageIcon imgDelete = new ImageIcon("src/Icon/delete2.png");
        btnThemDG.setIcon(imgDelete);
    }

    void hienThiDocGia() {
        dfm = (DefaultTableModel) tbDocGia.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHeadDG.clear();
        vctHeadDG.add("Mã đọc giả");
        vctHeadDG.add("Tên đọc giả");
        vctHeadDG.add("Địa chỉ");
        vctHeadDG.add("Ngày sinh");
        vctHeadDG.add("Giới tính");
        vctHeadDG.add("Email");
        vctHeadDG.add("Số điện thoại");
        vctHeadDG.add("Số thẻ");
        vctHeadDG.add("Ngày lập thẻ");

        //add từng dòng của bảng
        ArrayList<DocGia> lstDG = dgDAO.listDocGia();
        for (DocGia dg : lstDG) {
            Vector vctRow = new Vector();
            vctRow.add(dg.getMaDG());//Cột 1
            vctRow.add(dg.getTenDG());//Cột 2
            vctRow.add(dg.getDiaChi());//Cột 3
            vctRow.add(dg.getNgaySinh());//Cột 4
            vctRow.add(dg.getGioiTinh());//Cột 5
            vctRow.add(dg.getEmail());//Cột 6
            vctRow.add(dg.getSdt());//Cột 7
            vctRow.add(dg.getSoThe());//Cột 8
            vctRow.add(dg.getNgayBatDau());//Cột 9
            vctDataDG.add(vctRow);//dòng 1 
        }
        tbDocGia.setModel(new DefaultTableModel(vctDataDG, vctHeadDG));
        txtMaDG.setEnabled(false);
        hideBtnDG();
        //set width cho từng cột
        setSizeTableDG();

    }

    void setSizeTableDG() {
        int[] a = {120, 230, 160, 130, 70, 220, 130, 120, 130};
        tbDocGia.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 9; i++) {
            tbDocGia.getColumnModel().getColumn(i).setPreferredWidth(a[i]);
        }
    }

    void hienThiDocGiaSearch() {
        dfm = (DefaultTableModel) tbDocGia.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHeadDG.clear();
        vctHeadDG.add("Mã đọc giả");
        vctHeadDG.add("Tên đọc giả");
        vctHeadDG.add("Địa chỉ");
        vctHeadDG.add("Ngày sinh");
        vctHeadDG.add("Giới tính");
        vctHeadDG.add("Email");
        vctHeadDG.add("Số điện thoại");
        vctHeadDG.add("Số thẻ");
        vctHeadDG.add("Ngày lập thẻ");

        //add từng dòng của bảng
        String find = txtSearchDocGia.getText();
        ArrayList<DocGia> lstDG = dgDAO.listDocGia();
        for (DocGia dg : lstDG) {
            //dùng s1.contains(s2): kiểm tra chuỗi s2 tồn tại trong s1
            if (dg.getMaDG().contains(find) || dg.getTenDG().contains(find) || dg.getDiaChi().contains(find) || dg.getSoThe().contains(find) || dg.getSdt().contains(find) || dg.getEmail().contains(find) || dg.getGioiTinh().contains(find)) {
                Vector vctRow = new Vector();
                vctRow.add(dg.getMaDG());//Cột 1
                vctRow.add(dg.getTenDG());//Cột 2
                vctRow.add(dg.getDiaChi());//Cột 3
                vctRow.add(dg.getNgaySinh());//Cột 4
                vctRow.add(dg.getGioiTinh());//Cột 5
                vctRow.add(dg.getEmail());//Cột 6
                vctRow.add(dg.getSdt());//Cột 7
                vctRow.add(dg.getSoThe());//Cột 8
                vctRow.add(dg.getNgayBatDau());//Cột 9
                vctDataDG.add(vctRow);//dòng 1 
            }
        }
        tbDocGia.setModel(new DefaultTableModel(vctDataDG, vctHeadDG));
        setSizeTableDG();
    }

    public void layGioiTinh(Vector gt) {
        gt.add("Nam");
        gt.add("Nữ");
    }

    void layTheThuVien() {
        ArrayList<TheThuVien> lstTTT = tttDAO.listTheThuVien();
        for (TheThuVien t : lstTTT) {
            vctCbbMaThe.add(t.getSoThe());
        }
        cbbMaThe.setModel(new DefaultComboBoxModel(vctCbbMaThe));
    }

    void layCBBTheLoai() {
        vctComBoBoxTheLoai.clear();
        //Lấy combobox thể loại
        conSQL.layCBBTheLoaiSach(vctComBoBoxTheLoai);
        cbbTheLoai.setModel(new DefaultComboBoxModel(vctComBoBoxTheLoai));
    }

    void layCBBNXB() {
        vctComBoBoxNXB.clear();
        //Lấy combobox nhà xuất bản
        ArrayList<NhaXuatBan> lstNXB = nxbDAO.listNXB();
        for (NhaXuatBan nxb : lstNXB) {
            vctComBoBoxNXB.add(nxb.getTenNXB());
        }
        cbbNXB.setModel(new DefaultComboBoxModel(vctComBoBoxNXB));
    }

    void layCBBTacGia() {
        vctComBoBoxTacGia.clear();
        //Lấy combobox tác giả
        ArrayList<TacGia> lstTG = tgDAO.listTacGia();
        for (TacGia tg : lstTG) {
            vctComBoBoxTacGia.add(tg.getTenTG());
        }
        cbbTacGia.setModel(new DefaultComboBoxModel(vctComBoBoxTacGia));
    }

    void layCBBFrmSach() {
        //Lấy combobox thể loại
        conSQL.layCBBTheLoaiSach(vctComBoBoxTheLoai);
        cbbTheLoai.setModel(new DefaultComboBoxModel(vctComBoBoxTheLoai));
        //Lấy combobox năm xuất bản
        for (int i = 1980; i <= Year.now().getValue(); i++) {
            vctComBoBoxNamXB.add(i);
        }
        cbbNamXB.setModel(new DefaultComboBoxModel(vctComBoBoxNamXB));
        //Lấy combobox tình trạng
        vctComBoBoxTinhTrang.add("Còn");
        vctComBoBoxTinhTrang.add("Hết");
        cbbTinhTrang.setModel(new DefaultComboBoxModel(vctComBoBoxTinhTrang));
        //Lấy combobox tác giả
        ArrayList<TacGia> lstTG = tgDAO.listTacGia();
        for (TacGia tg : lstTG) {
            vctComBoBoxTacGia.add(tg.getTenTG());
        }
        cbbTacGia.setModel(new DefaultComboBoxModel(vctComBoBoxTacGia));
        //Lấy combobox nhà xuất bản
        ArrayList<NhaXuatBan> lstNXB = nxbDAO.listNXB();
        for (NhaXuatBan nxb : lstNXB) {
            vctComBoBoxNXB.add(nxb.getTenNXB());
        }
        cbbNXB.setModel(new DefaultComboBoxModel(vctComBoBoxNXB));
    }

    void showSachDaHet() {
        dfm = (DefaultTableModel) tbSach.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHead.clear();
        vctData.clear();
        vctHead.add("Mã sách");
        vctHead.add("Tên sách");
        vctHead.add("Thể loại");
        vctHead.add("Năm xuất bản");
        vctHead.add("Tác giả");
        vctHead.add("Số lượng");
        vctHead.add("Nhà xuất bản");
        vctHead.add("URL");
        //add từng dòng của bảng
        ArrayList<Sach> lstSach = sDAO.listSach();
        for (Sach s : lstSach) {//add đọc giả
            if (s.getSoLuong() == 0) {
                Vector vctRow = new Vector();
                vctRow.add(s.getMaS());//Cột 1
                vctRow.add(s.getTenS());//Cột 2
                vctRow.add(s.getTheLoai());//Cột 3
                vctRow.add(s.getNamXB());//Cột 4
                vctRow.add(s.getTacGia());//Cột 5
                vctRow.add(s.getSoLuong());//Cột 6
                vctRow.add(s.getNhaXB());//Cột 7
                vctRow.add(s.getHinh());//Cột 8
                vctData.add(vctRow);//dòng 1
            }
        }
        tbSach.setModel(new DefaultTableModel(vctData, vctHead));
        setSizeTableSach();
    }

    void showTacGia() {
        dfm = (DefaultTableModel) tbTG.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHeadTG.clear();
        vctDataTG.clear();
        vctHeadTG.add("Mã#");
        vctHeadTG.add("Tên tác giả");
        vctHeadTG.add("Website");
        vctHeadTG.add("Ghi chú");

        //add từng dòng của bảng
        ArrayList<TacGia> lstTG = tgDAO.listTacGia();
        for (TacGia tg : lstTG) {//add tác giả
            Vector vctRow = new Vector();
            vctRow.add(tg.getMaTG());//Cột 1
            vctRow.add(tg.getTenTG());//Cột 2
            vctRow.add(tg.getWebsite());//Cột 3
            vctRow.add(tg.getGhiChu());//Cột 4
            vctDataTG.add(vctRow);//dòng 1

        }
        tbTG.setModel(new DefaultTableModel(vctDataTG, vctHeadTG));
        hideBtnTG();
    }

    void showFindTacGia() {
        dfm = (DefaultTableModel) tbTG.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHeadTG.clear();
        vctDataTG.clear();
        vctHeadTG.add("Mã#");
        vctHeadTG.add("Tên tác giả");
        vctHeadTG.add("Website");
        vctHeadTG.add("Ghi chú");
        String find = txtFindTG.getText();
        //add từng dòng của bảng
        ArrayList<TacGia> lstTG = tgDAO.listTacGia();
        for (TacGia tg : lstTG) {//add tác giả
            if (tg.getMaTG().contains(find) || tg.getTenTG().contains(find) || tg.getWebsite().contains(find) || tg.getGhiChu().contains(find)) {
                Vector vctRow = new Vector();
                vctRow.add(tg.getMaTG());//Cột 1
                vctRow.add(tg.getTenTG());//Cột 2
                vctRow.add(tg.getWebsite());//Cột 3
                vctRow.add(tg.getGhiChu());//Cột 4
                vctDataTG.add(vctRow);//dòng 1
            }

        }
        tbTG.setModel(new DefaultTableModel(vctDataTG, vctHeadTG));
        hideBtnTG();
    }

    void showNXB() {
        dfm = (DefaultTableModel) tbNXB.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHeadNXB.clear();
        vctDataNXB.clear();
        vctHeadNXB.add("Mã#");
        vctHeadNXB.add("Tên NXB");
        vctHeadNXB.add("Địa chỉ");
        vctHeadNXB.add("Email");
        vctHeadNXB.add("Đại diện");

        //add từng dòng của bảng
        ArrayList<NhaXuatBan> lstNXB = nxbDAO.listNXB();
        for (NhaXuatBan nxb : lstNXB) {//add NXB
            Vector vctRow = new Vector();
            vctRow.add(nxb.getMaNXB());//Cột 1
            vctRow.add(nxb.getTenNXB());//Cột 2
            vctRow.add(nxb.getDiaChi());//Cột 3
            vctRow.add(nxb.getEmail());//Cột 4
            vctRow.add(nxb.getThongTinNguoiDaiDien());//Cột 5
            vctDataNXB.add(vctRow);//dòng 1

        }
        tbNXB.setModel(new DefaultTableModel(vctDataNXB, vctHeadNXB));
        hideBtnNXB();
    }

    void showFindNXB() {
        dfm = (DefaultTableModel) tbNXB.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHeadNXB.clear();
        vctDataNXB.clear();
        vctHeadNXB.add("Mã#");
        vctHeadNXB.add("Tên NXB");
        vctHeadNXB.add("Địa chỉ");
        vctHeadNXB.add("Email");
        vctHeadNXB.add("Đại diện");
        String find = txtFindNXB.getText();
        //add từng dòng của bảng
        ArrayList<NhaXuatBan> lstNXB = nxbDAO.listNXB();
        for (NhaXuatBan nxb : lstNXB) {//add NXB
            if (nxb.getMaNXB().contains(find) || nxb.getTenNXB().contains(find) || nxb.getDiaChi().contains(find) || nxb.getThongTinNguoiDaiDien().contains(find) || nxb.getEmail().contains(find)) {
                Vector vctRow = new Vector();
                vctRow.add(nxb.getMaNXB());//Cột 1
                vctRow.add(nxb.getTenNXB());//Cột 2
                vctRow.add(nxb.getDiaChi());//Cột 3
                vctRow.add(nxb.getEmail());//Cột 4
                vctRow.add(nxb.getThongTinNguoiDaiDien());//Cột 5
                vctDataNXB.add(vctRow);//dòng 1
            }

        }
        tbNXB.setModel(new DefaultTableModel(vctDataNXB, vctHeadNXB));
        hideBtnNXB();
    }

    void showTheLoai() {
        dfm = (DefaultTableModel) tbTL.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHeadTL.clear();
        vctDataTL.clear();
        vctHeadTL.add("Mã#");
        vctHeadTL.add("Tên thể loại");

        //add từng dòng của bảng
        ArrayList<TheLoai> lstTL = tlDAO.listTheLoai();
        for (TheLoai tl : lstTL) {//add TL
            Vector vctRow = new Vector();
            vctRow.add(tl.getMaTL());//Cột 1
            vctRow.add(tl.getTenTL());//Cột 2
            vctDataTL.add(vctRow);//dòng 1
        }
        tbTL.setModel(new DefaultTableModel(vctDataTL, vctHeadTL));
        hideBtnTL();
//        for (int i = 0; i < 2; i++) {
//            this.center.setHorizontalAlignment(jLabel1.CENTER);
//            tbTL.getColumnModel().getColumn(i).setCellRenderer(this.center);
//        }
////        tbTL.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
////        tbTL.getColumnModel().getColumn(0).setPreferredWidth(40);
////        tbTL.getColumnModel().getColumn(1).setPreferredWidth(200);
    }

    void setSizeTableTL() {

    }

    void showFindTheLoai() {
        dfm = (DefaultTableModel) tbTL.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHeadTL.clear();
        vctDataTL.clear();
        vctHeadTL.add("Mã#");
        vctHeadTL.add("Tên thể loại");
        String find = txtFindTL.getText();
        //add từng dòng của bảng
        ArrayList<TheLoai> lstTL = tlDAO.listTheLoai();
        for (TheLoai tl : lstTL) {//add TL
            if (tl.getMaTL().contains(find) || tl.getTenTL().contains(find)) {
                Vector vctRow = new Vector();
                vctRow.add(tl.getMaTL());//Cột 1
                vctRow.add(tl.getTenTL());//Cột 2
                vctDataTL.add(vctRow);//dòng 1
            }
        }
        tbTL.setModel(new DefaultTableModel(vctDataTL, vctHeadTL));
        hideBtnTL();
    }

    void hideMa() {
        txtMaNXB.setEnabled(false);
        txtMaTG.setEnabled(false);
        txtMaTL.setEnabled(false);
    }

    void hideBtnDG() {
        btnLuuDG.setEnabled(false);
        btnCapNhatDG.setEnabled(false);
        btnXoaDG.setEnabled(false);
    }

    void showBtnDG() {
        btnLuuDG.setEnabled(true);
        btnCapNhatDG.setEnabled(true);
        btnXoaDG.setEnabled(true);
    }

    void hideCBBDG() {
        cbbNgayMoThe.setEnabled(false);
        cbbThangMoThe.setEnabled(false);
        cbbNamMoThe.setEnabled(false);
    }

    void showCBBDG() {
        cbbNgayMoThe.setEnabled(true);
        cbbThangMoThe.setEnabled(true);
        cbbNamMoThe.setEnabled(true);
    }

    void hideBtnSach() {
        btnLuuSach.setEnabled(false);
        btnXoaSach.setEnabled(false);
        btnCapNhatSach.setEnabled(false);
    }

    void showBtnSach() {
        btnLuuSach.setEnabled(true);
        btnXoaSach.setEnabled(true);
        btnCapNhatSach.setEnabled(true);
    }

    void emptyTXTSach() {
        txtMaSach.setText("");
        txtTenSach.setText("");
        txtSoLuongSach.setText("");
        txtImg.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel14 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        txtSearchDocGia = new javax.swing.JTextField();
        btnSearchDocGia = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        txtTenDG = new javax.swing.JTextField();
        txtDiaChiDG = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        txtEmail = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cbbGioiTinh = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        cbbNgaySinh = new javax.swing.JComboBox<>();
        cbbThangSinh = new javax.swing.JComboBox<>();
        cbbNamSinh = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        cbbNgayMoThe = new javax.swing.JComboBox<>();
        cbbThangMoThe = new javax.swing.JComboBox<>();
        cbbNamMoThe = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbbMaThe = new javax.swing.JComboBox<>();
        btnThemThe = new javax.swing.JButton();
        btnResetThe = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtMaDG = new javax.swing.JTextField();
        btnLayMaDG = new javax.swing.JButton();
        lbTBSDT = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDocGia = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        btnThemDG = new javax.swing.JButton();
        btnLuuDG = new javax.swing.JButton();
        btnCapNhatDG = new javax.swing.JButton();
        btnXoaDG = new javax.swing.JButton();
        btnXoaSearchDG = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbNXB = new javax.swing.JTable();
        jPanel25 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        txtMaNXB = new javax.swing.JTextField();
        txtTenNXB = new javax.swing.JTextField();
        txtEmailNXB = new javax.swing.JTextField();
        txtDaiDienNXB = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDCNXB = new javax.swing.JTextArea();
        jLabel30 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        btnNewNXB = new javax.swing.JButton();
        btnSaveNXB = new javax.swing.JButton();
        btnUpNXB = new javax.swing.JButton();
        btnDeleteNXB = new javax.swing.JButton();
        lbtbNXB = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbTG = new javax.swing.JTable();
        jPanel26 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        txtMaTG = new javax.swing.JTextField();
        txtTenTG = new javax.swing.JTextField();
        txtWebsite = new javax.swing.JTextField();
        txtGhiChuTG = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        btnNewTG = new javax.swing.JButton();
        btnSaveTG = new javax.swing.JButton();
        btnUpTG = new javax.swing.JButton();
        btnDeleteTG = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbTL = new javax.swing.JTable();
        jPanel24 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        txtMaTL = new javax.swing.JTextField();
        txtTenTL = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        btnNewTL = new javax.swing.JButton();
        btnSaveTL = new javax.swing.JButton();
        btnUpTL = new javax.swing.JButton();
        btnDeleteTL = new javax.swing.JButton();
        btnFindMaSach = new javax.swing.JButton();
        txtFindNXB = new javax.swing.JTextField();
        btnFindMaSach1 = new javax.swing.JButton();
        txtFindTG = new javax.swing.JTextField();
        txtFindTL = new javax.swing.JTextField();
        btnFindMaSach2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtSearchSach = new javax.swing.JTextField();
        btnSearchSach = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnSachDaHet = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtSoLuongSach = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cbbTacGia = new javax.swing.JComboBox<>();
        jPanel19 = new javax.swing.JPanel();
        cbbTheLoai = new javax.swing.JComboBox<>();
        cbbNXB = new javax.swing.JComboBox<>();
        jPanel20 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        cbbNamXB = new javax.swing.JComboBox<>();
        cbbTinhTrang = new javax.swing.JComboBox<>();
        btnThemTL = new javax.swing.JButton();
        btnResetTL = new javax.swing.JButton();
        btnThemNXB = new javax.swing.JButton();
        btnResetNXB = new javax.swing.JButton();
        btnThemTacGia = new javax.swing.JButton();
        btnResetTG = new javax.swing.JButton();
        txtMaSach = new javax.swing.JTextField();
        btnLayMaSach = new javax.swing.JButton();
        txtTenSach = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        txtImg = new javax.swing.JTextField();
        lbtbSL = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSach = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        btnThemSach = new javax.swing.JButton();
        btnLuuSach = new javax.swing.JButton();
        btnCapNhatSach = new javax.swing.JButton();
        btnXoaSach = new javax.swing.JButton();
        lbHinh = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTabbedPane1.setBackground(new java.awt.Color(245, 237, 237));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        txtSearchDocGia.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        btnSearchDocGia.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnSearchDocGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Zoom-icon.png"))); // NOI18N
        btnSearchDocGia.setText("Search");
        btnSearchDocGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchDocGiaActionPerformed(evt);
            }
        });

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin đọc giả", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(204, 0, 153))); // NOI18N

        jPanel12.setLayout(new java.awt.GridLayout(2, 1, 0, 10));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setText("Tên đọc giả:");
        jPanel12.add(jLabel4);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setText("Địa chỉ:");
        jPanel12.add(jLabel5);

        jPanel13.setLayout(new java.awt.GridLayout(2, 1, 0, 10));

        txtTenDG.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTenDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenDGActionPerformed(evt);
            }
        });
        jPanel13.add(txtTenDG);

        txtDiaChiDG.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel13.add(txtDiaChiDG);

        jPanel17.setLayout(new java.awt.GridLayout(2, 1, 0, 10));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setText("Email:");
        jPanel17.add(jLabel9);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel10.setText("Điện thoại:");
        jPanel17.add(jLabel10);

        jPanel18.setLayout(new java.awt.GridLayout(2, 1, 0, 10));

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });
        jPanel18.add(txtEmail);

        txtSoDienThoai.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtSoDienThoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoDienThoaiKeyReleased(evt);
            }
        });
        jPanel18.add(txtSoDienThoai);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("Giới tính:");

        cbbGioiTinh.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cbbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel21.setText("Ngày sinh:");

        cbbNgaySinh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbNgaySinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbThangSinh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbThangSinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbNamSinh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbNamSinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel22.setText("Ngày lập thẻ:");

        cbbNgayMoThe.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbNgayMoThe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbThangMoThe.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbThangMoThe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbNamMoThe.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbNamMoThe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setText("Mã số thẻ:");

        cbbMaThe.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cbbMaThe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnThemThe.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        btnThemThe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconThem2.png"))); // NOI18N
        btnThemThe.setText("Thêm thẻ");
        btnThemThe.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnThemThe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemTheMouseClicked(evt);
            }
        });

        btnResetThe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconReset3.png"))); // NOI18N
        btnResetThe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetTheMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setText("Mã đọc giả:");

        txtMaDG.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        btnLayMaDG.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        btnLayMaDG.setText("Lấy mã");
        btnLayMaDG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLayMaDGMouseClicked(evt);
            }
        });
        btnLayMaDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLayMaDGActionPerformed(evt);
            }
        });

        lbTBSDT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbTBSDT.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cbbNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbbThangSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbbNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtMaDG)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnLayMaDG))
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(31, 31, 31)
                        .addComponent(cbbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(88, 88, 88)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(cbbMaThe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThemThe, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnResetThe, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(12, 12, 12)
                        .addComponent(cbbNgayMoThe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbThangMoThe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbNamMoThe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbTBSDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE))))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaDG, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLayMaDG))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbThangSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addContainerGap(30, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
                        .addGap(8, 8, 8)
                        .addComponent(lbTBSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbMaThe)
                            .addComponent(btnThemThe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnResetThe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 22, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbThangMoThe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbNamMoThe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbNgayMoThe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38))))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách đọc giả", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        tbDocGia.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
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
        jScrollPane2.setViewportView(tbDocGia);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1325, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thao tác", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(153, 0, 153))); // NOI18N

        jPanel16.setLayout(new java.awt.GridLayout(4, 1, 0, 10));

        btnThemDG.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnThemDG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconPlus.png"))); // NOI18N
        btnThemDG.setText("New");
        btnThemDG.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnThemDG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemDGMouseClicked(evt);
            }
        });
        btnThemDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDGActionPerformed(evt);
            }
        });
        jPanel16.add(btnThemDG);

        btnLuuDG.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnLuuDG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconSave.png"))); // NOI18N
        btnLuuDG.setText("Save");
        btnLuuDG.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLuuDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuDGActionPerformed(evt);
            }
        });
        jPanel16.add(btnLuuDG);

        btnCapNhatDG.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnCapNhatDG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/update.png"))); // NOI18N
        btnCapNhatDG.setText("Update");
        btnCapNhatDG.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCapNhatDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatDGActionPerformed(evt);
            }
        });
        jPanel16.add(btnCapNhatDG);

        btnXoaDG.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnXoaDG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/delete2.png"))); // NOI18N
        btnXoaDG.setText("Delete");
        btnXoaDG.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnXoaDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDGActionPerformed(evt);
            }
        });
        jPanel16.add(btnXoaDG);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        btnXoaSearchDG.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        btnXoaSearchDG.setText("X");
        btnXoaSearchDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSearchDGActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(38, 38, 38)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtSearchDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaSearchDG)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearchDocGia)
                        .addGap(31, 31, 31))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchDocGia)
                    .addComponent(btnXoaSearchDG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lý đọc giả", jPanel2);

        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        jPanel7.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhà xuất bản", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 13))); // NOI18N
        jPanel22.setLayout(new java.awt.GridLayout(2, 1, 0, 10));

        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tbNXB.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        tbNXB.setModel(new javax.swing.table.DefaultTableModel(
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
        tbNXB.setRowHeight(25);
        tbNXB.setRowMargin(7);
        tbNXB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNXBMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbNXB);

        jPanel22.add(jScrollPane6);

        jPanel27.setLayout(new java.awt.GridLayout(4, 1, 0, 10));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jLabel2.setText("Mã:");
        jPanel27.add(jLabel2);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jLabel7.setText("Tên NXB:");
        jPanel27.add(jLabel7);

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jLabel20.setText("Email:");
        jPanel27.add(jLabel20);

        jLabel23.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jLabel23.setText("Đại diện:");
        jPanel27.add(jLabel23);

        jPanel28.setLayout(new java.awt.GridLayout(4, 1, 0, 10));

        txtMaNXB.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        jPanel28.add(txtMaNXB);

        txtTenNXB.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jPanel28.add(txtTenNXB);

        txtEmailNXB.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        txtEmailNXB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailNXBKeyReleased(evt);
            }
        });
        jPanel28.add(txtEmailNXB);

        txtDaiDienNXB.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jPanel28.add(txtDaiDienNXB);

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jLabel14.setText("Địa chỉ:");

        txtDCNXB.setColumns(20);
        txtDCNXB.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        txtDCNXB.setRows(5);
        jScrollPane4.setViewportView(txtDCNXB);

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconReset.png"))); // NOI18N
        jLabel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel30MouseClicked(evt);
            }
        });

        jPanel33.setLayout(new java.awt.GridLayout(1, 4, 15, 0));

        btnNewNXB.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        btnNewNXB.setText("New");
        btnNewNXB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNewNXBMouseClicked(evt);
            }
        });
        btnNewNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewNXBActionPerformed(evt);
            }
        });
        jPanel33.add(btnNewNXB);

        btnSaveNXB.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        btnSaveNXB.setText("Save");
        btnSaveNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveNXBActionPerformed(evt);
            }
        });
        jPanel33.add(btnSaveNXB);

        btnUpNXB.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        btnUpNXB.setText("Update");
        btnUpNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpNXBActionPerformed(evt);
            }
        });
        jPanel33.add(btnUpNXB);

        btnDeleteNXB.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        btnDeleteNXB.setText("Delete");
        btnDeleteNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteNXBActionPerformed(evt);
            }
        });
        jPanel33.add(btnDeleteNXB);

        lbtbNXB.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbtbNXB.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(lbtbNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(lbtbNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel22.add(jPanel25);

        jPanel7.add(jPanel22);

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tác giả", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 13))); // NOI18N
        jPanel23.setLayout(new java.awt.GridLayout(2, 1, 0, 10));

        jScrollPane7.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane7.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tbTG.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        tbTG.setModel(new javax.swing.table.DefaultTableModel(
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
        tbTG.setRowHeight(25);
        tbTG.setRowMargin(7);
        tbTG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTGMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbTG);

        jPanel23.add(jScrollPane7);

        jPanel29.setLayout(new java.awt.GridLayout(4, 1, 0, 10));

        jLabel24.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jLabel24.setText("Mã:");
        jPanel29.add(jLabel24);

        jLabel25.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jLabel25.setText("Tên tác giả:");
        jPanel29.add(jLabel25);

        jLabel27.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jLabel27.setText("Website:");
        jPanel29.add(jLabel27);

        jLabel28.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jLabel28.setText("Ghi chú:");
        jPanel29.add(jLabel28);

        jPanel30.setLayout(new java.awt.GridLayout(4, 1, 0, 10));

        txtMaTG.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        jPanel30.add(txtMaTG);

        txtTenTG.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jPanel30.add(txtTenTG);

        txtWebsite.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jPanel30.add(txtWebsite);

        txtGhiChuTG.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jPanel30.add(txtGhiChuTG);

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconReset.png"))); // NOI18N
        jLabel31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel31MouseClicked(evt);
            }
        });

        jPanel34.setLayout(new java.awt.GridLayout(1, 4, 5, 0));

        btnNewTG.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        btnNewTG.setText("New");
        btnNewTG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewTGActionPerformed(evt);
            }
        });
        jPanel34.add(btnNewTG);

        btnSaveTG.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        btnSaveTG.setText("Save");
        btnSaveTG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveTGActionPerformed(evt);
            }
        });
        jPanel34.add(btnSaveTG);

        btnUpTG.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        btnUpTG.setText("Update");
        btnUpTG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpTGActionPerformed(evt);
            }
        });
        jPanel34.add(btnUpTG);

        btnDeleteTG.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        btnDeleteTG.setText("Delete");
        btnDeleteTG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteTGActionPerformed(evt);
            }
        });
        jPanel34.add(btnDeleteTG);

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel31))
                .addGap(18, 18, 18)
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel23.add(jPanel26);

        jPanel7.add(jPanel23);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thể loại", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 13))); // NOI18N
        jPanel8.setLayout(new java.awt.GridLayout(2, 1, 0, 10));

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tbTL.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        tbTL.setModel(new javax.swing.table.DefaultTableModel(
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
        tbTL.setRowHeight(25);
        tbTL.setRowMargin(7);
        tbTL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTLMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbTL);

        jPanel8.add(jScrollPane3);

        jPanel31.setLayout(new java.awt.GridLayout(2, 1, 0, 10));

        jLabel26.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jLabel26.setText("Mã:");
        jPanel31.add(jLabel26);

        jLabel29.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jLabel29.setText("Tên TL:");
        jPanel31.add(jLabel29);

        jPanel32.setLayout(new java.awt.GridLayout(2, 1, 0, 10));

        txtMaTL.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        jPanel32.add(txtMaTL);

        txtTenTL.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jPanel32.add(txtTenTL);

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconReset.png"))); // NOI18N
        jLabel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel32MouseClicked(evt);
            }
        });

        jPanel35.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        btnNewTL.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        btnNewTL.setText("New");
        btnNewTL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewTLActionPerformed(evt);
            }
        });
        jPanel35.add(btnNewTL);

        btnSaveTL.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        btnSaveTL.setText("Save");
        btnSaveTL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveTLActionPerformed(evt);
            }
        });
        jPanel35.add(btnSaveTL);

        btnUpTL.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        btnUpTL.setText("Update");
        btnUpTL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpTLActionPerformed(evt);
            }
        });
        jPanel35.add(btnUpTL);

        btnDeleteTL.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        btnDeleteTL.setText("Delete");
        btnDeleteTL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteTLActionPerformed(evt);
            }
        });
        jPanel35.add(btnDeleteTL);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel32))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jLabel32)
                .addGap(14, 14, 14)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.add(jPanel24);

        btnFindMaSach.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btnFindMaSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Zoom-icon.png"))); // NOI18N
        btnFindMaSach.setText("Find");
        btnFindMaSach.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnFindMaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindMaSachActionPerformed(evt);
            }
        });

        txtFindNXB.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        btnFindMaSach1.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btnFindMaSach1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Zoom-icon.png"))); // NOI18N
        btnFindMaSach1.setText("Find");
        btnFindMaSach1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnFindMaSach1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindMaSach1ActionPerformed(evt);
            }
        });

        txtFindTG.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        txtFindTL.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        btnFindMaSach2.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btnFindMaSach2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Zoom-icon.png"))); // NOI18N
        btnFindMaSach2.setText("Find");
        btnFindMaSach2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnFindMaSach2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindMaSach2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 1075, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtFindNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFindMaSach)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFindTG, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFindMaSach1)
                        .addGap(26, 26, 26)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(txtFindTL)
                        .addGap(114, 114, 114))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFindMaSach2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFindNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFindMaSach)
                    .addComponent(txtFindTL, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFindMaSach2)
                    .addComponent(btnFindMaSach1)
                    .addComponent(txtFindTG, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 11, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Thể loại, NXB, tác giả", jPanel5);

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        txtSearchSach.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        btnSearchSach.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnSearchSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Zoom-icon.png"))); // NOI18N
        btnSearchSach.setText("Search");
        btnSearchSach.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSearchSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchSachActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        btnReset.setText("X");
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel8.setText("Từ khóa:");

        btnSachDaHet.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnSachDaHet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconWarning.png"))); // NOI18N
        btnSachDaHet.setText("Sách đã hết");
        btnSachDaHet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSachDaHetActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 13))); // NOI18N

        jPanel4.setLayout(new java.awt.GridLayout(2, 1, 0, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel11.setText("Mã sách:");
        jPanel4.add(jLabel11);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel12.setText("Tên sách:");
        jPanel4.add(jLabel12);

        jPanel6.setLayout(new java.awt.GridLayout(2, 1, 0, 15));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel16.setText("Thể loại:");
        jPanel6.add(jLabel16);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel19.setText("Nhà xuất bản:");
        jPanel6.add(jLabel19);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel17.setText("Số lượng:");

        txtSoLuongSach.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtSoLuongSach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoLuongSachKeyReleased(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel13.setText("Tác giả:");

        cbbTacGia.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbTacGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPanel19.setLayout(new java.awt.GridLayout(2, 1, 0, 15));

        cbbTheLoai.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbTheLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel19.add(cbbTheLoai);

        cbbNXB.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cbbNXB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel19.add(cbbNXB);

        jPanel20.setLayout(new java.awt.GridLayout(2, 1, 0, 15));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel15.setText("Năm xuất bản:");
        jPanel20.add(jLabel15);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel18.setText("Tình trạng:");
        jPanel20.add(jLabel18);

        jPanel21.setLayout(new java.awt.GridLayout(2, 1, 0, 15));

        cbbNamXB.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbNamXB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel21.add(cbbNamXB);

        cbbTinhTrang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbTinhTrang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel21.add(cbbTinhTrang);

        btnThemTL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconThem2.png"))); // NOI18N
        btnThemTL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThemTL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemTLMouseClicked(evt);
            }
        });

        btnResetTL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconReset3.png"))); // NOI18N
        btnResetTL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnResetTL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetTLMouseClicked(evt);
            }
        });

        btnThemNXB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconThem2.png"))); // NOI18N
        btnThemNXB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThemNXB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemNXBMouseClicked(evt);
            }
        });

        btnResetNXB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconReset3.png"))); // NOI18N
        btnResetNXB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnResetNXB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetNXBMouseClicked(evt);
            }
        });

        btnThemTacGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconThem2.png"))); // NOI18N
        btnThemTacGia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThemTacGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemTacGiaMouseClicked(evt);
            }
        });

        btnResetTG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconReset3.png"))); // NOI18N
        btnResetTG.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnResetTG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetTGMouseClicked(evt);
            }
        });

        txtMaSach.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        btnLayMaSach.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        btnLayMaSach.setText("Lấy mã");
        btnLayMaSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLayMaSachMouseClicked(evt);
            }
        });

        txtTenSach.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jButton1.setText("Path:");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtImg.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N

        lbtbSL.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        lbtbSL.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtImg))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnLayMaSach))
                                .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThemTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnResetTG, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSoLuongSach, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addComponent(lbtbSL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnThemTL, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnResetTL, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnResetNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtSoLuongSach, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbtbSL, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnResetTL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnThemTL, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(11, 11, 11)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnThemNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnResetNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(9, 9, 9)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cbbTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(btnThemTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnResetTG, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtImg, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLayMaSach))
                        .addGap(18, 18, 18)
                        .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tbSach.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tbSach.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
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
        jScrollPane1.setViewportView(tbSach);

        jPanel9.setLayout(new java.awt.GridLayout(1, 4, 100, 0));

        btnThemSach.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnThemSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconPlus.png"))); // NOI18N
        btnThemSach.setText("New");
        btnThemSach.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnThemSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSachActionPerformed(evt);
            }
        });
        jPanel9.add(btnThemSach);

        btnLuuSach.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnLuuSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconSave.png"))); // NOI18N
        btnLuuSach.setText("Save");
        btnLuuSach.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLuuSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuSachActionPerformed(evt);
            }
        });
        jPanel9.add(btnLuuSach);

        btnCapNhatSach.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnCapNhatSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/update.png"))); // NOI18N
        btnCapNhatSach.setText("Update");
        btnCapNhatSach.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCapNhatSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatSachActionPerformed(evt);
            }
        });
        jPanel9.add(btnCapNhatSach);

        btnXoaSach.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnXoaSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/delete2.png"))); // NOI18N
        btnXoaSach.setText("Delete");
        btnXoaSach.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnXoaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSachActionPerformed(evt);
            }
        });
        jPanel9.add(btnXoaSach);

        lbHinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbHinh.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Image", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 13))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbHinh, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(56, 56, 56)
                                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 878, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(btnSachDaHet)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearchSach, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnReset)
                                .addGap(5, 5, 5)
                                .addComponent(btnSearchSach)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSachDaHet, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearchSach, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSearchSach, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8))))
                    .addComponent(lbHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lý sách", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1390, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemDGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemDGMouseClicked


    }//GEN-LAST:event_btnThemDGMouseClicked

    private void tbDocGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDocGiaMouseClicked
        showBtnDG();
        btnLuuDG.setEnabled(false);
        btnLayMaDG.setEnabled(false);
        int vt = tbDocGia.getSelectedRow();
        Vector vctRow = (Vector) vctDataDG.get(vt);
        txtMaDG.setText(vctRow.get(0).toString());
        txtTenDG.setText(vctRow.get(1).toString());
        txtDiaChiDG.setText(vctRow.get(2).toString());
        //tách Ngày dạng yyyy-MM-dd thành yyyy,MM,dd để dưa vào combobox ngày sinh
        String ngaySinh[] = vctRow.get(3).toString().split("-");
        int vtriNam = hxl.timViTriVctcbb(vctCbbNam, ngaySinh[0]);
        cbbNamSinh.setSelectedIndex(vtriNam);
        int vtriThang = hxl.timViTriVctcbb(vctCbbThang, ngaySinh[1]);
        cbbThangSinh.setSelectedIndex(vtriThang);
        int vtriNgay = hxl.timViTriVctcbb(vctCbbNgay, ngaySinh[2]);
        cbbNgaySinh.setSelectedIndex(vtriNgay);
        //tách Ngày dạng yyyy-MM-dd thành yyyy,MM,dd để dưa vào combobox ngày mở thẻ
        String ngayMoThe[] = vctRow.get(8).toString().split("-");
        int vtriNamMoThe = hxl.timViTriVctcbb(vctCbbNam, ngayMoThe[0]);
        cbbNamMoThe.setSelectedIndex(vtriNamMoThe);
        int vtriThangMoThe = hxl.timViTriVctcbb(vctCbbThang, ngayMoThe[1]);
        cbbThangMoThe.setSelectedIndex(vtriThangMoThe);
        int vtriNgayMoThe = hxl.timViTriVctcbb(vctCbbNgay, ngayMoThe[2]);
        cbbNgayMoThe.setSelectedIndex(vtriNgayMoThe);

        if (vctRow.get(4).toString().equals("Nam")) {
            cbbGioiTinh.setSelectedIndex(0);
        } else {
            cbbGioiTinh.setSelectedIndex(1);
        }
        txtEmail.setText(vctRow.get(5).toString());
        txtSoDienThoai.setText(vctRow.get(6).toString());
        int vtriSoThe = hxl.timViTriVctcbb(vctCbbMaThe, vctRow.get(7).toString());

        cbbMaThe.setSelectedIndex(vtriSoThe);
    }//GEN-LAST:event_tbDocGiaMouseClicked

    private void btnLayMaDGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLayMaDGMouseClicked
        txtMaDG.setText(taoMaDocGiaTuDong());
    }//GEN-LAST:event_btnLayMaDGMouseClicked

    private void btnResetTheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetTheMouseClicked
        vctCbbMaThe.clear();
        layTheThuVien();
    }//GEN-LAST:event_btnResetTheMouseClicked

    private void btnThemTheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemTheMouseClicked
        frm_ThemTheThuVien t = new frm_ThemTheThuVien();
        t.setVisible(true);
    }//GEN-LAST:event_btnThemTheMouseClicked

    private void txtSoDienThoaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoDienThoaiKeyReleased
        String phone = txtSoDienThoai.getText();
        if (hxl.checkPhone(phone) == false) {
            lbTBSDT.setText("Phone không đúng định dạng");
        } else {
            lbTBSDT.setText("");
        }
    }//GEN-LAST:event_txtSoDienThoaiKeyReleased

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        String email = txtEmail.getText();
        if (hxl.checkEmail(email) == false) {
            lbTBSDT.setText("Email không đúng định dạng");
        } else {
            lbTBSDT.setText("");
        }
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtTenDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenDGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenDGActionPerformed

    private void btnSearchDocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchDocGiaActionPerformed
        hienThiDocGiaSearch();
        hideBtnDG();
    }//GEN-LAST:event_btnSearchDocGiaActionPerformed

    private void btnSachDaHetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSachDaHetActionPerformed
        showSachDaHet();
        hideBtnSach();
    }//GEN-LAST:event_btnSachDaHetActionPerformed

    private void btnResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseClicked
        txtSearchSach.setText("");
        hienThiSach();
        hideBtnSach();
    }//GEN-LAST:event_btnResetMouseClicked

    private void btnSearchSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchSachActionPerformed
        hideBtnSach();
        dfm = (DefaultTableModel) tbSach.getModel();
        dfm.setRowCount(0);
        Vector vctSearch = new Vector();
        conSQL.Select_SearchSach(vctData, txtSearchSach.getText().trim());
        tbSach.setModel(new DefaultTableModel(vctData, vctHead));
        setSizeTableSach();

    }//GEN-LAST:event_btnSearchSachActionPerformed

    private void tbSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSachMouseClicked
        showBtnSach();
        btnLuuSach.setEnabled(false);
        int vt = tbSach.getSelectedRow();
        Vector vctRow = (Vector) vctData.get(vt);
        txtMaSach.setText(vctRow.get(0).toString());
        txtTenSach.setText(vctRow.get(1).toString());
        int vtTacGia = hxl.timViTriVctcbb(vctComBoBoxTacGia, vctRow.get(4).toString());
        cbbTacGia.setSelectedIndex(vtTacGia);
        //        txtTacGia.setText(vctRow.get(4).toString());
        txtSoLuongSach.setText(vctRow.get(5).toString());
        int vtNXB = hxl.timViTriVctcbb(vctComBoBoxNXB, vctRow.get(6).toString());
        cbbNXB.setSelectedIndex(vtNXB);
        //        txtNXB.setText(vctRow.get(6).toString());
        if (txtSoLuongSach.getText().equals("0")) {
            cbbTinhTrang.setSelectedIndex(1);
        } else {
            cbbTinhTrang.setSelectedIndex(0);
        }

        //hien thi len conBoBox the loai va NamXB
        String theLoai = vctRow.get(2).toString();
        int namXB = Integer.parseInt(vctRow.get(3).toString());

        for (int i = 0; i < cbbTheLoai.getItemCount(); i++) {
            if (vctComBoBoxTheLoai.get(i).toString().equals(theLoai)) {
                cbbTheLoai.setSelectedIndex(i);
                break;
            }
        }
        for (int i = 0; i < cbbNamXB.getItemCount(); i++) {
            if (Integer.parseInt(vctComBoBoxNamXB.get(i).toString()) == namXB) {
                cbbNamXB.setSelectedIndex(i);
                break;
            }
        }
        //Set image
        ImageIcon icon = new ImageIcon(vctRow.get(7).toString());
        lbHinh.setIcon(icon);
        txtImg.setText(vctRow.get(7).toString());
    }//GEN-LAST:event_tbSachMouseClicked

    private void btnThemSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSachActionPerformed
        emptyTXTSach();
        txtMaSach.requestFocus();
        hideBtnSach();
        btnLuuSach.setEnabled(true);
        String ma = taoMaSachSuDong();
        txtMaSach.setText(ma);
        JOptionPane.showMessageDialog(rootPane, "Tự động tạo mã sách: " + ma + "\nTiếp tục nhập các thông tim còn lại", "Thông báo", 1, imgTB.getImgCompleteYellow());
        txtTenSach.requestFocus();
    }//GEN-LAST:event_btnThemSachActionPerformed

    private void btnLayMaSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLayMaSachMouseClicked
        emptyTXTSach();
        hideBtnSach();
        btnLuuSach.setEnabled(true);
        String ma = taoMaSachSuDong();
        txtMaSach.setText(ma);
    }//GEN-LAST:event_btnLayMaSachMouseClicked

    private void btnResetTGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetTGMouseClicked
        layCBBTacGia();
        showTacGia();
    }//GEN-LAST:event_btnResetTGMouseClicked

    private void btnThemTacGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemTacGiaMouseClicked

        frmTG.setVisible(true);
    }//GEN-LAST:event_btnThemTacGiaMouseClicked

    private void btnResetNXBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetNXBMouseClicked
        layCBBNXB();
        showNXB();
    }//GEN-LAST:event_btnResetNXBMouseClicked

    private void btnThemNXBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNXBMouseClicked

        addNXB.setVisible(true);
    }//GEN-LAST:event_btnThemNXBMouseClicked

    private void btnResetTLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetTLMouseClicked
        layCBBTheLoai();
        showTheLoai();
    }//GEN-LAST:event_btnResetTLMouseClicked

    private void btnThemTLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemTLMouseClicked
        frm_ThemTheLoai frmTheLoai = new frm_ThemTheLoai();
        frmTheLoai.setVisible(true);
    }//GEN-LAST:event_btnThemTLMouseClicked

    private void txtSoLuongSachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongSachKeyReleased
        String sl = txtSoLuongSach.getText().trim();
        char c = evt.getKeyChar();
        int phim = evt.getKeyCode();
        if (sl.length() > 0 && phim != 8 & !(c == KeyEvent.VK_ENTER) && !(c >= '0' && c <= '9') && phim != 37 && phim != 39 && phim != 38 && phim != 40) {
            StringBuilder sb = new StringBuilder();
            String s = txtSoLuongSach.getText();
            sb.append(s);
            sb.deleteCharAt(s.length() - 1);
            s = sb.toString();
            txtSoLuongSach.setText(s);

        }
        if (hxl.kTraChuoiIsNumber(sl) == false) {
            lbtbSL.setText("Số lượng không hợp lệ");
        } else {
            lbtbSL.setText("");
        }

    }//GEN-LAST:event_txtSoLuongSachKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser fileChoose = new JFileChooser();//khai báo brownfile
        //Khai báo phần mở rộng
        FileNameExtensionFilter filter = new FileNameExtensionFilter("HinhNhanVien", "jpg", "png");
        fileChoose.setFileFilter(filter);
        int i = fileChoose.showOpenDialog(null);
        if (i == fileChoose.APPROVE_OPTION) {
            File f = fileChoose.getSelectedFile();//khai báo file = file dc chọn
            String filePath = "src/HinhNhanVien/" + f.getName();//hetName: tên file
            txtImg.setText(filePath);//đường dẫn
            try {
                ImageIcon icon = new ImageIcon(filePath);
                lbHinh.setIcon(icon);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        hienThiSach();
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        hienThiDocGia();
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        showNXB();
        showTacGia();
        showTheLoai();
    }//GEN-LAST:event_jPanel5MouseClicked

    private void tbNXBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNXBMouseClicked
        int row = tbNXB.getSelectedRow();
        txtMaNXB.setText(tbNXB.getValueAt(row, 0).toString().trim());
        txtTenNXB.setText(tbNXB.getValueAt(row, 1).toString().trim());
        txtDCNXB.setText(tbNXB.getValueAt(row, 2).toString().trim());
        txtEmailNXB.setText(tbNXB.getValueAt(row, 3).toString().trim());
        txtDaiDienNXB.setText(tbNXB.getValueAt(row, 4).toString().trim());
        showBtnNXB();
        btnSaveNXB.setEnabled(false);
        lbtbNXB.setText("");
    }//GEN-LAST:event_tbNXBMouseClicked

    private void tbTGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTGMouseClicked
        int row = tbTG.getSelectedRow();
        txtMaTG.setText(tbTG.getValueAt(row, 0).toString().trim());
        txtTenTG.setText(tbTG.getValueAt(row, 1).toString().trim());
        txtWebsite.setText(tbTG.getValueAt(row, 2).toString().trim());
        txtGhiChuTG.setText(tbTG.getValueAt(row, 3).toString().trim());
        showBtnTG();
        btnSaveTG.setEnabled(false);
    }//GEN-LAST:event_tbTGMouseClicked

    private void tbTLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTLMouseClicked
        int row = tbTL.getSelectedRow();
        txtMaTL.setText(tbTL.getValueAt(row, 0).toString().trim());
        txtTenTL.setText(tbTL.getValueAt(row, 1).toString().trim());
        showBtnTL();
        btnSaveTL.setEnabled(false);
    }//GEN-LAST:event_tbTLMouseClicked

    private void jLabel30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseClicked
        showNXB();
        hideBtnNXB();
        lbtbNXB.setText("");
    }//GEN-LAST:event_jLabel30MouseClicked

    private void jLabel31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseClicked
        showTacGia();
        hideBtnTG();
    }//GEN-LAST:event_jLabel31MouseClicked

    private void jLabel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseClicked
        showTheLoai();
        hideBtnTL();
    }//GEN-LAST:event_jLabel32MouseClicked

    private void btnNewNXBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewNXBMouseClicked

    }//GEN-LAST:event_btnNewNXBMouseClicked

    private void btnNewNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewNXBActionPerformed
        hideBtnNXB();
        btnSaveNXB.setEnabled(true);
        emptyNXB();
        lbtbNXB.setText("");
        int maNXB = taoMaNXB();
        txtMaNXB.setText(maNXB + "");
        txtTenNXB.requestFocus();
    }//GEN-LAST:event_btnNewNXBActionPerformed

    private void btnNewTGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewTGActionPerformed
        hideBtnTG();
        btnSaveTG.setEnabled(true);
        emptyTG();
        int maTG = taoMaTG();
        txtMaTG.setText(maTG + "");
        txtTenTG.requestFocus();
    }//GEN-LAST:event_btnNewTGActionPerformed

    private void btnNewTLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewTLActionPerformed
        hideBtnTL();
        btnSaveTL.setEnabled(true);
        emptyTL();
        int maTL = taoMaTL();
        txtMaTL.setText(maTL + "");
        txtTenTL.requestFocus();
    }//GEN-LAST:event_btnNewTLActionPerformed

    private void btnSaveNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveNXBActionPerformed
        if (txtTenNXB.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập tên nhà xuất bản", "Thông báo", 0, imgTB.getImgWarning());
            txtTenNXB.requestFocus();
        } else if (txtEmailNXB.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập Email", "Thông báo", 0, imgTB.getImgWarning());
            txtEmailNXB.requestFocus();
        } else if (hxl.checkEmail(txtEmailNXB.getText()) == false) {
            JOptionPane.showMessageDialog(rootPane, "Email không hợp lệ", "Thông báo", 0, imgTB.getImgWarning());
            txtEmailNXB.requestFocus();
        } else {
            String tenNXB = txtTenNXB.getText().trim();
            String email = txtEmailNXB.getText().trim();
            String diaChi = txtDCNXB.getText().trim();
            String ndd = txtDaiDienNXB.getText().trim();
            ArrayList<NhaXuatBan> lstNXB = nxbDAO.listNXB();
            int Ktra = 1;
            for (NhaXuatBan nxb : lstNXB) {//kiểm tra email
                if (nxb.getEmail().equals(email)) {
                    Ktra = 0;
                    txtEmailNXB.requestFocus();
                    break;
                }

            }
            if (Ktra == 0) {
                JOptionPane.showMessageDialog(rootPane, "Email đã tồn tại", "Thất bại!", 0, imgTB.getImgWarning());
            } else {
                NhaXuatBan nxb = new NhaXuatBan(tenNXB, diaChi, email, ndd);
                boolean kt = nxbDAO.themNXB(nxb);
                if (kt == false) {
                    JOptionPane.showMessageDialog(rootPane, "Error: Please review.", "Thất bại", 0, imgTB.getImgWarning());

                } else {
                    JOptionPane.showMessageDialog(rootPane, "Đã thêm nhà xuất bản: " + tenNXB, "Thành công", 1, imgTB.getImgCompleteGreen());
                    emptyNXB();
                    txtTenNXB.requestFocus();
                    showNXB();
                }
            }
        }
    }//GEN-LAST:event_btnSaveNXBActionPerformed

    private void txtEmailNXBKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailNXBKeyReleased
        String email = txtEmailNXB.getText().trim();
        if (hxl.checkEmail(email) == false) {
            lbtbNXB.setText("Error");
        } else {
            lbtbNXB.setText("");
        }
    }//GEN-LAST:event_txtEmailNXBKeyReleased

    private void btnSaveTGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveTGActionPerformed
        if (txtTenTG.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập tên tác giả", "Thông báo", 0, imgTB.getImgWarning());
            txtTenTG.requestFocus();
        } else {
            String tenTG = txtTenTG.getText().trim();
            ArrayList<TacGia> lstTG = tgDAO.listTacGia();
            int Ktra = 1;
            for (TacGia tg : lstTG) {
                if (tg.getTenTG().equals(tenTG)) {

                    txtTenTG.requestFocus();
                    Ktra = 0;
                    break;
                }
            }
            if (Ktra == 0) {
                JOptionPane.showMessageDialog(rootPane, "Tác giả đã tồn tại", "Thất bại!", 0, imgTB.getImgWarning());
            } else {
                TacGia tg = new TacGia(tenTG, txtWebsite.getText().trim(), txtGhiChuTG.getText().trim());
                boolean kt = tgDAO.themTacGia(tg);
                if (kt == false) {
                    JOptionPane.showMessageDialog(rootPane, "Thêm Thất bại", "Thông báo", 1, imgTB.getImgError());

                } else {
                    JOptionPane.showMessageDialog(rootPane, "Đã thêm tác giả: " + tenTG, "Thành công", 1, imgTB.getImgCompleteGreen());
                    emptyTG();
                    showTacGia();
                    txtTenTG.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_btnSaveTGActionPerformed

    private void btnSaveTLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveTLActionPerformed
        if (txtTenTL.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập tên thể loại", "Thông báo", 0, imgTB.getImgWarning());
            txtTenTL.requestFocus();
        } else {
            String tenTL = txtTenTL.getText().trim();
            ArrayList<TheLoai> lstTL = tlDAO.listTheLoai();
            int Ktra = 1;
            for (TheLoai tl : lstTL) {
                if (tl.getTenTL().equals(tenTL)) {
                    txtTenTL.requestFocus();
                    Ktra = 0;
                    break;
                }
            }
            if (Ktra == 0) {
                JOptionPane.showMessageDialog(rootPane, "Thể loại này đã tồn tại", "Thất bại!", 0, imgTB.getImgWarning());
            } else {
                TheLoai tl = new TheLoai(tenTL);
                boolean kt = tlDAO.themTheLoai(tl);
                if (kt == false) {
                    JOptionPane.showMessageDialog(rootPane, "Thêm Thất bại", "Thông báo", 1, imgTB.getImgError());

                } else {
                    JOptionPane.showMessageDialog(rootPane, "Đã thêm thể loại: " + tenTL, "Thành công", 1, imgTB.getImgCompleteGreen());
                    txtTenTL.requestFocus();
                    emptyTL();
                    showTheLoai();
                }
            }
        }
    }//GEN-LAST:event_btnSaveTLActionPerformed

    private void btnUpNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpNXBActionPerformed
        if (txtTenNXB.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập tên nhà xuất bản", "Thông báo", 0, imgTB.getImgWarning());
            txtTenNXB.requestFocus();
        } else if (txtEmailNXB.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập Email", "Thông báo", 0, imgTB.getImgWarning());
            txtEmailNXB.requestFocus();
        } else if (hxl.checkEmail(txtEmailNXB.getText()) == false) {
            JOptionPane.showMessageDialog(rootPane, "Email không hợp lệ", "Thông báo", 0, imgTB.getImgWarning());
            txtEmailNXB.requestFocus();
        } else {
            String tenNXB = txtTenNXB.getText().trim();
            String email = txtEmailNXB.getText().trim();
            String diaChi = txtDCNXB.getText().trim();
            String ndd = txtDaiDienNXB.getText().trim();
            String maNXB = txtMaNXB.getText();
            ArrayList<NhaXuatBan> lstNXB = nxbDAO.listNXB();
            int Ktra = 1;
            for (NhaXuatBan nxb : lstNXB) {//kiểm tra email
                if (nxb.getMaNXB().equals(maNXB) == false && nxb.getEmail().equals(email)) {
                    Ktra = 0;
                    txtEmailNXB.requestFocus();
                    break;
                }

            }
            if (Ktra == 0) {
                JOptionPane.showMessageDialog(rootPane, "Email đã tồn tại", "Thất bại!", 0, imgTB.getImgWarning());
            } else {

                NhaXuatBan nxb = new NhaXuatBan(maNXB, tenNXB, diaChi, email, ndd);
                boolean kt = nxbDAO.updateNXB(nxb);
                if (kt == false) {
                    JOptionPane.showMessageDialog(rootPane, "Error: Please review.", "Thất bại", 0, imgTB.getImgWarning());

                } else {
                    JOptionPane.showMessageDialog(rootPane, "Dã cập nhật", "Thành công", 1, imgTB.getImgCompleteGreen());
                    emptyNXB();
                    txtTenNXB.requestFocus();
                    showNXB();
                }
            }
        }
    }//GEN-LAST:event_btnUpNXBActionPerformed

    private void btnDeleteNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteNXBActionPerformed
        int index = tbNXB.getSelectedRow();
        String maNXB = txtMaNXB.getText();
        String tenNXB = tbNXB.getValueAt(index, 1).toString();
        //kiểm tra vi phạm khóa ngoại của bảng sách
        boolean check = true;
        ArrayList<Sach> lstSach = sDAO.listSach();
        for (Sach s : lstSach) {
            if (s.getNhaXB().equals(tenNXB)) {
                check = false;
                break;
            }
        }
        if (check == false) {
            JOptionPane.showMessageDialog(rootPane, "Không thể xóa nhà xuất bản này.\n(Lý do: vi phạm khóa ngoại)", "Error", 0, imgTB.getImgError());
        } else {
            int n = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa nhà xuát bản này", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, imgTB.getImgQuestion());
            if (n == JOptionPane.YES_OPTION) {
                boolean kq = nxbDAO.deleteNXB(maNXB);
                if (kq == false) {
                    JOptionPane.showMessageDialog(rootPane, "Không thể xóa nhà xuất bản này.", "Error", 0, imgTB.getImgError());
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Dã xóa", "Thành công", 1, imgTB.getImgCompleteGreen());
                    showNXB();
                    emptyNXB();
                    hideBtnNXB();
                }
            }
        }
    }//GEN-LAST:event_btnDeleteNXBActionPerformed

    private void btnUpTGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpTGActionPerformed
        if (txtTenTG.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập tên nhà xuất bản", "Thông báo", 0, imgTB.getImgWarning());
            txtTenTG.requestFocus();
        } else {
            String tenTG = txtTenTG.getText().trim();
            String maTG = txtMaTG.getText().trim();
            ArrayList<TacGia> lstTG = tgDAO.listTacGia();
            int Ktra = 1;
            //kiểm tra có tác giả này hay chưa
            for (TacGia tg : lstTG) {
                if (tg.getMaTG().equals(maTG) == false && tg.getTenTG().equals(tenTG)) {

                    txtTenTG.requestFocus();
                    Ktra = 0;
                    break;
                }
            }
            if (Ktra == 0) {
                JOptionPane.showMessageDialog(rootPane, "Tác giả đã tồn tại", "Thất bại!", 0, imgTB.getImgWarning());
            } else {
                TacGia tg = new TacGia(maTG, tenTG, txtWebsite.getText().trim(), txtGhiChuTG.getText().trim());
                boolean kt = tgDAO.updateTacGia(tg);
                if (kt == false) {
                    JOptionPane.showMessageDialog(rootPane, "Error!", "Thông báo", 1, imgTB.getImgWarning());

                } else {
                    JOptionPane.showMessageDialog(rootPane, "Đã cập nhật", "Thành công", 1, imgTB.getImgCompleteGreen());
                    emptyTG();
                    showTacGia();
                    txtTenTG.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_btnUpTGActionPerformed

    private void btnDeleteTGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteTGActionPerformed
        int index = tbTG.getSelectedRow();
        String maTG = txtMaTG.getText();
        String tenTG = tbTG.getValueAt(index, 1).toString();
        //kiểm tra vi phạm khóa ngoại của bảng sách
        boolean check = true;
        ArrayList<Sach> lstSach = sDAO.listSach();
        for (Sach s : lstSach) {
            if (s.getTacGia().equals(tenTG)) {
                check = false;
                break;
            }
        }
        if (check == false) {
            JOptionPane.showMessageDialog(rootPane, "Không thể xóa tác giả này bản này.\n(Lý do: vi phạm khóa ngoại)", "Error", 0, imgTB.getImgError());
        } else {
            int n = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa tác giả này", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, imgTB.getImgQuestion());
            if (n == JOptionPane.YES_OPTION) {
                boolean kq = tgDAO.deleteTacGia(maTG);
                if (kq == false) {
                    JOptionPane.showMessageDialog(rootPane, "Không thể xóa tác giả này.", "Error", 0, imgTB.getImgError());
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Dã xóa", "Thành công", 1, imgTB.getImgCompleteGreen());
                    showTacGia();
                    emptyTG();
                    hideBtnTG();
                }
            }
        }
    }//GEN-LAST:event_btnDeleteTGActionPerformed

    private void btnUpTLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpTLActionPerformed
        if (txtTenTL.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập tên thể loại", "Thông báo", 0, imgTB.getImgWarning());
            txtTenTL.requestFocus();
        } else {
            String tenTL = txtTenTL.getText().trim();
            String maTL = txtMaTL.getText().trim();
            ArrayList<TheLoai> lstTL = tlDAO.listTheLoai();
            int Ktra = 1;
            //kiểm tra có thể loại này hay chưa
            for (TheLoai tl : lstTL) {
                if (tl.getMaTL().equals(maTL) == false && tl.getTenTL().equals(tenTL)) {
                    txtTenTL.requestFocus();
                    Ktra = 0;
                    break;
                }
            }
            if (Ktra == 0) {
                JOptionPane.showMessageDialog(rootPane, "Thể loại đã tồn tại", "Thất bại!", 0, imgTB.getImgWarning());
            } else {
                TheLoai tl = new TheLoai(maTL, tenTL);
                boolean kt = tlDAO.updateTheLoai(tl);
                if (kt == false) {
                    JOptionPane.showMessageDialog(rootPane, "Error", "Thông báo", 1, imgTB.getImgWarning());

                } else {
                    JOptionPane.showMessageDialog(rootPane, "Đã cập nhật", "Thành công", 1, imgTB.getImgCompleteGreen());
                    emptyTL();
                    showTheLoai();
                    txtTenTL.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_btnUpTLActionPerformed

    private void btnDeleteTLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteTLActionPerformed
        int index = tbTL.getSelectedRow();
        String maTL = txtMaTL.getText();
        String tenTL = tbTL.getValueAt(index, 1).toString();
        //kiểm tra vi phạm khóa ngoại của bảng sách
        boolean check = true;
        ArrayList<Sach> lstSach = sDAO.listSach();
        for (Sach s : lstSach) {
            if (s.getTheLoai().equals(tenTL)) {
                check = false;
                break;
            }
        }
        if (check == false) {
            JOptionPane.showMessageDialog(rootPane, "Không thể xóa thể loại này bản này.\n(Lý do: vi phạm khóa ngoại)", "Error", 0, imgTB.getImgError());
        } else {
            int n = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa thể loại này", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, imgTB.getImgQuestion());
            if (n == JOptionPane.YES_OPTION) {
                boolean kq = tlDAO.deleteTheLoai(maTL);
                if (kq == false) {
                    JOptionPane.showMessageDialog(rootPane, "Không thể xóa thể lọai này.", "Error", 0, imgTB.getImgError());
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Dã xóa", "Thành công", 1, imgTB.getImgCompleteGreen());
                    showTheLoai();
                    emptyTL();
                    hideBtnTL();
                }
            }
        }
    }//GEN-LAST:event_btnDeleteTLActionPerformed

    private void btnThemDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDGActionPerformed
        xoaTrangDocGia();
        String maDG = taoMaDocGiaTuDong();
        txtMaDG.setText(maDG);
        btnLuuDG.setEnabled(true);
    }//GEN-LAST:event_btnThemDGActionPerformed

    private void btnLuuDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuDGActionPerformed
        String maDG = txtMaDG.getText().trim();
        String tenDG = txtTenDG.getText().trim();
        String diaChiDG = txtDiaChiDG.getText().trim();
        String emailDG = txtEmail.getText().trim();
        String sdtDG = txtSoDienThoai.getText().trim();
        String maTheDG = cbbMaThe.getSelectedItem().toString();
        String ngayMoThe = LocalDate.now().toString().trim();
        int ngay = Integer.parseInt(cbbNgaySinh.getSelectedItem().toString());
        int thang = Integer.parseInt(cbbThangSinh.getSelectedItem().toString());
        int nam = Integer.parseInt(cbbNamSinh.getSelectedItem().toString());
        String ngaySinh = nam + "-" + thang + "-" + ngay;
        String ngaySinh2 = ngay + "-" + thang + "-" + nam;
        //        txtMaDG.setText(ngaySinh2);
        String gioiTinh = cbbGioiTinh.getSelectedItem().toString();
        //        //Kiểm tra input
        if (maDG.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chưa lấy mã!", "Thong báo", 1,imgTB.getImgWarning());
            txtTenDG.requestFocus();
        } else if (tenDG.equals("") || hxl.kiemTraChuoiChuaToanKhoangTrang(tenDG) == true) {
            JOptionPane.showMessageDialog(rootPane, "Tên không được để trống !", "Thong báo", 1,imgTB.getImgWarning());
            txtTenDG.requestFocus();
        } else if (diaChiDG.equals("") || hxl.kiemTraChuoiChuaToanKhoangTrang(diaChiDG) == true) {
            JOptionPane.showMessageDialog(rootPane, "Địa chỉ không được để trống !", "Thong báo", 1,imgTB.getImgWarning());
            txtDiaChiDG.requestFocus();
        } else if (emailDG.equals("") || hxl.kiemTraChuoiChuaToanKhoangTrang(emailDG) == true) {
            JOptionPane.showMessageDialog(rootPane, "Email không được để trống !", "Thong báo", 1,imgTB.getImgWarning());
            txtEmail.requestFocus();
        } else if (sdtDG.equals("") || hxl.kiemTraChuoiChuaToanKhoangTrang(sdtDG) == true) {
            JOptionPane.showMessageDialog(rootPane, "Số điện thoại không được để trống !", "Thong báo", 1,imgTB.getImgWarning());
            txtSoDienThoai.requestFocus();
        } else if (hxl.checkPhone(sdtDG) == false) {
            JOptionPane.showMessageDialog(rootPane, "Số điện thoại không đúng định dạng!", "Thong báo", 1,imgTB.getImgWarning());
            txtSoDienThoai.requestFocus();
        } else if (hxl.kTraNgayHopLe(ngay, thang, nam) == false) {
            JOptionPane.showMessageDialog(rootPane, "Ngày sinh '" + ngay + "-" + thang + "-" + nam + " không hợp lệ, vui lòng kiểm tra lại.", "Thất bại", 0,imgTB.getImgError());
        } else//input hợp lệ
        {
            //kiểm tra sdt và email đã có trong database chưa
            ArrayList<DocGia> lstDG = dgDAO.listDocGia();
            boolean ktraMaDG = true;//kiểm tra mã đọc giả
            boolean ktraEmail = true;//kiểm tra email
            boolean ktraSDT = true;//kiểm tra số điện thoại
            boolean ktraTTV = true;//kiểm tra thể thư viện đẫ có đọc giả chưa
            for (DocGia dg : lstDG) {
                if (maDG.equals(dg.getMaDG())) {
                    ktraMaDG = false;//email đã tồn tại
                    break;
                }
            }
            for (DocGia dg : lstDG) {
                if (emailDG.equals(dg.getEmail())) {
                    ktraEmail = false;//email đã tồn tại
                    break;
                }
            }
            for (DocGia dg : lstDG) {
                if (sdtDG.equals(dg.getSdt())) {
                    ktraSDT = false;//sdt đã tồn tại
                    break;
                }
            }
            for (DocGia dg : lstDG) {
                if (maDG.equals(dg.getMaDG()) == false && dg.getSoThe().equals(maTheDG)) {
                    ktraTTV = false;//Mã thẻ đã có đọc giả đky
                    break;
                }
            }
            if (ktraMaDG == false) {//Kiểm tra Mã đọc giả
                int n = JOptionPane.showConfirmDialog(this, "Mã đọc giả đã tồn tại (bấm nút lấy mã)\nHoặc tạo mã ngay bây giờ?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, imgTB.getImgQuestion());
                if (n == JOptionPane.YES_OPTION) {
                    String maDGAuTo = taoMaDocGiaTuDong();
                    txtMaDG.setText(maDGAuTo);
                    JOptionPane.showMessageDialog(rootPane, "Đã tạo mã " + maDGAuTo);
                }
            } else if (ktraEmail == false) {//Kiểm tra Email
                JOptionPane.showMessageDialog(rootPane, "Email đã tồn tại !", "Thông báo", 1, imgTB.getImgWarning());
                txtEmail.requestFocus();
            } else if (ktraSDT == false) {//Kiểm tra SDT
                JOptionPane.showMessageDialog(rootPane, "Số điện thoại đã tồn tại !", "Thông báo", 1, imgTB.getImgWarning());
                txtSoDienThoai.requestFocus();
            } else if (ktraTTV == false) {//Kiểm tra Thẻ thư viện
                int n = JOptionPane.showConfirmDialog(this, "Thẻ đã có đọc giả đăng ký!\nBạn Có muốn thêm thẻ mới không?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, imgTB.getImgQuestion());
                if (n == JOptionPane.YES_OPTION) {
                    frm_ThemTheThuVien frmTTV = new frm_ThemTheThuVien();
                    frmTTV.setVisible(true);
                }
            } else//Tất cả đều hợp lệ. Insert vào database
            {
                DocGia dg = new DocGia(maDG, tenDG, diaChiDG, gioiTinh, emailDG, sdtDG, maTheDG, ngaySinh, ngayMoThe);

                boolean kt = dgDAO.themDocGia(dg);
                if (kt == true) {
                    JOptionPane.showMessageDialog(rootPane, "Thêm thành công\nMã: " + maDG + "\nHọ tên: " + tenDG + "\nEmail: " + emailDG + "\nPhone: " + sdtDG + "\nThẻ thư viên: " + maTheDG, "Thông báo", 1, imgTB.getImgCompleteGreen());
                    hienThiDocGia();
                    xoaTrangDocGia();
                    cbbNgayMoThe.setEnabled(true);
                    cbbThangMoThe.setEnabled(true);
                    cbbNamMoThe.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Thêm thất bại", "Thông báo", 1, imgTB.getImgError());
                }

            }
        }
    }//GEN-LAST:event_btnLuuDGActionPerformed

    private void btnCapNhatDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatDGActionPerformed
        int ngay = Integer.parseInt(cbbNgaySinh.getSelectedItem().toString());
        int thang = Integer.parseInt(cbbThangSinh.getSelectedItem().toString());
        int nam = Integer.parseInt(cbbNamSinh.getSelectedItem().toString());
        if (txtMaDG.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chưa chọn mã đọc giả cần cập nhật.", "Thông báo", 0,imgTB.getImgWarning());
            txtMaDG.requestFocus();
        } else if (txtTenDG.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Tên đọc giả đang trống", "Thông báo", 0,imgTB.getImgWarning());
            txtTenDG.requestFocus();
        } else if (txtDiaChiDG.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Địa chỉ đang trống", "Thông báo", 0,imgTB.getImgWarning());
            txtDiaChiDG.requestFocus();
        } else if (txtEmail.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Email đang trống", "Thông báo", 0,imgTB.getImgWarning());
            txtEmail.requestFocus();
        } else if (txtSoDienThoai.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Số điện thoại đang trống", "Thông báo", 0,imgTB.getImgWarning());
            txtSoDienThoai.requestFocus();
        } else if (hxl.checkPhone(txtSoDienThoai.getText()) == false) {
            JOptionPane.showMessageDialog(rootPane, "Số điện thoại không đúng định dạng", "Thông báo", 0,imgTB.getImgWarning());
            txtSoDienThoai.requestFocus();
        } else {
            String maDG = txtMaDG.getText().trim();
            ArrayList<DocGia> lstDG = dgDAO.listDocGia();
            int ktraMa = 0;
            for (DocGia dg : lstDG) {//Kiem tra mã đọc giả
                if (maDG.equals(dg.getMaDG())) {
                    ktraMa = 1;
                    break;
                }
            }
            if (ktraMa == 0) {
                JOptionPane.showMessageDialog(rootPane, "Mã đọc giả cập nhật không tồn tại.", "Thông báo", 1,imgTB.getImgWarning());
                txtMaSach.requestFocus();
            } else {
                String tenDG = txtTenDG.getText().trim();
                String diaChi = txtDiaChiDG.getText().trim();
                String ngaySinh = nam + "-" + thang + "-" + ngay;
                String email = txtEmail.getText().trim();
                String sdt = txtSoDienThoai.getText().trim();
                String maThe = cbbMaThe.getSelectedItem().toString();
                String gioiTinh = cbbGioiTinh.getSelectedItem().toString();

                int ktraEmail = 1;
                int ktraSDT = 1;

                for (DocGia docGia : lstDG) {
                    if (maDG.equals(docGia.getMaDG()) == false && email.equals(docGia.getEmail())) {
                        ktraEmail = 0;
                        break;
                    }
                }
                for (DocGia docGia : lstDG) {
                    if (maDG.equals(docGia.getMaDG()) == false && sdt.equals(docGia.getSdt())) {
                        ktraSDT = 0;
                        break;
                    }
                }
                if (ktraEmail == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Email đã tồn tại.", "Thông báo", 1,imgTB.getImgWarning());
                    txtEmail.requestFocus();
                } else if (ktraSDT == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Số điện thoại đã tồn tại.", "Thông báo", 1,imgTB.getImgWarning());
                    txtSoDienThoai.requestFocus();
                } else {
                    DocGia dg = new DocGia(maDG, tenDG, diaChi, gioiTinh, email, sdt, maThe, ngaySinh);
                    if (dgDAO.capNhatDocGia(dg) == false) {
                        JOptionPane.showMessageDialog(rootPane, "Chưa được cập nhật\nVui lòng kiểm tra lại thông tin", "Thất bại", 0,imgTB.getImgError());

                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Đã Cập nhật", "Thành công", 1,imgTB.getImgCompleteGreen());
                        hienThiDocGia();
                        xoaTrangDocGia();
                        txtMaDG.requestFocus();
                        hideBtnDG();
                    }
                }
            }
        }
    }//GEN-LAST:event_btnCapNhatDGActionPerformed

    private void btnXoaDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDGActionPerformed
        String maDG = txtMaDG.getText();
        if (maDG.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chưa chọn độc giả cần xóa", "Thong báo", 1,imgTB.getImgWarning());
        } else {
            ArrayList<DocGia> lstDG = dgDAO.listDocGia();
            int ktraMa = 0;
            for (DocGia dg : lstDG) {
                if (maDG.equals(dg.getMaDG())) {
                    ktraMa = 1;
                    break;
                }
            }
            if (ktraMa == 0) {
                JOptionPane.showMessageDialog(rootPane, "Độc giả này không có", "Thong báo", 0,imgTB.getImgWarning());
            } else {
                int n = JOptionPane.showConfirmDialog(this, "Chấp nhận xóa ?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,imgTB.getImgQuestion());
                if (n == JOptionPane.YES_OPTION) {
                    boolean kq = dgDAO.xoaDocGia(maDG);
                    if (kq == true) {
                        JOptionPane.showMessageDialog(rootPane, "Đã xóa đọc giả:'" + maDG + "'", "Thành công", 1,imgTB.getImgCompleteGreen());
                        xoaTrangDocGia();
                        hienThiDocGia();
                        hideBtnDG();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Đọc giả:'" + maDG + "' chưa được xóa", "Thất bại", 0,imgTB.getImgError());

                    }
                }
            }
        }
    }//GEN-LAST:event_btnXoaDGActionPerformed

    private void btnXoaSearchDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSearchDGActionPerformed
        hienThiDocGia();
        hideBtnDG();
    }//GEN-LAST:event_btnXoaSearchDGActionPerformed

    private void btnFindMaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindMaSachActionPerformed
        showFindNXB();
    }//GEN-LAST:event_btnFindMaSachActionPerformed

    private void btnFindMaSach1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindMaSach1ActionPerformed
        showFindTacGia();
    }//GEN-LAST:event_btnFindMaSach1ActionPerformed

    private void btnFindMaSach2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindMaSach2ActionPerformed
        showFindTheLoai();
    }//GEN-LAST:event_btnFindMaSach2ActionPerformed

    private void btnLuuSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuSachActionPerformed
        if (txtMaSach.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng lấy mã sách.", "Thông báo", 0, imgTB.getImgWarning());
            txtMaSach.requestFocus();
        } else if (txtTenSach.getText().equals("") || hxl.kiemTraChuoiChuaToanKhoangTrang(txtTenSach.getText()) == true) {
            JOptionPane.showMessageDialog(rootPane, "Chưa nhập tên sách", "Thông báo", 0, imgTB.getImgWarning());
            txtTenSach.requestFocus();
        } else if (txtSoLuongSach.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chưa nhập số lượng sách", "Thông báo", 0, imgTB.getImgWarning());
            txtSoLuongSach.requestFocus();
        } else if (hxl.kTraChuoiIsNumber(txtSoLuongSach.getText()) == false || txtSoLuongSach.getText().equals("0")) {
            JOptionPane.showMessageDialog(rootPane, "Số lượng sách không hợp lệ", "Thông báo", 0, imgTB.getImgWarning());
            txtSoLuongSach.requestFocus();
        } else {
            String maS = txtMaSach.getText().trim();
            String tenS = txtTenSach.getText();
            int soLuong = Integer.parseInt(txtSoLuongSach.getText());
            int nam = Integer.parseInt(cbbNamXB.getSelectedItem().toString());//Lấy index trên cbb năm xuất bản
            //        String tacGia = txtTacGia.getText();
            String theLoai = cbbTheLoai.getSelectedItem().toString();//Lấy index trên cbb thể loại
            String nxb1 = cbbNXB.getSelectedItem().toString();
            String tacGia = cbbTacGia.getSelectedItem().toString();
            String maTL = "", maNXB = "", maTG = "";
            String pathImg = txtImg.getText().trim();
            if (pathImg.length() == 0) {
                pathImg = "src/imageSach/default.jpg";
            }
            ArrayList<TheLoai> lstTL = tlDAO.listTheLoai();
            ArrayList<NhaXuatBan> lstNXB = nxbDAO.listNXB();
            ArrayList<TacGia> lstTG = tgDAO.listTacGia();
            //Tìm mã thể loại theo combobox đã chọn
            for (TheLoai tl : lstTL) {
                if (tl.getTenTL().equals(theLoai)) {
                    maTL = tl.getMaTL();
                    break;
                }
            }
            //Tìm mã nxb theo combobox đã chọn
            for (NhaXuatBan nxb : lstNXB) {
                if (nxb.getTenNXB().equals(nxb1)) {
                    maNXB = nxb.getMaNXB();
                    break;
                }
            }
            //Tìm mã tác giả theo combobox đã chọn
            for (TacGia tg : lstTG) {
                if (tg.getTenTG().equals(tacGia)) {
                    maTG = tg.getMaTG();
                    break;
                }
            }
            Sach s = new Sach(maS, tenS, maTL, maTG, maNXB, pathImg, nam, soLuong);
            if (sDAO.themSach(s) == false) {
                JOptionPane.showMessageDialog(rootPane, "Thêm thất bại\nVui lòng kiểm tra lại thông tin", "Thông báo", 0, imgTB.getImgWarning());

            } else {
                JOptionPane.showMessageDialog(rootPane, "Đã thêm sách: '" + tenS + "' với mã sách: '" + maS + "'", "Thành công", 1, imgTB.getImgCompleteGreen());
                hienThiSach();
                txtMaSach.setText("");
                txtTenSach.setText("");
                txtSoLuongSach.setText("");

                txtMaSach.requestFocus();
            }
        }
    }//GEN-LAST:event_btnLuuSachActionPerformed

    private void btnCapNhatSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatSachActionPerformed
        if (txtMaSach.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chưa chọn mã sách cần cập nhật.", "Thông báo", 0, imgTB.getImgWarning());
            txtMaSach.requestFocus();
        } else if (txtTenSach.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chưa nhập tên sách", "Thông báo", 0, imgTB.getImgWarning());
            txtTenSach.requestFocus();
        } else if (txtSoLuongSach.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chưa nhập số lượng sách", "Thông báo", 0, imgTB.getImgWarning());
            txtSoLuongSach.requestFocus();
        } else if (hxl.kTraChuoiIsNumber(txtSoLuongSach.getText()) == false) {
            JOptionPane.showMessageDialog(rootPane, "Số lượng sách không hợp lệ", "Thông báo", 0, imgTB.getImgWarning());
            txtSoLuongSach.requestFocus();
        } else {
            String maS = txtMaSach.getText().trim();
            ArrayList<Sach> lstSach = sDAO.listSach();
            int ktraMa = 0;
            for (Sach s : lstSach) {
                if (maS.equals(s.getMaS())) {
                    ktraMa = 1;
                    break;
                }
            }
            if (ktraMa == 0) {//Kiem tra mã sách
                JOptionPane.showMessageDialog(rootPane, "Mã Sách cập nhật không tồn tại.", "Thông báo", 1, imgTB.getImgWarning());
                txtMaSach.requestFocus();
            } else {
                String tenS = txtTenSach.getText();
                int soLuong = Integer.parseInt(txtSoLuongSach.getText());
                int nam = Integer.parseInt(cbbNamXB.getSelectedItem().toString());//Lấy index trên cbb năm xuất bản
                String theLoai = cbbTheLoai.getSelectedItem().toString();//Lấy index trên cbb thể loại
                String nxb1 = cbbNXB.getSelectedItem().toString();
                String tacGia = cbbTacGia.getSelectedItem().toString();
                String maTL = "", maNXB = "", maTG = "";

                ArrayList<TheLoai> lstTL = tlDAO.listTheLoai();
                ArrayList<NhaXuatBan> lstNXB = nxbDAO.listNXB();
                ArrayList<TacGia> lstTG = tgDAO.listTacGia();
                //Tìm mã thể loại theo combobox đã chọn
                for (TheLoai tl : lstTL) {
                    if (tl.getTenTL().equals(theLoai)) {
                        maTL = tl.getMaTL();
                        break;
                    }
                }
                //Tìm mã nxb theo combobox đã chọn
                for (NhaXuatBan nxb : lstNXB) {
                    if (nxb.getTenNXB().equals(nxb1)) {
                        maNXB = nxb.getMaNXB();
                        break;
                    }
                }
                //Tìm mã tác giả theo combobox đã chọn
                for (TacGia tg : lstTG) {
                    if (tg.getTenTG().equals(tacGia)) {
                        maTG = tg.getMaTG();
                        break;
                    }
                }
                Sach s = new Sach(maS, tenS, maTL, maTG, maNXB, nam, soLuong);
                if (sDAO.capNhatSach(s) == false) {
                    JOptionPane.showMessageDialog(rootPane, "Thêm thất bại\nVui lòng kiểm tra lại thông tin", "Thông báo", 0, imgTB.getImgError());

                } else {
                    JOptionPane.showMessageDialog(rootPane, "Đã Cập nhật", "Thành công", 1, imgTB.getImgCompleteGreen());
                    hienThiSach();
                    txtMaSach.setText("");
                    txtTenSach.setText("");
                    txtSoLuongSach.setText("");

                    txtMaSach.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_btnCapNhatSachActionPerformed

    private void btnXoaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSachActionPerformed
        if (txtMaSach.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chưa chọn sách", "Thông báo", 0, imgTB.getImgWarning());
        } else {
            ArrayList<Sach> lstS = sDAO.listSach();
            int kt = 0;
            for (Sach s : lstS) {
                if (txtMaSach.getText().equals(s.getMaS())) {
                    kt = 1;
                    break;
                }
            }
            if (kt == 1) {
                int n = JOptionPane.showConfirmDialog(this, "Chấp nhận xóa", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, imgTB.getImgQuestion());
                if (n == JOptionPane.YES_OPTION) {
                    boolean kq = sDAO.xoaSach(txtMaSach.getText());
                    if (kq == true) {
                        JOptionPane.showMessageDialog(rootPane, "Đã xóa sách:'" + txtMaSach.getText() + "'", "Thành công", 1, imgTB.getImgCompleteGreen());
                        hienThiSach();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Không xóa được (Sách đã có người mượn)", "Thất bại", 0, imgTB.getImgError());
                    }
                }

            } else {
                JOptionPane.showMessageDialog(rootPane, "Sách '" + txtMaSach.getText() + "' không tồn tại", "Thất bại", 0, imgTB.getImgWarning());
            }
        }
    }//GEN-LAST:event_btnXoaSachActionPerformed

    private void btnLayMaDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLayMaDGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLayMaDGActionPerformed

    public String taoMaSachSuDong()//Sạo mã sách tự động từ 's0000000' -> 'S9999999'
    {
        String maSach = "";
        maSach = sDAO.layMaSachCuoiCung();
        ArrayList<Sach> lstS = sDAO.listSach();
        if (lstS.size() <= 0) {
            maSach = "S0000000";
        } else {
            int ma = Integer.parseInt(maSach);
            int ma2 = ma + 1;
            if (ma >= 0 && ma < 9) {
                maSach = "S000000" + String.valueOf(ma2);
            } else if (ma >= 9 && ma < 99) {
                maSach = "S00000" + String.valueOf(ma2);
            } else if (ma >= 99 && ma < 999) {
                maSach = "S0000" + String.valueOf(ma2);
            } else if (ma >= 999 && ma < 9999) {
                maSach = "S000" + String.valueOf(ma2);
            } else if (ma >= 9999 && ma < 99999) {
                maSach = "S00" + String.valueOf(ma2);
            } else if (ma >= 99999 && ma < 999999) {
                maSach = "S0" + String.valueOf(ma2);
            }
        }
        return maSach;
    }

    public void clearCBBFrmSach() {
        vctComBoBoxTacGia.clear();
        vctComBoBoxNXB.clear();
        vctComBoBoxNamXB.clear();
        vctComBoBoxTheLoai.clear();
        vctComBoBoxTinhTrang.clear();
    }

    public void xoaTrangDocGia() {
        txtMaDG.setText("");
        txtTenDG.setText("");
        txtDiaChiDG.setText("");
        txtEmail.setText("");
        txtSoDienThoai.setText("");

        txtTenDG.requestFocus();
    }

    public String taoMaDocGiaTuDong()//Tạo mã hóa dơn tự động từ 'HD000000' -> 'HD999999'
    {
        String maDG = "";
        maDG = dgDAO.layMaDocGiaCuoiCung();
        ArrayList<DocGia> lstDG = dgDAO.listDocGia();
        if (lstDG.size() <= 0) {
            maDG = "DG0000000";
        } else {
            int ma = Integer.parseInt(maDG);
            int ma2 = ma + 1;
            if (ma >= 0 && ma < 9) {
                maDG = "DG000000" + String.valueOf(ma2);
            } else if (ma >= 9 && ma < 99) {
                maDG = "DG00000" + String.valueOf(ma2);
            } else if (ma >= 99 && ma < 999) {
                maDG = "DG0000" + String.valueOf(ma2);
            } else if (ma >= 999 && ma < 9999) {
                maDG = "DG000" + String.valueOf(ma2);
            } else if (ma >= 9999 && ma < 99999) {
                maDG = "DG00" + String.valueOf(ma2);
            } else if (ma >= 99999 && ma < 999999) {
                maDG = "DG0" + String.valueOf(ma2);
            }
        }

        return maDG;
    }

    //
    void hideBtnNXB() {
        btnSaveNXB.setEnabled(false);
        btnUpNXB.setEnabled(false);
        btnDeleteNXB.setEnabled(false);
    }

    void hideBtnTG() {
        btnSaveTG.setEnabled(false);
        btnUpTG.setEnabled(false);
        btnDeleteTG.setEnabled(false);
    }

    void hideBtnTL() {
        btnSaveTL.setEnabled(false);
        btnUpTL.setEnabled(false);
        btnDeleteTL.setEnabled(false);
    }

    void showBtnNXB() {
        btnSaveNXB.setEnabled(true);
        btnUpNXB.setEnabled(true);
        btnDeleteNXB.setEnabled(true);
    }

    void showBtnTG() {
        btnSaveTG.setEnabled(true);
        btnUpTG.setEnabled(true);
        btnDeleteTG.setEnabled(true);
    }

    void showBtnTL() {
        btnSaveTL.setEnabled(true);
        btnUpTL.setEnabled(true);
        btnDeleteTL.setEnabled(true);
    }

    void emptyNXB() {
        txtMaNXB.setText("");
        txtTenNXB.setText("");
        txtEmailNXB.setText("");
        txtDCNXB.setText("");
        txtDaiDienNXB.setText("");
    }

    void emptyTG() {
        txtMaTG.setText("");
        txtTenTG.setText("");
        txtWebsite.setText("");
        txtGhiChuTG.setText("");
    }

    void emptyTL() {
        txtMaTL.setText("");
        txtTenTL.setText("");
    }

    int taoMaNXB() {
        return Integer.parseInt(nxbDAO.layMaNXBCuoiCung()) + 1;
    }

    int taoMaTG() {
        return Integer.parseInt(tgDAO.layMaTGCuoiCung()) + 1;
    }

    int taoMaTL() {
        return Integer.parseInt(tlDAO.layMaTLCuoiCung()) + 1;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatDG;
    private javax.swing.JButton btnCapNhatSach;
    private javax.swing.JButton btnDeleteNXB;
    private javax.swing.JButton btnDeleteTG;
    private javax.swing.JButton btnDeleteTL;
    private javax.swing.JButton btnFindMaSach;
    private javax.swing.JButton btnFindMaSach1;
    private javax.swing.JButton btnFindMaSach2;
    private javax.swing.JButton btnLayMaDG;
    private javax.swing.JButton btnLayMaSach;
    private javax.swing.JButton btnLuuDG;
    private javax.swing.JButton btnLuuSach;
    private javax.swing.JButton btnNewNXB;
    private javax.swing.JButton btnNewTG;
    private javax.swing.JButton btnNewTL;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnResetNXB;
    private javax.swing.JButton btnResetTG;
    private javax.swing.JButton btnResetTL;
    private javax.swing.JButton btnResetThe;
    private javax.swing.JButton btnSachDaHet;
    private javax.swing.JButton btnSaveNXB;
    private javax.swing.JButton btnSaveTG;
    private javax.swing.JButton btnSaveTL;
    private javax.swing.JButton btnSearchDocGia;
    private javax.swing.JButton btnSearchSach;
    private javax.swing.JButton btnThemDG;
    private javax.swing.JButton btnThemNXB;
    private javax.swing.JButton btnThemSach;
    private javax.swing.JButton btnThemTL;
    private javax.swing.JButton btnThemTacGia;
    private javax.swing.JButton btnThemThe;
    private javax.swing.JButton btnUpNXB;
    private javax.swing.JButton btnUpTG;
    private javax.swing.JButton btnUpTL;
    private javax.swing.JButton btnXoaDG;
    private javax.swing.JButton btnXoaSach;
    private javax.swing.JButton btnXoaSearchDG;
    private javax.swing.JComboBox<String> cbbGioiTinh;
    private javax.swing.JComboBox<String> cbbMaThe;
    private javax.swing.JComboBox<String> cbbNXB;
    private javax.swing.JComboBox<String> cbbNamMoThe;
    private javax.swing.JComboBox<String> cbbNamSinh;
    private javax.swing.JComboBox<String> cbbNamXB;
    private javax.swing.JComboBox<String> cbbNgayMoThe;
    private javax.swing.JComboBox<String> cbbNgaySinh;
    private javax.swing.JComboBox<String> cbbTacGia;
    private javax.swing.JComboBox<String> cbbThangMoThe;
    private javax.swing.JComboBox<String> cbbThangSinh;
    private javax.swing.JComboBox<String> cbbTheLoai;
    private javax.swing.JComboBox<String> cbbTinhTrang;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbHinh;
    private javax.swing.JLabel lbTBSDT;
    private javax.swing.JLabel lbtbNXB;
    private javax.swing.JLabel lbtbSL;
    private javax.swing.JTable tbDocGia;
    private javax.swing.JTable tbNXB;
    private javax.swing.JTable tbSach;
    private javax.swing.JTable tbTG;
    private javax.swing.JTable tbTL;
    private javax.swing.JTextArea txtDCNXB;
    private javax.swing.JTextField txtDaiDienNXB;
    private javax.swing.JTextField txtDiaChiDG;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmailNXB;
    private javax.swing.JTextField txtFindNXB;
    private javax.swing.JTextField txtFindTG;
    private javax.swing.JTextField txtFindTL;
    private javax.swing.JTextField txtGhiChuTG;
    private javax.swing.JTextField txtImg;
    private javax.swing.JTextField txtMaDG;
    private javax.swing.JTextField txtMaNXB;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextField txtMaTG;
    private javax.swing.JTextField txtMaTL;
    private javax.swing.JTextField txtSearchDocGia;
    private javax.swing.JTextField txtSearchSach;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtSoLuongSach;
    private javax.swing.JTextField txtTenDG;
    private javax.swing.JTextField txtTenNXB;
    private javax.swing.JTextField txtTenSach;
    private javax.swing.JTextField txtTenTG;
    private javax.swing.JTextField txtTenTL;
    private javax.swing.JTextField txtWebsite;
    // End of variables declaration//GEN-END:variables
}
