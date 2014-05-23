<section class="login">
    <h1>Login</h1>
    <form action="login" method="post">
        <c:if test="${!empty error}">
            <p class="reason">${error}</p>
        </c:if>
        <div class="form-group">
            <input type="email" name="email" placeholder="Email" />
        </div>

        <div class="form-group">
            <input type="password" name="password" placeholder="Password" />
        </div>

        <input type="submit" value="Login" class="btn-primary" />
        <span>or <a href="register">register</a> now.</span>
    </form>
</section>