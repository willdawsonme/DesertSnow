<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <xsl:apply-templates />
    </xsl:template>     
        
    <xsl:template match="article">
        <div class="article-excerpt">
            <h2><a href="article?id={@id}"><xsl:apply-templates select="title"/></a></h2>
            <p class="excerpt"><xsl:apply-templates select="preview"/><a href="article?id={@id}" style="margin-left: 5px;">Read More &#8594;</a></p>

            <span class="meta">
                <xsl:apply-templates select="author"/> in <xsl:apply-templates select="category"/> on <xsl:apply-templates select="publishedDate"/>
            </span>
        </div>
    </xsl:template>

    <xsl:template match="title">
        <xsl:value-of select="."/>
    </xsl:template> 

    <xsl:template match="preview">
        <xsl:value-of select="."/>
    </xsl:template>

    <xsl:template match="author">
        <i class="icon-thin-male">&#8203;</i><a href="author?id={@id}"><xsl:value-of select="name"/></a>
    </xsl:template>

    <xsl:template match="category|publishedDate">
        <strong><xsl:value-of select="."/></strong>
    </xsl:template>
</xsl:stylesheet>