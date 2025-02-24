$("#searchBtn").on("click", function () {
    var bookName = $("#searchName").val();
    var page = 0;

    // 서버에 AJAX POST 요청 보내기
    $.ajax({
        type: "POST",
        url: "/rent/search/" + bookName + "?page=" + page,
        contentType: "application/json; charset=utf-8",
        dataType: "html", // 서버 응답을 JSON 형식으로 처리
    })
        .done(function (response) {
            // 성공 응답 처리
            $('#rentListContainer').html(response);  // bookListContainer는 HTML을 삽입할 div 또는 영역의 ID
                })
        .fail(function (xhr, textStatus, errorThrown) {
            // 실패 응답 처리
            if (xhr.status === 400) {
                try {
                    // JSON 파싱 시도
                    var response = JSON.parse(xhr.responseText);
                    alert("오류: " + response.message);
                } catch (e) {
                    // JSON 형식이 아닐 경우
                    alert("오류가 발생했습니다. 서버 메시지를 확인하세요: " + xhr.responseText);
                }
            } else {
                console.error("오류:", errorThrown);
                alert("서버 오류가 발생했습니다. 나중에 다시 시도해주세요.");
            }
        });
});



