# Single Service Restart Scripts - Summary

## 📋 Files Created

I've created 5 new files in your microservices directory:

### 1. **restart-service.ps1** (Windows PowerShell)
   - Full rebuild and restart of a service
   - Runs Maven clean install
   - Removes and rebuilds Docker image
   - Use this when you make code changes

### 2. **restart-service.sh** (Linux/Mac Bash)
   - Full rebuild and restart of a service (Bash version)
   - Same functionality as PowerShell version
   - Run with: `./restart-service.sh user-mgmt`

### 3. **quick-restart-service.ps1** (Windows PowerShell)
   - Quick restart without rebuild
   - Faster - just restarts the container
   - Use this when you only need to restart without code changes

### 4. **quick-restart-service.sh** (Linux/Mac Bash)
   - Quick restart without rebuild (Bash version)
   - Faster restart

### 5. **RESTART_GUIDE.md**
   - Complete documentation and usage guide
   - Troubleshooting tips
   - Common commands reference

---

## 🚀 Quick Usage Examples

### Windows PowerShell

#### Full Restart (with rebuild):
```powershell
.\restart-service.ps1 -ServiceName "user-mgmt"
```

#### Quick Restart (without rebuild):
```powershell
.\quick-restart-service.ps1 -ServiceName "user-mgmt"
```

### Linux/Mac

#### Full Restart (with rebuild):
```bash
./restart-service.sh user-mgmt
```

#### Quick Restart (without rebuild):
```bash
./quick-restart-service.sh user-mgmt
```

---

## 📊 Comparison

| Operation | Command | Time | Use Case |
|-----------|---------|------|----------|
| **Full Restart** | `restart-service.ps1 -ServiceName "user-mgmt"` | 2-5 mins | After code changes |
| **Quick Restart** | `quick-restart-service.ps1 -ServiceName "user-mgmt"` | 10-30 secs | Just restart container |
| **Manual Restart** | `docker-compose restart user-mgmt` | 10-20 secs | Very quick restart |

---

## 📝 Available Services

- `user-mgmt` - User Management Service (Port 8081)
- `product-mgmt` - Product Management Service (Port 8082)
- `security` - Security/JWT Service (Port 8084)
- `email` - Email Service (Port 8085)
- `api-gateway` - API Gateway (Port 8079)
- `service-registry` - Eureka Service Registry (Port 8761)

---

## ✨ Key Features

✅ **Validates service names** - Won't accept invalid service names  
✅ **Colored output** - Easy to read status messages  
✅ **Automatic dependency handling** - Respects service dependencies  
✅ **Status checking** - Shows container status after restart  
✅ **Log instructions** - Helps you view logs immediately  
✅ **Error handling** - Stops on errors and reports them clearly  
✅ **Cross-platform** - Works on Windows, Linux, and Mac  

---

## 🔄 What Happens During Full Restart

1. ✅ Maven build: `mvn clean install -DskipTests`
2. ✅ Stop container
3. ✅ Remove old container
4. ✅ Build new Docker image
5. ✅ Start new container
6. ✅ Show status and logs instructions

---

## ⚡ What Happens During Quick Restart

1. ✅ Stop container
2. ✅ Start container
3. ✅ Show status and logs instructions

**Much faster!** No rebuild needed.

---

## 📌 Important Notes

- **Database containers** (MySQL, Cassandra) are NOT affected
- **Other services** keep running - only one service restarts
- **Dependencies** are automatically handled
- **Data is preserved** - volumes are not deleted
- **No downtime for other services**

---

## 🎯 Examples

### Example 1: You fixed a bug in user-mgmt
```powershell
# Windows
.\restart-service.ps1 -ServiceName "user-mgmt"

# or Linux/Mac
./restart-service.sh user-mgmt
```

### Example 2: You need to quickly restart product-mgmt
```powershell
# Windows
.\quick-restart-service.ps1 -ServiceName "product-mgmt"

# or Linux/Mac
./quick-restart-service.sh product-mgmt
```

### Example 3: View logs after restart
```bash
docker-compose logs -f user-mgmt
```

---

## 🛠️ Troubleshooting

### PowerShell Execution Policy Error
```powershell
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
```

### Script Permission Denied (Linux/Mac)
```bash
chmod +x restart-service.sh
chmod +x quick-restart-service.sh
```

### Maven Not Found
```bash
# Ensure Maven is installed
mvn -version

# Add to PATH if needed
# Or use ./mvnw for project's Maven wrapper
```

---

## 📚 More Information

For detailed documentation, see: **RESTART_GUIDE.md**

---

## 🎉 You're All Set!

Now you can easily restart individual services without restarting everything!

**Example workflow:**
1. Make code changes
2. Run: `.\restart-service.ps1 -ServiceName "user-mgmt"`
3. Wait for script to complete
4. Test your changes

That's it! 🚀

