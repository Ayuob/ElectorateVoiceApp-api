INSERT INTO candidates (name, party, biography, achievements, key_issues) VALUES
    ('Jane Doe', 'Progressive Party', 'Biography text here...', 'Achievements text here...', 'Healthcare, Education, Climate Change');

INSERT INTO polls (title, description, start_date, end_date, visibility) VALUES
    ('Healthcare Reform Opinion Poll', 'Gathering opinions on the new healthcare reform.', '2023-06-01 00:00:00', '2023-06-30 23:59:59', TRUE);

INSERT INTO questions (poll_id, content) VALUES (1, 'Do you support the new healthcare reform?');
INSERT INTO answers (question_id, content) VALUES (1, 'Yes'), (1, 'No'), (1, 'Undecided');

INSERT INTO responses (answer_id, user_id) VALUES (1, 2);

INSERT INTO candidate_polls (candidate_id, poll_id) VALUES (1, 1);
