<section>
    <h1>${user.name}</h1>
    
    <div class="meta-block">
        <span class="meta-header">Manage Articles</span>
        <c:if test="${empty articles}">
            <p>You haven't added any articles yet.</p>
            <a class="btn-primary" href="${pageContext.servletContext.contextPath}/article/new">Write</a>
        </c:if>
        <c:forEach var="article" items="${articles}">
            <h4>
                <a href="${pageContext.servletContext.contextPath}/article?id=${article.id}">${article.title}</a>
                <a class="btn" href="${pageContext.servletContext.contextPath}/article/delete?id=${article.id}">Delete</a>
            </h4>
        </c:forEach>
    </div>
    <div class="meta-block">
        <span class="meta-header">Details</span>
        <p><strong>Born on</strong> <fmt:formatDate type="date" 
            dateStyle="long"
            value="${user.birth}" />.<br>
        ${user.biography}</p>
    </div>
</section>