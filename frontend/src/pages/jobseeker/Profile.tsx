import React, { useEffect, useState } from 'react'
import client from '../../api/client'

export default function Profile(){
  const [profile, setProfile] = useState<any>(null)
  const [editing, setEditing] = useState(false)
  const [loading, setLoading] = useState(true)

  useEffect(()=> {
    (async ()=> {
      try {
        const r = await client.get('/api/jobseeker/profile')
        setProfile(r.data)
      } catch (err) {
        console.error(err)
      } finally { setLoading(false) }
    })()
  }, [])

  const save = async () => {
    try {
      await client.put('/api/jobseeker/profile', profile)
      alert('Saved')
      setEditing(false)
    } catch (err:any) {
      alert(err?.response?.data?.message || 'Save failed')
    }
  }

  if (loading) return <div>Loading...</div>

  return (
    <div className="card">
      <h2 className="text-xl font-semibold mb-3">Your profile</h2>
      <div className="space-y-3">
        <div>
          <label className="text-sm">Full name</label>
          <input value={profile?.name || ''} onChange={e => setProfile({...profile, name: e.target.value})} className="w-full mt-1 input" disabled={!editing}/>
        </div>
        <div>
          <label className="text-sm">Bio</label>
          <textarea value={profile?.bio || ''} onChange={e => setProfile({...profile, bio: e.target.value})} className="w-full mt-1 input" disabled={!editing}/>
        </div>
        <div className="flex gap-2">
          {editing ? (
            <>
              <button onClick={save} className="px-4 py-2 bg-primary text-white rounded-lg">Save</button>
              <button onClick={()=>setEditing(false)} className="px-4 py-2 rounded-lg border">Cancel</button>
            </>
          ) : (
            <button onClick={()=>setEditing(true)} className="px-4 py-2 rounded-lg border">Edit profile</button>
          )}
        </div>
      </div>
    </div>
  )
}
