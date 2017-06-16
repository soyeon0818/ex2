<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>noticeUpdate</h1>
	<form action="noticeUpdate" method="post">
		<input type="hidden" name="num" value="${ view.num }">
		<p>제목 : <input type="text" name="title" value="${ view.title }"></p>
		<p>글쓴이 : <input type="text" name="writer" value="${ view.writer }" readonly="readonly"></p>
		<p>내용</p>
		<textarea  name="contents" cols="100" rows="30">${ view.contents }</textarea>
		<button>submit</button>
	</form>
</body>
</html>