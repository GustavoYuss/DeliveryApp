document.addEventListener("DOMContentLoaded", () => {
    const dataPedidosHoy = [12, 8];
    const dataPedidosExitoMes = [70, 30];
    const dataProgreso = [10, 20, 30, 40, 50];
    const dataPagadosCancelados = [40, 10];

    new Chart(document.getElementById("grafica-pedidos-dia"), {
        type: "pie",
        data: {
            labels: ["Completados", "En progreso"],
            datasets: [
                {
                    data: dataPedidosHoy,
                    backgroundColor: ["#4CAF50", "#FFC107"],
                },
            ],
        },
    });

    new Chart(document.getElementById("grafica-pedidos-exito-mes"), {
        type: "doughnut",
        data: {
            labels: ["Éxito", "Fallidos"],
            datasets: [
                {
                    data: dataPedidosExitoMes,
                    backgroundColor: ["#2196F3", "#FF5722"],
                },
            ],
        },
    });

    new Chart(document.getElementById("grafica-avance-pedidos"), {
        type: "line",
        data: {
            labels: ["Semana 1", "Semana 2", "Semana 3", "Semana 4", "Semana 5"],
            datasets: [
                {
                    label: "Progreso",
                    data: dataProgreso,
                    borderColor: "#673AB7",
                    fill: false,
                },
            ],
        },
    });

    new Chart(document.getElementById("grafica-pedidos-pagados-cancelados"), {
        type: "bar",
        data: {
            labels: ["Pagados", "Cancelados"],
            datasets: [
                {
                    label: "Pedidos",
                    data: dataPagadosCancelados,
                    backgroundColor: ["#8BC34A", "#F44336"],
                },
            ],
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                },
            },
        },
    });
});


// Pedidos pendientes por categorías
const dataPedidosPendientes = [15, 25, 10]; // Ejemplo: Urgente, Regular, Baja prioridad
new Chart(document.getElementById("grafica-pedidos-categorias"), {
    type: "bar",
    data: {
        labels: ["Urgente", "Regular", "Baja prioridad"],
        datasets: [
            {
                label: "Pedidos pendientes",
                data: dataPedidosPendientes,
                backgroundColor: ["#FF5722", "#FFC107", "#4CAF50"],
            },
        ],
    },
    options: {
        scales: {
            y: {
                beginAtZero: true,
            },
        },
    },
});

// Ingresos totales por mes
const dataIngresosMensuales = [1200, 1800, 1500, 2000, 1700]; // Ejemplo: ingresos en los últimos 5 meses
new Chart(document.getElementById("grafica-ingresos-mensuales"), {
    type: "line",
    data: {
        labels: ["Enero", "Febrero", "Marzo", "Abril", "Mayo"],
        datasets: [
            {
                label: "Ingresos totales ($)",
                data: dataIngresosMensuales,
                borderColor: "#3F51B5",
                backgroundColor: "rgba(63, 81, 181, 0.1)",
                fill: true,
            },
        ],
    },
    options: {
        scales: {
            y: {
                beginAtZero: true,
            },
        },
    },
});
