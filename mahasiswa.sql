-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 25 Mar 2025 pada 14.20
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_mahasiswa`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `id` int(11) NOT NULL,
  `nim` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `jenis_kelamin` varchar(255) NOT NULL,
  `fakultas` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `mahasiswa`
--

INSERT INTO `mahasiswa` (`id`, `nim`, `nama`, `jenis_kelamin`, `fakultas`) VALUES
(1, '2203999', 'Amelia Zalfa Julianti', 'Perempuan', 'FPMIPA'),
(2, '2202292', 'Muhammad Iqbal Fadhilah', 'Laki-laki', 'FPIPS'),
(3, '2202346', 'Muhammad Rifky Afandi', 'Laki-laki', 'FPBS'),
(4, '2210239', 'Muhammad Hanif Abdillah', 'Laki-laki', 'FPEB'),
(5, '2202046', 'Nurainun', 'Perempuan', 'FPTK'),
(6, '2205101', 'Kelvin Julian Putra', 'Laki-laki', 'FPOK'),
(7, '2200163', 'Rifanny Lysara Annastasya', 'Perempuan', 'FPSD'),
(8, '2202869', 'Revana Faliha Salma', 'Perempuan', 'FPMIPA'),
(9, '2209489', 'Rakha Dhifiargo Hariadi', 'Laki-laki', 'FPIPS'),
(10, '2203142', 'Roshan Syalwan Nurilham', 'Laki-laki', 'FPBS'),
(11, '2200311', 'Raden Rahman Ismail', 'Laki-laki', 'FPEB'),
(12, '2200978', 'Ratu Syahirah Khairunnisa', 'Perempuan', 'FPTK'),
(13, '2204509', 'Muhammad Fahreza Fauzan', 'Laki-laki', 'FPOK'),
(14, '2205027', 'Muhammad Rizki Revandi', 'Laki-laki', 'FPSD'),
(15, '2203484', 'Arya Aydin Margono', 'Laki-laki', 'FPMIPA'),
(17, '2209889', 'Muhammad Fadlul Hafiizh', 'Laki-laki', 'FPBS'),
(18, '2206697', 'Rifa Sania', 'Perempuan', 'FPEB'),
(19, '2207260', 'Imam Chalish Rafidhul Haque', 'Laki-laki', 'FPTK'),
(20, '2204343', 'Meiva Labibah Putri', 'Perempuan', 'FPOK'),
(28, '2301895', 'Aditya Hartawan', 'Laki-Laki', 'FPIPS');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `mahasiswa`
--
ALTER TABLE `mahasiswa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
