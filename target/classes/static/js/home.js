document.addEventListener("DOMContentLoaded", function () {
    function updateDateTime() {
        const now = new Date();

        // Formatear la fecha (Ej: Sábado, 20 de Enero de 2025)
        const optionsDate = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
        const currentDateElem = document.getElementById('current-date');
        if (currentDateElem) {
            currentDateElem.textContent = now.toLocaleDateString('es-ES', optionsDate);
        }

        // Formatear la hora (Ej: 14:30:15)
        const optionsTime = { hour: '2-digit', minute: '2-digit', second: '2-digit' };
        const currentTimeElem = document.getElementById('current-time');
        if (currentTimeElem) {
            currentTimeElem.textContent = now.toLocaleTimeString('es-ES', optionsTime);
        }
    }

    // Actualizar cada segundo
    setInterval(updateDateTime, 1000);
    updateDateTime(); // Llamada inicial
});
