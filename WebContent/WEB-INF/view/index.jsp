<section>
    <c:if test="${empty articles}">
        <p>Sorry! We couldn't find any articles to display.</p>
    </c:if>
    <c:forEach var="article" items="${articles}">
        <article>
            <h1><a href="${pageContext.servletContext.contextPath}/article?id=${article.id}">${article.title}</a></h1>
            <span class="meta">
                <span class="author">By <a href="${pageContext.servletContext.contextPath}/author?id=${article.author.id}">${article.author.name}</a></span> - 
                <span class="date"><fmt:formatDate type="date" 
                                            dateStyle="long"
                                                value="${article.publishedDate}" /></span>
            </span>
            <p>${fn:replace(article.content,'\\n','&lt;/p&gt;&lt;p&gt;')}</p>
            <p><a href="${pageContext.servletContext.contextPath}/article?id=${article.id}">Read More</a></p>
            <span class="meta category">Published in ${article.category}.</span>
        </article>
    </c:forEach>
</section>