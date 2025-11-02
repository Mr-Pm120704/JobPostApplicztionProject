import React, { useEffect, useRef, useState } from 'react'
import io from 'socket.io-client'
import { SOCKET_BASE } from '../config'

export default function ChatWidget(){
  const [open, setOpen] = useState(false)
  const [messages, setMessages] = useState<{id:string, text:string, from:'user'|'bot'}[]>([])
  const socketRef = useRef<any>(null)
  const inputRef = useRef<HTMLInputElement|null>(null)

  useEffect(() => {
    // lazy connect when widget opened
    if (!open) return
    if (!socketRef.current) {
      const socket = io(SOCKET_BASE, { path: '/support/socket.io' })
      socket.on('connect', () => {
        console.log('chat connected')
      })
      socket.on('bot-message', (msg:any) => {
        setMessages(m => [...m, { id: Date.now().toString(), text: msg, from: 'bot' }])
      })
      socketRef.current = socket
    }
    return () => {}
  }, [open])

  const send = () => {
    const text = inputRef.current?.value?.trim()
    if (!text) return
    setMessages(m => [...m, { id: Date.now().toString(), text, from: 'user' }])
    if (socketRef.current) socketRef.current.emit('user-message', text)
    if (inputRef.current) inputRef.current.value = ''
  }

  return (
    <div className="fixed right-6 bottom-6 z-50">
      <div className="flex flex-col items-end">
        {open && (
          <div className="w-80 card mb-3">
            <div className="flex items-center justify-between mb-3">
              <strong>Support</strong>
              <button onClick={() => setOpen(false)} className="text-sm">Close</button>
            </div>
            <div className="h-56 overflow-y-auto mb-3 space-y-2">
              {messages.map(m => (
                <div key={m.id} className={m.from === 'user' ? 'text-right' : 'text-left'}>
                  <div className={`inline-block p-2 rounded-xl ${m.from === 'user' ? 'bg-slate-100' : 'bg-primary text-white'}`}>{m.text}</div>
                </div>
              ))}
            </div>
            <div className="flex gap-2">
              <input ref={inputRef} className="flex-1 input" placeholder="Type a message..." onKeyDown={e => e.key === 'Enter' && send()} />
              <button onClick={send} className="px-3 py-1 rounded-md bg-primary text-white">Send</button>
            </div>
          </div>
        )}
        <button onClick={() => setOpen(o => !o)} className="rounded-full w-14 h-14 shadow-lg bg-primary text-white flex items-center justify-center">
          💬
        </button>
      </div>
    </div>
  )
}
