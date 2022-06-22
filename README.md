# movies_review_app
This is a movies review application created with java and spring boot framework.
This poject was created during my second year at linnaeus university, where I study my bachelor's degree in computer science.
There's allot of stuff you can do here. You can subscribe to a movie and get the lates review of that movie sent to you and much more!

### Instructions for movies reviews api.

Public URLs:
Auth api:
Urls:

POST https://mystudauthservice.herokuapp.com/api/v1/auth/authenticate : Authenticates user.

json
{
  "username": "username",
  "password": "password"
}


POST https://mystudauthservice.herokuapp.com/api/v1/auth/register : Register a new user.

json
{
  "username": "username",
  "password": "password"
}


 Movies api:

 Urls:

GET https://mymoviesreviewsapi.herokuapp.com/api/v1/movies : Returns all movies in the DB.

POST https://mymoviesreviewsapi.herokuapp.com/api/v1/movies : Creates a new movie.

json
{
  "title": "Harry potter",
  "category": "fantasi"
}


GET https://mymoviesreviewsapi.herokuapp.com/api/v1/movies/movie?title=fantastic four : Returns a movie with the title if found or 404 if not.

GET https://mymoviesreviewsapi.herokuapp.com/api/v1/movies/movie/62125be00c35237494342a67 : Returns a movie with the id if found or 404 if not.

GET https://mymoviesreviewsapi.herokuapp.com/api/v1/movies/category/crime : Returns movies with the given category if found or 404 if not.

GET https://mymoviesreviewsapi.herokuapp.com/api/v1/movies/movie/620f69312bcd613b84629907/reviews : Returns reviews for a movie.

POST https://mymoviesreviewsapi.herokuapp.com/api/v1/movies/movie/620f69312bcd613b84629907/reviews : Adds a review to a movie.

json
{
  "review": "This was a good movie."
}


PUT https://mymoviesreviewsapi.herokuapp.com/api/v1/movies/movie/620f69312bcd613b84629907/reviews : Updates the review, you created, for the movie.

json
{
  "review": "This is just an update of my review."
}


DELETE https://mymoviesreviewsapi.herokuapp.com/api/v1/movies/movie/620f69312bcd613b84629907/reviews : Removes your review from a movie.

Reviews hook:

Urls:

POST https://mymoviesreviewsapi.herokuapp.com/api/v1/webhooks/movie/620f69312bcd613b84629907 : Registers a webhook for a movie that will triger ever time a review is added to a movie.

json
{
    "hookUrl" : "your hook url"
}


DELETE https://mymoviesreviewsapi.herokuapp.com/api/v1/webhooks/movie/620f69312bcd613b84629907 : Removes you as a subscriber of a movie.
