import React, { useEffect, useState } from 'react'
import client from '../../api/client'

export default function Applications(){
  const [apps, setApps] = useState<any[]>([])

  useEffect(()=> {
    (async ()=> {
      const r = await client.get('/api/applications/my')
      setApps(r.data.applications || [])
    })()
  }, [])

  return (
    <div>
      <h2 className="text-xl font-semibold mb-4">Your applications</h2>
      {apps.map(a => (
        <div key={a.id} className="card mb-3">
          <div className="flex justify-between items-center">
            <div>
              <div className="font-semibold">{a.jobTitle}</div>
              <div className="text-sm text-slate-500">Status: <strong>{a.status}</strong></div>
            </div>
            <div>
              <a href={`/applications/${a.id}`} className="text-primary">Details</a>
            </div>
          </div>
        </div>
      ))}
    </div>
  )
}
