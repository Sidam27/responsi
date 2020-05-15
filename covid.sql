-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2020 at 03:06 AM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `covid`
--

-- --------------------------------------------------------

--
-- Table structure for table `penambahan`
--

CREATE TABLE `penambahan` (
  `id` int(11) NOT NULL,
  `id_daerah` int(11) NOT NULL,
  `hari` int(11) NOT NULL,
  `odp` int(11) NOT NULL,
  `pdp` int(11) NOT NULL,
  `positif` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `penambahan`
--

INSERT INTO `penambahan` (`id`, `id_daerah`, `hari`, `odp`, `pdp`, `positif`) VALUES
(1, 1, 1, 5, 2, 0),
(2, 1, 2, 10, 1, 2),
(3, 2, 2, 10, 2, 1),
(4, 2, 3, 1, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `pengurangan`
--

CREATE TABLE `pengurangan` (
  `id` int(11) NOT NULL,
  `id_daerah` int(11) NOT NULL,
  `hari` int(11) NOT NULL,
  `odp` int(11) NOT NULL,
  `pdp` int(11) NOT NULL,
  `positif` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengurangan`
--

INSERT INTO `pengurangan` (`id`, `id_daerah`, `hari`, `odp`, `pdp`, `positif`) VALUES
(1, 1, 2, 2, 1, 0),
(2, 1, 3, 0, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `total`
--

CREATE TABLE `total` (
  `id_daerah` int(11) NOT NULL,
  `nama_daerah` varchar(255) NOT NULL,
  `odp` int(11) NOT NULL,
  `pdp` int(11) NOT NULL,
  `positif` int(11) NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `total`
--

INSERT INTO `total` (`id_daerah`, `nama_daerah`, `odp`, `pdp`, `positif`, `status`) VALUES
(1, 'Jakarta', 13, 2, 1, 'merah'),
(2, 'Bandung', 11, 2, 1, 'merah'),
(3, 'Yogyakarta', 0, 0, 0, 'hijau');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `penambahan`
--
ALTER TABLE `penambahan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_daerah` (`id_daerah`);

--
-- Indexes for table `pengurangan`
--
ALTER TABLE `pengurangan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_daerah2` (`id_daerah`);

--
-- Indexes for table `total`
--
ALTER TABLE `total`
  ADD PRIMARY KEY (`id_daerah`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `penambahan`
--
ALTER TABLE `penambahan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `pengurangan`
--
ALTER TABLE `pengurangan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `total`
--
ALTER TABLE `total`
  MODIFY `id_daerah` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `penambahan`
--
ALTER TABLE `penambahan`
  ADD CONSTRAINT `id_daerah` FOREIGN KEY (`id_daerah`) REFERENCES `total` (`id_daerah`);

--
-- Constraints for table `pengurangan`
--
ALTER TABLE `pengurangan`
  ADD CONSTRAINT `id_daerah2` FOREIGN KEY (`id_daerah`) REFERENCES `total` (`id_daerah`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
