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



// "반납" 버튼 클릭 시
$('#completeRentBtn').click(function () {
    var rentId = $(this).val();  // 버튼에 설정된 bookNo 값을 가져옴

    // 확인창 표시
    if (confirm("정말 반납하시겠습니까?")) {
        // 확인 클릭 시 서버에 AJAX 요청 보내기
        $.ajax({
            type: "POST",
            url: "/rent/complete/" + rentId,  // 도서 번호를 URL 파라미터로 전달
            dataType: "html",  // 서버 응답을 HTML 형식으로 처리
        })
        .done(function (response) {
                                  alert("도서 반납이 완료되었습니다.");
                          })
        .fail(function (xhr, textStatus, errorThrown) {
            console.error("서버 오류:", errorThrown);
            alert("서버 오류가 발생했습니다. 나중에 다시 시도해주세요.");
        });
    } else {
        // 취소 클릭 시 아무 작업도 하지 않음
        console.log("반납 취소");
    }
});


