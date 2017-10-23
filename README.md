# PokemonShop

## SQL QUERIES

INSERT INTO ITEM (ITEM_NUMBER, NAME, AVAILABLE_QUANTITY, DESCRIPTION, UNIT_PRICE) VALUES ( 11, 'Bulbasaur', 15, 'Bulbasaur Pokemon card', 4.0)

CREATE TABLE ITEM (ID int auto_increment primary key, ITEM_NUMBER int, NAME varchar(255), DESCRIPTION varchar(255), AVAILABLE_QUANTITY int, UNIT_PRICE double);

CREATE TABLE CUSTOMER_ORDER_LINE_ITEM (ID int auto_increment primary key, ITEM_ID int, ITEM_NAME varchar(255), ITEM_QUANTITY int, CUSTOMER_ORDER_ID_FK int)

CREATE TABLE PAYMENT_INFO (ID int auto_increment primary key,  CREDIT_CARD_NUMBER varchar(255), CARD_HOLDER_NAME varchar(255), EXPIRATION_DATE varchar(255), CVV int)

CREATE TABLE SHIPPING_INFO (ID int auto_increment primary key, ADDRESS_LINE_1 varchar(255), ADDRESS_LINE_2 varchar(255), CITY varchar(255), STATE varchar(255), ZIPCODE varchar(255))

CREATE TABLE CUSTOMER_ORDER (ID int auto_increment primary key, CUSTOMER_NAME varchar(255), CUSTOMER_EMAIL varchar(255), SHIPPING_INFO_ID_FK int, PAYMENT_INFO_ID_FK int, STATUS varchar(255) default 'New')
