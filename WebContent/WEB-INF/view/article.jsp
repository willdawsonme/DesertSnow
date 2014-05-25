<section>
    <c:choose>
        <c:when test="${!empty error}">
            <div class="notification error">
                <p>${error}</p>
            </div>
        </c:when>
        <c:otherwise>
            <article>
                <h1><a href="${pageContext.servletContext.contextPath}/article?id=${article.id}">${article.title}</a></h1>
                <p class="excerpt">${article.preview}</p>
                <p>${article.content}</p>
                <span class="meta">Published in <strong>${article.category}</strong> on <strong><fmt:formatDate type="date" dateStyle="long"value="${article.publishedDate}" /></strong></span>
                <div class="meta-block">
                    <span class="meta-header">Written By</span>
                    <h3>${article.author.name}</h3>
                    <p>${article.author.biography}</p>
                    <a class="btn-primary btn-small" href="${pageContext.servletContext.contextPath}/author?id=${article.author.id}">See Profile</a>
                </div>
            </article>
        </c:otherwise>
    </c:choose>
</section>