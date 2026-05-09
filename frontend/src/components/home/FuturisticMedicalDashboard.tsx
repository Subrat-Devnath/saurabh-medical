export default function FuturisticMedicalDashboard() {
  const buyingHistory = [
    {
      id: 1,
      medicine: "Paracetamol 650mg",
      quantity: "2 Strips",
      supplier: "HealthCare Pharma",
      date: "08 May 2026",
      amount: "₹240",
      status: "Delivered",
    },
    {
      id: 2,
      medicine: "Vitamin D3",
      quantity: "1 Bottle",
      supplier: "Medilife",
      date: "06 May 2026",
      amount: "₹580",
      status: "In Transit",
    },
    {
      id: 3,
      medicine: "Azithromycin",
      quantity: "5 Boxes",
      supplier: "Apollo Distributor",
      date: "02 May 2026",
      amount: "₹1,240",
      status: "Delivered",
    },
  ]

  const sellingHistory = [
    {
      id: 1,
      customer: "Rahul Sharma",
      medicine: "Insulin Pen",
      quantity: "2 Units",
      amount: "₹1,850",
      payment: "UPI",
      date: "09 May 2026",
    },
    {
      id: 2,
      customer: "Neha Patil",
      medicine: "Dolo 650",
      quantity: "3 Strips",
      amount: "₹180",
      payment: "Cash",
      date: "08 May 2026",
    },
    {
      id: 3,
      customer: "Sanjay Verma",
      medicine: "BP Monitor",
      quantity: "1 Device",
      amount: "₹2,450",
      payment: "Card",
      date: "06 May 2026",
    },
  ]

  const stats = [
    {
      title: "Today's Revenue",
      value: "₹24,500",
      growth: "+12%",
    },
    {
      title: "Medicines Sold",
      value: "1,284",
      growth: "+18%",
    },
    {
      title: "Stock Alerts",
      value: "14",
      growth: "Low Stock",
    },
    {
      title: "Active Customers",
      value: "842",
      growth: "+8%",
    },
  ]

  return (
    <div className="min-h-screen bg-black text-white overflow-hidden relative">
      <div className="absolute inset-0 bg-[radial-gradient(circle_at_top_right,#0f766e_0%,transparent_25%),radial-gradient(circle_at_bottom_left,#1d4ed8_0%,transparent_25%)] opacity-30" />

      <div className="relative z-10">
        <header className="border-b border-white/10 backdrop-blur-xl bg-white/5 sticky top-0 z-50">
          <div className="max-w-7xl mx-auto px-6 py-4 flex items-center justify-between">
            <div>
              <h1 className="text-3xl font-bold tracking-tight bg-gradient-to-r from-cyan-400 to-blue-500 bg-clip-text text-transparent">
                Welcome to Saurabh Medical Dashboard
              </h1>
              <p className="text-sm text-gray-400 mt-1">
                Smart Medical Inventory & Sales Platform
              </p>
            </div>

            <div className="flex items-center gap-4">
              <input
                placeholder="Search medicines, orders, customers..."
                className="w-[320px] bg-white/10 border border-white/10 rounded-xl px-4 py-2 text-sm outline-none focus:border-cyan-500"
              />

              <button className="bg-cyan-500 hover:bg-cyan-400 transition px-5 py-2 rounded-xl font-medium text-black">
                + Add Medicine
              </button>
            </div>
          </div>
        </header>

        <section className="max-w-7xl mx-auto px-6 pt-12 pb-10">
          <div className="grid lg:grid-cols-2 gap-10 items-center">
            <div>
              
              <h2 className="text-6xl font-black leading-tight tracking-tight">
                The Future of
                <span className="block bg-gradient-to-r from-cyan-400 via-blue-400 to-purple-500 bg-clip-text text-transparent">
                  Medical Commerce
                </span>
              </h2>

              <p className="text-gray-400 text-lg mt-6 leading-relaxed max-w-2xl">
                Manage medicine inventory, customer billing, buying history,
                supplier tracking.
              </p>

              <div className="flex gap-4 mt-8">
                <button className="bg-gradient-to-r from-cyan-500 to-blue-500 px-6 py-3 rounded-2xl font-semibold hover:scale-105 transition-transform shadow-lg shadow-cyan-500/30">
                  Start Managing
                </button>

                <button className="border border-white/10 bg-white/5 hover:bg-white/10 px-6 py-3 rounded-2xl font-semibold transition">
                  View Analytics
                </button>
              </div>
            </div>

            <div className="relative">
              <div className="absolute inset-0 blur-3xl bg-cyan-500/20 rounded-full" />

              <div className="relative bg-white/5 border border-white/10 rounded-3xl p-8 backdrop-blur-2xl shadow-2xl">
                <div className="grid grid-cols-2 gap-5">
                  {stats.map((item, index) => (
                    <div
                      key={index}
                      className="bg-black/40 border border-white/10 rounded-2xl p-5 hover:border-cyan-500/50 transition"
                    >
                      <p className="text-sm text-gray-400">{item.title}</p>
                      <h3 className="text-3xl font-bold mt-3">{item.value}</h3>
                      <p className="text-cyan-400 mt-2 text-sm">
                        {item.growth}
                      </p>
                    </div>
                  ))}
                </div>
              </div>
            </div>
          </div>
        </section>

        <section className="max-w-7xl mx-auto px-6 py-8 grid lg:grid-cols-2 gap-8">
          <div className="bg-white/5 border border-white/10 rounded-3xl p-6 backdrop-blur-xl">
            <div className="flex items-center justify-between mb-6">
              <div>
                <h3 className="text-2xl font-bold text-cyan-400">
                  Buying History
                </h3>
                <p className="text-gray-400 text-sm mt-1">
                  Medicine purchases from suppliers
                </p>
              </div>

              <button className="bg-cyan-500/20 border border-cyan-500/30 px-4 py-2 rounded-xl text-cyan-300 text-sm hover:bg-cyan-500/30 transition">
                View All
              </button>
            </div>

            <div className="space-y-4">
              {buyingHistory.map((item) => (
                <div
                  key={item.id}
                  className="bg-black/40 border border-white/10 rounded-2xl p-5 hover:border-cyan-500/40 transition"
                >
                  <div className="flex justify-between items-start">
                    <div>
                      <h4 className="text-lg font-semibold">
                        {item.medicine}
                      </h4>
                      <p className="text-gray-400 text-sm mt-1">
                        Supplier: {item.supplier}
                      </p>
                    </div>

                    <span className="bg-green-500/10 text-green-400 px-3 py-1 rounded-full text-xs border border-green-500/20">
                      {item.status}
                    </span>
                  </div>

                  <div className="grid grid-cols-3 gap-4 mt-5 text-sm">
                    <div>
                      <p className="text-gray-500">Quantity</p>
                      <p className="font-medium mt-1">{item.quantity}</p>
                    </div>

                    <div>
                      <p className="text-gray-500">Date</p>
                      <p className="font-medium mt-1">{item.date}</p>
                    </div>

                    <div>
                      <p className="text-gray-500">Amount</p>
                      <p className="font-medium mt-1 text-cyan-400">
                        {item.amount}
                      </p>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          </div>

          <div className="bg-white/5 border border-white/10 rounded-3xl p-6 backdrop-blur-xl">
            <div className="flex items-center justify-between mb-6">
              <div>
                <h3 className="text-2xl font-bold text-purple-400">
                  Selling History
                </h3>
                <p className="text-gray-400 text-sm mt-1">
                  Recent medicine sales to customers
                </p>
              </div>

              <button className="bg-purple-500/20 border border-purple-500/30 px-4 py-2 rounded-xl text-purple-300 text-sm hover:bg-purple-500/30 transition">
                Export Report
              </button>
            </div>

            <div className="space-y-4">
              {sellingHistory.map((item) => (
                <div
                  key={item.id}
                  className="bg-black/40 border border-white/10 rounded-2xl p-5 hover:border-purple-500/40 transition"
                >
                  <div className="flex justify-between items-start">
                    <div>
                      <h4 className="text-lg font-semibold">
                        {item.medicine}
                      </h4>
                      <p className="text-gray-400 text-sm mt-1">
                        Customer: {item.customer}
                      </p>
                    </div>

                    <span className="bg-blue-500/10 text-blue-400 px-3 py-1 rounded-full text-xs border border-blue-500/20">
                      {item.payment}
                    </span>
                  </div>

                  <div className="grid grid-cols-3 gap-4 mt-5 text-sm">
                    <div>
                      <p className="text-gray-500">Quantity</p>
                      <p className="font-medium mt-1">{item.quantity}</p>
                    </div>

                    <div>
                      <p className="text-gray-500">Date</p>
                      <p className="font-medium mt-1">{item.date}</p>
                    </div>

                    <div>
                      <p className="text-gray-500">Amount</p>
                      <p className="font-medium mt-1 text-purple-400">
                        {item.amount}
                      </p>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          </div>
        </section>

        <section className="max-w-7xl mx-auto px-6 pb-16">
          <div className="bg-gradient-to-r from-cyan-500/10 to-purple-500/10 border border-white/10 rounded-3xl p-8 backdrop-blur-xl">
            <div className="grid lg:grid-cols-3 gap-6">
              <div className="bg-black/30 border border-white/10 rounded-2xl p-6">
                <h4 className="text-xl font-bold mb-4">AI Insights</h4>
                <p className="text-gray-400 leading-relaxed">
                  Smart prediction engine detects medicine demand trends and
                  recommends restocking automatically.
                </p>
              </div>

              <div className="bg-black/30 border border-white/10 rounded-2xl p-6">
                <h4 className="text-xl font-bold mb-4">Live Inventory</h4>
                <p className="text-gray-400 leading-relaxed">
                  Track medicine availability, expiry alerts, and supplier stock
                  status in real-time.
                </p>
              </div>

              <div className="bg-black/30 border border-white/10 rounded-2xl p-6">
                <h4 className="text-xl font-bold mb-4">Secure Billing</h4>
                <p className="text-gray-400 leading-relaxed">
                  Generate GST invoices, digital receipts, and customer reports
                  instantly with advanced security.
                </p>
              </div>
            </div>
          </div>
        </section>
      </div>
    </div>
  )
}
