Choice of algorithm for Shortest path

Friendship are created with default weight of "1". Since we do not have any functionality to update the weight between friends in the network , both distance and communities are done as "Unweighted".

Breadth First Search algorithm is used for calculating distance between users The Big O notation will be sum of number vertices(users) and number edges( friendships). If it is weighted network could have used algorithm like Dijkstra and be more efficient.
For community selection Louvian algorithm is used and weights are not considered.


Test coverage:

The complexity of the algorithms were tested with Junit and services are tested from postman. Postman collection is added in the resources folder in test.

Database:

MongoDb (free version) is used with a database "socialnetwork" and collections "users" and "friendship". I have included the picture of the collections in the resources folder in test.

Authentication and Authorisation.

Due to time constrain Basic authentication is used with userid and password.
References:
1. socialnetwork.postman_collection.json
2.friendship.png
3.users.png   
