<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col-8 offset-2">
	<form action="" method="post">
		<div class="card">
			<div class="card-header">
				<b>Registration</b>
			</div>
			<div class="card-body">
			<jsp:include page="/common/inform.jsp"></jsp:include>
				<div class="row">
					<div class="col">
						<div class="mb-3">
							<label for="username" class="form-label">Username:</label> <input
								type="text" class="form-control" name="username" id="username" value="${user.username }"
								aria-describedby="usernameHelpId" placeholder="username">
							<small id="usernameHelpId" class="form-text text-muted">User
								name is required!!</small>
						</div>
						<div class="mb-3">
							<label for="fullname" class="form-label">Fullname:</label> <input
								type="text" class="form-control" name="fullname" id="fullname" value="${user.fullname }"
								aria-describedby="fullnameHelpId" placeholder="fullname">
							<small id="fullnameHelpId" class="form-text text-muted">Fullname
								is required!!</small>
						</div>
					</div>
					<div class="col">
						<div class="mb-3">
							<label for="password" class="form-label">Password:</label> 
							<input
								type="password" class="form-control" name="password"
								id="password" aria-describedby="passwordHelpId"
								placeholder="password"> <small id="passwordHelpId"
								class="form-text text-muted">Password is required!!</small>
						</div>
						<div class="mb-3">
							<label for="email" class="form-label">Email:</label> <input
								type="text" class="form-control" name="email" id="email" value="${user.email }"
								aria-describedby="emailHelpId" placeholder="email"> <small
								id="emailHelpId" class="form-text text-muted">Email is
								required!!</small>
						</div>
					</div>
				</div>
			</div>
			<div class="card-footer text-muted">
				<button class="btn btn-success">Sign Up</button>
			</div>
		</div>
	</form>
</div>