<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../Include/MainPage.jsp"%>
<script src="resources/js/loginScript.js"></script>
<meta charset="UTF-8">
<title>방명록</title>
<body>
<input type="radio" name="chk_info" value="성공">성공
<input type="radio" name="chk_info" checked="checked" value="실패">실패
	<div class="row">
		<h2 class="text-center" id="listcomment">방명록 작성하기</h2>
		<h5 align="right">Total : ${totalRecord} Articles ( ${page.nowPage} / ${page.totalPage} ) </h5>
		<form name="commentInsert" id="insertForm" method="POST">
			<div class="table table-responsive">
				<table class="table table-striped">
					<tr>
						<td align="right">
							<input type="text" class="form-control" name="commentuser" id="username" placeholder="닉네임" maxlength="15" value="${sessionScope.idKey}">
						</td>
					</tr>
					<tr>
						<td>
							<textarea rows="5" cols="50" name="content" id= "content" class="form-control" style="resize: none;"></textarea>
						</td>
					</tr>
					<tr>
						<td align="right">
							<input type="checkbox" name="secret" value="0"/> 비밀글 &nbsp;
							<button type="button" id="commentSubmit" class="btn btn-success">답글 작성</button>
						</td>
					</tr>
				</table>
			</div>
		</form>
		
		<!-- 게시물 번호  작업 시작 -->
		<!-- Test 해보고 내릴 예정 -->
		<!-- Break가 걸리지 않기 때문에 T/F 주자 -->
		<%-- <c:set var="Loop" value="false"></c:set> 
		<c:set var="listsize" value="${totalRecord - page.start}"/>	
		계시물 번호 : 		
		<c:forEach var="i" begin="0" end="${page.numPerPage-1}" varStatus="status">
			<c:if test="${i == listsize}">
				<c:set var="Loop" value="true"/>
			</c:if>
			<c:if test="${i != listsize}">				
				<c:if test="${(totalRecord - ((page.nowPage-1)* page.numPerPage)- i) > 0}">
					<c:out value="${totalRecord - ((page.nowPage-1)* page.numPerPage)- i}"></c:out>	
				</c:if>					
			</c:if>
		</c:forEach>
		<br> --%>
		<!-- 게시물 번호  작업 완료 -->
		
		<!-- 페이징 & 블럭 Start -->
		<c:set var="pageStart" value="${(page.nowBlock -1) * page.pagePerBlock + 1}"></c:set>
		<c:set var="pageEnd" value="${((pageStart + page.pagePerBlock) <= page.totalPage) ? (pageStart+page.pagePerBlock):page.totalPage+1}"></c:set>
		
		<c:if test="${page.totalPage != 0}">			
			<div class="text-center">
				<ul class ="pagination">
					<li>
					<c:if test="${page.nowBlock > 1}">
						<a href="Comment?nowPage=${page.pagePerBlock * (page.nowBlock-1)}">prev...</a>&nbsp;
					</c:if>	
						<c:forEach var="i" begin="${pageStart}" end="${pageEnd-1}">	
							<a href="Comment?nowPage=${i}&start=${i * page.numPerPage-10}">			
							<c:if test="${i == page.nowPage}">	
								<font color="red">${i}</font>										
								<c:set var="Loop" value="false"></c:set>								
							</c:if>
							<c:if test="${i != page.nowPage}">${i}</c:if>
							</a>
						</c:forEach>
						<c:if test="${page.totalBlock > page.nowBlock}">
							<a href="Comment?nowPage=${page.pagePerBlock * (page.nowBlock+1-1)+1}">...next</a>&nbsp;
							
						</c:if>	
					</li>
				</ul>
			</div>		
		</c:if>
		<!-- 페이징 & 블럭 End -->
		
		<%-- <c:forEach var="i" begin="0" end="${page.numPerPage-1}" varStatus="status">
			<c:if test="${i == listsize}">
				<c:set var="Loop" value="true"/>
			</c:if>
			<c:if test="${i != listsize}">				
				<c:if test="${(totalRecord - ((page.nowPage-1)* page.numPerPage)- i) > 0}">
					<c:out value="${totalRecord - ((page.nowPage-1)* page.numPerPage)- i}"></c:out>	
				</c:if>					
			</c:if>
		</c:forEach> --%>
		
		<div id="listGuestBook">
		<c:forEach var="list" items="${listComment}" varStatus="status">
			<div class="table table-responsive" id="listcomm">
				<table class="table table-striped">
					<tr>
						<td>
							No : ${totalRecord - ((page.nowPage-1)* page.numPerPage) - status.count +1}
							<font color="blue">${list.username}</font>&nbsp; <font size="0.5em">(${list.datetime})</font>
						</td>
					</tr>
					<tr>
						<td>
							<textarea rows="3" cols="10" name="content" id= "content" class="form-control" style="resize: none;" readonly>${list.usercomment}</textarea>
						</td>
					</tr>
				</table>
			</div>
		</c:forEach>
		</div>

		<!-- 페이징 & 블럭 Start -->
		<c:set var="pageStart" value="${(page.nowBlock -1) * page.pagePerBlock + 1}"></c:set>
		<c:set var="pageEnd" value="${((pageStart + page.pagePerBlock) <= page.totalPage) ? (pageStart+page.pagePerBlock):page.totalPage+1}"></c:set>
		
		<c:if test="${page.totalPage != 0}">			
			<div class="text-center">
				<ul class ="pagination">
					<li>
					<c:if test="${page.nowBlock > 1}">
						<a href="Comment?nowPage=${page.pagePerBlock * (page.nowBlock-1)}">prev...</a>&nbsp;
					</c:if>	
						<c:forEach var="i" begin="${pageStart}" end="${pageEnd-1}">	
							<a href="Comment?nowPage=${i}&start=${i * page.numPerPage-10}">			
							<c:if test="${i == page.nowPage}">	
								<font color="red">${i}</font>										
								<c:set var="Loop" value="false"></c:set>								
							</c:if>
							<c:if test="${i != page.nowPage}">${i}</c:if>
							</a>
						</c:forEach>
						<c:if test="${page.totalBlock > page.nowBlock}">
							<a href="Comment?nowPage=${page.pagePerBlock * (page.nowBlock+1-1)+1}">...next</a>&nbsp;
							
						</c:if>	
					</li>
				</ul>
			</div>		
		</c:if>
		<!-- 페이징 & 블럭 End -->

		<!-- 유효성 검사 -->
		<div class="modal fade" id="layerpop" >
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <!-- header -->
		      <div class="modal-header">
		        <!-- 닫기(x) 버튼 -->
		        <button type="button" class="close" data-dismiss="modal">×</button>
		        <!-- header title -->
		        <h4 class="modal-title">방명록 작성 실패</h4>
		      </div>
		      <!-- body -->
		      <div class="modal-body">
		      	입력한 내용을 확인해주세요! (특수문자, 이모티콘 등..)
		      </div>
		      <!-- Footer -->
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
		      </div>
		    </div>
		  </div>
		</div>

	</div>	
</body>