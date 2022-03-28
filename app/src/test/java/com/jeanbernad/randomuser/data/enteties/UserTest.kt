package com.jeanbernad.randomuser.data.enteties

import com.google.gson.Gson
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UserTest {

    private lateinit var minimalUser: MinimalUser
    private lateinit var user: User

    @Before
    fun setup() {
        val userJson =
            "{\"results\":[{\"gender\":\"female\",\"name\":{\"title\":\"Mrs\",\"first\":" +
                    "\"Bella\",\"last\":\"Dean\"},\"location\":{\"street\":{\"number\":4563," +
                    "\"name\":\"E Pecan St\"},\"city\":\"El Cajon\",\"state\":\"South Carolina" +
                    "\",\"country\":\"United States\",\"postcode\":14194,\"coordinates\":{\"latitude" +
                    "\":\"87.3163\",\"longitude\":\"145.9911\"},\"timezone\":{\"offset\":\"+6:00\"," +
                    "\"description\":\"Almaty, Dhaka, Colombo\"}},\"email\":\"bella.dean@example.com\"" +
                    ",\"login\":{\"uuid\":\"137c734c-05d5-4569-9817-b18e3a9f06e1\",\"username\":\"heavypeacock157\"" +
                    ",\"password\":\"athlon\",\"salt\":\"w2wuzJLG\",\"md5\":\"7d9d6a3fe6132101f3aba51591e454a8\",\"" +
                    "sha1\":\"e11884487125853f8ccc02b51e02aa5c787c37e2\",\"sha256\":\"2ba4df517826885681e1e082e17c4877f1783571ca7355826513a9162f2e5602\"}," +
                    "\"dob\":{\"date\":\"1972-03-04T07:43:13.910Z\",\"age\":50},\"registered\":{\"date\":\"2017-12-05T17:18:46.567Z\",\"age\":5},\"phone\":\"" +
                    "(659)-048-0663\",\"cell\":\"(210)-369-3671\",\"id\":{\"name\":\"SSN\",\"value\":\"017-84-0676\"},\"picture\":{\"large\":" +
                    "\"https://randomuser.me/api/portraits/women/19.jpg\",\"medium\":\"https://randomuser.me/api/portraits/med/women/19.jpg\",\"thumbnail\":" +
                    "\"https://randomuser.me/api/portraits/thumb/women/19.jpg\"},\"nat\":\"US\"}],\"info\":{\"seed\":\"20e9eac3616de07f\",\"results\":1,\"page" +
                    "\":1,\"version\":\"1.3\"}}"
        user = Gson()
            .fromJson(userJson, User::class.java)
        minimalUser = user.minimalUser()

    }
    @Test
    fun isValidFullNameAfterCastIntoMinimalUser() {
        assertTrue(minimalUser.fullName == "Mrs Bella Dean")
    }

    @Test
    fun isValidFullAddressAfterCastIntoMinimalUser() {
        assertTrue(minimalUser.fullAddress == "E Pecan St, 4563")
    }

    @Test
    fun isValidGenderAfterCastIntoMinimalUser() {
        assertTrue(minimalUser.gender == "Female")
    }

    @Test
    fun isValidCoordinatesAfterCastIntoMinimalUser() {
        assertTrue(minimalUser.coordinates == "(87.3163, 145.9911)")
    }

@Test
    fun isValidBirthdayAfterCastIntoMinimalUser() {
        assertTrue(minimalUser.birthday == "04.03.1972")
    }


}