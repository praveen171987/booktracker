# Horizon Information Portal #

[Horizon](http://www.sirsidynix.com/products/horizon) by SirsiDynix


# Requisites #

The catalog url should be of the form:
```
http://your/library/address/ipac20/ipac.jsp
```

# Implementation #

The implementation uses the `GetXML=true` argument (in both GET and POST) operations. The response XML does not seem to be of any particular standard, its contents are very similar to the HTML generated in a normal operation, but because the XML is well formed it is easily traversed using XPath.