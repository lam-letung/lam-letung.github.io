<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col mt-4">
	<ul class="nav nav-tabs " id="myTab" role="tablist">
		<li class="nav-item" role="presentation">
			<button class="nav-link active" id="videoEditting-tab"
				data-bs-toggle="tab" data-bs-target="#videoEditting" type="button"
				role="tab" aria-controls="videoEditting" aria-selected="true">User
				Editting</button>
		</li>
		<li class="nav-item" role="presentation">
			<button class="nav-link" id="videoList-tab" data-bs-toggle="tab"
				data-bs-target="#videoList" type="button" role="tab"
				aria-controls="videoList" aria-selected="false">User List</button>
		</li>

	</ul>
	<div class="tab-content" id="myTabContent">
		<div class="tab-pane fade show active" id="videoEditting"
			role="tabpanel" aria-labelledby="videoEditting-tab">
			<form action="" method="post">
				<div class="card">
					<div class="card-body">
						<div class="row">
							<div class="col">
								<div class="mb-3">
									<label for="username" class="form-label">Username:</label> <input
										type="text" class="form-control" name="username" id="username"
										aria-describedby="UsernameHelpId" placeholder="Username">
									<small id="UsernameHelpId" class="form-text text-muted">Username
										is required!!!</small>
								</div>
								<div class="mb-3">
									<label for="fullname" class="form-label">Fullname:</label> <input
										type="text" class="form-control" name="fullname" id="fullname"
										aria-describedby="fullnameHelpId" placeholder="Fullname">
									<small id="fullnameHelpId" class="form-text text-muted">Fullname
										is required!!</small>
								</div>
							</div>
							<div class="col">
								<div class="mb-3">
									<label for="password" class="form-label">Password:</label> <input
										type="password" class="form-control" name="password"
										id="password" placeholder="Password">
								</div>
								<div class="mb-3">
									<label for="email" class="form-label">Email:</label> <input
										type="text" class="form-control" name="email" id="email"
										aria-describedby="emailHelpId" placeholder="email"> <small
										id="emailHelpId" class="form-text text-muted">Email is
										required!!</small>
								</div>
							</div>
						</div>
					</div>

					<div class="card-footer text-muted">
						<button class="btn btn-primary">Create</button>
						<button class="btn btn-warning">Update</button>
						<button class="btn btn-danger">Delete</button>
						<button class="btn btn-info">Reset</button>

					</div>
				</div>
			</form>
		</div>
		<div class="tab-pane fade" id="videoList" role="tabpanel"
			aria-labelledby="videoList-tab">
			<table class="table table-stripe">
				<tr>
					<th>Username</th>
					<th>Fullname</th>
					<th>Email</th>
					<th>Role</th>
					<th>&nbsp;</th>
				</tr>
				<tr>
					<td>LamLT</td>
					<td>Lê Tùng Lâm</td>
					<td>lamltpd04290@gmail.com</td>
					<td>Admin</td>
					<td><a href="" class="text-decoration-none"><i
							class="fa fa-edit" aria-hidden="true"></i> Edit</a> <a href=""
						class="text-decoration-none"><i class="fa fa-trash"
							aria-hidden="true"></i> Delete</a></td>
				</tr>
			</table>
		</div>

	</div>
</div>