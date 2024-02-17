CREATE TABLE IF NOT EXISTS resident
(
    resident_serial_number       INT          NOT NULL,
    name                         VARCHAR(100) NOT NULL,
    resident_registration_number VARCHAR(300) NOT NULL,
    gender_code                  VARCHAR(20)  NOT NULL,
    birth_date                   TIMESTAMP    NOT NULL,
    birth_place_code             VARCHAR(20)  NOT NULL,
    registration_base_address    VARCHAR(500) NOT NULL,
    death_date                   TIMESTAMP,
    death_place_code             VARCHAR(20),
    death_place_address          VARCHAR(500),
    PRIMARY KEY (resident_serial_number)
);

CREATE TABLE IF NOT EXISTS birth_death_report_resident
(
    resident_serial_number           INT         NOT NULL,
    birth_death_type_code            VARCHAR(20) NOT NULL,
    report_resident_serial_number    INT         NOT NULL,
    birth_death_report_date          DATE        NOT NULL,
    birth_report_qualifications_code VARCHAR(20),
    death_report_qualifications_code VARCHAR(20),
    email_address                    VARCHAR(50),
    phone_number                     VARCHAR(20) NOT NULL,
    PRIMARY KEY (resident_serial_number, birth_death_type_code, report_resident_serial_number),
    FOREIGN KEY (resident_serial_number) REFERENCES resident(resident_serial_number),
    FOREIGN KEY (report_resident_serial_number) REFERENCES resident(resident_serial_number)
);

CREATE TABLE IF NOT EXISTS family_relationship
(
    base_resident_serial_number   INT         NOT NULL,
    family_resident_serial_number INT         NOT NULL,
    family_relationship_code      VARCHAR(20) NOT NULL,
    PRIMARY KEY (base_resident_serial_number, family_resident_serial_number),
    FOREIGN KEY (base_resident_serial_number) REFERENCES resident(resident_serial_number)
);

CREATE TABLE IF NOT EXISTS household
(
    household_serial_number           INT          NOT NULL,
    household_resident_serial_number  INT          NOT NULL,
    household_composition_date        DATE         NOT NULL,
    household_composition_reason_code VARCHAR(20)  NOT NULL,
    current_house_movement_address    VARCHAR(500) NOT NULL,
    PRIMARY KEY (household_serial_number),
    FOREIGN KEY (household_resident_serial_number) REFERENCES resident(resident_serial_number)
);

CREATE TABLE IF NOT EXISTS household_movement_address
(
    household_serial_number    INT                 NOT NULL,
    house_movement_report_date DATE                NOT NULL,
    house_movement_address     VARCHAR(500)        NOT NULL,
    last_address_yn            CHAR(1) DEFAULT 'Y' NOT NULL,
    PRIMARY KEY (household_serial_number, house_movement_report_date),
    FOREIGN KEY (household_serial_number) REFERENCES household(household_serial_number)
);

CREATE TABLE IF NOT EXISTS household_composition_resident
(
    household_serial_number                  INT         NOT NULL,
    resident_serial_number                   INT         NOT NULL,
    report_date                              DATE        NOT NULL,
    household_relationship_code              VARCHAR(20) NOT NULL,
    household_composition_change_reason_code VARCHAR(20) NOT NULL,
    PRIMARY KEY (household_serial_number, resident_serial_number),
    FOREIGN KEY (household_serial_number) REFERENCES household(household_serial_number),
    FOREIGN KEY (resident_serial_number) REFERENCES resident(resident_serial_number)
);

CREATE TABLE IF NOT EXISTS certificate_issue
(
    certificate_confirmation_number BIGINT      NOT NULL,
    resident_serial_number          INT         NOT NULL,
    certificate_type_code           VARCHAR(20) NOT NULL,
    certificate_issue_date          DATE        NOT NULL,
    PRIMARY KEY (certificate_confirmation_number),
    FOREIGN KEY (resident_serial_number) REFERENCES resident(resident_serial_number)
);

-- 3. resident 테이블 데이터 추가
MERGE INTO resident VALUES (1, '남길동', '130914-*******', '남', '1913-09-14 07:22:00', '자택', '경기도 성남시 분당구 대왕판교로645번길', '2021-04-29 09:03:00','주택', '강원도 고성군 금강산로 290번길');
MERGE INTO resident VALUES (2, '남석환', '540514-*******', '남', '1954-05-14 17:30:00', '병원', '경기도 성남시 분당구 대왕판교로645번길', NULL, NULL, NULL);
MERGE INTO resident VALUES (3, '박한나', '551022-*******', '여', '1955-10-22 11:15:00', '병원', '서울특별시 중구 세종대로 110번길', NULL, NULL, NULL);
MERGE INTO resident VALUES (4, '남기준', '790510-*******', '남', '1979-05-10 20:45:00', '병원', '경기도 성남시 분당구 대왕판교로645번길', NULL, NULL, NULL);
MERGE INTO resident VALUES (5, '이주은', '820821-*******', '여', '1982-08-21 01:28:00', '병원', '경기도 수원시 팔달구 효원로 1번길', NULL, NULL, NULL);
MERGE INTO resident VALUES (6, '이선미', '851205-*******', '여', '1985-12-05 22:01:00', '병원', '경기도 수원시 팔달구 효원로 1번길', NULL, NULL, NULL);
MERGE INTO resident VALUES (7, '남기석', '120315-*******', '남', '2012-03-15 14:59:00', '병원', '경기도 성남시 분당구 대왕판교로645번길', NULL, NULL, NULL);

-- 4. birth_death_report_resident 테이블 데이터 추가
MERGE INTO birth_death_report_resident VALUES (7, '출생', 4, '2012-03-17', '부', NULL, 'nam@nhnad.co.kr', '010-1234-5678');
MERGE INTO birth_death_report_resident VALUES (1, '사망', 2, '2020-05-02', '비동거친족', NULL, NULL, '010-2345-6789');

-- 5. family_relationship 테이블 데이터 추가
MERGE INTO family_relationship VALUES (1, 2, '자녀');
MERGE INTO family_relationship VALUES (2, 1, '부');
MERGE INTO family_relationship VALUES (2, 3, '배우자');
MERGE INTO family_relationship VALUES (2, 4, '자녀');
MERGE INTO family_relationship VALUES (3, 2, '배우자');
MERGE INTO family_relationship VALUES (3, 4, '자녀');
MERGE INTO family_relationship VALUES (4, 2, '부');
MERGE INTO family_relationship VALUES (4, 3, '모');
MERGE INTO family_relationship VALUES (4, 5, '배우자');
MERGE INTO family_relationship VALUES (4, 7, '자녀');
MERGE INTO family_relationship VALUES (5, 4, '배우자');
MERGE INTO family_relationship VALUES (5, 7, '자녀');
MERGE INTO family_relationship VALUES (7, 4, '부');
MERGE INTO family_relationship VALUES (7, 5, '모');

-- 6. household 테이블 데이터 추가
MERGE INTO household VALUES (1, 4, '2009-10-02', '세대분리', '경기도 성남시 분당구 대왕판교로 645번길');

-- 7. household_movement_address 테이블 데이터 추가
MERGE INTO household_movement_address VALUES (1, '2007-10-31', '서울시 동작구 상도로 940번길', 'N');
MERGE INTO household_movement_address VALUES (1, '2009-10-31', '경기도 성남시 분당구 불정로 90번길', 'N');
MERGE INTO household_movement_address VALUES (1, '2013-03-05', '경기도 성남시 분당구 대왕판교로 645번길', 'Y');

-- 8. household_composition_resident 테이블 데이터 추가
MERGE INTO household_composition_resident VALUES (1, 4, '2009-10-02', '본인', '세대분리');
MERGE INTO household_composition_resident VALUES (1, 5, '2010-02-15', '배우자', '전입');
MERGE INTO household_composition_resident VALUES (1, 7, '2012-03-17', '자녀', '출생등록');
MERGE INTO household_composition_resident VALUES (1, 6, '2015-11-29', '동거인', '전입');

-- 9. certificate_issue 테이블 데이터 추가
MERGE INTO certificate_issue VALUES (1234567891011121, 4, '가족관계증명서', '2021-10-25');
MERGE INTO certificate_issue VALUES (9876543210987654, 4, '주민등록등본', '2021-10-25');