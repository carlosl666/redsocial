FROM postgres:15-alpine

COPY base-datos.sh /docker-entrypoint-initdb.d/

RUN chmod +x /docker-entrypoint-initdb.d/*.sh
RUN find . -name "*.sh" | xargs dos2unix