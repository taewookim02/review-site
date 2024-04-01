DROP TABLE "member" cascade constraints;

CREATE TABLE "member" (
	"no"	NUMBER		NOT NULL,
	"id"	VARCHAR2(20)		NULL,
	"pwd"	VARCHAR2(20)		NULL,
	"nick"	VARCHAR(20)		NULL,
	"is_admin"	CHAR(1)		NULL,
	"join_date"	timestamp		NULL,
	"quit_yn"	char(1)		NULL
);

DROP TABLE "normal_product" cascade constraints;

CREATE TABLE "normal_product" (
	"no"	number		NOT NULL,
	"name"	VARCHAR2(100)		NULL,
	"price"	VARCHAR2(50)		NULL,
	"is_Discount"	char(1)		NULL,
	"description"	VARCHAR(1000)		NULL
);

DROP TABLE "booster_product" cascade constraints;

CREATE TABLE "booster_product" (
	"no"	number		NOT NULL,
	"name"	VARCHAR2(100)		NULL,
	"price"	VARCHAR2(50)		NULL,
	"is_Discount"	char(1)		NULL,
	"description"	VARCHAR(1000)		NULL
);

DROP TABLE "food_product" cascade constraints;

CREATE TABLE "food_product" (
	"no"	number		NOT NULL,
	"name"	VARCHAR2(100)		NULL,
	"price"	VARCHAR2(50)		NULL,
	"is_Discount"	char(1)		NOT NULL,
	"description"	VARCHAR(1000)		NULL
);

DROP TABLE "normal_review" cascade constraints;

CREATE TABLE "normal_review" (
	"normal_review_no"	NUMBER		NOT NULL,
	"review"	VARCHAR2(1000)		NULL,
	"member_no"	NUMBER		NOT NULL,
	"normal_prod_no"	number		NOT NULL,
	"enroll_date"	timestamp		NULL,
	"quit_yn"	char(1)		NULL
);

DROP TABLE "booster_review" cascade constraints;

CREATE TABLE "booster_review" (
	"booster_review_no"	number		NOT NULL,
	"review"	VARCHAR2(1000)		NULL,
	"booster_prod_no"	number		NOT NULL,
	"member_no"	NUMBER		NOT NULL,
	"enroll_date"	timestamp		NULL,
	"quit_yn"	char(1)		NULL
);

DROP TABLE "announcement_board" cascade constraints;

CREATE TABLE "announcement_board" (
	"no"	number		NOT NULL,
	"title"	varchar2(1000)		NULL,
	"content"	varchar2(2000)		NULL,
	"writer_no"	NUMBER		NOT NULL,
	"enroll_date"	timestamp		NULL,
	"quit_yn"	char(1)		NULL
);

DROP TABLE "board" cascade constraints;

CREATE TABLE "board" (
	"no"	NUMBER		NOT NULL,
	"title"	varchar2(1000)		NULL,
	"content"	varchar2(2000)		NULL,
	"writer_no"	NUMBER		NOT NULL,
	"enroll_date"	timestamp		NULL,
	"quit_yn"	char(1)		NULL
);

DROP TABLE "food_review" cascade constraints;

CREATE TABLE "food_review" (
	"food_review_no"	number		NOT NULL,
	"review"	VARCHAR2(1000)		NULL,
	"food_prod_no"	number		NOT NULL,
	"member_no"	NUMBER		NOT NULL,
	"enroll_date"	timestamp		NULL,
	"quit_yn"	char(1)		NULL
);

ALTER TABLE "member" ADD CONSTRAINT "PK_MEMBER_NO" PRIMARY KEY (
	"no"
);

ALTER TABLE "normal_product" ADD CONSTRAINT "PK_NORMAL_PRODUCT" PRIMARY KEY (
	"no"
);

ALTER TABLE "booster_product" ADD CONSTRAINT "PK_BOOSTER_PRODUCT" PRIMARY KEY (
	"no"
);

ALTER TABLE "food_product" ADD CONSTRAINT "PK_FOOD_PRODUCT" PRIMARY KEY (
	"no"
);

ALTER TABLE "normal_review" ADD CONSTRAINT "PK_NORMAL_REVIEW" PRIMARY KEY (
	"normal_review_no"
);

ALTER TABLE "booster_review" ADD CONSTRAINT "PK_BOOSTER_REVIEW" PRIMARY KEY (
	"booster_review_no"
);

ALTER TABLE "announcement_board" ADD CONSTRAINT "PK_ANNOUNCEMENT_BOARD" PRIMARY KEY (
	"no"
);

ALTER TABLE "board" ADD CONSTRAINT "PK_BOARD" PRIMARY KEY (
	"no"
);

ALTER TABLE "food_review" ADD CONSTRAINT "PK_FOOD_REVIEW" PRIMARY KEY (
	"food_review_no"
);

ALTER TABLE "normal_review" ADD CONSTRAINT "FK_member_TO_normal_review_NO" FOREIGN KEY (
	"member_no"
)
REFERENCES "member" (
	"no"
);

ALTER TABLE "normal_review" ADD CONSTRAINT "FK_normal_product_TO_normal_review_1" FOREIGN KEY (
	"normal_prod_no"
)
REFERENCES "normal_product" (
	"no"
);

ALTER TABLE "booster_review" ADD CONSTRAINT "FK_booster_product_TO_booster_review_1" FOREIGN KEY (
	"booster_prod_no"
)
REFERENCES "booster_product" (
	"no"
);

ALTER TABLE "booster_review" ADD CONSTRAINT "FK_member_TO_booster_review_NO" FOREIGN KEY (
	"member_no"
)
REFERENCES "member" (
	"no"
);

ALTER TABLE "announcement_board" ADD CONSTRAINT "FK_member_TO_announcement_board_NO" FOREIGN KEY (
	"writer_no"
)
REFERENCES "member" (
	"no"
);

ALTER TABLE "board" ADD CONSTRAINT "FK_member_TO_board_NO" FOREIGN KEY (
	"writer_no"
)
REFERENCES "member" (
	"no"
);

ALTER TABLE "food_review" ADD CONSTRAINT "FK_food_product_TO_food_review_1" FOREIGN KEY (
	"food_prod_no"
)
REFERENCES "food_product" (
	"no"
);

ALTER TABLE "food_review" ADD CONSTRAINT "FK_member_TO_food_review_NO" FOREIGN KEY (
	"member_no"
)
REFERENCES "member" (
	"no"
);

drop table BOARD;
drop table MEMBER;