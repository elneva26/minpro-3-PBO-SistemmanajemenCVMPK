# LAPORAN MINI PROJECT 3 PEMROGRAMAN BERORIENTASI OBJEK

## SISTEM MANAJEMEN CV MANDIRI PRIMA KREATIF



---

Nama : **Elena Dementieva**

NIM : **2509116008**

Kelas : **Sistem Informasi A'25**

---

## **DAFTAR ISI**

- [BAB I Pendahuluan](#bab-i-pendahuluan)
- [BAB II Struktur Package](#bab-ii-struktur-package)
- [BAB III Alur Program](#bab-iii-alur-program)
- [BAB IV Validasi Input](#bab-iv-validasi-input)
- [BAB V Encapsulation dan Inheritance](#bab-v-encapsulation-dan-inheritance)
- [BAB VI Dummy data pada arrayList](#bab-vi-dummy-data-pada-arrayList)
- [BAB VII Polymorphism dan Abstraction](#bab-vii-polymorphism-dan-abstraction)
- [BAB VIII Interface](#bab-viii-interface)
- [BAB IX Kesimpulan](#bab-ix-kesimpulan)

---

## **BAB I PENDAHULUAN**

### **1.1 Deskripsi Singkat Program**

Sistem Manajemen CV Mandiri Prima Kreatif adalah program berbasis Java untuk membantu mengelola data pada CV Mandiri Prima Kreatif yang bergerak di bidang elektronik dan pengadaan barang.

Program mengelola tiga jenis data, yaitu data barang, data pemasok, dan data pengadaan, dengan konsep CRUD (Create, Read, Update, Delete). Khusus data barang, tersedia fitur tambahan Cari Barang (berdasarkan ID atau nama). Data disimpan sementara di dalam *"ArrayList"* selama program berjalan, dan sudah tersedia data awal (*dummy data*) pada tiap controller.

### **1.2 Tujuan**

Sistem Manajemen CV Mandiri Prima Kreatif dirancang dengan tujuan sebagai berikut:

* Membantu mengelola data barang, pemasok, dan pengadaan secara terstruktur.
* Memudahkan proses tambah, tampil, update, hapus, dan cari data (fungsi pencarian khusus pada data barang) terdapat dalam sistem.
* Menerapkan konsep pemrograman berorientasi objek, yaitu encapsulation, inheritance, polymorphism (overriding dan overloading), dan abstraction (abstract class dan abstract method) dalam program.
* Menerapkan struktur proyek MVC (Model, View, Controller) agar tugas dari setiap fungsi dalam bagian program terpisah dan kode lebih mudah dikelola.
* Menerapkan interface sebagai kontrak yang menyeragamkan seluruh controller data.
* Memastikan data yang dimasukkan sesuai melalui proses validasi input pada view dan model.

### **1.3 Alur Singkat**
Alur program dimulai dengan menampilkan menu utama Sistem Manajemen CV Mandiri Prima Kreatif, yaitu kelola data barang, kelola data pemasok, kelola data pengadaan, dan keluar. Menu utama diatur oleh MenuController, dimana menu tersebut meneruskan pilihan pengguna ke controller data yang sesuai. Menu utama akan terus ditampilkan sampai pengguna memilih menu keluar. Setiap menu kelola data memiliki sub-menu sendiri yang juga berulang sampai pengguna memilih kembali.

-> Pada menu kelola data barang, pengguna dapat memilih menu tambah, tampilkan, hapus, update stok, cari barang dan kembali. 

- Saat menambahkan barang, pengguna memasukkan ID barang, nama barang, dan stok, lalu memilih jenis barang, yaitu Barang Elektronik atau Barang Non-Elektronik.
- Barang elektronik memiliki data tambahan berupa garansi, sedangkan barang non-elektronik memiliki data tambahan berupa kategori.
- Lalu ID barang tidak boleh sama dengan ID yang sudah ada, dan pengguna dapat membatalkan penambahan dengan memasukkan angka 0 pada ID.
- Data barang yang ditampilkan dikelompokkan otomatis berdasarkan jenisnya.
- Pengguna juga dapat menghapus barang berdasarkan ID, memperbarui stok barang, serta mencari barang berdasarkan ID atau berdasarkan nama.

-> Pada menu kelola data pemasok, pengguna dapat menambahkan data pemasok dengan memasukkan ID pemasok, nama pemasok, alamat, dan nomor telepon yang hanya boleh berisi angka.
- Data pemasok yang telah tersimpan dapat ditampilkan, dihapus berdasarkan ID, serta diperbarui (nama, alamat, dan nomor telepon).

-> Pada menu kelola data pengadaan, pengguna dapat menambahkan data pengadaan dengan memasukkan ID pengadaan, tanggal pengadaan dengan format dd/MM/yyyy, dan alamat pengadaan.
- Data pengadaan yang telah tersimpan dapat ditampilkan, dihapus berdasarkan ID, serta diperbarui (tanggal dan alamat).

-> Setiap proses input dilengkapi dengan validasi dua lapis, yaitu; 
- Lapis pertama ada di view, yang memastikan input tidak kosong, berupa angka jika yang diminta angka, dan memenuhi nilai minimal, lalu jika terdapat input yang salah, sistem akan langsung meminta input ulang sampai benar.
- Lapis kedua ada di model, yang menjaga aturan data seperti stok tidak boleh kurang dari 0, nomor telepon hanya angka (tidak boleh huruf atau simbol), dan tanggal harus sesuai format penulisan tanggal yaitu dd/MM/yyyy.
Jika aturan pada program dilanggar oleh pengguna, maka model akan melempar IllegalArgumentException dan controller menampilkan pesannya melalui view.

Dalam setiap proses, pembagian tugas mengikuti pola MVC, yaitu controller meminta input kepada view, dan membuat ataupun mengubah objek model, lalu menyimpannya ke dalam ArrayList, setelah itu meminta view menampilkan hasilnya kepada pengguna.

--------------

## **BAB II STRUKTUR PACKAGE**

Program menerapkan struktur **MVC (Model-View-Controller)** dengan satu package tambahan yaitu "main" sebagai jalan masuk ke dalam program.

<img width="500" alt="image" src="https://github.com/user-attachments/assets/8dcb6403-55bf-48a9-9dda-7081769e01b8" />


### Penjelasan tiap package

| Package | Class | Tugas |
|---|---|---|
| `main` | `SistemmanajemenCVMPK` | Membuat `Scanner`, membuat `MenuController`, lalu menjalankannya. Tidak berisi logika lain. |
| `model` | `Barang`, `BarangElektronik`, `BarangNonElektronik`, `Pemasok`, `Pengadaan` | Menyimpan data dan menjaga aturan datanya (misalnya stok tidak boleh minus). Model **tidak** mencetak apa pun ke layar. Jika data tidak valid, model melempar `IllegalArgumentException`. |
| `view` | `BaseView`, `EntitasView`, `BarangView`, `PemasokView`, `PengadaanView`, `MenuView` | Seluruh `Scanner` dan `System.out` ada di sini: menampilkan menu, membaca input beserta validasinya, dan mencetak data. View tidak menyimpan data. |
| `controller` | `Kelola`, `MenuController`, `BarangController`, `PemasokController`, `PengadaanController` | Mengatur alur: meminta input ke View, membuat atau mengubah objek Model, menyimpannya di `ArrayList`, lalu meminta View menampilkan hasilnya. |

---

## **BAB III ALUR PROGRAM**

### **3.1 Alur Pemanggilan MVC**

Contoh alur pada saat **Tambah Barang**:

1. `BarangController.tambah()` meminta input ID, nama, stok, dan jenis ke `BarangView`.
2. `BarangView` membaca input dan memvalidasinya (kosong, bukan angka, kurang dari minimal) sampai valid.
3. `BarangController` membuat objek `BarangElektronik` atau `BarangNonElektronik` (Model).
4. Model memeriksa aturan datanya. Jika dilanggar, Model melempar `IllegalArgumentException`.
5. `BarangController` menyimpan objek ke `ArrayList<Barang>`, lalu meminta `BarangView` menampilkan pesan hasil.

Program dijalankan melalui class "SistemmanajemenCVMPK" yang berada pada package "main"

<img width="500" alt="image" src="https://github.com/user-attachments/assets/0888ecbb-f88d-4fb6-87b2-3463619a8406" />


### **3.2 Menu Utama**

Saat program dijalankan, pengguna akan diberikan menu utama dari Sistem manajemen CV mandiri prima kreatif, yaitu;

<img width="500" alt="image" src="https://github.com/user-attachments/assets/6a1bb7b9-3be7-4aa0-b5a0-fe4e26f3cdc2" />


### **3.4 Kelola Data Barang**

Pada menu kelola data barang, terdapat beberapa pilihan yang dapat dipilih oleh pengguna yaitu;

<img width="500" alt="image" src="https://github.com/user-attachments/assets/7e05659b-eb1b-4da5-9dcf-a3b2ffcf12f5" />

Pada pilihan **Tambah Barang**, pengguna dapat menginput ID barang, nama barang, stok, jenis barang, dan garansi.

<img width="500" alt="image" src="https://github.com/user-attachments/assets/af3e8ed8-2d0a-4fa1-a4d5-9d0d7c60a365" />

Data barang terbagi menjadi dua jenis:

- **Barang Elektronik**, memiliki atribut tambahan **garansi**.
- **Barang Non-Elektronik**, memiliki atribut tambahan **kategori**.

Barang elektronik memiliki atribut tambahan berupa garansi sesuai yang berfungsi sebagai jaminan ketahanan dari kualitas produk elektronik, sedangkan barang non-elektronik memiliki atribut tambahan berupa kategori yang berfungsi untuk mengategorikan produk tersebut sesuai fungsinya.

Saat fitur **Tampilkan Barang** dijalankan, data otomatis dikelompokkan berdasarkan jenisnya:


<img width="500" alt="image" src="https://github.com/user-attachments/assets/3859bcbd-07a9-44d5-8707-10207c8705c0" />

Pada pilihan **Hapus Barang**, pengguna dapat menginput ID barang yang ingin dihapus dari data yang tersimpan.


<img width="500" alt="image" src="https://github.com/user-attachments/assets/39cb770a-f279-4a36-9226-c08e265e30f2" />


Pada pilihan **Update stok**, pengguna dapat menginput ID yang ingin diupdate serta jumlah stok baru yang ingin di perbarui.

<img width="500" alt="image" src="https://github.com/user-attachments/assets/778c8722-d103-4805-bd9c-d6126f0d0f3a" />


Fitur **Cari Barang** dapat dilakukan berdasarkan **ID Barang** atau **Nama Barang**, setelah pengguna selesai menginput data yang ingin dicari, maka program akan langsung menampilkan data nya:



<img width="500" alt="image" src="https://github.com/user-attachments/assets/84364816-c0fb-4477-bf13-fe9e94e2616e" />



<img width="500" alt="image" src="https://github.com/user-attachments/assets/6d917e85-e2c0-49dd-8c9f-95e3372469ab" />


Pada pilihan **Kembali**, pengguna akan di arahkan oleh sistem untuk kembali ke menu utama.

<img width="500" alt="image" src="https://github.com/user-attachments/assets/adb7e722-cc9b-4275-944f-dedac0dbe1ea" />



### **3.5 Kelola Data Pemasok**

Di dalam menu kelola data pemasok terdapat beberapa opsi yang diberikan, yaitu tambah, tampilkan, hapus, update, dan kembali. Data pemasok terdiri dari ID, nama, alamat, dan nomor telepon (hanya angka) jika pengguna menginput selain angka, maka program akan langsung menampilkan pesan bahwa data yang di input oleh pengguna tidak sesuai. Setelah itu. pengguna akan diminta untuk menginput ulang data nya.

<img width="500" alt="image" src="https://github.com/user-attachments/assets/18fff18e-dd24-41eb-a71c-aa4bd5fbc88f" />

Pada pilihan **Tambah Pemasok**, pengguna dapat menginput ID pemasok, nama pemasok, alamat pemasok dan no telepon.


<img width="500" alt="image" src="https://github.com/user-attachments/assets/99cc8048-f559-4f0d-b434-4d4295306a65" />

Saat fitur **Tampilkan Pemasok** dijalankan, sistem akan langsung menampilkan data secara otomatis.


<img width="500" alt="image" src="https://github.com/user-attachments/assets/04b5332a-09ec-4f90-949e-6c8228d3327a" />


Pada pilihan **Hapus Pemasok**, pengguna dapat menginput ID pemasok yang ingin dihapus dari data yang tersimpan.

<img width="500" alt="image" src="https://github.com/user-attachments/assets/e46c1f43-f1ac-4e23-826f-c18594081a58" />


Pada pilihan **Update Pemasok**, pengguna dapat menginput ID yang ingin diupdate, nama baru, alamat baru serta no telepon yang ingin diperbarui.

<img width="500" alt="image" src="https://github.com/user-attachments/assets/94cc3fbb-1a27-416a-81c8-9a7a869682e6" />


Pada pilihan **Kembali**, pengguna akan di arahkan oleh sistem untuk kembali ke menu utama.

<img width="500" alt="image" src="https://github.com/user-attachments/assets/0e88effa-fafd-4dba-9e03-77c9bcc02a1e" />


### **3.6 Kelola Data Pengadaan**

Di dalam menu kelola data pengadaan terdapat beberapa opsi yang diberikan, yaitu tambah, tampilkan, hapus, update, dan kembali. Data pengadaan terdiri dari ID, tanggal (format `dd/MM/yyyy`), dan alamat. jika pengguna menginput tanggal lalu formatnya tidak sesuai, maka program akan langsung menampilkan pesan bahwa data yang di input oleh pengguna tidak sesuai. Setelah itu, pengguna akan diminta untuk menginput ulang data nya.


<img width="500" alt="image" src="https://github.com/user-attachments/assets/6d6a2439-48f3-4b77-95ab-b80b5b6dfbc6" />


Pada pilihan **Tambah Pengadaan**, pengguna dapat menginput ID pengadaan, tanggal pengadaan, dan alamat pengadaan.


<img width="500" alt="image" src="https://github.com/user-attachments/assets/e8c6f444-0d02-4f78-b813-37c42f26ca37" />


Saat fitur **Tampilkan Pengadaan** dijalankan, sistem akan langsung menampilkan data secara otomatis.

<img width="500" alt="image" src="https://github.com/user-attachments/assets/a1570adc-1816-4513-b227-35efc4960deb" />


Pada pilihan **Hapus Pengadaan**, pengguna dapat menginput ID pengadaan yang ingin dihapus dari data yang tersimpan.

<img width="500" alt="image" src="https://github.com/user-attachments/assets/37e39543-507b-483e-9ec9-28e800e92bae" />

Pada pilihan **Update Pengadaan**, pengguna dapat menginput ID yang ingin diupdate, tanggal baru, dan alamat baru yang ingin diperbarui.

<img width="500" alt="image" src="https://github.com/user-attachments/assets/aebd1001-cdf9-4f07-a5e3-f9a89c4a1450" />

Pada pilihan **Kembali**, pengguna akan di arahkan oleh sistem untuk kembali ke menu utama.

<img width="500" alt="image" src="https://github.com/user-attachments/assets/63b94ed3-f06c-48d6-822f-ab4c7ba692fa" />


### **3.7 Keluar**

Jika pengguna memilih menu 4 pada menu utama, program menampilkan pesan terima kasih lalu berhenti.

<img width="500" alt="image" src="https://github.com/user-attachments/assets/3df683c4-d41a-40b7-80e6-98245b3176d5" />


---

## **BAB IV VALIDASI INPUT**

Validasi dilakukan pada **dua lapis**:

1. **View** (`BaseView`): memastikan input tidak kosong, berupa angka, dan memenuhi nilai minimal. Input diulang sampai valid.
2. **Model**: menjaga aturan data. Jika dilanggar, Model melempar `IllegalArgumentException` dan Controller menampilkan pesannya lewat View.

| Data | Aturan |
|---|---|
| ID (barang, pemasok, pengadaan) | wajib diisi, harus angka, tidak boleh duplikat, ID pada update/hapus minimal 1 |
| Nama, alamat, garansi, kategori | tidak boleh kosong |
| Stok | harus angka, tidak boleh kurang dari 0 |
| Jenis barang | hanya boleh 1 atau 2 |
| No telepon | tidak boleh kosong, hanya angka |
| Tanggal pengadaan | tidak boleh kosong, format `dd/MM/yyyy`, dan tanggalnya harus valid (31/02/2026 akan ditolak) |
| Menu | harus angka dan sesuai pilihan yang tersedia |

Contoh hasil validasi saat **Tambah Barang** (input salah diulang sampai benar):

<img width="500" alt="image" src="https://github.com/user-attachments/assets/8a75e6b7-6ff8-4e75-90e5-662daeb1ecb9" />

<img width="500" alt="image" src="https://github.com/user-attachments/assets/686f861b-e8f4-4808-9f33-717a5aa5b684" />

Contoh hasil validasi saat **Tambah Pemasok** (input salah diulang sampai benar):

<img width="500" alt="image" src="https://github.com/user-attachments/assets/a065e70d-a3a1-4e23-b2b7-345794561dfb" />

Contoh hasil validasi saat **Tambah Pengadaan** (input salah diulang sampai benar):

<img width="500" alt="image" src="https://github.com/user-attachments/assets/333a3e9a-b70c-4e18-949b-c64e99117325" />

Validasi lain: input menu yang bukan angka ditolak dengan pesan "Input harus berupa angka!", dan angka di luar pilihan ditolak dengan "Pilihan tidak valid!".

<img width="500" alt="image" src="https://github.com/user-attachments/assets/2ec7c118-4f26-4858-b9d5-b3e9409fd3da" />


---

## **BAB V ENCAPSULATION DAN INHERITANCE**

### **5.1 Encapsulation**

Semua atribut pada class Model dibuat "private" dan diakses melalui getter/setter. Setter juga berfungsi memvalidasi data sebelum disimpan.

Contoh pada "model/Barang.java":

<img width="500" alt="image" src="https://github.com/user-attachments/assets/53d67193-29c1-4b1f-a524-677b6ac9df44" />

<img width="500" alt="image" src="https://github.com/user-attachments/assets/869ca4f8-16d5-4677-8b82-c965bfac6956" />

       
Poin penerapan encapsulation:

- Atribut "private", sehingga tidak dapat diubah langsung dari class lain.
- ID dibuat "final" dan tidak punya setter, karena ID tidak boleh berubah setelah objek dibuat.
- Setter yang dibuka ("public") hanya untuk data yang memang boleh diubah: "setStok" pada "Barang", "setNama" / "setAlamat" / "setNoTelepon" pada "Pemasok", dan "setTanggal" / "setAlamat" pada "Pengadaan".
- Setter "setNama" (pada "Barang"), "setGaransi", dan "setKategori" dibuat "private" karena hanya dipakai saat objek dibuat.
- Konsep yang sama diterapkan pada "Pemasok" dan "Pengadaan".

### **5.2 Inheritance**

**Barang** adalah superclass, sedangkan **BarangElektronik** dan **BarangNonElektronik** adalah subclass.

```
              Barang (abstract)
                 |
        ┌────────┴────────┐
        ↓                 ↓
BarangElektronik    BarangNonElektronik
```

- Barang Elektronik
- Barang Non Elektronik

Class BarangElektronik mewarisi class Barang menggunakan public class BarangElektronik extends Barang;

<img width="500" alt="image" src="https://github.com/user-attachments/assets/a9e7b2a6-3549-451d-8880-f5be65b3ec16" />

Sedangkan class BarangNonElektronik menggunakan public class BarangNonElektronik extends Barang;

<img width="500" alt="image" src="https://github.com/user-attachments/assets/b0aecf41-602d-4e58-86f9-94daac93dd93" />

Atribut umum berupa ID barang, nama, dan stok diletakkan pada superclass barang. Constructor-nya dibuat protected agar hanya dapat diakses oleh subclass melalui super.

<img width="500" alt="image" src="https://github.com/user-attachments/assets/fbef77f9-e921-459c-99a1-13b789826b46" />

Kemudian masing-masing subclass memiliki atribut khusus, yaitu;

- BarangElektronik memiliki atribut garansi

<img width="500" alt="image" src="https://github.com/user-attachments/assets/fd18c5d9-1100-4e9d-bc17-cd6169af8e14" />

- BarangNonElektronik memiliki atribut kategori

<img width="500" alt="image" src="https://github.com/user-attachments/assets/683c7375-ba97-4a33-889a-3f44d7104281" />

- Atribut umum ("idBarang", "nama", "stok") ditulis **sekali** di "Barang".
- Atribut khusus ada di subclass: "garansi" pada "BarangElektronik" dan "kategori" pada "BarangNonElektronik".
- Subclass memakai "super(...)" untuk menjalankan constructor milik "Barang".

Inheritance juga dipakai pada package view:

```
BaseView (abstract)
  ├── MenuView
  └── EntitasView<T> (abstract)
        ├── BarangView
        ├── PemasokView
        └── PengadaanView
```

---



## **BAB VI DUMMMY DATA PADA ARRAYLIST**

Program menyediakan dummy data awal di dalam ArrayList sehingga data sudah tersedia ketika fitur tampilkan data dijalankan.

Terdapat beberapa ArrayList yaitu;

<img width="500" alt="image" src="https://github.com/user-attachments/assets/627bc9b6-d223-43b7-8ae7-9695511fc2e1" />

---------------------------


<img width="500" alt="image" src="https://github.com/user-attachments/assets/8e8e7e47-4ad4-459e-b978-ff4e80e16b09" />

--------------------------


<img width="500" alt="image" src="https://github.com/user-attachments/assets/c5944366-741a-4fb2-aad4-1512abc19043" />

--------------------------


<img width="500" alt="image" src="https://github.com/user-attachments/assets/5d3c97d4-7d5f-44e7-bc97-781858e9ac03" />

--------------------------


<img width="500" alt="image" src="https://github.com/user-attachments/assets/05cabd8c-01d1-4dab-ac18-3a710c9e2218" />

--------------------------


Program kemudian memasukkan dummy data awal berupa;

<img width="500" alt="image" src="https://github.com/user-attachments/assets/fb424d10-0fcd-4665-9bd9-d14b5773a03c" />

----------------------------------

<img width="500" alt="image" src="https://github.com/user-attachments/assets/95f8b78d-dadb-4336-bee0-5421a8900150" />

----------------------------------

<img width="500" alt="image" src="https://github.com/user-attachments/assets/aced3ca2-47c8-48ab-a85c-597c761b275f" />

Dengan adanya dummy data tersebut, pengguna tidak perlu melakukan input terlebih dahulu untuk melihat data pada fitur read ataupun tampilkan Data.


## **BAB VII POLYMORPHISM DAN ABSTRACTION**

### **7.1 Abstraction**

#### Abstract class

| Abstract class | Lokasi | Fungsi |
|---|---|---|
| `Barang` | `model/Barang.java` | Kerangka umum barang. Tidak bisa dibuat langsung (`new Barang(...)` akan error), harus berupa barang elektronik atau non-elektronik. |
| `BaseView` | `view/BaseView.java` | Kerangka semua View: garis, judul, pesan, dan pembacaan input beserta validasinya. |
| `EntitasView<T>` | `view/EntitasView.java` | Kerangka View untuk satu jenis data: menu, input ID, dan daftar data. |

#### Abstract method

`model/Barang.java`:

<img width="500" alt="image" src="https://github.com/user-attachments/assets/962cf96a-9a29-4639-8419-4353ca84bc15" />


`view/EntitasView.java`:

<img width="500" alt="image" src="https://github.com/user-attachments/assets/9f6f97d5-d73a-45bf-82ee-9f1185c397d1" />


Method tanpa isi tersebut **wajib** diisi oleh subclass. Contoh pada `BarangElektronik`:

<img width="500" alt="image" src="https://github.com/user-attachments/assets/84db99a3-1a23-4ab6-a478-862ecb6ede40" />


dan pada `BarangNonElektronik`:

<img width="500" alt="image" src="https://github.com/user-attachments/assets/0729c1de-aafc-458c-8b95-36f6e8edcfd4" />



### **7.2 Polymorphism: Overriding**

| Lokasi | Method yang di-override | Keterangan |
|---|---|---|
| `BarangElektronik`, `BarangNonElektronik` | `getJenis()`, `getLabelDetail()`, `getNilaiDetail()` | Mengisi abstract method dari `Barang` |
| `BarangView`, `PemasokView`, `PengadaanView` | `namaEntitas()`, `tampilDetail()` | Mengisi abstract method dari `EntitasView` |
| `BarangView` | `labelUpdate()` | Mengubah teks menu menjadi "Update Stok" |
| `BarangView` | `daftarMenu()` | Menambah menu "Cari Barang" |
| `BarangView` | `tampilDaftar(List, String)` | Daftar barang dikelompokkan per jenis |
| `BarangController`, `PemasokController`, `PengadaanController` | `jalankanMenu()`, `tambah()`, `tampilkan()`, `hapus()`, `update()` | Mengisi kontrak interface `Kelola` |

**Dynamic polymorphism**: `ArrayList<Barang>` dapat menampung `BarangElektronik` maupun `BarangNonElektronik`. Saat ditampilkan, `BarangView` cukup memanggil method milik `Barang`, dan hasilnya otomatis menyesuaikan jenis objeknya:

<img width="500" alt="image" src="https://github.com/user-attachments/assets/4d4e63aa-e26e-4aad-be4c-8a125652c553" />


### **7.3 Polymorphism: Overloading**

| Lokasi | Method | Perbedaan parameter |
|---|---|---|
| `BarangController` | `cariBarang(int idBarang)` | Mencari berdasarkan ID, mengembalikan satu `Barang` |
| `BarangController` | `cariBarang(String keyword)` | Mencari berdasarkan nama, mengembalikan `List<Barang>` |
| `BaseView` | `bacaAngka(String label, int minimal)` | Membaca angka tanpa petunjuk |
| `BaseView` | `bacaAngka(String label, String petunjuk, int minimal)` | Membaca angka dengan petunjuk, misalnya "(0 untuk kembali)" |
| `BaseView` | `bacaTeks(String label)` | Membaca teks yang tidak boleh kosong |
| `BaseView` | `bacaTeks(String label, Predicate aturan, String pesan)` | Membaca teks dengan aturan tambahan (angka, tanggal) |
| `EntitasView` | `tampilDaftar(List data)` | Menampilkan daftar dengan judul bawaan |
| `EntitasView` | `tampilDaftar(List data, String judul)` | Menampilkan daftar dengan judul khusus ("HASIL PENCARIAN") |

Contoh pemakaian overloading `cariBarang` pada `BarangController`:

<img width="500" alt="image" src="https://github.com/user-attachments/assets/e4809921-a70a-416e-86c8-6e7ea66ee584" />


---

## **BAB VIII INTERFACE**

### **8.1 Letak Penerapan Interface**

| Berkas | Peran |
|---|---|
| `controller/Kelola.java` | **Interface** (kontrak) |
| `controller/BarangController.java` | `implements Kelola` |
| `controller/PemasokController.java` | `implements Kelola` |
| `controller/PengadaanController.java` | `implements Kelola` |
| `controller/MenuController.java` | Memakai tipe `Kelola` untuk memanggil ketiga controller |

### **8.2 Penjelasan**

Interface `Kelola` adalah kontrak yang wajib dipenuhi setiap controller data:

<img width="500" alt="image" src="https://github.com/user-attachments/assets/c2307470-3238-4a4e-b44e-9f990a17d2e7" />


Setiap controller menyetujui kontrak tersebut dengan `implements` dan wajib mengisi seluruh method-nya:

<img width="500" alt="image" src="https://github.com/user-attachments/assets/44f18901-62bb-4658-b1e9-53f678be5981" />


<img width="500" alt="image" src="https://github.com/user-attachments/assets/1614c37a-e7f3-4a81-b30c-a86bdb6808a3" />


<img width="500" alt="image" src="https://github.com/user-attachments/assets/a239e91b-6f05-40c5-9b3a-8641efbf0243" />


`MenuController` hanya mengenal kontraknya (apa yang dikerjakan), bukan cara tiap controller mengerjakannya:


<img width="500" alt="image" src="https://github.com/user-attachments/assets/7fadb034-eedf-42b2-88a8-c7f08e9e4a19" />


<img width="500" alt="image" src="https://github.com/user-attachments/assets/62aa0482-b5c0-41f5-86ad-baedd4414403" />


Dengan cara ini seluruh controller memiliki nama method yang seragam, dan controller baru (misalnya untuk data pelanggan) dapat ditambahkan tanpa mengubah cara `MenuController` memanggilnya.

---

## **BAB IX KESIMPULAN**

Program Sistem Manajemen CV Mandiri Prima Kreatif adalah aplikasi Java yang mengelola data barang, pemasok, dan pengadaan. Pada Mini Project 3, program dikembangkan dengan:

- **Abstraction**: `Barang` sebagai abstract class dengan abstract method, serta `BaseView` dan `EntitasView` sebagai kerangka View.
- **Polymorphism**: overriding pada subclass `Barang`, View, dan controller; overloading pada pencarian barang, pembacaan input, dan penampilan daftar.
- **MVC**: pemisahan package `model`, `view`, dan `controller` dengan `main` sebagai titik masuk.
- **Interface** (nilai tambah): `Kelola` sebagai kontrak seluruh controller data.

Dengan penerapan tersebut, program menjadi lebih terstruktur, tidak ada kode yang berulang, dan lebih mudah dikembangkan.
