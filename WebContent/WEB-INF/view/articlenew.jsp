<section class="register">
    <h1>New Article</h1>
    <form action="${pageContext.servletContext.contextPath}/article/new" method="post">
        <div class="form-group${!empty errors['title'] ? ' error' : ''}">
            <input type="text" name="title" value="${param['title']}" placeholder="Title" />
            <p class="reason">${errors['title']}</p>
        </div>
        <div class="form-group${!empty errors['content'] ? ' error' : ''}">
            <textarea name="content" rows="10" placeholder="Content">${param['content']}</textarea>
            <p class="reason">${errors['content']}</p>
        </div>
        <div class="form-group${!empty errors['category'] ? ' error' : ''}">
            <input type="text" name="category" value="${param['category']}" placeholder="Category" />
            <p class="reason">${errors['category']}</p>
        </div>
        <div class="form-group">
            <label for="visibility">Visibility</label>
            <input type="radio" name="visibility" value="public" checked>Public<br>
            <input type="radio" name="visibility" value="authors">Authors
        </div>

        <input type="submit" value="Post" class="btn-primary" />
    </form>
</section>