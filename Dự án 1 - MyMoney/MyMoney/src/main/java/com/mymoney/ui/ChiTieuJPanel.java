/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mymoney.ui;

import com.mymoney.dao.KhoanChiDAO;

import com.mymoney.dao.ThongKeDAO;

import com.mymoney.utils.Auth;
import java.text.NumberFormat;

import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lam
 */
public class ChiTieuJPanel extends javax.swing.JPanel {

    ThongKeDAO tkDao = new ThongKeDAO();
    KhoanChiDAO kcDao = new KhoanChiDAO();

    /**
     * Creates new form ThuNhapJPanel
     */
    public ChiTieuJPanel() {
        initComponents();
        init();
    }

    void init() {
        fillComboBoxNam();
        fillComboBoxThang();
        fillTable();
    }

    void fillComboBoxNam() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNam.getModel();
        model.removeAllElements();
        List<Integer> list = kcDao.selectYear();
        for (Integer year : list) {
            model.addElement(year);
        }
        cboNam.setSelectedIndex(0);
    }

    void fillComboBoxThang() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboThang.getModel();
        model.removeAllElements();
//        KhoanThu kt = (KhoanThu) cboThang.getSelectedItem();
        Integer cbNam = (Integer) cboNam.getSelectedItem();
        List<Integer> list = kcDao.selectMonth(cbNam);
        for (Integer month : list) {
            model.addElement(month);
        }
//        cboThang.setSelectedIndex(0);
    }

    void setText() {
        lblTextSum.setText("Tổng chi tiêu tháng " + cboThang.getSelectedItem() + " / " + cboNam.getSelectedItem());
    }

    void setNum() {
        Integer thang = (Integer) cboThang.getSelectedItem();
        Integer nam = (Integer) cboNam.getSelectedItem();
         Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1;
        String maTK = Auth.user.getMaTK();
        Integer sum = kcDao.selectSumBy3id(maTK, thang, nam);
        lblNumSum.setText(str1 = currencyVN.format(sum));

    }

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblChiTieu.getModel();
        model.setRowCount(0);
         Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1;
        Integer thang = (Integer) cboThang.getSelectedItem();
        Integer nam = (Integer) cboNam.getSelectedItem();
        String maTK = Auth.user.getMaTK().toString();
        List<Object[]> list = tkDao.getChiThamgNam(maTK, thang, nam);
        for (Object[] row : list) {

            model.addRow(new Object[]{row[0], str1 = currencyVN.format(row[1]), str1 = currencyVN.format(row[2]), 
                str1 = currencyVN.format(row[3]), str1 = currencyVN.format(row[4]), cboThang.getSelectedItem()+" / "+cboNam.getSelectedItem()});
//            , row[5] + " / " + row[6]+ " / " + row[7]
        }
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
        jLabel2 = new javax.swing.JLabel();
        cboThang = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cboNam = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        lblTextSum = new javax.swing.JLabel();
        lblNumSum = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChiTieu = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Tháng:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, 20));

        cboThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboThang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThangActionPerformed(evt);
            }
        });
        jPanel1.add(cboThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 200, -1));

        jLabel3.setText("Năm:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, -1, 20));

        cboNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboNam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamActionPerformed(evt);
            }
        });
        jPanel1.add(cboNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 200, -1));

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblTextSum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTextSum.setText("Tổng chi tiêu tháng");

        lblNumSum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumSum.setText("jLabel5");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTextSum, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
            .addComponent(lblNumSum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTextSum)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNumSum)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 180, 60));

        jScrollPane1.setBorder(null);
        jScrollPane1.setAutoscrolls(true);

        tblChiTieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên loại chi", "Tổng tiền loại chi", "Khoản chi thấp nhất", "Khoản chi cao nhất", "Khoản chi trung bình", "Thời gian"
            }
        ));
        tblChiTieu.setSelectionBackground(new java.awt.Color(153, 153, 255));
        jScrollPane1.setViewportView(tblChiTieu);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 717, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THỐNG KÊ CHI TIÊU");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamActionPerformed
        // TODO add your handling code here:
        fillComboBoxThang();


    }//GEN-LAST:event_cboNamActionPerformed

    private void cboThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThangActionPerformed
        // TODO add your handling code here:

        cboThang.getSelectedItem();
        fillTable();
        setText();
        setNum();
    }//GEN-LAST:event_cboThangActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboNam;
    private javax.swing.JComboBox<String> cboThang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNumSum;
    private javax.swing.JLabel lblTextSum;
    private javax.swing.JTable tblChiTieu;
    // End of variables declaration//GEN-END:variables
}
