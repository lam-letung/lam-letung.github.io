/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mymoney.ui;

import com.mymoney.dao.TietKiemDAO;
import com.mymoney.entity.TietKiem;
import com.mymoney.helper.DataValidator;
import com.mymoney.utils.Auth;
import com.mymoney.utils.MsgBox;
import com.mymoney.utils.XDate;
import java.awt.Color;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lam
 */
public class TietKiemJPanel extends javax.swing.JPanel {
     private MainForm parentForm;
    TietKiemDAO dao = new TietKiemDAO();
    int row = 0;

    /**
     * Creates new form TietKiemJPanel
     */
    public TietKiemJPanel() {
        initComponents();
        init();
        
    }

    void init() {
        fillTable();
         trangThai();
        updateStatus();
       
    }
    
    void trangThai(){
        for(int i =0 ; i<tblTietKiem.getRowCount();i++){
            String today = XDate.toString(XDate.now(), "dd/MM/yyyy");
            if(tblTietKiem.getValueAt(i,5).toString().equalsIgnoreCase(today)){
               String maTKiem = (String) tblTietKiem.getValueAt(i,0);
               TietKiem tkiem = dao.selectById(maTKiem);
               tkiem.setTrangThai(false);
               dao.update(tkiem);
               fillTable();
            }
        }
    }
    
   

    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblTietKiem.getModel();
        model.setRowCount(0);
        try {

            List<TietKiem> list = dao.selectByTaiKhoan(Auth.user.getMaTK());
            for (TietKiem tkiem : list) {
                Object[] row = {
                    tkiem.getMaTKiem(),
                    tkiem.getTenTKiem(),
                    tkiem.getThoiHan(),
                    tkiem.getNganHang(),
                    XDate.toString(tkiem.getNgayBatDau(), "dd/MM/yyyy"),
                    XDate.toString(tkiem.getNgayHetHan(), "dd/MM/yyyy"),
                    tkiem.isTrangThai() ? "Hoạt động" : "Hết hạn"
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liêu");
            throw new RuntimeException(e);
        }
    }

    void setForm(TietKiem model) {
        txtMaTKiem.setText(model.getMaTKiem());
        txtTenTKiem.setText(model.getTenTKiem());
        txtThoiHan.setText(String.valueOf(model.getThoiHan()));
        txtNganHang.setText(String.valueOf(model.getNganHang()));
        txtNgayBatDau.setText(XDate.toString(model.getNgayBatDau(), "dd/MM/yyyy"));
        txtNgayKetThuc.setText(XDate.toString(model.getNgayHetHan(), "dd/MM/yyyy"));
        rdoHoatDong.setSelected(model.isTrangThai());
        rdoHetHan.setSelected(!model.isTrangThai());

    }

    TietKiem getForm() {
        TietKiem model = new TietKiem();
        model.setMaTKiem(txtMaTKiem.getText());
        model.setTenTKiem(txtTenTKiem.getText());
        model.setThoiHan(Integer.valueOf(txtThoiHan.getText()));
        model.setNganHang(txtNganHang.getText());

        model.setNgayBatDau(XDate.toDate(txtNgayBatDau.getText(), "dd/MM/yyyy"));
        model.setNgayHetHan(XDate.toDate(txtNgayKetThuc.getText(), "dd/MM/yyyy"));
        model.setTrangThai(rdoHoatDong.isSelected());
        model.setMaTK(Auth.user.getMaTK());
        return model;
    }

    void edit() {
        try {
            String maTKiem = (String) tblTietKiem.getValueAt(row, 0);
            TietKiem model = dao.selectById(maTKiem);
            if (model != null) {
                setForm(model);
                updateStatus();
            }
        } catch (Exception e) {
        }
    }

    void updateStatus() {
        boolean edit = this.row >= 0;
        boolean first = this.row == 0;
        boolean last = this.row == tblTietKiem.getRowCount() - 1;
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
        txtThoiHan.setBackground(Color.white);
        txtMaTKiem.setBackground(Color.white);
        txtNganHang.setBackground(Color.white);
        txtNgayBatDau.setBackground(Color.white);
        txtNgayKetThuc.setBackground(Color.white);
        txtTenTKiem.setBackground(Color.white);
        txtThoiHan.setText("");
        txtMaTKiem.setText("");
        txtNganHang.setText("");
        txtNgayBatDau.setText("");
        txtNgayKetThuc.setText("");
        txtTenTKiem.setText("");
        row = -1;
        updateStatus();
    }

    void insert() {
        StringBuilder sb = new StringBuilder();
        DataValidator.validateEmpty(txtMaTKiem, sb, "Vui lòng nhập mã tiết kiệm!");
        DataValidator.validateEmpty(txtTenTKiem, sb, "Vui lòng nhập tên tiết kiệm!");
        DataValidator.validateEmpty(txtThoiHan, sb, "Vui lòng nhập thời hạn cho tiết kiệm!");
        DataValidator.validateEmpty(txtNganHang, sb, "Vui lòng nhập ngân hàng cho tiết kiệm!");
        DataValidator.validateEmpty(txtNgayBatDau, sb, "Vui lòng nhập ngày bắt dầu cho tiết kiệm!");
        DataValidator.validateEmpty(txtNgayKetThuc, sb, "Vui lòng nhập ngày kết thúc cho tiết kiệm!");

        if (sb.length() > 0) {
            MsgBox.alert(this, sb.toString());
            return;
        } else {

            TietKiem model = getForm();
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
        DataValidator.validateEmpty(txtMaTKiem, sb, "Vui lòng nhập mã tiết kiệm!");
        DataValidator.validateEmpty(txtTenTKiem, sb, "Vui lòng nhập tên tiết kiệm!");
        DataValidator.validateEmpty(txtThoiHan, sb, "Vui lòng nhập thời hạn cho tiết kiệm!");
        DataValidator.validateEmpty(txtNganHang, sb, "Vui lòng nhập ngân hàng cho tiết kiệm!");
        DataValidator.validateEmpty(txtNgayBatDau, sb, "Vui lòng nhập ngày bắt dầu cho tiết kiệm!");
        DataValidator.validateEmpty(txtNgayKetThuc, sb, "Vui lòng nhập ngày kết thúc cho tiết kiệm!");
        if (sb.length() > 0) {
            MsgBox.alert(this, sb.toString());
            return;
        } else {
            TietKiem model = getForm();

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
                String maTKiem = (String) tblTietKiem.getValueAt(this.row, 0);
                try {
                    dao.delete(maTKiem);
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
        if (row < tblTietKiem.getRowCount() - 1) {
            row++;
            edit();
        }
    }

    void last() {
        row = tblTietKiem.getRowCount() - 1;
        edit();
    }

    void openLaiSuat(){
         new LaiSuatJDialog(parentForm, true).setVisible(true);
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
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtMaTKiem = new javax.swing.JTextField();
        txtTenTKiem = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtThoiHan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNganHang = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNgayBatDau = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNgayKetThuc = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        rdoHetHan = new javax.swing.JRadioButton();
        rdoHoatDong = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        btnMoi = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTietKiem = new javax.swing.JTable();

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Mã tiết kiệm:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, 20));

        txtMaTKiem.setText("wew");
        jPanel2.add(txtMaTKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 153, -1));

        txtTenTKiem.setText("ưewe");
        jPanel2.add(txtTenTKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 166, -1));

        jLabel5.setText("Tên tiết kiệm:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, 20));

        txtThoiHan.setText("20");
        jPanel2.add(txtThoiHan, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 153, -1));

        jLabel6.setText("Thời hạn (Tháng):");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, 20));
        jPanel2.add(txtNganHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 167, -1));

        jLabel7.setText("Ngân hàng:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, -1, 20));

        txtNgayBatDau.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNgayBatDauFocusLost(evt);
            }
        });
        jPanel2.add(txtNgayBatDau, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 153, -1));

        jLabel8.setText("Ngày bắt đầu:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, 20));
        jPanel2.add(txtNgayKetThuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, 169, -1));

        jLabel9.setText("Ngày kết thúc:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, -1, 20));

        jLabel10.setText("Trạng thái:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, -1, 20));

        buttonGroup1.add(rdoHetHan);
        rdoHetHan.setText("Hết hạn");
        jPanel2.add(rdoHetHan, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, -1, -1));

        buttonGroup1.add(rdoHoatDong);
        rdoHoatDong.setSelected(true);
        rdoHoatDong.setText("Hoạt động");
        jPanel2.add(rdoHoatDong, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, -1, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mymoney/icon/Calculator.png"))); // NOI18N
        jButton1.setText("Tính lãi");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jButton1)
                        .addGap(7, 7, 7)
                        .addComponent(btnThem))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(btnFirst)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnSua)
                        .addGap(14, 14, 14)
                        .addComponent(btnXoa)
                        .addGap(11, 11, 11)
                        .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(btnPrev)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLast))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(btnThem)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnXoa)
                        .addComponent(btnSua))
                    .addComponent(btnMoi))
                .addGap(7, 7, 7)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFirst)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLast)
                        .addComponent(btnNext)
                        .addComponent(btnPrev))))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 490, 90));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 0, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("QUẢN LÝ TIẾT KIỆM");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tblTietKiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã tiết kiệm", "Tên tiết kiệm", "Thời hạn (Tháng)", "Ngân hàng", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"
            }
        ));
        tblTietKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTietKiemMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblTietKiem);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        last();
        txtThoiHan.setBackground(Color.white);
        txtMaTKiem.setBackground(Color.white);
        txtNganHang.setBackground(Color.white);
        txtNgayBatDau.setBackground(Color.white);
        txtNgayKetThuc.setBackground(Color.white);
        txtTenTKiem.setBackground(Color.white);
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        next();
        txtThoiHan.setBackground(Color.white);
        txtMaTKiem.setBackground(Color.white);
        txtNganHang.setBackground(Color.white);
        txtNgayBatDau.setBackground(Color.white);
        txtNgayKetThuc.setBackground(Color.white);
        txtTenTKiem.setBackground(Color.white);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        prev();
        txtThoiHan.setBackground(Color.white);
        txtMaTKiem.setBackground(Color.white);
        txtNganHang.setBackground(Color.white);
        txtNgayBatDau.setBackground(Color.white);
        txtNgayKetThuc.setBackground(Color.white);
        txtTenTKiem.setBackground(Color.white);
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        first();
        txtThoiHan.setBackground(Color.white);
        txtMaTKiem.setBackground(Color.white);
        txtNganHang.setBackground(Color.white);
        txtNgayBatDau.setBackground(Color.white);
        txtNgayKetThuc.setBackground(Color.white);
        txtTenTKiem.setBackground(Color.white);
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblTietKiemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTietKiemMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            try {
                this.row = tblTietKiem.rowAtPoint(evt.getPoint());
                edit();
                txtThoiHan.setBackground(Color.white);
                txtMaTKiem.setBackground(Color.white);
                txtNganHang.setBackground(Color.white);
                txtNgayBatDau.setBackground(Color.white);
                txtNgayKetThuc.setBackground(Color.white);
                txtTenTKiem.setBackground(Color.white);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }//GEN-LAST:event_tblTietKiemMousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        openLaiSuat();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtNgayBatDauFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNgayBatDauFocusLost
        // TODO add your handl
    }//GEN-LAST:event_txtNgayBatDauFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoHetHan;
    private javax.swing.JRadioButton rdoHoatDong;
    private javax.swing.JTable tblTietKiem;
    private javax.swing.JTextField txtMaTKiem;
    private javax.swing.JTextField txtNganHang;
    private javax.swing.JTextField txtNgayBatDau;
    private javax.swing.JTextField txtNgayKetThuc;
    private javax.swing.JTextField txtTenTKiem;
    private javax.swing.JTextField txtThoiHan;
    // End of variables declaration//GEN-END:variables
}
