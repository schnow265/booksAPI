# Usage

> This is intended to be used as a backend REST-API for a Website.

When you start this Application you can access the API interface on Port ``8080``.

## Searching

### Finding Books by name

<tldr>
    <p>Open <shortcut>localhost:8080/search/books?name=[query here]</shortcut> in your Browser.</p>
</tldr>

Open your Browser and access the API on ``localhost:8080/search/books`` and add ``?name=`` with the name of the book you want to search for.

> Firefox allows you to type your URL **with spaces**, not needing you to replace a space with ``%20`` in your URL.

The Response will be saved in a PostgreSQL Database.

### Finding books from an Author

<tldr>
    <p>Open <shortcut>localhost:8080/search/writer?name=[query here]</shortcut> in your Browser.</p>
</tldr>

Currently only returns the ID from openlibrary.org (thanks archive.org for that one!).

### Force-Reloading the cache

<tldr>
    <p>Endpoint <shortcut>/search/refreshNext</shortcut> forces a clean of all matches from postgres.</p>
</tldr>

Sometimes we want to clean the Database and get a fresh result. Open ``/refreshNext`` in your browser, and the next query will be force-reloded.

## Authentication

<warning>
    <p>This is not yet implemented.</p>
</warning>

### User Authentication

<tldr>
    <p>Endpoint <shortcut>/users/authenticate</shortcut> authenticates you</p>
</tldr>

## User Management

<warning>
    <p>This is not yet implemented.</p>
</warning>

<note>
    <p>Every Endpoint here will require Authentication</p>
</note>

### Add book to "Currently Reading"

<tldr>
    <p>Endpoint <shortcut>/users/addBook?name=[name here]</shortcut></p>
    <p>Returns the BookID. May be stored in local cache in the Browser.</p>
</tldr>

### Get Book ID

<tldr>
    <p>Endpoint <shortcut>/users/getID?user=[username]&bookName=[book name]</shortcut></p>
</tldr>

Gets the ID, if you didn't save it.

<warning>
    <p>May return multiple books.</p>
</warning>

### Set current page

<tldr>
    <p>Endpoint <shortcut>/users/updateBook?id=[id from other function]&page=[current page]</shortcut> to set the page</p>
</tldr>

<note>
    <p>Needs the Value from <shortcut>Get Book ID</shortcut> to complete.</p>
</note>

## Statistics

<warning>
    <p>This is not yet implemented.</p>
</warning>

May be called with other arguments:

| Argument  | Description                                                                     |
|-----------|---------------------------------------------------------------------------------|
| ``?user`` | Only show statistics for the user. May be used multiple times.                  |
| ``?book`` | Only search for statistics with the selected Books. May be used multiple times. |


### Most Pages

<tldr>
    <p>Endpoint <shortcut>/stats/mostPages</shortcut> returns JSON with the leaderboard</p>
</tldr>

### Most Books

<tldr>
    <p>Endpoint <shortcut>/stats/mostBooks</shortcut> returns JSON with the leaderboard</p>
</tldr>

### Reading Time

<tldr>
    <p>Endpoint <shortcut>/stats/readingTime?user=[user in question]</shortcut> returns JSON with Start, End and Last Update dates.</p>
    <p>If not completed, will return null in End Date.</p>
</tldr>