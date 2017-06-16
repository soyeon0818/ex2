<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>noticeWrite</h1>
	<form action="notice${ path }" method="post">
		<p><input type="text" name="title" placeholder="title" value=" ${ view.title }"></p>
		<p><textarea name="contents" placeholder="contents" cols="100" rows="30">${ view.contents }</textarea>
		<p><input type="text" name="writer" placeholder="writer" value=" ${ view.writer }"></p>
		<button>submit</button>
	</form>
</body>
</html>