-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema plantydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `plantydb` ;

-- -----------------------------------------------------
-- Schema plantydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `plantydb` DEFAULT CHARACTER SET utf8 ;
USE `plantydb` ;

-- -----------------------------------------------------
-- Table `plant_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `plant_category` ;

CREATE TABLE IF NOT EXISTS `plant_category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `plant` ;

CREATE TABLE IF NOT EXISTS `plant` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(500) NULL,
  `price` INT NOT NULL,
  `stock_quantity` INT NOT NULL,
  `plant_image_url` VARCHAR(255) NULL,
  `size` VARCHAR(45) NULL,
  `is_discounted` TINYINT NULL,
  `plant_category_id` INT NOT NULL,
  `enabled` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_plant_plant_category1_idx` (`plant_category_id` ASC),
  CONSTRAINT `fk_plant_plant_category1`
    FOREIGN KEY (`plant_category_id`)
    REFERENCES `plant_category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NOT NULL,
  `email` VARCHAR(255) NULL,
  `password` VARCHAR(255) NOT NULL,
  `enabled` TINYINT NULL,
  `role` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `payment_data`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `payment_data` ;

CREATE TABLE IF NOT EXISTS `payment_data` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `card_type` VARCHAR(255) NOT NULL,
  `card_number` VARCHAR(16) NOT NULL,
  `expiration_date` DATE NOT NULL,
  `enabled` TINYINT NULL,
  `full_name` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_payment_data_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_payment_data_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shipping_address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shipping_address` ;

CREATE TABLE IF NOT EXISTS `shipping_address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street_address` VARCHAR(255) NULL,
  `city` VARCHAR(200) NULL,
  `state` VARCHAR(45) NULL,
  `zipcode` VARCHAR(45) NULL,
  `enabled` TINYINT NULL,
  `country` VARCHAR(255) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_shipping_address_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_shipping_address_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `order_cart`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `order_cart` ;

CREATE TABLE IF NOT EXISTS `order_cart` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `payment_data_id` INT NULL,
  `total_price` INT NOT NULL,
  `date_placed` DATETIME NULL,
  `notes` VARCHAR(255) NULL,
  `estimated_delivery_date` DATETIME NULL,
  `tracking_number` INT NULL,
  `payment_method` VARCHAR(45) NULL,
  `enabled` TINYINT NULL,
  `shipping_address_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_payment_data1_idx` (`payment_data_id` ASC),
  UNIQUE INDEX `tracking_number_UNIQUE` (`tracking_number` ASC),
  INDEX `fk_order_cart_user1_idx` (`user_id` ASC),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC),
  INDEX `fk_order_cart_shipping_address1_idx` (`shipping_address_id` ASC),
  CONSTRAINT `fk_order_payment_data1`
    FOREIGN KEY (`payment_data_id`)
    REFERENCES `payment_data` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_cart_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_cart_shipping_address1`
    FOREIGN KEY (`shipping_address_id`)
    REFERENCES `shipping_address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `order_detail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `order_detail` ;

CREATE TABLE IF NOT EXISTS `order_detail` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `plant_id` INT NOT NULL,
  `quantity_ordered` INT NOT NULL,
  `unit_price` INT NOT NULL,
  `subtotal_price` INT NOT NULL,
  `gift_wrap` TINYINT NULL,
  `order_cart_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_detail_plant1_idx` (`plant_id` ASC),
  INDEX `fk_order_detail_order_cart1_idx` (`order_cart_id` ASC),
  CONSTRAINT `fk_order_detail_plant1`
    FOREIGN KEY (`plant_id`)
    REFERENCES `plant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_detail_order_cart1`
    FOREIGN KEY (`order_cart_id`)
    REFERENCES `order_cart` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `post` ;

CREATE TABLE IF NOT EXISTS `post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `title` VARCHAR(500) NOT NULL,
  `content` TEXT NOT NULL,
  `created_at` DATETIME NULL,
  `enabled` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_post_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_post_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `post_id` INT NOT NULL,
  `content` TEXT NOT NULL,
  `created_at` DATETIME NULL,
  `enabled` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_user1_idx` (`user_id` ASC),
  INDEX `fk_comment_post1_idx` (`post_id` ASC),
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plant_origin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `plant_origin` ;

CREATE TABLE IF NOT EXISTS `plant_origin` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `plant_id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `latitude` DECIMAL(9,6) NOT NULL,
  `longitude` DECIMAL(9,6) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_plant_origin_plant1_idx` (`plant_id` ASC),
  CONSTRAINT `fk_plant_origin_plant1`
    FOREIGN KEY (`plant_id`)
    REFERENCES `plant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS plantyuser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'plantyuser'@'localhost' IDENTIFIED BY 'plantyuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'plantyuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `plant_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantydb`;
INSERT INTO `plant_category` (`id`, `name`, `description`) VALUES (1, 'Evergreen perennials', 'Evergreen perennials have their leaves year-round and live longer than two years.');
INSERT INTO `plant_category` (`id`, `name`, `description`) VALUES (2, 'Tropical Trees', NULL);
INSERT INTO `plant_category` (`id`, `name`, `description`) VALUES (3, 'Succulents', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `plant`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantydb`;
INSERT INTO `plant` (`id`, `name`, `description`, `price`, `stock_quantity`, `plant_image_url`, `size`, `is_discounted`, `plant_category_id`, `enabled`) VALUES (1, 'Snake Plant', 'Long, light to dark green leaves.', 1000, 1000, 'https://target.scene7.com/is/image/Target/GUEST_71909e47-2c05-418f-a124-cf3910389a15?wid=1200&hei=1200&qlt=80&fmt=webp', 'medium', 1, 1, 1);
INSERT INTO `plant` (`id`, `name`, `description`, `price`, `stock_quantity`, `plant_image_url`, `size`, `is_discounted`, `plant_category_id`, `enabled`) VALUES (2, 'Money Tree', 'Short dark green leaves.', 1000, 1000, 'https://m.media-amazon.com/images/I/71M-lBHD4sL._AC_SX679_.jpg', 'medium', 1, 2, 1);
INSERT INTO `plant` (`id`, `name`, `description`, `price`, `stock_quantity`, `plant_image_url`, `size`, `is_discounted`, `plant_category_id`, `enabled`) VALUES (3, 'Aloe Vera', 'Medium length green succulent leaves that are prickly.It is well known for offering possible health and beauty benefits. It\'s also toxic to cats and dogs so we advise caution to where it\'s placed in your home.', 1100, 2000, 'https://m.media-amazon.com/images/I/713BlHvw8VL.__AC_SX300_SY300_QL70_FMwebp_.jpg', 'medium', 1, 3, 1);
INSERT INTO `plant` (`id`, `name`, `description`, `price`, `stock_quantity`, `plant_image_url`, `size`, `is_discounted`, `plant_category_id`, `enabled`) VALUES (4, 'Winter Green', 'The “Wintergreen” cultivar of the Ficus benjamina is characterized by its yellowish green foliage that help give it a glowing appearance. Otherwise, this tree is everything plant lovers appreciate about fig trees. Even indoors, this tree can grow to be over 10-feet tall.', 1500, 5000, 'https://m.media-amazon.com/images/I/81Ez5uPN9vL._AC_SY879_.jpg', 'large', 0, 2, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantydb`;
INSERT INTO `user` (`id`, `username`, `email`, `password`, `enabled`, `role`) VALUES (1, 'Samwise', 'sam1@aol.com', '$2a$10$rkKTjBmUEe/uxMS1KdJexupMtg5tNVl5d/16YzZ9OAnk0fhK8eiaS', 1, 'standard');

COMMIT;


-- -----------------------------------------------------
-- Data for table `payment_data`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantydb`;
INSERT INTO `payment_data` (`id`, `user_id`, `card_type`, `card_number`, `expiration_date`, `enabled`, `full_name`) VALUES (1, 1, 'VISA', '1234123412341234', '2024-11-15', 1, 'Dan The Man');

COMMIT;


-- -----------------------------------------------------
-- Data for table `shipping_address`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantydb`;
INSERT INTO `shipping_address` (`id`, `street_address`, `city`, `state`, `zipcode`, `enabled`, `country`, `user_id`) VALUES (1, '4567 sawmill blvd', 'Townsville', 'CO', '78456', 1, 'United States', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `order_cart`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantydb`;
INSERT INTO `order_cart` (`id`, `user_id`, `payment_data_id`, `total_price`, `date_placed`, `notes`, `estimated_delivery_date`, `tracking_number`, `payment_method`, `enabled`, `shipping_address_id`) VALUES (1, 1, 1, 2000, '2023-10-31 12:00:00', 'Please ring the doorbell and leave the plants on the front porch, thank you!', '2023-11-05 12:00:00', 1, 'Credit Card', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `order_detail`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantydb`;
INSERT INTO `order_detail` (`id`, `plant_id`, `quantity_ordered`, `unit_price`, `subtotal_price`, `gift_wrap`, `order_cart_id`) VALUES (1, 1, 2, 1000, 2000, 0, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `post`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantydb`;
INSERT INTO `post` (`id`, `user_id`, `title`, `content`, `created_at`, `enabled`) VALUES (1, 1, 'What is one benefit you\'ve received after incorporating house plants into your decor?', 'I really like the atmosphere they create. House plants bring a sense of calming to the room. Having oxygen being created inside of your home in a nice feature too.', '2023-11-01 13:11:30', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantydb`;
INSERT INTO `comment` (`id`, `user_id`, `post_id`, `content`, `created_at`, `enabled`) VALUES (1, 1, 1, 'I like creating a space that feels like a forest.', '2023-11-01 13:16:35', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `plant_origin`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantydb`;
INSERT INTO `plant_origin` (`id`, `plant_id`, `name`, `latitude`, `longitude`) VALUES (1, 1, 'Ghana', 5.5600, 0.2057);
INSERT INTO `plant_origin` (`id`, `plant_id`, `name`, `latitude`, `longitude`) VALUES (2, 2, 'Guyana', 6.8013, -58.1553);
INSERT INTO `plant_origin` (`id`, `plant_id`, `name`, `latitude`, `longitude`) VALUES (3, 3, 'Oman', 21.0000, 57.0000);
INSERT INTO `plant_origin` (`id`, `plant_id`, `name`, `latitude`, `longitude`) VALUES (4, 4, 'Ghana', 5.5600, 0.2057);

COMMIT;

