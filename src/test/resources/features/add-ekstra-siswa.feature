Feature: Tambah Kontrak Siswa ke Monitoring Ekstra

  Background:
    Given pengguna sudah mengakses halaman Monitoring Ekstra

  Scenario: Berhasil menambah siswa ekstra dengan data valid
    When pengguna klik tombol Tambah Kontrak
    And pengguna memasukkan data siswa secara manual:
      | Field | Nilai  |
      | NISN  | 100016 |
    And pengguna memilih jenis ekstra
    And pengguna memilih tanggal mulai dan tanggal selesai
    And pengguna memasukkan catatan (jika ada):
      | Field   | Nilai |
      | Catatan |       |
    Then siswa berhasil ditambahkan ke halaman Monitoring Ekstra

  Scenario: Gagal menambah siswa ekstra karena data belum lengkap
    When pengguna klik tombol Tambah Kontrak
    And pengguna hanya memasukkan NISN "100099"
    And pengguna tidak mengisi data lain
    Then siswa ekstra tidak berhasil ditambahkan
    And pengguna melihat pesan kesalahan "Harap lengkapi semua field."

