# UniScheduleManager

Hello, reader! Welcome to my Spring Boot application designed for managing university schedules. This application encompasses a comprehensive set of data related to various entities within the university ecosystem:

* **Teachers:** Information about the educators in the university.
* **Courses:** Details regarding the academic courses offered.
* **Students:** Data on the students enrolled in different courses.
* **Groups:** Group classifications for better organization.
* **Schedules:** Timetables and schedules for classes.

## Functionality Overview
The UniScheduleManager provides CRUD (Create, Read, Update, Delete) operations for the mentioned entities. Users can effortlessly perform the following actions:

* Create, update, and delete records for teachers, courses, students, groups, and schedules.
* View schedules specific to teachers, groups, or an overall schedule encompassing all classes.


## How to run
```
git clone https://github.com/msd921/UniScheduleManager.git
cd UniScheduleManager
./mvnw spring-boot:run
```

## Project Structure
The project follows a structured organization with clear separation of concerns between controllers, services, and repositories.

## Examples
1. **Create a New Course and Associate it with a Group:**
   - Navigate to the "Courses" section.
   - Click on the "Create" button.
   - Fill in the necessary details such as course name, description, and select the associated groupId.
   - Save the new course, and it will be linked to the specified group.

2. **Update the Schedule for a Specific Teacher:**
   - Access the "Teachers" section.
   - Select the schedule time for whom you want to update the schedule.
   - Make the necessary modifications to the teacher's timetable, including adding, editing, or removing classes.
   - Save the changes, and the updated schedule will be reflected.

These examples showcase the user-friendly functionalities of UniScheduleManager, allowing for efficient management of courses, schedules, and related entities.

## Used technologies
* **Spring Boot:** The foundation of the application.
  * **Web:** For handling web-related functionality.
  * **Spring Data JPA:** Simplifies data access with the Java Persistence API.
  * **Hibernate:** An object-relational mapping framework.
  * **Thymeleaf:** A modern server-side Java template engine for web and standalone environments.
* **Lombok:** A concise library to reduce boilerplate code.
* **PostgreSQL database:** The chosen database for data storage.

## License

This project is Apache License 2.0
