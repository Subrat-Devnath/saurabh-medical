import React from 'react';
import './ProductsList.css';

const ProductsList = ({ products }) => {
  if (!products || products.length === 0) {
    return (
      <div className="empty-state">
        <p>No products found. Try searching with different keywords.</p>
      </div>
    );
  }

  return (
    <div className="products-container">
      <h3 className="products-heading">Available Products ({products.length})</h3>
      <div className="products-grid">
        {products.map((product, index) => (
          <div key={product.id || index} className="product-card">
            <div className="product-header">
              <h4 className="product-name">
                {product.productName || product.name || 'Unknown Product'}
              </h4>
              {product.price && (
                <span className="product-price">
                  ${parseFloat(product.price).toFixed(2)}
                </span>
              )}
            </div>

            <div className="product-body">
              {product.description && (
                <p className="product-description">{product.description}</p>
              )}

              {product.category && (
                <div className="product-info">
                  <span className="info-label">Category:</span>
                  <span className="info-value">{product.category}</span>
                </div>
              )}

              {product.stock !== undefined && (
                <div className="product-info">
                  <span className="info-label">Stock:</span>
                  <span className={`info-value ${product.stock > 0 ? 'in-stock' : 'out-of-stock'}`}>
                    {product.stock > 0 ? `${product.stock} units` : 'Out of Stock'}
                  </span>
                </div>
              )}

              {product.manufacturer && (
                <div className="product-info">
                  <span className="info-label">Manufacturer:</span>
                  <span className="info-value">{product.manufacturer}</span>
                </div>
              )}
            </div>

            <div className="product-footer">
              <button className="view-button">View Details</button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ProductsList;

