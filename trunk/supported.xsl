<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
  <html>
    <head>
	  <script src="sorttable.js"></script>
	  <style type="text/css">
	    /* Sortable tables */
		table.sortable thead {
			background-color:#eee;
			color:#666666;
			font-weight: bold;
			cursor: default;
		}
		th.sorttable_nosort {
			background-color:#FFF;
			color:#000000
		}
	  </style>
	</head>
  <body>
  <h2>Supported Institutions</h2>
    <table border="1" class="sortable">
      <tr>
        <th>Name</th>
        <th>State</th>
        <th class="sorttable_nosort">Catalog URL</th>
		<th>System</th>
      </tr>
      <xsl:for-each select="xml/library">
		  <tr>
			<td>
			  <xsl:choose>
			  <xsl:when test="link">
				  <a><xsl:attribute name="href">
						<xsl:value-of select="link"/>
					 </xsl:attribute>
					 <xsl:value-of select="name"/>
				  </a>
			  </xsl:when>
			  <xsl:otherwise>
			    <xsl:value-of select="name"/>
			  </xsl:otherwise>
			  </xsl:choose>
			</td>
			<td><xsl:value-of select="state"/></td>
			<td>
			  <a><xsl:attribute name="href">
					<xsl:value-of select="arg[@key='url']"/>
				 </xsl:attribute>
				 <xsl:value-of select="arg[@key='url']"/>
			  </a>
			</td>
			<td>
			  <a><xsl:attribute name="href">
					<xsl:value-of select="concat('http://code.google.com/p/booktracker/wiki/',substring-after(class,'com.softwaresmithy.library.impl.'))"/>
				 </xsl:attribute>
				 <xsl:value-of select="substring-after(class,'com.softwaresmithy.library.impl.')"/>
			  </a>
			</td>
		  </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>

