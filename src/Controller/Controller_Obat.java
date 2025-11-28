package Controller;

import DAO.DAO_Interface;
import DAO.DAO_Obat;
import Model.varObat;
import View.frmObat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Controller_Obat {

    frmObat form;
    DAO_Interface<varObat> model;
    List<varObat> list;
    String[] header;

    public Controller_Obat(frmObat form) {
        this.form = form;
        model = new DAO_Obat();
        header = new String[]{"Kode_Obat", "Nama", "Harga", "Stok"};
        isiTabel();
    }

    public void reset() {
        form.getTxtKode_Obat().setText("");
        form.getTxtNama().setText("");
        form.getTxtHarga().setText("");
        form.getTxtStok().setText("");
        isiTabel();
    }

    public void isiTabel() {
        list = model.getAll();
        DefaultTableModel tbl = new DefaultTableModel(null, header);

        for (varObat x : list) {
            tbl.addRow(new Object[]{
                x.getKode_Obat(),
                x.getNama(),
                x.getHarga(),
                x.getStok()
            });
        }
        form.getTblObat().setModel(tbl);
    }

    public void isiField(int row) {
        form.getTxtKode_Obat().setText(list.get(row).getKode_Obat());
        form.getTxtNama().setText(list.get(row).getNama());
        form.getTxtHarga().setText(String.valueOf(list.get(row).getHarga()));
        form.getTxtStok().setText(String.valueOf(list.get(row).getStok()));
    }

    public void insert() {
        varObat o = new varObat();
        o.setKode_Obat(form.getTxtKode_Obat().getText());
        o.setNama(form.getTxtNama().getText());
        o.setHarga(Integer.parseInt(form.getTxtHarga().getText()));
        o.setStok(Integer.parseInt(form.getTxtStok().getText()));
        model.insert(o);
        JOptionPane.showMessageDialog(form, "Data berhasil ditambah");
    }

    public void update() {
        varObat o = new varObat();
        o.setKode_Obat(form.getTxtKode_Obat().getText());
        o.setNama(form.getTxtNama().getText());
        o.setHarga(Integer.parseInt(form.getTxtHarga().getText()));
        o.setStok(Integer.parseInt(form.getTxtStok().getText()));
        model.update(o);
        JOptionPane.showMessageDialog(form, "Data berhasil diubah");
    }

    public void delete() {
        if (form.getTxtKode_Obat().getText().isEmpty()) {
            JOptionPane.showMessageDialog(form, "Pilih data dulu");
        } else {
            model.delete(form.getTxtKode_Obat().getText());
            JOptionPane.showMessageDialog(form, "Data berhasil dihapus");
        }
    }

    public void isiTabelCari() {
        list = model.getCari(form.getTxtKode_Obat().getText());
        DefaultTableModel tbl = new DefaultTableModel(null, header);

        for (varObat x : list) {
            tbl.addRow(new Object[]{
                x.getKode_Obat(),
                x.getNama(),
                x.getHarga(),
                x.getStok()
            });
        }

        form.getTblObat().setModel(tbl);
    }
}
