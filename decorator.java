import java.util.*;

public class Main {
    public interface Coffee {
        String getDescription();
        double getCost();
    }

    // Make the class static so it can be instantiated from a static context
    public static class SimpleCoffee implements Coffee {
        public String getDescription() {
            return "A simple coffee";
        }

        public double getCost() {
            return 5.0;
        }
    }

    public static abstract class CoffeeDecorator implements Coffee {
        protected Coffee decoratedCoffee;

        public CoffeeDecorator(Coffee coffee) {
            this.decoratedCoffee = coffee;
        }

        public String getDescription() {
            return decoratedCoffee.getDescription();
        }

        public double getCost() {
            return decoratedCoffee.getCost();
        }
    }

    public static class MilkDecorator extends CoffeeDecorator {
        public MilkDecorator(Coffee coffee) {
            super(coffee);
        }

        public String getDescription() {
            return decoratedCoffee.getDescription() + ", Milk";
        }

        public double getCost() {
            return decoratedCoffee.getCost() + 0.5;
        }
    }

    public static class SugarDecorator extends CoffeeDecorator {
        public SugarDecorator(Coffee coffee) {
            super(coffee);
        }

        public String getDescription() {
            return decoratedCoffee.getDescription() + ", Sugar";
        }

        public double getCost() {
            return decoratedCoffee.getCost() + 1;
        }
    }

    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();  // No more errors
        System.out.println(coffee.getDescription() + " " + coffee.getCost());

        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " " + coffee.getCost());

        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " " + coffee.getCost());
    }
}
