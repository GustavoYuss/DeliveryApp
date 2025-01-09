let map;
let marker;
let userLat, userLng;
let currentAddress = { street: '', city: '', state: '' };

document.addEventListener("DOMContentLoaded", async () => {
    // Recuperar ubicación guardada
    const savedAddress = localStorage.getItem('selectedAddress');
    const savedLat = parseFloat(localStorage.getItem('selectedLat'));
    const savedLon = parseFloat(localStorage.getItem('selectedLon'));

    if (savedAddress && !isNaN(savedLat) && !isNaN(savedLon)) {
        userLat = savedLat;
        userLng = savedLon;
        document.getElementById("locationLabel").textContent = savedAddress;
    } else {
        // Intentar obtener ubicación actual
        try {
            const permission = await navigator.permissions.query({ name: 'geolocation' });
            if (permission.state === 'denied') {
                alert("Permiso de geolocalización denegado.");
                return;
            }
            navigator.geolocation.getCurrentPosition(
                async (position) => {
                    userLat = position.coords.latitude;
                    userLng = position.coords.longitude;
                    const data = await reverseGeocode(userLat, userLng);
                    if (data?.address) updateLocationLabelFromAddress(data.address);
                },
                (error) => {
                    console.error("Error al obtener la ubicación:", error.message);
                    alert("No se pudo obtener la ubicación.");
                }
            );
        } catch (error) {
            console.error("Error al verificar permisos:", error);
        }
    }
});

function showMap() {
    document.getElementById("mapModal").style.display = "flex";

    if (!map) {
        // Inicializar mapa
        map = L.map('map').setView([userLat || 0, userLng || 0], 15);
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            maxZoom: 19,
            attribution: '© OpenStreetMap contributors',
        }).addTo(map);
        marker = L.marker([userLat, userLng]).addTo(map);

        map.on('dblclick', async (e) => {
            const lat = e.latlng.lat;
            const lon = e.latlng.lng;
            updateMarker(lat, lon);
        });
    }

    setTimeout(() => {
        map.invalidateSize();
    }, 100);
}

function closeMap() {
    document.getElementById("mapModal").style.display = "none";
}

function updateMarker(lat, lon) {
    marker.setLatLng([lat, lon]);
    reverseGeocode(lat, lon).then((data) => {
        if (data?.address) {
            updateLocationLabelFromAddress(data.address);
            const formattedAddress = formatAddress(data.address);
            localStorage.setItem('selectedAddress', formattedAddress);
            localStorage.setItem('selectedLat', lat);
            localStorage.setItem('selectedLon', lon);
        }
    });
}

function updateLocationLabelFromAddress(address) {
    const formattedAddress = formatAddress(address);
    document.getElementById("locationLabel").textContent = formattedAddress;
    currentAddress = { ...address };
}

function formatAddress(address) {
    const street = address.road || address.street || '';
    const city = address.city || address.town || address.village || '';
    const state = address.state || '';
    return [street, city, state].filter(Boolean).join(', ');
}

async function reverseGeocode(lat, lon) {
    try {
        const url = `https://nominatim.openstreetmap.org/reverse?lat=${lat}&lon=${lon}&format=json&addressdetails=1`;
        const response = await fetch(url);
        if (!response.ok) throw new Error("Error al obtener datos de geocodificación inversa");
        return await response.json();
    } catch (error) {
        console.error("Error en reverseGeocode:", error);
    }
}

async function searchPlaces(query) {
    try {
        const url = `https://nominatim.openstreetmap.org/search?format=json&addressdetails=1&q=${encodeURIComponent(query)}`;
        const response = await fetch(url);
        if (!response.ok) throw new Error("Error al buscar lugares");
        return await response.json();
    } catch (error) {
        console.error("Error en searchPlaces:", error);
    }
}

function selectPlaceFromList(place) {
    const lat = parseFloat(place.lat);
    const lon = parseFloat(place.lon);
    updateMarker(lat, lon);

    const formattedAddress = formatAddress(place.address || {});
    document.getElementById("searchInput").value = formattedAddress;
    suggestionsList.style.display = "none";
}

const searchInput = document.getElementById("searchInput");
const suggestionsList = document.getElementById("searchSuggestions");

searchInput.addEventListener("input", async function () {
    const query = this.value.trim();
    if (query.length < 3) {
        suggestionsList.style.display = "none";
        return;
    }
    const results = await searchPlaces(query);
    suggestionsList.innerHTML = "";

    if (results?.length > 0) {
        results.forEach((place) => {
            const li = document.createElement("li");
            li.textContent = place.display_name;
            li.addEventListener("click", () => selectPlaceFromList(place));
            suggestionsList.appendChild(li);
        });
        suggestionsList.style.display = "block";
    } else {
        suggestionsList.style.display = "none";
    }
});


function redirectToIndex(){
    window.location.href = "/";
}

function redirectToLogin() {
    window.location.href = "/login";
}

function redirectToCustomerOrdersPage() {
    window.location.href = "/deliveryApp/customer/"
}

function goToCar() {
    window.location.href = "/deliveryApp/shoppingCar/";
}

function redirectToUserProfile() {

    const userJSON = localStorage.getItem('user');
    if (userJSON !== null) {
        try {
            const user = JSON.parse(userJSON);
            if (user && user.id) {
                window.location.href = `/deliveryApp/userProfile/showData?id=${user.id}`;
            } else {
                console.error("Invalid user data: Missing 'id'.");
            }
        } catch (error) {
            console.error("Failed to parse user JSON:", error);
        }
    } else {
        console.warn("No user data found in localStorage.");
    }
}


const menuBtn = document.getElementById("menu__btn");
const sideBar = document.querySelector(".sidebar");
const closeBtn = document.getElementById("close-sidebar-btn");

menuBtn.onclick = () => {
    sideBar.classList.toggle("visible");
};
closeBtn.onclick = () => {
    sideBar.classList.remove("visible");
};


let userJSON = null;
let user = null;

document.addEventListener("DOMContentLoaded", async function () {
    try {
        userJSON = localStorage.getItem("user");
        ValidateJSON(userJSON);
        user = JSON.parse(userJSON);

        const response = await fetch(`/deliveryApp/shoppingCar/getQuantityItems?id=${user.id}`);
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
});


function ValidateJSON(userJSON)
{
    if (!userJSON) {
        console.warn("No user data found in localStorage.");
        window.location.href = "/login";
        return;
    }
}


document.addEventListener("DOMContentLoaded", () => {
    const searchInput = document.getElementById("mainSearchInput");
    const resultsList = document.getElementById("searchResults");

    if (!searchInput || !resultsList) {
        console.error("No se encontraron los elementos necesarios en el DOM.");
        return;
    }

    searchInput.addEventListener("input", () => {
        const query = searchInput.value.trim();

        if (query === "") {
            resultsList.innerHTML = "";
            return;
        }

        fetch(`/search?query=${encodeURIComponent(query)}`)
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Error al obtener los datos.");
                }
                return response.json();
            })
            .then((data) => {
                resultsList.innerHTML = "";
                data.forEach((restaurant) => {
                    const li = document.createElement("li");
                    li.textContent = restaurant.name;
                    resultsList.appendChild(li);
                });
            })
            .catch((error) => {
                console.error("Error:", error);
                resultsList.innerHTML = "<li>Error al cargar los resultados.</li>";
            });
    });
});
