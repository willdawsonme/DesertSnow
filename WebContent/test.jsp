<%@ include file="WEB-INF/includes/header.jspf" %>

<c:set var="xmltext">
  <users title="View profile for Name">
    <user>
    <name>Name</name>
    <email>Email</email>
    <password>Password</password>
    <description>Description</description>
    </user>
  </users>
</c:set>
<c:import url="xslt.xsl" var="xslt"/>

<section>
    <x:transform xml="${articles}" xslt="${xslt}"/>
</section>

<%@ include file="WEB-INF/includes/footer.jspf" %>