<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="offset-4 col-4">
	<form action="" method="post">
		<div class="card">
			<div class="card-header">
				<b>Login to system</b>
			</div>
			<div class="card-body">
			<jsp:include page="/common/inform.jsp"></jsp:include>
				<div class="mb-3 form-group">
					<label for="username" class="form-label">User name</label> <input
						type="text" class="form-control" name="username" id="username"
						aria-describedby="usernameHelpId" placeholder="user name">
					<small id="usernameHelpId" class="form-text text-muted">User
						name is required!!</small>
				</div>
				<div class="mb-3 form-group">
					<label for="password" class="form-label">Password</label> <input
						type="password" class="form-control" name="password" id="password"
						aria-describedby="passwordHelpId" placeholder="password">
					<small id="passwordHelpId" class="form-text text-muted">Password
						is required</small>
				</div>
				<div class="form-check form-check-inline">
					<label><input type="checkbox" class="form-check-input"
						name="remember">Remember me</label>
				</div>
			</div>

			<div class="card-footer text-muted">
				<button class="btn btn-success">Login</button>
			</div>
		</div>
	</form>
</div>