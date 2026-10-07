package view;

import java.util.Scanner;
import model.Pemasok;

public class PemasokView extends EntitasView<Pemasok> {

    public PemasokView(Scanner scanner) {
        super(scanner);
    }

    @Override
    protected String namaEntitas() {
        return "Pemasok";
    }

    @Override
    protected void tampilDetail(Pemasok pemasok) {
        garisTipis();
        System.out.printf("%-12s: %d%n", "ID Pemasok", pemasok.getIdPemasok());
        System.out.printf("%-12s: %s%n", "Nama", pemasok.getNama());
        System.out.printf("%-12s: %s%n", "Alamat", pemasok.getAlamat());
        System.out.printf("%-12s: %s%n", "No Telepon", pemasok.getNoTelepon());
    }

    public String inputNama() {
        return bacaTeks("Nama Pemasok");
    }

    public String inputAlamat() {
        return bacaTeks("Alamat Pemasok");
    }

    public String inputNoTelepon() {
        return bacaTeks("No Telepon", Pemasok::isNoTeleponValid, "harus berupa angka!");
    }

    public String inputNamaBaru() {
        return bacaTeks("Nama Baru");
    }

    public String inputAlamatBaru() {
        return bacaTeks("Alamat Baru");
    }

    public String inputNoTeleponBaru() {
        return bacaTeks("No Telepon Baru", Pemasok::isNoTeleponValid, "harus berupa angka!");
    }
}
