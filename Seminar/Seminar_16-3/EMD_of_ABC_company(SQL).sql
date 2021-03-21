CREATE DATABASE ASSIGNMENT

----QUESTION 1-----

CREATE TABLE SKILL
(
	SkillNo int IDENTITY(1,1) CONSTRAINT S_SN_PK PRIMARY KEY,
	SkillName varchar(20) NOT NULL,
	Note char (100)
)

CREATE TABLE DEPARTMENT
(
	DeptNo int IDENTITY(1,1) CONSTRAINT DPT_DN_PK PRIMARY KEY,
	DeptName varchar(20) NOT NULL,
	Note char(100)
)

CREATE TABLE EMPLOYEE 
(
	EmpNo char(5) CONSTRAINT EMP_EN_PK PRIMARY KEY,
	EmpName varchar(20) NOT NULL,
	BirthDay smalldatetime NOT NULL,
	DeptNo int IDENTITY(1,1),
	MgrNo char(5) NOT NULL,
	StartDate smalldatetime NOT NULL,
	Salary money NOT NULL,
	Level integer NOT NULL CONSTRAINT EMP_SL_CK CHECK (1 <= Level AND Level <= 7),
	Status integer NOT NULL CONSTRAINT EMP_S_CK CHECK (0 <= Status AND Status <= 2),
	Note char(100),
)

CREATE TABLE EMP_SKILL
(
	SkillNo int,
	EmpNo char(5),
	SkillLevel integer NOT NULL CONSTRAINT EMPS_SL_CK CHECK (1 <= SkillLevel AND SkillLevel <= 3),
	RegDate smalldatetime NOT NULL,
	Description char(100),
	CONSTRAINT EMPS_EN_SN_PK PRIMARY KEY (EmpNo, SkillNo),
	CONSTRAINT EMPS_SN_FK FOREIGN KEY (SkillNo) REFERENCES SKILL(SkillNo),
	CONSTRAINT EMPS_EN_FK FOREIGN KEY (EmpNo) REFERENCES EMPLOYEE(EmpNo)
)

----QUESTION 2----

	----A. CREATE EMAIL FIELD----
ALTER TABLE EMPLOYEE ADD 
	Email varchar(20) UNIQUE

	----B. MODIFY EMPLOYEE----	
ALTER TABLE EMPLOYEE ADD
	CONSTRAINT EMP_MN_DF DEFAULT (0) FOR MgrNo,
	CONSTRAINT EMP_ST_DF DEFAULT (0) FOR Status

----QUESTION 3----
	
	----A. ADD FOREIGN KEY----
ALTER TABLE EMPLOYEE ADD
	CONSTRAINT EMP_DN_FK FOREIGN KEY (DeptNo) REFERENCES DEPARTMENT (DeptNo)

	----B. REMOVE DESCRIPTION----
ALTER TABLE EMP_SKILL DROP COLUMN Description 
	
----QUESTION 4----

	----A. ADD RECORDS----
INSERT INTO SKILL VALUES
	('Teamwork', NULL),
	('Comunication', NULL),
	('Written', NULL),
	('Flexibility', NULL),
	('Presentation', NULL)

INSERT INTO DEPARTMENT VALUES
	('HR', NULL),
	('Maneger', NULL),
	('Technical', NULL),
	('Design', NULL),
	('Logictist', NULL)

INSERT INTO EMPLOYEE VALUES
	('A01', 'Dung', '1982-10-19', 'A22', '2011-3-2', '5000000.0000', '2', '0', NULL, 'dg@email.com'),
	('A02', 'Mạnh Vũ', '1984-8-3', 'A43', '2011-3-2', '5000000.0000', '5', '2', NULL, 'v@email.com'),
	('A03', 'Anh Duy', '1980-1-31', 'A36', '2011-3-2', '5000000.0000', '3', '2', NULL, 'd@email.com'),
	('A04', 'Khánh An', '1983-12-27', 'A23', '2011-3-2', '5000000.0000', '1', '0', NULL, 'a@email.com'),
	('A05', 'Gia Minh', '1985-5-14', 'A43', '2011-3-2', '5000000.0000', '4', '1', NULL, 'm@email.com')

INSERT INTO EMP_SKILL VALUES
	(1, 'A01', '2', '2020-2-23'), 
	(2, 'A01', '2', '2020-2-23'), 
	(5, 'A01', '3', '2020-2-23'), 
	(1, 'A02', '3', '2020-2-23'), 
	(2, 'A02', '1', '2020-2-23'), 
	(3, 'A02', '2', '2020-2-23'), 
	(5, 'A02', '1', '2020-2-23')

	----B. CREATE VIEW----
CREATE VIEW EMPLOYEE_TRACKING AS
	SELECT EmpNo, EmpName AS Emp_Name, Level
	FROM EMPLOYEE
	WHERE Level >= 3 AND Level <= 5

