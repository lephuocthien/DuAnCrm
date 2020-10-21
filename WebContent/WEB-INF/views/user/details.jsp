<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<title>Chi tiết thành viên</title>
<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Chi tiết thành viên</h4>
		</div>
	</div>
	<!-- /.row -->
	<!-- .row -->
	<div class="row">
		<div class="col-md-4 col-xs-12">
			<div class="white-box">
				<div class="user-bg">
					<img width="100%" alt="user"
						src="<c:url value="/assets/plugins/images/large/img1.jpg" />">
					<div class="overlay-box">
						<div class="user-content">
							<a href="javascript:void(0)"><img
								src="<c:url value="/assets/plugins/images/users/genu.jpg"/>"
								class="thumb-lg img-circle" alt="img"></a>
							<h4 class="text-white">${user.fullname}</h4>
							<h5 class="text-white">${user.email}</h5>
						</div>
					</div>
				</div>
				<div class="user-btm-box">
					<div class="col-md-4 col-sm-4 text-center">
						<p class="text-purple">
							<i class="ti-facebook"></i>
						</p>
						<h4><fmt:formatNumber type="number" maxFractionDigits="2" value="${unfulfillPercent}" />%</h4>
						<h6>Chưa thực hiện</h6>
					</div>
					<div class="col-md-4 col-sm-4 text-center">
						<p class="text-blue">
							<i class="ti-twitter"></i>
						</p>
						<h4><fmt:formatNumber type="number" maxFractionDigits="2" value="${processPercent}" />%</h4>
						<h6>Đang thực hiện</h6>
					</div>
					<div class="col-md-4 col-sm-4 text-center">
						<p class="text-danger">
							<i class="ti-dribbble"></i>
						</p>
						<h4><fmt:formatNumber type="number" maxFractionDigits="2" value="${completePercent}" />%</h4>
						<h6>Hoàn thành</h6>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-8 col-xs-12">
			<div class="white-box">
				<form class="form-horizontal form-material">
					<div class="form-group">
						<label class="col-md-12">Full Name</label>
						<div class="col-md-12">
							<input type="text" readonly value="${ user.fullname }"
								class="form-control form-control-line" />
						</div>
					</div>
					<div class="form-group">
						<label for="example-email" class="col-md-12">Email</label>
						<div class="col-md-12">
							<input type="text" readonly value="${ user.email }"
								class="form-control form-control-line" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Password</label>
						<div class="col-md-12">
							<input type="password" readonly value="${ user.password }"
								class="form-control form-control-line" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Phone No</label>
						<div class="col-md-12">
							<input type="text" readonly value="N/A"
								class="form-control form-control-line" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-12">Select Country</label>
						<div class="col-md-12">
							<input type="text" readonly value="N/A"
								class="form-control form-control-line" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-12">Select Country</label>
						<div class="col-md-12">
							<input type="text" readonly value="N/A"
								class="form-control form-control-line" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<br />
	<!-- /.row -->
	<!-- BEGIN DANH SÁCH CÔNG VIỆC -->
	<h4>DANH SÁCH CÔNG VIỆC</h4>
	<div class="row">
		<div class="col-md-4">
			<div class="white-box">
				<h3 class="box-title">Chưa thực hiện</h3>
				<div class="message-center">
					<c:forEach items="${ tasks }" var="item">
						<c:choose>
							<c:when test="${ item.statusName=='Chưa thực hiện' }">
								<a href="#">
									<div class="mail-contnet">
										<h5>${ item.groupworkName }</h5>
										<span class="mail-desc">${item.name }</span>
										<span class="time"><fmt:formatDate
												value="${item.startDate}" pattern="dd/MM/yyyy" /> - <fmt:formatDate
												value="${item.endDate}" pattern="dd/MM/yyyy" /></span>
									</div>
								</a>
							</c:when>
							<c:otherwise>
								<h5></h5>
							</c:otherwise>
						</c:choose>
					</c:forEach>

				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="white-box">
				<h3 class="box-title">Đang thực hiện</h3>
				<div class="message-center">
					<c:forEach items="${ tasks }" var="item">
						<c:choose>
							<c:when test="${ item.statusName=='Đang thực hiện' }">
								<a href="#">
									<div class="mail-contnet">
										<h5>${ item.groupworkName }</h5>
										<span class="mail-desc">${item.name }</span>
										<span class="time"><fmt:formatDate
												value="${item.startDate}" pattern="dd/MM/yyyy" /> - <fmt:formatDate
												value="${item.endDate}" pattern="dd/MM/yyyy" /></span>
									</div>
								</a>
							</c:when>
							<c:otherwise>
								<h5></h5>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="white-box">
				<h3 class="box-title">Đã hoàn thành</h3>
				<div class="message-center">
					<c:forEach items="${ tasks }" var="item">
						<c:choose>
							<c:when test="${ item.statusName=='Đã hoàn thành' }">
								<a href="#">
									<div class="mail-contnet">
										<h5>${ item.groupworkName }</h5>
										<span class="mail-desc">${item.name }</span>
										<span class="time"><fmt:formatDate
												value="${item.startDate}" pattern="dd/MM/yyyy" /> - <fmt:formatDate
												value="${item.endDate}" pattern="dd/MM/yyyy" /></span>
									</div>
								</a>
							</c:when>
							<c:otherwise>
								<h5></h5>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<!-- END DANH SÁCH CÔNG VIỆC -->
</div>