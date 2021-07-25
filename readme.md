# Overview
Clickhouse demo
# Usage
1. open clickhouse server
    ```
    ./run.sh     // start clickhouse sever
    ```
2. enter clickhouse server and create `operational_data` table
    ```
    docker exec -it clickhouse-server clickhouse-client 
    ```
3. paste all `create_tables.sh` content in it and enter to create table;
# Arch
1. http-server: controller -> clickhouse
2. module: sink -> clickhouse