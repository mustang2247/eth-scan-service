var saveUrl = '/save';
var refererCode = '';
var inviteUrl = '/lock';
var l = 'en';

jQuery(function($) {

    $("#main-btn1").on("click", function () {
        var token = $("#token1").val();
        var rate = "1";
        execute(token,rate);
    })

    $("#main-btn2").on("click", function () {
        var token = $("#token2").val();
        var rate = "2";
        execute(token,rate);
    })

    $("#main-btn3").on("click", function () {
        var token = $("#token3").val();
        var rate = "3";
        execute(token,rate);
    })


    // $(".main-btn2").on("click", function () {
    //     var token = $("#token").val();
    //     refererCode = $("#refererCode").val();
    //
    //     if (isAddress(token)) {
    //         $.post(saveUrl, {token: token, l: l}, function (data) {
    //             if (data.code == 0 || data.code == 4) {
    //                 // $.post(inviteUrl + "?code=" + data.token + "&l=" + data.l, function (data) {
    //                 //     location.href = data;
    //                 // })
    //                 location.href = inviteUrl + "?code=" + data.token + "&l=" + data.l;
    //             }else {
    //                 alert("error");
    //             }
    //         })
    //     } else {
    //         if (l == "en"){
    //             alert("Please enter a valid ETH wallet address");
    //         }else {
    //             alert("请输入有效的 ETH 钱包地址");
    //         }
    //     }
    // });
    $(".header").on("click", function () {
        if ($(".header").text() == "En"){
            l = 'en';
        }else {
            l = 'zh';
        }

        refererCode = $("#refererCode").val();
        location.href = '/?code=' + refererCode + '&l=' + l;
    });
    var btn = document.querySelector('body')
    btn.addEventListener('touchstart', function () {
    });
});

function execute(token,rate) {
    if (isAddress(token,rate)) {
        $.post(saveUrl, {token: token, rate:rate, l: l}, function (data) {
            if (data.code == 0 || data.code == 4) {
                // $.post(inviteUrl + "?code=" + data.token + "&l=" + data.l, function (data) {
                //     location.href = data;
                // })
                location.href = inviteUrl + "?code=" + data.token + "&rate=" + data.rate + "&l=" + data.l;
            }else {
                alert("error");
            }
        })
    } else {
        if (l == "en"){
            alert("Please enter a valid ETH wallet address");
        }else {
            alert("请输入有效的 ETH 钱包地址");
        }
    }
}