<section>
    <h1>${author.name}</h1>
    <span class="birth">Born on <fmt:formatDate type="date" 
            dateStyle="long"
            value="${author.birth}" /></span>
    <h2>Biography</h2>
    <p>${author.biography}</p>
</section>