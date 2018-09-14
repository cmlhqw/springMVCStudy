<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>springmvc lists</title>
</head>

<style>
    td{
        padding: 2px;
    }
</style>
<body>


<table border="1">
    <tr><td colspan="5">    <h1 style="color: #00c5ff">spring springJDBC springMVC ajax实现对数据的增删改查</h1>
    </td></tr>
    <tr>
        <td width="50">Id</td>
        <td width="150">UserName</td>
        <td width="150">Password</td>
        <td width="150">Edit</td>
        <td width="150">Delete</td>
    </tr>
    <c:forEach items="${pageBean.list}" var="admin">
        <tr>
            <td>${admin.id}</td>
            <td>${admin.username}</td>
            <td>${admin.password}</td>
            <td><a style="background: rgba(0,197,255,0);color: #00c5ff;border: 1px dashed #00c5ff;"  href="edit?id=${admin.id}">Edit</a></td>
            <td><button style="background: rgba(0,197,255,0);color: #ff5329" onclick="deleteById(${admin.id})">Delete</button></td>
        </tr>
    </c:forEach>

    <tr>
        <td colspan="5">共&nbsp;${pageBean.getTotalPages()}&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;
            当前第&nbsp;${pageBean.getPageNo()}页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
                    href="list?pageNo=${pageBean.getTopPageNo()}">首页</a>
            <a
                    href="list?pageNo=${pageBean.getPreviousPageNo()}">上一页</a>
            <a
                    href="list?pageNo=${pageBean.getNextPageNo()}">下一页</a>
            <a
                    href="list?pageNo=${pageBean.bottomPageNo}">尾页</a>

        </td>
    </tr>
</table>

<h3><a href="add">添加</a></h3>
<script type="text/javascript" src="/js/jquery-2.1.0.js"></script>
<script>
    function deleteById(id) {
        $.ajax({
            type: "post",
            url: "del",
            data: {
                "id":id
            },
            success: function (data) {
                alert(data.toString());

                location.reload();
            }
        });
    }
</script>

<c:if test="${msg != null}">
    <script>
        alert("${msg}")
    </script>
</c:if>

</body>
</html>