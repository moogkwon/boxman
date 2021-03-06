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
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

  <title>${param.tabTitle }</title>

  <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>

  <!-- Custom fonts for this template-->
  <link href="${path }/resources/b4/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- CSS -->
  <link href="${path }/resources/b4/css/sb-admin-2.min.css" rel="stylesheet">
  
      <!-- Bootstrap core JavaScript-->
  <script src="${path }/resources/b4/vendor/jquery/jquery.min.js"></script>
  <script src="${path }/resources/b4/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="${path }/resources/b4/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="${path }/resources/b4/js/sb-admin-2.min.js"></script>
  
  <!-- favicon -->
  <link rel="icon" href="${path }/resources/logo/boxmanLogo.ico" type="image/gif" sizes="16x16">
  <!-- datepicker -->
  <link rel="stylesheet" href="${path }/resources/hb/css/bootstrap-datepicker.css">
  
  <!-- tableSorter -->
  <script src='${path }/resources/hb/js/jquery.tablesorter.min.js'></script>
    
  <script src="${path }/resources/hb/js/bootstrap-datepicker.js"></script>
  <script src="${path }/resources/hb/js/bootstrap-datepicker.ko.js"></script>

</head>
<style>
.table-responsive {
    display: block;
    width: 100%;
    overflow-x: hidden;
    -webkit-overflow-scrolling: touch;
}

.btn-success{
	background-color: #4e73df;
	border-color: #4e73df;
}

.btn-success hover{
	background-color: white;
	border-color: #4e73df;
}

.btn-outline-success{
	background-color: #4e73df;
	border-color: #4e73df;
	color: white;
}

</style>

<body>
<section>
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">일정 수정</h6>
		</div>
		<form id = "updateCalFrm" method = "post" action="${path}/calendar/updateCal2.do" onsubmit="return checkValue();">
		<div class="card-body">
		<input type = "hidden" name = "calNo" value = "${c._id }"/>
		<c:if test='${fn:trim(c.allDay) eq "true"}'>
		<label for="exampleInputName1">AllDay</label><br>
		 	<div class="custom-control">
		 		<input type="radio" class="custom-control-input" id="customCheck" name="allDay" value = "true" checked="checked">
				<label class="custom-control-label" for="customCheck">Y</label>
			</div>
			<div class="custom-control">
				<input type="radio" class="custom-control-input" id="customCheck1" name="allDay" value = "false">
				<label class="custom-control-label" for="customCheck1">N</label>
			</div>
		</c:if>
				<c:if test='${fn:trim(c.allDay) eq "false"}'>
		<label for="exampleInputName1">AllDay</label><br>
		 	<div class="custom-control">
		 		<input type="radio" class="custom-control-input" id="customCheck" name="allDay" value = "true">
				<label class="custom-control-label" for="customCheck">Y</label>
			</div>
			<div class="custom-control">
				<input type="radio" class="custom-control-input" id="customCheck1" name="allDay" value = "false" checked="checked">
				<label class="custom-control-label" for="customCheck1">N</label>
			</div>
		</c:if>
		<br>
			<div class="form-group">
				<label for="exampleInputName1">Title</label> 
				<input type="text" class="form-control" id="stuffName" value = "${c.title }" name="title">
			</div>
			<div class="form-group">
				<label for="exampleInputName1">Start</label>
 				<input type="date"
					class="form-control" id="exampleInputName1"
					name="start" value = "${c.start }">
			</div>
			
			<div class="form-group">
				<label for="exampleInputName1">End</label>
 				 				<input type="date"
					class="form-control" id="exampleInputName2"
					name="end" value = "${c.end }">
			</div>
			
		    <div class = "form-group row">
				<label class = "col-sm-2 col-form-labe">카테고리</label>
			</div>
				<div class = "col-sm-10">
					<select name = "backgoundColor" id = "color" class = "form-control">
					<c:if test='${fn:trim(c.backgroundColor) eq "#D25565"}'>
						<option value="#D25565" style="color: #D25565;">빨간색</option>
					</c:if>
					<c:if test='${fn:trim(c.backgroundColor) eq "#9775fa"}'>
						<option value="#D25565" style="color: #9775fa;">보라색</option>
					</c:if>
					<c:if test='${fn:trim(c.backgroundColor) eq "#ffa94d"}'>
						<option value="#D25565" style="color: #ffa94d;">주황색</option>
					</c:if>
					<c:if test='${fn:trim(c.backgroundColor) eq "#74c0fc"}'>
						<option value="#D25565" style="color: #74c0fc;">파란색</option>
					</c:if>
					<c:if test='${fn:trim(c.backgroundColor) eq "#f06595"}'>
						<option value="#D25565" style="color: #f06595;">핑크색</option>
					</c:if>
					<c:if test='${fn:trim(c.backgroundColor) eq "#63e6be"}'>
						<option value="#D25565" style="color: #63e6be;">연두색</option>
					</c:if>
					<c:if test='${fn:trim(c.backgroundColor) eq "#a9e34b"}'>
						<option value="#D25565" style="color: #a9e34b;">초록색</option>
					</c:if>
					<c:if test='${fn:trim(c.backgroundColor) eq "#4d638c"}'>
						<option value="#D25565" style="color: #4d638c;">남색</option>
					</c:if>				
					<c:if test='${fn:trim(c.backgroundColor) eq "#495057"}'>
						<option value="#D25565" style="color: #495057;">검정색</option>
					</c:if>									
						<option value="#D25565" style="color: #D25565;">빨간색</option>
						<option value="#9775fa" style="color: #9775fa;">보라색</option>
						<option value="#ffa94d" style="color: #ffa94d;">주황색</option>
						<option value="#74c0fc" style="color: #74c0fc;">파란색</option>
						<option value="#f06595" style="color: #f06595;">핑크색</option>
						<option value="#63e6be" style="color: #63e6be;">연두색</option>
						<option value="#a9e34b" style="color: #a9e34b;">초록색</option>
						<option value="#4d638c" style="color: #4d638c;">남색</option>
						<option value="#495057" style="color: #495057;">검정색</option>
					</select>	
				</div>
				<br>
		    <div class = "form-group row">
				<label class = "col-sm-2 col-form-labe">구분</label>
			</div>
				<div class = "col-sm-10">
					<select class="form-control" name="type" id="edit-type">
					    <option value = "${c.type }">${c.type }</option>
						<option value="개인">개인</option>
						<option value="부서">부서</option>
						<option value="회사">회사</option>
					</select>
				</div>
				<br>
				<div class="form-group">
                        <label for="exampleTextarea1">설명</label>
                        <textarea rows="8" cols="20" class = "form-control" id = "etc" name = "description" style = "resize: none">${c.description }</textarea>
            	</div>
            	<input type = "submit" class = "btn btn-success mr-2" value="수정" id = "btn">
		</div>
					
		</form>
	</div>

</section>

<script>
	function checkValue() {
		if($('#exampleInputName1').val() > $('#exampleInputName2').val()) {
			alert("종료 날짜는 시작 날짜보다 빠를 수 없습니다!");
			
			return false;
		}
	}
</script>



</body>
</html>