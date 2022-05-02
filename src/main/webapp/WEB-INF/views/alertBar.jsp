<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Alert Bar -->
<div id="alertBar" role="alert" class="alert px-4 fw-bold mb-0">
  <!-- success/error message 동적으로 추가 -->
</div>

<script>
	console.log('<%= request.getAttribute("msg") %>');
</script>


<c:if test="${!empty ok}">
	<script>
		$(document).ready(function() {
			showAlert(${ok}, "${msg}");
		});
		console.log("${ok}", "${msg}");
		<% request.removeAttribute("ok"); request.removeAttribute("msg"); %>
	</script>
</c:if>
