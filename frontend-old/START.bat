@echo off
echo Starting Medical Services Frontend...
echo.
echo This window will install dependencies and start the development server.
echo Please wait - this may take a few minutes.
echo.

REM Navigate to frontend directory
cd /d "G:\Mega Project\saurabh-medical\frontend"

REM Install dependencies if node_modules doesn't exist
if not exist node_modules (
    echo Installing dependencies...
    call npm install
)

REM Start the development server
echo.
echo Starting development server...
echo The application will open at http://localhost:3000
echo.
call npm start

