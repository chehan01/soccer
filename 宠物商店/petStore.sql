CREATE DATABASE petstore;
USE petstore;
CREATE TABLE USER(
userName VARCHAR(20) PRIMARY KEY,
userPassword VARCHAR(50)

)ENGINE=INNODB DEFAULT CHARSET=gbk;

CREATE TABLE pets(
number INT PRIMARY KEY,
kind ENUM('猫','狗') NOT NULL,
varieties VARCHAR(7) NOT NULL,
price DECIMAL(7,2) NOT NULL,
unit INT DEFAULT 0 NOT NULL
)ENGINE=INNODB DEFAULT CHARSET=gbk;

INSERT INTO pets VALUES(1,"猫","田园猫",1.0,2);
INSERT INTO pets VALUES(7,'狗',"田园狗",2.0,2);
