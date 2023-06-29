DROP TABLE IF EXISTS coach CASCADE;
DROP TABLE IF EXISTS member CASCADE;
DROP TABLE IF EXISTS course CASCADE;
--DROP SEQUENCE IF EXISTS coach_id_seq;
--DROP SEQUENCE IF EXISTS member_id_seq;
--DROP SEQUENCE IF EXISTS course_id_seq;

-- CREATE SEQUENCE coach_id_seq START WITH 1;
-- CREATE SEQUENCE member_id_seq START WITH 1;
-- CREATE SEQUENCE course_id_seq START WITH 1;


CREATE TABLE coach (
    /*id                INTEGER NOT NULL default nextval('coach_id_seq'), */
    id                BIGSERIAL NOT NULL,
    name              VARCHAR(30) not null unique,
    description       VARCHAR(150)
);

ALTER TABLE coach ADD CONSTRAINT coach_pk PRIMARY KEY ( id );

CREATE TABLE member (
    /*id              INTEGER NOT NULL default nextval('member_id_seq'),*/
    id              BIGSERIAL NOT NULL,
    login_name            VARCHAR(30) not null unique,
    password        VARCHAR(64),
    first_name      VARCHAR(30),
    last_name       VARCHAR(30),
    email           VARCHAR(50),
    address         VARCHAR(150),
    enrolled_date      date default CURRENT_DATE,
    coach_id   bigint NOT NULL
);

ALTER TABLE member ADD CONSTRAINT member_pk PRIMARY KEY ( id );

CREATE TABLE course (
    /*id             INTEGER NOT NULL default nextval('course_id_seq'),*/
    id             BIGSERIAL NOT NULL,
    name   VARCHAR(30),
    description       VARCHAR(150),
    create_date    date default CURRENT_DATE
);

ALTER TABLE course ADD CONSTRAINT course_pk PRIMARY KEY ( id );

CREATE TABLE member_course (
    member_id    BIGINT NOT NULL,
    course_id    BIGINT NOT NULL
);

ALTER TABLE member
    ADD CONSTRAINT member_coach_fk FOREIGN KEY ( coach_id )
        REFERENCES coach ( id );

ALTER TABLE member_course
    ADD CONSTRAINT member_fk FOREIGN KEY ( member_id )
        REFERENCES member ( id );

ALTER TABLE member_course
    ADD CONSTRAINT course_fk FOREIGN KEY ( course_id )
        REFERENCES course ( id );
