This repository contains my programming examples in Java.

The code was created in 2015 in course of my work on a Java programming project in a team of 3 people
at the Leibniz-Universität Hannover. The final application represented a household book which allowed
to create diverse user profiles, add already performed and planned for the future transactions, calculate
current and expected (after future transactions are executed) balance, produce diagrams, store the user
profile with its transaction history in a file and read it from there. The implementation followed the
OOP principles.

The files in this folder represent some of the basic classes within the application:

+ **UserProfile** - represents the user profile, contains the user name and information about all transactions that have been executed or planned, as well as 2 versions of the account balance: *current account balance* (account balance on the current day) and *total account balance* (account balance in which all transactions, including future ones, are taken into account).

+ **Transaction** - contains information about one specific incoming or outgoing transaction: date, description, amount, category.

+ **Editor** - contains *public static* methods *readIn(String fileName)* and *writeOut(UserProfile up)* which are used to save a user profile to and read it from an XML file.

Shield: [![CC BY-NC-SA 4.0][cc-by-nc-sa-shield]][cc-by-nc-sa]

This work is licensed under a
[Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License][cc-by-nc-sa].

[![CC BY-NC-SA 4.0][cc-by-nc-sa-image]][cc-by-nc-sa]

[cc-by-nc-sa]: http://creativecommons.org/licenses/by-nc-sa/4.0/
[cc-by-nc-sa-image]: https://licensebuttons.net/l/by-nc-sa/4.0/88x31.png
[cc-by-nc-sa-shield]: https://img.shields.io/badge/License-CC%20BY--NC--SA%204.0-lightgrey.svg
