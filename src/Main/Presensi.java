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
import java.time.LocalTime;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
/**
 *
 * @author Axel
 */
public class Presensi extends javax.swing.JPanel {
 String sql;
   Statement stat;
   ResultSet res;
   Koneksi cn = new Koneksi();
    DefaultTableModel model = new DefaultTableModel();
    TableModel tableModel;
  
 
   
    public Presensi() {
        initComponents();
        setBackground(new Color(0,0,0,0));
       Table.getTableHeader().setOpaque(false);
      Table.getTableHeader().setBackground(Color.MAGENTA);
       Table.getTableHeader().setFont(new Font("Montserrat Semibold", Font.PLAIN,11));
        Table.getTableHeader().setForeground(new Color(0,0,0));
        Table.setRowHeight(25);
        show();
        idpeg();
      jmasuk.setTimeToNow();
      jkeluar.setTimeToNow();
      getid.setVisible(false);
     
    
      getstm.setVisible(false);
      getstkel.setVisible(false);
    }

   @Override
    public void show (){
        try {
            model = (DefaultTableModel)Table.getModel();
            stat = cn.login().createStatement();
            sql ="SELECT * FROM  presensi" ;
            res = stat.executeQuery(sql);
            bersihtabel();
            while (res.next()) {
              
                Object [] obj = new Object[9];
                obj[0]=res.getString("id_presensi");
              obj[1] = res.getString("id_pgw");
                 obj[2] = res.getString("tgl_absen");
                 obj[3] = res.getString("jmasuk");
                 obj[4] = res.getString("jkeluar");
                 obj[5] = res.getString("st_masuk");
                 obj[6] = res.getString("st_keluar");
                 obj[7] = res.getString("wkt_telat");
                 obj[8] = res.getString("st_kehadiran");
           
           
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
               
           }
       } catch (Exception e) {
       }
   }
 
  String selectmasuk() {
        for (Enumeration<AbstractButton> buttons = Gmasuk.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }
    
   String selectkeluar() {
        for (Enumeration<AbstractButton> buttons = Gkeluar.getElements(); buttons.hasMoreElements();) {
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
        String dat = String.valueOf(date.format(tgl_abs.getDate()));
        try {
            stat=cn.login().createStatement();
            sql="INSERT INTO presensi (id_pgw, tgl_absen, jmasuk, jkeluar, st_masuk, st_keluar, wkt_telat, st_kehadiran) VALUES ('"+idpeg.getSelectedItem()+"', '"+dat+"','"+jmasuk.getTime()+"','"+jkeluar.getTime()+"','"+selectmasuk()+"','"+selectkeluar()+"','"+wtelat.getTime()+"','"+StHadir.getText()+"')";
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
               sql="DELETE FROM presensi WHERE id_presensi= '"+getid.getText()+"'";
   
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
        String dat = String.valueOf(date.format(tgl_abs.getDate()));
        String id1 = getid.getText();
        ResultSet ra;
        int opt = JOptionPane.showConfirmDialog(null,"Update?", "update",JOptionPane.YES_NO_OPTION);
        if (opt==0) {
        try {
             stat=cn.login().createStatement();
          sql="SELECT id_presensi from presensi WHERE id_presensi='"+id1+"'";
          ra = stat.executeQuery(sql);
            while (ra.next()) {
               String id= ra.getString("id_presensi"); 
                  sql="UPDATE presensi SET id_pgw='"+idpeg.getSelectedItem()+"', tgl_absen='"+dat+"', jmasuk='"+jmasuk.getTime()+"', jkeluar='"+jkeluar.getTime()+"', st_masuk='"+selectmasuk()+"', st_keluar='"+selectkeluar()+"', wkt_telat='"+wtelat.getTime()+"', st_kehadiran='"+StHadir.getText()+"' WHERE id_presensi='"+getid.getText()+"'";  
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

        Gmasuk = new javax.swing.ButtonGroup();
        Gkeluar = new javax.swing.ButtonGroup();
        mainpanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        insert = new javax.swing.JButton();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        getid = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        idpeg = new javax.swing.JComboBox<>();
        tgl_abs = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jmasuk = new com.github.lgooddatepicker.components.TimePicker();
        jLabel8 = new javax.swing.JLabel();
        jkeluar = new com.github.lgooddatepicker.components.TimePicker();
        jLabel9 = new javax.swing.JLabel();
        wtelat = new com.github.lgooddatepicker.components.TimePicker();
        jLabel10 = new javax.swing.JLabel();
        Sud = new javax.swing.JRadioButton();
        bel = new javax.swing.JRadioButton();
        tid = new javax.swing.JRadioButton();
        sud1 = new javax.swing.JRadioButton();
        bel1 = new javax.swing.JRadioButton();
        tid1 = new javax.swing.JRadioButton();
        StHadir = new javax.swing.JTextField();
        getstm = new javax.swing.JLabel();
        getstkel = new javax.swing.JLabel();
        preview = new javax.swing.JButton();
        search = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(607, 738));
        setPreferredSize(new java.awt.Dimension(738, 607));

        mainpanel.setBackground(new java.awt.Color(255, 255, 255));
        mainpanel.setPreferredSize(new java.awt.Dimension(725, 656));

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Id Pegawai", "Tanggal Masuk", "Jam Masuk", "Jam Keluar", "St Masuk", "St Keluar", "Waktu Telat", "St Kehadiran"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, true, true, true, true
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

        jLabel1.setText("LIST PRESENSI");
        jLabel1.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 24)); // NOI18N

        insert.setText("Insert");
        insert.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 12)); // NOI18N
        insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertActionPerformed(evt);
            }
        });

        update.setText("Update");
        update.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 12)); // NOI18N
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        delete.setText("Delete");
        delete.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 12)); // NOI18N
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        jLabel2.setText("INPUT DATA");
        jLabel2.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 24)); // NOI18N

        jLabel3.setText("Id Pegawai :");
        jLabel3.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N

        jLabel4.setText("Tanggal Absen:");
        jLabel4.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N

        getid.setText("_");

        jLabel5.setText("St Masuk :");
        jLabel5.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N

        jLabel6.setText("St Keluar :");
        jLabel6.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N

        tgl_abs.setDateFormatString("yyyy-MM-dd");

        jLabel7.setText("Jam Masuk :");
        jLabel7.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N

        jLabel8.setText("Jam Keluar :");
        jLabel8.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N

        jLabel9.setText("Waktu Telat :");
        jLabel9.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N

        jLabel10.setText("St Kehadiran :");
        jLabel10.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N

        Gmasuk.add(Sud);
        Sud.setText("Sudah");

        Gmasuk.add(bel);
        bel.setText("Belum");

        Gmasuk.add(tid);
        tid.setText("Tidak");

        Gkeluar.add(sud1);
        sud1.setText("Sudah");

        Gkeluar.add(bel1);
        bel1.setText("Belum");

        Gkeluar.add(tid1);
        tid1.setText("Tidak");

        getstm.setText("_");

        getstkel.setText("_");

        preview.setText("Preview");
        preview.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 12)); // NOI18N
        preview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previewActionPerformed(evt);
            }
        });

        search.setText("Cari Disini");
        search.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 12)); // NOI18N
        search.setForeground(new java.awt.Color(102, 102, 102));
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
                            .addGroup(mainpanelLayout.createSequentialGroup()
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jkeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(idpeg, 0, 116, Short.MAX_VALUE)
                                    .addComponent(tgl_abs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jmasuk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel2)))
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(35, 35, 35)
                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9))
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(mainpanelLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(wtelat, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainpanelLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(mainpanelLayout.createSequentialGroup()
                                        .addComponent(sud1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bel1))
                                    .addGroup(mainpanelLayout.createSequentialGroup()
                                        .addComponent(Sud)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bel))))))
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(StHadir, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(getstkel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(getstm, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tid)
                            .addComponent(tid1))
                        .addGap(48, 48, 48))
                    .addComponent(getid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(139, 139, 139))
            .addGroup(mainpanelLayout.createSequentialGroup()
                .addGap(165, 165, 165)
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
                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(34, 34, 34)
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idpeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(12, 12, 12)
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(tgl_abs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jmasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(getid)
                            .addComponent(getstm)
                            .addComponent(getstkel))
                        .addGap(34, 34, 34)
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(Sud)
                            .addComponent(bel)
                            .addComponent(tid))
                        .addGap(11, 11, 11)
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(sud1)
                                .addComponent(bel1)
                                .addComponent(tid1))
                            .addGroup(mainpanelLayout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(wtelat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(StHadir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(75, 75, 75)
                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(insert)
                    .addComponent(update)
                    .addComponent(delete)
                    .addComponent(preview))
                .addContainerGap(186, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 792, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

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
        getid.setText(model.getValueAt(selectRow,0).toString());
        idpeg.setSelectedItem(model.getValueAt(selectRow, 1).toString());
         try {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(selectRow, 2));
            tgl_abs.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(Pegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
         
       jmasuk.setTime(LocalTime.parse((String) model.getValueAt(selectRow, 3)));
       jkeluar.setTime(LocalTime.parse((String) model.getValueAt(selectRow, 4)));
       
       getstm.setText(model.getValueAt(selectRow, 5).toString());
       getstkel.setText(model.getValueAt(selectRow, 6).toString());
       String a = getstm.getText();
        if (a.equals("Sudah")) {
            Sud.setSelected(true);
        }else if (a.equals("Belum")) {
            bel.setSelected(true);
        }else if (a.equals("Tidak")) {
            tid.setSelected(true);
        }
        
        String b = getstkel.getText();
        if (b.equals("Sudah")) {
            sud1.setSelected(true);
        }else if (a.equals("Belum")) {
            bel1.setSelected(true);
        }else if (a.equals("Tidak")) {
            tid1.setSelected(true);
        }
       
       wtelat.setTime(LocalTime.parse((String) model.getValueAt(selectRow, 7)));
       StHadir.setText(model.getValueAt(selectRow, 8).toString());
    }//GEN-LAST:event_TableMousePressed

    private void previewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previewActionPerformed
        PreviewPrintPresensi ne = new PreviewPrintPresensi();
        ne.setVisible(true);
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

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        search();        // TODO add your handling code here:
    }//GEN-LAST:event_searchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Gkeluar;
    private javax.swing.ButtonGroup Gmasuk;
    private javax.swing.JTextField StHadir;
    private javax.swing.JRadioButton Sud;
    private javax.swing.JTable Table;
    private javax.swing.JRadioButton bel;
    private javax.swing.JRadioButton bel1;
    private javax.swing.JButton delete;
    private javax.swing.JLabel getid;
    private javax.swing.JLabel getstkel;
    private javax.swing.JLabel getstm;
    private javax.swing.JComboBox<String> idpeg;
    private javax.swing.JButton insert;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private com.github.lgooddatepicker.components.TimePicker jkeluar;
    private com.github.lgooddatepicker.components.TimePicker jmasuk;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JButton preview;
    private javax.swing.JTextField search;
    private javax.swing.JRadioButton sud1;
    private com.toedter.calendar.JDateChooser tgl_abs;
    private javax.swing.JRadioButton tid;
    private javax.swing.JRadioButton tid1;
    private javax.swing.JButton update;
    private com.github.lgooddatepicker.components.TimePicker wtelat;
    // End of variables declaration//GEN-END:variables
}
