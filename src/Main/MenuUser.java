/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Main;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import kepegawaian.Selecteventmenu;
/**
 *
 * @author Axel
 */
public class MenuUser extends javax.swing.JFrame {

    /**
     * Creates new form MenuUser
     */
    public MenuUser() {
        initComponents();
        menuUser1.initMoving(this);
        menuUser1.addevent(new Selecteventmenu() {
            @Override
            public void selected(int index) {
                if (index==0) {
                    setform(new absensi1());
                }else if (index==1) {
                    setform(new Presensi1());
                }else if (index==3) {
                   int opt = JOptionPane.showConfirmDialog(null, "Logout?","Logout",JOptionPane.YES_NO_OPTION);
                    if (opt==0) {
                        try {
                              login ne = new login();
                    ne.show();
                    dispose(); 
                    JOptionPane.showMessageDialog(null, "Berhasil Logout");
                        } catch (Exception e) {
                        }
  
                    }
                }else if (index==4) {
                    int opt = JOptionPane.showConfirmDialog(null, "Keluar?","Keluar",JOptionPane.YES_NO_OPTION);
                    if (opt==0) {
                        try {
                              System.exit(0);
                            } catch (Exception e) {
                        }
  
                    }
                }
            }
        });
    }

    private void setform(JComponent com){
    mainpanel.removeAll();
mainpanel.add(com);
mainpanel.repaint();
mainpanel.revalidate();
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        menuUser1 = new prop.Menuuser();
        mainpanel = new javax.swing.JPanel();

        jToolBar1.setRollover(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        mainpanel.setBackground(new java.awt.Color(255, 255, 255));
        mainpanel.setOpaque(false);
        mainpanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuUser1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(mainpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(menuUser1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(mainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(MenuUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel mainpanel;
    private prop.Menuuser menuUser1;
    // End of variables declaration//GEN-END:variables
}
