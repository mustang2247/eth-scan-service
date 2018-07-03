var saveUrl = '/save';
var refererCode = '';
var inviteUrl = '/invite';
var l = 'en';

jQuery(function($) {
    $(".main-btn2").on("click", function () {
        var token = $("#token").val();
        refererCode = $("#refererCode").val();

        if (isAddress(token)) {
            $.getJSON(saveUrl, {token: token, refererCode: refererCode, l: l}, function (data) {
                if (data.code == 0 || data.code == 4) {
                    location.href = inviteUrl + "?code=" + data.inviteCode + "&l=" + data.l;
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
    });
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

