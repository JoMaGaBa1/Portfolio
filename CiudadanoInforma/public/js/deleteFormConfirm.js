$(document).ready(function() {
    $(".btnEliminar").click(function(event) {
        var form =  $(this).closest("form");
        var elemento = $(this).data("name");
        event.preventDefault();
        swal({
            title: `¿Quieres eliminar el elemento "${elemento}"?`,
            text: "Si lo borras, no podrás recuperarlo.",
            icon: "warning",
            buttons: ["No, cancelar", "Sí, eliminar"],
            dangerMode: true,
        })
            .then((borrarConfirmado) => {
                if (borrarConfirmado) {
                    form.submit();
                }
            });
    });
});
