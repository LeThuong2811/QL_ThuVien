/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.DocGiaDAO;
import dao.HamXuLyChung;
import dao.MuonTraDAO;
import dao.NhaXuatBanDAO;
import dao.SachDAO;
import dao.TacGiaDAO;
import dao.TheLoaiDAO;
import dao.TheThuVienDAO;
import dao.ThongKeDAO;
import static gui.frm_MuonTra.dateNow;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pojo.DocGia;
import pojo.ImageThongBao;
import pojo.MuonTra;
import pojo.NhaXuatBan;
import pojo.Sach;
import pojo.TacGia;
import pojo.TheLoai;
import pojo.TheThuVien;

/**
 *
 * @author ADMIN
 */
public class frm_ThongKe extends javax.swing.JInternalFrame {

    /**
     * Creates new form frm_ThongKe
     */
    ButtonGroup btnGDK1 = new ButtonGroup();
    ButtonGroup btnGDK2 = new ButtonGroup();
    HamXuLyChung hxl = new HamXuLyChung();
    SachDAO sDAO = new SachDAO();
    DocGiaDAO dgDAO = new DocGiaDAO();
    TacGiaDAO tgDAO = new TacGiaDAO();
    NhaXuatBanDAO nxbDAO = new NhaXuatBanDAO();
    TheLoaiDAO tlDAO = new TheLoaiDAO();
    TheThuVienDAO ttvDAO = new TheThuVienDAO();
    ThongKeDAO tkDAO = new ThongKeDAO();
    MuonTraDAO mtDAO = new MuonTraDAO();
    ImageThongBao imgTB = new ImageThongBao();
    DefaultTableModel dfm = null;
    Vector vctCBBStartNgay = new Vector();
    Vector vctCBBStartThang = new Vector();
    Vector vctCBBStartNam = new Vector();

    Vector vctCBBEndNgay = new Vector();
    Vector vctCBBEndThang = new Vector();
    Vector vctCBBEndNam = new Vector();

    Vector vctHead = new Vector();
    Vector vctData = new Vector();

    public static String dateNow = LocalDate.now().toString();

    public frm_ThongKe() {
        initComponents();
        this.setTitle("Thống kê");
        //add cho group1
        btnGDK1.add(rbtnTheoSach);
        btnGDK1.add(rbtnTheoDocGia);
        btnGDK1.add(rbtnTheoNXB);
        btnGDK1.add(rbtnTheoTacGia);
        btnGDK1.add(rbtnTheoTheLoai);
        btnGDK1.add(rbtnDGHetHanThe);
        btnGDK1.add(rbtnSachDaMuonHet);
        btnGDK1.add(rbtnViPham);
        //add cho group2
        btnGDK2.add(rbtnAllTime);
        btnGDK2.add(rbtnThang);
        btnGDK2.add(rbtnTuan);
        btnGDK2.add(rbtnTimeDK);

        rbtnTheoSach.setSelected(true);
        rbtnAllTime.setSelected(true);

        layCBBDMY();
        showMuonTheoSach("all");

    }

    void layCBBDMY() {
        //Lấy combobox ngày bắt đầu
        hxl.layNgayThangNam(vctCBBStartNgay, vctCBBStartThang, vctCBBStartNam);
        cbbStartD.setModel(new DefaultComboBoxModel(vctCBBStartNgay));
        cbbStartMM.setModel(new DefaultComboBoxModel(vctCBBStartThang));
        cbbStartYYYY.setModel(new DefaultComboBoxModel(vctCBBStartNam));
        //Lấy combobox ngày kết thúc
        hxl.layNgayThangNam(vctCBBEndNgay, vctCBBEndThang, vctCBBEndNam);
        cbbEndD.setModel(new DefaultComboBoxModel(vctCBBEndNgay));
        cbbEndMM.setModel(new DefaultComboBoxModel(vctCBBEndThang));
        cbbEndYYYY.setModel(new DefaultComboBoxModel(vctCBBEndNam));
    }

    void showMuonTheoSach(String dk) {
        dfm = (DefaultTableModel) tbChung.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHead.clear();
        vctData.clear();
        vctHead.add("Mã sách");
        vctHead.add("Tên sách");
        vctHead.add("Số lượt mượn");
        ArrayList<Sach> lstSach = new ArrayList<>();
        if (dk.equals("all")) {
            lstSach = tkDAO.listSach();
        } else if (dk.equals("thang")) {
            lstSach = tkDAO.listSachThang();
        } else if (dk.equals("tuan")) {
            lstSach = tkDAO.listSachTuan();
        } else {
            //ngày bắt đầu
            String ngayStart = cbbStartD.getSelectedItem().toString();
            String thangStart = cbbStartMM.getSelectedItem().toString();
            String namStart = cbbStartYYYY.getSelectedItem().toString();
            String dateStart = namStart + "-" + thangStart + "-" + ngayStart;
            //ngày kết thúc
            String ngayEnd = cbbEndD.getSelectedItem().toString();
            String thangEnd = cbbEndMM.getSelectedItem().toString();
            String namEnd = cbbEndYYYY.getSelectedItem().toString();
            String dateEnd = namEnd + "-" + thangEnd + "-" + ngayEnd;
            //Kiểm tra ngày hợp lệ
            if (hxl.kTraNgayHopLe(Integer.parseInt(ngayStart), Integer.parseInt(thangStart), Integer.parseInt(namStart)) == false) {
                JOptionPane.showMessageDialog(rootPane, "Ngày bắt đầu: '" + dateStart + "' không hợp lệ", "Thất bại", 0);
            } else if (hxl.kTraNgayHopLe(Integer.parseInt(ngayEnd), Integer.parseInt(thangEnd), Integer.parseInt(namEnd)) == false) {
                JOptionPane.showMessageDialog(rootPane, "Ngày kết thúc: '" + dateEnd + "' không hợp lệ", "Thất bại", 0);
            } else {
                // kiểm tra ngày kết thúc phải lớn hơn ngày bắt đầu
                if (hxl.ktraNgay(dateStart, dateEnd) == false) {
                    JOptionPane.showMessageDialog(rootPane, "Ngày kết thúc không được nhỏ hơn ngày: '" + dateStart + "'.", "Thất bại", 0);
                } else {//hợp lệ
                    lstSach = tkDAO.listSachStartEnd(dateStart, dateEnd);
                }
            }
        }
        //add từng dòng của bảng
        for (Sach s : lstSach) {
            Vector vctRow = new Vector();
            vctRow.add(s.getMaS());//Cột 1
            vctRow.add(s.getTenS());//Cột 2
            vctRow.add(s.getSoLuotMuon());//Cột 3
            vctData.add(vctRow);//dòng 1
        }
        tbChung.setModel(new DefaultTableModel(vctData, vctHead));
    }

    void showMuonTheoDocGia(String dk) {
        dfm = (DefaultTableModel) tbChung.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHead.clear();
        vctData.clear();
        vctHead.add("Mã đọc giả");
        vctHead.add("Tên đọc giả");
        vctHead.add("Số lượt mượn");

        //add từng dòng của bảng
        ArrayList<DocGia> lstDocGia = new ArrayList<>();
        if (dk.equals("all")) {
            lstDocGia = tkDAO.listDocGia();
        } else if (dk.equals("thang")) {
            lstDocGia = tkDAO.listDocGiaThang();
        } else if (dk.equals("tuan")) {
            lstDocGia = tkDAO.listDocGiaTuan();
        } else {
            //ngày bắt đầu
            String ngayStart = cbbStartD.getSelectedItem().toString();
            String thangStart = cbbStartMM.getSelectedItem().toString();
            String namStart = cbbStartYYYY.getSelectedItem().toString();
            String dateStart = namStart + "-" + thangStart + "-" + ngayStart;
            //ngày kết thúc
            String ngayEnd = cbbEndD.getSelectedItem().toString();
            String thangEnd = cbbEndMM.getSelectedItem().toString();
            String namEnd = cbbEndYYYY.getSelectedItem().toString();
            String dateEnd = namEnd + "-" + thangEnd + "-" + ngayEnd;
            //Kiểm tra ngày hợp lệ
            if (hxl.kTraNgayHopLe(Integer.parseInt(ngayStart), Integer.parseInt(thangStart), Integer.parseInt(namStart)) == false) {
                JOptionPane.showMessageDialog(rootPane, "Ngày bắt đầu: '" + dateStart + "' không hợp lệ", "Thất bại", 0);
            } else if (hxl.kTraNgayHopLe(Integer.parseInt(ngayEnd), Integer.parseInt(thangEnd), Integer.parseInt(namEnd)) == false) {
                JOptionPane.showMessageDialog(rootPane, "Ngày kết thúc: '" + dateEnd + "' không hợp lệ", "Thất bại", 0);
            } else {
                // kiểm tra ngày kết thúc phải lớn hơn ngày bắt đầu
                if (hxl.ktraNgay(dateStart, dateEnd) == false) {
                    JOptionPane.showMessageDialog(rootPane, "Ngày kết thúc không được nhỏ hơn ngày: '" + dateStart + "'.", "Thất bại", 0);
                } else {//hợp lệ
                    lstDocGia = tkDAO.listDocStartEnd(dateStart, dateEnd);
                }
            }
        }
        for (DocGia dg : lstDocGia) {
            Vector vctRow = new Vector();
            vctRow.add(dg.getMaDG());//Cột 1
            vctRow.add(dg.getTenDG());//Cột 2
            vctRow.add(dg.getSoLuotMuon());//Cột 3
            vctData.add(vctRow);//dòng 1 
        }
        tbChung.setModel(new DefaultTableModel(vctData, vctHead));
    }

    void showMuonNXB(String dk) {
        dfm = (DefaultTableModel) tbChung.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHead.clear();
        vctData.clear();
        vctHead.add("Mã nhà xuất bản");
        vctHead.add("Tên nhà xuát bản");
        vctHead.add("Số lượt mượn");

        //add từng dòng của bảng
        ArrayList<NhaXuatBan> lstNXB = new ArrayList<>();
        if (dk.equals("all")) {
            lstNXB = tkDAO.listNXB();
        } else if (dk.equals("thang")) {
            lstNXB = tkDAO.listNXBThang();
        } else if (dk.equals("tuan")) {
            lstNXB = tkDAO.listNXBTuan();
        } else {
            //ngày bắt đầu
            String ngayStart = cbbStartD.getSelectedItem().toString();
            String thangStart = cbbStartMM.getSelectedItem().toString();
            String namStart = cbbStartYYYY.getSelectedItem().toString();
            String dateStart = namStart + "-" + thangStart + "-" + ngayStart;
            //ngày kết thúc
            String ngayEnd = cbbEndD.getSelectedItem().toString();
            String thangEnd = cbbEndMM.getSelectedItem().toString();
            String namEnd = cbbEndYYYY.getSelectedItem().toString();
            String dateEnd = namEnd + "-" + thangEnd + "-" + ngayEnd;
            //Kiểm tra ngày hợp lệ
            if (hxl.kTraNgayHopLe(Integer.parseInt(ngayStart), Integer.parseInt(thangStart), Integer.parseInt(namStart)) == false) {
                JOptionPane.showMessageDialog(rootPane, "Ngày bắt đầu: '" + dateStart + "' không hợp lệ", "Thất bại", 0);
            } else if (hxl.kTraNgayHopLe(Integer.parseInt(ngayEnd), Integer.parseInt(thangEnd), Integer.parseInt(namEnd)) == false) {
                JOptionPane.showMessageDialog(rootPane, "Ngày kết thúc: '" + dateEnd + "' không hợp lệ", "Thất bại", 0);
            } else {
                // kiểm tra ngày kết thúc phải lớn hơn ngày bắt đầu
                if (hxl.ktraNgay(dateStart, dateEnd) == false) {
                    JOptionPane.showMessageDialog(rootPane, "Ngày kết thúc không được nhỏ hơn ngày: '" + dateStart + "'.", "Thất bại", 0);
                } else {//hợp lệ
                    lstNXB = tkDAO.listNXBStartEnd(dateStart, dateEnd);
                }
            }
        }
        for (NhaXuatBan nxb : lstNXB) {
            Vector vctRow = new Vector();
            vctRow.add(nxb.getMaNXB());//Cột 1
            vctRow.add(nxb.getTenNXB());//Cột 2
            vctRow.add(nxb.getSoLuotMuon());//Cột 3
            vctData.add(vctRow);//dòng 1 
        }
        tbChung.setModel(new DefaultTableModel(vctData, vctHead));
    }

    void showMuonTheoTacGia(String dk) {
        dfm = (DefaultTableModel) tbChung.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHead.clear();
        vctData.clear();
        vctHead.add("Mã tác giả");
        vctHead.add("Tên tác giả");
        vctHead.add("Số lượt mượn");

        //add từng dòng của bảng
        ArrayList<TacGia> lstTG = new ArrayList<>();
        if (dk.equals("all")) {
            lstTG = tkDAO.listTacGia();
        } else if (dk.equals("thang")) {
            lstTG = tkDAO.listTacGiaThang();
        } else if (dk.equals("tuan")) {
            lstTG = tkDAO.listTacGiaTuan();
        } else {
            //ngày bắt đầu
            String ngayStart = cbbStartD.getSelectedItem().toString();
            String thangStart = cbbStartMM.getSelectedItem().toString();
            String namStart = cbbStartYYYY.getSelectedItem().toString();
            String dateStart = namStart + "-" + thangStart + "-" + ngayStart;
            //ngày kết thúc
            String ngayEnd = cbbEndD.getSelectedItem().toString();
            String thangEnd = cbbEndMM.getSelectedItem().toString();
            String namEnd = cbbEndYYYY.getSelectedItem().toString();
            String dateEnd = namEnd + "-" + thangEnd + "-" + ngayEnd;
            //Kiểm tra ngày hợp lệ
            if (hxl.kTraNgayHopLe(Integer.parseInt(ngayStart), Integer.parseInt(thangStart), Integer.parseInt(namStart)) == false) {
                JOptionPane.showMessageDialog(rootPane, "Ngày bắt đầu: '" + dateStart + "' không hợp lệ", "Thất bại", 0);
            } else if (hxl.kTraNgayHopLe(Integer.parseInt(ngayEnd), Integer.parseInt(thangEnd), Integer.parseInt(namEnd)) == false) {
                JOptionPane.showMessageDialog(rootPane, "Ngày kết thúc: '" + dateEnd + "' không hợp lệ", "Thất bại", 0);
            } else {
                // kiểm tra ngày kết thúc phải lớn hơn ngày bắt đầu
                if (hxl.ktraNgay(dateStart, dateEnd) == false) {
                    JOptionPane.showMessageDialog(rootPane, "Ngày kết thúc không được nhỏ hơn ngày: '" + dateStart + "'.", "Thất bại", 0);
                } else {//hợp lệ
                    lstTG = tkDAO.listTacGiaStartEnd(dateStart, dateEnd);
                }
            }
        }
        for (TacGia tg : lstTG) {
            Vector vctRow = new Vector();
            vctRow.add(tg.getMaTG());//Cột 1
            vctRow.add(tg.getTenTG());//Cột 2
            vctRow.add(tg.getSoLuotMuon());//Cột 3
            vctData.add(vctRow);//dòng 1 
        }
        tbChung.setModel(new DefaultTableModel(vctData, vctHead));
    }

    void showMuonTheoTheLoai(String dk) {
        dfm = (DefaultTableModel) tbChung.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHead.clear();
        vctData.clear();
        vctHead.add("Mã thể loại");
        vctHead.add("Tên thể loại");
        vctHead.add("Số lượt mượn");

        //add từng dòng của bảng
        ArrayList<TheLoai> lstTL = new ArrayList<>();
        if (dk.equals("all")) {
            lstTL = tkDAO.listTheLoai();
        } else if (dk.equals("thang")) {
            lstTL = tkDAO.listTheLoaiThang();
        } else if (dk.equals("tuan")) {
            lstTL = tkDAO.listTheLoaiTuan();
        } else {
            //ngày bắt đầu
            String ngayStart = cbbStartD.getSelectedItem().toString();
            String thangStart = cbbStartMM.getSelectedItem().toString();
            String namStart = cbbStartYYYY.getSelectedItem().toString();
            String dateStart = namStart + "-" + thangStart + "-" + ngayStart;
            //ngày kết thúc
            String ngayEnd = cbbEndD.getSelectedItem().toString();
            String thangEnd = cbbEndMM.getSelectedItem().toString();
            String namEnd = cbbEndYYYY.getSelectedItem().toString();
            String dateEnd = namEnd + "-" + thangEnd + "-" + ngayEnd;
            //Kiểm tra ngày hợp lệ
            if (hxl.kTraNgayHopLe(Integer.parseInt(ngayStart), Integer.parseInt(thangStart), Integer.parseInt(namStart)) == false) {
                JOptionPane.showMessageDialog(rootPane, "Ngày bắt đầu: '" + dateStart + "' không hợp lệ", "Thất bại", 0);
            } else if (hxl.kTraNgayHopLe(Integer.parseInt(ngayEnd), Integer.parseInt(thangEnd), Integer.parseInt(namEnd)) == false) {
                JOptionPane.showMessageDialog(rootPane, "Ngày kết thúc: '" + dateEnd + "' không hợp lệ", "Thất bại", 0);
            } else {
                // kiểm tra ngày kết thúc phải lớn hơn ngày bắt đầu
                if (hxl.ktraNgay(dateStart, dateEnd) == false) {
                    JOptionPane.showMessageDialog(rootPane, "Ngày kết thúc không được nhỏ hơn ngày: '" + dateStart + "'.", "Thất bại", 0);
                } else {//hợp lệ
                    lstTL = tkDAO.listTheLoaiStartEnd(dateStart, dateEnd);
                }
            }
        }
        for (TheLoai tl : lstTL) {
            Vector vctRow = new Vector();
            vctRow.add(tl.getMaTL());//Cột 1
            vctRow.add(tl.getTenTL());//Cột 2
            vctRow.add(tl.getSoLuotMuon());//Cột 3
            vctData.add(vctRow);//dòng 1 
        }
        tbChung.setModel(new DefaultTableModel(vctData, vctHead));
    }

    void showDGHetHanThe() {
        dfm = (DefaultTableModel) tbChung.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHead.clear();
        vctData.clear();
        vctHead.add("Mã đọc giả");
        vctHead.add("Tên đọc giả");
        vctHead.add("Mã thẻ");
        vctHead.add("Ngày đăng ký");
        vctHead.add("Ngày kêt thúc");

        //add từng dòng của bảng
        ArrayList<DocGia> lstDocGia = dgDAO.listDocGia();

        for (DocGia dg : lstDocGia) {//add đọc giả
            if (dg.getNgayHetHan().compareTo(dateNow) < 0) {
                Vector vctRow = new Vector();
                vctRow.add(dg.getMaDG());//Cột 1
                vctRow.add(dg.getTenDG());//Cột 2
                vctRow.add(dg.getSoThe());//Cột 3
                vctRow.add(dg.getNgayBatDau());//Cột 4
                vctRow.add(dg.getNgayHetHan());//Cột 5
                vctData.add(vctRow);//dòng 1 

            }
        }
        tbChung.setModel(new DefaultTableModel(vctData, vctHead));
    }

    void showSachDaHet() {
        dfm = (DefaultTableModel) tbChung.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHead.clear();
        vctData.clear();
        vctHead.add("Mã sách");
        vctHead.add("Tên sách");
        vctHead.add("Số lượng");
        //add từng dòng của bảng
        ArrayList<Sach> lstSach = sDAO.listSach();
        for (Sach s : lstSach) {//add đọc giả
            if (s.getSoLuong() == 0) {
                Vector vctRow = new Vector();
                vctRow.add(s.getMaS());//Cột 1
                vctRow.add(s.getTenS());//Cột 2
                vctRow.add(s.getSoLuong());//Cột 3
                vctData.add(vctRow);//dòng 1
            }
        }
        tbChung.setModel(new DefaultTableModel(vctData, vctHead));
    }

    void showDGViPham() {
        dfm = (DefaultTableModel) tbChung.getModel();
        dfm.setRowCount(0);
        //Tên cột
        vctHead.clear();
        vctData.clear();
        vctHead.add("Mã đọc giả");
        vctHead.add("Tên đọc giả");
        vctHead.add("Số điện thoại");
        vctHead.add("Tên sách");
        vctHead.add("Ngày trả");
      
        //add từng dòng của bảng
        ArrayList<DocGia> lstDG = dgDAO.listDGViPham();
        for (DocGia dg : lstDG) {

            Vector vctRow = new Vector();//Chứa dữ liệu cột
            vctRow.add(dg.getMaDG().trim());//Cột 1
            vctRow.add(dg.getTenDG().trim());//Cột 2
            vctRow.add(dg.getSdt().trim());//Cột 3
            vctRow.add(dg.getMaSach().trim());//Cột 4
            vctRow.add(dg.getNgayHetHan().trim());//Cột 5
            vctData.add(vctRow);//thêm Dòng vctRow gồm 5 cột  
        }
        tbChung.setModel(new DefaultTableModel(vctData, vctHead));
    }

    void hideTime() {
        rbtnAllTime.setEnabled(false);
        rbtnThang.setEnabled(false);
        rbtnTuan.setEnabled(false);
        rbtnTimeDK.setEnabled(false);
        cbbStartD.setEnabled(false);
        cbbStartMM.setEnabled(false);
        cbbStartYYYY.setEnabled(false);
        cbbEndD.setEnabled(false);
        cbbEndMM.setEnabled(false);
        cbbEndYYYY.setEnabled(false);
    }

    void showTime() {
        rbtnAllTime.setEnabled(true);
        rbtnThang.setEnabled(true);
        rbtnTuan.setEnabled(true);
        rbtnTimeDK.setEnabled(true);
        cbbStartD.setEnabled(true);
        cbbStartMM.setEnabled(true);
        cbbStartYYYY.setEnabled(true);
        cbbEndD.setEnabled(true);
        cbbEndMM.setEnabled(true);
        cbbEndYYYY.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        rbtnTheoSach = new javax.swing.JRadioButton();
        rbtnTheoNXB = new javax.swing.JRadioButton();
        rbtnTheoTheLoai = new javax.swing.JRadioButton();
        rbtnSachDaMuonHet = new javax.swing.JRadioButton();
        rbtnTheoDocGia = new javax.swing.JRadioButton();
        rbtnTheoTacGia = new javax.swing.JRadioButton();
        rbtnDGHetHanThe = new javax.swing.JRadioButton();
        rbtnViPham = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        rbtnAllTime = new javax.swing.JRadioButton();
        rbtnTuan = new javax.swing.JRadioButton();
        rbtnThang = new javax.swing.JRadioButton();
        rbtnTimeDK = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        cbbStartD = new javax.swing.JComboBox<>();
        cbbStartMM = new javax.swing.JComboBox<>();
        cbbStartYYYY = new javax.swing.JComboBox<>();
        cbbEndYYYY = new javax.swing.JComboBox<>();
        cbbEndMM = new javax.swing.JComboBox<>();
        cbbEndD = new javax.swing.JComboBox<>();
        btnThongKe = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbChung = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Yêu cầu thống kê", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        jPanel2.setLayout(new java.awt.GridLayout(2, 4, 20, 0));

        rbtnTheoSach.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbtnTheoSach.setText("Số lượt mượn theo sách");
        rbtnTheoSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbtnTheoSachMouseClicked(evt);
            }
        });
        jPanel2.add(rbtnTheoSach);

        rbtnTheoNXB.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbtnTheoNXB.setText("Số lượt mượn theo nhà xuất bản");
        rbtnTheoNXB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbtnTheoNXBMouseClicked(evt);
            }
        });
        jPanel2.add(rbtnTheoNXB);

        rbtnTheoTheLoai.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbtnTheoTheLoai.setText("Số lượt mượn theo thể loại");
        rbtnTheoTheLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbtnTheoTheLoaiMouseClicked(evt);
            }
        });
        jPanel2.add(rbtnTheoTheLoai);

        rbtnSachDaMuonHet.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbtnSachDaMuonHet.setText("Sách đã mượn hết");
        rbtnSachDaMuonHet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbtnSachDaMuonHetMouseClicked(evt);
            }
        });
        jPanel2.add(rbtnSachDaMuonHet);

        rbtnTheoDocGia.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbtnTheoDocGia.setText("Số lượt mượn theo đọc giả");
        rbtnTheoDocGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbtnTheoDocGiaMouseClicked(evt);
            }
        });
        jPanel2.add(rbtnTheoDocGia);

        rbtnTheoTacGia.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbtnTheoTacGia.setText("Số lượt mượn theo theo tác giả");
        rbtnTheoTacGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbtnTheoTacGiaMouseClicked(evt);
            }
        });
        jPanel2.add(rbtnTheoTacGia);

        rbtnDGHetHanThe.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbtnDGHetHanThe.setText("Danh sách đọc giả hết hạn thẻ");
        rbtnDGHetHanThe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbtnDGHetHanTheMouseClicked(evt);
            }
        });
        jPanel2.add(rbtnDGHetHanThe);

        rbtnViPham.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbtnViPham.setText("Danh sách vi phạm");
        rbtnViPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbtnViPhamMouseClicked(evt);
            }
        });
        jPanel2.add(rbtnViPham);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Khoảng thời gian", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        rbtnAllTime.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbtnAllTime.setText("Toàn thời gian");

        rbtnTuan.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbtnTuan.setText("1 Tuần");

        rbtnThang.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbtnThang.setText("1 Tháng");

        rbtnTimeDK.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbtnTimeDK.setText("Từ ngày");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("đến ngày");

        cbbStartD.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        cbbStartD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbStartMM.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        cbbStartMM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbStartYYYY.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        cbbStartYYYY.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbEndYYYY.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        cbbEndYYYY.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbEndMM.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        cbbEndMM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbEndMM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbEndMMActionPerformed(evt);
            }
        });

        cbbEndD.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        cbbEndD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbtnAllTime)
                .addGap(18, 18, 18)
                .addComponent(rbtnThang)
                .addGap(27, 27, 27)
                .addComponent(rbtnTuan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbtnTimeDK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbStartD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbStartMM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbStartYYYY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(cbbEndD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbEndMM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbEndYYYY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbEndD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbEndMM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbEndYYYY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbtnAllTime)
                        .addComponent(rbtnTuan)
                        .addComponent(rbtnThang)
                        .addComponent(rbtnTimeDK)
                        .addComponent(cbbStartD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbStartMM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbStartYYYY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        btnThongKe.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/complete.png"))); // NOI18N
        btnThongKe.setText("Thống kê");
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 32, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(559, 559, 559)
                .addComponent(btnThongKe)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThongKe)
                .addGap(24, 24, 24))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Kết quả", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tbChung.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tbChung.setModel(new javax.swing.table.DefaultTableModel(
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
        tbChung.setRowHeight(30);
        tbChung.setRowMargin(7);
        jScrollPane1.setViewportView(tbChung);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbbEndMMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbEndMMActionPerformed

    }//GEN-LAST:event_cbbEndMMActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        String dieuKien = "all";

        if (rbtnTheoSach.isSelected())// thống kê lượt mượn theo sách
        {
            if (rbtnAllTime.isSelected()) {//all time
                dieuKien = "all";
            } else if (rbtnThang.isSelected()) {//tháng
                dieuKien = "thang";
            } else if (rbtnTuan.isSelected()) {//tuần
                dieuKien = "tuan";
            } else if (rbtnTimeDK.isSelected()) {//tuần
                dieuKien = "dieuKien";
            }
            showMuonTheoSach(dieuKien);
        } else if (rbtnTheoDocGia.isSelected())// thống kê lượt mượn theo đọc giả
        {
            if (rbtnAllTime.isSelected()) {
                dieuKien = "all";
            } else if (rbtnThang.isSelected()) {//tháng
                dieuKien = "thang";
            } else if (rbtnTuan.isSelected()) {//tuần
                dieuKien = "tuan";
            } else if (rbtnTimeDK.isSelected()) {//tuần
                dieuKien = "dieuKien";
            }
            showMuonTheoDocGia(dieuKien);
        } else if (rbtnTheoNXB.isSelected())// thống kê lượt mượn theo NXB
        {
            if (rbtnAllTime.isSelected()) {
                dieuKien = "all";
            } else if (rbtnThang.isSelected()) {//tháng
                dieuKien = "thang";
            } else if (rbtnTuan.isSelected()) {//tuần
                dieuKien = "tuan";
            } else if (rbtnTimeDK.isSelected()) {//tuần
                dieuKien = "dieuKien";
            }
            showMuonNXB(dieuKien);
        } else if (rbtnTheoTacGia.isSelected())// thống kê lượt mượn theo tác giả
        {
            if (rbtnAllTime.isSelected()) {
                dieuKien = "all";
            } else if (rbtnThang.isSelected()) {//tháng
                dieuKien = "thang";
            } else if (rbtnTuan.isSelected()) {//tuần
                dieuKien = "tuan";
            } else if (rbtnTimeDK.isSelected()) {//tuần
                dieuKien = "dieuKien";
            }
            showMuonTheoTacGia(dieuKien);
        } else if (rbtnTheoTheLoai.isSelected())// thống kê lượt mượn theo thể loại
        {
            if (rbtnAllTime.isSelected()) {
                dieuKien = "all";
            } else if (rbtnThang.isSelected()) {//tháng
                dieuKien = "thang";
            } else if (rbtnTuan.isSelected()) {//tuần
                dieuKien = "tuan";
            } else if (rbtnTimeDK.isSelected()) {//tuần
                dieuKien = "dieuKien";
            }
            showMuonTheoTheLoai(dieuKien);
        } else if (rbtnDGHetHanThe.isSelected())// thống kê lượt mượn theo thể loại
        {
            showDGHetHanThe();
        } else if (rbtnSachDaMuonHet.isSelected())// thống kê lượt mượn theo thể loại
        {
            showSachDaHet();
        }
        else if (rbtnViPham.isSelected())// thống kê lượt mượn theo thể loại
        {
            showDGViPham();
        }
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void rbtnDGHetHanTheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbtnDGHetHanTheMouseClicked
        hideTime();
    }//GEN-LAST:event_rbtnDGHetHanTheMouseClicked

    private void rbtnTheoSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbtnTheoSachMouseClicked
        showTime();
    }//GEN-LAST:event_rbtnTheoSachMouseClicked

    private void rbtnTheoDocGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbtnTheoDocGiaMouseClicked
        showTime();
    }//GEN-LAST:event_rbtnTheoDocGiaMouseClicked

    private void rbtnTheoNXBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbtnTheoNXBMouseClicked
        showTime();
    }//GEN-LAST:event_rbtnTheoNXBMouseClicked

    private void rbtnTheoTacGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbtnTheoTacGiaMouseClicked
        showTime();
    }//GEN-LAST:event_rbtnTheoTacGiaMouseClicked

    private void rbtnTheoTheLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbtnTheoTheLoaiMouseClicked
        showTime();
    }//GEN-LAST:event_rbtnTheoTheLoaiMouseClicked

    private void rbtnSachDaMuonHetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbtnSachDaMuonHetMouseClicked
        hideTime();
    }//GEN-LAST:event_rbtnSachDaMuonHetMouseClicked

    private void rbtnViPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbtnViPhamMouseClicked
        hideTime();
    }//GEN-LAST:event_rbtnViPhamMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThongKe;
    private javax.swing.JComboBox<String> cbbEndD;
    private javax.swing.JComboBox<String> cbbEndMM;
    private javax.swing.JComboBox<String> cbbEndYYYY;
    private javax.swing.JComboBox<String> cbbStartD;
    private javax.swing.JComboBox<String> cbbStartMM;
    private javax.swing.JComboBox<String> cbbStartYYYY;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbtnAllTime;
    private javax.swing.JRadioButton rbtnDGHetHanThe;
    private javax.swing.JRadioButton rbtnSachDaMuonHet;
    private javax.swing.JRadioButton rbtnThang;
    private javax.swing.JRadioButton rbtnTheoDocGia;
    private javax.swing.JRadioButton rbtnTheoNXB;
    private javax.swing.JRadioButton rbtnTheoSach;
    private javax.swing.JRadioButton rbtnTheoTacGia;
    private javax.swing.JRadioButton rbtnTheoTheLoai;
    private javax.swing.JRadioButton rbtnTimeDK;
    private javax.swing.JRadioButton rbtnTuan;
    private javax.swing.JRadioButton rbtnViPham;
    private javax.swing.JTable tbChung;
    // End of variables declaration//GEN-END:variables
}
