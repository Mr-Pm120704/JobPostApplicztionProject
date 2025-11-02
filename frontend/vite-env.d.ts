/// <reference types="vite/client" />

interface ImportMetaEnv {
  readonly VITE_API_BASE: string
  readonly VITE_SOCKET_BASE: string
  readonly VITE_STRIPE_KEY: string
}

interface ImportMeta {
  readonly env: ImportMetaEnv
}
