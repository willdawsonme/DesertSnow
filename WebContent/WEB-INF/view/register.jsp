<section class="register">
    <h1>Register</h1>
    <form action="register" method="post">
        <div class="form-group${!empty errors['email'] ? ' error' : ''}">
            <input type="email" name="email" value="${param['email']}" placeholder="Email" />
            <p class="reason">${errors['email']}</p>
        </div>

        <div class="form-group${!empty errors['password'] ? ' error' : ''}">
            <input type="password" name="password" value="${param['password']}" placeholder="Password" />
            <p class="reason">${errors['password']}</p>
        </div>

        <div class="form-group${!empty errors['name'] ? ' error' : ''}">
            <input type="text" name="name" value="${param['name']}" placeholder="Name" />
            <p class="reason">${errors['name']}</p>
        </div>

        <div class="form-group${!empty errors['biography'] ? ' error' : ''}">
            <textarea name="biography" rows="5" placeholder="Biography">${param['biography']}</textarea>
            <p class="reason">${errors['biography']}</p>
        </div>

        <div class="form-group${!empty errors['birth'] ? ' error' : ''}">
            <input type="text" name="birth" placeholder="Date of Birth (DD/MM/YYYY)" value="${param['birth']}" />
            <p class="reason">${errors['birth']}</p>
        </div>

        <input type="submit" value="Register" class="btn-primary" />
    </form>
</section>