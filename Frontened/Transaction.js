
// Get HTML elements
const transactionForm = document.getElementById("transactionForm");

const accountNumber = document.getElementById("accountNumber");
const recipient = document.getElementById("recipient");
const recipientAccount = document.getElementById("recipientAccount");
const amount = document.getElementById("amount");
const transactionType = document.getElementById("transactionType");

const accountError = document.getElementById("accountError");
const recipientError = document.getElementById("recipientError");
const recipientAccountError =
    document.getElementById("recipientAccountError");
const amountError = document.getElementById("amountError");
const typeError = document.getElementById("typeError");

// Validate account number while typing
function validateAccount(input, errorElement, fieldName) {

    input.value = input.value.replace(/[^0-9]/g, "");

    if (input.value.length === 0) {
        errorElement.textContent = "";
    }
    else if (input.value.length !== 11) {
        errorElement.textContent =
            fieldName + " must contain exactly 11 digits.";
    }
    else {
        errorElement.textContent = "";
    }
}

// Sender account validation
accountNumber.addEventListener("input", function() {

    validateAccount(
        accountNumber,
        accountError,
        "Sender account number"
    );
});

// Recipient account validation
recipientAccount.addEventListener("input", function() {

    validateAccount(
        recipientAccount,
        recipientAccountError,
        "Recipient account number"
    );
});

// Recipient name validation
recipient.addEventListener("input", function() {

    if (recipient.value.trim() === "") {
        recipientError.textContent = "Please enter recipient name.";
    } else {
        recipientError.textContent = "";
    }
});

// Amount validation
amount.addEventListener("input", function() {

    if (amount.value !== "" && Number(amount.value) <= 0) {
        amountError.textContent =
            "Amount must be greater than zero.";
    } else {
        amountError.textContent = "";
    }
});

// Transaction type validation
transactionType.addEventListener("change", function() {

    if (transactionType.value === "") {
        typeError.textContent = "Please select transaction type.";
    } else {
        typeError.textContent = "";
    }
});

// Form submission validation
transactionForm.addEventListener("submit", function(event) {

    // Validate sender account
    if (!/^[0-9]{11}$/.test(accountNumber.value)) {

        event.preventDefault();

        accountError.textContent =
            "Please enter exactly 11 digits.";

        accountNumber.focus();
        return;
    }

    // Validate recipient account
    if (!/^[0-9]{11}$/.test(recipientAccount.value)) {

        event.preventDefault();

        recipientAccountError.textContent =
            "Please enter exactly 11 digits.";

        recipientAccount.focus();
        return;
    }

    // Check duplicate account numbers
    if (accountNumber.value === recipientAccount.value) {

        event.preventDefault();

        recipientAccountError.textContent =
            "Sender and recipient account numbers must be different.";

        recipientAccount.focus();
        return;
    }

    // Validate recipient name
    if (recipient.value.trim() === "") {

        event.preventDefault();

        recipientError.textContent =
            "Please enter recipient name.";

        recipient.focus();
        return;
    }

    // Validate amount
    if (!Number.isFinite(Number(amount.value)) ||
        Number(amount.value) <= 0) {

        event.preventDefault();

        amountError.textContent =
            "Please enter a valid amount.";

        amount.focus();
        return;
    }

    // Validate transaction type
    if (transactionType.value === "") {

        event.preventDefault();

        typeError.textContent =
            "Please select transaction type.";

        transactionType.focus();
        return;
    }

    // Valid form: allow normal submission to TransactionServlet.
});

// Logout
document.getElementById("logoutLink").addEventListener("click", function(event) {

    event.preventDefault();

    if (confirm("Are you sure you want to logout?")) {
        window.location.href = "Login.html";
    }
});
