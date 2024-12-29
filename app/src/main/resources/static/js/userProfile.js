document.addEventListener('DOMContentLoaded', () => {
    const editButtons = document.querySelectorAll('.edit-btn');
    const modal = document.getElementById('modal');
    const modalBody = document.getElementById('modal-body');
    const closeModal = document.querySelector('.close');
    const editForms = {
        'edit-name': `
            <h2>Nombre</h2>
            <p>Este es el nombre que quieres que las personas usen cuando se refieran a ti.</p>
            <form>
                <label class="labelNames" for="first-name">Nombre</label>
                <input type="text" id="first-name" value="Miguel">
                <label class="labelNames" for="last-name">Apellido</label>
                <input type="text" id="last-name" value="Camo">
                <button class="buttonRegister" type="submit">Actualizar</button>
            </form>
        `,
        'edit-phone': `
            <h2>Número de teléfono</h2>
            <p>Usarás este número para recibir notificaciones, iniciar sesión y recuperar tu cuenta.</p>
            <form>
                <label for="country-code">Código de país</label>
                <select id="country-code">
                    <option value="MX">MX</option>
                    <option value="US">US</option>
                </select>
                <input type="text" id="phone-number" value="+5212731010078">
                <button class="buttonRegister" type="submit">Actualizar</button>
            </form>
        `,
        'edit-email': `
            <h2>Correo electrónico</h2>
            <p>Usarás este correo para recibir mensajes, iniciar sesión y recuperar tu cuenta.</p>
            <form>
                <label for="email">Correo electrónico</label>
                <input type="email" id="email" value="camomiguel@gmail.com">
                <button class="buttonRegister" type="submit">Actualizar</button>
            </form>
        `
    };

    editButtons.forEach(button => {
        button.addEventListener('click', () => {
            const targetId = button.dataset.target;
            modalBody.innerHTML = editForms[targetId];
            modal.style.display = 'flex';
            document.body.classList.add('modal-open');
        });
    });

    closeModal.addEventListener('click', () => {
        modal.style.display = 'none';
        document.body.classList.remove('modal-open');
    });

    window.addEventListener('click', event => {
        if (event.target === modal) {
            modal.style.display = 'none';
            document.body.classList.remove('modal-open');
        }
    });
});

document.addEventListener('DOMContentLoaded', () => {
    const buttons = document.querySelectorAll('.sidebar button');
    const sections = document.querySelectorAll('.section');

    buttons.forEach(button => {
        button.addEventListener('click', () => {
            buttons.forEach(btn => btn.classList.remove('active'));
            button.classList.add('active');

            const sectionId = button.id.replace('btn-', '');
            sections.forEach(section => {
                section.classList.remove('active');
                if (section.id === sectionId) {
                    section.classList.add('active');
                }
            });
        });
    });
});


