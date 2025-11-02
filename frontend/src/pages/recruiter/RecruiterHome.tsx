import React, { useEffect, useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import client from "../../api/client"; // Axios instance
import { AuthContext } from "../../contexts/AuthContext";

// --- Type Definitions ---
interface Application {
  id: number;
  jobSeekerId: number;
  jobSeekerName: string;
  jobSeekerEmail: string;
  jobId: number;
  jobTitle: string;
  jobType: string;
  recruiterEmail: string;
  status: "PENDING" | "ACCEPTED" | "REJECTED";
  appliedAt: string;
}

interface DashboardData {
  applications: Application[];
  jobsPosted: number;
}

export default function RecruiterHome() {
  const navigate = useNavigate();
  const { logout, user } = useContext(AuthContext);

  const recruiterEmail = user?.email || localStorage.getItem("email") || ""; // fallback

  const [data, setData] = useState<DashboardData | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  // ✅ Fetch recruiter dashboard dynamically
  useEffect(() => {
    if (!recruiterEmail) return;

    const fetchRecruiterData = async () => {
      try {
        setLoading(true);
        setError(null);

        // Fetch applications for this recruiter
        const applicationsRes = await client.get<Application[]>(
          `/api/applications/recruiter/${encodeURIComponent(recruiterEmail)}`
        );

        // Fetch job count (or derive from applications)
        const jobsRes = await client.get<{ totalJobs: number }>(
          `/api/dashboards/jobs?recruiterEmail=${encodeURIComponent(
            recruiterEmail
          )}`
        );

        const applications = applicationsRes.data || [];
        const jobsPosted = jobsRes.data?.totalJobs ?? 0;

        setData({ applications, jobsPosted });
      } catch (err: any) {
        console.error("Error fetching recruiter dashboard:", err);
        setError(
          err?.response?.data?.message ||
            "Failed to load recruiter dashboard data"
        );
      } finally {
        setLoading(false);
      }
    };

    fetchRecruiterData();
  }, [recruiterEmail]);

  // ✅ Handle logout
  const handleLogout = () => {
    logout?.();
    localStorage.removeItem("token");
    localStorage.removeItem("email");
    navigate("/login", { replace: true });
  };

  // ✅ Handle Post Job
  const handlePostJob = () => {
    navigate("/recruiter/post-job");
  };

  // --- UI Rendering ---
  if (loading) {
    return <div className="p-8 text-center">Loading dashboard...</div>;
  }

  if (error) {
    return (
      <div className="p-8 text-center text-red-600">
        ⚠️ {error}
      </div>
    );
  }

  const totalApplications = data?.applications?.length ?? 0;

  return (
    <div className="p-6">
      {/* --- Header --- */}
      <div className="flex justify-between items-center mb-6">
        <h2 className="text-2xl font-bold">Recruiter Dashboard</h2>
        <button
          onClick={handleLogout}
          className="px-4 py-2 bg-red-600 text-white rounded-lg hover:bg-red-700 transition"
        >
          Logout
        </button>
      </div>

      {/* --- Dashboard Summary Cards --- */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
        {/* --- Post Job --- */}
        <div className="card flex flex-col justify-between p-4 bg-white shadow rounded-2xl">
          <div>
            <h3 className="font-semibold text-lg">Post a Job</h3>
            <p className="text-sm text-gray-600 mt-2">
              Create and publish new job openings for candidates.
            </p>
          </div>
          <button
            onClick={handlePostJob}
            className="mt-4 px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition"
          >
            + Post a Job
          </button>
        </div>

        {/* --- Applications Received --- */}
        <div className="card p-4 bg-white shadow rounded-2xl">
          <h3 className="font-semibold text-lg">Applications Received</h3>
          <p className="text-3xl font-bold mt-2">{totalApplications}</p>
        </div>

        {/* --- Jobs Posted --- */}
        <div className="card p-4 bg-white shadow rounded-2xl">
          <h3 className="font-semibold text-lg">Jobs Posted</h3>
          <p className="text-3xl font-bold mt-2">{data?.jobsPosted ?? 0}</p>
        </div>
      </div>

      {/* --- Applications Table --- */}
      <div className="card bg-white p-6 shadow rounded-2xl">
        <h3 className="text-xl font-bold mb-4">Recent Applications</h3>
        {totalApplications === 0 ? (
          <p className="text-gray-500 text-sm">No applications found.</p>
        ) : (
          <div className="overflow-x-auto">
            <table className="w-full text-left border-collapse">
              <thead className="border-b bg-gray-50">
                <tr>
                  <th className="py-2 px-3">Job Title</th>
                  <th className="py-2 px-3">Job Type</th>
                  <th className="py-2 px-3">Job Seeker</th>
                  <th className="py-2 px-3">Email</th>
                  <th className="py-2 px-3">Status</th>
                  <th className="py-2 px-3">Applied At</th>
                </tr>
              </thead>
              <tbody>
                {data?.applications?.map((app) => (
                  <tr
                    key={app.id}
                    className="border-b hover:bg-gray-50 transition"
                  >
                    <td className="py-2 px-3">{app.jobTitle}</td>
                    <td className="py-2 px-3">{app.jobType}</td>
                    <td className="py-2 px-3">{app.jobSeekerName}</td>
                    <td className="py-2 px-3">{app.jobSeekerEmail}</td>
                    <td className="py-2 px-3">
                      <span
                        className={`px-3 py-1 rounded text-sm ${
                          app.status === "ACCEPTED"
                            ? "bg-green-100 text-green-800"
                            : app.status === "REJECTED"
                            ? "bg-red-100 text-red-800"
                            : "bg-yellow-100 text-yellow-800"
                        }`}
                      >
                        {app.status}
                      </span>
                    </td>
                    <td className="py-2 px-3">
                      {new Date(app.appliedAt).toLocaleString()}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
}
