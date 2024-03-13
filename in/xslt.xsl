<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:p="http://serheev.com"
                xsi:schemaLocation="http://serheev.com usersWithMeals.xsd">

    <xsl:output method="html" omit-xml-declaration="yes" indent="yes"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Users with meals</title>
            </head>
            <body>
                <h1>Users with meals</h1>
                <hr/>
                <xsl:for-each select="/p:UsersWithMeals/p:Users/p:User">
                    <h3><xsl:value-of select="@name"/>:
                        <xsl:choose>
                            <xsl:when test="@enabled = 'true'">
                                <span style="color:#008000">enabled</span>
                            </xsl:when>
                            <xsl:otherwise>
                                <span style="color:#FF0000">disabled</span>
                            </xsl:otherwise>
                        </xsl:choose>
                    </h3>
                    <div style="font-size:large">
                        Email: <xsl:value-of select="@email"/><br/>
                        CaloriesPerDay: <xsl:value-of select="@caloriesPerDay"/><br/>
                        Registered: <xsl:value-of select="format-dateTime(@registered, '[Y0001]-[M01]-[D01] [H01]:[m01]')"/><br/>
                        Roles: <xsl:value-of select="@roles"/><br/>
                    </div>
                    <table border="1" cellpadding="8" cellspacing="0">
                        <thead>
                            <tr>
                                <th>Date</th>
                                <th>Description</th>
                                <th>Calories</th>
                            </tr>
                        </thead>
                        <xsl:variable name="currentEmail" select="@email"/>
                        <xsl:variable name="meals" select="/p:UsersWithMeals/p:Users/p:User[@email = $currentEmail]/p:Meals/p:Meal"/>
                        <xsl:for-each select="p:filter($meals, @caloriesPerDay)">
                            <xsl:variable name="colour">
                                <xsl:choose>
                                    <xsl:when test="p:getExcess(@dateTime)">color:#FF0000</xsl:when>
                                    <xsl:otherwise>color:#008000</xsl:otherwise>
                                </xsl:choose>
                            </xsl:variable>
                            <tr style="{$colour}">
                                <td><xsl:value-of select="format-dateTime(@dateTime, '[Y0001]-[M01]-[D01] [H01]:[m01]')"/></td>
                                <td><xsl:value-of select="."/></td>
                                <td><xsl:value-of select="@calories"/></td>
                            </tr>
                        </xsl:for-each>
                    </table>
                    <br/>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>