<section>
    <h1>${author.name}</h1>
    <p>${author.biography}</p>
    <div class="meta-block half">
        <span class="meta-header">Details</span>
        <h3>Birth</h3>
        <p><fmt:formatDate type="date" 
            dateStyle="long"
            value="${author.birth}" /></p>
    </div>
    <div class="meta-block half">
        <span class="meta-header">Other Articles</span>
        <c:if test="${empty articles}">
            <p>No other articles were found.</p>
        </c:if>
        <c:forEach var="article" items="${articles}">
            <h4><a href="${pageContext.servletContext.contextPath}/article?id=${article.id}">${article.title}</a></h4>
        </c:forEach>
    </div>
</section>