Feature: Tambah siswa konsumsi
  Background:
    Given pengguna berada di halaman login
    When pengguna mengisi email dan password yang benar
    And menekan tombol login
    Then pengguna diarahkan ke halaman dashboard
    When pengguna klik tombol konsumsi
    Then pengguna diarahkan ke halaman monitoring konsumsi

  Scenario: Tambah siswa konsumsi dengan data valid
    When pengguna klik tombol tambah siswa
    Then pengguna diarahkan ke halaman tambah siswa konsumsi
    When pengguna menginputkan data konsumsi siswa
    And klik tombol simpan siswa
    Then pengguna diarahkan ke halaman monitoring konsumsi
    And pengguna melihat pesan sukses tambah siswa konsumsi

  Scenario: Tambah siswa konsumsi dengan data tidak valid
    When pengguna klik tombol tambah siswa
    Then pengguna diarahkan ke halaman tambah siswa konsumsi
    When pengguna menginputkan data konsumsi siswa yang tidak valid
    And klik tombol simpan siswa
    Then pengguna tetap berada di halaman tambah siswa konsumsi
    And pengguna melihat pesan error validasi