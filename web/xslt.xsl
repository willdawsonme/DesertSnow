<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="users">
        <html>
            <head>
                <style>
                .table {  border: solid 1px black;}
                .artist { font-style: italic;}
                .coulor { background: #f2f2f2; }
                </style>
            </head>
                        
            <table>         
            <thead>
                <tr>                
                    <th>Email</th>
                    <th>Name</th>
                    <th>Password</th>
                    <th>Description</th>
                </tr>
            </thead>
            <tbody>
            
                <xsl:apply-templates />
                
            </tbody>
        </table>
        
        </html>
        </xsl:template>     
        
    <xsl:template match="users/user">
        <tr style="color:#0000EE">
        
            <xsl:apply-templates />
            
        </tr>
    </xsl:template>
    <xsl:template match="users/user/email|name|password">
        <td>
            
            <xsl:apply-templates />
            
        </td>
    </xsl:template> 
    <xsl:template match="users/user/description">
        <td class="artist">
            
          <xsl:apply-templates />
            
        </td>
    </xsl:template>
        
</xsl:stylesheet>