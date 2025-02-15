document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".dropdown-submenu > .dropdown-toggle").forEach(function (el) {
        el.addEventListener("click", function (e) {
            e.preventDefault();
            e.stopPropagation();
            let submenu = this.nextElementSibling;
            if (submenu) {
                submenu.classList.toggle("show");
            }
        });
    });
});

// NUEVO ROL
$("#guardarRol").on("click", function () {
    let rol = {
        nombre: $("#rNombre").val(),
    };

    $.ajax({
        type: "POST",
        url: "/roles/generar",
        data: JSON.stringify(rol),
        contentType: "application/json",
        success: function (response) {
            Swal.fire({
                title: "¡Éxito!",
                text: "El rol se guardó correctamente.",
                icon: "success",
                confirmButtonText: "Aceptar",
                confirmButtonColor: "#e0a800",
                background: "#495057",
                color: "#fff",
                timer: 2000,
                timerProgressBar: true
            }).then(() => {
                $('#modalRoles').modal('hide');
                location.reload();
            });
        },
        error: function (error) {
            console.error(error);
            Swal.fire({
                title: "Error",
                text: "Error al guardar el rol.",
                icon: "error",
                confirmButtonText: "Aceptar",
                confirmButtonColor: "#e0a800",
                background: "#495057",
                color: "#fff",
                timer: 2000,
                timerProgressBar: true
            });
        }
    });
});

// NUEVO PACIENTE
$("#guardarPaciente").on("click", function () {
    let paciente = {
        nombre: $("#pNombre").val(),
        apellido: $("#pApellido").val(),
        dni: $("#pDNI").val(),
        direccion: $("#pDireccion").val(),
        telefono: $("#pTelefono").val(),
        fechaNacimiento: $("#pFechaNacimiento").val(),
        obraSocial: $("#pObraSocial").val() || "Sin datos" // Si está vacío, asigna "Sin datos"
    };

    $.ajax({
        type: "POST",
        url: "/pacientes/generar",
        data: JSON.stringify(paciente),
        contentType: "application/json",
        success: function (response) {
            Swal.fire({
                title: "¡Éxito!",
                text: "El paciente se guardó correctamente.",
                icon: "success",
                confirmButtonText: "Aceptar",
                confirmButtonColor: "#e0a800",
                background: "#495057",
                color: "#fff"
            }).then(() => {
                $('#nuevoPacienteModal').modal('hide');
                location.reload();
            });
        },
        error: function (error) {
            console.error(error);
        }
    });
});

// NUEVO USUARIO
document.getElementById('btnGuardarUsuario').addEventListener('click', function () {

    var checkboxes = document.querySelectorAll('input[name="roles"]:checked');
    var rolesSeleccionados = Array.from(checkboxes).map(function (checkbox) {
        return checkbox.value;
    });

    var usuario = {
        username: document.getElementById('uUsername').value,
        email: document.getElementById('uEmail').value,
        password: document.getElementById('uPassword').value,
        rolesIds: rolesSeleccionados // Asigna el array de IDs de roles
    };

    console.log("Datos enviados: ", usuario);

    fetch('/usuarios/guardar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(usuario)
    })
        .then(response => response.json())
        .then(data => {
            console.log("Respuesta del servidor: ", data);
            var modalUsuario = new bootstrap.Modal(document.getElementById('modalUsuario'));
            modalUsuario.hide();
            Swal.fire({
                title: "¡Éxito!",
                text: data.mensaje,
                icon: "success",
                confirmButtonText: "Aceptar",
                confirmButtonColor: "#e0a800",
                timer: 10000,
                timerProgressBar: true,
                color: "#fff",
                background: "#495057"
            }).then(() => {
                location.reload();
                console.log("Usuario Creado");
            });
        })
        .catch(error => {
            console.error(error);
            Swal.fire({
                title: "Error",
                text: "Error al guardar el usuario.",
                icon: "error",
                confirmButtonText: "Aceptar",
                confirmButtonColor: "#e0a800",
                background: "#495057",
                color: "#fff",
                timer: 2000,
                timerProgressBar: true
            });
        });
});

// BOTON NUEVO USUARIO
$("#btnNuevoUsuario").on("click", function () {
    resetearModalUsuario();
    $('#modalUsuario').modal('show');
});

// BOTON NUEVO PACIENTE
$("#btnNuevoPaciente").on("click", function () {
    resetearModalPaciente();
    $('#modalPaciente').modal('show');
});

function resetearModalPaciente() {
    $("#pId").val("");
    $("#pNombre").val("");
    $("#pApellido").val("");
    $("#pDNI").val("");
    $("#pDireccion").val("");
    $("#pTelefono").val("");
    $("#pFechaNacimiento").val("");
    $("#pObraSocial").val("");

    // Actualizar el título del modal y los botones
    $('#modalPacienteLabel').text('Crear Nuevo Paciente');
    $('#btnActualizarPaciente').addClass('d-none');
    $('#guardarPaciente').removeClass('d-none');
}

function resetearModalUsuario() {
    $("#uId").val("");
    $("#uUsername").val("");
    $("#uEmail").val("");
    $("#uPassword").val("");
    //resetear cheks
    var checkboxes = document.querySelectorAll('input[name="roles"]');
    Array.from(checkboxes).forEach(function (checkbox) {
        checkbox.checked = false;
    });
    // Actualizar el título del modal y los botones
    $('#modalUsuarioLabel').text('Crear Nuevo Usuario');
    $('#btnActualizarUsuario').addClass('d-none');
    $('#btnGuardarUsuario').removeClass('d-none');
}