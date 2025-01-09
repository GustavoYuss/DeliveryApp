let userJSON = null;
let user = null;

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


document.addEventListener("DOMContentLoaded", async function () {
    try {
        userJSON = localStorage.getItem("user");
        ValidateJSON(userJSON);
        user = JSON.parse(userJSON);

        const response = await fetch(`/deliveryApp/shoppingCar/getItems?id=${user.id}`);
        if (!response.ok) {
            throw new Error(`Error fetching products: ${response.statusText}`);
        }

        const customerCar = await response.json();
        renderProducts(customerCar);

        const productsLabel = document.querySelector("#products");
        const totalPriceLabel = document.querySelector("#totalPrice");
        const shippingLabel = document.querySelector("#shippingLabel");

        let totalProductCost = 0;
        let totalProductCount = 0;
        let totalShippingCost = 0;

        customerCar.forEach(product => {
            totalProductCost += product.costByProduct;
            totalProductCount++;
        });

        const SHIPPING_COST_PER_PRODUCT = 50;
        totalShippingCost = totalProductCount * SHIPPING_COST_PER_PRODUCT;

        productsLabel.textContent = `Productos (${totalProductCount}): $${totalProductCost.toFixed(2)}`;
        shippingLabel.textContent = `Envíos (${totalProductCount}): $${totalShippingCost.toFixed(2)}. Nota: Se cobran $50 por producto`;
        totalPriceLabel.textContent = `$${(totalProductCost + totalShippingCost).toFixed(2)}`;

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

function renderProducts(customerCarItems) {
    const container = document.getElementById("products-container");
    container.innerHTML = "";

    customerCarItems.forEach(customerCar => {
        const productHTML = `
            <div class="product-item">
                <div class="cart-icon">
                    <h1>${customerCar.quantity}</h1>
                </div>
                <img src="${customerCar.dish.imagePath}" alt="${customerCar.name}">
                <div class="product-info">
                    <p>${customerCar.dish.name}</p>
                    <div class="product-actions">
                        <a class="decreaseItem-Button" data-id="${customerCar.dish.id}">Quitar Elemento</a> | 
                        <a class="deleteItem-Button" data-id="${customerCar.dish.id}">Eliminar del carrito</a> | 
                        <a class="showItem-Button" data-id="${customerCar.dish.id}">Ver Detalles</a>
                    </div>
                </div>
                <div class="product-price">$ ${customerCar.costByProduct}.00</div>
            </div>
        `;

        container.insertAdjacentHTML("beforeend", productHTML);
    });

    container.querySelectorAll(".decreaseItem-Button").forEach(button => {
        button.addEventListener("click", () => {
            const productId = button.dataset.id;
            decreaseItemFromCar(customerCarItems.find(item => item.dish.id == productId));
        });
    });

    container.querySelectorAll(".deleteItem-Button").forEach(button => {
        button.addEventListener("click", () => {
            const productId = button.dataset.id;
            deleteItemFromCar(customerCarItems.find(item => item.dish.id == productId));
        });
    });

    container.querySelectorAll(".showItem-Button").forEach(button => {
        button.addEventListener("click", () => {
            const productId = button.dataset.id;
            showItem(customerCarItems.find(item => item.dish.id == productId));
        });
    });
}


function deleteItemFromCar(item) {
    fetch(`/deliveryApp/shoppingCar/RemoveDishCart?id=${item.id}`, {
        method: 'Delete',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error(`HTTP status ${response.status}`);
            }
        })
        .then(result => {
            if (result) {
                ShowAlertOK(
                    'Producto Eliminado',
                    'Se elimino correctamente el producto del carrito de compras');
            } else {
                ShowAlertError();
            }
        })
        .catch(error => {
            ShowAlertError();
        });
}

function decreaseItemFromCar(item) {
    fetch(`/deliveryApp/shoppingCar/DecreaseDishCart?id=${item.id}`,{
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error(`HTTP status ${response.status}`);
            }
        })
        .then(result => {
            if (result) {
                location.reload(true);
            } else {
                ShowAlertError();
            }
        })
        .catch(error => {
            ShowAlertError();
        });
}

document.addEventListener("DOMContentLoaded", () => {
    const checkoutButton = document.querySelector(".checkout-button");
    checkoutButton.addEventListener("click", () => {
        fetch(`/deliveryApp/shoppingCar/makeOrder`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            credentials: 'include',
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error(`HTTP status ${response.status}`);
                }
            })
            .then(result => {
                if (result) {
                    ShowAlertOK(
                        'Producto Eliminado',
                        'Se elimino correctamente el producto del carrito de compras');
                } else {
                    ShowAlertError();
                }
            })
            .catch(error => {
                ShowAlertError();
            });
    });
});
