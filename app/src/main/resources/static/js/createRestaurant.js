document.getElementById('signup-form').addEventListener('submit', function (e) {
    e.preventDefault();
    alert('Form submitted successfully!');
});

/*
document.addEventListener("DOMContentLoaded", () => {
    const storeAddress = document.getElementById("store-address");
    const selectedItemsContainer = document.getElementById("selected-items-container");

    storeAddress.addEventListener("change", () => {
        const selectedValue = storeAddress.value;

        // Verifica que no se agregue duplicado
        const exists = Array.from(selectedItemsContainer.children).some(
            item => item.dataset.value === selectedValue
        );

        if (!exists) {
            createSelectedItem(selectedValue);
        }
    });

    function createSelectedItem(value) {
        const itemDiv = document.createElement("div");
        itemDiv.className = "selected-item";
        itemDiv.dataset.value = value;

        const text = document.createElement("span");
        text.textContent = value;

        const deleteButton = document.createElement("button");
        deleteButton.textContent = "X";
        deleteButton.onclick = () => {
            selectedItemsContainer.removeChild(itemDiv);
        };

        itemDiv.appendChild(text);
        itemDiv.appendChild(deleteButton);
        selectedItemsContainer.appendChild(itemDiv);
    }
});*/

document.addEventListener("DOMContentLoaded", () => {
    const selectedItemsContainer = document.getElementById("selected-items-container");
    const categoriesSelect = document.getElementById("categories");
    const addCategoryButton = document.getElementById("add-category-btn");
    const form = document.getElementById("signup-form");
    const selectedCategories = [];

    // Agregar categoría al contenedor
    addCategoryButton.addEventListener("click", () => {
        const category = categoriesSelect.value;
        if (category && !selectedCategories.includes(category)) {
            selectedCategories.push(category);

            // Crear elemento visual
            const categoryElement = document.createElement("div");
            categoryElement.className = "selected-item";
            categoryElement.innerHTML = `
                ${category}
                <button onclick="removeCategory('${category}')">X</button>
            `;
            selectedItemsContainer.appendChild(categoryElement);
        }
    });

    // Eliminar categoría seleccionada
    window.removeCategory = (category) => {
        const index = selectedCategories.indexOf(category);
        if (index > -1) {
            selectedCategories.splice(index, 1);
        }
        selectedItemsContainer.innerHTML = "";
        selectedCategories.forEach((cat) => {
            const categoryElement = document.createElement("div");
            categoryElement.className = "selected-item";
            categoryElement.innerHTML = `
                ${cat}
                <button onclick="removeCategory('${cat}')">X</button>
            `;
            selectedItemsContainer.appendChild(categoryElement);
        });
    };

    // Enviar datos al backend
    form.addEventListener("submit", async (event) => {
        event.preventDefault();

        const data = {
            nameRestaurant: form.nameRestaurant.value,
            openTime: form.openTime.value,
            closeTime: form.closeTime.value,
            imagePath: form.imagePath.value,
            imageLogoPath: form.imageLogoPath.value,
            categories: selectedCategories,
        };

        try {
            const response = await fetch("/api/restaurants", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(data),
            });

            if (response.ok) {
                alert("Restaurante registrado exitosamente");
                form.reset();
                selectedItemsContainer.innerHTML = "";
                selectedCategories.length = 0;
            } else {
                alert("Ocurrió un error al registrar el restaurante");
            }
        } catch (error) {
            alert("Error al conectar con el servidor");
        }
    });
});
