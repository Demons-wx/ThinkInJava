package online.wangxuan.generics.complex;

import online.wangxuan.generics.generator.Generator;
import online.wangxuan.generics.method.Generators;

import java.util.ArrayList;
import java.util.Random;

/**
 * 下面的示例，展示了使用泛型来构建复杂模型是多么简单。即使每个类都是作为
 * 一个构建块创建的，但是其整个还是包含许多部分，
 *
 * 本例中，构建的模型是一个零售店，它包含走廊、货架和商店：
 * Created by wangxuan on 2017/8/13.
 */
public class Store extends ArrayList<Aisle> {
    private ArrayList<CheckoutStand> checkouts = new ArrayList<>();
    private Office office = new Office();
    public Store(int nAisles, int nShelves, int nProducts) {
        for (int i = 0; i < nAisles; i++) {
            add(new Aisle(nShelves, nProducts));
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Aisle a : this)
            for (Shelf s : a)
                for (Product p : s) {
                    result.append(p);
                    result.append("\n");
                }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Store(14, 5, 10));
    }
}

class Product {
    private final int id;
    private String description;
    private double price;

    public Product(int id, String description, double price) {
        this.id = id;
        this.description = description;
        this.price = price;
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return id + ": " + description + ". price: $" + price;
    }

    public void priceChange(double change) {
        price += change;
    }

    public static Generator<Product> generator = new Generator<Product>() {
        private Random rand = new Random(47);
        public Product next() {
            return new Product(rand.nextInt(1000), "Test",
                    Math.round(rand.nextDouble() * 1000.0) + 0.99);
        }
    };

}

class Shelf extends ArrayList<Product> {
    public Shelf(int nProducts) {
        Generators.fill(this, Product.generator, nProducts);
    }
}

class Aisle extends ArrayList<Shelf> {
    public Aisle(int nShelves, int nProducts) {
        add(new Shelf(nProducts));
    }
}

class CheckoutStand {}
class Office {}

