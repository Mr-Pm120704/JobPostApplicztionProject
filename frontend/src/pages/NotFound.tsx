import React from 'react'
export default function NotFound(){
  return (
    <div className="min-h-screen flex items-center justify-center p-6">
      <div className="text-center">
        <h2 className="text-4xl font-bold">404</h2>
        <p className="mt-2 text-slate-600">Page not found</p>
        <a href="/" className="mt-4 inline-block text-primary">Go home</a>
      </div>
    </div>
  )
}
