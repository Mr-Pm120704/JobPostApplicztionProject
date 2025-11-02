import React from 'react'
import { Link, Routes, Route } from 'react-router-dom'
import AdminDashboard from '../pages/admin/AdminHome'
import Users from '../pages/admin/Users'
import Moderation from '../pages/admin/Moderation'
import Reports from '../pages/admin/Reports'
import ChatWidget from '../components/ChatWidget'

export default function AdminLayout(){
  return (
    <div>
      <header className="bg-white shadow-sm">
        <div className="container mx-auto px-4 py-4 flex justify-between items-center">
          <Link to="/" className="brand">Jobify</Link>
          <nav className="flex items-center gap-4">
            <Link to="/admin" className="text-sm">Dashboard</Link>
            <Link to="/admin/users" className="text-sm">Users</Link>
            <Link to="/admin/moderation" className="text-sm">Moderation</Link>
            <Link to="/admin/reports" className="text-sm">Reports</Link>
          </nav>
        </div>
      </header>

      <main className="container mx-auto p-4">
        <Routes>
          <Route path="/" element={<AdminHome />} />
          <Route path="users" element={<Users />} />
          <Route path="moderation" element={<Moderation />} />
          <Route path="reports" element={<Reports />} />
        </Routes>
      </main>

      <ChatWidget />
    </div>
  )
}
