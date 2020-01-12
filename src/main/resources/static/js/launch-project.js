
$(document).ready(function () {
    'use strict';

    var $username = $('#username'),

        $proNameInput = $('#pro-name-input'),  //项目名称
        $projectDescriptionInput = $('#pro-description-textarea'), //项目描述
        $currentLength = $('#current-length'),  //展示项目名称字数的地方

        $rewardChoicesRadio = $(':radio[name="reward-choices"]'),  //选择积分模式的radio按钮

        $rewardAnywayFieldset = $('#reward-anyway-fieldset'),  //不评价就奖励模式
        $rewardAnywayCredit = $('#reward-anyway-credit'),

        $autoEstimateFieldset = $('#auto-estimate-fieldset'),   //自动评价模式
        $rewardAnywayFieldsetLabel = $('#reward-anyway-fieldset-label'),
        $autoEstimateFieldsetLabel = $('.auto-estimate-fieldset-label'),
        $eachPicMarker = $('#each-pic-marker'),
        $excellentCredit = $('#excellent-credit'),
        $goodCredit = $('#good-credit'),
        $underAverageCredit = $('#under-average-credit'),

        //警告条们
        $rewardAnywayCreditAlert = $('#reward-anyway-credit-alert'),
        $autoEstimateExcellentAlert = $('#auto-estimate-excellent-alert'),
        $autoEstimateGoodAlert = $('#auto-estimate-good-alert'),
        $autoEstimateUnderaverageAlert = $('#auto-estimate-underaverage-alert'),
        $eachPicMarkerAlert = $('#each-pic-marker-alert'),
        $launchAlert = $('#launch-alert'),

        $launchBtn = $('#launch-btn'),   //发布项目按钮

        //数据是否有效
        rewardAnywayCreditIsValid = false,
        autoEstimateExcellentIsValid = false,
        autoEstimateGoodIsValid = false,
        autoEstimateUnderaverageIsValid = false,
        eachPicMarkerIsValid = false,

        rewardMode
        ;

    /*******************************************************************************************************************/
    /*显示当前登陆用户*/
    $username.html($.cookie('username'));
    /*********************************************************************************************/
    /*更改积分模式fieldset状态*/
    //使Reward anyway积分模式的fieldset可以使用
    function enableRewardAnywayFieldset() {
        $rewardAnywayFieldset.attr('disabled', false);
        $rewardAnywayFieldsetLabel.removeClass('text-lighter');
    }

    //使Reward anyway积分模式的fieldset无法使用
    function disableRewardAnywayFieldset() {
        $rewardAnywayFieldset.attr('disabled', true);
        $rewardAnywayFieldsetLabel.addClass('text-lighter');
    }

    //使Auto estimate积分模式的fieldset可以使用
    function enableAutoEstimateFieldset() {
        $autoEstimateFieldset.attr('disabled', false);
        $autoEstimateFieldsetLabel.removeClass('text-lighter');
    }

    //使Auto estimate积分模式的fieldset无法使用
    function disableAutoEstimateFieldset() {
        $autoEstimateFieldset.attr('disabled', true);
        $autoEstimateFieldsetLabel.addClass('text-lighter');
    }
    /*********************************************************************************************/
    /*监听项目名称输入长度*/
    $proNameInput.bind("propertychange input", function () {

        var len = $(this).val().length;
        $currentLength.text(len);
    });
    /*********************************************************************************************/
    /*监听选择模式radio按钮的变化*/
    /*当选择某一模式时，该模式需填写的信息设为可用，其他模式的信息设为不可用*/
    $rewardChoicesRadio.change(function () {
        rewardMode = $(this).val();
        if (rewardMode === 'reward-anyway'){
            enableRewardAnywayFieldset();
            disableAutoEstimateFieldset();
            $autoEstimateExcellentAlert.fadeOut();
            $autoEstimateGoodAlert.fadeOut();
            $autoEstimateUnderaverageAlert.fadeOut();
        }else if (rewardMode === 'auto-estimate'){
            disableRewardAnywayFieldset();
            enableAutoEstimateFieldset();
            $rewardAnywayCreditAlert.fadeOut();
        }
    });
    /*********************************************************************************************/
    /*输入规范性监控*/
    $rewardAnywayCredit.blur(function () {
        if (!parseInt($(this).val())){
            $rewardAnywayCreditAlert.fadeIn();
            rewardAnywayCreditIsValid = false;
        }else{
            $rewardAnywayCreditAlert.fadeOut();
            rewardAnywayCreditIsValid = true;
        }
        $launchAlert.slideUp();
    });

    $excellentCredit.blur(function () {
        if (!parseInt($(this).val())){
            $autoEstimateExcellentAlert.fadeIn();
            autoEstimateExcellentIsValid = false;
        }else{
            $autoEstimateExcellentAlert.fadeOut();
            autoEstimateExcellentIsValid = true;
        }
        $launchAlert.slideUp();
    });

    $goodCredit.blur(function () {
        if (!parseInt($(this).val())){
            $autoEstimateGoodAlert.fadeIn();
            autoEstimateGoodIsValid = false;
        }else{
            $autoEstimateGoodAlert.fadeOut();
            autoEstimateGoodIsValid = true;
        }
        $launchAlert.slideUp();
    });

    $underAverageCredit.blur(function () {
        if (!parseInt($(this).val())){
            $autoEstimateUnderaverageAlert.fadeIn();
            autoEstimateUnderaverageIsValid = false;
        }else{
            $autoEstimateUnderaverageAlert.fadeOut();
            autoEstimateUnderaverageIsValid = true;
        }
        $launchAlert.slideUp();
    });

    $eachPicMarker.blur(function () {
        if (!parseInt($(this).val())){
            $eachPicMarkerAlert.fadeIn();
            eachPicMarkerIsValid = false;
        }else{
            $eachPicMarkerAlert.fadeOut();
            eachPicMarkerIsValid = true;
        }
        $launchAlert.slideUp();
    });
    /*********************************************************************************************/
    /*发布项目按钮监听*/
    $launchBtn.on('click', function () {
        var projectName = $proNameInput.val();
        var projectDescription = $projectDescriptionInput.val();
        var eachPicMarkerNum = parseInt($eachPicMarker.val());

        if(projectName !== '' && projectDescription !== '' && eachPicMarkerIsValid){
            if (rewardMode === 'reward-anyway'){
                if (rewardAnywayCreditIsValid){
                    var rewardAnywayCredit = parseInt($rewardAnywayCredit.val());

                    if (rewardAnywayCredit > 0){
                        var project =
                            {
                                'projectName': projectName,
                                'description': projectDescription,
                                'launcher':  $.cookie('username'),
                                'projectType': 'SIMPLE',
                                'canBeJoined': true,
                                'eachCredit': rewardAnywayCredit,
                                'upUserLimit': eachPicMarkerNum
                            };

                        $.ajax({
                            data: JSON.stringify(project),
                            type: 'post',
                            url: '/launcher/launch/simple?username=' + $.cookie('username'),
                            contentType: 'application/json; charset=utf-8',
                            dataType: 'text',
                            success: function(projectId){
                                window.location = '/launcherproject?proId=' + projectId;
                            }
                        });
                    }else {

                    }
                }else{
                    $launchAlert.slideDown();
                }
            }else if (rewardMode === 'auto-estimate'){
                var excellentCredit = $excellentCredit.val();
                var goodCredit = $goodCredit.val();
                var underAverageCredit = $underAverageCredit.val();
            }
        }else{
            $launchAlert.slideDown();
        }
    });
});