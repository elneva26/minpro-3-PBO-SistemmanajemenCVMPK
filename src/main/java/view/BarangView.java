package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import model.Barang;

public class BarangView extends EntitasView<Barang> {

    public BarangView(Scanner scanner) {
        super(scanner);
    }

    @Override
    protected String namaEntitas() {
        return "Barang";
    }

    @Override
    protected String labelUpdate() {
        return "Update Stok";
    }

    @Override
    protected List<String> daftarMenu() {
        List<String> menu = new ArrayList<>(super.daftarMenu());
        menu.add(menu.size() - 1, "Cari " + namaEntitas());
        return menu;
    }

    // Polymorphism: getLabelDetail() & getNilaiDetail() otomatis menyesuaikan jenis barang
    @Override
    protected void tampilDetail(Barang barang) {
        garisTipis();
        System.out.printf("%-12s: %d%n", "ID Barang", barang.getIdBarang());
        System.out.printf("%-12s: %s%n", "Nama", barang.getNama());
        System.out.printf("%-12s: %d%n", "Stok", barang.getStok());
        System.out.printf("%-12s: %s%n", barang.getLabelDetail(), barang.getNilaiDetail());
    }

    // Overriding: daftar barang dikelompokkan berdasarkan jenisnya
    @Override
    public void tampilDaftar(List<Barang> data, String judulDaftar) {
        if (data.isEmpty()) {
            pesan("Data barang masih kosong!");
            return;
        }

        Map<String, List<Barang>> perJenis = new TreeMap<>();
        for (Barang barang : data) {
            perJenis.computeIfAbsent(barang.getJenis(), k -> new ArrayList<>()).add(barang);
        }

        judul(judulDaftar);
        for (Map.Entry<String, List<Barang>> kelompok : perJenis.entrySet()) {
            System.out.println();
            System.out.println("|- " + kelompok.getKey());
            for (Barang barang : kelompok.getValue()) {
                tampilDetail(barang);
            }
        }
        garisTipis();
        judul("DATA SELESAI DITAMPILKAN");
    }

    public String inputNama() {
        return bacaTeks("Nama Barang");
    }

    public int inputStok(String label) {
        return bacaAngka(label, 0);
    }

    public int inputJenis() {
        System.out.println("Jenis Barang:");
        System.out.println("1. Barang Elektronik");
        System.out.println("2. Barang Non-Elektronik");
        return bacaPilihanDua("Jenis barang", "Jenis barang hanya boleh memilih 1 atau 2!");
    }

    public String inputGaransi() {
        return bacaTeks("Garansi");
    }

    public String inputKategori() {
        return bacaTeks("Kategori");
    }

    public int inputCaraCari() {
        System.out.println("Cari berdasarkan:");
        System.out.println("1. ID Barang");
        System.out.println("2. Nama Barang");
        return bacaPilihanDua("Cara pencarian", "Cara pencarian hanya boleh memilih 1 atau 2!");
    }

    public String inputKeyword() {
        return bacaTeks("Nama barang yang dicari");
    }

    private int bacaPilihanDua(String label, String pesanSalah) {
        int pilihan = bacaAngka(label, "1-2", 1);
        while (pilihan > 2) {
            pesan(pesanSalah);
            pilihan = bacaAngka(label, "1-2", 1);
        }
        return pilihan;
    }
}
