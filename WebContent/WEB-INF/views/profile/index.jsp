<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<title>Thông tin tài khoản</title>
<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Thông tin tài khoản</h4>
		</div>
	</div>
	<!-- /.row -->
	<!-- .row -->
	<div class="row">
		<div class="col-md-4 col-xs-12">
			<div class="white-box">

				<div class="user-bg">
					<img width="100%" alt="user"
						src='<c:url value="/assets/plugins/images/large/img1.jpg"/>'>
					<div class="overlay-box">
						<div class="user-content">

							<a href="javascript:void(0)"><img
								src='<c:url value="/assets/plugins/images/users/genu.jpg"/>'
								class="thumb-lg img-circle" alt="img"></a>
							<h4 class="text-white">${user.fullname }</h4>
							<h5 class="text-white">${user.email }</h5>
						</div>
					</div>
				</div>

			</div>
		</div>
		<div class="col-md-8 col-xs-12">
			<!-- BEGIN THỐNG KÊ -->
			<div class="row">
				<!--col -->
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<div class="white-box">
						<div class="col-in row">
							<div class="col-xs-12">
								<h3 class="counter text-right m-t-15 text-danger">
								<fmt:formatNumber type="number" maxFractionDigits="2" value="${unfulfillPercent}" />%</h3>
							</div>
							<div class="col-xs-12">
								<i data-icon="E" class="linea-icon linea-basic"></i>
								<h5 class="text-muted vb text-center">CHƯA BẮT ĐẦU</h5>
							</div>
							<div class="col-md-12 col-sm-12 col-xs-12">
								<div class="progress">
									<div class="progress-bar progress-bar-danger"
										role="progressbar" aria-valuenow="40" aria-valuemin="0"
										aria-valuemax="100" style="width: ${unfulfillPercent}%"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /.col -->
				<!--col -->
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<div class="white-box">
						<div class="col-in row">
							<div class="col-xs-12">
								<h3 class="counter text-right m-t-15 text-megna">
								<fmt:formatNumber type="number" maxFractionDigits="2" value="${processPercent}" />%</h3>
							</div>
							<div class="col-xs-12">
								<i class="linea-icon linea-basic" data-icon="&#xe01b;"></i>
								<h5 class="text-muted vb text-center">ĐANG THỰC HIỆN</h5>
							</div>
							<div class="col-md-12 col-sm-12 col-xs-12">
								<div class="progress">
									<div class="progress-bar progress-bar-megna" role="progressbar"
										aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
										style="width: ${processPercent}%"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /.col -->
				<!--col -->
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<div class="white-box">
						<div class="col-in row">
							<div class="col-xs-12">
								<h3 class="counter text-right m-t-15 text-primary">
								<fmt:formatNumber type="number" maxFractionDigits="2" value="${completePercent}" />%</h3>
							</div>
							<div class="col-xs-12">
								<i class="linea-icon linea-basic" data-icon="&#xe00b;"></i>
								<h5 class="text-muted vb text-center">HOÀN THÀNH</h5>
							</div>
							<div class="col-md-12 col-sm-12 col-xs-12">
								<div class="progress">
									<div class="progress-bar progress-bar-primary"
										role="progressbar" aria-valuenow="40" aria-valuemin="0"
										aria-valuemax="100" style="width: ${completePercent}%"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /.col -->
			</div>
			<!-- END THỐNG KÊ -->

		</div>
	</div>
	<br />
	<!-- /.row -->
	<!-- BEGIN DANH SÁCH CÔNG VIỆC -->
	<h4>DANH SÁCH CÔNG VIỆC</h4>
	<div class="row">
		<div class="col-sm-12">
			<div class="white-box">
				<div class="table-responsive">
					<table class="table" id="example">
						<thead>
							<tr>
								<th>STT</th>
								<th>Tên Công Việc</th>
								<th>Dự Án</th>
								<th>Ngày Bắt Đầu</th>
								<th>Ngày Kết Thúc</th>
								<th>Trạng Thái</th>
								<th>Hành Động</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ tasks }" var="item" varStatus="counter">
							<tr>
								<td>${counter.count}</td>
								<td>${item.name}</td>
								<td>${item.groupworkName}</td>
								<td><fmt:formatDate value="${item.startDate}"
											pattern="dd/MM/yyyy" /></td>
								<td><fmt:formatDate value="${item.endDate}"
											pattern="dd/MM/yyyy" /></td>
								<td>${item.statusName}</td>
								<td><a href="<c:url value="/profile/edit?id=${ item.id }"/>"
									class="btn btn-sm btn-primary">Cập nhật</a></td>
								
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- END DANH SÁCH CÔNG VIỆC -->
</div>