
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.HamXuLyChung;
import dao.NhanVienDAO;
import dao.TaiKhoanDAO;
import java.awt.Font;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyCode;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import pojo.ImageThongBao;
import pojo.NhanVien;
import pojo.TaiKhoan;

/**
 *
 * @author ADMIN
 */
public class frm_NhanVien extends javax.swing.JInternalFrame {

    public ArrayList<NhanVien> list;
    TaiKhoanDAO tkDAO = new TaiKhoanDAO();
    HamXuLyChung hxl = new HamXuLyChung();

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    void loadAccount(String user) {
        this.username = user;
    }

    /**
     * Creates new form frm_NhanVien
     */
    DefaultTableModel dfm = null;
    ButtonGroup btnGGioiTinh = new ButtonGroup();
    Vector vctHead = new Vector();//chứa tên cột cảu bảng
    Vector vctData = new Vector();//chứa data các dòng của bảng
    Vector vctCBBNgay = new Vector();//chứa data Day 
    Vector vctCBBThang = new Vector();//chứa data month
    Vector vctCBBNam = new Vector();//chứa data Year
    Vector vctCBBStatus = new Vector();//chứa data tình trạng

    Vector vctHeadTK = new Vector();//chứa titile của bảng account
    Vector vctDataTK = new Vector();//chứa data của account
    Vector vctCBBMaLoaiTK = new Vector();//chứa data combobox của mã loại tài khoản
    Vector vctCBBMaNV = new Vector();//chứa data combobox của mã Nhân viên
    ImageThongBao imgTB = new ImageThongBao();
    
    public frm_NhanVien() {
        initComponents();
        this.setSize(1400, 750);
        this.setTitle("Quản lý nhân viên");
        btnGGioiTinh.add(rbtnNam);
        btnGGioiTinh.add(rbtnNu);
        showNhanVien(vctHead, vctData);
        showCBB();
        txtMaNV.setEnabled(false);
        txtTuoi.setEnabled(false);
        rbtnNam.setSelected(true);
        hideUpDelete();
        btnSave.setEnabled(false);

        //Show panel account
        showAccount(vctHeadTK, vctDataTK);

    }

    public void showNhanVien(Vector vctHead, Vector vctData) {
        dfm = (DefaultTableModel) tbNhanVien.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHead.clear();
        vctData.clear();
        vctHead.add("Mã#");
        vctHead.add("Tên nhân viên");
        vctHead.add("Ngày sinh");
        vctHead.add("Số điện thoại");
        vctHead.add("Email:");
        vctHead.add("Giới tính");
        vctHead.add("Lương");
        vctHead.add("CMND/CCCD");
        vctHead.add("URL");
        vctHead.add("Status");
        //add từng dòng của bảng
        ArrayList<TaiKhoan> lstTK = tkDAO.listNhanVien();
        for (TaiKhoan tk : lstTK) {
            Vector vctRow = new Vector();
            vctRow.add(tk.getMaNV());//Cột 1
            vctRow.add(tk.getTenNV());//Cột 2
            vctRow.add(tk.getNgaySinh());//Cột 3
            vctRow.add(tk.getSdt());//Cột 4
            vctRow.add(tk.getEmail());//Cột 5
            vctRow.add(tk.getGioiTinh());//Cột 6
            vctRow.add(tk.getLuong());//Cột 7
            vctRow.add(tk.getCmnd());//Cột 8
            vctRow.add(tk.getHinh());//Cột 9
            vctRow.add(tk.getTinhTrang());//Cột 10
            vctData.add(vctRow);//dòng 1 
        }
        tbNhanVien.setModel(new DefaultTableModel(vctData, vctHead));
        setSizeTableNV();
    }

    void setSizeTableNV() {

//        tbMuonTra.setFont(new Font("Times New Roman", Font.PLAIN, 20));//Set font size cho chu
        int[] lstSize = {110, 200, 120, 120, 300, 60, 110, 140, 90, 120};
        tbNhanVien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < 10; i++) {
            tbNhanVien.getColumnModel().getColumn(i).setPreferredWidth(lstSize[i]);
        }
    }

    public void empty() {
        txtMaNV.setText("");
        txtTenNV.setText("");
        txtSoDienThoai.setText("");
        txtBrownser.setText("");
        txtEmail.setText("");
        txtTuoi.setText("");
        txtCMND.setText("");
        txtLuong.setText("");
        ImageIcon imgIcon = new ImageIcon("");
        lbHinh.setIcon(imgIcon);
    }

    void hideUpDelete() {
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);

    }

    void showUpDelete() {
        btnUpdate.setEnabled(true);
        btnDelete.setEnabled(true);

    }

    public void showCBB() {
        hxl.layNgayThangNam(vctCBBNgay, vctCBBThang, vctCBBNam);
        cbbNgay.setModel(new DefaultComboBoxModel(vctCBBNgay));
        cbbThang.setModel(new DefaultComboBoxModel(vctCBBThang));
        cbbNam.setModel(new DefaultComboBoxModel(vctCBBNam));
        vctCBBStatus.add("Đang làm");
        vctCBBStatus.add("Đã nghỉ việc");
        cbbTinhTrang.setModel(new DefaultComboBoxModel(vctCBBStatus));
    }

    public void showFindNV() {
        dfm = (DefaultTableModel) tbNhanVien.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHead.clear();
        vctData.clear();
        vctHead.add("Mã#");
        vctHead.add("Tên nhân viên");
        vctHead.add("Ngày sinh");
        vctHead.add("Số điện thoại");
        vctHead.add("Email:");
        vctHead.add("Giới tính");
        vctHead.add("Lương");
        vctHead.add("CMND/CCCD");
        vctHead.add("URL");
        vctHead.add("Status");
        //add từng dòng của bảng
        String find = txtSearchNV.getText().trim();
        ArrayList<TaiKhoan> lstNV = tkDAO.listNhanVien();
        for (TaiKhoan tk : lstNV) {
            //kiểm tra chuỗi tồn tại trong chuỗi
            if (tk.getMaNV().contains(find) || tk.getTenNV().contains(find) || tk.getSdt().contains(find) || tk.getEmail().contains(find) || tk.getCmnd().contains(find) || tk.getGioiTinh().contains(find)) {
                Vector vctDataFind = new Vector();
                vctDataFind.add(tk.getMaNV());//Cột 1
                vctDataFind.add(tk.getTenNV());//Cột 2
                vctDataFind.add(tk.getNgaySinh());//Cột 3
                vctDataFind.add(tk.getSdt());//Cột 4
                vctDataFind.add(tk.getEmail());//Cột 5
                vctDataFind.add(tk.getGioiTinh());//Cột 6
                vctDataFind.add(tk.getLuong());//Cột 7
                vctDataFind.add(tk.getCmnd());//Cột 8
                vctDataFind.add(tk.getHinh());//Cột 9
                vctDataFind.add(tk.getTinhTrang());//Cột 10
                vctData.add(vctDataFind);//dòng 1 
            }
        }
        tbNhanVien.setModel(new DefaultTableModel(vctData, vctHead));
        setSizeTableNV();
    }

    public void showNVTheoDieuKien(String dk) {
        dfm = (DefaultTableModel) tbNhanVien.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHead.clear();
        vctData.clear();
        vctHead.add("Mã#");
        vctHead.add("Tên nhân viên");
        vctHead.add("Ngày sinh");
        vctHead.add("Số điện thoại");
        vctHead.add("Email:");
        vctHead.add("Giới tính");
        vctHead.add("Lương");
        vctHead.add("CMND/CCCD");
        vctHead.add("URL");
        vctHead.add("Status");
        //add từng dòng của bảng
        String find = txtSearchNV.getText().trim();
        ArrayList<TaiKhoan> lstNV = tkDAO.listNhanVien();
        for (TaiKhoan tk : lstNV) {
            //kiểm tra chuỗi tồn tại trong chuỗi
            if (tk.getTinhTrang().equals(dk)) {
                Vector vctDataFind = new Vector();
                vctDataFind.add(tk.getMaNV());//Cột 1
                vctDataFind.add(tk.getTenNV());//Cột 2
                vctDataFind.add(tk.getNgaySinh());//Cột 3
                vctDataFind.add(tk.getSdt());//Cột 4
                vctDataFind.add(tk.getEmail());//Cột 5
                vctDataFind.add(tk.getGioiTinh());//Cột 6
                vctDataFind.add(tk.getLuong());//Cột 7
                vctDataFind.add(tk.getCmnd());//Cột 8
                vctDataFind.add(tk.getHinh());//Cột 9
                vctDataFind.add(tk.getTinhTrang());//Cột 10
                vctData.add(vctDataFind);//dòng 1 
            }
        }
        tbNhanVien.setModel(new DefaultTableModel(vctData, vctHead));
        setSizeTableNV();
    }

    public String taoMaNVTuDong()//Tạo mã nhân viên tự động từ 'HD000000' -> 'HD999999'
    {
        String maNV = "";
        maNV = tkDAO.layMaNVCuoiCung();
        ArrayList<TaiKhoan> lstTK = tkDAO.listTaiKhoan();
        if (lstTK.size() <= 0) {
            maNV = "NV0000000";
        } else {
            int ma = Integer.parseInt(maNV);
            int ma2 = ma + 1;
            if (ma >= 0 && ma < 9) {
                maNV = "NV000000" + String.valueOf(ma2);
            } else if (ma >= 9 && ma < 99) {
                maNV = "NV00000" + String.valueOf(ma2);
            } else if (ma >= 99 && ma < 999) {
                maNV = "NV0000" + String.valueOf(ma2);
            } else if (ma >= 999 && ma < 9999) {
                maNV = "NV000" + String.valueOf(ma2);
            } else if (ma >= 9999 && ma < 99999) {
                maNV = "NV00" + String.valueOf(ma2);
            } else if (ma >= 99999 && ma < 999999) {
                maNV = "NV0" + String.valueOf(ma2);
            }
        }
        return maNV;
    }

    public void labelEmpty() {
        lbtbLeft.setText("");
        lbtbRight.setText("");

    }

    public void showAccount(Vector vctHead, Vector vctData) {
        dfm = (DefaultTableModel) tbTaiKhoan.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHead.clear();
        vctData.clear();
        vctHead.add("Username");
        vctHead.add("Password (Encrypted)");
        vctHead.add("Account type");
        vctHead.add("Officer name");
        //add từng dòng của bảng
        ArrayList<TaiKhoan> lstTK = tkDAO.listTaiKhoan();
        for (TaiKhoan tk : lstTK) {
            Vector vctRow = new Vector();
            vctRow.add(tk.getUsername());//Cột 1
            vctRow.add(tk.getPassword());//Cột 2
            vctRow.add(tk.getTenLoaiTK());//Cột 3
            vctRow.add(tk.getTenNV());//Cột 4
            vctData.add(vctRow);//dòng 1 
        }
        tbTaiKhoan.setModel(new DefaultTableModel(vctDataTK, vctHeadTK));
        txtOfficerName.setEnabled(false);
        txtAccountName.setEnabled(false);

        hideErrorAcc();
        showCBBAccount();
        hideBtnAccount();
        hideCheckBoxCodeOfficer();
        hideTXTAcc();
        hideQuyTac();
        setSizeTableAcc();
    }

    void setSizeTableAcc() {
        vctHead.add("Username");
        vctHead.add("Password (Encrypted)");
        vctHead.add("Account type");
        vctHead.add("Officer name");
//        tbMuonTra.setFont(new Font("Times New Roman", Font.PLAIN, 20));//Set font size cho chu
        int[] lstSize = {150, 250, 135, 170};
        tbTaiKhoan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < 4; i++) {
            tbTaiKhoan.getColumnModel().getColumn(i).setPreferredWidth(lstSize[i]);
        }
    }

    public void showSearchAccount(Vector vctHead, Vector vctData) {
        dfm = (DefaultTableModel) tbTaiKhoan.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHead.clear();
        vctData.clear();
        vctHead.add("Username");
        vctHead.add("Password (Encrypted)");
        vctHead.add("Account type");
        vctHead.add("Officer name");
        //add từng dòng của bảng
        String find = txtSearchAccount.getText();
        ArrayList<TaiKhoan> lstTK = tkDAO.listTaiKhoan();
        for (TaiKhoan tk : lstTK) {
            if (tk.getUsername().contains(find) || tk.getLoaiTK().contains(find) || tk.getMaNV().contains(find) || tk.getTenNV().contains(find) || tk.getTenLoaiTK().contains(find)) {
                Vector vctRow = new Vector();
                vctRow.add(tk.getUsername());//Cột 1
                vctRow.add(tk.getPassword());//Cột 2
                vctRow.add(tk.getTenLoaiTK());//Cột 3
                vctRow.add(tk.getTenNV());//Cột 4
                vctData.add(vctRow);//dòng 1 
            }
        }
        tbTaiKhoan.setModel(new DefaultTableModel(vctDataTK, vctHeadTK));
        txtOfficerName.setEnabled(false);
        txtAccountName.setEnabled(false);

        hideErrorAcc();
        hideBtnAccount();
        hideCheckBoxCodeOfficer();
        hideTXTAcc();
    }

    public void showAccountLocked(Vector vctHead, Vector vctData) {
        dfm = (DefaultTableModel) tbTaiKhoan.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHead.clear();
        vctData.clear();
        vctHead.add("Username");
        vctHead.add("Password (Encrypted)");
        vctHead.add("Account type");
        vctHead.add("Officer name");
        //add từng dòng của bảng
        String find = txtSearchAccount.getText();
        ArrayList<TaiKhoan> lstTK = tkDAO.listTaiKhoan();
        for (TaiKhoan tk : lstTK) {
            if (tk.getLoaiTK().equals("lock")) {
                Vector vctRow = new Vector();
                vctRow.add(tk.getUsername());//Cột 1
                vctRow.add(tk.getPassword());//Cột 2
                vctRow.add(tk.getTenLoaiTK());//Cột 3
                vctRow.add(tk.getTenNV());//Cột 4
                vctData.add(vctRow);//dòng 1 
            }
        }
        tbTaiKhoan.setModel(new DefaultTableModel(vctDataTK, vctHeadTK));
        txtOfficerName.setEnabled(false);
        txtAccountName.setEnabled(false);

        hideErrorAcc();
        hideBtnAccount();
        hideCheckBoxCodeOfficer();
        hideTXTAcc();
        setSizeTableAcc();
    }

    void hideErrorAcc() {
        lbErrorUser.setVisible(false);
        lbErrorPass.setVisible(false);
    }

    void showErrorAcc() {
        lbErrorUser.setVisible(true);
        lbErrorPass.setVisible(true);
    }

    void hideErrorUser() {
        lbErrorUser.setVisible(false);
    }

    void showErrorUser() {
        lbErrorUser.setVisible(true);
    }

    void hideErrorPass() {
        lbErrorPass.setVisible(false);
    }

    void showErrorPass() {
        lbErrorPass.setVisible(true);
    }

    void hideBtnAccount() {
        btnSaveAcc.setEnabled(false);
        btnUpdateAcc.setEnabled(false);
        btnLockAcc.setEnabled(false);
        btnResetPass.setEnabled(false);
    }

    void showBtnAccount() {
        btnSaveAcc.setEnabled(true);
        btnUpdateAcc.setEnabled(true);
        btnLockAcc.setEnabled(true);
        btnResetPass.setEnabled(true);
    }

    void hideCheckBoxCodeOfficer() {
        cbbOfficerCode.setEnabled(false);
    }

    void showCheckBoxCodeOfficer() {
        cbbOfficerCode.setEnabled(true);
    }

    void emptyAccount() {
        txtUsername.setText("");
        txtPassword.setText("");
        txtAccountName.setText("");
        txtOfficerName.setText("");
    }

    String themNVDefault() {//tạo nhân viên mặc định khi tạo tài khoản
        String maNV = taoMaNVTuDong();
        String tenNV = "Default";
        TaiKhoan tk = new TaiKhoan();
        tk.setMaNV(maNV);
        tk.setTenNV(tenNV);
        tkDAO.themNhanVien(tk);
        return maNV;
    }

    void hideTXTAcc() {
        txtUsername.setEnabled(false);
        txtPassword.setEnabled(false);
    }

    void showTXTAcc() {
        txtUsername.setEnabled(true);
        txtPassword.setEnabled(true);
    }

    void hideQuyTac() {
        pnQuyTac.setVisible(false);
    }

    void showQuyTac() {
        pnQuyTac.setVisible(true);
    }

    void showCBBAccount() {
        vctCBBMaLoaiTK.clear();
        vctCBBMaNV.clear();

        ArrayList<TaiKhoan> lstTk = tkDAO.listLoaiTK();
        for (TaiKhoan tk : lstTk) {
            vctCBBMaLoaiTK.add(tk.getLoaiTK());
        }
        cbbAccountCode.setModel(new DefaultComboBoxModel(vctCBBMaLoaiTK));
        ArrayList<TaiKhoan> lstNV = tkDAO.listNhanVien();
        for (TaiKhoan tk : lstNV) {
            vctCBBMaNV.add(tk.getMaNV());
        }
        cbbOfficerCode.setModel(new DefaultComboBoxModel(vctCBBMaNV));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnBrownser = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        txtMaNV = new javax.swing.JTextField();
        txtTenNV = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        cbbNgay = new javax.swing.JComboBox<>();
        cbbThang = new javax.swing.JComboBox<>();
        cbbNam = new javax.swing.JComboBox<>();
        txtSoDienThoai = new javax.swing.JTextField();
        txtBrownser = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        txtTuoi = new javax.swing.JTextField();
        txtLuong = new javax.swing.JTextField();
        txtCMND = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        rbtnNam = new javax.swing.JRadioButton();
        rbtnNu = new javax.swing.JRadioButton();
        cbbTinhTrang = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lbtbLeft = new javax.swing.JLabel();
        lbtbRight = new javax.swing.JLabel();
        txtSearchNV = new javax.swing.JTextField();
        btnSearchNV = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbNhanVien = new javax.swing.JTable();
        lbHinh = new javax.swing.JLabel();
        btnXoaSearch = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbTaiKhoan = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        txtAccountName = new javax.swing.JTextField();
        cbbAccountCode = new javax.swing.JComboBox<>();
        jPanel16 = new javax.swing.JPanel();
        txtOfficerName = new javax.swing.JTextField();
        cbbOfficerCode = new javax.swing.JComboBox<>();
        jPanel17 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        cbMaNV = new javax.swing.JCheckBox();
        jPanel20 = new javax.swing.JPanel();
        lbErrorUser = new javax.swing.JLabel();
        lbErrorPass = new javax.swing.JLabel();
        pnQuyTac = new javax.swing.JPanel();
        pnUser = new javax.swing.JPanel();
        lbDKUser1 = new javax.swing.JLabel();
        lbDKUser2 = new javax.swing.JLabel();
        lbUser = new javax.swing.JLabel();
        lbPass = new javax.swing.JLabel();
        pnPass = new javax.swing.JPanel();
        lbDKPass1 = new javax.swing.JLabel();
        lbDKPass2 = new javax.swing.JLabel();
        lbDKPass3 = new javax.swing.JLabel();
        lbDKPass4 = new javax.swing.JLabel();
        lbDKPass5 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        btnAddAcc = new javax.swing.JButton();
        btnSaveAcc = new javax.swing.JButton();
        btnUpdateAcc = new javax.swing.JButton();
        btnLockAcc = new javax.swing.JButton();
        btnResetPass = new javax.swing.JButton();
        btnAccLocked = new javax.swing.JButton();
        txtSearchAccount = new javax.swing.JTextField();
        btnXoaSearchAcc = new javax.swing.JButton();
        btnSearchAcc = new javax.swing.JButton();

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin nhân viên", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 13))); // NOI18N

        jPanel4.setLayout(new java.awt.GridLayout(5, 1, 0, 10));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Mã nhân viên:");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel4.add(jLabel1);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Họ tên:");
        jPanel4.add(jLabel2);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Ngày sinh:");
        jPanel4.add(jLabel3);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Số điện thoại:");
        jPanel4.add(jLabel5);

        btnBrownser.setFont(new java.awt.Font("Times New Roman", 1, 19)); // NOI18N
        btnBrownser.setText("Path:");
        btnBrownser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnBrownser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrownserActionPerformed(evt);
            }
        });
        jPanel4.add(btnBrownser);

        jPanel5.setLayout(new java.awt.GridLayout(5, 1, 0, 10));

        txtMaNV.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jPanel5.add(txtMaNV);

        txtTenNV.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jPanel5.add(txtTenNV);

        jPanel6.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        cbbNgay.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        cbbNgay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel6.add(cbbNgay);

        cbbThang.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        cbbThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel6.add(cbbThang);

        cbbNam.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        cbbNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel6.add(cbbNam);

        jPanel5.add(jPanel6);

        txtSoDienThoai.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txtSoDienThoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoDienThoaiKeyReleased(evt);
            }
        });
        jPanel5.add(txtSoDienThoai);

        txtBrownser.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jPanel5.add(txtBrownser);

        jPanel8.setLayout(new java.awt.GridLayout(6, 1, 0, 10));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Tuổi:");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel8.setFocusable(false);
        jPanel8.add(jLabel8);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Lương:");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel6.setFocusable(false);
        jPanel8.add(jLabel6);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 19)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("CMND/CCCD:");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel9.setFocusable(false);
        jPanel8.add(jLabel9);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Email:");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel7.setFocusable(false);
        jPanel8.add(jLabel7);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Giới tính:");
        jPanel8.add(jLabel4);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Tình trạng:");
        jPanel8.add(jLabel10);

        jPanel9.setLayout(new java.awt.GridLayout(6, 1, 0, 10));

        txtTuoi.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jPanel9.add(txtTuoi);

        txtLuong.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txtLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLuongKeyReleased(evt);
            }
        });
        jPanel9.add(txtLuong);

        txtCMND.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txtCMND.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCMNDKeyReleased(evt);
            }
        });
        jPanel9.add(txtCMND);

        txtEmail.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });
        jPanel9.add(txtEmail);

        jPanel2.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        rbtnNam.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        rbtnNam.setText("Nam");
        jPanel2.add(rbtnNam);

        rbtnNu.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        rbtnNu.setText("Nữ");
        jPanel2.add(rbtnNu);

        jPanel9.add(jPanel2);

        cbbTinhTrang.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        cbbTinhTrang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel9.add(cbbTinhTrang);

        jPanel7.setLayout(new java.awt.GridLayout(1, 4, 100, 0));

        btnNew.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconPlus.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jPanel7.add(btnNew);

        btnSave.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconSave.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel7.add(btnSave);

        btnUpdate.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/update.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel7.add(btnUpdate);

        btnDelete.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/delete2.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel7.add(btnDelete);

        lbtbLeft.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbtbLeft.setForeground(new java.awt.Color(255, 0, 51));

        lbtbRight.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbtbRight.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 969, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbtbLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
                        .addGap(18, 19, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                            .addComponent(lbtbRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbtbLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbtbRight, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txtSearchNV.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtSearchNV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchNVKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchNVKeyTyped(evt);
            }
        });

        btnSearchNV.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        btnSearchNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Zoom-icon.png"))); // NOI18N
        btnSearchNV.setText("Tìm kiêm");
        btnSearchNV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSearchNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchNVActionPerformed(evt);
            }
        });

        tbNhanVien.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tbNhanVien.setFont(new java.awt.Font("Times New Roman", 0, 19)); // NOI18N
        tbNhanVien.setModel(new javax.swing.table.DefaultTableModel(
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
        tbNhanVien.setRowHeight(30);
        tbNhanVien.setRowMargin(7);
        tbNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbNhanVien);

        lbHinh.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Avata", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 13))); // NOI18N
        lbHinh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnXoaSearch.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnXoaSearch.setText("X");
        btnXoaSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSearchActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconOff.png"))); // NOI18N
        jButton1.setText("Đã nghỉ việc");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconHD2.png"))); // NOI18N
        jButton2.setText("Đang làm");
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSearchNV, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXoaSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearchNV)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton1)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnXoaSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnSearchNV)
                                        .addComponent(txtSearchNV, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(lbHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lý nhân viên", jPanel1);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tbTaiKhoan.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        tbTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
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
        tbTaiKhoan.setRowHeight(30);
        tbTaiKhoan.setRowMargin(6);
        tbTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTaiKhoanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbTaiKhoan);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Account Infomation", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 13))); // NOI18N

        jPanel12.setLayout(new java.awt.GridLayout(2, 1, 0, 15));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 19)); // NOI18N
        jLabel11.setText("Username:");
        jPanel12.add(jLabel11);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 19)); // NOI18N
        jLabel12.setText("Password:");
        jPanel12.add(jLabel12);

        jPanel13.setLayout(new java.awt.GridLayout(2, 1, 0, 15));

        txtUsername.setFont(new java.awt.Font("Times New Roman", 0, 19)); // NOI18N
        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsernameKeyReleased(evt);
            }
        });
        jPanel13.add(txtUsername);

        txtPassword.setFont(new java.awt.Font("Times New Roman", 0, 19)); // NOI18N
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPasswordKeyReleased(evt);
            }
        });
        jPanel13.add(txtPassword);

        jPanel14.setLayout(new java.awt.GridLayout(2, 1, 0, 15));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 19)); // NOI18N
        jLabel13.setText("Account type:");
        jPanel14.add(jLabel13);

        jPanel15.setLayout(new java.awt.GridLayout(2, 1, 0, 15));

        txtAccountName.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jPanel15.add(txtAccountName);

        cbbAccountCode.setFont(new java.awt.Font("Times New Roman", 0, 19)); // NOI18N
        cbbAccountCode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel15.add(cbbAccountCode);

        jPanel16.setLayout(new java.awt.GridLayout(2, 1, 0, 15));

        txtOfficerName.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jPanel16.add(txtOfficerName);

        cbbOfficerCode.setFont(new java.awt.Font("Times New Roman", 0, 19)); // NOI18N
        cbbOfficerCode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel16.add(cbbOfficerCode);

        jPanel17.setLayout(new java.awt.GridLayout(2, 1, 0, 15));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 19)); // NOI18N
        jLabel14.setText("Officer name:");
        jPanel17.add(jLabel14);

        cbMaNV.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cbMaNV.setText("For Officer");
        cbMaNV.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cbMaNV.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        cbMaNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbMaNVMouseClicked(evt);
            }
        });
        jPanel17.add(cbMaNV);

        jPanel20.setLayout(new java.awt.GridLayout(2, 1, 0, 15));

        lbErrorUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconError.png"))); // NOI18N
        lbErrorUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbErrorUserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbErrorUserMouseExited(evt);
            }
        });
        jPanel20.add(lbErrorUser);

        lbErrorPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconError.png"))); // NOI18N
        lbErrorPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbErrorPassMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbErrorPassMouseExited(evt);
            }
        });
        jPanel20.add(lbErrorPass);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                .addGap(16, 16, 16)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pnQuyTac.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quy tắc", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        pnUser.setLayout(new java.awt.GridLayout(2, 0, 0, 10));

        lbDKUser1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbDKUser1.setForeground(new java.awt.Color(255, 51, 51));
        lbDKUser1.setText("- Chữ thường, không dấu, không khoảng trắng.");
        pnUser.add(lbDKUser1);

        lbDKUser2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbDKUser2.setForeground(new java.awt.Color(255, 51, 51));
        lbDKUser2.setText("- Độ dài từ 6 kí tự trở lên.");
        pnUser.add(lbDKUser2);

        lbUser.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbUser.setForeground(new java.awt.Color(255, 51, 51));
        lbUser.setText("Username:");

        lbPass.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbPass.setForeground(new java.awt.Color(255, 51, 51));
        lbPass.setText("Password:");

        pnPass.setLayout(new java.awt.GridLayout(5, 1, 0, 5));

        lbDKPass1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbDKPass1.setForeground(new java.awt.Color(255, 51, 51));
        lbDKPass1.setText("- Tối thiểu 8 kí tự.");
        pnPass.add(lbDKPass1);

        lbDKPass2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbDKPass2.setForeground(new java.awt.Color(255, 51, 51));
        lbDKPass2.setText("- Chứa ít nhất 1 chữ cái viết Thường.");
        pnPass.add(lbDKPass2);

        lbDKPass3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbDKPass3.setForeground(new java.awt.Color(255, 51, 51));
        lbDKPass3.setText("- Chứa ít nhất 1 chữ cái viết Hoa");
        pnPass.add(lbDKPass3);

        lbDKPass4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbDKPass4.setForeground(new java.awt.Color(255, 51, 51));
        lbDKPass4.setText("- Chứa ít nhất 1 chữ số.");
        pnPass.add(lbDKPass4);

        lbDKPass5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbDKPass5.setForeground(new java.awt.Color(255, 51, 51));
        lbDKPass5.setText("- Không chứa khoảng trắng.");
        pnPass.add(lbDKPass5);

        javax.swing.GroupLayout pnQuyTacLayout = new javax.swing.GroupLayout(pnQuyTac);
        pnQuyTac.setLayout(pnQuyTacLayout);
        pnQuyTacLayout.setHorizontalGroup(
            pnQuyTacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQuyTacLayout.createSequentialGroup()
                .addGroup(pnQuyTacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnQuyTacLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnQuyTacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbUser)
                            .addComponent(lbPass)))
                    .addGroup(pnQuyTacLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(pnPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnQuyTacLayout.createSequentialGroup()
                .addGap(0, 47, Short.MAX_VALUE)
                .addComponent(pnUser, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        pnQuyTacLayout.setVerticalGroup(
            pnQuyTacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQuyTacLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbUser)
                .addGap(4, 4, 4)
                .addComponent(pnUser, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbPass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        jPanel21.setLayout(new java.awt.GridLayout(1, 4, 40, 0));

        btnAddAcc.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnAddAcc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconPlus.png"))); // NOI18N
        btnAddAcc.setText("ADD");
        btnAddAcc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAddAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAccActionPerformed(evt);
            }
        });
        jPanel21.add(btnAddAcc);

        btnSaveAcc.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnSaveAcc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconSave.png"))); // NOI18N
        btnSaveAcc.setText("Save");
        btnSaveAcc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSaveAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAccActionPerformed(evt);
            }
        });
        jPanel21.add(btnSaveAcc);

        btnUpdateAcc.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnUpdateAcc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/update.png"))); // NOI18N
        btnUpdateAcc.setText("Update");
        btnUpdateAcc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnUpdateAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateAccActionPerformed(evt);
            }
        });
        jPanel21.add(btnUpdateAcc);

        btnLockAcc.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnLockAcc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconLock.png"))); // NOI18N
        btnLockAcc.setText("Lock");
        btnLockAcc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLockAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLockAccActionPerformed(evt);
            }
        });
        jPanel21.add(btnLockAcc);

        btnResetPass.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnResetPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconUnlock.png"))); // NOI18N
        btnResetPass.setText("Reset password");
        btnResetPass.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnResetPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetPassActionPerformed(evt);
            }
        });

        btnAccLocked.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnAccLocked.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconAccLock2.png"))); // NOI18N
        btnAccLocked.setText("Account locked");
        btnAccLocked.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAccLocked.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccLockedActionPerformed(evt);
            }
        });

        txtSearchAccount.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtSearchAccount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchAccountKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchAccountKeyTyped(evt);
            }
        });

        btnXoaSearchAcc.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnXoaSearchAcc.setText("X");
        btnXoaSearchAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSearchAccActionPerformed(evt);
            }
        });

        btnSearchAcc.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        btnSearchAcc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Zoom-icon.png"))); // NOI18N
        btnSearchAcc.setText("Tìm kiêm");
        btnSearchAcc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSearchAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchAccActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnResetPass)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAccLocked))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtSearchAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaSearchAcc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearchAcc)))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnQuyTac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnXoaSearchAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnSearchAcc)
                                .addComponent(txtSearchAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnResetPass, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAccLocked, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnQuyTac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(39, 39, 39))))
        );

        jTabbedPane1.addTab("Quản lý tài khoản", jPanel10);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1384, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchNVActionPerformed
        showFindNV();
        hideUpDelete();

    }//GEN-LAST:event_btnSearchNVActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        String id = txtMaNV.getText();
        String name = txtTenNV.getText().trim();
        String phone = txtSoDienThoai.getText().trim();
        String day = cbbNgay.getSelectedItem().toString();
        String month = cbbThang.getSelectedItem().toString();
        String year = cbbNam.getSelectedItem().toString();
        String birthday = year + "-" + month + "-" + day;
        String salary = txtLuong.getText().trim();
        String status = cbbTinhTrang.getSelectedItem().toString();
        String cmnd = txtCMND.getText().trim();
        String email = txtEmail.getText().trim();
        String gender = "Nam";
        if (rbtnNu.isSelected()) {
            gender = "Nữ";
        }
        String imageURL = txtBrownser.getText().trim();
        if (imageURL.length() == 0) {
            imageURL = "src/HinhNhanVien/default.jpg";
        }
        //kiểm tra rỗng
        if (name.length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tên nhân viên không được để trống", "Thông báo", 0);
            txtTenNV.requestFocus();
        } else if (phone.length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Số điện thoại không được để trống", "Thông báo", 0);
            txtSoDienThoai.requestFocus();
        } else if (cmnd.length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "CMND/CCCD không được để trống", "Thông báo", 0);
            txtCMND.requestFocus();
        } else if (email.length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Email không được để trống", "Thông báo", 0);
            txtEmail.requestFocus();
        } //Kiểm tra Input
        else if (hxl.checkPhone(phone) == false) {
            JOptionPane.showMessageDialog(rootPane, "Số điện thoại không đúng định dạng", "Thông báo", 0);
            txtSoDienThoai.requestFocus();
        } else if (hxl.kTraChuoiIsNumber(salary) == false) {
            JOptionPane.showMessageDialog(rootPane, "Mức lương không đúng định dạng", "Thông báo", 0);
            txtLuong.requestFocus();
        } else if (hxl.kTraChuoiIsNumber(cmnd) == false) {
            JOptionPane.showMessageDialog(rootPane, "CMND/CCCD không đúng định dạng", "Thông báo", 0);
            txtLuong.requestFocus();
        } else if (hxl.checkEmail(email) == false) {
            JOptionPane.showMessageDialog(rootPane, "Email không đúng định dạng", "Thông báo", 0);
            txtEmail.requestFocus();
        } else if (hxl.kTraNgayHopLe(Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year)) == false) {
            JOptionPane.showMessageDialog(rootPane, "Ngày sinh không đúng định dạng", "Thông báo", 0);
        } else {
            //Kiểm tra tồn tại email, sđt,CMND/CCCD hay chưa
            boolean checkPhone = true;
            boolean checkEmail = true;
            boolean checkCMND = true;
            ArrayList<TaiKhoan> lstNV = tkDAO.listNhanVien();
            for (TaiKhoan tk : lstNV) {
                if (tk.getSdt().equals(phone)) {
                    checkPhone = false;
                }
                if (tk.getEmail().equals(email)) {
                    checkEmail = false;
                }
                if (tk.getCmnd().equals(cmnd)) {
                    checkCMND = false;
                }
            }
            if (checkPhone == false) {
                JOptionPane.showMessageDialog(rootPane, "Số điện thoại đã tồn tại", "Thông báo", 0);
                txtSoDienThoai.requestFocus();
            } else if (checkEmail == false) {
                JOptionPane.showMessageDialog(rootPane, "Email đã tồn tại", "Thông báo", 0);
                txtEmail.requestFocus();
            } else if (checkCMND == false) {
                JOptionPane.showMessageDialog(rootPane, "CMND/CCCD đã tồn tại", "Thông báo", 0);
                txtCMND.requestFocus();
            } else {//Tất cả hợp lệ
                int salary2 = 3000000;
                if (salary.length() == 0) {
                    salary2 = 3000000;
                }
                String info = "Mã: " + id + "\nName: " + name + "\nBirthday: " + birthday + "\nPhone: " + phone
                        + "\nGender: " + gender + "\nEmail: " + email + "\nCMND/CCCD: " + cmnd;
                int n = JOptionPane.showConfirmDialog(rootPane, "Thêm nhân viên này:\n" + info, "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,imgTB.getImgQuestion());
                if (n == JOptionPane.YES_OPTION) {
                    TaiKhoan tk = new TaiKhoan(id, name, phone, gender, birthday, imageURL, cmnd, email, salary2, status);
                    boolean kqInsertNV = tkDAO.themNhanVien(tk);
                    boolean kqInsertTK = tkDAO.themTaiKhoan(tk);
                    if (kqInsertNV == false || kqInsertTK == false) {
                        JOptionPane.showMessageDialog(rootPane, "Error:Check again, please", "Thất bại", 1);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Thêm thành công", "Thông báo", 3,imgTB.getImgCompleteGreen());
                        empty();
                        showNhanVien(vctHead, vctData);
                        showAccount(vctHeadTK, vctDataTK);
                        btnSave.setEnabled(false);
                    }
                }
            }
        }


    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtSearchNVKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchNVKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txtSearchNVKeyTyped

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        labelEmpty();
        empty();
        String maNV = taoMaNVTuDong();
        txtMaNV.setText(maNV);
        hideUpDelete();
        btnSave.setEnabled(true);
        JOptionPane.showMessageDialog(rootPane,"Đã tạo nhân viên với mã: " + maNV + "\nTiếp tục nhập các thông tin còn lại.", "Thông báo", 3, imgTB.getImgCompleteYellow());
        txtTenNV.requestFocus();
    }//GEN-LAST:event_btnNewActionPerformed

    private void txtSearchNVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchNVKeyReleased

    }//GEN-LAST:event_txtSearchNVKeyReleased

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String maNV = txtMaNV.getText();
        String tenNV = txtTenNV.getText();
        boolean checkAdmin = true;
        String user = "";
        //Lấy username
        ArrayList<TaiKhoan> lstTK = tkDAO.listTaiKhoan();

        for (TaiKhoan tk : lstTK) {
            if (tk.getMaNV().equals(maNV) && (tk.getLoaiTK().equals("ad") || tk.getLoaiTK().equals("lock"))) {
                checkAdmin = false;
            }
            if (tk.getMaNV().equals(maNV)) {
                user = tk.getUsername();
                break;
            }
        }
        if (this.username.equals("admin") == false && checkAdmin == false) {
            JOptionPane.showMessageDialog(rootPane, "Không thể thao tác trên người này.\n(Người này có quyền Admin hoặc tài khoản đã bị khóa)", "Error !", 0);
        } else {
//            int n = JOptionPane.showConfirmDialog(rootPane, "Bạn chắc muốn xóa " + tenNV + " không\n(Xóa nhân viên tự động khóa tài khoản của người này)?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            int n = JOptionPane.showConfirmDialog(rootPane, "Bạn chắc muốn xóa " + tenNV + " không\n(Xóa nhân viên tự động khóa tài khoản của người này)?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,imgTB.getImgQuestion());

            if (n == JOptionPane.YES_OPTION) {
                boolean kqKhoaTK = tkDAO.khoaTaiKhoan(user);
                boolean kqXoa = tkDAO.xoaNhanVien(maNV);
                if (kqKhoaTK == false || kqXoa == false) {
                    JOptionPane.showMessageDialog(rootPane, "Error:Check again, please", "Thất bại", 0);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Đã xóa", "Thông báo", 1,imgTB.getImgCompleteYellow());
                    empty();
                    showNhanVien(vctHead, vctData);
                    showAccount(vctHeadTK, vctDataTK);
                    hideUpDelete();
                }
            }
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tbNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNhanVienMouseClicked
        labelEmpty();
        int row = tbNhanVien.getSelectedRow();
        txtMaNV.setText(tbNhanVien.getValueAt(row, 0).toString().trim());
        txtTenNV.setText(tbNhanVien.getValueAt(row, 1).toString().trim());
        String ngaySinhSplit[] = tbNhanVien.getValueAt(row, 2).toString().split("-");//tách ngày thành DD,MM,YYYY
        String nam = ngaySinhSplit[0];
        String thang = ngaySinhSplit[1];
        String ngay = ngaySinhSplit[2];
        //Tìm vị trí combobox
        int indexD = hxl.timViTriVctcbb(vctCBBNgay, ngay);
        int indexMM = hxl.timViTriVctcbb(vctCBBThang, thang);
        int indexYYYY = hxl.timViTriVctcbb(vctCBBNam, nam);
        //set selected cho cbb tại index vừa tìm
        cbbNgay.setSelectedIndex(indexD);
        cbbThang.setSelectedIndex(indexMM);
        cbbNam.setSelectedIndex(indexYYYY);
        //Tuổi
        int tuoi = Year.now().getValue() - Integer.parseInt(nam);
        txtTuoi.setText(tuoi + "");
        txtSoDienThoai.setText(tbNhanVien.getValueAt(row, 3).toString().trim());
        txtEmail.setText(tbNhanVien.getValueAt(row, 4).toString().trim());
        //set giới tính
        String gt = tbNhanVien.getValueAt(row, 5).toString().trim();
        if (gt.equals("Nam")) {
            rbtnNam.setSelected(true);
        } else {
            rbtnNu.setSelected(true);
        }
        txtLuong.setText(tbNhanVien.getValueAt(row, 6).toString().trim());
        txtCMND.setText(tbNhanVien.getValueAt(row, 7).toString().trim());
        txtBrownser.setText(tbNhanVien.getValueAt(row, 8).toString().trim());
        //set Image
        ImageIcon icon = new ImageIcon(tbNhanVien.getValueAt(row, 8).toString().trim());
        lbHinh.setIcon(icon);
        //set tình trạng
        String status = tbNhanVien.getValueAt(row, 9).toString().trim();
        int indexStatus = hxl.timViTriVctcbb(vctCBBStatus, status);
        cbbTinhTrang.setSelectedIndex(indexStatus);
        btnSave.setEnabled(false);
        showUpDelete();
    }//GEN-LAST:event_tbNhanVienMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String id = txtMaNV.getText();
        String name = txtTenNV.getText().trim();
        String phone = txtSoDienThoai.getText().trim();
        String day = cbbNgay.getSelectedItem().toString();
        String month = cbbThang.getSelectedItem().toString();
        String year = cbbNam.getSelectedItem().toString();
        String birthday = year + "-" + month + "-" + day;
        String salary = txtLuong.getText().trim();
        String status = "";
        if (cbbTinhTrang.getSelectedIndex() < 0) {
            status = "Đang làm";
        } else {
            status = cbbTinhTrang.getSelectedItem().toString();
        }
        String cmnd = txtCMND.getText().trim();
        String email = txtEmail.getText().trim();
        String gender = "Nam";
        //kiểm tra ko cho thao tác trên người quản lý khác
        boolean checkAdmin = true;
        //Lấy username
        ArrayList<TaiKhoan> lstTK = tkDAO.listTaiKhoan();
        for (TaiKhoan tk : lstTK) {
            if (tk.getMaNV().equals(id) && (tk.getLoaiTK().equals("ad") || tk.getLoaiTK().equals("lock"))) {
                checkAdmin = false;
                break;
            }
        }
        if (this.username.equals("admin") == false && checkAdmin == false) {
            JOptionPane.showMessageDialog(rootPane, "Không thể thao tác trên người này.\n(Người này có quyền Admin hoặc tài khoản đã bị khóa)", "Error !",1, imgTB.getImgWarning());
        } else {
            if (rbtnNu.isSelected()) {
                gender = "Nữ";
            }
            String imageURL = txtBrownser.getText();
            if (imageURL.length() == 0) {
                imageURL = "src/HinhNhanVien/default.jpg";
            }
            //kiểm tra rỗng
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(rootPane, "Tên nhân viên không được để trống", "Thông báo", 0);
                txtTenNV.requestFocus();
            } else if (phone.length() == 0) {
                JOptionPane.showMessageDialog(rootPane, "Số điện thoại không được để trống", "Thông báo", 0);
                txtSoDienThoai.requestFocus();
            } else if (cmnd.length() == 0) {
                JOptionPane.showMessageDialog(rootPane, "CMND/CCCD không được để trống", "Thông báo", 0);
                txtCMND.requestFocus();
            } else if (email.length() == 0) {
                JOptionPane.showMessageDialog(rootPane, "Email không được để trống", "Thông báo", 0);
                txtEmail.requestFocus();
            } //Kiểm tra Input
            else if (hxl.checkPhone(phone) == false) {
                JOptionPane.showMessageDialog(rootPane, "Số điện thoại không đúng định dạng", "Thông báo", 0);
                txtSoDienThoai.requestFocus();
            } else if (hxl.kTraChuoiIsNumber(salary) == false) {
                JOptionPane.showMessageDialog(rootPane, "Mức lương không đúng định dạng", "Thông báo", 0);
                txtLuong.requestFocus();
            } else if (hxl.kTraChuoiIsNumber(cmnd) == false) {
                JOptionPane.showMessageDialog(rootPane, "CMND/CCCD không đúng định dạng", "Thông báo", 0);
                txtLuong.requestFocus();
            } else if (hxl.checkEmail(email) == false) {
                JOptionPane.showMessageDialog(rootPane, "Email không đúng định dạng", "Thông báo", 0);
                txtEmail.requestFocus();
            } else if (hxl.kTraNgayHopLe(Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year)) == false) {
                JOptionPane.showMessageDialog(rootPane, "Ngày sinh không đúng định dạng", "Thông báo", 0);
            } else {
                //Kiểm tra tồn tại email, sđt,CMND/CCCD hay chưa
                boolean checkPhone = true;
                boolean checkEmail = true;
                boolean checkCMND = true;
                ArrayList<TaiKhoan> lstNV = tkDAO.listNhanVien();
                for (TaiKhoan tk : lstNV) {
                    if (tk.getMaNV().equals(id) == false && tk.getSdt().equals(phone)) {
                        checkPhone = false;
                    }
                    if (tk.getMaNV().equals(id) == false && tk.getEmail().equals(email)) {
                        checkEmail = false;
                    }
                    if (tk.getMaNV().equals(id) == false && tk.getCmnd().equals(cmnd)) {
                        checkCMND = false;
                    }
                }
                if (checkPhone == false) {
                    JOptionPane.showMessageDialog(rootPane, "Số điện thoại đã tồn tại", "Thông báo", 0);
                    txtSoDienThoai.requestFocus();
                } else if (checkEmail == false) {
                    JOptionPane.showMessageDialog(rootPane, "Email đã tồn tại", "Thông báo", 0);
                    txtEmail.requestFocus();
                } else if (checkCMND == false) {
                    JOptionPane.showMessageDialog(rootPane, "CMND/CCCD đã tồn tại", "Thông báo", 0);
                    txtCMND.requestFocus();
                } else {//Tất cả hợp lệ
                    int salary2 = 3000000;
                    if (salary.length() == 0) {
                        salary2 = 3000000;
                    }
                    String info = "Mã: " + id + "\nName: " + name + "\nBirthday: " + birthday + "\nPhone: " + phone
                            + "\nGender: " + gender + "\nEmail: " + email + "\nCMND/CCCD: " + cmnd;
                    int n = JOptionPane.showConfirmDialog(rootPane, "Cập nhật thành:\n" + info, "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,imgTB.getImgWarning());
                    if (n == JOptionPane.YES_OPTION) {
                        TaiKhoan tk = new TaiKhoan(id, name, phone, gender, birthday, imageURL, cmnd, email, salary2, status);
                        if (tkDAO.capNhatNhanVien(tk) == false) {
                            JOptionPane.showMessageDialog(rootPane, "Error:Check again, please", "Thất bại", 0);
                        } else {
                            ImageIcon img = new ImageIcon("src/Icon/complete2.png");
                            JOptionPane.showMessageDialog(rootPane, "Đã cập nhật", "Thông báo", 1,imgTB.getImgCompleteGreen());
                            empty();
                            showNhanVien(vctHead, vctData);
                            showAccount(vctHeadTK, vctDataTK);
                            hideUpDelete();
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnBrownserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrownserActionPerformed
        JFileChooser fileChoose = new JFileChooser();//khai báo brownfile
        //Khai báo phần mở rộng
        FileNameExtensionFilter filter = new FileNameExtensionFilter("HinhNhanVien", "jpg", "png");
        fileChoose.setFileFilter(filter);
        int i = fileChoose.showOpenDialog(null);
        if (i == fileChoose.APPROVE_OPTION) {
            File f = fileChoose.getSelectedFile();//khai báo file = file dc chọn
            String filePath = "src/HinhNhanVien/" + f.getName();//hetName: tên file
            txtBrownser.setText(filePath);//đường dẫn
            try {
                ImageIcon icon = new ImageIcon(filePath);
                lbHinh.setIcon(icon);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnBrownserActionPerformed

    private void txtSoDienThoaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoDienThoaiKeyReleased
        String phone = txtSoDienThoai.getText();
        if (hxl.checkPhone(phone) == false) {
            lbtbLeft.setText("Phone không đúng định dạng");
        } else {
            lbtbLeft.setText("");
        }
    }//GEN-LAST:event_txtSoDienThoaiKeyReleased

    private void txtLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLuongKeyReleased
        String salary = txtLuong.getText();
        if (hxl.kTraChuoiIsNumber(salary) == false) {
            lbtbRight.setText("Mức lương không đúng định dạng");
        } else {
            lbtbRight.setText("");
        }
    }//GEN-LAST:event_txtLuongKeyReleased

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        String email = txtEmail.getText();
        if (hxl.checkEmail(email) == false) {
            lbtbRight.setText("Email không đúng định dạng");
        } else {
            lbtbRight.setText("");
        }
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtCMNDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCMNDKeyReleased
        String cmnd = txtCMND.getText();
        if (hxl.kTraChuoiIsNumber(cmnd) == false) {
            lbtbRight.setText("CMND/CCCD không đúng định dạng");
        } else {
            lbtbRight.setText("");
        }
    }//GEN-LAST:event_txtCMNDKeyReleased

    private void btnXoaSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSearchActionPerformed
        showNhanVien(vctHead, vctData);
        txtSearchNV.setText("");
        hideUpDelete();
        setSizeTableNV();
    }//GEN-LAST:event_btnXoaSearchActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        showNVTheoDieuKien("Đã nghỉ việc");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTaiKhoanMouseClicked
        showBtnAccount();
        hideTXTAcc();
        hideCheckBoxCodeOfficer();
        btnSaveAcc.setEnabled(false);
        cbMaNV.setEnabled(false);
        int index = tbTaiKhoan.getSelectedRow();
        String user = tbTaiKhoan.getValueAt(index, 0).toString();
        String maLoaiTk = "nv";
        String maNV = "";
        txtUsername.setText(user);
        txtPassword.setText(tbTaiKhoan.getValueAt(index, 1).toString());
        txtAccountName.setText(tbTaiKhoan.getValueAt(index, 2).toString());
        txtOfficerName.setText(tbTaiKhoan.getValueAt(index, 3).toString());
        //setselected cho combobox mã loại Account và mã nhân viên
        ArrayList<TaiKhoan> lstTK = tkDAO.listTaiKhoan();
        for (TaiKhoan tk : lstTK) {//tìm mã loại tk và mã nhân viên theo username
            if (tk.getUsername().equals(user)) {
                maLoaiTk = tk.getLoaiTK();
                maNV = tk.getMaNV();
                break;
            }
        }
        //Tìm vị trí combobox
        int indexMaLoaiTK = hxl.timViTriVctcbb(vctCBBMaLoaiTK, maLoaiTk);
        int indexMaNV = hxl.timViTriVctcbb(vctCBBMaNV, maNV);
        cbbAccountCode.setSelectedIndex(indexMaLoaiTK);
        cbbOfficerCode.setSelectedIndex(indexMaNV);
    }//GEN-LAST:event_tbTaiKhoanMouseClicked

    private void txtUsernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyReleased
        String username = txtUsername.getText().trim();
        if (username.length() > 0 && hxl.checkUsername(username) == false) {
            showErrorUser();
        } else {
            hideErrorUser();
        }
    }//GEN-LAST:event_txtUsernameKeyReleased

    private void txtPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyReleased
        String pass = txtPassword.getText().trim();
        if (pass.length() > 0 && hxl.checkPsssword(pass) == false) {
            showErrorPass();
        } else {
            hideErrorPass();
        }
    }//GEN-LAST:event_txtPasswordKeyReleased

    private void btnAddAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAccActionPerformed
        emptyAccount();
        hideBtnAccount();
        hideCheckBoxCodeOfficer();
        showTXTAcc();
        btnSaveAcc.setEnabled(true);
        txtUsername.requestFocus();
        cbMaNV.setEnabled(true);
    }//GEN-LAST:event_btnAddAccActionPerformed

    private void btnSaveAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAccActionPerformed
        String user = txtUsername.getText().trim();
        String pass = txtPassword.getText().trim();
        String passMD5 = hxl.getHashMD5(pass);
        String type = cbbAccountCode.getSelectedItem().toString();
        String Officercode = cbbOfficerCode.getSelectedItem().toString();
        boolean checkMaNV = true;//biến kiểm tra mã nhân viên đã có tài khoản hay chưa: true:Chua co
        boolean checkUser = true;//biến kiểm tra Username đã tồn tại hay chưa; true: hợp lệ
        ArrayList<TaiKhoan> lstTK = tkDAO.listTaiKhoan();
        for (TaiKhoan tk : lstTK) {
            if (tk.getMaNV().equals(Officercode))//Mã nhân viên đã có Acc
            {
                checkMaNV = false;
            }
            if (tk.getUsername().equals(user)) {//username đã tồn tại
                checkUser = false;
            }
        }

        //kiểm tra nhập rỗng
        if (user.length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Username connot empty !", "Error", 0,imgTB.getImgWarning());
            txtUsername.requestFocus();
        } else if (pass.length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Password connot empty !", "Error", 0,imgTB.getImgWarning());
            txtPassword.requestFocus();
        } else if (hxl.checkUsername(user) == false) {//check user đã đúng định dạng
            JOptionPane.showMessageDialog(rootPane, "Username không đúng định dạng !", "Error", 0,imgTB.getImgWarning());
            txtUsername.requestFocus();
        } else if (checkUser == false) {//check user đã tồn tại hay chưa
            JOptionPane.showMessageDialog(rootPane, "Username Already Exist !", "Error", 0,imgTB.getImgWarning());
            txtUsername.requestFocus();
        } else if (hxl.checkPsssword(pass) == false) {//check password đúng định dạng hay chưa
            JOptionPane.showMessageDialog(rootPane, "Password không đúng định dạng !", "Error", 0,imgTB.getImgWarning());
            txtPassword.requestFocus();
        } else {
            //Check tạo tài khoản cho nhân viên hay ko?
            //Check nhân viên đã có tài khoản hay chưa
            if (cbMaNV.isSelected() && checkMaNV == false) {//Đã có acc
                JOptionPane.showMessageDialog(rootPane, "Nhân viên này đã có tài khoản !", "Error", 0,imgTB.getImgWarning());
            } else if (cbMaNV.isSelected() && checkMaNV == true)//Chưa có acc
            {

                TaiKhoan tk = new TaiKhoan(user, passMD5, type, Officercode);
                boolean kq = tkDAO.themTaiKhoanPass(tk);
                if (kq == false) {
                    JOptionPane.showMessageDialog(rootPane, "Connot inssert !", "Error", 0);
                } else {
                    String info = "Username: " + user + "\nPassword: " + pass + "\nAccount type: " + type + "\nOfficer: " + Officercode;
                    JOptionPane.showMessageDialog(rootPane, "Đã thêm:\n" + info, "Success", 1);
                    showNhanVien(vctHead, vctData);
                    showAccount(vctHeadTK, vctDataTK);
                    emptyAccount();
                }
            } else {//thêm Account với nhân viên được tạo mặc định
                String maNVDefault = themNVDefault();
                TaiKhoan tk = new TaiKhoan(user, passMD5, type, maNVDefault);
                boolean kq = tkDAO.themTaiKhoanPass(tk);
                if (kq == false) {
                    JOptionPane.showMessageDialog(rootPane, "Connot inssert!", "Error", 0);
                } else {
                    String info = "Username: " + user + "\nPassword: " + pass + "\nAccount type: " + type;
                    JOptionPane.showMessageDialog(rootPane, "Đã thêm:\n" + info, "Success", 1,imgTB.getImgCompleteGreen());
                    showNhanVien(vctHead, vctData);
                    showAccount(vctHeadTK, vctDataTK);
                    emptyAccount();
                }
            }
        }


    }//GEN-LAST:event_btnSaveAccActionPerformed

    private void btnUpdateAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateAccActionPerformed
        String maLoaiTK = cbbAccountCode.getSelectedItem().toString();
        String user = txtUsername.getText().trim();
        boolean checkAdmin = true;
        //Lấy username
        ArrayList<TaiKhoan> lstTK = tkDAO.listTaiKhoan();

        for (TaiKhoan tk : lstTK) {
            if (tk.getUsername().equals(user) && tk.getLoaiTK().equals("ad")) {
                checkAdmin = false;
                break;
            }
        }
        if (this.username.equals("admin") == false && checkAdmin == false) {
            JOptionPane.showMessageDialog(rootPane, "Không thể thao tác trên Account quản lý.\n(Account này có quyền Admin)", "Error !", 0,imgTB.getImgWarning());
        } else {
            String quyen = "Nhân viên";
            if (maLoaiTK.equals("ad")) {
                quyen = "Quản lý";
            } else if (maLoaiTK.equals("lock")) {
                quyen = "lock";
            }
            if (quyen.equals("lock")) {
                JOptionPane.showMessageDialog(rootPane, "Không thể phân quyền này", "Thất bại", 0);
            } else {
                int n = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn phân quyền: " + quyen + " cho user: " + user, "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,imgTB.getImgQuestion());
                if (n == JOptionPane.YES_OPTION) {
                    boolean kq = tkDAO.phanQuyen(user, maLoaiTK);
                    if (kq == false) {
                        JOptionPane.showMessageDialog(rootPane, "Error:Check again, please", "Thất bại", 0);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Thành công", "Thông báo", 1,imgTB.getImgCompleteGreen());
                        empty();
                        showNhanVien(vctHead, vctData);
                        showAccount(vctHeadTK, vctDataTK);
                        hideUpDelete();
                        showCBBAccount();
                    }
                }
            }
        }
    }//GEN-LAST:event_btnUpdateAccActionPerformed

    private void btnLockAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLockAccActionPerformed
        String user = txtUsername.getText().trim();
        boolean checkAdmin = true;
        //Lấy username
        ArrayList<TaiKhoan> lstTK = tkDAO.listTaiKhoan();

        for (TaiKhoan tk : lstTK) {
            if (tk.getUsername().equals(user) && tk.getLoaiTK().equals("ad")) {
                checkAdmin = false;
                break;
            }
        }
        if (this.username.equals("admin") == false && checkAdmin == false) {
            JOptionPane.showMessageDialog(rootPane, "Không thể thao tác trên Account quản lý.\n(Account này có quyền Admin)", "Error !", 0,imgTB.getImgWarning());
        } else {
            int n = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc khóa Account: " + user + " ?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,imgTB.getImgQuestion());
            if (n == JOptionPane.YES_OPTION) {
                boolean kq = tkDAO.khoaTaiKhoan(user);
                if (kq == false) {
                    JOptionPane.showMessageDialog(rootPane, "Error:Check again, please", "Thất bại", 0);
                } else {
                    
                    JOptionPane.showMessageDialog(rootPane, "Đã khóa.", "Thông báo", 1,imgTB.getImgLock());
                    empty();
                    showNhanVien(vctHead, vctData);
                    showAccount(vctHeadTK, vctDataTK);
                    hideUpDelete();
                    showCBBAccount();
                }

            }
        }
    }//GEN-LAST:event_btnLockAccActionPerformed

    private void cbMaNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbMaNVMouseClicked
        if (cbMaNV.isSelected()) {
            showCheckBoxCodeOfficer();
        } else {
            hideCheckBoxCodeOfficer();
        }
    }//GEN-LAST:event_cbMaNVMouseClicked

    private void btnResetPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetPassActionPerformed
        int indexRow = tbTaiKhoan.getSelectedRow();
        String user = tbTaiKhoan.getValueAt(indexRow, 0).toString();
        String loaiTK = "nv";
        //Lấy username
        ArrayList<TaiKhoan> lstTK = tkDAO.listTaiKhoan();
        for (TaiKhoan tk : lstTK) {
            if (tk.getUsername().equals(user)) {
                loaiTK = tk.getLoaiTK();
                break;
            }
        }
        if (this.username.equals("admin") == false && loaiTK.equals("ad")) {
            JOptionPane.showMessageDialog(rootPane, "Không thể thao tác trên người quản lý khác\n(Người có quyền Admin)", "Error !", 0,imgTB.getImgWarning());
        } else {
            boolean kq = tkDAO.resetPassword(user);
            if (kq == true) {
                JOptionPane.showMessageDialog(rootPane, "Đã reset password.", "Success", 1,imgTB.getImgCompleteGreen());
                showAccount(vctHeadTK, vctDataTK);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Cannot Reset !", "Error !", 0,imgTB.getImgWarning());
            }
        }
    }//GEN-LAST:event_btnResetPassActionPerformed

    private void txtSearchAccountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchAccountKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchAccountKeyReleased

    private void txtSearchAccountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchAccountKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchAccountKeyTyped

    private void btnXoaSearchAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSearchAccActionPerformed
        txtSearchAccount.setText("");
        showAccount(vctHeadTK, vctDataTK);
        setSizeTableAcc();
    }//GEN-LAST:event_btnXoaSearchAccActionPerformed

    private void btnSearchAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchAccActionPerformed
        showSearchAccount(vctHeadTK, vctDataTK);
        setSizeTableAcc();
    }//GEN-LAST:event_btnSearchAccActionPerformed

    private void btnAccLockedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccLockedActionPerformed
        showAccountLocked(vctHeadTK, vctDataTK);

    }//GEN-LAST:event_btnAccLockedActionPerformed

    private void lbErrorUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbErrorUserMouseEntered
//        showQuyTacUser();
        showQuyTac();
    }//GEN-LAST:event_lbErrorUserMouseEntered

    private void lbErrorPassMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbErrorPassMouseEntered
        showQuyTac();
    }//GEN-LAST:event_lbErrorPassMouseEntered

    private void lbErrorUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbErrorUserMouseExited
//        hideQuyTacUser();
        hideQuyTac();
    }//GEN-LAST:event_lbErrorUserMouseExited

    private void lbErrorPassMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbErrorPassMouseExited
        hideQuyTac();
    }//GEN-LAST:event_lbErrorPassMouseExited

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        showNVTheoDieuKien("Đang làm");
    }//GEN-LAST:event_jButton2ActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new frm_NhanVien().setVisible(true);
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccLocked;
    private javax.swing.JButton btnAddAcc;
    private javax.swing.JButton btnBrownser;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnLockAcc;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnResetPass;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSaveAcc;
    private javax.swing.JButton btnSearchAcc;
    private javax.swing.JButton btnSearchNV;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdateAcc;
    private javax.swing.JButton btnXoaSearch;
    private javax.swing.JButton btnXoaSearchAcc;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbMaNV;
    private javax.swing.JComboBox<String> cbbAccountCode;
    private javax.swing.JComboBox<String> cbbNam;
    private javax.swing.JComboBox<String> cbbNgay;
    private javax.swing.JComboBox<String> cbbOfficerCode;
    private javax.swing.JComboBox<String> cbbThang;
    private javax.swing.JComboBox<String> cbbTinhTrang;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbDKPass1;
    private javax.swing.JLabel lbDKPass2;
    private javax.swing.JLabel lbDKPass3;
    private javax.swing.JLabel lbDKPass4;
    private javax.swing.JLabel lbDKPass5;
    private javax.swing.JLabel lbDKUser1;
    private javax.swing.JLabel lbDKUser2;
    private javax.swing.JLabel lbErrorPass;
    private javax.swing.JLabel lbErrorUser;
    private javax.swing.JLabel lbHinh;
    private javax.swing.JLabel lbPass;
    private javax.swing.JLabel lbUser;
    private javax.swing.JLabel lbtbLeft;
    private javax.swing.JLabel lbtbRight;
    private javax.swing.JPanel pnPass;
    private javax.swing.JPanel pnQuyTac;
    private javax.swing.JPanel pnUser;
    private javax.swing.JRadioButton rbtnNam;
    private javax.swing.JRadioButton rbtnNu;
    private javax.swing.JTable tbNhanVien;
    private javax.swing.JTable tbTaiKhoan;
    private javax.swing.JTextField txtAccountName;
    private javax.swing.JTextField txtBrownser;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtOfficerName;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtSearchAccount;
    private javax.swing.JTextField txtSearchNV;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTuoi;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
