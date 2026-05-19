# Quick Start Guide - React Medical Services Frontend

## 🚀 Quick Start (30 seconds)

### Option 1: Double-click the START script (Windows)
```
G:\Mega Project\saurabh-medical\frontend\START.bat
```

### Option 2: Manual startup (Terminal/PowerShell)
```powershell
cd "G:\Mega Project\saurabh-medical\frontend"
npm install  # Only needed on first run
npm start
```

### Option 3: Using bash/WSL
```bash
cd "G:\Mega Project\saurabh-medical\frontend"
bash START.sh
```

## ✨ Application Overview

### 📋 Login Page
- Clean, modern login interface
- Email and password inputs
- Real-time error messages
- Loading state during authentication

**Test Credentials:**
```
Email: subartnath63@gmail.com
Password: Yagna@123#
```

### 🎯 Dashboard
After login, you'll see:
- Welcome message with your username
- "View Products" button
- Product search functionality
- Logout button in top-right

### 📦 Products List
- Displays products in a responsive grid
- Shows product details (price, description, stock, etc.)
- "View Details" button for each product
- Beautiful card-based layout

## 🔧 System Requirements

- Node.js v14+ (download from https://nodejs.org/)
- Windows, macOS, or Linux
- Modern web browser (Chrome, Firefox, Safari, Edge)

## ⚙️ Backend Services Required

Before running the frontend, ensure these services are running:

| Service | Port | API Base |
|---------|------|----------|
| Security Service | 8084 | `/security/api/v1` |
| User Management | 8081 | `/user-mgmt/api/v1` |
| Product Management | 8082 | `/product-mgmt/api/v1` |

## 📊 Application Flow

```
┌─────────────┐
│  Login Page │ ← Start here
└──────┬──────┘
       │ (Enter credentials & login)
       ▼
┌──────────────┐
│  Dashboard   │ ← Protected route
└──────┬───────┘
       │ (Click "View Products")
       ▼
┌──────────────────┐
│  Products List   │ ← Display all products
└──────┬───────────┘
       │ (Search or view details)
       ▼
[Logout] → Back to Login
```

## 🎨 UI Features

### Login Page
- Purple gradient background
- Clean white form container
- Real-time validation
- Error message display
- Smooth animations

### Dashboard
- Professional header with gradient
- Welcome greeting
- Organized sections
- Footer with copyright
- Responsive design

### Products Grid
- Auto-responsive columns (adjusts to screen size)
- Hover effects on product cards
- Color-coded stock status
- Easy-to-read product information

## 📱 Responsive Design

The application is fully responsive:
- **Desktop**: Full 3-4 column grid
- **Tablet**: 2 column grid
- **Mobile**: 1 column layout

## 🔐 Authentication Flow

1. **Login Request**: 
   - Send email/password to Security Service
   - Receive JWT token

2. **Token Storage**: 
   - Token stored in browser localStorage
   - Automatically used for protected routes

3. **Protected Routes**: 
   - Dashboard requires valid token
   - Redirects to login if token missing

4. **Logout**: 
   - Clear token from storage
   - Redirect to login page

## 🌐 API Endpoints Used

### Login
```
POST http://localhost:8084/security/api/v1/login
Content-Type: application/json

{
  "userName": "email@example.com",
  "password": "password"
}
```

### Search Products (NO TOKEN NEEDED)
```
GET http://localhost:8082/product-mgmt/api/v1/search-product?productName=searchTerm
```

## 🛠️ Available Commands

```bash
# Start development server
npm start

# Build for production
npm run build

# Run tests
npm test

# Eject configuration (not recommended)
npm run eject
```

## 🐛 Troubleshooting

### "Port 3000 is already in use"
```powershell
# Kill process using port 3000
Get-NetTCPConnection -LocalPort 3000 | Stop-Process -Force
```

### "npm: command not found"
- Install Node.js from https://nodejs.org/
- Restart your terminal after installation

### Login fails
- Verify Security Service is running on port 8084
- Check username/password are correct
- Look at browser console (F12) for detailed error

### Products not showing
- Verify Product Service is running on port 8082
- Check network tab (F12 → Network) for API responses
- Ensure search-product API returns correct format

## 📂 Project Structure

```
frontend/
├── public/
│   └── index.html
├── src/
│   ├── components/
│   │   ├── PrivateRoute.js
│   │   ├── ProductsList.js
│   │   └── ProductsList.css
│   ├── pages/
│   │   ├── LoginPage.js
│   │   ├── LoginPage.css
│   │   ├── Dashboard.js
│   │   └── Dashboard.css
│   ├── services/
│   │   └── api.js
│   ├── App.js
│   ├── App.css
│   ├── index.js
│   └── index.css
├── package.json
├── .env
├── .env.local
└── README.md
```

## 🎯 Next Steps

1. ✅ Install Node.js (if not already done)
2. ✅ Run `npm install` in the frontend directory
3. ✅ Run `npm start` to launch the application
4. ✅ Open browser to `http://localhost:3000`
5. ✅ Login with your credentials
6. ✅ Explore the dashboard and products

## 💡 Tips

- Use F12 to open Developer Tools for debugging
- Check Network tab to see API calls
- Check Console for error messages
- Refresh page if stuck
- Clear browser cache if issues persist

## 📖 File Descriptions

| File | Purpose |
|------|---------|
| `App.js` | Main app component with routing |
| `index.js` | React app entry point |
| `LoginPage.js` | Login form component |
| `Dashboard.js` | Main dashboard component |
| `ProductsList.js` | Products display component |
| `PrivateRoute.js` | Route protection wrapper |
| `api.js` | API service layer |
| `.env` | Environment configuration |
| `package.json` | Dependencies and scripts |

## ⚡ Performance Notes

- App loads in ~2-3 seconds on average connection
- Search results instant with good network
- Optimized production build available with `npm run build`

## 🔒 Security Notes

- Tokens stored in localStorage (consider httpOnly cookies for production)
- All API requests validated on backend
- CORS headers configured correctly
- No sensitive data in client-side code

## 📞 Need Help?

1. Check browser console (F12 → Console tab)
2. Check Network tab for API issues
3. Verify backend services are running
4. Check `.env` file for correct URLs
5. Review error messages in the application

---

**Happy coding! 🎉**

For detailed information, see README.md

