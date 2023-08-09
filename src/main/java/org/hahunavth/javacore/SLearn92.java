package org.hahunavth.javacore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Product {
    protected int productCode;
    protected String name;
    protected int inventoryQuantity;
    protected double unitPrice;

    public Product(int productCode, String name, int inventoryQuantity, double unitPrice) {
        this.productCode = productCode;
        this.name = name;
        this.inventoryQuantity = inventoryQuantity;
        this.unitPrice = unitPrice;
    }

    public abstract double calculateVAT();

    public int getProductCode() {
        return productCode;
    }

    public String getName() {
        return name;
    }

    public int getInventoryQuantity() {
        return inventoryQuantity;
    }
}

class FoodProduct extends Product {
    private LocalDate manufactureDate;
    private LocalDate expirationDate;
    private String supplier;

    public FoodProduct(int productCode, String name, int inventoryQuantity, double unitPrice,
                       LocalDate manufactureDate, LocalDate expirationDate, String supplier) {
        super(productCode, name, inventoryQuantity, unitPrice);
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
        this.supplier = supplier;
    }

    @Override
    public double calculateVAT() {
        return unitPrice * 0.05;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}

class ElectricalProduct extends Product {
    private int warrantyMonths;
    private double capacityKW;

    public ElectricalProduct(int productCode, String name, int inventoryQuantity, double unitPrice,
                             int warrantyMonths, double capacityKW) {
        super(productCode, name, inventoryQuantity, unitPrice);
        this.warrantyMonths = warrantyMonths;
        this.capacityKW = capacityKW;
    }

    @Override
    public double calculateVAT() {
        return unitPrice * 0.10;
    }
}

class CrockeryProduct extends Product {
    private String manufacturer;
    private LocalDate arrivalDate;

    public CrockeryProduct(int productCode, String name, int inventoryQuantity, double unitPrice,
                           String manufacturer, LocalDate arrivalDate) {
        super(productCode, name, inventoryQuantity, unitPrice);
        this.manufacturer = manufacturer;
        this.arrivalDate = arrivalDate;
    }

    @Override
    public double calculateVAT() {
        return unitPrice * 0.10;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}

class SupermarketManager {
    private List<Product> inventory;

    public SupermarketManager() {
        inventory = new ArrayList<>();
    }

    public void addProduct(Product product) {
        for (Product p : inventory) {
            if (p.getProductCode() == product.getProductCode()) {
                System.out.println("Product with the same code already exists.");
                return;
            }
        }
        inventory.add(product);
    }

    public void measureConsumption() {
        LocalDate currentDate = LocalDate.now();

        for (Product product : inventory) {
            if (product instanceof ElectricalProduct) {
                if (product.getInventoryQuantity() < 3) {
                    System.out.println("Electronic product with code " + product.getProductCode() + " has been sold.");
                }
            } else if (product instanceof FoodProduct) {
                FoodProduct foodProduct = (FoodProduct) product;
                if (foodProduct.getInventoryQuantity() > 0 && currentDate.isAfter(foodProduct.getExpirationDate())) {
                    System.out.println("Food product with code " + product.getProductCode() + " is hard to sell.");
                }
            } else if (product instanceof CrockeryProduct) {
                CrockeryProduct crockeryProduct = (CrockeryProduct) product;
                LocalDate arrivalDate = crockeryProduct.getArrivalDate();
                if (crockeryProduct.getInventoryQuantity() > 50 &&
                    currentDate.minusDays(10).isAfter(arrivalDate)) {
                    System.out.println("Crockery product with code " + product.getProductCode() + " is a slow sale.");
                }
            }
        }
    }

    public void printInventory() {
        for (Product product : inventory) {
            System.out.println("Product Code: " + product.getProductCode());
            System.out.println("Product Name: " + product.getName());
            System.out.println("Inventory Quantity: " + product.getInventoryQuantity());
            System.out.println("Unit Price: " + product.calculateVAT());
            System.out.println();
        }
    }
}


public class SLearn92 {
    public static void main(String[] args) {
        SupermarketManager manager = new SupermarketManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select product type to add (1: Food, 2: Electrical, 3: Crockery, 4: Exit):");
            int choice = scanner.nextInt();

            if (choice == 4) {
                break;
            }

            System.out.println("Enter product code:");
            int productCode = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.println("Enter product name:");
            String productName = scanner.nextLine();

            System.out.println("Enter inventory quantity:");
            int inventoryQuantity = scanner.nextInt();

            System.out.println("Enter unit price:");
            double unitPrice = scanner.nextDouble();

            if (choice == 1) {
                System.out.println("Enter manufacture date (YYYY-MM-DD):");
                String manufactureDateStr = scanner.next();
                LocalDate manufactureDate = LocalDate.parse(manufactureDateStr);

                System.out.println("Enter expiration date (YYYY-MM-DD):");
                String expirationDateStr = scanner.next();
                LocalDate expirationDate = LocalDate.parse(expirationDateStr);

                scanner.nextLine(); // Consume newline

                System.out.println("Enter supplier:");
                String supplier = scanner.nextLine();

                manager.addProduct(new FoodProduct(productCode, productName, inventoryQuantity,
                        unitPrice, manufactureDate, expirationDate, supplier));
            } else if (choice == 2) {
                System.out.println("Enter warranty months:");
                int warrantyMonths = scanner.nextInt();

                System.out.println("Enter capacity KW:");
                double capacityKW = scanner.nextDouble();

                manager.addProduct(new ElectricalProduct(productCode, productName, inventoryQuantity,
                        unitPrice, warrantyMonths, capacityKW));
            } else if (choice == 3) {
                scanner.nextLine(); // Consume newline

                System.out.println("Enter manufacturer:");
                String manufacturer = scanner.nextLine();

                System.out.println("Enter arrival date (YYYY-MM-DD):");
                String arrivalDateStr = scanner.next();
                LocalDate arrivalDate = LocalDate.parse(arrivalDateStr);

                manager.addProduct(new CrockeryProduct(productCode, productName, inventoryQuantity,
                        unitPrice, manufacturer, arrivalDate));
            }
        }

        manager.printInventory();
    }
}

