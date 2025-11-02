import React, { useEffect, useState } from 'react'
import client from '../../api/client'

export default function ManageJobs(){
  const [jobs, setJobs] = useState<any[]>([])
  useEffect(()=> {
    (async ()=> {
      const r = await client.get('/api/recruiter/jobs')
      setJobs(r.data.jobs || [])
    })()
  }, [])

  return (
    <div>
      <div className="flex justify-between items-center mb-4">
        <h2 className="text-xl font-semibold">Jobs</h2>
        <a href="/recruiter/jobs/new" className="px-3 py-2 rounded-md bg-primary text-white">Post job</a>
      </div>
      {jobs.map(j => (
        <div key={j.id} className="card mb-3 flex justify-between">
          <div>
            <div className="font-semibold">{j.title}</div>
            <div className="text-sm text-slate-500">{j.status} • {j.createdAt}</div>
          </div>
          <div>
            <a href={`/recruiter/jobs/${j.id}`} className="text-primary">Edit</a>
          </div>
        </div>
      ))}
    </div>
  )
}
