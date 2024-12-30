document.addEventListener("DOMContentLoaded", function () {
    const restaurantId = 1;
    console.log("Archivo JavaScript cargado.");

    fetch(`/getDishesByRestaurant?id=${restaurantId}`, {
        method: "GET",
        credentials: "include",
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Error al obtener los datos del servidor.");
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            data.map(product => createDishItemHTML(product)).join('');
        })
        .catch(error => console.error("Error:", error));
});

document.addEventListener("DOMContentLoaded", function () {
    const restaurantId = 1;
    console.log("Archivo JavaScript cargado.");

    fetch(`/getDishesByRestaurant?id=${restaurantId}`, {
        method: "GET",
        credentials: "include",
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Error al obtener los datos del servidor.");
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            data.map(product => createDishPopularItemHTML(product)).join('');
        })
        .catch(error => console.error("Error:", error));
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

function addToCart(product) {
    console.log(`Producto añadido al carrito: ${product.name}`);
}
