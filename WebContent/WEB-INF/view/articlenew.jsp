<section>
    <h1>New Article</h1>
    <form action="${pageContext.servletContext.contextPath}/article/new" method="post">
        <div class="form-group${!empty errors['title'] ? ' error' : ''}">
            <input type="text" name="title" value="${param['title']}" placeholder="Title" />
            <p class="reason">${errors['title']}</p>
        </div>
        <div class="form-group${!empty errors['preview'] ? ' error' : ''}">
            <textarea name="preview" rows="2" placeholder="Preview">${param['preview']}</textarea>
            <p class="reason">${errors['preview']}</p>
        </div>
        <div class="form-group${!empty errors['content'] ? ' error' : ''}">
            <textarea name="content" rows="8" placeholder="Content">${!empty lipsum ? lipsum : param['content']}</textarea>
            <input type="submit" name="lipsum" class="btn-primary full small" style="font-size: 0.85em; margin-top: 10px;" value="Generate Lipsum" />
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

        <input type="submit" name="default" value="Post" class="btn-primary" />
    </form>
</section>