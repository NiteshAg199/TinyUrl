import './App.css';
import { useRef, useState } from 'react';
import { useDownloadExcel } from 'react-export-table-to-excel';
import { saveAs } from 'file-saver';
import * as XLSX from 'xlsx';
import CloseIcon from '@mui/icons-material/Close';
import ContentCopyIcon from '@mui/icons-material/ContentCopy';
import axios from 'axios';
import Home from './pages/Home/Home';
import RedirectionPage from './pages/RedirectionPage/Redirection';
import { Routes,Route } from 'react-router-dom';
function App() {

  return (
    <>
    <Routes>
      <Route path="/" element={<Home/>}></Route>
      <Route path ="/:url" element={<RedirectionPage/>}/>
    </Routes>
    
    
    </>
  )
}

export default App;
