<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<title>Chi tiết công việc</title>
<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Chi tiết công việc</h4>
		</div>
		<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
			<ol class="breadcrumb">
				<li><a href="#">Dashboard</a></li>
				<li class="active">Blank Page</li>
			</ol>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- BEGIN THỐNG KÊ -->
	<div class="row">
		<!--col -->
		<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
			<div class="white-box">
				<div class="col-in row">
					<div class="col-md-6 col-sm-6 col-xs-6">
						<i data-icon="E" class="linea-icon linea-basic"></i>
						<h5 class="text-muted vb">CHƯA BẮT ĐẦU</h5>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6">
						<h3 class="counter text-right m-t-15 text-danger">
						<fmt:formatNumber type="number" maxFractionDigits="2" value="${unfulfillPercent}" />%</h3>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="progress">
							<div class="progress-bar progress-bar-danger" role="progressbar"
								aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
								style="width: ${unfulfillPercent}%"></div>
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
					<div class="col-md-6 col-sm-6 col-xs-6">
						<i class="linea-icon linea-basic" data-icon="&#xe01b;"></i>
						<h5 class="text-muted vb">ĐANG THỰC HIỆN</h5>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6">
						<h3 class="counter text-right m-t-15 text-megna">
						<fmt:formatNumber type="number" maxFractionDigits="2" value="${processPercent}" />%</h3>
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
					<div class="col-md-6 col-sm-6 col-xs-6">
						<i class="linea-icon linea-basic" data-icon="&#xe00b;"></i>
						<h5 class="text-muted vb">HOÀN THÀNH</h5>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6">
						<h3 class="counter text-right m-t-15 text-primary">
						<fmt:formatNumber type="number" maxFractionDigits="2" value="${completePercent}" />%</h3>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="progress">
							<div class="progress-bar progress-bar-primary" role="progressbar"
								aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
								style="width: ${completePercent}%"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /.col -->
	</div>
	<!-- END THỐNG KÊ -->

	<!-- BEGIN DANH SÁCH CÔNG VIỆC -->
	<c:forEach items="${ tasks }" var="item">
		<c:choose>
			<c:when test="${ item.groupworkName==groupwork.name }">
				<div class="row">
					<div class="col-xs-12">
						<a href="#" class="group-title"> <img width="30"
							src='<c:url value="/assets/plugins/images/users/pawandeep.jpg"/>'
							class="img-circle" /> <span>${item.userName }</span>
						</a>
					</div>
					<div class="col-md-4">
						<div class="white-box">
							<h3 class="box-title">Chưa thực hiện</h3>
							<div class="message-center">
								<c:choose>
									<c:when test="${ item.statusName=='Chưa thực hiện' }">
										<a href="#">
											<div class="mail-contnet">
												<h5>${ item.groupworkName }</h5>
												<span class="mail-desc">${item.name }</span> <span
													class="time"><fmt:formatDate
														value="${item.startDate}" pattern="dd/MM/yyyy" /> - <fmt:formatDate
														value="${item.endDate}" pattern="dd/MM/yyyy" /></span>
											</div>
										</a>
									</c:when>
									<c:otherwise>
										<h5></h5>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="white-box">
							<h3 class="box-title">Đang thực hiện</h3>
							<div class="message-center">
								<c:choose>
									<c:when test="${ item.statusName=='Đang thực hiện' }">
										<a href="#">
											<div class="mail-contnet">
												<h5>${ item.groupworkName }</h5>
												<span class="mail-desc">${item.name }</span> <span
													class="time"><fmt:formatDate
														value="${item.startDate}" pattern="dd/MM/yyyy" /> - <fmt:formatDate
														value="${item.endDate}" pattern="dd/MM/yyyy" /></span>
											</div>
										</a>
									</c:when>
									<c:otherwise>
										<h5></h5>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="white-box">
							<h3 class="box-title">Đã hoàn thành</h3>
							<div class="message-center">
								<c:choose>
									<c:when test="${ item.statusName=='Đã hoàn thành' }">
										<a href="#">
											<div class="mail-contnet">
												<h5>${ item.groupworkName }</h5>
												<span class="mail-desc">${item.name }</span> <span
													class="time"><fmt:formatDate
														value="${item.startDate}" pattern="dd/MM/yyyy" /> - <fmt:formatDate
														value="${item.endDate}" pattern="dd/MM/yyyy" /></span>
											</div>
										</a>
									</c:when>
									<c:otherwise>
										<h5></h5>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div></div>
			</c:otherwise>
		</c:choose>
	</c:forEach>

	<!-- END DANH SÁCH CÔNG VIỆC -->
</div>