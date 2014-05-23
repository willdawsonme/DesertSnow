<section class="register">
    <h1>New Article</h1>
    <form action="${pageContext.servletContext.contextPath}/article/new" method="post">
        <div class="form-group${!empty errors['title'] ? ' error' : ''}">
            <label for="title">Title</label>
            <input type="text" name="title" value="${param['title']}" />
            <p class="reason">${errors['title']}</p>
        </div>
        <div class="form-group${!empty errors['content'] ? ' error' : ''}">
            <label for="content">Content</label>
            <textarea name="content">${param['content']}</textarea>
            <p class="reason">${errors['content']}</p>
        </div>
        <div class="form-group${!empty errors['category'] ? ' error' : ''}">
            <label for="category">Category</label>
            <input type="text" name="category" value="${param['category']}" />
            <p class="reason">${errors['category']}</p>
        </div>

        <input type="submit" value="Post" class="btn-primary" />
    </form>
</section>