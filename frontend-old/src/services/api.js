import axios from 'axios';

const API_BASE_URL = process.env.REACT_APP_API_BASE_URL || 'http://localhost:8081/user-mgmt/api/v1';
const SECURITY_API_URL = 'http://localhost:8084/security/api/v1';
const PRODUCT_API_URL = 'http://localhost:8082/product-mgmt/api/v1';

// Create axios instances
const apiClient = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

const securityClient = axios.create({
  baseURL: SECURITY_API_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

const productClient = axios.create({
  baseURL: PRODUCT_API_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Auth Services
export const authService = {
  login: async (userName, password) => {
    try {
      const response = await securityClient.post('/login', {
        userName,
        password,
      });

      if (response.data && response.data.data && response.data.data.token) {
        localStorage.setItem('token', response.data.data.token);
        localStorage.setItem('user', JSON.stringify(response.data.data));
      }

      return response.data;
    } catch (error) {
      console.error('Login error:', error);
      throw error;
    }
  },

  logout: () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
  },

  getToken: () => {
    return localStorage.getItem('token');
  },

  getUser: () => {
    const user = localStorage.getItem('user');
    return user ? JSON.parse(user) : null;
  },

  isAuthenticated: () => {
    return !!localStorage.getItem('token');
  },
};

// Product Services
export const productService = {
  searchProducts: async (searchTerm) => {
    try {
      const response = await productClient.get('/search-product', {
        params: {
          productName: searchTerm || '',
        },
      });
      return response.data;
    } catch (error) {
      console.error('Search products error:', error);
      throw error;
    }
  },

  getAllProducts: async () => {
    try {
      const response = await productClient.get('/all-products');
      return response.data;
    } catch (error) {
      console.error('Get all products error:', error);
      throw error;
    }
  },
};

export default {
  authService,
  productService,
};

