#!/bin/bash

# Stop execution on any error
set -e
set -o pipefail

echo "Building services..."
bash build-services.sh

echo "Stopping all running containers..."
bash stop-containers.sh

echo "Deleting Docker images..."
bash delete-images.sh

echo "Starting all containers..."
docker compose -f docker-compose.yml up -d

echo "✅ All steps completed successfully."
