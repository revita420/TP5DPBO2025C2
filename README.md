# Janji
Saya Syahraini Revita Puri dengan NIM 2301895 berjanji mengerjakan TP5 DPBO dengan keberkahan-Nya, maka saya tidak akan melakukan kecurangan sesuai yang telah di spesifikasikan, Aamiin

# Desain Program
Program ini adalah aplikasi manajemen data mahasiswa dengan GUI (Graphical User Interface) menggunakan Java Swing dan koneksi database MySQL. **Komponen utama program**:


**1.Class Database**: 

- **Tujuan**: Mengelola koneksi dan opeasi database

- **Metode Utama**:
  - `selectQuery() : Mengambil data dari database
  - `insertUpdateDeleteQuery()` : Melakukan operasi insert, update, atau delete


**2. Class Mahasiswa**:

- **Tujuan:** Merepresentasikan struktur data mahasiswa

- **Atribut:**
  - `id` : int untuk id mahasiswa
  - `nim` : string untuk nim 
  - `nama` : string untuk nama mahasiswa
  - `jenisKelamin` : string untuk jenis kelamin mahasiswa
  - `fakultas` : string untuk fakultas mahasiswa

- **Atribut:**
  - `selectedIndex`: Integer untuk menyimpan indeks baris yang dipilih
  - `listMahasiswa`: ArrayList yang menyimpan objek Mahasiswa


# Alur Program
**1.Inisialisasi Program:**
- Program dimulai dari method main()
- Membuat instance Menu
- Background berwarna pink pastel (RGB: 255, 209, 220)

**2.Koneksi Database:**
- Membuat objek Database
- Melakukan koneksi ke database MySQL
- Mengambil data awal mahasiswa dari tabel

**3.Tabel:**
- Method setTable() digunakan untuk mengambil data dari database
- Membuat model tabel dengan kolom
  - No
  - NIM
  - Nama
  - Jenis Kelamin
  - Fakultas

**4.Operasi CRUD:**

**a. Tambah Data (Insert)**: 
- Method insertData()
- Validasi input :
  - Cek kelengkapan field
  - Cek duplikasi NIM
- Eksekusi query INSERT ke database
- Tampilkan pesan sukses

**b. Updata Data:**
- Method updateData()
- Validasi input :
  - Cek kelengkapan field
  - Cek duplikasi NIM
- Ambil id database dari baris terpilih
- Eksekusi query UPDATE ke database
- Tampilkan pesan sukses

**c.Hapus Data:**
- Method deleteData()
- Konfirmasi penghapusan
- Ambil id database dari baris terpilih
- Eksekusi query DELETE ke database
- Tampilkan pesan sukses

# Dokumentasi
**Menambahkan data**
![WhatsApp Image 2025-03-25 at 19 17 16_d52d8597](https://github.com/user-attachments/assets/a306fe30-0c25-4753-9b45-58c5aa82616d)

**Pesan berhasil**
![WhatsApp Image 2025-03-25 at 19 17 42_556fedf3](https://github.com/user-attachments/assets/fe719882-b164-4e54-9b35-555e4848d714)

**Update tanpa mengisi salah satu field**
![WhatsApp Image 2025-03-25 at 19 18 22_d27e706c](https://github.com/user-attachments/assets/4c1be5e1-1758-471b-bc4e-d75e93bf9791)

**Warning**
![WhatsApp Image 2025-03-25 at 19 18 37_7edf1372](https://github.com/user-attachments/assets/299027a0-04f9-447b-a21e-de61a4488649)

**Update dengan benar**
![WhatsApp Image 2025-03-25 at 19 19 08_4e3fee3d](https://github.com/user-attachments/assets/acd4c77a-4205-4a50-afe4-17698d132da4)

**Pesan berhasil**
![WhatsApp Image 2025-03-25 at 19 19 26_f91e13ed](https://github.com/user-attachments/assets/9442e3f2-e62d-49fd-b227-68df3dafda1b)

**Menambahkan data baru dengan NIM yg sudah ada**
![WhatsApp Image 2025-03-25 at 19 19 59_29298f8c](https://github.com/user-attachments/assets/2576d946-2f58-482e-b31b-9a0e0daee58c)

**Error**
![WhatsApp Image 2025-03-25 at 19 20 18_67882129](https://github.com/user-attachments/assets/52dd50db-d1a0-4fb9-9695-83b28c6bfa17)

**Menghapus salah satu data**
![WhatsApp Image 2025-03-25 at 19 21 08_85544d49](https://github.com/user-attachments/assets/3f571605-9e03-45f5-b9ba-2ab582d54349)

**Konfirmasi**
![WhatsApp Image 2025-03-25 at 19 21 25_954dde54](https://github.com/user-attachments/assets/0cef379a-75d0-4446-9d37-4b6f834f0f98)

**Pesan berhasil**
![WhatsApp Image 2025-03-25 at 19 21 47_b16e75bc](https://github.com/user-attachments/assets/7ac6568c-ee99-43dc-832a-8a5fc96bd3d9)



