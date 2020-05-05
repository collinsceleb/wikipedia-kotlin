package com.bdn.collinsceleb.myapplication.models

object Urls {
    val BaseUrl = "https://wikipedia.org/w/api.php"

    fun getSearchUrl(term: String, skip: Int, take: Int) : String {
        return BaseUrl + "?action=query" +
                "&formatversion=2" +
                "&generator=prefixsearch" +
                "&gpssearch=$term" +
                "&gpslimit=$take" +
                "&gpsoffset=$skip" +
                "&prop=pageimages|info" +
                "piprop=thumbnail|url" +
                "&pithumbsize=200" +
                "&pilimit=$take" +
                "&wbptterms=description" +
                "&format=json" +
                "&inprop=url"
    }
    fun getRandomUrl(take: Int) : String {
        return BaseUrl + "?action=query" +
                "&formatversion=2" +
                "&generator=random" +
                "&grnlimit=$take" +
                "&prop=pageimages|info" +
                "&pithumbsize=200" +
                "&format=json" +
                "&inprop=url" +
                "grnnamespace=0"
    }
}