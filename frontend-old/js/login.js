function login() {

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const errorMsg = document.getElementById("errorMsg");

    // Basic validation
    if (!email || !password) {
        errorMsg.innerText = "Email and password are required";
        errorMsg.style.display = "block";
        return;
    }

    // Correct endpoint: Security service on port 8084 with context path /security
    fetch("http://localhost:8084/security/api/v1/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            userName: email,
            password: password
        })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json();
    })
    .then(data => {
        if (data.isSuccess) {
            // ✅ Store token
            if (data.data && data.data.token) {
                localStorage.setItem("token", data.data.token);
                localStorage.setItem("user", JSON.stringify(data.data));
            }
            // Redirect to home page
            window.location.href = "/home.html";
        } else {
            // ❌ Show error message
            errorMsg.innerText = data.sucessMessage || "Invalid email or password";
            errorMsg.style.display = "block";
        }
    })
    .catch(error => {
        console.error("Login error:", error);
        errorMsg.innerText = "Server error. Please try again later. " + error.message;
        errorMsg.style.display = "block";
    });
}


function logout() {
        localStorage.removeItem("token");
        window.location.href = "/index.html";
    }