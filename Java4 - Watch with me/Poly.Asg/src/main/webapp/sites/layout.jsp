<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${page.title }</title>
<!-- link css -->

<base href="/Poly.Asg/">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
	integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>
	<main class="container">
		<header class="row pt-3 pb-2">
			<div class="col-9">
				<h1>Online Entertaiment</h1>
			</div>
			<div class="col-3 text-right">
				<img src="../images/800px-FPT_Polytechnic.png" alt="logo-fpt"
					class="mr-4 w-75">
			</div>
		</header>
		<nav class="row">
			<nav class=" col navbar navbar-expand-lg navbar-light bg-light">
				<div class="container-fluid">
					<a class="navbar-brand" href="#">Online Entertaiment</a>
					<button class="navbar-toggler" type="button"
						data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav me-auto mb-2 mb-lg-0">
							<li class="nav-item"><a class="nav-link active  "
								aria-current="page" href="Homepage"><i class="fa fa-home"></i> Home</a>
							</li>
							<li class="nav-item"><a class="nav-link" href="#"><i
									class="fa-solid fa-circle-info"></i>About Us</a></li>
							<li class="nav-item"><a class="nav-link" href="#"><i
									class="fa-solid fa-id-card"></i>Contact Us</a></li>
							<li class="nav-item"><a class="nav-link" href="#"><i
									class="fa fa-comment" aria-hidden="true"></i> My Favorites</a></li>
							<li class="nav-item dropdown">
							
							<a
								class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-bs-toggle="dropdown" aria-expanded="false">
									<i class="fa fa-user" aria-hidden="true"></i> My Account
							</a>
								<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
									<c:if test="${! isLogin }">
										<li><a class="dropdown-item" href="Login">Login</a></li>
										<li><a class="dropdown-item" href="#">Forgot Password
										</a></li>
										<li><a class="dropdown-item" href="Registration">Registration
										</a></li>
									</c:if>
									<c:if test="${isLogin }">
										<li><a class="dropdown-item" href="Logoff">Logoff</a></li>
										<li><a class="dropdown-item" href="ChangePassword">Change Password
										</a></li>
										<li><a class="dropdown-item" href="EditProfile">Edit Profile</a></li>
									</c:if>
								</ul></li>

						</ul>
					</div>
				</div>
			</nav>
		</nav>
		<section class="row">
			<jsp:include page="${page.contentUrl }"></jsp:include>
		</section>
		<footer class="row">
			<div class="col text-center border-top">
				<strong>Fpt Poly &copy;2022. All right reserved.</strong>
			</div>
		</footer>
	</main>



	<!-- javascript bootstrap -->
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
		integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
		integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
		crossorigin="anonymous"></script>
	<c:if test="${not empty page.scriptUrl }">
		<jsp:include page="${page.scriptUrl }"></jsp:include>
	</c:if>


</body>
</html>