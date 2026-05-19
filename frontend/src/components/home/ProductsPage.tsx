import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

type Product = {
    productName: string;
    category: string;
};

type ProductPageResponse = {
    products: Product[];
    nextPageState: string | null;
    hasNext: boolean;
};

function ProductsPage() {

    const [products, setProducts] = useState<Product[]>([]);
    const [searchText, setSearchText] = useState("");

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    // Cassandra paging state
    const [pageState, setPageState] = useState<string | null>(null);

    // history for prev button
    const [pageStateStack, setPageStateStack] = useState<string[]>([]);

    const [hasNext, setHasNext] = useState(false);

    const pageSize = 5;

    const API = "http://127.0.0.1:8079/product-mgmt/api/v1";

    const navigate = useNavigate();

    // ---------------- FETCH PRODUCTS ----------------
    const fetchProducts = async (
        nextState: string | null = null,
        isNext: boolean = true
    ) => {

        try {

            setLoading(true);
            setError("");

            const token = localStorage.getItem("accessToken");

            const response = await fetch(
                `${API}/products-with-pagination`,
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                        Authorization: `Bearer ${token}`,
                    },
                    body: JSON.stringify({
                        pageSize: pageSize,
                        pageState: nextState,
                    }),
                }
            );

            if (!response.ok) {
                throw new Error(`API Error: ${response.status}`);
            }

            const data: ProductPageResponse = await response.json();

            // populate grid
            setProducts(data.products || []);

            // save next page state
            setPageState(data.nextPageState);

            // next button control
            setHasNext(data.hasNext);

            // maintain history for prev button
            if (isNext && nextState) {
                setPageStateStack((prev) => [...prev, nextState]);
            }

        } catch (err: any) {

            setError(err.message || "Error loading products");

        } finally {

            setLoading(false);
        }
    };

    // ---------------- SEARCH PRODUCT ----------------
    const searchProduct = async (name: string) => {

        try {

            setLoading(true);
            setError("");

            const token = localStorage.getItem("accessToken");

            const response = await fetch(
                `${API}/products/${name}`,
                {
                    method: "GET",
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                }
            );

            if (!response.ok) {
                throw new Error("Search failed");
            }

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

            fetchProducts(null, false);

        } else {

            searchProduct(searchText);
        }
    };

    // initial load
    useEffect(() => {
        fetchProducts(null, false);
    }, []);

    // ---------------- NEXT PAGE ----------------
    const handleNext = () => {

        if (hasNext && pageState) {
            fetchProducts(pageState, true);
        }
    };

    // ---------------- PREV PAGE ----------------
    const handlePrev = () => {

        const stack = [...pageStateStack];

        // remove current
        stack.pop();

        const prevState =
            stack.length > 0 ? stack[stack.length - 1] : null;

        setPageStateStack(stack);

        fetchProducts(prevState, false);
    };

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

            {/* SEARCH */}
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

            {/* ERROR */}
            {error && (
                <p className="text-red-400 mb-4">{error}</p>
            )}

            {/* LOADING */}
            {loading && (
                <p className="text-cyan-400 mb-4">Loading...</p>
            )}

            {/* PRODUCTS */}
            <div className="grid gap-4">

                {products.map((p, index) => (

                    <div
                        key={index}
                        className="bg-white/5 border border-white/10 rounded-2xl p-4 flex justify-between"
                    >

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

            {/* PAGINATION */}
            <div className="flex justify-center gap-4 mt-8">

                <button
                    disabled={pageStateStack.length === 0}
                    onClick={handlePrev}
                    className="px-4 py-2 bg-gray-700 rounded-xl disabled:opacity-40"
                >
                    Prev
                </button>

                <button
                    disabled={!hasNext}
                    onClick={handleNext}
                    className="px-4 py-2 bg-cyan-600 rounded-xl disabled:opacity-40"
                >
                    Next
                </button>

            </div>

        </div>
    );
}

export default ProductsPage;