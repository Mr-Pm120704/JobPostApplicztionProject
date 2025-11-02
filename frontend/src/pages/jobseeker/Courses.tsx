import React, { useEffect, useState } from 'react'
import client from '../../api/client'
import { loadStripe } from '@stripe/stripe-js'
import { STRIPE_PUBLISHABLE_KEY } from '../../config'

const stripePromise = loadStripe(STRIPE_PUBLISHABLE_KEY)

export default function Courses(){
  const [courses, setCourses] = useState<any[]>([])
  const [loading, setLoading] = useState(true)

  useEffect(()=> {
    (async ()=> {
      try {
        const r = await client.get('/api/courses')
        setCourses(r.data.courses || [])
      } finally { setLoading(false) }
    })()
  }, [])

  const subscribe = async (courseId:string) => {
    // create checkout session on backend
    try {
      const r = await client.post('/api/payment/create-session', { courseId })
      const sessionId = r.data.sessionId
      const stripe = await stripePromise
      if (!stripe) throw new Error('Stripe not configured')
      await stripe.redirectToCheckout({ sessionId })
    } catch (err:any) {
      alert(err?.response?.data?.message || 'Payment failed')
    }
  }

  if (loading) return <div>Loading...</div>

  return (
    <div>
      <h2 className="text-xl font-semibold mb-4">Courses</h2>
      <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
        {courses.map(c => (
          <div key={c.id} className="card">
            <div className="flex justify-between">
              <div>
                <h4 className="font-semibold">{c.title}</h4>
                <p className="text-sm text-slate-500">{c.description}</p>
              </div>
              <div className="text-right">
                <div className="text-sm text-slate-600">₹{c.price}</div>
                <button onClick={()=>subscribe(c.id)} className="mt-2 px-3 py-1 rounded-md bg-primary text-white">Enroll</button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  )
}
