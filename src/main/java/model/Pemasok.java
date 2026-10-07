package model;

public class Pemasok {

    private final int idPemasok;
    private String nama;
    private String alamat;
    private String noTelepon;

    public Pemasok(int idPemasok, String nama, String alamat, String noTelepon) {
        if (idPemasok <= 0) {
            throw new IllegalArgumentException("ID pemasok tidak valid!");
        }
        this.idPemasok = idPemasok;
        setNama(nama);
        setAlamat(alamat);
        setNoTelepon(noTelepon);
    }

    public static boolean isNoTeleponValid(String noTelepon) {
        return noTelepon != null && noTelepon.trim().matches("\\d+");
    }

    public int getIdPemasok() {
        return idPemasok;
    }

    public String getNama() {
        return nama;
    }

    public final void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama pemasok tidak boleh kosong!");
        }
        this.nama = nama.trim();
    }

    public String getAlamat() {
        return alamat;
    }

    public final void setAlamat(String alamat) {
        if (alamat == null || alamat.trim().isEmpty()) {
            throw new IllegalArgumentException("Alamat pemasok tidak boleh kosong!");
        }
        this.alamat = alamat.trim();
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public final void setNoTelepon(String noTelepon) {
        if (noTelepon == null || noTelepon.trim().isEmpty()) {
            throw new IllegalArgumentException("No telepon tidak boleh kosong!");
        }
        if (!isNoTeleponValid(noTelepon)) {
            throw new IllegalArgumentException("No telepon harus berupa angka!");
        }
        this.noTelepon = noTelepon.trim();
    }
}
