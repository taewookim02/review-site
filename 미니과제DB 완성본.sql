--------------------------------
---------- 시퀀스 삭제-----------
--------------------------------
DROP SEQUENCE SEQ_MEMBER_NO;
DROP SEQUENCE SEQ_BOOSTER_PROD_NO;
DROP SEQUENCE SEQ_BOOSTER_REVIEW_NO;
DROP SEQUENCE SEQ_NORMAL_PROD_NO;
DROP SEQUENCE SEQ_NORMAL_REVIEW_NO;
DROP SEQUENCE SEQ_FOOD_PROD_NO;
DROP SEQUENCE SEQ_FOOD_REVIEW_NO;
DROP SEQUENCE SEQ_ANNOUNCEMENT_BAORD_NO;
DROP SEQUENCE SEQ_BAORD_NO;



--------------------------------
---------- 시퀀스 생성 -----------
--------------------------------
CREATE SEQUENCE SEQ_MEMBER_NO NOCACHE NOCYCLE;
CREATE SEQUENCE SEQ_BOOSTER_PROD_NO NOCACHE NOCYCLE;
CREATE SEQUENCE SEQ_BOOSTER_REVIEW_NO NOCACHE NOCYCLE;
CREATE SEQUENCE SEQ_NORMAL_PROD_NO NOCACHE NOCYCLE;
CREATE SEQUENCE SEQ_NORMAL_REVIEW_NO NOCACHE NOCYCLE;
CREATE SEQUENCE SEQ_FOOD_PROD_NO NOCACHE NOCYCLE;
CREATE SEQUENCE SEQ_FOOD_REVIEW_NO NOCACHE NOCYCLE;
CREATE SEQUENCE SEQ_ANNOUNCEMENT_BAORD_NO NOCACHE NOCYCLE;
CREATE SEQUENCE SEQ_BAORD_NO NOCACHE NOCYCLE;


--------------------------------
---------- 테이블 삭제 -----------
--------------------------------
DROP TABLE MEMBER CASCADE CONSTRAINTS;
DROP TABLE BOOSTER_PRODUCT cascade constraints;
DROP TABLE BOOSTER_REVIEW cascade constraints;
DROP TABLE NORMAL_PRODUCT CASCADE CONSTRAINTS;
DROP TABLE NORMAL_REVIEW CASCADE CONSTRAINTS;
DROP TABLE FOOD_PRODUCT cascade constraints;
DROP TABLE FOOD_REVIEW cascade constraints;
DROP TABLE ANNOUNCEMENT_BAORD CASCADE CONSTRAINTS;
DROP TABLE BOARD CASCADE CONSTRAINTS;



--------------------------------
---------- 테이블 생성 -----------
--------------------------------
CREATE TABLE MEMBER(
    NO                      NUMBER          CONSTRAINT PK_MEMBER_NO PRIMARY KEY
    , ID                    VARCHAR2(100)   CONSTRAINT NN_MEMBER_ID NOT NULL CONSTRAINT UQ_MEMBER_ID UNIQUE
    , PWD                   VARCHAR2(100)   CONSTRAINT NN_MEMBER_PWD NOT NULL
    , NICK                  VARCHAR2(100)   CONSTRAINT NN_MEMBER_NICK NOT NULL --우리 사이트는 닉네임 중복은 허용할거임
    , JOIN_DATE             TIMESTAMP       DEFAULT SYSDATE
    , MODIFY_DATE           TIMESTAMP       
    , QUIT_YN               CHAR(1)         DEFAULT 'N' CONSTRAINT CK_MEMBER_QUIT CHECK( QUIT_YN IN ('Y','N')  )
    , ADMIN_YN              CHAR(1)         DEFAULT 'N' CONSTRAINT CK_MEMBER_ISADMIN CHECK( ADMIN_YN IN ('Y','N')  )
);

CREATE TABLE BOOSTER_PRODUCT (
   BOOSTER_PROD_NO           NUMBER         PRIMARY KEY,
   NAME                      VARCHAR2(100)  NOT NULL,
   PRICE                     VARCHAR2(50),
   IS_DISCOUNTINUED_YN       CHAR(1)        DEFAULT 'Y' CONSTRAINT CK_DIS_YN_1 CHECK (IS_DISCOUNTINUED_YN IN ('Y','N')),
   DESCRIPTION               VARCHAR2(1000)      
);

CREATE TABLE BOOSTER_REVIEW (
   BOOSTER_REVIEW_NO         NUMBER         PRIMARY KEY,
   REVIEW                    VARCHAR2(1000) NULL,
   BOOSTER_PROD_NO           NUMBER,
   MEMBER_NO                 NUMBER,
   ENROLL_DATE               timestamp      DEFAULT SYSDATE,
   QUIT_YN                   CHAR(1)        DEFAULT 'N' CONSTRAINT CK_QUIT_YN_1 CHECK( QUIT_YN IN ('Y','N'))      
);

CREATE TABLE NORMAL_PRODUCT (
   NORMAL_PROD_NO           NUMBER          PRIMARY KEY,
   NAME                     VARCHAR2(100)   NOT NULL,
   PRICE                    VARCHAR2(50),
   IS_DISCOUNTINUED_YN      CHAR(1)         DEFAULT 'Y' CONSTRAINT CK_DIS_YN_2 CHECK (IS_DISCOUNTINUED_YN IN ('Y','N')),
   DESCRIPTION              VARCHAR2(1000)       
);

CREATE TABLE NORMAL_REVIEW (
   NORMAL_REVIEW_NO         NUMBER          PRIMARY KEY,
   REVIEW                   VARCHAR2(1000)  NULL,
   NORMAL_PROD_NO           NUMBER,
   WRITER_NO                NUMBER,
   ENROLL_DATE              TIMESTAMP       DEFAULT SYSDATE,
   QUIT_YN                  CHAR(1)         DEFAULT 'Y'CONSTRAINT CK_QUIT_YN_2 CHECK( QUIT_YN IN ('Y','N') )
);

CREATE TABLE FOOD_PRODUCT (
	FOOD_PROD_NO	        NUMBER          PRIMARY KEY ,
	NAME	                VARCHAR2(1000)  NOT NULL,
	PRICE	                VARCHAR2(50)		    ,
	IS_DISCOUNTINUED_YN	    CHAR(1)		    DEFAULT 'Y' CONSTRAINT CK_DIS_YN_3 CHECK (IS_DISCOUNTINUED_YN IN ('Y','N')),
	DESCRIPTION	            VARCHAR2(1000)	    
);

CREATE TABLE FOOD_REVIEW (
	FOOD_REVIEW_NO	        NUMBER          PRIMARY KEY,
	REVIEW	                VARCHAR2(1000)	NULL,
	FOOD_PROD_NO	        NUMBER,
	WRITER_NO	            NUMBER	,
	ENROLL_DATE	            timestamp       DEFAULT SYSDATE,
	QUIT_YN	                char(1)	        DEFAULT 'Y'CONSTRAINT CK_QUIT_YN_3 CHECK( QUIT_YN IN ('Y','N') )
);

CREATE TABLE ANNOUNCEMENT_BAORD (
    NO                  NUMBER              PRIMARY KEY
    , TITLE             VARCHAR2(1000)      CONSTRAINT NN_ANNOUNCEMENT_BAORD_TITLE NOT NULL
    , CONTENT           VARCHAR2(4000)      CONSTRAINT NN_ANNOUNCEMENT_BAORD_CONTENT NOT NULL
    , WRITER_NO         NUMBER
    , ENROLL_DATE       TIMESTAMP           DEFAULT SYSDATE
    , DEL_YN            CHAR(1)             DEFAULT 'N' CONSTRAINT CK_ANNOUNCEMENT_BAORD_DEL CHECK(DEL_YN IN ('Y','N'))
);

CREATE TABLE BOARD (
    NO                  NUMBER          PRIMARY KEY
    , TITLE             VARCHAR2(1000)  CONSTRAINT NN_BAORD_TITLE NOT NULL
    , CONTENT           VARCHAR2(4000)  CONSTRAINT NN_BAORD_CONTENT NOT NULL
    , WRITER_NO         NUMBER
    , ENROLL_DATE       TIMESTAMP       DEFAULT SYSDATE
    , DEL_YN            CHAR(1)         DEFAULT 'N' CONSTRAINT CK_BAORD_DEL CHECK(DEL_YN IN ('Y','N'))
);




--------------------------------
---------- 외래키 추가 -----------
--------------------------------

ALTER TABLE BOOSTER_REVIEW
ADD CONSTRAINT FK_BOOSTER_PROD_NO FOREIGN KEY (BOOSTER_PROD_NO) REFERENCES BOOSTER_PRODUCT(BOOSTER_PROD_NO);

ALTER TABLE BOOSTER_REVIEW
ADD CONSTRAINT FK_BR_MEMBERNO FOREIGN KEY (MEMBER_NO) REFERENCES MEMBER(NO);

ALTER TABLE NORMAL_REVIEW 
ADD CONSTRAINT FK_NORMAL_PROD_NO FOREIGN KEY (NORMAL_PROD_NO) REFERENCES NORMAL_PRODUCT(NORMAL_PROD_NO);

ALTER TABLE NORMAL_REVIEW
ADD CONSTRAINT FK_NORMAL_MEMBER_NO FOREIGN KEY (WRITER_NO) REFERENCES MEMBER(NO);

ALTER TABLE FOOD_REVIEW 
ADD CONSTRAINT FK_FOOD_PROD_NO FOREIGN KEY (FOOD_PROD_NO) REFERENCES FOOD_PRODUCT(FOOD_PROD_NO);

ALTER TABLE FOOD_REVIEW
ADD CONSTRAINT FK_FOOD_MEMBER_NO FOREIGN KEY (WRITER_NO) REFERENCES MEMBER(NO);

ALTER TABLE BOARD 
ADD CONSTRAINT FK_MEMBER_TO_BOARD_NO FOREIGN KEY (WRITER_NO)REFERENCES MEMBER (NO);


ALTER TABLE ANNOUNCEMENT_BAORD 
ADD CONSTRAINT FK_MEMBER_TO_ANNOUNCEMENT_BAORD_NO FOREIGN KEY (WRITER_NO)REFERENCES MEMBER (NO);


--------------------------------
---------- 데이터 삽입 -----------
--------------------------------
INSERT INTO MEMBER(NO, ID, PWD, NICK, ADMIN_YN) VALUES(SEQ_MEMBER_NO.NEXTVAL, 'admin', '1234', '관리자', 'Y'); -- 관리자계정 삽입


COMMIT;
