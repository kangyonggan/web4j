$(function () {

    var $form = $('#login-form');
    $form.validate({
        rules: {
            username: {
                required: true,
                isUsername: true
            },
            password: {
                required: true,
                isPassword: true
            },
            captcha: {
                // required: true,
                // isCaptcha: true
            }
        },
        submitHandler: function () {
            $form.ajaxSubmit({
                dataType: 'json',
                success: function (response) {
                    if (response.errCode == "success") {
                        window.location.href = ctx + response.errMsg;
                    } else {
                        Notify.error(response.errMsg);
                    }
                },
                error: function () {
                    Notify.error("服务器内部错误，请稍后再试。");
                }
            });
            return false;
        },
        errorPlacement: function (error, element) {
            error.appendTo(element.parent());
        },
        errorElement: "div",
        errorClass: "error"
    });
});

