<section class="login">
    <h1>Login</h1>
    <form action="login" method="post">
        <c:if test="${!empty error}">
            <div class="notification error">
                <p>${error}</p>
            </div>
        </c:if>
        <div class="form-group">
            <input type="email" name="email" placeholder="Email" value="${param['email']}"/>
        </div>

        <div class="form-group">
            <input type="password" name="password" placeholder="Password" />
        </div>

        <input type="submit" value="Login" class="btn-primary" />
        <span>or <a href="register">register</a> now.</span>
    </form>
</section>