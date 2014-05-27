<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <xsl:apply-templates />
    </xsl:template>     
        
    <xsl:template match="author">
        <h1><xsl:apply-templates select="name" /></h1>
        <p><xsl:apply-templates select="biography" /></p>

        <div class="meta-block half">
            <span class="meta-header">Details</span>
            <xsl:apply-templates select="birth" />
        </div>
    </xsl:template>

    <xsl:template match="birth">
        <p><strong>Born on </strong> <xsl:value-of select="." />.</p>
    </xsl:template>
</xsl:stylesheet>