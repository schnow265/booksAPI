# Testing

> This project uses Test Driven Development. Which means that a test should be written for every feature *before* you write that feature.

## Database

Originally Testing with [h2](http://h2database.com/html/main.html) was planned, but there were problems getting it to work with the SQL scripts used for Production.

This means that this Project gets tested with PostgreSQL. To stop interference with the prod db, a second DB called `bookdb_testing`.