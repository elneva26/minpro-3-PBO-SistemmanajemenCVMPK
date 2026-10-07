package controller;

import java.util.ArrayList;
import model.Pemasok;
import view.PemasokView;

public class PemasokController implements Kelola {

    private final ArrayList<Pemasok> daftarPemasok = new ArrayList<>();
    private final PemasokView view;

    public PemasokController(PemasokView view) {
        this.view = view;

        daftarPemasok.add(new Pemasok(1, "PT Sumber Elektronik", "Samarinda", "081234567890"));
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
        view.judul("TAMBAH DATA PEMASOK");

        int id = view.inputIdBaru();
        if (id == 0) {
            view.pesan("Penambahan pemasok dibatalkan!");
            return;
        }

        if (cariPemasok(id) != null) {
            view.pesan("ID pemasok sudah digunakan!");
            return;
        }

        String nama = view.inputNama();
        String alamat = view.inputAlamat();
        String noTelepon = view.inputNoTelepon();

        try {
            daftarPemasok.add(new Pemasok(id, nama, alamat, noTelepon));
            view.pesan("Pemasok baru berhasil ditambahkan!");
        } catch (IllegalArgumentException e) {
            view.pesan(e.getMessage());
        }
    }

    @Override
    public void tampilkan() {
        view.tampilDaftar(daftarPemasok);
    }

    @Override
    public void hapus() {
        Pemasok pemasok = cariPemasok(view.inputId());

        if (pemasok == null) {
            view.pesan("ID pemasok tidak ditemukan!");
            return;
        }

        daftarPemasok.remove(pemasok);
        view.pesan("Pemasok berhasil dihapus!");
    }

    @Override
    public void update() {
        Pemasok pemasok = cariPemasok(view.inputId());

        if (pemasok == null) {
            view.pesan("ID pemasok tidak ditemukan!");
            return;
        }

        try {
            pemasok.setNama(view.inputNamaBaru());
            pemasok.setAlamat(view.inputAlamatBaru());
            pemasok.setNoTelepon(view.inputNoTeleponBaru());
            view.pesan("Data pemasok berhasil diperbarui!");
        } catch (IllegalArgumentException e) {
            view.pesan(e.getMessage());
        }
    }

    private Pemasok cariPemasok(int idPemasok) {
        for (Pemasok pemasok : daftarPemasok) {
            if (pemasok.getIdPemasok() == idPemasok) {
                return pemasok;
            }
        }
        return null;
    }
}
