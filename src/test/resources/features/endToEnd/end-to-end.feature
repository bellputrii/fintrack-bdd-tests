Feature: End-to-end
  Background:
    Given pengguna berada di halaman login
    When pengguna mengisi email dan password yang benar
    And menekan tombol login
    Then pengguna diarahkan ke halaman dashboard

  Scenario: Tambah kontrak siswa praxis
    When pengguna klik tombol praxis academy di dashboard
    Then pengguna diarahkan ke halaman monitoring praxis academy
    When pengguna klik tombol tambah kontrak siswa praxis
    Then pengguna diarahkan ke halaman tambah kontrak siswa praxis academy
    When pengguna menginputkan data kontrak siswa praxis academy yang valid
    And pengguna klik tombol simpan kontrak praxis
    Then pengguna diarahkan ke halaman monitoring praxis academy

  Scenario: Tambah siswa konsumsi
    When pengguna klik tombol konsumsi
    Then pengguna diarahkan ke halaman monitoring konsumsi
    When pengguna klik tombol tambah siswa
    Then pengguna diarahkan ke halaman tambah siswa konsumsi
    When pengguna menginputkan data konsumsi siswa
    And klik tombol simpan siswa
    Then pengguna diarahkan ke halaman monitoring konsumsi
    And pengguna melihat pesan sukses tambah siswa konsumsi

  Scenario: Buat tagihan siswa
    When pengguna klik tombol tagihan di dashboard
    Then pengguna diarahkan ke halaman monitoring tagihan siswa
    When pengguna klik tombol tambah tagihan
    Then pengguna diarahkan ke halaman buat tagihan
    When pengguna menginputkan data tagihan siswa valid
    And klik tombol simpan dan cetak tagihan
    Then pengguna diarahkan kembali ke halaman monitoring tagihan siswa
    And data cetak tagihan siswa ditampilkan di tabel monitoring tagihan

  Scenario: Pembayaran siswa di praxis
    When pengguna klik tombol praxis academy di dashboard
    Then pengguna diarahkan ke halaman monitoring praxis academy
    When pengguna klik tombol pembayaran siswa praxis
    Then pengguna diarahkan ke halaman pembayaran siswa praxis academy
    When pengguna menginputkan data pembayaran praxis siswa yang valid
    And pengguna klik tombol simpan pembayaran praxis
    Then pengguna diarahkan ke halaman monitoring praxis academy

  Scenario: Pembayaran siswa konsumsi
    When pengguna klik tombol konsumsi
    Then pengguna diarahkan ke halaman monitoring konsumsi
    When pengguna klik tombol pembayaran siswa
    Then pengguna diarahkan ke halaman pembayaran siswa konsumsi
    When pengguna menginputkan data pembayaran konsumsi siswa
    And pengguna klik tombol simpan pembayaran
    Then pengguna diarahkan ke halaman monitoring konsumsi
    And pengguna melihat pesan sukses pembayaran siswa konsumsi

  Scenario: Logout
    Given pengguna berada di halaman login
    When pengguna mengisi email dan password yang benar
    And menekan tombol login
    Then pengguna diarahkan ke halaman dashboard
    When pengguna klik tombol logout
    Then pengguna berada di halaman login setelah logout