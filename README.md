# Personalized Restaurant Review Application
#### by *Jingyuan Liu*

## The Inspiration of This Application
In today's digital age, with the prevalence of the Internet,
everyone is able to freely express their ideas online. However,
these opinions and reviews are not entirely reliable.
For example, in various online restaurant review platforms, 
such as **Uber Eats** and **Google Map**
sometimes can have reviews that are  markedly biased for a restaurant.
From a statistical perspective, this phenomenon can be
explained to what is called *response bias*. Those with
stronger opinions, whether positive or negative, 
tend to share their ideas more often.
This is why we see many extreme high and low reviews on 
restaurant review applications. If people have a middling 
experience at a restaurant, they may not be willing to devote time to writing
a meaningless review. But if someone is really satisfied 
or disappointed by a restaurant, they are more likely to 
share their reviews to let others know their experiences.
Besides that, sometimes an improper competition between 
restaurants can also lead to misleading and mendacious reviews.
Most importantly, whether people like a restaurant or not
is personal preference, can be very subjective, varying greatly from person to person; 
it is quite unfair to have a uniform rating system for
a restaurant for everyone.

To address the issue above, I decide to create a personalized 
restaurant review and rating application to empower users to save their
own reviews of restaurants and make them easily accessible for future reference when needed. 
This application is designed for those who have visited many 
restaurants, but still do not know if they should revisit a specific 
restaurant again after a long time. By saving the review of a restaurant
in this application, users can compile information such as the name, the rating of 
the restaurant on a scale of 5, the average cost in that restaurant, the relevant title or 
keywords for that restaurant, and whether they want to go again, etc.
The flexibility of this system makes it possible for users to store an arbitrary size of restaurants, you can always 
add a new restaurant to the list, remove a restaurant that is closed from the 
review list, and update the review information of a restaurant at any time.
So in the future, if you do not know which restaurant to dine with your friends,
you can always check back this application and look through those reviews and valuable insights
you had added, which might give you a good choice from your past dining experience.



## User Stories
- As a user, I want to be able to add a new `RestaurantReview` object to my `RestaurantReviewList`
and specify the name, rating on a scale of 5, the average cost, the keywords or relevant title of the 
new restaurant, and whether you want to visit it again.
- As a user, I want to be able to view a subset of restaurant reviews of my choice from 
the `RestaurantReviewList`. For example, I want to view all restaurants that I want to go again;
I want to view all restaurants that have a rating with at least some points.
- As a user, I want to be able to remove the review information of a closed restaurant
  from my `RestaurantReviewList`.
- As a user, I want to be able to update the review information of a restaurant after I revisit 
a specific restaurant.
- As a user, I want to be able to save my restaurant review list to file and have the option to do so or just quit.
- As a user, when I start the application, I want to be given the option to load my 
previously saved restaurant review list saved last time from file.
  

## Instructions for Grader