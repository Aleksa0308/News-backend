-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 22, 2022 at 03:29 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `news`
--

-- --------------------------------------------------------

--
-- Table structure for table `kategorije`
--

CREATE TABLE `kategorije` (
  `id` int(11) NOT NULL,
  `tip` varchar(15) NOT NULL,
  `opis` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kategorije`
--

INSERT INTO `kategorije` (`id`, `tip`, `opis`) VALUES
(1, 'Sport', 'Sve najnovije sportske vesti!'),
(2, 'Film', 'Sve najnovije vesti o filmovima!'),
(3, 'Igrice', 'Sve najnovije vesti u svetu Gameinga');

-- --------------------------------------------------------

--
-- Table structure for table `komentari`
--

CREATE TABLE `komentari` (
  `id` int(11) NOT NULL,
  `vestId` int(11) NOT NULL,
  `ime` varchar(50) NOT NULL,
  `tekst` varchar(250) NOT NULL,
  `datumKreiranja` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `komentari`
--

INSERT INTO `komentari` (`id`, `vestId`, `ime`, `tekst`, `datumKreiranja`) VALUES
(2, 1, 'Pera', 'Zanimljivo!', '2022-08-17 22:00:00'),
(7, 1, 'Darko', 'Bravoo Nollle!!', '2022-08-19 16:22:37'),
(8, 4, 'Pera', 'Omg jedva cekam!!!', '2022-08-19 16:23:45'),
(9, 4, 'Milica', 'Marvel filmovi smarajuuuuuuu', '2022-08-19 16:24:49'),
(15, 1, 'sofija', 'bravo noloo', '2022-08-21 20:33:58');

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE `tag` (
  `id` int(11) NOT NULL,
  `kljucnaRec` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `hashedPassword` varchar(500) NOT NULL,
  `email` varchar(50) NOT NULL,
  `role` varchar(20) NOT NULL,
  `status` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `hashedPassword`, `email`, `role`, `status`) VALUES
(1, 'Aleksa', '2ac9a6746aca543af8dff39894cfe8173afba21eb01c6fae33d52947222855ef', 'aleksa@gmail.com', 'user', 'active'),
(2, 'admin_user', '83cf8b609de60036a8277bd0e96135751bbc07eb234256d4b65b893360651bf2', 'admin@gmail.com', 'admin', 'active'),
(3, 'content_creator', 'f6e0a1e2ac41945a9aa7ff8a8aaa0cebc12a3bcc981a929ad5cf810a090e11ae', 'creator@gmail.com', 'creator', 'active'),
(6, 'TestUser', 'f6e0a1e2ac41945a9aa7ff8a8aaa0cebc12a3bcc981a929ad5cf810a090e11ae', 'test@gmail.com', 'creator', 'active'),
(11, 'sofija', '8bb0cf6eb9b17d0f7d22b456f121257dc1254e1f01665370476383ea776df414', 'sofija@gmail.com', 'user', 'active');

-- --------------------------------------------------------

--
-- Table structure for table `vesti`
--

CREATE TABLE `vesti` (
  `id` int(11) NOT NULL,
  `kategorijaId` int(11) NOT NULL,
  `naslov` varchar(150) NOT NULL,
  `tekst` varchar(1000) NOT NULL,
  `vremeKreiranja` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `brPoseta` int(11) NOT NULL,
  `autor` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `vesti`
--

INSERT INTO `vesti` (`id`, `kategorijaId`, `naslov`, `tekst`, `vremeKreiranja`, `brPoseta`, `autor`) VALUES
(1, 2, 'Novak Djokovic Tenis', 'Novak Djokovic pobedio negde tada i tada', '2022-08-20 16:13:12', 93, 'Pera Peric'),
(4, 2, 'Novi Marvel film', 'Novi Marvel film najavljen za sledecu godinu!', '2022-08-19 19:16:59', 100, 'Steva Stevic');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kategorije`
--
ALTER TABLE `kategorije`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `kategorije_tip_uindex` (`tip`),
  ADD UNIQUE KEY `kategorije_id_uindex` (`id`);

--
-- Indexes for table `komentari`
--
ALTER TABLE `komentari`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Komentari_id_uindex` (`id`);

--
-- Indexes for table `tag`
--
ALTER TABLE `tag`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `tag_kljucnaRec_uindex` (`kljucnaRec`),
  ADD UNIQUE KEY `tag_id_uindex` (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_email_uindex` (`email`),
  ADD UNIQUE KEY `users_username_uindex` (`username`),
  ADD UNIQUE KEY `users_id_uindex` (`id`);

--
-- Indexes for table `vesti`
--
ALTER TABLE `vesti`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `vest_naslov_uindex` (`naslov`),
  ADD UNIQUE KEY `vest_id_uindex` (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kategorije`
--
ALTER TABLE `kategorije`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `komentari`
--
ALTER TABLE `komentari`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `tag`
--
ALTER TABLE `tag`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `vesti`
--
ALTER TABLE `vesti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
