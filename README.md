# TpFoyer - Spring Boot Management System

A comprehensive Spring Boot application for managing university dormitories (foyers), demonstrating advanced Spring Boot features including entity associations, CRUD operations, DTOs, entity assignments, custom queries with JPQL, scheduled tasks, and aspect-oriented programming.

## Technology Stack

- **Java**: 17
- **Framework**: Spring Boot 3.x
- **Build Tool**: Maven
- **Database**: MySQL / H2 (for testing)
- **ORM**: Spring Data JPA / Hibernate
- **API Documentation**: Swagger/OpenAPI 3.0
- **API Testing**: Postman
- **Logging**: SLF4J with Logback
- **Validation**: Spring Validation

## Project Features

### 1. Entity Associations
Complete implementation of JPA entity relationships:
- **One-to-One**: Room ↔ Reservation
- **One-to-Many**: Foyer → Blocks, Block → Rooms
- **Many-to-One**: Room → Block, Block → Foyer
- **Many-to-Many**: Student ↔ Reservation
- Bidirectional and Unidirectional mappings
- Cascade operations
- Fetch strategies (LAZY/EAGER)

### 2. CRUD Operations
Full CRUD functionality for all entities:
- **Create**: Add new entities
- **Read**: Retrieve single or multiple entities
- **Update**: Modify existing entities
- **Delete**: Remove entities
- Pagination and sorting support
- Custom repository methods

### 3. DTOs (Data Transfer Objects)
Implementation of DTO pattern for:
- Separation of concerns
- Data encapsulation
- API response optimization
- Avoiding circular references
- Mapping between entities and DTOs using ModelMapper

### 4. Entity Affectation (Assignment)
Business logic for entity assignments:
- Assign students to rooms
- Assign rooms to blocks
- Assign blocks to foyers
- Validate capacity constraints
- Handle assignment conflicts
- Unassignment operations

### 5. Keywords & Search
Advanced search functionality:
- Search by keywords across multiple fields
- Case-insensitive search
- Wildcard search support
- Multiple search criteria
- Search with filters

### 6. JPQL (Java Persistence Query Language)
Custom queries implementation:
- Named queries
- Dynamic queries
- Complex join queries
- Aggregate functions (COUNT, SUM, AVG)
- Subqueries
- Custom projections

### 7. Scheduler
Scheduled tasks using Spring `@Scheduled`:
- Automatic room assignment
- Reservation expiration check
- Database cleanup tasks
- Report generation
- Email notifications
- Cron expressions for flexible scheduling

### 8. Aspect-Oriented Programming (AOP)
Cross-cutting concerns implementation:
- **Logging Aspect**: Log method entry, exit, and parameters
- **Performance Aspect**: Monitor execution time
- **Exception Handling Aspect**: Centralized exception logging
- **Security Aspect**: Audit sensitive operations
- Custom annotations for aspects

## Project Structure

```
tpfoyer/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── tpfoyer/
│   │   │           ├── entity/
│   │   │           │   ├── Foyer.java
│   │   │           │   ├── Bloc.java
│   │   │           │   ├── Chambre.java
│   │   │           │   ├── Etudiant.java
│   │   │           │   ├── Reservation.java
│   │   │           │   └── TypeChambre.java (enum)
│   │   │           ├── dto/
│   │   │           │   ├── FoyerDTO.java
│   │   │           │   ├── BlocDTO.java
│   │   │           │   ├── ChambreDTO.java
│   │   │           │   ├── EtudiantDTO.java
│   │   │           │   └── ReservationDTO.java
│   │   │           ├── repository/
│   │   │           │   ├── FoyerRepository.java
│   │   │           │   ├── BlocRepository.java
│   │   │           │   ├── ChambreRepository.java
│   │   │           │   ├── EtudiantRepository.java
│   │   │           │   └── ReservationRepository.java
│   │   │           ├── service/
│   │   │           │   ├── IFoyerService.java
│   │   │           │   ├── FoyerServiceImpl.java
│   │   │           │   ├── IBlocService.java
│   │   │           │   ├── BlocServiceImpl.java
│   │   │           │   ├── IChambreService.java
│   │   │           │   ├── ChambreServiceImpl.java
│   │   │           │   ├── IEtudiantService.java
│   │   │           │   ├── EtudiantServiceImpl.java
│   │   │           │   ├── IReservationService.java
│   │   │           │   └── ReservationServiceImpl.java
│   │   │           ├── controller/
│   │   │           │   ├── FoyerController.java
│   │   │           │   ├── BlocController.java
│   │   │           │   ├── ChambreController.java
│   │   │           │   ├── EtudiantController.java
│   │   │           │   └── ReservationController.java
│   │   │           ├── aspect/
│   │   │           │   ├── LoggingAspect.java
│   │   │           │   ├── PerformanceAspect.java
│   │   │           │   └── ExceptionAspect.java
│   │   │           └── TpFoyerApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   └── test/
│       └── java/
├── pom.xml
└── README.md
```

## Prerequisites

- JDK 17 or higher
- Maven 3.6+
- MySQL 8.0+ or H2 Database
- Postman (for API testing)
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

## Installation

### 1. Clone the repository
```bash
git clone https://github.com/tayaria/tpFoyer.git
cd tpFoyer
```

### 2. Configure Database
Create a MySQL database:
```sql
CREATE DATABASE tpfoyer_db;
```

Update `src/main/resources/application.properties`:
```properties
# Server Configuration
server.port=8089

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/tpfoyer_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

```

### 3. Build the project
```bash
mvn clean install
```

### 4. Run the application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8089`

## API Documentation

Access Swagger UI for interactive API documentation:
```
http://localhost:8089/swagger-ui.html
```

OpenAPI JSON:
```
http://localhost:8089/api-docs
```

## Main Entities

### 1. Foyer (Dormitory)
```java
@Entity
public class Foyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFoyer;
    private String nomFoyer;
    private Long capaciteFoyer;
    
    @OneToMany(mappedBy = "foyer", cascade = CascadeType.ALL)
    private List<Bloc> blocs;
}
```

### 2. Bloc (Block)
```java
@Entity
public class Bloc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBloc;
    private String nomBloc;
    private Long capaciteBloc;
    
    @ManyToOne
    private Foyer foyer;
    
    @OneToMany(mappedBy = "bloc", cascade = CascadeType.ALL)
    private List<Chambre> chambres;
}
```

### 3. Chambre (Room)
```java
@Entity
public class Chambre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChambre;
    private Long numeroChambre;
    
    @Enumerated(EnumType.STRING)
    private TypeChambre typeC;
    
    @ManyToOne
    private Bloc bloc;
    
    @OneToMany(mappedBy = "chambre")
    private List<Reservation> reservations;
}
```

### 4. Etudiant (Student)
```java
@Entity
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtudiant;
    private String nomEtudiant;
    private String prenomEtudiant;
    private Long cin;
    private String ecole;
    
    @ManyToMany(mappedBy = "etudiants")
    private List<Reservation> reservations;
}
```

### 5. Reservation
```java
@Entity
public class Reservation {
    @Id
    private String idReservation;
    private LocalDate anneeUniversitaire;
    private Boolean estValide;
    
    @ManyToMany
    private List<Etudiant> etudiants;
}
```

## API Endpoints

### Foyer Endpoints
- `GET /api/foyers` - Get all foyers
- `GET /api/foyers/{id}` - Get foyer by ID
- `POST /api/foyers` - Create new foyer
- `PUT /api/foyers/{id}` - Update foyer
- `DELETE /api/foyers/{id}` - Delete foyer
- `GET /api/foyers/search?keyword={keyword}` - Search foyers

### Bloc Endpoints
- `GET /api/blocs` - Get all blocs
- `GET /api/blocs/{id}` - Get bloc by ID
- `POST /api/blocs` - Create new bloc
- `PUT /api/blocs/{id}` - Update bloc
- `DELETE /api/blocs/{id}` - Delete bloc
- `POST /api/blocs/{blocId}/affecter-foyer/{foyerId}` - Assign bloc to foyer

### Chambre Endpoints
- `GET /api/chambres` - Get all rooms
- `GET /api/chambres/{id}` - Get room by ID
- `POST /api/chambres` - Create new room
- `PUT /api/chambres/{id}` - Update room
- `DELETE /api/chambres/{id}` - Delete room
- `GET /api/chambres/type/{type}` - Get rooms by type
- `GET /api/chambres/disponibles` - Get available rooms
- `POST /api/chambres/{chambreId}/affecter-bloc/{blocId}` - Assign room to block

### Etudiant Endpoints
- `GET /api/etudiants` - Get all students
- `GET /api/etudiants/{id}` - Get student by ID
- `POST /api/etudiants` - Create new student
- `PUT /api/etudiants/{id}` - Update student
- `DELETE /api/etudiants/{id}` - Delete student
- `GET /api/etudiants/search?keyword={keyword}` - Search students
- `GET /api/etudiants/ecole/{ecole}` - Get students by school

### Reservation Endpoints
- `GET /api/reservations` - Get all reservations
- `GET /api/reservations/{id}` - Get reservation by ID
- `POST /api/reservations` - Create new reservation
- `PUT /api/reservations/{id}` - Update reservation
- `DELETE /api/reservations/{id}` - Delete reservation
- `POST /api/reservations/{reservationId}/affecter-etudiant/{etudiantId}` - Assign student to reservation
- `GET /api/reservations/annee/{annee}` - Get reservations by year

## JPQL Queries Examples

Located in respective repository classes:

```java
// FoyerRepository
@Query("SELECT f FROM Foyer f WHERE f.capaciteFoyer > :capacite")
List<Foyer> findFoyersByCapaciteGreaterThan(@Param("capacite") Long capacite);

@Query("SELECT f FROM Foyer f JOIN f.blocs b WHERE b.nomBloc = :nomBloc")
Foyer findFoyerByBlocName(@Param("nomBloc") String nomBloc);

// ChambreRepository
@Query("SELECT c FROM Chambre c WHERE c.typeC = :type AND c.numeroChambre LIKE %:numero%")
List<Chambre> findByTypeAndNumeroContaining(@Param("type") TypeChambre type, @Param("numero") String numero);

// EtudiantRepository
@Query("SELECT e FROM Etudiant e WHERE LOWER(e.nomEtudiant) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
       "OR LOWER(e.prenomEtudiant) LIKE LOWER(CONCAT('%', :keyword, '%'))")
List<Etudiant> searchByKeyword(@Param("keyword") String keyword);

// ReservationRepository
@Query("SELECT COUNT(r) FROM Reservation r WHERE YEAR(r.anneeUniversitaire) = :annee")
Long countReservationsByYear(@Param("annee") Integer annee);
```

## Scheduler Tasks

Located in `ReservationScheduler.java`:

```java
@Scheduled(cron = "0 0 2 * * ?") // Every day at 2 AM
public void checkExpiredReservations() {
    // Check and update expired reservations
}

@Scheduled(fixedDelay = 3600000) // Every hour
public void generateReports() {
    // Generate statistical reports
}

@Scheduled(cron = "0 0 0 1 * ?") // First day of each month
public void monthlyCleanup() {
    // Perform monthly database cleanup
}
```

## Aspect-Oriented Programming

### Logging Aspect
```java
@Aspect
@Component
public class LoggingAspect {
    
    @Before("execution(* com.tpfoyer.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Method called: {}", joinPoint.getSignature().getName());
    }
    
    @AfterReturning(pointcut = "execution(* com.tpfoyer.service.*.*(..))", 
                    returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Method {} returned: {}", joinPoint.getSignature().getName(), result);
    }
}
```

### Performance Aspect
```java
@Aspect
@Component
public class PerformanceAspect {
    
    @Around("execution(* com.tpfoyer.service.*.*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        log.info("{} executed in {} ms", joinPoint.getSignature(), executionTime);
        return result;
    }
}
```

## DTO Mapping

Using ModelMapper for entity-DTO conversion:

```java
@Service
public class FoyerServiceImpl implements IFoyerService {
    
    @Autowired
    private ModelMapper modelMapper;
    
    public FoyerDTO convertToDto(Foyer foyer) {
        return modelMapper.map(foyer, FoyerDTO.class);
    }
    
    public Foyer convertToEntity(FoyerDTO foyerDTO) {
        return modelMapper.map(foyerDTO, Foyer.class);
    }
}
```

## Testing with Postman

Import the Postman collection from `postman/TpFoyer.postman_collection.json`

### Example Requests

**Create Foyer:**
```json
POST http://localhost:8089/api/foyers
Content-Type: application/json

{
  "nomFoyer": "Foyer Universitaire A",
  "capaciteFoyer": 500
}
```

**Create Student:**
```json
POST http://localhost:8089/api/etudiants
Content-Type: application/json

{
  "nomEtudiant": "Dupont",
  "prenomEtudiant": "Jean",
  "cin": 12345678,
  "ecole": "ESPRIT"
}
```

**Search Students:**
```
GET http://localhost:8089/api/etudiants/search?keyword=jean
```

## Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=FoyerServiceTest

# Run with coverage
mvn clean test jacoco:report
```

## Build for Production

```bash
mvn clean package
java -jar target/tpfoyer-0.0.1-SNAPSHOT.jar
```

## Docker Support

Create `Dockerfile`:
```dockerfile
FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

Build and run:
```bash
docker build -t tpfoyer .
docker run -p 8089:8089 tpfoyer
```

## Dependencies

Key dependencies in `pom.xml`:
```xml
<dependencies>
    <!-- Spring Boot Starter -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <!-- Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <!-- MySQL Driver -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
    </dependency>
    
    <!-- Swagger/OpenAPI -->
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.0.2</version>
    </dependency>
    
    <!-- ModelMapper -->
    <dependency>
        <groupId>org.modelmapper</groupId>
        <artifactId>modelmapper</artifactId>
        <version>3.1.1</version>
    </dependency>
    
    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
    
    <!-- Spring AOP -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-aop</artifactId>
    </dependency>
</dependencies>
```

## Troubleshooting

### Database Connection Issues
- Verify MySQL is running
- Check credentials in `application.properties`
- Ensure database exists

### Port Already in Use
Change port in `application.properties`:
```properties
server.port=8090
```

### Swagger Not Loading
Verify springdoc dependency and access correct URL

## Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open Pull Request

## License

This project is licensed under the MIT License.

## Author

Aymen Tayari - aymentayari191@gmail.com

## Acknowledgments

- Spring Boot Documentation
- Hibernate Documentation
- Swagger/OpenAPI Specification
