<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판</title>
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
    <tbody id="tableBody"></tbody>
</table>

<div class="pagination-wrapper clearfix">
    <ul class="pagination float--right" id="pages">
    </ul>
</div>

<script>
    function paging(totalData, currentPage) {
        const dataPerPage = 10;
        const pageCount = 5;

        const totalPage = Math.ceil(totalData / dataPerPage);
        const pageGroup = Math.ceil(currentPage / pageCount);

        let last = pageGroup * pageCount;

        if (last > totalPage)
            last = totalPage;
        let first = last - (pageCount - 1);

        const next = last + 1;
        const prev = first - 1;

        if (totalPage < 1) {
            first = last;
        }
        const pages = $("#pages");
        pages.empty();
        if (first > 5) {
            pages.append("<li class=\"pagination-item\">" +
                "<a onclick=\"GetTarget(" + prev + ");\" style='margin-left: 2px'>prev</a></li>");
        }
        for (let j = first; j <= last; j++) {
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
                "<a onclick=\"GetTarget(" + next + ");\" style='margin-left: 2px'>next</a></li>");
        }
    }
 // 임시 데이터 배열
    var tempData = [
        { id: 1, name: "Product 1", price: 100 },
        { id: 2, name: "Product 2", price: 200 },
        { id: 3, name: "Product 3", price: 300 },
        { id: 4, name: "Product 4", price: 400 },
        { id: 5, name: "Product 5", price: 500 },
        { id: 6, name: "Product 6", price: 600 },
        { id: 7, name: "Product 7", price: 700 },
        { id: 8, name: "Product 8", price: 800 },
        { id: 9, name: "Product 9", price: 900 },
        { id: 10, name: "Product 10", price: 1000 },
        { id: 11, name: "Product 10", price: 1000 },
        { id: 12, name: "Product 10", price: 1000 },
        { id: 13, name: "Product 10", price: 1000 },
        { id: 14, name: "Product 10", price: 1000 },
        { id: 15, name: "Product 10", price: 1000 },
        { id: 16, name: "Product 10", price: 1000 },
        { id: 17, name: "Product 10", price: 1000 },
        { id: 18, name: "Product 10", price: 1000 },
        { id: 19, name: "Product 10", price: 1000 },
        { id: 20, name: "Product 10", price: 1000 },
        { id: 21, name: "Product 10", price: 1000 },
        { id: 22, name: "Product 10", price: 1000 },
        { id: 23, name: "Product 10", price: 1000 },
        { id: 24, name: "Product 10", price: 1000 },
        { id: 25, name: "Product 10", price: 1000 },
        { id: 26, name: "Product 10", price: 1000 },
        { id: 27, name: "Product 10", price: 1000 },
        { id: 28, name: "Product 10", price: 1000 },
        { id: 29, name: "Product 10", price: 1000 }
    ];

    function GetTarget(pageNumber) {
        // 페이지에 해당하는 데이터를 임시 데이터 배열에서 가져옴
        var startIndex = (pageNumber - 1) * 10;
        var endIndex = startIndex + 10;
        var data = tempData.slice(startIndex, endIndex);

        // 데이터를 화면에 표시하는 로직
        renderData(data);
    }

    function renderData(data) {
        var tableBody = $("#tableBody");
        tableBody.empty();

        // 데이터를 반복하여 테이블에 추가
        for (var i = 0; i < data.length; i++) {
            var rowData = data[i];
            var rowHtml = "<tr>" +
                "<td>" + rowData.id + "</td>" +
                "<td>" + rowData.name + "</td>" +
                "<td>" + rowData.price + "</td>" +
                "</tr>";
            tableBody.append(rowHtml);
        }
    }

    // 초기 페이지 로딩 시 첫 번째 페이지 데이터 표시
    GetTarget(1);

    // 초기 페이지 로딩 시 페이징 함수 호출
    paging(tempData.length, 1);
</script>
</body>
</html>