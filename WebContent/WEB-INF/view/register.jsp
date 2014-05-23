<section class="register">
    <h1>Register</h1>
    <form action="register" method="post">
        <div class="form-group${!empty errors['email'] ? ' error' : ''}">
            <label for="email">Email</label>
            <input type="email" name="email" value="${param['email']}" />
            <p class="reason">${errors['email']}</p>
        </div>

        <div class="form-group${!empty errors['password'] ? ' error' : ''}">
            <label for="password">Password</label>
            <input type="password" name="password" value="${param['password']}" />
            <p class="reason">${errors['password']}</p>
        </div>

        <div class="form-group${!empty errors['name'] ? ' error' : ''}">
            <label for="name">Name</label>
            <input type="text" name="name" value="${param['name']}" />
            <p class="reason">${errors['name']}</p>
        </div>

        <div class="form-group${!empty errors['biography'] ? ' error' : ''}">
            <label for="biography">Bio</label>
            <textarea name="biography">${param['biography']}</textarea>
            <p class="reason">${errors['biography']}</p>
        </div>

        <div class="form-group${!empty errors['birth'] ? ' error' : ''}">
            <label>Date of Birth</label>
            <input type="text" name="birth" placeholder="MM/DD/YYYY" value="${param['birth']}" />
            <p class="reason">${errors['birth']}</p>
        </div>

        <input type="submit" value="Register" class="btn-primary" />
    </form>
</section>