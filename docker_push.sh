#!/bin/sh
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
docker push 95kevid/battleshipsboot
