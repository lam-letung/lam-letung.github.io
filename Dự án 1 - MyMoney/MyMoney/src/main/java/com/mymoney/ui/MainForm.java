/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mymoney.ui;

import com.mymoney.utils.Auth;
import com.mymoney.utils.MsgBox;
import com.mymoney.utils.XImage;
import java.awt.Color;
import java.awt.Component;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;
import javax.swing.JDialog;
import javax.swing.JPanel;


/**
 *
 * @author lam
 */
public class MainForm extends javax.swing.JFrame {

    private JPanel chilJPanel;
    private MainForm parentForm;

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        init();
    }

    void init() {
        setIconImage(XImage.getAppIcon());
        setLocationRelativeTo(null);
        setTitle("MY MONEY");
         new Timer(1000,new ActionListener(){
             SimpleDateFormat format = new SimpleDateFormat("hh/mm/ss a");
            @Override
            public void actionPerformed(ActionEvent e) {
            lblDongHo.setText(format.format(new Date()));   
            }
             
            
        }).start();
        this.openDangNhap();
        setTaiKhoan();
    }

    void openDangNhap() {
        new DangNhapJDialog(parentForm, true).setVisible(true);
    }
    
    void setTaiKhoan(){
        lblTaiKhoan.setText("Xin chào: "+ Auth.user.getMaTK());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidepane = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnHeThong = new javax.swing.JPanel();
        lblHeThong = new javax.swing.JLabel();
        pnQuanLy = new javax.swing.JPanel();
        lblQuanLy = new javax.swing.JLabel();
        pnThongKe = new javax.swing.JPanel();
        lblThongKe = new javax.swing.JLabel();
        pnGioiThieu = new javax.swing.JPanel();
        lblGioiThieu = new javax.swing.JLabel();
        pnHuongDan = new javax.swing.JPanel();
        lblHuongDan = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblDongHo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblTaiKhoan = new javax.swing.JLabel();
        pnMain = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidepane.setBackground(new java.awt.Color(54, 33, 89));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("My Money");

        pnHeThong.setBackground(new java.awt.Color(85, 65, 118));

        lblHeThong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblHeThong.setForeground(new java.awt.Color(255, 255, 255));
        lblHeThong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeThong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mymoney/icon/Home.png"))); // NOI18N
        lblHeThong.setText("Hệ Thống   ");
        lblHeThong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblHeThong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHeThongMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblHeThongMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblHeThongMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnHeThongLayout = new javax.swing.GroupLayout(pnHeThong);
        pnHeThong.setLayout(pnHeThongLayout);
        pnHeThongLayout.setHorizontalGroup(
            pnHeThongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHeThong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnHeThongLayout.setVerticalGroup(
            pnHeThongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHeThong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        pnQuanLy.setBackground(new java.awt.Color(85, 65, 118));
        pnQuanLy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnQuanLyMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnQuanLyMouseExited(evt);
            }
        });

        lblQuanLy.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblQuanLy.setForeground(new java.awt.Color(255, 255, 255));
        lblQuanLy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuanLy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mymoney/icon/List.png"))); // NOI18N
        lblQuanLy.setText("Quản Lý       ");
        lblQuanLy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblQuanLy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblQuanLyMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblQuanLyMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblQuanLyMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnQuanLyLayout = new javax.swing.GroupLayout(pnQuanLy);
        pnQuanLy.setLayout(pnQuanLyLayout);
        pnQuanLyLayout.setHorizontalGroup(
            pnQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblQuanLy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnQuanLyLayout.setVerticalGroup(
            pnQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblQuanLy, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        pnThongKe.setBackground(new java.awt.Color(85, 65, 118));
        pnThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnThongKeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnThongKeMouseExited(evt);
            }
        });

        lblThongKe.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblThongKe.setForeground(new java.awt.Color(255, 255, 255));
        lblThongKe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mymoney/icon/Diagram.png"))); // NOI18N
        lblThongKe.setText("Thống Kê     ");
        lblThongKe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThongKeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblThongKeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblThongKeMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnThongKeLayout = new javax.swing.GroupLayout(pnThongKe);
        pnThongKe.setLayout(pnThongKeLayout);
        pnThongKeLayout.setHorizontalGroup(
            pnThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnThongKeLayout.setVerticalGroup(
            pnThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblThongKe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        pnGioiThieu.setBackground(new java.awt.Color(85, 65, 118));
        pnGioiThieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnGioiThieuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnGioiThieuMouseExited(evt);
            }
        });

        lblGioiThieu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblGioiThieu.setForeground(new java.awt.Color(255, 255, 255));
        lblGioiThieu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGioiThieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mymoney/icon/List.png"))); // NOI18N
        lblGioiThieu.setText("Giới thiệu      ");
        lblGioiThieu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblGioiThieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGioiThieuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblGioiThieuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblGioiThieuMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnGioiThieuLayout = new javax.swing.GroupLayout(pnGioiThieu);
        pnGioiThieu.setLayout(pnGioiThieuLayout);
        pnGioiThieuLayout.setHorizontalGroup(
            pnGioiThieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblGioiThieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnGioiThieuLayout.setVerticalGroup(
            pnGioiThieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblGioiThieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        pnHuongDan.setBackground(new java.awt.Color(85, 65, 118));
        pnHuongDan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnHuongDanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnHuongDanMouseExited(evt);
            }
        });

        lblHuongDan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblHuongDan.setForeground(new java.awt.Color(255, 255, 255));
        lblHuongDan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHuongDan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mymoney/icon/Delete.png"))); // NOI18N
        lblHuongDan.setText("Kết thúc        ");
        lblHuongDan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblHuongDan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHuongDanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblHuongDanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblHuongDanMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnHuongDanLayout = new javax.swing.GroupLayout(pnHuongDan);
        pnHuongDan.setLayout(pnHuongDanLayout);
        pnHuongDanLayout.setHorizontalGroup(
            pnHuongDanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHuongDan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnHuongDanLayout.setVerticalGroup(
            pnHuongDanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHuongDan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 255));
        jLabel5.setText("Giàu không tiết kiệm, nghèo liền tay");

        jLabel6.setBackground(new java.awt.Color(204, 204, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 255));
        jLabel6.setText("Nghèo không tiết kiệm, sớm ăn mày");

        lblDongHo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDongHo.setForeground(new java.awt.Color(255, 255, 255));
        lblDongHo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDongHo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mymoney/icon/Clock.png"))); // NOI18N
        lblDongHo.setText("jLabel2");

        jPanel2.setBackground(new java.awt.Color(85, 65, 118));

        lblTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        lblTaiKhoan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        lblTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mymoney/icon/User.png"))); // NOI18N
        lblTaiKhoan.setText("jLabel2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout sidepaneLayout = new javax.swing.GroupLayout(sidepane);
        sidepane.setLayout(sidepaneLayout);
        sidepaneLayout.setHorizontalGroup(
            sidepaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnHeThong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnQuanLy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnGioiThieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnHuongDan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sidepaneLayout.createSequentialGroup()
                .addGroup(sidepaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidepaneLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sidepaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidepaneLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lblDongHo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        sidepaneLayout.setVerticalGroup(
            sidepaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidepaneLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(25, 25, 25)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnHeThong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(pnQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(pnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(pnGioiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(pnHuongDan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                .addComponent(lblDongHo)
                .addGap(20, 20, 20))
        );

        getContentPane().add(sidepane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 620));

        pnMain.setLayout(new java.awt.BorderLayout());

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mymoney/icon/stock1.png"))); // NOI18N
        pnMain.add(jLabel4, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        pnMain.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(pnMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 720, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblHeThongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHeThongMouseClicked
        if (Auth.isLogin()) {
            chilJPanel = new HeThongJPanel();
            pnMain.removeAll();
            pnMain.add(chilJPanel);

            pnHeThong.setBackground(Color.white);
            lblHeThong.setForeground(new Color(255, 255, 102));
            pnQuanLy.setBackground(new Color(85, 65, 118));
            lblQuanLy.setForeground(Color.white);
            pnThongKe.setBackground(new Color(85, 65, 118));
            lblThongKe.setForeground(Color.white);
            pnHuongDan.setBackground(new Color(85, 65, 118));
            lblHuongDan.setForeground(Color.white);
            pnGioiThieu.setBackground(new Color(85, 65, 118));
            lblGioiThieu.setForeground(Color.white);

            pnMain.validate();
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập");

            openDangNhap();
        }

    }//GEN-LAST:event_lblHeThongMouseClicked

    private void lblQuanLyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuanLyMouseClicked
        // TODO add your handling code here:
        if (Auth.isLogin()) {
            chilJPanel = new QuanLyJPanel();
            pnMain.removeAll();
            pnMain.add(chilJPanel);

            pnHeThong.setBackground(new Color(85, 65, 118));
            lblHeThong.setForeground(Color.white);
            pnQuanLy.setBackground(Color.white);
            lblQuanLy.setForeground(new Color(255, 255, 102));
            pnThongKe.setBackground(new Color(85, 65, 118));
            lblThongKe.setForeground(Color.white);
            pnHuongDan.setBackground(new Color(85, 65, 118));
            lblHuongDan.setForeground(Color.white);
            pnGioiThieu.setBackground(new Color(85, 65, 118));
            lblGioiThieu.setForeground(Color.white);
            pnMain.validate();
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập");
            openDangNhap();
        }

    }//GEN-LAST:event_lblQuanLyMouseClicked

    private void lblThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThongKeMouseClicked
        // TODO add your handling code here:
        if (Auth.isLogin()) {
            chilJPanel = new ThongKeJPanel();
            pnMain.removeAll();
            pnMain.add(chilJPanel);
            pnHeThong.setBackground(new Color(85, 65, 118));
            lblHeThong.setForeground(Color.white);
            pnQuanLy.setBackground(new Color(85, 65, 118));
            lblQuanLy.setForeground(Color.white);
            pnThongKe.setBackground(Color.white);
            lblThongKe.setForeground(new Color(255, 255, 102));
            pnHuongDan.setBackground(new Color(85, 65, 118));
            lblHuongDan.setForeground(Color.white);
            pnGioiThieu.setBackground(new Color(85, 65, 118));
            lblGioiThieu.setForeground(Color.white);
            
            pnMain.validate();
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập");
            openDangNhap();
        }
    }//GEN-LAST:event_lblThongKeMouseClicked

    private void lblGioiThieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGioiThieuMouseClicked
        // TODO add your handling code here:
        if (Auth.isLogin()) {
            chilJPanel = new GioiThieuJPanel();
            pnMain.removeAll();
            pnMain.add(chilJPanel);
            pnHeThong.setBackground(new Color(85, 65, 118));
            lblHeThong.setForeground(Color.white);
            pnQuanLy.setBackground(new Color(85, 65, 118));
            lblQuanLy.setForeground(Color.white);
            pnThongKe.setBackground(new Color(85, 65, 118));
            lblThongKe.setForeground(Color.white);
            pnHuongDan.setBackground(new Color(85, 65, 118));
            lblHuongDan.setForeground(Color.white);
            pnGioiThieu.setBackground(Color.white);
            lblGioiThieu.setForeground(new Color(255, 255, 102));
            
            pnMain.validate();
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập");
            openDangNhap();
        }
    }//GEN-LAST:event_lblGioiThieuMouseClicked

    private void lblHuongDanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHuongDanMouseClicked
        // TODO add your handling code here:
        System.exit(0);
        pnHeThong.setBackground(new Color(85, 65, 118));
        lblHeThong.setForeground(Color.white);
        pnQuanLy.setBackground(new Color(85, 65, 118));
        lblQuanLy.setForeground(Color.white);
        pnThongKe.setBackground(new Color(85, 65, 118));
        lblThongKe.setForeground(Color.white);
        pnHuongDan.setBackground(Color.white);
        lblHuongDan.setForeground(new Color(255, 255, 102));
        pnGioiThieu.setBackground(new Color(85, 65, 118));
        lblGioiThieu.setForeground(Color.white);
    }//GEN-LAST:event_lblHuongDanMouseClicked

    private void lblHeThongMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHeThongMouseEntered
        // TODO add your handling code here:
        pnHeThong.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_lblHeThongMouseEntered

    private void lblHeThongMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHeThongMouseExited
        // TODO add your handling code here:

        pnHeThong.setBackground(new Color(85, 65, 118));
    }//GEN-LAST:event_lblHeThongMouseExited

    private void pnQuanLyMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnQuanLyMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_pnQuanLyMouseEntered

    private void pnQuanLyMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnQuanLyMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_pnQuanLyMouseExited

    private void pnThongKeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnThongKeMouseEntered
        // TODO add your handling code here:


    }//GEN-LAST:event_pnThongKeMouseEntered

    private void pnThongKeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnThongKeMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_pnThongKeMouseExited

    private void pnGioiThieuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnGioiThieuMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_pnGioiThieuMouseEntered

    private void pnGioiThieuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnGioiThieuMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_pnGioiThieuMouseExited

    private void pnHuongDanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnHuongDanMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_pnHuongDanMouseEntered

    private void pnHuongDanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnHuongDanMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_pnHuongDanMouseExited

    private void lblQuanLyMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuanLyMouseEntered
        // TODO add your handling code here:
        pnQuanLy.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_lblQuanLyMouseEntered

    private void lblQuanLyMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuanLyMouseExited
        // TODO add your handling code here:
        pnQuanLy.setBackground(new Color(85, 65, 118));
    }//GEN-LAST:event_lblQuanLyMouseExited

    private void lblThongKeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThongKeMouseEntered
        // TODO add your handling code here:
        pnThongKe.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_lblThongKeMouseEntered

    private void lblThongKeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThongKeMouseExited
        // TODO add your handling code here:
        pnThongKe.setBackground(new Color(85, 65, 118));
    }//GEN-LAST:event_lblThongKeMouseExited

    private void lblGioiThieuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGioiThieuMouseEntered
        // TODO add your handling code here:
        pnGioiThieu.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_lblGioiThieuMouseEntered

    private void lblGioiThieuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGioiThieuMouseExited
        // TODO add your handling code here:
        pnGioiThieu.setBackground(new Color(85, 65, 118));
    }//GEN-LAST:event_lblGioiThieuMouseExited

    private void lblHuongDanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHuongDanMouseEntered
        // TODO add your handling code here:
        pnHuongDan.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_lblHuongDanMouseEntered

    private void lblHuongDanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHuongDanMouseExited
        // TODO add your handling code here:
        pnHuongDan.setBackground(new Color(85, 65, 118));
    }//GEN-LAST:event_lblHuongDanMouseExited

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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JLabel lblGioiThieu;
    private javax.swing.JLabel lblHeThong;
    private javax.swing.JLabel lblHuongDan;
    private javax.swing.JLabel lblQuanLy;
    private javax.swing.JLabel lblTaiKhoan;
    private javax.swing.JLabel lblThongKe;
    private javax.swing.JPanel pnGioiThieu;
    private javax.swing.JPanel pnHeThong;
    private javax.swing.JPanel pnHuongDan;
    private javax.swing.JPanel pnMain;
    private javax.swing.JPanel pnQuanLy;
    private javax.swing.JPanel pnThongKe;
    private javax.swing.JPanel sidepane;
    // End of variables declaration//GEN-END:variables
}
