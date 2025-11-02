import React from 'react'
export default function PublicHome(){
  return (
    <div className="min-h-screen flex items-center justify-center p-6">
      <div className="card max-w-3xl text-center">
        <h1 className="text-3xl font-bold mb-2">Welcome to Jobify</h1>
        <p className="text-slate-600">Login or create an account to get started.</p>
        <div className="mt-6 flex justify-center gap-3">
          <a href="/login" className="px-4 py-2 rounded-lg border">Login</a>
          <a href="/register" className="px-4 py-2 rounded-lg bg-primary text-white">Register</a>
        </div>
      </div>
    </div>
  )
}
