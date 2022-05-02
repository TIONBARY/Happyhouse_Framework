<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
	<%@ include file="/WEB-INF/views/header.jsp" %>
    <script src="${pageContext.request.contextPath}/js/account.js"></script>
    <script src="${pageContext.request.contextPath}/js/post.js"></script>
    
  </head>
  <body>
    <div class="px-0 h-100">
    	  <div class="container-lg navbar navbar-light">
      	<%@ include file="/WEB-INF/views/topBar.jsp" %>
      </div>

      <!-- Post -->
      <div
        id="postDiv"
        class="bg-light container-2xl d-flex justify-content-center align-items-center"
      >
        <div class="position-relative w-50">
          <!-- Post Form -->
          <div id="loginForm" class="bg-white p-4 rounded-3 shadow-sm">
            <h3 class="fw-bolder text-start">게시글 보기</h3>
            <div class="d-flex flex-column">
              <div class="d-flex mt-4 mb-2">
                <span class="me-2 fw-bold">제목:</span>
                <span class="ms-1">${postDto.getSubject() }</span>
              </div>
              <hr />
              <div class="d-flex mt-2 mb-5">
                <span class="me-2 fw-bold">내용:</span>
                <span class="ms-1"
                  >${postDto.getContent() }</span
                >
              </div>

              <div class="w-100 d-flex justify-content-end">
                <button
                  type="button"
                  onclick="location.assign('${root}/board/getAllPost')"
                  class="border-0 py-2 px-3 me-3 mb-3 rounded-2 text-dark fw-bold"
                  style="width: max-content"
                >
                  목록으로
                </button>

                <button
                  id="updatePost"
                  onclick="location.replace('${root}/board/updatePost/${postDto.getNo() }')"
                  class="border-0 mb-3 px-3 py-2 rounded-2 text-light fw-bold"
                  style="background-color: #86c232; width: max-content"
                >
                  수정하기
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>

      <%@ include file="/WEB-INF/views/footer.jsp" %>
    </div>
    
  </body>
</html>
