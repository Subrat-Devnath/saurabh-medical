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

    fetch("http://127.0.0.1:8081/user-mgmt/api/v1/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            email: email,
            password: password
        })
    })
    .then(response => response.json())
    .then(data => {

        if (data.success) {
            // ✅ Redirect to home page
            window.location.href = "../html/home.html";
        } else {
            // ❌ Show error message
            errorMsg.innerText = data.message || "Invalid email or password";
            errorMsg.style.display = "block";
        }
    })
    .catch(error => {
        console.error("Login error:", error);
        errorMsg.innerText = "Server error. Please try again later.";
        errorMsg.style.display = "block";
    });
}
