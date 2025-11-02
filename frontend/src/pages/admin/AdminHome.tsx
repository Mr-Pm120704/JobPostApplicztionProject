import React, { useEffect, useState, useContext } from 'react'
import { useNavigate } from 'react-router-dom'
import client from '../../api/client'
import { AuthContext } from '../../contexts/AuthContext'

interface User {
  id: string
  name: string
  email: string
  status: 'active' | 'blocked'
}

interface DashboardMetrics {
  jobs: { totalJobs: number }
  applications: { totalApplications: number }
  users: { totalUsers: number; users: User[] }
  courses: { totalCourses: number }
  subscriptions: { active: number }
}

export default function AdminHome() {
  const navigate = useNavigate()
  const { logout } = useContext(AuthContext)

  const [metrics, setMetrics] = useState<DashboardMetrics | null>(null)
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)
  const [actionLoading, setActionLoading] = useState<string | null>(null)

  // Fetch dashboard metrics
  useEffect(() => {
    const fetchDashboardData = async () => {
      try {
        setLoading(true)
        const [jobs, apps, users, courses, subs] = await Promise.all([
          client.get('/api/dashboards/jobs'),
          client.get('/api/dashboards/applications'),
          client.get('/api/dashboards/users'),
          client.get('/api/dashboards/courses'),
          client.get('/api/dashboards/subscriptions')
        ])

        setMetrics({
          jobs: jobs.data,
          applications: apps.data,
          users: users.data,
          courses: courses.data,
          subscriptions: subs.data
        })
      } catch (err: any) {
        setError(err?.response?.data?.message || 'Failed to load dashboard data')
      } finally {
        setLoading(false)
      }
    }

    fetchDashboardData()
  }, [])

  const handleBlockUser = async (userId: string) => {
    try {
      setActionLoading(userId)
      await client.post(`/api/users/${userId}/block`)
      setMetrics(prev =>
        prev
          ? {
              ...prev,
              users: {
                ...prev.users,
                users: prev.users.users.map(u =>
                  u.id === userId ? { ...u, status: 'blocked' } : u
                )
              }
            }
          : prev
      )
    } catch {
      setError('Failed to block user')
    } finally {
      setActionLoading(null)
    }
  }

  const handleUnblockUser = async (userId: string) => {
    try {
      setActionLoading(userId)
      await client.post(`/api/users/${userId}/unblock`)
      setMetrics(prev =>
        prev
          ? {
              ...prev,
              users: {
                ...prev.users,
                users: prev.users.users.map(u =>
                  u.id === userId ? { ...u, status: 'active' } : u
                )
              }
            }
          : prev
      )
    } catch {
      setError('Failed to unblock user')
    } finally {
      setActionLoading(null)
    }
  }

  const handleLogout = () => {
    logout()
    navigate('/login', { replace: true })
  }

  if (loading) return <div className="p-8 text-center">Loading dashboard...</div>

  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-6">
        <h2 className="text-2xl font-bold">Admin Dashboard</h2>
        <button
          onClick={handleLogout}
          className="px-4 py-2 bg-red-600 text-white rounded-lg hover:bg-red-700 transition"
        >
          Logout
        </button>
      </div>

      {error && (
        <div className="p-3 mb-4 bg-red-100 text-red-700 rounded">
          {error}
        </div>
      )}

      {/* Metrics Grid */}
      <div className="grid md:grid-cols-5 gap-6 mb-8">
        <div className="card">
          <h4 className="font-semibold">Total Users</h4>
          <p className="text-3xl font-bold mt-2">{metrics?.users?.totalUsers ?? 0}</p>
        </div>
        <div className="card">
          <h4 className="font-semibold">Jobs Posted</h4>
          <p className="text-3xl font-bold mt-2">{metrics?.jobs?.totalJobs ?? 0}</p>
        </div>
        <div className="card">
          <h4 className="font-semibold">Applications</h4>
          <p className="text-3xl font-bold mt-2">{metrics?.applications?.totalApplications ?? 0}</p>
        </div>
        <div className="card">
          <h4 className="font-semibold">Courses</h4>
          <p className="text-3xl font-bold mt-2">{metrics?.courses?.totalCourses ?? 0}</p>
        </div>
        <div className="card">
          <h4 className="font-semibold">Active Subscriptions</h4>
          <p className="text-3xl font-bold mt-2">{metrics?.subscriptions?.active ?? 0}</p>
        </div>
      </div>

      {/* User Management Section */}
      <div className="card">
        <h3 className="text-xl font-bold mb-4">User Management</h3>
        <div className="overflow-x-auto">
          <table className="w-full text-left">
            <thead className="border-b">
              <tr>
                <th className="pb-2">Name</th>
                <th className="pb-2">Email</th>
                <th className="pb-2">Status</th>
                <th className="pb-2">Action</th>
              </tr>
            </thead>
            <tbody>
              {metrics?.users?.users?.map(user => (
                <tr key={user.id} className="border-b hover:bg-gray-50">
                  <td className="py-3">{user.name}</td>
                  <td className="py-3">{user.email}</td>
                  <td className="py-3">
                    <span
                      className={`px-3 py-1 rounded text-sm ${
                        user.status === 'active'
                          ? 'bg-green-100 text-green-800'
                          : 'bg-red-100 text-red-800'
                      }`}
                    >
                      {user.status}
                    </span>
                  </td>
                  <td className="py-3">
                    {user.status === 'active' ? (
                      <button
                        onClick={() => handleBlockUser(user.id)}
                        disabled={actionLoading === user.id}
                        className="px-3 py-1 bg-red-500 text-white rounded text-sm hover:bg-red-600 disabled:opacity-50"
                      >
                        {actionLoading === user.id ? 'Blocking...' : 'Block'}
                      </button>
                    ) : (
                      <button
                        onClick={() => handleUnblockUser(user.id)}
                        disabled={actionLoading === user.id}
                        className="px-3 py-1 bg-green-500 text-white rounded text-sm hover:bg-green-600 disabled:opacity-50"
                      >
                        {actionLoading === user.id ? 'Unblocking...' : 'Unblock'}
                      </button>
                    )}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  )
}
