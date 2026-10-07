package model;

public class BarangElektronik extends Barang {

    private String garansi;

    public BarangElektronik(int idBarang, String nama, int stok, String garansi) {
        super(idBarang, nama, stok);
        setGaransi(garansi);
    }

    public String getGaransi() {
        return garansi;
    }

    private void setGaransi(String garansi) {
        if (garansi == null || garansi.trim().isEmpty()) {
            throw new IllegalArgumentException("Garansi tidak boleh kosong!");
        }
        this.garansi = garansi.trim();
    }

    // ---- Overriding abstract method dari Barang ----
    @Override
    public String getJenis() {
        return "BARANG ELEKTRONIK";
    }

    @Override
    public String getLabelDetail() {
        return "Garansi";
    }

    @Override
    public String getNilaiDetail() {
        return getGaransi();
    }
}
