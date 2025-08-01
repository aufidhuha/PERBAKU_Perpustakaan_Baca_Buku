/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package latihanKu;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
/**
 *
 * @author ASUS
 */
public class cetakFrame extends javax.swing.JFrame {

    /**
     * Creates new form cetakFrame
     */
    public cetakFrame() {
        initComponents();
        dataCetak();
        setLocation(250, 75);
       
    }
    
   
    void dataCetak(){
        
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("ID Buku");
        model.addColumn("Nama Buku");
        
        String IDPinjamVar = "";
        String namaPeminjam = "";
        String tanggalPinjam = "";
        String tanggalKembali = "";
        int jumlah = 0;
        
        try {
            Connection cnVar = koneksi.getKoneksi();
            
            String sql = "SELECT * FROM pinjam ORDER BY id_pinjam DESC LIMIT 1";
            Statement stVarPinjam = cnVar.createStatement();
            ResultSet rsVarPinjam = stVarPinjam.executeQuery(sql);
            
            if (rsVarPinjam.next()) {
                IDPinjamVar = rsVarPinjam.getString("id_pinjam");
                namaPeminjam = rsVarPinjam.getString("nama");
                tanggalPinjam = rsVarPinjam.getString("tanggal_pinjam");
                tanggalKembali = rsVarPinjam.getString("tanggal_kembali");
                jumlah = rsVarPinjam.getInt("jumlah");
            }
            
            labelIDPinjam.setText(IDPinjamVar);
            labelNamaPeminjam.setText(namaPeminjam);
            labelTanggalPeminjam.setText(tanggalPinjam);
            labelTanggalKembali.setText(tanggalKembali);
            labelTotalPinjam.setText(String.valueOf(jumlah) + "  Buku");
            
            
            String sqlBuku = "SELECT buku.id_buku, buku.nama_buku FROM detail_pinjam JOIN buku ON detail_pinjam.id_buku = buku.id_buku WHERE detail_pinjam.id_pinjam = ?";
            PreparedStatement psVarBuku = cnVar.prepareStatement(sqlBuku);
            psVarBuku.setString(1, labelIDPinjam.getText());
            ResultSet rsVarBuku = psVarBuku.executeQuery();
            
            while (rsVarBuku.next()) {                
                String idBuku = rsVarBuku.getString("id_buku");
                String namaBuku = rsVarBuku.getString("nama_buku");
                
                Object data[] = {idBuku, namaBuku};
                model.addRow(data);
            }
            
            tabelCetak.setModel(model);
            
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        cetakPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelCetak = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        labelTotalPinjam = new javax.swing.JLabel();
        labelIDPinjam = new javax.swing.JLabel();
        labelNamaPeminjam = new javax.swing.JLabel();
        labelTanggalPeminjam = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        labelTanggalKembali = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        buttonCetak = new javax.swing.JButton();
        buttonKembali = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        cetakPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tanggal Pinjam      : ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Tanggal Kembali    :");

        tabelCetak.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelCetak);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("TOTAL PINJAM   :");

        labelTotalPinjam.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelTotalPinjam.setForeground(new java.awt.Color(0, 0, 0));
        labelTotalPinjam.setText("0");

        labelIDPinjam.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelIDPinjam.setForeground(new java.awt.Color(0, 0, 0));
        labelIDPinjam.setText("Label ID Pinjam");

        labelNamaPeminjam.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelNamaPeminjam.setForeground(new java.awt.Color(0, 0, 0));
        labelNamaPeminjam.setText("Label Nama Peminjam");

        labelTanggalPeminjam.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelTanggalPeminjam.setForeground(new java.awt.Color(0, 0, 0));
        labelTanggalPeminjam.setText("Label Tanggal Pinjam");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/latihanKu/image/icons8-book-32 (1).png"))); // NOI18N
        jLabel1.setText("PERBAKU");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("PERPUSTAKAAN BACA BUKU");

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Kecamatan Durian Kota Apel");

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Jalan Soekarno-Hatta No. 78 Suryahardjo");

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("085543890127");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("ID Pinjam                : ");

        labelTanggalKembali.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelTanggalKembali.setForeground(new java.awt.Color(0, 0, 0));
        labelTanggalKembali.setText("Label Tanggal Kembali");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Nama Peminjam    : ");

        javax.swing.GroupLayout cetakPanelLayout = new javax.swing.GroupLayout(cetakPanel);
        cetakPanel.setLayout(cetakPanelLayout);
        cetakPanelLayout.setHorizontalGroup(
            cetakPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cetakPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cetakPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(cetakPanelLayout.createSequentialGroup()
                        .addGroup(cetakPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cetakPanelLayout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addGroup(cetakPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(cetakPanelLayout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addComponent(jLabel1))
                                    .addGroup(cetakPanelLayout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(jLabel3))
                                    .addGroup(cetakPanelLayout.createSequentialGroup()
                                        .addGap(63, 63, 63)
                                        .addComponent(jLabel5))
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)))
                            .addGroup(cetakPanelLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(cetakPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(cetakPanelLayout.createSequentialGroup()
                                        .addGroup(cetakPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(cetakPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelIDPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(labelNamaPeminjam, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(labelTanggalPeminjam, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(labelTanggalKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(cetakPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelTotalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)))
                .addContainerGap())
        );
        cetakPanelLayout.setVerticalGroup(
            cetakPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cetakPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cetakPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(labelIDPinjam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cetakPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(labelNamaPeminjam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cetakPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(labelTanggalPeminjam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cetakPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(labelTanggalKembali))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(cetakPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTotalPinjam)
                    .addComponent(jLabel10))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        buttonCetak.setBackground(new java.awt.Color(0, 123, 255));
        buttonCetak.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonCetak.setForeground(new java.awt.Color(255, 255, 255));
        buttonCetak.setText("CETAK");
        buttonCetak.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCetakActionPerformed(evt);
            }
        });

        buttonKembali.setBackground(new java.awt.Color(211, 47, 47));
        buttonKembali.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonKembali.setForeground(new java.awt.Color(255, 255, 255));
        buttonKembali.setText("KEMBALI");
        buttonKembali.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonKembaliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(cetakPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(buttonKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(buttonCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(cetakPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCetakActionPerformed
        // TODO add your handling code here:
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Cetak Struk");
        
        PageFormat pageFormat = job.defaultPage();
        Paper paper = new Paper();
        
        double width = 226.77;
        double heigth = 814.89;
        
        paper.setSize(width, heigth);
        paper.setImageableArea(10, 10, width - 20, heigth - 20);
        pageFormat.setPaper(paper);
        
        job.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                
                if (pageIndex > 0) {
                    return  NO_SUCH_PAGE;
                }                
                
                double panelWidth = cetakPanel.getWidth();
                double panetlHeight = cetakPanel.getHeight();
                
                double printWidth = pageFormat.getImageableWidth();
                double printHeight = pageFormat.getImageableHeight();
                
                double scaleX = printWidth / panelWidth;
                double scaleY = printHeight / panetlHeight;
                double scale = Math.min(scaleX, scaleY);
                
                Graphics2D gp2 = (Graphics2D) graphics;
                gp2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                gp2.scale(scale, scale);
                
                cetakPanel.printAll(gp2);
                
                return  PAGE_EXISTS;
            }
        }, pageFormat);
        
        boolean printOk = job.printDialog();
        if (printOk) {
            try {
                job.print();
            } catch (PrinterException printerException) {
                JOptionPane.showMessageDialog(null, "Error : " + printerException.getMessage());
            }
        }
    }//GEN-LAST:event_buttonCetakActionPerformed

    private void buttonKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonKembaliActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_buttonKembaliActionPerformed

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
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cetakFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCetak;
    private javax.swing.JButton buttonKembali;
    private javax.swing.JPanel cetakPanel;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelIDPinjam;
    private javax.swing.JLabel labelNamaPeminjam;
    private javax.swing.JLabel labelTanggalKembali;
    private javax.swing.JLabel labelTanggalPeminjam;
    private javax.swing.JLabel labelTotalPinjam;
    private javax.swing.JTable tabelCetak;
    // End of variables declaration//GEN-END:variables
}
