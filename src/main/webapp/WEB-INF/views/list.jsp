<%--
  Created by IntelliJ IDEA.
  User: MyHusky
  Date: 2020/7/20
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>员工列表</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/icofont/icofont.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/custom.css">
</head>
<body>

    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>SSM CRUD</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 col-md-offset-8">
                <button class="btn btn-primary">新增</button>
                <button class="btn btn-danger">删除</button>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <table class="table table-hover center">
                    <tr>
                        <th>id</th>
                        <th>empName</th>
                        <th>gender</th>
                        <th>email</th>
                        <th>deptName</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${pageInfo.list}" var="emp">
                        <tr>
                            <th>${emp.empId}</th>
                            <th>${emp.empName}</th>
                            <th>${emp.gender=="M"?"男":"女"}</th>
                            <th>${emp.email}</th>
                            <th>${emp.department.deptName}</th>
                            <th>
                                <button class="btn btn-info btn-xs"><i class="icofont-pen-alt-2"></i> 编辑</button>
                                <button class="btn btn-danger btn-xs"><i class="icofont-bin"></i> 删除</button>
                            </th>
                        </tr>
                    </c:forEach>

                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                当前 ${pageInfo.pageNum} 页，总 ${pageInfo.pages} 页，共 ${pageInfo.total} 条记录
            </div>
            <div class="col-md-6">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <li><a href="${pageContext.request.contextPath}/emps?pn=1">首页</a></li>
                        <c:if test="${pageInfo.hasPreviousPage}">
                            <li>
                                <a href="${pageContext.request.contextPath}/emps?pn=${pageInfo.pageNum - 1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <f:forEach items="${pageInfo.navigatepageNums}" var="page_Num">
                            <c:if test="${page_Num == pageInfo.pageNum}">
                                <li class="active"><a href="#">${page_Num}</a></li>
                            </c:if>
                            <c:if test="${page_Num != pageInfo.pageNum}">
                                <li><a href="${pageContext.request.contextPath}/emps/?pn=${page_Num}">${page_Num}</a></li>
                            </c:if>
                        </f:forEach>
                        <c:if test="${pageInfo.hasNextPage}">
                            <li>
                                <a href="${pageContext.request.contextPath}/emps?pn=${pageInfo.pageNum + 1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <li><a href="${pageContext.request.contextPath}/emps?pn=${pageInfo.pages}">末页</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>
