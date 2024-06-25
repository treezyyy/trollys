$(document).ready(function() {
    // Load transports on page load
    loadTransports();

    // Handle form submissions
    $('#transport-form').off('submit').on('submit', function(event) {
        event.preventDefault(); // Prevent the form from submitting via the browser
        addTransport();
    });

    $('#equipment-form').off('submit').on('submit', function(event) {
        event.preventDefault(); // Prevent the form from submitting via the browser
        addEquipment();
    });
});

function loadTransports() {
    $.get('/api/v1/transport/get_transports', function(data) {
        console.log('Received data:', data); // Debug line
        var transportsTableBody = $('#transports-table-body');
        transportsTableBody.empty();

        data.forEach(function(transport) {
            var equipmentHTML = '';
            if (transport.equipmentList.length > 0) {
                transport.equipmentList.forEach(function(equipment) {
                    equipmentHTML += '<li><strong>Название оборудования:</strong> ' + equipment.nameEquipment + ' (' +
                                    '<strong>Серийный номер:</strong> ' + equipment.serialNumber + ', ' +
                                    '<strong>Статус:</strong> ' + equipment.status + ')</li>';
                });
            } else {
                equipmentHTML = '<li>No equipment</li>';
            }

            var row = '<tr>' +
                        '<td>' + transport.vin + '</td>' +
                        '<td>' + transport.garageNumber + '</td>' +
                        '<td>' + transport.infoteh + '</td>' +
                        '<td><ul>' + equipmentHTML + '</ul></td>' +
                      '</tr>';
            transportsTableBody.append(row);
        });
    }).fail(function(jqXHR, textStatus, errorThrown) {
        console.error('Error fetching transports:', textStatus, errorThrown); // Error handling
    });
}

function addTransport() {
    var transportData = {
        vin: $('#vin').val(),
        garageNumber: $('#garageNumber').val(),
        infoteh: $('#infoteh').val()
    };

    $.ajax({
        type: 'POST',
        url: '/api/v1/transport/create_transport',
        contentType: 'application/json',
        data: JSON.stringify(transportData),
        success: function(response) {
            alert('Транспорт успешно добавлен');
            loadTransports(); // Reload the transports table
            $('#transport-form')[0].reset(); // Reset the form
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert('Ошибка при добавлении транспорта: ' + jqXHR.responseText);
        }
    });
}

function addEquipment() {
    var equipmentData = {
        transport_vin: $('#transportVin').val(),
        nameEquipment: $('#nameEquipment').val(),
        serialNumber: $('#serialNumber').val(),
        status: $('#status').val()
    };

    $.ajax({
        type: 'POST',
        url: '/api/v1/equipment/create_equipment',
        contentType: 'application/json',
        data: JSON.stringify(equipmentData),
        success: function(response) {
            alert('Оборудование успешно добавлено');
            loadTransports(); // Reload the transports table
            $('#equipment-form')[0].reset(); // Reset the form
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert('Ошибка при добавлении оборудования: ' + jqXHR.responseText);
        }
    });
}
