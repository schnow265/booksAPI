CREATE TABLE IF NOT EXISTS book (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    author_name TEXT[],
    first_publish_year INT,
    cover_i INT,
    has_fulltext BOOLEAN,
    edition_count INT,
    key VARCHAR(255),
    ia TEXT[],
    author_key TEXT[],
    public_scan_b BOOLEAN
);
