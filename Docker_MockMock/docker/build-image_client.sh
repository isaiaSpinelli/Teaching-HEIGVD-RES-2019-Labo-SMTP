#!/bin/bash

mvn clean install --file ../src/prankSMTP/pom.xml

cp ../src/prankSMTP/target/NomProjet-1.0-SNAPSHOT.jar ./DockerfileClient

# Build the Docker image locally
docker build --tag client_mockmock ./DockerfileClient