import React, { useEffect, useState } from 'react'
import client from '../../api/client'

export default function Reports(){
  const [reports, setReports] = useState<any[]>([])
  useEffect(()=> {
    (async ()=> {
      const r = await client.get('/api/admins/reports')
      setReports(r.data.reports || [])
    })()
  }, [])

  return (
    <div>
      <h2 className="text-xl font-semibold mb-4">System reports</h2>
      {reports.map(r => (
        <div key={r.id} className="card mb-3">
          <div className="font-semibold">{r.title}</div>
          <div className="text-sm text-slate-500">{r.summary}</div>
        </div>
      ))}
    </div>
  )
}
