<section>
    <c:choose>
        <c:when test="${!empty error}">
            <div class="notification error">
                <p>${error}</p>
            </div>
        </c:when>
        <c:otherwise>
            <c:import url="/WEB-INF/xslt/article.xsl" var="xslt"/>
            <x:transform xml="${article}" xslt="${xslt}"/>
        </c:otherwise>
    </c:choose>
</section>