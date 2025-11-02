import React, { useContext, useEffect, useState } from 'react'
import client from '../api/client'
import { AuthContext } from '../contexts/AuthContext'
import AdminHome from './admin/AdminHome'
import RecruiterHome from './recruiter/RecruiterHome'
import DashboardHome from './jobseeker/DashboardHome'

interface DashboardData {
  jobs?: any
  applications?: any
  users?: any
  courses?: any
  subscriptions?: any
}

export default function Dashboard() {
  const { user } = useContext(AuthContext)
  const [data, setData] = useState<DashboardData>({})
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    const fetchDashboardData = async () => {
      try {
        const [jobs, apps, users, courses, subs] = await Promise.all([
          client.get('/api/dashboards/jobs'),
          client.get('/api/dashboards/applications'),
          client.get('/api/dashboards/users'),
          client.get('/api/dashboards/courses'),
          client.get('/api/dashboards/subscriptions')
        ])

        setData({
          jobs: jobs.data,
          applications: apps.data,
          users: users.data,
          courses: courses.data,
          subscriptions: subs.data
        })
      } catch (err) {
        console.error('Failed to fetch dashboard data', err)
      } finally {
        setLoading(false)
      }
    }

    fetchDashboardData()
  }, [])

  if (loading) return <div className="p-8 text-center">Loading dashboard...</div>

  // Render based on user role
  if (!user) return <div className="p-8 text-center">Unauthorized</div>

  if (user.role === 'ADMIN') {
    return <AdminHome data={data} />
  } else if (user.role === 'RECRUITER') {
    return <RecruiterHome data={data} />
  } else {
    return <DashboardHome data={data} />
  }
}
