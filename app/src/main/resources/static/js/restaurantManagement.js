document.addEventListener("DOMContentLoaded", () => {
    const sidebarItems = document.querySelectorAll(".sidebar li");
    const sections = document.querySelectorAll(".section");

    sidebarItems.forEach(item => {
        item.addEventListener("click", () => {
            sidebarItems.forEach(i => i.classList.remove("active"));
            item.classList.add("active");
            sections.forEach(section => section.classList.remove("active"));
            const sectionId = item.getAttribute("data-section");
            document.getElementById(sectionId).classList.add("active");
        });
    });
});

const products = [
    {
        id: 1,
        name: "8 Galletas Surtidas",
        price: "$119.00",
        rating: "100%",
        reviews: 5,
        description: "Amar es compartir! Enciende su corazón con 8 Galletas surtidas por...",
        image: "https://images.unsplash.com/photo-1562967914-01efa7e87832?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2252&q=80"
    },
    {
        id: 1,
        name: "8 Galletas Surtidas",
        price: "$119.00",
        rating: "100%",
        reviews: 5,
        description: "Amar es compartir! Enciende su corazón con 8 Galletas surtidas por...",
        image: "https://images.unsplash.com/photo-1562967914-01efa7e87832?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2252&q=80"
    },
    {
        id: 1,
        name: "8 Galletas Surtidas",
        price: "$119.00",
        rating: "100%",
        reviews: 5,
        description: "Amar es compartir! Enciende su corazón con 8 Galletas surtidas por...",
        image: "https://images.unsplash.com/photo-1562967914-01efa7e87832?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2252&q=80"
    },
    {
        id: 1,
        name: "8 Galletas Surtidas",
        price: "$119.00",
        rating: "100%",
        reviews: 5,
        description: "Amar es compartir! Enciende su corazón con 8 Galletas surtidas por...",
        image: "https://images.unsplash.com/photo-1562967914-01efa7e87832?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2252&q=80"
    },
    {
        id: 1,
        name: "8 Galletas Surtidas",
        price: "$119.00",
        rating: "100%",
        reviews: 5,
        description: "Amar es compartir! Enciende su corazón con 8 Galletas surtidas por...",
        image: "https://images.unsplash.com/photo-1562967914-01efa7e87832?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2252&q=80"
    },
    {
        id: 1,
        name: "8 Galletas Surtidas",
        price: "$119.00",
        rating: "100%",
        reviews: 5,
        description: "Amar es compartir! Enciende su corazón con 8 Galletas surtidas por...",
        image: "https://images.unsplash.com/photo-1562967914-01efa7e87832?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2252&q=80"
    },
];

const productContainer = document.getElementById("product-container");

products.forEach(product => {
    const productCard = document.createElement("div");
    productCard.classList.add("product-card");

    productCard.innerHTML = `
    <div class="product-info">
      <h2>${product.name}</h2>
      <div class="price-rating">
        <span class="price">${product.price}</span>
        <span class="rating">• ${product.rating} (${product.reviews})</span>
      </div>
      <p>${product.description}</p>
    </div>
    <img src="${product.image}" alt="${product.name}">
    <button class="add-to-cart">+</button>
  `;

    productContainer.appendChild(productCard);
});