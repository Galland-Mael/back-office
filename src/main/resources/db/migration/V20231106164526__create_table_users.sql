CREATE TABLE users
(
    id                SERIAL PRIMARY KEY,
    first_name        VARCHAR(100) NOT NULL,
    last_name         VARCHAR(100) NOT NULL,
    email             VARCHAR(100) NOT NULL UNIQUE,
    facebook_id       VARCHAR(100) ,
    password          VARCHAR(100) NOT NULL,
    phone             VARCHAR(20)  NOT NULL,
    quality_id           INT          NOT NULL,
    token             VARCHAR(100) NOT NULL UNIQUE,
    admin             SMALLINT     NOT NULL DEFAULT 0,
    library_timestamp DATE             DEFAULT CURRENT_TIMESTAMP,
    timestamp         DATE,
    CONSTRAINT email_unique UNIQUE (email),
    CONSTRAINT token_unique UNIQUE (token),
    CONSTRAINT fk_quality FOREIGN KEY (quality_id) REFERENCES quality (id)
);