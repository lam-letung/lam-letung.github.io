/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mymoney.ui;

import com.mymoney.dao.KhoanThuDAO;
import com.mymoney.dao.LoaiThuDAO;
import com.mymoney.entity.KhoanThu;
import com.mymoney.entity.LoaiThu;
import com.mymoney.helper.DataValidator;
import com.mymoney.utils.Auth;
import com.mymoney.utils.MsgBox;
import com.mymoney.utils.XDate;
import java.awt.Color;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lam
 */
public class KhoanThuJPanel extends javax.swing.JPanel {

    LoaiThuDAO ltDao = new LoaiThuDAO();
    KhoanThuDAO dao = new KhoanThuDAO();

    int row = 0;

    /**
     * Creates new form KhoanChiJPanel
     */
    public KhoanThuJPanel() {
        initComponents();
        init();
    }

    void init() {
        this.fillTable();
        this.fillComboBoxKT();
        this.updateStatus();
    }

    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblKhoanThu.getModel();
        model.setRowCount(0);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
       String str1;
      
        try {

            List<KhoanThu> list = dao.selectByNgayThu(Auth.user.getMaTK());
            for (KhoanThu kt : list) {
                Object[] row = {
                    kt.getMaKT(),
                    kt.getTenKT(),
                     str1 = currencyVN.format( kt.getSoTienKT()),
                    kt.getMaLT(),
                    XDate.toString(kt.getNgayThu(), "dd-MM-yyyy")

                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liêu");
            throw new RuntimeException(e);
        }
    }

    public void fillComboBoxKT() {

        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoaiThu.getModel();
        model.removeAllElements();
        try {
            List<LoaiThu> list = ltDao.selectByTaiKhoan(Auth.user.getMaTK());
            for (LoaiThu lthu : list) {
                model.addElement(lthu);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        cboLoaiThu.setSelectedIndex(0);
    }

    void setForm(KhoanThu model) {
     
        cboLoaiThu.setToolTipText(String.valueOf(model.getMaKT()));
        cboLoaiThu.setSelectedItem(ltDao.selectById((String) tblKhoanThu.getValueAt(row, 3)));
        txtTenKT.setText(model.getTenKT());
        txtSoTien.setText(String.valueOf(model.getSoTienKT()));
        txtNgayThu.setText(XDate.toString(model.getNgayThu(), "dd-MM-yyyy"));
        txaGhiChu.setText(model.getGhiChu());

    }

//    void setForm2(LoaiThu model1) {
//        if (tblKhoanThu.getValueAt(row, 3).equals(model1.getMaLT())) {
//            LoaiThu item = ltDao.selectById(model1.getMaLT());
//            cboLoaiThu.setSelectedItem(item);
//        }
//    }
    KhoanThu getForm() {
        KhoanThu model = new KhoanThu();
        LoaiThu loaiThu = (LoaiThu) cboLoaiThu.getSelectedItem();
        model.setMaLT(loaiThu.getMaLT());
        model.setTenKT(txtTenKT.getText());
        model.setSoTienKT(Float.valueOf(txtSoTien.getText()));
        model.setNgayThu(XDate.toDate(txtNgayThu.getText(), "dd-MM-yyyy"));
        model.setGhiChu(txaGhiChu.getText());
        model.setMaTK(Auth.user.getMaTK());
        model.setMaKT((int) tblKhoanThu.getValueAt(row, 0));
        return model;
    }

    void edit() {
        try {
            Integer maKT = (Integer) tblKhoanThu.getValueAt(this.row, 0);
            KhoanThu model = dao.selectById(maKT);
            if (model != null) {
                this.setForm(model);
                this.updateStatus();

            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
            throw new RuntimeException(e);
        }
    }

    void updateStatus() {
        boolean edit = this.row >= 0;
        boolean first = this.row == 0;
        boolean last = this.row == tblKhoanThu.getRowCount() - 1;
//        cboLoaiThu.setEditable(!edit);

        // khi insert thì không update , delete
        btnThem.setEnabled(!edit);
        btnSua.setEnabled(edit);
        btnXoa.setEnabled(edit);

        btnFirst.setEnabled(edit && !first);
        btnPrev.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);
    }

    void clearForm() {
//        this.setForm(new ChuyenDe());
        txtTenKT.setBackground(Color.white);
        txtSoTien.setBackground(Color.white);
        txtNgayThu.setBackground(Color.white);
        txaGhiChu.setBackground(Color.white);
        txtTenKT.setText("");
        txtSoTien.setText("");
        txaGhiChu.setText("");
        txtNgayThu.setText("");
        this.row = -1;
        this.updateStatus();

    }

    void insert() {
        StringBuilder sb = new StringBuilder();
        DataValidator.validateEmpty(txtTenKT, sb, "Vui lòng nhập tên khoản thu!");
        DataValidator.validateEmpty(txtSoTien, sb, "Vui lòng nhập số tiền cho khoản thu này!");
        DataValidator.validateEmpty(txtNgayThu, sb, "Vui lòng chọn ngày cho khoản thu này!");

        if (sb.length() > 0) {
            MsgBox.alert(this, sb.toString());
            return;
        } else {

            KhoanThu model = getForm();
            try {
                dao.insert(model);

                this.fillTable();
                this.clearForm();
                MsgBox.alert(this, "Thêm mới thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Thêm mới thất bại!");
                throw new RuntimeException(e);

            }
        }
    }

    void update() {
        StringBuilder sb = new StringBuilder();
        DataValidator.validateEmpty(txtTenKT, sb, "Vui lòng nhập tên khoản thu!");
        DataValidator.validateEmpty(txtSoTien, sb, "Vui lòng nhập số tiền cho khoản thu này!");
        DataValidator.validateEmpty(txtNgayThu, sb, "Vui lòng chọn ngày cho khoản thu này!");
        if (sb.length() > 0) {
            MsgBox.alert(this, sb.toString());
            return;
        } else {
            KhoanThu model = getForm();

            try {
                dao.update(model);

                this.fillTable();
                this.clearForm();
                MsgBox.alert(this, "Cập nhật thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Cập nhật thất bại");
            }
        }
    }

    void delete() {
        if (!Auth.isLogin()) {
            MsgBox.alert(this, "Vui lòng đăng nhập!");

        } else {
            if (MsgBox.confirm(this, "Bạn thực sự muốn xoá khoản thu này?")) {
                Integer maKT = (Integer) tblKhoanThu.getValueAt(this.row, 0);
                try {
                    dao.delete(maKT);
                    this.fillTable();
                    this.clearForm();
                    MsgBox.alert(this, "Xoá thành công!");
                } catch (Exception e) {
                    MsgBox.alert(this, "Xoá thất bại!");
                }
            }
        }
    }

    void first() {
        row = 0;
        edit();
    }
//

    void prev() {
        if (row > 0) {
            row--;
            edit();
        }
    }

    void next() {
        if (row < tblKhoanThu.getRowCount() - 1) {
            row++;
            edit();
        }
    }

    void last() {
        row = tblKhoanThu.getRowCount() - 1;
        edit();
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
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTenKT = new javax.swing.JTextField();
        txtSoTien = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnMoi = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtNgayThu = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaGhiChu = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        cboLoaiThu = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhoanThu = new javax.swing.JTable();

        jLabel1.setBackground(new java.awt.Color(153, 153, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("KHOẢN THU");

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Tên khoản thu:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));
        jPanel1.add(txtTenKT, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 234, -1));
        jPanel1.add(txtSoTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 234, -1));

        jLabel4.setText("Số tiền:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, -1, 20));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mymoney/icon/Refresh.png"))); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mymoney/icon/Delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLast.setText(">>");
        btnLast.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mymoney/icon/Edit.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnNext.setText(">");
        btnNext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnPrev.setText("<");
        btnPrev.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnFirst.setText("<<");
        btnFirst.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mymoney/icon/Add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 13, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnFirst)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrev)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLast)
                        .addGap(100, 100, 100))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMoi)
                    .addComponent(btnXoa)
                    .addComponent(btnSua)
                    .addComponent(btnThem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLast)
                    .addComponent(btnNext)
                    .addComponent(btnPrev)
                    .addComponent(btnFirst))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 380, -1));

        jLabel2.setText("Ngày thu:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));
        jPanel1.add(txtNgayThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 230, -1));

        jLabel5.setText("Ghi chú:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, -1, -1));

        txaGhiChu.setColumns(20);
        txaGhiChu.setRows(5);
        jScrollPane2.setViewportView(txaGhiChu);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, 230, 60));

        jLabel6.setText("Loại thu:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        cboLoaiThu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLoaiThu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(cboLoaiThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 230, -1));

        jScrollPane1.setAutoscrolls(true);

        tblKhoanThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khoản thu", "Tên khoản thu", "Số tiền", "Loại thu", "Ngày thu"
            }
        ));
        tblKhoanThu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblKhoanThu.setSelectionBackground(new java.awt.Color(153, 153, 255));
        tblKhoanThu.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                tblKhoanThuComponentRemoved(evt);
            }
        });
        tblKhoanThu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblKhoanThuMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhoanThu);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        insert();

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clearForm();
        txtNgayThu.setText(XDate.toString(XDate.now(), "dd/MM/yyyy"));
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        first();
        txtTenKT.setBackground(Color.white);
        txtSoTien.setBackground(Color.white);
        txtNgayThu.setBackground(Color.white);
        txaGhiChu.setBackground(Color.white);
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        prev();
        txtTenKT.setBackground(Color.white);
        txtSoTien.setBackground(Color.white);
        txtNgayThu.setBackground(Color.white);
        txaGhiChu.setBackground(Color.white);
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        next();
        txtTenKT.setBackground(Color.white);
        txtSoTien.setBackground(Color.white);
        txtNgayThu.setBackground(Color.white);
        txaGhiChu.setBackground(Color.white);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        last();
        txtTenKT.setBackground(Color.white);
        txtSoTien.setBackground(Color.white);
        txtNgayThu.setBackground(Color.white);
        txaGhiChu.setBackground(Color.white);

    }//GEN-LAST:event_btnLastActionPerformed

    private void tblKhoanThuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhoanThuMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            try {
                this.row = tblKhoanThu.rowAtPoint(evt.getPoint());
                edit();
                txtTenKT.setBackground(Color.white);
                txtSoTien.setBackground(Color.white);
                txtNgayThu.setBackground(Color.white);
                txaGhiChu.setBackground(Color.white);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }//GEN-LAST:event_tblKhoanThuMousePressed

    private void tblKhoanThuComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tblKhoanThuComponentRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tblKhoanThuComponentRemoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboLoaiThu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblKhoanThu;
    private javax.swing.JTextArea txaGhiChu;
    private javax.swing.JTextField txtNgayThu;
    private javax.swing.JTextField txtSoTien;
    private javax.swing.JTextField txtTenKT;
    // End of variables declaration//GEN-END:variables
}
