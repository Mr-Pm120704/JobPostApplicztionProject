import React, { useEffect, useState } from 'react'
import client from '../../api/client'

export default function SavedJobs(){
  const [saved, setSaved] = useState<any[]>([])

  useEffect(()=> {
    (async ()=> {
      const r = await client.get('/api/jobseeker/saved')
      setSaved(r.data.saved || [])
    })()
  }, [])

  const remove = async (id:string) => {
    await client.delete(`/api/jobseeker/saved/${id}`)
    setSaved(s => s.filter(x => x.id !== id))
  }

  return (
    <div>
      <h2 className="text-xl font-semibold mb-4">Saved jobs</h2>
      {saved.map(s => (
        <div key={s.id} className="card mb-3 flex justify-between">
          <div>
            <h4 className="font-semibold">{s.title}</h4>
            <p className="text-sm text-slate-600">{s.company}</p>
          </div>
          <div>
            <button onClick={()=>remove(s.id)} className="px-3 py-1 rounded-md border">Remove</button>
          </div>
        </div>
      ))}
    </div>
  )
}
