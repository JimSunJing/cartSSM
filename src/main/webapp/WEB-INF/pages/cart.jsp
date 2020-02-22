<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>购物车</title>
</head>
<body>

<h2 align="center">购物车</h2>
<div align="center">当前用户: ${user.name}</div>
<table align="center" border="1" cellspacing="0">
    <tr>
        <th>名称</th>
        <th>单价</th>
        <th>数量</th>
        <th>小计</th>
        <th>删除项目</th>
    </tr>

    <c:forEach items="${OrderItems}" var="Item" >
        <tr>
            <td>${Item.product.name}</td>
            <td>${Item.product.price}</td>
            <td>${Item.num}</td>
            <td>${Item.num * Item.product.price}</td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/product/removeOrderItem">
                    <input type="hidden" value="${Item.product.name}" name="name">
                    <input type="submit" value="删除">
                </form>
            </td>
        </tr>
    </c:forEach>
    
    <c:if test="${!empty OrderItems}">
        <tr>
            <td colspan="5" align="right">
                <a href="${pageContext.request.contextPath}/user/createOrder">生成订单</a>
            </td>
        </tr>
    </c:if>
</table>

<a href="${pageContext.request.contextPath}/product/findAll">前往购物</a>

</body>
</html>
