<%--
  Created by IntelliJ IDEA.
  User: MyHusky
  Date: 2020/7/20
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                        <th>#</th>
                        <th>empName</th>
                        <th>gender</th>
                        <th>email</th>
                        <th>deptName</th>
                        <th>操作</th>
                    </tr>
                    <tr>
                        <th>1</th>
                        <th>echo</th>
                        <th>男</th>
                        <th>echo.me@hotmail.com</th>
                        <th>开发部</th>
                        <th>
                            <button class="btn btn-info btn-xs"><i class="icofont-pen-alt-2"></i> 编辑</button>
                            <button class="btn btn-danger btn-xs"><i class="icofont-bin"></i> 删除</button>
                        </th>
                    </tr>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                当前记录数
            </div>
            <div class="col-md-6">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <li><a>首页</a></li>
                        <li>
                            <a href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li>
                            <a href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                        <li><a>末页</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>
