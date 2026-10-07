package model;

public class BarangNonElektronik extends Barang {

    private String kategori;

    public BarangNonElektronik(int idBarang, String nama, int stok, String kategori) {
        super(idBarang, nama, stok);
        setKategori(kategori);
    }

    public String getKategori() {
        return kategori;
    }

    private void setKategori(String kategori) {
        if (kategori == null || kategori.trim().isEmpty()) {
            throw new IllegalArgumentException("Kategori tidak boleh kosong!");
        }
        this.kategori = kategori.trim();
    }

    // ---- Overriding abstract method dari Barang ----
    @Override
    public String getJenis() {
        return "BARANG NON-ELEKTRONIK";
    }

    @Override
    public String getLabelDetail() {
        return "Kategori";
    }

    @Override
    public String getNilaiDetail() {
        return getKategori();
    }
}
