CREATE SCHEMA IF NOT EXISTS MEMO;
SET SCHEMA MEMO;

CREATE TABLE IF NOT EXISTS CARD
(
  CARD_ID serial primary key,
  CARD_NAME varchar(255) unique not null,
  OVERVIEW varchar(255) not null,
  UPDATED_AT timestamp(3) default current_timestamp(3),
  DELETE_FLAG boolean default 0 not null
);
