import React from 'react';
import { useNavigate } from 'react-router-dom';

export const upcomingMatches = [
    {
        id: 1,
        series: 'Indian Premier League',
        team1: { name: 'CSK', abbr: 'CSK', color: '#F9CD05' },
        team2: { name: 'RCB', abbr: 'RCB', color: '#E11B22' },
        timeLeft: '2h 15m',
        prizePool: '55 Crores'
    },
    {
        id: 2,
        series: 'T20 World Cup',
        team1: { name: 'IND', abbr: 'IND', color: '#0066cc' },
        team2: { name: 'AUS', abbr: 'AUS', color: '#ffcc00' },
        timeLeft: 'Tomorrow, 7:30 PM',
        prizePool: '20 Crores'
    },
    {
        id: 3,
        series: "Women's Premier League",
        team1: { name: 'MIW', abbr: 'MIW', color: '#004BA0' },
        team2: { name: 'DCW', abbr: 'DCW', color: '#17407a' },
        timeLeft: '4h 45m',
        prizePool: '5 Crores'
    }
];

export default function Home() {
    const navigate = useNavigate();

    return (
        <div>
            <div className="banner">
                <img src="https://images.unsplash.com/photo-1540747913346-19e32dc3e97e?q=80&w=1000&auto=format&fit=crop" alt="Cricket Banner" />
            </div>
            <div className="section-title">Upcoming Matches</div>
            
            {upcomingMatches.map((match, index) => (
                <div 
                    key={match.id} 
                    className="match-card" 
                    style={{ animationDelay: `${index * 0.1}s` }}
                    onClick={() => navigate(`/contests/${match.id}`)}
                >
                    <div className="match-header">
                        <span>{match.series}</span>
                        <span><i className="fa-solid fa-bell"></i> LINEUPS OUT</span>
                    </div>
                    <div className="match-body">
                        <div className="team">
                            <div className="team-logo" style={{ background: match.team1.color, color: 'white' }}>{match.team1.abbr}</div>
                            <span className="team-name">{match.team1.name}</span>
                        </div>
                        <div className="match-time">
                            <span>{match.timeLeft}</span>
                        </div>
                        <div className="team">
                            <div className="team-logo" style={{ background: match.team2.color, color: 'white' }}>{match.team2.abbr}</div>
                            <span className="team-name">{match.team2.name}</span>
                        </div>
                    </div>
                    <div className="match-footer">
                        <i className="fa-solid fa-trophy"></i> ₹{match.prizePool} Mega Contest!
                    </div>
                </div>
            ))}
        </div>
    );
}
