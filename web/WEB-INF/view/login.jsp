<section class="login">
    <h1>Login</h1>
    <c:if test="${!empty errors}">
        <p>${errors}</p>
    </c:if>
    <form action="login" method="post">
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" name="email" />
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name="password" />
        </div>

        <input type="submit" value="Login" class="btn-primary" />
        <p class="note">or <a href="register">register</a> now.</p>
    </form>
</section>