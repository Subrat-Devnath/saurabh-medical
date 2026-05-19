# Frontend Application - Component Documentation

## Components Overview

### 🔐 PrivateRoute Component
**File**: `src/components/PrivateRoute.js`

Protects routes that require authentication.

```javascript
import { Navigate } from 'react-router-dom';
import { authService } from '../services/api';

const PrivateRoute = ({ children }) => {
  const isAuthenticated = authService.isAuthenticated();
  if (!isAuthenticated) {
    return <Navigate to="/login" replace />;
  }
  return children;
};
```

**Features**:
- Checks for authentication token
- Redirects unauthenticated users to login
- Wraps protected routes
- Used in App.js for Dashboard route

**Usage**:
```jsx
<Route 
  path="/dashboard" 
  element={
    <PrivateRoute>
      <Dashboard />
    </PrivateRoute>
  } 
/>
```

---

### 🔑 LoginPage Component
**File**: `src/pages/LoginPage.js`

Handles user authentication and login flow.

**Features**:
- Email and password input fields
- Form validation
- Loading state during submission
- Error message display
- Automatic redirect on successful login
- Stores authentication token

**State Management**:
```javascript
const [email, setEmail] = useState('');
const [password, setPassword] = useState('');
const [error, setError] = useState('');
const [loading, setLoading] = useState(false);
```

**API Call**:
```javascript
const response = await authService.login(email, password);
```

**Styling**: `LoginPage.css`
- Gradient purple background
- Centered white form
- Smooth animations
- Responsive design

---

### 📊 Dashboard Component
**File**: `src/pages/Dashboard.js`

Main application dashboard for authenticated users.

**Features**:
- Welcome message with username
- Products section with "View Products" button
- Search functionality
- Product list display
- Logout functionality
- Error handling
- Loading states

**State Management**:
```javascript
const [showProducts, setShowProducts] = useState(false);
const [products, setProducts] = useState([]);
const [loading, setLoading] = useState(false);
const [error, setError] = useState('');
const [searchTerm, setSearchTerm] = useState('');
```

**Key Functions**:
- `handleShowProducts()` - Fetches products from API
- `handleSearch()` - Searches products based on term
- `handleLogout()` - Clears token and redirects to login

**Styling**: `Dashboard.css`
- Professional header with gradient
- Responsive layout
- Color-coded sections
- Footer with copyright

---

### 📦 ProductsList Component
**File**: `src/components/ProductsList.js`

Displays products in a responsive grid layout.

**Props**:
```javascript
ProductsList.propTypes = {
  products: PropTypes.arrayOf(PropTypes.object).isRequired
};
```

**Product Data Structure**:
```javascript
{
  id: 1,
  productName: "Product Name",
  price: 99.99,
  description: "Product description",
  category: "Category Name",
  stock: 10,
  manufacturer: "Manufacturer Name"
}
```

**Features**:
- Responsive grid (3-4 columns on desktop, 2 on tablet, 1 on mobile)
- Product cards with hover effects
- Price badge
- Stock status indicator
- Empty state message
- "View Details" button

**Styling**: `ProductsList.css`
- CSS Grid layout
- Card-based design
- Color-coded stock status
- Responsive columns

---

### 🌐 API Service
**File**: `src/services/api.js`

Centralized API integration layer.

**Services**:

#### 1. Auth Service
```javascript
authService.login(userName, password)
authService.logout()
authService.getToken()
authService.getUser()
authService.isAuthenticated()
```

#### 2. Product Service
```javascript
productService.searchProducts(searchTerm)
productService.getAllProducts()
```

**API Base URLs**:
```javascript
const API_BASE_URL = 'http://localhost:8081/user-mgmt/api/v1'
const SECURITY_API_URL = 'http://localhost:8084/security/api/v1'
const PRODUCT_API_URL = 'http://localhost:8082/product-mgmt/api/v1'
```

**Token Storage**:
- Tokens stored in `localStorage`
- Retrieved on login
- Cleared on logout

---

### 🎨 App Component
**File**: `src/App.js`

Main application component with routing configuration.

**Routes**:
- `/login` - Login page (public)
- `/dashboard` - Dashboard (protected)
- `/` - Redirect to dashboard

**Routing Setup**:
```javascript
<BrowserRouter>
  <Routes>
    <Route path="/login" element={<LoginPage />} />
    <Route 
      path="/dashboard" 
      element={
        <PrivateRoute>
          <Dashboard />
        </PrivateRoute>
      } 
    />
    <Route path="/" element={<Navigate to="/dashboard" replace />} />
  </Routes>
</BrowserRouter>
```

---

## Styling Architecture

### CSS Files Structure

| File | Purpose |
|------|---------|
| `index.css` | Global styles and resets |
| `App.css` | Main app styles and utility classes |
| `LoginPage.css` | Login page styling |
| `Dashboard.css` | Dashboard layout and styling |
| `ProductsList.css` | Products grid and cards |

### Color Scheme

```css
Primary Gradient: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
Success: #27ae60
Error: #e74c3c
Background: #f5f5f5
Text: #333
Secondary: #666
Border: #ddd
```

### Responsive Breakpoints

```css
Mobile: max-width: 480px
Tablet: max-width: 768px
Desktop: 768px and above
Max Width Container: 1200px
```

---

## Data Flow Diagram

```
┌──────────────────┐
│   Login Page     │
│  (public route)  │
└────────┬─────────┘
         │ (submit credentials)
         ▼
┌──────────────────┐
│   API Service    │
│  (authService)   │
└────────┬─────────┘
         │ (returns token)
         ▼
┌──────────────────┐
│  localStorage    │
│  (store token)   │
└────────┬─────────┘
         │ (authenticated)
         ▼
┌──────────────────┐
│   Dashboard      │
│ (protected route)│
└────────┬─────────┘
         │ (click "View Products")
         ▼
┌──────────────────┐
│  API Service     │
│(productService)  │
└────────┬─────────┘
         │ (returns products)
         ▼
┌──────────────────┐
│ ProductsList     │
│ (display grid)   │
└──────────────────┘
```

---

## State Management Pattern

The application uses React Hooks for state management:

### Example: Dashboard State
```javascript
const [showProducts, setShowProducts] = useState(false);
const [products, setProducts] = useState([]);
const [loading, setLoading] = useState(false);
const [error, setError] = useState('');
```

### State Update Pattern
```javascript
try {
  setLoading(true);
  const response = await apiCall();
  setProducts(response.data);
  setError('');
} catch (err) {
  setError(err.message);
  setProducts([]);
} finally {
  setLoading(false);
}
```

---

## Error Handling

### Login Page Errors
```javascript
if (!email || !password) {
  setError('Email and password are required');
}

try {
  const response = await authService.login(email, password);
  if (response.isSuccess) {
    navigate('/dashboard');
  } else {
    setError(response.sucessMessage || 'Invalid email or password');
  }
} catch (err) {
  setError(err.response?.data?.message || 'Server error');
}
```

### Product Fetch Errors
```javascript
try {
  const response = await productService.searchProducts(searchTerm);
  setProducts(response.data);
  setError('');
} catch (err) {
  setError('Failed to load products. Please try again.');
  setProducts([]);
}
```

---

## Best Practices Implemented

✅ **Component Structure**
- Separate page and component folders
- Single responsibility principle
- Reusable components

✅ **State Management**
- React Hooks (useState, useEffect via custom hooks potential)
- Proper state lifting
- Clean state initialization

✅ **API Integration**
- Centralized API service
- Error handling
- Token management

✅ **Styling**
- CSS modules organization
- Responsive design
- Consistent color scheme
- Smooth animations

✅ **Security**
- Protected routes
- Token storage
- Logout functionality
- CORS handling

✅ **User Experience**
- Loading states
- Error messages
- Form validation
- Smooth transitions

✅ **Code Quality**
- Clean code structure
- Meaningful variable names
- Comments where needed
- DRY principles

---

## Performance Optimizations

1. **Code Splitting**: React Router enables automatic code splitting
2. **CSS Organization**: Component-scoped styles
3. **Image Optimization**: Can be added to ProductsList
4. **Bundle Size**: Minimal dependencies (react, react-dom, react-router-dom, axios)
5. **Lazy Loading**: Can be implemented for routes if needed

---

## Accessibility Features

- ✅ Semantic HTML elements
- ✅ Form labels and inputs
- ✅ ARIA attributes where needed
- ✅ Keyboard navigation support
- ✅ Color contrast compliance
- ✅ Focus states on interactive elements

---

## Browser Compatibility

Tested and supported on:
- Chrome 90+
- Firefox 88+
- Safari 14+
- Edge 90+
- Mobile browsers (iOS Safari, Chrome Mobile)

---

## Future Enhancement Ideas

1. **Authentication**
   - Refresh token mechanism
   - Remember me functionality
   - Password reset flow

2. **Products**
   - Product detail page
   - Product filters
   - Sorting options
   - Pagination

3. **User Features**
   - User profile page
   - Settings/preferences
   - Wishlist functionality

4. **Shopping**
   - Shopping cart
   - Checkout process
   - Order history

5. **Admin**
   - Admin dashboard
   - Product management
   - User management
   - Analytics

---

## Troubleshooting Guide

### Component Not Rendering
- Check browser console for errors
- Verify component is imported correctly
- Check route path matches
- Verify state is initialized

### Styles Not Applied
- Check CSS file is imported
- Verify class names match
- Check CSS specificity
- Clear browser cache

### API Not Responding
- Verify backend service is running
- Check .env file for correct URLs
- Check network tab in DevTools
- Verify CORS configuration

---

**Last Updated**: March 8, 2026
**Version**: 1.0.0

