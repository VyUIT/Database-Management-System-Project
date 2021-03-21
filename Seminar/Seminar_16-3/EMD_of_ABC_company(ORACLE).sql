--Question 1
-- a)
CREATE TABLE EMPLOYEE
(
  EmpNo char(3),
  EmpName char(30),
  BirthDay date,
  DeptNo int,
  MrgNo char(3) not null,
  StartDate date,
  Salary NUMBER(19,-2),
  Levell int,
  Status int,
  Note nclob,
  CONSTRAINT PR_EMPNO PRIMARY KEY(EmpNo)
);
--b)
CREATE TABLE SKILL
(
  SkillNo int,
  SkillName char(30),
  Note nclob,
  CONSTRAINT PR_SKILLNO PRIMARY KEY(SkillNo)
);
--c)
CREATE TABLE DEPARTMENT
(
  DeptNo int,
  DeptName char(30),
  Note nclob,
  CONSTRAINT PR_DEPTNO PRIMARY KEY(DeptNo)
);
--d) 
CREATE TABLE EMP_SKILL
(
  SkillNo int,
  EmpNo char(3),
  SkillLevel int,
  RegDate date,
  Description nclob,
  CONSTRAINT PR_SKILL_EMP PRIMARY KEY(SkillNo,EmpNo)
);
-- FOREIGN KEY
ALTER TABLE EMP_SKILL
  ADD CONSTRAINT FK_EMPNO 
  FOREIGN KEY(EmpNo) REFERENCES EMPLOYEE(EmpNo);
  
ALTER TABLE EMP_SKILL
  ADD CONSTRAINT FK_SKILLNO
  FOREIGN KEY(SkillNo) REFERENCES SKILL(SkillNo);
--CHECK
ALTER TABLE EMPLOYEE
  ADD CONSTRAINT CHK_LEVEL
  CHECK(Levell BETWEEN 1 AND 7);

ALTER TABLE EMPLOYEE
  ADD CONSTRAINT CHK_STATUS
  CHECK(Status IN(0,1,2));

ALTER TABLE EMP_SKILL
  ADD CONSTRAINT CHK_SKILLLEVEL
  CHECK(SkillLevel IN(1,2,3));
--AUTO INCREMENT 
CREATE SEQUENCE SkillNo_seq
  START WITH 1
  INCREMENT BY 1
  NOMAXVALUE;
DROP SEQUENCE DeptNo_seq;
CREATE SEQUENCE DeptNo_seq
  START WITH 1
  INCREMENT BY 1
  NOMAXVALUE;
--Question 2
--a)
ALTER TABLE EMPLOYEE
  ADD Email char(30) UNIQUE;
--b)
AlTER TABLE EMPLOYEE
  MODIFY (MrgNo char(5) DEFAULT 0);
ALTER TABLE EMPLOYEE
  MODIFY (Status int DEFAULT 0);
  
--Question 3
--a)

ALTER TABLE EMPLOYEE
  ADD CONSTRAINT FK_DEPTNO 
  FOREIGN KEY(DeptNo) REFERENCES DEPARTMENT(DeptNo);
  
--b)
ALTER TABLE EMP_SKILL
  DROP COLUMN  Description;

--Question 4
INSERT INTO SKILL(SkillNo,SkillName,Note)
  VALUES(SkillNo_seq.nextval,'Teamword',NULL);
INSERT INTO SKILL(SkillNo,SkillName,Note)
  VALUES(SkillNo_Seq.nextval,'Communication',NULL);
INSERT INTO SKILL(SkillNo,SkillName,Note)
  VALUES(SkillNo_Seq.nextval,'Written',NULL);
INSERT INTO SKILL(SkillNo,SkillName,Note)
  VALUES(SkillNo_Seq.nextval,'Flexibility',NULL);
INSERT INTO SKILL(SkillNo,SkillName,Note)
  VALUES(SkillNo_Seq.nextval,'Presentation',NULL);
  
INSERT INTO DEPARTMENT(DeptNo,DeptName,Note)
  VALUES(DeptNo_seq.nextval,'Hr',NULL);
INSERT INTO DEPARTMENT(DeptNo,DeptName,Note)
  VALUES(DeptNo_seq.nextval,'Manager',NULL);
INSERT INTO DEPARTMENT(DeptNo,DeptName,Note)
  VALUES(DeptNo_seq.nextval,'Technical',NULL);
INSERT INTO DEPARTMENT(DeptNo,DeptName,Note)
  VALUES(DeptNo_seq.nextval,'Design',NULL);
INSERT INTO DEPARTMENT(DeptNo,DeptName,Note)
  VALUES(DeptNo_seq.nextval,'Logictist',NULL);
  
ALTER TABLE EMPLOYEE
  DROP CONSTRAINT FK_DEPTNO;
  DROP TABLE DEPARTMENT;
  
ALTER TABLE EMPLOYEE
  ADD CONSTRAINT FK_DEPTNO 
  FOREIGN KEY(DeptNo) REFERENCES DEPARTMENT(DeptNo);
  
  
INSERT INTO EMPLOYEE(EmpNo,EmpName,BirthDay,DeptNo,MgrNo,StartDate,Salary,Levell,Status,Note,Email)
  VALUES('A01','Dung','19-OCT-1982',5,'A22','2-MAR-2011',5000000,2,0,NULL,'dg@email.com');
INSERT INTO EMPLOYEE(EmpNo,EmpName,BirthDay,DeptNo,MgrNo,StartDate,Salary,Levell,Status,Note,Email)
  VALUES('A02','Manh Vu','3-AUG-1984',4,'A43','2-MAR-2011',5000000,5,2,NULL,'v@email.com');
INSERT INTO EMPLOYEE(EmpNo,EmpName,BirthDay,DeptNo,MgrNo,StartDate.Salary,Levell,Status,Note,Email)
  VALUES('A03','Anh Duy','31-JAN-1980',3,'A22','2-MAR-2011',5000000,2,0,NULL,'dg@email.com');
INSERT INTO EMPLOYEE(EmpNo,EmpName,BirthDay,DeptNo,MrgNo,StartDate,Salary,Levell,Status,Email)
  VALUES('A04','Khanh An','27-NOV-1983',2,'A23'.'2-MAR-2011',5000000,3,2,NULL,'d@email.com');
INSERT INTO EMPLOYEE(EmpNo,EmpName,BirthDay,DeptNo,MrgNo,StartDate,Salary,Levell,Status,Email)
  VALUES('A05','Gia Minh','14-MAY-1985',4,'A43','2-MAR-2011',5000000,4,1,NULL,'m@email.com');
  
INSERT INTO EMP_SKILL(SkillNo,EmpNo,SkillLevel,RegDate)
  VALUES(1,'A01',2,'23-FEB-2020');
INSERT INTO EMP_SKILL(SkillNo,EmpNo,SkillLevel,RegDate)
  VALUES(2,'A01',2,'23-FEB-2020');
INSERT INTO EMP_SKILL(SkillNo,EmpNo,SkillLevel,RegDate)
  VALUES(5,'A01',3,'23-FEB-2020');
INSERT INTO EMP_SKILL(SkillNo,EmpNo,SkillLevel,RegDate)
  VALUES(4,'A02',3,'23-FEB-2020');
INSERT INTO EMP_SKILL(SkillNo,EmpNo,SkillLevel,RegDate)
  VALUES(1,'A02',1,'23-FEB-2020');
INSERT INTO EMP_SKILL(SkillNo,EmpNo,SkillLevel,RegDate)
  VALUES(2,'A02',2,'23-FEB-2020');
INSERT INTO EMP_SKILL(SkillNo,EmpNo,SkillLevel,RegDate)
  VALUES(5,'A02',1,'23-FEB-2020);
  
--b)
  CREATE VIEW EMPLOYEE_TRACKING AS
    SELECT EmpNo,EmpName,Levell
    FROM EMPLOYEE
    WHERE Level BETWEEN 3 AND 5;