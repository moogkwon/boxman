var modalTitle = $('.modal-title');
var eventModal = $('#eventModal');
var loginEmp = $('#empNo');
var editAllDay = $('#edit-allDay');
var editTitle = $('#edit-title'); 
var editStart = $('#edit-start');
var editEnd = $('#edit-end');
var editType = $('#edit-type');
var editColor = $('#edit-color');
var editDesc = $('#edit-desc');

var addBtnContainer = $('.modalBtnContainer-addEvent');
var modifyBtnContainer = $('.modalBtnContainer-modifyEvent');


/* ****************
 *  새로운 일정 생성
 * ************** */
var newEvent = function (start, end, eventType) {

    $("#contextMenu").hide(); //메뉴 숨김
    modalTitle.html();
    editStart.val(start);
    editEnd.val(end);
    editType.val(eventType).prop("selected", true);

    addBtnContainer.show();
    modifyBtnContainer.hide();
    eventModal.modal('show');

    //새로운 일정 저장버튼 클릭
    $('#save-event').unbind();
    $('#save-event').on('click', function () {

        var eventData = {
        	
            title: editTitle.val(),
            description: editDesc.val(),
            start: editStart.val(),
            end: editEnd.val(),
            type: editType.val(),
            username: loginEmp.val(),
            backgroundColor: editColor.val(),
            textColor: '#ffffff',
            allDay: false
            
        };

        if (eventData.start > eventData.end) {
            alert('끝나는 날짜가 앞설 수 없습니다.');
            return false;
        }

        if (eventData.title === '') {
            alert('일정명은 필수입니다.');
            return false;
        }

        var realEndDay;

        if (editAllDay.is(':checked')) {
            eventData.start = moment(eventData.start).format('YYYY-MM-DD');
            //render시 날짜표기수정
            eventData.end = moment(eventData.end).add(1, 'days').format('YYYY-MM-DD');
            //DB에 넣을때(선택)
            realEndDay = moment(eventData.end).format('YYYY-MM-DD');

            eventData.allDay = true;
        }

        $("#calendar").fullCalendar('renderEvent', eventData, true);
        eventModal.find('input, textarea').val('');
        editAllDay.prop('checked', false);
        eventModal.modal('hide');

        //새로운 일정 저장
        $.ajax({
            type: "get",
            url: "insertCalendarEnd.do?",
            data: {
                title: eventData.title,
                description: eventData.description,
                start: eventData.start,
                end: eventData.end,
                type: eventData.type,
                username: eventData.username,
                backgroundColor: eventData.backgroundColor,
                textColor: eventData.textColor,
                allDay: eventData.allDay
            },
            dataType :'json',
            success: function (response) {
            	console.log(response);
                $('#calendar').fullCalendar('removeEvents');
                $('#calendar').fullCalendar('refetchEvents');
                
                if(response != null) {
                	$("#myTable2").find(".rooo").remove();
        				tr = $('<tr class="dataTable1"/>');
        				tr.append("<td>" + response.title + "</td>");
        				tr.append("<td>" + response.type + "</td>");
        		        $('#myTable2').append(tr);
                    	alert("일정 등록 성공");
                } else {
                	alert("일정 등록 실패");
                }
            }
        });
    });
};
