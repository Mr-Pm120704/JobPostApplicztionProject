export default function DashboardHome({ data }: { data: DashboardData }) {
  return (
    <div>
      <h2 className="text-2xl font-bold mb-6">My Dashboard</h2>
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        <div className="card">
          <h3 className="font-semibold">Quick Actions</h3>
          <ul className="mt-3 text-sm text-slate-600">
            <li>🔍 Search jobs</li>
            <li>❤️ View saved jobs</li>
            <li>📄 Manage applications</li>
          </ul>
        </div>
        <div className="card md:col-span-2">
          <h3 className="font-semibold">Applications Summary</h3>
          <p className="text-sm text-slate-500">You have applied to {data.applications?.total ?? 0} jobs.</p>
        </div>
      </div>
    </div>
  )
}
