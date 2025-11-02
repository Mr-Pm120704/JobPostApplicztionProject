import React, { useEffect, useState } from 'react'
import client from '../../api/client'

export default function Analytics(){
  const [stats, setStats] = useState<any>(null)
  useEffect(()=> {
    (async ()=> {
      const r = await client.get('/api/dashboards/recruiter')
      setStats(r.data)
    })()
  }, [])
  return (
    <div className="grid md:grid-cols-3 gap-6">
      <div className="card">
        <h4 className="font-semibold">Applications</h4>
        <div className="text-3xl mt-4">{stats?.applications || 0}</div>
      </div>
      <div className="card">
        <h4 className="font-semibold">Views</h4>
        <div className="text-3xl mt-4">{stats?.views || 0}</div>
      </div>
      <div className="card">
        <h4 className="font-semibold">Conversion</h4>
        <div className="text-3xl mt-4">{stats?.conversion || '0%'}</div>
      </div>
    </div>
  )
}
