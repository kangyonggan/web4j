$(function () {

    var $form = $('#modal-form');

    $form.validate({
        rules: {
            username: {
                required: true,
                isUsername: true,
                remote: {
                    url: "/validate/user",
                    type: 'post',
                    data: {
                        'username': function () {
                            return $('#username').val()
                        },
                        'oldUsername': function () {
                            return $('#old-username').val();
                        }
                    }
                }
            },
            password: {
                required: true,
                isPassword: true
            },
            fullname: {
                required: true,
                isFullname: true
            }
        },
        messages: {
            username: {
                remote: "不可用"
            }
        },
        submitHandler: function (form, event) {
            event.preventDefault();
            $(form).ajaxSubmit({
                dataType: 'json',
                success: function (response) {
                    if (response.errCode == 'success') {
                        window.location.reload();
                    } else {
                        Notify.error(response.errMsg);
                    }
                },
                error: function () {
                    Notify.error("服务器内部错误，请稍后再试。");
                }
            });
        },
        errorPlacement: function (error, element) {
            error.appendTo(element.parent());
        },
        errorElement: "div",
        errorClass: "error"
    });
});
