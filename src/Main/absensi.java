/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Main;


import java.awt.Color;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.sql.*;
import javax.swing.*;
import Config.Koneksi;
import java.awt.HeadlessException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
/**
 *
 * @author Axel
 */
public class absensi extends javax.swing.JPanel {
 String sql;
   Statement stat;
   ResultSet res;
   Koneksi cn = new Koneksi();
    DefaultTableModel model = new DefaultTableModel();
    TableModel tableModel;
  
 
   
    public absensi() {
        initComponents();
        setBackground(new Color(0,0,0,0));
       Table.getTableHeader().setOpaque(false);
      Table.getTableHeader().setBackground(Color.MAGENTA);
       Table.getTableHeader().setFont(new Font("Montserrat Semibold", Font.PLAIN,11));
        Table.getTableHeader().setForeground(new Color(0,0,0));
        Table.setRowHeight(25);
        show();
        idpeg();
     getstat.setVisible(false);
      getid.setVisible(false);
     
    
     
    }

   @Override
    public void show (){
        try {
            model = (DefaultTableModel)Table.getModel();
            stat = cn.login().createStatement();
            sql ="SELECT * FROM  absensi" ;
            res = stat.executeQuery(sql);
            bersihtabel();
            while (res.next()) {
              
                Object [] obj = new Object[7];
                obj[0]=res.getString("id_absen");
              obj[1] = res.getString("id_pgw");
                 obj[2] = res.getString("nama");
                 obj[3] = res.getString("tgl_mulai");
                 obj[4] = res.getString("tgl_selesai");
                 obj[5] = res.getString("status");
                 obj[6] = res.getString("ket");
 
                     model.addRow(obj);
            }
        } catch (Exception e) {
        }
    }
    
   public void bersihtabel(){
       while (model.getRowCount()>0) {
          model.setRowCount(0);
       }
   }
   
   public void search(){
   DefaultTableModel model = (DefaultTableModel)Table.getModel();
   TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
   Table.setRowSorter(tr);
   tr.setRowFilter(RowFilter.regexFilter(search.getText().trim()));
   
   }
 public void idpeg(){
       try {
           stat=cn.login().createStatement();
           sql="SELECT * FROM pegawai";
           res=stat.executeQuery(sql);
           while (res.next()) {
            idpeg.addItem(res.getString("id_pgw"));
            namapeg.addItem(res.getString("nama"));
           }
       } catch (Exception e) {
       }
   }
 

   String selectstatus() {
        for (Enumeration<AbstractButton> buttons = Gstatus.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }
    public void insert(){
        String Tdate = "yyyy-MM-dd";
        SimpleDateFormat date = new SimpleDateFormat(Tdate);
        String dat = String.valueOf(date.format(tglmul.getDate()));
        String dat1 = String.valueOf(date.format(tglsel.getDate()));
        try {
            stat=cn.login().createStatement();
            sql="INSERT INTO absensi (id_pgw, nama, tgl_mulai, tgl_selesai, status, ket) VALUES ('"+idpeg.getSelectedItem()+"', '"+namapeg.getSelectedItem()+"', '"+dat+"', '"+dat1+"', '"+selectstatus()+"', '"+ketera.getText()+"')";
            stat.execute(sql);
            bersihtabel();
           JOptionPane.showMessageDialog(null, "berhasil");
        } catch (Exception e) {
              JOptionPane.showMessageDialog(null, "Gagal"+e);
        }
    }
    
    public void delete(){
        int opt = JOptionPane.showConfirmDialog(null,"Yakin mau dihapus?", "hapus",JOptionPane.YES_NO_OPTION);
        if (opt==0) {
             try {
               stat=cn.login().createStatement();
               sql="DELETE FROM absensi WHERE id_absen= '"+getid.getText()+"'";
   
               stat.executeUpdate(sql);
               bersihtabel();
               JOptionPane.showMessageDialog(null, "data dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error");
        }
        
        }
    }
    public void update(){
          String Tdate = "yyyy-MM-dd";
        SimpleDateFormat date = new SimpleDateFormat(Tdate);
        String dat = String.valueOf(date.format(tglmul.getDate()));
        String dat1 = String.valueOf(date.format(tglsel.getDate()));
        String id1 = getid.getText();
        ResultSet ra;
        int opt = JOptionPane.showConfirmDialog(null,"Update?", "update",JOptionPane.YES_NO_OPTION);
        if (opt==0) {
        try {
             stat=cn.login().createStatement();
          sql="SELECT id_absen from absensi WHERE id_absen='"+id1+"'";
          ra = stat.executeQuery(sql);
            while (ra.next()) {
               String id= ra.getString("id_absen");
                  sql=" UPDATE absensi SET id_pgw='"+idpeg.getSelectedItem()+"', nama='"+namapeg.getSelectedItem()+"', tgl_mulai='"+dat+"', tgl_selesai='"+dat1+"', status='"+selectstatus()+"', ket='"+ketera.getText()+"' WHERE id_absen='"+id1+"'";  
            } stat.executeUpdate(sql);
             JOptionPane.showMessageDialog(null,"data di update");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal"+e);
        }
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

        Gstatus = new javax.swing.ButtonGroup();
        mainpanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        insert = new javax.swing.JButton();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        getid = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        idpeg = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cut = new javax.swing.JRadioButton();
        iji = new javax.swing.JRadioButton();
        sak = new javax.swing.JRadioButton();
        ketera = new javax.swing.JTextField();
        tglmul = new com.toedter.calendar.JDateChooser();
        tglsel = new com.toedter.calendar.JDateChooser();
        namapeg = new javax.swing.JComboBox<>();
        getstat = new javax.swing.JLabel();
        preview = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(607, 738));
        setPreferredSize(new java.awt.Dimension(738, 607));

        mainpanel.setBackground(new java.awt.Color(255, 255, 255));
        mainpanel.setPreferredSize(new java.awt.Dimension(725, 656));

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Id Pegawai", "Nama", "Tanggal Mulai", "Tanggal  Selesai", "Status", "Keterangan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table.setFocusable(false);
        Table.setIntercellSpacing(new java.awt.Dimension(0, 0));
        Table.setName(""); // NOI18N
        Table.setRowHeight(25);
        Table.setSelectionBackground(new java.awt.Color(156, 236, 251));
        Table.setShowGrid(true);
        Table.getTableHeader().setReorderingAllowed(false);
        Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(Table);

        jLabel1.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 24)); // NOI18N
        jLabel1.setText("LIST ABSENSI");

        search.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 12)); // NOI18N
        search.setForeground(new java.awt.Color(102, 102, 102));
        search.setText("Cari Disini");
        search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchFocusLost(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });

        insert.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 12)); // NOI18N
        insert.setText("Insert");
        insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertActionPerformed(evt);
            }
        });

        update.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 12)); // NOI18N
        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        delete.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 12)); // NOI18N
        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 24)); // NOI18N
        jLabel2.setText("INPUT DATA");

        jLabel3.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N
        jLabel3.setText("Id Pegawai :");

        jLabel4.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N
        jLabel4.setText("Nama :");

        getid.setText("_");

        jLabel5.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N
        jLabel5.setText("Status :");

        jLabel7.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N
        jLabel7.setText("Tanggal Mulai :");

        jLabel8.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N
        jLabel8.setText("Tanggal Selesai :");

        jLabel10.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N
        jLabel10.setText("Keterangan :");

        Gstatus.add(cut);
        cut.setText("Cuti");

        Gstatus.add(iji);
        iji.setText("Izin");

        Gstatus.add(sak);
        sak.setText("Sakit");

        tglmul.setDateFormatString("yyyy-MM-dd");

        tglsel.setDateFormatString("yyyy-MM-dd");

        getstat.setText("_");

        preview.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 12)); // NOI18N
        preview.setText("Preview");
        preview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainpanelLayout = new javax.swing.GroupLayout(mainpanel);
        mainpanel.setLayout(mainpanelLayout);
        mainpanelLayout.setHorizontalGroup(
            mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(mainpanelLayout.createSequentialGroup()
                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(mainpanelLayout.createSequentialGroup()
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(idpeg, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tglmul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tglsel, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                    .addComponent(namapeg, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(65, 65, 65)
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(mainpanelLayout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addGap(28, 28, 28)
                                            .addComponent(cut)
                                            .addGap(13, 13, 13)
                                            .addComponent(iji)
                                            .addGap(18, 18, 18)
                                            .addComponent(sak))
                                        .addGroup(mainpanelLayout.createSequentialGroup()
                                            .addComponent(jLabel10)
                                            .addGap(18, 18, 18)
                                            .addComponent(ketera)))
                                    .addGroup(mainpanelLayout.createSequentialGroup()
                                        .addGap(189, 189, 189)
                                        .addComponent(getstat, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(getid, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
            .addGroup(mainpanelLayout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addComponent(insert)
                .addGap(18, 18, 18)
                .addComponent(update)
                .addGap(18, 18, 18)
                .addComponent(delete)
                .addGap(18, 18, 18)
                .addComponent(preview)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainpanelLayout.setVerticalGroup(
            mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(getid)
                    .addComponent(getstat))
                .addGap(34, 34, 34)
                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cut)
                            .addComponent(iji)
                            .addComponent(sak))
                        .addGap(18, 18, 18)
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(ketera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idpeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainpanelLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel4))
                            .addGroup(mainpanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(namapeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(16, 16, 16)
                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(tglmul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(tglsel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(insert)
                    .addComponent(update)
                    .addComponent(delete)
                    .addComponent(preview))
                .addContainerGap(227, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 792, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
search();        // TODO add your handling code here:
    }//GEN-LAST:event_searchKeyReleased

    private void insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertActionPerformed
insert();
show();// TODO add your handling code here:
    }//GEN-LAST:event_insertActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
delete();
show();// TODO add your handling code here:
    }//GEN-LAST:event_deleteActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
update();
show();// TODO add your handling code here:
    }//GEN-LAST:event_updateActionPerformed

    private void TableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMousePressed
        int selectRow = Table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)Table.getModel();
       getid.setText(model.getValueAt(selectRow, 0).toString());
       idpeg.setSelectedItem(model.getValueAt(selectRow, 1).toString());
       namapeg.setSelectedItem(model.getValueAt(selectRow, 2).toString());
       
       try {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(selectRow, 3));
            tglmul.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(Pegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
       try {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(selectRow, 4));
            tglsel.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(Pegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       getstat.setText(model.getValueAt(selectRow, 5).toString());
       String a =getstat.getText();
        if (a.equals("cuti")) {
            cut.setSelected(true);
        }else if (a.equals("izin")) {
            iji.setSelected(true);
        }else if (a.equals("sakit")) {
            sak.setSelected(true);
        }
        
        ketera.setText(model.getValueAt(selectRow, 6).toString());
    }//GEN-LAST:event_TableMousePressed

    
    private void previewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previewActionPerformed
   PreviewPrintAbsen previewprint = new PreviewPrintAbsen();
previewprint.setVisible(true);
    }//GEN-LAST:event_previewActionPerformed

    private void searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusGained
        if (search.getText().equals("Cari Disini")) {
            search.setText("");
            search.setForeground(new Color(102,102,102));
        }
   // TODO add your handling code here:
    }//GEN-LAST:event_searchFocusGained

    private void searchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusLost
         if (search.getText().equals("")) {
            search.setText("Cari Disini");
            search.setForeground(new Color(102,102,102));
        }// TODO add your handling code here:
    }//GEN-LAST:event_searchFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Gstatus;
    private javax.swing.JTable Table;
    private javax.swing.JRadioButton cut;
    private javax.swing.JButton delete;
    private javax.swing.JLabel getid;
    private javax.swing.JLabel getstat;
    private javax.swing.JComboBox<String> idpeg;
    private javax.swing.JRadioButton iji;
    private javax.swing.JButton insert;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField ketera;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JComboBox<String> namapeg;
    private javax.swing.JButton preview;
    private javax.swing.JRadioButton sak;
    private javax.swing.JTextField search;
    private com.toedter.calendar.JDateChooser tglmul;
    private com.toedter.calendar.JDateChooser tglsel;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
