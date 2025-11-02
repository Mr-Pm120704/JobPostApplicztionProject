import React from 'react'
import { useForm } from 'react-hook-form'
import { z } from 'zod'
import { zodResolver } from '@hookform/resolvers/zod'
import client from '../../api/client'
import { useNavigate } from 'react-router-dom'
import { useContext } from 'react'
import { AuthContext } from '../../contexts/AuthContext'

const LoginSchema = z.object({
  userEmail: z.string().email(),
  password: z.string().min(6)
})

type LoginForm = z.infer<typeof LoginSchema>

export default function LoginPage(){
  const { register, handleSubmit, formState: { errors, isSubmitting } } = useForm<LoginForm>({
    resolver: zodResolver(LoginSchema)
  })
  const navigate = useNavigate()
  const { login } = useContext(AuthContext)

  const onSubmit = async (data: LoginForm) => {
    try {
      const res = await client.post('/api/auth/login', data)
      const token = res.data.token
      await login(token)
	  const me = await client.get('/api/auth/me', {
	         headers: { Authorization: `Bearer ${token}` }
	       })

	       const user = me.data

	       // ✅ Step 3: Redirect user based on their role
	      // if (user.role === 'ADMIN') {
	         navigate('/dashboard', { replace: true })
	       //} else if (user.role === 'RECRUITER') {
	         //navigate('/recruiter/dashboard', { replace: true })
	       //} else {
	         //navigate('/jobseeker/dashboard', { replace: true })
	       //}
    } catch (err: any) {
      alert(err?.response?.data?.message || 'Login failed, please try again..')
    }
  }

  return (
    <div className="min-h-screen flex items-center justify-center p-6">
      <div className="w-full max-w-3xl grid grid-cols-2 gap-8">
        <div className="hidden md:flex flex-col justify-center p-8 card">
          <h1 className="brand text-3xl mb-4">Jobify</h1>
          <p className="text-slate-600">Find your dream job or the perfect candidate. Smooth hiring, better hiring.</p>
          <div className="mt-6">
            <ul className="text-sm text-slate-500 space-y-2">
              <li>✨ Advanced search & filters</li>
              <li>🔒 Secure payments for courses</li>
              <li>💬 24/7 chat support</li>
            </ul>
          </div>
        </div>

        <div className="card">
          <h2 className="text-xl font-semibold mb-4">Welcome back</h2>
          <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
            <div>
              <label className="text-sm">Email</label>
              <input {...register('userEmail')} className="w-full mt-1 input" placeholder="you@example.com" />
              <div className="text-xs text-rose-600">{errors.userEmail?.message as any}</div>
            </div>
            <div>
              <label className="text-sm">Password</label>
              <input type="password" {...register('password')} className="w-full mt-1 input" placeholder="••••••" />
              <div className="text-xs text-rose-600">{errors.password?.message as any}</div>
            </div>

            <button disabled={isSubmitting} className="w-full py-2 rounded-xl bg-primary text-white">
              {isSubmitting ? 'Signing in...' : 'Sign in'}
            </button>
            <div className="text-xs text-slate-500">
              Don't have an account? <a href="/register" className="text-primary font-medium">Create one</a>
            </div>
          </form>
        </div>
      </div>
    </div>
  )
}
