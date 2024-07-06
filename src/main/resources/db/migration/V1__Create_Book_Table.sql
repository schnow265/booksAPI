CREATE TABLE IF NOT EXISTS public.book
(
    id                 SERIAL PRIMARY KEY,
    title              VARCHAR(255),
    author_name        TEXT,
    first_publish_year INT,
    cover_i            INT,
    has_fulltext       BOOLEAN,
    edition_count      INT,
    book_key           VARCHAR(255),
    ia                 TEXT[],
    author_key         TEXT[],
    public_scan_b      BOOLEAN
);


CREATE TABLE IF NOT EXISTS public.ia_ids
(
    book_id BIGINT       NOT NULL,
    ia_id   VARCHAR(255) NOT NULL,
    PRIMARY KEY (book_id, ia_id),
    CONSTRAINT fk_book_id FOREIGN KEY (book_id) REFERENCES public.book (id) ON DELETE CASCADE
);

ALTER TABLE public.ia_ids
    DROP CONSTRAINT ia_ids_pkey;

create table if not exists public.keys
(
    id  SERIAL PRIMARY KEY,
    key VARCHAR(255)
);