<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <xsl:apply-templates />
    </xsl:template>     
        
    <xsl:template match="article">
        <div class="article-excerpt">
            <xsl:apply-templates select="title|preview" />

            <span class="meta">
                <xsl:apply-templates select="author"/> in <strong><xsl:value-of select="category"/></strong> on <strong><xsl:value-of select="publishedDate"/></strong>
            </span>
        </div>
    </xsl:template>

    <xsl:template match="title">
        <h2><a><xsl:value-of select="."/></a></h2>
    </xsl:template> 

    <xsl:template match="preview">
        <p class="excerpt"><xsl:value-of select="."/></p>
    </xsl:template>

    <xsl:template match="author">
        <i class="icon-thin-male">&#8203;</i><a href="/desertsnow/author?id=@id"><xsl:value-of select="name"/></a>
    </xsl:template>
</xsl:stylesheet>