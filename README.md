# Park and Ride - Parking Reservation System

A Spring Boot application for managing parking reservations at park and ride facilities.

## Overview

Park and Ride is a web-based parking management system that allows users to find available parking locations, view available slots, and make reservations. The system efficiently manages parking slots and automatically handles reservation expiration.

## Features

- Find nearby parking locations
- View available parking slots at a specific location
- Make parking reservations
- Automatic release of expired reservations
- User management
- Full REST API for client applications

## Technology Stack

- Java
- Spring Boot
- Spring Data JPA
- H2/MySQL Database (configurable)
- RESTful API architecture

## API Endpoints

### User Management
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `POST /api/users` - Create a new user
- `DELETE /api/users/{id}` - Delete a user

### Parking Locations
- `GET /api/parkingLocations` - Get all nearby parking locations (within 1km radius)
- `GET /api/parkingLocations/{id}` - Get parking location by ID
- `POST /api/parkingLocations` - Create a new parking location
- `DELETE /api/parkingLocations/{id}` - Delete a parking location

### Parking Slots
- `GET /api/slots/parking/{id}` - Get all available slots for a specific parking location
- `GET /api/slots/{id}` - Get parking slot by ID
- `POST /api/slots` - Create a new parking slot
- `DELETE /api/slots/{id}` - Delete a parking slot

### Reservations
- `GET /api/reservations` - Get all reservations
- `GET /api/reservations/{id}` - Get reservation by ID
- `POST /api/reservations` - Create a new reservation
- `DELETE /api/reservations/{id}` - Delete a reservation

### Booking
- `POST /api/booking/reserve` - Book a parking slot

## Data Models

### User
- id: Long
- username: String
- email: String
- passwordHash: String
- phone: String
- createdAt: LocalDateTime

### ParkingLocation
- id: Long
- name: String
- latitude: Double
- longitude: Double
- description: String

### ParkingSlot
- id: Long
- parkingLocation: ParkingLocation
- slotNumber: String
- status: SlotStatus (AVAILABLE, RESERVED, OCCUPIED)

### Reservation
- id: Long
- user: User
- parkingSlot: ParkingSlot
- startTime: LocalDateTime
- endTime: LocalDateTime
- status: ReservationStatus (ACTIVE, CANCELLED, COMPLETED)
- createdAt: LocalDateTime
- updatedAt: LocalDateTime

## Booking Process

1. User selects a parking location
2. User views available slots at that location
3. User sends a booking request with:
   - userId
   - slotId
   - start time
   - end time
4. System checks slot availability and creates a reservation
5. Parking slot status is updated to RESERVED
6. When reservation expires, the system automatically releases the slot

## Automated Processes

The system includes a scheduled task that runs every minute to check for expired reservations:
- Finds all active reservations with end times in the past
- Sets reservation status to COMPLETED
- Sets parking slot status back to AVAILABLE

## Setup and Installation

1. Clone the repository
2. Configure database in `application.properties`
3. Run `mvn clean install`
4. Run `mvn spring-boot:run`

## API Request Examples

### Book a Parking Slot
```json
POST /api/booking/reserve
{
  "userId": 1,
  "slotId": 2,
  "start": "2023-12-01T10:00:00",
  "end": "2023-12-01T18:00:00"
}
```

### Create a Parking Location
```json
POST /api/parkingLocations
{
  "name": "Central Station Parking",
  "latitude": 28.6139,
  "longitude": 77.2090,
  "description": "Parking facility at Central Station"
}
```

## Future Enhancements

- Payment integration
- Mobile application
- User authentication and authorization
- QR code generation for reservations
- Notification system for booking confirmations and reminders
- Advanced search and filtering options
