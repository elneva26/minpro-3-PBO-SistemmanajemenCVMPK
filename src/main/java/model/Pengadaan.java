package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Pengadaan {

    private static final DateTimeFormatter FORMAT_TANGGAL
            = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    private final int idPengadaan;
    private String tanggal;
    private String alamat;

    public Pengadaan(int idPengadaan, String tanggal, String alamat) {
        if (idPengadaan <= 0) {
            throw new IllegalArgumentException("ID pengadaan tidak valid!");
        }
        this.idPengadaan = idPengadaan;
        setTanggal(tanggal);
        setAlamat(alamat);
    }

    public static boolean isTanggalValid(String tanggal) {
        if (tanggal == null) {
            return false;
        }
        try {
            LocalDate.parse(tanggal.trim(), FORMAT_TANGGAL);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public int getIdPengadaan() {
        return idPengadaan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public final void setTanggal(String tanggal) {
        if (tanggal == null || tanggal.trim().isEmpty()) {
            throw new IllegalArgumentException("Tanggal pengadaan tidak boleh kosong!");
        }
        if (!isTanggalValid(tanggal)) {
            throw new IllegalArgumentException("Format tanggal tidak valid! Gunakan dd/MM/yyyy!");
        }
        this.tanggal = tanggal.trim();
    }

    public String getAlamat() {
        return alamat;
    }

    public final void setAlamat(String alamat) {
        if (alamat == null || alamat.trim().isEmpty()) {
            throw new IllegalArgumentException("Alamat pengadaan tidak boleh kosong!");
        }
        this.alamat = alamat.trim();
    }
}
