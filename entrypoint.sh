#!/bin/sh


mkdir mediaFolder
chmod 777 mediaFolder

./consul agent -config-dir=/consul-config &

java -jar nistagram-media.jar