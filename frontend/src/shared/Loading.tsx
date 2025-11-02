import React from 'react'

export default function Loading(){ 
  return (
    <div className="min-h-screen flex items-center justify-center">
      <div className="text-center">
        <div className="loader mb-4">🔄</div>
        <div className="text-sm text-slate-500">Loading...</div>
      </div>
    </div>
  )
}
