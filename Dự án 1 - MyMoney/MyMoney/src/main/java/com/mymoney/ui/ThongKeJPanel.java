/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mymoney.ui;

import com.mymoney.utils.Auth;
import com.mymoney.utils.MsgBox;
import javax.swing.JPanel;

/**
 *
 * @author lam
 */
public class ThongKeJPanel extends javax.swing.JPanel {
     private JPanel chilJPanel; 
    /**
     * Creates new form ThongKeJPanel
     */
    public ThongKeJPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        btnKhoanThu = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnKhoanChi = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        pnMainTKe = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        jToolBar1.setBackground(new java.awt.Color(110, 89, 222));
        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setForeground(new java.awt.Color(110, 89, 222));
        jToolBar1.setRollover(true);
        jToolBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnKhoanThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mymoney/icon/Download.png"))); // NOI18N
        btnKhoanThu.setText("Thống kê thu nhập");
        btnKhoanThu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKhoanThu.setFocusable(false);
        btnKhoanThu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKhoanThu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnKhoanThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoanThuActionPerformed(evt);
            }
        });
        jToolBar1.add(btnKhoanThu);
        jToolBar1.add(jSeparator2);

        btnKhoanChi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mymoney/icon/Remove from basket.png"))); // NOI18N
        btnKhoanChi.setText("Thống kê chi tiêu");
        btnKhoanChi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKhoanChi.setFocusable(false);
        btnKhoanChi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKhoanChi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnKhoanChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoanChiActionPerformed(evt);
            }
        });
        jToolBar1.add(btnKhoanChi);
        jToolBar1.add(jSeparator1);

        pnMainTKe.setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mymoney/icon/stock1.png"))); // NOI18N
        pnMainTKe.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnMainTKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnMainTKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnKhoanThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoanThuActionPerformed
        // TODO add your handling code here:
         if (Auth.isLogin()) {
            chilJPanel = new ThuNhapJPanel();
            pnMainTKe.removeAll();
            pnMainTKe.add(chilJPanel);

          

            pnMainTKe.validate();
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        }
    }//GEN-LAST:event_btnKhoanThuActionPerformed

    private void btnKhoanChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoanChiActionPerformed
        // TODO add your handling code here:
         if (Auth.isLogin()) {
            chilJPanel = new ChiTieuJPanel();
            pnMainTKe.removeAll();
            pnMainTKe.add(chilJPanel);

          

            pnMainTKe.validate();
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        }
    }//GEN-LAST:event_btnKhoanChiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKhoanChi;
    private javax.swing.JButton btnKhoanThu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel pnMainTKe;
    // End of variables declaration//GEN-END:variables
}
