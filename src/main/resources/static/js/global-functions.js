/*公用js函数*/

var imageRootPath = '/data/ImageFlow/';

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
                    '<div class="col-md-2"><img src="' + imageRootPath + pictures[count].fileName + '" class="thumbnail img-responsive"></div>';

                count ++;
            }
            row += '</div>';
            $div.append(row);
        }
}
/***********************************************************************************************/
/*展示一页积分排名*/
function showOnePageRankings($div, rankings, onePageRankingNum, currentRankingPointer) {
    $div.html('');
    var rankPointer;
    var table =
        '<table class="table">' +
        '<thead>' +
        '<tr>' +
        '<th>Ranking</th>' +
        '<th>Username</th>' +
        '<th>Credits</th>' +
        '</tr>' +
        '</thead>' +
        '<tbody>'
    ;


    for (rankPointer = 0; rankPointer < onePageRankingNum; rankPointer ++){
        if (rankPointer === rankings.length){
            table += '</tbody></table>';
            break;
        }

        var rank = currentRankingPointer + rankPointer + 1;

        table +=
            '<tr>' +
            '<td>' + rank + '</td>' +
            '<td>' + rankings[rankPointer]['name'] + '</td>' +
            '<td>' + rankings[rankPointer]['credit'] + '</td>' +
            '</tr>';
    }

    $div.append(table);
}
/***********************************************************************************************/
/*双向图片翻页*/
//$previousPicBtn: 分页器向前翻页按钮
//$nextPicBtn: 分页器向后翻页按钮
//$projectPic: 要添加图片的div
//pictureUrl: 获取这一页上显示的图片的url
//eachPageRowNum: 一页上图片的行数
//eachRowPicNum: 一行上图片的张数
//$currentPicPointer: 当前页面上的第一张图片在整个图片列表中的索引（0-based）
function picDuoPagination($previousPicBtn, $nextPicBtn, $projectPic,
                          pictureUrl, eachPageRowNum, eachRowPicNum, $currentPicPointer,
                          username, projectId) {
    var onePagePicNum = eachPageRowNum * eachRowPicNum;
    var currentPicPointer = parseInt($currentPicPointer.val());

    $previousPicBtn.on('click', function () {
        if (currentPicPointer > 0)
            currentPicPointer -= onePagePicNum;

        $.ajax({
            type: 'get',
            url: pictureUrl + '?username=' + username + '&projectId=' + projectId + '&begin=' + currentPicPointer + '&num=' + onePagePicNum,
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(pics){
                if (pics){
                    showOnePagePics($projectPic, pics, eachPageRowNum, eachRowPicNum);

                    $nextPicBtn.attr('disabled', false).removeClass('btn-disabled');

                    if (currentPicPointer === 0){
                        $(this).attr('disabled', true).addClass('btn-disabled');
                    }


                    $currentPicPointer.attr('value', currentPicPointer);
                }
            }
        });
    });

    $nextPicBtn.on('click', function () {
        currentPicPointer += onePagePicNum;

        $.ajax({
            type: 'get',
            url: pictureUrl + '?username=' + username + '&projectId=' + projectId + '&begin=' + currentPicPointer + '&num=' + onePagePicNum,
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(pics){
                if (pics){
                    showOnePagePics($projectPic, pics, eachPageRowNum, eachRowPicNum);

                    $previousPicBtn.attr('disabled', false).removeClass('btn-disabled');

                    if (pics.length < onePagePicNum){
                        $(this).attr('disabled', true).addClass('btn-disabled');
                    }

                    $currentPicPointer.attr('value', currentPicPointer);
                }
            }
        });
    });
}
/***********************************************************************************************/
/*任意定位图片翻页*/
//$previousPicBtn: 分页器向前翻页按钮
//$nextPicBtn: 分页器向后翻页按钮
//$projectPic: 要添加图片的div
//pictureUrl: 获取这一页上显示的图片的url
//eachPageRowNum: 一页上图片的行数
//eachRowPicNum: 一行上图片的张数
//$currentPicPointer: 当前页面上的第一张图片在整个图片列表中的索引（0-based）
function picPagination() {
    
}

/***********************************************************************************************/
/*双向积分翻页*/
//$previousRankingBtn: 分页器向前翻页按钮
//$nextRankingBtn: 分页器向后翻页按钮
//$rankingList: 要添加排名的div
//rankingUrl: 获取这一页上显示的排名的url
//eachPageRankingNum: 一页上的排名数
//$currentRankingPointer: 当前页面上的第一个排名在整个排名列表中的索引（0-based）
function rankingDuoPagination($previousRankingBtn, $nextRankingBtn, $rankingList,
                              rankingUrl, onePageRankingNum, $currentRankingPointer,
                              username, projectId) {
    var currentRankingPointer = parseInt($currentRankingPointer.val());

    $previousRankingBtn.on('click', function () {
        if (currentRankingPointer > 0)
            currentRankingPointer -= onePageRankingNum;

        $.ajax({
            type: 'get',
            url: rankingUrl + '?projectId=' + projectId + '&begin=' + currentRankingPointer + '&num=' + onePageRankingNum,
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(rankings){
                showOnePageRankings($rankingList, rankings, onePageRankingNum, currentRankingPointer);

                $nextRankingBtn.attr('disabled', false).removeClass('btn-disabled');

                if (currentRankingPointer === 0)
                    $(this).attr('disabled', true).addClass('btn-disabled');

                $currentRankingPointer.attr('value', currentRankingPointer);
            }
        });
    });

    $nextRankingBtn.on('click', function () {
        currentRankingPointer += onePageRankingNum;

        $.ajax({
            type: 'get',
            url: rankingUrl + '?projectId=' + projectId + '&begin=' + currentRankingPointer + '&num=' + onePageRankingNum,
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(rankings){
                console.log(rankings);
                showOnePageRankings($rankingList, rankings, onePageRankingNum, currentRankingPointer);

                $previousRankingBtn.attr('disabled', false).removeClass('btn-disabled');

                if (rankings.length < onePageRankingNum)
                    $(this).attr('disabled', true).addClass('btn-disabled');

                $currentRankingPointer.attr('value', currentRankingPointer);
            }
        });
    });
}





