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
