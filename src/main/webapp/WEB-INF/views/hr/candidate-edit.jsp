<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="fa.training.impsystem.consts.CommonConst" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
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
    <form:form modelAttribute="candidate"
               action="${pageContext.request.contextPath}${CommonConst.HR_CANDIDATE_UPDATE_URL}"
               method="post">
        <div>
            <h2>Candidate Edit</h2>
            <div>
                <button type="submit">Save</button>
                <a href="${pageContext.request.contextPath}${CommonConst.HR_CANDIDATE_LIST_URL}">Back
                </a>
            </div>
        </div>
        <table>
            <tr style="display: none">
<%--                <td>--%>
<%--                    Candidate ID--%>
<%--                </td>--%>
                <td>
                    <form:input path="candidateId" type="number"/>
                </td>
                <td>
                    <form:errors path="candidateId"/>
                </td>
            </tr>
            <tr>
                <td>
                    Candidate Name
                </td>
                <td>
                    <form:input path="candidateName" type="text"/>
                </td>
                <td>
                    <form:errors path="candidateName"/>
                </td>
            </tr>
            <tr>
                <td>
                    Card Id
                </td>
                <td>
                    <form:input path="cardId" type="number"/>
                </td>
                <td>
                    <form:errors path="cardId"/>
                </td>
            </tr>
            <tr>
                <td>
                    Card Date
                </td>
                <td>
                    <form:input path="cardIdDate" type="date"/>
                </td>
                <td>
                    <form:errors path="cardIdDate"/>
                </td>
            </tr>
            <tr>
                <td>
                    Phone
                </td>
                <td>
                    <form:input path="phone" type="text"/>
                </td>
                <td>
                    <form:errors path="phone"/>
                </td>
            </tr>
            <tr>
                <td>
                    Gender
                </td>
                <td>
                    <form:radiobutton path="gender" value="0"/> Male
                    <form:radiobutton path="gender" value="1"/> Female
                </td>
                <td>
                    <form:errors path="gender"/>
                </td>
            </tr>
            <tr>
                <td>
                    Email
                </td>
                <td>
                    <form:input path="email" type="text"/>
                </td>
                <td>
                    <form:errors path="email"/>
                </td>
            </tr>
            <tr>
                <td>
                    Experience
                </td>
                <td>
                    <form:input path="experience" type="text"/>
                </td>
                <td>
                    <form:errors path="experience"/>
                </td>
            </tr>
            <tr>
                <td>
                    Activity
                </td>
                <td>
                    <form:input path="activity" type="text"/>
                </td>
                <td>
                    <form:errors path="activity"/>
                </td>
            </tr>
            <tr>
                <td>
                    Photo
                </td>
                <td>
                    <form:input path="photo" type="file"/>
                </td>
                <td>
                    <form:errors path="photo"/>
                </td>
            </tr>
            <tr>
                <td>
                    Status
                </td>
                <td>
                    <form:input path="photo" type="file"/>
                </td>
                <td>
                    <form:errors path="photo"/>
                </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
