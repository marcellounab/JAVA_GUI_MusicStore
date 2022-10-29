-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 16, 2022 at 07:43 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `clay_music_store`
--

-- --------------------------------------------------------

--
-- Table structure for table `history_detail`
--

CREATE TABLE `history_detail` (
  `history_id` int(11) NOT NULL,
  `music_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `history_detail`
--

INSERT INTO `history_detail` (`history_id`, `music_id`) VALUES
(41, 57),
(41, 64),
(41, 61),
(41, 65),
(41, 55),
(41, 58),
(42, 64),
(42, 56),
(42, 66),
(42, 55),
(42, 58),
(42, 55),
(42, 66),
(42, 79),
(42, 80),
(42, 58),
(42, 62),
(42, 82),
(42, 80),
(42, 77),
(42, 73),
(42, 70),
(42, 70),
(42, 76),
(42, 81),
(42, 82),
(42, 58),
(42, 81),
(42, 62),
(42, 70),
(42, 64),
(42, 78),
(42, 66),
(42, 61),
(42, 60),
(42, 55),
(42, 80),
(42, 67),
(42, 73),
(42, 81),
(42, 63),
(42, 82),
(42, 59),
(42, 76),
(42, 73),
(42, 82),
(42, 80),
(42, 59),
(42, 78),
(42, 78),
(42, 81),
(42, 80),
(42, 59),
(42, 65),
(42, 75),
(42, 56),
(49, 61),
(49, 70),
(51, 64),
(51, 76),
(51, 80),
(55, 76),
(55, 82),
(55, 59),
(55, 76),
(55, 82),
(55, 59),
(56, 59),
(56, 82),
(56, 76),
(56, 56),
(56, 68),
(56, 76),
(56, 80),
(56, 76),
(56, 80),
(56, 74),
(56, 74),
(60, 76),
(60, 80),
(60, 57),
(60, 56);

-- --------------------------------------------------------

--
-- Table structure for table `history_header`
--

CREATE TABLE `history_header` (
  `id` int(11) NOT NULL,
  `total_purchase` int(11) NOT NULL,
  `date_purchase` date NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `history_header`
--

INSERT INTO `history_header` (`id`, `total_purchase`, `date_purchase`, `user_id`) VALUES
(41, 75, '2022-01-15', 3),
(42, 124, '2022-01-15', 3),
(49, 55, '2022-01-15', 3),
(50, 69, '2022-01-15', 3),
(51, 167, '2022-01-15', 3),
(55, 132, '2022-01-15', 4),
(56, 42, '2022-01-15', 4),
(60, 157, '2022-01-15', 5);

-- --------------------------------------------------------

--
-- Table structure for table `musics`
--

CREATE TABLE `musics` (
  `id` int(11) NOT NULL,
  `music_name` varchar(100) DEFAULT NULL,
  `music_genre_id` int(11) NOT NULL,
  `music_price` int(11) NOT NULL,
  `music_artist_name` varchar(100) NOT NULL,
  `release_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `musics`
--

INSERT INTO `musics` (`id`, `music_name`, `music_genre_id`, `music_price`, `music_artist_name`, `release_date`) VALUES
(55, 'Lilac', 19, 10, 'IU', '2007-07-09'),
(56, 'Design', 22, 9, 'IU', '2007-08-01'),
(57, 'Ah Puh', 20, 10, 'Snarky Pup', '2008-08-09'),
(58, 'Marvin Gaye', 24, 25, 'Charlie Puth', '2009-07-09'),
(59, 'Dean Town', 23, 8, 'Vulfpeck', '2020-04-09'),
(60, 'Shotgun', 19, 30, 'yellow claw', '2020-07-09'),
(61, 'IDGAF', 19, 10, 'Dua lipa', '2019-12-30'),
(62, 'Cant hold us', 24, 28, 'Maclemore', '2009-08-07'),
(63, 'Love story', 23, 50, 'Taylor Swift', '2012-09-28'),
(64, 'Lights down low', 20, 10, 'Your babe', '2007-09-08'),
(65, 'Home', 19, 10, 'Michael buble', '2018-07-09'),
(66, 'Coin', 19, 70, 'IU', '2022-01-15'),
(67, 'Holiday', 20, 79, 'Kim yonk', '2022-01-15'),
(68, 'Hijjack', 20, 87, 'Lamborgi', '2022-01-15'),
(70, 'SomeDay', 20, 45, 'Kelvin', '2022-01-15'),
(71, 'Cintaku', 20, 51, 'Raisa', '2022-01-15'),
(72, 'BIBBI', 20, 89, 'IU', '2022-01-15'),
(73, 'Killing me ', 20, 34, 'Raya', '2022-01-15'),
(74, 'Dancing In The Dark', 20, 40, 'Joji', '2022-01-15'),
(75, 'My love', 21, 9, 'Taylor Swift', '2022-01-15'),
(76, 'Mean', 21, 90, 'Taylor Swift', '2022-01-15'),
(77, 'Bad Blood', 21, 78, 'Taylor Swift', '2022-01-15'),
(78, 'Bitter Sweet', 21, 45, 'Arditho Pramono', '2022-01-15'),
(79, 'Go', 22, 45, 'Justin Bieber', '2022-01-15'),
(80, 'Mirror', 22, 67, 'Justin Timberlake', '2022-01-15'),
(81, 'Ghost', 22, 87, 'Justin Bieber', '2022-01-15'),
(82, 'War Paint', 23, 34, 'Niki', '2022-01-15'),
(83, 'Afgan', 23, 4, 'Cinta ini membunuh mu', '2022-01-17');

-- --------------------------------------------------------

--
-- Table structure for table `music_genres`
--

CREATE TABLE `music_genres` (
  `id` int(11) NOT NULL,
  `genre_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `music_genres`
--

INSERT INTO `music_genres` (`id`, `genre_name`) VALUES
(19, 'EDM'),
(20, 'Pop'),
(21, 'Funk'),
(22, 'Soul'),
(23, 'Jazz'),
(24, 'Rock'),
(25, 'Sport');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` int(11) NOT NULL,
  `gender` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `email`, `password`, `role`, `gender`) VALUES
(1, 'admin', 'admin@email.com', 'admin', 1, 'none'),
(3, 'marcello', 'marcello@yahoo.co.id', '12', 2, 'Male'),
(4, 'budi', 'budi@email.com', 'budi', 2, 'Female'),
(5, 'marjan', 'marjan@email.com', 'qw', 2, 'Female');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `history_detail`
--
ALTER TABLE `history_detail`
  ADD KEY `history_detail_history_header_id_fk` (`history_id`),
  ADD KEY `history_detail_musics_id_fk` (`music_id`);

--
-- Indexes for table `history_header`
--
ALTER TABLE `history_header`
  ADD PRIMARY KEY (`id`),
  ADD KEY `history_header_users_id_fk` (`user_id`);

--
-- Indexes for table `musics`
--
ALTER TABLE `musics`
  ADD PRIMARY KEY (`id`),
  ADD KEY `musics_music_genres_id_fk` (`music_genre_id`);

--
-- Indexes for table `music_genres`
--
ALTER TABLE `music_genres`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `history_header`
--
ALTER TABLE `history_header`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT for table `musics`
--
ALTER TABLE `musics`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=84;

--
-- AUTO_INCREMENT for table `music_genres`
--
ALTER TABLE `music_genres`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `history_detail`
--
ALTER TABLE `history_detail`
  ADD CONSTRAINT `history_detail_history_header_id_fk` FOREIGN KEY (`history_id`) REFERENCES `history_header` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `history_detail_musics_id_fk` FOREIGN KEY (`music_id`) REFERENCES `musics` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `history_header`
--
ALTER TABLE `history_header`
  ADD CONSTRAINT `history_header_users_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `musics`
--
ALTER TABLE `musics`
  ADD CONSTRAINT `musics_music_genres_id_fk` FOREIGN KEY (`music_genre_id`) REFERENCES `music_genres` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
