let map;
let marker;
let userLat, userLng;
let currentAddress = { street: '', city: '', state: '' };

document.addEventListener("DOMContentLoaded", async () => {
    // Verificamos si hay una dirección guardada en localStorage
    const savedAddress = localStorage.getItem('selectedAddress');
    const savedLat = localStorage.getItem('selectedLat');
    const savedLon = localStorage.getItem('selectedLon');

    if (savedAddress && savedLat && savedLon) {
        // Si existe dirección guardada, la usamos
        userLat = parseFloat(savedLat);
        userLng = parseFloat(savedLon);

        // Mostramos la dirección guardada en la barra
        const locationLabel = document.getElementById("locationLabel");
        locationLabel.textContent = savedAddress;
    } else {
        // Si no hay nada guardado en localStorage, utilizamos la geolocalización actual
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(async function(position) {
                userLat = position.coords.latitude;
                userLng = position.coords.longitude;

                const data = await reverseGeocode(userLat, userLng);
                if (data && data.address) {
                    updateLocationLabelFromAddress(data.address);
                }
            }, function() {
                alert("No se pudo obtener la ubicación.");
            });
        } else {
            alert("La geolocalización no está disponible en este navegador.");
        }
    }
});

function showMap() {
    document.getElementById("mapModal").style.display = "flex";

    if (!map) {
        map = L.map('map').setView([userLat, userLng], 15);

        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            maxZoom: 19,
            attribution: '© OpenStreetMap contributors'
        }).addTo(map);

        marker = L.marker([userLat, userLng]).addTo(map);

        // Evento para manejar doble clic en el mapa
        map.on('dblclick', async (e) => {
            const lat = e.latlng.lat;
            const lon = e.latlng.lng;
            marker.setLatLng([lat, lon]);
            const reverseData = await reverseGeocode(lat, lon);
            if (reverseData && reverseData.address) {
                updateLocationLabelFromAddress(reverseData.address);

                // Guardamos la selección en localStorage
                const street = reverseData.address.road || reverseData.address.street || '';
                const city = reverseData.address.city || reverseData.address.town || reverseData.address.village || '';
                const state = reverseData.address.state || '';
                const formattedAddress = [street, city, state].filter(Boolean).join(', ');

                localStorage.setItem('selectedAddress', formattedAddress);
                localStorage.setItem('selectedLat', lat);
                localStorage.setItem('selectedLon', lon);
            }
        });
    }

    setTimeout(() => {
        map.invalidateSize();
    }, 100);
}

function closeMap() {
    document.getElementById("mapModal").style.display = "none";
}

function updateLocationLabelFromAddress(address) {
    const street = address.road || address.street || '';
    const city = address.city || address.town || address.village || '';
    const state = address.state || '';
    const formattedAddress = [street, city, state].filter(Boolean).join(', ');

    currentAddress = { street, city, state };

    const locationLabel = document.getElementById("locationLabel");
    locationLabel.textContent = formattedAddress;
}

async function reverseGeocode(lat, lon) {
    const url = `https://nominatim.openstreetmap.org/reverse?lat=${lat}&lon=${lon}&format=json&addressdetails=1`;
    const response = await fetch(url);
    const data = await response.json();
    return data;
}

async function searchPlaces(query) {
    const url = `https://nominatim.openstreetmap.org/search?format=json&addressdetails=1&q=${encodeURIComponent(query)}`;
    const response = await fetch(url);
    const data = await response.json();
    return data;
}

function selectPlaceFromList(place) {
    const lat = parseFloat(place.lat);
    const lon = parseFloat(place.lon);

    map.setView([lat, lon], 15);
    marker.setLatLng([lat, lon]);

    if (place.address) {
        updateLocationLabelFromAddress(place.address);
        const street = place.address.road || place.address.street || '';
        const city = place.address.city || place.address.town || place.address.village || '';
        const state = place.address.state || '';
        const formattedAddress = [street, city, state].filter(Boolean).join(', ');

        // Guardamos en localStorage
        localStorage.setItem('selectedAddress', formattedAddress);
        localStorage.setItem('selectedLat', lat);
        localStorage.setItem('selectedLon', lon);
    } else {
        reverseGeocode(lat, lon).then(data => {
            if (data && data.address) {
                updateLocationLabelFromAddress(data.address);
                const street = data.address.road || data.address.street || '';
                const city = data.address.city || data.address.town || data.address.village || '';
                const state = data.address.state || '';
                const formattedAddress = [street, city, state].filter(Boolean).join(', ');

                localStorage.setItem('selectedAddress', formattedAddress);
                localStorage.setItem('selectedLat', lat);
                localStorage.setItem('selectedLon', lon);
            }
        });
    }

    suggestionsList.style.display = "none";
    searchInput.value = [place.address?.road || place.display_name, place.address?.city, place.address?.state].filter(Boolean).join(', ');
}

// Buscar lugares al escribir en el input del mapa
const searchInput = document.getElementById("searchInput");
const suggestionsList = document.getElementById("searchSuggestions");

searchInput.addEventListener("input", async function() {
    const query = this.value.trim();
    if (query.length < 3) {
        suggestionsList.style.display = "none";
        return;
    }

    const results = await searchPlaces(query);
    suggestionsList.innerHTML = "";
    if (results.length > 0) {
        for (let place of results) {
            const li = document.createElement("li");
            li.textContent = place.display_name;
            li.addEventListener("click", () => {
                selectPlaceFromList(place);
            });
            suggestionsList.appendChild(li);
        }
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

function goToCar() {
    window.location.href = "/car";
}

function redirectToUserProfile() {
    window.location.href = "/userProfile";
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