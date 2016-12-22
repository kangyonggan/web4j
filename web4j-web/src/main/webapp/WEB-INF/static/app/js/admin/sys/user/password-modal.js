$(function () {
    var $form = $('#modal-form');
    var $modal = $form.parents('.modal');

    $form.validate({
        rules: {
            password: {
                required: true,
                isPassword: true
            }
        },
        submitHandler: function (form, event) {
            event.preventDefault();
            $(form).ajaxSubmit({
                dataType: 'json',
                success: function (response) {
                    if (response.errCode == 'success') {
                        $modal.modal('hide');
                        Notify.success("修改成功");
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