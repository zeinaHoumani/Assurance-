-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 15, 2017 at 06:38 PM
-- Server version: 10.1.8-MariaDB
-- PHP Version: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `insurance`
--

-- --------------------------------------------------------

--
-- Table structure for table `car_info`
--

CREATE TABLE `car_info` (
  `id` int(20) NOT NULL,
  `model` varchar(255) DEFAULT NULL,
  `parked` varchar(255) DEFAULT NULL,
  `accident` varchar(255) DEFAULT NULL,
  `security` varchar(255) DEFAULT NULL,
  `modified` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `car_info`
--

INSERT INTO `car_info` (`id`, `model`, `parked`, `accident`, `security`, `modified`) VALUES
(1, 'BMW 2', 'SomeWhere else', 'yes', 'Alarm', 'yes'),
(27, 'Porsche turbo', 'in a garage ', 'no', 'No Alarm ', 'yes'),
(28, 'BMW 6', 'in a garage ', 'no', 'No Alarm ', 'yes'),
(29, 'Porsche Panamera', 'in a garage ', 'no', 'No Alarm ', 'yes'),
(30, 'Porsche macan', 'in a garage ', 'yes', 'No Alarm ', 'yes'),
(31, 'Porsche turbo', 'in a garage ', 'yes', 'No Alarm ', 'yes'),
(32, 'Porsche turbo', 'in a garage ', 'yes', 'No Alarm ', 'yes'),
(33, 'Porsche turbo', 'in a garage ', 'yes', 'No Alarm ', 'yes'),
(34, 'Porsche turbo', 'Under a carport', 'yes', 'No Alarm ', 'yes'),
(35, 'Porsche turbo', 'in a garage ', 'no', 'No Alarm ', 'yes'),
(36, 'BMW M2 (F87)', 'in a garage ', 'no', 'No Alarm ', 'yes'),
(37, 'Porsche Panamera', 'in a garage ', 'no', 'No Alarm ', 'yes'),
(38, 'BMW M2 (F87)', 'in a garage ', 'no', 'No Alarm ', 'yes'),
(39, 'BMW 6', 'in a garage ', 'no', 'No Alarm ', 'yes'),
(40, 'BMW i8', 'in a garage ', 'no', 'No Alarm ', 'yes'),
(41, 'BMW 6', 'in a garage ', 'no', 'No Alarm ', 'yes'),
(42, 'BMW 6', 'in a garage ', 'no', 'No Alarm ', 'yes'),
(43, 'BMW M2 (F87)', 'in a garage ', 'no', 'No Alarm ', 'yes'),
(44, 'Porsche turbo', 'in a garage ', 'yes', 'No Alarm ', 'yes'),
(45, 'Porsche Panamera', 'in a garage ', 'yes', 'No Alarm ', 'yes'),
(46, 'Porsche macan', 'in a garage ', 'yes', 'No Alarm ', 'yes'),
(47, 'Porsche macan', 'in a garage ', 'yes', 'No Alarm ', 'yes'),
(48, 'Porsche Panamera', 'in a garage ', 'no', 'No Alarm ', 'yes'),
(49, 'Porsche turbo', 'Under a carport', 'yes', 'No Alarm ', 'yes');

-- --------------------------------------------------------

--
-- Table structure for table `car_model`
--

CREATE TABLE `car_model` (
  `model` varchar(255) NOT NULL,
  `company` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `car_model`
--

INSERT INTO `car_model` (`model`, `company`, `image`, `year`) VALUES
('BMW 2', 'BMW', 'BMW-2.jpg', '2014'),
('BMW 6', 'BMW', 'BMW-6.jpg', '2015'),
('BMW i8', 'BMW', 'BMW-i8.jpg', '2014'),
('BMW M2 (F87)', 'BMW', 'BMW-M2.jpg', '2015'),
('Porsche macan', 'Porsche', 'Porsche-macan.jpg', '2014'),
('Porsche Panamera', 'Porsche', 'Porsche-Panamera.jpg', '2014'),
('Porsche turbo', 'Porsche', 'Porsche-turbo.jpg', '2014');

-- --------------------------------------------------------

--
-- Table structure for table `clients`
--

CREATE TABLE `clients` (
  `clientID` int(11) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `fathername` varchar(255) NOT NULL,
  `mothername` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `job` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `age` varchar(255) NOT NULL,
  `birthplace` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clients`
--

INSERT INTO `clients` (`clientID`, `firstname`, `lastname`, `fathername`, `mothername`, `address`, `job`, `phone`, `state`, `age`, `birthplace`) VALUES
(1, 'zeina', 'houmani', 'khalil houmani', 'amina choeuib', 'doueir', 'doctor', '71887958', 'single', '20', 'saida'),
(4, 'alaa', 'salemeh', 'hassan taha', 'mariam Adam', 'nabatieh', 'engineer', '03333721', 'married', '40', 'nabatieh'),
(9, 'yara', 'kadi', 'mohammad kadi', 'sanaa shoeib', 'beyrout', 'nurse', '71234569', 'married', '30', 'tripoli'),
(10, 'alya', 'salemeh', 'hassan taha', 'mariam Adam', 'nabatieh', 'engineer', '03333721', 'married', '25', 'sour'),
(24, 'Amira ', 'kanso', 'fadel kanso', 'layla chalhoub', 'sour', 'teacher', '03333721', 'married', '30', 'sour'),
(26, 'zeinab', 'hammoud', 'hassan chaaban', 'Alia taha', 'Nabatieh', 'nurse', '01236547', 'single', '42', 'Saida'),
(27, 'hadi', 'hoteit', 'hassan hoteit', 'zeinab ahmad', 'beyrout', 'nurse', '03125478', 'married', '26', 'sour'),
(28, 'TEST', 'TEST', 'TEST', 'TEST', 'TEST', 'TEST', '03333333', 'TEST', '20', 'TEST');

-- --------------------------------------------------------

--
-- Table structure for table `client_disease`
--

CREATE TABLE `client_disease` (
  `clientID` int(20) NOT NULL,
  `disease` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client_disease`
--

INSERT INTO `client_disease` (`clientID`, `disease`) VALUES
(6, 'BloodPressure'),
(6, 'cancer'),
(6, 'Cholesterol'),
(7, 'HeartProblem'),
(7, 'Depression or Anxiety'),
(7, 'Diabetes'),
(7, 'alcohol'),
(24, 'BloodPressure'),
(24, 'bla'),
(29, 'bla'),
(30, 'bla');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `employeeID` int(50) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`employeeID`, `firstname`, `lastname`, `mail`, `phone`) VALUES
(1, 'Saly', 'Hawa', 'Saly@gmail.com', '71548978'),
(2, 'Ali', 'baraket', 'Ali.baraket@live.com', '03412589');

-- --------------------------------------------------------

--
-- Table structure for table `insurance`
--

CREATE TABLE `insurance` (
  `id` int(20) NOT NULL,
  `type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `insurance`
--

INSERT INTO `insurance` (`id`, `type`) VALUES
(1, 'Life insurance'),
(2, 'Hospitalization insurance '),
(3, 'Car insurance');

-- --------------------------------------------------------

--
-- Table structure for table `licence`
--

CREATE TABLE `licence` (
  `id` int(20) NOT NULL,
  `licence_type` varchar(255) DEFAULT NULL,
  `licence_nb` varchar(255) DEFAULT NULL,
  `licence_to` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `licence`
--

INSERT INTO `licence` (`id`, `licence_type`, `licence_nb`, `licence_to`) VALUES
(1, 'C Car', '1234', '10 \\Years'),
(41, 'MR Medium rigid', '787878', '   10 / Years'),
(42, 'LR Light rigid', '1111111', '7    / Years'),
(43, 'LR Light rigid', '4587', '  20  / Years'),
(44, 'LR Light rigid', '789456', '   10 / Years'),
(45, 'C Car', '787887', '  10  / Years'),
(46, 'C Car', '7878', '  10  / Years'),
(47, 'C Car', '44444', '    / Years'),
(48, 'C Car', '21555', '   2020 / Years'),
(49, 'C Car', '1255', '    / Years'),
(50, 'C Car', '444', '    / Years'),
(51, 'LR Light rigid', '125478', '   10 / Years'),
(52, 'C Car', '555', '    / Years');

-- --------------------------------------------------------

--
-- Table structure for table `policy`
--

CREATE TABLE `policy` (
  `policyID` int(11) NOT NULL,
  `clientID` int(20) NOT NULL,
  `employeeID` int(20) DEFAULT NULL,
  `type` varchar(20) NOT NULL,
  `amount` varchar(255) DEFAULT NULL,
  `term` varchar(255) DEFAULT NULL,
  `date` varchar(255) NOT NULL,
  `first_Benef` varchar(255) DEFAULT NULL,
  `Second_Benef` varchar(255) DEFAULT NULL,
  `Applicant_name` varchar(255) DEFAULT NULL,
  `Applicant_phone` varchar(255) DEFAULT NULL,
  `hosp_type` varchar(255) DEFAULT '-',
  `licence` varchar(255) DEFAULT NULL,
  `car_info` varchar(255) DEFAULT NULL,
  `policy_start` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `policy`
--

INSERT INTO `policy` (`policyID`, `clientID`, `employeeID`, `type`, `amount`, `term`, `date`, `first_Benef`, `Second_Benef`, `Applicant_name`, `Applicant_phone`, `hosp_type`, `licence`, `car_info`, `policy_start`) VALUES
(11, 4, 1, '2', '75 $', NULL, '2016-12-30', NULL, NULL, NULL, NULL, 'Guaranteed accident', NULL, '44', NULL),
(19, 1, 1, '1', '500 $', '5 Year Term', 'A', 'A', 'A', 'Insured Name', 'Insured Phone', NULL, NULL, '44', NULL),
(20, 7, 1, '1', '', '5 Year Term', 's', 's', 's', 'Insured Name', 'Insured Phone', 'Guaranteed accident', NULL, '41', NULL),
(23, 9, 1, '2', '150 $', NULL, '2016-12-30', NULL, NULL, NULL, NULL, 'circulation accident', NULL, '41', NULL),
(28, 10, 2, '2', '75$', NULL, '2016-12-31', NULL, NULL, NULL, NULL, 'Guaranteed accident', NULL, '44', NULL),
(30, 12, 2, '1', '500 $', '5 Year Term', 'A', 'G', 'G', 'Insured Name', 'Insured Phone', '-', NULL, NULL, NULL),
(37, 22, 2, '3', NULL, NULL, '2017-1-04', NULL, NULL, NULL, NULL, '-', '41', '27', '1/January/2017'),
(38, 23, 2, '3', NULL, NULL, '2017-1-04', NULL, NULL, NULL, NULL, '-', '42', '28', '1/January/2017'),
(39, 24, 1, '1', '8394', '15 Year Term', '2017-1-04', 'Ali kanso', 'Samira kanso', 'mohammad salemeh', '71256389', '-', NULL, NULL, NULL),
(40, 27, 1, '2', '150 $', NULL, '2017-1-19', NULL, NULL, NULL, NULL, 'circulation accident', NULL, NULL, NULL),
(41, 28, 1, '3', NULL, NULL, '2017-1-19', NULL, NULL, NULL, NULL, '-', '48', '44', '1/January/2017'),
(42, 29, 1, '1', '3603', '10 Year Term', '2017-1-19', 't', 't', 'Insured Name', 'Insured Phone', '-', NULL, '43', NULL),
(43, 30, 1, '1', '5050', '5 Year Term', '2017-1-19', 'aa', 'aa', 'new', 'new', '-', NULL, NULL, NULL),
(44, 31, 0, '2', '75$', NULL, '2017-2-02', NULL, NULL, NULL, NULL, 'Guaranteed accident', NULL, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `car_info`
--
ALTER TABLE `car_info`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `car_model`
--
ALTER TABLE `car_model`
  ADD PRIMARY KEY (`model`);

--
-- Indexes for table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`clientID`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employeeID`);

--
-- Indexes for table `insurance`
--
ALTER TABLE `insurance`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `licence`
--
ALTER TABLE `licence`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `policy`
--
ALTER TABLE `policy`
  ADD PRIMARY KEY (`policyID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `car_info`
--
ALTER TABLE `car_info`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;
--
-- AUTO_INCREMENT for table `clients`
--
ALTER TABLE `clients`
  MODIFY `clientID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT for table `licence`
--
ALTER TABLE `licence`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;
--
-- AUTO_INCREMENT for table `policy`
--
ALTER TABLE `policy`
  MODIFY `policyID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
