CREATE TABLE currency_tbl
(
    id     INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name   VARCHAR(50)                    NOT NULL,
    code   VARCHAR(50)                    NOT NULL,
    symbol VARCHAR(50)                    NOT NULL
);

CREATE TABLE user_tbl
(
    id        INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    user      VARCHAR(15)                    NOT NULL,
    password  VARCHAR(15)                    NOT NULL,
    user_role VARCHAR(10)                    NOT NULL
);

CREATE TABLE bank_tbl
(
    id   INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(50)                    NOT NULL
);

CREATE TABLE account_tbl
(
    id           INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name         VARCHAR(50)                    NOT NULL,
    amount       INT                            NOT NULL,
    currency_id  INT                            NOT NULL,
    account_type VARCHAR(50)                    NOT NULL,
    user_id      INT                            NOT NULL,

    CONSTRAINT currency_fk FOREIGN KEY (currency_id) REFERENCES currency_tbl (id),
    CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES user_tbl (id)
);

CREATE TABLE category_tbl
(
    id                 INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name               VARCHAR(50)                    NOT NULL,
    parent_category_id INT
);

CREATE TABLE transaction_tbl
(
    id          INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    amount      DECIMAL(15, 2)                 NOT NULL,
    date        DATE                           NOT NULL,
    source      VARCHAR(50),
    bank_id     INT,
    account_id  INT                            NOT NULL,
    currency_id INT                            NOT NULL,

    CONSTRAINT bank_fk FOREIGN KEY (bank_id) REFERENCES bank_tbl (id),
    CONSTRAINT account_fk FOREIGN KEY (account_id) REFERENCES account_tbl (id),
    CONSTRAINT currency_transaction_fk FOREIGN KEY (currency_id) REFERENCES currency_tbl (id)
);

CREATE TABLE transaction_category_tbl
(
    transaction_id INT NOT NULL,
    category_id    INT NOT NULL,

    CONSTRAINT transaction_category_category_fk FOREIGN KEY (category_id) REFERENCES category_tbl (id),
    CONSTRAINT transaction_category_transaction_fk FOREIGN KEY (transaction_id) REFERENCES transaction_tbl (id)
);

