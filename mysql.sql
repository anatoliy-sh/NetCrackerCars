CREATE DATABASE AutoRepairCars;


CREATE TABLE IF NOT EXISTS `lada` (
`id` bigint(20) PRIMARY KEY,
  `name` varchar(20) NOT NULL,
  `owner` varchar(20) NOT NULL,
  `handling_time` int(20) NOT NULL
) 
CREATE TABLE IF NOT EXISTS `audi` (
`id` bigint(20) PRIMARY KEY,
  `name` varchar(20) NOT NULL,
  `owner` varchar(20) NOT NULL,
  `handling_time` int(20) NOT NULL
) 
CREATE TABLE IF NOT EXISTS `ford` (
`id` bigint(20) PRIMARY KEY,
  `name` varchar(20) NOT NULL,
  `owner` varchar(20) NOT NULL,
  `handling_time` int(20) NOT NULL
) 
CREATE TABLE IF NOT EXISTS `renault` (
`id` bigint(20) PRIMARY KEY,
  `name` varchar(20) NOT NULL,
  `owner` varchar(20) NOT NULL,
  `handling_time` int(20) NOT NULL
) 
