import axios from 'axios'
import { API_BASE } from '../config'

const client = axios.create({
  baseURL: API_BASE,
  headers: { 'Content-Type': 'application/json' },
  withCredentials: false
})

// Request interceptor to add auth token if present
client.interceptors.request.use((cfg) => {
	console.log(cfg.headers['Authorization']);
  const token = localStorage.getItem('jobify_token')
  if (token) {
    cfg.headers = cfg.headers || {}
    cfg.headers['Authorization'] = `Bearer ${token}`
  }
  return cfg
})

// Basic response error handling
client.interceptors.response.use(
  r => r,
  err => {
    // central place for handling auth expiration / global errors
    if (err.response && err.response.status === 401) {
      // Optionally broadcast logout event
      window.dispatchEvent(new CustomEvent('jobify:unauth'))
    }
    return Promise.reject(err)
  }
)

export default client
