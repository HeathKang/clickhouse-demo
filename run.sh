
docker run -d --name clickhouse-server \
 -p 18123:8123  -p 9000:9000 -p 9009:9009 \
 --ulimit nofile=262144:262144 \
 --volume=$(pwd)/clickhouse_database:/var/lib/clickhouse \
 yandex/clickhouse-server