CREATE TABLE note(
 num NUMBER PRIMARY KEY,
 name VARCHAR2(30) NOT NULL,
 passwd VARCHAR2(10) NOT NULL,
 subject VARCHAR2(60) NOT NULL,
 content VARCHAR2(4000) NOT NULL,
 email VARCHAR2(60),
 reg_date DATE NOT NULL
);
CREATE SEQUENCE note_seq;