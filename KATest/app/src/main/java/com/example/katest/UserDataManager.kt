package com.example.katest

class UserDataManager {
    var firstName: String? = null
    var lastName: String? = null
    var city: String? = null
    var postalCode: String? = null
    var street: String? = null
    var streetCode: String? = null
    var country: String? = null
    var birthDate: String? = null

    constructor(
        firstName: String?,
        lastName: String?,
        city: String?,
        postalCode: String?,
        street: String?,
        streetCode: String?,
        country: String?,
        birthDate: String?
    ) {
        this.firstName = firstName
        this.lastName = lastName
        this.city = city
        this.postalCode = postalCode
        this.street = street
        this.streetCode = streetCode
        this.country = country
        this.birthDate = birthDate
    }

    public fun displayData(): String {
        var ret: String = ""
        if(firstName != null && firstName != "") {
            ret += "first_name: $firstName\n"
        }
        if(lastName != null && lastName != "") {
            ret += "last_name: $lastName\n"
        }
        if(city != null && city != "") {
            ret += "city: $city\n"
        }
        if(postalCode != null && postalCode != "") {
            ret += "postal_code: $postalCode\n"
        }
        if(street != null && street != "") {
            ret += "street: $street\n"
        }
        if(streetCode != null && streetCode != "") {
            ret += "street_code: $streetCode\n"
        }
        if(country != null && country != "") {
            ret += "country: $country\n"
        }
        if(birthDate != null && birthDate != "") {
            ret += "birth_date: $birthDate\n"
        }

        return ret
    }
}