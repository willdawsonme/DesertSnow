<section>
    <c:if test="${empty articles}">
        <p>Sorry! We couldn't find any articles to display.</p>
    </c:if>

    <c:import url="/WEB-INF/xslt/articles.xsl" var="xslt"/>
    <x:transform xml="${articles}" xslt="${xslt}"/>
    
</section>