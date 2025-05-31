Feature: Tambah Kontrak Siswa Praxis

  Background:
    Given pengguna sudah mengakses halaman monitoring Praxis

  Scenario: Berhasil menambah kontrak siswa dengan data valid
    When pengguna klik tombol Tambah Siswa
    And pengguna memasukkan NISN "100001" sehingga data terkait terisi otomatis
    And pengguna memasukkan nominal jenis tagihan sesuai kontrak:
      | Jenis       | Nominal   |
      | KBM         | 1000000   |
      | Pemeliharaan| 1000000   |
      | SPP         | 1000000   |
      | Sumbangan   | 1000000   |
    And pengguna mengupload file kontrak valid berukuran kurang dari 5 MB dengan format PDF
    Then kontrak siswa berhasil ditambahkan

  Scenario: Gagal menambah kontrak siswa karena file kontrak melebihi ukuran maksimal
    When pengguna klik tombol Tambah Siswa
    And pengguna memasukkan NISN "100001" sehingga data terkait terisi otomatis
    And pengguna memasukkan nominal jenis tagihan sesuai kontrak:
      | Jenis       | Nominal   |
      | KBM         | 1000000   |
      | Pemeliharaan| 1000000   |
      | SPP         | 1000000   |
      | Sumbangan   | 1000000   |
    And pengguna mengupload file kontrak dengan ukuran lebih dari 10 MB dengan format PDF
    Then kontrak siswa tidak berhasil ditambahkan
    And pengguna melihat pesan kesalahan "Ukuran file melebihi batas maksimal 5 MB"
