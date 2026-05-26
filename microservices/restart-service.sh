#!/bin/bash

# Color codes for output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# Check if service name is provided
if [ -z "$1" ]; then
    echo -e "${RED}❌ Error: Service name not provided!${NC}"
    echo -e "${BLUE}Usage: ./restart-service.sh <service-name>${NC}"
    echo -e "${YELLOW}Available services:${NC}"
    echo "  - user-mgmt"
    echo "  - product-mgmt"
    echo "  - security"
    echo "  - email"
    echo "  - api-gateway"
    echo "  - service-registry"
    exit 1
fi

SERVICE_NAME=$1

# Validate service name
VALID_SERVICES=("user-mgmt" "product-mgmt" "security" "email" "api-gateway" "service-registry")
if [[ ! " ${VALID_SERVICES[@]} " =~ " ${SERVICE_NAME} " ]]; then
    echo -e "${RED}❌ Invalid service name: ${SERVICE_NAME}${NC}"
    echo -e "${YELLOW}Valid services are:${NC}"
    for service in "${VALID_SERVICES[@]}"; do
        echo "  - $service"
    done
    exit 1
fi

echo -e "${BLUE}========================================${NC}"
echo -e "${BLUE}🔄 Restarting Service: ${SERVICE_NAME}${NC}"
echo -e "${BLUE}========================================${NC}"

# Step 1: Build the service
echo -e "${BLUE}[1/4] 🔨 Building ${SERVICE_NAME} with Maven...${NC}"
cd "${SERVICE_NAME}" || exit 1
mvn clean install -DskipTests
if [ $? -eq 0 ]; then
    echo -e "${GREEN}✅ Maven build successful for ${SERVICE_NAME}${NC}"
else
    echo -e "${RED}❌ Maven build failed for ${SERVICE_NAME}${NC}"
    exit 1
fi
cd .. || exit 1

# Step 2: Stop the running container
echo -e "${YELLOW}[2/4] 🛑 Stopping ${SERVICE_NAME} container...${NC}"
docker-compose stop "${SERVICE_NAME}"
if [ $? -eq 0 ]; then
    echo -e "${GREEN}✅ Container stopped successfully${NC}"
else
    echo -e "${RED}❌ Failed to stop container${NC}"
    exit 1
fi

# Step 3: Remove the old image
echo -e "${RED}[3/4] 🧹 Removing old Docker image for ${SERVICE_NAME}...${NC}"
docker-compose rm -f "${SERVICE_NAME}"
if [ $? -eq 0 ]; then
    echo -e "${GREEN}✅ Container removed successfully${NC}"
else
    echo -e "${RED}❌ Failed to remove container${NC}"
    exit 1
fi

# Step 4: Start the service with new image
echo -e "${BLUE}[4/4] 🚀 Starting ${SERVICE_NAME} container with new image...${NC}"
docker-compose up -d "${SERVICE_NAME}"
if [ $? -eq 0 ]; then
    echo -e "${GREEN}✅ Container started successfully${NC}"
else
    echo -e "${RED}❌ Failed to start container${NC}"
    exit 1
fi

echo ""
echo -e "${BLUE}========================================${NC}"
echo -e "${GREEN}✅ Service '${SERVICE_NAME}' restarted successfully!${NC}"
echo -e "${BLUE}========================================${NC}"
echo ""
echo -e "${YELLOW}📊 Checking container status:${NC}"
docker-compose ps "${SERVICE_NAME}"
echo ""
echo -e "${YELLOW}📋 To view logs, run:${NC}"
echo -e "  docker-compose logs -f ${SERVICE_NAME}"

