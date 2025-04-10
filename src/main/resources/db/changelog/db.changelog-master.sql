
CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(100) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       full_name VARCHAR(100)
);

CREATE TABLE user_roles (
                            user_id INT NOT NULL,
                            role_id INT NOT NULL,
                            PRIMARY KEY (user_id, role_id),
                            FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                            FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

CREATE TABLE books (
                       id SERIAL PRIMARY KEY,
                       title VARCHAR(200) NOT NULL,
                       author VARCHAR(100),
                       available BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE issued_books (
                              id SERIAL PRIMARY KEY,
                              user_id INT NOT NULL,
                              book_id INT NOT NULL,
                              issue_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              return_date TIMESTAMP,
                              FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                              FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);

INSERT INTO roles (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO users (username, password, full_name)
VALUES
    ('admin', 'admin123', 'Admin User'),
    ('user1', 'user123', 'Regular User');

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u, roles r
WHERE (u.username = 'admin' AND r.name = 'ROLE_ADMIN')
   OR (u.username = 'user1' AND r.name = 'ROLE_USER');

INSERT INTO books (title, author)
VALUES
    ('Put k uspehu', 'Nikolay Sobolev'),
    ('Dejavu', 'Oleg Kizaru'),
    ('1922', 'John');
