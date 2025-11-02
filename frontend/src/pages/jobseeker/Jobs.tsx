import React, { useEffect, useState } from 'react'
import client from '../../api/client'

export default function Jobs(){
  const [jobs, setJobs] = useState<any[]>([])
  const [filters, setFilters] = useState({location:'', type:'', minSalary:'', maxSalary:'', experience:''})
  const [loading, setLoading] = useState(false)

  useEffect(() => {
    load()
  }, [])

  const load = async () => {
    setLoading(true)
    try {
      const r = await client.get('/api/jobPost', { params: filters })
      setJobs(r.data.jobs || [])
    } catch(err){ console.error(err) } finally { setLoading(false) }
  }

  return (
    <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
      <div className="card">
        <h3 className="font-semibold">Filters</h3>
        <div className="mt-3 space-y-2">
          <input placeholder="Location" className="w-full input" value={filters.location} onChange={e => setFilters(f => ({...f, location: e.target.value}))} />
          <input placeholder="Min salary" className="w-full input" value={filters.minSalary} onChange={e => setFilters(f => ({...f, minSalary: e.target.value}))} />
          <select className="w-full input" value={filters.type} onChange={e => setFilters(f => ({...f, type: e.target.value}))}>
            <option value="">Any type</option>
            <option>Full-time</option>
            <option>Part-time</option>
            <option>Contract</option>
          </select>
          <button onClick={load} className="w-full py-2 bg-primary text-white rounded-xl">Search</button>
        </div>
      </div>

      <div className="md:col-span-2">
        <div className="card mb-4">
          <h3 className="font-semibold">Jobs</h3>
        </div>
        {loading ? <div>Loading...</div> : jobs.map(job => (
          <div key={job.id} className="card mb-3">
            <div className="flex justify-between">
              <div>
                <h4 className="font-semibold">{job.title}</h4>
                <p className="text-sm text-slate-600">{job.company} • {job.location}</p>
              </div>
              <div>
                <a href={`/job/${job.id}`} className="text-primary">View</a>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  )
}
