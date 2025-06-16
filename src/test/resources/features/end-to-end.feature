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
    And pengguna melihat pesan sukses kontrak siswa praxis

  Scenario: Tambah siswa konsumsi
    When pengguna klik tombol konsumsi
    Then pengguna diarahkan ke halaman monitoring konsumsi
    When pengguna klik tombol tambah siswa
    Then pengguna diarahkan ke halaman tambah siswa konsumsi
    When pengguna menginputkan data konsumsi siswa
    And klik tombol simpan siswa
    Then pengguna diarahkan ke halaman monitoring konsumsi
    And pengguna melihat pesan sukses tambah siswa konsumsi

#  Scenario: Tambah siswa boarding

#  Scenario: Tambah siswa ekstra

#  Scenario: Buat tagihan siswa

  Scenario: Pembayaran siswa di praxis
    When pengguna klik tombol praxis academy di dashboard
    Then pengguna diarahkan ke halaman monitoring praxis academy
    When pengguna klik tombol pembayaran siswa praxis
    Then pengguna diarahkan ke halaman pembayaran siswa praxis academy
    When pengguna menginputkan data pembayaran praxis siswa yang valid
    And pengguna klik tombol simpan pembayaran praxis
    Then pengguna diarahkan ke halaman monitoring praxis academy
#    And data pembayaran siswa praxis academy ditampilkan di tabel monitoring
    And pengguna melihat pesan sukses pembayaran siswa praxis

#  Scenario: Pembayaran siswa boarding

  Scenario: Pembayaran siswa konsumsi
    When pengguna klik tombol konsumsi
    Then pengguna diarahkan ke halaman monitoring konsumsi
    When pengguna klik tombol pembayaran siswa
    Then pengguna diarahkan ke halaman pembayaran siswa konsumsi
    When pengguna menginputkan data pembayaran konsumsi siswa
    And pengguna klik tombol simpan pembayaran
    Then pengguna diarahkan ke halaman monitoring konsumsi
    And pengguna melihat pesan sukses pembayaran siswa konsumsi

#  Scenario: Pembayaran siswa ekstra
#  Scenario: Logout