My name is Yaroslav and I am one of the Senior Software Engineers here at DKatalis.

First of all, thank you for taking the time to speak to us about our software engineering opportunities. Based on your profile we would like to invite you to take part in our interview process.

The first stage is a take-home challenge (details below). Once you submit your solution we will review in 2-3 days. If successful we will then invite you for a combined tech-chat / pair-programming interview which will last around 2 hours. If this is positive again we will invite you to become part of our journey.

Best of luck with the challenge. Note that the deadline is next Tuesday (9am) and we anticipate that it should take around 4 hours but feel free to take as much time as you need to produce a polished solution that you would be happy to release into production.

 

Nature of the Game
We want to understand how you think as a software engineer and the level of craft you bring to bear when building software.

Of course, the ideal would be a real-world problem with real scale, but that isn’t practical as it would take too much time. So instead, we have a dead-simple, high-school level problem that we want you to solve as though it was a real-world problem.
Please note that not following the instructions below will result in an automatic rejection.

Please take your time to go through it and build a solution that you can be proud to discuss and show to other engineers. We would prefer you take enough time to produce your best result than to rush to submit something with lesser quality.

 

Rules of the Game
You are expected to choose one of the Problem Statements attached and come up with a solution for it.
Please complete the exercise using any programming language in Java or JavaScript derivatives that you are most comfortable with
Your application must run and please include instruction manual for operation
If there are special cases or ambiguity, please take your own decision on how it should be handled and explain your decision
If you made any assumptions and/or deviations from the problem statement, please detail your reasoning
We hope you will be able to submit by Tuesday morning, but in case you need more time please don’t hesitate to contact us. We’d rather you, don’t rush but don’t tarry
Once you are done, please send us a Git archive as attachment
Please don’t include binary files and/or executable files
Please don’t push into any public code repositories
You are allowed to use any library, but you should not use any library that outright solves the problem
You should aim to implement to a level where you would be proud to have other engineers look and review your result
All parts of your submission will be assessed including but not limited to:
Design choices (maintainability, legibility, refactorability, extensibility)
Implementation technique (including tests)
Exception handling and special case handling
If you have any questions, please feel free to contact us.


===>

We appreciate you for taking the time to participate in our hiring process and your interest in our company and the job. 

However, after much consideration we regret to inform you that we will not be pursuing your candidacy for this position. A few notes from our reviewers:

Pros:
 - Detailed instructions in the README file
 - Solution is working

Cons:
 - Corner cases handling could be done better: no negative number sanitization (app crashes on the attempt to create a parking lot with negative capacity; it's possible to leave with the negative amount of hours), it's possible to issue a new create command losing the state in the middle of the operation
 - Strange way to implement integration tests, not really sure why not to include them as JUnit tests; overall not much tests is presented in the solution
 - Questionable design choices: all files are in single package and one class handles both the commands parsing and execution, which can affect the extensibility of the solution; not really clear why to use enums for service\utility classes (just for the sake of singleton instance?)


Nevertheless, we will still be keeping your record and contact you should there be a position matching with your profile in the future. Please do keep an eye on our website and/or follow our LinkedIn as we’re always updating it with new positions available.

Thanks again for your interest in DKatalis and we wish you luck in your search.

===>

This is why I didn't want to take part in such "rat races" and told Olga about that. I just expected this "some new special case", but was completely disappointed.
I understand that your evaluation is just one or several people's opinions, which are just people with it's personal goals and level of expertise.

About your cons:
1) "Corner cases handling could be done better: no negative number sanitization"
a) app crashes on the attempt to create a parking lot with negative capacity
Exception in thread "main" java.lang.IllegalArgumentException - this is difficult to find more responsive exception, that argument stupid and opposite to best practices
b) it's possible to leave with the negative amount of hours
0 or "negative amount of hours" - no charge, this is pretty simple assumption
c) it's possible to issue a new create command losing the state in the middle of the operation
noone if you owner of parking lot can prohibit you to close it and and open absolutely new from the begin state

2) "Strange way to implement integration tests, not really sure why not to include them as JUnit tests; overall not much tests is presented in the solution"
If something strange to you, this is a reason to familiarize with good coding practices, there are so many number of tests to get workable solution and the way, which was provided to verify solution, is easy to use for this particular case.

3) "Questionable design choices:"
a) "all files are in single package" 
There are 7 files, which intentions and names as sharp as possible, it is necessary to be idiot to separate these to addition packages
b) "one class handles both the commands parsing and execution"
" CommandInterpreter  " responsible to parse command
" ParkingLotControlPoint  " responsible to execute the command

All classes in the application:
App - start point for main;
ParkingLotControlPoint - control point to execute command;
CommandInterpreter - parse string command to ParkingLotCommand object;
ParkingAction - actions with description for Parking Lot;
ParkingLotCharge - for charge calculation;
ParkingLotCommand - command object;
ParkingLotCmdException - redefinition of exception class specific for this application;

c) "not really clear why to use enums for service\utility classes (just for the sake of singleton instance?)"
this is easy-peasy link for "scholars" who does not familiar with best practices and was surprised to see these: https://www.baeldung.com/java-singleton 

Absolutely stupid arguments, from the point, if you try to search something negative - you definitely find it.
Can you post a reference solution? To compare, just for fun.

To be positive, Yaroslav, my best wishes to you, rest in peace.
