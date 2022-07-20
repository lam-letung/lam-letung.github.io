/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mymoney.ui;

import com.mymoney.dao.LoaiChiDAO;

import com.mymoney.entity.LoaiChi;

import com.mymoney.helper.DataValidator;
import com.mymoney.utils.Auth;
import com.mymoney.utils.MsgBox;
import com.mymoney.utils.XImage;
import java.awt.Color;
import java.io.File;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lam
 */
public class LoaiChiJPanel extends javax.swing.JPanel {

    JFileChooser fileChooser = new JFileChooser();
    LoaiChiDAO dao = new LoaiChiDAO();
    int row = 0;

    /**
     * Creates new form KhoanChiJPanel
     */
    public LoaiChiJPanel() {
        initComponents();
        init();
    }

    void init() {
        fillTable();
        updateStatus();
        
    }

    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblLoaiChi.getModel();
        model.setRowCount(0);
        try {
           int i = 1;
            List<LoaiChi> list = dao.selectByTaiKhoan(Auth.user.getMaTK());
            for (LoaiChi lc : list ) {                   
                    Object[] row = {
                        i++,
                        lc.getMaLC(),
                        lc.getTenLC(),
                        lc.getHinhLC()
                    };
                    model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liêu");
            throw new RuntimeException(e);
        }
    }

    void chonAnh() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            XImage.save(file);
            ImageIcon icon = XImage.read(file.getName());
            lblHinhAnh.setIcon(icon);
            lblHinhAnh.setToolTipText(file.getName());
            lblHinhAnh.setText("");
        }
    }

    void setForm(LoaiChi model) {
        txtMaLC.setText(model.getMaLC());
        txtTenLC.setText(model.getTenLC());
        if (!model.getHinhLC().equals("")) {
            lblHinhAnh.setIcon(XImage.read(model.getHinhLC()));
            lblHinhAnh.setToolTipText(model.getHinhLC());
            lblHinhAnh.setText("");
        }
    }

    LoaiChi getForm() {
        LoaiChi model = new LoaiChi();
        model.setMaLC(txtMaLC.getText());
        model.setTenLC(txtTenLC.getText());
        model.setHinhLC(lblHinhAnh.getToolTipText());
        model.setMaTK(Auth.user.getMaTK());
       
        return model;
    }

    void edit() {
        try {
            String maLC = (String) tblLoaiChi.getValueAt(this.row, 1);
            LoaiChi model = dao.selectById(maLC);
            if (model != null) {
                setForm(model);
                updateStatus();
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    void updateStatus() {
        boolean edit = this.row >= 0;
        boolean first = this.row == 0;
        boolean last = this.row == tblLoaiChi.getRowCount() - 1;
        txtMaLC.setEditable(!edit);
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
        txtMaLC.setBackground(Color.white);
        txtTenLC.setBackground(Color.white);
        lblHinhAnh.setBackground(Color.white);
        txtMaLC.setText("");
        txtTenLC.setText("");
        lblHinhAnh.setIcon(null);
        lblHinhAnh.setText("Chọn hình");
        lblHinhAnh.setToolTipText(null);
        updateStatus();
        row = -1;
        

    }

    void insert() {
        StringBuilder sb = new StringBuilder();
        DataValidator.validateEmpty(txtMaLC, sb, "Vui lòng nhập mã loại chi!");
        DataValidator.validateEmpty(txtTenLC, sb, "Vui lòng nhập tên loại chi!");
        DataValidator.validateEmpty(lblHinhAnh, sb, "Vui lòng chọn hình ảnh cho loại chi này!");
        if (sb.length() > 0) {
            MsgBox.alert(this, sb.toString());
            return;
        }else if(dao.selectById(txtMaLC.getText()) != null){
                    MsgBox.alert(this, "Mã loại chi này đã tồn tại!");
                    txtMaLC.setBackground(Color.yellow);
                    return;
            } 
        else {
            
            txtMaLC.setBackground(Color.white);
            LoaiChi model = getForm();
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
        DataValidator.validateEmpty(txtMaLC, sb, "Vui lòng nhập mã loại chi!");
        DataValidator.validateEmpty(txtTenLC, sb, "Vui lòng nhập tên loại chi!");
        DataValidator.validateEmpty(lblHinhAnh, sb, "Vui lòng chọn hình ảnh chuyên đề!");
        if (sb.length() > 0) {
            MsgBox.alert(this, sb.toString());
            return;
        } else {
            LoaiChi model = getForm();

            try {
                dao.update(model);
                this.fillTable();
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
            if (MsgBox.confirm(this, "Bạn thực sự muốn xoá loại chi này?")) {
                String maLC = txtMaLC.getText();
                try {
                    dao.delete(maLC);
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
        if (row < tblLoaiChi.getRowCount() - 1) {
            row++;
            edit();
        }
    }

    void last() {
        row = tblLoaiChi.getRowCount() - 1;
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
        lblHinhAnh = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaLC = new javax.swing.JTextField();
        txtTenLC = new javax.swing.JTextField();
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLoaiChi = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LOẠI CHI");

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHinhAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinhAnh.setText("Hình ảnh");
        lblHinhAnh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblHinhAnh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblHinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblHinhAnhMousePressed(evt);
            }
        });
        jPanel1.add(lblHinhAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 149, 190));

        jLabel3.setText("Mã  loại chi:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, -1, 20));
        jPanel1.add(txtMaLC, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, 234, -1));
        jPanel1.add(txtTenLC, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, 234, -1));

        jLabel4.setText("Tên loại chi:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, -1, 20));

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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(btnFirst)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrev)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLast)))
                .addContainerGap())
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

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 380, 100));

        tblLoaiChi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Loại chi", "Tên loại chi", "Hình"
            }
        ));
        tblLoaiChi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblLoaiChi.setSelectionBackground(new java.awt.Color(153, 153, 255));
        tblLoaiChi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblLoaiChiMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblLoaiChi);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
        updateStatus();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        first();
        txtMaLC.setBackground(Color.white);
        txtTenLC.setBackground(Color.white);
        lblHinhAnh.setBackground(Color.white);
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        prev();
        txtMaLC.setBackground(Color.white);
        txtTenLC.setBackground(Color.white);
        lblHinhAnh.setBackground(Color.white);
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        next();
        txtMaLC.setBackground(Color.white);
        txtTenLC.setBackground(Color.white);
        lblHinhAnh.setBackground(Color.white);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        last();
        txtMaLC.setBackground(Color.white);
        txtTenLC.setBackground(Color.white);
        lblHinhAnh.setBackground(Color.white);

    }//GEN-LAST:event_btnLastActionPerformed

    private void lblHinhAnhMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnhMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            chonAnh();
        }
    }//GEN-LAST:event_lblHinhAnhMousePressed

    private void tblLoaiChiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLoaiChiMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.row = tblLoaiChi.rowAtPoint(evt.getPoint());
            txtMaLC.setBackground(Color.white);
            txtTenLC.setBackground(Color.white);
            lblHinhAnh.setBackground(Color.white);
            edit();
        }
    }//GEN-LAST:event_tblLoaiChiMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JTable tblLoaiChi;
    private javax.swing.JTextField txtMaLC;
    private javax.swing.JTextField txtTenLC;
    // End of variables declaration//GEN-END:variables
}
