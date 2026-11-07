
// Registration Form Validation

const registerForm = document.getElementById("registerForm");

registerForm.addEventListener("submit", function(event) {

    event.preventDefault();

    const firstName = document.getElementById("firstName").value.trim();
    const lastName = document.getElementById("lastName").value.trim();
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value;
    const age = Number(document.getElementById("age").value);
    const gender = document.getElementById("gender").value;
    const accountNumber = document.getElementById("accountNumber").value.trim();
    const address = document.getElementById("address").value.trim();
    const terms = document.getElementById("terms").checked;

    // Name Validation
    if (firstName === "" || lastName === "") {
        alert("Please enter your first name and last name.");
        return;
    }

    // Email Validation
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!emailPattern.test(email)) {
        alert("Please enter a valid email address.");
        return;
    }

    // Password Validation
    if (password.length < 8) {
        alert("Password must contain at least 8 characters.");
        return;
    }

    // Age Validation
    if (!Number.isInteger(age) || age <= 18) {
        alert("Age must be greater than 18.");
        return;
    }

    // Gender Validation
    if (gender === "") {
        alert("Please select your gender.");
        return;
    }

    // Account Number Validation
    const accountPattern = /^[0-9]{11}$/;

    if (!accountPattern.test(accountNumber)) {
        alert("Demo account number must contain exactly 11 digits.");
        return;
    }

    // Address Validation
    if (address === "") {
        alert("Please enter your address.");
        return;
    }

    // Terms Validation
    if (!terms) {
        alert("Please accept the Terms and Conditions.");
        return;
    }

    alert("Registration form validation successful!");

});
