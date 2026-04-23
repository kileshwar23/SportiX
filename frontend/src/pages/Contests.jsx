import React, { useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { upcomingMatches } from './Home';

export default function Contests() {
    const { id } = useParams();
    const navigate = useNavigate();
    
    useEffect(() => {
        window.scrollTo(0, 0);
    }, []);

    const match = upcomingMatches.find(m => m.id === parseInt(id));

    if (!match) return <div style={{padding: '20px'}}>Match not found</div>;

    return (
        <div>
            <div className="page-header" onClick={() => navigate(-1)} style={{ cursor: 'pointer' }}>
                <i className="fa-solid fa-arrow-left"></i>
                <span>{match.team1.abbr} vs {match.team2.abbr}</span>
            </div>
            
            <div style={{ padding: '16px' }}>
                <h3 style={{ marginBottom: '16px' }}>Mega Contests</h3>
                
                <div className="match-card" style={{ margin: '0 0 16px 0', border: '1px solid var(--primary-red)', position: 'relative' }}>
                    <div style={{ position: 'absolute', top: 0, right: 0, background: 'var(--primary-red)', color: 'white', fontSize: '10px', padding: '2px 8px', fontWeight: 'bold', borderBottomLeftRadius: '8px' }}>GUARANTEED</div>
                    <div className="match-body" style={{ flexDirection: 'column', alignItems: 'flex-start' }}>
                        <div style={{ display: 'flex', justifyContent: 'space-between', width: '100%', marginBottom: '12px' }}>
                            <div style={{ fontSize: '24px', fontWeight: '800' }}>₹10 Crores</div>
                            <button style={{ background: 'var(--success-green)', color: 'white', border: 'none', borderRadius: '6px', fontWeight: 'bold', padding: '8px 24px', cursor: 'pointer' }} onClick={() => alert('Join Team UI coming next!')}>₹49</button>
                        </div>
                        
                        <div style={{ width: '100%', background: '#eee', height: '6px', borderRadius: '3px', overflow: 'hidden', marginBottom: '8px' }}>
                            <div style={{ height: '100%', width: '65%', background: 'var(--primary-red)' }}></div>
                        </div>
                        
                        <div style={{ display: 'flex', justifyContent: 'space-between', width: '100%', fontSize: '12px', color: 'var(--text-secondary)' }}>
                            <span>2,24,560 spots left</span>
                            <span>4,50,000 spots</span>
                        </div>
                    </div>
                    <div className="match-footer" style={{ display: 'flex', justifyContent: 'space-between' }}>
                        <span><i className="fa-solid fa-trophy"></i> ₹1 Crore First Prize</span>
                        <span>M ₹55%</span>
                    </div>
                </div>
                
                 <h3 style={{ marginBottom: '16px' }}>Head-to-Head</h3>
                 
                 <div className="match-card" style={{ margin: '0 0 16px 0' }}>
                    <div className="match-body" style={{ flexDirection: 'column', alignItems: 'flex-start' }}>
                        <div style={{ display: 'flex', justifyContent: 'space-between', width: '100%', alignItems: 'center' }}>
                            <div style={{ fontSize: '20px', fontWeight: '700' }}>₹10,000</div>
                            <button style={{ background: 'var(--success-green)', color: 'white', border: 'none', borderRadius: '6px', fontWeight: 'bold', padding: '8px 24px', cursor: 'pointer' }} onClick={() => alert('Connect to build team API')}>₹5,750</button>
                        </div>
                    </div>
                    <div className="match-footer">2 Spots • 1 Winner</div>
                 </div>
                 
            </div>
            
            <button style={{ position: 'fixed', bottom: '85px', right: '24px', background: '#1A1A1A', color: 'white', display: 'flex', alignItems: 'center', gap: '8px', padding: '12px 20px', borderRadius: '30px', fontWeight: 'bold', border: 'none', boxShadow: '0 4px 10px rgba(0,0,0,0.3)', zIndex: 90 }}>
                <i className="fa-solid fa-plus"></i> Create Team
            </button>
        </div>
    );
}
