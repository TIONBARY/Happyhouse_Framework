<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
	<%@ include file="/WEB-INF/views/header.jsp" %>
    <script src="${pageContext.request.contextPath}/js/account.js"></script>
  </head>
  <body>
    <div class="container-lg px-0 h-100">
	  <%@ include file="/WEB-INF/views/alertBar.jsp" %>
	  <div class="container-lg navbar navbar-light">
	  	<%@ include file="/WEB-INF/views/topBar.jsp" %>
	  </div>

      <!-- Contents -->
      <div
        id="noticeDiv"
        class="container-lg p-4 d-flex flex-column"
        style="height: calc(100% - 220px)"
      >
        <div style="height: calc(100% - 110px)">
          <h3 class="fw-bolder mb-3">공지사항</h3>
          <table class="table table-hover">
            <thead>
              <tr onclick="location.assign('post.jsp')">
                <th>번호</th>
                <th>제목</th>
                <th>글쓴이</th>
                <th>작성일</th>
                <th>조회수</th>
              </tr>
            </thead>
            <tbody>

            
          	<c:forEach var="post" items="${postList}">
          	  <tr onclick="location.assign('${root}/board/getPost/${post.getNo() }')">
                <td>${post.getNo()}</td>
                <td>${post.getSubject()}</td>
                <td>${post.getUserid() }</td>
                <td>${post.getRegtime() }</td>
                <td>${post.getViews() }</td>
              </tr>
		    </c:forEach>

            </tbody>
          </table>
        </div>
        <div>
          <!-- 글쓰기 -->
          <div class="d-flex justify-content-end mb-2">
            <button
              onclick="location.assign('${root}/board/createPost')"
              class="px-3 py-2 border-0 rounded-3 text-light fw-bold"
              style="background-color: #86c232"
            >
              글 작성하기
            </button>
          </div>

          <!-- Pagination -->
          <ul class="pagination justify-content-center">
            <li class="page-item">
              <a class="page-link" href="#">
                <span aria-hidden="true">&laquo;</span>
                <span class="sr-only">Previous</span>
              </a>
            </li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item">
              <a class="page-link" href="#">
                <span aria-hidden="true">&raquo;</span>
                <span class="sr-only">Next</span>
              </a>
            </li>
          </ul>
        </div>
      </div>

      <%@ include file="/WEB-INF/views/footer.jsp" %>
    </div>
  </body>
</html>
