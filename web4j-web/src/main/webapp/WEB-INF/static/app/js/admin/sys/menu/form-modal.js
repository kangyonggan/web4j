$(function () {

    var $form = $('#modal-form');

    $form.validate({
        rules: {
            code: {
                required: true,
                isMenuCode: true,
                remote: {
                    url: "/validate/menu",
                    type: 'post',
                    data: {
                        'code': function () {
                            return $('#code').val()
                        },
                        'oldCode': function () {
                            return $('#old-code').val();
                        }
                    }
                }
            },
            name: {
                required: true,
                isName: true
            },
            url: {
                required: true,
                isMenuUrl: true
            },
            sort: {
                required: true,
                isMenuSort: true
            }
        },
        messages: {
            code: {
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