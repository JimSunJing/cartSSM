<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List</title>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
    <script>
        $(function () {
            $(".AddForm").submit(function (event) {
                event.preventDefault();
                // 将按钮变灰，无法再次添加购物车
                $(this).children(".submit").attr("disabled",true);
                var form = $(this);
                // ajax 添加进入购物车
                $.ajax({
                    url:form.attr("action"),
                    type:form.attr("method"),
                    data:form.serialize(),
                    dataType:"json",
                    success:function (data) {
                        // alert(data.msg);
                    }
                });
            });


        })
    </script>
</head>
<body>

<h2 align="center">产品列表</h2>
<div align="center">当前用户: ${user.name}</div>
<table align="center" border="1" cellspacing="0">
    <tr>
        <th>id</th>
        <th>名称</th>
        <th>价格</th>
        <th>购买</th>
    </tr>

    <c:forEach items="${productList}" var="product" varStatus="i" >
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>
                <%--<form action="${pageContext.request.contextPath}/product/addOrderItem" method="post">
                    数量<input type="text" value="1" name="num">
                    <input type="hidden" name="pid" value="${product.id}">
                    <input type="submit" value="加入购物车">
                </form>--%>

                <%--使用jQuery完成表单ajax提交--%>
                <form method="post" class="AddForm" action="${pageContext.request.contextPath}/product/addOrderItem" >
                    数量<input type="text" value="1" name="num">
                    <input type="hidden" name="pid" value="${product.id}">
                    <input type="submit" value="加入购物车" class="submit" >
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="${pageContext.request.contextPath}/user/userCart">前往购物车</a>

</body>
</html>
