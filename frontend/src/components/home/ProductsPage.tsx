import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

type Product = {
    productName: string;
    category: string;
};

function ProductsPage() {
    const [products, setProducts] = useState<Product[]>([]);
    const [searchText, setSearchText] = useState("");

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    const [page, setPage] = useState(0);
    const pageSize = 5;

    const API = "http://127.0.0.1:8079/product-mgmt/api/v1";
    const navigate = useNavigate();

    // ---------------- PRODUCTS (PAGINATION) ----------------
    const fetchProducts = async () => {
        try {
            setLoading(true);
            setError("");

            const token = localStorage.getItem("accessToken");

            const response = await fetch(`${API}/products-with-pagination`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`,
                },
                body: JSON.stringify({
                    startIndex: page * pageSize,
                    pageSize: pageSize,
                }),
            });

            if (!response.ok) throw new Error(`API Error: ${response.status}`);

            const data = await response.json();
            setProducts(Array.isArray(data) ? data : []);

        } catch (err: any) {
            setError(err.message || "Error loading products");
        } finally {
            setLoading(false);
        }
    };

    // ---------------- SEARCH API ----------------
    const searchProduct = async (name: string) => {
        try {
            setLoading(true);
            setError("");

            const token = localStorage.getItem("accessToken");

            const response = await fetch(
                `http://127.0.0.1:8079/product-mgmt/api/v1/products/${name}`,
                {
                    method: "GET",
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                }
            );

            if (!response.ok) throw new Error("Search failed");

            const data = await response.json();

            setProducts(Array.isArray(data) ? data : []);

        } catch (err: any) {
            setError(err.message || "Search error");
        } finally {
            setLoading(false);
        }
    };

    // ---------------- SEARCH HANDLER ----------------
    const handleSearch = () => {
        if (searchText.trim() === "") {
            fetchProducts(); // reset
        } else {
            searchProduct(searchText);
        }
    };

    useEffect(() => {
        fetchProducts();
    }, [page]);

    return (
        <div className="min-h-screen bg-black text-white p-6">

            {/* BACK */}
            <button
                onClick={() => navigate("/home")}
                className="mb-6 px-4 py-2 rounded-xl bg-white/10 border border-white/10 hover:bg-white/20"
            >
                ← Back
            </button>

            <h1 className="text-3xl font-bold text-cyan-400 mb-6">
                Manage Products
            </h1>

            {/* 🔍 SEARCH BOX (RIGHT SIDE SMALL) */}
            <div className="flex justify-end mb-6">
                <div className="flex gap-2 items-center">
                    <input
                        type="text"
                        placeholder="Search product..."
                        value={searchText}
                        onChange={(e) => setSearchText(e.target.value)}
                        className="w-64 px-4 py-2 rounded-xl bg-white/5 border border-white/10 focus:outline-none focus:border-cyan-400"
                    />

                    <button
                        onClick={handleSearch}
                        disabled={searchText.trim() === ""}
                        className={`px-4 py-2 rounded-xl transition 
        ${searchText.trim() === ""
                                ? "bg-gray-600 cursor-not-allowed opacity-50"
                                : "bg-cyan-600 hover:bg-cyan-500"
                            }`}
                    >
                        Search
                    </button>
                </div>
            </div>

            {error && (
                <p className="text-red-400 mb-4">{error}</p>
            )}

            {/* PRODUCTS (UNCHANGED UI + CLICKABLE NAME KEPT) */}
            <div className="grid gap-4">
                {products.map((p, index) => (
                    <div
                        key={index}
                        className="bg-white/5 border border-white/10 rounded-2xl p-4 flex justify-between"
                    >
                        {/* CLICKABLE PRODUCT NAME (UNCHANGED) */}
                        <button
                            onClick={() =>
                                navigate(`/purchase-history/${p.productName}`)
                            }
                            className="text-left"
                        >
                            <h2 className="text-lg font-semibold text-cyan-300 hover:text-cyan-400 transition">
                                {p.productName}
                            </h2>
                        </button>

                        <div className="text-right text-purple-400 font-medium">
                            Product Type: {p.category}
                        </div>
                    </div>
                ))}
            </div>

            {/* PAGINATION (UNCHANGED) */}
            <div className="flex justify-center gap-4 mt-8">
                <button
                    disabled={page === 0}
                    onClick={() => setPage(page - 1)}
                    className="px-4 py-2 bg-gray-700 rounded-xl disabled:opacity-40"
                >
                    Prev
                </button>

                <button
                    onClick={() => setPage(page + 1)}
                    className="px-4 py-2 bg-cyan-600 rounded-xl"
                >
                    Next
                </button>
            </div>

        </div>
    );
}

export default ProductsPage;