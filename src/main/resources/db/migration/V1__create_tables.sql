CREATE TABLE performance
(
    id   SERIAL PRIMARY KEY,
    text VARCHAR(255) NOT NULL
);

CREATE TABLE student
(
    id             SERIAL PRIMARY KEY,
    full_name      VARCHAR(255) NOT NULL,
    birth_date     DATE,
    performance_id INT REFERENCES performance (id)
);

CREATE INDEX idx_student_full_name ON student (full_name);
CREATE INDEX idx_student_birth_date ON student (birth_date);
