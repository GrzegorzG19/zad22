package pl.golan.zadanie22;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class ProductRepository {
    private Set<Product> products;
    private double sum = 0;

    public ProductRepository() {
        products = new HashSet<>();
        products.add(new Product("Ziemniak", 13.42));
        products.add(new Product("Pomidor", 23.32));
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Product findByName(String name) {
        for (Product Product : products) {
            if (Product.getName().equals(name)) {
                return Product;
            }
        }

        return null;
    }

    public Set<Product> findAll() {
        return products;
    }

    public double sum() {
        double suma = 0;
        for (Product product : products) {
            suma += product.getPrice();
        }
        return suma;
    }

    public void add(Product user) {
        products.add(user);
    }
}
