#!/bin/bash

mvn clean install --file ../../Labo4_SMTP/pom.xml

cp ../../Labo4_SMTP/target/prankSMTP-1.0-SNAPSHOT.jar ./DockerfileClient

# Build the Docker image locally
docker build --tag client_mockmock ./DockerfileClient