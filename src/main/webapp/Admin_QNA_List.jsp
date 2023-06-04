<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
	</script>
</body>
</html>