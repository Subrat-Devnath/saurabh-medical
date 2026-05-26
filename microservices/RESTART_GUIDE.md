# Service Restart Guide

This guide explains how to restart individual services without restarting all services.

## Quick Start

### For Windows (PowerShell)
```powershell
# Restart user-mgmt service
.\restart-service.ps1 -ServiceName "user-mgmt"

# Restart product-mgmt service
.\restart-service.ps1 -ServiceName "product-mgmt"

# Restart security service
.\restart-service.ps1 -ServiceName "security"

# Restart email service
.\restart-service.ps1 -ServiceName "email"

# Restart api-gateway service
.\restart-service.ps1 -ServiceName "api-gateway"

# Restart service-registry service
.\restart-service.ps1 -ServiceName "service-registry"
```

### For Linux/Mac (Bash)
```bash
# Make the script executable (first time only)
chmod +x restart-service.sh

# Restart user-mgmt service
./restart-service.sh user-mgmt

# Restart product-mgmt service
./restart-service.sh product-mgmt

# Restart security service
./restart-service.sh security

# Restart email service
./restart-service.sh email

# Restart api-gateway service
./restart-service.sh api-gateway

# Restart service-registry service
./restart-service.sh service-registry
```

## What the Script Does

The restart script performs the following steps automatically:

1. **Maven Build** - Runs `mvn clean install -DskipTests` for the specific service
2. **Stop Container** - Stops the running Docker container
3. **Remove Container** - Removes the old container and image
4. **Start Container** - Starts a new container with the updated image
5. **Status Check** - Displays the container status and log instructions

## Available Services

- `user-mgmt` - User Management Service
- `product-mgmt` - Product Management Service
- `security` - Security/JWT Service
- `email` - Email Service
- `api-gateway` - API Gateway
- `service-registry` - Eureka Service Registry

## Examples

### Example 1: Restart user-mgmt (Windows)
```powershell
.\restart-service.ps1 -ServiceName "user-mgmt"
```

### Example 2: Restart product-mgmt (Linux/Mac)
```bash
./restart-service.sh product-mgmt
```

### Example 3: View logs after restart
```bash
# After restart, view logs in real-time
docker-compose logs -f user-mgmt
```

## Troubleshooting

### Maven Build Fails
- Check if Maven is installed: `mvn -version`
- Ensure you're in the correct directory
- Check for Java dependency issues in the service

### Docker Container Won't Start
```bash
# Check container logs
docker-compose logs user-mgmt

# Check Docker daemon status
docker ps

# Ensure port is not already in use
netstat -an | grep 8081  # For user-mgmt
```

### Permission Denied (Linux/Mac)
```bash
# Make the script executable
chmod +x restart-service.sh

# Then run it
./restart-service.sh user-mgmt
```

### PowerShell Execution Policy (Windows)
```powershell
# If you get execution policy error, run:
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser

# Then run the script
.\restart-service.ps1 -ServiceName "user-mgmt"
```

## Manual Alternative (Without Script)

If you prefer to do it manually:

```bash
# 1. Build the service
cd user-mgmt
mvn clean install -DskipTests
cd ..

# 2. Stop the container
docker-compose stop user-mgmt

# 3. Remove the old container
docker-compose rm -f user-mgmt

# 4. Start the new container
docker-compose up -d user-mgmt

# 5. Check status
docker-compose ps user-mgmt

# 6. View logs
docker-compose logs -f user-mgmt
```

## Common Commands Reference

```bash
# View all containers and their status
docker-compose ps

# View logs for a specific service (last 100 lines)
docker-compose logs --tail=100 user-mgmt

# View logs in real-time
docker-compose logs -f user-mgmt

# Restart without rebuilding (quick restart)
docker-compose restart user-mgmt

# Check if container is running
docker-compose ps user-mgmt

# Get container details
docker-compose inspect user-mgmt

# Execute command inside container
docker-compose exec user-mgmt bash
```

## Important Notes

⚠️ **Remember:**
- This script performs a full rebuild with `mvn clean install`
- If you only want to restart without rebuilding, use: `docker-compose restart <service-name>`
- Database containers (MySQL, Cassandra) are not affected by service restarts
- Restarting a service doesn't stop other services - they keep running

## Next Steps

For more information:
- View Docker Compose documentation: https://docs.docker.com/compose/
- View Docker documentation: https://docs.docker.com/
- Check your service logs: `docker-compose logs -f <service-name>`

