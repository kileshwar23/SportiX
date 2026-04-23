// MOCK DATA
const upcomingMatches = [
    {
        id: 1,
        series: 'Indian Premier League',
        team1: { name: 'CSK', abbr: 'CSK', color: '#F9CD05' },
        team2: { name: 'RCB', abbr: 'RCB', color: '#E11B22' },
        timeLeft: '2h 15m',
        prizePool: '₹55 Crores'
    },
    {
        id: 2,
        series: 'T20 World Cup',
        team1: { name: 'IND', abbr: 'IND', color: '#0066cc' },
        team2: { name: 'AUS', abbr: 'AUS', color: '#ffcc00' },
        timeLeft: 'Tomorrow, 7:30 PM',
        prizePool: '₹20 Crores'
    },
    {
        id: 3,
        series: 'Women\'s Premier League',
        team1: { name: 'MIW', abbr: 'MIW', color: '#004BA0' },
        team2: { name: 'DCW', abbr: 'DCW', color: '#17407a' },
        timeLeft: '4h 45m',
        prizePool: '₹5 Crores'
    }
];

// APP LOGIC
const app = {
    container: null,
    
    init() {
        this.container = document.getElementById('app-content');
        this.navigate('home');
    },

    navigate(page) {
        // Update nav UI
        document.querySelectorAll('.nav-item').forEach(el => {
            el.classList.remove('active');
        });
        
        try {
            const activeNav = document.querySelector(`.nav-item[onclick="app.navigate('${page}')"]`);
            if (activeNav) activeNav.classList.add('active');
        } catch(e){}

        // Render page
        if (page === 'home') {
            this.renderHome();
        } else {
            this.renderComingSoon(page);
        }
        
        window.scrollTo(0,0);
    },

    renderHome() {
        let html = `
            <div class="banner">
                <img src="https://images.unsplash.com/photo-1540747913346-19e32dc3e97e?q=80&w=1000&auto=format&fit=crop" alt="Cricket Banner">
            </div>
            <div class="section-title">Upcoming Matches</div>
        `;

        upcomingMatches.forEach((match, index) => {
            html += `
                <div class="match-card" style="animation-delay: ${index * 0.1}s" onclick="app.showContests(${match.id})">
                    <div class="match-header">
                        <span>${match.series}</span>
                        <span><i class="fa-solid fa-bell"></i> LINEUPS OUT</span>
                    </div>
                    <div class="match-body">
                        <div class="team">
                            <div class="team-logo" style="background:${match.team1.color}; color: white;">${match.team1.abbr}</div>
                            <span class="team-name">${match.team1.name}</span>
                        </div>
                        <div class="match-time">
                            <span>${match.timeLeft}</span>
                        </div>
                        <div class="team">
                            <div class="team-logo" style="background:${match.team2.color}; color: white;">${match.team2.abbr}</div>
                            <span class="team-name">${match.team2.name}</span>
                        </div>
                    </div>
                    <div class="match-footer">
                        <i class="fa-solid fa-trophy"></i> ₹${match.prizePool} Mega Contest!
                    </div>
                </div>
            `;
        });

        this.container.innerHTML = html;
    },

    showContests(matchId) {
        const match = upcomingMatches.find(m => m.id === matchId);
        
        let html = `
            <div class="page-header" onclick="app.navigate('home')" style="cursor:pointer;">
                <i class="fa-solid fa-arrow-left"></i>
                <span>${match.team1.abbr} vs ${match.team2.abbr}</span>
            </div>
            
            <div style="padding: 16px;">
                <h3 style="margin-bottom: 16px;">Mega Contests</h3>
                
                <div class="match-card" style="margin: 0 0 16px 0; border: 1px solid var(--primary-red); position: relative;">
                    <div style="position: absolute; top:0; right:0; background:var(--primary-red); color:white; font-size:10px; padding:2px 8px; font-weight:bold; border-bottom-left-radius:8px;">GUARANTEED</div>
                    <div class="match-body" style="flex-direction:column; align-items:flex-start;">
                        <div style="display:flex; justify-content:space-between; width:100%; margin-bottom:12px;">
                            <div style="font-size:24px; font-weight:800;">₹10 Crores</div>
                            <button style="background:var(--success-green); color:white; border:none; border-radius:6px; font-weight:bold; padding:8px 24px; cursor:pointer;" onclick="alert('Join Team UI coming next!')">₹49</button>
                        </div>
                        
                        <div style="width:100%; background:#eee; height:6px; border-radius:3px; overflow:hidden; margin-bottom:8px;">
                            <div style="height:100%; width:65%; background:var(--primary-red);"></div>
                        </div>
                        
                        <div style="display:flex; justify-content:space-between; width:100%; font-size:12px; color:var(--text-secondary);">
                            <span>2,24,560 spots left</span>
                            <span>4,50,000 spots</span>
                        </div>
                    </div>
                    <div class="match-footer" style="display:flex; justify-content:space-between;">
                        <span><i class="fa-solid fa-trophy"></i> ₹1 Crore First Prize</span>
                        <span>M ₹55%</span>
                    </div>
                </div>
                
                 <h3 style="margin-bottom: 16px;">Head-to-Head</h3>
                 
                 <div class="match-card" style="margin: 0 0 16px 0;">
                    <div class="match-body" style="flex-direction:column; align-items:flex-start;">
                        <div style="display:flex; justify-content:space-between; width:100%; align-items:center;">
                            <div style="font-size:20px; font-weight:700;">₹10,000</div>
                            <button style="background:var(--success-green); color:white; border:none; border-radius:6px; font-weight:bold; padding:8px 24px; cursor:pointer;" onclick="alert('Connect to build team API')">₹5,750</button>
                        </div>
                    </div>
                    <div class="match-footer">2 Spots • 1 Winner</div>
                 </div>
                 
            </div>
            
            <!-- Create Team FAB -->
            <button style="position:fixed; bottom: 85px; right: 24px; background:#1A1A1A; color:white; display:flex; align-items:center; gap:8px; padding:12px 20px; border-radius:30px; font-weight:bold; border:none; box-shadow:0 4px 10px rgba(0,0,0,0.3); z-index:90;">
                <i class="fa-solid fa-plus"></i> Create Team
            </button>
        `;
        
        this.container.innerHTML = html;
        window.scrollTo(0,0);
    },
    
    renderComingSoon(page) {
        this.container.innerHTML = `
            <div style="display:flex; flex-direction:column; align-items:center; justify-content:center; height:60vh; color:var(--text-secondary);">
                <i class="fa-solid fa-hammer" style="font-size:48px; margin-bottom:16px;"></i>
                <h2>${page.charAt(0).toUpperCase() + page.slice(1)}</h2>
                <p style="margin-top:8px;">This section is under development.</p>
            </div>
        `;
    }
};

// Initialize App
document.addEventListener('DOMContentLoaded', () => {
    app.init();
});
