<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<title>Cập nhật dự án</title>
<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Cập nhật dự án</h4>
		</div>
	</div>
	<!-- /.row -->
	<!-- .row -->
	<div class="row">
		<div class="col-md-2 col-12"></div>
		<div class="col-md-8 col-xs-12">
			<div class="white-box">
				<form action='<c:url value="/groupwork/edit" />' method="post" 
				class="form-horizontal form-material">
				<input type="hidden" name="id" value="${ groupwork.id }" />
					<div class="form-group">
						<label class="col-md-12">Tên dự án</label>
						<div class="col-md-12">
							<input type="text" name="name" placeholder="Tên công việc"
								value="${groupwork.name }"
								class="form-control form-control-line">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Ngày bắt đầu</label>
						<div class="col-md-12">
							<input type="text" name="start_date" placeholder="dd/MM/yyyy" 
								value="<fmt:formatDate value="${groupwork.startDate}" pattern="dd/MM/yyyy"/>"
								class="form-control form-control-line">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Ngày kết thúc</label>
						<div class="col-md-12">
							<input type="text" name="end_date" placeholder="dd/MM/yyyy" 
							value="<fmt:formatDate value="${groupwork.endDate}" pattern="dd/MM/yyyy"/>"
							class="form-control form-control-line">	
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<button type="submit" class="btn btn-success">Lưu lại</button>
							<a href="<c:url value="/groupwork" />" class="btn btn-primary">Quay
								lại</a>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="col-md-2 col-12"></div>
	</div>
	<!-- /.row -->
</div>