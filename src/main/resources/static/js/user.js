$(document).ready(function () {
  $("#switchBtn").on("click", switchHandler);
  $("#cancelBtn").on("click", cancelHandler);
  $("#updateUserBtn").on("click", updateUserHandler);
  $("#deleteUserBtn").on("click", deleteUserHandler);
});

function cancelHandler() {
  // 취소 버튼 클릭 시, 원래의 상태로 돌아간다
  // input의 readonly 다 붙고, 수정 버튼으로 전환
  $("form > span > input").attr("readonly", true);

  $("#switchBtn").show();
  $("#cancelBtn").hide();
  $("#updateUserBtn").hide();
}

function switchHandler() {
  // 수정 버튼 클릭 시, input의 readonly 속성 모두 제거
  $("form > span > input").removeAttr("readonly");

  $("#switchBtn").hide();
  $("#cancelBtn").show();
  $("#updateUserBtn").show();
}

function updateUserHandler() {
  const id = $("#id").val();
  const pwd = $("#pwd").val();
  const name = $("#name").val();
  const email = $("#email").val();
  const phone = $("#phone").val();

  if (!id || !pwd || !name || !email || !phone) {
    showAlert(false, "빈 칸을 채워주세요.");
    return;
  }
  
  $("form").attr("action", "/auth/update").submit();
}

function deleteUserHandler() {
	const flag = confirm("정말로 탈퇴하시겠습니까?");
	if(flag) {
	  location.href = "/auth/delete";
	}
}
