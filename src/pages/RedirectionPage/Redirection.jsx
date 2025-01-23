import axios from "axios";
import React, { useEffect,useState } from "react";
import { useParams } from "react-router-dom";

function RedirectionPage(){
    let { url } = useParams();
    console.log(url);
    const [isRedirecting, setIsRedirecting] = useState(true);
    useEffect(
        ()=>{
           axios.get(`http://localhost:5010/api/${url}`).then((response)=>{
            // console.log(response.data.actualUrl);
            if(response.data.actualUrl!=="not avaliable") window.location.href = response.data.actualUrl
            else setIsRedirecting(false);
            }).catch(function (error) {
                console.log(error);
              })
        },[]
    )
    if (isRedirecting) {
        return null; // Prevent rendering the page
    }
    return(
        <>
        <h1>
            hi look at me
        </h1>
        </>
    )
}
export default RedirectionPage;