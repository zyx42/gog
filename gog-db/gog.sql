DROP SCHEMA IF EXISTS gog;
CREATE SCHEMA gog;

DROP TABLE IF EXISTS gog.accounts;

CREATE TABLE gog.accounts (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    last_seen TIMESTAMP
);

CREATE TYPE time_period AS ENUM('YEAR', 'QUARTER', 'MONTH', 'DAY');

DROP TABLE IF EXISTS gog.exercises;

CREATE TABLE gog.exercises (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    overall_reps DOUBLE PRECISION NOT NULL,
    period time_period
);

DROP TABLE IF EXISTS gog.measurements;

CREATE TABLE gog.measurements (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    girth DOUBLE PRECISION NOT NULL,
    point_in_time TIMESTAMP NOT NULL
);

DROP TABLE IF EXISTS gog.users;

CREATE TABLE gog.users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS gog.recipients;

CREATE TABLE gog.recipients (
    id SERIAL PRIMARY KEY,
    accountName VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TYPE frequency AS ENUM('WEEKLY', 'MONTHLY', 'QUARTERLY');

CREATE TYPE notification_type AS ENUM('BACKUP', 'REMIND');

DROP TABLE IF EXISTS gog.notification_settings;

CREATE TABLE gog.notification_settings (
    id SERIAL PRIMARY KEY,
    active BOOLEAN NOT NULL,
    frequency frequency NOT NULL,
    last_notified DATE,
    notification_type notification_type,
    recipient_id BIGINT NOT NULL REFERENCES "gog.recipients" (id)
);

CREATE TABLE gog.accounts_statistics (
    id SERIAL PRIMARY KEY
);

CREATE TYPE time_period_stats AS ENUM('YEAR', 'QUARTER', 'MONTH', 'DAY', 'HOUR');

CREATE TABLE gog.exercises (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    overall_reps DOUBLE PRECISION NOT NULL,
    time_period time_period_stats NOT NULL,
    account_id BIGINT NOT NULL REFERENCES "gog.accounts_statistics"(id)
);

CREATE TABLE gog.measurements (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    girth DOUBLE PRECISION NOT NULL,
    point_in_time TIMESTAMP,
    account_id BIGINT NOT NULL REFERENCES "gog.accounts_statistics" (id)
);

CREATE TABLE gog.datapoints (
    id SERIAL PRIMARY KEY,
    account VARCHAR(255),
    date DATE
);

CREATE TABLE gog.exercise_metrics (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    overall_reps DOUBLE PRECISION NOT NULL,
    datapoint_id BIGINT NOT NULL REFERENCES "gog.datapoints" (id)
);

CREATE TABLE gog.measurement_metrics (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    girth DOUBLE PRECISION NOT NULL,
    datapoint_id BIGINT NOT NULL REFERENCES "gog.datapoints" (id)
);

CREATE TYPE statistic_metric AS ENUM('EXERCISES_REPS', 'MEASUREMENTS_SUM');

CREATE TABLE gog.statistics (
    statistic_metric statistic_metric,
    amount DOUBLE PRECISION,
    datapoint_id BIGINT NOT NULL REFERENCES "gog.datapoints" (id)
);