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

function changeStatus(newStatus) {
    const pedidoItems = document.querySelectorAll(".pedido-item");
    pedidoItems.forEach((item) => {
        const id = item.querySelector(".pedido-id").textContent;
        if (id === currentPedidoId) {
            item.querySelector(".pedido-status").textContent = newStatus;
        }
    });
    closeStatusForm();
}

function openSidebar(pedidoId, userName, time, productName, productSize, productPrice, totalPrice) {
    // Actualiza los datos dinámicamente
    document.querySelector(".sidebar2-title").textContent = `Pedido ${pedidoId}`;
    document.querySelector(".user-name").textContent = userName;
    document.querySelector(".sidebar2-date").textContent = "Aug 1, 2021";

    // Actualiza detalles del pedido
    document.querySelector(".order-details").innerHTML = `
    <div class="order-item">
      <span>1</span>
      <p>${productName} 🐟</p>
      <p class="item-details">${productSize}</p>
      <p class="item-price">MX$${productPrice}.00</p>
    </div>
    <div class="order-item">
      <span>1</span>
      <p>Clamato Preparado (Sin Alcohol)</p>
      <p class="item-price">MX$70.00</p>
    </div>
    <div class="order-item">
      <span>1</span>
      <p>Tostadas (Paq. con 20)</p>
      <p class="item-price">MX$60.00</p>
    </div>
  `;

    const sidebar = document.getElementById("sidebar2");
    sidebar.style.display = "block";
}

function closeSidebar() {
    const sidebar = document.getElementById("sidebar2");
    sidebar.style.display = "none";
}

