$("#searchBtn").on("click", function () {
    var bookName = $("#searchName").val();
    var page = 0;

    // 서버에 AJAX POST 요청 보내기
    $.ajax({
        type: "POST",
        url: "/book/search/" + bookName + "?page=" + page,
        contentType: "application/json; charset=utf-8",
        dataType: "html", // 서버 응답을 JSON 형식으로 처리
    })
        .done(function (response) {
            // 성공 응답 처리
            $('#bookListContainer').html(response);  // bookListContainer는 HTML을 삽입할 div 또는 영역의 ID
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



$(document).on("click", "#openModalBtn", function () {
    var bookNo = $("#openModalBtn").val();  // 클릭한 버튼의 도서 번호 가져오기

    // 서버에 AJAX 요청 보내기
    $.ajax({
        type: "GET",
        url: "/book/detail/" + bookNo,  // 도서 번호를 URL 파라미터로 전달
        dataType: "html", // 서버 응답을 HTML 형식으로 처리
    })
    .done(function (response) {
       console.log(response); // 응답 내용 확인
       $('#modalContent').html(response);  // 모달 내용 업데이트
       $('#bookModal').modal('show');
    })
    .fail(function (xhr, textStatus, errorThrown) {
        console.error("오류:", errorThrown);
        alert("서버 오류가 발생했습니다. 나중에 다시 시도해주세요.");
    });
});







$("#rentBtn").on("click", function () {
    var rentUser = $("#rentUser").val();
    var phoneNo = $("#phoneNo").val();
    var bookNo = $("#modalBookId").val();

    // 서버에 AJAX POST 요청 보내기
    $.ajax({
        type: "POST",
        url: "/rent/create",
        data: JSON.stringify({
            bookNo: bookNo,
            rentUser: rentUser,
            phoneNo: phoneNo
        }),
        contentType: "application/json; charset=utf-8",
        dataType: "json", // 서버 응답을 JSON 형식으로 처리
    })
        .done(function (response) {
            // 성공 응답 처리
           if (response.success) {
                          alert("도서 대여 작업이 완료되었습니다.");
                      } else {
                          alert("도서 대여 등록에 실패했습니다: " + response.message);
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
