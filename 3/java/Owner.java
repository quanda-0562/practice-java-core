public class Owner {
    private final String idNumber;
    private final String fullName;
    private final String email;

    public Owner(String idNumber, String fullName, String email) {
        if (idNumber == null || !idNumber.matches("[0-9]{12}")) {
            throw new IllegalArgumentException("ID number must contain exactly 12 digits.");
        }
        this.idNumber = idNumber;
        this.fullName = requireText(fullName, "Full name");
        this.email = requireText(email, "Email");
        if (!this.email.matches("[^\\s@]+@[^\\s@.]+(?:\\.[^\\s@.]+)+")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }

    public static String requireText(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(field + " must not be blank.");
        }
        return value.trim();
    }

    public String getIdNumber() { return idNumber; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }

    public boolean hasSameInformation(Owner other) {
        return idNumber.equals(other.idNumber) && fullName.equals(other.fullName) && email.equals(other.email);
    }

    @Override public String toString() {
        return fullName + " | ID: " + idNumber + " | Email: " + email;
    }
}
