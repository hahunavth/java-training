package org.hahunavth.javacore.practice;

import java.util.*;

class VehicleOwner {
    private String cmndNumber;
    private String fullName;
    private String email;

    public VehicleOwner(String cmndNumber, String fullName, String email) {
        this.cmndNumber = cmndNumber;
        this.fullName = fullName;
        this.email = email;
    }

    public String getCmndNumber() {
        return cmndNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }
}

class Vehicle {
    protected String vehicleNumber;
    protected String manufacturer;
    protected int yearOfManufacture;
    protected String vehicleColor;
    protected VehicleOwner owner;

    public Vehicle(String vehicleNumber, String manufacturer, int yearOfManufacture, String vehicleColor, VehicleOwner owner) {
        this.vehicleNumber = vehicleNumber;
        this.manufacturer = manufacturer;
        this.yearOfManufacture = yearOfManufacture;
        this.vehicleColor = vehicleColor;
        this.owner = owner;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public VehicleOwner getOwner() {
        return owner;
    }
}

class Car extends Vehicle {
    private int numberOfSeats;
    private String engineType;

    public Car(String vehicleNumber, String manufacturer, int yearOfManufacture, String vehicleColor, VehicleOwner owner, int numberOfSeats, String engineType) {
        super(vehicleNumber, manufacturer, yearOfManufacture, vehicleColor, owner);
        this.numberOfSeats = numberOfSeats;
        this.engineType = engineType;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public String getEngineType() {
        return engineType;
    }
}

class Motorbike extends Vehicle {
    private int capacity;

    public Motorbike(String vehicleNumber, String manufacturer, int yearOfManufacture, String vehicleColor, VehicleOwner owner, int capacity) {
        super(vehicleNumber, manufacturer, yearOfManufacture, vehicleColor, owner);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}

class Truck extends Vehicle {
    private double tonnage;

    public Truck(String vehicleNumber, String manufacturer, int yearOfManufacture, String vehicleColor, VehicleOwner owner, double tonnage) {
        super(vehicleNumber, manufacturer, yearOfManufacture, vehicleColor, owner);
        this.tonnage = tonnage;
    }

    public double getTonnage() {
        return tonnage;
    }
}

class PoliceTransportManagement {
    private List<Vehicle> vehicles = new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public Vehicle findVehicleByNumber(String vehicleNumber) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleNumber().equals(vehicleNumber)) {
                return vehicle;
            }
        }
        return null;
    }

    public List<Vehicle> findVehiclesByManufacturer(String manufacturer) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getManufacturer().equalsIgnoreCase(manufacturer)) {
                result.add(vehicle);
            }
        }
        return result;
    }

    public Vehicle findVehicleByOwnerCmnd(String cmndNumber) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getOwner().getCmndNumber().equals(cmndNumber)) {
                return vehicle;
            }
        }
        return null;
    }

    public void deleteVehiclesByManufacturer(String manufacturer) {
        Iterator<Vehicle> iterator = vehicles.iterator();
        while (iterator.hasNext()) {
            Vehicle vehicle = iterator.next();
            if (vehicle.getManufacturer().equalsIgnoreCase(manufacturer)) {
                iterator.remove();
            }
        }
    }

    public String getManufacturerWithMostVehicles() {
        Map<String, Integer> manufacturerCount = new HashMap<>();
        for (Vehicle vehicle : vehicles) {
            manufacturerCount.put(vehicle.getManufacturer(), manufacturerCount.getOrDefault(vehicle.getManufacturer(), 0) + 1);
        }

        String mostManufacturer = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : manufacturerCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostManufacturer = entry.getKey();
            }
        }
        return mostManufacturer;
    }

    public void sortVehiclesByCount() {
        vehicles.sort((v1, v2) -> Integer.compare(findVehiclesByManufacturer(v2.getManufacturer()).size(), findVehiclesByManufacturer(v1.getManufacturer()).size()));
    }

    public Map<String, Integer> getVehicleTypeStatistics() {
        Map<String, Integer> vehicleTypeCount = new HashMap<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Car) {
                vehicleTypeCount.put("Car", vehicleTypeCount.getOrDefault("Car", 0) + 1);
            } else if (vehicle instanceof Motorbike) {
                vehicleTypeCount.put("Motorbike", vehicleTypeCount.getOrDefault("Motorbike", 0) + 1);
            } else if (vehicle instanceof Truck) {
                vehicleTypeCount.put("Truck", vehicleTypeCount.getOrDefault("Truck", 0) + 1);
            }
        }
        return vehicleTypeCount;
    }

}

public class SLearn93 {
    public static void main(String[] args) {
        PoliceTransportManagement transportManagement = new PoliceTransportManagement();

        // Creating vehicle owners
        VehicleOwner owner1 = new VehicleOwner("123456789012", "John Doe", "john@example.com");
        VehicleOwner owner2 = new VehicleOwner("987654321012", "Jane Smith", "jane@example.com");

        // Creating vehicles
        Car car1 = new Car("CAR01", "Toyota", 2020, "Blue", owner1, 5, "Gasoline");
        Car car2 = new Car("CAR02", "Honda", 2018, "Red", owner2, 4, "Electric");
        Motorbike motorbike1 = new Motorbike("BIKE01", "Yamaha", 2022, "Black", owner1, 250);
        Truck truck1 = new Truck("TRUCK01", "Suzuki", 2015, "White", owner2, 10.5);

        // Adding vehicles to the transport management system
        transportManagement.addVehicle(car1);
        transportManagement.addVehicle(car2);
        transportManagement.addVehicle(motorbike1);
        transportManagement.addVehicle(truck1);

        // Example usage of methods
        System.out.println("Vehicle found by number: " + transportManagement.findVehicleByNumber("CAR01").getManufacturer());

        Vehicle foundVehicle = transportManagement.findVehicleByOwnerCmnd("987654321012");
        if (foundVehicle != null) {
            System.out.println("Vehicle owner's vehicle found: " + foundVehicle.getVehicleNumber());
        } else {
            System.out.println("No vehicle found for the provided owner CMND.");
        }

        transportManagement.deleteVehiclesByManufacturer("Toyota");
        System.out.println("Vehicles of manufacturer Toyota deleted.");

        String mostManufacturer = transportManagement.getManufacturerWithMostVehicles();
        System.out.println("Manufacturer with most vehicles: " + mostManufacturer);

        transportManagement.sortVehiclesByCount();
        System.out.println("Vehicles sorted by manufacturer count.");

        Map<String, Integer> vehicleTypeStatistics = transportManagement.getVehicleTypeStatistics();
        System.out.println("Vehicle type statistics:");
        for (Map.Entry<String, Integer> entry : vehicleTypeStatistics.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}