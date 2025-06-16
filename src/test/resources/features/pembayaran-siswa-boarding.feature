Feature: Pembayaran siswa Boarding

  Background:
    Given pengguna berada di halaman login
    When pengguna mengisi email dan password yang benar
    And pengguna menekan tombol login
    Then pengguna diarahkan ke halaman dashboard
    When pengguna mengklik menu boarding
    Then pengguna diarahkan ke halaman monitoring boarding

  Scenario: Pembayaran siswa boarding dengan data valid
    When pengguna mengklik tombol bayar untuk siswa eka
    Then pengguna diarahkan ke halaman pembayaran boarding
    When pengguna mengisi form pembayaran dengan data valid
    And pengguna mengklik tombol simpan
    Then pengguna diarahkan kembali ke halaman monitoring boarding
    When pengguna mengklik tombol riwayat untuk siswa eka
    Then pengguna diarahkan ke halaman riwayat pembayaran
    And pengguna melihat data pembayaran telah tercatat

  Scenario: Pembayaran siswa boarding dengan data tidak valid
    When pengguna mengklik tombol bayar untuk siswa eka
    Then pengguna diarahkan ke halaman pembayaran boarding
    When pengguna mengisi form pembayaran dengan data tidak valid
    And pengguna mengklik tombol simpan
    Then pengguna tetap berada di halaman pembayaran boarding
    And pengguna melihat pesan error validasi pembayaran
