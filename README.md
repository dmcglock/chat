Simple Chat App
=========
The purpose of this API is to allow at least two users to send short text messages to each other. 

## Getting Started

#### Clone the repository
`git clone https://github.com/dmcglock/chat`

#### Install Gradle (if not already installed)
[Gradle install](https://gradle.org/install/)

#### Download Java / Check version
To check your version `java -version`

This project was created using Java 11. [Download](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) 

#### Unit tests
You can choose to run the unit tests through `./gradlew clean build`

#### Start the Service
`./gradlew run` <br/>

If the application has booted correctly, the following message will appear ```Started Application in 3.696 seconds (JVM running for 4.148) ```

## API 

### User
#### POST /create
Takes a username, first name, and last name and creates a user
Request: `localhost:3000/user/create` <br/>
Body: `{
           "username": "newguy",
           "firstName": "newman",
           "lastName": "seinfeld"
       }` <br/>
201 - Successful Response: `{
               "userId": 1,
               "username": "newguy",
               "firstName": "newman",
               "lastName": "seinfeld"
           }`
400 - Missing username, first name, last name
409 - Username already taken
           
#### GET /user/all
Returns a JSON array of user information <br/>
Request: `localhost:3000/user/all` <br/>
Response: `[
               {
                   "userId": 1,
                   "username": "newguy",
                   "firstName": "newman",
                   "lastName": "seinfeld"
               },
               {
                   "userId": 2,
                   "username": "scottiep",
                   "firstName": "scott",
                   "lastName": "pilgrim"
               }
           ]` <br/>
           
#### GET /user/{userId}
Returns a single user given their ID <br/>
Request: `localhost:3000/user/1` <br/>
Response: `{
              "userId": 1,
              "username": "newguy",
              "firstName": "newman",
              "lastName": "seinfeld"
          }` <br/>           

#### DELETE /user/{userId}
Deletes a single user from the datastore
Request: `localhost:3000/user/1`
Returns a 200 on completion

## Chat Room
#### GET /chat/room
Returns a chat room given a sender id and a recipient id <br/>

Request: `localhost:3000/chat/room?senderId=1&recipientId=2` <br/>
Response: `{
               "chatRoomId": 3,
               "senderUserId": 1,
               "recipientUserId": 2,
               "purpose": "No purpose set for this chat room"
           }`
           
#### POST /chat/room/{id}
Update the purpose for the given chat room <br/>

Request: `localhost:3000/chat/room?senderId=1&recipientId=2` <br/>
Response: `{
               "chatRoomId": 3,
               "senderUserId": null,
               "recipientUserId": null,
               "purpose": "The purpose is changed"
           }`    

## Messages
#### POST /messenger/send
Returns a chat room given a sender id and a recipient id <br/>
Message size must be between 1 and 255 characters <br/>
Chat Room Id must not be null <br/>
Sender Id must not be null <br/>
Recipient Id must not be null <br/>

Request: `localhost:3000/chat/room?senderId=1&recipientId=2` <br/>
Body: `{
           "message": "Hey! Hows it going?",
           "chatRoomId": 3,
           "senderId": 2,
           "recipientId": 1
       }` <br/>
200 Response on success <br/>
400 Response on request body failures
           
#### GET messenger/all
Get all messages within the last 30 days or by passing through a limit. Default behavior is to grab messages within the last 30 days when limit is not present <br/>
Optional request parameter limit
Request: `localhost:3000/messenger/all?limit=1` <br/>
Response: `[
               {
                   "id": 1,
                   "message": "Ahhh",
                   "chatRoomId": 13,
                   "timestamp": 1601939654221,
                   "senderId": 2,
                   "recipientId": 1
               },
               {
                   "id": 2,
                   "message": "Whats up",
                   "chatRoomId": 13,
                   "timestamp": 1601939661291,
                   "senderId": 2,
                   "recipientId": 1
               }
           ]` <br/>
200 on success <br/>
204 Response when no messages present

#### GET messenger/{userId}/all
Get all messages for a user within the last 30 days or by passing through a limit. Default behavior is to grab messages within the last 30 days when limit is not present. Does not include messages sent by the user <br/>
Optional request parameter limit
Request: `localhost:3000/messenger/all?limit=1` <br/>
Response: `[
               {
                   "id": 1,
                   "message": "Whats up",
                   "chatRoomId": 13,
                   "timestamp": 1601940124344,
                   "senderId": 2,
                   "recipientId": 1
               }
           ]` <br/>
200 on success <br/>           
204 Response when no messages present                                  
           


## Future Improvements
Possible features:
* Allow users to add each other to their friends list
  * Pull friends chats first when retrieving chat rooms 
* Add more user features such as permissions, allowing users to be moderators of a chat room
* More robust deletion
  * Delete all content when a user deletes their profile

Technical:
* Change timestamp of messages to not use system millis
  * Depending on the data center distribution, system millis might need to be "normalized" across data centers
* Dockerize the service
* If actually want a true chat service, would probably need to use sockets

## Design Explanation
### Flow
The API makes some assumptions about the flow of the calls from clients. 
To start, the user would need to be created before we start populating messages with
an id. Therefore, by the time we start saving messages, we assume the client has validated 
that the ids are valid. We also assume that the client has created a chat room with the other 
user at some point before. I decided to make a separate table that held the chat room and 
user association, in the hopes to be more flexible in the future. Doing so, I was hoping
that a chat room could hold multiple users. 
 

Thanks for your consideration



