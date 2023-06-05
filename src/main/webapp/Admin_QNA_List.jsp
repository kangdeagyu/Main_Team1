<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>공지사항</title>
    <style>
        .pagination-wrapper {
            margin-top: 20px;
        }

        .pagination-item {
            display: inline-block;
            margin-right: 5px;
        }

        .pagination-item a {
            padding: 5px 10px;
            border: 1px solid #ccc;
            text-decoration: none;
            color: #333;
        }

        .pagination-item a.active {
            background-color: #333;
            color: #fff;
        }
    </style>
</head>
<body>
<table>
    <tbody id="tableBody">
        <c:forEach items="${list}" var="dto">
            <tr>
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

<script>
    // 실제 데이터가 필요하지 않으므로 realData 변수를 제거합니다.

    function GetTarget(pageNumber) {
        var dataPerPage = 10; // 한 페이지에 보여줄 데이터 개수
        var startIndex = (pageNumber - 1) * dataPerPage;
        var endIndex = startIndex + dataPerPage;
        var data = $(".data-row").slice(startIndex, endIndex);

        renderData(data);
    }

    function renderData(data) {
        var tableBody = $("#tableBody");
        tableBody.empty();
        tableBody.append(data);
    }

    GetTarget(1);

    function paging(totalData, currentPage) {
        var dataPerPage = 10; // 한 페이지에 보여줄 데이터 개수
        var pageCount = 5; // 페이지 그룹의 개수

        var totalPage = Math.ceil(totalData / dataPerPage);
        var pageGroup = Math.ceil(currentPage / pageCount);

        var last = pageGroup * pageCount;

        if (last > totalPage)
            last = totalPage;
        var first = last - (pageCount - 1);

        var next = last + 1;
        var prev = first - 1;

        var pages = $("#pages");
        pages.empty();

        if (first > 5) {
            pages.append("<li class=\"pagination-item\">" +
                "<a onclick=\"GetTarget(" + prev + ");\" style='margin-left: 2px'>이전</a></li>");
        }

        for (var j = first; j <= last; j++) {
            if (currentPage === j) {
                pages.append("<li class=\"pagination-item\">" +
                    "<a class='active' onclick=\"GetTarget(" + j +
                    ");\" style='margin-left: 2px'>" + j + "</a></li>");
            } else if (j > 0) {
                pages.append("<li class=\"pagination-item\">" +
                    "<a onclick=\"GetTarget(" + j + ");\" style='margin-left: 2px'>" + j + "</a></li>");
            }
        }

        if (next > 5 && next < totalPage) {
            pages.append("<li class=\"pagination-item\">" +
                "<a onclick=\"GetTarget(" + next + ");\" style='margin-left: 2px'>다음</a></li>");
        }
    }

    paging(${list.size()}, 1);
</script>
</body>
</html>