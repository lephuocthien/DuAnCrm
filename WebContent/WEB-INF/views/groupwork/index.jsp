<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<title>Danh sách dự án</title>
<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Danh sách dự án</h4>
		</div>
		<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
			<a href="<c:url value="/groupwork/add" />"
				class="btn btn-sm btn-success">Thêm mới</a>
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
							<c:forEach items="${ groupworks }" var="item" varStatus="counter">
								<tr>
									<td>${counter.count}</td>
									<td>${item.name}</td>
									<td><fmt:formatDate value="${item.startDate}"
											pattern="dd/MM/yyyy" /></td>
									<td><fmt:formatDate value="${item.endDate}"
											pattern="dd/MM/yyyy" /></td>
									<td><a href="<c:url value="/groupwork/edit?id=${ item.id }" />"
										class="btn btn-sm btn-primary">Sửa</a> 
										<a href="<c:url value="/groupwork/delete?id=${ item.id }" />"class="btn btn-sm btn-danger">Xóa</a> 
										<a href="<c:url value="/groupwork/details?id=${ item.id }" />"
										class="btn btn-sm btn-info">Xem</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>