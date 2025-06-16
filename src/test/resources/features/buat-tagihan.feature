Feature: Buat Tagihan Siswa

  Background:
    Given pengguna berada di halaman login
    When pengguna mengisi email dan password yang benar
    And menekan tombol login
    Then pengguna diarahkan ke halaman dashboard
    When pengguna klik tombol tagihan di dashboard
    Then pengguna diarahkan ke halaman monitoring tagihan siswa

  Scenario: Berhasil melakukan buat tagihan valid
    When pengguna klik tombol tambah tagihan
    Then pengguna diarahkan ke halaman buat tagihan
    When pengguna menginputkan data tagihan siswa valid
    And klik tombol simpan dan cetak tagihan
    Then pengguna diarahkan kembali ke halaman monitoring tagihan siswa
    And data cetak tagihan siswa ditampilkan di tabel monitoring tagihan

  Scenario: Berhasil melakukan buat tagihan tidak valid
    When pengguna klik tombol tambah tagihan
    Then pengguna diarahkan ke halaman buat tagihan
    When pengguna menginputkan data tagihan siswa tidak valid
    And klik tombol simpan dan cetak tagihan
    Then pengguna tetap dihalaman buat tagihan
    And muncul pesan error "Data tagihan tidak valid"