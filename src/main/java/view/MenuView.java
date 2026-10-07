package view;

import java.util.Scanner;

public class MenuView extends BaseView {

    public MenuView(Scanner scanner) {
        super(scanner);
    }

    public void tampilMenuUtama() {
        judul("SISTEM MANAJEMEN CV MANDIRI PRIMA KREATIF");
        System.out.println("1. Kelola Data Barang");
        System.out.println("2. Kelola Data Pemasok");
        System.out.println("3. Kelola Data Pengadaan");
        System.out.println("4. Keluar");
        garis();
    }

    public int pilihMenu() {
        return bacaPilihan("Pilih menu (1-4): ");
    }

    public void tampilKeluar() {
        pesan("Terima kasih telah menggunakan sistem manajemen CV MPK");
    }
}
