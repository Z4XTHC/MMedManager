// TABLA USUARIOS
$(document).ready(function () {
    $('#tablaUsuarios').DataTable({
        lengthMenu: [5, 10, 15, 20],
        language: {
            "search": "Buscar: ",
            "lengthMenu": "Mostrar _MENU_ registros",
            "info": "Mostrando _START_ a _END_ de _TOTAL_ registros",
            "zeroRecords": "No hay registros",
            "infoEmpty": "No hay registros",
            "infoFiltered": "(Encontrados _MAX_ de registros)",
            "paginate": {
                "first": "Primero",
                "last": "Último",
                "previous": "Anterior",
                "next": "Siguiente"
            }
        }
    });

});

function editar(id) {
    $.ajax({
        type: "GET",
        url: "/usuarios/editar/" + id,
        success: function (data) {
            console.log(data);
            $("#uUsername").val(data.username);
            $("#uEmail").val(data.email);
            $("#uPassword").val(data.password);

            // Limpiar y establecer roles
            $('input[name="roles"]').prop('checked', false); // Desmarcar todas las casillas
            data.roles.forEach(function (rol) {
                $('#rol-' + rol.id).prop('checked', true); // Marcar las casillas correspondientes
            });
            $('#modalUsuario').modal('show');

            // Actualizar el título del modal y los botones
            $('#modalUsuarioLabel').text('Editar Usuario');
            $('#btnGuardarUsuario').addClass('d-none');
            $('#btnActualizarUsuario').removeClass('d-none');

            // Reemplazar evento para evitar múltiples llamadas
            $("#btnActualizarUsuario").off("click").on("click", function () {
                actualizarUsuario(data.id);
            });
        },
        error: function (response) {
            console.error(response);
            Swal.fire({
                title: "Error",
                text: "Error al cargar los datos del usuario.",
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
}


function actualizarUsuario(id) {
    var checkboxes = document.querySelectorAll('input[name="roles"]:checked');
    var rolesSeleccionados = Array.from(checkboxes).map(function (checkbox) {
        return { id: checkbox.value };
    });

    var usuario = {
        id: id,
        username: $("#uUsername").val(),
        email: $("#uEmail").val(),
        password: $("#uPassword").val(),
        roles: rolesSeleccionados
    };

    console.log("Datos a actualizar: ", usuario);

    $.ajax({
        type: "PUT",
        url: "/usuarios/actualizar/" + id,
        data: JSON.stringify(usuario),
        contentType: "application/json",
        success: function (response) {
            console.log(response);
            Swal.fire({
                title: "Actualización exitosa",
                text: "Usuario actualizado correctamente.",
                icon: "success",
                confirmButtonText: "Aceptar",
                confirmButtonColor: "#28a745",
                background: "#495057",
                color: "#fff",
                timer: 10000,
                timerProgressBar: true
            }).then(() => {
                $('#modalUsuario').modal('hide');
                location.reload();
                console.log("Usuario Editado");
                resetearModal();
            });
        },
        error: function (response) {
            console.error(response);
            Swal.fire({
                title: "Error",
                text: "Error al actualizar el usuario.",
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
}

function resetearModal() {
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