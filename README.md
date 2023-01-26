# Uri's Review Tracker API
A REST-API built using Spring Boot and MYSQL.
---

## This repository contains the source code for the project. However, some important things to note are:
**To run project you would need to:**
1. Have MYSQL installed.
2. Create a database called feedback_project_database (No need to create tables as they are generated through JPA and Hibernate).
3. Have Java 17 installed
4. Have Postman installed to send Requests to the endpoints of the API


## How to use

### To run application
All you need to do is run the following command in your terminal:
> `mvn spring-boot:run`


### To use application
This application is simply run in localhost so to use the API,
you will send requests to the server using Postman.

---
**NOTE**

The database will have some seed data from the main method in the ReviewTrackerApplciation file.
You can either comment that out to start with a fresh database or utilize existing data to test out endpoints.

----
### Project Overview
**- Purpose of Data Flow**

This project was created so that Managers can create feedback and checkpoint for their engineers,
so that their progress can be tracked over time.

Below is a diagram display the Data Model of this project.


![data model example](https://github.com/uri4587/Review-Tracker-API/blob/main/images/Review%20Tracker.png)

Above is displayed the relationships between my models.

An Engineer and Manager have a many-to-many relationship as an Engineer can have many
managers that they report to and a Manager can have many engineers that they supervise.

Each Manager and Engineer have a one-to-many relationship with the Feedback model.
Each Feedback has one Manager and one Engineer associated with the feedback given.

Then we have the Checkpoint model that has a many-to-one relationship with the Feedback
model. 

So each Feedback instance can have many checkpoints, but each checkpoint is associated 
with only one Feedback.

This flow allows the database to keep track of which engineers work with which managers, along
with the given feedback and checkpoints.

----

