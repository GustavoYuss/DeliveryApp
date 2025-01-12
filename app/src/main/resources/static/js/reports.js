document.addEventListener("DOMContentLoaded", async () => {

    const response = await fetch(`http://localhost:8080/getTodayStats`);
    if (!response.ok) {
        console.log("ALGO MAAL PASO")
    }
    const todayStats = await response.json();

    const responseTotal = await fetch(`http://localhost:8080/getStats`);
    if (!responseTotal.ok) {
        console.log("ALGO MAAL PASO")
    }
    const totalStats = await responseTotal.json();

    new Chart(document.getElementById("grafica-pedidos-dia"), {
        type: "pie",
        data: {
            labels: ["En progreso", "Enviados", "Entregados", "Cancelados"],
            datasets: [
                {
                    data: todayStats,
                    backgroundColor: ["#4CAF50", "#FFC107", "#673AB7"],
                },
            ],
        },
    });

    new Chart(document.getElementById("grafica-pedidos-exito-mes"), {
        type: "doughnut",
        data: {
            labels: ["En progreso", "Enviados", "Entregados", "Cancelados"],
            datasets: [
                {
                    data: totalStats,
                    backgroundColor: ["#2196F3", "#FF5722", "#673AB7"],
                },
            ],
        },
    });


    new Chart(document.getElementById("grafica-pedidos-pagados-cancelados"), {
        type: "bar",
        data: {
            labels: ["En progreso", "Enviados", "Entregados", "Cancelados"],
            datasets: [
                {
                    label: "Pedidos",
                    data: totalStats,
                    backgroundColor: ["#8BC34A", "#F44336", "#673AB7"],
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


