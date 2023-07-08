DROP TABLE IF EXISTS USER_ROLE_TBL;


CREATE TABLE USER_ROLE_TBL (
  ID int(11) NOT NULL AUTO_INCREMENT,
  VERSION    INT,
  CREATE_USR_ID INT,
  CREATE_GMT DATE,
  USR_ID INT,
  GMT DATE,
  STATUS VARCHAR(1),
  COMMENT_ VARCHAR(512),
  USER_ID int(11) NOT NULL,
  ROLE_ID int(11) NOT NULL,
  PRIMARY KEY (ID),
  KEY `FKa68196081fvovjhkek5m97n3y` (ROLE_ID),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (USER_ID) REFERENCES USER_TBL (ID),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (ROLE_ID) REFERENCES ROLE_TBL (ID)
  ;