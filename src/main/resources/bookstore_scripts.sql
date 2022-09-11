
-- --------------------------------------------------------
-- (C)Copywrite Panos Zafeiropoulos - 2022
-- --------------------------------------------------------

-- --------------------------------------------------------
-- CREATE SCHEMA (DATABASE) WITH NAME `bookstore`
-- --------------------------------------------------------
BEGIN;
CREATE SCHEMA IF NOT EXISTS bookstore 
CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;
COMMIT;

USE `bookstore`;

-- --------------------------------------------------------
-- CREATE TABLES
-- --------------------------------------------------------

BEGIN;
CREATE TABLE `categories` (
  `categoryId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(100) NOT NULL,
  `categoryUUID` varchar(100),
  PRIMARY KEY (`categoryId`)
);
COMMIT;

BEGIN;
CREATE TABLE `vendors` (
  `vendorId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `vendorName` varchar(100) NOT NULL,
  `vendorUUID` varchar(100),
  PRIMARY KEY (`vendorId`)
);
COMMIT;

BEGIN;
CREATE TABLE `items` (
  `itemId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `itemName` varchar(100) NOT NULL,
  `itemCategoryId` int(10) unsigned NOT NULL,
  `itemVendorId` int(10) unsigned NOT NULL,
  `itemModelYear` int(10) unsigned DEFAULT NULL,
  `itemListPrice` decimal(10,2) NOT NULL,
  `itemUUID` varchar(100),
  PRIMARY KEY (`itemId`)
);
COMMIT;

BEGIN;
CREATE TABLE `vendorcategories` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `vendorId` int(10) unsigned NOT NULL,
  `categoryId` int(10) unsigned NOT NULL,
  `isAllowed` TINYINT(1),
  PRIMARY KEY (`id`)
);
COMMIT;






-- --------------------------------------------------------
-- ADD RECORDS TO TABLES
-- --------------------------------------------------------



BEGIN;
INSERT INTO `categories` (`categoryName`) VALUES ('Pens');
INSERT INTO `categories` (`categoryName`) VALUES ('Books');
INSERT INTO `categories` (`categoryName`) VALUES ('Notebooks');
INSERT INTO `categories` (`categoryName`) VALUES ('Pencils');
INSERT INTO `categories` (`categoryName`) VALUES ('Markers');
INSERT INTO `categories` (`categoryName`) VALUES ('Writing Pads');
INSERT INTO `categories` (`categoryName`) VALUES ('Envelops');
INSERT INTO `categories` (`categoryName`) VALUES ('Organizers');
INSERT INTO `categories` (`categoryName`) VALUES ('Document Holders');
INSERT INTO `categories` (`categoryName`) VALUES ('Punchers');
INSERT INTO `categories` (`categoryName`) VALUES ('Sticky tapes');
INSERT INTO `categories` (`categoryName`) VALUES ('Highlighter Pen');
INSERT INTO `categories` (`categoryName`) VALUES ('Rulers');
INSERT INTO `categories` (`categoryName`) VALUES ('Pencil Sharpeners');
INSERT INTO `categories` (`categoryName`) VALUES ('Scissors');
INSERT INTO `categories` (`categoryName`) VALUES ('Staplers');
INSERT INTO `categories` (`categoryName`) VALUES ('Paper Clips');
INSERT INTO `categories` (`categoryName`) VALUES ('Thumb Pins');
INSERT INTO `categories` (`categoryName`) VALUES ('Glues – Glue Sticks');
INSERT INTO `categories` (`categoryName`) VALUES ('Erasers');
INSERT INTO `categories` (`categoryName`) VALUES ('Pen Cups');
INSERT INTO `categories` (`categoryName`) VALUES ('Files');
INSERT INTO `categories` (`categoryName`) VALUES ('File Racks');
INSERT INTO `categories` (`categoryName`) VALUES ('Paper Trays');
INSERT INTO `categories` (`categoryName`) VALUES ('Paper Boxes');
INSERT INTO `categories` (`categoryName`) VALUES ('School Bugs');
INSERT INTO `categories` (`categoryName`) VALUES ('Waste buckets');
INSERT INTO `categories` (`categoryName`) VALUES ('Printer Paper Packs');
INSERT INTO `categories` (`categoryName`) VALUES ('Ink cartridges');
INSERT INTO `categories` (`categoryName`) VALUES ('Toners');
COMMIT;


BEGIN;
INSERT INTO `vendors` (`vendorName`) VALUES ('R&L Distributors');
INSERT INTO `vendors` (`vendorName`) VALUES ('Vaber Castell');
INSERT INTO `vendors` (`vendorName`) VALUES ('Office Suppliers');
INSERT INTO `vendors` (`vendorName`) VALUES ('YPrinting');
INSERT INTO `vendors` (`vendorName`) VALUES ('Staplesh');
INSERT INTO `vendors` (`vendorName`) VALUES ('Stationeries');
INSERT INTO `vendors` (`vendorName`) VALUES ('SKAEG');
INSERT INTO `vendors` (`vendorName`) VALUES ('Mou');
INSERT INTO `vendors` (`vendorName`) VALUES ('Peppermint');
INSERT INTO `vendors` (`vendorName`) VALUES ('Printinfy');
COMMIT;


BEGIN;
INSERT INTO `items` (`itemName`, `itemCategoryId`, `itemVendorId`, `itemModelYear`, `itemListPrice`) VALUES ('Wooden Pencil', 3, 2, 1998, 2.65);
INSERT INTO `items` (`itemName`, `itemCategoryId`, `itemVendorId`, `itemModelYear`, `itemListPrice`) VALUES ('Basic Notebook', 2, 6, 2005, 3.75);
INSERT INTO `items` (`itemName`, `itemCategoryId`, `itemVendorId`, `itemModelYear`, `itemListPrice`) VALUES ('Silica Eraser', 1, 1, 2007, 1.15);
INSERT INTO `items` (`itemName`, `itemCategoryId`, `itemVendorId`, `itemModelYear`, `itemListPrice`) VALUES ('Soft Polymer Eraser', 1, 8, 2020, 1.05);
INSERT INTO `items` (`itemName`, `itemCategoryId`, `itemVendorId`, `itemModelYear`, `itemListPrice`) VALUES ('Soft Ballpoint Pen', 1, 9, 2019, 2.95);
INSERT INTO `items` (`itemName`, `itemCategoryId`, `itemVendorId`, `itemModelYear`, `itemListPrice`) VALUES ('Paper Dossier', 2, 7, 2019, 3.70);
INSERT INTO `items` (`itemName`, `itemCategoryId`, `itemVendorId`, `itemModelYear`, `itemListPrice`) VALUES ('A4 Glossy Paper Pack 250', 2, 10, 2019, 6.15);
INSERT INTO `items` (`itemName`, `itemCategoryId`, `itemVendorId`, `itemModelYear`, `itemListPrice`) VALUES ('Rubber Bands Small Pack', 1, 6, 2019, 0.50);
INSERT INTO `items` (`itemName`, `itemCategoryId`, `itemVendorId`, `itemModelYear`, `itemListPrice`) VALUES ('Fancy Jotter Notepad', 2, 7, 2021, 1.65);
INSERT INTO `items` (`itemName`, `itemCategoryId`, `itemVendorId`, `itemModelYear`, `itemListPrice`) VALUES ('Fountain Metal Brass Pen', 3, 2, 2018, 5.10);
INSERT INTO `items` (`itemName`, `itemCategoryId`, `itemVendorId`, `itemModelYear`, `itemListPrice`) VALUES ('Permanent Marker Black', 1, 1, 2017, 3.40);
INSERT INTO `items` (`itemName`, `itemCategoryId`, `itemVendorId`, `itemModelYear`, `itemListPrice`) VALUES ('Rubber Pencil', 3, 2, 2019, 2.80);
COMMIT;

BEGIN;
INSERT INTO `vendorcategories` (`vendorId`,`categoryId`,`isAllowed` ) VALUES (1,8,1);
INSERT INTO `vendorcategories` (`vendorId`,`categoryId`,`isAllowed` ) VALUES (1,9,1);
INSERT INTO `vendorcategories` (`vendorId`,`categoryId`,`isAllowed` ) VALUES (1,4,0);
INSERT INTO `vendorcategories` (`vendorId`,`categoryId`,`isAllowed` ) VALUES (1,1,0);
INSERT INTO `vendorcategories` (`vendorId`,`categoryId`,`isAllowed` ) VALUES (1,14,1);
INSERT INTO `vendorcategories` (`vendorId`,`categoryId`,`isAllowed` ) VALUES (2,4,1);
INSERT INTO `vendorcategories` (`vendorId`,`categoryId`,`isAllowed` ) VALUES (2,14,1);
INSERT INTO `vendorcategories` (`vendorId`,`categoryId`,`isAllowed` ) VALUES (2,1,0);
INSERT INTO `vendorcategories` (`vendorId`,`categoryId`,`isAllowed` ) VALUES (2,20,1);
COMMIT;

-- --------------------------------------------------------
-- GRANT ALL PRIVILEGES 
-- FOR THE `bookstore` SCHEMA 
-- TO A USER WITH USERNAME: `user1`
-- --------------------------------------------------------

GRANT ALL ON bookstore.* TO 'user1'@'%';