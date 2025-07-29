/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package latihanKu;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
/**
 *
 * @author ASUS
 */
public class bukuPanel extends javax.swing.JPanel {

    /**
     * Creates new form bukuPanel
     */
    public bukuPanel() {
        initComponents();
        data_buku();
        reset();
        comboKategori();
    }
    
    void data_buku(){
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("ID Buku");
        model.addColumn("Nama Buku");
        model.addColumn("Kategori Buku");
        model.addColumn("Nama Penerbit");
        model.addColumn("Tanggal Terbit");
        
        try {
            String sql = "SELECT buku.id_buku, buku.nama_buku, kategori.nama_kategori, buku.penerbit, buku.tanggal_terbit FROM buku JOIN kategori ON buku.kategori_buku = kategori.id_kategori ORDER BY buku.id_buku ASC";
            Connection cnVar = koneksi.getKoneksi();
            
            Statement stVar = cnVar.createStatement();
            ResultSet rsVar = stVar.executeQuery(sql);
            
            while (rsVar.next()) {                
                String idBuku = rsVar.getString("id_buku");
                String namaBuku = rsVar.getString("nama_buku");
                String kategori = rsVar.getString("nama_kategori");
                String penerbit = rsVar.getString("penerbit");
                String tanggal = rsVar.getString("tanggal_terbit");
                
                Object data[] = {idBuku,namaBuku,kategori,penerbit,tanggal};
                model.addRow(data);
            }
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
            return;
        }
        
        tabelBuku.setModel(model);
    }
    
    
    void cari_buku(){
        
            DefaultTableModel model = (DefaultTableModel) tabelBuku.getModel();
            String cari = txtCari.getText();
            model.setRowCount(0);
            
        try {
            
            String sqlCari = "SELECT buku.id_buku, buku.nama_buku, kategori.nama_kategori, buku.penerbit, buku.tanggal_terbit FROM buku JOIN kategori ON buku.kategori_buku = kategori.id_kategori WHERE buku.id_buku LIKE ? OR buku.nama_buku LIKE ? OR kategori.nama_kategori LIKE ? OR buku.penerbit LIKE ? ORDER BY buku.id_buku ASC";
            
            Connection cnVar = koneksi.getKoneksi();
            
            PreparedStatement psVar = cnVar.prepareStatement(sqlCari);
            psVar.setString(1, "%" + cari + "%");
            psVar.setString(2, "%" + cari + "%");
            psVar.setString(3, "%" + cari + "%");
            psVar.setString(4, "%" + cari + "%");
            ResultSet rsVar = psVar.executeQuery();
            
            while (rsVar.next()) {                
                String idBuku = rsVar.getString("id_buku");
                String namaBuku = rsVar.getString("nama_buku");
                String kategori = rsVar.getString("nama_kategori");
                String penerbit = rsVar.getString("penerbit");
                String tanggal = rsVar.getString("tanggal_terbit");
                
                Object data[] = {idBuku,namaBuku,kategori,penerbit,tanggal};
                model.addRow(data);
            }           
        
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
    }
    

    void reset(){
        txtNamaBuku.setText(null);
        txtNamaPenerbit.setText(null);
        cbKategoriBuku.setSelectedItem(null);
        JDateTanggalTerbit.setDate(null);
        txtCari.setText(null);
        auto_id();
        buttonSimpan.setText("SIMPAN");
    }
    
    void comboKategori(){
        
        try {
            String sql = "SELECT * FROM kategori";
            
            Connection cnVar = koneksi.getKoneksi();
            Statement stVar = cnVar.createStatement();
            
            ResultSet rsVar = stVar.executeQuery(sql);
            cbKategoriBuku.removeAllItems();
            
            while (rsVar.next()) {                
                cbKategoriBuku.addItem(rsVar.getString("nama_kategori"));
            }
            
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        cbKategoriBuku.setSelectedItem(null);
    }
    
    
    String kodeKategori(String kategori){
        
        try {
            String sql = "SELECT id_kategori FROM kategori WHERE nama_kategori = ?";
            
            Connection cnVar = koneksi.getKoneksi();
            PreparedStatement psVar = cnVar.prepareStatement(sql);
            
            psVar.setString(1, kategori);
            ResultSet rsVar = psVar.executeQuery();
            
            while (rsVar.next()) {                
                return rsVar.getString("id_kategori");
            }
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
            return "";
        }
        
        return "";
    }
    
    
    private void auto_id(){
        
        try {
            Connection cnVar = koneksi.getKoneksi();
            String sql = "SELECT * FROM buku ORDER BY id_buku DESC";
            Statement stVar = cnVar.createStatement();
            ResultSet rsVar = stVar.executeQuery(sql);
            
            if (rsVar.next()) {
                String NoFaktur = rsVar.getString("id_buku").substring(4);
                String transaksi =  "" + (Integer.parseInt(NoFaktur) + 1);
                String nol = "";
                
                if (transaksi.length() == 1) {
                    nol = "00";
                } else if (transaksi.length() == 2) {
                    nol = "0";
                } else if (transaksi.length() == 3) {
                    nol = "";
                } 
                
                txtIDBuku.setText("IDBK" + nol + transaksi);
            } else {
                txtIDBuku.setText("IDBK001");
            }
            
            rsVar.close();
            stVar.close();
            
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Auto Number Transaksi Error " + sQLException.getMessage());
        }
        txtIDBuku.setEditable(false);
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
        jLabel2 = new javax.swing.JLabel();
        txtIDBuku = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNamaBuku = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNamaPenerbit = new javax.swing.JTextField();
        cbKategoriBuku = new javax.swing.JComboBox<>();
        JDateTanggalTerbit = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        buttonSimpan = new javax.swing.JButton();
        buttonHapus = new javax.swing.JButton();
        buttonBatal = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelBuku = new javax.swing.JTable();
        txtCari = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DAFTAR BUKU");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addContainerGap(852, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("ID BUKU");

        txtIDBuku.setBackground(new java.awt.Color(232, 232, 232));
        txtIDBuku.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtIDBuku.setForeground(new java.awt.Color(0, 0, 0));
        txtIDBuku.setText("jTextField1");
        txtIDBuku.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("NAMA BUKU");

        txtNamaBuku.setBackground(new java.awt.Color(232, 232, 232));
        txtNamaBuku.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNamaBuku.setForeground(new java.awt.Color(0, 0, 0));
        txtNamaBuku.setText("jTextField1");
        txtNamaBuku.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("KATEGORI BUKU");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("CARI");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("TANGGAL TERBIT");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("NAMA PENERBIT");

        txtNamaPenerbit.setBackground(new java.awt.Color(232, 232, 232));
        txtNamaPenerbit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNamaPenerbit.setForeground(new java.awt.Color(0, 0, 0));
        txtNamaPenerbit.setText("jTextField1");
        txtNamaPenerbit.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        cbKategoriBuku.setBackground(new java.awt.Color(232, 232, 232));
        cbKategoriBuku.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbKategoriBuku.setForeground(new java.awt.Color(0, 0, 0));
        cbKategoriBuku.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        JDateTanggalTerbit.setBackground(new java.awt.Color(232, 232, 232));
        JDateTanggalTerbit.setForeground(new java.awt.Color(0, 0, 0));
        JDateTanggalTerbit.setDateFormatString("yyyy-MM-dd");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TOMBOL", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        buttonSimpan.setBackground(new java.awt.Color(40, 167, 69));
        buttonSimpan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonSimpan.setForeground(new java.awt.Color(255, 255, 255));
        buttonSimpan.setText("SIMPAN");
        buttonSimpan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSimpanActionPerformed(evt);
            }
        });

        buttonHapus.setBackground(new java.awt.Color(220, 53, 69));
        buttonHapus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonHapus.setForeground(new java.awt.Color(255, 255, 255));
        buttonHapus.setText("HAPUS");
        buttonHapus.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHapusActionPerformed(evt);
            }
        });

        buttonBatal.setBackground(new java.awt.Color(108, 117, 125));
        buttonBatal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonBatal.setForeground(new java.awt.Color(255, 255, 255));
        buttonBatal.setText("BATAL");
        buttonBatal.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBatalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(buttonSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(buttonHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(buttonBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        tabelBuku.setBackground(new java.awt.Color(255, 255, 255));
        tabelBuku.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabelBuku.setForeground(new java.awt.Color(0, 0, 0));
        tabelBuku.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelBukuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelBuku);

        txtCari.setBackground(new java.awt.Color(232, 232, 232));
        txtCari.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCari.setForeground(new java.awt.Color(0, 0, 0));
        txtCari.setText("jTextField1");
        txtCari.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(txtIDBuku)
                            .addComponent(jLabel3)
                            .addComponent(txtNamaBuku, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                            .addComponent(cbKategoriBuku, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(txtNamaPenerbit, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                            .addComponent(JDateTanggalTerbit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCari))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(54, 54, 54))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(txtIDBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(txtNamaBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel4)
                            .addGap(18, 18, 18)
                            .addComponent(cbKategoriBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addGap(18, 18, 18)
                            .addComponent(txtNamaPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(JDateTanggalTerbit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel5)
                            .addGap(18, 18, 18)
                            .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSimpanActionPerformed
        // TODO add your handling code here:
        
        String idBuku = txtIDBuku.getText();
        String namaBuku = txtNamaBuku.getText();
        String kategori = kodeKategori(cbKategoriBuku.getSelectedItem().toString());
        String penerbit = txtNamaPenerbit.getText();
        Date tanggalDate = (Date) JDateTanggalTerbit.getDate();
        String tanggal = new SimpleDateFormat("yyyy-MM-dd").format(tanggalDate);
        
        // Date tanggalDate = JDateTanggalTerbit.getDate();
        // java.sql.Date tanggal = new java.sql.Date(tanggalDate.getTime());
        
        if (idBuku.isBlank() || namaBuku.isBlank() || kategori.isBlank() || penerbit.isBlank() || tanggal == null) {
            JOptionPane.showMessageDialog(null, "Harap memilih data atau mengisi data secara keseluruhan");
            return;
        }
        
        try {
            String sqlCheck = "SELECT * FROM buku WHERE id_buku = ?";
            Connection cnVar = koneksi.getKoneksi();
            PreparedStatement psVar = cnVar.prepareStatement(sqlCheck);
            
            psVar.setString(1, idBuku);
            ResultSet rsVar = psVar.executeQuery();
            
            if (!rsVar.next()) {
                
                String sqlInsert = "INSERT INTO buku VALUES (?, ?, ?, ?, ?)";
                PreparedStatement psVarInsert = cnVar.prepareStatement(sqlInsert);
                
                psVarInsert.setString(1, idBuku);
                psVarInsert.setString(2, namaBuku);
                psVarInsert.setString(3, kategori);
                psVarInsert.setString(4, penerbit);
                psVarInsert.setString(5, tanggal);
                psVarInsert.execute();
                
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
                psVarInsert.close();
                
            } else {
                String sqlUpdate = "UPDATE buku SET nama_buku = ?, kategori_buku = ?, penerbit = ?, tanggal_terbit = ? WHERE id_buku = ?";
                PreparedStatement psVarUpdate = cnVar.prepareStatement(sqlUpdate);
                
                psVarUpdate.setString(1, namaBuku);
                psVarUpdate.setString(2, kategori);
                psVarUpdate.setString(3, penerbit);
                psVarUpdate.setString(4, tanggal);
                psVarUpdate.setString(5, idBuku);
                psVarUpdate.execute();
                
                JOptionPane.showMessageDialog(null, "Data berhasil diubah");
                psVarUpdate.close();
            }
            
            rsVar.close();
            psVar.close();
            cnVar.close();
            
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        
        reset();
        data_buku();
    }//GEN-LAST:event_buttonSimpanActionPerformed

    private void tabelBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBukuMouseClicked
        // TODO add your handling code here:
        try {
            int choiceRow = tabelBuku.getSelectedRow();

            String idBuku = tabelBuku.getValueAt(choiceRow, 0).toString();
            String namaBuku = tabelBuku.getValueAt(choiceRow, 1).toString();
            String kategori = tabelBuku.getValueAt(choiceRow, 2).toString();
            String penerbit = tabelBuku.getValueAt(choiceRow, 3).toString();
            String tanggal = tabelBuku.getValueAt(choiceRow, 4).toString();

            txtIDBuku.setText(idBuku);
            txtNamaBuku.setText(namaBuku);
            cbKategoriBuku.setSelectedItem(kategori);
            txtNamaPenerbit.setText(penerbit);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(tanggal);
            JDateTanggalTerbit.setDate(date);
            
            buttonSimpan.setText("UBAH");
            
        } catch (ParseException parseException) {
            JOptionPane.showMessageDialog(null, "Error : " + parseException.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
    }//GEN-LAST:event_tabelBukuMouseClicked

    private void buttonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHapusActionPerformed
        // TODO add your handling code here:
        String idBuku = txtIDBuku.getText();
        
        int yesOrNo = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if (yesOrNo == JOptionPane.YES_OPTION) {
            
            try {
                String sqlDelete = "DELETE FROM buku WHERE id_buku = ?";
                Connection cnVar = koneksi.getKoneksi();
                
                PreparedStatement psVar = cnVar.prepareStatement(sqlDelete);
                psVar.setString(1, idBuku);
                psVar.execute();
                
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            } catch (SQLException sQLException) {
                JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
            }
            
        }
        
        reset();
        data_buku();
    }//GEN-LAST:event_buttonHapusActionPerformed

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
        cari_buku();
    }//GEN-LAST:event_txtCariActionPerformed

    private void buttonBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBatalActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_buttonBatalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser JDateTanggalTerbit;
    private javax.swing.JButton buttonBatal;
    private javax.swing.JButton buttonHapus;
    private javax.swing.JButton buttonSimpan;
    private javax.swing.JComboBox<String> cbKategoriBuku;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelBuku;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtIDBuku;
    private javax.swing.JTextField txtNamaBuku;
    private javax.swing.JTextField txtNamaPenerbit;
    // End of variables declaration//GEN-END:variables
}
