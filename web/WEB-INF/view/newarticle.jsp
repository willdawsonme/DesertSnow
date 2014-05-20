<section class="register">
    <h1>New Article</h1>
    <c:if test="${!empty errors}">
        <p>${errors}</p>
    </c:if>
    <form action="newarticle" method="post">
        <div class="form-group">
            <label for="content">Content</label>
            <textarea name="content"></textarea>
        </div>

        <input type="submit" value="Post" class="btn-primary" />
    </form>
</section>