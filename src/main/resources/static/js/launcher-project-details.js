

$(document).ready(function () {
    'use strict';

    var $username = $('#username'),

        $projectName = $('#project-name'),
        $projectDescription = $('#project-description'),

        projectId = $projectName.attr('name'),

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

        $closeProBtn = $('#close-pro-btn'),  //关闭项目
        $closeModal = $('#close-modal'),

        $uploadPicBtn = $('#upload-pic-btn'), //上传图片
        $picUpload = $('#pic-upload'),
        $uploadPicArea = $('#upload-pic-area'),

        eachRowPicNum = 6,   //一行上的图片数量
        eachPageRowNum = 3,  //一页上的行数
        eachPageRankingNum = 15,  //一页上的排名数
        completeRatio
        ;

    /*******************************************************************************************************************/
    /*显示当前登陆用户*/
    $username.html($.cookie('username'));
    /***************************************************************************************************/
    /*图片翻页*/
    picDuoPagination($previousPicBtn, $nextPicBtn, $projectPic, '/launcher/listLauncherImages', eachPageRowNum, eachRowPicNum, $currentPicPointer, $.cookie('username'), projectId);
    /***************************************************************************************************/
    /*排名翻页*/
    rankingDuoPagination($previousRankingBtn, $nextRankingBtn, $rankingList, '/rank/list', eachPageRankingNum, $currentRankingPointer, $.cookie('username'), projectId);
    /***************************************************************************************************/
    /*关闭项目*/
    $closeProBtn.click(function () {
        $.ajax({
            type: 'get',
            url: '/launcher/close?projectId=' + projectId,
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function(isClosed){
                console.log('closed');
                if (isClosed){
                    $closeModal.modal('hide');
                }
            }
        });
    });
    /***************************************************************************************************/
    /*上传图片*/
    $uploadPicBtn.click(function () {
        $uploadPicArea.slideToggle();
    });

    $picUpload.fileinput({
        method: 'post',
        uploadUrl: '/launcher/upload?projectId=' + projectId,
        allowedFileExtensions : ['zip'],
        overwriteInitial: false
        //maxFileSize: 100000000000000,
        //maxFilesNum: 10
       // allowedFileTypes: ['image'],
    });
    /***************************************************************************************************/
    /*初始化页面信息*/
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
        }
    });

    $previousPicBtn.click();   //项目图片

    $previousRankingBtn.click(); //项目参与者积分排名
    /***************************************************************************************************/

});