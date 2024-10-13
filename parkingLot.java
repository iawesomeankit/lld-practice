import java.util.*;

abstract class Vehicle {
    private String licensePlate;
    private VehicleType type;
    
    public Vehicle(String licensePlate, VehicleType type) {
        this.licensePlate = licensePlate;
        this.type = type;
    }
    
    public VehicleType getType() {
        return type;
    }
    
    public String getLicensePlate() {
        return licensePlate;
    }
}

enum VehicleType {
    CAR, BIKE, TRUCK
}

class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate, VehicleType.CAR);
    }
}

class Bike extends Vehicle {
    public Bike(String licensePlate) {
        super(licensePlate, VehicleType.BIKE);
    }
}

class Truck extends Vehicle {
    public Truck(String licensePlate) {
        super(licensePlate, VehicleType.TRUCK);
    }
}

class ParkingSpot {
    private int spotId;
    private VehicleType spotType;
    private boolean isAvailable;
    
    public ParkingSpot(int spotId, VehicleType spotType) {
        this.spotId = spotId;
        this.spotType = spotType;
        this.isAvailable = true;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public VehicleType getSpotType() {
        return spotType;
    }
    
    public void assignSpot() {
        isAvailable = false;
    }
    
    public void freeSpot() {
        isAvailable = true;
    }
    
    public int getSpotId() {
        return spotId;
    }
}

class Ticket {
    private int ticketId;
    private int spotId;
    private String licensePlate;
    private VehicleType vehicleType;
    private Date entryTime;
    
    public Ticket(int ticketId, int spotId, String licensePlate, VehicleType vehicleType) {
        this.ticketId = ticketId;
        this.spotId = spotId;
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
        this.entryTime = new Date();
    }
    
    public String getTicketInfo() {
        return "Ticket ID: " + ticketId + ", Spot ID: " + spotId + ", Vehicle Type: " + vehicleType + ", License Plate: " + licensePlate + ", Entry Time: " + entryTime;
    }
    
    public int getSpotId() {
        return spotId;
    }
}

class ParkingLot {
    private Map<Integer, ParkingSpot> parkingSpots;
    private List<Ticket> tickets;
    private int ticketCounter = 1;

    public ParkingLot() {
        parkingSpots = new HashMap<>();
        tickets = new ArrayList<>();
    }

    public void addParkingSpot(int spotId, VehicleType spotType) {
        parkingSpots.put(spotId, new ParkingSpot(spotId, spotType));
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : parkingSpots.values()) {
            if (spot.isAvailable() && spot.getSpotType() == vehicle.getType()) {
                spot.assignSpot();
                Ticket ticket = new Ticket(ticketCounter++, spot.getSpotId(), vehicle.getLicensePlate(), vehicle.getType());
                tickets.add(ticket);
                return ticket;
            }
        }
        System.out.println("No available spot for vehicle type: " + vehicle.getType());
        return null;
    }

    public void unParkVehicle(int ticketId) {
        for (Ticket ticket : tickets) {
            if (ticketId == ticket.getTicketInfo().hashCode()) { // Simplified ticket lookup
                int spotId = ticket.getSpotId();
                parkingSpots.get(spotId).freeSpot();
                System.out.println("Vehicle unparked from spot " + spotId);
                tickets.remove(ticket);
                break;
            }
        }
    }

    public void displayAvailableSpots() {
        System.out.println("Available spots:");
        for (ParkingSpot spot : parkingSpots.values()) {
            if (spot.isAvailable()) {
                System.out.println("Spot ID: " + spot.getSpotId() + ", Spot Type: " + spot.getSpotType());
            }
        }
    }
}

public class ParkingLotSystem {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();
        
        // Add parking spots
        parkingLot.addParkingSpot(1, VehicleType.CAR);
        parkingLot.addParkingSpot(2, VehicleType.BIKE);
        parkingLot.addParkingSpot(3, VehicleType.TRUCK);
        parkingLot.addParkingSpot(4, VehicleType.CAR);
        parkingLot.addParkingSpot(5, VehicleType.BIKE);
        
        // Park vehicles
        Vehicle car1 = new Car("CAR123");
        Vehicle bike1 = new Bike("BIKE123");
        Vehicle truck1 = new Truck("TRUCK123");
        
        Ticket carTicket = parkingLot.parkVehicle(car1);
        Ticket bikeTicket = parkingLot.parkVehicle(bike1);
        Ticket truckTicket = parkingLot.parkVehicle(truck1);
        
        // Display available spots
        parkingLot.displayAvailableSpots();
        
        // Unpark vehicle (based on ticket)
        parkingLot.unParkVehicle(carTicket.getTicketInfo().hashCode());
        
        // Display available spots after un-parking
        parkingLot.displayAvailableSpots();
    }
}
