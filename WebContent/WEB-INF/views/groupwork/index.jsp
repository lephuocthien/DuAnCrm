<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Danh sách dự án</h4>
		</div>
		<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
			<a href="<c:url value="/groupwork/add" />" class="btn btn-sm btn-success">Thêm
				mới</a>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /row -->
	<div class="row">
		<div class="col-sm-12">
			<div class="white-box">
				<div class="table-responsive">
					<table class="table" id="example">
						<thead>
							<tr>
								<th>STT</th>
								<th>Tên Dự Án</th>
								<th>Ngày Bắt Đầu</th>
								<th>Ngày Kết Thúc</th>
								<th>Hành Động</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ groupworks }" var="item">
								<tr>
									<td> ${ item.id } </td>
									<td> ${ item.name } </td>
									<td> ${ item.startDay } </td>
									<td> ${ item.endDay } </td>
									<td> ${ item.statusId } </td>
									<td> ${ item.userId } </td>
									<td> ${ item.taskId } </td>
									<td><a href="<c:url value="/groupwork/edit" />" class="btn btn-sm btn-primary">Sửa</a> <a
										href="#" class="btn btn-sm btn-danger">Xóa</a> <a
										href="<c:url value="/groupwork/details" />" class="btn btn-sm btn-info">Xem</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
=======
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
>>>>>>> parent of 08ef7d5... Update groupwork folder
