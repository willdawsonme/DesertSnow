<section class="register">
    <h1>Register</h1>
    <c:if test="${!empty errors}">
        <p>${errors}</p>
    </c:if>
    <form action="register" method="post">
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" name="email" value="${email}" />
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name="password" />
        </div>

        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" name="name" />
        </div>

        <div class="form-group">
            <label for="biography">Bio</label>
            <textarea name="biography"></textarea>
        </div>

        <div class="form-group">
            <label>Date of Birth</label>
            <input type="text" name="birth" placeholder="MM/DD/YYYY" />
        </div>

        <input type="submit" value="Register" class="btn-primary" />
    </form>
</section>