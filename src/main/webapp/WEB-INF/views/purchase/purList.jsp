<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<jsp:include page="/WEB-INF/views/common/header.jsp">   
   <jsp:param value="구매 관리" name="tabTitle"/> 
</jsp:include>

<style>
th {
	text-align: center;
}
</style>

<section>

	<div class="card shadow mb-4">
       <div class="card-header py-3">
         <h6 class="m-0 font-weight-bold text-primary">구매 목록</h6>
       </div>
       <div class="card-body">
         <div class="table-responsive">
           <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
              <div class="row">
                 <div class="col-sm-12 col-md-6">
                 <form id="searchFrm">
                    <div class="dataTables_length" id="dataTable_length">
                       <label>Search:
                       		<select name="type" id="searchKeyword" class="form-control form-control-sm">
								<option value="purCode">구매코드</option>
								<option value="conCode">거래처명</option>
								<option value="empNo">담당자명</option>
								<option value="purCk">결재승인여부</option>
								<option value="deposCk">입금확정여부</option>
							</select>
							<input type="search" class="form-control form-control-sm" name="data" aria-controls="dataTable">
							<input type="hidden" name="empId" value="${loginEmp['EMPID'] }">
						</label>
						<button onclick = "searchPurInfo();" class="btn btn-primary mr-2">
	                   		<span class="text">검색</span>
                 		</button>
                    </div>
                   </form>
                 </div>
                 <div class="col-sm-12 col-md-6">
                  <div id="dataTable_filter" class="dataTables_filter">
                     <div style="float:right;">
                      <button type="button" onclick="location.href='${path}/purchase/enrollPurInfo.do'" class="btn btn-primary mr-2">
					        구매정보 등록
					</button>
	                 </div>
                  </div>
                 </div>
              </div>
              <div class="row">
                 <div class="col-sm-12">
                    <table class="table table-striped table-hover tablesorter" id="myTable" width="100%" cellspacing="0" role="grid" aria-describedby="dataTable_info" style="width: 100%;">
                       <thead>
                         <tr>
                            <th>구매코드</th>
							<th>등록일자</th>
							<th>거래처</th>
							<th>담당자</th>
							<th>구매총액</th>
							<th>결재승인</th>
							<th>승인일자</th>
							<th>송금여부</th>
							<th>송금일자</th>
                         </tr>
                       </thead>
                       <tbody>
                          <c:forEach items="${list }" var="p">
								<tr style="text-align:center;">
									<td><a href='${path }/purchase/verificationPurInfo.do?purCode=${p["PURCODE"]}'><c:out value='${p["PURCODE"] }'/></a></td>
									<td><c:out value='${p["PURENROLLDATE"] }' /></td>
									<td><c:out value='${p["CONNAME"] }' /></td>
									<td><c:out value='${p["EMPNAME"] }' /></td>
									<td style="text-align:right;"><c:out value='${p["PURTOTAMT"] }'/></td>
									<td><c:out value='${p["PURCK"] }'/></td>
									<td>
										<c:if test='${p["PURDATE"] eq null}'>
											-
										</c:if>
										<c:if test='${p["PURDATE"] ne null}'>
											<c:out value='${p["PURDATE"] }' />
										</c:if>
									</td>
									<td><c:out value='${p["DEPOSCK"] }' /></td>
									<td>
										<c:if test='${p["DEPOSDATE"] eq null}'>
											-
										</c:if>
										<c:if test='${p["DEPOSDATE"] ne null}'>
											<c:out value='${p["DEPOSDATE"] }' />
										</c:if>
									</td>
								</tr>
							</c:forEach>
                       </tbody>
                     </table>
                   </div>
                 </div>
               </div>
			</div>
		</div>
      <div style="margin:0 auto; width:fit-content;">
		${pageBar }
      </div>
	</div>

</section>

<script>
function searchPurInfo(){
	$("#searchFrm").attr("action","${path}/purchase/searchPurInfo.do");
	$("#searchFrm").submit();
}
//테이블 정렬
$(function() {
  $("#myTable").tablesorter();
});
</script>