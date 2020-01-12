

$(document).ready(function () {
    'use strict';

    var imageIndex = 0,
        mark = 'mark',
        sentence = 'sentence',
        dotMarks = 'dotMarks',
        singleRecMark = 'singleRecMark',
        recGroupMarks = 'recGroupMarks',
        newMark = {},
        $canvas = $('#mark-canvas'),

        $pictureList = $('#picture-list'), //界面上方显示图片列表的面板
        $previousBtn = $('#pic-previous'),
        $nextBtn = $('#pic-next'),
       // $directPager = $('#direct-pager'),  //图片列表的分页器
        $scrollable = $('#scrollable'),  //可伸缩区域
        $currentPicPointer = $('#current-pic-pointer'),

        $tagList = $('#tag-list'),  //画布右侧显示tag的区域
        $tagContent = $('#tag-content'), //添加tag的下拉菜单区域
        $sentenceContent = $('#sentence-content'), //添加整体描述的下拉菜单区域
        $addTagButton = $('#add-tag-btn'),  //添加标签按钮
        $modePrompt = $('#mode-prompt'), //当前绘图工具提示

        $eraser = $('#eraser'),

        $savePrompt = $('#save-prompt'),
        $savePromptYesBtn = $('#save-prompt-yes-btn'),
        $savePromptNoBtn = $('#save-prompt-no-btn'),

        projectId = $('#project-id').val(),
        imgNum = parseInt($('#img-num').val()),

        eachRowPicNum = 6,   //一行上的图片数量
        eachPageRowNum = 3,  //一页上的行数

        imageRootPath = '/data/ImageFlow/',

        baseName = 'rec',
        layerIndex = 0,
        inGroupIndex = -1,
        groupId = -1,
        groupColor = [],

        isSaved = true,

        color,
        imageId, //当前图片的id
        picInfo, //当前显示的图片的信息
        imageAcquired
    ;

    function drawDot(x, y, r, style, isLayer) {
        //画点
        //x: 点的横坐标
        //y: 点的纵坐标
        //r: 点的半径
        //style: 点的颜色
        r = r || 3;
        style = style || 'red';
        isLayer = isLayer || true;
        $canvas.drawArc({
            layer: isLayer,
            fillStyle: style,
            x: x,
            y: y,
            radius: r
        });
    }

    function drawRec(x, y, width, height, layerName, strokeStyle, isLayer, strokeWidth, isFromCenter, isDraggable) {
        strokeStyle = strokeStyle || 'red';
        strokeWidth = strokeWidth ||4;
        isLayer = isLayer || true;
        isDraggable = isDraggable || true;
        $canvas.drawRect({
            layer: isLayer,
            name: layerName,
            x: x,
            y: y,
            width: width,
            height: height,
            strokeStyle: strokeStyle,
            strokeWidth: strokeWidth,
            fromCenter: isFromCenter,
            draggable: isDraggable
        });
    }

    function getCanvasLocation(x, y) {
        return {
            x: x - $canvas.offset().left - parseFloat($canvas.css('border-left-width')),
            y: y - $canvas.offset().top - parseFloat($canvas.css('border-left-width'))
        };
    }

    function randomColor() {
        var r = Math.floor(Math.random() * 256);
        var g = Math.floor(Math.random() * 256);
        var b = Math.floor(Math.random() * 256);
        return 'rgb(' + r + ',' + g + ',' + b + ')';
    }

    function drawNewPicture($pic) {
        imageId = $pic.attr('id');
        newMark = {};

        //清空当前画布、标记、描述内容
        $canvas.removeLayers().drawLayers();
        $tagList.html("");
        $tagContent.html("");
        $sentenceContent.html("");

        //重新绘制图像
        $canvas.drawImage({
            source: $pic.attr("src"),
            layer: true,
            x: 0,
            y: 0,
            fromCenter: false
        });

        //获得该图片存在数组中的信息
        imageIndex = $pic.attr('name');
        picInfo = imageAcquired[imageIndex];

        if (picInfo.mark) {
            newMark = picInfo.mark;

            if (newMark.sentence){
                //如果有整体描述, 则在整体描述下拉框显示内容
                $sentenceContent.text(newMark.sentence.rawStr);
            }

            if (newMark.dotMarks){
                //有点标记
                $.each(newMark.dotMarks, function (groupIndex, groupContent) {
                    //遍历数组显示点标记
                    $.each(groupContent.positions, function (positionIndex, positionContent) {
                        //遍历一组点标记中点的位置画点
                        drawDot(positionContent.x, positionContent.y);
                    });

                    if (groupContent.tag !== ''){
                        var tagShow =
                            '<p>Point mode:</p>' +
                            '<textarea id="point-tag-show" rows="5">' +
                            groupContent.tag +
                            '</textarea>';
                        $tagList.append(tagShow);
                        $("#point-tag-show").on('blur', function () {
                            newMark.dotMarks[0].tag = $(this).val();
                        });
                    }
                });
            }

            if (newMark.singleRecMark){
                //如果单框模式存在
                drawRec(newMark.singleRecMark.startDot.x, newMark.singleRecMark.startDot.y,
                    newMark.singleRecMark.width, newMark.singleRecMark.height, 'single-rec');

                if (newMark.singleRecMark.tag !== ''){
                    var tagShow =
                        '<p>Single rectangle mode:</p>' +
                        '<textarea id="single-rec-tag-show" rows="5">' +
                        newMark.singleRecMark.tag +
                        '</textarea>';
                    $tagList.append(tagShow);
                    $("#single-rec-tag-show").on('blur', function () {
                        newMark.singleRecMark.tag = $(this).val();
                    });
                }
            }

            if (newMark.recGroupMarks){
                //多框模式
                $.each(newMark.recGroupMarks, function (groupIndex, groupContent) {
                    //遍历多框标记数组
                    color = randomColor();
                    groupColor.push(color);

                    $.each(groupContent.rectangleMarks, function (positionIndex, positionContent) {
                        //遍历每个多框标记中框的位置数组
                        drawRec(positionContent.startDot.x, positionContent.startDot.y,
                            positionContent.width, positionContent.height, baseName + layerIndex, color);
                        layerIndex ++;
                    });

                    if (groupContent.tag !== ''){
                        var tagShow =
                            '<div id="group' + groupIndex + '">' +
                            'Multi rectangle mode:'+
                            '<textarea id="'+groupIndex+'" class="multi-rec-tag-show" rows="5" style="color:' + color + '">' +
                            groupContent.tag +
                            '</textarea>' +
                            '</div>';
                        $tagList.append(tagShow);

                        $(".multi-rec-tag-show").blur(function () {
                            newMark.recGroupMarks[groupIndex].tag = $(this).val();
                        });
                    }

                });

                groupId = newMark.recGroupMarks.length - 1;

                if (newMark.recGroupMarks[groupId].tag === ''){
                    inGroupIndex = newMark.recGroupMarks[groupId].rectangleMarks.length - 1;
                }
            }

        }
    }
    /***********************************************************************************************/
    /*保存图片*/
    function saveImage() {
        imageAcquired[imageIndex].mark = newMark;

        var image = imageAcquired[imageIndex];
        console.log(image);

        $.ajax({
            data :JSON.stringify(image),
            type:"post",
            url: "/worker/saveImage",
            contentType:"application/json; charset=utf-8",
            dataType:"json",
            success:function(data){
                if (data){
                    isSaved = true;
                }
            }
        })
    }

    /***********************************************************************************************/
    /*展示一页图片*/
    function showOnePagePics($div, pictures, eachPageRowNum, eachRowPicNum) {
        $div.html('');
        var  rowPicPointer, pageRowPointer, count = 0;
        rowLoop:
            for (pageRowPointer = 0; pageRowPointer < eachPageRowNum; pageRowPointer ++){
                var row = '<div class="row">';
                for (rowPicPointer = 0; rowPicPointer < eachRowPicNum; rowPicPointer ++){
                    if (count === pictures.length){
                        row += '</div>';
                        $div.append(row);
                        break rowLoop;
                    }

                    row +=
                        '<div class="col-md-2"><img id="' + pictures[count].imageId + '" name="' + count + '" src="' + imageRootPath + pictures[count].fileName + '" class="thumbnail img-responsive picture"></div>';

                    count ++;
                }
                row += '</div>';
                $div.append(row);
            }

        /*当某一张图的缩略图被点击时，在画板上显示这张图片以及这张图片的标记（如果有的话）*/
        $('.picture').on('click', function () {
            var $pic = $(this);
            if (isSaved){
                drawNewPicture($pic);
            }else{
                $savePrompt.slideDown();

                $savePromptYesBtn.on('click', function () {
                    saveImage();
                    drawNewPicture($pic);
                    $savePrompt.slideUp();
                });

                $savePromptNoBtn.on('click', function () {
                    drawNewPicture($pic);
                    isSaved = true;
                    $savePrompt.slideUp();
                })
            }
        });
    }
    /********************************************************************************************************************************************************/
    /*伸缩图片列表面板数字跳转*/
    /*
    var pagerNum = imgNum / (eachRowPicNum * eachPageRowNum) + 1;
    for (var pagerIndex = 1; pagerIndex <= pagerNum; pagerIndex ++){
        $directPager.append(
            '<option>' + pagerIndex + '</option>'
        );
    }
    */
    /********************************************************************************************************************************************************/
    /*在伸缩面板展示图片*/
    var onePagePicNum = eachPageRowNum * eachRowPicNum;
    var currentPicPointer = parseInt($currentPicPointer.val());

    $previousBtn.on('click', function () {
        if (currentPicPointer > 0)
            currentPicPointer -= onePagePicNum;

        $.ajax({
            type: 'get',
            url: '/worker/images' + '?username=' + $.cookie('username') + '&projectId=' + projectId + '&begin=' + currentPicPointer + '&num=' + onePagePicNum,
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(pics){
                imageAcquired = pics;

                showOnePagePics($pictureList, pics, eachPageRowNum, eachRowPicNum);

                $nextBtn.attr('disabled', false).removeClass('btn-disabled');

                if (currentPicPointer === 0){
                    $(this).attr('disabled', true).addClass('btn-disabled');
                }

                $currentPicPointer.attr('value', currentPicPointer);
            }
        });
    });

    $nextBtn.on('click', function () {
        currentPicPointer += onePagePicNum;

        $.ajax({
            type: 'get',
            url: '/worker/images' + '?username=' + $.cookie('username') + '&projectId=' + projectId + '&begin=' + currentPicPointer + '&num=' + onePagePicNum,
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(pics){
                imageAcquired = pics;

                showOnePagePics($pictureList, pics, eachPageRowNum, eachRowPicNum);

                $previousBtn.attr('disabled', false).removeClass('btn-disabled');

                if (pics.length < onePagePicNum){
                    $(this).attr('disabled', true).addClass('btn-disabled');
                }

                $currentPicPointer.attr('value', currentPicPointer);
            }
        });
    });

    $previousBtn.click();
    /********************************************************************************************************************************************************/
    /*伸缩图片列表面板*/
    $('#slide-btn').on('click', function () {
        $scrollable.slideToggle("slow");
    });
    /********************************************************************************************************************************************************/
    /*描点模式*/
    $("#point").on('click', function () {
        if ($modePrompt.text() !=='Point mode'){
            $modePrompt.text('Point mode');

            $canvas.css('cursor', 'default');

            //解除别的事件绑定
            $canvas.unbind('click');
            $canvas.unbind('mousedown');
            $canvas.unbind('mousemove');
            $canvas.unbind('mouseup');

            $canvas.on('click', function (event) {
                var location = getCanvasLocation(event.pageX, event.pageY);//获取鼠标点击位置

                drawDot(location.x, location.y);//画点

                //将点的位置记录到newMark
                if (!(dotMarks in newMark)){
                    //如果新标记中还没有dotMarks元素，则添加dotMarks数组
                    newMark.dotMarks = [
                        {
                            'tag': '',
                            'positions': []
                        }
                    ];
                }

                newMark.dotMarks[0].positions.push({
                    'x': location.x,
                    'y': location.y
                });

                isSaved = false;
            });

            //增加标签
            $addTagButton.unbind('click').on('click', function () {
                newMark.dotMarks[0].tag = $tagContent.val();

                var tagShow =
                    '<p>Point mode:</p>'+
                    '<textarea id="point-tag-show" rows="5">' +
                    $tagContent.val() +
                    '</textarea>';
                $tagList.append(tagShow);

                $('#point-tag-show').blur(function () {
                    newMark.dotMarks[0].tag = $(this).val();
                });
            });

            //描点模式下的橡皮擦功能
            $eraser.unbind('click').on('click', function () {
                $modePrompt.text("Point mode: eraser");

                $canvas.css('cursor', 'url("../img/eraser-cursor.png")');

                $canvas.unbind('click').on('click', function (event) {
                    if(dotMarks in newMark){
                        var location = getCanvasLocation(event.pageX, event.pageY);

                        var i = newMark.dotMarks[0].positions.length - 1;
                        while (i >= 0){
                            if (Math.abs(newMark.dotMarks[0].positions[i].x - location.x) < 5 && Math.abs(newMark.dotMarks[0].positions[i].y - location.y) < 5){
                                newMark.dotMarks[0].positions.splice(i, 1);
                                $canvas.removeLayer(i + 1).drawLayers();
                            }
                            i --;
                        }

                        if (newMark.dotMarks[0].positions.length === 0){
                            delete newMark.dotMarks;
                            $tagList.html('');
                        }

                        isSaved = false;
                    }
                });
            });
        }
    });  //描点模式结束
    /********************************************************************************************************************************************************/
    /*单框模式*/
    $('#single-square').on('click', function () {  //单框模式
        if ($modePrompt.text() !== 'Single rectangle mode'){
            $modePrompt.text('Single rectangle mode');
            $canvas.unbind('click');
            $canvas.unbind('mousedown');
            $canvas.unbind('mousemove');
            $canvas.unbind('mouseup');

            $canvas.css('cursor', 'crosshair');

            var baseName = 'rec';
            var initX;
            var initY;

            $canvas.mousedown(function (event) {
                var locationStart = getCanvasLocation(event.pageX, event.pageY);
                initX = locationStart.x;
                initY = locationStart.y;

                drawRec(initX, initY, 0, 0, baseName);

                $canvas.mousemove(function (ev) {
                    var locationMove = getCanvasLocation(ev.pageX, ev.pageY);
                    $canvas.removeLayer(baseName).drawLayers();
                    drawRec(initX, initY, locationMove.x - initX, locationMove.y - initY, baseName);
                });
            });

            $canvas.mouseup(function (ev) {
                $canvas.unbind("mousemove");

                var locationEnd = getCanvasLocation(ev.pageX, ev.pageY);

                if (locationEnd.x !== initX || locationEnd.y !== initY){
                    $canvas.removeLayer(baseName).drawLayers();
                    drawRec(initX, initY, locationEnd.x - initX, locationEnd.y - initY, baseName);

                    if(!(singleRecMark in newMark)){
                        newMark[singleRecMark] = {
                            'startDot': {
                                'x': 0,
                                'y': 0
                            },
                            'width': 0,
                            'height': 0,
                            'tag': ''
                        }
                    }

                    newMark.singleRecMark.startDot.x = initX;
                    newMark.singleRecMark.startDot.y = initY;
                    newMark.singleRecMark.width = locationEnd.x - initX;
                    newMark.singleRecMark.height = locationEnd.y - initY;

                    isSaved = false;
                }
            });

            //增加标记
            $addTagButton.unbind('click').on('click', function () {
                newMark.singleRecMark.tag = $tagContent.val();

                var tagShow =
                    '<p>Single rectangle mode:</p>'+
                    '<textarea id="single-rec-tag-show" rows="5">' +
                    $tagContent.val() +
                    '</textarea>';
                $tagList.append(tagShow);

                $("#single-rec-tag-show").blur(function () {
                    if(singleRecMark in newMark){
                        newMark.singleRecMark.tag = $(this).val();
                        console.log(newMark.singleRecMark.tag);
                    }
                });
            });

            //橡皮擦功能
            $eraser.unbind('click').click(function () {
                if (singleRecMark in newMark){
                    $canvas.removeLayer(baseName).drawLayers();
                    $tagList.html("");
                    delete newMark.singleRecMark;

                    isSaved = false;
                }
            });
        }
    });
    /********************************************************************************************************************************************************/
    /*多框模式*/
    $("#multi-square").on('click', function () {  //多框模式
        if($modePrompt.text() !== 'Multi rectangle mode'){
            //如果点击多框模式按钮时，并不在多框模式下，则执行以下代码，否则什么也不做
            $modePrompt.text('Multi rectangle mode');
            $canvas.unbind("click");
            $canvas.unbind("mousedown");
            $canvas.unbind("mousemove");
            $canvas.unbind("mouseup");

            $canvas.css("cursor", "crosshair");

            var layerName = '';
            var initX;
            var initY;

            if (!color){
                //如果颜色不存在，即这张图片从未有过多框标记，或这张图片上一次保存前的最后一次操作正好是addTag
                color = randomColor();
            }


            $canvas.mousedown(function (event) {
                var locationStart = getCanvasLocation(event.pageX, event.pageY);
                initX = locationStart.x;
                initY = locationStart.y;

                layerName = baseName + layerIndex;

                drawRec(initX, initY, 0, 0, layerName, color);

                $canvas.mousemove(function (ev) {
                    var locationMove = getCanvasLocation(ev.pageX, ev.pageY);
                    $canvas.removeLayer(layerName).drawLayers();
                    drawRec(initX, initY, locationMove.x - initX, locationMove.y - initY, layerName, color);
                });
            });

            $canvas.mouseup(function (ev) {
                $canvas.unbind("mousemove");

                var locationEnd = getCanvasLocation(ev.pageX, ev.pageY);
                $canvas.removeLayer(layerName).drawLayers();

                if (initX !== locationEnd.x || initY !== locationEnd.y){  //如果鼠标发生位移，才记录这个矩形

                    drawRec(initX, initY, locationEnd.x - initX, locationEnd.y - initY, layerName, color);

                    if (!(recGroupMarks in newMark))
                        newMark[recGroupMarks] = [];

                    if (inGroupIndex === -1){ //如果这一组还不存在
                        groupId ++;
                        groupColor.push(color);

                        newMark.recGroupMarks.push(
                            {
                                'groupId': groupId,
                                'rectangleMarks': [],
                                'tag': ''
                            }
                        );
                    }
                    newMark.recGroupMarks[groupId].rectangleMarks.push({
                        "startDot":{
                            "x": initX,
                            "y":initY
                        },
                        "width": locationEnd.x - initX,
                        "height": locationEnd.y - initY
                    });

                    inGroupIndex ++;
                    layerIndex ++;
                }

                isSaved = false;
            });

            //添加tag
            $addTagButton.unbind('click').on('click', function () {

                if (inGroupIndex !== -1){
                    //如果这一组已经存在，那可以添加标记
                    var tag = $tagContent.val();

                    newMark.recGroupMarks[groupId].tag = tag;

                    if ($('#group' + groupId).length > 0){
                        $('#' + groupId).text(tag);
                    } else {
                        var tagShow =
                            '<div id="group' + groupId + '">' +
                            'Multi rectangle mode:'+
                            '<textarea id="'+groupId+'" class="multi-rec-tag-show" rows="5" style="color:' + color + '">' +
                            tag +
                            '</textarea>' +
                            '</div>';
                        $tagList.append(tagShow);

                        $(".multi-rec-tag-show").blur(function () {
                            newMark.recGroupMarks[groupId].tag = $(this).val();
                            console.log(newMark.recGroupMarks[groupId].tag);
                        });
                    }

                    color = randomColor();
                    inGroupIndex = -1;

                    isSaved = false;
                }

                //如果不存在那么什么也不做
            });

            //多框模式下的橡皮擦功能
            $eraser.unbind('click').click(function () {
                if (recGroupMarks in newMark){
                    layerIndex --;
                    $canvas.removeLayer(baseName + layerIndex).drawLayers();
                    if (inGroupIndex === -1){
                        inGroupIndex = newMark.recGroupMarks[groupId].rectangleMarks.length - 1;
                    }
                    inGroupIndex --;

                    //console.log('layerIndex:' + layerIndex);
                    //console.log('inGroupIndex:' + inGroupIndex);
                    //console.log('groupId:' + groupId);

                    if (layerIndex <= 0){
                        //如果所有矩形都被删除了
                        $('#group'+groupId).remove();
                        delete newMark.recGroupMarks;
                        //console.log(newMark);
                    } else if (layerIndex > 0 && inGroupIndex === -1){
                        //如果这一组矩形被全删掉了
                        newMark.recGroupMarks.splice(groupId, 1);
                        $('#group'+groupId).remove();
                        groupColor.pop();  //删除这一组的颜色

                        groupId --;

                        color = groupColor[groupId]; //颜色变为上一组的颜色

                        inGroupIndex = newMark.recGroupMarks[groupId].rectangleMarks.length - 1;
                    } else {
                        newMark.recGroupMarks[groupId].rectangleMarks.splice(inGroupIndex, 1);
                    }

                    isSaved = false;
                }
            });

        }
    });

    //添加整体描述
    $("#add-sentence-btn").click(function () {
        if (!(sentence in newMark))
            newMark[sentence] =
                {
                    'rawStr': '',
                    'tokens': []
                };

        newMark.sentence.rawStr = $sentenceContent.val();

        isSaved = false;
    });

    //保存图片
    $("#save-btn").click(function () {
        saveImage();
    });

});