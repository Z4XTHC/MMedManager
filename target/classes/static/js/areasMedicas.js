// TABLA ROLES
$(document).ready(function () {
    $('#tablaAreasMedicas').DataTable({
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
        url: '/areas-medicas/editar/' + id,
        method: 'GET',
        success: function (data) {
            $('#amId').val(data.id);
            $('#amNombre').val(data.nombre);
            $('#amDescripcion').val(data.descripcion);
            $("#modalAreaMedica").modal('show');

            // Actualizar el titulo del modal y los botones
            $('#modalAreaMedicaLabel').text('Editar Área Médica');
            $('#btnActualizarAreaMedica').removeClass('d-none');
            $('#guardarAreaMedica').addClass('d-none');

            // Reemplazar evento para evitar multiples llamadas
            $('#btnActualizarAreaMedica').off("click").on("click", function () {
                actualizarAreaMedica(data.id);
            });
        },
        error: function (response) {
            console.error(response);
            Swal.fire({
                title: "Error",
                text: "Error al cargar los datos de la área médica.",
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

// Actualizar Área Médica

function actualizarAreaMedica(id) {
    var areaMedica = {
        nombre: $('#amNombre').val(),
        descripcion: $('#amDescripcion').val()
    };
    $.ajax({
        url: '/areas-medicas/actualizar/' + id,
        method: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(areaMedica),
        success: function (response) {
            $('#modalAreaMedica').modal('hide');

            console.log("Respuesta del servidor:", response);

            Swal.fire({
                title: "Área Médica Actualizada",
                text: response.mensaje,
                icon: "success",
                confirmButtonText: "Aceptar",
                confirmButtonColor: "#e0a800",
                background: "#495057",
                color: "#fff",
                timer: 2000,
                timerProgressBar: true
            }).then(() => {
                resetearModalAreaMedica();
                location.reload();
            });
        },
        error: function (response) {
            console.error(response);
            Swal.fire({
                title: "Error",
                text: "Error al actualizar la área médica.",
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