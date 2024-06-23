$(document).ready(function() {
    // Загрузка транспортных средств при загрузке страницы
    loadTransports();

    // Обработчик события клика на ссылки VIN
    $('#transports-table-body').on('click', '.vin-link', function(event) {
        event.preventDefault();
        var vin = $(this).data('vin');
        window.location.href = '/equipments?vin=' + encodeURIComponent(vin);
    });
});

function loadTransports() {
    $.get('/api/v1/transport/get_transports', function(data) {
        console.log('Полученные данные:', data); // Для отладки
        var transportsTableBody = $('#transports-table-body');
        transportsTableBody.empty();

        data.forEach(function(transport) {
            var row = '<tr>' +
                        '<td><a href="#" class="vin-link" data-vin="' + transport.vin + '">' + transport.vin + '</a></td>' +
                        '<td>' + transport.garageNumber + '</td>' +
                        '<td>' + transport.infoteh + '</td>' +
                      '</tr>';
            transportsTableBody.append(row);
        });
    }).fail(function(jqXHR, textStatus, errorThrown) {
        console.error('Ошибка при загрузке транспортных средств:', textStatus, errorThrown); // Обработка ошибок
    });
}
