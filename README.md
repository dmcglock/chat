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
           
#### GET /anagrams/check
Endpoint that takes a set of words and returns whether or not they are all anagrams of each other <br/>
Request: `localhost:3000/anagrams/check?words=tea, ate` <br/>
Response: `{
               "words": [
                   "tea",
                   "ate"
               ],
               "anagrams": true
           }`
#### GET /anagrams/check
Endpoint to return all anagram groups of size >= *x* <br/>
Request: `localhost:3000/anagrams/groups?size=3` <br/>
Response: `{
               "adekn": [
                   "kande",
                   "knead",
                   "naked"
               ],
               "addeeglnr": [
                   "gladdener",
                   "glandered",
                   "regladden"
               ]
            }`
#### DELETE /anagrams/read
Endpoint to delete a word *and all of its anagrams <br/>
Request: `localhost:3000/anagrams/read` <br/>
Response: 204 No content


## Future
Possible features for the future might include:
* Allow words endpoint to take in sentences and check if sentences are anagrams
* Delete all anagrams of size x

Thanks for your consideration



