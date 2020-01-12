$(document).ready(function () {
    var $myProArea = $('#my-pro-area'),
        $username = $('#username')
    ;

    /*******************************************************************************************************************/
    /*显示当前登陆用户*/
    $username.html($.cookie('username'));
    /*******************************************************************************************************************/
    /*显示项目列表*/
    $.ajax({
        type: 'get',
        url: '/launcher/listLaunchedProjs?username=' + $.cookie('username'),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function(projectInfo){
            if (projectInfo){
                $myProArea.html('');
                $.each(projectInfo, function (index, content) {
                    var eachpro =
                        '<div class="row project-list-gap">' +
                            '<div class="col-md-2">' +
                                '<img src="/img/colorIcon1.png">' +
                            '</div>' +
                            '<div class="col-md-10">' +
                                '<div class="row">' +
                                    '<h4 class="pull-left"><a href="/launcherproject?proId=' + content.projectId + '">'+content.projectName+'</a></h4>' +
                                '</div>' +
                                '<div class="row">' +
                                    '<p class="text-lighter description-overflow pull-left">' + content.description + '</p>' +
                                '</div>' +
                                '<div class="row">' +
                                    '<div class="col-md-6">' +
                                        '<p class="pull-left text-lighter">' + content.createDate + '</p>' +
                                    '</div>' +
                                    '<div class="col-md-6">' +
                                        '<p class="text-lighter pull-right">Launcher: ' + content.launcherName + '</p>' +
                                    '</div>' +
                                '</div>' +
                            '</div>' +
                        '</div>';

                    $myProArea.append(eachpro);
                });
            }

        }
    });


});