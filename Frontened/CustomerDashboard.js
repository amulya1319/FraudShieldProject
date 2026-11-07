
// Customer Dashboard JavaScript

// Logout Button
const logoutBtn = document.getElementById("logoutBtn");

logoutBtn.addEventListener("click", function() {

    const confirmLogout = confirm("Are you sure you want to logout?");

    if (confirmLogout) {
        window.location.href = "Login.html";
    }

});
