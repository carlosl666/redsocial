#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL

    CREATE USER admin PASSWORD 'admin123456';

    CREATE DATABASE dbms_proyecto OWNER admin;
    GRANT ALL PRIVILEGES ON DATABASE dbms_proyecto TO admin;


    CREATE DATABASE dbms_publicaciones OWNER admin;
    GRANT ALL PRIVILEGES ON DATABASE dbms_publicaciones TO admin;

EOSQL
