#!/bin/sh
ls
docker kill $(docker ps -q)
git fetch && git rebase
docker build -t java-8 .
docker run java-8