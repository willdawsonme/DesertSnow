<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <xsl:apply-templates />
    </xsl:template>     
        
    <xsl:template match="article">
        <article>
            <h1><a href="/desertsnow/article?id={@id}"><xsl:apply-templates select="title"/></a></h1>
            <xsl:apply-templates select="preview"/>
            <xsl:apply-templates select="content"/>

            <span class="meta">
                Published in <xsl:apply-templates select="category"/> on <xsl:apply-templates select="publishedDate"/>
            </span>

            <xsl:apply-templates select="author"/>
        </article>
    </xsl:template>

    <xsl:template match="title">
        <xsl:value-of select="."/>
    </xsl:template> 

    <xsl:template match="preview">
        <p class="excerpt"><xsl:value-of select="."/></p>
    </xsl:template>

    <xsl:template match="content">
        <p><xsl:value-of select="."/></p>
    </xsl:template>

    <xsl:template match="category|publishedDate">
        <strong><xsl:value-of select="."/></strong>
    </xsl:template>

    <xsl:template match="author">
        <div class="meta-block">
            <span class="meta-header">Written By</span>
            <h3><xsl:value-of select="name" /></h3>
            <p><xsl:value-of select="biography" /></p>
            <a class="btn-primary btn-small" href="/desertsnow/author?id={@id}">See Profile</a>
        </div>
    </xsl:template>
</xsl:stylesheet>