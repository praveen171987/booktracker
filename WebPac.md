# WebPac #

[WebPac Pro](http://www.iii.com/products/webpac_pro.shtml) by Innovative Interfaces


# Requisites #

The catalog url should be of the form
```
http://libsys.arlingtonva.us/search/?searchtype=i&searcharg=%s&searchscope=1
```
where `searchtype=i` indicates an ISBN search, `searcharg=%s` is where the ISBN number will go and `searchscope` indicates the position of the target branch in the user interface's dropdown list.  In general `searchscope` is the only element that should vary, and it should probably be set to the 'main' branch unless the user wants a very narrow search.