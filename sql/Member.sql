USE jsp2;


CREATE TABLE member
(
    id          VARCHAR(50) PRIMARY KEY,
    password    VARCHAR(100) NOT NULL,
    nick_name   VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(2000),
    inserted    DATETIME     NOT NULL DEFAULT NOW()
);

SELECT *
FROM member;

# DROP TABLE member;

CREATE TABLE auth
(
    id   VARCHAR(50) REFERENCES member (id),
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (id, name)
);
DROP TABLE auth;

SELECT *
FROM auth;

# admin, bdmin
INSERT INTO auth
    (id, name)
VALUES ('admin', 'admin'),
       ('bdmin', 'admin');