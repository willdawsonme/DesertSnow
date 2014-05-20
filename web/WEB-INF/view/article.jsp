<section>
    <c:choose>
        <c:when test="${empty article}">
            <p>Sorry, there is no article available with that ID.</p>
        </c:when>
        <c:otherwise>
            <article>
                <h1>${article.title}</h1>
                <span class="meta">
                    <span class="author">By <a href="author?id=${article.author.id}">${article.author.name}</a></span> - 
                    <span class="date"><fmt:formatDate type="date" 
                                                dateStyle="long"
                                                    value="${article.publishedDate}" /></span>
                </span>
                <p>${fn:replace(article.content,'\\n','&lt;/p&gt;&lt;p&gt;')}</p>
                <span class="meta category">Published in ${article.category}.</span>
            </article>
        </c:otherwise>
    </c:choose>
</section>