--------------------------------
---------- ������ ���� -----------
--------------------------------
DROP SEQUENCE SEQ_MEMBER_NO;
DROP SEQUENCE SEQ_BOOSTER_PROD_NO;
DROP SEQUENCE SEQ_BOOSTER_REVIEW_NO;
DROP SEQUENCE SEQ_NORMAL_PROD_NO;
DROP SEQUENCE SEQ_NORMAL_REVIEW_NO;
DROP SEQUENCE SEQ_FOOD_PROD_NO;
DROP SEQUENCE SEQ_FOOD_REVIEW_NO;
DROP SEQUENCE SEQ_ANNOUNCEMENT_BOARD_NO;
DROP SEQUENCE SEQ_BOARD_NO;



--------------------------------
---------- ������ ���� -----------
--------------------------------
CREATE SEQUENCE SEQ_MEMBER_NO NOCACHE NOCYCLE;
CREATE SEQUENCE SEQ_BOOSTER_PROD_NO NOCACHE NOCYCLE;
CREATE SEQUENCE SEQ_BOOSTER_REVIEW_NO NOCACHE NOCYCLE;
CREATE SEQUENCE SEQ_NORMAL_PROD_NO NOCACHE NOCYCLE;
CREATE SEQUENCE SEQ_NORMAL_REVIEW_NO NOCACHE NOCYCLE;
CREATE SEQUENCE SEQ_FOOD_PROD_NO NOCACHE NOCYCLE;
CREATE SEQUENCE SEQ_FOOD_REVIEW_NO NOCACHE NOCYCLE;
CREATE SEQUENCE SEQ_ANNOUNCEMENT_BOARD_NO NOCACHE NOCYCLE;
CREATE SEQUENCE SEQ_BOARD_NO NOCACHE NOCYCLE;


--------------------------------
---------- ���̺� ���� -----------
--------------------------------
DROP TABLE MEMBER CASCADE CONSTRAINTS;
DROP TABLE BOOSTER_PRODUCT cascade constraints;
DROP TABLE BOOSTER_REVIEW cascade constraints;
DROP TABLE NORMAL_PRODUCT CASCADE CONSTRAINTS;
DROP TABLE NORMAL_REVIEW CASCADE CONSTRAINTS;
DROP TABLE FOOD_PRODUCT cascade constraints;
DROP TABLE FOOD_REVIEW cascade constraints;
DROP TABLE ANNOUNCEMENT_BOARD CASCADE CONSTRAINTS;
DROP TABLE ANNOUNCEMENT_BAORD CASCADE CONSTRAINTS;
DROP TABLE BOARD CASCADE CONSTRAINTS;



--------------------------------
---------- ���̺� ���� -----------
--------------------------------
CREATE TABLE MEMBER(
    NO                      NUMBER          CONSTRAINT PK_MEMBER_NO PRIMARY KEY
    , ID                    VARCHAR2(100)   CONSTRAINT NN_MEMBER_ID NOT NULL CONSTRAINT UQ_MEMBER_ID UNIQUE
    , PWD                   VARCHAR2(100)   CONSTRAINT NN_MEMBER_PWD NOT NULL
    , NICK                  VARCHAR2(100)   CONSTRAINT NN_MEMBER_NICK NOT NULL --?���? ?��?��?��?�� ?��?��?�� 중복?? ?��?��?��거임
    , JOIN_DATE             TIMESTAMP       DEFAULT SYSDATE
    , MODIFY_DATE           TIMESTAMP       
    , QUIT_YN               CHAR(1)         DEFAULT 'N' CONSTRAINT CK_MEMBER_QUIT CHECK( QUIT_YN IN ('Y','N')  )
    , ADMIN_YN              CHAR(1)         DEFAULT 'N' CONSTRAINT CK_MEMBER_ISADMIN CHECK( ADMIN_YN IN ('Y','N')  )
);

CREATE TABLE BOOSTER_PRODUCT (
   BOOSTER_PROD_NO           NUMBER         PRIMARY KEY,
   NAME                      VARCHAR2(100)  NOT NULL,
   PRICE                     NUMBER,
   IS_DISCOUNTINUED_YN       CHAR(1)        DEFAULT 'N' CONSTRAINT CK_DIS_YN_1 CHECK (IS_DISCOUNTINUED_YN IN ('Y','N')),
   DESCRIPTION               VARCHAR2(1000)      
);

CREATE TABLE BOOSTER_REVIEW (
   BOOSTER_REVIEW_NO         NUMBER         PRIMARY KEY,
   REVIEW_TITLE              VARCHAR2(100),
   REVIEW                    VARCHAR2(1000) NULL,
   BOOSTER_PROD_NO           NUMBER,
   MEMBER_NO                 NUMBER,
   ENROLL_DATE               timestamp      DEFAULT SYSDATE,
   QUIT_YN                   CHAR(1)        DEFAULT 'N' CONSTRAINT CK_QUIT_YN_1 CHECK( QUIT_YN IN ('Y','N'))      
);

CREATE TABLE NORMAL_PRODUCT (
   NORMAL_PROD_NO           NUMBER          PRIMARY KEY,
   NAME                     VARCHAR2(100)   NOT NULL,
   PRICE                    NUMBER,
   IS_DISCOUNTINUED_YN      CHAR(1)         DEFAULT 'N' CONSTRAINT CK_DIS_YN_2 CHECK (IS_DISCOUNTINUED_YN IN ('Y','N')),
   DESCRIPTION              VARCHAR2(1000)       
);

CREATE TABLE NORMAL_REVIEW (
   NORMAL_REVIEW_NO         NUMBER          PRIMARY KEY,
   REVIEW_TITLE             VARCHAR2(100),
   REVIEW                   VARCHAR2(1000)  NULL,
   NORMAL_PROD_NO           NUMBER,
   WRITER_NO                NUMBER,
   ENROLL_DATE              TIMESTAMP       DEFAULT SYSDATE,
   QUIT_YN                  CHAR(1)         DEFAULT 'N'CONSTRAINT CK_QUIT_YN_2 CHECK( QUIT_YN IN ('Y','N') )
);

CREATE TABLE FOOD_PRODUCT (
	FOOD_PROD_NO	        NUMBER          PRIMARY KEY ,
	NAME	                VARCHAR2(1000)  NOT NULL,
	PRICE	                NUMBER		    ,
	IS_DISCOUNTINUED_YN	    CHAR(1)		    DEFAULT 'N' CONSTRAINT CK_DIS_YN_3 CHECK (IS_DISCOUNTINUED_YN IN ('Y','N')),
	DESCRIPTION	            VARCHAR2(1000)	    
);

CREATE TABLE FOOD_REVIEW (
	FOOD_REVIEW_NO	        NUMBER          PRIMARY KEY,
    REVIEW_TITLE            VARCHAR2(100),
	REVIEW	                VARCHAR2(1000)	NULL,
	FOOD_PROD_NO	        NUMBER,
	WRITER_NO	            NUMBER	,
	ENROLL_DATE	            timestamp       DEFAULT SYSDATE,
	QUIT_YN	                char(1)	        DEFAULT 'N'CONSTRAINT CK_QUIT_YN_3 CHECK( QUIT_YN IN ('Y','N') )
);

CREATE TABLE ANNOUNCEMENT_BOARD (
    NO                  NUMBER              PRIMARY KEY
    , TITLE             VARCHAR2(1000)      CONSTRAINT NN_ANNOUNCEMENT_BOARD_TITLE NOT NULL
    , CONTENT           VARCHAR2(4000)      CONSTRAINT NN_ANNOUNCEMENT_BOARD_CONTENT NOT NULL
    , WRITER_NO         NUMBER
    , ENROLL_DATE       TIMESTAMP           DEFAULT SYSDATE
    , DEL_YN            CHAR(1)             DEFAULT 'N' CONSTRAINT CK_ANNOUNCEMENT_BOARD_DEL CHECK(DEL_YN IN ('Y','N'))
);

CREATE TABLE BOARD (
    NO                  NUMBER          PRIMARY KEY
    , TITLE             VARCHAR2(1000)  CONSTRAINT NN_BOARD_TITLE NOT NULL
    , CONTENT           VARCHAR2(4000)  CONSTRAINT NN_BOARD_CONTENT NOT NULL
    , WRITER_NO         NUMBER
    , ENROLL_DATE       TIMESTAMP       DEFAULT SYSDATE
    , DEL_YN            CHAR(1)         DEFAULT 'N' CONSTRAINT CK_BAORD_DEL CHECK(DEL_YN IN ('Y','N'))
);




--------------------------------
---------- �ܷ�Ű ���� -----------
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


ALTER TABLE ANNOUNCEMENT_BOARD 
ADD CONSTRAINT FK_MEMBER_TO_ANNOUNCEMENT_BOARD_NO FOREIGN KEY (WRITER_NO)REFERENCES MEMBER (NO);


--------------------------------
---------- ���̵����� ���� -----------
--------------------------------

------------- ���̵� �߰� -------------
INSERT INTO MEMBER(NO, ID, PWD, NICK, ADMIN_YN) VALUES(SEQ_MEMBER_NO.NEXTVAL, 'admin', '1234', '������', 'Y'); -- �?리자계정 ?��?��
INSERT INTO MEMBER(NO, ID, PWD, NICK) VALUES(SEQ_MEMBER_NO.NEXTVAL, 'nyamnyam', '1234', 'nyamgu'); 
INSERT INTO MEMBER(NO, ID, PWD, NICK) VALUES(SEQ_MEMBER_NO.NEXTVAL, 'gugu', '1234', 'gugu'); 
INSERT INTO MEMBER(NO, ID, PWD, NICK) VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user03', '1234', 'nick03'); 
INSERT INTO MEMBER(NO, ID, PWD, NICK) VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user04', '1234', 'nicknick'); 
INSERT INTO MEMBER(NO, ID, PWD, NICK) VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user05', 'password', 'nicksix'); 
INSERT INTO MEMBER(NO, ID, PWD, NICK) VALUES(SEQ_MEMBER_NO.NEXTVAL, 'hoho', '1234', 'hehe'); 

------------- ���� ��ǰ �߰� -------------
INSERT INTO FOOD_PRODUCT(FOOD_PROD_NO, NAME, PRICE, DESCRIPTION) VALUES(SEQ_FOOD_PROD_NO.NEXTVAL, '�����̾� �߰�����', '2000', '5���� ������ ���� ����� ���� ǰ��');
INSERT INTO FOOD_PRODUCT(FOOD_PROD_NO, NAME, PRICE, DESCRIPTION) VALUES(SEQ_FOOD_PROD_NO.NEXTVAL, '�߰����� ���ö�', '9000', 'ź��ȭ�� 51g �ܹ��� 39g ����6g ���̾�Ʈ �Ҷ� �ѳ��� �Ա� ���� ��ǰ');
INSERT INTO FOOD_PRODUCT(FOOD_PROD_NO, NAME, PRICE, DESCRIPTION) VALUES(SEQ_FOOD_PROD_NO.NEXTVAL, '�߰����� ����� ��ü', '1700', '������ �߰������ �귱�� ���� ����� �ӳ��� Ȱ�뵵');
INSERT INTO FOOD_PRODUCT(FOOD_PROD_NO, NAME, PRICE, DESCRIPTION) VALUES(SEQ_FOOD_PROD_NO.NEXTVAL, '���̾�Ʈ �ѽ� ���ö�', '16000', 'ü�������� ���̾�Ʈ ���ö� �������� �� �Ծ 360Kcal');
INSERT INTO FOOD_PRODUCT(FOOD_PROD_NO, NAME, PRICE, DESCRIPTION) VALUES(SEQ_FOOD_PROD_NO.NEXTVAL, '���캺����AND���׶���', '21900', '����� ���� ä�Ұ� ���� ����־� �������� ����� ���� ����');
INSERT INTO FOOD_PRODUCT(FOOD_PROD_NO, NAME, PRICE, DESCRIPTION) VALUES(SEQ_FOOD_PROD_NO.NEXTVAL, '�Ұ�⺼ ��������', '27000', '�ε巯�� ȣ�ֻ� �Ұ�⸦ ��� �����ϰ� ���� �� �ִ� ���� ũ��');
INSERT INTO FOOD_PRODUCT(FOOD_PROD_NO, NAME, PRICE, DESCRIPTION) VALUES(SEQ_FOOD_PROD_NO.NEXTVAL, '�Ұ�� û����� ������ũ', '29000', '���ְ� �ʰ� ������ �ʰ� ������ �ִ� ������ũ');
INSERT INTO FOOD_PRODUCT(FOOD_PROD_NO, NAME, PRICE, DESCRIPTION) VALUES(SEQ_FOOD_PROD_NO.NEXTVAL, '�߰��� ��Ʈ', '45000', '�츮���� �ʿ��� 5���� ������ �ż��� �߰��� ���');
INSERT INTO FOOD_PRODUCT(FOOD_PROD_NO, NAME, PRICE, DESCRIPTION) VALUES(SEQ_FOOD_PROD_NO.NEXTVAL, 'Ǯ�� ������ ��ƮAND����Ÿ', '23700','Ǫ���� ��� ����Ƽ�� �Ѵ� ��� ������ ������ ���� �ʴ� ��ǰ');
INSERT INTO FOOD_PRODUCT(FOOD_PROD_NO, NAME, PRICE, DESCRIPTION) VALUES(SEQ_FOOD_PROD_NO.NEXTVAL, '���� ������ AYPE', '11900', '��ô�� ������ �Ǿ� �ٷ� �Ծ OK �Ŀ�ġ �������� ��𼭵��� �Ա����� ��ǰ');
INSERT INTO FOOD_PRODUCT(FOOD_PROD_NO, NAME, PRICE, DESCRIPTION) VALUES(SEQ_FOOD_PROD_NO.NEXTVAL, 'DAILY ������ �׸�100g', '2100', '������ ä�Ҹ� ��� ���ϴ� ���ΰ� �ҽ��� ������ ������ ����� ����');
INSERT INTO FOOD_PRODUCT(FOOD_PROD_NO, NAME, PRICE, DESCRIPTION) VALUES(SEQ_FOOD_PROD_NO.NEXTVAL, '����ġŲ ���� �ߴٸ���', '6000', '���쿡 ���� �⸧�� ���� ���������̱�� �����ϰ� ������ �츮 ��');
INSERT INTO FOOD_PRODUCT(FOOD_PROD_NO, NAME, PRICE, DESCRIPTION) VALUES(SEQ_FOOD_PROD_NO.NEXTVAL, '���̽� �� ���� ȣ�� ����', '11900', '��~�� ������ �˵�~�� ȣ�ڰ��� ��!');
INSERT INTO FOOD_PRODUCT(FOOD_PROD_NO, NAME, PRICE, DESCRIPTION) VALUES(SEQ_FOOD_PROD_NO.NEXTVAL, 'ġ��ǰ�� ����', '4900', '���� �� ġ���� ȯ�� �ɹ�');
INSERT INTO FOOD_PRODUCT(FOOD_PROD_NO, NAME, PRICE, DESCRIPTION) VALUES(SEQ_FOOD_PROD_NO.NEXTVAL, '�˶���� ���ø�', '3000', '������� �ٷ� �Դ� ���� ������ ���� ���� 60Kcal ������ ����');


------------- ���� ���� �߰� -------------
INSERT INTO FOOD_REVIEW(FOOD_REVIEW_NO, REVIEW_TITLE, REVIEW, FOOD_PROD_NO, WRITER_NO, ENROLL_DATE) VALUES(SEQ_FOOD_REVIEW_NO.NEXTVAL, '�ѽ� ���ö� ����', '���� �ѳ� ����ϰ� �԰� ������ ��õ�帳�ϴ�. ���� ���־���!!', 4, 3,SYSDATE);
INSERT INTO FOOD_REVIEW(FOOD_REVIEW_NO, REVIEW_TITLE, REVIEW, FOOD_PROD_NO, WRITER_NO, ENROLL_DATE) VALUES(SEQ_FOOD_REVIEW_NO.NEXTVAL, '�߰����� ���ö�', '�Ϲ� ���ö� ���� �Ա� ���ƿ�!!! ������ �� ��ų ������ ��õ �帳�ϴ�!!!', 2, 4,SYSDATE);
INSERT INTO FOOD_REVIEW(FOOD_REVIEW_NO, REVIEW_TITLE, REVIEW, FOOD_PROD_NO, WRITER_NO, ENROLL_DATE) VALUES(SEQ_FOOD_REVIEW_NO.NEXTVAL, '��õ �� ���� ������!!', 'ü�� ���� �Ϸ��� ���� ������ �԰� �ִµ� ���� ���ƿ� ���� �Ծ ������ �ʴ� �����忡��!!!', 9, 2,SYSDATE);
INSERT INTO FOOD_REVIEW(FOOD_REVIEW_NO, REVIEW_TITLE, REVIEW, FOOD_PROD_NO, WRITER_NO, ENROLL_DATE) VALUES(SEQ_FOOD_REVIEW_NO.NEXTVAL, '������ ����~~~', '���� ���� �������ϴµ� �� ������ ���� ���־��!!!!', 14, 5,SYSDATE);
INSERT INTO FOOD_REVIEW(FOOD_REVIEW_NO, REVIEW_TITLE, REVIEW, FOOD_PROD_NO, WRITER_NO, ENROLL_DATE) VALUES(SEQ_FOOD_REVIEW_NO.NEXTVAL, '���� �ϱ� ���� �߰���^^', '��ϴ� ģ���鿡�� �����ϱ� ���ƿ� �絵 ���� ������ �ߵǾ� �־� �����ϴ�', 8, 6,SYSDATE);
INSERT INTO FOOD_REVIEW(FOOD_REVIEW_NO, REVIEW_TITLE, REVIEW, FOOD_PROD_NO, WRITER_NO, ENROLL_DATE) VALUES(SEQ_FOOD_REVIEW_NO.NEXTVAL, '�������� �Ա� ���� ����', '�������� �ԱⰡ ���Ƽ� ���� ��Խ��ϴ�~ �߰������� ���ܿ�� �ѹ��� �Ա� ���ƿ�', 12, 5,SYSDATE);
INSERT INTO FOOD_REVIEW(FOOD_REVIEW_NO, REVIEW_TITLE, REVIEW, FOOD_PROD_NO, WRITER_NO, ENROLL_DATE) VALUES(SEQ_FOOD_REVIEW_NO.NEXTVAL, '�̰� ������..... ����..', '���ø� �԰� �; �Ծ�ôµ� ��� Ư�� ������ ���� ���� �Ա� ��������Ф�', 15, 2,SYSDATE);
INSERT INTO FOOD_REVIEW(FOOD_REVIEW_NO, REVIEW_TITLE, REVIEW, FOOD_PROD_NO, WRITER_NO, ENROLL_DATE) VALUES(SEQ_FOOD_REVIEW_NO.NEXTVAL, '���� ���ܿ�� �̰� �Ծ����^^', '�߰��� �ʹ� ���ܿ��� ���Ѻôµ� ���� ���ƿ� ������ ������ ������ ���ƿ� ������ �� ��μ� �̺�Ʈ�Ҷ� ��� ������ ���ƿ�', 7, 4,SYSDATE);
INSERT INTO FOOD_REVIEW(FOOD_REVIEW_NO, REVIEW_TITLE, REVIEW, FOOD_PROD_NO, WRITER_NO, ENROLL_DATE) VALUES(SEQ_FOOD_REVIEW_NO.NEXTVAL, '���ִ� ������ ã��!!!!!!', '��¥ �ǳ� ���̾�Ʈ ���ö��� �Դٰ� �̹��� �������־� �Ծ�ôµ� ���� ���־��!! ���̾�Ʈ ���ö� ���ܿ�� �ѹ��� �Ծ�߰ھ��', 5, 7,SYSDATE);
INSERT INTO FOOD_REVIEW(FOOD_REVIEW_NO, REVIEW_TITLE, REVIEW, FOOD_PROD_NO, WRITER_NO, ENROLL_DATE) VALUES(SEQ_FOOD_REVIEW_NO.NEXTVAL, '�����ϰ� ���� ���� �ѳ� �Ļ� ���', '��ħ�� ����ϱ��� ��Ա� ���鶧 �ϳ� ì�ܰ��� �Ա� ���ƿ� �������� ����ϰ� ���ƿ�', 3, 6,SYSDATE);
INSERT INTO FOOD_REVIEW(FOOD_REVIEW_NO, REVIEW_TITLE, REVIEW, FOOD_PROD_NO, WRITER_NO, ENROLL_DATE) VALUES(SEQ_FOOD_REVIEW_NO.NEXTVAL, '��� �Ա����� �߰�����', '������ �߰������� �ƴ� �����ϰ� �Ա� ���� �߰������̿��� �� �̰Ÿ԰� 5KG �����!!', 1, 3,SYSDATE);
INSERT INTO FOOD_REVIEW(FOOD_REVIEW_NO, REVIEW_TITLE, REVIEW, FOOD_PROD_NO, WRITER_NO, ENROLL_DATE) VALUES(SEQ_FOOD_REVIEW_NO.NEXTVAL, '�Ұ�� �԰� ������ �������� ��õ@@@@', '���� ũ�⿩�� �������� �Ա� ���ƿ� �׸��� �������� �Ұ�� �İ��� ���� ���� ��¥ �ҸԴ� ����̿���~~~!!', 6, 4,SYSDATE);
INSERT INTO FOOD_REVIEW(FOOD_REVIEW_NO, REVIEW_TITLE, REVIEW, FOOD_PROD_NO, WRITER_NO, ENROLL_DATE) VALUES(SEQ_FOOD_REVIEW_NO.NEXTVAL, '������ �������� ����....', '���ۺ��� �Ծ�ôµ� ���Ը������� ����..... �׷��� ���׶����� ���־�������', 5, 6,SYSDATE);
INSERT INTO FOOD_REVIEW(FOOD_REVIEW_NO, REVIEW_TITLE, REVIEW, FOOD_PROD_NO, WRITER_NO, ENROLL_DATE) VALUES(SEQ_FOOD_REVIEW_NO.NEXTVAL, '���ۺ��� ���ִ� ���� ã��!!', '��α��ѵ� �������� ���� ������!! ȣ�� ���� �����ϸ� �̰� ���� �Ծ����', 13, 2,SYSDATE);
INSERT INTO FOOD_REVIEW(FOOD_REVIEW_NO, REVIEW_TITLE, REVIEW, FOOD_PROD_NO, WRITER_NO, ENROLL_DATE) VALUES(SEQ_FOOD_REVIEW_NO.NEXTVAL, 'ä�� ��� ���鶧 �������� ������', 'ä�һ缭 ���� �Һ��ϱ� ���絥 �̰� ��� �Ա� �׷� ������ ���� ��� ���ƿ� �Һ� �������� ��¥ ��û ��õ!!!!!', 10, 7,SYSDATE);


------------- BOOSTER ��ǰ �߰� -------------
INSERT INTO BOOSTER_PRODUCT (BOOSTER_PROD_NO, NAME, PRICE, IS_DISCOUNTINUED_YN, DESCRIPTION)
VALUES (SEQ_BOOSTER_PROD_NO.NEXTVAL, 'EVLution Nutrition, ENGN, Pre-workout Engine, ��� ����� ��, 312g(11oz)', 27952, 'N', '��Ģ���� ��� �����ϸ� ENGN�� Pre-Workout �������� �����ϸ� ���� ���鿡�� ȿ���� �� �� �ֽ��ϴ�. ���߷�, ������, ��ɷ�');
INSERT INTO BOOSTER_PRODUCT (BOOSTER_PROD_NO, NAME, PRICE, IS_DISCOUNTINUED_YN, DESCRIPTION)
VALUES (SEQ_BOOSTER_PROD_NO.NEXTVAL, '�񿡽��� ��Ÿ6�ܹ����Ŀ�� ��Ű��ũ���� 28ȸ��', 34900, 'N', '���� ���ִ� ���� �ܹ���, ��Ÿ6-��Ű��ũ����');
INSERT INTO BOOSTER_PRODUCT (BOOSTER_PROD_NO, NAME, PRICE, IS_DISCOUNTINUED_YN, DESCRIPTION)
VALUES (SEQ_BOOSTER_PROD_NO.NEXTVAL, 'JNX Sports, The Curse, � �� ������, ü�� ���ӿ��̵� ��, 150g(5.3oz)', 24104, 'N', 'The Curse!���� ���ڿ����� ������ ��ü�� ������ ������� ����ʽÿ�.');
INSERT INTO BOOSTER_PRODUCT (BOOSTER_PROD_NO, NAME, PRICE, IS_DISCOUNTINUED_YN, DESCRIPTION)
VALUES (SEQ_BOOSTER_PROD_NO.NEXTVAL, '������ �ｺ�ν���', 19900, 'N', '� ���ۺ��� ������ ������ ���ΰ��� ������ �ֵ��� ���� ������ �ｺ �ν����Դϴ�.');
INSERT INTO BOOSTER_PRODUCT (BOOSTER_PROD_NO, NAME, PRICE, IS_DISCOUNTINUED_YN, DESCRIPTION)
VALUES (SEQ_BOOSTER_PROD_NO.NEXTVAL, '�ͽ�Ʈ�� OPTIMA BCAA Ÿ�츰 7000+', 20865, 'N', '9�� �ʼ� �ƹ̳�� ���� �Ϸ�!');
INSERT INTO BOOSTER_PRODUCT (BOOSTER_PROD_NO, NAME, PRICE, IS_DISCOUNTINUED_YN, DESCRIPTION)
VALUES (SEQ_BOOSTER_PROD_NO.NEXTVAL, '�ͽ�Ʈ�� Ʈ���� �Ƹ���� 6200', 18315, 'N', '4�߿����� ����Ŭ ����� UP �ó��� 2��');
INSERT INTO BOOSTER_PRODUCT (BOOSTER_PROD_NO, NAME, PRICE, IS_DISCOUNTINUED_YN, DESCRIPTION)
VALUES (SEQ_BOOSTER_PROD_NO.NEXTVAL, '�ͽ�Ʈ�� �Ƹ���� ������ƽ', 18315, 'N', '��ܹ� 10g ���� LOW SUGAR');
INSERT INTO BOOSTER_PRODUCT (BOOSTER_PROD_NO, NAME, PRICE, IS_DISCOUNTINUED_YN, DESCRIPTION)
VALUES (SEQ_BOOSTER_PROD_NO.NEXTVAL, '�ͽ�Ʈ�� ��� �Ƹ���� �÷���', 18315, 'N', 'Ȱ�� Ư�� ���� ���̿��丰 ����');
INSERT INTO BOOSTER_PRODUCT (BOOSTER_PROD_NO, NAME, PRICE, IS_DISCOUNTINUED_YN, DESCRIPTION)
VALUES (SEQ_BOOSTER_PROD_NO.NEXTVAL, '�ͽ�Ʈ�� �۷�Ÿ�� ZMA', 18315, 'N', '�۷�Ÿ�� + ZMA �ڽ��ְ� �����̴� ����');
INSERT INTO BOOSTER_PRODUCT (BOOSTER_PROD_NO, NAME, PRICE, IS_DISCOUNTINUED_YN, DESCRIPTION)
VALUES (SEQ_BOOSTER_PROD_NO.NEXTVAL, '�����ھ� C4 �������� ������ũ�ƿ� �׸����ø�', 41560, 'N', '� �� �����ϴ� ���� ������');
INSERT INTO BOOSTER_PRODUCT (BOOSTER_PROD_NO, NAME, PRICE, IS_DISCOUNTINUED_YN, DESCRIPTION)
VALUES (SEQ_BOOSTER_PROD_NO.NEXTVAL, '�����ھ� C4 �������� ������ũ�ƿ� ����Ʈ ��ġ ��', 41560, 'N', '� �� �����ϴ� ���� ������');
INSERT INTO BOOSTER_PRODUCT (BOOSTER_PROD_NO, NAME, PRICE, IS_DISCOUNTINUED_YN, DESCRIPTION)
VALUES (SEQ_BOOSTER_PROD_NO.NEXTVAL, '����� ���� ��ī���� �Ƹ���� �ｺ�ν��� 70ml 30��', 39500, 'N', '6000mg�� �Ƴ����� ���Է� �Ƹ����');
INSERT INTO BOOSTER_PRODUCT (BOOSTER_PROD_NO, NAME, PRICE, IS_DISCOUNTINUED_YN, DESCRIPTION)
VALUES (SEQ_BOOSTER_PROD_NO.NEXTVAL, '��Ʈ������ L �Ƹ����, 120��, 2��', 22710, 'N', '�ٻ� ����� ���� ������ ����!');
INSERT INTO BOOSTER_PRODUCT (BOOSTER_PROD_NO, NAME, PRICE, IS_DISCOUNTINUED_YN, DESCRIPTION)
VALUES (SEQ_BOOSTER_PROD_NO.NEXTVAL, 'EVLUTIONNUTRITION ���� ����ƾ ���� ��ġ ���ݸ� ��', 47100, 'N', '��ǰ���� �и� ��û �ܹ��� �Ŀ���Դϴ�.');
INSERT INTO BOOSTER_PRODUCT (BOOSTER_PROD_NO, NAME, PRICE, IS_DISCOUNTINUED_YN, DESCRIPTION)
VALUES (SEQ_BOOSTER_PROD_NO.NEXTVAL, 'EVLUTIONNUTRITION ���� ����ƾ �����', 47100, 'N', '��ǰ���� �и� ��û �ܹ��� �Ŀ���Դϴ�.');



------------- BOOSTER ���� �߰� -------------
INSERT INTO BOOSTER_REVIEW (BOOSTER_REVIEW_NO, REVIEW_TITLE,
REVIEW, BOOSTER_PROD_NO, MEMBER_NO, ENROLL_DATE, QUIT_YN)
VALUES (SEQ_BOOSTER_REVIEW_NO.NEXTVAL, '����', '�ϴ� ���� ���ƿ� �׸��� �������� �鿡�� ��ǰ���̱� ������ ������ �� �����̾ ����� ì�ܸ԰� �־��.', SEQ_BOOSTER_PROD_NO.CURRVAL, 2,
SYSDATE, 'N');
INSERT INTO BOOSTER_PRODUCT (BOOSTER_PROD_NO, NAME, PRICE, IS_DISCOUNTINUED_YN, DESCRIPTION)
VALUES (SEQ_BOOSTER_PROD_NO.NEXTVAL, '�񿡽��� ��Ÿ6�ܹ����Ŀ�� ��Ű��ũ���� 28ȸ��', 34900, 'N', '���� ���ִ� ���� �ܹ���, ��Ÿ6-��Ű��ũ����');
INSERT INTO BOOSTER_REVIEW (BOOSTER_REVIEW_NO, REVIEW_TITLE,
REVIEW, BOOSTER_PROD_NO, MEMBER_NO, ENROLL_DATE, QUIT_YN)
VALUES (SEQ_BOOSTER_REVIEW_NO.NEXTVAL, '���󺸴� ���־��', '��Ÿ6�� �Ǹ��� �ܹ������޿��Դϴ�.', SEQ_BOOSTER_PROD_NO.CURRVAL, 3,
SYSDATE, 'N');
INSERT INTO BOOSTER_REVIEW (BOOSTER_REVIEW_NO, REVIEW_TITLE,
REVIEW, BOOSTER_PROD_NO, MEMBER_NO, ENROLL_DATE, QUIT_YN)
VALUES (SEQ_BOOSTER_REVIEW_NO.NEXTVAL, '��ħ ��� �����ϴ�.', '��ħ ��� �غ��߾��. ü���� ���� ���ƿ�. ǰ���� ���� �ʿ�� �ϴ� �Ϳ� �Ϻ��մϴ�. �ٽ� �� ���Դϴ�. ���� ���� ��ħ �װ��� ���� �ϰ��� ��´�.', SEQ_BOOSTER_PROD_NO.CURRVAL, 4,
SYSDATE, 'N');
INSERT INTO BOOSTER_REVIEW (BOOSTER_REVIEW_NO, REVIEW_TITLE,
REVIEW, BOOSTER_PROD_NO, MEMBER_NO, ENROLL_DATE, QUIT_YN)
VALUES (SEQ_BOOSTER_REVIEW_NO.NEXTVAL, '���ƿ�.', '���ƿ�.', SEQ_BOOSTER_PROD_NO.CURRVAL, 5,
SYSDATE, 'N');
INSERT INTO BOOSTER_REVIEW (BOOSTER_REVIEW_NO, REVIEW_TITLE,
REVIEW, BOOSTER_PROD_NO, MEMBER_NO, ENROLL_DATE, QUIT_YN)
VALUES (SEQ_BOOSTER_REVIEW_NO.NEXTVAL, '���� ��� �����մϴ�.', '����� ���� ���ƿ�.', SEQ_BOOSTER_PROD_NO.CURRVAL, 6,
SYSDATE, 'N');
INSERT INTO BOOSTER_REVIEW (BOOSTER_REVIEW_NO, REVIEW_TITLE,
REVIEW, BOOSTER_PROD_NO, MEMBER_NO, ENROLL_DATE, QUIT_YN)
VALUES (SEQ_BOOSTER_REVIEW_NO.NEXTVAL, '���ƿ�', '�Ŷ��� ������ �������Դϴ�. �����ϱ� �԰�����?', SEQ_BOOSTER_PROD_NO.CURRVAL, 3,
SYSDATE, 'N');
INSERT INTO BOOSTER_REVIEW (BOOSTER_REVIEW_NO, REVIEW_TITLE,
REVIEW, BOOSTER_PROD_NO, MEMBER_NO, ENROLL_DATE, QUIT_YN)
VALUES (SEQ_BOOSTER_REVIEW_NO.NEXTVAL, '���', '���� �Դ� ��ǰ���ٵ� �Է��� ������ ã�� �־��µ� ���Դϴ�.', SEQ_BOOSTER_PROD_NO.CURRVAL, 2,
SYSDATE, 'N');
INSERT INTO BOOSTER_REVIEW (BOOSTER_REVIEW_NO, REVIEW_TITLE,
REVIEW, BOOSTER_PROD_NO, MEMBER_NO, ENROLL_DATE, QUIT_YN)
VALUES (SEQ_BOOSTER_REVIEW_NO.NEXTVAL, '�̰� ������ �ȵ�', '���� �̰� ������ ��� ���մϴ�. ��Ͻôºе� ����!', SEQ_BOOSTER_PROD_NO.CURRVAL, 4,
SYSDATE, 'N');
INSERT INTO BOOSTER_REVIEW (BOOSTER_REVIEW_NO, REVIEW_TITLE,
REVIEW, BOOSTER_PROD_NO, MEMBER_NO, ENROLL_DATE, QUIT_YN)
VALUES (SEQ_BOOSTER_REVIEW_NO.NEXTVAL, '���� ��ǰ', '�Ź� ���� ���·� �۷�Ÿ���� ���� �Ͽ��µ� ���� ��ǰ ã���� ���� �߽��ϴ�.', SEQ_BOOSTER_PROD_NO.CURRVAL, 3,
SYSDATE, 'N');
INSERT INTO BOOSTER_REVIEW (BOOSTER_REVIEW_NO, REVIEW_TITLE,
REVIEW, BOOSTER_PROD_NO, MEMBER_NO, ENROLL_DATE, QUIT_YN)
VALUES (SEQ_BOOSTER_REVIEW_NO.NEXTVAL, '��õ�մϴ�', '���� ������ Ʈ���̳��Դϴ�. �����ھ�� ���� ��� ���� �Ծ�ôµ� ���������� �� ���� �ż��ϰ� ���ƿ�', SEQ_BOOSTER_PROD_NO.CURRVAL, 5,
SYSDATE, 'N');
INSERT INTO BOOSTER_REVIEW (BOOSTER_REVIEW_NO, REVIEW_TITLE,
REVIEW, BOOSTER_PROD_NO, MEMBER_NO, ENROLL_DATE, QUIT_YN)
VALUES (SEQ_BOOSTER_REVIEW_NO.NEXTVAL, '�ʹ� �����ϴ�', '��� ���� ȿ���� ȿ������ �㿡 ���� �� �� ������ �����մϴ�.', SEQ_BOOSTER_PROD_NO.CURRVAL, 4,
SYSDATE, 'N');
INSERT INTO BOOSTER_REVIEW (BOOSTER_REVIEW_NO, REVIEW_TITLE,
REVIEW, BOOSTER_PROD_NO, MEMBER_NO, ENROLL_DATE, QUIT_YN)
VALUES (SEQ_BOOSTER_REVIEW_NO.NEXTVAL, '�Ա� �����ؿ�', 'ĥĥ�̵��� �� �԰ھ�� �� �Դ� �ʿ� �� ������', SEQ_BOOSTER_PROD_NO.CURRVAL, 3,
SYSDATE, 'N');
INSERT INTO BOOSTER_REVIEW (BOOSTER_REVIEW_NO, REVIEW_TITLE,
REVIEW, BOOSTER_PROD_NO, MEMBER_NO, ENROLL_DATE, QUIT_YN)
VALUES (SEQ_BOOSTER_REVIEW_NO.NEXTVAL, '������ �����ؿ�', '���� ���� �����ߴµ� ������ ü�µ� �������� �ǰ��ؼ� �����߾��.', SEQ_BOOSTER_PROD_NO.CURRVAL, 4,
SYSDATE, 'N');
INSERT INTO BOOSTER_REVIEW (BOOSTER_REVIEW_NO, REVIEW_TITLE,
REVIEW, BOOSTER_PROD_NO, MEMBER_NO, ENROLL_DATE, QUIT_YN)
VALUES (SEQ_BOOSTER_REVIEW_NO.NEXTVAL, '��õ�ؿ�', '���簡 ���� �� Ǯ���� ���� ���� ���帧�� ���ٰų� ������ �ʽ��ϴ�.', SEQ_BOOSTER_PROD_NO.CURRVAL, 2,
SYSDATE, 'N');
INSERT INTO BOOSTER_REVIEW (BOOSTER_REVIEW_NO, REVIEW_TITLE,
REVIEW, BOOSTER_PROD_NO, MEMBER_NO, ENROLL_DATE, QUIT_YN)
VALUES (SEQ_BOOSTER_REVIEW_NO.NEXTVAL, '��û �ް� ���ƿ�.', '���� ������ �����ϰų� ������ ���� ���� �� �ѵ� �����ϰ� ����.', SEQ_BOOSTER_PROD_NO.CURRVAL, 6,
SYSDATE, 'N');

------------- �������� �߰� -------------
INSERT INTO ANNOUNCEMENT_BOARD(NO, TITLE, CONTENT, WRITER_NO) VALUES(SEQ_ANNOUNCEMENT_BOARD_NO.NEXTVAL , '�Խ��� �̿� ��Ģ �ȳ�.' , 'ȸ�� �����е��� �Խ��� ������ ���� ���Ǹ� ���߰� �����ϴ� �����⸦ ������ �ֽñ� �ٶ��ϴ�.' || CHR(10) || '     ������ �弳 ��� �� �̿� ���� ��ġ �� �� ������ �����Ͻñ� �ٶ��ϴ�.' || CHR(10) || '  �����մϴ�.', '1');
INSERT INTO ANNOUNCEMENT_BOARD(NO, TITLE, CONTENT, WRITER_NO) VALUES(SEQ_ANNOUNCEMENT_BOARD_NO.NEXTVAL , '���� ���� ����.' , '���� �� ���� ���񽺸� ���� ���� ������ ������ �����Դϴ�.' || CHR(10) || '     ����6�� ~ 9�� ���� ����Ʈ �̿��� ��ƽ��ϴ�.(�ð��� ��Ȳ�� ���� ���� �� �� �ֽ��ϴ�.)' || CHR(10) || '  �����մϴ�.', '1');
INSERT INTO ANNOUNCEMENT_BOARD(NO, TITLE, CONTENT, WRITER_NO) VALUES(SEQ_ANNOUNCEMENT_BOARD_NO.NEXTVAL , '���� �Ű� �� �ǵ�� ����' , '���� �̿밣�� ���׳� ������ �߻��� �� ������ ������ �����ڿ��� �Ű����ֽñ⸦ ��Ź�帳�ϴ�.' || CHR(10) || '     ���� �ǵ���̳� ������ �����ø� �����ؽø� �����ϰڽ��ϴ�.' || CHR(10) || '  �����մϴ�.', '1');
INSERT INTO ANNOUNCEMENT_BOARD(NO, TITLE, CONTENT, WRITER_NO) VALUES(SEQ_ANNOUNCEMENT_BOARD_NO.NEXTVAL , '�̺�Ʈ �ȳ� 1' , '�̹� ��, ���� ���񽺸� �̿��ϴ� ȸ���Ե��� ���� �̺�Ʈ�� �غ�Ǿ����ϴ�.' || CHR(10) || '  �ڼ��� ������ ���� ������ Ȯ�����ֽñ� �ٶ��ϴ�.' || CHR(10) || '  �����մϴ�.', '1');
INSERT INTO ANNOUNCEMENT_BOARD(NO, TITLE, CONTENT, WRITER_NO) VALUES(SEQ_ANNOUNCEMENT_BOARD_NO.NEXTVAL , '�̺�Ʈ �ȳ� 2' , '���� ���� ���並 �����ֽ� �� �п��� ����� ���޵� �����Դϴ�.' || CHR(10) || '  ���� ���� ��Ź�帳�ϴ�.' || CHR(10) || '  �����մϴ�.', '1');


------------- �����Խ��� �߰� -------------

INSERT INTO BOARD (NO, TITLE, CONTENT, WRITER_NO, ENROLL_DATE, DEL_YN) 
VALUES (SEQ_BOARD_NO.NEXTVAL, '�ֱ� �� ��ȭ ��õ���ּ���!', '���� �� ��ȭ �߿� ��վ��� ��ȭ�� �ֳ���? ��õ���ּ���!', 2, SYSDATE, 'N');
INSERT INTO BOARD (NO, TITLE, CONTENT, WRITER_NO, ENROLL_DATE, DEL_YN) 
VALUES (SEQ_BOARD_NO.NEXTVAL, '������ �ϱ�: �ູ�� �Ϸ翴���', '������ ģ����� �Բ� ���� �ð��� ���¾��. �ູ�� �Ϸ翴���ϴ�.', 3, SYSDATE, 'N');
INSERT INTO BOARD (NO, TITLE, CONTENT, WRITER_NO, ENROLL_DATE, DEL_YN) 
VALUES (SEQ_BOARD_NO.NEXTVAL, '���� ���� ���� ���� ����ΰ���?', '������ ��ȹ ���ε�, �������� ������ ���� �������� ������� �ñ��մϴ�. ��õ ��Ź�����!', 4, SYSDATE, 'N');
INSERT INTO BOARD (NO, TITLE, CONTENT, WRITER_NO, ENROLL_DATE, DEL_YN) 
VALUES (SEQ_BOARD_NO.NEXTVAL, '�ֱ� ���� å �����ؿ�!', '���� ���� å �߿� �λ� ����� å�� �ֳ���? �Բ� �������ּ���!', 5, SYSDATE, 'N');
INSERT INTO BOARD(NO, TITLE, CONTENT, WRITER_NO) 
VALUES(SEQ_BOARD_NO.NEXTVAL, '���� ��ħ �������� ����', '���� ��ħ�� ��� 42�� �Ծ����ϴ�.. ���־��׿�. ���� ��θ��⵵ �ϳ׿�', 4);
INSERT INTO BOARD(NO, TITLE, CONTENT, WRITER_NO) 
VALUES(SEQ_BOARD_NO.NEXTVAL, '���� ������', '�������� ������,, ��õ����', 3);
INSERT INTO BOARD(NO, TITLE, CONTENT, WRITER_NO) 
VALUES(SEQ_BOARD_NO.NEXTVAL, '�Ϸ翡 Ŀ�� �� ���� �������?', 'Ŀ�� �Ϸ翡 89�� �Դµ� ����������?', 7);
INSERT INTO BOARD(NO, TITLE, CONTENT, WRITER_NO) 
VALUES(SEQ_BOARD_NO.NEXTVAL, '��ǻ�� ���� ��õ', '��ī��豸 ��õ', 5);
INSERT INTO BOARD(NO, TITLE, CONTENT, WRITER_NO) 
VALUES (SEQ_BOARD_NO.NEXTVAL, '���� ��ħ ������ ����', '���� ���� ��ħ�� ��� 10�� �Ծ����ϴ�. ���� ������׿�.', 5);
INSERT INTO BOARD(NO, TITLE, CONTENT, WRITER_NO) 
VALUES (SEQ_BOARD_NO.NEXTVAL, '������ �ϻ�', '���� �Ϸ縦 ��� ���´��� �����ϰ� �ͽ��ϴ�. �Բ� �������ּ���!', 3);
INSERT INTO BOARD(NO, TITLE, CONTENT, WRITER_NO) 
VALUES (SEQ_BOARD_NO.NEXTVAL, '�ǻ�', '�츮�� ������ �Ϳ���? (���� ������ ����)', 4);
INSERT INTO BOARD(NO, TITLE, CONTENT, WRITER_NO) 
VALUES (SEQ_BOARD_NO.NEXTVAL, '�ʳ״� �̷��� ��������...', '�ƴ� ������ ���� �� �ϰ� ���� ���ܼ� ���ο� ����� �־������ xx �Ϸ縸�� ���� ���ư���.', 6);
INSERT INTO BOARD(NO, TITLE, CONTENT, WRITER_NO) 
VALUES (SEQ_BOARD_NO.NEXTVAL, '3�� 500', '�̰� �� ���ϳ���? 1�⸸ ��ص� 500�� ������ �� �� ���� �� ���׿�', 7);
INSERT INTO BOARD(NO, TITLE, CONTENT, WRITER_NO) 
VALUES (SEQ_BOARD_NO.NEXTVAL, '��....�ϳ� ������ ���� ', '���ϴ� ��Ż�� ������....�ֵ� ���̵� ��͵� �ƴϰ� ���̸� ���ϳ�', 3);
INSERT INTO BOARD(NO, TITLE, CONTENT, WRITER_NO) 
VALUES (SEQ_BOARD_NO.NEXTVAL, '�ֽ� ��õ��....', '�ֽ� �����ߴµ� �������? �췮�ֻ�� ���� �ؾ��ϳ�....', 6);
INSERT INTO BOARD(NO, TITLE, CONTENT, WRITER_NO) 
VALUES (SEQ_BOARD_NO.NEXTVAL, '��ƾ �˷��ֽ� ��ַ� ã���ϴ�', '��ϴµ� ���� �߾ȵǴ°� ������ �˷��� ���� ���մϴ�', 5);
INSERT INTO BOARD(NO, TITLE, CONTENT, WRITER_NO) 
VALUES (SEQ_BOARD_NO.NEXTVAL, '��ģ����@@@@@@@@ ��ϴ� �����ϴ� ���� ����', '��ģ �ޱ� ���� �Ұ��� ���� ��� �ٸ� ���� �帲', 4);
INSERT INTO BOARD (NO, TITLE, CONTENT, WRITER_NO, ENROLL_DATE, DEL_YN)
VALUES (SEQ_BOARD_NO.NEXTVAL, '�ٵ� ���� �ν��� ����ó���', '� �� �ƾƸ� ���ôµ� �ν��͵� ���ð�;�� �ٵ� ����ô��� ��õ���ּ���', 7 , SYSDATE, 'N');
INSERT INTO BOARD (NO, TITLE, CONTENT, WRITER_NO, ENROLL_DATE, DEL_YN)
VALUES (SEQ_BOARD_NO.NEXTVAL, '�����?', '�ٵ� ����� �ϼ���? �� ��� ���� ��ü �� 4���� �մϴ�', 2 , SYSDATE, 'N');
INSERT INTO BOARD (NO, TITLE, CONTENT, WRITER_NO, ENROLL_DATE, DEL_YN)
VALUES (SEQ_BOARD_NO.NEXTVAL, '��ü� ��ƾ', '��ü� �� �� ���� �ܽ�Ʈ�� ��Ϸ��� ���� ��Ƽ�� ���帮��Ʈ �����ϰ��ִµ� ȿ�� ���� �� ���ƿ� 15-20���� 4��Ʈ �߰��غ�����~', 5 , SYSDATE, 'N');
INSERT INTO BOARD (NO, TITLE, CONTENT, WRITER_NO, ENROLL_DATE, DEL_YN)
VALUES (SEQ_BOARD_NO.NEXTVAL, '���̾�Ʈ ��ü��', '�Ѵ�° ��ü���Դϴ� �ʹ� ��Ʈ�����ް� �׳� �԰������ �����е��� �̷� �� ��� �ذ��ϼ��� �Ф�', 3 , SYSDATE, 'N');

------------- NORMAL ��ǰ �߰� -------------
INSERT INTO NORMAL_PRODUCT(NORMAL_PROD_NO, NAME ,PRICE,DESCRIPTION)
VALUES (SEQ_NORMAL_PROD_NO.NEXTVAL ,'����������� ī�� ������ȣ��',19000,'������ ���������� ����ִ� ������ȣ��');
INSERT INTO NORMAL_PRODUCT(NORMAL_PROD_NO, NAME ,PRICE,DESCRIPTION)
VALUES (SEQ_NORMAL_PROD_NO.NEXTVAL ,'����������� ���̺긮�� �׿����� �Ȳ�ġ ��ȣ��',31900,'���߷����� �δ� ���� �����°� �йڰ�');
INSERT INTO NORMAL_PRODUCT(NORMAL_PROD_NO, NAME ,PRICE,DESCRIPTION)
VALUES (SEQ_NORMAL_PROD_NO.NEXTVAL ,'����������� �� �ո� ��ȣ��',10900,'���� �������� ����,������ �����,�����ΰ� ��ɼ� ��� ���� �ո� ��ȣ��');
INSERT INTO NORMAL_PRODUCT(NORMAL_PROD_NO, NAME ,PRICE,DESCRIPTION)
VALUES (SEQ_NORMAL_PROD_NO.NEXTVAL ,'Harbinger �ｺ ��Ʈ ������ ����Ʈ ���� �㸮��ȣ��',62000,'����� ���밨 ��������Ʈ�� ����ȭ�� ��Ʈ');
INSERT INTO NORMAL_PRODUCT(NORMAL_PROD_NO, NAME ,PRICE,DESCRIPTION)
VALUES (SEQ_NORMAL_PROD_NO.NEXTVAL ,'Harbinger ������ �� �� ���',40700,'������ ���� ��е� EVA�� ����, ��� ����� ���� �뷱��');
INSERT INTO NORMAL_PRODUCT(NORMAL_PROD_NO, NAME ,PRICE,DESCRIPTION)
VALUES (SEQ_NORMAL_PROD_NO.NEXTVAL ,'Harbinger 3��ġ ��� ��Ƽ ��Ŭ Ŀ��',26400,'�� ���� ���̺� �ӽŰ� ȣȯ�Ǵ� ��� ��Ƽ ��ƿ D�� , �߸� ���� ��� ����');
INSERT INTO NORMAL_PRODUCT(NORMAL_PROD_NO, NAME ,PRICE,DESCRIPTION)
VALUES (SEQ_NORMAL_PROD_NO.NEXTVAL ,'SHIELD �Ŀ������� �ｺ ��Ʈ��',30000,'������ �׸���,��������ƼAND��Ÿ�ϸ����� ��Ʈ��,�����̾� ���� ������');
INSERT INTO NORMAL_PRODUCT(NORMAL_PROD_NO, NAME ,PRICE,DESCRIPTION)
VALUES (SEQ_NORMAL_PROD_NO.NEXTVAL ,'SHIELD �����̾� AB ��Ʈ�� ',42000,'�پ ź���°� ��ǰ �������� ���� �����̾� �׿����� ����');
INSERT INTO NORMAL_PRODUCT(NORMAL_PROD_NO, NAME ,PRICE,DESCRIPTION)
VALUES (SEQ_NORMAL_PROD_NO.NEXTVAL ,'SHIELD ���� �Ŀ����� ũ��',30000,'��� / �� �ֻ��� ����� ������ ���� , ��ģ���� �Ƿθ� ȿ�������� Ǯ����');
INSERT INTO NORMAL_PRODUCT(NORMAL_PROD_NO, NAME ,PRICE,DESCRIPTION)
VALUES (SEQ_NORMAL_PROD_NO.NEXTVAL ,'HDEX ��ս� ����� ��� ũ��ž',50000,'ĳ�־��� ����� ����Ǵ� ���� ������');
INSERT INTO NORMAL_PRODUCT(NORMAL_PROD_NO, NAME ,PRICE,DESCRIPTION)
VALUES (SEQ_NORMAL_PROD_NO.NEXTVAL ,'HDEX ��ս� ������ ���Ϸ� ����',84000,'��е� �������� ���۵� ��ս� ������ ������ ��Ƽ��Ƽ Ȱ��');
INSERT INTO NORMAL_PRODUCT(NORMAL_PROD_NO, NAME ,PRICE,DESCRIPTION)
VALUES (SEQ_NORMAL_PROD_NO.NEXTVAL ,'HDEX ��ս� �ɺ� �� ��ũ ũ�� �� ������',61200,'������ ������ ���༺���� ����� ���밨�� �Ǻο� �ڱ� ���� ����');
INSERT INTO NORMAL_PRODUCT(NORMAL_PROD_NO, NAME ,PRICE,DESCRIPTION)
VALUES (SEQ_NORMAL_PROD_NO.NEXTVAL ,'MUSCLE ARMED ���ù�',179000,'���� �������� �뵵���� ���� ����� ������ 7���� ����');
INSERT INTO NORMAL_PRODUCT(NORMAL_PROD_NO, NAME ,PRICE,DESCRIPTION)
VALUES (SEQ_NORMAL_PROD_NO.NEXTVAL ,'MUSCLE ARMED ������ T-SHIRT',64500,'���� ��� ���� ����Ʈ,������ ����ȭ,�������� �ٸ� �� ������ �޽� ���� ���');
INSERT INTO NORMAL_PRODUCT(NORMAL_PROD_NO, NAME ,PRICE,DESCRIPTION)
VALUES (SEQ_NORMAL_PROD_NO.NEXTVAL ,'MUSCLE ARMED ��Ƽ�� �ױ� ���̵� PANTS',133000,'���� ���븳 ���� ������ ���Ϸ� ������ ����Ͽ� �����ð� ��������� ª�� ������');

------------- NORMAL ���� �߰� -------------
INSERT INTO NORMAL_REVIEW( NORMAL_REVIEW_NO,REVIEW_TITLE ,REVIEW ,NORMAL_PROD_NO,WRITER_NO)
VALUES (SEQ_NORMAL_REVIEW_NO.NEXTVAL,'������ ����� ������ȣ��','������ �ʹ� ���ļ� �ϳ� �常�߾�� �����ε� �̻ڰ� ¯¯�ؼ� ���ƿ�',1,2);
INSERT INTO NORMAL_REVIEW( NORMAL_REVIEW_NO,REVIEW_TITLE ,REVIEW ,NORMAL_PROD_NO,WRITER_NO)
VALUES (SEQ_NORMAL_REVIEW_NO.NEXTVAL,'���� ��õ�ص帲~','������ �ʿ��ؼ� �ϳ� �常�ߴµ� ���ϸ��� �Ա� ���ƿ� :)',15,7);
INSERT INTO NORMAL_REVIEW( NORMAL_REVIEW_NO,REVIEW_TITLE ,REVIEW ,NORMAL_PROD_NO,WRITER_NO)
VALUES (SEQ_NORMAL_REVIEW_NO.NEXTVAL,'�߿�ũ�� �ı� ���ܵ����','���� ���� �ҽ��ؼ� �λ�������� �ϳ� �常�ߴµ� ���� ���� ������ �ö� ������� �� �� �־����ϴ�~',9,4);
INSERT INTO NORMAL_REVIEW( NORMAL_REVIEW_NO,REVIEW_TITLE ,REVIEW ,NORMAL_PROD_NO,WRITER_NO)
VALUES (SEQ_NORMAL_REVIEW_NO.NEXTVAL,'�ӽ��ϵ� Ƽ���� �ı�','���ݴ밡 �� ������ ���� ����ߴµ� ��ǳ�� �ߵǼ� ���ݰ� �ϴ±��� �ͽ��ϴ� �����ϴ�',14,3);
INSERT INTO NORMAL_REVIEW( NORMAL_REVIEW_NO,REVIEW_TITLE ,REVIEW ,NORMAL_PROD_NO,WRITER_NO)
VALUES (SEQ_NORMAL_REVIEW_NO.NEXTVAL,'�Ȳ�ġ��ȣ�� ����~~~','���� �����̳� ����� �� �� �Ȳ�ġ �Ҷ� �� �Ҹ��� �� ���� �ϳ� �常�ߴµ� ���� �����ϴ� ���߿� �� ��Կ�~',2,6);
INSERT INTO NORMAL_REVIEW( NORMAL_REVIEW_NO,REVIEW_TITLE ,REVIEW ,NORMAL_PROD_NO,WRITER_NO)
VALUES (SEQ_NORMAL_REVIEW_NO.NEXTVAL,'������ȣ��','�Ͻ�����δ� ���� ����غôµ� ���� ó���Դϴ� �ٵ� �� �����ϳ׿� �� �����갡 �� ���� �� ���ƿ� ��',1,4);
INSERT INTO NORMAL_REVIEW( NORMAL_REVIEW_NO,REVIEW_TITLE ,REVIEW ,NORMAL_PROD_NO,WRITER_NO)
VALUES (SEQ_NORMAL_REVIEW_NO.NEXTVAL,'�㸮��ȣ��','�ٵ� �㸮��ȣ�� �ϳ��� �常�ϰ� �����? �ٵ� ���� �Ϻ����� ���� ���� �� ���ƿ� �Ѽ�Ʈ �ϰ��� ������ ��� �谨 ���ƽ��ϴ�',4,3);
INSERT INTO NORMAL_REVIEW( NORMAL_REVIEW_NO,REVIEW_TITLE ,REVIEW ,NORMAL_PROD_NO,WRITER_NO)
VALUES (SEQ_NORMAL_REVIEW_NO.NEXTVAL,'�̰� ���󼳸��ؾ���','���̺� ��� �� �� ���� ����ϴµ� ���̺��̶� ���Ը� ���� �ø� �� �� �־ �ʹ� ���ƿ�',6,3);
INSERT INTO NORMAL_REVIEW( NORMAL_REVIEW_NO,REVIEW_TITLE ,REVIEW ,NORMAL_PROD_NO,WRITER_NO)
VALUES (SEQ_NORMAL_REVIEW_NO.NEXTVAL,'����ġ����','�� ������ �׻� ����ġ������ �ֿ��ϴ� �� ���ƿ� �����ε� �̻۵� �ǿ뼺�� �־ ���� ��ϴ� ',10,7);
INSERT INTO NORMAL_REVIEW( NORMAL_REVIEW_NO,REVIEW_TITLE ,REVIEW ,NORMAL_PROD_NO,WRITER_NO)
VALUES (SEQ_NORMAL_REVIEW_NO.NEXTVAL,'�ｺ ������ ��õ','����Ű�� �ʹ� ���ؼ� �ٸ����� ã�ٰ� �ӽ��ϵ岨 ���ԵǾ��µ� ���ݴ밡 �� ������ �ǿ뼺���� ������ ���Ƽ� ���� �� ���ƿ�',13,3);
INSERT INTO NORMAL_REVIEW( NORMAL_REVIEW_NO,REVIEW_TITLE ,REVIEW ,NORMAL_PROD_NO,WRITER_NO)
VALUES (SEQ_NORMAL_REVIEW_NO.NEXTVAL,'���� ������ ��õ','�׳� �̰����ͺ��ٰ� ���ùͽ����� �� �̻� �� ���� ã�ٰ� ����ġ������ ó������ �˰ԵǾ��µ� �̻ڰ� ���׿�',12,5);
INSERT INTO NORMAL_REVIEW( NORMAL_REVIEW_NO,REVIEW_TITLE ,REVIEW ,NORMAL_PROD_NO,WRITER_NO)
VALUES (SEQ_NORMAL_REVIEW_NO.NEXTVAL,'�ո�Ʈ�� ��õ','��Ǯ�ϴµ� �ٷ� �� ��Ʈ�� �ʿ��ߴµ� ¯¯�ϰ� �������� ���Ƽ� ���� 20Ű�γ� �� �÷Ƚ��ϴ�~',7,6);
INSERT INTO NORMAL_REVIEW( NORMAL_REVIEW_NO,REVIEW_TITLE ,REVIEW ,NORMAL_PROD_NO,WRITER_NO)
VALUES (SEQ_NORMAL_REVIEW_NO.NEXTVAL,'��ȣ�� ��õ�մϴ�','�ƹ��� ����������� ���� �����ϴ� ���μ������� ����ߴٰ� �ϱ淡 �ϰ� ��ôµ� ���� ������ �Դϴ�',3,7);
INSERT INTO NORMAL_REVIEW( NORMAL_REVIEW_NO,REVIEW_TITLE ,REVIEW ,NORMAL_PROD_NO,WRITER_NO)
VALUES (SEQ_NORMAL_REVIEW_NO.NEXTVAL,'������ �','���̺�� �ϴ� �����̿ Ŀ�� ã���־��µ� �ｺ�忡 ��� ���ο� �ϳ� �常�߾��~',6,2);
INSERT INTO NORMAL_REVIEW( NORMAL_REVIEW_NO,REVIEW_TITLE ,REVIEW ,NORMAL_PROD_NO,WRITER_NO)
VALUES (SEQ_NORMAL_REVIEW_NO.NEXTVAL,'���� �̻ڰ� �����ؿ�','���ϸ��� ������ �ϳ� �常�ߴµ� �������ʳ׿� ������ ���Ϸ� ����� ���� ������ ���ƿ�',15,6);

COMMIT;


