# E-Commerce Console Application

Welcome to the E-Commerce Console Application! This project is designed to simulate an e-commerce platform with various functionalities for different user roles: Admins and Customers. The application offers a range of features to manage products, user accounts, shopping carts, orders, and more.

## How to Use

To get started:

1. Run the JSON server before using the application: Open the terminal in the `jsonServer` folder and run the command: `npx json-server --watch db.json`.
2. Update the port configuration in the `config.ini` file if it hasn't been set to match the active port, which defaults to 3000.
3. Run the main method in the Main class of your preferred Java IDE or command line interface.
4. And you are good to go!

### Testing

Use the provided accounts or create your own for testing different functionalities of the application. Feel free to manually modify the `db.json` file to simulate various scenarios. Here are some test accounts:

- **Admin:**
  - Email: shellytalley@plasmox.com
  - Password: incididuntincididunt

- **Customer:**
  - Email: holtpickett@accupharm.com
  - Password: laboreduis

Refer to the [db.json](jsonServer/db.json) file for more accounts.  
For further information on testing the project, check the [notes](notes.txt)  file.

## Project Overview

The E-Commerce Console Application is a comprehensive system built to emulate an online shopping platform, offering an array of features.

## Features

- **Members:**
  - View available products with a search option.
  - View and manage shopping carts and place orders.
  - shipping methods: normal delivery, free delivery for purchases over a fixed amount and extra fees for express delivery
  - process payments using a credit card.(not real payments, just a simulation
  - View purchase history.
  - add/remove products to/from wishlist.
  - add reviews to products.
  - Manage account details like name, password, institution, etc.

- **Admins:**
  - Manage products: view, add, remove, and modify.
  - register stock for products.
  - recover user accounts. (by resetting their password to a default `0000`)
  - View and modify system data.


**Settings:**
  - each user can customize the application to their liking by changing their settings. The settings are saved in the database with the user account and are loaded automatically when the user logs in. (the only user setting implemented in this project is the date format)
  - the application also has a global setting stored in the database (like default date format and fees) that can be changed by admins (settings modification is not implemented in this project). the global settings are loaded automatically on first use.    


## Project Structure

### Models:
Chose to define [Models](diagrams/Models1.png) as classes that don't have a logic on their own. They are structures that represent entities from the database. Any logic or treatment on these objects is implemented in other classes like Managers. Some attributes don't even have setters since every modification has to pass through the database first.

### Managers:
[Managers](diagrams/Managers.png) are classes that handle the logic behind Models, since Models are only structures. They are also the classes that communicate with services.

### Services:
[Services](diagrams/Services.png) are the only classes that communicate with the database JSON server.

### Helpers:
[Helpers](diagrams/Helpers.png) are classes with static methods only that provide functionalities to all parts of the application. Methods that would otherwise be defined in multiple classes resulting in redundant code.

Class diagrams are available in the [diagrams](diagrams) folder.
## Development Choices

  I aimed to utilize various Object-Oriented Programming (OOP) concepts covered in our class, adapting them to the project's unique requirements, even though it's not a typical project setup.
  Additionally, I tried to keep the code as clean as possible, following the best practices I've learned so far.

  I reused some of the code of my previous project (Library Management System) especially for the projects overall structure and the Page System since it was a similar project. However, I improved it a lot and added new features and more flexibility. 

### JSON Server and related features:
  The choice to employ a JSON server as a backend stemmed from the need to create a database-like structure. It's a swift solution as the server handles most logic, eliminating the need for extensive database management. Additionally, its setup is more user-friendly for anyone attempting to use the application.


- **Testing Json-server:**  
A test runs upon application startup to verify the JSON server's status. Created a unique test endpoint named "ECommerceProjectTestEndPoint" to identify my server, preventing conflicts on the specified port. I'm sure there are cleaner solutions, but this is the one I came up with!


- **Type Adapters:**   
  I used [Type Adapters](src/adapters) to convert JSON objects to Java objects and vice-versa. This approach is more flexible than using the Gson library's default implementation, allowing for more control over the conversion process.


- **GsonInstance:**  
  I created a [GsonInstance](src/adapters/GsonInstance.java) class holding a static preconfigured Gson object to avoid creating multiple Gson objects throughout the application and configuring them every time. This approach is more efficient and allows for easier manipulation of the Gson object.

  
- **Proxy:**  
  I used a [Proxy](src/adapters/Proxy.java) class to handle objects with foreign keys in the database. This class is responsible for fetching the foreign object from the database on first use. This approach is more efficient than fetching the foreign object every time it's needed.


- **QueryParamsBuilder:**  
  Developed a [QueryParamsBuilder](src/helpers/QueryParamsBuilder.java) to facilitate HTTPS request creation.


- **API_URLs Handling:**  
  Opted to define API_URLs in the DatabaseService parent class instead of separate subclasses, facilitating easier manipulation and grouping constants together.


- **Filtered https requests:**  
  Simple filters are implemented by a simple query parameter in the URL. However, due to limitations in checking for null or applying conditions in query parameters for the JSON server, more complex filters are implemented by fetching all objects and filtering them locally. This approach is less efficient, but it's the only way to implement more complex filters.


- **Null values in JSON:**   
  I chose wrapper classes over primitive types to support null values for non essential fields


- **Data generation:**  
  I used various methods to create the JSON database, focusing on the products section. I started with the base structure from [DummyJson](https://dummyjson.com/docs/products) and expanded it by including extra details for specific categories using chatGPT. After that, I fine-tuned the values manually to align the database with my project's needs.  
Additionally, I employed a json-Generator tool available online at https://json-generator.com/. Some data, such as serial numbers, was generated by the application itself, while other information was created using specific scripts I developed.


### Console UI:
  The console UI is designed to be as user-friendly as possible, with a simple and intuitive interface. 
- **Page System:**   
see [Page System Overview](#page-system-overview) section.


- **ConsoleHelper:**  
    The [ConsoleHelper](src/helpers/ConsoleHelper.java) class is responsible for handling all console-related operations, including input and output. 


- **ConsoleColors:**  
    The [ConsoleColors](src/helpers/ConsoleColors.java) class holds constants for various console colors, for a more colorful and visually appealing user interface.


- **TableLayout:**  
    The [TableLayout](src/helpers/TableLayout.java) class is responsible for displaying data in a table format. It accepts a list of columns and a list of rows to display. The TableLayout class automatically adjusts the column width to fit the data and displays the table in a visually appealing format.

### Page System Overview:

The Page System within this project manages the user interface flow by organizing pages using a stack structure. Each time a user navigates to a new page, it's added to the top of the stack. This stack-based approach allows for easy navigation between pages and maintains a history of visited pages.

- **Action Menu Integration:**    
The [ActionMenu](src/helpers/ActionMenu.java) class is responsible for managing multiple-choice menus within the application. It accepts an array of actions to display and prompts the user for input (the index of the desired action) to execute the corresponding action. This system is extensively used across various pages within the application.
  - The ActionMenu also includes a closing action, often used to return to the previous page. This functionality simplifies navigation, especially for pages not calling other pages directly.
  
  - The abstract BackOnlyPage class utilizes the ActionMenu by automatically presenting a minimal menu with just the closing action. This kind of page is useful for displaying information without invoking other pages and simplifies the process of returning to the previous page.


- **Access Levels:**   
Each page has an access level that determines which users can access it. The access level is checked upon page creation, and if the user doesn't have the required access level (a scenario that shouldn't occur), they are redirected to the previous page. This approach is useful for restricting access to pages based on user roles.

### Authentication System:
- **email validation:**  
  The email validation is done by a regex pattern. The pattern is not perfect, but it's good enough for this project.
  The email is stored in the database in lowercase to avoid case sensitivity issues.


- **Password encryption:**  
    Passwords are encrypted using an encryption algorithm to prevent unauthorized access to user accounts. The encrypted password is stored in the database instead of the original password. For simplicity, the crypt function just returns the password as is to simplify testing, but in a real-world scenario, it would encrypt the password. 


- **CAPTCHA System:**  
  A [CAPTCHA system](src/helpers/CaptchaTest.java) is implemented to prevent brute force attacks on the login page and to prevent bots from purchasing products. The CAPTCHA Test requires the user to solve a simple math problem to proceed.

### Products:
  - **Product Categories:**  
Products are saved in the database in the same table, but they are separated into categories and each category has a different set of attributes. The category is saved in the database as a string.
The [ProductAdapter](src/adapters/ProductAdapter.java) uses the category attribute to determine the correct class to parse the product to.


  - **Product Stock:**  
    Products have a quantity attribute that is updated when a product is purchased.


  - **Serial Numbers**  
    For products that support serial numbers, each instance of the product has a unique serial number. The serial number is generated automatically when a new stock is registered for a product. 


  - **Product Reviews:**  
    Products can have reviews. Each review has a rating and a comment. The rating is an integer between 1 and 5.


  - **Product Wishlist:**  
    Users can add products to their wishlist. The wishlist is saved in the database as a list of product IDs.


  - **Other Product Attributes:**  
    Products can have another set of custom attributes regardless of their category. These attributes are represented in the database as a JSON object and parsed to a HashMap in the application.

  
## Conclusion

This project was a valuable learning experience, covering aspects like GitHub commands, JSON server usage, and project structuring.
I have numerous original ideas that I'm enthusiastic about implementing in this project. Due to upcoming exams, I need to prioritize my studies over further development of this project

