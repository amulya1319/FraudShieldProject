
const riskFilter = document.getElementById("riskFilter");
const alertCards = document.querySelectorAll(".alert-card");
const noAlerts = document.getElementById("noAlerts");

riskFilter.addEventListener("change", function() {

    const selectedRisk = riskFilter.value;
    let visibleCount = 0;

    alertCards.forEach(function(card) {

        const cardRisk = card.dataset.risk;

        if (selectedRisk === "all" || selectedRisk === cardRisk) {
            card.style.display = "block";
            visibleCount++;
        } else {
            card.style.display = "none";
        }

    });

    noAlerts.style.display =
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
