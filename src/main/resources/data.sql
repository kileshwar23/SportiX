-- =============================================
-- Roles (idempotent — safe to run on every startup)
-- =============================================
INSERT INTO roles (name)
VALUES ('ROLE_USER'), ('ROLE_ADMIN')
ON CONFLICT (name) DO NOTHING;

-- =============================================
-- Sample Matches
-- =============================================
INSERT INTO matches (team1, team2, match_date, venue, status, created_at, updated_at)
VALUES
  ('Mumbai Indians',     'Chennai Super Kings',   NOW() + INTERVAL '2 days', 'Wankhede Stadium, Mumbai',    'SCHEDULED', NOW(), NOW()),
  ('Royal Challengers',  'Kolkata Knight Riders', NOW() + INTERVAL '4 days', 'M. Chinnaswamy Stadium',      'SCHEDULED', NOW(), NOW()),
  ('Delhi Capitals',     'Rajasthan Royals',      NOW() + INTERVAL '6 days', 'Arun Jaitley Stadium, Delhi', 'SCHEDULED', NOW(), NOW()),
  ('Punjab Kings',       'Sunrisers Hyderabad',   NOW() - INTERVAL '1 day',  'PCA Stadium, Mohali',         'LIVE',      NOW(), NOW())
ON CONFLICT DO NOTHING;

-- =============================================
-- Sample Contests
-- Column names match entity @Column mappings:
--   entryFee       → entry_fee
--   prizePool      → prize_pool
--   maxParticipants → max_participants
--   currentParticipants → current_participants
--   startTime      → start_time
--   endTime        → end_time
--   maxTeamsPerUser → max_teams_per_user
--   firstPrize     → first_prize
-- =============================================
INSERT INTO contests (name, description, category, entry_fee, prize_pool, max_participants, current_participants, start_time, end_time, status, max_teams_per_user, first_prize, created_at, updated_at)
VALUES
  ('Mega Contest', 'Win big with the mega contest!', 'CRICKET', 49.0,  1000000.0, 10000, 0, NOW() + INTERVAL '2 days', NOW() + INTERVAL '3 days', 'ACTIVE', 1, 500000.0, NOW(), NOW()),
  ('Small League', 'Perfect for beginners',          'CRICKET', 10.0,  50000.0,   500,   0, NOW() + INTERVAL '2 days', NOW() + INTERVAL '3 days', 'ACTIVE', 2, 25000.0,  NOW(), NOW()),
  ('Free Contest', 'Free to enter, real prizes!',    'CRICKET', 0.0,   10000.0,   1000,  0, NOW() + INTERVAL '4 days', NOW() + INTERVAL '5 days', 'ACTIVE', 1, 5000.0,   NOW(), NOW()),
  ('Head to Head', '1v1 battle, winner takes all',   'CRICKET', 100.0, 180.0,     2,     0, NOW() + INTERVAL '6 days', NOW() + INTERVAL '7 days', 'ACTIVE', 1, 180.0,    NOW(), NOW())
ON CONFLICT DO NOTHING;
