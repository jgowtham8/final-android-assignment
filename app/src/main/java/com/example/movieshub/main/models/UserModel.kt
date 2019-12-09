package com.example.movieshub.main.models

import com.chibatching.kotpref.KotprefModel

object UserModel : KotprefModel() {
    var name: String by stringPref()
    var email: String by stringPref()
    var dateOfBirth: String by stringPref()
    var photoUrl: String by stringPref()
}