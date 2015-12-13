CREATE TABLE IF NOT EXISTS posts (
    postUuid uuid primary key,
    title text not null,
    content text,
    publishingDate date
);

CREATE TABLE IF NOT EXISTS comments (
    commentUuid uuid primary key,
    postUuid uuid references posts(postUuid),
    author text,
    content text,
    approved bool,
    submissionDate date
);

CREATE TABLE IF NOT EXISTS posts_categories (
    postUuid uuid references posts(postUuid),
    category text
);

-- GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO sa;