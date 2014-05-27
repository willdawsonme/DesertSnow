<section>
    <c:choose>
        <c:when test="${!empty error}">
            <div class="notification error">
                <p>${error}</p>
            </div>
        </c:when>
        <c:otherwise>
            <c:import url="/WEB-INF/xslt/author.xsl" var="xslt"/>
            <x:transform xml="${author}" xslt="${xslt}"/>
            <div class="meta-block half">
                <span class="meta-header">Articles</span>
                <c:if test="${empty articles}">
                    <p>No articles were found.</p>
                </c:if>
                <c:forEach var="article" items="${articles}">
                    <h4><a href="${pageContext.servletContext.contextPath}/article?id=${article.id}">${article.title}</a></h4>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</section>