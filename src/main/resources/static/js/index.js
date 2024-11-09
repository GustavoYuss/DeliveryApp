
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
    { name: "Sopas", icon: "/images/soup.png" },
    { name: "Vino", icon: "/images/wine.png" },
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

enableCarouselScroll('#categoryList', '#leftArrow', '#rightArrow');
enableCarouselScroll('.ads-carousel', '.arrow-left', '.arrow-right');
enableCarouselScroll('#recentRestaurants', '#arrow1', '#arrow2');
enableCarouselScroll('.recent-restaurants', '.left-arrow-rr', '.right-arrow-rr');
enableCarouselScroll('#Cheap', '#leftArrow2', '#rightArrow2');


const restaurants = [
    { name: 'Comida Mexicana', img: 'https://images.unsplash.com/photo-1562967914-01efa7e87832?ixlib=rb-1.2.1', desc: 'Hamburguesa | Comida Rápida', price: '$25.00 MXM' },
    { name: 'Pizza Italiana', img: 'https://images.unsplash.com/photo-1562967914-01efa7e87832?ixlib=rb-1.2.1', desc: 'Pepperoni | Extra Queso', price: '$50.00 MXM' },
    { name: 'Sushi Japonés', img: 'https://images.unsplash.com/photo-1553621042-f6e147245754', desc: 'Arroz | Pescado', price: '$70.00 MXM' },
    { name: 'Tacos al Pastor', img: 'https://images.unsplash.com/photo-1594007650641-4001a41df6b8', desc: 'Tortilla | Carne', price: '$15.00 MXM' },
    { name: 'Ensalada Fresca', img: 'https://images.unsplash.com/photo-1562967914-01efa7e87832?ixlib=rb-1.2.1', desc: 'Verduras | Vinagreta', price: '$30.00 MXM' },
    // Agrega más restaurantes según sea necesario...
];

let currentRow = 0;
const ROW_SIZE = 5;

const restaurantsTable = document.getElementById('restaurantsTable');
const loadMoreBtn = document.getElementById('loadMoreBtn');

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

function loadMoreRows() {
    const endRow = currentRow + ROW_SIZE;

    const row = document.createElement('div');
    row.classList.add('restaurant-row');

    for (let i = currentRow; i < endRow && i < restaurants.length; i++) {
        const restaurantItem = createRestaurantItem(restaurants[i]);
        row.appendChild(restaurantItem);
    }

    restaurantsTable.appendChild(row);
    currentRow += ROW_SIZE;

    if (currentRow >= restaurants.length) {
        loadMoreBtn.style.display = 'none';
    }
}
loadMoreRows();
loadMoreBtn.addEventListener('click', loadMoreRows);

function redirectToLogin() {
    window.location.href = "/login";
}




document.addEventListener("DOMContentLoaded", function () {
    fetch("/home/recent")
        .then(response => response.json())
        .then(data => {
            const recentSection = document.querySelector(".recent-restaurants");
            recentSection.innerHTML = data.map(restaurant => createRestaurantHTML(restaurant)).join('');
        });
});

function loadPopularRestaurants() {
    fetch("/home/popular")
        .then(response => response.json())
        .then(data => {
            const popularSection = document.querySelector("#recentRestaurants");
            popularSection.innerHTML = data.map(restaurant => createRestaurantHTML(restaurant)).join('');
        });
}
loadPopularRestaurants();

function loadCheapRestaurants() {
    fetch("/home/cheap")
        .then(response => response.json())
        .then(data => {
            const cheapSection = document.querySelector("#Cheap");
            cheapSection.innerHTML = data.map(restaurant => createRestaurantHTML(restaurant)).join('');
        });
}
loadCheapRestaurants();

function createRestaurantHTML(restaurant) {
    const openTimeFormatted = formatTime12Hour(restaurant.openTime);
    const closeTimeFormatted = formatTime12Hour(restaurant.closeTime);
    const isOpen = checkIfOpen(restaurant.openTime, restaurant.closeTime);

    return `
        <div class="restaurants-item">
            <img src="${restaurant.imagePath}" alt="${restaurant.nameRestaurant}">
            <div class="restaurants-content">
                <h3>${restaurant.nameRestaurant}</h3>
                <span>Apertura: ${openTimeFormatted} | Cierre: ${closeTimeFormatted}</span>
                <span class="rating">${restaurant.rating} ★</span>
                <span class="status" style="color: ${isOpen ? 'green' : 'red'};">
                    ${isOpen ? 'Abierto' : 'Cerrado'}
                </span>
            </div>
        </div>
    `;
}

function formatTime12Hour(time24) {
    const [hours, minutes] = time24.split(':').map(Number);
    const period = hours >= 12 ? 'PM' : 'AM';
    const hours12 = hours % 12 || 12; 
    return `${hours12}:${minutes.toString().padStart(2, '0')} ${period}`;
}

function checkIfOpen(openTime, closeTime) {
    const now = new Date();
    const currentHour = now.getHours();
    const currentMinute = now.getMinutes();

    const [openHour, openMinute] = openTime.split(':').map(Number);
    const [closeHour, closeMinute] = closeTime.split(':').map(Number);

    const isAfterOpen = (currentHour > openHour) || (currentHour === openHour && currentMinute >= openMinute);
    const isBeforeClose = (currentHour < closeHour) || (currentHour === closeHour && currentMinute < closeMinute);

    return isAfterOpen && isBeforeClose;
}

