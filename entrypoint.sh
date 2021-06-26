#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar nistagram-media.jar