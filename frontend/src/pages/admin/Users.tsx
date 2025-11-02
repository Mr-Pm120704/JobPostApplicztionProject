import React, { useEffect, useState } from 'react'
import client from '../../api/client'

export default function Users(){
  const [users, setUsers] = useState<any[]>([])
  const [query, setQuery] = useState('')
  useEffect(()=> {
    (async ()=> {
      const r = await client.get('/api/admins/users')
      setUsers(r.data.users || [])
    })()
  }, [])

  const approve = async (id:string) => {
    await client.post(`/api/admins/users/${id}/approve`)
    setUsers(u => u.map(x => x.id===id ? {...x, verified:true} : x))
  }

  const reject = async (id:string) => {
    await client.post(`/api/admins/users/${id}/reject`)
    setUsers(u => u.filter(x => x.id !== id))
  }

  const filtered = users.filter(u => u.name.toLowerCase().includes(query.toLowerCase()) || u.email.toLowerCase().includes(query.toLowerCase()))

  return (
    <div>
      <div className="flex justify-between mb-3">
        <h2 className="text-xl font-semibold">Users</h2>
        <input placeholder="Search..." className="input" value={query} onChange={e=>setQuery(e.target.value)} />
      </div>
      {filtered.map(u => (
        <div key={u.id} className="card mb-2 flex justify-between">
          <div>
            <div className="font-semibold">{u.name}</div>
            <div className="text-sm text-slate-500">{u.email} • {u.role}</div>
          </div>
          <div className="flex gap-2">
            {!u.verified && <button onClick={()=>approve(u.id)} className="px-3 py-1 rounded-md bg-primary text-white">Approve</button>}
            <button onClick={()=>reject(u.id)} className="px-3 py-1 rounded-md border">Reject</button>
          </div>
        </div>
      ))}
    </div>
  )
}
