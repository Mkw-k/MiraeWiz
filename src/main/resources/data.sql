-- Initial Data for MiraeWiz

-- Admin Account (password: admin123! - needs to be encoded in actual implementation)
-- Note: In real app, we use BCrypt. This is just for schema initialization.
INSERT INTO members (username, password) VALUES ('admin', '$2a$10$8.UnVuG9HHgffUDAlk8qnO6p5M.7.S561u0x3.S561u0x3.S561u0x'); 

-- Site Contents (About Us)
INSERT INTO site_contents (content_key, content_value, description) VALUES 
('ABOUT_VISION', '미래를 향한 끊임없는 도전, 미래위즈', '회사 비전'),
('ABOUT_GREETING', '안녕하세요. 미래위즈입니다. 우리는 교육의 가치를 기술로 실현합니다.', 'CEO 인사말'),
('ABOUT_HISTORY', '2025.01 미래위즈 설립\n2025.06 중학 영단어 앱 출시', '회사 연혁');

-- Sample Program
INSERT INTO programs (title, description, google_play_url) VALUES 
('중학 영단어', '가장 쉽고 빠르게 외우는 중학 필수 영단어', 'https://play.google.com/store/apps/details?id=com.miraewiz.eng_middle_word_basic');

-- Sample FAQ
INSERT INTO faqs (question, answer, category) VALUES 
('앱은 무료인가요?', '네, 기본적으로 무료로 이용 가능하며 일부 유료 콘텐츠가 포함될 수 있습니다.', '서비스');

-- Sample Review
INSERT INTO reviews (author, content, rating, is_best) VALUES 
('열공학생', '단어가 정말 잘 외워져요! 추천합니다.', 5, TRUE);
