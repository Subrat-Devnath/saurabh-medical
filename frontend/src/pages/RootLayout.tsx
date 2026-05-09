
import { Outlet } from 'react-router'

import Navbar from "../components/Navbar"

function ReactLayout() {
  return (
    <div>
     {/* <Navbar /> */}
      <Outlet />

    </div>
  )
}

export default ReactLayout