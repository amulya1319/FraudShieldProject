
const searchInput = document.getElementById("searchInput");
const transactionTable = document.getElementById("transactionTable");
const noResults = document.getElementById("noResults");

const rows = Array.from(transactionTable.querySelectorAll("tr"));

// Search transactions by ID, recipient or transaction type
searchInput.addEventListener("input", function() {

    const searchValue = searchInput.value.toLowerCase().trim();
    let visibleCount = 0;

    rows.forEach(function(row) {

        const rowText = row.textContent.toLowerCase();

        if (rowText.includes(searchValue)) {
            row.style.display = "";
            visibleCount++;
        } else {
            row.style.display = "none";
        }

    });

    noResults.style.display =
        visibleCount === 0 ? "block" : "none";
});

// Logout
document.getElementById("logoutLink")
    .addEventListener("click", function(event) {

        event.preventDefault();

        if (confirm("Are you sure you want to logout?")) {
            window.location.href = "Login.html";
        }

    });
