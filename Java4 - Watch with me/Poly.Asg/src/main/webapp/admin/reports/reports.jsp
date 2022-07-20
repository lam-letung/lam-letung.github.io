<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col mt-4">
	<ul class="nav nav-tabs " id="myTab" role="tablist">
		<li class="nav-item" role="presentation">
			<button class="nav-link active" id="videoEditting-tab"
				data-bs-toggle="tab" data-bs-target="#videoEditting" type="button"
				role="tab" aria-controls="videoEditting" aria-selected="true">Favorites</button>
		</li>
		<li class="nav-item" role="presentation">
			<button class="nav-link" id="videoList-tab" data-bs-toggle="tab"
				data-bs-target="#videoList" type="button" role="tab"
				aria-controls="videoList" aria-selected="false">Favorite
				Users</button>
		</li>
		<li class="nav-item" role="presentation">
			<button class="nav-link" id="shareFriends-tab" data-bs-toggle="tab"
				data-bs-target="#shareFriends" type="button" role="tab"
				aria-controls="shareFriends" aria-selected="false">Share
				friends</button>
		</li>

	</ul>
	<div class="tab-content" id="myTabContent">
		<div class="tab-pane fade show active" id="videoEditting"
			role="tabpanel" aria-labelledby="videoEditting-tab">
			<table class="table table-bordered mt-3">
				<tr>
					<th>Video Title</th>
					<th>Favorites Count</th>
					<th>Lasted Date</th>
					<th>Oldest Date</th>
				</tr>
				<c:forEach var="item" items="${favList }">
					<tr>
						<td>${item.videoTitle }</td>
						<td>${item.favoriteCount }</td>
						<td>${item.newestDate }</td>
						<td>${item.oldestDate }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="tab-pane fade" id="videoList" role="tabpanel"
			aria-labelledby="videoList-tab">
			<form action="" method="get">
				<div class="row mt-3">
					<div class="col">
						<div class="form-inline">
							<div class="form-group">
								<label>Video Title 
								<select name="videoUserId" id="videoUserId"
									class="form-control">
									<c:forEach var="item" items="${vidList }">
										<option value="${item.videoId }" 
										${item.videoId == videoUserId?'selected':'' }>${item.title }</option>
									</c:forEach>
								</select>
								</label>
								<button class="btn btn-info">Report</button>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col mt-3">
						<table class="table table-bordered">
							<tr>
								<th>Username</th>
								<th>Fullname</th>
								<th>Email</th>
								<th>Favorite Date</th>
							</tr>
							<c:forEach var="item" items="${favUsers }">
							<tr>
								<td>${item.username }</td>
								<td>${item.fullname }</td>
								<td>${item.email }</td>
								<td>${item.likeDate }</td>
							</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</form>
		</div>
		<div class="tab-pane fade" id="shareFriends" role="tabpanel"
			aria-labelledby="shareFriends-tab">
			<form action="" method="get">
				<div class="row mt-3">
					<div class="col">
						<div class="form-inline">
							<div class="form-group">
								<label>Video Title 
								<select name="videoUserId" id="videoUserId"
									class="form-control">
									<c:forEach var="item" items="${vidList }">
										<option value="${item.videoId }" ${item.videoId == videoUserId?'selected':'' }>${item.title }</option>
									</c:forEach>
								</select>
								</label>
								<button class="btn btn-info">Report</button>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col mt-3">
						<table class="table table-bordered">
							<tr>
								<th>Sender Name</th>
								<th>Sender Email</th>
								<th>Receiver Email</th>
								<th>Send Date</th>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</table>
					</div>
				</div>
			</form>
		</div>

	</div>
</div>