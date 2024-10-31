
// Contenido dinamico del filtro de categorias
const categories = [
    { name: "Súper", icon: "/images/Super.png" },
    { name: "Pizza", icon: "/images/piza.png" },
    { name: "Sushi", icon: "/images/sushi.png" },
    { name: "Hamburguesa", icon: "/images/burger.png" },
    { name: "Alitas", icon: "/images/chicken.png" },
    { name: "Comida rápida", icon: "/images/fries.png" },
    { name: "Postres", icon: "/images/cookies.png" },
    { name: "Mexicana", icon: "/images/taco.png" },
    { name: "Saludable", icon: "/images/salad.png" },
    { name: "Sándwich", icon: "/images/sandwich.png" },
    { name: "Italiana", icon: "/images/pasta.png" },
    { name: "Café", icon: "/images/coffee-cup.png" },
    { name: "Americana", icon: "/images/hot-dog.png" },
    { name: "Asiática", icon: "/images/ramen.png" },
    { name: "Panadería", icon: "/images/pan.png" },
    { name: "Helado", icon: "/images/ice-cream.png" },
    { name: "China", icon: "/images/china.png" },
    { name: "Sopas", icon: "/images/ramen.png" },
    { name: "Vino", icon: "/images/pan.png" },
    { name: "Vegana", icon: "/images/Vegetarian.png" },
];

const categoryList = document.getElementById('categoryList');

categories.forEach(category => {
    const item = document.createElement('div');
    item.classList.add('category-item');

    const img = document.createElement('img');
    img.src = category.icon;
    img.alt = category.name;

    const span = document.createElement('span');
    span.textContent = category.name;

    item.appendChild(img);
    item.appendChild(span);
    categoryList.appendChild(item);
});

// Desplazamiento del categorias de izquierda a derecha
const leftArrow = document.getElementById('leftArrow');
const rightArrow = document.getElementById('rightArrow');

leftArrow.addEventListener('click', () => {
    categoryList.scrollBy({ left: -300, behavior: 'smooth' });
});

rightArrow.addEventListener('click', () => {
    categoryList.scrollBy({ left: 300, behavior: 'smooth' });
});


// Desplazamiento del anuncios de izquierda a derecha
const carousel = document.querySelector('.ads-carousel');
const arrowLeft = document.querySelector('.arrow-left');
const arrowRight = document.querySelector('.arrow-right');
const scrollAmount = 300;

arrowRight.addEventListener('click', () => {
    carousel.scrollBy({ left: scrollAmount, behavior: 'smooth' });
});

arrowLeft.addEventListener('click', () => {
    carousel.scrollBy({ left: -scrollAmount, behavior: 'smooth' });
});




// Lista simulada de restaurantes (puedes reemplazarlo con datos dinámicos).
const restaurants = [
    { name: 'Comida Mexicana', img: 'https://images.unsplash.com/photo-1562967914-01efa7e87832?ixlib=rb-1.2.1', desc: 'Hamburguesa | Comida Rápida', price: '$25.00 MXM' },
    { name: 'Pizza Italiana', img: 'https://images.unsplash.com/photo-1562967914-01efa7e87832?ixlib=rb-1.2.1', desc: 'Pepperoni | Extra Queso', price: '$50.00 MXM' },
    { name: 'Sushi Japonés', img: 'https://images.unsplash.com/photo-1553621042-f6e147245754', desc: 'Arroz | Pescado', price: '$70.00 MXM' },
    { name: 'Tacos al Pastor', img: 'https://images.unsplash.com/photo-1594007650641-4001a41df6b8', desc: 'Tortilla | Carne', price: '$15.00 MXM' },
    { name: 'Ensalada Fresca', img: 'https://images.unsplash.com/photo-1562967914-01efa7e87832?ixlib=rb-1.2.1', desc: 'Verduras | Vinagreta', price: '$30.00 MXM' },
    // Agrega más restaurantes según sea necesario...
];

let currentRow = 0;
const ROW_SIZE = 5; // Número de restaurantes por fila

// Referencias a elementos del DOM
const restaurantsTable = document.getElementById('restaurantsTable');
const loadMoreBtn = document.getElementById('loadMoreBtn');

// Función para crear un item de restaurante
function createRestaurantItem(restaurant) {
    const item = document.createElement('div');
    item.classList.add('restaurant-item');
    item.innerHTML = `
        <img src="${restaurant.img}" alt="${restaurant.name}">
        <div class="restaurant-content">
            <h3>${restaurant.name}</h3>
            <span>${restaurant.price}</span>
            <p>${restaurant.desc}</p>
        </div>
    `;
    return item;
}

// Función para cargar más filas de restaurantes
function loadMoreRows() {
    const endRow = currentRow + ROW_SIZE;

    const row = document.createElement('div');
    row.classList.add('restaurant-row');

    // Crear los items para esta fila
    for (let i = currentRow; i < endRow && i < restaurants.length; i++) {
        const restaurantItem = createRestaurantItem(restaurants[i]);
        row.appendChild(restaurantItem);
    }

    restaurantsTable.appendChild(row);
    currentRow += ROW_SIZE;

    // Ocultar botón si ya no hay más restaurantes
    if (currentRow >= restaurants.length) {
        loadMoreBtn.style.display = 'none';
    }
}

// Cargar la primera fila al inicio
loadMoreRows();

// Evento para cargar más filas al hacer clic en el botón
loadMoreBtn.addEventListener('click', loadMoreRows);


