package controller;

import java.util.ArrayList;
import model.Pengadaan;
import view.PengadaanView;

public class PengadaanController implements Kelola {

    private final ArrayList<Pengadaan> daftarPengadaan = new ArrayList<>();
    private final PengadaanView view;

    public PengadaanController(PengadaanView view) {
        this.view = view;

        daftarPengadaan.add(new Pengadaan(1, "18/09/2026", "Gudang CV MPK"));
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
                    kembali = true;
                default ->
                    view.pesan("Pilihan tidak valid!");
            }
        }
    }

    @Override
    public void tambah() {
        view.judul("TAMBAH DATA PENGADAAN");

        int id = view.inputIdBaru();
        if (id == 0) {
            view.pesan("Penambahan pengadaan dibatalkan!");
            return;
        }

        if (cariPengadaan(id) != null) {
            view.pesan("ID pengadaan sudah digunakan!");
            return;
        }

        String tanggal = view.inputTanggal();
        String alamat = view.inputAlamat();

        try {
            daftarPengadaan.add(new Pengadaan(id, tanggal, alamat));
            view.pesan("Pengadaan baru berhasil ditambahkan!");
        } catch (IllegalArgumentException e) {
            view.pesan(e.getMessage());
        }
    }

    @Override
    public void tampilkan() {
        view.tampilDaftar(daftarPengadaan);
    }

    @Override
    public void hapus() {
        Pengadaan pengadaan = cariPengadaan(view.inputId());

        if (pengadaan == null) {
            view.pesan("ID pengadaan tidak ditemukan!");
            return;
        }

        daftarPengadaan.remove(pengadaan);
        view.pesan("Pengadaan berhasil dihapus!");
    }

    @Override
    public void update() {
        Pengadaan pengadaan = cariPengadaan(view.inputId());

        if (pengadaan == null) {
            view.pesan("ID pengadaan tidak ditemukan!");
            return;
        }

        try {
            pengadaan.setTanggal(view.inputTanggalBaru());
            pengadaan.setAlamat(view.inputAlamatBaru());
            view.pesan("Data pengadaan berhasil diperbarui!");
        } catch (IllegalArgumentException e) {
            view.pesan(e.getMessage());
        }
    }

    private Pengadaan cariPengadaan(int idPengadaan) {
        for (Pengadaan pengadaan : daftarPengadaan) {
            if (pengadaan.getIdPengadaan() == idPengadaan) {
                return pengadaan;
            }
        }
        return null;
    }
}
