# whatflix-Greektrust-coding-challenge
A restful web service implementation of building a movie recommendation system using Apache Lucence. Apache Lucence is a high-performance, full-featured text search engine library written entirely in Java. Also we can use other search technologies stack like elastic search (very famous for distributed environment) and Apache solr to make the same. The project included  centralized cache mechanism for storing user preference data. Technology stack uses Jersey, Apache Lucence, Jackson, Google guava and so on. 

Build a Restful web service API which will accept a search string and userID and return unique movies in the order of preference for that user.

- [x] Build data model
- [x] Automate load Movie Documents to Apache Lucence Search
- [x] Build Apache Lucence Query
- [x] Convert them into recommendation api

 http://ipaddress:portno/api/movies/user/100/search?text=avatar,the lovers

Response: 
          [
            "alice in wonderland",
            "avatar",
            "the dark knight rises",
            "the lovers"
          ]

-------------------------------------------------------------------------------------------------------------------------------------------

Build a Restful web service API which will list out all the users and the top 3 recommended movies for each user based on their preferences.

http://ipaddress:portno/advanced-jaxrs-07-with-rxjava-whatflix-api/api/movies/users

Response:
[
    {
        "user": "100",
        "movies": [
            "avatar",
            "titanic"
        ]
    },
    {
        "user": "101",
        "movies": [
            "alice in wonderland",
            "avatar",
            "the dark knight rises"
        ]
    },
    {
        "user": "102",
        "movies": [
            "avatar"
        ]
    },
    {
        "user": "103",
        "movies": [
            "avatar"
        ]
    },
    {
        "user": "104",
        "movies": [
            "avatar"
        ]
    },
    {
        "user": "105",
        "movies": [
            "spectre"
        ]
    },
    {
        "user": "106",
        "movies": [
            "alice in wonderland",
            "avatar",
            "avengers: age of ultron"
        ]
    },
    {
        "user": "107",
        "movies": []
    }
]

For Further reference please visit : https://www.geektrust.in/api/pdf/open/ARCHPS1 
