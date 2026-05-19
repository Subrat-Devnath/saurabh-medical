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

    // add product modal
    const [showAddModal, setShowAddModal] = useState(false);

    // form
    const [productName, setProductName] = useState("");
    const [category, setCategory] = useState("");
    const [supplierName, setSupplierName] = useState("");

    const [totalQuantity, setTotalQuantity] = useState("");

    // pricing
    const [listPrice, setListPrice] = useState(""); // MRP
    const [buyPrice, setBuyPrice] = useState("");
    const [buyDiscount, setBuyDiscount] = useState("");
    const [sellPrice, setSellPrice] = useState("");
    const [sellDiscount, setSellDiscount] = useState("");

    // dates
    const [purchaseDate, setPurchaseDate] = useState("");
    const [expiryDate, setExpiryDate] = useState("");

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

            setProducts(data.products || []);
            setPageState(data.nextPageState);
            setHasNext(data.hasNext);

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
    const searchProduct = async (
        name: string,
        nextState: string | null = null,
        isNext: boolean = true
    ) => {

        try {

            setLoading(true);
            setError("");

            const token = localStorage.getItem("accessToken");

            const response = await fetch(
                `${API}/search-products-with-pagination?productName=${encodeURIComponent(name)}`,
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
                throw new Error("Search failed");
            }

            const data: ProductPageResponse = await response.json();

            setProducts(data.products || []);
            setPageState(data.nextPageState);
            setHasNext(data.hasNext);

            if (isNext && nextState) {
                setPageStateStack((prev) => [...prev, nextState]);
            }

        } catch (err: any) {

            setError(err.message || "Search error");

        } finally {

            setLoading(false);
        }
    };

    // ---------------- RESET FORM ----------------
    const resetForm = () => {

        setProductName("");
        setCategory("");
        setSupplierName("");

        setTotalQuantity("");

        setListPrice("");
        setBuyPrice("");
        setBuyDiscount("");
        setSellPrice("");
        setSellDiscount("");

        setPurchaseDate("");
        setExpiryDate("");
    };

    // ---------------- ADD PRODUCT ----------------
    const addProduct = async () => {

        try {

            setLoading(true);
            setError("");

            const token = localStorage.getItem("accessToken");

            // convert date -> epoch
            const purchaseEpoch =
                purchaseDate
                    ? new Date(purchaseDate).getTime()
                    : null;

            const expiryEpoch =
                expiryDate
                    ? new Date(expiryDate).getTime()
                    : null;

            const quantity = Number(totalQuantity);

            const payload = {

                productName,
                category,
                supplierName,

                totalQuantity: quantity,
                purchasedQuantity: quantity,
                remainingQuantity: quantity,
                soldQuantity: 0,

                // pricing
                listPrice: Number(listPrice), // MRP
                buyPrice: Number(buyPrice),
                buyDiscount: Number(buyDiscount),

                sellPrice: Number(sellPrice),
                sellDiscount: Number(sellDiscount),

                purchaseDate: purchaseEpoch,
                expiryDate: expiryEpoch,
            };

            const response = await fetch(
                `${API}/product`,
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                        Authorization: `Bearer ${token}`,
                    },
                    body: JSON.stringify(payload),
                }
            );

            if (!response.ok) {
                throw new Error("Failed to add product");
            }

            resetForm();

            setShowAddModal(false);

            fetchProducts(null, false);

        } catch (err: any) {

            setError(err.message || "Add product failed");

        } finally {

            setLoading(false);
        }
    };

    // ---------------- SEARCH HANDLER ----------------
    const handleSearch = () => {

        setPageState(null);
        setPageStateStack([]);

        if (searchText.trim() === "") {

            fetchProducts(null, false);

        } else {

            searchProduct(searchText, null, false);
        }
    };

    // initial load
    useEffect(() => {
        fetchProducts(null, false);
    }, []);

    // ---------------- NEXT PAGE ----------------
    const handleNext = () => {

        if (!hasNext || !pageState) {
            return;
        }

        if (searchText.trim() === "") {

            fetchProducts(pageState, true);

        } else {

            searchProduct(searchText, pageState, true);
        }
    };

    // ---------------- PREV PAGE ----------------
    const handlePrev = () => {

        const stack = [...pageStateStack];

        stack.pop();

        const prevState =
            stack.length > 0
                ? stack[stack.length - 1]
                : null;

        setPageStateStack(stack);

        if (searchText.trim() === "") {

            fetchProducts(prevState, false);

        } else {

            searchProduct(searchText, prevState, false);
        }
    };

    return (

        <div className="min-h-screen bg-black text-white p-6">

            {/* TOP BAR */}
            <div className="flex justify-between items-center mb-6">

                <button
                    onClick={() => navigate("/home")}
                    className="px-4 py-2 rounded-xl bg-white/10 border border-white/10 hover:bg-white/20"
                >
                    ← Back
                </button>

                <button
                    onClick={() => setShowAddModal(true)}
                    className="px-4 py-2 rounded-xl bg-green-600 hover:bg-green-500"
                >
                    + Add Product
                </button>

            </div>

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
                        className="px-4 py-2 rounded-xl bg-cyan-600 hover:bg-cyan-500"
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

            {/* ADD PRODUCT MODAL */}
            {showAddModal && (

                <div className="fixed inset-0 bg-black/70 flex justify-center items-center z-50">

                    <div className="bg-zinc-900 p-6 rounded-2xl w-[650px] border border-white/10">

                        <h2 className="text-2xl font-bold text-cyan-400 mb-6">
                            Add Product
                        </h2>

                        <div className="grid grid-cols-2 gap-4">

                            <input
                                placeholder="Product Name"
                                value={productName}
                                onChange={(e) => setProductName(e.target.value)}
                                className="px-4 py-2 rounded-xl bg-white/5 border border-white/10"
                            />

                            <input
                                placeholder="Category"
                                value={category}
                                onChange={(e) => setCategory(e.target.value)}
                                className="px-4 py-2 rounded-xl bg-white/5 border border-white/10"
                            />

                            <input
                                placeholder="Supplier Name"
                                value={supplierName}
                                onChange={(e) => setSupplierName(e.target.value)}
                                className="px-4 py-2 rounded-xl bg-white/5 border border-white/10"
                            />

                            <input
                                type="number"
                                placeholder="Total Quantity"
                                value={totalQuantity}
                                onChange={(e) => setTotalQuantity(e.target.value)}
                                className="px-4 py-2 rounded-xl bg-white/5 border border-white/10"
                            />

                            {/* MRP */}
                            <input
                                type="number"
                                placeholder="MRP / List Price"
                                value={listPrice}
                                onChange={(e) => setListPrice(e.target.value)}
                                className="px-4 py-2 rounded-xl bg-white/5 border border-white/10"
                            />

                            {/* BUY PRICE */}
                            <input
                                type="number"
                                placeholder="Buy Price"
                                value={buyPrice}
                                onChange={(e) => setBuyPrice(e.target.value)}
                                className="px-4 py-2 rounded-xl bg-white/5 border border-white/10"
                            />

                            {/* BUY DISCOUNT */}
                            <input
                                type="number"
                                placeholder="Buy Discount %"
                                value={buyDiscount}
                                onChange={(e) => setBuyDiscount(e.target.value)}
                                className="px-4 py-2 rounded-xl bg-white/5 border border-white/10"
                            />

                            {/* SELL PRICE */}
                            <input
                                type="number"
                                placeholder="Sell Price"
                                value={sellPrice}
                                onChange={(e) => setSellPrice(e.target.value)}
                                className="px-4 py-2 rounded-xl bg-white/5 border border-white/10"
                            />

                            {/* SELL DISCOUNT */}
                            <input
                                type="number"
                                placeholder="Sell Discount %"
                                value={sellDiscount}
                                onChange={(e) => setSellDiscount(e.target.value)}
                                className="px-4 py-2 rounded-xl bg-white/5 border border-white/10"
                            />

                            {/* PURCHASE DATE */}
                            <div>

                                <label className="text-sm text-gray-300">
                                    Purchase Date
                                </label>

                                <input
                                    type="date"
                                    value={purchaseDate}
                                    onChange={(e) => setPurchaseDate(e.target.value)}
                                    className="w-full mt-1 px-4 py-2 rounded-xl bg-white/5 border border-white/10"
                                />

                            </div>

                            {/* EXPIRY DATE */}
                            <div>

                                <label className="text-sm text-gray-300">
                                    Expiry Date
                                </label>

                                <input
                                    type="date"
                                    value={expiryDate}
                                    onChange={(e) => setExpiryDate(e.target.value)}
                                    className="w-full mt-1 px-4 py-2 rounded-xl bg-white/5 border border-white/10"
                                />

                            </div>

                        </div>

                        {/* ACTIONS */}
                        <div className="flex justify-end gap-3 mt-6">

                            <button
                                onClick={() => {
                                    resetForm();
                                    setShowAddModal(false);
                                }}
                                className="px-4 py-2 rounded-xl bg-gray-700 hover:bg-gray-600"
                            >
                                Cancel
                            </button>

                            <button
                                onClick={addProduct}
                                className="px-4 py-2 rounded-xl bg-green-600 hover:bg-green-500"
                            >
                                Save Product
                            </button>

                        </div>

                    </div>

                </div>

            )}

        </div>
    );
}

export default ProductsPage;