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
import java.util.Enumeration;
import javax.swing.RowFilter;
/**
 *
 * @author Axel
 */
public class user extends javax.swing.JPanel {
 String sql;
   Statement stat;
   ResultSet res;
   Koneksi cn = new Koneksi();
    DefaultTableModel model = new DefaultTableModel();
    TableModel tableModel;
  
 
   
    public user() {
        initComponents();
        setBackground(new Color(0,0,0,0));
       Table.getTableHeader().setOpaque(false);
      Table.getTableHeader().setBackground(Color.MAGENTA);
       Table.getTableHeader().setFont(new Font("Montserrat Semibold", Font.PLAIN,11));
        Table.getTableHeader().setForeground(new Color(0,0,0));
        Table.setRowHeight(25);
        show();
        namapeg();
      
      getid.setVisible(false);
    }

   @Override
    public void show (){
        try {
            model = (DefaultTableModel)Table.getModel();
            stat = cn.login().createStatement();
            sql ="SELECT * FROM  user" ;
            res = stat.executeQuery(sql);
            bersihtabel();
            while (res.next()) {
              
                Object [] obj = new Object[05];
                obj[0]=res.getString("id_user");
              obj[1] = res.getString("nama");
                 obj[2] = res.getString("level");
                 obj[3] = res.getString("user");
                 obj[4] = res.getString("pass");
           
           
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
 public void namapeg(){
       try {
           stat=cn.login().createStatement();
           sql="SELECT * FROM pegawai";
           res=stat.executeQuery(sql);
           while (res.next()) {
            nama.addItem(res.getString("nama"));
               
           }
       } catch (Exception e) {
       }
   
   }
    
    public void insert(){
        try {
            stat=cn.login().createStatement();
            sql=" INSERT INTO user (nama, level, user, pass)"+"VALUES ( '"+nama.getSelectedItem()+"','"+level.getSelectedItem()+"', '"+user.getText()+"', '"+pass.getText()+"')";
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
               sql="DELETE FROM user WHERE id_user= '"+getid.getText()+"'";
   
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
          sql="SELECT id_user from user WHERE id_user='"+id1+"'";
          ra = stat.executeQuery(sql);
            while (ra.next()) {
               String id= ra.getString("id_user");
                  sql="UPDATE user SET  nama='"+nama.getSelectedItem()+"', level ='"+level.getSelectedItem()+"', user ='"+user.getText()+"', pass ='"+pass.getText()+"' WHERE id_user='"+id+"' ";  
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
        user = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        pass = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        nama = new javax.swing.JComboBox<>();
        level = new javax.swing.JComboBox<>();
        search = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(607, 738));
        setPreferredSize(new java.awt.Dimension(738, 607));

        mainpanel.setBackground(new java.awt.Color(255, 255, 255));
        mainpanel.setPreferredSize(new java.awt.Dimension(725, 656));

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Nama", "Level", "User", "Pass"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
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
        jLabel1.setText("LIST USER");

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
        jLabel3.setText("Nama :");

        jLabel4.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N
        jLabel4.setText("Level :");

        getid.setText("_");

        jLabel5.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N
        jLabel5.setText("User :");

        jLabel6.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N
        jLabel6.setText("Password :");

        level.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "User", "Admin" }));

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
            .addComponent(jScrollPane1)
            .addGroup(mainpanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(488, 488, 488))
            .addGroup(mainpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainpanelLayout.createSequentialGroup()
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(mainpanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(insert)
                                .addGap(18, 18, 18)
                                .addComponent(update)
                                .addGap(18, 18, 18)
                                .addComponent(delete))
                            .addGroup(mainpanelLayout.createSequentialGroup()
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(mainpanelLayout.createSequentialGroup()
                                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3))
                                        .addGap(18, 18, 18)
                                        .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(level, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))))
                        .addGap(37, 37, 37)
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(155, 155, 155))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainpanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(getid, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
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
                .addComponent(jLabel2)
                .addGap(14, 14, 14)
                .addComponent(getid)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainpanelLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(mainpanelLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(level, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(insert)
                            .addComponent(update)
                            .addComponent(delete))
                        .addGap(158, 158, 158))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        nama.setSelectedItem(model.getValueAt(selectRow, 1).toString());
         level.setSelectedItem(model.getValueAt(selectRow,2).toString());
        user.setText(model.getValueAt(selectRow,3).toString());
        pass.setText(model.getValueAt(selectRow,4).toString());
  
    }//GEN-LAST:event_TableMousePressed

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
    private javax.swing.JTable Table;
    private javax.swing.JButton delete;
    private javax.swing.JLabel getid;
    private javax.swing.JButton insert;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> level;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JComboBox<String> nama;
    private javax.swing.JTextField pass;
    private javax.swing.JTextField search;
    private javax.swing.JButton update;
    private javax.swing.JTextField user;
    // End of variables declaration//GEN-END:variables
}
