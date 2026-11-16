class Owner(val idNumber: String, fullName: String, email: String) {
    val fullName: String = requireText(fullName, "Full name")
    val email: String = requireText(email, "Email")

    init {
        require(idNumber.matches(Regex("[0-9]{12}"))) { "ID number must contain exactly 12 digits." }
        require(this.email.matches(Regex("[^\\s@]+@[^\\s@.]+(?:\\.[^\\s@.]+)+"))) { "Invalid email format." }
    }

    fun hasSameInformation(other: Owner): Boolean =
        idNumber == other.idNumber && fullName == other.fullName && email == other.email

    override fun toString(): String = "$fullName | ID: $idNumber | Email: $email"

    companion object {
        fun requireText(value: String, field: String): String {
            require(value.isNotBlank()) { "$field must not be blank." }
            return value.trim()
        }
    }
}
