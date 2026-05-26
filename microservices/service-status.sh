#!/bin/bash

# Show status of all services
# Usage: ./service-status.sh

# Color codes
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m'

echo ""
echo -e "${BLUE}========================================${NC}"
echo -e "${BLUE}📊 MICROSERVICES STATUS DASHBOARD${NC}"
echo -e "${BLUE}========================================${NC}"
echo ""

echo -e "${YELLOW}Getting service status...${NC}"
echo ""

# Show all services status
docker-compose ps

echo ""
echo -e "${BLUE}========================================${NC}"
echo -e "${BLUE}🔍 SERVICE DETAILS${NC}"
echo -e "${BLUE}========================================${NC}"
echo ""

SERVICES=("user-mgmt" "product-mgmt" "security" "email" "api-gateway" "service-registry")

for service in "${SERVICES[@]}"; do
    STATUS=$(docker-compose ps "$service" --format "{{.State}}")

    if [[ "$STATUS" == *"running"* ]]; then
        echo -e "${GREEN}✅ $service : RUNNING${NC}"
    elif [[ "$STATUS" == *"exited"* ]]; then
        echo -e "${RED}❌ $service : STOPPED${NC}"
    else
        echo -e "${YELLOW}⚠️  $service : $STATUS${NC}"
    fi
done

echo ""
echo -e "${BLUE}========================================${NC}"
echo -e "${BLUE}📋 QUICK COMMANDS${NC}"
echo -e "${BLUE}========================================${NC}"
echo ""
echo -e "${YELLOW}View logs for a service:${NC}"
echo "  docker-compose logs -f <service-name>"
echo ""
echo -e "${YELLOW}Restart a service (with rebuild):${NC}"
echo "  ./restart-service.sh user-mgmt"
echo ""
echo -e "${YELLOW}Quick restart (without rebuild):${NC}"
echo "  ./quick-restart-service.sh user-mgmt"
echo ""
echo -e "${YELLOW}Stop a service:${NC}"
echo "  docker-compose stop <service-name>"
echo ""
echo -e "${YELLOW}Start a service:${NC}"
echo "  docker-compose start <service-name>"
echo ""

