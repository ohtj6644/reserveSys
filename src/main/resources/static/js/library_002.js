document.querySelector(".form").addEventListener("submit", function(event) {
    event.preventDefault(); // 폼 제출 기본 동작 방지

    var formData = new FormData(this);
    var xhr = new XMLHttpRequest();

    xhr.open("POST", "/book/create", true); // POST 요청으로 URL 설정
    xhr.onload = function() {
      if (xhr.status === 200) {
        var response = JSON.parse(xhr.responseText); // 응답 JSON 파싱
        if (response.success) {
          alert("도서가 저장되었습니다!");
        } else {
          alert("저장에 실패했습니다.");
        }
      } else {
        alert("서버와의 통신에 실패했습니다.");
      }
    };

    xhr.onerror = function() {
      alert("서버와의 통신에 실패했습니다.");
    };

    xhr.send(formData); // 폼 데이터 전송
  });