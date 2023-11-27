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
- As a user, I want to be able to remove the review information of a closed restaurant
from my `RestaurantReviewList`.
- As a user, I want to be able to view a subset of restaurant reviews of my choice from 
the `RestaurantReviewList`. For example, I want to view all restaurants that I want to go again;
I want to view all restaurants that have a rating with at least some points.
- As a user, I want to be able to update the review information of a restaurant after I revisit 
a specific restaurant.
- As a user, I want to be able to save my restaurant review list to file if I want to do so before I quit.
- As a user, when I start the application, I want to be able to load my 
previously saved restaurant review list saved last time from file if I want to do so when I start the application.
  

## Instructions for Grader
- You can locate my visual components of this interface in two places: the first one is when you start this 
application, there will be a splash image of a restaurant showing up on the screen for **5 seconds**, 
and then the interface window will appear and the splash image will be removed; the second place is the icon 
on the application window, even though the icon is small to see, but I did change it to a new icon image rather 
than the default icon given by IntelliJ.
- When the application starts (after the splash image disappears), you can choose to load the state of 
this restaurant review application from file by clicking the "Load Review From File!" button.
- When the application starts  (after the splash image disappears), no matter whether you choose to load
file or not, you can simply view the initial state of restaurant review information by clicking the "View All 
Restaurants Review!" button.
- You can generate the first required action related to the user story "adding multiple restaurant reviews to the
review list" by typing in the information of a restaurant review (name, rating score out of 5, average cost,
title, and whether you want to go again) in the corresponding textbox and then simply clicking the "Add This Restaurant
Review!" button. The rating input could be any real numbers in [0,5]. The average cost should be any 
non-negative whole number measured in $CAD. The "go again" textbox could be either "true" or "false". After clicking 
the add button, the "View" panel will automatically display all the restaurant review information
that have already been added to this application. You don't need to click the "View All Restaurants Review!" button
again.
- You can generate the second required action related to the user story "removing a restaurant review from the
review list" by typing in the name of the restaurant that you want to remove in the textbox and then simply clicking 
the "Remove Thi Restaurant Review!" button. After clicking the remove button, the "View" panel will automatically 
display all the restaurant review information that have already been added to this application and are still in 
this application. You don't need to click the "View All Restaurants Review!" button again.
- You can generate another required action related to the user story "filter and display all the restaurants 
you want to revisit from all restaurants you have added to this application" (view a subset of restaurant reviews) 
by clicking the "All Restaurants You Want To Revisit!" button. Then the screen will display the name of all restaurants 
you want to go again.
- Before you quit the application, you can save the state of the application to file by clicking the 
"Save Review To File!" button.
- Another feature of this application is that when you hang the mouse over the buttons or input textboxes, you will be 
able to see a tool tip for the component (e.g. What is this button doing? What should you type in for 
this textbox? etc.)

## Phase 4: Task 2
Here is an example of the event log when my program runs:\
\
Sun Nov 26 00:54:44 PST 2023\
All the restaurant review information from the restaurant review list was displayed 
(or automatically updated and displayed after adding or removing) in the APP!\
\
Sun Nov 26 00:54:48 PST 2023\
You viewed all the restaurants that you want to revisit from the restaurant review list!\
\
Sun Nov 26 00:54:55 PST 2023\
A restaurant was removed from the restaurant review list!\
\
Sun Nov 26 00:54:55 PST 2023\
All the restaurant review information from the restaurant review list was displayed 
(or automatically updated and displayed after adding or removing) in the APP!\
\
Sun Nov 26 00:55:14 PST 2023\
A new restaurant was added to the restaurant review list!\
\
Sun Nov 26 00:55:14 PST 2023\
All the restaurant review information from the restaurant review list was displayed 
(or automatically updated and displayed after adding or removing) in the APP!\
\
Sun Nov 26 00:55:15 PST 2023\
You viewed all the restaurants that you want to revisit from the restaurant review list!

## Phase 4: Task 3
The UML class diagram of my program looks clear and easy to follow, but it is still necessary
to improve the design of my program by making it more cohesive and reducing the coupling 
between classes. 

Most classes in the model package are very cohesive. The RestaurantReview and RestaurantReviewList
classes mainly focus on their own  responsibility; the only place in the model package that can 
improve the cohesion of the design is where I can make the RestaurantReview class abstract and
let the two restaurant review classes (the restaurant that you want to revisit & the restaurant 
that you don't want to go again) extend the abstract RestaurantReview class. The reason is that
in my program, we need to filter all restaurants we want to go again and the printed message for 
this two types of restaurants are different, which makes it helpful to separate them into two distinct
subclasses. And the part that is the least cohesive is the GUI (RestaurantReviewAPPGUI) class.
There are many private classes in my GUI class, which constructs the panel related to each action
(i.e. Add, Remove, Load, etc). This is significantly against Single Responsibility Principle. I 
think it would be easy to refactor it just by taking those private panel classes out from the main
GUI class so that each panel class is associated with only one particular action and focus on its 
own responsibility.

Both the Moderate Coupling and Semantic Coupling are possible in this design. I believe most of 
classes relations associated to the Json classes and the Event or EventLog class are working fine,
but the two user interface classes (GUI and console-based) are highly dependent on the 
RestaurantReviewList class which itself depends on the RestaurantReview class. Therefore, any 
changes in the RestaurantReview class will trigger bugs or even errors in the RestaurantReviewList
class and the two user interface classes. One of the possible ways to deal with it is to remove 
the association arrows from the user interface classes to the RestaurantReviewList class, and make
the two user interface classes directly include a collection of RestaurantReview as a field.