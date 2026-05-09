import { useNavigate } from "react-router-dom"
import { useState } from "react"

import { Card, CardContent } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Button } from "@/components/ui/button"
import { Label } from "@/components/ui/label"

import { Eye, EyeOff } from "lucide-react"

function Signup() {

    const navigate = useNavigate()

    const [showPassword, setShowPassword] = useState(false)

    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")

    const handleSignup = async (
        e: React.FormEvent
    ) => {

        e.preventDefault()

        console.log({
            email,
            password,
        })

        /*
            Call signup API here
        */
    }

    return (

        <div className="min-h-screen bg-black text-white relative overflow-hidden flex items-center justify-center px-6 py-10">

            {/* Background Effects */}
            <div className="absolute inset-0 bg-[radial-gradient(circle_at_top_left,#06b6d4_0%,transparent_25%),radial-gradient(circle_at_bottom_right,#7c3aed_0%,transparent_25%)] opacity-30" />

            <div className="absolute w-[500px] h-[500px] bg-cyan-500/20 blur-3xl rounded-full -top-32 -left-20" />

            <div className="absolute w-[500px] h-[500px] bg-purple-500/20 blur-3xl rounded-full -bottom-32 -right-20" />

            <Card className="relative z-10 w-full max-w-md bg-white/5 border-white/10 backdrop-blur-2xl text-white rounded-3xl">

                <CardContent className="p-8">

                    <div className="text-center mb-8">

                        <div className="inline-flex items-center justify-center w-20 h-20 rounded-3xl bg-gradient-to-br from-cyan-400 to-blue-600 shadow-lg shadow-cyan-500/40 mb-5">

                            <span className="text-3xl">
                                ✚
                            </span>

                        </div>

                        <h1 className="text-4xl font-black tracking-tight bg-gradient-to-r from-cyan-400 via-blue-400 to-purple-500 bg-clip-text text-transparent">

                            Create Your Account

                        </h1>

                        <p className="text-gray-400 mt-3 text-sm leading-relaxed">

                            Create your medical management account and manage
                            inventory, billing, buying history, and medicine sales.

                        </p>

                    </div>

                    <form
                        className="space-y-6"
                        onSubmit={handleSignup}
                    >

                        {/* Email */}
                        <div className="space-y-2">

                            <Label>Email ID</Label>

                            <Input
                                type="email"
                                placeholder="Enter your email"
                                value={email}
                                onChange={(e) =>
                                    setEmail(e.target.value)
                                }
                                className="bg-black/40 border-white/10 h-12"
                            />

                        </div>

                        {/* Password */}
                        <div className="space-y-2">

                            <Label>Password</Label>

                            <div className="relative">

                                <Input
                                    type={
                                        showPassword
                                            ? "text"
                                            : "password"
                                    }
                                    placeholder="Enter your password"
                                    value={password}
                                    onChange={(e) =>
                                        setPassword(e.target.value)
                                    }
                                    className="bg-black/40 border-white/10 h-12 pr-12"
                                />

                                <Button
                                    type="button"
                                    variant="ghost"
                                    size="icon"
                                    onClick={() =>
                                        setShowPassword(!showPassword)
                                    }
                                    className="absolute right-1 top-1/2 -translate-y-1/2 hover:bg-transparent"
                                >

                                    {
                                        showPassword
                                            ? <EyeOff size={18} />
                                            : <Eye size={18} />
                                    }

                                </Button>

                            </div>

                        </div>

                        {/* Submit */}
                        <Button
                            type="submit"
                            className="w-full h-12 text-lg font-bold bg-gradient-to-r from-cyan-500 to-blue-600 hover:from-cyan-400 hover:to-blue-500"
                        >

                            Create Account

                        </Button>

                    </form>

                    {/* Divider */}
                    <div className="relative my-8">

                        <div className="absolute inset-0 flex items-center">

                            <div className="w-full border-t border-white/10" />

                        </div>

                        <div className="relative flex justify-center text-sm">

                            <span className="bg-[#050816] px-4 text-gray-500">

                                Secure Medical Platform

                            </span>

                        </div>

                    </div>

                    {/* Stats */}
                    <div className="grid grid-cols-3 gap-4 text-center">

                        <div className="bg-white/5 border border-white/10 rounded-2xl p-4">

                            <p className="text-cyan-400 text-2xl font-bold">

                                24/7

                            </p>

                            <p className="text-gray-400 text-xs mt-1">

                                Support

                            </p>

                        </div>

                        <div className="bg-white/5 border border-white/10 rounded-2xl p-4">

                            <p className="text-purple-400 text-2xl font-bold">

                                Data

                            </p>

                            <p className="text-gray-400 text-xs mt-1">

                                Analytics

                            </p>

                        </div>

                        <div className="bg-white/5 border border-white/10 rounded-2xl p-4">

                            <p className="text-green-400 text-2xl font-bold">

                                100%

                            </p>

                            <p className="text-gray-400 text-xs mt-1">

                                Secure

                            </p>

                        </div>

                    </div>

                    {/* Login Link */}
                    <p className="text-center text-gray-400 text-sm mt-8">

                        Already have an account?

                        <Button
                            variant="link"
                            type="button"
                            onClick={() => navigate("/login")}
                            className="text-cyan-400"
                        >

                            Sign In

                        </Button>

                    </p>

                </CardContent>

            </Card>

        </div>
    )
}

export default Signup