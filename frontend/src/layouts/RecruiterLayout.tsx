import React from 'react'
import { Link, Routes, Route } from 'react-router-dom'
import RecruiterHome from '../pages/recruiter/RecruiterHome'
import ManageJobs from '../pages/recruiter/ManageJobs'
import Applicants from '../pages/recruiter/Applicants'
import Analytics from '../pages/recruiter/Analytics'
import ChatWidget from '../components/ChatWidget'

export default function RecruiterLayout(){
  return (
    <div>
      <header className="bg-white shadow-sm">
        <div className="container mx-auto px-4 py-4 flex justify-between items-center">
          <Link to="/" className="brand">Jobify</Link>
          <nav className="flex items-center gap-4">
            <Link to="/recruiter" className="text-sm">Dashboard</Link>
            <Link to="/recruiter/jobs" className="text-sm">Jobs</Link>
            <Link to="/recruiter/applicants" className="text-sm">Applicants</Link>
            <Link to="/recruiter/analytics" className="text-sm">Analytics</Link>
          </nav>
        </div>
      </header>

      <main className="container mx-auto p-4">
        <Routes>
          <Route path="/" element={<RecruiterHome />} />
          <Route path="jobs" element={<ManageJobs />} />
          <Route path="applicants" element={<Applicants />} />
          <Route path="analytics" element={<Analytics />} />
        </Routes>
      </main>

      <ChatWidget />
    </div>
  )
}
