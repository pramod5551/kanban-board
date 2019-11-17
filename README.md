# Kanban Board

A simple Kanban Board to keep track of the tasks.
Multiple operations supported for this board and task can be found in [swagger.yaml](https://github.com/pramod5551/kanban-board/blob/master/swagger.yaml "swagger.yaml")


## Project Setup

This application is written in Spring Boot and uses oAuth2 to validate and authorised the appropriate user.
There are two projects in this repository.
1. [kanban-board](https://github.com/pramod5551/kanban-board/tree/master/kanban-board "kanban-board") (API which provided the business functionalities)
2. [oauth2-authorization-server](https://github.com/pramod5551/kanban-board/tree/master/oauth2-authorization-server "oauth2-authorization-server") (Authorisation server to validate and authorised the API users)

>The API is being developed with mySQL as backend. However if you don't have a mySQL setup, feel free to use in-memory H2 DB which comes handy with spring boot. All the required configurations are already mentioned in the [application.yml](https://github.com/pramod5551/kanban-board/blob/master/kanban-board/src/main/resources/application.yml "application.yml") file. Just comment the mySQL set up and uncomment the H2 setup and you are good to go.

## Running Instructions 

Both the projects can be run as Java application. The Authorisation server has to run first as the kanban-board api consumes the functionalities of authorisation server.

## Testing the functionalities 

All the functionalities can be tested using any tools that supports webservice testing. However it's quite easy to test all the operations through Swagger.
To test it through Swagger:
1. Import the [swagger.yaml](https://github.com/pramod5551/kanban-board/blob/master/swagger.yaml "swagger.yaml") file either in your local swagger client or in [swagger editor](https://editor.swagger.io/).
2. Authorise the API by clicking the 'Authorize' button and provide the required details as mentioned below.
3. After authorisation, test the operations as applicable for the acquired scope(s).

To test it through Postman: 
1. import the [postman collection](https://github.com/pramod5551/kanban-board/blob/master/KanbanBoard.postman_collection.json "KanbanBoard.postman_collection.json"). 
2. Use the /auth/token request in the collection to fetch the token from the authorisation server. Provide the grant_type, username and password in the x-www-form-urlencoded in the body and application client details(as mentioned below) in basic authentication.
3. Use the bearer token in the Authorization section to hit the desired request.

## Authorisation Details

There are three in-memory users been created.
1. admin (password: admin)
2. readuser (password: readpass)
3. writeuser (password: writepass)

Three application clients also has been created.
1. kanban_admin (password: kanban_admin)
2. kanban_readuser (password: kanban_readpass)
3. kanban_writeuser (password: kanban_writepass)

The scopes for above clients are as follows:
- kanban\_admin: **write:board**, **write:task**, **read:board**, **read:task**, **delete:board**, **delete:task**
-  kanban\_readuser: **read:board**, **read:task**
-  kanban\_writeuser: **write:board**, **write:task**, **read:board**, **read:task**

> Please Note: For now only **'password'** authorisation grant flow is supported. Hence you need to provide the required above mentioned user and client details while asking for scopes from authorisation server.

## Enhancements
Few future enhancements can be done for this service are:

- Store the token in a persistence store.
- Centralise all the config using config server.
- Implements proper logging.
- Containerisation
															... and many more