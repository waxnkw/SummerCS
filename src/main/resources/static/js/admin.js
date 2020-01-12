
$(document).ready(function () {
    'use strict';

    var $totalUserNum = $('#total-user-num'),
        $doneNum = $('#done-num'),
        $doingNum = $('#doing-num')
    ;

    alert('为了方便检查，这次以链接的形式直接呈现管理员界面');

    $.ajax({
        type: 'get',
        url: '/admin/userNum',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function(userNum){
            $totalUserNum.text(userNum);
        }
    });

    $.ajax({
        type: 'get',
        url: '/admin/projectStatistic',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false,
        success: function(info){
            $doneNum.text(info.doneNum);
            $doingNum.text(info.doingNum);
        }
    });

});