<section>
    <c:choose>
        <c:when test="${!empty error}">
            <div class="notification error">
                <p>${error}</p>
            </div>
        </c:when>
        <c:otherwise>
            <h1>${author.name}</h1>
            <p>${author.biography}</p>
            <div class="meta-block half">
                <span class="meta-header">Details</span>
                <p><strong>Born on</strong> <fmt:formatDate type="date" 
                    dateStyle="long"
                    value="${author.birth}" />.</p>
            </div>
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