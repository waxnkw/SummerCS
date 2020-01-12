

$(document).ready(function () {

    var $username = $('#username'),

        $projectName = $('#project-name'),
        $projectDescription = $('#project-description'),

        projectId = $projectName.attr('name'),

        $imageLeft = $('.image-left'),

        $projectPic = $('#project-pic'),  //项目图片
        $previousPicBtn = $('#pic-previous'),
        $nextPicBtn = $('#pic-next'),
        $currentPicPointer = $('#current-pic-pointer'),

        $progressbar = $('#progressbar'),  //进度
        $completedText = $('#completed-text'),
        $progressNumber = $('#progress-number'),

        $rankingList = $('#ranking-list'),  //积分排名
        $previousRankingBtn = $('#ranking-previous'),
        $nextRankingBtn = $('#ranking-next'),
        $currentRankingPointer = $('#current-ranking-pointer'),

        $joinProBtn = $("#join-pro-btn"),  //加入项目
        $claimNumberText = $('#claim-number-text'),
        $claimNumberError = $('#claim-number-error'),
        $joinModal = $('#join-modal'),
        $joinInBtn = $('#join-in-btn'),

        eachRowPicNum = 6,   //一行上的图片数量
        eachPageRowNum = 3,  //一页上的行数
        eachPageRankingNum = 15,  //一页上的排名数
        completeRatio
    ;

    /*******************************************************************************************************************/
    /*显示当前登陆用户*/
    $username.html($.cookie('username'));
    /*******************************************************************************************************************/
    /*图片翻页*/
    picDuoPagination($previousPicBtn, $nextPicBtn, $projectPic, '/launcher/listLauncherImages', eachPageRowNum, eachRowPicNum, $currentPicPointer, $.cookie('username'), projectId);
    /*******************************************************************************************************************/
    /*排名翻页*/
    rankingDuoPagination($previousRankingBtn, $nextRankingBtn, $rankingList, '/rank/list', eachPageRankingNum, $currentRankingPointer, $.cookie('username'), projectId);
    /*******************************************************************************************************************/
    /*初始化项目信息*/
    $.ajax({
        type: 'get',
        url: '/launcher/queryLauncherProjInfo?projectId=' + projectId + '&username=' + $.cookie('username'),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function(projectInfo){
            $projectName.html(projectInfo.projectName);   //项目名
            $projectDescription.html(projectInfo.description);    //项目描述
            //完成进度
            var ratio;
            if (projectInfo.totalNumOfImgs){
                ratio = projectInfo.doneNumOfImgs/ projectInfo.totalNumOfImgs;
            }else{
                ratio = 0;
            }
            completeRatio = Math.round(ratio * 100);
            $progressbar.attr('aria-valuenow', completeRatio / 100).attr('style', 'width: ' + completeRatio + '%');
            $completedText.html(completeRatio + '% completed');
            $progressNumber.html(projectInfo.doneNumOfImgs + '/' + projectInfo.totalNumOfImgs);

            if (projectInfo.userIn){
                $joinInBtn.attr('disabled', true).addClass('btn-disabled');
            }
        }
    });

    $previousPicBtn.click();   //项目图片

    $previousRankingBtn.click(); //项目参与者积分排名
    /*******************************************************************************************************************/
    /*加入项目*/
    $claimNumberText.bind('propertychange input', function () {
        //用户编辑认领数量，错误提示就收起
        $claimNumberError.slideUp();
    });

    $joinInBtn.on('click', function () {
        $.ajax({
            type: 'get',
            url: '/proj/imageleft?username=' + $.cookie('username') + '&projectId=' + projectId,
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function(left){
                $imageLeft.text(left);

                $joinProBtn.click(function () {
                    var claimNumber = $claimNumberText.val();

                    var isValid = String(parseInt(claimNumber)) === claimNumber;

                    if (isValid && parseInt(claimNumber) <= left && parseInt(claimNumber) > 0) {

                        $.ajax({
                            type: 'get',
                            url: '/worker/join?projectId=' + projectId + '&username=' + $.cookie('username') + '&claimNumber=' + claimNumber,
                            contentType: 'application/json; charset=utf-8',
                            dataType: 'text',
                            success: function(projectId){
                                $joinModal.modal('hide');
                                window.location = '/workerproject?proId=' + projectId;
                            }
                        });

                    }else {
                        $claimNumberError.slideDown();
                    }
                });
            }
        });
    });


});