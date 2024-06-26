$(document).ready(function() {
    // Load transports on page load
    loadTransports();
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