Feature: Pembayaran siswa Praxis Academy

  Background:
    Given pengguna berada di halaman login
    When pengguna mengisi email dan password yang benar
    And menekan tombol login
    Then pengguna diarahkan ke halaman dashboard
    When pengguna klik tombol praxis academy di dashboard
    Then pengguna diarahkan ke halaman monitoring praxis academy

  Scenario: Pembayaran siswa praxis academy dengan data valid
    When pengguna klik tombol pembayaran siswa praxis
    Then pengguna diarahkan ke halaman pembayaran siswa praxis academy
    When pengguna menginputkan data pembayaran praxis siswa yang valid
    And pengguna klik tombol simpan pembayaran praxis
    Then pengguna diarahkan ke halaman monitoring praxis academy
    And data pembayaran siswa praxis academy ditampilkan di tabel monitoring

  Scenario: Pembayaran siswa praxis academy dengan data tidak valid
    When pengguna klik tombol pembayaran siswa praxis
    Then pengguna diarahkan ke halaman pembayaran siswa praxis academy
    When pengguna menginputkan data pembayaran praxis siswa yang tidak valid
    And pengguna klik tombol simpan pembayaran praxis
    Then pengguna tetap berada di halaman pembayaran siswa praxis academy
    And pengguna melihat pesan error validasi pembayaran praxis