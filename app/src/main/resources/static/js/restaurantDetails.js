function removeFirstAndLastChar(str) {
    if (str.length <= 2) {
        return '';
    }
    return str.substring(1, str.length - 1);
}

function getRestaurantID() {
    const restaurantId = localStorage.getItem('restaurant');
    const aux= removeFirstAndLastChar(restaurantId);
    return parseInt(aux, 10);
}

function getUserID() {
    let userJSON = localStorage.getItem("user");
    ValidateJSON(userJSON);
    return JSON.parse(userJSON);
}

function ShowAlertOK(title, menssage)
{
    Swal.fire({
        icon: "success",
        title: title,
        text: menssage,
        confirmButtonText: 'OK'
    }).then(() => {
        location.reload(true);
    });
}

function ShowAlertError()
{
    Swal.fire({
        icon: "error",
        title: 'Error de red',
        text: 'No fue posible conectar con el servidor. Por favor, verifica tu conexión e inténtalo nuevamente.'
    });
}

document.addEventListener("DOMContentLoaded", function () {
    const restaurantId = getRestaurantID();
    if (!isNaN(restaurantId)) {
        fetch(`/deliveryApp/restaurants/getDishesByRestaurant?id=${restaurantId}`, {
            method: "GET",
            credentials: "include",
        })
            .then(response => {
                if (!response.ok) {
                    ShowAlertError();
                    throw new Error("Error en la respuesta del servidor");
                }
                return response.json();
            })
            .then(data => {
                if (Array.isArray(data) && data.length > 0) {
                    const dishesHTML = data.map(product => createDishItemHTML(product)).join('');
                    document.getElementById('dishes-container').innerHTML = dishesHTML;
                } else {
                    document.getElementById('dishes-container').innerHTML = "<p>No hay platos disponibles.</p>";
                }
            })
            .catch(error => {
                console.error("Error:", error);
            });
    }
});

document.addEventListener("DOMContentLoaded", function () {
    const restaurantId = getRestaurantID();
    fetch(`/deliveryApp/restaurants/getDishesByRestaurant?id=${restaurantId}`, {
        method: "GET",
        credentials: "include",
    })
        .then(response => {
            if (!response.ok) {
                ShowAlertError();
                throw new Error("Error en la respuesta del servidor");
            }
            return response.json();
        })
        .then(data => {
            if (Array.isArray(data) && data.length > 0) {
                const popularDishesHTML = data.map(product => createDishPopularItemHTML(product)).join('');
                document.getElementById('popular-dishes-container').innerHTML = popularDishesHTML;
            } else {
                document.getElementById('popular-dishes-container').innerHTML = "<p>No hay platos populares disponibles.</p>";
            }
        })
        .catch(error => {
            console.error("Error:", error);
        });
});



function createDishItemHTML(product) {
    const productCard = document.createElement("div");
    const productContainer = document.getElementById("product-container");
    productCard.classList.add("product-card");

    productCard.innerHTML = `
        <div class="product-info">
          <h2>${product.name}</h2>
          <div class="price-rating">
            <span class="price">${product.normalPrice}.00</span>
            <span class="rating">• ${product.normalPrice} (${product.offerPrice})</span>
          </div>
          <p>${product.description}</p>
        </div>
        <img src="${product.imagePath}" alt="${product.name}">
        <button class="add-to-cart">+</button>
      `;
    productCard.addEventListener('click', () => showModal(product));

    const addToCartButton = productCard.querySelector('.add-to-cart');
    addToCartButton.addEventListener('click', (event) => {
        event.stopPropagation();
        addToCart(product);
    });

    productContainer.appendChild(productCard);
}

function createDishPopularItemHTML(product) {
    const container = document.getElementById('products-Recents');
    const item = document.createElement('div');
    item.classList.add('restaurants-item');
    const img = document.createElement('img');
    img.src = product.imagePath;
    img.alt = `Imagen de ${product.name}`;
    const content = document.createElement('div');
    content.classList.add('restaurants-content');
    const title = document.createElement('h3');
    title.textContent = product.name;
    const details = document.createElement('span');
    details.innerHTML = `${product.normalPrice} | <span class="rating">${product.offerPrice}</span>`;
    content.appendChild(title);
    content.appendChild(details);
    item.appendChild(img);
    item.appendChild(content);
    item.addEventListener('click', () => showModal(product));
    container.appendChild(item);
}

const container = document.getElementById('products-Recents');

function enableCarouselScroll(containerSelector, leftButtonSelector, rightButtonSelector, scrollAmount = 300) {
    const container = document.querySelector(containerSelector);
    const leftButton = document.querySelector(leftButtonSelector);
    const rightButton = document.querySelector(rightButtonSelector);

    if (container && leftButton && rightButton) {
        leftButton.addEventListener('click', () => {
            container.scrollBy({ left: -scrollAmount, behavior: 'smooth' });
        });

        rightButton.addEventListener('click', () => {
            container.scrollBy({ left: scrollAmount, behavior: 'smooth' });
        });
    } else {
        console.error(`Error: No se encontraron elementos para ${containerSelector}, ${leftButtonSelector} o ${rightButtonSelector}`);
    }
}

enableCarouselScroll('#products-Recents', '#leftArrow', '#rightArrow');

const productContainer = document.getElementById("product-container");
const modal = document.getElementById("product-modal");
const closeModal = document.getElementById("close-modal");

const modalName = document.getElementById("modal-name");
const modalPrice = document.getElementById("modal-price");
const modalDescription = document.getElementById("modal-description");
const modalImage = document.getElementById("modal-image");

function showModal(product) {
    modalName.textContent = product.name;
    modalPrice.textContent = `$${product.normalPrice}.00 MX`;
    modalDescription.textContent = product.description;
    modalImage.src = product.imagePath;
    modal.classList.remove("hidden");

    const button = document.getElementById("add-to-cart");
    button.replaceWith(button.cloneNode(true));
    const newButton = document.getElementById("add-to-cart");
    newButton.addEventListener('click', () => addToCart(product));
}


closeModal.addEventListener("click", () => {
    modal.classList.add("hidden");
});

document.addEventListener("DOMContentLoaded", () => {
    const dropdownButton = document.querySelector(".dropdown-button");
    const dropdownMenu = document.querySelector(".dropdown-menu");

    dropdownButton.addEventListener("click", () => {
        dropdownMenu.style.display =
            dropdownMenu.style.display === "block" ? "none" : "block";
    });

    dropdownMenu.addEventListener("click", (event) => {
        if (event.target.tagName === "LI") {
            dropdownButton.innerHTML = `${event.target.textContent} <span class="arrow">▼</span>`;
            dropdownMenu.style.display = "none";
        }
    });

    document.addEventListener("click", (event) => {
        if (!dropdownButton.contains(event.target) && !dropdownMenu.contains(event.target)) {
            dropdownMenu.style.display = "none";
        }
    });
});

function ValidateJSON(userJSON)
{
    if (!userJSON) {
        console.warn("No user data found in localStorage.");
        window.location.href = "/login";
    }
}

function addToCart(product) {
    const quantity = parseInt(document.querySelector(".dropdown-button").textContent.trim());
    const specialInstructions = document.getElementById("special-instructions").value;
    let user = getUserID();

    const customerCart = {
        user: { id: user.id, name: user.name, email: user.email},
        dish: { id: product.id },
        quantity: quantity,
        costByProduct: product.normalPrice * quantity
    };

    fetch('/deliveryApp/restaurants/addDishToCar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(customerCart),
        credentials: "include"
    })
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                throw new Error(`HTTP status ${response.status}`);
            }
        })
        .then(result => {
            if (result === "Registro correcto") {
                ShowAlertOK("Producto agregado al carrito con éxito",
                    "Producto agregado al carrito")
                reloadItemsCart();
                modal.classList.add("hidden");
            } else {
                ShowAlertError();
            }
        })
        .catch(error => {
            ShowAlertError();
        });
}

async function reloadItemsCart() {
    try {
        let user = getUserID()
        const response = await fetch(`/shoppingCar/getQuantityItems?id=${user.id}`);
        if (!response.ok) {
            throw new Error(`Error fetching products: ${response.statusText}`);
        }

        const quantity = await response.json();

        const notificationSpan = document.querySelector(".cart-icon .notification");
        if (notificationSpan) {
            notificationSpan.textContent = quantity;
        }

    } catch (error) {
        console.error("Failed to fetch products:", error);
    }
}


document.addEventListener("DOMContentLoaded", () => {
    const menuSection = document.querySelector("#menu-section");
    const reviewsSection = document.querySelector("#view-reviews");
    const writeReviewSection = document.querySelector("#write-review");
    const optionButtons = document.querySelectorAll(".optionButtons");

    const viewMenuButton = optionButtons[0];
    const viewReviewsButton = optionButtons[1];
    const writeReviewButton = optionButtons[2];

    viewMenuButton.addEventListener("click", () => {
        toggleSections(menuSection);
        setActiveButton(viewMenuButton);
    });

    viewReviewsButton.addEventListener("click", () => {
        toggleSections(reviewsSection);
        setActiveButton(viewReviewsButton);
        let restaurantIdInt = getRestaurantID();
        loadReviews(restaurantIdInt);
    });

    writeReviewButton.addEventListener("click", () => {
        toggleSections(writeReviewSection);
        setActiveButton(writeReviewButton);
    });

    function toggleSections(activeSection) {
        [menuSection, reviewsSection, writeReviewSection].forEach((section) => {
            section.classList.add("hidden");
        });
        activeSection.classList.remove("hidden");
    }

    function setActiveButton(activeButton) {
        optionButtons.forEach((button) => button.classList.remove("active"));
        activeButton.classList.add("active");
    }

    async function loadReviews(restaurantId) {
        const reviewsList = document.getElementById("reviews-list");
        reviewsList.innerHTML = "";

        try {
            const response = await fetch(`http://localhost:8080/deliveryApp/restaurants/getReviews?id=${restaurantId}`);
            if (!response.ok) throw new Error("Error al cargar las reseñas");

            const reviews = await response.json();

            if (reviews.length === 0) {
                reviewsList.innerHTML = "<li>No hay reseñas disponibles para este restaurante.</li>";
                return;
            }

            reviews.forEach((review) => {
                const li = document.createElement("li");
                li.innerHTML = `
                    <span class="review-author">Usuario: ${review.userName}</span>
                    <span class="review-rating">${"★".repeat(review.rating)}</span>
                    <span class="review-description">${review.description}</span>
                `;
                reviewsList.appendChild(li);
            });
        } catch (error) {
            reviewsList.innerHTML = "<li>Error al cargar las reseñas.</li>";
        }
    }

    const reviewForm = document.getElementById("review-form");
    reviewForm.addEventListener("submit", (e) => {
        e.preventDefault();
        const rating = document.getElementById("review-rating").value;
        const description = document.getElementById("review-description").value;

        if (rating && description) {
            ShowAlertOK("Reseña enviada con existo",
                `Tu reseña fue enviada con ${rating} estrellas.`);
            reviewForm.reset();
        } else {
            alert("Por favor, completa todos los campos.");
        }
    });
});


document.getElementById('review-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const rating = document.getElementById('review-rating').value;
    const description = document.getElementById('review-description').value;

    if (!rating || !description) {
        alert('Por favor, complete todos los campos.');
        return;
    }

    let userID = getUserID().id
    let restaurantID = getRestaurantID()

    const reviewData = {
        description: description,
        rating: rating,
        user: { id: userID  },
        restaurant: { id: restaurantID }
    };

    fetch('/deliveryApp/restaurants/registerReviews', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(reviewData)
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Error al enviar la reseña');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Hubo un error al enviar la reseña');
        });
});


