#!/bin/sh
echo "开始"
# shellcheck disable=SC2162
while read LINE; do
   echo ">>>""${LINE}"
done