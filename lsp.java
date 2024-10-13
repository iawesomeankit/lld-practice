// Base class Bird
class Bird {
    // Common behavior for all birds
    public void eat() {
        System.out.println("The bird is eating.");
    }
}

// Separate class for birds that can fly
class FlyingBird extends Bird {
    public void fly() {
        System.out.println("The bird is flying.");
    }
}

// Class for birds that cannot fly
class Penguin extends Bird {
    // Penguins do not fly, but they can still eat
    public void swim() {
        System.out.println("The penguin is swimming.");
    }
}

public class Main {
    public static void main(String[] args) {
        Bird sparrow = new FlyingBird();
        sparrow.eat();  // Works fine, birds eat
        ((FlyingBird) sparrow).fly();  // Works fine, sparrow can fly

        Bird penguin = new Penguin();
        penguin.eat();  // Works fine, penguins eat
        // No need to call fly() on Penguin, as it does not have the fly method
        ((Penguin) penguin).swim();  // Penguins can swim
    }
}
