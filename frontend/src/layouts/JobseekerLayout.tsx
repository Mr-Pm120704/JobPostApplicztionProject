import React from 'react'
import { Link, Routes, Route } from 'react-router-dom'
import DashboardHome from '../pages/jobseeker/DashboardHome'
import Profile from '../pages/jobseeker/Profile'
import Jobs from '../pages/jobseeker/Jobs'
import SavedJobs from '../pages/jobseeker/SavedJobs'
import Applications from '../pages/jobseeker/Applications'
import Courses from '../pages/jobseeker/Courses'
import ChatWidget from '../components/ChatWidget'

export default function JobseekerLayout(){
  return (
    <div className="min-h-screen">
      <header className="bg-white shadow-sm">
        <div className="container mx-auto px-4 py-4 flex justify-between items-center">
          <Link to="/" className="brand">Jobify</Link>
          <nav className="flex items-center gap-4">
            <Link to="/jobseeker" className="text-sm">Dashboard</Link>
            <Link to="/jobseeker/jobs" className="text-sm">Jobs</Link>
            <Link to="/jobseeker/courses" className="text-sm">Courses</Link>
            <Link to="/jobseeker/profile" className="text-sm">Profile</Link>
          </nav>
        </div>
      </header>

      <main className="container mx-auto p-4">
        <Routes>
          <Route path="/" element={<DashboardHome />} />
          <Route path="profile" element={<Profile />} />
          <Route path="jobs" element={<Jobs />} />
          <Route path="saved" element={<SavedJobs />} />
          <Route path="applications" element={<Applications />} />
          <Route path="courses/*" element={<Courses />} />
        </Routes>
      </main>

      <ChatWidget />
    </div>
  )
}
