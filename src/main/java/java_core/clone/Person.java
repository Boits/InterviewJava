package java_core.clone;

class Person {
    private String name;
    private int age;
    private Address address; // Изменяемый объект

    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = new Address(address); // Глубокая копия
    }

    // Конструктор копирования (Copy constructor)
    public Person(Person original) {
        this.name = original.name;  // String immutable, можно копировать ссылку
        this.age = original.age;
        this.address = new Address(original.address); // Клонируем Address
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress(){
        return address;
    }

    @Override
    public String toString() {
        return name + " (" + age + ") from " + address;
    }
}

