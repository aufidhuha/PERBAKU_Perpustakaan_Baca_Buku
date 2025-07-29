/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package latihanKu;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ASUS
 */
public class detailPinjamPanel extends javax.swing.JPanel {

    /**
     * Creates new form detailPinjamPanel
     */
    public detailPinjamPanel() {
        initComponents();
        txtCari.setText(null);
        data_pinjam();
        total();
        buttonCatatanPeminjaman.setBackground(new java.awt.Color(40, 167, 69));
    }
    
    private String isPinjamMode = "pinjam";
    
    void data_detail(){
        
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("ID Pinjam");
        model.addColumn("Nama Peminjam");
        model.addColumn("Nama Buku");
        model.addColumn("Tanggal Pinjam");
        model.addColumn("Tanggal Kembali");
        
        try {
            String sql = "SELECT detail_pinjam.id_pinjam, pinjam.nama, buku.nama_buku, detail_pinjam.tanggal_pinjam, detail_pinjam.tanggal_kembali FROM detail_pinjam JOIN pinjam ON pinjam.id_pinjam = detail_pinjam.id_pinjam JOIN buku ON detail_pinjam.id_buku = buku.id_buku";
            Connection cnVar = koneksi.getKoneksi();
            Statement stVar = cnVar.createStatement();
            ResultSet rsVar = stVar.executeQuery(sql);
            
            while (rsVar.next()) {                
                String idPinjam = rsVar.getString("id_pinjam");
                String nama = rsVar.getString("nama");
                String namaBuku = rsVar.getString("nama_buku");
                String tanggalPinjam = rsVar.getString("tanggal_pinjam");
                String tanggalKembali = rsVar.getString("tanggal_kembali");
                
                Object data[] = {idPinjam, nama, namaBuku, tanggalPinjam, tanggalKembali};
                model.addRow(data);
            }
            
            tableDetailPinjam.setModel(model);
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " +sQLException.getMessage());
        }
    }
    
    
  
    void cari_data_detail(){
        
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("ID Pinjam");
        model.addColumn("Nama Peminjam");
        model.addColumn("Nama Buku");
        model.addColumn("Tanggal Pinjam");
        model.addColumn("Tanggal Kembali");
        
        String cari = txtCari.getText();
        
        try {
            String sql = "SELECT detail_pinjam.id_pinjam, pinjam.nama, buku.nama_buku, detail_pinjam.tanggal_pinjam, detail_pinjam.tanggal_kembali FROM detail_pinjam JOIN pinjam ON pinjam.id_pinjam = detail_pinjam.id_pinjam JOIN buku ON detail_pinjam.id_buku = buku.id_buku WHERE detail_pinjam.id_pinjam LIKE ? OR pinjam.nama LIKE ? OR buku.nama_buku LIKE ? OR detail_pinjam.tanggal_pinjam LIKE ? OR detail_pinjam.tanggal_kembali LIKE ?";
            Connection cnVar = koneksi.getKoneksi();
            PreparedStatement psVar = cnVar.prepareStatement(sql);
            psVar.setString(1, "%" + cari + "%");
            psVar.setString(2, "%" + cari + "%");
            psVar.setString(3, "%" + cari + "%");
            psVar.setString(4, "%" + cari + "%");
            psVar.setString(5, "%" + cari + "%");
            ResultSet rsVar = psVar.executeQuery();
            
            while (rsVar.next()) {                
                String idPinjam = rsVar.getString("id_pinjam");
                String nama = rsVar.getString("nama");
                String namaBuku = rsVar.getString("nama_buku");
                String tanggalPinjam = rsVar.getString("tanggal_pinjam");
                String tanggalKembali = rsVar.getString("tanggal_kembali");
                
                Object data[] = {idPinjam, nama, namaBuku, tanggalPinjam, tanggalKembali};
                model.addRow(data);
            }
            
            tableDetailPinjam.setModel(model);
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " +sQLException.getMessage());
        }
    }
    
    
      void data_pinjam (){
        
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("ID Pinjam");
        model.addColumn("Nama Peminjam");
        model.addColumn("Tanggal Pinjam");
        model.addColumn("Tanggal Kembali");
        model.addColumn("Jumlah Pinjam");
        
        try {
            String sql = "SELECT * FROM pinjam ORDER BY id_pinjam ASC";
            Connection cnVar = koneksi.getKoneksi();
            Statement stVar = cnVar.createStatement();
            ResultSet rsVar = stVar.executeQuery(sql);
            
            while (rsVar.next()) {                
                String idPinjam = rsVar.getString("id_pinjam");
                String nama = rsVar.getString("nama");
                String tanggalPinjam = rsVar.getString("tanggal_pinjam");
                String tanggalKembali = rsVar.getString("tanggal_kembali");
                int jumlah = rsVar.getInt("jumlah");
                
                Object data[] = {idPinjam, nama, tanggalPinjam, tanggalKembali, jumlah};
                model.addRow(data);
            }
            
            tableDetailPinjam.setModel(model);
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " +sQLException.getMessage());
        }
    }
    
    
    
    void cari_data_pinjam(){
        
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("ID Pinjam");
        model.addColumn("Nama Peminjam");
        model.addColumn("Tanggal Pinjam");
        model.addColumn("Tanggal Kembali");
        model.addColumn("Jumlah Pinjam");
        
        String cari = txtCari.getText();
        
        try {
            String sql = "SELECT * FROM pinjam WHERE id_pinjam LIKE ? OR nama LIKE ? OR tanggal_pinjam LIKE ? OR tanggal_kembali LIKE ? OR jumlah LIKE ?";
            Connection cnVar = koneksi.getKoneksi();
            PreparedStatement psVar = cnVar.prepareStatement(sql);
            psVar.setString(1, "%" + cari + "%");
            psVar.setString(2, "%" + cari + "%");
            psVar.setString(3, "%" + cari + "%");
            psVar.setString(4, "%" + cari + "%");
            psVar.setString(5, "%" + cari + "%");
            ResultSet rsVar = psVar.executeQuery();
            
            while (rsVar.next()) {                
                String idPinjam = rsVar.getString("id_pinjam");
                String nama = rsVar.getString("nama");
                String tanggalPinjam = rsVar.getString("tanggal_pinjam");
                String tanggalKembali = rsVar.getString("tanggal_kembali");
                String jumlah = rsVar.getString("jumlah");
                
                Object data[] = {idPinjam, nama, tanggalPinjam, tanggalKembali, jumlah};
                model.addRow(data);
            }
            
            tableDetailPinjam.setModel(model);
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " +sQLException.getMessage());
        }
    }

    
    void total(){
        
        try {
            Connection cnVar = koneksi.getKoneksi();
            
            String sqlPeminjaman = "SELECT COUNT(*) AS total FROM pinjam";
            Statement stVarPeminjaman = cnVar.createStatement();
            ResultSet rsVar = stVarPeminjaman.executeQuery(sqlPeminjaman);
            
            if (rsVar.next()) {
                int total = rsVar.getInt("total");
                labelPeminjaman.setText(String.valueOf(total));
            }
            rsVar.close();
            stVarPeminjaman.close();
            
            String sqlBukuPinjam = "SELECT COUNT(*) AS total FROM detail_pinjam";
            Statement stVarBukuPinjam = cnVar.createStatement();
            ResultSet rsVarBukuPinjam = stVarBukuPinjam.executeQuery(sqlBukuPinjam);
            
            if (rsVarBukuPinjam.next()) {
                int total = rsVarBukuPinjam.getInt("total");
                labelPeminjamanBuku.setText(String.valueOf(total));
            }
            rsVarBukuPinjam.close();
            stVarBukuPinjam.close();
            
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

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDetailPinjam = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        labelPeminjaman = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labelPeminjamanBuku = new javax.swing.JLabel();
        buttonCatatanPeminjaman = new javax.swing.JButton();
        buttonCatatanDetail = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DAFTAR PEMINJAMAN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addContainerGap(803, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        tableDetailPinjam.setBackground(new java.awt.Color(255, 255, 255));
        tableDetailPinjam.setForeground(new java.awt.Color(0, 0, 0));
        tableDetailPinjam.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tableDetailPinjam);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("TOTAL PEMINJAMAN  :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("CARI ");

        txtCari.setBackground(new java.awt.Color(255, 255, 255));
        txtCari.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCari.setForeground(new java.awt.Color(0, 0, 0));
        txtCari.setText("jTextField1");
        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });

        labelPeminjaman.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelPeminjaman.setForeground(new java.awt.Color(0, 0, 0));
        labelPeminjaman.setText("jLabel4");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("TOTAL PEMINJAMAN BUKU  :");

        labelPeminjamanBuku.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelPeminjamanBuku.setForeground(new java.awt.Color(0, 0, 0));
        labelPeminjamanBuku.setText("jLabel4");

        buttonCatatanPeminjaman.setBackground(new java.awt.Color(102, 102, 102));
        buttonCatatanPeminjaman.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonCatatanPeminjaman.setForeground(new java.awt.Color(255, 255, 255));
        buttonCatatanPeminjaman.setText("CATATAN PEMINJAMAN");
        buttonCatatanPeminjaman.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        buttonCatatanPeminjaman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCatatanPeminjamanActionPerformed(evt);
            }
        });

        buttonCatatanDetail.setBackground(new java.awt.Color(102, 102, 102));
        buttonCatatanDetail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonCatatanDetail.setForeground(new java.awt.Color(255, 255, 255));
        buttonCatatanDetail.setText("CATATAN DETAIL PEMINJAMAN");
        buttonCatatanDetail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        buttonCatatanDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCatatanDetailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(39, 39, 39)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(buttonCatatanPeminjaman)
                            .addGap(18, 18, 18)
                            .addComponent(buttonCatatanDetail))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(labelPeminjaman)
                                .addGap(94, 94, 94)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(labelPeminjamanBuku))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCatatanDetail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCatatanPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(labelPeminjamanBuku))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(labelPeminjaman)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:    
        
        if (isPinjamMode.equals("pinjam")) {
            cari_data_pinjam();  
        } else {
            cari_data_detail();
        }           
            
    }//GEN-LAST:event_txtCariActionPerformed

    private void buttonCatatanDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCatatanDetailActionPerformed
        // TODO add your handling code here:
        isPinjamMode = "detail";
        buttonCatatanPeminjaman.setBackground(new java.awt.Color(102,102,102));
        buttonCatatanDetail.setBackground(new java.awt.Color(40, 167, 69));
        data_detail();
    }//GEN-LAST:event_buttonCatatanDetailActionPerformed

    private void buttonCatatanPeminjamanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCatatanPeminjamanActionPerformed
        // TODO add your handling code here:
        isPinjamMode = "pinjam";
        buttonCatatanPeminjaman.setBackground(new java.awt.Color(40, 167, 69));
        buttonCatatanDetail.setBackground(new java.awt.Color(102,102,102));
        data_pinjam();
    }//GEN-LAST:event_buttonCatatanPeminjamanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCatatanDetail;
    private javax.swing.JButton buttonCatatanPeminjaman;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelPeminjaman;
    private javax.swing.JLabel labelPeminjamanBuku;
    private javax.swing.JTable tableDetailPinjam;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables
}
