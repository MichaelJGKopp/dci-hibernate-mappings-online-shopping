package io.michaeljgkopp.github.springconsoledemo.controller;

import io.michaeljgkopp.github.springconsoledemo.domain.entity.Customer;
import io.michaeljgkopp.github.springconsoledemo.domain.entity.Order;
import io.michaeljgkopp.github.springconsoledemo.domain.entity.Product;
import io.michaeljgkopp.github.springconsoledemo.presentation.Menu;
import io.michaeljgkopp.github.springconsoledemo.presentation.MenuItem;
import io.michaeljgkopp.github.springconsoledemo.service.CustomerService;
import io.michaeljgkopp.github.springconsoledemo.service.OrderService;
import io.michaeljgkopp.github.springconsoledemo.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Configuration
public class ConsoleController {

    private ProductService productService;
    private CustomerService customerService;
    private OrderService orderService;
    private Scanner scanner;

    @Bean
    public CommandLineRunner commandLineRunner(
            ProductService productService,
            CustomerService customerService,
            OrderService orderService,
            Scanner scanner) {

        this.productService = productService;
        this.customerService = customerService;
        this.orderService = orderService;
        this.scanner = scanner;

        return args -> {

            // Populate some dummy data
//            populateDummyData();

            // Main Menu
            Menu menu = new Menu("Online Shopping System");

            menu.addItem(new MenuItem("List Products", this::listProducts));
            menu.addItem(new MenuItem("Add Product", this::addProduct));
            menu.addItem(new MenuItem("Update Product", this::updateProduct));
            menu.addItem(new MenuItem("Delete Product", this::deleteProduct));

            menu.addItem(new MenuItem("List Customers", this::listCustomers));
            menu.addItem(new MenuItem("Add Customer", this::addCustomer));
            menu.addItem(new MenuItem("Update Customer", this::updateCustomer));
            menu.addItem(new MenuItem("Delete Customer", this::deleteCustomer));

            menu.addItem(new MenuItem("List Orders", this::listOrders));
            menu.addItem(new MenuItem("Add Order", this::addOrder));
            menu.addItem(new MenuItem("Update Order", this::updateOrder));
            menu.addItem(new MenuItem("Delete Order", this::deleteOrder));

            menu.display();
        };
    }

    // ================================================================================
    // Product Management
    // ================================================================================
    private void listProducts() {
        List<Product> products = productService.getAllProducts();
        products.forEach(System.out::println);
    }

    private void addProduct() {
        Product product = new Product();

        System.out.print("Enter product name: ");
        product.setName(scanner.nextLine().trim());

        System.out.print("Enter product description: ");
        product.setDescription(scanner.nextLine().trim());

        System.out.print("Enter product price: ");
        product.setPrice(Double.parseDouble(scanner.nextLine().trim()));

        productService.saveProduct(product);
    }

    private void updateProduct() {
        Product product = retrieveProduct();
        if (product == null) return;

        System.out.print("Enter product name: ");
        product.setName(scanner.nextLine().trim());

        System.out.print("Enter product description: ");
        product.setDescription(scanner.nextLine().trim());

        System.out.print("Enter product price: ");
        product.setPrice(Double.parseDouble(scanner.nextLine().trim()));

        productService.saveProduct(product);
    }

    private void deleteProduct() {
        Product product = retrieveProduct();
        if (product == null) return;

        System.out.println("Are you sure you want to delete this product? (y/n)");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            productService.deleteProduct(product.getId());
        }
    }

    private Product retrieveProduct() {
        System.out.print("Enter product id: ");
        String input = scanner.nextLine().trim();

        Long productId = null;
        try {
            productId = Long.parseLong(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid product id: " + input);
            return null;
        }

        Product product = productService.getProductById(productId);
        if (product == null) {
            System.out.println("Product with id " + productId + " not found");
            return null;
        }

        System.out.println("Product: " + product + "\n");
        return product;
    }

    // ================================================================================
    // Customer Management
    // ================================================================================
    private void listCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        customers.forEach(System.out::println);
    }

    private void addCustomer() {
        Customer customer = new Customer();

        System.out.print("Enter customer first name: ");
        customer.setFirstName(scanner.nextLine().trim());

        System.out.print("Enter customer last name: ");
        customer.setLastName(scanner.nextLine().trim());

        System.out.print("Enter customer address: ");
        customer.setAddress(scanner.nextLine().trim());

        System.out.print("Enter customer email: ");
        customer.setEmail(scanner.nextLine().trim());

        customerService.saveCustomer(customer);
    }

    private void updateCustomer() {
        Customer customer = retrieveCustomer();
        if (customer == null) return;

        System.out.print("Enter customer first name: ");
        customer.setFirstName(scanner.nextLine().trim());

        System.out.print("Enter customer last name: ");
        customer.setLastName(scanner.nextLine().trim());

        System.out.print("Enter customer address: ");
        customer.setAddress(scanner.nextLine().trim());

        System.out.print("Enter customer email: ");
        customer.setEmail(scanner.nextLine().trim());

        customerService.saveCustomer(customer);
    }

    private void deleteCustomer() {
        Customer customer = retrieveCustomer();
        if (customer == null) return;

        System.out.println("Are you sure you want to delete this customer? (y/n)");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            customerService.deleteCustomer(customer.getId());
        }
    }

    private Customer retrieveCustomer() {
        System.out.print("Enter customer id: ");
        String input = scanner.nextLine().trim();

        Long customerId = null;
        try {
            customerId = Long.parseLong(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid customer id: " + input);
            return null;
        }

        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer with id " + customerId + " not found");
            return null;
        }

        System.out.println("Customer: " + customer + "\n");
        return customer;
    }

    // ================================================================================
    // Order Management
    // ================================================================================
    private void listOrders() {
        List<Order> orders = orderService.getAllOrders();
        orders.forEach(System.out::println);
    }

    private void addOrder() {
        Order order = new Order();

        Customer customer = retrieveCustomer();
        if (customer == null) return;
        order.setCustomer(customer);

        // Add products to the order
        addProductToOrder(order);

        orderService.saveOrder(order);
    }

    private void addProductToOrder(Order order) {

        order.setProducts(new ArrayList<>());

        while (true) {
            System.out.print("Enter product id to add to order (or 0 to finish): ");
            String input = scanner.nextLine().trim();

            Long productId = null;
            try {
                productId = Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid product id: " + input);
                continue;
            }

            if (productId == 0) {
                break;
            }

            Product product = productService.getProductById(productId);
            if (product == null) {
                System.out.println("Product with id " + productId + " not found");
                continue;
            }

            order.addProduct(product);
        }
    }

    private void updateOrder() {
        Order order = retrieveOrder();
        if (order == null) return;

        Customer customer = retrieveCustomer();
        if (customer == null) return;
        order.setCustomer(customer);

        // Add products to the order
        addProductToOrder(order);

        orderService.saveOrder(order);
    }

    private void deleteOrder() {
        Order order = retrieveOrder();
        if (order == null) return;

        System.out.println("Are you sure you want to delete this order? (y/n)");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            orderService.deleteOrder(order.getId());
        }
    }

    private Order retrieveOrder() {
        System.out.print("Enter order id: ");
        String input = scanner.nextLine().trim();

        Long orderId = null;
        try {
            orderId = Long.parseLong(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid order id: " + input);
            return null;
        }

        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            System.out.println("Order with id " + orderId + " not found");
            return null;
        }

        System.out.println("Order: " + order + "\n");
        return order;
    }

    private void populateDummyData() {
        // Create some dummy products
        Product product1 = new Product("Laptop", "High-performance laptop", 1200.00);
        Product product2 = new Product("Mouse", "Wireless mouse", 25.00);
        Product product3 = new Product("Keyboard", "Mechanical keyboard", 75.00);
        productService.saveProduct(product1);
        productService.saveProduct(product2);
        productService.saveProduct(product3);

        // Create some dummy customers
        Customer customer1 = new Customer("John", "Doe", "123 Main St", "john.doe@example.com");
        Customer customer2 = new Customer("Jane", "Smith", "456 Oak Ave", "jane.smith@example.com");
        customerService.saveCustomer(customer1);
        customerService.saveCustomer(customer2);

        //Create Dummy Orders
        Order order1 = new Order();
        order1.setCustomer(customer1);
        order1.addProduct(product1);
        order1.addProduct(product2);
        orderService.saveOrder(order1);

        Order order2 = new Order();
        order2.setCustomer(customer2);
        order2.addProduct(product3);
        orderService.saveOrder(order2);
    }
}