const pedidos = [
    { codigo: "P001", fecha: "2024-12-24 10:00 AM", costo: "$150.00", estado: "Pagado" },
    { codigo: "P002", fecha: "2024-12-23 02:30 PM", costo: "$200.00", estado: "Pendiente" },
    { codigo: "P003", fecha: "2024-12-22 01:15 PM", costo: "$100.00", estado: "Cancelado" },
];

const tablaPedidos = document.getElementById("tabla-pedidos");

pedidos.forEach((pedido) => {
    const row = document.createElement("tr");
    row.innerHTML = `
    <td>${pedido.codigo}</td>
    <td>${pedido.fecha}</td>
    <td>${pedido.costo}</td>
    <td>${pedido.estado}</td>
  `;
    tablaPedidos.appendChild(row);
});
