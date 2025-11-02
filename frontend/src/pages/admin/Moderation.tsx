import React, { useEffect, useState } from 'react'
import client from '../../api/client'

export default function Moderation(){
  const [posts, setPosts] = useState<any[]>([])
  useEffect(()=> {
    (async ()=> {
      const r = await client.get('/api/admins/moderation')
      setPosts(r.data.posts || [])
    })()
  }, [])

  const remove = async (id:string) => {
    await client.delete(`/api/admins/posts/${id}`)
    setPosts(p => p.filter(x => x.id !== id))
  }

  return (
    <div>
      <h2 className="text-xl font-semibold mb-4">Content moderation</h2>
      {posts.map(p => (
        <div key={p.id} className="card mb-3 flex justify-between">
          <div>
            <div className="font-semibold">{p.title}</div>
            <div className="text-sm text-slate-500">{p.body}</div>
          </div>
          <div>
            <button onClick={()=>remove(p.id)} className="px-3 py-1 rounded-md border">Remove</button>
          </div>
        </div>
      ))}
    </div>
  )
}
