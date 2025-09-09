package java_core.clone;

class Address {
    private String city;

    public Address(String city) {
        this.city = city;
    }

    public Address(Address other) { // Конструктор копирования (Copy constructor)
        this.city = other.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return city;
    }
}
