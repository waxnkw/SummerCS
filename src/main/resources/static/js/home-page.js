

$(document).ready(function () {
    var $projectArea = $("#project-area"),
        $username = $('#username');

    /*******************************************************************************************************************/
    /*显示当前登陆用户*/
    $username.html($.cookie('username'));
    /*******************************************************************************************************************/
    /*显示项目列表*/
    $.ajax({
        type: 'get',
        url: '/worker/proj/canJoinedProj',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function(projectInfo){
            if (projectInfo){
                $projectArea.html('');

                var panels = '';
                $.each(projectInfo, function (index, content) {

                    var random = Math.floor(Math.random()*5+1);

                    var each =
                        '<div class="col-md-6">'+
                            '<div class="panel panel-default">'+
                                '<div class="panel-heading">' +
                                    '<h4><a href="/homeproject?proId=' + content.projectId + '">' + content.projectName + '</a></h4>'+
                                '</div>' +
                                '<div class="panel-body">'+
                                    '<p class="description-overflow">' +
                                    content.description +
                                    '</p>' +
                                    '<div class="row">' +
                                        '<div class="col-md-6">' +
                                            '<p class="pull-left text-lighter">' + content.createDate + '</p>' +
                                        '</div>' +
                                        '<div class="col-md-6">' +
                                            '<p class="text-lighter pull-right">Launcher: ' + content.launcherName + '</p>' +
                                        '</div>' +
                                    '</div>' +
                                '</div>'+
                            '</div>'+
                        '</div>';
                    if (index % 2 === 0){
                        each = '<div class="row">'+ each;
                    }else {
                        each += '</div>';
                    }

                    panels += each;
                });

                if (projectInfo.length % 2 ===1)
                    panels += '</div>';

                $projectArea.append(panels);
            }
        }
     });


});