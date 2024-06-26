$(document).ready(function() {
    var url = window.location.href;
    const parts = url.split('/');
    const lastPart = parts[parts.length - 1];
    const lastNumber = parseInt(lastPart, 10);

    if (!isNaN(lastNumber)) {
        console.log(url);
        loadEquipment(lastNumber);
        $('#VINname').text('VIN:' + lastNumber);
    } else {
        console.error('Неправильный формат VIN в URL');
    }
});

function loadEquipment(vin) {
    // Загружаем оборудование по VIN
    $.get('/api/v1/equipment/get_equipmentByVin/' + vin, function(equipmentList) {
        console.log('Полученное оборудование:', equipmentList); // Для отладки
        var transportsTableBody = $('#equipment-details');
        transportsTableBody.empty();

        if (Array.isArray(equipmentList) && equipmentList.length > 0) {
            equipmentList.forEach(function(equipment) {
                if (equipment && equipment.nameEquipment && equipment.serialNumber && equipment.status) {
                    var row = '<tr>' +
                                '<td>' + escapeHTML(equipment.nameEquipment) + '</td>' +
                                '<td>' + escapeHTML(equipment.serialNumber) + '</td>' +
                                '<td>' + escapeHTML(equipment.status) + '</td>' +
                              '</tr>';
                    transportsTableBody.append(row);
                } else {
                    console.error('Некорректные данные оборудования:', equipment);
                }
            });
        } else {
            console.error('Пустой или некорректный список оборудования');
            transportsTableBody.append('<tr><td colspan="3">Нет доступного оборудования</td></tr>');
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
        console.error('Ошибка при загрузке оборудования:', textStatus, errorThrown); // Обработка ошибок
        $('#equipment-details').append('<tr><td colspan="3">Ошибка при загрузке данных</td></tr>');
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
