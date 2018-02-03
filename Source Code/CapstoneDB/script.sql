-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema capstone
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema capstone
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `capstone` DEFAULT CHARACTER SET utf8 ;
USE `capstone` ;

-- -----------------------------------------------------
-- Table `capstone`.`Type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `capstone`.`Type` (
  `id` INT NOT NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `capstone`.`Lecturer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `capstone`.`Lecturer` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `capstone`.`Major`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `capstone`.`Major` (
  `id` INT NOT NULL,
  `code` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `capstone`.`Course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `capstone`.`Course` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `majorID` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Course_Major1_idx` (`majorID` ASC),
  CONSTRAINT `fk_Course_Major1`
    FOREIGN KEY (`majorID`)
    REFERENCES `capstone`.`Major` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `capstone`.`Info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `capstone`.`Info` (
  `id` INT NOT NULL,
  `semester` DATE NULL,
  `lecturerID` INT NULL,
  `courseID` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Info_Lecturer1_idx` (`lecturerID` ASC),
  INDEX `fk_Info_Course1_idx` (`courseID` ASC),
  CONSTRAINT `fk_Info_Lecturer1`
    FOREIGN KEY (`lecturerID`)
    REFERENCES `capstone`.`Lecturer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Info_Course1`
    FOREIGN KEY (`courseID`)
    REFERENCES `capstone`.`Course` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `capstone`.`Department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `capstone`.`Department` (
  `id` INT NOT NULL,
  `name` VARCHAR(50) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `capstone`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `capstone`.`Role` (
  `id` INT NOT NULL,
  `roleName` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `capstone`.`UserInformation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `capstone`.`UserInformation` (
  `id` INT NOT NULL,
  `username` VARCHAR(50) NULL,
  `password` VARCHAR(50) NULL,
  `fullname` VARCHAR(50) NULL,
  `mail` VARCHAR(50) NULL,
  `birth` DATE NULL,
  `status` TINYINT(1) NULL,
  `roleID` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Role_UserInformation_idx` (`roleID` ASC),
  CONSTRAINT `fk_Role_UserInformation`
    FOREIGN KEY (`roleID`)
    REFERENCES `capstone`.`Role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '		';


-- -----------------------------------------------------
-- Table `capstone`.`Feedback`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `capstone`.`Feedback` (
  `id` INT NOT NULL,
  `point` INT NULL,
  `preferenceID` INT NULL,
  `isTemplate` TINYINT(1) NULL,
  `createDate` DATETIME NULL,
  `fromDate` DATETIME NULL,
  `endDate` DATETIME NULL,
  `templateName` VARCHAR(50) NULL,
  `feedbackName` VARCHAR(50) NULL,
  `templateDes` VARCHAR(50) NULL,
  `feedbackDes` VARCHAR(50) NULL,
  `isPublished` TINYINT(1) NULL,
  `questionID` INT NULL,
  `userID` INT NULL,
  `departmentID` INT NULL,
  `courseID` INT NULL,
  `lecturerID` INT NULL,
  `majorID` INT NULL,
  `infoID` INT NULL,
  `typeID` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Feedback_Type1_idx` (`typeID` ASC),
  INDEX `fk_Feedback_Info1_idx` (`infoID` ASC),
  INDEX `fk_Feedback_Major1_idx` (`majorID` ASC),
  INDEX `fk_Feedback_Lecturer1_idx` (`lecturerID` ASC),
  INDEX `fk_Feedback_Course1_idx` (`courseID` ASC),
  INDEX `fk_Feedback_Department1_idx` (`departmentID` ASC),
  INDEX `fk_Feedback_UserInformation1_idx` (`userID` ASC),
  CONSTRAINT `fk_Feedback_Type1`
    FOREIGN KEY (`typeID`)
    REFERENCES `capstone`.`Type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Feedback_Info1`
    FOREIGN KEY (`infoID`)
    REFERENCES `capstone`.`Info` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Feedback_Major1`
    FOREIGN KEY (`majorID`)
    REFERENCES `capstone`.`Major` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Feedback_Lecturer1`
    FOREIGN KEY (`lecturerID`)
    REFERENCES `capstone`.`Lecturer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Feedback_Course1`
    FOREIGN KEY (`courseID`)
    REFERENCES `capstone`.`Course` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Feedback_Department1`
    FOREIGN KEY (`departmentID`)
    REFERENCES `capstone`.`Department` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Feedback_UserInformation1`
    FOREIGN KEY (`userID`)
    REFERENCES `capstone`.`UserInformation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `capstone`.`Question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `capstone`.`Question` (
  `id` INT NOT NULL,
  `type` VARCHAR(50) NULL,
  `questionContent` VARCHAR(50) NULL,
  `suggestion` VARCHAR(50) NULL,
  `isRequied` TINYINT(1) NULL,
  `criteriaID` INT NULL,
  `feedbackID` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Question_Feedback1_idx` (`feedbackID` ASC),
  CONSTRAINT `fk_Question_Feedback1`
    FOREIGN KEY (`feedbackID`)
    REFERENCES `capstone`.`Feedback` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `capstone`.`Option`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `capstone`.`Option` (
  `id` INT NOT NULL,
  `optionContent` VARCHAR(50) NULL,
  `point` FLOAT NULL,
  `questionID` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Option_Question1_idx` (`questionID` ASC),
  CONSTRAINT `fk_Option_Question1`
    FOREIGN KEY (`questionID`)
    REFERENCES `capstone`.`Question` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `capstone`.`Answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `capstone`.`Answer` (
  `id` INT NOT NULL,
  `answerContent` VARCHAR(50) NULL,
  `createDate` DATETIME NULL,
  `optionID` INT NULL,
  `userID` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Answer_Option1_idx` (`optionID` ASC),
  INDEX `fk_Answer_UserInformation1_idx` (`userID` ASC),
  CONSTRAINT `fk_Answer_Option1`
    FOREIGN KEY (`optionID`)
    REFERENCES `capstone`.`Option` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Answer_UserInformation1`
    FOREIGN KEY (`userID`)
    REFERENCES `capstone`.`UserInformation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `capstone`.`Criteria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `capstone`.`Criteria` (
  `id` INT NOT NULL,
  `criteria` VARCHAR(50) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Criteria_Question1_idx` (`criteria` ASC),
  CONSTRAINT `fk_Criteria_Question1`
    FOREIGN KEY (`criteria`)
    REFERENCES `capstone`.`Question` (`criteriaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `capstone`.`Privilege`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `capstone`.`Privilege` (
  `id` INT NOT NULL,
  `name` VARCHAR(50) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '	';


-- -----------------------------------------------------
-- Table `capstone`.`PrivilegeRole`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `capstone`.`PrivilegeRole` (
  `id` INT NOT NULL,
  `roleID` INT NULL,
  `privilegeID` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_PrivilegeRole_Privilege1_idx` (`privilegeID` ASC),
  INDEX `fk_PrivilegeRole_Role1_idx` (`roleID` ASC),
  CONSTRAINT `fk_PrivilegeRole_Privilege1`
    FOREIGN KEY (`privilegeID`)
    REFERENCES `capstone`.`Privilege` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PrivilegeRole_Role1`
    FOREIGN KEY (`roleID`)
    REFERENCES `capstone`.`Role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
