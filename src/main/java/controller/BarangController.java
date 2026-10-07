package controller;

import java.util.ArrayList;
import java.util.List;
import model.Barang;
import model.BarangElektronik;
import model.BarangNonElektronik;
import view.BarangView;

public class BarangController implements Kelola {

    private final ArrayList<Barang> daftarBarang = new ArrayList<>();
    private final BarangView view;

    public BarangController(BarangView view) {
        this.view = view;

        daftarBarang.add(new BarangElektronik(1, "Laptop ASUS", 10, "2 Tahun"));
        daftarBarang.add(new BarangNonElektronik(2, "Meja Kantor", 5, "Perlengkapan Kantor"));
    }

    @Override
    public void jalankanMenu() {
        boolean kembali = false;

        while (!kembali) {
            view.tampilMenu();

            switch (view.pilihMenu()) {
                case 1 ->
                    tambah();
                case 2 ->
                    tampilkan();
                case 3 ->
                    hapus();
                case 4 ->
                    update();
                case 5 ->
                    cari();
                case 6 ->
                    kembali = true;
                default ->
                    view.pesan("Pilihan tidak valid!");
            }
        }
    }

    @Override
    public void tambah() {
        view.judul("TAMBAH DATA BARANG");

        int id = view.inputIdBaru();
        if (id == 0) {
            view.pesan("Penambahan barang dibatalkan");
            return;
        }

        if (cariBarang(id) != null) {
            view.pesan("ID barang sudah digunakan!");
            return;
        }

        String nama = view.inputNama();
        int stok = view.inputStok("Stok Barang");
        int jenis = view.inputJenis();

        try {
            Barang barangBaru;
            if (jenis == 1) {
                barangBaru = new BarangElektronik(id, nama, stok, view.inputGaransi());
            } else {
                barangBaru = new BarangNonElektronik(id, nama, stok, view.inputKategori());
            }

            daftarBarang.add(barangBaru);
            view.pesan("Barang baru berhasil ditambahkan!");
        } catch (IllegalArgumentException e) {
            view.pesan(e.getMessage());
        }
    }

    @Override
    public void tampilkan() {
        view.tampilDaftar(daftarBarang);
    }

    @Override
    public void hapus() {
        Barang barang = cariBarang(view.inputId());

        if (barang == null) {
            view.pesan("ID barang tidak ditemukan!");
            return;
        }

        daftarBarang.remove(barang);
        view.pesan("Barang berhasil dihapus!");
    }

    @Override
    public void update() {
        Barang barang = cariBarang(view.inputId());

        if (barang == null) {
            view.pesan("ID barang tidak ditemukan!");
            return;
        }

        try {
            barang.setStok(view.inputStok("Stok Baru"));
            view.pesan("Stok barang berhasil diperbarui!");
        } catch (IllegalArgumentException e) {
            view.pesan(e.getMessage());
        }
    }

    private void cari() {
        view.judul("CARI DATA BARANG");

        if (view.inputCaraCari() == 1) {
            Barang barang = cariBarang(view.inputId());   // overloading: parameter int

            if (barang == null) {
                view.pesan("ID barang tidak ditemukan!");
                return;
            }
            view.tampilDaftar(List.of(barang), "HASIL PENCARIAN");

        } else {
            List<Barang> hasil = cariBarang(view.inputKeyword());   // overloading: parameter String

            if (hasil.isEmpty()) {
                view.pesan("Barang tidak ditemukan!");
                return;
            }
            view.tampilDaftar(hasil, "HASIL PENCARIAN");
        }
    }

    // ---- Overloading: satu nama method, beda parameter & hasil ----
    private Barang cariBarang(int idBarang) {
        for (Barang barang : daftarBarang) {
            if (barang.getIdBarang() == idBarang) {
                return barang;
            }
        }
        return null;
    }

    private List<Barang> cariBarang(String keyword) {
        List<Barang> hasil = new ArrayList<>();
        String kunci = keyword.toLowerCase();

        for (Barang barang : daftarBarang) {
            if (barang.getNama().toLowerCase().contains(kunci)) {
                hasil.add(barang);
            }
        }
        return hasil;
    }
}
