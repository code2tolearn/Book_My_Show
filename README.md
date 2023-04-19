# Book_My_Show
Built a Book My show Application where admin can add Show, Theater, Movie, User can Book Ticket  and can cancel Ticket..


# Technology Used 

Java

Spring Boot

Hibernate

RESTful APIS

Maven

My SQL

Postman 

# Functionality
User

    • Add User

Theater 

    • Add Theater 
    
Movies 

    • Add Movies 
    
Shows 

    • Add Shows
    
Tickets

• Book Tickets 

      ○ Send email notification to the user
  
• Cancel Tickets

     ○ Send email notification to the user 
     
# Postman Commond     

    To Add User:
    
localhost:8080/user/add

{
  "name": "String",
  "email": "String",
  "address": "String",
  "mobileNumber": "String",
  "age" : int
} 

      To Add Theater: 
      
localhost:8080/theater/add

{
  "name": "String",
  "location": "String",
  "classicSeatsCount": int,
  "premiumSeatsCount": int
} 

         To Add Movies:
         
localhost:8080/movies/add

{
  "movieName": "String",
  "genre": "ENUM Type",
  "language": "ENUM Type",
  "rating": double,
  "duration": int
} 

      To Add Shows:
      
localhost:8080/shows/add

{
  "movieId":int,
  "theaterId": int,
  "classSeatPrice": int,
  "premiumSeatPrice": int,
  "showType": "ENUM Type",
  "showTime": "hh:mm:ss",
  "showDate":"yyyy-mm-dd"
} 

    To Book Ticket: 
    
localhost:8080/tickets/book

{
  "showId": int,
  "requestedSeats": ["String", "String", "..."],
  "userId": int
}

    To Cancel Ticket: 
    
 localhost:8080/tickets/cancel-ticket

{
  "ticketId" : int,
  "deleteTicketList":["String", "String", "..."]
}
