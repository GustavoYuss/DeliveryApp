function redirectToIndex() {
    window.location.href = "/";
}

let currentPedidoId = null;

function openStatusForm(event, pedidoId) {
    event.stopPropagation();
    currentPedidoId = pedidoId;
    const modal = document.getElementById("statusModal");
    modal.style.display = "flex";
}

function closeStatusForm() {
    const modal = document.getElementById("statusModal");
    modal.style.display = "none";
}

async function changeStatus(newStatus) {
    const pedidoItems = document.querySelectorAll(".pedido-item");
    /*pedidoItems.forEach((item) => {
        const id = item.querySelector(".pedido-id").textContent;
        if (id === currentPedidoId) {
            item.querySelector(".pedido-status").textContent = newStatus;
        }
    });*/
    const response = await fetch(`http://localhost:8080/updateStatusOrder?idStatus=${newStatus}&idOrder=${currentPedidoId}`);
    if (!response.ok) {
        throw new Error(`Error en la solicitud: ${response.status}`);
    }
    location.reload();
}

async function openSidebar(event) {
    const clickedElement = event.currentTarget;
    const idOrder = clickedElement.querySelector('.pedido-id').textContent.trim();
    const userName = clickedElement.querySelector('.user-name').textContent.trim();
    const orderDate = clickedElement.querySelector('.pedido-time').textContent.trim();

    document.querySelector(".sidebar2-title").textContent = `Pedido ${idOrder}`;
    document.querySelector(".user-name").textContent = userName;
    document.querySelector(".sidebar2-date").textContent = orderDate;

    try {
        const response = await fetch(`http://localhost:8080/deliveryApp/customer/getDishesFromOrder?idOrder=${idOrder}`);
        if (!response.ok) {
            throw new Error(`Error en la solicitud: ${response.status}`);
        }

        const dishItems = await response.json();

        const orderDetailsContainer = document.querySelector(".order-details");
        orderDetailsContainer.innerHTML = "";

        dishItems.forEach((item) => {
            const orderItem = document.createElement("div");
            orderItem.className = "order-item";

            const amount = item.amount || "N/A";
            const dishName = item.dish?.name || "Platillo desconocido";
            const price = item.dish?.normalPrice != null ? `MX$${item.dish.normalPrice.toFixed(2)} C/U` : "Precio no disponible";

            if(dishName !== "Platillo desconocido")
            {
                orderItem.innerHTML = `
                    <span>${amount}</span>
                    <p>${dishName}</p>
                    <p class="item-price">${price}</p>
                `;
                orderDetailsContainer.appendChild(orderItem);
            }
        });

        const sidebar = document.getElementById("sidebar2");
        sidebar.style.display = "block";
    } catch (error) {
        console.error("Error al obtener los detalles del pedido:", error);
    }
}

function closeSidebar() {
    const sidebar = document.getElementById("sidebar2");
    sidebar.style.display = "none";
}

document.addEventListener("DOMContentLoaded", () => {
    const today = new Date().toISOString().split("T")[0];
    document.querySelectorAll(".filter-date").forEach(input => input.value = today);

    fetch('/deliveryApp/customer/last-update')
        .then(response => {
            if (!response.ok) {
                throw new Error("Error al obtener la última actualización");
            }
            return response.text();
        })
        .then(data => {
            document.querySelector('.last-update').textContent = `Última actualización: ${data}`;
        })
        .catch(error => {
            console.error(error);
            document.querySelector('.last-update').textContent = "Error al obtener la última actualización";
        });
});

function cancelOrder(orderId) {
    if (confirm("¿Estás seguro de que deseas cancelar este pedido?")) {
        fetch(`/cancelOrder/${orderId}`, {
            method: "POST",
        })
            .then(response => response.json())
            .then(data => {
                alert("Pedido cancelado con éxito");
                location.reload();
            })
            .catch(error => {
                alert("Error al cancelar el pedido");
            });
    }
}


/*
function fetchFilteredOrders() {
    const startDate = document.querySelector(".filter-date:nth-of-type(1)").value;
    const endDate = document.querySelector(".filter-date:nth-of-type(2)").value;
    const status = document.querySelector(".filter-dropdown").value;

    const url = new URL("/deliveryApp/customer/filter", window.location.origin);
    if (startDate) url.searchParams.append("startDate", startDate);
    if (endDate) url.searchParams.append("endDate", endDate);
    if (status && status !== "Estatus del pedido") url.searchParams.append("status", status);

    fetch(url)
        .then(response => response.json())
        .then(orders => {
            const pedidosList = document.querySelector(".pedidos-list");
            pedidosList.innerHTML = `
                    <div id="pedidos-header">
                        <div class="pedido-user"></div>
                        <div class="pedido-id">ID del Pedido</div>
                        <div class="pedido-status">Estado</div>
                        <div class="pedido-payment">Número de Tarjeta</div>
                        <div class="pedido-time">Fecha</div>
                        <div class="pedido-price">Total</div>
                    </div>
                `;
            orders.forEach(order => {
                pedidosList.innerHTML += `
                        <div class="pedido-item">
                            <div class="pedido-user">
                                <img src="/images/online-order.png" alt="" class="user-avatar" />
                                <span class="user-name">${order.user}</span>
                            </div>
                            <span class="pedido-id">${order.id}</span>
                            <span class="pedido-status">${order.status}</span>
                            <span class="pedido-payment">${order.cardNumber}</span>
                            <span class="pedido-time">${order.date}</span>
                            <span class="pedido-price">${order.total}</span>
                        </div>
                    `;
            });
        });
}

document.addEventListener("DOMContentLoaded", () => {
    document.querySelector(".filter-eats-pass").addEventListener("click", fetchFilteredOrders);
});
*/