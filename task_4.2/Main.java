/*
Создать class Dog. У собаки должна быть кличка String name и возраст int age.
Создайте геттеры и сеттеры для всех переменных класса Dog.
Требования:
•	Программа не должна считывать данные с клавиатуры.
•	У класса Dog должна быть переменная name с типом String.
•	У класса Dog должна быть переменная age с типом int.
•	У класса должен быть сеттер для переменной name.
•	У класса должен быть геттер для переменной name.
•	У класса должен быть сеттер для переменной age.
•	У класса должен быть геттер для переменной age.
*/


public class Main {
    public static void main(String[] args) {
    Dog dog1 = new Dog();
    Dog dog2 = new Dog();
    dog1.setName("Chappi");
    dog1.setAge(1);
    dog2.setName("Beethoven");
    dog2.setAge(2);
    System.out.println(dog1.getName() + ", " + dog1.getAge());
    System.out.println(dog2.getName() + ", " + dog2.getAge());
    }
}
