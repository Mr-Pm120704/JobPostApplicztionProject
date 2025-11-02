import React, { Suspense, useContext } from 'react'
import { Routes, Route, Navigate } from 'react-router-dom'
import { AuthProvider, AuthContext } from './contexts/AuthContext'
import Loading from './shared/Loading'
import LoginPage from './pages/auth/Login'
import RegisterPage from './pages/auth/Register'
import JobseekerLayout from './layouts/JobseekerLayout'
import RecruiterLayout from './layouts/RecruiterLayout'
import AdminLayout from './layouts/AdminLayout'
import PublicHome from './pages/PublicHome'
import NotFound from './pages/NotFound'
import Dashboard from './pages/Dashboard'

function AppRoutes() {
  const { user, loading } = useContext(AuthContext)

  if (loading) return <Loading />

  return (
    <Suspense fallback={<Loading />}>
      <Routes>
        <Route path="/" element={<PublicHome />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
		<Route path="/dashboard" element={<Dashboard />} />

        {/* Protected dashboards */}
        <Route path="/jobseeker/*" element={
          user?.role === 'jobseeker' ? <JobseekerLayout /> : <Navigate to={user ? `/${user.role}` : '/login'} replace />
        } />
        <Route path="/recruiter/*" element={
          user?.role === 'recruiter' ? <RecruiterLayout /> : <Navigate to={user ? `/${user.role}` : '/login'} replace />
        } />
        <Route path="/admin/*" element={
          user?.role === 'admin' ? <AdminLayout /> : <Navigate to={user ? `/${user.role}` : '/login'} replace />
        } />

        <Route path="*" element={<NotFound />} />
      </Routes>
    </Suspense>
  )
}

export default function App() {
  return (
    <AuthProvider>
      <AppRoutes />
    </AuthProvider>
  )
}
