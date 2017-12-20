<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.bxslider_e88acd1b.js"></script>
<div class="menu">
    <!--Begin 商品分类详情 Begin-->
    <div class="nav">
        <div class="nav_t">全部商品分类</div>
        <div class="leftNav">
            <ul>
                <c:forEach items="${pclist}" var="pc1">
                    <li>
                        <div class="fj">
                                <span class="n_img"><span>

                                </span><img src="${ctx}/images/nav1.png" /></span>
                            <span class="fl">${pc1.productCategory.name}</span>
                        </div>
                        <div class="zj">
                            <div class="zj_l">
                                <c:forEach items="${pc1.productCategoryVosList}" var="pc2">
                                    <div class="zj_l_c">
                                        <h2><a href="${ctx}/Product?action=queryProductList&categoryId=${pc2.productCategory.id}">${pc2.productCategory.name}</a></h2>
                                        <c:forEach items="${pc2.productCategoryVosList}" var="pc3">
                                            <a href="${ctx}/Product?action=queryProductList&categoryId=${pc3.productCategory.id}">${pc3.productCategory.name}</a>|
                                        </c:forEach>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="zj_r">
                                <a href="#"><img src="${ctx}/images/n_img1.jpg" width="236" height="200" /></a>
                                <a href="#"><img src="${ctx}/images/n_img2.jpg" width="236" height="200" /></a>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <!--End 商品分类详情 End-->
    <ul class="menu_r">
        <li><a href="Index.html">首页</a></li>
        <c:forEach items="${pclist}" var="pcList">
            <li><a href="${ctx}/Product?action=queryProductList&categoryId=${pcList.productCategory.id}" >${pcList.productCategory.name}</a></li>
        </c:forEach>
    </ul>
    <div class="m_ad">中秋送好礼！</div>
</div>