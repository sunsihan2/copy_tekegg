import {Route} from "react-router-dom"

class ErrorBoundary extends React.Component{
    constructor(props){
        super(props)
        this.state - {hasError: false}
    }

    componentDidCatch(error, info){
        this.setState ({hasError: true})
    }

    render(){
        if(this.state.hasError){
            <Navigate to="/error" replace />
        }
    }
}