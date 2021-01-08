package com.fileskripsi.skripsi


data class User(val displayName: String="", val status: String=""){
    constructor():this("",""){}
}