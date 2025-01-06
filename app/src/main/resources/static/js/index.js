const categories = [
    { id: 1, name: "Súper", icon: "/images/Super.png" },
    { id: 2, name: "Pizza", icon: "/images/piza.png" },
    { id: 3, name: "Sushi", icon: "/images/sushi.png" },
    { id: 4, name: "Hamburguesa", icon: "/images/burger.png" },
    { id: 5, name: "Alitas", icon: "/images/chicken.png" },
    { id: 6, name: "Comida rápida", icon: "/images/fries.png" },
    { id: 7, name: "Postres", icon: "/images/cookies.png" },
    { id: 8, name: "Mexicana", icon: "/images/taco.png" },
    { id: 9, name: "Saludable", icon: "/images/salad.png" },
    { id: 10, name: "Sándwich", icon: "/images/sandwich.png" },
    { id: 11, name: "Italiana", icon: "/images/pasta.png" },
    { id: 12, name: "Café", icon: "/images/coffee-cup.png" },
    { id: 13, name: "Americana", icon: "/images/hot-dog.png" },
    { id: 14, name: "Asiática", icon: "/images/ramen.png" },
    { id: 15, name: "Panadería", icon: "/images/pan.png" },
    { id: 16, name: "Helado", icon: "/images/ice-cream.png" },
    { id: 17, name: "China", icon: "/images/china.png" },
    { id: 18, name: "Sopas", icon: "/images/soup.png" },
    { id: 19, name: "Vino", icon: "/images/wine.png" },
    { id: 20, name: "Vegana", icon: "/images/Vegetarian.png" },
];

const restaurants = [
    { name: 'Comida Mexicana', img: 'https://images.unsplash.com/photo-1562967914-01efa7e87832?ixlib=rb-1.2.1', desc: 'Hamburguesa | Comida Rápida', price: '$25.00 MXM' },
    { name: 'Pizza Italiana', img: 'https://images.unsplash.com/photo-1562967914-01efa7e87832?ixlib=rb-1.2.1', desc: 'Pepperoni | Extra Queso', price: '$50.00 MXM' },
    { name: 'Sushi Japonés', img: 'https://images.unsplash.com/photo-1553621042-f6e147245754', desc: 'Arroz | Pescado', price: '$70.00 MXM' },
    { name: 'Tacos al Pastor', img: 'https://images.unsplash.com/photo-1594007650641-4001a41df6b8', desc: 'Tortilla | Carne', price: '$15.00 MXM' },
    { name: 'Ensalada Fresca', img: 'https://images.unsplash.com/photo-1562967914-01efa7e87832?ixlib=rb-1.2.1', desc: 'Verduras | Vinagreta', price: '$30.00 MXM' },
];

const adsData = [
    {
        title: "McTrio Cuarto de libra con Salsa Cheddar y un complemento sin costo",
        description: "¡Cheddar Nights!",
        buttonText: "Ordena aquí",
        imageUrl: "https://image.freepik.com/psd-gratis/plantilla-promocional-deliciosa-hamburguesa-o-restaurante-redes-sociales_256771-31.jpg",
        altText: "McTrio con Salsa Cheddar"
    },
    {
        title: "Disfruta de la Big Mac con Papas Grandes",
        description: "¡La clásica que nunca falla!",
        buttonText: "Pídela ahora",
        imageUrl: "https://th.bing.com/th/id/OIP.7GycFitRKb6nybSeMWY4WgHaHa?rs=1&pid=ImgDetMain",
        altText: "Big Mac con Papas"
    },
    {
        title: "¡Nuevas McNuggets sabor BBQ!",
        description: "El sabor que estabas esperando",
        buttonText: "Prueba ya",
        imageUrl: "https://th.bing.com/th/id/OIP.aum_5lnaH_UXIWE76GAetgHaFo?rs=1&pid=ImgDetMain",
        altText: "McNuggets BBQ"
    },
    {
        title: "Desayunos McMuffin con Café incluido",
        description: "Empieza el día con energía",
        buttonText: "Descubre más",
        imageUrl: "https://th.bing.com/th/id/OIP.AfqBvdmdAPvh4ShFwM6BNQHaHa?rs=1&pid=ImgDetMain",
        altText: "Desayuno McMuffin"
    },
    {
        title: "Helados Sundae: Chocolate o Caramelo",
        description: "¡El postre perfecto para cualquier momento!",
        buttonText: "Elige tu favorito",
        imageUrl: "https://th.bing.com/th/id/OIP.61qyDGXrOX95LkDi34eG4QHaHa?w=768&h=768&rs=1&pid=ImgDetMain",
        altText: "Sundae Chocolate o Caramelo"
    },
    {
        title: "Nuevo McWrap con Pollo Crispy",
        description: "Ligero, delicioso y saludable",
        buttonText: "Prueba uno hoy",
        imageUrl: "https://freshcore.com/wp-content/uploads/2018/08/McWrap-Chicken-Bacon.jpg",
        altText: "McWrap Pollo Crispy"
    },
    {
        title: "Combo Familiar con Pizza McHouse",
        description: "¡Para compartir con los tuyos!",
        buttonText: "Ordena ahora",
        imageUrl: "https://static.expressodelivery.com.br/imagens/banners/322729/Expresso-Delivery_2155f32966c0f3a01832a9d9f6e9566e.jpg",
        altText: "Combo Familiar McHouse"
    }
];

console.log("Contenido recibido de authenticatedUser en bruto:", authenticatedUser);

if (authenticatedUser && authenticatedUser.id && authenticatedUser.name) {
    localStorage.setItem('user', JSON.stringify(authenticatedUser));
    console.log("Usuario guardado en localStorage:", authenticatedUser);
} else {
    console.error("No se pudo obtener el usuario autenticado correctamente");
}


categories.forEach(category => {
    const categoryList = document.getElementById('categoryList');
    const item = document.createElement('div');
    item.classList.add('category-item');

    const img = document.createElement('img');
    img.src = category.icon;
    img.alt = category.name;

    const span = document.createElement('span');
    span.textContent = category.name;

    item.appendChild(img);
    item.appendChild(span);

    item.addEventListener('click', () => handleCategoryClick(category.id))
    categoryList.appendChild(item);
});

function handleCategoryClick(categoryId) {
    fetch(`/restaurants?category=${categoryId}`, {
        method: "GET",
        credentials: "include",
    })
        .then((response) => {
            if (!response.ok) {
                throw new Error("Error al obtener los restaurantes");
            }
            return response.json();
        })
        .then(restaurants => {
            document.getElementById("mas-bara").style.display = "none";
            document.getElementById("mas-puntuadas").style.display = "none";
            document.getElementById("recientes").style.display = "none";
            const restaurantsTable = document.querySelector("#restaurantsTable");
            restaurantsTable.innerHTML = "";

            if (restaurants.length != 0) {
                restaurantsTable.innerHTML = restaurants.map(restaurant => createRestaurantHTML(restaurant)).join('');
            }
            else
            {
                restaurantsTable.innerHTML = "<p>No se encontraron restaurantes para esta categoría.</p>";
                restaurantsTable.style.display = "block";
            }

        })
        .catch((error) => {
            alert("Hubo un error al obtener los restaurantes. Inténtalo de nuevo.");
        });
}

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

document.addEventListener("DOMContentLoaded", function () {
    fetch("/recent")
        .then(response => response.json())
        .then(data => {
            const recentSection = document.querySelector(".recent-restaurants");
            recentSection.innerHTML = data.map(restaurant => createRestaurantHTML(restaurant)).join('');
        });
});

document.addEventListener("DOMContentLoaded", function () {
    fetch("/popular")
        .then(response => response.json())
        .then(data => {
            const popularSection = document.querySelector("#recentRestaurants");
            popularSection.innerHTML = data.map(restaurant => createRestaurantHTML(restaurant)).join('');
        });
});

document.addEventListener("DOMContentLoaded", function () {
    fetch("/cheap")
        .then(response => response.json())
        .then(data => {
            const cheapSection = document.querySelector("#Cheap");
            cheapSection.innerHTML = data.map(restaurant => createRestaurantHTML(restaurant)).join('');
        });
});

document.addEventListener("DOMContentLoaded", function () {
    fetch("/cheap")
        .then(response => response.json())
        .then(data => {
            const restaurantSection = document.querySelector("#restaurantsTable");

            restaurantSection.innerHTML = data.map(restaurant => createRestaurantHTML(restaurant)).join('');
        });
});

function createRestaurantHTML(restaurant) {
    const openTimeFormatted = formatTime12Hour(restaurant.openTime);
    const closeTimeFormatted = formatTime12Hour(restaurant.closeTime);
    const isOpen = checkIfOpen(restaurant.openTime, restaurant.closeTime);

    return `
        <div class="restaurants-item" data-id="${restaurant.id}">
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

document.addEventListener('click', function (event) {
    const restaurantItem = event.target.closest('.restaurants-item');
    if (restaurantItem) {
        const restaurantId = restaurantItem.getAttribute('data-id');
        if (restaurantId) {
            window.location.href = `/deliveryApp/restaurants/showDetails?id=${restaurantId}`;
        }
    }
});

document.addEventListener("DOMContentLoaded", () => {
    const adsContainer = document.getElementById("ads");
    const createAd = ({ title, description, buttonText, imageUrl, altText }) => {
        const adDiv = document.createElement("div");
        adDiv.className = "ad";

        const adContent = `
            <div class="ad-content">
                <h2>${title}</h2>
                <p>${description}</p>
                <button>${buttonText}</button>
            </div>
            <div class="ad-image">
                <img src="${imageUrl}" alt="${altText}">
            </div>
        `;
        adDiv.innerHTML = adContent;
        return adDiv;
    };
    adsData.forEach(adData => {
        const adElement = createAd(adData);
        adsContainer.appendChild(adElement);
    });
});
