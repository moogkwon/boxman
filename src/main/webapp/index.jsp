<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>BOXMAN-로그인</title>

<!-- Custom fonts for this template-->
<link href="${path }/resources/b4/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

<!-- Custom styles for this template-->
<link href="${path }/resources/b4/css/sb-admin-2.min.css" rel="stylesheet">
<style>
.vertical-center {
	min-height: 100%; /* Fallback for browsers do NOT support vh unit */
	min-height: 100vh; /* These two lines are counted as one :-) */
	display: flex;
	align-items: center;
}
</style>
</head>

<body class="bg-gradient-primary vertical-center">
	<div class="container">
		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="row">
							<div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
							<div class="col-lg-6">
								<div class="p-5">
									<div class="text-center">
										<h1 class="h4 text-gray-900 mb-4">BOXMAN</h1>
									</div>
									<form class="user" action="${path }/bfLogin/loginEmp.do" method="post">
										<div class="form-group">
											<input type="text" class="form-control form-control-user"
												id="empId" name="empId" placeholder="아이디">
										</div>
										<div class="form-group">
											<input type="password" class="form-control form-control-user"
												id="empPassword" name="empPassword" placeholder="비밀번호">
										</div>
										<input type="submit"
											class="btn btn-primary btn-user btn-block" value="로그인"/>
											<%--  href="${path }/emp/loginEmp.do" --%>
									</form>
									<%-- <hr>
									<div class="text-center">
										<a class="small" href="${path }/bfLogin/forgotPassword.do">비밀번호 찾기</a>
									</div> --%>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="${path }/resources/b4/vendor/jquery/jquery.min.js"></script>
	<script
		src="${path }/resources/b4/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="${path }/resources/b4/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="${path }/resources/b4/js/sb-admin-2.min.js"></script>

</body>

</html>
