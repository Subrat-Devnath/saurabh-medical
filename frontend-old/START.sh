#!/bin/bash

echo "Starting Medical Services Frontend..."
echo ""
echo "This script will install dependencies and start the development server."
echo "Please wait - this may take a few minutes."
echo ""

# Navigate to frontend directory
cd "$(dirname "$0")"

# Install dependencies if node_modules doesn't exist
if [ ! -d "node_modules" ]; then
    echo "Installing dependencies..."
    npm install
fi

# Start the development server
echo ""
echo "Starting development server..."
echo "The application will open at http://localhost:3000"
echo ""

npm start

