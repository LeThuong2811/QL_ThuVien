/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.HamXuLyChung;
import dao.NhaXuatBanDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import pojo.ImageThongBao;
import pojo.NhaXuatBan;

/**
 *
 * @author ADMIN
 */
public class frm_ThemNhaXuatBan extends javax.swing.JFrame {

    /**
     * Creates new form frm_ThemNhaXuatBan
     */
    NhaXuatBanDAO nxbDAO = new NhaXuatBanDAO();
    HamXuLyChung hxl = new HamXuLyChung();
    ImageThongBao imgTB = new ImageThongBao();
    public frm_ThemNhaXuatBan() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtTenNXB = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtNguoiDD = new javax.swing.JTextField();
        btnLuu = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        lbTB = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setLayout(new java.awt.GridLayout(4, 1, 0, 10));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Tên nhà xuất bản:");
        jPanel1.add(jLabel2);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Email:");
        jPanel1.add(jLabel4);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Địa chỉ:");
        jPanel1.add(jLabel3);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Người đại diện:");
        jPanel1.add(jLabel5);

        jPanel2.setLayout(new java.awt.GridLayout(4, 1, 0, 10));

        txtTenNXB.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jPanel2.add(txtTenNXB);

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });
        jPanel2.add(txtEmail);

        txtDiaChi.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jPanel2.add(txtDiaChi);

        txtNguoiDD.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jPanel2.add(txtNguoiDD);

        btnLuu.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        btnLuu.setText("Save");
        btnLuu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuuMouseClicked(evt);
            }
        });

        btnThoat.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        btnThoat.setText("Exit");
        btnThoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThoatMouseClicked(evt);
            }
        });

        lbTB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbTB.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnLuu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThoat))
                    .addComponent(lbTB, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(252, 252, 252))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTB, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThoat)
                    .addComponent(btnLuu))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Source Code Pro Semibold", 2, 35)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("THÊM NHÀ XUẤT BẢN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(124, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 554, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatMouseClicked
        this.setVisible(false);
    }//GEN-LAST:event_btnThoatMouseClicked

    private void btnLuuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuMouseClicked
        if (txtTenNXB.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập tên nhà xuất bản", "Thông báo", 0,imgTB.getImgWarning());
            txtTenNXB.requestFocus();
        } else if (txtEmail.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập Email", "Thông báo", 0,imgTB.getImgWarning());
            txtEmail.requestFocus();
        }else if (hxl.checkEmail(txtEmail.getText())==false) {
            JOptionPane.showMessageDialog(rootPane, "Email không hợp lệ", "Thông báo", 0,imgTB.getImgWarning());
            txtEmail.requestFocus();
        }
        else  {
            String tenNXB = txtTenNXB.getText().trim();
            String email = txtEmail.getText().trim();
            String diaChi = txtDiaChi.getText().trim();
            String ndd = txtNguoiDD.getText().trim();
            ArrayList<NhaXuatBan> lstNXB = nxbDAO.listNXB();
            int Ktra = 1;
            for (NhaXuatBan nxb : lstNXB) {
                if (nxb.getEmail().equals(email)) {
                    Ktra = 0;
                    txtEmail.requestFocus();
                    break;
                }

            }
            if (Ktra == 0) {
                JOptionPane.showMessageDialog(rootPane, "Email đã tồn tại", "Thất bại!", 0,imgTB.getImgWarning());
            } else {
                NhaXuatBan nxb = new NhaXuatBan(tenNXB, diaChi, email, ndd);
                boolean kt = nxbDAO.themNXB(nxb);
                if (kt == false) {
                    JOptionPane.showMessageDialog(rootPane, "Thêm Thất bại", "Thông báo", 1,imgTB.getImgWarning());

                } else {
                    JOptionPane.showMessageDialog(rootPane, "Đã thêm nhà xuất bản:\n" + tenNXB, "Thành công", 1,imgTB.getImgCompleteGreen());
                    txtTenNXB.setText("");
                    txtEmail.setText("");
                    txtDiaChi.setText("");
                    txtNguoiDD.setText("");
                    txtTenNXB.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_btnLuuMouseClicked

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        String email = txtEmail.getText().trim();
        if(hxl.checkEmail(email)==false)
            lbTB.setText("Email không đúng định dạng");
        else
            lbTB.setText("");
    }//GEN-LAST:event_txtEmailKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_ThemNhaXuatBan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThoat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lbTB;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNguoiDD;
    private javax.swing.JTextField txtTenNXB;
    // End of variables declaration//GEN-END:variables
}