let userJSON = null;
let user = null;

function ValidateJSON(userJSON)
{
    if (!userJSON) {
        console.warn("No user data found in localStorage.");
        window.location.href = "/login";
        return;
    }
}

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

function ShowAlertError(title, menssage)
{
    Swal.fire({
        icon: "error",
        title: title,
        text: menssage
    });
}

function redirectToIndex() {
    window.location.href = "/";
}

document.addEventListener('DOMContentLoaded', () => {

    userJSON = localStorage.getItem("user");
    ValidateJSON(userJSON);
    user = JSON.parse(userJSON);

    const editButtons = document.querySelectorAll('.edit-btn');
    const modal = document.getElementById('modal');
    const modalBody = document.getElementById('modal-body');
    const closeModal = document.querySelector('.close');

    const editForms = {
        'edit-name': `
        <h2>Nombre</h2>
        <p>Este es el nombre que quieres que las personas usen cuando se refieran a ti.</p>
        <form id="edit-name-form">
            <label class="labelNames" for="first-name">Nombre Completo</label>
            <input type="text" id="first-name" name="name" value="${user.name}" required>
            <button class="buttonRegister" type="button" data-action="updateName">Actualizar</button>
        </form>
    `,
        'edit-phone': `
        <h2>Número de teléfono</h2>
        <p>Usarás este número para recibir notificaciones, iniciar sesión y recuperar tu cuenta.</p>
        <form id="edit-phone-form">
            <label for="country-code">Código de país</label>
            <select id="country-code" name="countryCode">
                <option value="MX" ${user.countryCode === 'MX' ? 'selected' : ''}>MX</option>
                <option value="US" ${user.countryCode === 'US' ? 'selected' : ''}>US</option>
            </select>
            <input type="text" id="phone-number" name="phoneNumber" value="${user.phoneNumber}" required>
            <button class="buttonRegister" type="button" data-action="updatePhone">Actualizar</button>
        </form>
    `,
        'edit-email': `
        <h2>Correo electrónico</h2>
        <p>Usarás este correo para recibir mensajes, iniciar sesión y recuperar tu cuenta.</p>
        <form id="edit-email-form">
            <label for="email">Correo electrónico</label>
            <input type="email" id="email" name="email" value="${user.email}" required>
            <button class="buttonRegister" type="button" data-action="updateEmail">Actualizar</button>
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

    document.addEventListener('click', (event) => {
        if (event.target.classList.contains('buttonRegister')) {
            const action = event.target.getAttribute('data-action');
            switch (action) {
                case 'updateName':
                    updateName();
                    break;
                case 'updatePhone':
                    updatePhone();
                    break;
                case 'updateEmail':
                    updateEmail();
                    break;
            }
        }
    });

    function updateEmail() {
        const form = document.getElementById('edit-email-form');
        const email = form.querySelector('#email').value;

        fetch('/deliveryApp/userProfile/updateEmail', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            credentials: 'include',
            body: JSON.stringify({ id: user.id, email: email })
        })
            .then((response) => {
                if(response.ok) {
                    ShowAlertOK('Correo electrónico con éxito',
                        'Se registro correctamente los datos del usuario en la base de datos');
                }
                else {
                    ShowAlertError('lo siento, hubo un error, intentelo de nuevo',
                        'Lo siento pero hubo un error con el servidor, en el de que siga, intentelo mas tarde');
                }
            })
            .catch((error) =>
            {
                ShowAlertError(
                    'Error de red',
                    'No fue posible conectar con el servidor. Por favor, verifica tu conexión e inténtalo nuevamente.'
                );
            });
    }

    function updatePhone() {
        const form = document.getElementById('edit-phone-form');
        const countryCode = form.querySelector('#country-code').value;
        const phoneNumber = form.querySelector('#phone-number').value;

        fetch('/deliveryApp/userProfile/updatePhone', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            credentials: 'include',
            body: JSON.stringify({ id: user.id, phoneNumber: phoneNumber })
        })
            .then((response) => {
                if(response.ok) {
                    ShowAlertOK('Teléfono actualizado con éxito',
                        'Se registro correctamente los datos del usuario en la base de datos');
                }
                else {
                    ShowAlertError('lo siento, hubo un error, intentelo de nuevo',
                        'Lo siento pero hubo un error con el servidor, en el de que siga, intentelo mas tarde');
                }
            })
            .catch((error) => {
                ShowAlertError(
                    'Error de red',
                    'No fue posible conectar con el servidor. Por favor, verifica tu conexión e inténtalo nuevamente.'
                );
            });
    }

    function updateName() {
        const form = document.getElementById('edit-name-form');
        const name = form.querySelector('#first-name').value;

        fetch('/deliveryApp/userProfile/updateName', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            credentials: 'include',
            body: JSON.stringify({ id: user.id, name: name })
        })
            .then((response) => {
                if(response.ok) {
                    ShowAlertOK('Nombre actualizado con éxito',
                        'Se registro correctamente los datos del usuario en la base de datos');
                }
                else {
                    ShowAlertError('lo siento, hubo un error, intentelo de nuevo',
                        'Lo siento pero hubo un error con el servidor, en el de que siga, intentelo mas tarde');
                }
            })
            .catch((error) => {
                ShowAlertError(
                    'Error de red',
                    'No fue posible conectar con el servidor. Por favor, verifica tu conexión e inténtalo nuevamente.'
                );
            });
    }
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

document.getElementById('updatePasswordButton').addEventListener('click',() => {
    const oldPassword = document.getElementById('last-Password').value;
    const newPassword = document.getElementById('new-password').value;
    const newPasswordConfirm = document.getElementById('new-password-Confirm').value;

    if (newPassword === newPasswordConfirm){
        let userLocalStorage = localStorage.getItem("user");
        ValidateJSON(userLocalStorage);
        let userParse = JSON.parse(userLocalStorage);

        fetch('/deliveryApp/userProfile/updatePassword', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            credentials: 'include',
            body: JSON.stringify({ id: userParse.id, oldPassword, newPassword })
        })
            .then((response) => {
                console.log('Response status:', response.status);
                if (response.ok) {
                    Swal.fire({
                        icon: "success",
                        title: 'Contraseña actualizada con éxito',
                        text: 'Se registró correctamente los datos del usuario en la base de datos',
                        confirmButtonText: 'OK'
                    });
                } else if (response.status === 401) {
                    console.warn("Error 401: La contraseña antigua no coincide.");
                    ShowAlertError(
                        'Contraseña antigua incorrecta',
                        'Lo siento, pero tu contraseña antigua no coincide con la registrada en la base de datos.'
                    );
                } else {
                    console.error("Error del servidor:", response.status);
                    ShowAlertError(
                        'Error del servidor',
                        'Ocurrió un problema en el servidor. Por favor, inténtalo más tarde.'
                    );
                }
            })
            .catch((error) => {
                console.error("Error de red:", error);
                ShowAlertError(
                    'Error de red',
                    'No fue posible conectar con el servidor. Por favor, verifica tu conexión e inténtalo nuevamente.'
                );
            });

    }
});


