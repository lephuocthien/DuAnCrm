<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<title>Thêm mới công việc</title>
<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Thêm mới công việc</h4>
		</div>
	</div>
	<!-- /.row -->
	<!-- .row -->
	<div class="row">
		<div class="col-md-2 col-12"></div>
		<div class="col-md-8 col-xs-12">
			<div class="white-box">
				<form action='<c:url value="/profile/edit" />' method="post" 
				class="form-horizontal form-material">
				<input type="hidden" name="id" value="${ task.id }" />
					<div class="form-group">
						<label class="col-md-12">Tên dự án</label>
						<div class="col-md-12">
							<input type="text"
							readonly value="${task.groupworkName }"
								class="form-control form-control-line">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Tên công việc</label>
						<div class="col-md-12">
							<input type="text" 
							readonly value="${ task.name}"
								class="form-control form-control-line">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Ngày bắt đầu</label>
						<div class="col-md-12">
							<input type="text"
							readonly value="<fmt:formatDate value="${task.startDate}" pattern="dd/MM/yyyy"/>"
								class="form-control form-control-line">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Ngày kết thúc</label>
						<div class="col-md-12">
							<input type="text" 
							readonly value="<fmt:formatDate value="${task.endDate}" pattern="dd/MM/yyyy"/>"
								class="form-control form-control-line">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Trạng thái</label>
						<div class="col-md-12">
							<select name="status_id" class="form-control form-control-line">
								<c:forEach items="${ statuss }" var="item">
									<c:choose>
										<c:when test="${ item.id == task.statusId }">
											<option value="${ item.id }" selected>${ item.name }</option>
										</c:when>
										<c:otherwise>
											<option value="${ item.id }">${ item.name }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<button type="submit" class="btn btn-success">Lưu lại</button>
							<a href="<c:url value="/profile"/>" class="btn btn-primary">Quay
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