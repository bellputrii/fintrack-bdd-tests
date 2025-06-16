Feature: Tambah siswa Boarding
  Background:
    Given pengguna berada di halaman login
    When pengguna mengisi email dan password yang benar
    And menekan tombol login
    Then pengguna diarahkan ke halaman dashboard
    When pengguna klik tombol boarding
    Then pengguna diarahkan ke halaman monitoring boarding

  Scenario: Tambah siswa boarding dengan data valid
    When pengguna klik tombol tambah siswa
    Then pengguna diarahkan ke halaman tambah siswa boarding
    When pengguna menginputkan data boarding siswa
    And klik tombol simpan siswa
    Then pengguna diarahkan kembali ke halaman monitoring boarding
    And data boarding siswa ditampilkan

  Scenario: Tambah siswa boarding dengan data tidak valid
    When pengguna klik tombol tambah siswa
    Then pengguna diarahkan ke halaman tambah siswa boarding
    When pengguna menginputkan data boarding siswa yang tidak valid
    And klik tombol simpan siswa
    Then pengguna tetap berada di halaman tambah siswa boarding
    And pengguna melihat pesan error validasi