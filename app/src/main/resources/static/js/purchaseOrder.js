let map;
let marker;
let userLat, userLng;
let currentAddress = { street: '', city: '', state: '' };

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

function ShowAlertError() {
    Swal.fire({
        icon: "error",
        title: 'Error de red',
        text: 'No fue posible conectar con el servidor. Por favor, verifica tu conexión e inténtalo nuevamente.'
    });
}

document.addEventListener("DOMContentLoaded", () => {
    const checkoutButton = document.querySelector(".checkout-button");

    checkoutButton.addEventListener("click", () => {
        const address = document.getElementById("locationLabel").innerText;
        const data = {
            userID: getUserID().id,
            address: address,
            payment: {
                cardName: cardDetails.cardName,
                cardNumber: cardDetails.cardNumber,
                cvv: cardDetails.cvv,
                expirationDate: cardDetails.expirationDate,
                idUser: {
                    id: getUserID().id
                }
            }
        };

        fetch(`/deliveryApp/shoppingCar/makeOrder`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            credentials: 'include',
            body: JSON.stringify(data)
        })
            .then(response => {
                if (response.ok) {
                    cleanShoppingCar(getUserID().id)
                    return response.text();
                } else {
                    throw new Error(`HTTP status ${response.status}`);
                }
            })
            .then(result => {
                if (result === "Orden creada exitosamente") {
                    ShowAlertOK(
                        'Orden creada exitosamente',
                        'Se registro correctamente la orden');
                } else {
                    ShowAlertError();
                }
            })
            .catch(error => {
                ShowAlertError();
            });
    });
});


document.addEventListener("DOMContentLoaded", async function () {
    try {
        const userJSON = localStorage.getItem("user");
        if (!userJSON) {
            throw new Error("User not found in localStorage");
        }
        const user = JSON.parse(userJSON);

        const response = await fetch(`/deliveryApp/shoppingCar/getItems?id=${user.id}`);
        if (!response.ok) {
            throw new Error(`Error fetching products: ${response.statusText}`);
        }

        const customerCar = await response.json();
        if (!Array.isArray(customerCar) || customerCar.length === 0) {
            throw new Error("No products found in the cart.");
        }
        
        const productsLabel = document.getElementById("count");
        const totalPriceLabel = document.getElementById("costoEnvio");
        const shippingLabel = document.getElementById("Total");
        const subtotal = document.getElementById("subtotal");

        let totalProductCost = 0;
        let totalProductCount = 0;
        let totalShippingCost = 0;

        customerCar.forEach(product => {
            totalProductCost += product.costByProduct;
            totalProductCount++;
        });

        const SHIPPING_COST_PER_PRODUCT = 50;
        totalShippingCost = totalProductCount * SHIPPING_COST_PER_PRODUCT;

        productsLabel.textContent = `${totalProductCount} Articulo(s)`;
        subtotal.textContent = `$${totalProductCost.toFixed(2)}`;
        shippingLabel.textContent = `$${(totalProductCost + totalShippingCost).toFixed(2)} MX`;
        totalPriceLabel.textContent = `$ ${totalShippingCost.toFixed(2)}.00`;

    } catch (error) {
        console.error("Failed to fetch products:", error);
    }
});


document.addEventListener("DOMContentLoaded", async () => {
    const savedAddress = localStorage.getItem('selectedAddress');
    const savedLat = parseFloat(localStorage.getItem('selectedLat'));
    const savedLon = parseFloat(localStorage.getItem('selectedLon'));

    if (savedAddress && !isNaN(savedLat) && !isNaN(savedLon)) {
        userLat = savedLat;
        userLng = savedLon;
        document.getElementById("locationLabel").textContent = "   " + savedAddress;
    } else {
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
    document.getElementById("locationLabel").textContent = "   " + formattedAddress;
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

function ValidateJSON(userJSON)
{
    if (!userJSON) {
        console.warn("No user data found in localStorage.");
        window.location.href = "/login";
    }
}

function getUserID() {
    let userJSON = localStorage.getItem("user");
    ValidateJSON(userJSON);
    return JSON.parse(userJSON);
}

function showCardForm() {
    document.getElementById("addPaymentButton").style.display = "none";
    document.getElementById("cardForm").style.display = "block";
}

function cancelCardForm() {
    document.getElementById("cardForm").style.display = "none";
    document.getElementById("addPaymentButton").style.display = "block";
}

let cardDetails = {};

function submitCardForm(event) {
    event.preventDefault();

    const cardNumber = document.getElementById("cardNumber").value;
    const expirationDate = document.getElementById("expirationDate").value;
    const cvv = document.getElementById("cvv").value;
    const nameOnCard = document.getElementById("nameOnCard").value;

    if(validateDateFormat(expirationDate) === 2) {
        alert("Fecha incorrecta, sigaa el formato YYYY-MM-DD");
        return;
    }

    if (cardNumber && expirationDate && cvv && nameOnCard) {

        cardDetails = {
            cardName: nameOnCard,
            cardNumber: cardNumber,
            cvv: cvv,
            expirationDate: expirationDate
        };

        document.getElementById("cardNumberDisplay").innerText =
            "**** **** **** " + cardNumber.slice(-4);
        document.getElementById("cardNameDisplay").innerText = nameOnCard;

        document.getElementById("cardForm").style.display = "none";
        document.getElementById("cardItem").style.display = "flex";

        const checkoutButton = document.getElementById("checkoutButton");
        checkoutButton.disabled = false;
        alert("Tarjeta guardada correctamente");
    }
}

function editCard() {
    const currentCardNumber = document
        .getElementById("cardNumberDisplay")
        .innerText.replace(/\D/g, "")
        .padStart(16, "*");
    const currentNameOnCard = document.getElementById("cardNameDisplay").innerText;

    document.getElementById("cardNumber").value = currentCardNumber;
    document.getElementById("nameOnCard").value = currentNameOnCard;

    document.getElementById("cardItem").style.display = "none";
    document.getElementById("cardForm").style.display = "block";
}

function redirectToShoppingCar(){
    window.location.href = "/deliveryApp/shoppingCar/"
}

function cleanShoppingCar(userId)
{
    fetch(`/deliveryApp/shoppingCar/cleanShoppingCar?id=${userId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
    })
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                throw new Error(`HTTP status ${response.status}`);
            }
        })
        .catch(error => {
            ShowAlertError();
        });
}

function validateDateFormat(input) {
    const dateRegex = /^\d{4}-\d{2}-\d{2}$/; // Regex para validar formato YYYY-MM-DD
    const errorMessage = document.getElementById("error-message");

    if (dateRegex.test(input)) {
        const [year, month, day] = input.split('-').map(Number);
        const date = new Date(year, month - 1, day);
        if (
            date.getFullYear() === year &&
            date.getMonth() === month - 1 &&
            date.getDate() === day
        ) {
            return 1;
        }
    }
    return 2;
}

