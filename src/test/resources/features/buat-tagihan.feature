Feature: Buat Tagihan Siswa
  Background:
    Given pengguna berada di halaman login
    When pengguna mengisi email dan password yang benar
    And menekan tombol login
    Then pengguna diarahkan ke halaman dashboard
    When pengguna klik tombol tagihan
    Then pengguna diarahkan ke halaman riwayat tagihan siswa

  Scenario: Berhasil melakukan buat tagihan
    When pengguna klik tombol tambah tagihan
    Then pengguna diarahkan ke halaman form buat tagihan
    When pengguna menginputkan data siswa
    And klik tombol simpan dan cetak tagihan
    Then pengguna tetap berada di halaman form buat tagihan dan berhasil download PDF tagihan

  Scenario: Gagal melakukan buat tagihan
    When pengguna klik tombol tambah tagihan
    Then pengguna diarahkan ke halaman form buat tagihan
    When pengguna tidak menginputkan data siswa
    And klik tombol simpan dan cetak tagihan
    Then pengguna tetap berada di halaman form buat tagihan dan muncul eror validasi

