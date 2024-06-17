# Usage

> This is intended to be used as a backend REST-API for a Website.

When you start this Application you can access the API interface on Port ``8080``.

## Finding Books by name

<tldr>
    <p>Open <shortcut>localhost:8080/search/books?name=[query here]</shortcut> in your Browser.</p>
</tldr>

Open your Browser and access the API on ``localhost:8080/search/books`` and add ``?name=`` with the name of the book you want to search for.

> Firefox allows you to type your URL **with spaces**, not needing you to replace a space with ``%20`` in your URL.

The Response will be saved in a PostgreSQL Database.

## Finding books from an Author

<tldr>
    <p>Open <shortcut>localhost:8080/search/writer?name=[query here]</shortcut> in your Browser.</p>
</tldr>

Currently only returns the ID from openlibrary.org (thanks archive.org for that one!).

## Force-Reloading the cache

<tldr>
    <p>Endpoint <shortcut>/refreshNext</shortcut> forces a clean of all matches from postgres.</p>
</tldr>

Sometimes we want to clean the Database and get a fresh result. Open ``/refreshNext`` in your browser, and the next query will be force-reloded.
