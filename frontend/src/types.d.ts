export type Role = 'jobseeker' | 'recruiter' | 'admin'

export interface User {
  id: string
  name: string
  email: string
  role: Role
  avatar?: string
  verified?: boolean
  bio?: string
  // other profile fields...
}
