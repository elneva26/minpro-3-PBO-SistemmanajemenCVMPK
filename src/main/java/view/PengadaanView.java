package view;

import java.util.Scanner;
import model.Pengadaan;

public class PengadaanView extends EntitasView<Pengadaan> {

    public PengadaanView(Scanner scanner) {
        super(scanner);
    }

    @Override
    protected String namaEntitas() {
        return "Pengadaan";
    }

    @Override
    protected void tampilDetail(Pengadaan pengadaan) {
        garisTipis();
        System.out.printf("%-13s: %d%n", "ID Pengadaan", pengadaan.getIdPengadaan());
        System.out.printf("%-13s: %s%n", "Tanggal", pengadaan.getTanggal());
        System.out.printf("%-13s: %s%n", "Alamat", pengadaan.getAlamat());
    }

    public String inputTanggal() {
        return bacaTeks("Tanggal Pengadaan (dd/MM/yyyy)", Pengadaan::isTanggalValid, "tidak valid!");
    }

    public String inputAlamat() {
        return bacaTeks("Alamat Pengadaan");
    }

    public String inputTanggalBaru() {
        return bacaTeks("Tanggal Baru (dd/MM/yyyy)", Pengadaan::isTanggalValid, "tidak valid!");
    }

    public String inputAlamatBaru() {
        return bacaTeks("Alamat Baru");
    }
}
