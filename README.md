CS -- 360: Inventory Application - "LoginDemo"

John deLuccia

Southern New Hampshire University

**\
**

**Requirements, Goals, and User Needs:**

The overall purpose of this application is to serve as a secure
interface to a database of items stored in a warehouse. The app contains
a login screen which prompts new users to "register" or create a new
login. All the items which are entered into the inventory app by the
user are displayed on an inventory page using a list view. Being able to
quickly and clearly identify each item in the inventory, as well as
being able to increase or decrease the quantity of said item allows the
app to cater to the user's main goal. The user's main goal is to
efficiently manage a simple inventory database for items stored in a
warehouse. Beyond being able to view the inventory and change the
quantities, the user may also add or remove items from the list. There
is also an add item button on the same screen, allowing users to meet
their goals of adding, removing, increasing, and decreasing items in the
inventory all from one screen. The user will also be notified when the
quantity of an item on the list reaches 0 via text message should they
accept permissions.

**User Interface:**

The inventory application required a variety of screens to meet all user
needs and produce a user centered UI. The first screen is the main
activity, which serves as the login screen. From this page, the user can
access a sperate registration screen if they are new, a forgot password
screen to reset their password via their registered email, and the
inventory screen after they have logged in. The inventory screen is the
most important as it is where most of the user needs are met including
interacting with their inventory database. My designs were successful
because they focus on the user and their needs. The login screen is
simple and intuitive, and provides access to the other screen which the
user may need when logging in. The design also allows the user to
clearly manage their inventory by incorporating all features clearly.
The user does not have to think very hard to figure out how to use the
app. For example, to add an item the user simply clicks a plus sign icon
button after entering the details. This simplicity is what users want
with a simple inventory application like this one.

**Process:**

The process of coding this app was daunting at first as I still am a
very inexperienced programmer. However, I was able to complete the app
by working through many tutorials and practice problems early in the
course. I also spent a lot of time reading the Java documentation.
Between these two methods I was able to work out different portions step
by step. Rather than try and do the whole thing at once, I broke the
assignment up into smaller portions. First, I completed a very simple
application that did nothing but would run, then I worked on the UI for
the login screen, and immediately after added in functionality for the
Login UI. I also found it very helpful to use Git as much as possible
and create different branches between each finished step. This stepwise
process is what works best for me and I plan on using it in the future
both to continue working on this application and in new projects.

**Testing:**

Testing my code was crucial to my development of this project. I relied
heavily on both the debug console in Android Studio as well as the
Emulator. The debug console was very helpful in general, but it was most
helpful when I was getting errors and did not know why. Reading the
errors from the console gave me something to google when all as seemed
lost and allowed me to remedy many issues along the way. The Emulator
was crucial as it allows you to interact with the application as the
user would. This makes things like design flaws or errors become much
more apparent. It also gives you the mindset of the user, meaning things
that could be improved are much more apparent. At different stages
during development I experimented with different layouts, and a few
times I had noticed that the layouts did not look right at all when
actually run on the phone. I also noticed things like a need for bigger
or smaller font. The Emulator also allowed me to test and ensure all
different types of user input would work, and that the user could not
enter something out of the box which caused the app to crash. For me the
Emulator was the best way to fully test my application.

**Overcoming Challenges:**

When writing this application, I needed to be innovative all along the
way. This is more to do with my level of programming than the difficulty
of the application, but nonetheless it required innovation. I feel that
I had to be innovative in the way I met nearly every user goal, but the
one that stands out the most was the decision to use Firebase rather
than SQLite. I was very inexperienced with SQL and was having difficulty
early on. In doing research I became aware of Firebase, and the many
tools it offers. Firebase was simple to use but yet could do everything
I needed for the Inventory application. It was particularly useful in
being able to use the Firebase console to view the structure of my data
as I read and wrote to it during development. Discovering this tool to
meet my own needs allowed to put more focus in other areas while still
learning something new and useful.

**Areas of Success:**

I feel I was most successful in designing the login portion of the
application. Before this project I new very little about creating login
credentials and authenticating users, and nothing about how to do that
in an Android application. Having completed this project, I have
designed several login interfaces, and learned how to complete all
necessary tasks, including password reset and email verification. I also
was successful in creating a user profile screen with data relating to
the user. This was not necessary for the project, but I had done it
early on to learn and saw no reason to remove it. Learning how to
implement these two portions of the app was where I was the most
successful.

References

1.  DK, P. (Director). (2020). *Simple Login App Tutorial* \[Video
    file\]. Retrieved July/August, 2020, from
    https://www.youtube.com/watch?v=lF5m4o_CuNg&list=PLbte_tgDKVWQOCRIzkgEQ8umdn_S6ZnHr&index=1

2.  Google. (2020). Firebase Documentation. Retrieved July/August, 2020,
    from https://firebase.google.com/docs/reference/android/packages
