Task Manager
========

This project involves a task management system where users create, update, and manage tasks. The application implements JWT authentication for security and includes aggregate views of task data. This web service provides CRUD operations for daily use.

Account
--------
- POST /api/accounts to register account:
```
{
  "email": "example@mail.com",
  "password": "password"
}
```
Authentication
--------
- POST /api/auth/token to create token:
```
{
  "token": <string>
}
```
Features (Use access token to the Authorization header)
--------
```
Authorization: Bearer <string token value>
```
- GET /api/tasks to display tasks. Optional "author" and or "assignee" parameter to display tasks authored by or assigned to a particular user.
```
GET /api/tasks?author=example@mail.com
GET /api/tasks?assignee=example@mail.com
```
- POST /api/tasks to add task:
```
{
  "title": <string, not null, not blank>,
  "description": <string, not null, not blank>
}
```
- PUT /api/tasks/<taskId>/assign to assign task. The user must be the author to assign task:
```
{ 
  "assignee": <email address|"none"> 
}
```
- PUT /api/tasks/<taskId>/status to change status. The user must be the author or the assignee:
```
{
  "status": <"CREATED"|"IN_PROGRESS"|"COMPLETED">
}
```
