package view;

import java.util.Scanner;
import java.util.function.Predicate;


public abstract class BaseView {

    private static final int LEBAR = 68;

    protected final Scanner scanner;

    protected BaseView(Scanner scanner) {
        this.scanner = scanner;
    }

    // ================= TAMPILAN =================
    public void garis() {
        System.out.println("=".repeat(LEBAR));
    }

    public void garisTipis() {
        System.out.println("-".repeat(LEBAR));
    }

    public void judul(String teks) {
        garis();
        System.out.println(tengah(teks, LEBAR));
        garis();
    }

    public void pesan(String teks) {
        garis();
        System.out.println(">>>>>" + tengah(teks, LEBAR - 10) + "<<<<<");
        garis();
    }

    protected String tengah(String teks, int lebar) {
        if (teks.length() >= lebar) {
            return teks;
        }
        int kiri = (lebar - teks.length()) / 2;
        int kanan = lebar - teks.length() - kiri;
        return " ".repeat(kiri) + teks + " ".repeat(kanan);
    }

    // ================= INPUT (OVERLOADING) =================
    protected int bacaPilihan(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                pesan("Input harus berupa angka!");
            }
        }
    }

    // Overloading 1: angka >= minimal
    protected int bacaAngka(String label, int minimal) {
        return bacaAngka(label, null, minimal);
    }

    // Overloading 2: angka >= minimal, dengan petunjuk pada prompt
    protected int bacaAngka(String label, String petunjuk, int minimal) {
        String prompt = (petunjuk == null) ? label : label + " (" + petunjuk + ")";
        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                pesan(label + " wajib diisi!");
                continue;
            }

            try {
                int angka = Integer.parseInt(input);
                if (angka >= minimal) {
                    return angka;
                }
                pesan(label + " tidak boleh kurang dari " + minimal + "!");
            } catch (NumberFormatException e) {
                pesan(label + " harus berupa angka!");
            }
        }
    }

    // Overloading 1: teks tidak boleh kosong
    protected String bacaTeks(String label) {
        return bacaTeks(label, teks -> true, "");
    }

    // Overloading 2: teks tidak boleh kosong + harus lolos aturan tertentu
    protected String bacaTeks(String label, Predicate<String> aturan, String pesanFormat) {
        while (true) {
            System.out.print(label + ": ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                pesan(label + " tidak boleh kosong!");
                continue;
            }

            if (!aturan.test(input)) {
                pesan(label + " " + pesanFormat);
                continue;
            }

            return input;
        }
    }
}
