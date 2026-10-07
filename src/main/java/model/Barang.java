package model;

public abstract class Barang {

    private final int idBarang;
    private String nama;
    private int stok;

    protected Barang(int idBarang, String nama, int stok) {
        if (idBarang <= 0) {
            throw new IllegalArgumentException("ID barang tidak valid!");
        }
        this.idBarang = idBarang;
        setNama(nama);
        setStok(stok);
    }

    public int getIdBarang() {
        return idBarang;
    }

    public String getNama() {
        return nama;
    }

    public int getStok() {
        return stok;
    }

    private void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama barang tidak boleh kosong!");
        }
        this.nama = nama.trim();
    }

    public final void setStok(int stok) {
        if (stok < 0) {
            throw new IllegalArgumentException("Stok tidak boleh kurang dari 0!");
        }
        this.stok = stok;
    }

    // ---- Abstract method: wajib diisi oleh setiap subclass ----
    public abstract String getJenis();

    public abstract String getLabelDetail();

    public abstract String getNilaiDetail();
}
