/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyquanhatkaraoke.application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.dao.DAOFactory;
import model.dao.LichLamViecDAO;
import model.entities.CaLamViec;
import model.entities.LichDangKy;
import model.entities.LichLamViec;
import model.entities.NguoiDung;
import model.entities.QuanLy;

/**
 *
 * @author thuongptitdev
 */
public class CheckInCheckOutFrm extends javax.swing.JFrame {

    private List<LichLamViec> lichLamViec;
    private List<LichLamViec> lichAll;
    private List<LichLamViec> lichDaCheckIn;
    QuanLy quanLy;
    LichLamViec s1,s2;
    

    /**
     * Creates new form CheckInCheckOutFrm
     */
    public CheckInCheckOutFrm(QuanLy quanLy) {
        initComponents();
        this.quanLy = quanLy;
        getLichLamViec();
        jButton1.setVisible(false);
        jButton2.setVisible(false);
        jTable2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                int selectrow = jTable2.getSelectedRow();
                if(selectrow == -1){
                    return;
                }
                s1 = lichAll.get(selectrow);
                jButton2.setVisible(true);
                if(s1.getGioden() == null){
                    jButton2.setText("Check in");
                }else if(s1.getGiodi()== null){
                    jButton2.setText("Check out");
                }else{
                    jButton2.setVisible(false);
                }
                // print first column value from selected row
                // System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
            }
        });
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                int selectrow = jTable1.getSelectedRow();
                if(selectrow == -1){
                    return;
                }
                s2 = lichDaCheckIn.get(selectrow);
                if(s1.getGiodi()== null){
                    jButton1.setVisible(true);
                }else{
                    jButton1.setVisible(false);
                }
                // print first column value from selected row
                // System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
            }
        });
    }

    private void getLichLamViec() {
        try {
            LichLamViecDAO dao = DAOFactory.createLichLamViecDAO();
            lichLamViec = filterLichLamViec(dao.fillAll(), "");
            System.out.println("");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void displayTable(String search) {
        try {
            jButton1.setVisible(false);
            jButton2.setVisible(false);
            LichLamViecDAO dao = DAOFactory.createLichLamViecDAO();
            lichLamViec = filterLichLamViec(dao.fillAll(), search);
            String[] tblHead = {"Tên nhân viên", "Chức vụ", "Tên ca", "Ngày", "Giờ đến", "Giờ về"};
            DefaultTableModel model = new DefaultTableModel(tblHead, 0);
            DefaultTableModel model2 = new DefaultTableModel(tblHead, 0);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
            lichAll = new ArrayList<>();
            lichDaCheckIn = new ArrayList<>();
            for (LichLamViec lichlam : lichLamViec) {
                List<String> list = new ArrayList<String>();
                LichDangKy lich = lichlam.getLichDangKy();
                list.add(lichlam.getLichDangKy().getNguoiDung().getTen());
                String chucvu = "N/A";
                if (lich.getNguoiDung().getRole() == NguoiDung.ROLE.LE_TAN) {
                    chucvu = "Lễ tân";
                } else if (lich.getNguoiDung().getRole() == NguoiDung.ROLE.PHUC_VU) {
                    chucvu = "Phục vụ";
                }
                list.add(chucvu);
                list.add(lich.getCaLamViec().getTen());
                list.add(formatter.format(lich.getNgay()));
                if(lichlam.getGioden() != null){
                    list.add(lichlam.getGioden());
                }else{
                    list.add("");
                }
                if(lichlam.getGiodi()!= null){
                    list.add(lichlam.getGiodi());
                }else{
                    list.add("");
                }
                model.addRow(list.toArray());
                lichAll.add(lichlam);
                if(lichlam.getGioden() != null){
                    lichDaCheckIn.add(lichlam);
                    model2.addRow(list.toArray());
                }
            }
            jTable2.setModel(model);
            jTable1.setModel(model2);

            System.out.println("");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
    private String getGio(Date d){
        return "";
    }

    private List<LichLamViec> filterLichLamViec(List<LichLamViec> lich, String ten) {
        List<LichLamViec> result = new ArrayList<>();
        for (LichLamViec e : lich) {
            LichDangKy dk = e.getLichDangKy();
            CaLamViec ca = dk.getCaLamViec();
            Date d = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String d1 = formatter.format(d);
            String d2 = formatter.format(dk.getNgay());
            if (d1.equals(d2)) {
                // xet den gio
                int h = d.getHours();
                int p = d.getMinutes();
                if (h >= ca.getGioBatDau() && h <= ca.getGioKetThuc()) {
                    if ((h == ca.getGioBatDau() && p <= ca.getPhutBatDau()) || (h == ca.getGioKetThuc() && p > ca.getPhutKetThuc())) {
                    } else {
                        if (ten != null) {
                            if (ten.equals("")) {
                                result.add(e);
                            } else {
                                String tmp = dk.getNguoiDung().getHo() + dk.getNguoiDung().getTen();
                                if (tmp.toLowerCase().indexOf(ten.toLowerCase()) >= 0) {
                                    result.add(e);
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public void failedMessage(String errormsg) {
        JOptionPane.showMessageDialog(null, errormsg); // give user feedback
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        jButton2.setText("Checkin");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Tim");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("CheckIn/CheckOut", jPanel1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("CheckOut");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Người dung đang checkin", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String seach = jTextField1.getText();
        displayTable(seach);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            LichLamViecDAO dao = DAOFactory.createLichLamViecDAO();
            Date d = new Date();
            if(s1.getGioden() == null){
                dao.checkin(s1, d.getHours() + "h" + d.getMinutes());
                failedMessage("Check in thành công");
            }else if(s1.getGiodi() == null){
                dao.checkout(s1, d.getHours() + "h" + d.getMinutes());
                failedMessage("Check out thành công");
            }
            displayTable("");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            LichLamViecDAO dao = DAOFactory.createLichLamViecDAO();
            Date d = new Date();
            if(s2.getGioden() == null){
                dao.checkin(s2, d.getHours() + "h" + d.getMinutes());
                failedMessage("Check in thành công");
            }else if(s2.getGiodi() == null){
                dao.checkout(s2, d.getHours() + "h" + d.getMinutes());
                failedMessage("Check out thành công");
            }
            displayTable("");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
