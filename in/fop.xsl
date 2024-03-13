<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:p="http://serheev.com"
                xsi:schemaLocation="http://serheev.com usersWithMeals.xsd
						http://www.w3.org/1999/XSL/Format https://raw.githubusercontent.com/apache/xmlgraphics-fop/main/fop/src/foschema/fop.xsd">

    <xsl:template match="/">
        <fo:root font-family="Verdana" font-weight="normal">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="FOP"
                                       page-width="210mm"
                                       page-height="297mm"
                                       margin-left="20mm"
                                       margin-right="10mm"
                                       margin-top="15mm"
                                       margin-bottom="15mm">
                    <fo:region-body region-name="xsl-region-body"/>
                    <fo:region-before region-name="xsl-region-before" extent="15mm"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="FOP" initial-page-number="1">
                <fo:static-content flow-name="xsl-region-before">
                    <fo:block font-size="20pt"
                              font-weight="normal"
                              text-align="left"
                              border-width="1pt"
                              border-style="solid"
                              border-color="black">
                        Users with meals
                    </fo:block>
                </fo:static-content>

                <fo:flow flow-name="xsl-region-body">
                    <fo:block>
                        <xsl:for-each select="p:UsersWithMeals/p:Users/p:User">
                            <fo:block margin-top="10mm">
                                <fo:block>
                                    <fo:wrapper font-weight="bold"><xsl:value-of select="@name"/>:&#160;
                                    </fo:wrapper>
                                    <xsl:choose>
                                        <xsl:when test="@enabled = 'true'">
                                            <fo:inline>
                                                <fo:wrapper color="green">enabled</fo:wrapper>
                                            </fo:inline>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:inline>
                                                <fo:wrapper color="red">disabled</fo:wrapper>
                                            </fo:inline>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </fo:block>
                                <fo:block margin-top="3mm">
                                    e-mail:&#160;<xsl:value-of select="@email"/>
                                </fo:block>
                                <fo:block>
                                    CaloriesPerDay:&#160;<xsl:value-of select="@caloriesPerDay"/>
                                </fo:block>
                                <fo:block>
                                    Registered:&#160;<xsl:value-of
                                        select="format-dateTime(@registered, '[Y0001]-[M01]-[D01] [H01]:[m01]')"/>
                                </fo:block>
                                <fo:block>
                                    Roles:&#160;
                                    <xsl:choose>
                                        <xsl:when test="@roles">
                                            <fo:inline>
                                                <xsl:value-of select="@roles"/>
                                            </fo:inline>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:inline>none</fo:inline>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </fo:block>
                            </fo:block>
                            <fo:block>
                                <fo:table border-width="1pt" border-style="solid" border-color="black" margin-top="3mm">
                                    <fo:table-body>
                                        <fo:table-row text-align="center" font-weight="bold">
                                            <fo:table-cell border-width="1pt" border-style="solid" border-color="black">
                                                <fo:block>Date</fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell border-width="1pt" border-style="solid" border-color="black">
                                                <fo:block>Description</fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell border-width="1pt" border-style="solid" border-color="black">
                                                <fo:block>Calories</fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                        <xsl:for-each select="p:Meals/p:Meal">
                                            <fo:table-row text-align="center" font-size="10pt">
                                                <fo:table-cell border-width="1pt" border-style="solid" border-color="black" padding="1mm">
                                                    <fo:block>
                                                        <xsl:value-of
                                                                select="format-dateTime(@dateTime, '[Y0001]-[M01]-[D01] [H01]:[m01]')"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-width="1pt" border-style="solid" border-color="black" padding="1mm">
                                                    <fo:block>
                                                        <xsl:value-of select="."/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-width="1pt" border-style="solid" border-color="black" padding="1mm">
                                                    <fo:block>
                                                        <xsl:value-of select="@calories"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                        </xsl:for-each>
                                    </fo:table-body>
                                </fo:table>
                            </fo:block>
                        </xsl:for-each>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>