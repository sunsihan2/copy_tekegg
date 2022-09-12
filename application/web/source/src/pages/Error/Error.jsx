import React, { useEffect , useState} from 'react'
import { useLocation } from 'react-router-dom'

const Error = () => {
  const location = useLocation()
  let isUnhandledException = location.state?.isUnhandledException
  const [errorMessage, setErrorMessage] = useState("404 Page Not Found")
  useEffect(()=> {
    if(isUnhandledException){
      setErrorMessage("Unhandled Error Exception")
    }
  },[])
  return (
    <div id="error" className='wrapper-404'>
      <div className='blue-screen'>
        <h3 >

        </h3>
      </div>
        <h2 id="404-error-message">404 error</h2>
    </div>
  )
}

export default Error