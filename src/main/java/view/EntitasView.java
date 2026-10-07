package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public abstract class EntitasView<T> extends BaseView {

    protected EntitasView(Scanner scanner) {
        super(scanner);
    }

    // ---- Abstract method: wajib diisi subclass ----
    protected abstract String namaEntitas();

    protected abstract void tampilDetail(T item);

    // ---- Method biasa: boleh di-override subclass ----
    protected String labelUpdate() {
        return "Update " + namaEntitas();
    }

    protected List<String> daftarMenu() {
        List<String> menu = new ArrayList<>();
        menu.add("Tambah " + namaEntitas());
        menu.add("Tampilkan " + namaEntitas());
        menu.add("Hapus " + namaEntitas());
        menu.add(labelUpdate());
        menu.add("Kembali");
        return menu;
    }

    public void tampilMenu() {
        judul("KELOLA DATA " + namaEntitas().toUpperCase());
        int nomor = 1;
        for (String item : daftarMenu()) {
            System.out.println(nomor + ". " + item);
            nomor++;
        }
        garis();
    }

    public int pilihMenu() {
        return bacaPilihan("Pilih menu: ");
    }

    public int inputIdBaru() {
        return bacaAngka("ID " + namaEntitas(), "0 untuk kembali", 0);
    }

    public int inputId() {
        return bacaAngka("ID " + namaEntitas(), 1);
    }

    // ---- Overloading: tampilDaftar dengan / tanpa judul ----
    public void tampilDaftar(List<T> data) {
        tampilDaftar(data, "DAFTAR " + namaEntitas().toUpperCase());
    }

    public void tampilDaftar(List<T> data, String judulDaftar) {
        if (data.isEmpty()) {
            pesan("Data " + namaEntitas().toLowerCase() + " masih kosong!");
            return;
        }

        judul(judulDaftar);
        for (T item : data) {
            tampilDetail(item);
        }
        garisTipis();
        judul("DATA SELESAI DITAMPILKAN");
    }
}
