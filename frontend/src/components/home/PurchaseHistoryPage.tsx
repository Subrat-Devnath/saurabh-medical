import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

type PurchaseHistory = {
    productName: string;
    purchaseDate: number;
    listPrice: number;
    buyPrice: number;
    buyDiscount: number;
    sellPrice: number;
    sellDiscount: number;
    purchasedQuantity: number;
    remainingQuantity: number;
    soldQuantity: number;
    expiryDate: number;
    supplierName: string;
};

function PurchaseHistoryPage() {
    const navigate = useNavigate();
    const { productName } = useParams();

    const [history, setHistory] = useState<PurchaseHistory[]>([]);
    const [loading, setLoading] = useState(false);

    const API = "http://127.0.0.1:8079/product-mgmt/api/v1";

    const formatDate = (timestamp?: number) => {
        if (!timestamp) return "-";
        return new Date(timestamp).toLocaleDateString("en-IN", {
            day: "2-digit",
            month: "short",
            year: "numeric",
        });
    };

    const fetchHistory = async () => {
        try {
            setLoading(true);

            const token = localStorage.getItem("accessToken");

            const response = await fetch(
                `${API}/prices/${productName}`,
                {
                    method: "GET",
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                }
            );

            if (!response.ok) throw new Error("Failed to fetch history");

            const data = await response.json();
            setHistory(Array.isArray(data) ? data : []);
        } catch (err) {
            console.error(err);
            setHistory([]);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        if (productName) fetchHistory();
    }, [productName]);

    return (
        <div className="min-h-screen bg-black text-white p-6">

            {/* BACK BUTTON */}
            <button
                onClick={() => navigate("/products")}
                className="mb-6 px-4 py-2 rounded-xl bg-white/10 border border-white/10 hover:bg-white/20 transition"
            >
                ← Back
            </button>

            <h1 className="text-3xl font-bold text-green-400 mb-2">
                Purchase History
            </h1>

            <h2 className="text-lg text-cyan-300 mb-6">
                Product: {productName}
            </h2>

            {/* LOADING */}
            {loading && (
                <p className="text-gray-400">Loading history...</p>
            )}

            {/* LIST */}
            <div className="grid gap-6">

                {history.map((h, i) => (
                    <div
                        key={i}
                        className="bg-white/5 border border-white/10 rounded-2xl p-5 hover:border-cyan-500/30 transition"
                    >

                        {/* TOP ROW */}
                        <div className="flex justify-between items-center mb-4">

                            <div>
                                <p className="text-gray-400 text-sm">Purchase Date</p>
                                <p className="text-white font-medium">
                                    {formatDate(h.purchaseDate)}
                                </p>
                            </div>

                            <div>
                                <p className="text-gray-400 text-sm">Expiry Date</p>
                                <p className="text-red-300 font-medium">
                                    {formatDate(h.expiryDate)}
                                </p>
                            </div>

                        </div>

                        {/* PRICE GRID */}
                        <div className="grid grid-cols-2 md:grid-cols-4 gap-4 text-sm">

                            <div>
                                <p className="text-gray-400">MRP</p>
                                <p className="text-white font-semibold">₹{h.listPrice}</p>
                            </div>

                            <div>
                                <p className="text-gray-400">Buy Price</p>
                                <p className="text-cyan-300 font-semibold">₹{h.buyPrice}</p>
                            </div>

                            <div>
                                <p className="text-gray-400">Buy Discount</p>
                                <p className="text-green-300 font-semibold">{h.buyDiscount}%</p>
                            </div>

                            <div>
                                <p className="text-gray-400">Sell Price</p>
                                <p className="text-purple-300 font-semibold">₹{h.sellPrice}</p>
                            </div>

                            <div>
                                <p className="text-gray-400">Sell Discount</p>
                                <p className="text-yellow-300 font-semibold">{h.sellDiscount}%</p>
                            </div>

                            <div>
                                <p className="text-gray-400">Purchased Qty</p>
                                <p className="text-white font-semibold">{h.purchasedQuantity}</p>
                            </div>

                            <div>
                                <p className="text-gray-400">Sold Qty</p>
                                <p className="text-pink-300 font-semibold">{h.soldQuantity ?? 0}</p>
                            </div>

                            <div>
                                <p className="text-gray-400">Remaining</p>
                                <p className="text-cyan-400 font-semibold">{h.remainingQuantity}</p>
                            </div>

                            <div>
                                <p className="text-gray-400">Supplier Name</p>
                                <p className="text-orange-300 font-semibold">
                                    {h.supplierName || "-"}
                                </p>
                            </div>

                        </div>

                    </div>
                ))}

            </div>

        </div>
    );
}

export default PurchaseHistoryPage;