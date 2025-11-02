import React from 'react'
import { useForm } from 'react-hook-form'
import { z } from 'zod'
import { zodResolver } from '@hookform/resolvers/zod'
import client from '../../api/client'
import { useNavigate } from 'react-router-dom'

const RegisterSchema = z.object({
  userName: z.string().min(2),
  userEmail: z.string().email(),
  password: z.string().min(6),
  role: z.enum(['JOBSEEKER','RECRUITER','ADMIN'])
})

type RegisterForm = z.infer<typeof RegisterSchema>

export default function RegisterPage(){
  const { register, handleSubmit, formState: { errors, isSubmitting } } = useForm<RegisterForm>({
    resolver: zodResolver(RegisterSchema)
  })
  const navigate = useNavigate()

  const onSubmit = async (data: RegisterForm) => {
    try {
      await client.post('/api/auth/register', data)
      alert('Account created. Please check your email for verification.')
      navigate('/login')
    } catch (err: any) {
      alert(err?.response?.data?.message || 'Registration failed')
    }
  }

  return (
    <div className="min-h-screen flex items-center justify-center p-6">
      <div className="w-full max-w-lg card">
        <h2 className="text-2xl font-semibold mb-4">Create your account</h2>
        <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
          <div>
            <label className="text-sm">Full name</label>
            <input {...register('userName')} className="w-full mt-1 input" />
            <div className="text-xs text-rose-600">{errors.userName?.message as any}</div>
          </div>
          <div>
            <label className="text-sm">Email</label>
            <input {...register('userEmail')} className="w-full mt-1 input" />
            <div className="text-xs text-rose-600">{errors.userEmail?.message as any}</div>
          </div>
          <div>
            <label className="text-sm">Password</label>
            <input type="password" {...register('password')} className="w-full mt-1 input" />
            <div className="text-xs text-rose-600">{errors.password?.message as any}</div>
          </div>
          <div>
            <label className="text-sm">I'm a</label>
            <select {...register('role')} className="w-full mt-1 input">
              <option value="JOBSEEKER">Jobseeker</option>
              <option value="RECRUITER">Recruiter</option>
              <option value="ADMIN">Admin</option>
            </select>
            <div className="text-xs text-rose-600">{errors.role?.message as any}</div>
          </div>
          <button className="w-full py-2 rounded-xl bg-primary text-white">{isSubmitting ? 'Creating...' : 'Create account'}</button>
        </form>
      </div>
    </div>
  )
}
