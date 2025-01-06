document.addEventListener("DOMContentLoaded", () => {
    const nuevoArticuloBtn = document.getElementById("nuevo-articulo-btn");
    const modal = document.getElementById("articulo-modal");
    const closeModal = document.querySelector(".close");
    const articuloForm = document.getElementById("articulo-form");
    const articulosTbody = document.getElementById("articulos-tbody");
    const modalTitulo = document.getElementById("modal-titulo");

    let editandoArticulo = null;

    const articulos = [
        {
            foto: "https://via.placeholder.com/50",
            nombre: "12 Alitas Hot",
            notas: "Incluye salsa ranch",
            precio: "115.00",
            menus: "Tacos, Sopes",
            categorias: "Alitas Hot Wings",
            seUsaEn: "-",
            contiene: "Salsa Hot",
            ultimaActualizacion: "07/29",
        },
    ];

    nuevoArticuloBtn.addEventListener("click", () => {
        modal.style.display = "flex";
        modalTitulo.textContent = "Nuevo Artículo";
        articuloForm.reset();
        editandoArticulo = null;
    });

    closeModal.addEventListener("click", () => {
        modal.style.display = "none";
    });

    document.addEventListener("DOMContentLoaded", () => {
        const form = document.getElementById("articulo-form");

        form.addEventListener("submit", async (event) => {
            event.preventDefault(); // Evita el envío tradicional del formulario

            const formData = new FormData(form);

            try {
                const response = await fetch(form.action, {
                    method: "POST",
                    body: formData
                });

                if (response.ok) {
                    // Recargar la página si el servidor responde con éxito (200 OK)
                    location.reload();
                    const articulosSection = document.getElementById("articulos-section");

                    if (articulosSection) {
                        articulosSection.scrollIntoView({ behavior: "smooth" });
                    }
                } else {
                    // Manejar errores
                    const errorMessage = await response.text();
                    alert("Error: " + errorMessage);
                }
            } catch (error) {
                console.error("Error al enviar el formulario:", error);
                alert("Error al procesar la solicitud. Intenta nuevamente.");
            }
        });
    });

    articulosTbody.addEventListener("click", (e) => {
        if (e.target.classList.contains("editar-btn")) {
            const index = e.target.getAttribute("data-index");
            const articulo = articulos[index];

            modal.style.display = "flex";
            modalTitulo.textContent = "Editar Artículo";
            articuloForm.nombre.value = articulo.nombre;
            articuloForm.notas.value = articulo.notas;
            articuloForm.precio.value = articulo.precio;
            articuloForm.menus.value = articulo.menus;
            articuloForm.categorias.value = articulo.categorias;
            articuloForm["se-usa-en"].value = articulo.seUsaEn;
            articuloForm.contiene.value = articulo.contiene;

            editandoArticulo = index;
        }
    });

});
