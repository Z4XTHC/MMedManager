// TABLA USUARIOS
$(document).ready(function () {
    $('#tablaPacientes').DataTable({
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

// EDITAR PACIENTE DESDE EL MODAL
function editar(id) {
    $.ajax({
        type: "GET",
        url: "/pacientes/editar/" + id,
        success: function (data) {
            console.log(data);
            $("#pId").val(data.id);
            $("#pNombre").val(data.nombre);
            $("#pApellido").val(data.apellido);
            $("#pDNI").val(data.dni);
            $("#pDireccion").val(data.direccion);
            $("#pTelefono").val(data.telefono);
            $("#pFechaNacimiento").val(data.fechaNacimiento);
            $("#pObraSocial").val(data.obraSocial);
            $('#modalPaciente').modal('show');

            // Reemplazar evento para evitar múltiples llamadas
            $("#guardarPaciente").off("click").on("click", function () {
                actualizarPaciente(data.id);
            });
        },
        error: function (response) {
            console.error(response);
            Swal.fire({
                title: "Error",
                text: "Error al cargar los datos del paciente.",
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

// ACTUALIZAR PACIENTE
function actualizarPaciente(id) {
    let paciente = {
        nombre: $("#pNombre").val(),
        apellido: $("#pApellido").val(),
        dni: $("#pDNI").val(),
        direccion: $("#pDireccion").val(),
        telefono: $("#pTelefono").val(),
        fechaNacimiento: $("#pFechaNacimiento").val(),
        obraSocial: $("#pObraSocial").val()
    };

    $.ajax({
        type: "PUT",
        url: "/pacientes/actualizar/" + id,
        data: JSON.stringify(paciente),
        contentType: "application/json",
        success: function (response) {
            $('#modalPaciente').modal('hide');

            console.log("Respuesta del servidor:", response); // Verificar JSON

            Swal.fire({
                title: "¡Éxito!",
                text: response.mensaje, // Usa el mensaje devuelto por el servidor
                icon: "success",
                confirmButtonText: "Aceptar",
                confirmButtonColor: "#e0a800",
                background: "#495057",
                color: "#fff",
                timer: 2000,
                timerProgressBar: true
            }).then(() => {
                resetear(); //Resetea los valores del formulario.
                $("#guardarPaciente").text("Guardar"); //Vuelve a mostrar el texto original.
                location.reload();
            });
        },
        error: function (response) {
            console.error(response);
            Swal.fire({
                title: "Error",
                text: "Error al actualizar el paciente.",
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


//funcion para llamar a resetear los valores del formulario

function resetear() {
    $("#pId").val("");
    $("#pNombre").val("");
    $("#pApellido").val("");
    $("#pDNI").val("");
    $("#pDireccion").val("");
    $("#pTelefono").val("");
    $("#pFechaNacimiento").val("");
    $("#pObraSocial").val("");
}