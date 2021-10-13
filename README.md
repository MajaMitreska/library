# library

Solution of the second homework of EMT.

TODO:
Make one module from an application for renting books from a certain one library, ie the module that would be used by a user with a role librarian, who would have several privileges:
- To add new books that can be rented
- Delete books that are no longer in good condition and will not be rented
- To modify a certain entry for a book
- To mark a certain book as rented

What you will need is a Spring Boot application, ie an API that you will create according to the relevant requirements, as well as the React application which will be in charge of displaying the data and user interaction. Additionally, in terms of the database, you can use PostgreSQL (as in auditory exercises), but you can also use MySQL and others.

The following book data is stored within the application: id (Long), name (String), category (enum), author (Author), availableCopies (Integer). The book category can be: NOVEL, THRILER, HISTORY, FANTASY, BIOGRAPHY, CLASSICS, DRAMA. For each author again store data: id (Long), name (String), surname (String), country (Country). They are for every country store data: id (Long), name (String), continent (String).

Appropriate controllers, services and repositories should be defined using layered architecture.

The React application should be the one that will make the appropriate requests to your API. Note that since both applications will be launched locally, you should enable it and avoid CORS error.

This application should look similar to the application of auditory exercises, that is:
  - All books are displayed on the home page (can be seen on the path / and /books)
  
    -- Edit, Delete, Mark As Taken buttons are provided for each book
    
    -- Click on the Edit / Delete buttons to call the API and to do so edit / delete the book
    
    -- Click the Mark As Taken button, you need to make a call to the API and reduce the number of Available Copies accordingly
    
    -- Additionally there is an Add a new book button, which allows adding of a new book
    
  - On the other side are displayed all the categories (can be seen on the path /categories)
  - There is a header section which is actually a navigation menu
  
For the React application, in addition to routing, you need to use the Hook at least once, example useState.
Additionally, pagination should be implemented for the display of books e.g. 5 books will be displayed on each page.
