var l = 'en';
var code = '';

jQuery(function ($) {
    var btn = document.querySelector('body')
        btn.addEventListener('touchstart', function () {
    });

    var clipboard = new Clipboard('#copyCode');
    clipboard.on('success', function (e) {
    });
    clipboard.on('error', function (e) {
    });

    var clipboard2 = new Clipboard('#copyUrl');
    clipboard2.on('success', function (e) {

    });
    clipboard2.on('error', function (e) {

    });

    $(".header").on("click", function () {

        if ($(".header").text() == "En"){
            l = 'en';
        }else {
            l = 'zh';
        }

        code = $('#inviteCode').val();
        location.href = '/invite?code=' + code + '&l=' + l;
    });
});

