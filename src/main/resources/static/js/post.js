$(document).ready(function () {

  $("#submitPost").on("click", submitPostHandler);
  $("#saveUpdatedPost").on("click", updatePostHandler);
});

function submitPostHandler() {

	const subject = $("#subject").val();
	const content = $("#content").val();
	
	if (!subject || !content) {
		alert("빈 칸을 채워주세요.");
	    return;
	}
	  
	$("form").attr("action", "/board/createPost").submit();
}


function updatePostHandler() {

	const subject = $("#subject").val();
	const content = $("#content").val();
	
	if (!subject || !content) {
		alert("빈 칸을 채워주세요.");
	    return;
	}
	  
	$("form").attr("action", "/board/updatePost").submit();
}


