<section>
    <c:if test="${empty articles}">
        <p>Sorry! We couldn't find any articles to display.</p>
    </c:if>
    <c:forEach var="article" items="${articles}">
        <div class="article-excerpt">
            <h2><a href="${pageContext.servletContext.contextPath}/article?id=${article.id}">${article.title}</a></h2>
            <p class="excerpt">${article.preview} <a href="${pageContext.servletContext.contextPath}/article?id=${article.id}">Read More &rarr;</a></p>
            <span class="meta"><i class="icon-thin-male"></i><a href="${pageContext.servletContext.contextPath}/author?id=${article.author.id}">${article.author.name}</a> in <strong>${article.category}</strong> on <strong><fmt:formatDate type="date" dateStyle="long"value="${article.publishedDate}" /></strong></span>
        </div>
    </c:forEach>
</section>