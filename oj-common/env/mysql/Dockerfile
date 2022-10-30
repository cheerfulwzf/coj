FROM mysql:5.7
MAINTAINER wangzhifu
COPY init.sql /init.sql
COPY my.cnf /etc/mysql/my.cnf
RUN mv init.sql /docker-entrypoint-initdb.d