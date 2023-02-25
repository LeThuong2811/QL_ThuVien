/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.TheLoaiDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import pojo.ImageThongBao;
import pojo.TheLoai;

/**
 *
 * @author ADMIN
 */
public class frm_ThemTheLoai extends javax.swing.JFrame {

    /**
     * Creates new form frm_ThemTheLoai
     */
    TheLoaiDAO tlDAO = new TheLoaiDAO();
    ImageThongBao imgTB = new ImageThongBao();
    public frm_ThemTheLoai() {
        initComponents();
        this.setTitle("thêm thể loại");
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
        btnLuu = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        txtTenTheLoai = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        txtTenTheLoai.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel2.setText("Tên thể loại");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnLuu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThoat))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(84, 84, 84))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThoat)
                    .addComponent(btnLuu))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Source Code Pro Semibold", 2, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("THÊM THỂ LOẠI");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btnLuuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuMouseClicked
        if (txtTenTheLoai.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập tên thể loại", "Thông báo", 0,imgTB.getImgWarning());
            txtTenTheLoai.requestFocus();
        } else {
            String tenTL = txtTenTheLoai.getText().trim();
            ArrayList<TheLoai> lstTL = tlDAO.listTheLoai();
            int Ktra = 1;
            for (TheLoai tl : lstTL) {
                if (tl.getTenTL().equals(tenTL)) {
                    txtTenTheLoai.requestFocus();
                    Ktra = 0;
                    break;
                }
            }
            if (Ktra == 0) {
                JOptionPane.showMessageDialog(rootPane, "Tên thể loại đã tồn tại", "Thất bại!", 0,imgTB.getImgWarning());
            } else {
                TheLoai tl = new TheLoai(tenTL);
                boolean kt = tlDAO.themTheLoai(tl);
                if (kt == false) {
                    JOptionPane.showMessageDialog(rootPane, "Thêm Thất bại", "Thông báo", 1,imgTB.getImgError());

                } else {
                    JOptionPane.showMessageDialog(rootPane, "Đã thêm thể loại:\n" + tenTL, "Thành công", 1,imgTB.getImgCompleteGreen());
                    txtTenTheLoai.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_btnLuuMouseClicked

    private void btnThoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatMouseClicked
        this.setVisible(false);
    }//GEN-LAST:event_btnThoatMouseClicked

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
            java.util.logging.Logger.getLogger(frm_ThemTheLoai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_ThemTheLoai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_ThemTheLoai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_ThemTheLoai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_ThemTheLoai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThoat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField txtTenTheLoai;
    // End of variables declaration//GEN-END:variables
}
