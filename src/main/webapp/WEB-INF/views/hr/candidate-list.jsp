<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="fa.training.impsystem.consts.CommonConst" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>
        Candidate Management
    </title>
</head>
<body>
    <div>
        <c:if test="${responseStatus != null}">
            <div class="alert ${responseStatus.hasError() ? "alert-danger" : "alert-success"} text-light" role="alert">
                    ${responseStatus.message}
            </div>
        </c:if>
    </div>
    <div>
        <table class="table align-items-center mb-0">
            <caption><h2>Candidate</h2></caption>
            <thead>
            <tr>
                <th>STT</th>
                <th>Họ và Tên</th>
                <th>CMND</th>
                <th>SDT</th>
                <th>Email</th>
                <th>Thông tin</th>
                <th>Trạng thái</th>
                <th>Hành động</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="count" value="0"/>
            <c:forEach var="candidate" items="${candidates}">
                <tr>
                    <td>
                        <c:set var="count" value="${count+1}"/>
                        <p>${count}</p>
                    </td>
                    <td>
                        <p>${candidate.candidateName}</p>
                    </td>
                    <td>
                        <p>${candidate.cardId}</p>
                    </td>
                    <td>
                        <p>${candidate.phone}</p>
                    </td>
                    <td>
                        <p>${candidate.email}</p>
                    </td>
                    <td>
                        <p>
                            <a href="#">Chi tiết</a>
                        </p>
                    </td>
                    <td>
                        <p>${candidate.status}</p>
                    </td>
                    <td class="align-middle">
                        <a href="${pageContext.request.contextPath}${CommonConst.HR_CANDIDATE_UPDATE_URL}?id=${candidate.candidateId}">
                            Cập nhật
                        </a> ||
                        <a href="${pageContext.request.contextPath}${CommonConst.HR_CANDIDATE_DELETE_URL}?id=${candidate.candidateId}">
                            Xóa
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>
