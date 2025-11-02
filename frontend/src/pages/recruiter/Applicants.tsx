import React, { useEffect, useState } from 'react'
import client from '../../api/client'

export default function Applicants(){
  const [applicants, setApplicants] = useState<any[]>([])
  useEffect(()=> {
    (async ()=> {
      const r = await client.get('/api/recruiter/applicants')
      setApplicants(r.data.applicants || [])
    })()
  }, [])

  const shortlist = async (id:string) => {
    await client.post(`/api/recruiter/applicants/${id}/shortlist`)
    setApplicants(a => a.map(x => x.id === id ? {...x, shortlisted: true} : x))
  }

  return (
    <div>
      <h2 className="text-xl font-semibold mb-4">Applicants</h2>
      {applicants.map(a => (
        <div key={a.id} className="card mb-3 flex justify-between">
          <div>
            <div className="font-semibold">{a.name}</div>
            <div className="text-sm text-slate-500">{a.positionApplied}</div>
          </div>
          <div className="flex gap-2">
            <button onClick={()=>shortlist(a.id)} className="px-3 py-1 rounded-md border">Shortlist</button>
            <a href={`/recruiter/applicants/${a.id}`} className="text-primary">View</a>
          </div>
        </div>
      ))}
    </div>
  )
}
