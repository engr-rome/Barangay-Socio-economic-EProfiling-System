-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 10, 2024 at 04:37 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `household`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `house_number` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`house_number`, `username`, `password`) VALUES
(1, 'admin', 'admin123');

-- --------------------------------------------------------

--
-- Table structure for table `household`
--

CREATE TABLE `household` (
  `number` int(11) NOT NULL,
  `representative` varchar(100) NOT NULL,
  `house_number` int(100) NOT NULL,
  `zone` varchar(100) NOT NULL,
  `phoneNumber` varchar(100) NOT NULL,
  `householdMembers` int(100) NOT NULL,
  `employed` varchar(100) NOT NULL,
  `image` varchar(100) NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `household`
--

INSERT INTO `household` (`number`, `representative`, `house_number`, `zone`, `phoneNumber`, `householdMembers`, `employed`, `image`, `date`) VALUES
(26, 'Jerome Panaga', 16, '1', '09357288132', 5, '3', 'C:\\Users\\QUEANA WESLEY\\Downloads\\EMOREJ.jpg', '2024-06-03'),
(35, 'lemuel castillo', 12, '12', '12345678910', 12, '12', 'C:\\\\Users\\\\QUEANA WESLEY\\\\OneDrive\\\\Pictures\\\\Camera Roll\\\\WIN_20220803_23_19_50_Pro.jpg', '2024-06-09'),
(36, 'tan pallagao', 23, '23', '12312312345', 23, '23', 'C:\\\\Users\\\\QUEANA WESLEY\\\\OneDrive\\\\Pictures\\\\Camera Roll\\\\WIN_20220804_22_42_00_Pro.jpg', '2024-06-09'),
(37, 'jarel marabut', 34, '34', '12341234123', 34, '34', 'C:\\\\Users\\\\QUEANA WESLEY\\\\OneDrive\\\\Pictures\\\\Camera Roll\\\\WIN_20220805_17_10_36_Pro.jpg', '2024-06-09'),
(38, 'queana lasap', 45, '45', '12345123451', 45, '45', 'C:\\\\Users\\\\QUEANA WESLEY\\\\OneDrive\\\\Pictures\\\\Camera Roll\\\\WIN_20230802_18_28_55_Pro (2).jpg', '2024-06-09'),
(39, 'cee', 56, '56', '565656565656', 56, '56', 'C:\\\\Users\\\\QUEANA WESLEY\\\\OneDrive\\\\Pictures\\\\Camera Roll\\\\WIN_20220826_00_51_28_Pro.jpg', '2024-06-10');

-- --------------------------------------------------------

--
-- Table structure for table `householdinfo`
--

CREATE TABLE `householdinfo` (
  `number` int(11) NOT NULL,
  `representative` varchar(100) NOT NULL,
  `house_number` int(100) NOT NULL,
  `zone` varchar(100) NOT NULL,
  `phoneNumber` varchar(100) NOT NULL,
  `income` varchar(100) NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `householdinfo`
--

INSERT INTO `householdinfo` (`number`, `representative`, `house_number`, `zone`, `phoneNumber`, `income`, `date`) VALUES
(20, 'Jerome Panaga', 16, '1', '09357288132', '100k - 500k', NULL),
(29, 'lemuel castillo', 12, '12', '12345678910', '500k - 1M', NULL),
(30, 'tan pallagao', 23, '23', '12312312345', '1M - 2M', NULL),
(31, 'jarel marabut', 34, '34', '12341234123', '100k - 500k', NULL),
(32, 'queana lasap', 45, '45', '12345123451', '0', NULL),
(33, 'cee', 56, '56', '565656565656', '0', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`house_number`);

--
-- Indexes for table `household`
--
ALTER TABLE `household`
  ADD PRIMARY KEY (`number`);

--
-- Indexes for table `householdinfo`
--
ALTER TABLE `householdinfo`
  ADD PRIMARY KEY (`number`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `house_number` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `household`
--
ALTER TABLE `household`
  MODIFY `number` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT for table `householdinfo`
--
ALTER TABLE `householdinfo`
  MODIFY `number` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
