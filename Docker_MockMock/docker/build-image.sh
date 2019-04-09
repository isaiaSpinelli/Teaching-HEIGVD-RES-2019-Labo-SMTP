#!/bin/bash

mvn clean install --file ../src/prankSMTP/pom.xml

cp ../src/prankSMTP/target/NomProjet-1.0-SNAPSHOT.jar .

# Build the Docker image locally
docker build --tag server_mockmock .