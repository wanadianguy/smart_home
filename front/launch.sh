#!/bin/bash

git pull

docker build -t frontend -f frontend.Dockerfile .
docker run -it frontend
