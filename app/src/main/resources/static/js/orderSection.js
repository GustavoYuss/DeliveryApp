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

    // Actualiza los datos de la cabecera de la sidebar
    document.querySelector(".sidebar2-title").textContent = `Pedido ${idOrder}`;
    document.querySelector(".user-name").textContent = userName;
    document.querySelector(".sidebar2-date").textContent = orderDate;

    console.log(idOrder);

    try {
        const response = await fetch(`http://localhost:8080/getDishesFromOrder?idOrder=${idOrder}`);
        if (!response.ok) {
            throw new Error(`Error en la solicitud: ${response.status}`);
        }

        const dishItems = await response.json();
        console.log("JSON recibido del servidor:", dishItems);

        const orderDetailsContainer = document.querySelector(".order-details");
        orderDetailsContainer.innerHTML = "";

        dishItems.forEach((item) => {
            const orderItem = document.createElement("div");
            orderItem.className = "order-item";
            orderItem.innerHTML = `
                <span>${item.amount}</span>
                <p>${item.dish.name}</p>
                <p class="item-price">MX$${item.dish.normalPrice.toFixed(2)} C/U</p>
            `;
            orderDetailsContainer.appendChild(orderItem);
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

