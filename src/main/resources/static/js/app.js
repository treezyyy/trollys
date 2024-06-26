$(document).ready(function() {
    // Загрузить транспортные средства при загрузке страницы
    loadTransports();

    // Обработка отправки формы
    $('#transport-form').off('submit').on('submit', function(event) {
        event.preventDefault(); // Предотвратить отправку формы через браузер
        addTransport();
    });

    $('#equipment-form').off('submit').on('submit', function(event) {
        event.preventDefault(); // Предотвратить отправку формы через браузер
        addEquipment();
    });
});

function loadTransports() {
    $.get('/api/v1/transport/get_transports', function(data) {
        console.log('Получены данные:', data); // Строка для отладки
        var transportsTableBody = $('#transports-table-body');
        transportsTableBody.empty();

        if (Array.isArray(data) && data.length > 0) {
            data.forEach(function(transport) {
                var equipmentHTML = '';
                if (Array.isArray(transport.equipmentList) && transport.equipmentList.length > 0) {
                    transport.equipmentList.forEach(function(equipment) {
                        if (equipment && equipment.nameEquipment && equipment.serialNumber && equipment.status) {
                            equipmentHTML += '<li><strong>Название оборудования:</strong> ' + escapeHTML(equipment.nameEquipment) + ' (' +
                                            '<strong>Серийный номер:</strong> ' + escapeHTML(equipment.serialNumber) + ', ' +
                                            '<strong>Статус:</strong> ' + escapeHTML(equipment.status) + ')</li>';
                        } else {
                            console.error('Некорректные данные оборудования:', equipment);
                        }
                    });
                } else {
                    equipmentHTML = '<li>Нет оборудования</li>';
                }

                if (transport && transport.vin && transport.garageNumber && transport.infoteh) {
                    var row = '<tr>' +
                                '<td><a href="/equipments/' + escapeHTML(transport.vin) + '">' + escapeHTML(transport.vin) + '</a></td>' +
                                '<td>' + escapeHTML(transport.garageNumber) + '</td>' +
                                '<td>' + escapeHTML(transport.infoteh) + '</td>' +
                                '<td><ul>' + equipmentHTML + '</ul></td>' +
                              '</tr>';
                    transportsTableBody.append(row);
                } else {
                    console.error('Некорректные данные транспорта:', transport);
                }
            });
        } else {
            console.error('Пустой или некорректный список транспортных средств');
            transportsTableBody.append('<tr><td colspan="4">Нет доступных транспортных средств</td></tr>');
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
        console.error('Ошибка при получении транспортных средств:', textStatus, errorThrown); // Обработка ошибки
        $('#transports-table-body').append('<tr><td colspan="4">Ошибка при загрузке данных</td></tr>');
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
            loadTransports(); // Перезагрузить таблицу транспортных средств
            $('#transport-form')[0].reset(); // Сбросить форму
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
            loadTransports(); // Перезагрузить таблицу транспортных средств
            $('#equipment-form')[0].reset(); // Сбросить форму
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert('Ошибка при добавлении оборудования: ' + jqXHR.responseText);
        }
    });
}

// Функция для экранирования HTML
function escapeHTML(str) {
    return str.replace(/[&<>"']/g, function(match) {
        const escape = {
            '&': '&amp;',
            '<': '&lt;',
            '>': '&gt;',
            '"': '&quot;',
            "'": '&#39;'
        };
        return escape[match];
    });
}
