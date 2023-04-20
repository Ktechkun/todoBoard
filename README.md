# Shared TodoList 

This project is shared todolist where user can:
1. Create a shared board
2. Add todo items on the board
3. Mark the todo items completed
4. Get notifications on terminal

## Prerequisites

Gradle version 8.1\
Java version 20\
Kafka server running in 9092 port

## How to run
```
gradle bootRun
```

## How to test

Import json file provided in `/todoBoard/postman` in Postman.\
Hit the apis
1. Create user
2. Create / Delete Board
3. Add / Modify / Delete Todo item

