<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<jsp:include page="/WEB-INF/views/common/header.jsp">   
   <jsp:param value="구매 관리" name="tabTitle"/> 
</jsp:include>

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
								<option value="#">구매코드</option>
							</select>
							<input type="search" class="form-control form-control-sm" name="data" aria-controls="dataTable">
						</label>
						<button onclick = "searchPurInfo();" class="btn btn-light btn-icon-split">
	                   		<span class="icon text-gray-600">
	                    		<i class="fas fa-arrow-right"></i>
	                   		</span>
	                   		<span class="text">검색</span>
                 		</button>
                    </div>
                   </form>
                 </div>
                 <div class="col-sm-12 col-md-6">
                  <div id="dataTable_filter" class="dataTables_filter">
                     <div style="float:right;">
	                      <a href="${path }/purchase/enrollPurInfo.do" class="btn btn-light btn-icon-split">
		                      <span class="icon text-gray-600">
		                      	<i class="fas fa-arrow-right"></i>
		                      </span>
		                      <span class="text">구매정보 등록</span>
	                  	  </a>
	                 </div>
                  </div>
                 </div>
              </div>
              <div class="row">
                 <div class="col-sm-12">
                    <table class="table table-striped table-hover" id="dataTable" width="100%" cellspacing="0" role="grid" aria-describedby="dataTable_info" style="width: 100%;">
                       <thead>
                         <tr>
                            <th>구매코드</th>
							<th>등록일자</th>
							<th>거래처</th>
							<th>담당자</th>
							<th>거래유형</th>
							<th>구매총액</th>
							<th>구매확정</th>
							<th>구매일자</th>
							<th>입금여부</th>
							<th>입금일자</th>
                         </tr>
                       </thead>
                       <tbody>
                          <c:forEach items="${list }" var="p">
								<tr>
									<td><a href='#'><c:out value='${p["PURCODE"] }'/></a></td>
									<td><c:out value='${p["PURENROLLDATE"] }' /></td>
									<td><c:out value='${p["CONNAME"] }' /></td>
									<td><c:out value='${p["EMPNAME"] }' /></td>
									<td><c:out value='${p["PURTRANTYPE"] }' /></td>
									<td><c:out value='${p["PURTOTAMT"] }'/></td>
									<td><c:out value='${p["PURCK"] }'/></td>
									<td><c:out value='${p["PURDATE"] }' /></td>
									<td><c:out value='${p["DEPOSCK"] }' /></td>
									<td><c:out value='${p["DEPOSDATE"] }' /></td>
								</tr>
							</c:forEach>
                       </tbody>
                     </table>
                   </div>
                 </div>
               </div>
			</div>
		</div>
       ${pageBar }
	</div>

</section>

<script>
function searchPurInfo(){
	$("#searchFrm").attr("action","${path}/purchase/searchPurInfo.do");
	$("#searchFrm").submit();
}
</script>