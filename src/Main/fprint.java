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
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
public class fprint extends javax.swing.JPanel {
 String sql;
   Statement stat;
   ResultSet res;
   Koneksi cn = new Koneksi();
    DefaultTableModel model = new DefaultTableModel();
    TableModel tableModel;
  String filename = null;
    byte[] person_img = null;
 
   
    public fprint() {
        initComponents();
        setBackground(new Color(0,0,0,0));
       Table.getTableHeader().setOpaque(false);
      Table.getTableHeader().setBackground(Color.MAGENTA);
       Table.getTableHeader().setFont(new Font("Montserrat Semibold", Font.PLAIN,11));
        Table.getTableHeader().setForeground(new Color(0,0,0));
        Table.setRowHeight(25);
        show();
        idpeg();
        idkantor();
        getid.setVisible(false);
     
    
     
    }

   @Override
    public void show (){
        try {
            model = (DefaultTableModel)Table.getModel();
            stat = cn.login().createStatement();
            sql ="SELECT * FROM  fprint" ;
            res = stat.executeQuery(sql);
            bersihtabel();
            while (res.next()) {
              
                Object [] obj = new Object[6];
                obj[0]=res.getString("id_fprint");
              obj[1] = res.getString("id_pgw");
                 obj[2] = res.getString("nama");
                 obj[3] = res.getString("id_kantor");
                 obj[4] = res.getString("scan_1");
                 obj[5] = res.getString("scan_2");
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
 
 public void idkantor(){
       try {
           stat=cn.login().createStatement();
           sql="SELECT * FROM kantor";
           res=stat.executeQuery(sql);
           while (res.next()) {
            idkant.addItem(res.getString("id_kantor"));
           
           }
       } catch (Exception e) {
       }
   }
 
 public void selectpic() {
        JFileChooser pilih = new JFileChooser();
        pilih.showOpenDialog(null);
        File f = pilih.getSelectedFile();
        filename = f.getAbsolutePath();
        foto.setText(filename);
        ImageIcon imgag = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(scan1.getWidth(), 
                scan1.getHeight(), Image.SCALE_SMOOTH));
        scan1.setIcon(imgag);
        try {
            File image = new File(filename);
            FileInputStream fis = new FileInputStream(image);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            person_img = bos.toByteArray();
        } catch (Exception e) {
        }
    }
 
 public void selectpic2() {
        JFileChooser pilih = new JFileChooser();
        pilih.showOpenDialog(null);
        File f = pilih.getSelectedFile();
        filename = f.getAbsolutePath();
        foto1.setText(filename);
        ImageIcon imgag = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(scan2.getWidth(), 
                scan2.getHeight(), Image.SCALE_SMOOTH));
        scan2.setIcon(imgag);
        try {
            File image = new File(filename);
            FileInputStream fis = new FileInputStream(image);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            person_img = bos.toByteArray();
        } catch (Exception e) {
        }
    }
 
    public void insert(){
     
        try {
            stat=cn.login().createStatement();
            sql="INSERT INTO fprint (id_pgw, nama,id_kantor, scan_1, scan_2) VALUES ('"+idpeg.getSelectedItem()+"', '"+namapeg.getSelectedItem()+"','"+idkant.getSelectedItem()+"','"+foto.getText()+"','"+foto1.getText()+"' )";
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
               sql="DELETE FROM fprint WHERE id_fprint= '"+getid.getText()+"'";
   
               stat.executeUpdate(sql);
               bersihtabel();
               JOptionPane.showMessageDialog(null, "data dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error");
        }
        
        }
    }
    public void update(){
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
                  sql=" UPDATE absensi SET id_pgw='"+idpeg.getSelectedItem()+"', nama='"+namapeg.getSelectedItem()+"',id_kantor='"+idkant.getSelectedItem()+"', scan_1='"+foto.getText()+"', scan_2='"+foto1.getText()+"' WHERE id_fprint='"+id1+"'";  
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
        insert = new javax.swing.JButton();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        getid = new javax.swing.JLabel();
        idpeg = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        namapeg = new javax.swing.JComboBox<>();
        idkant = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        scan2 = new javax.swing.JLabel();
        scan1 = new javax.swing.JLabel();
        selectpic = new javax.swing.JButton();
        selectpic1 = new javax.swing.JButton();
        foto = new javax.swing.JLabel();
        foto1 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(607, 738));
        setPreferredSize(new java.awt.Dimension(738, 607));

        mainpanel.setBackground(new java.awt.Color(255, 255, 255));
        mainpanel.setPreferredSize(new java.awt.Dimension(725, 656));

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Id Pegawai", "Nama", "Id Kantor", "Scan 1", "Scan 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
        jLabel1.setText("LIST FINGER PRINT");

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

        jLabel7.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N
        jLabel7.setText("Id Kantor :");

        jLabel8.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N
        jLabel8.setText("Scan 1 :");

        jLabel9.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N
        jLabel9.setText("Scan 2 :");

        scan2.setText("-");

        scan1.setText("-");

        selectpic.setText("Pilih Gambar");
        selectpic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectpicActionPerformed(evt);
            }
        });

        selectpic1.setText("Pilih Gambar");
        selectpic1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectpic1ActionPerformed(evt);
            }
        });

        foto.setText("-");

        foto1.setText("-");

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

        javax.swing.GroupLayout mainpanelLayout = new javax.swing.GroupLayout(mainpanel);
        mainpanel.setLayout(mainpanelLayout);
        mainpanelLayout.setHorizontalGroup(
            mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
            .addGroup(mainpanelLayout.createSequentialGroup()
                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainpanelLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(32, 32, 32)
                                .addComponent(getid, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainpanelLayout.createSequentialGroup()
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(mainpanelLayout.createSequentialGroup()
                                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel7))
                                        .addGap(46, 46, 46)
                                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(idpeg, 0, 153, Short.MAX_VALUE)
                                            .addComponent(namapeg, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(idkant, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(jLabel4))
                                .addGap(47, 47, 47)
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(mainpanelLayout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(20, 20, 20)
                                        .addComponent(scan2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(mainpanelLayout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(scan1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(15, 15, 15)
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(selectpic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(selectpic1))
                                    .addComponent(foto1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(foto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addComponent(insert)
                        .addGap(18, 18, 18)
                        .addComponent(update)
                        .addGap(18, 18, 18)
                        .addComponent(delete))
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addGap(18, 18, 18))
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
                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(getid))
                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idpeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(38, 38, 38)
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainpanelLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel4))
                            .addComponent(namapeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(idkant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(insert)
                            .addComponent(update)
                            .addComponent(delete))
                        .addGap(30, 30, 30))
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainpanelLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(48, 48, 48))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainpanelLayout.createSequentialGroup()
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(scan1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(selectpic))
                                .addGap(17, 17, 17))
                            .addComponent(foto, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(scan2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(selectpic1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(foto1)
                        .addContainerGap(213, Short.MAX_VALUE))))
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
       idkant.setSelectedItem(model.getValueAt(selectRow, 3).toString());
       foto.setText(model.getValueAt(selectRow, 4).toString());
       foto1.setText(model.getValueAt(selectRow, 5).toString());
       
      
    }//GEN-LAST:event_TableMousePressed

    private void selectpicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectpicActionPerformed
selectpic();// TODO add your handling code here:
    }//GEN-LAST:event_selectpicActionPerformed

    private void selectpic1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectpic1ActionPerformed
selectpic2();       // TODO add your handling code here:
    }//GEN-LAST:event_selectpic1ActionPerformed

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
    private javax.swing.ButtonGroup Gstatus;
    private javax.swing.JTable Table;
    private javax.swing.JButton delete;
    private javax.swing.JLabel foto;
    private javax.swing.JLabel foto1;
    private javax.swing.JLabel getid;
    private javax.swing.JComboBox<String> idkant;
    private javax.swing.JComboBox<String> idpeg;
    private javax.swing.JButton insert;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JComboBox<String> namapeg;
    private javax.swing.JLabel scan1;
    private javax.swing.JLabel scan2;
    private javax.swing.JTextField search;
    private javax.swing.JButton selectpic;
    private javax.swing.JButton selectpic1;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
