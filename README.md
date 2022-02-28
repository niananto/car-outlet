# Car Outlet

This project was made with Java and JavaFX. The idea is to have multiple car sellers and buyers in a marketplace where everyone can login independently and access the common marketplace simultaneously over a network. Yes you guessed it right, the main goal of this project was to implement a network system with a common server and multiple client while also learning some basic designs in JavaFX. It was Level 2 Term 2 in Buet and the course was 'CSE 108 : Object Oriented Programming Language Sessional'

## Setup Locally

First of all, do a `git clone` or download the zip. Then open this directory as an IntelliJ IDEA Project. If you don't have IntelliJ IDEA installed in your computer, do as I tell you.

INSTALL IT FFS. WHAT ELSE ARE YOU GOING TO DO!

Then it's pretty much all about some clicks here and there. Build the project. Run `/network/Server.java` first and then you can run multiple instances of `/fx/Main.java` to login as Manufacturer (seller) or Viewer (buyer).

Accepted usernames and passwords for login as a manufacturer -
```
admin - 1234
ananto - pass
boss - done
biplob - biplob
```

Accepted usernames and passwords for login as a viewer -
```
viewer - '' (null string, just hit enter)
```

You can hardcode more passwords for manufacturers in the `/network/ServerThread.java`.  
A list of car is maintained by a simple file `cars.txt`. You can simply hardcode more cars by changing this file. In a real life scenario, this can be done by the means of a database.