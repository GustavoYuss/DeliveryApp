let map;
let autocomplete;
let marker;

// Inicializar el mapa y la búsqueda de lugares
function showMap() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            const lat = position.coords.latitude;
            const lng = position.coords.longitude;

            // Mostrar el modal
            document.getElementById("mapModal").style.display = "flex";

            // Crear el mapa
            map = new google.maps.Map(document.getElementById("map"), {
                center: { lat: lat, lng: lng },
                zoom: 15
            });

            // Añadir un marcador en la ubicación actual
            marker = new google.maps.Marker({
                position: { lat: lat, lng: lng },
                map: map
            });

            // Configurar el Autocomplete para la barra de búsqueda
            const searchInput = document.getElementById("searchInput");
            autocomplete = new google.maps.places.Autocomplete(searchInput);
            autocomplete.bindTo("bounds", map);

            // Cuando el usuario selecciona un lugar
            autocomplete.addListener("place_changed", onPlaceChanged);
        });
    } else {
        alert("La geolocalización no está disponible en este navegador.");
    }
}

// Función para manejar el lugar seleccionado
function onPlaceChanged() {
    const place = autocomplete.getPlace();

    if (!place.geometry || !place.geometry.location) {
        alert("No se encontró información del lugar seleccionado.");
        return;
    }

    // Centrar el mapa en el lugar seleccionado
    map.setCenter(place.geometry.location);
    map.setZoom(15);

    // Mover el marcador al nuevo lugar
    marker.setPosition(place.geometry.location);
    marker.setVisible(true);

    // Actualizar la etiqueta de ubicación en la barra de navegación
    const locationLabel = document.querySelector(".header-option .space-right");
    locationLabel.textContent = `Ubicación: ${place.name || place.formatted_address}`;
}

// Función para cerrar el modal
function closeMap() {
    document.getElementById("mapModal").style.display = "none";
}


function redirectToIndex(){
    window.location.href = "/home";
}

function redirectToLogin() {
    window.location.href = "/login";
}