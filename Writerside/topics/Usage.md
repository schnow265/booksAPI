# Usage

> This is intended to be used as a backend REST-API for a Website.

When you start this Application you can access the API interface on Port ``8080``.

## Finding Books by name

<tldr>
    <p>Open <shortcut>localhost:8080/search?name=[query here]</shortcut> in your Browser.</p>
    
</tldr>

Open your Browser and access the API on ``localhost:8080/search`` and add ``?name=`` with the name of the book you want to search for.

> Firefox allows you to type your URL **with spaces**, not needing you to replace a space with ``%20`` in your URL.

The Response will be saved in a PostgreSQL Database.