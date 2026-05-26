#!/bin/bash

# Quick restart a single Docker service (without rebuild)
# Usage: ./quick-restart-service.sh user-mgmt

# Color codes for output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# Check if service name is provided
if [ -z "$1" ]; then
    echo -e "${RED}❌ Error: Service name not provided!${NC}"
    echo -e "${BLUE}Usage: ./quick-restart-service.sh <service-name>${NC}"
    echo -e "${YELLOW}Available services:${NC}"
    echo "  - user-mgmt"
    echo "  - product-mgmt"
    echo "  - security"
    echo "  - email"
    echo "  - api-gateway"
    echo "  - service-registry"
    echo "  - mysql"
    echo "  - cassandra"
    echo "  - nginx"
    echo "  - frontend"
    exit 1
fi

SERVICE_NAME=$1

# Validate service name
VALID_SERVICES=("user-mgmt" "product-mgmt" "security" "email" "api-gateway" "service-registry" "mysql" "cassandra" "nginx" "frontend")
if [[ ! " ${VALID_SERVICES[@]} " =~ " ${SERVICE_NAME} " ]]; then
    echo -e "${RED}❌ Invalid service name: ${SERVICE_NAME}${NC}"
    echo -e "${YELLOW}Valid services are:${NC}"
    for service in "${VALID_SERVICES[@]}"; do
        echo "  - $service"
    done
    exit 1
fi

echo -e "${BLUE}========================================${NC}"
echo -e "${BLUE}⚡ Quick Restart Service: ${SERVICE_NAME}${NC}"
echo -e "${BLUE}========================================${NC}"
echo ""

# Quick restart without rebuild
echo -e "${BLUE}🔄 Restarting ${SERVICE_NAME} container...${NC}"
docker-compose restart "${SERVICE_NAME}"

if [ $? -eq 0 ]; then
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
else
    echo -e "${RED}❌ Failed to restart container${NC}"
    exit 1
fi

