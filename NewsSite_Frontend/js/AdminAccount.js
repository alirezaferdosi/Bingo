

/////////////////////////////////////  Edit profile
function toggleEdit(fieldId) {
	
    const field = document.getElementById(fieldId);
    const isReadOnly = field.hasAttribute("readonly");
	resizeTextbox(field);
    if (isReadOnly) {
        // Remove readonly to allow editing
        field.removeAttribute("readonly");
        field.style.border = "1px solid #ccc"; // Optional: Add visual feedback
        field.focus();
    } else {
        // Reapply readonly to make the field untouchable again
        field.setAttribute("readonly", true);
        field.style.border = "none"; // Optional: Revert visual feedback
    }
	
}


// Handle toggling password edit
function togglePasswordEdit() {
    const passwordInput = document.getElementById("password");
    const confirmPasswordInput = document.getElementById("confirmPassword");
    const isReadOnly = passwordInput.hasAttribute("readonly");

    if (isReadOnly) {
        // Enable editing
        passwordInput.removeAttribute("readonly");
        confirmPasswordInput.removeAttribute("readonly");
        confirmPasswordInput.style.display = "inline"; // Show the confirmation field
    } else {
        // Check if passwords match
        if (passwordInput.value !== confirmPasswordInput.value) {
            alert("Passwords aren't the same. Please correct them.");
            return; // Do not hide the confirmation field or make inputs read-only
        }

        // Disable editing if passwords match
        passwordInput.setAttribute("readonly", true);
        confirmPasswordInput.setAttribute("readonly", true);
        confirmPasswordInput.style.display = "none"; // Hide the confirmation field
    }
}


function showPassword() {
    const password = document.getElementById("password");
    const confirmPassword = document.getElementById("confirmPassword");

    password.type = "text";
    confirmPassword.type = "text";

    // Adjust width dynamically
    resizeTextbox(password);
    resizeTextbox(confirmPassword);
}

function hidePassword() {
    const password = document.getElementById("password");
    const confirmPassword = document.getElementById("confirmPassword");

    password.type = "password";
    confirmPassword.type = "password";

    // Adjust width dynamically
    resizeTextbox(password);
    resizeTextbox(confirmPassword);
}

function resizeTextbox(input) {
    if (input) {
        input.style.width = 23 + "ch"; // Dynamically resize based on content
    }
}

//////////////////////////////////////////////////////////////////////////////////////


// Handle changing the profile picture
function changePhoto() {
    const photoInput = document.getElementById("photo");
    const profilePic = document.getElementById("profile-pic");
    const file = photoInput.files[0];

    if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
            profilePic.src = e.target.result;
        };
        reader.readAsDataURL(file);
    }
}

// Navigation click logic (same as your existing code)
document.querySelectorAll(".navList, .bottom-link li").forEach(function (element) {
    element.addEventListener("click", function () {
        document.querySelectorAll(".navList, .bottom-link li").forEach(function (e) {
            e.classList.remove("active");
        });

        this.classList.add("active");

        document.querySelectorAll(".data-table").forEach(function (table) {
            table.style.display = "none";
        });

        const allMenuItems = document.querySelectorAll(".navList, .bottom-link li");
        const index = Array.from(allMenuItems).indexOf(this);

        const tables = document.querySelectorAll(".data-table");
        if (tables.length > index) {
            tables[index].style.display = "block";
        }

        if (this.classList.contains("active")) {
            setTimeout(() => {
                this.classList.remove("active");
            }, 1000);
        } else {
            this.classList.add("active");
        }
    });
});

// Select all cards
document.querySelectorAll(".card").forEach(card => {
    card.addEventListener("click", () => {
        // Check the current status of the card
        const currentStatus = card.getAttribute("data-status");

        if (currentStatus === "active") {
            // Set to deactive and apply grayscale
            card.setAttribute("data-status", "deactive");
        } else {
            // Set to active and remove grayscale
            card.setAttribute("data-status", "active");
        }
    });
});


//=====================================  logout alert   ===============================
function confirmLogout() {
    const userConfirmed = confirm("Are you sure you want to log out?");
    if (userConfirmed) {
        // Redirect to the logout page or perform logout actions
        window.location.href = "index.html"; // Replace with your actual logout URL or function
    }
}
// Show the logout confirmation modal
function showLogoutModal() {
    document.getElementById("logoutModal").style.display = "flex";
}

// Close the modal
function closeLogoutModal() {
    document.getElementById("logoutModal").style.display = "none";
}

// Perform the logout action
function logout() {
    // Redirect to the logout page or perform logout logic
    window.location.href = "index.html"; // Replace with your actual logout URL or function
}

//===================================================================================================
document.addEventListener("DOMContentLoaded", function () {
    initializeBinaryToggleButton();
});

function initializeBinaryToggleButton() {
    const binaryButton = document.getElementById("toggleButton");

    if (!binaryButton) {
        console.error('Toggle button with ID "toggleButton" not found.');
        return;
    }

    // Set initial color based on the default state
    updateButtonStyle(binaryButton);

    binaryButton.addEventListener("click", function () {
        const currentState = this.getAttribute("data-state");

        if (currentState === "approval") {
            this.setAttribute("data-state", "removing");
            this.textContent = "Removing News";
        } else {
            this.setAttribute("data-state", "approval");
            this.textContent = "Pending Approval";
        }

        // Update button color based on the new state
        updateButtonStyle(this);
    });
}

function updateButtonStyle(button) {
    const currentState = button.getAttribute("data-state");

    if (currentState === "approval") {
        button.style.backgroundColor = "green";
        button.style.color = "white";
    } else if (currentState === "removing") {
        button.style.backgroundColor = "red";
        button.style.color = "white";
    }
}
