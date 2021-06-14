<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
    데이터가 너무 많아서 한 페이지에 모든것을 출력하기 어려운 경우
    페이징을 이용해서 전체 데이터를 일정한 범위로 나누고
    특정 범위의 데이터만 출력하는것 효율적임

    예를들어
    총 데이터수: 105, 한페이지당 출력할 게시글 수 :25
    총 페이지수 : 5페이지
--%>

<%--
    게시판 네비게이션
    현재 페이지에 따라 보여줄 페이지 블럭을 결정
    ex) 총페이지수가 27일때

    cp : 현재페이지
    cp = 1 : 1 2 3 4 5 6 7 8 9 10 다음
    cp = 3 : 1 2 3 4 5 6 7 8 9 10 다음
    cp = 9: 1 2 3 4 5 6 7 8 9 10 다음
    cp = 11: 이전 11 12 13 14 15 16 17 18 19 20 다음
    cp = 17: 이전 11 12 13 14 15 16 17 18 19 20 다음
    cp = 23: 이전 21 22 23 24 25 26 27
    따라서, cp에 따라 페이지 블럭의 시작값을 계산
    startpage = ((cp - 1) / 10) * 10 + 1
    endpage = startpage + 9
--%>

<%-- cp = currentpage --%>
<fmt:parseNumber var = "cp" value="${param.cp}" />
<fmt:parseNumber var = "sp" value="${(cp - 1) / 10}" integerOnly="true"/>
<fmt:parseNumber var = "sp" value="${sp * 10 + 1}"/>
<fmt:parseNumber var = "ep" value="${sp + 9}" />

<%-- 총 게시물 수를 / 페이지당 게시물수로 나눔 -> 총 페이지수(tp)임 --%>
<fmt:parseNumber var = "tp" value="${bdcnt / 30}" integerOnly="true" />
<c:if test="${(bdcnt % 30) > 0}" >
    <fmt:parseNumber var = "tp" value="${tp + 1}" />
</c:if>

<%-- 글번호 계산하는법 --%>
<fmt:parseNumber var="snum" value="${bdcnt - (cp - 1) * 30}" />

<%-- 페이지 링크 : 검색 기능 x  --%>
<c:set var="pglink" value="/board/list?cp=" />


<%-- 페이지 링크: 검색 기능 o, 예를들어 제목으로 빅데이터를 검색하면
http://localhost:8080/board/find?findtype=title&findkey=빅데이터&cp=1 이 검색창에 뜸  --%>
<c:if test="${not empty param.findkey}">
    <c:set var="pglink"
           value="/board/find?findtype=${param.findtype}&findkey=${param.findkey}&cp=" />
</c:if>

<div id="main">
                        <%-- 화면상단에: 자유 게시판 121 / 3604 이거한것 --%>
<div>
    <i class="fas fa-comments fa-2x"> 자유 게시판 ${tp} / ${bdcnt}</i>
    <hr>
</div><!-- 페이지 타이틀 --> 

<div class="row">
<div class="col-5 offset-1">
   <div class="form-group row">
    <select class="form-control col-3 border-primary"
            name="findtype" id="findtype">
        <option value="title">제목</option>
        <option value="titcont">제목+내용</option>
        <option value="userid">작성자</option>
        <option value="contents">내용</option>
    </select>&nbsp;
    <input type="text" name="findkey" id="findkey"
       class="form-control col-4 border-primary"
           value="${param.findkey}">
    <button type="button" id="findbtn"
            class="btn btn-primary">
    <i class="fas fa-search"></i> 검색</button>
   </div>
</div>
    <div class="col-5 text-right">
     <button type="button" class="btn btn-light" id="newbdbtn">
        <i class="fas fa-plus-circle"></i> 새글쓰기</button>
    </div>
</div><!-- 검색, 버튼 -->


<div class="row">
<div class="col-10 offset-1">
<table class="table table-striped text-center table-hover">
    <thead style="background: #dff0d8">
<tr>
<th style="width: 7%">번호</th>
<th style="">제목</th>
<th style="width: 12%">작성자</th>
<th style="width: 10%">작성일</th>
<th style="width: 7%">추천</th>
<th style="width: 7%">조회</th>
</tr>
</thead>
   <tbody>
     <tr class="text-danger bg-warning"><th>공지</th>
         <th><span class="badge badge-danger">Hot</span>
             Lorem ipsum dolor sit amet, consectetur adipiscing elit.
         </th>
         <th>운영자</th>
         <th>2021.05.21</th>
         <th>10</th>
         <th>521</th>
     </tr>

     <!-- bds : 여러 게시물 수 , 그중에 하나의 게시물(bd) -->
     <c:forEach var="bd" items="${bds}">
      <tr>
          <td>${snum}</td>
          <td><a href="/board/view?bdno=${bd.bdno}">${bd.title}</a></td>
          <td>${bd.userid}</td>
          <td>${fn:substring(bd.regdate,0,10)}</td>
          <td>${bd.thumbup}</td>
          <td>${bd.views}</td>
          <c:set var="snum" value="${snum - 1}" />
      </tr>
     </c:forEach>

      </tbody>
     </table>
   </div>
 </div>
</div>


<!-- 페이지네이션  -->
<div class="row">
    <div class="col-12">
        <ul class="pagination justify-content-center">

            <%-- '이전'버튼이 작동되어야 할때는 sp가 11보다 클때
             sp = start page, ep = end page
                disabled의 경우 html를 화면에서 보이지 않게 숨김처리 하는 기능입니다
                1페이지가 11페이지보다 작으므로 이전버튼 안눌리게 disabled한것 --%>
            <li class="page-item <c:if test="${sp lt 11}">disabled</c:if>">
                <a href="${pglink}${sp - 10}" class="page-link">이전</a></li>


             <%-- 반복문을 이용해서 페이지 링크 생성 --%>
            <c:forEach var="i" begin="${sp}" end="${ep}" step="1">
              <%-- 표시하는 페이지i가 총페이지수보다 작거나, 현재 페이지수와 같을 동안만 출력
              그리고 active를 사용함으로써 링크를 클릭 할 수 있게만듬 --%>
               <c:if test="${i le tp}">
                 <c:if test="${i eq cp}">
                   <li class="page-item active">
                     <a href="${pglink}${i}" class="page-link">${i}</a></li>
               </c:if>

                    <%-- active안써서 링크안만들어서 클릭 못하게 함 --%>
                 <c:if test="${i ne cp}">
                   <li class="page-item">
                     <a href="${pglink}${i}" class="page-link">${i}</a></li>
                </c:if>
              </c:if>
            </c:forEach>

                <%-- '다음'버튼이 작동되어야 할때는 ???
                 121페이지가 마지막 123페이지보다 이상 이므로 다음버튼 안눌리게 disabled한것
                 밑에 sp+10은 다음버튼 누를떄마다 31페이지,41페이지처럼 숫자10씩 증가함--%>
            <li class="page-item <c:if test="${ep gt tp}">disabled</c:if>">
                <a href="${pglink}${sp+10}" class="page-link">다음</a></li>
        </ul>
    </div>
</div>