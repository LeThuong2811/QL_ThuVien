/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.HamXuLyChung;
import dao.TheThuVienDAO;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import pojo.ImageThongBao;
import pojo.TheThuVien;

/**
 *
 * @author ADMIN
 */
public class frm_ThemTheThuVien extends javax.swing.JFrame {

    /**
     * Creates new form frm_ThemTheThuVien
     */
    HamXuLyChung hxl = new HamXuLyChung();
    TheThuVienDAO ttvDAO = new TheThuVienDAO();
    Vector vctNgayHetHan = new Vector();
    Vector vctThangHetHan = new Vector();
    Vector vctNamHetHan = new Vector();
    ImageThongBao imgTB = new ImageThongBao();
    public static String dateNow = LocalDate.now().toString();

    public frm_ThemTheThuVien() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Thêm thẻ");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        txtMaThe.setEnabled(false);
        txtNgayBatDau.setEnabled(false);
        txtNgayBatDau.setText(dateNow);

        hxl.layNgayThangNam(vctNgayHetHan, vctThangHetHan, vctNamHetHan);
        cbbNgayHetHan.setModel(new DefaultComboBoxModel(vctNgayHetHan));
        cbbThangHetHan.setModel(new DefaultComboBoxModel(vctThangHetHan));
        vctNamHetHan.clear();
        for (int i = Year.now().getValue(); i <= Year.now().getValue() + 4; i++) {
            vctNamHetHan.add(i);
        }

        cbbNamHetHan.setModel(new DefaultComboBoxModel(vctNamHetHan));
        cbbNgayHetHan.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtMaThe = new javax.swing.JTextField();
        txtNgayBatDau = new javax.swing.JTextField();
        txtGhiChu = new javax.swing.JTextField();
        cbbNgayHetHan = new javax.swing.JComboBox<>();
        cbbThangHetHan = new javax.swing.JComboBox<>();
        cbbNamHetHan = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnTaoMa = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Source Code Pro Semibold", 2, 35)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("THÊM THẺ THƯ VIỆN");

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setLayout(new java.awt.GridLayout(3, 1, 0, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel2.setText("Mã thẻ:");
        jPanel1.add(jLabel2);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel3.setText("Ngày bắt đầu:");
        jPanel1.add(jLabel3);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel4.setText("Ghi chú:");
        jPanel1.add(jLabel4);

        jPanel2.setLayout(new java.awt.GridLayout(3, 1, 0, 20));

        txtMaThe.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jPanel2.add(txtMaThe);

        txtNgayBatDau.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jPanel2.add(txtNgayBatDau);

        txtGhiChu.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jPanel2.add(txtGhiChu);

        cbbNgayHetHan.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        cbbNgayHetHan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbThangHetHan.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        cbbThangHetHan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbNamHetHan.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        cbbNamHetHan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel5.setText("Ngày hết hạn:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(cbbNgayHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbThangHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbNamHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(249, 249, 249))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbNgayHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbNamHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbThangHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setLayout(new java.awt.GridLayout(3, 1, 0, 25));

        btnTaoMa.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        btnTaoMa.setText("Create code Auto");
        btnTaoMa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTaoMaMouseClicked(evt);
            }
        });
        jPanel3.add(btnTaoMa);

        btnLuu.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        btnLuu.setText("Save");
        btnLuu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuuMouseClicked(evt);
            }
        });
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        jPanel3.add(btnLuu);

        btnThoat.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        btnThoat.setText("Exit");
        btnThoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThoatMouseClicked(evt);
            }
        });
        jPanel3.add(btnThoat);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 515, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 199, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addGap(189, 189, 189))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaoMaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaoMaMouseClicked
        String maThe = taoMaTheTuDong();
        txtMaThe.setText(maThe);
    }//GEN-LAST:event_btnTaoMaMouseClicked

    private void btnThoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatMouseClicked
        this.setVisible(false);
    }//GEN-LAST:event_btnThoatMouseClicked

    private void btnLuuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuMouseClicked
        if (txtMaThe.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa tạo mã thẻ.", "Thông báo", 0,imgTB.getImgWarning());
        } else {
            String ngay = cbbNgayHetHan.getSelectedItem().toString();
            String thang = cbbThangHetHan.getSelectedItem().toString();
            String nam = cbbNamHetHan.getSelectedItem().toString();
            String ngayHetHan = nam + "-" + thang + "-" + ngay;
            boolean checkNgay = hxl.kTraNgayHopLe(Integer.parseInt(ngay), Integer.parseInt(thang), Integer.parseInt(nam));
            boolean soSanhNgay = hxl.ktraNgay(dateNow, ngayHetHan);
            if (checkNgay == false) {
                JOptionPane.showMessageDialog(rootPane, "Ngày " + ngay + "-" + thang + "-" + nam + " không hợp lệ.", "Thông báo", 0,imgTB.getImgWarning());
            } else if (soSanhNgay == false) {
                JOptionPane.showMessageDialog(rootPane, "Ngày hết hạn không hợp lệ\n (Phải lớn hơn ngày hiện tại)", "Thông báo", 0,imgTB.getImgWarning());
            } else {
                TheThuVien th = new TheThuVien(txtMaThe.getText(), txtNgayBatDau.getText(), ngayHetHan, txtGhiChu.getText());
                boolean kq = ttvDAO.themTheThuVien(th);
                if (kq == true) {
                    JOptionPane.showMessageDialog(rootPane, "Đã thêm thẻ có mã " + txtMaThe.getText() + " ngày hết hạn: " + ngayHetHan, "Thành công", 1,imgTB.getImgCompleteGreen());
                    txtMaThe.setText("");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Thêm thẻ mới thất bại","Error !",1,imgTB.getImgError());
                }

            }
        }


    }//GEN-LAST:event_btnLuuMouseClicked

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuActionPerformed
    public String taoMaTheTuDong()//Tạo mã thẻ tự động từ 'T0000000' -> 'T9999999'
    {
        String maThe = "";
        maThe = ttvDAO.layMaTheCuoiCung();
        ArrayList<TheThuVien> lstT = ttvDAO.listTheThuVien();
        if (lstT.size() <= 0) {
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
//Kiểm tra ngày hết hạn phải lớn hơn ngày bắt đầu(ngày hiện tại)

    public static boolean ktraNgay(String s1, String s2) {
        String date1[] = s1.split("-");
        int nam1 = Integer.parseInt(date1[0]);
        int thang1 = Integer.parseInt(date1[1]);
        int ngay1 = Integer.parseInt(date1[2]);

        String date2[] = s2.split("-");
        int nam2 = Integer.parseInt(date2[0]);
        int thang2 = Integer.parseInt(date2[1]);
        int ngay2 = Integer.parseInt(date2[2]);

        if (nam2 > nam1) {
            return true;
        } else if (nam2 < nam1) {
            return false;
        } else {
            if (thang2 > thang1) {
                return true;
            } else if (thang2 < thang1) {
                return false;
            } else {
                if (ngay2 > ngay1) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_ThemTheThuVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_ThemTheThuVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_ThemTheThuVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_ThemTheThuVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_ThemTheThuVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnTaoMa;
    private javax.swing.JButton btnThoat;
    private javax.swing.JComboBox<String> cbbNamHetHan;
    private javax.swing.JComboBox<String> cbbNgayHetHan;
    private javax.swing.JComboBox<String> cbbThangHetHan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtMaThe;
    private javax.swing.JTextField txtNgayBatDau;
    // End of variables declaration//GEN-END:variables
}
