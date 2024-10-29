USE jsp2;

CREATE TABLE board
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    title    VARCHAR(200)  NOT NULL,
    content  VARCHAR(5000) NOT NULL,
    writer   VARCHAR(200)  NOT NULL,
    inserted DATETIME      NOT NULL DEFAULT NOW()
);

SELECT *
FROM board
ORDER BY id;

SELECT *
FROM jsp2.board
ORDER BY id
LIMIT 0,10;

SELECT COUNT(*)
FROM board;
# 페이징 연습용 게시물 복붙
INSERT INTO board
    (title, content, writer)
SELECT title, content, writer
FROM board;

DROP TABLE board;