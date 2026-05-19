import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { authService, productService } from '../services/api';
import ProductsList from '../components/ProductsList';
import './Dashboard.css';

const Dashboard = () => {
  const [showProducts, setShowProducts] = useState(false);
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [searchTerm, setSearchTerm] = useState('');
  const navigate = useNavigate();
  const user = authService.getUser();

  const handleShowProducts = async () => {
    setShowProducts(true);
    setLoading(true);
    setError('');

    try {
      // Call search-product API without token (as per requirement)
      const response = await productService.searchProducts(searchTerm);

      if (response.isSuccess && response.data) {
        setProducts(response.data);
      } else if (Array.isArray(response)) {
        setProducts(response);
      } else if (response.data) {
        setProducts(Array.isArray(response.data) ? response.data : [response.data]);
      }
    } catch (err) {
      console.error('Error fetching products:', err);
      setError('Failed to load products. Please try again.');
      setProducts([]);
    } finally {
      setLoading(false);
    }
  };

  const handleLogout = () => {
    authService.logout();
    navigate('/login');
  };

  const handleSearch = (e) => {
    e.preventDefault();
    if (showProducts) {
      handleShowProducts();
    }
  };

  return (
    <div className="dashboard-container">
      <header className="dashboard-header">
        <div className="header-content">
          <h1>Medical Services Dashboard</h1>
          <div className="user-info">
            <span className="welcome-text">
              Welcome, {user?.userName || 'User'}
            </span>
            <button className="logout-button" onClick={handleLogout}>
              Logout
            </button>
          </div>
        </div>
      </header>

      <main className="dashboard-main">
        <section className="dashboard-section">
          <h2>Products</h2>
          <p className="section-description">
            View and search for available products
          </p>

          {!showProducts ? (
            <div className="button-container">
              <button
                className="primary-button"
                onClick={handleShowProducts}
              >
                View Products
              </button>
            </div>
          ) : (
            <div className="search-container">
              <form onSubmit={handleSearch} className="search-form">
                <input
                  type="text"
                  placeholder="Search products..."
                  value={searchTerm}
                  onChange={(e) => setSearchTerm(e.target.value)}
                  className="search-input"
                  disabled={loading}
                />
                <button
                  type="submit"
                  className="search-button"
                  disabled={loading}
                >
                  {loading ? 'Searching...' : 'Search'}
                </button>
              </form>

              {error && <div className="error-alert">{error}</div>}

              {loading ? (
                <div className="loading-spinner">Loading products...</div>
              ) : (
                <ProductsList products={products} />
              )}
            </div>
          )}
        </section>
      </main>

      <footer className="dashboard-footer">
        <p>&copy; 2026 Medical Services. All rights reserved.</p>
      </footer>
    </div>
  );
};

export default Dashboard;

