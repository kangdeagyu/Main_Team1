<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>공지사항</title>
    <link rel="stylesheet" type="text/css" href="aQnA_style.css">
    <script>
        var totalData = ${listSize};

        function renderData(data) {
            var tableBody = $("#tableBody");
            tableBody.empty();
            tableBody.append(data);
        }

        function GetTarget(pageNumber) {
            var dataPerPage = 10;
            var startIndex = (pageNumber - 1) * dataPerPage;
            var endIndex = startIndex + dataPerPage;
            var rows = $("#tableBody tr.data-row");
            rows.addClass("hidden-row"); // 모든 행 숨기기
            rows.slice(startIndex, endIndex).removeClass("hidden-row"); // 페이지에 해당하는 행 표시

            paging(totalData, pageNumber);
        }

        function paging(totalData, currentPage) {
            var dataPerPage = 10;
            var pageCount = 10;

            var totalPage = Math.ceil(totalData / dataPerPage);
            var pageGroup = Math.ceil(currentPage / pageCount);

            var last = pageGroup * pageCount;
            var first = last - (pageCount - 1);
            var next = last + 1;
            var prev = first - 1;

            var pages = $("#pages");
            pages.empty();

            if (first > 10) {
                pages.append("<li class=\"pagination-item\">" +
                    "<a onclick=\"GetTarget(" + prev + ");\" style='margin-left: 2px'>이전</a></li>");
            }
            for (var i = first; i <= last; i++) {
                if (i > totalPage) {
                    break;
                }
                if (i == currentPage) {
                    pages.append("<li class=\"pagination-item\">" +
                        "<a class=\"active\">" + i + "</a></li>");
                } else {
                    pages.append("<li class=\"pagination-item\">" +
                        "<a onclick=\"GetTarget(" + i + ");\">" + i + "</a></li>");
                }
            }
            if (last < totalPage) {
                pages.append("<li class=\"pagination-item\">" +
                    "<a onclick=\"GetTarget(" + next + ");\" style='margin-left: 2px'>다음</a></li>");
            }
        }

        $(document).ready(function() {
            GetTarget(1); // 초기 페이지 설정
        });
    </script>
</head>
<body>
    <table>
        <thead>
            <tr class="data-row">
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>등록일</th>
            </tr>
        </thead>
        <tbody id="tableBody">
            <c:forEach items="${list}" var="dto" varStatus="status">
                <tr class="data-row hidden-row" id="dataRow${status.index}">
                    <td>${dto.fid}</td>
                    <td>${dto.ftitle}</td>
                    <td>${dto.f_cid}</td>
                    <td>${dto.finsertdate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div class="pagination-wrapper clearfix">
        <ul class="pagination float--right" id="pages">
        </ul>
    </div>
</body>
</html>