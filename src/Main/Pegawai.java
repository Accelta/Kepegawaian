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
import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import static java.lang.System.err;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import javax.swing.RowFilter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.table.TableCellRenderer;


/**
 *
 * @author Axel
 */
public class Pegawai extends javax.swing.JPanel {

    String sql;
    Statement stat;
    ResultSet res;
    Koneksi cn = new Koneksi();
    DefaultTableModel model = new DefaultTableModel();
    TableModel tableModel;
    String filename = null;
    byte[] person_img = null;
Blob image;
    public Pegawai() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        Table.getTableHeader().setOpaque(false);
        Table.getTableHeader().setBackground(Color.MAGENTA);
        Table.getTableHeader().setFont(new Font("Montserrat Semibold", Font.PLAIN, 11));
        Table.getTableHeader().setForeground(new Color(0, 0, 0));
        Table.setRowHeight(25);
        show();
        divisi();
        kantor();
        getkel.setVisible(false);
        getid.setVisible(false);
    }

    @Override
    public void show() {
        try {
            model = (DefaultTableModel) Table.getModel();
            stat = cn.login().createStatement();
            sql = "SELECT * FROM  pegawai";
            res = stat.executeQuery(sql);
            bersihtabel();
            while (res.next()) {

                Object[] obj = new Object[12];
                obj[0] = res.getString("id_pgw");
                obj[1] = res.getString("nama");
                obj[2] = res.getString("alamat");
                obj[3] = res.getString("tempat_lahir");
                obj[4] = res.getString("tgl_lahir");
                obj[5] = res.getString("kelamin");
                obj[6] = res.getString("agama");
                obj[7] = res.getString("nope");
                obj[8] = res.getString("email");
                obj[9] = res.getString("nm_divisi");
                obj[10] = res.getString("id_kantor");
                obj[11] = res.getBlob("foto");
                model.addRow(obj);
            }
        } catch (Exception e) {
        }
    }


    public void bersihtabel() {
        while (model.getRowCount() > 0) {
            model.setRowCount(0);
        }
    }

    public void search() {
        DefaultTableModel model = (DefaultTableModel) Table.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        Table.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search.getText().trim()));

    }

    public void divisi() {
        try {
            stat = cn.login().createStatement();
            sql = "SELECT * FROM divisi";
            res = stat.executeQuery(sql);
            while (res.next()) {
                divisi.addItem(res.getString("nm_divisi"));

            }
        } catch (Exception e) {
        }

    }

    public void kantor() {
        try {
            stat = cn.login().createStatement();
            sql = "SELECT * FROM kantor";
            res = stat.executeQuery(sql);
            while (res.next()) {
                kantor.addItem(res.getString("id_kantor"));

            }
        } catch (Exception e) {
        }

    }

    String selectbutton() {
        for (Enumeration<AbstractButton> buttons = Groupkel.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    public void insert() {
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String date = String.valueOf(fm.format(tgllahir.getDate()));
        try {
            stat = cn.login().createStatement();
            sql = "INSERT INTO pegawai (id_pgw, nama, alamat, tempat_lahir, tgl_lahir, kelamin, agama, nope, email, nm_divisi, id_kantor, foto)" + "VALUES('" + idpeg.getText() + "','" + nama.getText() + "','" + alamat.getText() + "','" + tlahir.getText() + "','" + date + "','" + selectbutton() + "','" + agama.getText() + "','" + nope.getText() + "','" + email.getText() + "','" + divisi.getSelectedItem() + "','" + kantor.getSelectedItem() + "','" + foto.getText()+ "') ";
            stat.execute(sql);
            bersihtabel();
            JOptionPane.showMessageDialog(null, "berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal" + e);
        }
    }

    public void delete() {
        int opt = JOptionPane.showConfirmDialog(null, "Yakin mau dihapus?", "hapus", JOptionPane.YES_NO_OPTION);
        if (opt == 0) {
            try {
                stat = cn.login().createStatement();
                sql = "DELETE FROM pegawai WHERE id_pgw= '" + idpeg.getText() + "'";

                stat.executeUpdate(sql);
                bersihtabel();
                JOptionPane.showMessageDialog(null, "data dihapus");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "error");
            }
        }
    }

    public void update() {
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String date = String.valueOf(fm.format(tgllahir.getDate()));
        String id1 = getid.getText();
        ResultSet ra;
        int opt = JOptionPane.showConfirmDialog(null, "Update?", "update", JOptionPane.YES_NO_OPTION);
        if (opt == 0) {
            try {
                stat = cn.login().createStatement();
                sql = "SELECT id_pgw from pegawai WHERE id_pgw='" + id1 + "'";
                ra = stat.executeQuery(sql);
                while (ra.next()) {
                    String id = ra.getString("id_pgw");
                    sql = "UPDATE pegawai SET id_pgw ='" + idpeg.getText() + "', nama='" + nama.getText() + "', alamat='" + alamat.getText() + "', tempat_lahir='" + tlahir.getText() + "', tgl_lahir='" + date + "', kelamin='" + selectbutton() + "', agama='" + agama.getText() + "', nope='" + nope.getText() + "', email='" + email.getText() + "', nm_divisi='" + divisi.getSelectedItem() + "', id_kantor='" + kantor.getSelectedItem() + "', foto='" +foto.getText()+ "' WHERE id_pgw ='" + id + "'";
                }
                stat.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "data di update");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Gagal" + e);
            }
        }
    }

    public void selectpic() {
        JFileChooser pilih = new JFileChooser();
        pilih.showOpenDialog(null);
        File f = pilih.getSelectedFile();
        filename = f.getAbsolutePath();
        foto.setText(filename);
        ImageIcon imgag = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(framefoto.getWidth(), 
                framefoto.getHeight(), Image.SCALE_SMOOTH));
        framefoto.setIcon(imgag);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Groupkel = new javax.swing.ButtonGroup();
        mainpanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        insert = new javax.swing.JButton();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        idpeg = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tlahir = new javax.swing.JTextField();
        alamat = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        KelL = new javax.swing.JRadioButton();
        kelP = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        agama = new javax.swing.JTextField();
        nope = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        divisi = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        kantor = new javax.swing.JComboBox<>();
        foto = new javax.swing.JTextField();
        getkel = new javax.swing.JLabel();
        getid = new javax.swing.JLabel();
        tgllahir = new com.toedter.calendar.JDateChooser();
        framefoto = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        search = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(607, 738));
        setPreferredSize(new java.awt.Dimension(738, 607));

        mainpanel.setBackground(new java.awt.Color(255, 255, 255));
        mainpanel.setPreferredSize(new java.awt.Dimension(725, 656));

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nama", "Alamat", " Tempat Lahir", "Tanggal Lahir", "Kelamin", "Agama", "Nope", "Email", "Divisi", "Id Kantor", "Foto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
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
        jLabel1.setText("LIST PEGAWAI");

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

        jLabel5.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N
        jLabel5.setText("Tempat lahir :");

        jLabel6.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N
        jLabel6.setText("Alamat :");

        jLabel7.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N
        jLabel7.setText("Tanggal Lahir :");

        Groupkel.add(KelL);
        KelL.setText("L");

        Groupkel.add(kelP);
        kelP.setText("P");

        jLabel8.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N
        jLabel8.setText("Kelamin :");

        jLabel9.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N
        jLabel9.setText("Agama :");

        jLabel10.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N
        jLabel10.setText("Nope :");

        jLabel11.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N
        jLabel11.setText("Email :");

        jLabel12.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N
        jLabel12.setText("Id Kantor :");

        jLabel13.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N
        jLabel13.setText("Foto :");

        jLabel14.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 13)); // NOI18N
        jLabel14.setText("Divisi :");

        getkel.setText("_");

        getid.setText("_");

        tgllahir.setDateFormatString("yyyy-MM-dd");

        framefoto.setBackground(new java.awt.Color(255, 255, 255));
        framefoto.setForeground(new java.awt.Color(51, 51, 255));
        framefoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        framefoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                none(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Geometr212 BkCn BT", 0, 12)); // NOI18N
        jButton1.setText("Pilih Gambar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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
                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(mainpanelLayout.createSequentialGroup()
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(mainpanelLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(61, 61, 61)
                                        .addComponent(alamat, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(mainpanelLayout.createSequentialGroup()
                                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3))
                                        .addGap(37, 37, 37)
                                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(idpeg, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(mainpanelLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(27, 27, 27)
                                        .addComponent(tlahir, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(mainpanelLayout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(tgllahir, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(mainpanelLayout.createSequentialGroup()
                                        .addGap(73, 73, 73)
                                        .addComponent(agama, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(mainpanelLayout.createSequentialGroup()
                                        .addGap(78, 78, 78)
                                        .addComponent(KelL)
                                        .addGap(10, 10, 10)
                                        .addComponent(kelP))
                                    .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9)
                                            .addGroup(mainpanelLayout.createSequentialGroup()
                                                .addGap(71, 71, 71)
                                                .addComponent(nope, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel10))
                                        .addGroup(mainpanelLayout.createSequentialGroup()
                                            .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel11)
                                                .addComponent(jLabel14))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(divisi, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainpanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(getkel, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(getid, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainpanelLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(mainpanelLayout.createSequentialGroup()
                                            .addComponent(jLabel13)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(foto, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(mainpanelLayout.createSequentialGroup()
                                            .addComponent(jLabel12)
                                            .addGap(18, 18, 18)
                                            .addComponent(kantor, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(framefoto, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainpanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(insert)
                .addGap(18, 18, 18)
                .addComponent(update)
                .addGap(18, 18, 18)
                .addComponent(delete)
                .addGap(159, 159, 159)
                .addComponent(jButton1)
                .addGap(82, 82, 82))
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
                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainpanelLayout.createSequentialGroup()
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(KelL)
                                    .addComponent(kelP)
                                    .addComponent(jLabel8))
                                .addGap(16, 16, 16)
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(agama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(nope, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(divisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)))
                            .addGroup(mainpanelLayout.createSequentialGroup()
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(idpeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(alamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(tlahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(tgllahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(mainpanelLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(kantor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(mainpanelLayout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(jLabel13))
                                    .addGroup(mainpanelLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(foto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(17, 17, 17)
                                .addComponent(framefoto, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(46, 46, 46)
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(insert)
                            .addComponent(update)
                            .addComponent(delete)
                            .addComponent(jButton1)))
                    .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(getkel)
                        .addComponent(getid)))
                .addContainerGap(137, Short.MAX_VALUE))
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

    private void TableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMousePressed
        int selectRow = Table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) Table.getModel();
        idpeg.setText(model.getValueAt(selectRow, 0).toString());
        getid.setText(model.getValueAt(selectRow, 0).toString());
        nama.setText(model.getValueAt(selectRow, 1).toString());
        alamat.setText(model.getValueAt(selectRow, 2).toString());
        tlahir.setText(model.getValueAt(selectRow, 3).toString());
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(selectRow, 4));
            tgllahir.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(Pegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
        getkel.setText(model.getValueAt(selectRow, 5).toString());
        String a = getkel.getText();
        if (a.equals("L")) {
            KelL.setSelected(true);
        } else {
            kelP.setSelected(true);
        }
        agama.setText(model.getValueAt(selectRow, 6).toString());
        nope.setText(model.getValueAt(selectRow, 7).toString());
        email.setText(model.getValueAt(selectRow, 8).toString());
        divisi.setSelectedItem(model.getValueAt(selectRow, 9).toString());
        kantor.setSelectedItem(model.getValueAt(selectRow, 10).toString());
        foto.setText(model.getValueAt(selectRow, 11).toString());
//        try {
//            stat = cn.login().createStatement();
//            sql="SELECT foto FROM pegawai where id_pgw='"+getid.getText()+"'";
//            res = stat.executeQuery(sql);
//            if (res.next()) {
//             image = res.getBlob(1);
//             BufferedImage im = ImageIO.read(res.getBinaryStream("foto"));
//             person_img = image.getBytes(1, (int) image.length());
//             framefoto.setIcon(new ImageIcon(person_img));
//             
//            }else{
//            JOptionPane.showMessageDialog(null, "error");
//            return;
//            }
//          
//            
//        } catch (Exception e) {
//             JOptionPane.showMessageDialog(null, e);
//        }

    }//GEN-LAST:event_TableMousePressed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        delete();
        show();// TODO add your handling code here:
    }//GEN-LAST:event_deleteActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        update();
        show();// TODO add your handling code here:
    }//GEN-LAST:event_updateActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        selectpic();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void none(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_none

    }//GEN-LAST:event_none

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
    private javax.swing.ButtonGroup Groupkel;
    private javax.swing.JRadioButton KelL;
    private javax.swing.JTable Table;
    private javax.swing.JTextField agama;
    private javax.swing.JTextField alamat;
    private javax.swing.JButton delete;
    private javax.swing.JComboBox<String> divisi;
    private javax.swing.JTextField email;
    private javax.swing.JTextField foto;
    private javax.swing.JLabel framefoto;
    private javax.swing.JLabel getid;
    private javax.swing.JLabel getkel;
    private javax.swing.JTextField idpeg;
    private javax.swing.JButton insert;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> kantor;
    private javax.swing.JRadioButton kelP;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JTextField nama;
    private javax.swing.JTextField nope;
    private javax.swing.JTextField search;
    private com.toedter.calendar.JDateChooser tgllahir;
    private javax.swing.JTextField tlahir;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
