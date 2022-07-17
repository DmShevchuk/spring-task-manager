#!/bin/bash

./gradlew build -x test
cd docker
docker-compose down -v
docker-compose up --build