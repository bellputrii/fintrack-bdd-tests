Feature: Pembayaran Ekstrakurikuler

  Background:
    Given pengguna berada di halaman login
    When pengguna mengisi email dan password yang benar
    And menekan tombol login
    Then pengguna diarahkan ke halaman dashboard

  Scenario: Pembayaran ekstra berhasil dilakukan
    When User klik tombol ekstrakurikuler
    Then User diarahkan ke halaman monitoring Ekstrakurikuler
    When User memilih Level X dari dropdown untuk pembayaran ekstra
    And User klik tombol bayar ekstra
    And User mengisi tanggal pembayaran dengan "2025-07-15"
    And User mengisi nominal pembayaran dengan "50000"
    And User mengisi catatan pembayaran dengan "Pembayaran Juli"
    And User menekan tombol simpan pembayaran
    Then User melihat konfirmasi pembayaran berhasil

