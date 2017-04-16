String.prototype.format = function () {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function (match, number) {
        return typeof args[number] != 'undefined'
            ? args[number]
            : match
            ;
    });
};


jQuery("#answerWrite").click(function (e) {
        e.preventDefault(); // 이벤트 전파 방지

        var targetAnswer = jQuery("form[name=answer]").serialize();

        jQuery.ajax({
            type: 'post',
            url: '/api/qna/addAnswer',
            data: targetAnswer,
            dataType: 'json',
            async: false,
            success: function (result) {
                var answerTemplate = jQuery("#answerTemplate").html();
                var template = answerTemplate.format(result.writer, new Date(result.createdDate), result.contents, result.answerId);

                jQuery(".qna-comment-slipp-articles").prepend(template);
                alert("저장되었습니다.");
                console.log(result.writer, new Date(result.createdDate), result.contents, result.answerId);
            },
            error: function () {
                alert("실패했습니다.");
            }
        });
    }
);

jQuery(".qna-comment").on ("click", ".link-delete-article", function(e) {
    e.preventDefault();

    var answerId = jQuery(this).siblings("input[name=answerId]").val();
    var deletedAnswer = jQuery(this).closest(".article");

    jQuery.ajax({
        type: 'post',
        url: '/api/qna/deleteAnswer',
        data: {
            answerId: answerId
        },
        dataType: 'json',
        success: function(result) {
            if (result == "success") {
                deletedAnswer.remove();
                alert("삭제되었습니다.");
            } else {
                alert("삭제에 실패했습니다.");
            }
        },
        error: function() {
            alert("삭제에 실패했습니다2");
        }
    });
});