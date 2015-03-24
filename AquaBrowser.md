# AquaBrowser #

[AquaBrowser](http://www.serialssolutions.com/aquabrowser) by Serial Solutions


# Requisites #
The catalog url should be of the form:
```
http://your/library/address/rss.ashx
```

# Implementation #

The implementation uses the RSS module to generate an xml response to an ISBN query.  Unfortunately, at this time if the optional RSS module is unavailable, we don't support that institution.