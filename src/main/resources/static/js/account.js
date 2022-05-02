$(document).ready(function () {
  $("#alertBar").hide();
  $("#registerBtn").on("click", registerHandler);
  $("#loginBtn").on("click", loginHandler);
  $("#logoutNav").on("click", logoutHandler);
  $("#findPasswordBtn").on("click", findPasswordHandler);
});

// 상단 Alert Bar 띄우기
function showAlert(ok, msg) {
  const msgElement = $(`<span>&nbsp;&nbsp;${msg}</span>`);
  const successIcon = $('<i class="fa-solid fa-circle-check"></i>');
  const failIcon = $('<i class="fa-solid fa-triangle-exclamation"></i>');

  $("#alertBar").empty();

  if (ok) {
    $("#alertBar").removeClass("alert-danger");
    $("#alertBar").addClass("alert-success");
    $("#alertBar").append(successIcon);
    $("#alertBar").append(msgElement);
  } else {
    $("#alertBar").removeClass("alert-success");
    $("#alertBar").addClass("alert-danger");
    $("#alertBar").append(failIcon);
    $("#alertBar").append(msgElement);
  }

  $("#alertBar")
    .fadeTo(2000, 500)
    .slideUp(function () {
      $("#alertBar").slideUp(500);
    });
}

// 회원가입
function registerHandler() {
  const id = $("#id").val();
  const pwd = $("#pwd").val();
  const name = $("#name").val();
  const email = $("#email").val();
  const phone = $("#phone").val();

  if (!id || !pwd || !name || !email || !phone) {
    showAlert(false, "빈 칸을 채워주세요.");
    return;
  }

  $("form").attr("action", "/auth/register").submit();
}

// 로그인
function loginHandler() {
  const id = $("#id").val();
  const pwd = $("#pwd").val();

  if (!id || !pwd) {
    showAlert(false, "빈 칸을 채워주세요.");
    return;
  }
  
  $("form").attr("action", "/auth/login").submit();
}

// 로그아웃
function logoutHandler() {
  $.post("/auth/logout", {}, function () {
    $.removeCookie("id");
    $("#logoutNav > a").text("로그인");
    location.href = "index.html";
  });
}

// 비밀번호 찾기
function findPasswordHandler(e) {
  e.preventDefault();

  const id = $("#id").val();
  const phone = $("#phone").val();

  console.log(id, phone);
  if (!id || !phone) {
    showAlert(false, "빈 칸을 채워주세요.");
  }

  $.post(
    "/auth/findPassword",
    { id, phone },
    function (data) {
      const { pwd } = JSON.parse(data);

      if (pwd) {
        alert(`${id} 님의 비밀번호는 ${pwd} 입니다.`);
        location.href = "login.html";
      } else {
        showAlert(false, "잘못된 아이디 혹은 전화번호입니다.");
      }
    }
  );
}
