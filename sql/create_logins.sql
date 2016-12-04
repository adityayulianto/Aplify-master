CREATE TABLE `activepoints`.`logins` ( `id` INT NOT NULL AUTO_INCREMENT , `email` VARCHAR(50) NOT NULL , 
`firstname` VARCHAR(30) NOT NULL , `lastname` VARCHAR(30) NOT NULL , `password` VARCHAR(50) NOT NULL , 
`last_login` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP , `incorrect_counter` INT NOT NULL DEFAULT '0' , 
PRIMARY KEY (`id`)) ENGINE = MyISAM;