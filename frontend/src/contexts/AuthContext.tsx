import React, { createContext, useEffect, useState, useCallback  } from 'react'
import { useNavigate } from 'react-router-dom'
import client from '../api/client'
import type { User } from '../types'

interface AuthState {
  user: User | null
  loading: boolean
  login: (token: string) => Promise<void>
  logout: () => void
  refreshUser: () => Promise<void>
}

export const AuthContext = createContext<AuthState>({
  user: null,
  loading: true,
  login: async () => {},
  logout: () => {},
  refreshUser: async () => {}
})

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
	const navigate = useNavigate() 
  	const [user, setUser] = useState<User | null>(null)
  	const [loading, setLoading] = useState(true)

  // ✅ On mount: check if token exists before refreshing user
  	useEffect(() => {
    const token = localStorage.getItem('jobify_token')
    if (token) {
      // Attach token to axios client globally
      client.defaults.headers.common['Authorization'] = `Bearer ${token}`
      refreshUser().finally(() => setLoading(false))
    } else {
      setLoading(false)
    }

    const onUnauth = () => {
      setUser(null)
      localStorage.removeItem('jobify_token')
      delete client.defaults.headers.common['Authorization']
    }
    window.addEventListener('jobify:unauth', onUnauth)
    return () => window.removeEventListener('jobify:unauth', onUnauth)
  }, [])

  // ✅ After login: store token and refresh user
  const login = async (token: string) => {
    localStorage.setItem('jobify_token', token)
    client.defaults.headers.common['Authorization'] = `Bearer ${token}`
    await refreshUser()
  }

  // ✅ Logout: clear token and user
  const logout = useCallback(() => {
    localStorage.removeItem('jobify_token')
    delete client.defaults.headers.common['Authorization']
    setUser(null)
    navigate('/login')
  }, [navigate])

  // ✅ Refresh user safely
  const refreshUser = async () => {
    try {
      const resp = await client.get('/api/auth/me')
      // Depending on your backend, resp.data may be either `user` or plain user object
      setUser(resp.data.user || resp.data)
    } catch (err) {
      console.error('Failed to refresh user:', err)
      setUser(null)
    }
  }

  return (
    <AuthContext.Provider value={{ user, loading, login, logout, refreshUser }}>
      {children}
    </AuthContext.Provider>
  )
}
