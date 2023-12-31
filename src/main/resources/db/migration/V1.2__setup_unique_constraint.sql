ALTER TABLE COACH
ADD CONSTRAINT UQ_coach_name UNIQUE(name);

ALTER TABLE MEMBER
ADD CONSTRAINT UQ_login_name UNIQUE(login_name);

ALTER TABLE MEMBER
ADD CONSTRAINT UQ_email UNIQUE(email);

ALTER TABLE COURSE
ADD CONSTRAINT UQ_course_name UNIQUE(name);

ALTER TABLE MEMBER_COURSE
ADD CONSTRAINT UQ_member_id_course_id UNIQUE(member_id, course_id);
