# Library Management Console Application

Welcome to the Library Management Console Application! This project is designed to manage a library system with distinct user roles: Admins, Employees, and Members. The application provides a range of functionalities catering to each user's specific needs.

## How to Use

To get started:

1. Ensure to run the JSON server before using the application: Open the terminal in the `jsonServer` folder and run the command: `npx json-server --watch db.json`.
2. Update the port configuration in the `config.ini` file if it hasn't been set to match the active port, which defaults to 3000.
3. And you are good to go!

### Testing

Use the provided accounts for testing different functionalities of the application. Feel free to manually modify the `db.json` file to simulate various scenarios. Here are some test accounts:

- **Admin:**
  - Email: shellytalley@plasmox.com
  - Password: incididuntincididunt

- **Employee:**
  - Email: maxwellthornton@frosnex.com
  - Password: animirure

- **Member:**
  - Email: holtpickett@accupharm.com
  - Password: laboreduis

## Project Overview

This console application aims to manage library operations efficiently. Users, based on their roles, can perform various tasks like viewing available books, managing accounts, handling subscriptions, and tracking bookings.

## Features

- **All Users:**
  - View available books with a search option.
  - Manage account details like name, password, institution, etc.
  - Access upcoming events and events history.

- **Members:**
  - View active bookings and booking history.

- **Employees:**
  - View public data of all users.
  - Handle subscriptions, payment registration.
  - Add new user accounts (limited to members), manage bookings and returns.

- **Admins:**
  - View and modify system data including fees.
  - Create events, manage accounts, modify passwords.

## Project Structure

### Models:
Chose to define Models as classes that don't have a logic on their own. They are structures that represent entities from the database. Any logic or treatment on these objects is implemented in other classes like Managers. Some attributes don't even have setters since every modification has to pass through the database first.

### Managers:
Managers are classes that handle the logic behind Models, since Models are only structures. They are also the only classes that communicate with services (services are sometimes called directly from other classes in this project, this detail is corrected in the next project).

### Services:
Services are the classes that communicate with the database JSON server.

### Helpers:
Helpers are classes with static methods only that provide functionalities to all parts of the application. Methods that would otherwise be defined in multiple classes resulting in redundant code.


## Development Choices

  I aimed to utilize various Object-Oriented Programming (OOP) concepts covered in our class, adapting them to the project's unique requirements, even though it's not a typical project setup.

- **JSON-Server:**
  The choice to employ a JSON server as a backend stemmed from the need to create a database-like structure. It's a swift solution as the server handles most logic, eliminating the need for extensive database management. Additionally, its setup is more user-friendly for anyone attempting to use the application.

- **Enum for UserType:**
  Utilized an enum for UserType to simplify database manipulation instead of defining different classes for each UserType by extending the User Class.

- **BarcodeScannerSimulator:**
  Created the BarcodeScannerSimulator class to simulate the scanning of barcodes for books or user IDs, replicating real library scenarios.

- **QueryParamsBuilder:**
  Developed a QueryParamsBuilder to facilitate HTTPS request creation.

- **API_URLs Handling:**
  Opted to define API_URLs in the DatabaseService parent class instead of separate subclasses, facilitating easier manipulation and grouping constants together.

- **Filter Functionality:**
  Implemented a filter function in some GET requests due to limitations in checking for null or applying conditions in query parameters for the JSON server.

- **LoremIpsumGenerator:**
  Utilized the LoremIpsumGenerator class to generate book descriptions within the application.

- **Testing Solution:**
  A test runs upon application startup to verify the JSON server's status. Created a unique test endpoint named "libraryProjectTestEndPoint" to identify my server, preventing conflicts on the specified port. I'm sure there are cleaner solutions, but this is the one I came up with!

## Page System Overview

The Page System within this project manages the user interface flow by organizing pages using a stack structure. Each time a user navigates to a new page, it's added to the top of the stack. This stack-based approach allows for easy navigation between pages and maintains a history of visited pages.

### Action Menu Integration:

The ActionMenu class is responsible for managing multiple-choice menus within the application. It accepts an array of actions to display and prompts the user for input (the index of the desired action) to execute the corresponding action. This system is extensively used across various pages within the application.

  - The ActionMenu also includes a closing action, often used to return to the previous page. This functionality simplifies navigation, especially for pages not calling other pages directly.
    
  - The abstract BackOnlyPage class utilizes the ActionMenu by automatically presenting a minimal menu with just the closing action. This kind of page is useful for displaying information without invoking other pages and simplifies the process of returning to the previous page.



## Challenges Faced

Working on this project took a lot of time, but I found it enjoyable. I didn't face any big problems, and when challenges popped up, I solved them by researching simple solutions.

## Conclusion

This project was a valuable learning experience, covering aspects like GitHub commands, JSON server usage, and project structuring.
However, I won't be continuing work on this project as there are a few secondary features left unfinished. These features, although similar to existing ones, require substantial time to implement. Due to upcoming exams, I need to prioritize my studies over further development of this project
