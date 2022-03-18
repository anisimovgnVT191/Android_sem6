package com.example.android.catsapp.domainlayer

sealed class CatsAppExceptions: Exception()

class NoInternetConnectionException: CatsAppExceptions()

class SearchReturnedZeroItemsException: CatsAppExceptions()