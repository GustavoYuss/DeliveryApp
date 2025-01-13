function getUserID() {
    let userJSON = localStorage.getItem("user");
    ValidateJSON(userJSON);
    return JSON.parse(userJSON);
}

function ValidateJSON(userJSON)
{
    if (!userJSON) {
        console.warn("No user data found in localStorage.");
        window.location.href = "/login";
    }
}

document.addEventListener("DOMContentLoaded", () => {
    const selectedItemsContainer = document.getElementById("selected-items-container");
    const categoriesSelect = document.getElementById("categories");
    const addCategoryButton = document.getElementById("add-category-btn");
    const form = document.getElementById("signup-form");
    let selectedCategories = [];

    addCategoryButton.addEventListener("click", () => {
        const selectedOption = categoriesSelect.options[categoriesSelect.selectedIndex];

        if (selectedOption && selectedOption.value) {
            const categoryId = selectedOption.value;
            const categoryName = selectedOption.text;
            console.log("id: " + categoryId + " name: " + categoryName);

            if (!selectedCategories.some(cat => cat.id === categoryId)) {
                selectedCategories.push({ id: categoryId, name: categoryName });
                const categoryElement = document.createElement("div");
                categoryElement.className = "selected-item";
                categoryElement.innerHTML = `
                    ${categoryName}
                    <button type="button" class="remove-category-btn" data-id="${categoryId}">X</button>
                `;
                selectedItemsContainer.appendChild(categoryElement);
            }
        }
    });

    selectedItemsContainer.addEventListener("click", (event) => {
        if (event.target.classList.contains("remove-category-btn")) {
            const categoryId = event.target.dataset.id;
            selectedCategories = selectedCategories.filter(cat => cat.id !== categoryId);
            const categoryElement = event.target.parentElement;
            selectedItemsContainer.removeChild(categoryElement);
        }
    });

    form.addEventListener("submit", async (event) => {
        event.preventDefault();
        const data = {
            nameRestaurant: form.nameRestaurant.value,
            openTime: form.openTime.value,
            closeTime: form.closeTime.value,
            imagePath: form.imagePath.value,
            imageLogoPath: form.imageLogoPath.value,
            categories: selectedCategories,
            user: getUserID(),
        };

        try {
            const response = await fetch("/registerRestaurant", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(data),
            });
            if (response.ok) {
                alert("Restaurante Creado Correctamente");
                window.location.href = "/restaurantManagement"
            } else {
                const errorData = await response.json();
                alert(`Error: ${errorData.message || "Ocurrió un error al registrar el restaurante"}`);
            }
        } catch (error) {
            alert("Error al conectar con el servidor");
        }
    });
});
