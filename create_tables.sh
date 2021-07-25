SHOW databases;
USE default;
CREATE TABLE IF NOT EXISTS default.operational_data 
(
    `name` String,
    `a1` Float64,
    `a2` Float64,
    `a3` Float64,
    `a4` Float64,
    `a5` Float64,
    `a6` Float64,
    `time` DateTime64(3)
 )
 ENGINE = MergeTree()
 PARTITION BY toYYYYMM(time)
 ORDER BY (time, name)
 SAMPLE BY name;