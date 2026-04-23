import { Routes, Route, useNavigate, useLocation } from 'react-router-dom';
import Home from './pages/Home';
import Contests from './pages/Contests';

function App() {
  const navigate = useNavigate();
  const location = useLocation();

  return (
    <>
      <header className="app-header">
        <div className="header-logo">
          <span className="logo-text">Dream11 Fan</span>
        </div>
        <div className="header-actions">
          <div className="wallet-icon"><i className="fa-solid fa-wallet"></i> <span>₹150</span></div>
          <div className="notification-icon"><i className="fa-regular fa-bell"></i></div>
        </div>
      </header>

      <main id="app-content">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/contests/:id" element={<Contests />} />
        </Routes>
      </main>

      <nav className="bottom-nav">
        <div className={`nav-item ${location.pathname === '/' ? 'active' : ''}`} onClick={() => navigate('/')}>
          <i className="fa-solid fa-house"></i>
          <span>Home</span>
        </div>
        <div className={`nav-item ${location.pathname === '/matches' ? 'active' : ''}`} onClick={() => alert('WIP')}>
          <i className="fa-solid fa-trophy"></i>
          <span>My Matches</span>
        </div>
        <div className={`nav-item ${location.pathname === '/rewards' ? 'active' : ''}`} onClick={() => alert('WIP')}>
          <i className="fa-solid fa-gift"></i>
          <span>Rewards</span>
        </div>
        <div className={`nav-item ${location.pathname === '/chat' ? 'active' : ''}`} onClick={() => alert('WIP')}>
          <i className="fa-regular fa-comment-dots"></i>
          <span>Chat</span>
        </div>
      </nav>
    </>
  );
}

export default App;
