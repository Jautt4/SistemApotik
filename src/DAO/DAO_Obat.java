package DAO;

import Koneksi.Database;
import Model.varObat;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO_Obat implements DAO_Interface<varObat> {

    Connection conn;

    public DAO_Obat() {
        conn = Database.KoneksiDB();
    }

    @Override
    public void insert(varObat o) {
        String sql = "INSERT INTO tblObat VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, o.getKode_Obat());
            ps.setString(2, o.getNama());
            ps.setInt(3, o.getHarga());
            ps.setInt(4, o.getStok());
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public void update(varObat o) {
        String sql = "UPDATE tblObat SET Nama=?, Harga=?, Stok=? WHERE Kode_Obat=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, o.getNama());
            ps.setInt(2, o.getHarga());
            ps.setInt(3, o.getStok());
            ps.setString(4, o.getKode_Obat());
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public void delete(String kode) {
        String sql = "DELETE FROM tblObat WHERE Kode_Obat=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kode);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public List<varObat> getAll() {
        List<varObat> list = new ArrayList<>();
        String sql = "SELECT * FROM tblObat";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                varObat o = new varObat();
                o.setKode_Obat(rs.getString("Kode_Obat"));
                o.setNama(rs.getString("Nama"));
                o.setHarga(rs.getInt("Harga"));
                o.setStok(rs.getInt("Stok"));
                list.add(o);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    @Override
    public List<varObat> getCari(String kode) {
        List<varObat> list = new ArrayList<>();
        String sql = "SELECT * FROM tblObat WHERE Kode_Obat LIKE ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + kode + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                varObat o = new varObat();
                o.setKode_Obat(rs.getString("Kode_Obat"));
                o.setNama(rs.getString("Nama"));
                o.setHarga(rs.getInt("Harga"));
                o.setStok(rs.getInt("Stok"));
                list.add(o);
            }
        } catch (SQLException e) {
        }
        return list;
    }
}
