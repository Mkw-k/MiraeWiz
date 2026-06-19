-- Initial Data for MiraeWiz (Updated with actual image paths)

-- Admin Account
INSERT INTO members (username, password) VALUES ('admin', '$2a$10$.U247P1fHcbcUyPW9J3Ype1LBJVkS.8lPVQXfCZWojdOUy6MoY7NO'); 

-- Site Contents
INSERT INTO site_contents (content_key, content_value, description) VALUES 
('ABOUT_VISION', '미래를 향한 끊임없는 도전, 미래위즈', '회사 비전'),
('ABOUT_GREETING', '안녕하세요. 미래위즈입니다. 우리는 교육의 가치를 기술로 실현합니다.', 'CEO 인사말'),
('ABOUT_HISTORY', '2025.01 미래위즈 설립\n2025.06 WW Kingdom 출시', '회사 연혁');

-- Main App (WW Kingdom)
INSERT INTO programs (title, description, icon_url, google_play_url, display_order) VALUES 
('WW Kingdom', '게임처럼 즐기는 중등 영단어 정복! 단계별 레벨 클리어로 재미있게 학습하세요.', '/images/ref/01.png', 'https://play.google.com/store/apps/details?id=com.miraewiz.eng_middle_word_basic', 1);

-- Other Apps/Screenshots (Using the 37 images)
INSERT INTO programs (title, description, icon_url, display_order) VALUES 
('영어 리스닝 훈련', '정확한 발음과 리스닝을 위한 통합 연습 모드', '/images/ref/02.png', 2),
('오답 자동 복습', '틀린 단어만 모아서 똑똑하게 복습하는 시스템', '/images/ref/03.jpg', 3),
('레벨별 단어장', '교과서 기반 필수 영단어 완벽 수록', '/images/ref/04.png', 4);

-- FAQ
INSERT INTO faqs (question, answer, category, display_order) VALUES 
('앱 이용료는 얼마인가요?', '기본적인 학습 기능은 무료이며, 광고 제거 등 일부 기능은 인앱 결제로 제공됩니다.', '이용안내', 1),
('오프라인에서도 사용 가능한가요?', '한 번 다운로드된 데이터는 네트워크 연결 없이도 학습이 가능합니다.', '기술문의', 2);

-- Reviews (password: 1234)
INSERT INTO reviews (author, password, content, rating, is_best) VALUES 
('우등생A', '$2a$10$NfzRMcPpTNggbiNHALCSCOuZhIxOKZ3dm6E5lhqC/7VkE4DN4Ei/C', '진짜 게임하는 것 같아서 시간 가는 줄 모르고 공부했어요. 중학 필수 단어가 다 있어서 너무 좋아요!', 5, TRUE),
('학부모님', '$2a$10$NfzRMcPpTNggbiNHALCSCOuZhIxOKZ3dm6E5lhqC/7VkE4DN4Ei/C', '아이가 영단어 외우는 걸 너무 싫어했는데 이 앱은 스스로 켜서 하네요. 감사합니다.', 5, TRUE),
('열공이', '$2a$10$NfzRMcPpTNggbiNHALCSCOuZhIxOKZ3dm6E5lhqC/7VkE4DN4Ei/C', '디자인이 깔끔하고 리스닝까지 같이 할 수 있어서 편해요.', 4, FALSE),
('111', '$2a$10$mzjEXXJI67u6DPl0cINAwuIUlAWABvh2/18UyvaQG0Uye9uGHwpZq', '11', 5, FALSE),
('222', '$2a$10$mzjEXXJI67u6DPl0cINAwuIUlAWABvh2/18UyvaQG0Uye9uGHwpZq', '22', 5, FALSE),
('333', '$2a$10$mzjEXXJI67u6DPl0cINAwuIUlAWABvh2/18UyvaQG0Uye9uGHwpZq', '33', 5, FALSE),
('444', '$2a$10$mzjEXXJI67u6DPl0cINAwuIUlAWABvh2/18UyvaQG0Uye9uGHwpZq', '44', 5, FALSE),
('555', '$2a$10$mzjEXXJI67u6DPl0cINAwuIUlAWABvh2/18UyvaQG0Uye9uGHwpZq', '55', 5, FALSE),
('666', '$2a$10$mzjEXXJI67u6DPl0cINAwuIUlAWABvh2/18UyvaQG0Uye9uGHwpZq', '66', 5, FALSE),
('777', '$2a$10$mzjEXXJI67u6DPl0cINAwuIUlAWABvh2/18UyvaQG0Uye9uGHwpZq', '77', 5, FALSE),
('888', '$2a$10$mzjEXXJI67u6DPl0cINAwuIUlAWABvh2/18UyvaQG0Uye9uGHwpZq', '88', 5, FALSE);
