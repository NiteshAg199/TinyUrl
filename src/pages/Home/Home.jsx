import React from "react";
import { useRef, useState } from 'react';
import { useDownloadExcel } from 'react-export-table-to-excel';
import { saveAs } from 'file-saver';
import * as XLSX from 'xlsx';
import CloseIcon from '@mui/icons-material/Close';
import ContentCopyIcon from '@mui/icons-material/ContentCopy';
import axios from 'axios';
const Home=()=>{
    const [url,setUrl] = useState("");
  const [shortenUrl,setShortenUrl] =useState("");
  const inputRef=useRef(null);
  function getTableData() {
    const table =inputRef.current;
    const rows = Array.prototype.slice.call(table.querySelectorAll('tr'));
    const cols= table.querySelectorAll('tbody tr td').length;
    const jsonData = rows.filter((e,i)=>{return i!==0?true:false})
    .map(e=>{
      const data = {};
  for(let ind=0;ind<cols;ind++){
    const key = rows[0].cells[ind].innerText; // First column as key
    const value = e.cells[ind].innerText; // Second column as value
    data[key] = value;
  }
  console.log(e);
  return data;
    });
    console.log(rows,cols,jsonData);
    return jsonData;
  }


  const downloadXls=()=>{
    let data=getTableData();
    console.log(data);
    const worksheet = XLSX.utils.json_to_sheet(data);
    const workbook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(workbook, worksheet, "Sheet1");
    XLSX.writeFile(workbook, "MYSavedData.xlsx");
  }
  
  const generateShortenUrl=(e)=>{
      e.preventDefault();
      let request={}
      request["actualUrl"]=url
      axios.post("http://localhost:5010/api/generateShortenUrl",request).then(function (response) {
        setShortenUrl(window.location.host+"/"+response.data.shortUrl);
      })
      .catch(function (error) {
        console.log(error);
      });
            
    //   setShortenUrl(window.location.host+"/");
  }
  
  function handleCopy() {
    // shortenUrl.select();
    // shortenUrl.setSelectionRange(0, 99999); // For mobile devices
    navigator.clipboard.writeText(shortenUrl);
    alert("Copied the text: " + shortenUrl);
  }
  const handleChange=(e)=>{
      setUrl(e.target.value);
      console.log(url);
  }
  const handleClose=()=>{
    console.log(url);
    setShortenUrl("");
  }
  return (
   <>
    <div className='heading'>TinyUrl generator</div>
    <div className='formContainer'>
    <div className='frmFieldContainer'>
    <label className='lblField' htmlFor="enteredUrl">Enter the URL</label>
    <input id="enteredUrl" type="text" className='inputUrl' value={url} onChange={handleChange}></input>
    </div>
    <div className='frmBtnContainer'>
    <button className='generateBtn' onClick={generateShortenUrl}>Generate ShortenUrl</button>
    </div>
    <div className='generatedResultContainer'>
      <div className='generatedResult'>{shortenUrl}</div>
      <div className='shortenUrlBtnsContainer'>
        <CloseIcon onClick={handleClose}/>
        <ContentCopyIcon onClick={handleCopy}/>
      </div>
    </div>
    </div>
   </>
  );
}
export default Home;