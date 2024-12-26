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
        // Agregar más artículos si es necesario
    ];

    function renderizarArticulos() {
        articulosTbody.innerHTML = "";
        articulos.forEach((articulo, index) => {
            const row = document.createElement("tr");
            row.innerHTML = `
        <td><img src="${articulo.foto}" alt="Foto" width="50"></td>
        <td>${articulo.nombre}</td>
        <td>${articulo.notas}</td>
        <td>${articulo.precio}</td>
        <td>${articulo.menus}</td>
        <td>${articulo.categorias}</td>
        <td>${articulo.seUsaEn}</td>
        <td>${articulo.contiene}</td>
        <td>${articulo.ultimaActualizacion}</td>
        <td>
          <button class="editar-btn" data-index="${index}">Editar</button>
        </td>
      `;
            articulosTbody.appendChild(row);
        });
    }

    nuevoArticuloBtn.addEventListener("click", () => {
        modal.style.display = "flex";
        modalTitulo.textContent = "Nuevo Artículo";
        articuloForm.reset();
        editandoArticulo = null;
    });

    closeModal.addEventListener("click", () => {
        modal.style.display = "none";
    });

    articuloForm.addEventListener("submit", (e) => {
        e.preventDefault();
        const nuevoArticulo = {
            foto: "https://via.placeholder.com/50",
            nombre: e.target.nombre.value,
            notas: e.target.notas.value,
            precio: e.target.precio.value,
            menus: e.target.menus.value,
            categorias: e.target.categorias.value,
            seUsaEn: e.target["se-usa-en"].value,
            contiene: e.target.contiene.value,
            ultimaActualizacion: new Date().toLocaleDateString(),
        };

        if (editandoArticulo !== null) {
            articulos[editandoArticulo] = nuevoArticulo;
        } else {
            articulos.push(nuevoArticulo);
        }

        renderizarArticulos();
        modal.style.display = "none";
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

    renderizarArticulos();
});
