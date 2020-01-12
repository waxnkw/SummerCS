

$(document).ready(function () {
    'use strict';

    var $landingTextDiv = $('#landing-text-div'),
        $landingDec = $('#landing-dec'),
        
        $signInUsername = $('#sign-in-username'),  //登陆
        $signInPassword = $('#sign-in-password'),
        $signInBtn = $('#sign-in-btn'),
        $signInError = $('#sign-in-error'),
        
        $signUpUsername = $('#sign-up-username'),  //注册
        $signUpEmail = $('#sign-up-email'),
        $signUpPassword = $('#sign-up-password'),
        $signUpPasswordAgain = $('#sign-up-password-again'),
        $signUpBtn = $('#sign-up-btn'),
        $signUpError = $('#sign-up-error'),

        username,
        password,
        email,
        passwordAgain
    ;

    /*****************************************************************************************/
    /*首页动画*/
    $landingTextDiv.slideDown("slow");
    $landingDec.fadeIn(2000);
    /*****************************************************************************************/
    /*登陆*/
    $signInBtn.on('click', function () {
        username = $signInUsername.val();
        password = $signInPassword.val();

        $.ajax({
            type: 'post',
            url: '/login?logStr=' + username + '&password=' + password,
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function(isLoggedIn){
                if (isLoggedIn === true){
                    window.location = '/homepage';
                    $.cookie('username', username);
                }else{
                    $signInError.slideDown();
                }
            }
        });
    });
    /*****************************************************************************************/
    /*注册*/
    $signUpBtn.on('click', function () {
        username = $signUpUsername.val();
        email = $signUpEmail.val();
        password = $signUpPassword.val();
        passwordAgain = $signUpPasswordAgain.val();

        if (password !== passwordAgain){
            $signUpError.html('The two passwords disagree with each other.').slideDown();
        }else{
            var user = {
                'username': username,
                'password': password,
                'email': email
            };

            $.ajax({
                data: JSON.stringify(user),
                type: 'post',
                url: '/register',
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                success: function(isSignedUp){
                    if (isSignedUp === true){
                        window.location = '/homepage';
                        $.cookie('username', username);
                    }else{
                        $signUpError.html('The username or email has been used.').slideDown();
                    }
                }
            });

        }

    });
    /*****************************************************************************************/
    
});