<section>
    <c:choose>
        <c:when test="${empty articles}">
            <p>Sorry! We couldn't find any articles to display.</p>
        </c:when>
        <c:otherwise>
            <c:import url="/WEB-INF/xslt/articles.xsl" var="xslt"/>
            <x:transform xml="${articles}" xslt="${xslt}"/>
        </c:otherwise>
    </c:choose>
</section>