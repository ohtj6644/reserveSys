$("#createBtn").on("click", function () {
    var bookNo = $("#bookNo").val();
    var bookName = $("#bookName").val();
    var bookWriter = $("#bookWriter").val();

    // 서버에 AJAX POST 요청 보내기
    $.ajax({
        type: "POST",
        url: "/book/create",
        data: JSON.stringify({
            bookNo: bookNo,
            bookName: bookName,
            bookWriter: bookWriter
        }),
        contentType: "application/json; charset=utf-8",
        dataType: "json", // 서버 응답을 JSON 형식으로 처리
    })
        .done(function (response) {
            // 성공 응답 처리
            if (response.success) {
                alert("도서 등록이 완료되었습니다.");
            } else {
                alert("도서 등록에 실패했습니다: " + response.message);
            }
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
