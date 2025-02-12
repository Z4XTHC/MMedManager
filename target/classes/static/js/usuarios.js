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
            $("#uId").val(data.id);
            $("#uUsername").val(data.username);
            $("#uEmail").val(data.email);
            $("#uPassword").val(data.password);

            // Limpiar y establecer roles
            $('input[name="roles"]').prop('checked', false); // Desmarcar todas las casillas
            data.roles.forEach(function (rol) {
                $('#rol-' + rol.id).prop('checked', true); // Marcar las casillas correspondientes
            });
            $('#modalUsuario').modal('show');

            // Reemplazar evento para evitar múltiples llamadas
            $("#guardarUsuario").off("click").on("click", function () {
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
        nombre: $("#uNombre").val(),
        apellido: $("#uApellido").val(),
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
                timer: 2000,
                timerProgressBar: true
            }).then(() => {
                $('#modalUsuario').modal('hide');
                location.reload();
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


function resetear() {
    $("#uId").val("");
    $("#uNombre").val("");
    $("#uApellido").val("");
    $("#uEmail").val("");
    $("#uPassword").val("");
    //resetear cheks
    var checkboxes = document.querySelectorAll('input[name="roles"]');
    Array.from(checkboxes).forEach(function (checkbox) {
        checkbox.checked = false;
    });
}