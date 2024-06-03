-- Users Table
CREATE TABLE users (
                       user_id SERIAL PRIMARY KEY,
                       username VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL -- Possible values: 'ADMIN', 'ELECTOR'
);

-- Candidates Table
CREATE TABLE candidates (
                            candidate_id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            party VARCHAR(255),
                            biography TEXT,
                            achievements TEXT,
                            key_issues TEXT
);

-- Polls Table
CREATE TABLE polls (
                       poll_id SERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       description TEXT,
                       start_date TIMESTAMP NOT NULL,
                       end_date TIMESTAMP NOT NULL,
                       visibility BOOLEAN NOT NULL
);

-- Questions Table
CREATE TABLE questions (
                           question_id SERIAL PRIMARY KEY,
                           poll_id INT NOT NULL,
                           content TEXT NOT NULL,
                           FOREIGN KEY (poll_id) REFERENCES polls(poll_id) ON DELETE CASCADE
);

-- Answers Table
CREATE TABLE answers (
                         answer_id SERIAL PRIMARY KEY,
                         question_id INT NOT NULL,
                         content TEXT NOT NULL,
                         FOREIGN KEY (question_id) REFERENCES questions(question_id) ON DELETE CASCADE
);

-- Responses Table
CREATE TABLE responses (
                           response_id SERIAL PRIMARY KEY,
                           answer_id INT NOT NULL,
                           user_id INT NOT NULL,
                           timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           FOREIGN KEY (answer_id) REFERENCES answers(answer_id) ON DELETE CASCADE,
                           FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- CandidatePolls Table (Association between Candidates and Polls)
CREATE TABLE candidate_polls (
                                 candidate_poll_id SERIAL PRIMARY KEY,
                                 candidate_id INT NOT NULL,
                                 poll_id INT NOT NULL,
                                 FOREIGN KEY (candidate_id) REFERENCES candidates(candidate_id) ON DELETE CASCADE,
                                 FOREIGN KEY (poll_id) REFERENCES polls(poll_id) ON DELETE CASCADE
);

-- Indexes for faster search
CREATE INDEX idx_candidates_name_party ON candidates (name, party);
CREATE INDEX idx_questions_poll_id ON questions (poll_id);
CREATE INDEX idx_answers_question_id ON answers (question_id);
CREATE INDEX idx_responses_answer_id_user_id ON responses (answer_id, user_id);
